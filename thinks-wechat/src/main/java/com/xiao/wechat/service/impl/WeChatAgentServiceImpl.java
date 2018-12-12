package com.xiao.wechat.service.impl;

import java.util.Map;

import com.xiao.wechat.service.WeChatAgentServiceAPI;

public class WeChatAgentServiceImpl extends BaseWeChatService implements WeChatAgentServiceAPI {

	@Override
	public String addAgent(Map<String, Object> paramMap) {
		return request("addAgent", paramMap);
	}

	@Override
	public String updateAgent(Map<String, Object> paramMap) {
		return request("updateAgent", paramMap);
	}

	@Override
	public String removeAgent(Map<String, Object> paramMap) {
		return request("removeAgent", paramMap);
	}

	@Override
	public String getAllAgent() {
		return requestToken("getAllAgent");
	}

	@Override
	public String sendMsgByAgent(Map<String, Object> paramMap) {
		return request("sendMsgByAgent", paramMap);
	}

	@Override
	public String sendAgentStatus(Map<String, Object> paramMap) {
		return request("sendAgentStatus", paramMap);
	}

}
