package com.xiao.wechat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiao.wechat.service.WeChatMenuServiceAPI;

@RestController
@RequestMapping("/weChat/menu")
public class WeChatMenuController {

	@Resource
	private WeChatMenuServiceAPI weChatMenuServiceAPI;

	@GetMapping("/get")
	public String getMenu() {
		return weChatMenuServiceAPI.getMenu();
	}

	@GetMapping("/create")
	public String createMenu() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return weChatMenuServiceAPI.createMenu(paramMap);
	}

}
