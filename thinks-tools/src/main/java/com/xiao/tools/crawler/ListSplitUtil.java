package com.xiao.tools.crawler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 拆分集合工具
 * 
 * @author XiaoJinRong
 * @times 2018年5月16日 下午3:28:25
 * @version 1.0
 */
public class ListSplitUtil {

	public static Map<String, List<String>> listSplit(List<String> list, int threadCount) {
		Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
		int totalCount = list.size();
		threadCount = threadCount > totalCount ? totalCount : threadCount;
		threadCount = threadCount < 1 ? totalCount : threadCount;
		int splitCount = (int) Math.ceil((double) totalCount / threadCount);
		for (int i = 0; i < threadCount; i++) {
			int fromIndex = i * splitCount;
			int toIndex = (i + 1) * splitCount > totalCount ? totalCount : (i + 1) * splitCount;
			map.put((fromIndex + 1) + "-" + toIndex, list.subList(fromIndex, toIndex));
		}
		return map;
	}
}
