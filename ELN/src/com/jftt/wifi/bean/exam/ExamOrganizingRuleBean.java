/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamOrganizingRuleBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/11        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam;

import java.util.Date;

import com.jftt.wifi.bean.exam.vo.AutoQuestionGroupVo;

/**
 * @author JFTT)wangyifeng
 * class name:ExamOrganizingRuleBean <BR>
 * class description: 组卷规则 <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/11
 */
public class ExamOrganizingRuleBean {
	/**  
	 * 主键ID
	 */
	private Integer id;
	/**  
	 * 试卷ID
	 */
	private Integer paperId;
	/**  
	 * 试题展示类型
	 */
	private Integer questionDisplayType;
	/**  
	 * 试题分类ID
	 */
	private Integer questionCategoryId;
	/**  
	 * 试题分类名称
	 */
	private String questionCategoryName;
	/**  
	 * 规则
	 */
	private String rule;
	/**  
	 * 总分
	 */
	private Integer totalScore;
	/**  
	 * 序号
	 */
	private Integer orderNum;
	/**  
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getAutoQuestionGroup <BR>
	 * Description: 获取该实例对应的AutoQuestionGroupVo实例 <BR>
	 * Remark: <BR>
	 * @return  AutoQuestionGroupVo<BR>
	 */
	public AutoQuestionGroupVo getAutoQuestionGroup() {
		return AutoQuestionGroupVo.getInstance(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public Integer getQuestionDisplayType() {
		return questionDisplayType;
	}

	public void setQuestionDisplayType(Integer questionDisplayType) {
		this.questionDisplayType = questionDisplayType;
	}

	public Integer getQuestionCategoryId() {
		return questionCategoryId;
	}

	public void setQuestionCategoryId(Integer questionCategoryId) {
		this.questionCategoryId = questionCategoryId;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getQuestionCategoryName() {
		return questionCategoryName;
	}

	public void setQuestionCategoryName(String questionCategoryName) {
		this.questionCategoryName = questionCategoryName;
	}
}
