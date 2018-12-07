package com.xiao.wechat.entity;

import java.io.Serializable;

/**
 * 回复文本消息
 * 
 * @author XiaoJinRong
 * @times 2018年12月7日 上午11:48:17
 * @version 1.0
 */
public class ReplyText extends Reply implements Serializable {

	private static final long serialVersionUID = 1L;

	private String content;

	public ReplyText() {
	}

	public ReplyText(String toUserName, String fromUserName, int createTime, String msgType, String content) {
		super(toUserName, fromUserName, createTime, msgType);
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
