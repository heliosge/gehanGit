package com.jftt.wifi.bean;

import java.util.Date;

/**
 * class LearnPlanStageCourseRelationBean <BR>
 * class description: 学习计划阶段课程关联bean <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-6
 * @author zhangbocheng
 * 
 */
public class LearnPlanStageCourseRelationBean {
	   private Integer id; //学习计划阶段课程关联id
	   private Integer stageId;//学习计划阶段id
	   private Integer courseId;//课程id
	   private Date beginTime;//学习开始时间(开始和结束时间，必须有一个非空）
	   private Date endTime;//学习结束时间(开始和结束时间，必须有一个非空）
	   private Integer isDisabled;//1:禁止学习，0：允许学习，默认允许学习
	   private Date updateTime;//最后更新时间
	   
	   
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStageId() {
		return stageId;
	}
	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
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

	public Integer getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	   
	   
}
