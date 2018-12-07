package com.xiao.wechat.entity;

import java.io.Serializable;

/**
 * 音乐消息
 * 
 * @author XiaoJinRong
 * @times 2018年12月7日 下午12:01:03
 * @version 1.0
 */
public class ReplyMusic extends Reply implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 音乐标题 */
	private String title;

	/** 音乐描述 */
	private String description;

	/** 音乐链接 */
	private String musicURL;

	/** 高质量音乐链接，WIFI环境优先使用该链接播放音乐 */
	private String hqMusicUrl;

	/** 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id */
	private String thumbMediaId;

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

	public String getMusicURL() {
		return musicURL;
	}

	public void setMusicURL(String musicURL) {
		this.musicURL = musicURL;
	}

	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
}
