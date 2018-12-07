package com.xiao.wechat.entity;

import java.io.Serializable;

/**
 * 视频消息
 * 
 * @author XiaoJinRong
 * @times 2018年12月7日 上午11:59:16
 * @version 1.0
 */
public class ReplyVideo extends ReplyMedia implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 视频消息的标题 */
	private String title;

	/** 视频消息的描述 */
	private String description;

	public ReplyVideo() {
	}

	public ReplyVideo(String toUserName, String fromUserName, int createTime, String msgType, String mediaId,
			String title, String description) {
		super(toUserName, fromUserName, createTime, msgType, mediaId);
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
