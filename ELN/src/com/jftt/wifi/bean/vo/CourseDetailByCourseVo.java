/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseDetailByCourseVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-1-14        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.Date;

import com.jftt.wifi.bean.questionnaire.UserBaseBean;

/**
 * @author JFTT)zhangchen<BR>
 * class name:CourseDetailByCourseVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2016-1-14
 */
public class CourseDetailByCourseVo extends UserBaseBean{
	/**  
	 * 用户ID
	 */
	private Integer userId;
	/**  
	 * 课程ID
	 */
	private Integer courseId;
	/**  
	 * 学习进度（1：进行中 2：已完成）
	 */
	private Integer learnProcess;
	/**  
	 * 开始学习时间
	 */
	private String startTime;
	/**  
	 * 本次学习时间
	 */
	private String thisLearnTime;
	/**  
	 * 学习时长
	 */
	private Integer durationTime;
	/**  
	 * 获得学分
	 */
	private Integer getScore;
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getLearnProcess() {
		return learnProcess;
	}
	public void setLearnProcess(Integer learnProcess) {
		this.learnProcess = learnProcess;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getThisLearnTime() {
		return thisLearnTime;
	}
	public void setThisLearnTime(String thisLearnTime) {
		this.thisLearnTime = thisLearnTime;
	}
	public Integer getDurationTime() {
		return durationTime;
	}
	public void setDurationTime(Integer durationTime) {
		this.durationTime = durationTime;
	}
	public Integer getGetScore() {
		return getScore;
	}
	public void setGetScore(Integer getScore) {
		this.getScore = getScore;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
