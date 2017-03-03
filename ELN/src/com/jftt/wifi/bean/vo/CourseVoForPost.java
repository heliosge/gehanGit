/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseVoForPost.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年9月11日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * class name:CourseVoForPost <BR>
 * class description: 岗位课程vo <BR>
 * Remark: <BR>
 * @version 1.00 2015年9月11日
 * @author JFTT)ShenHaijun
 */
public class CourseVoForPost {
	private Integer id;//课程id
	private String name;//课程名称
	private String categoryName;//所属体系名称
	private Integer courseType;//课程类型 ：1、选修 2、必修
	private Integer learnProcess;//学习进度 ：1、进行中 2、已完成
	
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getCourseType() {
		return courseType;
	}
	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}
	public Integer getLearnProcess() {
		return learnProcess;
	}
	public void setLearnProcess(Integer learnProcess) {
		this.learnProcess = learnProcess;
	}
}
