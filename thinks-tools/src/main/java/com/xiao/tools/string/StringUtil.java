package com.xiao.tools.string;

import java.util.UUID;

public class StringUtil {
	
	/**
	 * 判断字符串是否为空
	 * @param source
	 * @return
	 */
	public static boolean isEmpty(String source) {
		return source==null||source.trim().length()==0||"null".equals(source);
	}
	
	
	/**
	 * 随机生成UUID
	 * @return
	 */
	public static String readomUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
