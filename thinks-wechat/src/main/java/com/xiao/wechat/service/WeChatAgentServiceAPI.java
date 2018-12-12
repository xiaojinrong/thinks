package com.xiao.wechat.service;

import java.util.Map;

/**
 * 微信客服
 * 
 * @author XiaoJinRong
 * @times 2018年12月10日 上午9:16:34
 * @version 1.0
 */
public interface WeChatAgentServiceAPI {

	/**
	 * 添加客服帐号
	 * 
	 * @param paramMap
	 * @return
	 */
	String addAgent(Map<String, Object> paramMap);

	/**
	 * 修改客户账号
	 * 
	 * @param paramMap
	 * @return
	 */
	String updateAgent(Map<String, Object> paramMap);

	/**
	 * 删除客服帐号
	 * 
	 * @param paramMap
	 * @return
	 */
	String removeAgent(Map<String, Object> paramMap);

	/**
	 * 设置客服账号头像
	 * 
	 * @return
	 */
	String getAllAgent();

	/**
	 * 客服接口-发消息
	 * 
	 * @return
	 */
	String sendMsgByAgent(Map<String, Object> paramMap);

	/**
	 * 客服输入状态
	 * @param paramMap
	 * @return
	 */
	String sendAgentStatus(Map<String, Object> paramMap);
}
