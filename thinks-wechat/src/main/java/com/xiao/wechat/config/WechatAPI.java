package com.xiao.wechat.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信接口管理类
 * 
 * @author XiaoJinRong
 * @times 2018年12月6日 下午12:51:10
 * @version 1.0
 */
public class WechatAPI {

	/** 请求地址 */
	private String url;

	/** 请求类型 */
	private String method = "GET";

	/** 参数类型 */
	private String paramType;

	/** 请求参数 */
	private Map<String, Object> paramMap = new HashMap<String, Object>();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

}
