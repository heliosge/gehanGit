/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: AjaxReturnVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-20        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

import java.io.Serializable;


/**
 * wj
 */
public class CompanyVo implements Serializable{
	
	/**  
	 * define a field serialVersionUID which type is long
	 */
	private static final long serialVersionUID = 2464436063518464984L;
	
	private Long id;
	private String name;
	
	private Integer flag;    //1: 集团公司,  2: 子公司
	
	
	
	public CompanyVo() {
		super();
	}

	public CompanyVo(Long id, String name, Integer flag) {
		super();
		this.id = id;
		this.name = name;
		this.flag = flag;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}
