/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: InvestigationAnswerVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-12-3        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.questionnaire;

import java.io.Serializable;

/**
 * @author JFTT)zhangchen<BR>
 * class name:InvestigationAnswerVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-3
 */
public class InvestigationAnswerVo implements Serializable{

	/**  
	 * define a field serialVersionUID which type is long
	 */
	private static final long serialVersionUID = 1L;
	/**  
	 * 调查主键ID
	 */
	private String id;
	/**  
	 * 参与ID
	 */
	private int assignId;
	/**  
	 * 问题ID
	 */
	private String[] questionId;
	/**  
	 * 用户答案
	 */
	private String[] answer;
	/**  
	 * 用户ID
	 */
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAssignId() {
		return assignId;
	}
	public void setAssignId(int assignId) {
		this.assignId = assignId;
	}
	public String[] getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String[] questionId) {
		this.questionId = questionId;
	}
	public String[] getAnswer() {
		return answer;
	}
	public void setAnswer(String[] answer) {
		this.answer = answer;
	}

}
