package com.xiao.wechat.service.impl;

import org.springframework.stereotype.Service;

import com.xiao.tools.json.JsonUtil;
import com.xiao.tools.string.StringUtil;
import com.xiao.wechat.service.WeChatServiceAPI;
import com.xiao.wechat.util.WechatUtil;

@Service
public class WeChatService extends BaseWeChatService implements WeChatServiceAPI {

	@Override
	public String getAccessToken() {
		String result = request("getAccessToken");
		// 保存新access_token
		WechatUtil.ACCESS_TOKEN = StringUtil.toString(JsonUtil.toMap(result).get("access_token"));
		return result;
	}

	@Override
	public String getWechatIp() {
		return requestToken("getWechatIp");
	}

	@Override
	public String checkNet() {
		return requestToken("checkNet");
	}

}
