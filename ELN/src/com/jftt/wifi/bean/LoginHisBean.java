/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: AapproveBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月29日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 登陆记录
 */
public class LoginHisBean implements Serializable {

	
	/**  
	 * define a field serialVersionUID which type is long
	 */
	private static final long serialVersionUID = 967593083816899890L;
	
	private Long id;
	private Long user_id;
	private Long companyId;
	private String login_time;
	private String session_id;
	
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}
}
