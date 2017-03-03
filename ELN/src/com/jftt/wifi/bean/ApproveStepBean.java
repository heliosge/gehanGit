/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ApproveStepBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月30日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.bean;

/**
 * class name:审批步骤bean
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月30日
 * @author JFTT)liucaibao
 */
public class ApproveStepBean {
	private int stepId;
	private int wayId;
	private int orderNum; 
	private int approverType;
	private int approverId;
	private String showName ="";
	
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public int getStepId() {
		return stepId;
	}
	public void setStepId(int stepId) {
		this.stepId = stepId;
	}
	public int getWayId() {
		return wayId;
	}
	public void setWayId(int wayId) {
		this.wayId = wayId;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getApproverType() {
		return approverType;
	}
	public void setApproverType(int approverType) {
		this.approverType = approverType;
	}
	public int getApproverId() {
		return approverId;
	}
	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}
 
}
