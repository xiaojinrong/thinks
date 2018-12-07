package com.xiao.wechat.entity;

import java.io.Serializable;

/**
 * 回复图文消息
 * 
 * @author XiaoJinRong
 * @times 2018年12月7日 下午12:25:15
 * @version 1.0
 */
public class ReplyNews extends Reply implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 图文消息个数 */
	private int articleCount;

	/** 图文消息信息 */
	private String articles;

	/** 图文消息标题 */
	private String title;

	/** 图文消息描述 */
	private String description;

	/** 图片链接，支持JPG、PNG格式 */
	private String picUrl;

	/** 点击图文消息跳转链接 */
	private String url;

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public String getArticles() {
		return articles;
	}

	public void setArticles(String articles) {
		this.articles = articles;
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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
