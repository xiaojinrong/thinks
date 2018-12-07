package com.xiao.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import com.xiao.tools.crawler.HttpClientUtil;
import com.xiao.tools.json.JsonUtil;
import com.xiao.tools.string.StringUtil;
import com.xiao.wechat.config.WeChatConfig;
import com.xiao.wechat.config.WechatAPI;
import com.xiao.wechat.util.WechatUtil;

public abstract class BaseWeChatService {

	@Resource
	private WeChatConfig weChatConfig;

	/**
	 * 请求微信API地址
	 * 
	 * @param type
	 *            种类
	 * @return
	 */
	public String request(String type) {
		String result = "";
		WechatAPI wechartAPI = weChatConfig.getWechatAPIs().get(type);
		if ("GET".equalsIgnoreCase(wechartAPI.getMethod())) {
			result = HttpClientUtil.get(wechartAPI.getUrl(), wechartAPI.getParamMap());
		} else if ("POST".equalsIgnoreCase(wechartAPI.getMethod())) {
			if ("JSON".equalsIgnoreCase(wechartAPI.getParamType())) {
				result = HttpClientUtil.postJson(wechartAPI.getUrl(), JsonUtil.toString(wechartAPI.getParamMap()));
			} else {
				Map<String, String> paramMaps = new HashMap<String, String>();
				for (Entry<String, Object> item : wechartAPI.getParamMap().entrySet()) {
					paramMaps.put(item.getKey(), item.getValue().toString());
				}
				result = HttpClientUtil.post(wechartAPI.getUrl(), paramMaps);
			}
		}
		if (result.contains("errcode")) {
			Map<String, Object> resultMap = JsonUtil.toMap(result);
			String errorMsg = weChatConfig.getCodes().get(resultMap.get("errcode").toString());
			return StringUtil.isEmpty(errorMsg) ? result : errorMsg;
		}
		return result;
	}

	/**
	 * 请求微信API地址
	 * 
	 * @param type
	 * @return
	 */
	public String requestToken(String type) {
		setWechatAPIUrl(type);
		return request(type);
	}

	/**
	 * 请求微信API地址
	 * 
	 * @param type
	 * @param paramMap
	 *            参数
	 * @return
	 */
	public String request(String type, Map<String, Object> paramMap) {
		WechatAPI wechartAPI = setWechatAPIUrl(type);
		Map<String, Object> paramMaps = wechartAPI.getParamMap();
		paramMaps.putAll(paramMap);
		return request(type);
	}

	/**
	 * 设置WeChat的URL
	 * 
	 * @param type
	 * @return
	 */
	private WechatAPI setWechatAPIUrl(String type) {
		WechatAPI wechartAPI = weChatConfig.getWechatAPIs().get(type);
		String lastCh = wechartAPI.getUrl().indexOf("?") == -1 ? "?" : "&";
		String url = wechartAPI.getUrl() + lastCh + weChatConfig.getAccessToken() + "="
				+ WechatUtil.ACCESS_TOKEN.getAccessToken();
		wechartAPI.setUrl(url);
		return wechartAPI;
	}
}
