/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireAnswer.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-18        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.questionnaire;

/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireAnswer <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-18
 */
/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireAnswerBean <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-9
 */
public class QuestionnaireAnswerBean {
	/**
	 * 答案主键ID
	 */
	private Integer id;
	/**  
	 * 参与ID
	 */
	private Integer assignId;
	/**  
	 *问题ID
	 */
	private Integer questionId;
	/**  
	 * 调查答案
		1、单选题：存放选项ID
		2、多选题：存放选项ID，以逗号拼接
		3、主观题：存放文本内容
		4、评星题：存放星级对应的数字
	 */
	private String answer;
	/**  
	 * 更新时间
	 */
	private String updateTime;
	/**  
	 * 用户ID
	 */
	private Integer userId;
	/**  
	 * 答案提交时间
	 */
	private String submitTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAssignId() {
		return assignId;
	}
	public void setAssignId(Integer assignId) {
		this.assignId = assignId;
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
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	

}
