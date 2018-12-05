package com.xiao.data.entity;

import java.io.Serializable;

/**
 * 用户类
 * 
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
public class User implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;

	/**  */
	private String userId;

	/**  */
	private String userName;

	/**  */
	private String password;

	/**  */
	private String createdTime;

	/**  */
	private String updateTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}