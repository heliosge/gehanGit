/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: HtmlUtil.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-8        | JFTT)zhangchen    | original version
 */
package com.jftt.elasticsearch.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;

import com.jftt.elasticsearch.bean.BackArray;
import com.jftt.elasticsearch.bean.MappingProperty;
import com.jftt.elasticsearch.bean.PageSort;
import com.jftt.elasticsearch.bean.QueryFilter;
import com.jftt.elasticsearch.bean.RangerFilter;
import com.jftt.elasticsearch.bean.TermQuery;
import com.jftt.elasticsearch.common.ElasticConstant.MappingIndex;
import com.jftt.elasticsearch.common.ElasticConstant.MappingStore;
import com.jftt.elasticsearch.common.ElasticConstant.MappingType;
import com.jftt.elasticsearch.common.ElasticConstant.QueryType;
import com.jftt.elasticsearch.common.ElasticConstant.SortType;
import com.jftt.wifi.common.Constant;

/**
 * 
 */
public class ElnTest {  
      
    public static void main(String[] args) {
    	
    	//创建数据库
    	//ElastisearchUtil.indexCreate(Constant.ELASTICSEARCH_INDEX);
		
    	//数据库 是否存在
    	//System.out.println(ElastisearchUtil.indexExists(Constant.ELASTICSEARCH_INDEX));
    	
    	//删除数据库
    	//ElastisearchUtil.indexDelete("wjtv");
    	
    	//表 是否存在
    	//System.out.println(ElastisearchUtil.typeExists("wjtv", "news"));
    	
    	//获得表结构
    	//System.out.println(ElastisearchUtil.typeMapping("wjtv", "news")[1]);
    	
    	//创建表
    	/*List<MappingProperty> propertyList = new ArrayList<MappingProperty>();
    	
    	MappingProperty ID = new MappingProperty("ID", MappingType.long_type.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(ID);
    	
    	MappingProperty NEWSID = new MappingProperty("NEWSID", MappingType.long_type.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(NEWSID);
    	
    	MappingProperty REPORTER = new MappingProperty("REPORTER", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.yes.getValue(), null, null);
    	propertyList.add(REPORTER);
    	
    	MappingProperty CAMERAMAN = new MappingProperty("CAMERAMAN", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.yes.getValue(), null, null);
    	propertyList.add(CAMERAMAN);
    	
    	MappingProperty ZIMU = new MappingProperty("ZIMU", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.yes.getValue(), null, null);
    	propertyList.add(ZIMU);
    	
    	MappingProperty ZHENGWEN = new MappingProperty("ZHENGWEN", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.yes.getValue(), null, null);
    	propertyList.add(ZHENGWEN);
    	
    	MappingProperty KOUBO = new MappingProperty("KOUBO", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.yes.getValue(), null, null);
    	propertyList.add(KOUBO);
    	
    	MappingProperty COMMITTIME = new MappingProperty("COMMITTIME", MappingType.date.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), "yyyy-MM-dd HH:mm:ss", null);
    	propertyList.add(COMMITTIME);
    	
    	MappingProperty BROADCASTDATE = new MappingProperty("BROADCASTDATE", MappingType.date.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), "yyyy-MM-dd HH:mm:ss", null);
    	propertyList.add(BROADCASTDATE);
    	
    	MappingProperty TITLE = new MappingProperty("TITLE", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.yes.getValue(), null, null);
    	propertyList.add(TITLE);
    	
    	MappingProperty NEWSVERSION = new MappingProperty("NEWSVERSION", MappingType.long_type.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(NEWSVERSION);
    	
    	MappingProperty USERNAME = new MappingProperty("USERNAME", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.yes.getValue(), null, null);
    	propertyList.add(USERNAME);
    	
    	MappingProperty REFERENCETIMES = new MappingProperty("REFERENCETIMES", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(REFERENCETIMES);
    	
    	MappingProperty FIRSTBROADCASTTIME = new MappingProperty("FIRSTBROADCASTTIME", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(FIRSTBROADCASTTIME);
    	
    	MappingProperty BROADCASTCOLUMN = new MappingProperty("BROADCASTCOLUMN", MappingType.long_type.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(BROADCASTCOLUMN);
    	
    	MappingProperty BROADCASTCOLUMN_NAME = new MappingProperty("BROADCASTCOLUMN_NAME", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.yes.getValue(), null, null);
    	propertyList.add(BROADCASTCOLUMN_NAME);
    	
    	MappingProperty PERSON = new MappingProperty("PERSON", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(PERSON);
    	
    	ElastisearchUtil.mappingCreate("wjtv", "news", propertyList);*/
    	
    	//查询
    	/*String[] filters = {"COMMITTIME:[\"2000-01-01 00:00:00\" TO *]"};
    	
    	List<TermQuery> termQueryList = new ArrayList<TermQuery>();
    	TermQuery query = new TermQuery(QueryType.must.getValue(), "TITLE", "福布斯 排行榜 money", 1.5F);
    	termQueryList.add(query);
    	
    	TermQuery query2 = new TermQuery(QueryType.must_not.getValue(), "TITLE", "周立波", 1.5F);
    	termQueryList.add(query2);
    	
    	PageSort pageSort = new PageSort(0, 10, "ID", SortType.asc.getValue());
    	
    	BackArray backArray = ElastisearchUtil.search("wjtv", "news", filters, termQueryList, pageSort);
    	System.out.println(backArray.getTotal());
    	System.out.println(backArray.getList());*/
    }
}  