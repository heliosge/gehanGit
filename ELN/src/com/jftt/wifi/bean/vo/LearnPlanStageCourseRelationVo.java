package com.jftt.wifi.bean.vo;

import java.util.Date;

import com.jftt.wifi.bean.LearnPlanStageCourseRelationBean;
import com.jftt.wifi.util.TimeUtil;


/**
 * class LearnPlanStageCourseRelationVo <BR>
 * class description: 计划阶段下课程 <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-12
 * @author zhangbocheng
 * 
 */
public class LearnPlanStageCourseRelationVo {

	   private Integer id; //学习计划阶段课程关联id
	   private Integer stageId;//学习计划阶段id
	   private Integer courseId;//课程id
	   private String courseName;//课程名称
	   private String courseCode;//课程编号
	   private String beginTime;//学习开始时间
	   private String endTime;//学习结束时间
	   private Integer isDisabled;//1:禁止学习，0：允许学习，默认允许学习
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Integer getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Integer isDesabled) {
		this.isDisabled = isDesabled;
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
	
	private Date getDate4String(String date,String pattern){
		try{
			return TimeUtil.parseString2Date(date, pattern);
		}catch(Exception e){
			return null;
		}
	}

	public LearnPlanStageCourseRelationBean vo2Bean() throws Exception{
		LearnPlanStageCourseRelationBean bean = new LearnPlanStageCourseRelationBean();
		bean.setId(id);
		bean.setBeginTime(getDate4String(beginTime, "yyyy-MM-dd HH:mm"));
		bean.setEndTime(getDate4String(endTime, "yyyy-MM-dd HH:mm"));
		bean.setIsDisabled(isDisabled);
		bean.setStageId(stageId);
		bean.setCourseId(courseId);
		return bean;
	}
	   
}
