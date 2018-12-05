package com.xiao.tools.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页
 * 
 * @author XiaoJinRong
 * @times 2018年11月26日 下午12:24:09
 * @version 1.0
 */
public class Page {

	/** 当前页数 */
	private int pageIndex;

	/** 每页页数 */
	private int pageSize;

	/** 总页数 */
	private int totalPage;

	/** 总数量 */
	private int totalSize;

	/** 起始页 */
	private int startIndex;

	/** 终止页 */
	private int endIndex;

	/** 下一页 */
	private int nextPage;

	/** 上一页 */
	private int prevPage;

	/** 数据 */
	private Object result;

	/** 查询条件 */
	private List<PageSearch> searchParams = new ArrayList<PageSearch>();

	/** 排序条件 */
	private Map<String, String> orderParams = new HashMap<String, String>();

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public List<PageSearch> getSearchParams() {
		return searchParams;
	}

	public void setSearchParams(List<PageSearch> searchParams) {
		this.searchParams = searchParams;
	}

	public Map<String, String> getOrderParams() {
		return orderParams;
	}

	public void setOrderParams(Map<String, String> orderParams) {
		this.orderParams = orderParams;
	}
}
