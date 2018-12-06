package com.xiao.wechat.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.xiao.wechat.service.WeChatMenuServiceAPI;

@Service
public class WeChatMenuServiceImpl extends BaseWeChatService implements WeChatMenuServiceAPI {

	@Override
	public String getMenu() {
		return requestToken("getMenu");
	}

	@Override
	public String createMenu(Map<String, Object> paramMap) {
		return request("createMenu", paramMap);
	}

}
