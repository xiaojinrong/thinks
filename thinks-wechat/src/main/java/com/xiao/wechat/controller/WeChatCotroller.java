package com.xiao.wechat.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiao.wechat.service.WeChatServiceAPI;
import com.xiao.wechat.util.WechatUtil;

@RestController
@RequestMapping("/weChat")
public class WeChatCotroller {

	@Resource
	private WeChatServiceAPI weChatServiceAPI;

	/**
	 * 验证消息的确来自微信服务器
	 * 
	 * @param signature
	 *            微信加密签名
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机数
	 * @param echostr
	 *            随机字符串
	 * @return 随机字符串
	 */
	@GetMapping("/checkSignature")
	public String checkSignature(String signature, String timestamp, String nonce, String echostr) {
		return WechatUtil.checkSignature(signature, timestamp, nonce) ? echostr : "";
	}

	/**
	 * 接收微信服务器事件
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/checkSignature")
	public String processMsg(HttpServletRequest request) {
		return weChatServiceAPI.processRequest(request);
	}
}
