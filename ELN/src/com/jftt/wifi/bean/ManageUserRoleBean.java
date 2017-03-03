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
 */
public class ManageUserRoleBean implements Serializable{
	
	/**  
	 * define a field serialVersionUID which type is long
	 */
	private static final long serialVersionUID = -4432020923995355568L;
	
	private long id; 		         //主键ID
	private int userId;
	private int roleId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
}
