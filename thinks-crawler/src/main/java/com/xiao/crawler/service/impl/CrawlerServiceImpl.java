package com.xiao.crawler.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xiao.crawler.entity.Collection;
import com.xiao.crawler.service.CrawlerService;
import com.xiao.tools.crawler.CrawlerThreadPool;
import com.xiao.tools.crawler.HttpClientUtil;
import com.xiao.tools.crawler.JsoupUtil;

@Service
public class CrawlerServiceImpl implements CrawlerService {

	@Override
	public void downFile(Collection collection) {
		String html = HttpClientUtil.get(collection.getAddress());
		List<String> links = JsoupUtil.getJsoupByAttr(html, collection.getSelectCss(), collection.getSelectAttr());
		CrawlerThreadPool.downFile(collection.getThreadCount(), links, collection.getDownPath());
	}

}
