/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: Mapping.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-2-19        | JFTT)wangjian    | original version
 */
package com.jftt.elasticsearch.bean;

import com.jftt.elasticsearch.common.ElasticConstant.QueryType;

/**
 * 查询
 */
public class TermQuery {
	
	public TermQuery() {
		super();
	}
	
	public TermQuery(String type, String filed, String value, Float boost) {
		super();
		this.type = type;
		this.filed = filed;
		this.value = value;
		this.boost = boost;
	}

	/**
	 * 是否必须满足
	 */
	private String type = QueryType.must.getValue();
	
	
	/**
	 * 需要查询的列，多个用逗号劈开，null，表示所有列
	 */
	private String filed;
	
	/**
	 * 查询内容
	 */
	private String value;
	
	/**
	 * 权重，数值越大，满足这个条件的结果排序越靠前
	 */
	private Float boost;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFiled() {
		return filed;
	}

	public void setFiled(String filed) {
		this.filed = filed;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Float getBoost() {
		return boost;
	}

	public void setBoost(Float boost) {
		this.boost = boost;
	}

}
