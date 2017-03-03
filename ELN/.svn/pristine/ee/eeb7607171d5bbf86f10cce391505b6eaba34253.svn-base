/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: QuestionVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/27        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam.vo;

import com.jftt.wifi.bean.exam.QuestionRawTypeInfo;
import com.jftt.wifi.bean.exam.enumtype.QuestionDisplayType;

/**
 * class name:QuestionVo <BR>
 * class description: 试题列表项Vo <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/27
 * @author JFTT)wangyifeng
 */
public class QuestionListItemVo implements QuestionRawTypeInfo {
	/**
	 * 试题主键ID
	 */
	private Integer id;
	/**
	 * 题干
	 */
	private String content;
	/**  
	 * UI展示的题型：1主观题，2单选题，3多选题，4判断题，5填空题，6组合题，7多媒体题
	 */
	private Integer displayType;
	/**
	 * 题型<br/>
	 * 此处的类型包含多媒体题
	 */
	private Integer type;
	/**
	 * 是否为多媒体提
	 */
	private Boolean isMultimedia;
	/**
	 * 难度级别
	 */
	private Integer difficultyLevel;
	/**
	 * 是否可用
	 */
	private Boolean isEnabled;
	/**
	 * 试题分类ID
	 */
	private Integer questionCategoryId;
	/**
	 * 试题分类全名
	 */
	private String categoryFullName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public Boolean getIsMultimedia() {
		return isMultimedia;
	}

	public void setIsMultimedia(Boolean isMultimedia) {
		this.isMultimedia = isMultimedia;
	}

	public Integer getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Integer getQuestionCategoryId() {
		return questionCategoryId;
	}

	public void setQuestionCategoryId(Integer questionCateogryId) {
		this.questionCategoryId = questionCateogryId;
	}

	public String getCategoryFullName() {
		return categoryFullName;
	}

	public void setCategoryFullName(String categoryFullName) {
		this.categoryFullName = categoryFullName;
	}

	public QuestionDisplayType getQuestionDisplayType() {
		return QuestionDisplayType.getDisplayQuestionType(this);
	}

	@Override
	public Integer getDisplayType() {
		return displayType;
	}

	public void setDisplayType(Integer displayType) {
		this.displayType = displayType;
	}
}
