package com.xiao.tools.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 
 * 集合工具类
 * @author XiaoJinRong
 * @times 2018年11月27日 上午9:24:53 
 * @version 1.0
 */
public class CollectionUtil {


	/**
	 * List简单集合去重
	 * @param list
	 * @param object
	 * @return
	 */
	public static List<?> repeatList(List<?> source){
		return source.stream().distinct().collect(Collectors.toList());
	}

	/**
	 * List<Map>去重
	 * @param source
	 * @param objects
	 * @return
	 */
	public static List<Map<String,Object>> repeatList(List<Map<String,Object>> source,String key){
		Set<Map<String,Object>> resultSet = new TreeSet<>((o1, o2) -> ((String) o1.get(key)).compareTo((String) o2.get(key)));
		resultSet.addAll(source);
		return new ArrayList<>(resultSet);
	}
	
	/**
	 * 判断集合是否为空
	 * @param source
	 * @return
	 */
	public static boolean isEmpty(Collection<? extends Object> source) {
		return source==null||source.size()==0;
	}
}
