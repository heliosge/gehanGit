/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseQuestionTopicBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-15        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean;

import java.util.Date;

/**
 * class name:CourseQuestionTopicBean <BR>
 * class description: 课程问答问题bean <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-15
 * @author JFTT)ShenHaijun
 */
public class CourseQuestionTopicBean {
	private Integer id;//问题id
	private Integer courseId;//课程id
	private Integer userId;//提问人id
	private String title;//提问标题
	private String content;//提问内容
	private String frontImage;//问题封面图片
	private String qcode;//问题编号
	private Date asktime;//提问时间
	private Date asktimeStart;//时间范围开始时间
	private Date asktimeEnd;//时间范围结束时间
	private String courseName;//课程名称
	private Integer state;//问题状态（1：未回答（默认） 2：已回答）
	private Integer companyId;//公司id
	private Integer subCompanyId;//子公司id
	private Integer courseWareId;//问题所在课件的id
	private Integer courseWareType;//问题所在课件的类型
	
	public Integer getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFrontImage() {
		return frontImage;
	}
	public void setFrontImage(String frontImage) {
		this.frontImage = frontImage;
	}
	public String getQcode() {
		return qcode;
	}
	public void setQcode(String qcode) {
		this.qcode = qcode;
	}
	public Date getAsktime() {
		return asktime;
	}
	public void setAsktime(Date asktime) {
		this.asktime = asktime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getAsktimeStart() {
		return asktimeStart;
	}
	public void setAsktimeStart(Date asktimeStart) {
		this.asktimeStart = asktimeStart;
	}
	public Date getAsktimeEnd() {
		return asktimeEnd;
	}
	public void setAsktimeEnd(Date asktimeEnd) {
		this.asktimeEnd = asktimeEnd;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getCourseWareId() {
		return courseWareId;
	}
	public void setCourseWareId(Integer courseWareId) {
		this.courseWareId = courseWareId;
	}
	public Integer getCourseWareType() {
		return courseWareType;
	}
	public void setCourseWareType(Integer courseWareType) {
		this.courseWareType = courseWareType;
	}
}
