package com.xiao.tools.net;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import com.xiao.tools.constant.SystemUtil;
import com.xiao.tools.string.StringUtil;

/**
 * 下载工具类
 * @author XiaoJinRong
 * @times 2018年11月26日 上午11:52:52 
 * @version 1.0
 */
public class DownLoadUtil {
	
	/**
	 * 下载时将字符串转为IE浏览器能识别的字符串防止乱码
	 * @param fileName
	 * @param request
	 * @return
	 */
	public static String parseIEString(String fileName,HttpServletRequest request){
		String result = fileName;
		if(StringUtil.isEmpty(result)){
			return "";
		}
		try{
			String userAgent = request.getHeader("user-agent").toLowerCase();  
		    if (userAgent.contains("msie") || userAgent.contains("like gecko") ) {  
		         // win10 ie edge 浏览器 和其他系统的ie  
		    	result = URLEncoder.encode(result, SystemUtil.Character.UTF_8);  
		    } else {  
		        // fe  
		    	result = new String(result.getBytes(SystemUtil.Character.UTF_8), SystemUtil.Character.ISO_8859_1);  
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
