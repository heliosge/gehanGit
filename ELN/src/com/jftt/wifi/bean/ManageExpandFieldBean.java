/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageExpandField.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月31日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.bean;

/**
 * class name:ManageExpandField <BR>
 * class description: 人员扩展字段维护 <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月31日
 * @author JFTT)Lu Yunlong
 */
public class ManageExpandFieldBean {
	private Integer id;
	private Integer originalFiled;//原字段
	private String companyFieldName;//公司新字段
	private String type;//中英文
	private Integer type_;//类型1：文本；2：日期；3：下拉菜单；
	private String value;
    
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOriginalFiled() {
		return originalFiled;
	}
	public void setOriginalFiled(Integer originalFiled) {
		this.originalFiled = originalFiled;
	}
	public String getCompanyFieldName() {
		return companyFieldName;
	}
	public void setCompanyFieldName(String companyFieldName) {
		this.companyFieldName = companyFieldName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getType_() {
		return type_;
	}
	public void setType_(Integer type_) {
		this.type_ = type_;
	}
	
}
