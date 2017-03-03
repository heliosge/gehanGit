/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamUserAnswerBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/29        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam;

import java.util.Date;
import java.util.List;

/**
 * class name:ExamUserAnswerBean <BR>
 * class description: 用户答案Bean <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/29
 * @author JFTT)wangyifeng
 */
public class ExamUserAnswerBean {
	/**
	 * 用户答题ID，自增ID
	 */
	private Integer id;
	/**
	 * 考试分配表ID
	 */
	private Integer examAssignId;
	/**
	 * 试题ID
	 */
	private Integer questionId;
	/**  
	 * 实际试题
	 */
	private QuestionBean question;
	/**
	 * 用户答案
	 */
	private String answer;
	/**
	 * 试题序号
	 */
	private Integer orderNum;
	/**
	 * 子试题序号（仅组合题非空）：
	 * 组合题本身的sub_order_num为0，
	 * 它下面子试题的sub_order_num按照实际顺序来
	 */
	private Integer subOrderNum;
	/**
	 * 试题分值
	 */
	private Integer score;
	/**
	 * 获得分数
	 */
	private Integer getScore;
	/**
	 * 评语
	 */
	private String evaluation;
	/**
	 * 是否收藏在错题集中
	 */
	private Integer isFavorated;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 子试题答案
	 */
	private List<ExamUserAnswerBean> subAnswers;
	/**
	 * 正确答案
	 */
	private String correctAnswer;
	/**  
	 * 答案ID
	 */
	private String correctAnswer2;
	/**  
	 * 子试题ID
	 */
	private String questionIdList;
	
	
	public String getQuestionIdList() {
		return questionIdList;
	}

	public void setQuestionIdList(String questionIdList) {
		this.questionIdList = questionIdList;
	}

	public String getCorrectAnswer2() {
		return correctAnswer2;
	}

	public void setCorrectAnswer2(String correctAnswer2) {
		this.correctAnswer2 = correctAnswer2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExamAssignId() {
		return examAssignId;
	}

	public void setExamAssignId(Integer examAssignId) {
		this.examAssignId = examAssignId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getGetScore() {
		return getScore;
	}

	public void setGetScore(Integer getScore) {
		this.getScore = getScore;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public Integer getIsFavorated() {
		return isFavorated;
	}

	public void setIsFavorated(Integer isFavorated) {
		this.isFavorated = isFavorated;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getSubOrderNum() {
		return subOrderNum;
	}

	public void setSubOrderNum(Integer subOrderNum) {
		this.subOrderNum = subOrderNum;
	}

	public QuestionBean getQuestion() {
		return question;
	}

	public void setQuestion(QuestionBean question) {
		this.question = question;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public List<ExamUserAnswerBean> getSubAnswers() {
		return subAnswers;
	}

	public void setSubAnswers(List<ExamUserAnswerBean> subAnswers) {
		this.subAnswers = subAnswers;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
}
