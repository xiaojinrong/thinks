package com.xiao.tools.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiao.tools.number.NumberUtil;

/**
 * 下载文件工具类
 * 
 * @author XiaoJinRong
 * @times 2018年5月16日 上午11:47:18
 * @version 1.0
 */
public class DownFileUtil {

	private static final Logger logger = LoggerFactory.getLogger(DownFileThread.class);

	/**
	 * 根据URL下载资源文件
	 * 
	 * @param link
	 * @throws IOException
	 */
	public static void downMediaByUrl(String link, File file) throws IOException {
		OutputStream out = null;
		InputStream in = null;
		try {
			out = new FileOutputStream(file);
			URL url = new URL(link);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("Referer", "https://www.baidu.com/");
			in = conn.getInputStream();
			long size = conn.getContentLengthLong();
			byte[] b = new byte[1024 * 1024];
			int len = -1;
			int number = 0;
			// 文件大小
			while ((len = in.read(b)) != -1) {
				number += len;
				out.write(b, 0, len);
				logger.info(file.getName() + "大小{},当前下载大小{}，下载进度{}{}", NumberUtil.broadband(size),
						NumberUtil.broadband(number), NumberUtil.percent(number, size), NumberUtil.broadband(len));
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 保存文件数据
	 * 
	 * @param text
	 * @param file
	 * @throws IOException
	 */
	public static void downTextByUrl(String text, File file) throws IOException {
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(text.getBytes());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
