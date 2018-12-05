package com.xiao.tools.db.util;

import java.util.Date;

import com.xiao.tools.date.DateUtil;
import com.xiao.tools.string.StringUtil;

/**
 * 数据库工具类
 * 
 * @author XiaoJinRong
 * @times 2018年8月8日 下午12:37:12
 * @version 1.0
 */
public class DBPropertyUtil {

	/**
	 * 输入数据库类型，返回类属性
	 * 
	 * @param jdbcType
	 * @return
	 */
	public static String mapping(String jdbcType) {
		if (StringUtil.isEmpty(jdbcType)) {
			return "";
		}
		String propertyType = "Object";
		switch (jdbcType.toLowerCase()) {
		case "varchar":
		case "char":
		case "text":
			propertyType = "String";
			break;
		case "int":
		case "tinyint":
		case "integer":
			propertyType = "Integer";
			break;
		case "float":
		case "double":
			propertyType = "Double";
			break;
		case "date":
		case "datetime":
		case "timestamp":
			propertyType = "String";
			break;
		default:
			break;
		}
		return propertyType;
	}

	/**
	 * 首字母大写
	 * 
	 * @param propertyName
	 * @return
	 */
	public static String firstWordUpper(String propertyName) {
		if (StringUtil.isEmpty(propertyName)) {
			return "";
		}
		return propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
	}

	/**
	 * 首字母小写
	 * 
	 * @param propertyName
	 * @return
	 */
	public static String firstWordLower(String propertyName) {
		if (StringUtil.isEmpty(propertyName)) {
			return "";
		}
		return propertyName.substring(0, 1).toLowerCase() + propertyName.substring(1);
	}

	/**
	 * 将表注释的表变成类
	 * 
	 * @param className
	 * @return
	 */
	public static String lastWordChange(String className) {
		String newName = className;
		char lastCh = className.charAt(className.length() - 1);
		if (lastCh == '表') {
			newName = className.substring(0, className.length() - 1) + "类";
		} else {
			newName = className + "类";
		}
		return newName;
	}

	/**
	 * 将字符串最后一个字（类|表）删除，补充新词
	 * 
	 * @param className
	 * @param supply
	 * @return
	 */
	public static String lastWordChange(String className, String supply) {
		String newName = className;
		char lastCh = className.charAt(className.length() - 1);
		if (lastCh == '表' | lastCh == '类') {
			newName = className.substring(0, className.length() - 1) + supply;
		} else {
			newName = className + supply;
		}
		return newName;
	}

	/**
	 * 列名重命名为属性名称规范
	 * 
	 * @param jdbcName
	 * @return
	 */
	public static String createNewName(String jdbcName) {
		if (StringUtil.isEmpty(jdbcName)) {
			return "";
		}
		String newName = "";
		String temp_1 = jdbcName.toLowerCase();
		// 判断列名是否包含下划线或横杠
		if (temp_1.contains("_") || temp_1.contains("-")) {
			char[] ch = temp_1.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				if (ch[i] == '_' || ch[i] == '-') {
					if (i < ch.length - 1) {
						newName += String.valueOf(ch[i + 1]).toUpperCase();
						i++;
					}
				} else {
					newName += ch[i];
				}
			}
		} else {
			newName = temp_1;
		}
		return newName;
	}

	/**
	 * 类型信息
	 * 
	 * @param anno
	 * @return
	 */
	public static String generateClassAnno(String anno) {
		StringBuffer sb = new StringBuffer();
		sb.append("/**").append(StringUtil.LINE).append(" * ").append(anno).append(StringUtil.LINE);
		sb.append(" * @author ").append(System.getenv("username")).append(StringUtil.LINE).append(" * @times ");
		sb.append(DateUtil.parseDate(new Date())).append(StringUtil.LINE).append(" * @version 1.0");
		sb.append(StringUtil.LINE).append(" */").append(StringUtil.LINE);
		return sb.toString();
	}
}
