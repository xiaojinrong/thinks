package com.xiao.push.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import com.xiao.push.websocket.WebSocketServer;
import com.xiao.tools.date.DateUtil;
import com.xiao.tools.entity.ProgressUpload;
import com.xiao.tools.io.FileUtil;
import com.xiao.tools.json.JsonUtil;
import com.xiao.tools.number.NumberUtil;
import com.xiao.tools.string.StringUtil;

@Component
public class ProgressListeners implements ProgressListener {

	private long startTime;

	private long nextTime;

	private List<String> fileItems = new ArrayList<String>();

	/**
	 * @param pBytesRead
	 *            已读取文件的比特数
	 * @param pContentLength
	 *            文件总比特数
	 * @param pItems
	 *            正读的第几个文件
	 */
	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		ProgressUpload progress = new ProgressUpload();
		long currentTime = System.currentTimeMillis();
		long usedTime = currentTime - startTime;
		double percentage = NumberUtil.percents(pBytesRead, pContentLength);
		double broadbands = pBytesRead * 1.0 / usedTime;
		progress.setFileName(fileItems.size() == pItems && fileItems.size() != 0 ? fileItems.get(pItems - 1)
				: StringUtil.toString(pItems));
		progress.setUsedTime(DateUtil.toTime(usedTime));
		progress.setDownloadSpeed(NumberUtil.broadbands(broadbands));
		progress.setFileSize(NumberUtil.broadband(pContentLength));
		progress.setPercentage(percentage);
		progress.setUploadFileSize(NumberUtil.broadband(pBytesRead));
		progress.setRemainingTime(DateUtil.toTime(NumberUtil.parseLong((pContentLength - pBytesRead) / broadbands)));
		try {
			// 500毫秒发送一次或者进度完成100%
			if (currentTime > nextTime + 500 || pBytesRead == pContentLength) {
				nextTime += 500;
				FileUtil.writeFile("D://22.txt", JsonUtil.toString(progress) + StringUtil.LINE, true);
				WebSocketServer.sendInfo(JsonUtil.toString(progress));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getNextTime() {
		return nextTime;
	}

	public void setNextTime(long nextTime) {
		this.nextTime = nextTime;
	}

	public List<String> getFileItems() {
		return fileItems;
	}

	public void setFileItems(List<String> fileItems) {
		this.fileItems = fileItems;
	}
}
