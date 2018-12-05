package com.xiao.tools.crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiao.tools.string.StringUtil;

public class DownTextThread implements Callable<Integer> {

	private static final Logger logger = LoggerFactory.getLogger(DownTextThread.class);

	private int tryCount = 3;
	private String filePath = "D://1";
	private List<String> list = new ArrayList<String>();

	public DownTextThread() {
	}

	public DownTextThread(List<String> list) {
		this.list = list;
	}

	public DownTextThread(String filePath, List<String> list) {
		this.filePath = filePath;
		this.list = list;
	}

	public DownTextThread(int tryCount, String filePath, List<String> list) {
		this.tryCount = tryCount;
		this.filePath = filePath;
		this.list = list;
	}

	@Override
	public Integer call() {
		int count = 0;
		for (String text : list) {
			if (StringUtil.isEmpty(text)) {
				return count;
			}
			String fileName = UUIDUtil.uuid() + ".txt";
			File folder = new File(filePath);
			folder.mkdirs();
			File file = new File(folder, fileName);
			logger.info("正在下载{}", fileName);
			for (int i = 0; i < tryCount; i++) {
				try {
					DownFileUtil.downTextByUrl(text, file);
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
