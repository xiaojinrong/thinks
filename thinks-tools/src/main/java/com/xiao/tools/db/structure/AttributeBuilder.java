package com.xiao.tools.db.structure;

import java.util.ArrayList;
import java.util.List;

import com.xiao.tools.string.StringUtil;

/**
 * 属性类
 * @author XiaoJinRong
 * @times 2018年8月13日 上午9:37:03 
 * @version 1.0
 */
public class AttributeBuilder {
	
	/** 访问修饰符 */
	private String access;
	
	/** 返回值类型 */
	private String type;
	
	/** 属性名 */
	private String name;
	
	/** 默认值 */
	private String defaultValue;
	
	/** 文档注释 */
	private String documentInfo;
	
	/** 属性注解 */
	private List<AnnotationBuilder> annotationBuilders = new ArrayList<AnnotationBuilder>();

	public AttributeBuilder() {
	}
	
	public AttributeBuilder(String access, String type, String name) {
		this.access = access;
		this.type = type;
		this.name = name;
	}

	public AttributeBuilder(String access, String type, String name, String defaultValue, String documentInfo) {
		this.access = access;
		this.type = type;
		this.name = name;
		this.defaultValue = defaultValue;
		this.documentInfo = documentInfo;
	}

	public AttributeBuilder(String access, String type, String name, String documentInfo) {
		this.access = access;
		this.type = type;
		this.name = name;
		this.documentInfo = documentInfo;
	}

	public AttributeBuilder(String access, String type, String name, String documentInfo,
			List<AnnotationBuilder> annotationBuilders) {
		this.access = access;
		this.type = type;
		this.name = name;
		this.documentInfo = documentInfo;
		this.annotationBuilders = annotationBuilders;
	}
	
	public AttributeBuilder(String access, String type, String name, String documentInfo,
			AnnotationBuilder annotationBuilders) {
		this.access = access;
		this.type = type;
		this.name = name;
		this.documentInfo = documentInfo;
		this.annotationBuilders.add(annotationBuilders);
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocumentInfo() {
		return documentInfo;
	}

	public void setDocumentInfo(String documentInfo) {
		this.documentInfo = documentInfo;
	}

	public List<AnnotationBuilder> getAnnotationBuilders() {
		return annotationBuilders;
	}

	public void setAnnotationBuilders(List<AnnotationBuilder> annotationBuilders) {
		this.annotationBuilders = annotationBuilders;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.SPACE_FOUR).append("/** ").append(StringUtil.parseStr(this.documentInfo)).append(" */").append(StringUtil.LINE);
		for (AnnotationBuilder annotationBuilder : annotationBuilders) {
			sb.append(annotationBuilder).append(StringUtil.LINE);
		}
		sb.append(StringUtil.SPACE_FOUR).append(StringUtil.parseStr(this.access)).append(" ");
		sb.append(this.type).append(" ").append(this.name);
		if(!StringUtil.isEmpty(this.defaultValue)) {
			sb.append(" = ").append(this.defaultValue);
		}
		sb.append(";");
		return sb.toString();
	}
	public static void main(String[] args) {
		List<AnnotationBuilder> annos = new ArrayList<AnnotationBuilder>();
		annos.add(new AnnotationBuilder("Service"));
		annos.add(new AnnotationBuilder("ResponseBody", "123"));
		AttributeBuilder attributeBuilder = new AttributeBuilder("public","String","name","账号",annos);
		System.out.println(attributeBuilder);
	}
}
