/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamUserAnswerVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-7        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean;

import java.io.Serializable;


/**
 * @author JFTT)zhangchen
 * class name:ExamUserAnswerVo <BR>
 * class description: 用于提交试卷时接收参数的Bean 
 * 以下所有字段接收的参数，均以逗号隔开形式，例如questionId => 1,2,3
 * 这样就可以用表单映射至action<BR>
 * Remark: <BR>
 * @version 1.00 2015-8-7
 */
public class ExamUserAnswerVo implements Serializable{
	/**  
	 * define a field serialVersionUID which type is long
	 */
	private static final long serialVersionUID = 1L;
	private String id;//答案ID
	private int userId;//用户ID
	private int examId;//考试ID
	private int examAssignId;//考试分配表ID
	private int examType;//考试种类：1普通考试，2赛程考试，3模拟考试，4学习地图测试，5课程章节测试（实际由课程部分管理）
	private String[] questionId;//问题ID
	private String[] parentId;//问题父ID
	private String[] questionType;//题型：1主观题，2单选题，3多选题，4判断题，5填空题，6组合题
	private String[] userAnswer;//用户答案
	private String[] correctAnswer;//正确答案
	private String[] orderNum;//题目序号
	private String[] subOrderNum;//子题目序号
	private String[] score;//题目分值
	private String[] subQuestionId;//子问题ID
	private String getScore;//获得分数
	private String evaluation;//评语
	private String isFavorated;//是否收藏在错题集中
	private int examPassScore;//及格分
	private int isAutoMarking;//是否自动阅卷
	private int remainingTime;//剩余考试时间（秒）
	private int organizingMode;//1：标准组卷2：随机组卷
	private int totalScore;//总分
	private int examRecordId;//课程考试记录ID
	
	private String matchId;//赛程id
	
	private int categoryId;//试题库ID，这里指三级试题库
	private int parentCategoryId;//父试题库ID，这里指二级试题库
	
	
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	public String[] getSubQuestionId() {
		return subQuestionId;
	}
	public void setSubQuestionId(String[] subQuestionId) {
		this.subQuestionId = subQuestionId;
	}
	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	public int getExamAssignId() {
		return examAssignId;
	}
	public void setExamAssignId(int examAssignId) {
		this.examAssignId = examAssignId;
	}
	
	public String[] getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String[] questionId) {
		this.questionId = questionId;
	}
	public String[] getParentId() {
		return parentId;
	}
	public void setParentId(String[] parentId) {
		this.parentId = parentId;
	}
	public String[] getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String[] questionType) {
		this.questionType = questionType;
	}
	public String[] getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(String[] userAnswer) {
		this.userAnswer = userAnswer;
	}
	public String[] getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String[] correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String[] getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String[] orderNum) {
		this.orderNum = orderNum;
	}
	public String[] getSubOrderNum() {
		return subOrderNum;
	}
	public void setSubOrderNum(String[] subOrderNum) {
		this.subOrderNum = subOrderNum;
	}
	public String[] getScore() {
		return score;
	}
	public void setScore(String[] score) {
		this.score = score;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getGetScore() {
		return getScore;
	}
	public void setGetScore(String getScore) {
		this.getScore = getScore;
	}
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	public String getIsFavorated() {
		return isFavorated;
	}
	public void setIsFavorated(String isFavorated) {
		this.isFavorated = isFavorated;
	}
	
	public int getExamType() {
		return examType;
	}
	public void setExamType(int examType) {
		this.examType = examType;
	}
	public int getExamPassScore() {
		return examPassScore;
	}
	public void setExamPassScore(int examPassScore) {
		this.examPassScore = examPassScore;
	}
	public int getIsAutoMarking() {
		return isAutoMarking;
	}
	public void setIsAutoMarking(int isAutoMarking) {
		this.isAutoMarking = isAutoMarking;
	}
	public int getRemainingTime() {
		return remainingTime;
	}
	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}
	public int getOrganizingMode() {
		return organizingMode;
	}
	public void setOrganizingMode(int organizingMode) {
		this.organizingMode = organizingMode;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public int getExamRecordId() {
		return examRecordId;
	}
	public void setExamRecordId(int examRecordId) {
		this.examRecordId = examRecordId;
	}
	
}
