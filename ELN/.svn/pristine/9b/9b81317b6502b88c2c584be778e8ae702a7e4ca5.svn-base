/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseQuestionReplyBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-15        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean;

import java.util.Date;

import com.jftt.wifi.common.Constant;

/**
 * class name:CourseQuestionReplyBean <BR>
 * class description: 课程问答回答bean <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-15
 * @author JFTT)ShenHaijun
 */
public class CourseQuestionReplyBean {
	private Integer id;//回答id
	private Integer topicId;//问题id
	private Integer userId;//回答人id
	private String content;//回答内容
	private Date answertime;//回答时间
	private Integer type;//回答类型（1：问题的回答 2：回答的回答）
	private Integer parentId;//父级回答id
	private Integer companyId;//公司id
	private Integer subCompanyId;//子公司id
	
	private Date asktimeStart;//时间范围开始时间
	private Date asktimeEnd;//时间范围结束时间
	private String courseName;//课程名称
	private int  level;
	
	private String replyContent;//富文本,回帖整存
	
	//分页
	private Integer pageStartSize;//limit pageStartSize,pageSize
	private Integer pageIndex;//当前页
	private Integer pageSize;//每页显示记录数
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
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getAnswertime() {
		return answertime;
	}
	public void setAnswertime(Date answertime) {
		this.answertime = answertime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
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
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Integer getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	
}
