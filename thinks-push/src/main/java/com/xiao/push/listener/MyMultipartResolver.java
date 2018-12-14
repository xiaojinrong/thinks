package com.xiao.push.listener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemHeaders;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadBase.FileUploadIOException;
import org.apache.commons.fileupload.FileUploadBase.IOFileUploadException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ParameterParser;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MyMultipartResolver extends CommonsMultipartResolver {

	@Resource
	private ProgressListeners progressListeners;

	@Override
	protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
		String encoding = determineEncoding(request);
		FileUpload fileUpload = prepareFileUpload(encoding);
		long currentTime = System.currentTimeMillis();
		progressListeners.setStartTime(currentTime);
		progressListeners.setNextTime(currentTime);
		fileUpload.setProgressListener(progressListeners);

		try {
			ServletFileUpload servletFileUpload = ((ServletFileUpload) fileUpload);
			List<FileItem> items = new ArrayList<FileItem>();
			boolean successful = false;
			try {
				ServletRequestContext requestContext = new ServletRequestContext(request);
				FileItemIterator iter = servletFileUpload.getItemIterator(requestContext);
				FileItemFactory fac = getFileItemFactory();
				if (fac == null) {
					throw new NullPointerException("No FileItemFactory has been set.");
				}
				while (iter.hasNext()) {
					final FileItemStream item = iter.next();
					final String fileName = item.getName();

					ParameterParser parser = new ParameterParser();
					parser.setLowerCaseNames(true);
					Map<String, String> params = parser.parse(item.getContentType(), new char[] { ';', ',' });
					String boundaryStr = params.get("boundary");

					if (boundaryStr == null) {
						return null;
					}
					byte[] boundary;
					try {
						boundary = boundaryStr.getBytes("ISO-8859-1");
					} catch (UnsupportedEncodingException e) {
						boundary = boundaryStr.getBytes();
					}

					System.out.println(boundary.length);

					progressListeners.getFileItems().add(fileName);
					FileItem fileItem = fac.createItem(item.getFieldName(), item.getContentType(), item.isFormField(),
							fileName);
					items.add(fileItem);
					try {
						Streams.copy(item.openStream(), fileItem.getOutputStream(), true);
					} catch (FileUploadIOException e) {
						throw (FileUploadException) e.getCause();
					} catch (IOException e) {
						throw new IOFileUploadException(e.getMessage(), e);
					}
					final FileItemHeaders fih = item.getHeaders();
					fileItem.setHeaders(fih);
				}
				successful = true;
			} catch (FileUploadIOException e) {
				throw (FileUploadException) e.getCause();
			} catch (IOException e) {
				throw new FileUploadException(e.getMessage(), e);
			} finally {
				if (!successful) {
					for (FileItem fileItem : items) {
						try {
							fileItem.delete();
						} catch (Throwable e) {
						}
					}
				}
			}
			return parseFileItems(items, encoding);
			/*
			 * List<FileItem> fileItems = servletFileUpload.parseRequest(request); return
			 * parseFileItems(fileItems, encoding);
			 */
		} catch (FileUploadBase.SizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
		} catch (FileUploadException ex) {
			throw new MultipartException("Could not parse multipart servlet request", ex);
		} catch (Exception ex) {
			throw new RuntimeException("running error", ex);
		}
	}

}
