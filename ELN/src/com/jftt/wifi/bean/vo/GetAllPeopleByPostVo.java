/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: GetAllPeopleByPostVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-9-16        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * @author JFTT)chenrui
 * class name:GetAllPeopleByPostVo <BR>
 * class description: 根据岗位获取批阅人Vo <BR>
 * Remark: <BR>
 * @version 1.00 2015-9-16
 * @author JFTT)chenrui
 */
public class GetAllPeopleByPostVo {
	private String userName;
	private String name;
	private String postId;
	private String deptId;
	private String userId;
	private String page;
	private String pageSize;
	private long fromNum;
	
	private String allCompany;   //all:集团的人
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public long getFromNum() {
		return fromNum;
	}
	public void setFromNum(long fromNum) {
		this.fromNum = fromNum;
	}
	public String getAllCompany() {
		return allCompany;
	}
	public void setAllCompany(String allCompany) {
		this.allCompany = allCompany;
	}
	
}
