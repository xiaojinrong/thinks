package com.xiao.activiti.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiao.tools.string.StringUtil;

@Service
public class TestService {

	@Autowired
	private TaskService taskService;

	@Autowired
	private RuntimeService runtimeService;

	/**
	 * 启动流程
	 */
	public void startProcess(String bizId) {
		ProcessInstance process = runtimeService.startProcessInstanceByKey("myProcess", bizId);
		System.err.println("流程启动成功，流程ID" + process.getId());
	}

	/**
	 * 根据用户id查询待办任务列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<Task> findTasksByUserId(String userId) {
		List<Task> resultTask = taskService.createTaskQuery().processDefinitionKey("myProcess")
				.taskCandidateOrAssigned(userId).list();
		return resultTask;
	}

	/**
	 * 更改流程图状态
	 * 
	 * @param execution
	 * @param status
	 */
	public void shenqing(DelegateExecution execution, String status) {
		String key = execution.getProcessBusinessKey();
		System.out.println("流程【" + key + "】，状态更改为：" + status);
	}

	/**
	 * 员工申请
	 * 
	 * @param execution
	 * @return
	 */
	public List<String> findUserBySQ(DelegateExecution execution) {
		updateStatus(execution, "员工申请");
		return Arrays.asList("sq-1");
	}

	/**
	 * 领导审批
	 * 
	 * @param execution
	 * @return
	 */
	public List<String> findUserBySP(DelegateExecution execution) {
		updateStatus(execution, "领导审批");
		return Arrays.asList("sp-1", "sp-2", "sp-3");
	}

	/**
	 * 任务审批（执行任务）
	 * 
	 * @param taskId
	 * @param userId
	 * @param result
	 */
	public void completeTask(String taskId, String userId, String result) {
		// 判断任务是否被认领，如果已被认领则当前人员无法完成任务
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (StringUtil.isEmpty(task.getAssignee()) || StringUtil.equals(task.getAssignee(), userId)) {
			// 认领任务
			taskService.claim(taskId, userId);
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("sign", result);
			// 完成任务
			taskService.complete(taskId, vars);
			System.err.println("任务完成");
		}
	}

	/**
	 * 更新状态
	 * 
	 * @param execution
	 * @param status
	 */
	public void updateStatus(DelegateExecution execution, String status) {
		String processKey = execution.getProcessBusinessKey();
		execution.setVariable("status", status);
		System.err.println("业务表【" + processKey + "】当前状态：" + status);
	}

	/**
	 * 获取工作流当前状态
	 * 
	 * @param processKey
	 */
	public void getStatus(String taskId) {
		taskService.getVariable(taskId, "status");
	}
}
