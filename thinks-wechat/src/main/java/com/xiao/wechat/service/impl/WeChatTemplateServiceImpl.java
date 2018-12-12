package com.xiao.wechat.service.impl;

import java.util.Map;

import com.xiao.wechat.service.WeChatTemplateServiceAPI;

public class WeChatTemplateServiceImpl extends BaseWeChatService implements WeChatTemplateServiceAPI {

	@Override
	public String setTemplateIndustry(Map<String, Object> paramMap) {
		return request("setTemplateIndustry", paramMap);
	}

	@Override
	public String getTemplateIndustryInfo() {
		return requestToken("getTemplateIndustryInfo");
	}

	@Override
	public String getTemplateId(Map<String, Object> paramMap) {
		return request("getTemplateId", paramMap);
	}

	@Override
	public String getTemplateList() {
		return requestToken("getTemplateList");
	}

	@Override
	public String removeTemplate(Map<String, Object> paramMap) {
		return request("removeTemplate", paramMap);
	}

	@Override
	public String sendTemplateMsg(Map<String, Object> paramMap) {
		return request("sendTemplateMsg", paramMap);
	}

}
