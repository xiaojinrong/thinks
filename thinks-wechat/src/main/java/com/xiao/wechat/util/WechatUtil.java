package com.xiao.wechat.util;

import java.util.Arrays;

import com.xiao.tools.hash.MD5Util;
import com.xiao.wechat.entity.AccessToken;

public class WechatUtil {

	/** 定义access_token全局存储变量 */
	public static AccessToken ACCESS_TOKEN = new AccessToken();

	/**
	 * WeChart验证
	 * 
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arrays = { ACCESS_TOKEN.getAccessToken(), timestamp, nonce };
		Arrays.sort(arrays);
		StringBuffer valText = new StringBuffer();
		for (String array : arrays) {
			valText.append(array);
		}
		return MD5Util.getSHA1(valText.toString()).equalsIgnoreCase(signature);
	}
}
