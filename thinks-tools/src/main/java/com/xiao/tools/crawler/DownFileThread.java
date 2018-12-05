package com.xiao.tools.crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiao.tools.string.StringUtil;

public class DownFileThread implements Callable<Integer> {

	private static final Logger logger = LoggerFactory.getLogger(DownFileThread.class);

	private int tryCount = 3;
	private String filePath = "D://";
	private List<String> list = new ArrayList<String>();

	public DownFileThread() {
	}

	public DownFileThread(List<String> list) {
		this.list = list;
	}

	public DownFileThread(String filePath, List<String> list) {
		this.filePath = filePath;
		this.list = list;
	}

	public DownFileThread(int tryCount, String filePath, List<String> list) {
		this.tryCount = tryCount;
		this.filePath = filePath;
		this.list = list;
	}

	@Override
	public Integer call() {
		int count = 0;
		for (String link : list) {
			if (StringUtil.isEmpty(link)) {
				return count;
			}
			int suffix_index = link.lastIndexOf(".");
			if (suffix_index < 1) {
				return count;
			}
			String suffix = link.substring(suffix_index);
			int suffixIndex = suffix.indexOf("/");
			if (suffixIndex != -1) {
				suffix = suffix.substring(0, suffixIndex);
			}
			String fileName = UUIDUtil.uuid() + suffix;
			File folder = new File(filePath);
			folder.mkdirs();
			File file = new File(folder, fileName);
			logger.info("线程{}正在运行,正在下载{}", Thread.currentThread().getName(), fileName);
			for (int i = 0; i < tryCount; i++) {
				try {
					DownFileUtil.downMediaByUrl(link, file);
					count++;
					logger.info("{}下载完成，尝试下载次数为{}", fileName, i + 1);
					break;
				} catch (Exception e) {
					logger.debug("{}第{}次下载失败，失败原因{}", fileName, i + 1, e.getMessage());
					if (i < tryCount - 1) {
						logger.info("{}正在尝试下载第{}次", fileName, i + 1);
					} else {
						file.deleteOnExit();
					}
				}
			}
		}
		return count;
	}
}
