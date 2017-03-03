/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseStudyVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月28日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.Date;


/**
 * class name:CourseStudyVo <BR>
 * class description: 课程学习页面课程vo <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月28日
 * @author JFTT)ShenHaijun
 */
public class CourseStudyVo {
	private Integer id;//课程id
	private String name;//课程名称
	private Float avgScore;//课程评分
	private Integer evaluatorCount;//评价人数
	private Date createTime;//创建时间
	private String sectionName;//章节名称
	private String wareName;//课件名称
	private Integer studentNum;//学习人数

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(Float avgScore) {
		this.avgScore = avgScore;
	}
	public Integer getEvaluatorCount() {
		return evaluatorCount;
	}
	public void setEvaluatorCount(Integer evaluatorCount) {
		this.evaluatorCount = evaluatorCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getWareName() {
		return wareName;
	}
	public void setWareName(String wareName) {
		this.wareName = wareName;
	}
	public Integer getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}
}
