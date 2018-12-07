package com.xiao.wechat.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("/create")
	public String createMenu(@RequestBody Map<String, Object> paramMap) {
		return weChatMenuServiceAPI.createMenu(paramMap);
	}

	@GetMapping("/getSelfmenu")
	public String getSelfmenu() {
		return weChatMenuServiceAPI.getSelfmenu();
	}
}
