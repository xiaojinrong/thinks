package com.xiao.data;

import java.util.List;
import java.util.Map;

public interface BaseMapperAPI<T> {

	int save(T entity);

	int update(T entity);

	int delete(String id);

	List<T> queryPage(Map<String, Object> paramMap);

	int getCount(Map<String, Object> paramMap);
}
