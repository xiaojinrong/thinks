package com.xiao.tools.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author XiaoJinRong
 * @times 2018年11月26日 上午11:33:07
 * @version 1.0
 */
public class DateUtil {

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @return
	 */
	public static String parseDate(Date date) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sd.format(date);
	}

	/**
	 * 获取当前秒数
	 * 
	 * @return
	 */
	public static int getNowSecond() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	/**
	 * 毫秒转换时间(最大时间单位)
	 * 
	 * @param timeMillis
	 * @return 1000 60000 3600000
	 */
	public static String toTime(long timeMillis) {
		String date = "";
		if (timeMillis < 1000) {
			date = timeMillis + "毫秒";
		} else if (timeMillis < 60000) {
			date = timeMillis / 1000 + "秒" + timeMillis % 1000 + "毫秒";
		} else if (timeMillis < 3600000) {
			date = timeMillis / 60000 + "分" + timeMillis % 60000 / 1000 + "秒" + timeMillis % 60000 % 1000 + "毫秒";
		} else {
			date = timeMillis / 3600000 + "小时" + timeMillis % 3600000 / 60000 + "分"
					+ timeMillis % 3600000 % 60000 / 1000 + "秒";
		}
		return date;
	}

	/**
	 * 时间差（和当前时间比较）
	 * 
	 * @param startTimeMillis
	 * @return
	 */
	public static String diffCurrentTime(long startTimeMillis) {
		long diffTime = System.currentTimeMillis() - startTimeMillis;
		return toTime(diffTime);
	}

	public static void main(String[] args) {
		System.out.println(Long.MAX_VALUE);
	}

}
