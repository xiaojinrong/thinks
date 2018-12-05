package com.xiao.tools.entity;

public class PageSearch {

	private String key;

	private String value;

	private String relation = DataUtil.EQ;

	private String orAnd = DataUtil.AND;

	public PageSearch() {
	}

	public PageSearch(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public PageSearch(String key, String value, String relation) {
		this.key = key;
		this.value = value;
		this.relation = relation;
	}

	public PageSearch(String key, String value, String relation, String orAnd) {
		this.key = key;
		this.value = value;
		this.relation = relation;
		this.orAnd = orAnd;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getOrAnd() {
		return orAnd;
	}

	public void setOrAnd(String orAnd) {
		this.orAnd = orAnd;
	}
}
