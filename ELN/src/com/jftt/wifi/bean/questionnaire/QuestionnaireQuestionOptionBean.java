/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireQuestionOptionBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-18        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.questionnaire;

/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireQuestionOptionBean <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-18
 */
public class QuestionnaireQuestionOptionBean {
	/**
	 * 关联主键ID
	 */
	private Integer id;
	/**  
	 * 问题ID
	 */
	private Integer questionId;
	/**  
	 * 排序
	 */
	private Integer orderNum;
	/**  
	 * 选项内容
	 */
	private String content;
	/**  
	 * 更新时间
	 */
	private String updateTime;
	/**  
	 * 被选中的次数
	 */
	private Integer selectNum;
	/**  
	 * 被选中的百分比
	 */
	private String selectPercent;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getSelectNum() {
		return selectNum;
	}
	public void setSelectNum(Integer selectNum) {
		this.selectNum = selectNum;
	}
	public String getSelectPercent() {
		return selectPercent;
	}
	public void setSelectPercent(String selectPercent) {
		this.selectPercent = selectPercent;
	}

}
