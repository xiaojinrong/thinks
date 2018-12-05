package com.xiao.quartz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.xiao.quartz.entity.TaskInfo;
import com.xiao.quartz.service.JobService;

@RestController
@RequestMapping("/quartz")
public class JobController {

	@Autowired
	private JobService jobService;

	/**
	 * 创建cron任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	// {"name":"cron-1","group":"cron-group-1","description":"我是备注","jobClass":"com.xiao.quartz.task.CronJob","dataMap":{"taskData":"我是参数"},"triggerInfos":[{"name":"cron-1_trigger","group":"cron-group-1_trigger","description":"我是触发器描述","cronExpression":"*/5
	// * * * * ?"}]}
	@RequestMapping(value = "/cron", method = RequestMethod.POST)
	public String startCronJob(@RequestBody TaskInfo taskInfo) {
		jobService.addCronJob(taskInfo);
		return "create cron task success";
	}

	/**
	 * 创建异步任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	@RequestMapping(value = "/async", method = RequestMethod.POST)
	public String startAsyncJob(@RequestBody TaskInfo taskInfo) {
		jobService.addAsyncJob(taskInfo);
		return "create async task success";
	}

	/**
	 * 暂停任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	@RequestMapping(value = "/pause", method = RequestMethod.POST)
	public String pauseJob(String triggerName, String triggerGroup) {
		jobService.pauseOrResumeTrigger(triggerName, triggerGroup, 1);
		return "pause job success";
	}

	/**
	 * 恢复任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	@RequestMapping(value = "/resume", method = RequestMethod.POST)
	public String resumeJob(String triggerName, String triggerGroup) {
		jobService.pauseOrResumeTrigger(triggerName, triggerGroup, 0);
		return "resume job success";
	}

	/**
	 * 删除任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteJob(String jobName, String jobGroup) {
		jobService.deleteJob(jobName, jobGroup);
		return "delete job success";
	}

	/**
	 * 获取全部任务
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findAll")
	public String findAll() {
		return JSON.toJSONString(jobService.findAll());
	}

	/**
	 * 查看任务详情
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	@RequestMapping(value = "/getTask")
	public String get(String jobName, String jobGroup) {
		return JSON.toJSONString(jobService.get(jobName, jobGroup));
	}

	/**
	 * 给task添加新的触发器
	 * 
	 * @param taskInfo
	 * @return
	 */
	@RequestMapping(value = "/addTriggerByJob")
	public String addTriggerByJob(@RequestBody TaskInfo taskInfo) {
		jobService.addTriggerByJob(taskInfo);
		return "add Trigger task success";
	}
}
