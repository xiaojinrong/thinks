package com.xiao.wechat.service;

import javax.servlet.http.HttpServletRequest;

/**
 * WeChat
 * 
 * @author XiaoJinRong
 * @times 2018年12月6日 下午2:43:29
 * @version 1.0
 */
public interface WeChatServiceAPI {

	/**
	 * 获取access_token
	 * 
	 * @return
	 */
	String getAccessToken();

	/**
	 * 获取微信服务器IP地址
	 * 
	 * @return
	 */
	String getWechatIp();

	/**
	 * 微信网络检查
	 * 
	 * @return
	 */
	String checkNet();

	/**
	 * 接收微信服务器事件
	 * 
	 * @param request
	 * @return
	 */
	String processRequest(HttpServletRequest request);

}
