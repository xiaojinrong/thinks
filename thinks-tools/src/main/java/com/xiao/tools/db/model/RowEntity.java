package com.xiao.tools.db.model;

import java.io.Serializable;

/**
 * 数据行结构
 * @author XiaoJinRong
 * @times 2018年8月7日 下午4:25:43 
 * @version 1.0
 */
public class RowEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 名称 */
	private String name;
	
	/** 类型 */
	private String type;
	
	/** 长度 */
	private int length;

	/** 小数点 */
	private int digits;
	
	/** 是否为null 0:not null 1:null */
	private int isNull;
	
	/** 是否是主键 */
	private boolean isPrimary;
	
	/** 默认值 */
	private String defaultValue;
	
	/** 注释 */
	private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}

	public int getIsNull() {
		return isNull;
	}

	public void setIsNull(int isNull) {
		this.isNull = isNull;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "RowEntity [name=" + name + ", type=" + type + ", length=" + length + ", digits=" + digits + ", isNull="
				+ isNull + ", isPrimary=" + isPrimary + ", defaultValue=" + defaultValue + ", remark=" + remark + "]";
	}
}
