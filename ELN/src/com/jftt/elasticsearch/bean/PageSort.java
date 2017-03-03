/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: Mapping.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-2-19        | JFTT)wangjian    | original version
 */
package com.jftt.elasticsearch.bean;

import com.jftt.elasticsearch.common.ElasticConstant.SortType;

/**
 * 分页排序
 */
public class PageSort {
	
	public PageSort() {
		super();
	}
	
	public PageSort(Integer from, Integer size, String field, String sort) {
		super();
		this.from = from;
		this.size = size;
		this.field = field;
		this.sort = sort;
	}

	private Integer from;
	private Integer size;
	
	private String field;  //列名
	private String sort = SortType.asc.getValue();
	
	public Integer getFrom() {
		return from;
	}
	public void setFrom(Integer from) {
		this.from = from;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

}
