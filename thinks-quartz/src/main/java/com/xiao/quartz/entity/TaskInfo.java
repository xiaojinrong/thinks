package com.xiao.quartz.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 任务信息
 * @author XiaoJinRong
 * @times 2018年10月26日 上午9:08:30 
 * @version 1.0
 */
public class TaskInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 任务名称 */
	private String name;
	
	/** 任务分组 */
	private String group;
	
	/** 任务描述 */
	private String description;
	
	/** jobClass */
	private String jobClass;

	/** 传入参数 */
	private Map<? extends String,? extends Object> dataMap;
	
	/** 触发器 */
	private List<TriggerInfo> triggerInfos = new ArrayList<TriggerInfo>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<? extends String, ? extends Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<? extends String, ? extends Object> dataMap) {
		this.dataMap = dataMap;
	}

	public List<TriggerInfo> getTriggerInfos() {
		return triggerInfos;
	}

	public void setTriggerInfos(List<TriggerInfo> triggerInfos) {
		this.triggerInfos = triggerInfos;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	
}
