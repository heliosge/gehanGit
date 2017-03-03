/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamScheduleBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/29        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.enumtype.ExamRelationType;
import com.jftt.wifi.bean.exam.enumtype.ExamType;
import com.jftt.wifi.bean.exam.vo.ScoreMarkerVo;

/**
 * class name:ExamScheduleBean <BR>
 * class description: 考试Bean <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/29
 * @author JFTT)wangyifeng
 */
public class ExamScheduleBean {
	/**
	 * 考试ID
	 */
	private Integer id;
	/**
	 * 考试标题（名称）
	 */
	private String title;
	/**
	 * 试卷ID
	 */
	private Integer paperId;
	/**
	 * 考试开始时间
	 */
	private String beginTime;
	/**
	 * 考试结束时间
	 */
	private String endTime;
	/**
	 * 考试时长（分钟）
	 */
	private Integer duration;
	/**
	 * 试卷显示方式
	 */
	private Integer displayMode;
	/**
	 * 允许考试次数
	 */
	private Integer allowTimes;
	/**
	 * 随机打乱模式
	 */
	private Integer randomOrderMode;
	/**
	 * 是否公开答案
	 */
	private Integer isAnswerPublic;
	/**
	 * 是否公开成绩
	 */
	private Integer isScorePublic;
	/**
	 * 及格分（百分比）
	 */
	private Integer passScorePercent;
	/**
	 * 考前须知
	 */
	private String notice;
	/**
	 * 批阅人ID（逗号分隔)
	 */
	private String scoreMarker;
	/**
	 * 批阅人列表
	 */
	private List<ScoreMarkerVo> scoreMarkerList = new ArrayList<ScoreMarkerVo>();
	private String scoreMarkerName;
	/**
	 * 是否发布
	 */
	private Integer isPublished;
	/**
	 * 是否批阅
	 */
	private Integer isMarked;
	/**
	 * 是否发布成绩
	 */
	private Boolean isScorePublished;
	/**
	 * 是否开启防作弊
	 */
	private Integer isAntiCheating;
	/**
	 * 允许离开考试次数
	 */
	private Integer allowLeaveTimes;
	/**
	 * 是否自动批阅(包含主观题时是否自动批阅)
	 */
	private Integer isAutoMarking;
	/**
	 * 创建人ID
	 */
	private Integer createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否删除
	 */
	private Boolean isDeleted;
	/**
	 * 分公司ID
	 */
	private Integer subCompanyId;
	/**
	 * 公司ID
	 */
	private Integer companyId;
	/**
	 * 考试种类
	 */
	private Integer type;
	/**
	 * 试卷名称
	 */
	private String paperName;
	/**  
	 * 允许最快交卷时间
	 */
	private Integer submitDuration;
	/**
	 * 授权考试ID列表
	 */
	private List<Integer> assignedUserIdList = new ArrayList<Integer>();
	/**
	 * 考试分配情况列表
	 */
	private List<ExamAssignBean> assignInfoList = new ArrayList<ExamAssignBean>();
	
	private List<ManageUserBean> userList = new ArrayList<ManageUserBean>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(Integer displayMode) {
		this.displayMode = displayMode;
	}

	public Integer getAllowTimes() {
		return allowTimes;
	}

	public void setAllowTimes(Integer allowTimes) {
		this.allowTimes = allowTimes;
	}

	public Integer getRandomOrderMode() {
		return randomOrderMode;
	}

	public void setRandomOrderMode(Integer randomOrderMode) {
		this.randomOrderMode = randomOrderMode;
	}

	public Integer getPassScorePercent() {
		return passScorePercent;
	}

	public void setPassScorePercent(Integer passScorePercent) {
		this.passScorePercent = passScorePercent;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getScoreMarker() {
		return scoreMarker;
	}

	public void setScoreMarker(String scoreMarker) {
		this.scoreMarker = scoreMarker;
	}

	public Boolean getIsScorePublished() {
		return isScorePublished;
	}

	public void setIsScorePublished(Boolean isScorePublished) {
		this.isScorePublished = isScorePublished;
	}

	public Integer getAllowLeaveTimes() {
		return allowLeaveTimes;
	}

	public void setAllowLeaveTimes(Integer allowLeaveTimes) {
		this.allowLeaveTimes = allowLeaveTimes;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public List<Integer> getAssignedUserIdList() {
		return assignedUserIdList;
	}

	public void setAssignedUserIdList(List<Integer> assignedUserIdList) {
		this.assignedUserIdList = assignedUserIdList;
	}

	public List<ScoreMarkerVo> getScoreMarkerList() {
		return scoreMarkerList;
	}

	public void setScoreMarkerList(List<ScoreMarkerVo> scoreMarkerList) {
		this.scoreMarkerList = scoreMarkerList;
	}

	public List<ExamAssignBean> getAssignInfoList() {
		return assignInfoList;
	}

	public void setAssignInfoList(List<ExamAssignBean> assignInfoList) {
		this.assignInfoList = assignInfoList;
	}
	
	public Integer getIsAnswerPublic() {
		return isAnswerPublic;
	}

	public void setIsAnswerPublic(Integer isAnswerPublic) {
		this.isAnswerPublic = isAnswerPublic;
	}

	public Integer getIsScorePublic() {
		return isScorePublic;
	}

	public void setIsScorePublic(Integer isScorePublic) {
		this.isScorePublic = isScorePublic;
	}

	public Integer getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Integer isPublished) {
		this.isPublished = isPublished;
	}

	public Integer getIsMarked() {
		return isMarked;
	}

	public void setIsMarked(Integer isMarked) {
		this.isMarked = isMarked;
	}

	public Integer getIsAntiCheating() {
		return isAntiCheating;
	}

	public void setIsAntiCheating(Integer isAntiCheating) {
		this.isAntiCheating = isAntiCheating;
	}

	public Integer getIsAutoMarking() {
		return isAutoMarking;
	}

	public void setIsAutoMarking(Integer isAutoMarking) {
		this.isAutoMarking = isAutoMarking;
	}
	
	public String getScoreMarkerName() {
		return scoreMarkerName;
	}

	public void setScoreMarkerName(String scoreMarkerName) {
		this.scoreMarkerName = scoreMarkerName;
	}
	
	public List<ManageUserBean> getUserList() {
		return userList;
	}

	public void setUserList(List<ManageUserBean> userList) {
		this.userList = userList;
	}

	/**
	 * Method name: getExamType <BR>
	 * Description: 考试所属的枚举类型 <BR>
	 * Remark: <BR>
	 * 
	 * @return ExamType<BR>
	 */
	public ExamType getExamType() {
		return ExamType.getExamType(getType());
	}

	/**
	 * Method name: getExamRelationType <BR>
	 * Description: 获取考试关系类型（用于ExamAssign表的类别标示） <BR>
	 * Remark: <BR>
	 * 
	 * @return Integer<BR>
	 */
	public Integer getExamRelationType() {
		return ExamRelationType.COMMON;
	}

	public Integer getSubmitDuration() {
		return submitDuration;
	}

	public void setSubmitDuration(Integer submitDuration) {
		this.submitDuration = submitDuration;
	}
	
}
