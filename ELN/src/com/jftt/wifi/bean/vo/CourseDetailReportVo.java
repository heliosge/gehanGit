/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseDetailReportVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-1-12        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * @author JFTT)zhangchen<BR>
 * class name:CourseDetailReportVo <BR>
 * class description: 课程学习情况统计vo <BR>
 * Remark: <BR>
 * @version 1.00 2016-1-12
 */
public class CourseDetailReportVo {
	/**  
	 * 课程ID
	 */
	private Integer id;
	/**  
	 * 课程名称
	 */
	private String courseName;
	/**  
	 * 课程与
	 */
	private String courseCode;
	/**  
	 * 课程体系名称
	 */
	private String courseCategory;
	/**  
	 * 课程分类名称
	 */
	private String courseType;
	/**  
	 * 课程学时 
	 */
	private Integer courseTime;
	/**  
	 * 正在学习人数
	 */
	private Integer learningNum;
	/**  
	 * 完成学习人数
	 */
	private Integer completeNum;
	/**  
	 * 自学人数
	 */
	private Integer selfStudyNum;
	/**  
	 * 指派学习人数
	 */
	private Integer assignNum;
	/**  
	 * 总学习人数
	 */
	private Integer totalNum;
	/**  
	 * 总学习时长
	 */
	private Integer totalTime;
	/**  
	 * 获得学分
	 */
	private Integer getScore;
	/**  
	 * 学习人数占比
	 */
	private String learnPercent;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseCategory() {
		return courseCategory;
	}
	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}
	public Integer getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(Integer courseTime) {
		this.courseTime = courseTime;
	}
	public Integer getLearningNum() {
		return learningNum;
	}
	public void setLearningNum(Integer learningNum) {
		this.learningNum = learningNum;
	}
	public Integer getCompleteNum() {
		return completeNum;
	}
	public void setCompleteNum(Integer completeNum) {
		this.completeNum = completeNum;
	}
	public Integer getSelfStudyNum() {
		return selfStudyNum;
	}
	public void setSelfStudyNum(Integer selfStudyNum) {
		this.selfStudyNum = selfStudyNum;
	}
	public Integer getAssignNum() {
		return assignNum;
	}
	public void setAssignNum(Integer assignNum) {
		this.assignNum = assignNum;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}
	public Integer getGetScore() {
		return getScore;
	}
	public void setGetScore(Integer getScore) {
		this.getScore = getScore;
	}
	public String getLearnPercent() {
		return learnPercent;
	}
	public void setLearnPercent(String learnPercent) {
		this.learnPercent = learnPercent;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
}
