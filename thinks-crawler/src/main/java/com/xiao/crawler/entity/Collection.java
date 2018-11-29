package com.xiao.crawler.entity;

import java.io.Serializable;

/**
 * 采集规则类
 * 
 * @author XiaoJinRong
 * @times 2018年11月28日 下午4:15:30
 * @version 1.0
 */
public class Collection implements Serializable {

	private static final long serialVersionUID = 1L;

	private int defaultThreadCount = 10;

	private String defaultDownPath = "D:/1/";
	/** ID */
	private String id;

	/** 采集名称 */
	private String name;

	/** 采集地址 */
	private String address;

	/** 下载路径 */
	private String downPath = defaultDownPath;

	/** 线程个数 */
	private int threadCount = defaultThreadCount;

	/** 采集CSS规则 */
	private String selectCss;

	/** 采集属性规则 */
	private String selectAttr;

	/** 采集描述 */
	private String description;

	/** 网站 */
	private WebSite webSite;

	/** 采集种类 */
	private CollectionType collectionType;

	public int getDefaultThreadCount() {
		return defaultThreadCount;
	}

	public void setDefaultThreadCount(int defaultThreadCount) {
		this.defaultThreadCount = defaultThreadCount;
	}

	public String getDefaultDownPath() {
		return defaultDownPath;
	}

	public void setDefaultDownPath(String defaultDownPath) {
		this.defaultDownPath = defaultDownPath;
	}

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

	public String getDownPath() {
		return downPath;
	}

	public void setDownPath(String downPath) {
		this.downPath = downPath;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public String getSelectCss() {
		return selectCss;
	}

	public void setSelectCss(String selectCss) {
		this.selectCss = selectCss;
	}

	public String getSelectAttr() {
		return selectAttr;
	}

	public void setSelectAttr(String selectAttr) {
		this.selectAttr = selectAttr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public CollectionType getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(CollectionType collectionType) {
		this.collectionType = collectionType;
	}

}
