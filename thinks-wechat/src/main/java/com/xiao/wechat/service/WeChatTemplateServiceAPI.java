package com.xiao.wechat.service;

import java.util.Map;

/**
 * 微信模板消息API
 * 
 * @author XiaoJinRong
 * @times 2018年12月10日 上午9:54:21
 * @version 1.0
 */
public interface WeChatTemplateServiceAPI {

	/**
	 * 设置所属行业
	 * 
	 * @return
	 */
	String setTemplateIndustry(Map<String, Object> paramMap);

	/**
	 * 获取设置的行业信息
	 * 
	 * @return
	 */
	String getTemplateIndustryInfo();

	/**
	 * 获得模板ID
	 * 
	 * @param paramMap
	 * @return
	 */
	String getTemplateId(Map<String, Object> paramMap);

	/**
	 * 获取模板列表
	 * 
	 * @return
	 */
	String getTemplateList();

	/**
	 * 删除模板
	 * 
	 * @param paramMap
	 * @return
	 */
	String removeTemplate(Map<String, Object> paramMap);

	/**
	 * 发送模板消息
	 * 
	 * @param paramMap
	 * @return
	 */
	String sendTemplateMsg(Map<String, Object> paramMap);
}
