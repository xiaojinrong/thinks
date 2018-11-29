package com.xiao.tools.crawler;

import java.util.UUID;

public class UUIDUtil {

	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "") + (int) (Math.random() * 10000);
	}
}
