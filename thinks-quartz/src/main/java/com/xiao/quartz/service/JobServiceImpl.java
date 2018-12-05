package com.xiao.quartz.service;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.xiao.quartz.entity.TaskInfo;
import com.xiao.quartz.entity.TriggerInfo;

@SuppressWarnings("unchecked")
@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Override
	public void addCronJob(TaskInfo taskInfo) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(taskInfo.getName(), taskInfo.getGroup());
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			if (jobDetail != null) {
				System.out.println("job:" + taskInfo.getName() + " 已存在");
				return;
			}
			Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(taskInfo.getJobClass());
			// 构建job信息
			jobDetail = JobBuilder.newJob(clazz).withIdentity(taskInfo.getName(), taskInfo.getGroup())
					.withDescription(taskInfo.getDescription()).storeDurably(true).build();
			// 用JopDataMap来传递数据
			jobDetail.getJobDataMap().putAll(taskInfo.getDataMap());
			scheduler.addJob(jobDetail, true);
			for (TriggerInfo triggerInfo : taskInfo.getTriggerInfos()) {
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(triggerInfo.getCronExpression());
				// 按新的cronExpression表达式构建一个新的trigger
				CronTrigger trigger = TriggerBuilder.newTrigger()
						.withIdentity(triggerInfo.getName(), triggerInfo.getGroup()).withSchedule(scheduleBuilder)
						.withDescription(triggerInfo.getDescription()).forJob(jobDetail).build();
				scheduler.scheduleJob(trigger);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addAsyncJob(TaskInfo taskInfo) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();

			JobKey jobKey = JobKey.jobKey(taskInfo.getName(), taskInfo.getGroup());
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			if (jobDetail != null) {
				System.out.println("job:" + taskInfo.getName() + " 已存在");
				return;
			}
			Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(taskInfo.getJobClass());
			// 构建job信息,在用JobBuilder创建JobDetail的时候，有一个storeDurably()方法，可以在没有触发器指向任务的时候，将任务保存在队列中了。然后就能手动触发了
			jobDetail = JobBuilder.newJob(clazz).withIdentity(taskInfo.getName(), taskInfo.getGroup()).storeDurably()
					.build();
			jobDetail.getJobDataMap().putAll(taskInfo.getDataMap());
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(taskInfo.getName() + "_trigger", taskInfo.getGroup() + "_trigger").startNow()// 一旦加入scheduler，立即生效
					.withSchedule(simpleSchedule())// 使用SimpleTrigger
					.build();
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CronTrigger createTrigger(JobDetail jobDetail, TriggerInfo triggerInfo) {
		// 表达式调度构建器(即任务执行的时间,每5秒执行一次)
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(triggerInfo.getCronExpression());
		// 按新的cronExpression表达式构建一个新的trigger
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerInfo.getName(), triggerInfo.getGroup())
				.withSchedule(scheduleBuilder).withDescription(triggerInfo.getDescription()).forJob(jobDetail).build();
		return trigger;
	}

	@Override
	public void pauseOrResumeTrigger(String triggerName, String triggerGroup, int status) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
			String result = "resume";
			if (status == 0) {
				scheduler.resumeTrigger(triggerKey);
			} else {
				scheduler.pauseTrigger(triggerKey);
				result = "pause";
			}
			System.out.println("=========================" + result + " triggerName:" + triggerName
					+ " success========================");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteJob(String jobName, String jobGroup) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			scheduler.deleteJob(jobKey);
			System.out.println("=========================delete job:" + jobName + " success========================");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TaskInfo> findAll() {
		List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			List<String> groupNames = scheduler.getJobGroupNames();
			for (String groupName : groupNames) {
				Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEndsWith(groupName));
				for (JobKey jobKey : jobKeys) {
					TaskInfo taskInfo = new TaskInfo();

					taskInfo.setName(jobKey.getName());
					taskInfo.setGroup(jobKey.getGroup());

					List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
					for (Trigger trigger : triggers) {
						TriggerInfo triggerInfo = new TriggerInfo();
						if (trigger instanceof CronTrigger) {
							CronTrigger cronTrigger = (CronTrigger) trigger;
							triggerInfo.setCronExpression(cronTrigger.getCronExpression());
						}
						TriggerKey triggerKey = trigger.getKey();
						triggerInfo.setName(triggerKey.getName());
						triggerInfo.setGroup(triggerKey.getGroup());
						triggerInfo.setDescription(trigger.getDescription());
						triggerInfo.setNextTime(trigger.getNextFireTime());
						triggerInfo.setPrevTime(trigger.getPreviousFireTime());

						TriggerState triggerState = scheduler.getTriggerState(triggerKey);
						String status = "";
						switch (triggerState) {
						case NONE:
							status = "不存在";
							break;
						case NORMAL:
							status = "正常";
							break;
						case PAUSED:
							status = "暂停";
							break;
						case COMPLETE:
							status = "完成";
							break;
						case ERROR:
							status = "错误";
							break;
						case BLOCKED:
							status = "阻塞";
							break;
						default:
							break;
						}
						triggerInfo.setStatus(status);

						taskInfo.getTriggerInfos().add(triggerInfo);
					}
					JobDetail jobDetail = scheduler.getJobDetail(jobKey);
					taskInfo.setDescription(jobDetail.getDescription());
					taskInfo.setDataMap(jobDetail.getJobDataMap());

					taskInfos.add(taskInfo);
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return taskInfos;
	}

	@Override
	public void addTriggerByJob(@RequestBody TaskInfo taskInfo) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(taskInfo.getName(), taskInfo.getGroup());
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			if (jobDetail == null) {
				System.out.println("job:" + taskInfo.getName() + " 不存在");
				return;
			}
			for (TriggerInfo triggerInfo : taskInfo.getTriggerInfos()) {
				CronTrigger trigger = createTrigger(jobDetail, triggerInfo);
				scheduler.scheduleJob(trigger);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TaskInfo get(String jobName, String jobGroup) {
		List<TaskInfo> taskInfos = findAll();
		for (TaskInfo taskInfo : taskInfos) {
			if (taskInfo.getName().equals(jobName) && taskInfo.getGroup().equals(jobGroup)) {
				return taskInfo;
			}
		}
		return null;
	}

}
