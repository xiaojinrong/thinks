package com.xiao.tools.hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * 
 * @author XiaoJinRong
 * @times 2018年12月3日 上午9:31:28
 * @version 1.0
 */
public class MD5Util {
	/**
	 * 基本MD5
	 * 
	 * @param text
	 * @return
	 */
	public static String getMD5(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
