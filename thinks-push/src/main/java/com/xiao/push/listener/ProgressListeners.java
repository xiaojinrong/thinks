package com.xiao.push.listener;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import com.xiao.tools.date.DateUtil;
import com.xiao.tools.entity.ProgressUpload;
import com.xiao.tools.io.FileUtil;
import com.xiao.tools.number.NumberUtil;
import com.xiao.tools.string.StringUtil;

@Component
public class ProgressListeners implements ProgressListener {

	private long startTime;

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
		long usedTime = System.currentTimeMillis() - startTime;
		double broadbands = pBytesRead * 1.0 / usedTime;
		ProgressUpload progress = new ProgressUpload();
		progress.setUsedTime(DateUtil.toTime(usedTime));
		progress.setDownloadSpeed(NumberUtil.broadbands(broadbands));
		progress.setFileSize(NumberUtil.broadband(pContentLength));
		progress.setPercentage(NumberUtil.percent(pBytesRead, pContentLength));
		progress.setUploadFileSize(NumberUtil.broadband(pBytesRead));
		progress.setRemainingTime(
				DateUtil.toTime(NumberUtil.parseLong(NumberUtil.parseInt(pContentLength - pBytesRead) * NumberUtil.parseInt(broadbands))));
		FileUtil.writeFile("D://22.txt", progress + StringUtil.LINE, true);
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
}
