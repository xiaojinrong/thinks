package com.xiao.wechat.util;

import java.util.Arrays;

import com.xiao.tools.hash.MD5Util;

public class WechatUtil {

	/** 定义access_token全局存储变量 */
	public static String ACCESS_TOKEN;

	/**
	 * WeChart验证
	 * 
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static String checkSignature(String token, String timestamp, String nonce) {
		String[] arrays = { token, timestamp, nonce };
		Arrays.sort(arrays);
		StringBuffer valText = new StringBuffer();
		for (String array : arrays) {
			valText.append(array);
		}
		return MD5Util.getSHA1(valText.toString());
	}
}
