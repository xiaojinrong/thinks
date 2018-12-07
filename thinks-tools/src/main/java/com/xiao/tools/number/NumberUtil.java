package com.xiao.tools.number;

import java.text.DecimalFormat;

import com.xiao.tools.string.StringUtil;

public class NumberUtil {

	private static DecimalFormat DF = new DecimalFormat("0.00");

	/**
	 * 转为Int
	 * 
	 * @param object
	 * @return
	 */
	public static int parseInt(Object object) {
		if (StringUtil.isEmpty(object)) {
			return 0;
		}
		return Integer.parseInt(StringUtil.toString(object));
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
}
