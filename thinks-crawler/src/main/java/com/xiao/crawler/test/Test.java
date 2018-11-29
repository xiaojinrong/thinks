package com.xiao.crawler.test;

import com.xiao.crawler.entity.Collection;
import com.xiao.crawler.service.CrawlerService;
import com.xiao.crawler.service.impl.CrawlerServiceImpl;

public class Test {

	public static void main(String[] args) {
		CrawlerService imageService = new CrawlerServiceImpl();
		Collection collection = new Collection();
		collection.setAddress("http://www.27270.com/beautiful/beijingtupian/");
		collection.setSelectAttr("src");
		collection.setSelectCss(".w1200:gt(0) img");
		collection.setDownPath("D://1//");
		imageService.downFile(collection);
	}
}
