package com.xiao.quartz.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 触发器信息
 * @author XiaoJinRong
 * @times 2018年10月26日 上午11:29:32 
 * @version 1.0
 */
public class TriggerInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** 触发器名称 */
	private String name;
	
	/** 触发器组名 */
	private String group;
	
	/** 触发器状态 */
	private String status;
	
	/** 触发器描述 */
	private String description;
	
	/** 触发器表达式 */ //[*/5 * * * * ?]
	private String cronExpression;
	
	/** 下次执行时间 */
	private Date nextTime;
	
	/** 上次执行时间 */
	private Date prevTime;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Date getNextTime() {
		return nextTime;
	}

	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}

	public Date getPrevTime() {
		return prevTime;
	}

	public void setPrevTime(Date prevTime) {
		this.prevTime = prevTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
