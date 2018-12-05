package com.xiao.tools.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.xiao.tools.db.model.DBEntity;

/**
 * 连接数据库
 * 
 * @author XiaoJinRong
 * @times 2018年12月3日 上午10:30:40
 * @version 1.0
 */
public class ConnectionUtil {

	public static Connection connection(DBEntity db) {
		try {
			Class.forName(db.getDriverName()).newInstance();
			Properties properties = new Properties();
			properties.setProperty("user", db.getUserName());
			properties.setProperty("password", db.getPassword());
			// 设置可以获取remarks信息
			properties.setProperty("remarks", "true");
			// 设置可以获取tables remarks信息
			properties.setProperty("useInformationSchema", "true");
			return DriverManager.getConnection(db.getUrl(), properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
