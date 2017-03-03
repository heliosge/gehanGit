/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseNoteBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-15        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean;

import java.util.Date;

import com.jftt.wifi.common.Constant;

/**
 * class name:CourseNoteBean <BR>
 * class description: 课程笔记bean <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-15
 * @author JFTT)ShenHaijun
 */
public class CourseNoteBean {
	private Integer id;//课程笔记id
	private Integer courseId;//课程id
	private Integer userId;//用户id
	private Date createTime;//创建时间
	private Integer openState;//公开状态（1：公开 2：不公开）
	private String title;//笔记标题
	private String content;//笔记内容
	private Integer companyId;//公司id
	private Integer subCompanyId;//子公司id
	
	private String code;//
	private String frontImage;//封面
	private String courseName;//课程名
	private String num;//笔记数
	private Date createTimeStart; //查询时间开始
	private Date createTimeEnd;   //查询时间结束
	
	//排序
	private String sort;//排序字段
	private String order;//排序升降
	
	//分页
	private Integer pageStartSize;//limit pageStartSize,pageSize
	private Integer pageIndex;//当前页
	private Integer pageSize;//每页显示记录数
	
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getOpenState() {
		return openState;
	}
	public void setOpenState(Integer openState) {
		this.openState = openState;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFrontImage() {
		return frontImage;
	}
	public void setFrontImage(String frontImage) {
		this.frontImage = frontImage;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public Date getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public Integer getPageStartSize() {
		return pageStartSize;
	}
	public void setPageStartSize(Integer pageStartSize) {
		this.pageStartSize = pageStartSize;
	}
	public Integer getPageIndex() {
		return pageIndex==null?1:pageIndex<0?1:pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex==null?1:pageIndex<0?1:pageIndex;
	}
	public Integer getPageSize() {
		return pageSize==null?Constant.PAGE_SIZE:pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize==null?Constant.PAGE_SIZE:pageSize;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
}
