/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: cjylSearchVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-25        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean.exam.vo;

import java.util.ArrayList;
import java.util.List;

import com.jftt.wifi.bean.ManageUserBean;

/**
 * @author JFTT)chenrui
 * class name:cjylSearchVo <BR>
 * class description:  <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-25
 * @author JFTT)chenrui
 */
public class cjylSearchVo {
	private String examId;
	private String userId;
	
	private int companyId;
	private int subCompanyId;
	private String userName;
	private String postName;
	private String depName;
	private String isAttend;
	private String page;
	private String pageSize;
	private long fromNum;
	private List<ManageUserBean> userList;
	
	//zhangchen add
	private int deptId;
	private String name;
	private String isPassed;    //查询用，yes:通过的  no:没通过的
	private String examTitle;
	private String startTime;
	private String endTime;
	List<DeptExamVo> deptExamVoList = new ArrayList<DeptExamVo>();
	
	private String deptIds;   //公司 部门ID 多个用,劈开
	private String postIds;   //岗位ID  多个用,劈开
	private String withManager;   //默认包括     no: 不包括初始管理员  
	
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
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
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
	public String getIsAttend() {
		return isAttend;
	}
	public void setIsAttend(String isAttend) {
		this.isAttend = isAttend;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public List<ManageUserBean> getUserList() {
		return userList;
	}
	public void setUserList(List<ManageUserBean> userList) {
		this.userList = userList;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsPassed() {
		return isPassed;
	}
	public void setIsPassed(String isPassed) {
		this.isPassed = isPassed;
	}
	public String getExamTitle() {
		return examTitle;
	}
	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
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
	public List<DeptExamVo> getDeptExamVoList() {
		return deptExamVoList;
	}
	public void setDeptExamVoList(List<DeptExamVo> deptExamVoList) {
		this.deptExamVoList = deptExamVoList;
	}
	public String getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}
	public String getPostIds() {
		return postIds;
	}
	public void setPostIds(String postIds) {
		this.postIds = postIds;
	}
	public String getWithManager() {
		return withManager;
	}
	public void setWithManager(String withManager) {
		this.withManager = withManager;
	}
	
	
}
