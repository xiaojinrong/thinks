package com.xiao.tools.constant;

/**
 * 系统常量工具类
 * @author XiaoJinRong
 * @times 2018年11月26日 上午11:30:19 
 * @version 1.0
 */
public class SystemUtil {
	
	/**
	 * 正则表达式
	 * @author XiaoJinRong
	 * @times 2018年11月26日 上午11:30:12 
	 * @version 1.0
	 */
	public class Regex{
		/** 正则 YYYY-MM-DD */
		public static final String DATE_FORMAT_REGEX = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

		/** 正则 HH:MM:SS */
		public static final String TIME_FORMAT_REGEX = "((20|21|22|23|[0-1][0-9]):([0-5][0-9]):([0-5][0-9]))";
	}
	
	/**
	 * 字符集
	 * @author XiaoJinRong
	 * @times 2018年11月26日 上午11:49:31 
	 * @version 1.0
	 */
	public class Character{
		/** UTF-8 */
		public static final String UTF_8 = "UTF-8";
		
		/** ISO-8859-1 */
		public static final String ISO_8859_1 = "ISO-8859-1";
	}

}
