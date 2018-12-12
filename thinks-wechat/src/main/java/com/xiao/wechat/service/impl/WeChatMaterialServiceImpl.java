package com.xiao.wechat.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.xiao.wechat.service.WeChatMaterialServiceAPI;

@Service
public class WeChatMaterialServiceImpl extends BaseWeChatService implements WeChatMaterialServiceAPI {

	@Override
	public String uploadMedia(Map<String, Object> paramMap) {
		return request("uploadMedia", paramMap);
	}

	@Override
	public String getMedia(Map<String, Object> paramMap) {
		return request("getMedia", paramMap);
	}

	@Override
	public String addMaterial(Map<String, Object> paramMap) {
		return request("addMaterial", paramMap);
	}

	@Override
	public String uploadMediaImg(Map<String, Object> paramMap) {
		return request("uploadMediaImg", paramMap);
	}

	@Override
	public String uploadMediaOthers(Map<String, Object> paramMap) {
		return request("uploadMediaOthers", paramMap);
	}

	@Override
	public String getMaterial(Map<String, Object> paramMap) {
		return request("getMaterial", paramMap);
	}

	@Override
	public String removeMaterial(Map<String, Object> paramMap) {
		return request("removeMaterial", paramMap);
	}

	@Override
	public String updateMaterial(Map<String, Object> paramMap) {
		return request("updateMaterial", paramMap);
	}

	@Override
	public String getMaterialCount() {
		return requestToken("getMaterialCount");
	}

	@Override
	public String getMaterialList(Map<String, Object> paramMap) {
		return request("getMaterialList", paramMap);
	}

}
