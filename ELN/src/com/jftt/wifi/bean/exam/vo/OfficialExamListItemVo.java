/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: OfficialExamListItemVo.java
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
import com.jftt.wifi.util.ExamUtils;

/**
 * class name:OfficialExamListItemVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/04
 * @author JFTT)wangyifeng
 */
public class OfficialExamListItemVo implements ExamRawStateInfo {
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
	 * 考试开始时间
	 */
	private String beginTime;
	/**
	 * 考试结束时间
	 */
	private String endTime;
	/**
	 * 答案是否公开
	 */
	private Boolean isAnswerPublic;
	/**
	 * 考试次数
	 */
	private Integer allowTimes;
	/**
	 * 试卷总分
	 */
	private Integer totalScore;
	/**
	 * 试卷及格分的百分比值
	 */
	private Integer passScorePercent;
	/**
	 * 考试创建时间
	 */
	private String createTime;
	/**
	 * 是否已经发布
	 */
	private Boolean isPublished;
	/**
	 * 考试类型
	 */
	private Integer type;

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

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsAnswerPublic() {
		return isAnswerPublic;
	}

	public void setIsAnswerPublic(Boolean isAnswerPublic) {
		this.isAnswerPublic = isAnswerPublic;
	}

	public Integer getAllowTimes() {
		return allowTimes;
	}

	public void setAllowTimes(Integer allowTimes) {
		this.allowTimes = allowTimes;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getPassScorePercent() {
		return passScorePercent;
	}

	public void setPassScorePercent(Integer passScorePercent) {
		this.passScorePercent = passScorePercent;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * Method name: getExamState <BR>
	 * Description: 获取考试状态 <BR>
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

	/**
	 * Method name: getCanBeCanceled <BR>
	 * Description: 是否可以取消发布 <BR>
	 * Remark: <BR>
	 * 
	 * @return Boolean<BR>
	 * @throws ParseException 
	 */
	public Boolean getCanBeCanceled() throws ParseException {
		return getExamState().getCanBeCanceled();
	}

	/**
	 * Method name: getPassScore <BR>
	 * Description: 四舍五入获取通过分数 <BR>
	 * Remark: <BR>
	 * 
	 * @return Integer<BR>
	 */
	public Integer getPassScore() {
		if(getType() == 1){
			return ExamUtils.calculatePassScore(getTotalScore(),
					getPassScorePercent());
		}else{
			return null;
		}
		
	}
}
