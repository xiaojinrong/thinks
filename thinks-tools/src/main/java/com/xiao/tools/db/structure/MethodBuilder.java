package com.xiao.tools.db.structure;

import java.util.ArrayList;
import java.util.List;

import com.xiao.tools.string.StringUtil;

/**
 * 方法构建
 * 
 * @author XiaoJinRong
 * @times 2018年8月13日 上午9:52:38
 * @version 1.0
 */
public class MethodBuilder {

	/** 访问修饰符(暂时只需要一个) */
	private String access;

	/** 返回值类型 */
	private String returnType;

	/** 属性名 */
	private String name;

	/** 参数列表集合 */
	private List<ParamBuilder> paramBuilders = new ArrayList<ParamBuilder>();

	/** 内容 */
	private String content;

	/** 注解 */
	private List<AnnotationBuilder> annotationBuilders = new ArrayList<AnnotationBuilder>();

	/** 文档注释 */
	private String documentInfo;

	public MethodBuilder() {
	}

	public MethodBuilder(String access, String returnType, String name) {
		this.access = access;
		this.returnType = returnType;
		this.name = name;
	}

	public MethodBuilder(String access, String returnType, String name, String content) {
		this.access = access;
		this.returnType = returnType;
		this.name = name;
		this.setContent(content);
	}

	public MethodBuilder(String returnType, String name, List<ParamBuilder> paramBuilders, String documentInfo) {
		this.returnType = returnType;
		this.name = name;
		this.paramBuilders = paramBuilders;
		this.setDocumentInfo(documentInfo);
	}

	public MethodBuilder(String access, String returnType, String name, List<ParamBuilder> paramBuilders,
			String documentInfo) {
		this.access = access;
		this.returnType = returnType;
		this.name = name;
		this.paramBuilders = paramBuilders;
		this.setDocumentInfo(documentInfo);
	}

	public MethodBuilder(String access, String returnType, String name, List<ParamBuilder> paramBuilders,
			String content, String documentInfo) {
		this.access = access;
		this.returnType = returnType;
		this.name = name;
		this.paramBuilders = paramBuilders;
		this.setContent(content);
		this.setDocumentInfo(documentInfo);
	}

	public MethodBuilder(String access, String returnType, String name, List<ParamBuilder> paramBuilders,
			String content, List<AnnotationBuilder> annotationBuilders, String documentInfo) {
		this.access = access;
		this.returnType = returnType;
		this.name = name;
		this.paramBuilders = paramBuilders;
		this.setContent(content);
		this.annotationBuilders = annotationBuilders;
		this.setDocumentInfo(documentInfo);
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ParamBuilder> getParamBuilders() {
		return paramBuilders;
	}

	public void setParamBuilders(List<ParamBuilder> paramBuilders) {
		this.paramBuilders = paramBuilders;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (StringUtil.isEmpty(this.content)) {
			this.content = content;
		} else {
			this.content = StringUtil.parseStr(this.content) + StringUtil.LINE + StringUtil.SPACE_EIGHT + content;
		}
	}

	public List<AnnotationBuilder> getAnnotationBuilders() {
		return annotationBuilders;
	}

	public void setAnnotationBuilders(List<AnnotationBuilder> annotationBuilders) {
		this.annotationBuilders = annotationBuilders;
	}

	/**
	 * @param type
	 * @return
	 */
	public String getDocumentInfo() {
		return documentInfo;
	}

	public void setDocumentInfo(String documentInfo) {
		StringBuffer sb = new StringBuffer();
		String anno = "     * ";
		sb.append(StringUtil.SPACE_FOUR).append("/** ").append(StringUtil.LINE).append(anno).append(documentInfo)
				.append(StringUtil.LINE);
		for (ParamBuilder paramBuilder : paramBuilders) {
			sb.append(anno).append("@param ").append(paramBuilder.getValue());
			sb.append(" ").append(paramBuilder.getDocumentInfo()).append(StringUtil.LINE);
		}
		// 判断是否有返回值
		if (!"void".equals(this.returnType)) {
			sb.append(anno).append("@return ").append(StringUtil.LINE);
		}
		sb.append(StringUtil.SPACE_FOUR).append(" */");
		this.documentInfo = sb.toString();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (!StringUtil.isEmpty(this.documentInfo)) {
			sb.append(this.documentInfo).append(StringUtil.LINE);
		}
		for (AnnotationBuilder annotationBuilder : annotationBuilders) {
			sb.append(annotationBuilder);
			sb.append(StringUtil.LINE);
		}
		sb.append(StringUtil.SPACE_FOUR).append(StringUtil.parseStr(this.access)).append(" ").append(this.returnType)
				.append(" ");
		sb.append(this.name).append("(");
		String paramString = "";
		for (ParamBuilder paramBuilder : paramBuilders) {
			paramString += StringUtil.parseStr(paramBuilder.getAnnotationBuilder()) + " " + paramBuilder.getType() + " "
					+ paramBuilder.getValue() + ",";
		}
		paramString = paramString != "" ? paramString.substring(0, paramString.lastIndexOf(",")) : paramString;
		sb.append(paramString.trim());
		sb.append(")");
		// 判断是否是抽象方法
		if (this.access == null || this.content == null || this.access.contains("abstract")) {
			sb.append(";");
		} else {
			sb.append("{").append(StringUtil.LINE).append(StringUtil.SPACE_EIGHT)
					.append(StringUtil.parseStr(this.content));
			sb.append(StringUtil.LINE).append(StringUtil.SPACE_FOUR).append("}");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		List<ParamBuilder> paramBuilders = new ArrayList<ParamBuilder>();
		paramBuilders.add(new ParamBuilder("String", "id", "主键ID"));
		MethodBuilder methodBuilder = new MethodBuilder("public", "User", "get", paramBuilders, "根据主键ID获取User对象信息");
		List<AnnotationBuilder> annotationBuilders = new ArrayList<AnnotationBuilder>();
		annotationBuilders.add(new AnnotationBuilder("name", "appi"));
		methodBuilder.setAnnotationBuilders(annotationBuilders);
		methodBuilder.setContent("StringBuffer sb = new StringBuffer();");
		methodBuilder.setContent("StringBuffer sb = new StringBuffer();");
		System.out.println(methodBuilder);
	}
}