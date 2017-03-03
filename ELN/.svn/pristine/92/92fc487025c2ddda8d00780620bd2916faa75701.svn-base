/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: QuestionSearchOptionBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/22        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam.vo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.jftt.wifi.bean.exam.enumtype.QuestionDisplayType;
import com.jftt.wifi.bean.knowledge_contest.SearchOptionBaseWithPagination;

/**
 * class name:QuestionSearchOptionBean <BR>
 * class description: 试题查询条件Vo <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/22
 * @author JFTT)wangyifeng
 */
public class QuestionSearchOptionVo extends SearchOptionBaseWithPagination {
	/**
	 * 是否可用
	 */
	private Boolean isEnabled;
	/**
	 * 题干
	 */
	private String content;
	/**
	 * 试题类型
	 */
	private QuestionDisplayType questionDisplayType;
	/**
	 * 难度级别
	 */
	private Integer difficultyLevel;
	
	/**
	 * 试题分类ID
	 */
	private Integer categoryId;
	/**  
	 * 试题分类ID字符串，逗号分隔
	 */
	private String categoryIdListStr;
	/**
	 * 分公司ID
	 */
	private Integer subCompanyId;
	/**
	 * 公司ID
	 */
	private Integer companyId;
	/**
	 * 创建者ID
	 */
	private Integer createUserId;
	
	/**  
	 * 用户ID
	 */
	private Integer userId;
	
	private String difficultyLevelStr;//难易度，多个以逗号拼接
	private String categoryName;//试题库名称
	private String displayMode;//显示方式 
	/**  
	 * 父试题库ID
	 */
	private Integer ParentCategoryId;
	
	
	public String getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(String displayMode) {
		this.displayMode = displayMode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDifficultyLevelStr() {
		return difficultyLevelStr;
	}

	public void setDifficultyLevelStr(String difficultyLevelStr) {
		this.difficultyLevelStr = difficultyLevelStr;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public QuestionDisplayType getQuestionDisplayType() {
		return questionDisplayType;
	}

	public void setQuestionDisplayType(QuestionDisplayType questionDisplayType) {
		this.questionDisplayType = questionDisplayType;
	}

	public Integer getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSubCompanyId() {
		return subCompanyId;
	}

	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getCategoryIdListStr() {
		return categoryIdListStr;
	}

	public void setCategoryIdListStr(String categoryIdListStr) {
		this.categoryIdListStr = categoryIdListStr;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getParentCategoryId() {
		return ParentCategoryId;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		ParentCategoryId = parentCategoryId;
	}
	
	
	
}
