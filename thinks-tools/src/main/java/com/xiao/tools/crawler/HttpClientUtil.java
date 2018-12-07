package com.xiao.tools.crawler;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.xiao.tools.string.StringUtil;

/**
 * HttpClient工具类
 * 
 * @author XiaoJinRong
 * @times 2018年5月16日 上午9:06:52
 * @version 1.0
 */
public class HttpClientUtil {

	private static final String USER_AGENT_KEY = "User-Agent";

	private static final String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.90 Safari/537.36";

	private static final String REFERER_KEY = "Referer";

	private static final String REFERER_VALUE = "http://www.baidu.com";

	private static final String CHARSET_UTF_8 = "UTF-8";

	private static final int TIME_OUT = 10 * 1000;

	private static final int MAX_TOTAL = 200;

	private static final int MAX_PERROUTE = 40;

	private static final int MAX_ROUTE = 100;

	private static CloseableHttpClient httpClient;

	private final static Object syncLock = new Object();

	private static void config(HttpRequestBase httpRequestBase) {
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT)
				.setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
		httpRequestBase.setHeader(USER_AGENT_KEY, USER_AGENT_VALUE);
		httpRequestBase.setHeader(REFERER_KEY, REFERER_VALUE);
		httpRequestBase.setConfig(requestConfig);
	}

	/**
	 * 创建HttpClient
	 * 
	 * @param maxTotal
	 * @param maxPerRoute
	 * @param maxRoute
	 * @param hostname
	 * @param port
	 * @return
	 */
	private static CloseableHttpClient createHttpClient(int maxTotal, int maxPerRoute, int maxRoute, String hostname,
			int port) {
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", plainsf).register("https", sslsf).build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		// 将最大连接数添加
		cm.setMaxTotal(maxTotal);
		// 将每一个路由基础的连接添加
		cm.setDefaultMaxPerRoute(maxPerRoute);
		HttpHost httpHost = new HttpHost(hostname, port);
		// 将目标主机的最大连接数添加
		cm.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);

		// 请求重试处理
		HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= 5) {// 假设已经重试了5次，就放弃
					return false;
				}
				if (exception instanceof NoHttpResponseException) {// 假设server丢掉了连接。那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
					return false;
				}
				if (exception instanceof InterruptedIOException) {// 超时
					return false;
				}
				if (exception instanceof UnknownHostException) {// 目标server不可达
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
					return false;
				}
				if (exception instanceof SSLException) {// SSL握手异常
					return false;
				}

				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				// 假设请求是幂等的，就再次尝试
				if (!(request instanceof HttpEntityEnclosingRequest)) {
					return true;
				}
				return false;
			}
		};
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm)
				.setRetryHandler(httpRequestRetryHandler).build();
		return httpClient;
	}

	/**
	 * 获取HttpClient
	 * 
	 * @param url
	 * @return
	 */
	private static CloseableHttpClient getHttpClient(String url) {
		String hostname = url.split("/")[2];
		int port = 80;
		if (hostname.contains(":")) {
			String[] arr = hostname.split(":");
			hostname = arr[0];
			port = Integer.parseInt(arr[1]);
		}
		if (httpClient == null) {
			synchronized (syncLock) {
				if (httpClient == null) {
					httpClient = createHttpClient(MAX_TOTAL, MAX_PERROUTE, MAX_ROUTE, hostname, port);
				}
			}
		}
		return httpClient;
	}

	/**
	 * Post请求时参数设置
	 * 
	 * @param httpost
	 * @param params
	 */
	private static void setPostParams(HttpPost httpost, Map<String, String> params) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, CHARSET_UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Post请求内容
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, Map<String, String> params) {
		HttpPost httppost = new HttpPost(url);
		config(httppost);
		setPostParams(httppost, params);
		try (CloseableHttpResponse response = getHttpClient(url).execute(httppost, HttpClientContext.create())) {
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, CHARSET_UTF_8);
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Post请求
	 * 
	 * @param url
	 * @param jsonText
	 *            JSON字符串参数
	 * @return
	 */
	public static String postJson(String url, String jsonText) {
		HttpPost httppost = new HttpPost(url);
		config(httppost);
		StringEntity entitys = new StringEntity(jsonText, CHARSET_UTF_8);
		entitys.setContentType("application/json");
		entitys.setContentEncoding(CHARSET_UTF_8);
		httppost.setEntity(entitys);
		try (CloseableHttpResponse response = getHttpClient(url).execute(httppost, HttpClientContext.create())) {
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, CHARSET_UTF_8);
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get请求内容
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		HttpGet httpget = new HttpGet(url);
		config(httpget);
		try (CloseableHttpResponse response = getHttpClient(url).execute(httpget, HttpClientContext.create())) {
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, CHARSET_UTF_8);
			EntityUtils.consume(entity);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get请求内容
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 */
	public static String get(String url, Map<String, ?> paramMap) {
		String params = "";
		for (Entry<String, ?> param : paramMap.entrySet()) {
			params += param.getKey() + "=" + StringUtil.toString(param.getValue()) + "&";
		}
		String lastCh = url.indexOf("?") == -1 ? "?" : "&";
		url = url + lastCh + StringUtil.subLast(params, "&");
		return get(url);
	}

}
