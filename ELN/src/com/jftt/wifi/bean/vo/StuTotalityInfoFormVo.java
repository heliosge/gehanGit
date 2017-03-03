/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: StuTotalityInfoFormVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-1-11        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.Date;

import com.sun.star.bridge.oleautomation.Decimal;

/**
 * class name:StuTotalityInfoFormVo <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2016-1-11
 * @author JFTT)chenrui
 */
public class StuTotalityInfoFormVo{
	private String userId;
	private String name;
	private String deptName;
	private String postName;
	private int getIntegral; // 获得积分
	private int deductIntegral; // 扣除积分
	private int getCredit; // 获得学分
	private int deductCredit; // 扣除学分
	private int totalStuTime; // 总学时
	private int finishCourseBySelf; // 自学完成课程数
	private int finishCourseByAppoint; // 指派课程完成数量
	private int attendExamCounts; // 参加考试数
	private Decimal passExamRate; // 考试通过率
	private int shareKnowledgeCount; // 分享知识数
	private int loginCounts; // 登录系统次数
	private int askCounts; // 提问总数
	private int answerCounts; // 回答总数
	private int evaluationCounts; // 评论总数
	private Date firstLoginTime; // 首次登录时间
	private Date lastLoginTime; // 最近登录时间
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
	public int getGetIntegral() {
		return getIntegral;
	}
	public void setGetIntegral(int getIntegral) {
		this.getIntegral = getIntegral;
	}
	public int getDeductIntegral() {
		return deductIntegral;
	}
	public void setDeductIntegral(int deductIntegral) {
		this.deductIntegral = deductIntegral;
	}
	public int getGetCredit() {
		return getCredit;
	}
	public void setGetCredit(int getCredit) {
		this.getCredit = getCredit;
	}
	public int getDeductCredit() {
		return deductCredit;
	}
	public void setDeductCredit(int deductCredit) {
		this.deductCredit = deductCredit;
	}
	public int getTotalStuTime() {
		return totalStuTime;
	}
	public void setTotalStuTime(int totalStuTime) {
		this.totalStuTime = totalStuTime;
	}
	public int getFinishCourseBySelf() {
		return finishCourseBySelf;
	}
	public void setFinishCourseBySelf(int finishCourseBySelf) {
		this.finishCourseBySelf = finishCourseBySelf;
	}
	
	public int getFinishCourseByAppoint() {
		return finishCourseByAppoint;
	}
	public void setFinishCourseByAppoint(int finishCourseByAppoint) {
		this.finishCourseByAppoint = finishCourseByAppoint;
	}
	public int getAttendExamCounts() {
		return attendExamCounts;
	}
	public void setAttendExamCounts(int attendExamCounts) {
		this.attendExamCounts = attendExamCounts;
	}
	public Decimal getPassExamRate() {
		return passExamRate;
	}
	public void setPassExamRate(Decimal passExamRate) {
		this.passExamRate = passExamRate;
	}
	public int getShareKnowledgeCount() {
		return shareKnowledgeCount;
	}
	public void setShareKnowledgeCount(int shareKnowledgeCount) {
		this.shareKnowledgeCount = shareKnowledgeCount;
	}
	public int getLoginCounts() {
		return loginCounts;
	}
	public void setLoginCounts(int loginCounts) {
		this.loginCounts = loginCounts;
	}
	public int getAskCounts() {
		return askCounts;
	}
	public void setAskCounts(int askCounts) {
		this.askCounts = askCounts;
	}
	public int getAnswerCounts() {
		return answerCounts;
	}
	public void setAnswerCounts(int answerCounts) {
		this.answerCounts = answerCounts;
	}
	public int getEvaluationCounts() {
		return evaluationCounts;
	}
	public void setEvaluationCounts(int evaluationCounts) {
		this.evaluationCounts = evaluationCounts;
	}
	public Date getFirstLoginTime() {
		return firstLoginTime;
	}
	public void setFirstLoginTime(Date firstLoginTime) {
		this.firstLoginTime = firstLoginTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
