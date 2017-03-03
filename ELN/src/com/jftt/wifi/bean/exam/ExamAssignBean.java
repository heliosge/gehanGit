/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamAssignBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/29        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam;

import java.util.Date;
import java.util.List;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.enumtype.UserExamState;

/**
 * class name:ExamAssignBean <BR>
 * class description: 考试分配情况Bean <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/29
 * @author JFTT)wangyifeng
 */
public class ExamAssignBean {
	/**
	 * 考试分配情况主键ID
	 */
	private Integer id;
	/**
	 * 考试类型
	 */
	private Integer relationType;
	/**
	 * 考试类型关联ID
	 */
	private Integer relationId;
	/**
	 * 试卷ID
	 */
	private Integer paperId;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 考试状态
	 */
	private Integer examStatus;
	/**
	 * 考试开始时间
	 */
	private Date examBeginTime;
	/**
	 * 考试提交时间
	 */
	private Date examSubmitTime;
	/**
	 * 考试次数
	 */
	private Integer testTimes;
	/**
	 * 是否通过
	 */
	private Boolean isPassed;
	/**
	 * 通过考试时，获得的分数
	 */
	private Integer passScore;
	/**
	 * 是否已批阅
	 */
	private Boolean isMarked;
	/**
	 * 获得分数
	 */
	private Integer score;
	/**
	 * 是否修改过成绩
	 */
	private Boolean isScoreModified;
	/**  
	 * 修改成绩理由
	 */
	private String modifyReason;
	/**
	 * 剩余考试时间（秒）
	 */
	private Integer remainingTime;
	/**
	 * 离开考试次数
	 */
	private Integer leaveTimes;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 用户姓名
	 */
	private String name;
	/**  
	 * 用户部门名称
	 */
	private String departmentName;
	/**  
	 * 用户岗位名称
	 */
	private String postName;
	/**  
	 * 身份证
	 */
	private String id_card;
	/**
	 * 排名
	 */
	private Integer rank;
	/**  
	 * 用户答题列表
	 */
	private Integer isAttend;//是否能参加考试 0 否 1 是
	private List<ExamUserAnswerBean> userAnswerList;
	
	private List<ManageUserBean> userList;//用户信息
	/**  
	 * 用户名
	 */
	private String userName;

	public static ExamAssignBean generateAssignInfo(ExamScheduleBean exam,Integer userId, Integer passScore) {
		// 以下被注释的属性是初始化时的非必须属性
		ExamAssignBean result = new ExamAssignBean();
		// result.setId(Integer);
		result.setRelationType(exam.getExamRelationType());
		result.setRelationId(exam.getId());
		result.setPaperId(exam.getPaperId());
		result.setUserId(userId);
		result.setExamStatus(UserExamState.NOT_START);
		// result.setExamBeginTime(Date);
		// result.setExamSubmitTime(Date);
		result.setTestTimes(0);
		// result.setIsPassed(Boolean);
		result.setPassScore(passScore);
		result.setIsMarked(false);
		result.setScore(0);
		// result.setIsScoreModified(Boolean);
		if(exam.getDuration() != null){
			result.setRemainingTime(exam.getDuration()*60);
		}
		// result.setLeaveTimes(Integer);
		// result.setUpdateTime(Date);
		// result.setName(String);
		// result.setRank(Integer);
		return result;
	}
	
	public Integer getIsAttend() {
		return isAttend;
	}

	public void setIsAttend(Integer isAttend) {
		this.isAttend = isAttend;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRelationType() {
		return relationType;
	}

	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(Integer examStatus) {
		this.examStatus = examStatus;
	}

	public Date getExamBeginTime() {
		return examBeginTime;
	}

	public void setExamBeginTime(Date examBeginTime) {
		this.examBeginTime = examBeginTime;
	}

	public Date getExamSubmitTime() {
		return examSubmitTime;
	}

	public void setExamSubmitTime(Date examSubmitTime) {
		this.examSubmitTime = examSubmitTime;
	}

	public Integer getTestTimes() {
		return testTimes;
	}

	/**
	 * Method name: getIsAttended <BR>
	 * Description: 根据参考次数是否>0来判断是否参加了考试 <BR>
	 * Remark: <BR>
	 * 
	 * @return Boolean<BR>
	 */
	public Boolean getIsAttended() {
		return testTimes != null && testTimes > 0;
	}

	public void setTestTimes(Integer testTimes) {
		this.testTimes = testTimes;
	}

	public Boolean getIsPassed() {
		return isPassed;
	}

	public void setIsPassed(Boolean isPassed) {
		this.isPassed = isPassed;
	}

	public Integer getPassScore() {
		return passScore;
	}

	public void setPassScore(Integer passScore) {
		this.passScore = passScore;
	}

	public Boolean getIsMarked() {
		return isMarked;
	}

	public void setIsMarked(Boolean isMarked) {
		this.isMarked = isMarked;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Boolean getIsScoreModified() {
		return isScoreModified;
	}

	public void setIsScoreModified(Boolean isScoreModified) {
		this.isScoreModified = isScoreModified;
	}

	public Integer getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(Integer remainingTime) {
		this.remainingTime = remainingTime;
	}

	public Integer getLeaveTimes() {
		return leaveTimes;
	}

	public void setLeaveTimes(Integer leaveTimes) {
		this.leaveTimes = leaveTimes;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getModifyReason() {
		return modifyReason;
	}

	public void setModifyReason(String modifyReason) {
		this.modifyReason = modifyReason;
	}

	public List<ExamUserAnswerBean> getUserAnswerList() {
		return userAnswerList;
	}

	public void setUserAnswerList(List<ExamUserAnswerBean> userAnswerList) {
		this.userAnswerList = userAnswerList;
	}

	public List<ManageUserBean> getUserList() {
		return userList;
	}

	public void setUserList(List<ManageUserBean> userList) {
		this.userList = userList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	
	
	

}
