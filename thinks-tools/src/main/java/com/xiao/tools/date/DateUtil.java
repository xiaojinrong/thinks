package com.xiao.tools.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author XiaoJinRong
 * @times 2018年11月26日 上午11:33:07 
 * @version 1.0
 */
public class DateUtil {
	
	public static String parseDate(Date date) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sd.format(date);
	}
}
