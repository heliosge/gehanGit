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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.http.HttpMethod;

import com.jftt.elasticsearch.bean.BackArray;
import com.jftt.elasticsearch.bean.Filter;
import com.jftt.elasticsearch.bean.MappingProperty;
import com.jftt.elasticsearch.bean.PageSort;
import com.jftt.elasticsearch.bean.QueryFilter;
import com.jftt.elasticsearch.bean.RangerFilter;
import com.jftt.elasticsearch.bean.TermQuery;
import com.jftt.elasticsearch.common.ElasticConstant.QueryType;
import com.jftt.wifi.util.PropertyUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;


/**
 * 
 */
public class ElastisearchUtil {  
	
	private static String baseUrl = PropertyUtil.getConfigureProperties("ELASTICSEARCH_HOST");
      
    /** 
     * 数据库 是否存在
     * 存在 true
     */  
    public static boolean indexExists(String index) {
    	
    	String[] back = HttpUtil.httpClient(baseUrl+"/"+index, HttpMethod.HEAD);
    	
    	if(back[0].equals("404")){
    		return false;
    	}else{
    		return true;
    	}
    } 
    
    /** 
     * 删除 数据库
     */  
    public static void indexDelete(String index) {
    	
    	String[] back = HttpUtil.httpClient(baseUrl+"/"+index, HttpMethod.DELETE);
    	
    	/*System.out.println(back[0]);
    	System.out.println(back[1]);*/
    } 
    
    /** 
     * 创建 数据库
     */  
    public static void indexCreate(String index) {
    	
    	String[] back = HttpUtil.httpClient(baseUrl+"/"+index, HttpMethod.PUT);
    	
    	/*System.out.println(back[0]);
    	System.out.println(back[1]);*/
    } 
    
    /** 
     * 表 是否存在
     * 存在 true
     */  
    public static boolean typeExists(String index, String type) {
    	
    	String[] back = HttpUtil.httpClient(baseUrl+"/"+index+"/"+type, HttpMethod.HEAD);
    	
    	if(back[0].equals("404")){
    		return false;
    	}else{
    		return true;
    	}
    } 
    
    /** 
     * 获得表结构
     */  
    public static String[] typeMapping(String index, String type) {
    	
    	String[] back = HttpUtil.httpClient(baseUrl+"/"+index+"/_mapping/"+type, HttpMethod.GET);
    	
    	return back;
    } 
    
    /** 
     * 创建表
     */  
    public static void mappingCreate(String index, String type, List<MappingProperty> propertyList) {
    	
    	JSONObject obj = JSONUtil.MappingProperty(propertyList);
    	//System.out.println(obj);
    	
    	HttpUtil.httpClientPut(baseUrl+"/"+index+"/_mapping/"+type, obj);
    } 
    
    /** 
     * 增加数据
     * id: 主键
     * obj: 对应mapping 的json 数据
     */  
    public static void dataAdd(String index, String type, String id, JSONObject obj) {
    	//System.out.println(obj);
    	
    	HttpUtil.httpClientPut(baseUrl+"/"+index+"/"+type+"/"+id, obj);
    }
    
    /** 
     * 修改数据
     * id: 主键
     * obj: 对应mapping 的json 数据
     */  
    public static void dataUpdate(String index, String type, String id, JSONObject obj) {
    	
    	HttpUtil.httpClientPut(baseUrl+"/"+index+"/"+type+"/"+id, obj);
    }
    
    /** 
     * 删除数据
     * id: 主键
     */  
    public static void dataDelete(String index, String type, String id) {
    	
    	HttpUtil.httpClient(baseUrl+"/"+index+"/"+type+"/"+id, HttpMethod.DELETE);
    }
    
