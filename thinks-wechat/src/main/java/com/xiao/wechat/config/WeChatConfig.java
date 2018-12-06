package com.xiao.wechat.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信配置信息
 * 
 * @author XiaoJinRong
 * @times 2018年12月6日 上午11:40:47
 * @version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WeChatConfig {

	private String appId;

	private String appSecret;

	private String EncodingAESKey;

	private String token;

	private String accessToken;

	private Map<String, WechatAPI> wechatAPIs;

	private Map<String, String> codes;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEncodingAESKey() {
		return EncodingAESKey;
	}

	public void setEncodingAESKey(String encodingAESKey) {
		EncodingAESKey = encodingAESKey;
	}

	public Map<String, String> getCodes() {
		return codes;
	}

	public void setCodes(Map<String, String> codes) {
		this.codes = codes;
	}

	public Map<String, WechatAPI> getWechatAPIs() {
		return wechatAPIs;
	}

	public void setWechatAPIs(Map<String, WechatAPI> wechatAPIs) {
		this.wechatAPIs = wechatAPIs;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
