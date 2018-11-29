package com.xiao.tools.crawler;

/**
 * 百度解析加密图片URL（objURL）
 * 
 * @author XiaoJinRong
 * @times 2018年5月17日 下午2:29:09
 * @version 1.0
 */
public class DecodeBDUtil {
	/**
	 * 解析百度图片网址
	 * 
	 * @param url
	 *            传入的图片加密网址，参数以ippr开头，以_z&e3B3r2结尾
	 * @return 返回真实的网络图片地址
	 */
	public static String decode(String url) {
		String myUrl = "";
		myUrl = url.replace("ippr", "http");
		myUrl = myUrl.replace("_z2C$q", ":");
		myUrl = myUrl.replace("AzdH3F", "/");
		myUrl = myUrl.replace("_z&e3B", ".");
		myUrl = myUrl.toLowerCase();
		myUrl = myUrl.substring(4);
		char[] arr = myUrl.toCharArray();
		myUrl = "";
		for (char c : arr) {
			switch (c) {
			case 'w':
				myUrl += "a";
				break;
			case 'k':
				myUrl += "b";
				break;
			case 'v':
				myUrl += "c";
				break;
			case '1':
				myUrl += "d";
				break;
			case 'j':
				myUrl += "e";
				break;
			case 'u':
				myUrl += "f";
				break;
			case '2':
				myUrl += "g";
				break;
			case 'i':
				myUrl += "h";
				break;
			case 't':
				myUrl += "i";
				break;
			case '3':
				myUrl += "j";
				break;
			case 'h':
				myUrl += "k";
				break;
			case 's':
				myUrl += "l";
				break;
			case '4':
				myUrl += "m";
				break;
			case 'g':
				myUrl += "n";
				break;
			case '5':
				myUrl += "o";
				break;
			case 'r':
				myUrl += "p";
				break;
			case 'q':
				myUrl += "q";
				break;
			case '6':
				myUrl += "r";
				break;
			case 'f':
				myUrl += "s";
				break;
			case 'p':
				myUrl += "t";
				break;
			case '7':
				myUrl += "u";
				break;
			case 'e':
				myUrl += "v";
				break;
			case 'o':
				myUrl += "w";
				break;
			case '8':
				myUrl += "1";
				break;
			case 'd':
				myUrl += "2";
				break;
			case 'n':
				myUrl += "3";
				break;
			case '9':
				myUrl += "4";
				break;
			case 'c':
				myUrl += "5";
				break;
			case 'm':
				myUrl += "6";
				break;
			case '0':
				myUrl += "7";
				break;
			case 'b':
				myUrl += "8";
				break;
			case 'l':
				myUrl += "9";
				break;
			case 'a':
				myUrl += "0";
				break;
			default:
				myUrl += c;
				break;
			}
		}
		return "http" + myUrl;
	}
}
