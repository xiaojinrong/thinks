package com.xiao.wechat.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xiao.wechat.service.impl.WeChatService;

/**
 * 定时获取Access_Token
 * 
 * @author XiaoJinRong
 * @times 2018年12月6日 下午2:10:41
 * @version 1.0
 */
@Component
public class WeChatTokenTask {

	@Resource
	private WeChatService weChatService;

	/**
	 * 定时获取Access_Token
	 */
	@Scheduled(fixedDelay = 7100000)
	public void requestTokenTask() {
		weChatService.getAccessToken();
	}
}
