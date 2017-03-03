/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PostCourseVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-13        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.bean.vo;

import com.jftt.wifi.common.Constant;

/**
 * class name:PostCourseVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-13
 * @author JFTT)HeTianrui
 */
public class PostCourseVo {

	private Integer id;//课程ID
	private Integer courseId;
	private Integer categoryId;//课程体系ID
	private String code;//课程编码
	private String name;//课程名称
	private Integer companyId;//公司ID
	private Integer learningNum;//学习人数
	private float  score;//评分
	private Integer learnScore;
	private Integer  courseType;//课程类型
	
	private Integer postId;//岗位ID
	
	private String categoryName;//体系名称
	//分页
	private Integer pageStartSize;//limit pageStartSize,pageSize
	private Integer pageIndex;//当前页
	private Integer pageSize;//每页显示记录数
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
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
	public Integer getCourseType() {
		return courseType;
	}
	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getLearningNum() {
		return learningNum;
	}
	public void setLearningNum(Integer learningNum) {
		this.learningNum = learningNum;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getLearnScore() {
		return learnScore;
	}
	public void setLearnScore(Integer learnScore) {
		this.learnScore = learnScore;
	}
	
	
	
	

}
