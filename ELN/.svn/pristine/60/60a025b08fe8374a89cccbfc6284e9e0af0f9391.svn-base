/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: HtmlUtil.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-8        | JFTT)zhangchen    | original version
 */
package com.jftt.elasticsearch.util;

import java.lang.reflect.Field;
import java.util.List;

import com.jftt.elasticsearch.bean.MappingProperty;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 */
public class JSONUtil {  
	
    /** 
     * 根据 MappingProperty 
     * 返回 Elastisearch 的数据结构
     */  
    public static JSONObject MappingProperty(List<MappingProperty> propertyList) {
    	
    	JSONObject obj = new JSONObject();
    	
    	try {
			
    		for (MappingProperty property : propertyList) {
        		
        		String name = property.getName();
        		property.setName(null);
        		
        		//去除null 或 "" 属性
        		JSONObject propertyObj = new JSONObject();
        		
        		Class cls = property.getClass();
        		Field[] fields = cls.getDeclaredFields();
        		for(int i=0; i<fields.length; i++){
        			
        			fields[i].setAccessible(true);
        			
        			Object value = fields[i].get(property);
        			if(value !=null && !value.equals("")){
        				propertyObj.put(fields[i].getName(), value);
        			}
        		}
        		
        		obj.put(name, propertyObj);
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	JSONObject backObj = new JSONObject();
    	backObj.put("properties", obj);
    	
    	return backObj;
    } 

}  