package com.xiao.tools.string;

public class StringUtil {

	/** 换行符 */
	public static final String LINE = System.getProperty("line.separator");

	/** 一个空格 */
	public static final String SPACE_ONE = " ";

	/** 四个空格 */
	public static final String SPACE_FOUR = "    ";

	/** 八个空格 */
	public static final String SPACE_EIGHT = "        ";

	/** 十二个空格 */
	public static final String SPACE_TWELVE = "            ";

	/**
	 * 字符串判断是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 字符串判断是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		return (obj == null) || (!(obj instanceof String)) || (String.valueOf(obj).trim().length() == 0);
	}

	/**
	 * 请强制转为字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		String text = obj == null ? "" : String.valueOf(obj);
		return text;
	}

	/**
	 * 截取最后一个字符
	 * 
	 * @param source
	 * @param ch
	 * @return
	 */
	public static String subLast(String source, String chats) {
		if (isEmpty(source)) {
			return "";
		}
		int index = source.lastIndexOf(chats);
		return index != -1 ? source.substring(0, index) : source;
	}

	/**
	 * 截取最后一个逗号
	 * 
	 * @param source
	 * @return
	 */
	public static String sublength(String source) {
		if (isEmpty(source)) {
			return "";
		}
		int index = source.lastIndexOf(",");
		return index != -1 ? source.substring(0, index) : source;
	}

	/**
	 * 截取最后一个换行
	 * 
	 * @param source
	 * @return
	 */
	public static String subLine(String source) {
		if (isEmpty(source)) {
			return "";
		}
		int index = source.lastIndexOf(StringUtil.LINE);
		return index != -1 ? source.substring(0, index) : source;
	}

	/**
	 * 首字母大写
	 * 
	 * @param propertyName
	 * @return
	 */
	public static String firstWordUpper(String propertyName) {
		if (StringUtil.isEmpty(propertyName)) {
			return "";
		}
		return propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
	}

	/**
	 * 首字母小写
	 * 
	 * @param propertyName
	 * @return
	 */
	public static String firstWordLower(String propertyName) {
		if (StringUtil.isEmpty(propertyName)) {
			return "";
		}
		return propertyName.substring(0, 1).toLowerCase() + propertyName.substring(1);
	}
}
