package com.xiao.crawler.test;

import java.io.File;
import java.io.IOException;

import com.xiao.tools.crawler.DownFileUtil;

public class Test2 {
	
	public static void main(String[] args) throws IOException {
		String link = "http://dl418.80s.im:920/1811/功夫联盟/功夫联盟.mp4";
		File file = new File("D://1.mp4");
		DownFileUtil.downMediaByUrl(link, file);
	}
	
}
