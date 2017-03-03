/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: QuestionBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/22        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jftt.wifi.bean.exam.enumtype.QuestionDisplayType;

/**
 * class name:QuestionBean <BR>
 * class description: 试题Bean <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/22
 * @author JFTT)wangyifeng
 */
public class QuestionBean implements QuestionRawTypeInfo {
	/**
	 * 试题主键ID
	 */
	private Integer id;
	/**
	 * 试题分类ID
	 */
	private Integer categoryId;
	/**
	 * 题干
	 */
	private String content;
	/**  
	 * UI展示的题型：1主观题，2单选题，3多选题，4判断题，5填空题，6组合题，7多媒体题
	 */
	private Integer displayType;
	/**
	 * 题型 此处的类型不包含多媒体<br/>
	 * 是否为多媒体题由isMultimedia属性来定义<br/>
	 */
	private Integer type;
	/**
	 * 是否为组合题的子试题
	 */
	private Boolean isSubQuestion;
	/**
	 * 子试题ID列表，逗号分隔
	 */
	private String questionIdList;
	/**
	 * 是否为多媒体提
	 */
	private Boolean isMultimedia;
	/**
	 * 多媒体附件类型
	 */
	private Integer multimediaType;
	/**
	 * 多媒体附件URL
	 */
	private String multimediaUrl;
	private Long file_size;          //附件大小， 只正对 音频  视频
	/**
	 * 正确与否
	 */
	private Boolean isTrue;
	/**
	 * 难度级别
	 */
	private Integer difficultyLevel;
	/**
	 * 答案关键字：空格分隔
	 */
	private String answerKeys;
	/**
	 * 试题解析
	 */
	private String analysis;
	/**
	 * 创建者ID
	 */
	private Integer createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否可用
	 */
	private Boolean isEnabled;
	/**
	 * 是否删除
	 */
	private Boolean isDeleted;
	/**
	 * 分公司ID
	 */
	private Integer subCompanyId;
	/**
	 * 公司ID
	 */
	private Integer companyId;
	/**
	 * 选项列表
	 */
	private List<QuestionOptionBean> options = new ArrayList<QuestionOptionBean>();
	/**
	 * 子试题列表
	 */
	private List<QuestionBean> subQuestions = new ArrayList<QuestionBean>();
	/**
	 * 正确答案
	 */
	private String correctAnswer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public Boolean getIsSubQuestion() {
		return isSubQuestion;
	}

	public void setIsSubQuestion(Boolean isSubQuestion) {
		this.isSubQuestion = isSubQuestion;
	}

	public String getQuestionIdList() {
		return questionIdList;
	}

	public void setQuestionIdList(String questionIdList) {
		this.questionIdList = questionIdList;
	}

	@Override
	public Boolean getIsMultimedia() {
		return isMultimedia;
	}

	public void setIsMultimedia(Boolean isMultimedia) {
		this.isMultimedia = isMultimedia;
	}

	public Integer getMultimediaType() {
		return multimediaType;
	}

	public void setMultimediaType(Integer multimediaType) {
		this.multimediaType = multimediaType;
	}

	public String getMultimediaUrl() {
		return multimediaUrl;
	}

	public void setMultimediaUrl(String multimediaUrl) {
		this.multimediaUrl = multimediaUrl;
	}

	public Boolean getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}

	public Integer getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getAnswerKeys() {
		return answerKeys;
	}

	public void setAnswerKeys(String answerKeys) {
		this.answerKeys = answerKeys;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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

	public List<QuestionOptionBean> getOptions() {
		return options;
	}

	public void setOptions(List<QuestionOptionBean> options) {
		this.options = options;
	}

	public List<QuestionBean> getSubQuestions() {
		return subQuestions;
	}

	public void setSubQuestions(List<QuestionBean> subQuestions) {
		this.subQuestions = subQuestions;
	}

	public QuestionDisplayType getQuestionDisplayType() {
		return QuestionDisplayType.getDisplayQuestionType(this);
	}

	@Override
	public Integer getDisplayType() {
		if (displayType == null && QuestionDisplayType.checkValidQuestion(this)) {
			displayType = QuestionDisplayType.getDisplayQuestionType(this)
					.getIntValue();
		}
		return displayType;
	}

	public void setDisplayType(Integer displayType) {
		this.displayType = displayType;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Long getFile_size() {
		return file_size;
	}

	public void setFile_size(Long file_size) {
		this.file_size = file_size;
	}
	
}
