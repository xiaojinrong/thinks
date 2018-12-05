package com.xiao.tools.db.util;

/**
 * 生成方法
 * 
 * @author XiaoJinRong
 * @times 2018年8月10日 下午2:45:57
 * @version 1.0
 */
public class ClassProperty {

	public static final String ACCESS_PUBLIC = "public";
	public static final String ACCESS_PROTECT = "protect";
	public static final String ACCESS_DEFAULT = "";
	public static final String ACCESS_PRIVATE = "private";
	public static final String BASIC_TYPE_VOID = "void";
	public static final String BASIC_TYPE_INTEGER = "Integer";
	public static final String BASIC_TYPE_LONG = "Long";
	public static final String BASIC_TYPE_DOUBLE = "Double";
	public static final String BASIC_TYPE_FLOAT = "Float";
	public static final String BASIC_TYPE_DATA = "Date";
	public static final String BASIC_TYPE_CHARACTER = "Character";
	public static final String BASIC_TYPE_BOOLEAN = "Boolean";
	public static final String BASIC_TYPE_BYTE = "Byte";
	public static final String BASIC_TYPE_STRING = "String";
	public static final String BASIC_TYPE_OBJECT = "Object";
	public static final String BASE_TYPE_INT = "int";
	public static final String BASE_TYPE_DOUBLE = "double";
	public static final String BASE_TYPE_FLOAT = "float";
	public static final String BASE_TYPE_BYTE = "byte";
	public static final String BASE_TYPE_CHAR = "char";
	public static final String BASE_TYPE_LONG = "long";
	public static final String BASE_TYPE_SHORT = "short";
	public static final String BASE_TYPE_BOOLEAN = "boolean";
	public static final String METHOD_NAME_SET = "set";
	public static final String METHOD_NAME_GET = "get";
	public static final String METHOD_NAME_FIND = "find";
	public static final String METHOD_NAME_SAVE = "save";
	public static final String METHOD_NAME_UPDATE = "update";
	public static final String METHOD_NAME_REMOVE = "remove";
	public static final String METHOD_NAME_PAGE = "page";
	public static final String METHOD_NAME_PAGECOUNT = "pageCount";
	public static final String PACKAGE_NAME_DATE = "java.util.Date";
	
	/**
	 * 返回值类型
	 * 
	 * @author XiaoJinRong
	 * @times 2018年8月10日 下午2:51:26
	 * @version 1.0
	 */
	public static class ReturnProperty {

		public static String list(String type) {
			return "List<" + type + ">";
		}

		public static String set(String type) {
			return "Set<" + type + ">";
		}

		public static String map(String key, String value) {
			return "Map<" + key + "," + value + ">";
		}

		public static String map(String collections) {
			return "Map<" + collections + ">";
		}
	}
}