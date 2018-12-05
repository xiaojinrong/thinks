package com.xiao.quartz.service;

import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;

import com.xiao.quartz.entity.TaskInfo;
import com.xiao.quartz.entity.TriggerInfo;


public interface JobService {
	
	/**
     * 添加一个定时任务
     * @param jobName
     * @param jobGroup
     */
    void addCronJob(TaskInfo taskInfo);

    /**
     * 添加异步任务
     * @param jobName
     * @param jobGroup
     */
    void addAsyncJob(TaskInfo taskInfo);
    
    /**
     * 创建新的触发器
     * @param triggerInfo
     * @return
     */
    CronTrigger createTrigger(JobDetail jobDetail,TriggerInfo triggerInfo); 

    /**
     * 为任务添加新的触发器
     * @param triggerInfo
     */
    void addTriggerByJob(TaskInfo taskInfo);
    
    /**
     * 暂停or恢复任务触发器
     * @param triggerName
     * @param triggerGroup
     * @param status 0：恢复，其它：暂停
     */
    void pauseOrResumeTrigger(String triggerName,String triggerGroup,int status);

    /**
     * 删除job
     * @param jobName
     * @param jobGroup
     */
    void deleteJob(String jobName,String jobGroup);
    
    /**
	 * 获取任务表集合
	 * @return
	 */
	List<TaskInfo> findAll();
	
	/**
	 * 根据Id查询
	 * @param id
	 * @return
	 */
	TaskInfo get(String jobName,String jobGroup);
}
