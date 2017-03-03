/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: SimExamListItemVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/04        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam.vo;

import java.text.ParseException;
import java.util.Date;

import com.jftt.wifi.bean.exam.ExamRawStateInfo;
import com.jftt.wifi.bean.exam.enumtype.ExamState;

/**
 * class name:SimExamListItemVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/04
 * @author JFTT)wangyifeng
 */
public class SimExamListItemVo implements ExamRawStateInfo {
	/**
	 * 考试ID
	 */
	private Integer id;
	/**
	 * 考试名称
	 */
	private String title;
	/**
	 * 考试时长
	 */
	private Integer duration;
	/**
	 * 考试创建时间
	 */
	private String createTime;
	/**
	 * 是否发布
	 */
	private Boolean isPublished;
	/**
	 * 显示模式
	 */
	private Integer displayMode;
	/**
	 * 试卷名称
	 */
	private String paperName;
	/**
	 * 考试类型
	 */
	private Integer type;
	/**
	 * 试卷总分
	 */
	private Integer totalScore;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}

	public Integer getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(Integer displayMode) {
		this.displayMode = displayMode;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	/**
	 * Method name: getExamState <BR>
	 * Description: 考试状态 <BR>
	 * Remark: <BR>
	 * 
	 * @return ExamState<BR>
	 * @throws ParseException 
	 */
	public ExamState getExamState() throws ParseException {
		return ExamState.getExamState(this);
	}

	/**
	 * Method name: getCanBeDeleted <BR>
	 * Description: 是否可以删除 <BR>
	 * Remark: <BR>
	 * 
	 * @return Boolean<BR>
	 * @throws ParseException 
	 */
	public Boolean getCanBeDeleted() throws ParseException {
		return getExamState().getCanBeDeleted();
	}

	@Override
	public String getBeginTime() {
		// 模拟考试无需该属性
		return null;
	}

	@Override
	public String getEndTime() {
		// 模拟考试无需该属性
		return null;
	}
}
