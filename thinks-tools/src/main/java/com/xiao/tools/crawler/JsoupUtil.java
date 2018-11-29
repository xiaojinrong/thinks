package com.xiao.tools.crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {

	/**
	 * 根据属性获取路径等
	 * 
	 * @param html
	 * @param selectCss
	 * @param attr
	 *            [abs:src|abs:href]
	 * @return
	 */
	public static List<String> getJsoupByAttr(String html, String selectCss, String attr) {
		attr = "src".equals(attr) || "href".equals(attr) ? "abs:" + attr : attr;
		Document document = Jsoup.parse(html);
		Elements elements = document.select(selectCss);
		List<String> links = new ArrayList<String>();
		for (Element element : elements) {
			String link = element.attr(attr);
			if (!StringUtil.isEmpty(link)) {
				links.add(link);
			}
		}
		return links;
	}

	/**
	 * 指定内容获取
	 * 
	 * @param html
	 * @return
	 */
	public static List<String> getJsoup(String html, String cssSelect) {
		Document document = Jsoup.parse(html);
		Elements elements = document.select(cssSelect);
		List<String> contents = new ArrayList<String>();
		for (Element element : elements) {
			String content = element.html();
			String lineSeparator = System.getProperty("line.separator");
			content = content.replaceAll("(<br/?>)", lineSeparator).replaceAll("</p>", lineSeparator + lineSeparator)
					.replaceAll("(<p>)||", "").trim();
			contents.add(content);
		}
		return contents;
	}

	/**
	 * 获取百度Script的JSON
	 * 
	 * @param html
	 * @param beginText
	 * @param endText
	 * @return
	 */
	public static String getJsoupScriptJson(String html, int scriptIndex, String beginText, String endText) {
		Document document = Jsoup.parse(html);
		Elements elements = document.getElementsByTag("script").eq(scriptIndex);
		String scriptText = elements.html();
		int beginIndex = scriptText.indexOf(beginText) + beginText.length();
		int endIndex = scriptText.indexOf(endText);
		String selectContent = scriptText.substring(beginIndex, endIndex);
		String jsonContent = selectContent.trim().substring(selectContent.indexOf("{") - 1,
				selectContent.lastIndexOf("}"));
		return jsonContent;
	}

}
