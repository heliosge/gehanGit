/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ReportFormsParam.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-1-11        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.ArrayList;
import java.util.List;

import com.jftt.wifi.bean.ManageUserBean;

/**
 * class name:ReportFormsParam <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2016-1-11
 * @author JFTT)chenrui
 */
public class ReportFormsParam extends CommonPagingVo{
	private String name;
	private String userName;
	private String deptName;
	private String postName;
	private Integer companyId;
	private Integer subCompanyId;
	private String isGetAllWithSub; // 如果不为null 则代表获取当前帐号所在公司人员以及子公司人员
	//zhangchen add
	private Integer courseId;//课程ID
	private Integer userId;//用户ID
	private Integer courseType;//课程类型
	private String courseName;//课程名称
	private String courseCode;//课程编号
	
	private Integer learn_process;  //学习进度  1：进行中 2：已完成
	private String deptId;    //公司部门
	private String postId;    //岗位
	private String withManager;   //默认包括     no: 不包括初始管理员  
	
	private List<ManageUserBean> userList = new ArrayList<ManageUserBean>();
	
	
	public String getIsGetAllWithSub() {
		return isGetAllWithSub;
	}
	public void setIsGetAllWithSub(String isGetAllWithSub) {
		this.isGetAllWithSub = isGetAllWithSub;
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
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
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
	public Integer getCourseType() {
		return courseType;
	}
	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}
	public List<ManageUserBean> getUserList() {
		return userList;
	}
	public void setUserList(List<ManageUserBean> userList) {
		this.userList = userList;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public Integer getLearn_process() {
		return learn_process;
	}
	public void setLearn_process(Integer learn_process) {
		this.learn_process = learn_process;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getWithManager() {
		return withManager;
	}
	public void setWithManager(String withManager) {
		this.withManager = withManager;
	}
	
}
