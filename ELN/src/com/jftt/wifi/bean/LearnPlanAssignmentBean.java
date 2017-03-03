/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: LearnPlanAssignmentBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-31        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean;

import java.util.Date;

/**
 * class name:LearnPlanAssignmentBean <BR>
 * class description: 用户学习计划关联bean <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-31
 * @author JFTT)ShenHaijun
 */
public class LearnPlanAssignmentBean {
	private Integer id;//用户学习计划关联id
	private Integer userId;//用户id
	private Integer planId;//计划id
	private Date assignTime;//指派时间
	private Integer status;//学习状态（1；进行中 2：已完成）
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public Date getAssignTime() {
		return assignTime;
	}
	public void setAssignTime(Date assignTime) {
		this.assignTime = assignTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
