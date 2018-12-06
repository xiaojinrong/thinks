package com.xiao.tools.json;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

@SuppressWarnings("unchecked")
public class JsonUtil {
	
	/**
	 * 对象转为JSON字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toString(Object object) {
		return JSONObject.toJSONString(object, SerializerFeature.WriteMapNullValue,
				SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteDateUseDateFormat);
	}

	/**
	 * JSON字符串转为Map<String,Object>
	 * 
	 * @param jsonText
	 * @return
	 */
	public static Map<String, Object> toMap(String jsonText) {
		Map<String, Object> map = JSON.parseObject(jsonText, Map.class);
		return map;
	}
}
