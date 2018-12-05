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
	public static String parseStr(Object obj) {
		String text = obj == null ? "" : String.valueOf(obj);
		return text;
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

	public static String subLine(String source) {
		if (isEmpty(source)) {
			return "";
		}
		int index = source.lastIndexOf(StringUtil.LINE);
		return index != -1 ? source.substring(0, index) : source;
	}
}
