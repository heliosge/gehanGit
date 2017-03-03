/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2013
 * FileName: Constant.java
 * Version:  $Revision$
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |	2012-12-12		|	JFTT)WANGJIAN	|	original version
 */
package com.jftt.elasticsearch.common;


/**
 * 
 * @author jftt
 *
 */
public class ElasticConstant {
	
	//数据类型
	public enum MappingType{
		string("string"),      //
		long_type("long"),     //
		integer("integer"),     //
		short_type("short"),     //
		byte_type("byte"),     //
		double_type("double"),     //
		float_type("float"),     //
		date("date"),     //
		boolean_type("boolean");     //
		
		private String value;
		
		private MappingType(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
	
	//是否保存
	public enum MappingStore{
		yes("yes"),      //
		no("no");     //
		
		private String value;
		
		private MappingStore(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
	
	//是否分词  
	//只建议对 string 类型 分词,  如果String类型会用于排序，最好也不要分词
	public enum MappingIndex{
		yes("analyzed"),      //
		no("not_analyzed");     //
		
		private String value;
		
		private MappingIndex(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
	
	//查询条件
	//
	public enum QueryType{
		must("must"),      	   //必须有
		should("should"),      //可以有
		must_not("must_not");  //必须没有
		
		private String value;
		
		private QueryType(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
	
	//排序条件
	//
	public enum SortType{
		asc("asc"),      	   //必须有
		desc("desc");  //必须没有
		
		private String value;
		
		private SortType(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
}
