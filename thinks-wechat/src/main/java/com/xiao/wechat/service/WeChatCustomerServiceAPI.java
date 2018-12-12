package com.xiao.wechat.service;

import java.util.Map;

/**
 * 微信用户管理
 * 
 * @author XiaoJinRong
 * @times 2018年12月10日 上午10:57:38
 * @version 1.0
 */
public interface WeChatCustomerServiceAPI {

	/**
	 * 创建用户标签
	 * 
	 * @param paramMap
	 * @return
	 */
	String createCustomerTag(Map<String, Object> paramMap);

	/**
	 * 获取公众号已创建的标签
	 * 
	 * @return
	 */
	String getCustomerTag();

	/**
	 * 编辑标签
	 * 
	 * @param paramMap
	 * @return
	 */
	String updatecCustomerTag(Map<String, Object> paramMap);

	/**
	 * 删除标签
	 * 
	 * @param paramMap
	 * @return
	 */
	String removeCustomerTag(Map<String, Object> paramMap);

	/**
	 * 获取标签下粉丝列表
	 * 
	 * @param paramMap
	 * @return
	 */
	String getTagUser(Map<String, Object> paramMap);

	/**
	 * 批量为用户打标签
	 * 
	 * @param paramMap
	 * @return
	 */
	String batchAddTag(Map<String, Object> paramMap);

	/**
	 * 批量为用户取消标签
	 * 
	 * @param paramMap
	 * @return
	 */
	String batchCancalTag(Map<String, Object> paramMap);

	/**
	 * 获取用户身上的标签列表
	 * 
	 * @param paramMap
	 * @return
	 */
	String getTagListByCustomer(Map<String, Object> paramMap);

	/**
	 * 设置用户备注名
	 * 
	 * @param paramMap
	 * @return
	 */
	String updateCustomerRemark(Map<String, Object> paramMap);

	/**
	 * 获取用户基本信息
	 * 
	 * @param paramMap
	 * @return
	 */
	String getCustomerInfo(Map<String, Object> paramMap);

	/**
	 * 获取用户列表
	 * 
	 * @param paramMap
	 * @return
	 */
	String getCustomerList(Map<String, Object> paramMap);

	/**
	 * 获取公众号的黑名单列表
	 * 
	 * @param paramMap
	 * @return
	 */
	String getBlackList(Map<String, Object> paramMap);

	/**
	 * 拉黑用户
	 * 
	 * @param paramMap
	 * @return
	 */
	String batchBlackList(Map<String, Object> paramMap);

	/**
	 * 取消拉黑用户
	 * 
	 * @param paramMap
	 * @return
	 */
	String batchUnblackList(Map<String, Object> paramMap);

}
