package com.xiao.tools.crawler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * HtmlUnit解析HTML工具
 * 
 * @author XiaoJinRong
 * @times 2018年5月16日 上午10:37:47
 * @version 1.0
 */
public class HtmlUnitUtil {

	private static final String USER_AGENT_KEY = "User-Agent";

	private static final String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.90 Safari/537.36";

	public static String html(String url) {
		try (WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
			// 启动JS
			webClient.getOptions().setJavaScriptEnabled(true);
			// 禁用Css，可避免自动二次请求Css进行渲染
			webClient.getOptions().setCssEnabled(false);
			// 启动客户端重定向
			webClient.getOptions().setRedirectEnabled(true);
			// JS运行错误时，设置不抛出异常
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setActiveXNative(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			// 设置超时时间
			webClient.getOptions().setTimeout(5000);
			webClient.addRequestHeader(USER_AGENT_KEY, USER_AGENT_VALUE);
			// 获取网页
			HtmlPage page = webClient.getPage(url);
			// 等待JS驱动dom完成获得还原后的网页
			webClient.waitForBackgroundJavaScript(500);
			return page.asXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}