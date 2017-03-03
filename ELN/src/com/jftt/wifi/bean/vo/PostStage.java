/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PostStage.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-17        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.Date;

/**
 * class name:PostStage <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-17
 * @author JFTT)HeTianrui
 */
public class PostStage {
	
	private Integer id;//晋升路径阶段ID，自增ID
	private Integer postId;//岗位ID
	private Integer pathId;//晋升路径ID
	private String description;//阶段描述
	private Integer period;//晋升周期（单位：天？）
	private  float obligatoryMinCredits;//必修最少学分
	private  float electiveMinCredits;//选修最少学分
	private  Integer examId;//试卷ID
	private  Integer paperId;//试卷
	private  Integer duration;//考试时长
	private  Integer passScorePercent;//及格分（百分比）
	private  Integer orderNum;//阶段序号
	private  Integer isDeleted;//是否删除（逻辑删除）：1逻辑删除，0非删除
	private  Date updateTime;//最后更新时间
	
	private String postName;//岗位名称
	private String examTitle;//试卷标题
	private Integer companyId;//公司
	private Integer subCompanyId;//分公司
	private Integer createUserId;//创建人
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Integer getPathId() {
		return pathId;
	}
	public void setPathId(Integer pathId) {
		this.pathId = pathId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public float getObligatoryMinCredits() {
		return obligatoryMinCredits;
	}
	public void setObligatoryMinCredits(float obligatoryMinCredits) {
		this.obligatoryMinCredits = obligatoryMinCredits;
	}
	public float getElectiveMinCredits() {
		return electiveMinCredits;
	}
	public void setElectiveMinCredits(float electiveMinCredits) {
		this.electiveMinCredits = electiveMinCredits;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getPassScorePercent() {
		return passScorePercent;
	}
	public void setPassScorePercent(Integer passScorePercent) {
		this.passScorePercent = passScorePercent;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getExamTitle() {
		return examTitle;
	}
	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
