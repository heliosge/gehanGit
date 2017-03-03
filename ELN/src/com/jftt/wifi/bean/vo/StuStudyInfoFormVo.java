/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: StuStudyInfoFormVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-1-15        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean.vo;


/**
 * class name:StuStudyInfoFormVo <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2016-1-15
 * @author JFTT)chenrui
 */
public class StuStudyInfoFormVo {
	
	private String userId;
	private String name;
	private String userName;// 用户名
	private String sex; // 性别
	private String deptName;
	private String postName;
	
	private String finishCourseBySelf; // 完成自学课程数
	private String finishCourseTimeBySelf; // 完成自学课程学时
	private String getCreditBySelf; // 获得自学课程学分
	
	private String finishCourseByAppoint; // 完成指派课程数
	private String finishCourseTimeByAppoint; // 完成指派课程学时
	private String getCreditByAppoint; // 获得指派课程学分
	
	private String totalCourse; // 总课程数
	private String totalTime; // 总学时
	private String totalCredit; // 总学分
	
	private String statisticsDate; // 统计日期
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getFinishCourseBySelf() {
		return finishCourseBySelf;
	}
	public void setFinishCourseBySelf(String finishCourseBySelf) {
		this.finishCourseBySelf = finishCourseBySelf;
	}
	public String getFinishCourseTimeBySelf() {
		return finishCourseTimeBySelf;
	}
	public void setFinishCourseTimeBySelf(String finishCourseTimeBySelf) {
		this.finishCourseTimeBySelf = finishCourseTimeBySelf;
	}
	public String getGetCreditBySelf() {
		return getCreditBySelf;
	}
	public void setGetCreditBySelf(String getCreditBySelf) {
		this.getCreditBySelf = getCreditBySelf;
	}
	public String getFinishCourseByAppoint() {
		return finishCourseByAppoint;
	}
	public void setFinishCourseByAppoint(String finishCourseByAppoint) {
		this.finishCourseByAppoint = finishCourseByAppoint;
	}
	public String getFinishCourseTimeByAppoint() {
		return finishCourseTimeByAppoint;
	}
	public void setFinishCourseTimeByAppoint(String finishCourseTimeByAppoint) {
		this.finishCourseTimeByAppoint = finishCourseTimeByAppoint;
	}
	public String getGetCreditByAppoint() {
		return getCreditByAppoint;
	}
	public void setGetCreditByAppoint(String getCreditByAppoint) {
		this.getCreditByAppoint = getCreditByAppoint;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public String getTotalCredit() {
		return totalCredit;
	}
	public void setTotalCredit(String totalCredit) {
		this.totalCredit = totalCredit;
	}
	public String getStatisticsDate() {
		return statisticsDate;
	}
	public void setStatisticsDate(String statisticsDate) {
		this.statisticsDate = statisticsDate;
	}
	public String getTotalCourse() {
		return totalCourse;
	}
	public void setTotalCourse(String totalCourse) {
		this.totalCourse = totalCourse;
	}
	
	
}
