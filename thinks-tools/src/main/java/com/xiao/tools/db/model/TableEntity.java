package com.xiao.tools.db.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据表结构
 * @author XiaoJinRong
 * @times 2018年8月7日 下午4:36:40 
 * @version 1.0
 */
public class TableEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 表名称  */
	private String name;
	
	/** 注释 */
	private String remark;
	
	/** 行结构集合 */
	private List<RowEntity> rows = new ArrayList<RowEntity>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<RowEntity> getRows() {
		return rows;
	}

	public void setRows(List<RowEntity> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "TableEntity [name=" + name + ", remark=" + remark + ", rows=" + rows + "]";
	}
}
