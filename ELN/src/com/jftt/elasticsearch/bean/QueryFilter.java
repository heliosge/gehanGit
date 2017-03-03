/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: Mapping.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-2-19        | JFTT)wangjian    | original version
 */
package com.jftt.elasticsearch.bean;

/**
 * 查询 过滤器
 */
public class QueryFilter extends Filter{
	
	public QueryFilter() {
		super();
	}
	
	public QueryFilter(String field, String value) {
		super();
		this.field = field;
		this.value = value;
	}


	/**
	 * 需要过滤的列
	 */
	private String field;
	
	/**
	 * 需要过滤的值
	 */
	private String value;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