    /** 
     * 查询
     * filters 分2种  
     * 查询过滤 TITLE:"豪情"
     * 范围过滤 COMMITTIME:[* TO "2015-12-31 00:00:00"] *表示无穷大，无穷小  {不包括边界，[包括边界
     * 
     * fields：返回的列， 默认所有列
     */  
    public static BackArray search(String index, String type, String[] filters, List<TermQuery> termQueryList, PageSort pageSort, String... fields) {
    	
    	JSONObject obj = new JSONObject();
    	
    	String from = "0";
    	String size = "10";
    	
    	//返回指定列
    	if(fields !=null && fields.length > 0){
    		obj.put("_source", fields);
    	}
    	
    	//分页排序
    	if(pageSort !=null){
    		
    		if(pageSort.getFrom() != null){
    			from = pageSort.getFrom() + "";
    		}
    		
    		if(pageSort.getSize() != null){
    			size = pageSort.getSize() + "";
    		}	
    		
    		if(pageSort.getField() !=null && !pageSort.getField().equals("")){
    			JSONArray sortArray = new JSONArray();
    			JSONObject sortObj = new JSONObject();
    			
    			sortObj.put(pageSort.getField(), pageSort.getSort());
    			sortArray.add(sortObj);
    			
    			obj.put("sort", sortArray);
    		}
    	}
    	obj.put("from", from);
    	obj.put("size", size);
    	
    	/*String queryString = "";
    	
    	if(filters !=null && filters.length > 0){
    		//过滤器
    		for (String filter : filters) {
				
    			queryString += " AND " + filter;
			}
    		
    		queryString = queryString.substring(5);
    	}
    	
    	//查询条件
    	String termQueryString = AnalyzerUtil.searchString(termQueryList);
    	if(queryString.equals("")){

    		queryString = termQueryString.substring(termQueryString.indexOf("("));
    	}else{
    		queryString += termQueryString;
    	}
    	
    	JSONObject query_string = new JSONObject();
		JSONObject query = new JSONObject();
		query.put("query", queryString);
		query_string.put("query_string", query);
		obj.put("query", query_string);*/
    	
    	//查询条件
    	JSONObject queryObj = AnalyzerUtil.searchObj(filters, termQueryList);
    	
    	obj.put("query", queryObj);
    	
    	JSONObject backObj = HttpUtil.httpClientPost(baseUrl+"/"+index+"/"+type+"/_search", obj);
    	
    	int total = (Integer) backObj.getJSONObject("hits").get("total");
    	JSONArray array = backObj.getJSONObject("hits").getJSONArray("hits");

    	for(int i=0; i<array.size(); i++){
    		
    		array.add(i, array.getJSONObject(i).getJSONObject("_source"));
    		array.remove(i+1);
    	}
    	
    	return new BackArray(total, array);
    }
    
    /** 
     * keyword:查询关键字
     * field:查询的列
     * pageSize:最大数量
     * idName:主键列名
     * 
     * 返回主键的集合
     */  
    public static List<Object> searchId(String index, String type, String keyword, String field, String idName) {
    	
JSONObject obj = new JSONObject();
    	
    	obj.put("_source", idName);
    	
    	List<TermQuery> termQueryList = new ArrayList<TermQuery>();
    	TermQuery query = new TermQuery(QueryType.must.getValue(), field, keyword, 1F);
    	termQueryList.add(query);
    	
    	//查询条件
    	JSONObject queryObj = AnalyzerUtil.searchObj(null, termQueryList);
    	
    	obj.put("query", queryObj);
    	
    	JSONObject backObj = HttpUtil.httpClientPost(baseUrl+"/"+index+"/"+type+"/_search", obj);
    	
    	int total = (Integer) backObj.getJSONObject("hits").get("total");
    	System.out.println(total);
    	
    	List<Object> backList = new ArrayList<Object>();
    	
    	int totalPage = 1;
    	int maxPageSize = 10000;
    	if(total%maxPageSize == 0){
    		totalPage = total/maxPageSize;
    	}else{
    		totalPage = total/maxPageSize + 1;
    	}
    	
    	for(int k=1; k<=totalPage; k++){
    		
    		obj.put("from", maxPageSize*(k-1));
    		obj.put("size", maxPageSize);
    		
    		backObj = HttpUtil.httpClientPost(baseUrl+"/"+index+"/"+type+"/_search", obj);

        	JSONArray array = backObj.getJSONObject("hits").getJSONArray("hits");
        	for(int i=0; i<array.size(); i++){
        		
        		backList.add(array.getJSONObject(i).getJSONObject("_source").get(idName));
        	}
    	}

    	System.out.println(backList);
    	System.out.println(backList.size());
    	
    	return backList;
    }
    
    
}  