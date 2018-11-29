package com.xiao.crawler.entity;

import java.io.Serializable;

/**
 * 网站信息
 * 
 * @author XiaoJinRong
 * @times 2018年11月28日 下午4:18:09
 * @version 1.0
 */
public class WebSite implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private String id;

	/** 网站名称 */
	private String name;

	/** 网站地址 */
	private String address;

	/** 网站描述 */
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
