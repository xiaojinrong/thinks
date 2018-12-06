package com.xiao.wechat.service;

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

}
