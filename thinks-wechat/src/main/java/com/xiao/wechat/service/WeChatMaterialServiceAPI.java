package com.xiao.wechat.service;

import java.util.Map;

/**
 * 素材管理API
 * 
 * @author XiaoJinRong
 * @times 2018年12月10日 上午10:31:37
 * @version 1.0
 */
public interface WeChatMaterialServiceAPI {

	/**
	 * 上传临时素材
	 * 
	 * @param paramMap
	 * @return
	 */
	String uploadMedia(Map<String, Object> paramMap);

	/**
	 * 获取临时素材
	 * 
	 * @param paramMap
	 * @return
	 */
	String getMedia(Map<String, Object> paramMap);

	/**
	 * 新增永久图文素材
	 * 
	 * @param paramMap
	 * @return
	 */
	String addMaterial(Map<String, Object> paramMap);

	/**
	 * 上传图文消息内的图片获取URL
	 * 
	 * @param paramMap
	 * @return
	 */
	String uploadMediaImg(Map<String, Object> paramMap);

	/**
	 * 新增其他类型永久素材
	 * 
	 * @param paramMap
	 * @return
	 */
	String uploadMediaOthers(Map<String, Object> paramMap);

	/**
	 * 获取永久素材
	 * 
	 * @param paramMap
	 * @return
	 */
	String getMaterial(Map<String, Object> paramMap);

	/**
	 * 删除永久素材
	 * 
	 * @param paramMap
	 * @return
	 */
	String removeMaterial(Map<String, Object> paramMap);

	/**
	 * 修改永久图文素材
	 * 
	 * @param paramMap
	 * @return
	 */
	String updateMaterial(Map<String, Object> paramMap);

	/**
	 * 获取素材总数
	 * 
	 * @param paramMap
	 * @return
	 */
	String getMaterialCount();

	/**
	 * 获取素材列表
	 * 
	 * @param paramMap
	 * @return
	 */
	String getMaterialList(Map<String, Object> paramMap);
}
