package com.xiao.wechat.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.xiao.wechat.config.WeChatConfig;

@RestController
public class IndexCotroller {

	@Resource
	private WeChatConfig weChatConfig;

	@GetMapping({ "/", "/index" })
	public String index() {
		return JSON.toJSONString(weChatConfig.getWechatAPIs());
	}
}
