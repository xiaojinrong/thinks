package com.xiao.tools.entity;

import java.io.Serializable;

/**
 * 上传文件进度条
 * 
 * @author XiaoJinRong
 * @times 2018年12月11日 上午11:27:52
 * @version 1.0
 */
public class ProgressUpload implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 文件名称 */
	private String fileName;

	/** 文件大小 */
	private String fileSize;

	/** 进度比 */
	private double percentage;

	/** 已上传文件大小 */
	private String uploadFileSize;

	/** 上传速度 */
	private String downloadSpeed;

	/** 已用时间 */
	private String usedTime;

	/** 剩余时间 */
	private String remainingTime;

	public ProgressUpload() {
	}

	public ProgressUpload(String fileName, String fileSize) {
		this.fileName = fileName;
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getUploadFileSize() {
		return uploadFileSize;
	}

	public void setUploadFileSize(String uploadFileSize) {
		this.uploadFileSize = uploadFileSize;
	}

	public String getDownloadSpeed() {
		return downloadSpeed;
	}

	public void setDownloadSpeed(String downloadSpeed) {
		this.downloadSpeed = downloadSpeed;
	}

	public String getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime;
	}

	public String getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(String remainingTime) {
		this.remainingTime = remainingTime;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "ProgressUpload [fileName=" + fileName + ", fileSize=" + fileSize + ", percentage=" + percentage
				+ ", uploadFileSize=" + uploadFileSize + ", downloadSpeed=" + downloadSpeed + ", usedTime=" + usedTime
				+ ", remainingTime=" + remainingTime + "]";
	}
}
