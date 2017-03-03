/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: Mapping.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-2-19        | JFTT)wangjian    | original version
 */
package com.jftt.elasticsearch.bean;

import com.jftt.elasticsearch.common.ElasticConstant.MappingIndex;
import com.jftt.elasticsearch.common.ElasticConstant.MappingStore;
import com.jftt.elasticsearch.common.ElasticConstant.MappingType;

/**
 * 数据库类型
 */
public class MappingProperty {
	
	public MappingProperty() {
		super();
	}
	
	public MappingProperty(String name, String type, String store,
			String index, String format, String search_analyzer) {
		super();
		this.name = name;
		this.type = type;
		Store = store;
		this.index = index;
		this.format = format;
		this.search_analyzer = search_analyzer;
	}

	/**
	 * 字段名称
	 * 必填
	 */
	private String name;

	/**
	 * 数据类型  必填
	 * 默认 string
	 */
	private String type = MappingType.string.getValue();
	
	/**
	 * 是否保存  必填
	 * 默认yes
	 */
	private String Store = MappingStore.yes.getValue();
	
	/**
	 * 是否分词 必填
	 * 默认yes
	 */
	private String index = MappingIndex.yes.getValue();
	
	/**
	 * date 格式类型  不必填
	 * 只针对 date数据类型
	 * yyyy-MM-dd HH:mm:ss  ...
	 */
	private String format;
	
	/**
	 * 使用的分词器  不必填
	 */
	private String search_analyzer;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStore() {
		return Store;
	}

	public void setStore(String store) {
		Store = store;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getSearch_analyzer() {
		return search_analyzer;
	}

	public void setSearch_analyzer(String search_analyzer) {
		this.search_analyzer = search_analyzer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
