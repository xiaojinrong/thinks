package com.xiao.tools.db.util;

import org.junit.Before;
import org.junit.Test;

import com.xiao.tools.db.model.DBEntity;

public class CreateMain {

	public String driverName = "com.mysql.jdbc.Driver";
	public String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8&useSSL=false";
	public String userName = "root";
	public String password = "123456";
	public String packageName = "com.xiao.data";
	public DBEntity db = new DBEntity(driverName, url, userName, password);

	@Before
	public void before() {
		DBUtil.init(db);
	}

	@Test
	public void test1() {
		String path = "D:/1";
		DBUtil.generateBean(path, packageName);
	}

	@Test
	public void test2() {
		String path = "D:/1/dao";
		DBUtil.generateDao(path, packageName);
	}

	@Test
	public void test3() {
		String ipath = "D:/1/service";
		DBUtil.generateService(ipath, packageName);
	}

	@Test
	public void test4() {
		String path = "D:/1/controller";
		DBUtil.generateController(path, packageName);
	}

	@Test
	public void test5() {
		String path = "D:/1/";
		DBUtil.generateMapper(path, packageName);
	}

	@Test
	public void test6() {
		String path = "D:/1/";
		DBUtil.createAll(path, packageName);
	}
}
