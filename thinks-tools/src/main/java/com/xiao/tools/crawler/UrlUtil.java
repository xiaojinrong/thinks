package com.xiao.tools.crawler;

public class UrlUtil {
	public static String getRoot(String url) {
		int index = url.indexOf("//");
		String http = "http://";
		if (index != -1) {
			http = url.substring(0, index) + "//";
		}
		String context = url.substring(index + 2);
		String www = context.substring(0, context.indexOf("/") + 1);
		String root = http + www;
		return root;
	}
}
