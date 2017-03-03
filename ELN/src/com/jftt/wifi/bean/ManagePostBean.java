/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManagePostBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月5日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.bean;

/**
 * class name:ManagePostBean <BR>
 * class description: 岗位表 <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月5日
 * @author JFTT)Lu Yunlong
 */
public class ManagePostBean {
	private Integer id;
	private String code;//编码
	private String name;//名称
	private Integer companyId;//公司id
	private Integer subCompanyId;//子公司id
	private Integer parentId;//父id
	private String descr;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	
	
}
