/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: DeptExamDetailVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-2-22        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.exam.vo;

/**
 * @author JFTT)zhangchen<BR>
 * class name:DeptExamDetailVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2016-2-22
 */
public class DeptExamDetailVo {
	private Integer userId;
	private String userName;
	private String name;
	private String post;
	private String deptName;
	private Integer examId;
	private String examTitle;
	private Integer isPassed;
	private Integer getScore;
	private Integer totalScore;
	private Integer passScore;
	private String startTime;
	private String endTime;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
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
	public Integer getGetScore() {
		return getScore;
	}
	public void setGetScore(Integer getScore) {
		this.getScore = getScore;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public Integer getPassScore() {
		return passScore;
	}
	public void setPassScore(Integer passScore) {
		this.passScore = passScore;
	}
	public Integer getIsPassed() {
		return isPassed;
	}
	public void setIsPassed(Integer isPassed) {
		this.isPassed = isPassed;
	}
	
}
