/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseCollectionBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-15        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean;

import java.util.Date;

import com.jftt.wifi.common.Constant;

/**
 * class name:CourseCollectionBean <BR>
 * class description: 课程收藏bean <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-15
 * @author JFTT)ShenHaijun
 */
public class CourseCollectionBean {
	private Integer id;//课程收藏id
	private Integer courseId;//课程id
	private Integer userId;//收藏人id
	private Date collectTime;//收藏时间
	private Integer companyId;//公司id
	private String name;//课程名称
	private String frontImage;//课程封面
	private Integer averageScore;//该课程平均评分
	private Integer evaluatorNum;//该课程评分人数
	private Integer studentNum;//该课程学生人数
	private Date createTimeStart; //查询时间开始
	private Date createTimeEnd;   //查询时间结束
	private String showType;//大图=0/列表=1
	private Integer subCompanyId;//子公司id
	private Date updateTime;//更新时间
	
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
	public Date getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFrontImage() {
		return frontImage;
	}
	public void setFrontImage(String frontImage) {
		this.frontImage = frontImage;
	}
	public Integer getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(Integer averageScore) {
		this.averageScore = averageScore;
	}
	public Integer getEvaluatorNum() {
		return evaluatorNum;
	}
	public void setEvaluatorNum(Integer evaluatorNum) {
		this.evaluatorNum = evaluatorNum;
	}
	public Integer getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
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
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
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
	public Integer getPageStartSize() {
		return pageStartSize;
	}
	public void setPageStartSize(Integer pageStartSize) {
		this.pageStartSize = pageStartSize;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
