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
 * 范围 过滤器
 */
public class RangerFilter extends Filter{
	
	public RangerFilter() {
		super();
	}
	
	public RangerFilter(String field, String start, String end,
			Boolean startBorder, Boolean endBorder) {
		super();
		this.field = field;
		this.start = start;
		this.end = end;
		this.startBorder = startBorder;
		this.endBorder = endBorder;
	}

	/**
	 * 需要过滤的列
	 */
	private String field;
	
	/**
	 * 开始
	 */
	private String start = "*";
	
	/**
	 * 结束
	 */
	private String end = "*";
	
	/**
	 * 是否包含开始
	 */
	private Boolean startBorder = true;
	
	/**
	 * 是否包含结束
	 */
	private Boolean endBorder = true;
	

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Boolean getStartBorder() {
		return startBorder;
	}

	public void setStartBorder(Boolean startBorder) {
		this.startBorder = startBorder;
	}

	public Boolean getEndBorder() {
		return endBorder;
	}

	public void setEndBorder(Boolean endBorder) {
		this.endBorder = endBorder;
	}

}
