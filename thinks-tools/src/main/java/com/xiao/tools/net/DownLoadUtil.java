package com.xiao.tools.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiao.tools.constant.SystemUtil;
import com.xiao.tools.string.StringUtil;

/**
 * 下载工具类
 * 
 * @author XiaoJinRong
 * @times 2018年11月26日 上午11:52:52
 * @version 1.0
 */
public class DownLoadUtil {

	/**
	 * 下载二级制文件
	 * 
	 * @param filePath
	 * @param request
	 * @param response
	 */
	public static void downFile(String filePath, HttpServletRequest request, HttpServletResponse response) {
		File file = new File(filePath);
		response.addHeader("Content-Disposition",
				"inline;filename=" + DownLoadUtil.parseIEString(file.getName(), request));
		response.setContentType("application/octet-stream");
		try (InputStream in = new FileInputStream(file); OutputStream out = response.getOutputStream();) {
			byte[] b = new byte[4096];
			int len = -1;
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 下载时将字符串转为IE浏览器能识别的字符串防止乱码
	 * 
	 * @param fileName
	 * @param request
	 * @return
	 */
	public static String parseIEString(String fileName, HttpServletRequest request) {
		String result = fileName;
		if (StringUtil.isEmpty(result)) {
			return "";
		}
		try {
			String userAgent = request.getHeader("user-agent").toLowerCase();
			if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
				// win10 ie edge 浏览器 和其他系统的ie
				result = URLEncoder.encode(result, SystemUtil.Character.UTF_8);
			} else {
				// fe
				result = new String(result.getBytes(SystemUtil.Character.UTF_8), SystemUtil.Character.ISO_8859_1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
