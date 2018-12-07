package com.xiao.wechat.entity;

import java.io.Serializable;

/**
 * 微信通用接口凭证
 * 
 * @author XiaoJinRong
 * @times 2018年12月7日 上午10:23:30
 * @version 1.0
 */
public class AccessToken implements Serializable {
	private static final long serialVersionUID = 1L;

	private String accessToken;

	private int expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
