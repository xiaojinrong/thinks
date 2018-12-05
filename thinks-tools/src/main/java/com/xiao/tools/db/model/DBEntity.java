package com.xiao.tools.db.model;

public class DBEntity {

	private String driverName;
	private String url;
	private String userName;
	private String password;

	public DBEntity() {
	}

	public DBEntity(String driverName, String url, String userName, String password) {
		this.driverName = driverName;
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

}
