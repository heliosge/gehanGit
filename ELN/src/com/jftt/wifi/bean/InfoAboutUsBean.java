/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: InformationBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月17日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.bean;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * class name:资讯信息bean
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月17日
 * @author JFTT)liucaibao
 */
public class InfoAboutUsBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7124312780468392305L;
	
	private Integer id;
	private Integer companyId;
	private Integer type;
	private String infoDesc;
	private Integer userId;

	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getCompanyId() {
		return companyId;
	}



	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}



	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}



	public String getInfoDesc() {
		return infoDesc;
	}



	public void setInfoDesc(String infoDesc) {
		this.infoDesc = infoDesc;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new ReflectionToStringBuilder(this).toString();
	}
}
