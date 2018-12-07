package com.xiao.tools.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.xiao.tools.string.StringUtil;

/**
 * 反射工具类
 * 
 * @author XiaoJinRong
 * @times 2018年11月27日 上午10:19:29
 * @version 1.0
 */
public class ReflectUtil {

	/**
	 * 查询全部属性
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Field> getAllField(Class<?> clazz) {
		List<Field> fieldList = new ArrayList<Field>();
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				/** 过滤静态|transient属性 **/
				if (Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers())) {
					continue;
				}
				fieldList.add(field);
			}
		}
		return fieldList;
	}

	/**
	 * 设置属性
	 * 
	 * @param object
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public static Object setAttribute(Object object, String fieldName, String fieldValue) {
		return attribute(object, "set", fieldName, fieldValue);
	}

	/**
	 * 获取属性
	 * 
	 * @param object
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public static Object getAttribute(Object object, String fieldName, String fieldValue) {
		return attribute(object, "get", fieldName, fieldValue);
	}

	/**
	 * 设置/获取 属性
	 * 
	 * @param object
	 * @param type
	 *            get/set
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	private static Object attribute(Object object, String type, String fieldName, String fieldValue) {
		for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				String fieldNameLower = StringUtil.firstWordLower(fieldName);
				String fieldNameUpper = StringUtil.firstWordUpper(fieldName);
				Field field = clazz.getDeclaredField(fieldNameLower);
				Method method = object.getClass().getMethod(type + fieldNameUpper, field.getType());
				return method.invoke(object, fieldValue);
			} catch (Exception e) {
			}
		}
		return object;
	}
}
