package com.xiao.tools.db.structure;

/**
 * 参数类型构建 
 * @author XiaoJinRong
 * @times 2018年8月13日 上午10:15:05 
 * @version 1.0
 */
public class ParamBuilder {

	/** 参数类型 */
	private String type;

	/** 参数名称 */
	private String value;
	
	/** 参数注释 */
	private String documentInfo;
	
	/** 参数注解 */
	private AnnotationBuilder annotationBuilder;

	public ParamBuilder() {
		
	}

	public ParamBuilder(String type, String value) {
		this.type = type;
		this.value = value;
	}

	public ParamBuilder(String type, String value, String documentInfo) {
		this.type = type;
		this.value = value;
		this.documentInfo = documentInfo;
	}

	public ParamBuilder(String type, String value, String documentInfo, AnnotationBuilder annotationBuilder) {
		this.type = type;
		this.value = value;
		this.documentInfo = documentInfo;
		this.annotationBuilder = annotationBuilder;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDocumentInfo() {
		return documentInfo;
	}

	public void setDocumentInfo(String documentInfo) {
		this.documentInfo = documentInfo;
	}

	public AnnotationBuilder getAnnotationBuilder() {
		return annotationBuilder;
	}

	public void setAnnotationBuilder(AnnotationBuilder annotationBuilder) {
		this.annotationBuilder = annotationBuilder;
	}
}
