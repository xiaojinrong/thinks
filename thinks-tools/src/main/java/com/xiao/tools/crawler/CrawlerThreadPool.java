package com.xiao.tools.crawler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrawlerThreadPool {

	private static final Logger logger = LoggerFactory.getLogger(CrawlerThreadPool.class);

	/**
	 * 根据URL集合下载不同的资源
	 * 
	 * @param threadCount
	 * @param list
	 * @param filePath
	 * @return
	 */
	public static int downFile(int threadCount, List<String> list, String filePath) {
		Map<String, List<String>> map = ListSplitUtil.listSplit(list, threadCount);
		Map<String, Future<Integer>> futures = new LinkedHashMap<String, Future<Integer>>();
		ExecutorService pool = Executors.newFixedThreadPool(threadCount);
		for (Entry<String, List<String>> item : map.entrySet()) {
			Future<Integer> future = pool.submit(new DownFileThread(filePath, item.getValue()));
			futures.put(item.getKey(), future);
		}
		pool.shutdown();
		int count = 0;
		for (Entry<String, Future<Integer>> item : futures.entrySet()) {
			try {
				count += item.getValue().get();
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("future结果错误,{}", e.getMessage());
			}
		}
		logger.info("共{}个，本次总共下载{}个", list.size(), count);
		return count;
	}

	/**
	 * 下载文本文件
	 * 
	 * @param threadCount
	 * @param url
	 * @param cssSelect
	 * @param filePath
	 * @return
	 */
	public static int downTextFile(int threadCount, String url, String cssSelect, String filePath) {
		String html = HttpClientUtil.get(url);
		List<String> list = JsoupUtil.getJsoup(html, cssSelect);
		Map<String, List<String>> map = ListSplitUtil.listSplit(list, threadCount);
		Map<String, Future<Integer>> futures = new LinkedHashMap<String, Future<Integer>>();
		ExecutorService pool = Executors.newFixedThreadPool(threadCount);
		for (Entry<String, List<String>> item : map.entrySet()) {
			Future<Integer> future = pool.submit(new DownTextThread(filePath, item.getValue()));
			futures.put(item.getKey(), future);
		}
		pool.shutdown();
		int count = 0;
		for (Entry<String, Future<Integer>> item : futures.entrySet()) {
			try {
				count += item.getValue().get();
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("future结果错误,{}", e.getMessage());
			}
		}
		logger.info("共{}个，本次总共下载{}个,{}", list.size(), count, url);
		return count;
	}
}
