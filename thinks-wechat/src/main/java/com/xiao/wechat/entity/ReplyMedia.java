package com.xiao.wechat.entity;

import java.io.Serializable;

/**
 * 回复图片、语音消息
 * 
 * @author XiaoJinRong
 * @times 2018年12月7日 上午11:57:38
 * @version 1.0
 */
public class ReplyMedia extends Reply implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 通过素材管理中的接口上传多媒体文件 */
	private String mediaId;

	public ReplyMedia() {
	}

	public ReplyMedia(String toUserName, String fromUserName, int createTime, String msgType, String mediaId) {
		super(toUserName, fromUserName, createTime, msgType);
		this.mediaId = mediaId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
}
