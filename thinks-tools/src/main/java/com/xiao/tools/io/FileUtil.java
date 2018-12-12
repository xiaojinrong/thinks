package com.xiao.tools.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

	/**
	 * 字符串生成TXT文件
	 * 
	 * @param text
	 * @return
	 */
	public static boolean writeFile(String path, String text) {
		boolean flag = false;
		createFilePath(path);
		File file = new File(path);
		try (FileWriter writer = new FileWriter(file);) {
			writer.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 字符串生成TXT文件
	 * 
	 * @param text
	 * @return
	 */
	public static boolean writeFile(String path, String text, boolean append) {
		boolean flag = false;
		createFilePath(path);
		File file = new File(path);
		try (FileWriter writer = new FileWriter(file, append);) {
			writer.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 读取TXT文件字符串
	 * 
	 * @param path
	 * @return
	 */
	public static String readText(String path) {
		File file = new File(path);
		StringBuffer sb = new StringBuffer();
		try (FileReader read = new FileReader(file);) {
			char[] c = new char[1024];
			int len = 0;
			while ((len = read.read(c)) != -1) {
				sb.append(new String(c, 0, len));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sb.toString().replaceAll("\\s*|\t|\r|\n", "");
	}

	/**
	 * 读取项目相对路径，如【/1.txt】
	 * 
	 * @param path
	 * @return
	 */
	public static String readSysText(String path) {
		StringBuffer sb = new StringBuffer();
		byte[] b = new byte[1024];
		int len = 0;
		try (InputStream in = Object.class.getResourceAsStream(path);) {
			while ((len = in.read(b)) != -1) {
				sb.append(new String(b, 0, len, "UTF-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 读取项目文件生成系统文件
	 * 
	 * @param sysPath
	 * @param osPath
	 * @return
	 */
	public static boolean readSysText(String sysPath, String osPath) {
		String text = readSysText(sysPath);
		return writeFile(osPath, text);
	}

	/**
	 * 根据路径创建文件以及目录
	 * 
	 * @param path
	 */
	public static void createFilePath(String path) {
		int index = path.lastIndexOf("/");
		String folderPath = path.substring(0, index);
		String filePath = path.substring(index);
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
