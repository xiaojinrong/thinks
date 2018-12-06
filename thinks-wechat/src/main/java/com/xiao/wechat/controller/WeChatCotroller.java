package com.xiao.wechat.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiao.wechat.config.WeChatConfig;
import com.xiao.wechat.service.impl.WeChatService;
import com.xiao.wechat.util.WechatUtil;

@RestController
@RequestMapping("/weChat")
public class WeChatCotroller {

	/** 微信信息配置 */
	@Resource
	private WeChatConfig weChatConfig;

	@Resource
	private WeChatService weChatService;

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
		String source = WechatUtil.checkSignature(weChatConfig.getToken(), timestamp, nonce);
		return source.equalsIgnoreCase(signature) ? echostr : "";
	}

	/**
	 * 获取access_token
	 * 
	 * @return
	 */
	@GetMapping("/getAccessToken")
	public String getAccessToken() {
		return weChatService.getAccessToken();
	}

	/**
	 * 获取微信服务器IP地址
	 * 
	 * @return
	 */
	@GetMapping("/getWechatIp")
	public String getWechatIp() {
		return weChatService.getWechatIp();
	}

	/**
	 * 网络检查
	 * 
	 * @return
	 */
	@GetMapping("/checkNet")
	public String checkNet() {
		return weChatService.checkNet();
	}

}
