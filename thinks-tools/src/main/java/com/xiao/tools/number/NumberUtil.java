package com.xiao.tools.number;

import java.text.DecimalFormat;

import com.xiao.tools.string.StringUtil;

public class NumberUtil {

	private static DecimalFormat DF = new DecimalFormat("0.00");

	/**
	 * 转为int
	 * 
	 * @param object
	 * @return
	 */
	public static int parseInt(Object object) {
		if (StringUtil.isEmpty(object)) {
			return 0;
		}
		String number = StringUtil.toString(object);
		int index = number.lastIndexOf(".");
		number = index == -1 ? number : number.substring(0, index);
		return Integer.parseInt(number);
	}

	/**
	 * 转为long
	 * 
	 * @param object
	 * @return
	 */
	public static long parseLong(Object object) {
		if (StringUtil.isEmpty(object)) {
			return 0;
		}
		String number = StringUtil.toString(object);
		int index = number.lastIndexOf(".");
		number = index == -1 ? number : number.substring(0, index);
		return Long.parseLong(number);
	}

	/**
	 * 计算百分比
	 * 
	 * @return 返回 98.99%
	 */
	public static String percent(long count, long total) {
		return DF.format(count * 100.0 / total) + "%";
	}

	/**
	 * 计算百分比
	 * 
	 * @return 返回 98.99
	 */
	public static double percents(long count, long total) {
		return Double.parseDouble(DF.format(count * 100.0 / total));
	}

	/**
	 * 单位为B的转为带宽单位
	 * 
	 * @param size
	 * @return
	 */
	public static String broadband(double size) {
		String result = "";
		if (size < 1024) {
			result = DF.format(size) + "B";
		} else if (size < 1048576) {
			result = DF.format(size / 1024) + "KB";
		} else if (size < 1073741824) {
			result = DF.format(size / 1048576) + "MB";
		} else {
			result = DF.format(size / 1073741824) + "GB";
		}
		return result;
	}

	/**
	 * b/s --> kb/s --> mb/s --> gb/s 宽带转换
	 * 
	 * @param size
	 * @param millis
	 * @return
	 */
	public static String broadbands(double size) {
		String result = "";
		if (size < 1.24) {
			result = DF.format(size * 1000) + "B/S";
		} else if (size < 1024) {
			result = DF.format(size / 1.24) + "KB/S";
		} else {
			result = DF.format(size / 1048.576) + "MB/S";
		}
		return result;
	}

	/**
	 * 保留2位小数
	 * 
	 * @param number
	 * @return
	 */
	public static String toDecimal(double number) {
		return DF.format(number);
	}
}
