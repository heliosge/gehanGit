package com.jftt.wifi.bean.vo;


import java.util.List;

import com.jftt.wifi.bean.LearnPlanStageBean;

public class LearnPlanStageCourseEditVo {

	private Integer id;//阶段id
	private Integer planId;//计划id
	private String name;//阶段名称
	private String description;//阶段描述
	private String lecturers;//用逗号分隔的讲师ID
	private Integer learnTime;//学时
	private Integer learnScore;//学分
    private String updateTime;
    private List<LearnPlanStageCourseRelationVo>  courseList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLecturers() {
		return lecturers;
	}
	public void setLecturers(String lecturers) {
		this.lecturers = lecturers;
	}
	public Integer getLearnTime() {
		return learnTime;
	}
	public void setLearnTime(Integer learnTime) {
		this.learnTime = learnTime;
	}
	public Integer getLearnScore() {
		return learnScore;
	}
	public void setLearnScore(Integer learnScore) {
		this.learnScore = learnScore;
	}

  
  
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public List<LearnPlanStageCourseRelationVo> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<LearnPlanStageCourseRelationVo> courseList) {
		this.courseList = courseList;
	}
	public LearnPlanStageBean getBean(){
    	LearnPlanStageBean bean = new LearnPlanStageBean();
    	bean.setId(this.id);
    	bean.setName(this.name);
    	bean.setPlanId(this.planId);
    	bean.setDescription(this.description);
    	bean.setLecturers(this.lecturers);
    	bean.setLearnTime(this.learnTime);
    	bean.setLearnScore(this.learnScore);
    	return bean;
    }

}
