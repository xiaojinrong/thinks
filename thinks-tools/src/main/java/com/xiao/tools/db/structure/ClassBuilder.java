package com.xiao.tools.db.structure;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.xiao.tools.date.DateUtil;
import com.xiao.tools.string.StringUtil;

/**
 * Bean类
 * 
 * @author XiaoJinRong
 * @times 2018年8月13日 上午9:27:37
 * @version 1.0
 */
public class ClassBuilder {

	/** 所属包名 */
	private String packageName;

	/** 依赖包集合 */
	private Set<String> dependencyPackages = new LinkedHashSet<String>();

	/** 访问修饰符(暂时只需要一个) */
	private String access = "public";

	/** 类型（class|interface|enum） */
	private String type = "class";

	/** 类名 */
	private String className;

	/** 实现接口集合 */
	private List<String> interfaces = new ArrayList<String>();

	/** 父类名 */
	private String parentClass;

	/** 文档注释 */
	private String documentInfo;

	/** 类注解集合 */
	private List<AnnotationBuilder> annotationBuilders = new ArrayList<AnnotationBuilder>();

	/** 属性集合 */
	private List<AttributeBuilder> attributeBuilders = new ArrayList<AttributeBuilder>();

	/** 方法集合 */
	private List<MethodBuilder> methodBuilders = new ArrayList<MethodBuilder>();

	public ClassBuilder() {
	}

	public ClassBuilder(String className, List<AttributeBuilder> attributeBuilders,
			List<MethodBuilder> methodBuilders) {
		this.className = className;
		this.attributeBuilders = attributeBuilders;
		this.methodBuilders = methodBuilders;
	}

	public ClassBuilder(String className, String documentInfo, List<AttributeBuilder> attributeBuilders,
			List<MethodBuilder> methodBuilders) {
		this.className = className;
		this.documentInfo = documentInfo;
		this.attributeBuilders = attributeBuilders;
		this.methodBuilders = methodBuilders;
	}

	public ClassBuilder(Set<String> dependencyPackages, String className, String documentInfo,
			List<AttributeBuilder> attributeBuilders, List<MethodBuilder> methodBuilders) {
		this.dependencyPackages = dependencyPackages;
		this.className = className;
		this.documentInfo = documentInfo;
		this.attributeBuilders = attributeBuilders;
		this.methodBuilders = methodBuilders;
	}

	public ClassBuilder(String packageName, Set<String> dependencyPackages, String access, String className,
			List<String> interfaces, String parentClass, String documentInfo,
			List<AnnotationBuilder> annotationBuilders, List<AttributeBuilder> attributeBuilders,
			List<MethodBuilder> methodBuilders) {
		this.packageName = packageName;
		this.dependencyPackages = dependencyPackages;
		this.access = access;
		this.className = className;
		this.interfaces = interfaces;
		this.parentClass = parentClass;
		this.documentInfo = documentInfo;
		this.annotationBuilders = annotationBuilders;
		this.attributeBuilders = attributeBuilders;
		this.methodBuilders = methodBuilders;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Set<String> getDependencyPackages() {
		return dependencyPackages;
	}

	public void setDependencyPackages(Set<String> dependencyPackages) {
		this.dependencyPackages = dependencyPackages;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<String> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<String> interfaces) {
		this.interfaces = interfaces;
	}

	public String getParentClass() {
		return parentClass;
	}

	public void setParentClass(String parentClass) {
		this.parentClass = parentClass;
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

	public List<AttributeBuilder> getAttributeBuilders() {
		return attributeBuilders;
	}

	public void setAttributeBuilders(List<AttributeBuilder> attributeBuilders) {
		this.attributeBuilders = attributeBuilders;
	}

	public List<MethodBuilder> getMethodBuilders() {
		return methodBuilders;
	}

	public void setMethodBuilders(List<MethodBuilder> methodBuilders) {
		this.methodBuilders = methodBuilders;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		// 是否存在所属包
		if (!StringUtil.isEmpty(this.packageName)) {
			sb.append("package ").append(this.packageName).append(";").append(StringUtil.LINE).append(StringUtil.LINE);
		}
		// 引用依赖包
		for (String dependency : dependencyPackages) {
			sb.append("import").append(" ").append(dependency).append(";").append(StringUtil.LINE);
		}
		// 注释
		sb.append("/**").append(StringUtil.LINE).append(" * ").append(StringUtil.parseStr(this.documentInfo))
				.append(StringUtil.LINE);
		sb.append(" * @author ").append(System.getenv("username")).append(StringUtil.LINE);
		sb.append(" * @times ").append(DateUtil.parseDate(new Date())).append(StringUtil.LINE);
		sb.append(" * @version 1.0").append(StringUtil.LINE).append(" */").append(StringUtil.LINE);
		// 注解
		for (AnnotationBuilder annotationBuilder : this.annotationBuilders) {
			sb.append(annotationBuilder.toString().trim()).append(StringUtil.LINE);
		}
		// 创建类
		sb.append(this.access).append(" ").append(this.type).append(" ").append(this.className).append(" ");
		// 判断是否继承父类
		if (!StringUtil.isEmpty(this.parentClass)) {
			sb.append("extends ").append(this.parentClass).append(" ");
		}
		// 判断是否实现接口
		if (this.interfaces != null && this.interfaces.size() > 0) {
			sb.append("implements ");
			String interfaces = "";
			for (String item : this.interfaces) {
				interfaces += item + ",";
			}
			interfaces = interfaces != "" ? interfaces.substring(0, interfaces.length() - 1) : interfaces;
			sb.append(interfaces);
		}
		sb.append("{").append(StringUtil.LINE).append(StringUtil.LINE);
		// 属性
		for (AttributeBuilder attributeBuilder : this.attributeBuilders) {
			sb.append(attributeBuilder).append(StringUtil.LINE).append(StringUtil.LINE);
		}
		// 方法
		for (MethodBuilder methodBuilder : this.methodBuilders) {
			sb.append(methodBuilder).append(StringUtil.LINE).append(StringUtil.LINE);
		}
		sb.append("}");
		return sb.toString();
	}

}
