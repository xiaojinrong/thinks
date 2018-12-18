package com.xiao.activiti.test;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xiao.activiti.Application;
import com.xiao.activiti.service.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivitiTest {

	@Autowired
	TestService testService;

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	/**
	 * 开启流程
	 */
	@Test
	public void testStartProcess() {
		testService.startProcess("2");
	}

	/**
	 * 获取批次任务列表
	 */
	@Test
	public void findTaskByUserId() {
		List<Task> tasks = testService.findTasksByUserId("sp-3");
		System.out.println(tasks);
	}

	@Test
	public void completeTasksForSP() {
		testService.completeTask("57504", "sp-1", "true");
	}

}
