package com.xiao.tools.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtil {
	public static void printTxt(String path, String text) {
		OutputStream out = null;
		try {
			File file = new File(path);
			if (!file.exists() || !file.isFile()) {
				file.createNewFile();
			}
			out = new FileOutputStream(file);
			byte[] b = text.getBytes();
			out.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
