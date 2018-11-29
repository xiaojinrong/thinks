package com.xiao.crawler.service;

import com.xiao.crawler.entity.Collection;

/**
 * 获取图片逻辑
 * 
 * @author XiaoJinRong
 * @times 2018年11月28日 下午4:12:29
 * @version 1.0
 */
public interface CrawlerService {

	/**
	 * 下载图片、视频等文件
	 * 
	 * @param collections
	 */
	void downFile(Collection collection);
}
