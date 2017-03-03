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
 * class name:审核流程bean。
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月29日
 * @author JFTT)liucaibao
 */
public class ApproveBean implements Serializable {

	private static final long serialVersionUID = -4714709574354070550L;
	private int wayId;
	private int wayType;
	private int isApprove;
	private int companyId;
	private int userId;
	private int fromNum;
	private int page;
	private List<ApproveStepBean> listStep = new ArrayList<ApproveStepBean>();
	private List<Integer> deptList = new ArrayList<Integer>();
	 
	public List<Integer> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Integer> deptList) {
		this.deptList = deptList;
	}
	public List<ApproveStepBean> getListStep() {
		return listStep;
	}
	public void setListStep(List<ApproveStepBean> listStep) {
		this.listStep = listStep;
	}
	public int getWayId() {
		return wayId;
	}
	public void setWayId(int wayId) {
		this.wayId = wayId;
	}
	public int getWayType() {
		return wayType;
	}
	public void setWayType(int wayType) {
		this.wayType = wayType;
	}
	public int getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(int isApprove) {
		this.isApprove = isApprove;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFromNum() {
		return fromNum;
	}
	public void setFromNum(int fromNum) {
		this.fromNum = fromNum;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new ReflectionToStringBuilder(this).toString();
	}
}
