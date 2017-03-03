/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamAssignDetailVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-3        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.List;

import com.jftt.wifi.bean.exam.ExamUserAnswerBean;

/**
 * @author JFTT)zhangchen
 * class name:ExamAssignDetailVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-3
 */
public class ExamAssignDetailVo {
	private int id;//考试信息ID
	private int assignId;//考试记录ID
	private int userId;//用户ID
	private int examType;//考试类型
	private int paperId;//试卷ID
	private int passScore;//及格分
	private int score;//用户得分
	private String title;//考试信息名称
	private String beginTime;//考试开始时间
	private String endTime;//考试结束时间
	private int duration;//考试时长
	private int displayMode;//试卷显示方式1  整卷显示2  单题可逆3  单题不可逆
	private int organizingMode;//组卷方式（类型）1：标准组卷，关联试题内容固定2：随机组卷，当选择随机组卷时，可以（/只能？）设置组卷规则
	private String paperName;//试卷名称
	private int totalScore;//试卷总分
	private ExamScheduleVo examScheduleVo;//考试所有信息
	private List<ExamUserAnswerBean> userAnswerList;//用户答案
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAssignId() {
		return assignId;
	}
	public void setAssignId(int assignId) {
		this.assignId = assignId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getExamType() {
		return examType;
	}
	public void setExamType(int examType) {
		this.examType = examType;
	}
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public int getPassScore() {
		return passScore;
	}
	public void setPassScore(int passScore) {
		this.passScore = passScore;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getDisplayMode() {
		return displayMode;
	}
	public void setDisplayMode(int displayMode) {
		this.displayMode = displayMode;
	}
	public int getOrganizingMode() {
		return organizingMode;
	}
	public void setOrganizingMode(int organizingMode) {
		this.organizingMode = organizingMode;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public List<ExamUserAnswerBean> getUserAnswerList() {
		return userAnswerList;
	}
	public void setUserAnswerList(List<ExamUserAnswerBean> userAnswerList) {
		this.userAnswerList = userAnswerList;
	}
	public ExamScheduleVo getExamScheduleVo() {
		return examScheduleVo;
	}
	public void setExamScheduleVo(ExamScheduleVo examScheduleVo) {
		this.examScheduleVo = examScheduleVo;
	}
	
}
