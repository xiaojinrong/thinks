package com.xiao.wechat.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.xiao.tools.date.DateUtil;
import com.xiao.tools.json.JsonUtil;
import com.xiao.tools.number.NumberUtil;
import com.xiao.tools.string.StringUtil;
import com.xiao.tools.xml.XmlUtil;
import com.xiao.wechat.entity.ReplyText;
import com.xiao.wechat.service.WeChatServiceAPI;
import com.xiao.wechat.util.WechatUtil;

@Service
public class WeChatService extends BaseWeChatService implements WeChatServiceAPI {

	@Override
	public String getAccessToken() {
		String result = request("getAccessToken");
		// 保存新access_token
		Map<String, Object> tokenMap = JsonUtil.toMap(result);
		WechatUtil.ACCESS_TOKEN.setAccessToken(StringUtil.toString(tokenMap.get("access_token")));
		WechatUtil.ACCESS_TOKEN.setExpiresIn(NumberUtil.parseInt(tokenMap.get("expires_in")));
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

	@Override
	public String processRequest(HttpServletRequest request) {
		String result = "";
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> resultMap = XmlUtil.parseObjectByInput(request.getInputStream());
			System.err.println("接收消息内容:" + JsonUtil.toString(resultMap));
			// 获取消息类型
			String msgType = resultMap.get("MsgType");
			String fromUserName = resultMap.get("FromUserName");
			String toUserName = resultMap.get("ToUserName");
			switch (msgType) {
			// 文本消息
			case "text":
				System.err.println("文本消息：" + resultMap.get("Content"));
				String msg = "自动回复你的消息都是假的，小伙子";
				ReplyText replyText = new ReplyText(fromUserName, toUserName, DateUtil.getNowSecond(), "text", msg);
				result = XmlUtil.parseXmlStringWX(replyText);
				break;
			// 图片消息
			case "image":
				System.err.println("图片消息：" + resultMap.get("PicUrl"));
				break;
			// 语音消息
			case "voice":
				System.err.println("语音消息");
				break;
			// 视频消息
			case "video":
				System.err.println("视频消息");
				break;
			// 小视频消息
			case "shortvideo":
				System.err.println("小视频消息");
				break;
			// 地理位置消息
			case "location":
				System.err.println("地理位置消息");
				break;
			// 链接消息
			case "link":
				System.err.println("链接消息");
				break;
			// 事件推送消息
			case "event":
				String event = resultMap.get("Event");
				result = eventHandle(event, fromUserName, toUserName, msgType);
				break;
			// 其他事件
			default:
				System.err.println("其他事件");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 接收事件处理
	 * 
	 * @return
	 */
	public String eventHandle(String eventType, String fromUserName, String toUserName, String msgType) {
		String result = "";
		switch (eventType) {
		// 菜单点击事件
		case "CLICK":
			System.err.println("菜单点击事件");
			String msg = "点什么点？";
			ReplyText replyText = new ReplyText(fromUserName, toUserName, DateUtil.getNowSecond(), "text", msg);
			result = XmlUtil.parseXmlStringWX(replyText);
			break;
		// 点击菜单跳转链接时的事件推送
		case "VIEW":
			System.err.println("点击菜单跳转链接时的事件推送");
			break;
		// 扫码推事件的事件推送
		case "scancode_push":
			System.err.println("扫码推事件的事件推送");
			break;
		// 扫码推事件且弹出“消息接收中”提示框的事件推送
		case "scancode_waitmsg":
			System.err.println("扫码推事件且弹出“消息接收中”提示框的事件推送");
			break;
		// 弹出系统拍照发图的事件推送
		case "pic_sysphoto":
			System.err.println("弹出系统拍照发图的事件推送");
			break;
		// 弹出拍照或者相册发图的事件推送
		case "pic_photo_or_album":
			System.err.println("弹出拍照或者相册发图的事件推送");
			break;
		// 弹出微信相册发图器的事件推送
		case "pic_weixin":
			System.err.println("弹出微信相册发图器的事件推送");
			break;
		// 弹出地理位置选择器的事件推送
		case "location_select":
			System.err.println("弹出地理位置选择器的事件推送");
			break;
		// 关注订阅
		case "subscribe":
			System.err.println("关注订阅");
			break;
		// 取消订阅
		case "unsubscribe":
			System.err.println("取消订阅");
			break;
		// 用户已关注点击关注订阅
		case "SCAN":
			System.err.println("用户已关注时的事件推送");
			break;
		// 上报地理位置事件
		case "LOCATION":
			System.err.println("上报地理位置事件");
			break;
		default:
			System.err.println("其他事件");
			break;
		}
		return result;
	}
}
