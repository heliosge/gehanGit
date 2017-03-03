/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:Ad.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2014/01/07           |  JFTT)wangjian     |  original version
 */
package com.jftt.wifi.bean;

import java.io.Serializable;

/**
 * ManageRoleBean 角色表
 */
public class ManageRoleBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7745943564762744067L;
	
	private long id; 		         //主键ID
	private String name;             //姓名
	private Integer companyId;		//公司id
	private Integer subCompanyId;	//子公司id
	private String descr;
	private Integer status;//1:正常；2：冻结。
	
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
