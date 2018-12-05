package com.xiao.tools.db.structure;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.xiao.tools.string.StringUtil;

/**
 * 注解构建
 * @author XiaoJinRong
 * @times 2018年8月13日 上午9:55:03 
 * @version 1.0
 */
public class AnnotationBuilder {
	
	/**  注解名称 */
	private String name;
	
	/**  注解默认值 */
	private String defaultValue;
	
	/** 注解属性值 */
	private Map<String,String> paramMap = new LinkedHashMap<>();

	public AnnotationBuilder() {
	}

	public AnnotationBuilder(String name) {
		this.name = name;
	}

	public AnnotationBuilder(String name, String defaultValue) {
		this.name = name;
		this.defaultValue = defaultValue;
	}

	public AnnotationBuilder(String name, Map<String, String> paramMap) {
		this.name = name;
		this.paramMap = paramMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}


	public Map<String, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.SPACE_FOUR).append("@").append(this.name);
		if(StringUtil.isEmpty(this.defaultValue)) {
			if(this.paramMap!=null&&this.paramMap.size()>0) {
				sb.append("(");
				String paramString = "";
				for (Entry<String, String> map : this.getParamMap().entrySet()) {
					paramString+=map.getKey()+"=\""+map.getValue()+"\",";
				}
				paramString=paramString!=""?paramString.trim().substring(0,paramString.length()-1):paramString;
				sb.append(paramString).append(")");
			}
		}else {
			sb.append("(").append("\"").append(this.defaultValue).append("\"").append(")");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Map<String,String> param = new LinkedHashMap<>();
		param.put("key", "1");
		param.put("value", "2");
		AnnotationBuilder builder = new AnnotationBuilder("Service",param);
		System.out.println(builder);
	}
}
