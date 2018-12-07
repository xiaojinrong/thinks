package com.xiao.wechat.entity;

import java.io.Serializable;

/**
 * 回复消息格式
 * 
 * @author XiaoJinRong
 * @times 2018年12月7日 上午11:47:01
 * @version 1.0
 */
public class Reply implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 接收方帐号（收到的OpenID） */
	private String toUserName;

	/** 开发者微信号 */
	private String fromUserName;

	/** 消息创建时间 （整型） */
	private int createTime;

	/** 消息类型 */
	private String msgType;
	
	public Reply() {
	}

	public Reply(String toUserName, String fromUserName, int createTime, String msgType) {
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
}
