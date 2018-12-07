package com.xiao.tools.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.xiao.tools.reflect.ReflectUtil;
import com.xiao.tools.string.StringUtil;

@SuppressWarnings("unchecked")
public class XmlUtil {

	/**
	 * IO解析XML
	 * 
	 * @param inputStream
	 * @return
	 */
	public static Map<String, String> parseObjectByInput(InputStream in) {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		try {
			// 读取输入流
			SAXReader reader = new SAXReader();
			Document document;
			document = reader.read(in);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			List<Element> elementList = root.elements();
			// 遍历所有子节点
			for (Element e : elementList) {
				map.put(e.getName(), e.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * IO解析XML
	 * 
	 * @param inputStream
	 * @return
	 */
	public static Object parseObjectByInput(InputStream in, Class<?> clazz) {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(in);
			return parseXmlObject(document, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 对象转xml
	 * 
	 * @param object
	 * @return
	 */
	public static Document parseXmlByObject(Object object) {
		Document document = DocumentHelper.createDocument();
		try {
			// 创建根节点元素
			Class<?> clazz = object.getClass();
			Element root = document.addElement(clazz.getSimpleName());
			List<Field> fields = ReflectUtil.getAllField(clazz);
			for (Field field : fields) {
				String name = StringUtil.firstWordUpper(field.getName());
				Method getMethod = clazz.getMethod("get" + name);
				String value = StringUtil.toString(getMethod.invoke(object));
				Element element = root.addElement(name);
				element.setText(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * 对象转xml格式的字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String parseXmlString(Object object) {
		return toString(parseXmlByObject(object));
	}

	/**
	 * 微信对象转xml格式的字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String parseXmlStringWX(Object object) {
		Document document = parseXmlByObject(object);
		document.getRootElement().setName("xml");
		return toString(document);
	}

	/**
	 * Xml转为字符串
	 * 
	 * @param document
	 * @return
	 */
	public static String toString(Document document) {
		OutputFormat format = new OutputFormat();
		format.setEncoding("UTF-8");
		// 全部为<br></br>
		format.setExpandEmptyElements(true);
		// 删除XML头
		format.setSuppressDeclaration(true);
		format.setNewLineAfterDeclaration(false);
		StringWriter out = new StringWriter();
		XMLWriter writer = new XMLWriter(out, format);
		try {
			writer.write(document);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toString();
	}

	/**
	 * xml文档Document转对象
	 * 
	 * @param document
	 * @param clazz
	 * @return
	 */
	public static Object parseXmlObject(Document document, Class<?> clazz) {
		// 获取根节点
		Element root = document.getRootElement();
		try {
			Object object = clazz.newInstance();// 创建对象
			List<Element> properties = root.elements();
			for (Element pro : properties) {
				ReflectUtil.setAttribute(object, pro.getName(), pro.getText());
			}
			return object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * xml字符串转对象
	 * 
	 * @param xmlString
	 *             
	 * @param clazz
	 * @return
	 */
	public static Object parseObjectByXmlString(String xmlString, Class<?> clazz) {
		try {
			Document document = DocumentHelper.parseText(xmlString);
			return parseXmlObject(document, clazz);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
}
