/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: SafetyEducationSearchVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月16日        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * class name:SafetyEducationSearchVo <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月16日
 * @author JFTT)chenrui
 */
public class SafetyEducationSearchVo extends CommonPagingVo{
	private String title;
	private int companyId;
	private int subCompanyId;
	private String startTime;
	private String endTime;
	private String type;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(int subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
