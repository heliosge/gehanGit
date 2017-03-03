/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireQuestionBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-18        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.questionnaire;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireQuestionBean <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-20
 */
public class QuestionnaireQuestionBean {
	/**
	 * 问题主键ID
	 */
	private Integer id;
	/**  
	 * 问卷ID
	 */
	private Integer questionnaireId;
	/**
	 * 问题题干内容
	 */
	private String content;
	/**
	 * 题目类型，1-单选2-多选3-主观4-评星
	 */
	private Integer type;
	/**  
	 * 是否必填，0-否 1-是
	 */
	private Integer isRequired;
	/**  
	 * 排序
	 */
	private Integer orderNum;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**  
	 * 问题选项
	 */
	List<QuestionnaireQuestionOptionBean> options = new ArrayList<QuestionnaireQuestionOptionBean>();
	/**  
	 * 用户每一题对应的用户答案
	 * 主观题存文本，评星题存分数，单选与多选存选项的ID
	 */
	private String answer;
	/**  
	 * 该题总回答的人数
	 */
	private Integer totalAnswerNum;
	/**  
	 * 该题对应的回答详情,主要用于主观题与评星题
	 */
	private List<AnswerVoBean> answerList = new ArrayList<AnswerVoBean>();
	/**  
	 * 评星题的平均得分
	 */
	private Integer starAverage;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(Integer questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(Integer isRequired) {
		this.isRequired = isRequired;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public List<QuestionnaireQuestionOptionBean> getOptions() {
		return options;
	}
	public void setOptions(List<QuestionnaireQuestionOptionBean> options) {
		this.options = options;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Integer getTotalAnswerNum() {
		return totalAnswerNum;
	}
	public void setTotalAnswerNum(Integer totalAnswerNum) {
		this.totalAnswerNum = totalAnswerNum;
	}
	public List<AnswerVoBean> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<AnswerVoBean> answerList) {
		this.answerList = answerList;
	}
	public Integer getStarAverage() {
		return starAverage;
	}
	public void setStarAverage(Integer starAverage) {
		this.starAverage = starAverage;
	}

}
