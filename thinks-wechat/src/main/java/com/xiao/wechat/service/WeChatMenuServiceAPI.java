package com.xiao.wechat.service;

import java.util.Map;

/**
 * 微信菜单Service
 * 
 * @author XiaoJinRong
 * @times 2018年12月6日 下午4:46:59
 * @version 1.0
 */
public interface WeChatMenuServiceAPI {

	/**
	 * 菜单查询
	 * 
	 * @return
	 */
	String getMenu();

	/**
	 * 创建菜单
	 * 
	 * @param menuJson
	 *            菜单JSON字符串
	 * @return
	 */
	String createMenu(Map<String, Object> paramMap);
}
