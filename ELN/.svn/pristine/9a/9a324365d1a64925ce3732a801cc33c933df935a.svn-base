/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: MarkExamListItemVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/04        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam.vo;

import java.util.Date;

import org.springframework.util.Assert;

/**
 * class name:MarkExamListItemVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/04
 * @author JFTT)wangyifeng
 */
public class MarkExamListItemVo {
	/**  
	 * 考试ID
	 */
	private Integer examId;
	/**  
	 * 考试名称
	 */
	private String title;
	/**  
	 * 考试开始时间
	 */
	private Date beginTime;
	/**  
	 * 考试结束时间
	 */
	private Date endTime;
	/**  
	 * 考试创建时间
	 */
	private Date createTime;
	/**  
	 * 成绩是否公开
	 */
	private Boolean isScorePublic;
	/**  
	 * 参与人数
	 */
	private Integer attendCount;
	/**  
	 * 及格人数
	 */
	private Integer passCount;
	/**  
	 * 安排人数
	 */
	private Integer assignCount;
	/**  
	 * 批阅人数
	 */
	private Integer markCount;
	/**  
	 * 考试是否完成批阅
	 */
	private Boolean isMarked;
	/**  
	 * 是否发布成绩
	 */
	private Boolean isScorePublished;
	
	/**  
	 * 考试时长
	 */
	private Integer duration;
	/**  
	 * 考试次数
	 */
	private Integer allowTimes;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsScorePublic() {
		return isScorePublic;
	}

	public void setIsScorePublic(Boolean isScorePublic) {
		this.isScorePublic = isScorePublic;
	}

	public Integer getAttendCount() {
		return attendCount;
	}

	public void setAttendCount(Integer attendCount) {
		this.attendCount = attendCount;
	}

	public Integer getPassCount() {
		return passCount;
	}

	public void setPassCount(Integer passCount) {
		this.passCount = passCount;
	}

	public Integer getAssignCount() {
		return assignCount;
	}

	public void setAssignCount(Integer assignCount) {
		this.assignCount = assignCount;
	}

	public Integer getMarkCount() {
		return markCount;
	}

	public void setMarkCount(Integer markCount) {
		this.markCount = markCount;
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getTotalCountToBeMarked <BR>
	 * Description: 待批阅的总人数 <BR>
	 * Remark: <BR>
	 * @return  Integer<BR>
	 */
	public Integer getTotalCountToBeMarked() {
		return getAttendCount();
	}

	public Boolean getIsMarked() {
		return isMarked;
	}

	public void setIsMarked(Boolean isMarked) {
		this.isMarked = isMarked;
	}

	public Boolean getIsScorePublished() {
		return isScorePublished;
	}

	public void setIsScorePublished(Boolean isScorePublished) {
		this.isScorePublished = isScorePublished;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Boolean getCanBeMarked() {
		Assert.notNull(getIsScorePublished());
		return !getIsScorePublished();
	}

	public Boolean getScoreCanBePublished() {
		Assert.notNull(getIsMarked());
		return getIsMarked();
	}

	public Boolean getScoreCanBeModified() {
		Assert.notNull(getIsScorePublished());
		return getIsScorePublished();
	}

	public Boolean getCanBePreviewed() {
		Assert.notNull(getIsMarked());
		return getIsMarked();
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getAllowTimes() {
		return allowTimes;
	}

	public void setAllowTimes(Integer allowTimes) {
		this.allowTimes = allowTimes;
	}
	
}
