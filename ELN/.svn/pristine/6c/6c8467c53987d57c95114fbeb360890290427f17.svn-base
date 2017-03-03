/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: HtmlUtil.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-8        | JFTT)zhangchen    | original version
 */
package com.jftt.elasticsearch.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.eclipse.jdt.internal.compiler.ast.JavadocSingleNameReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.jftt.elasticsearch.bean.Filter;


/**
 * 
 */
public class HttpUtil {  
	
	private static SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
    
	private static RestTemplate restTemplate = getRestTemplate();
	
    /** 
     * 0: respons 状态  200 404 ...
     * 1: 返回的字符流
     */  
    public static String[] httpClient(String url, HttpMethod httpMethod) {
    	
    	String[] back = {null, null};
    	
		try {
			URI uri = new URI(url);
			
			ClientHttpRequest chr = schr.createRequest(uri, httpMethod);
			ClientHttpResponse res = chr.execute();
			
			//System.out.println(res.getStatusText());
			//System.out.println(res.getStatusCode().toString());
			back[0] = res.getStatusCode().toString();
			
			InputStream is = res.getBody(); //获得返回数据,注意这里是个流
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String str = "";
			while((str = br.readLine())!=null){
				//System.out.println(str);
				back[1] = str;
			}
			
			is.close();
			isr.close();
			br.close();
			res.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return back;
    }  
    
    private static RestTemplate getRestTemplate(){
    	
    	SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(5000);
        requestFactory.setConnectTimeout(5000);
    	
    	// 添加转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        /*messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());*/
 
        RestTemplate restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(requestFactory);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        
        return restTemplate;
    }
    
    /** 
     * 
     */  
    public static void httpClientPut(String url, JSONObject obj) {
    	
		try {
			
			HttpEntity request = new HttpEntity(obj.toString());
			
			restTemplate.put(url, request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
    
    /** 
     * 删除
     */  
    public static void httpClientDelete(String url) {
    	
		try {
			
			restTemplate.delete(url);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
    
    /** 
     * 查询
     */  
    public static JSONObject httpClientPost(String url, JSONObject obj) {
    	
    	System.out.println(obj);
    	
    	HttpEntity request = new HttpEntity(obj.toString());
		
		ResponseEntity aa = restTemplate.postForEntity(url, request, String.class);
		
		obj = JSONObject.fromObject(aa.getBody().toString());
		
		//System.out.println(obj.getJSONObject("hits").get("total"));
		//System.out.println("END");
		
		return obj;
    } 
    
}  