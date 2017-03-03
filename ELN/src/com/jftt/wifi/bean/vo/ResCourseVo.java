/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ResCourseVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-21        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.Date;
import java.util.List;

/**
 * class name:ResCourseVo <BR>
 * class description: 页面展示用课程vo <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-21
 * @author JFTT)ShenHaijun
 */
public class ResCourseVo {
	private Integer id;//课程id
	private Integer recordId;//课程学习记录id
	private Integer categoryId;//课程体系id
	private Integer categoryOrType;//体系还是分类（1：体系，2：分类）
	private List<Integer> childCategoryIdList;//该课程体系（分类）下的所有子体系（子分类）列表
	private String name;//课程名称
	private String frontImage;//课程封面
	private String outline;//课程简介
	private String tags;//课程标签
	private Date createTime;//创建时间
	private Float averageScore;//该课程平均评分
	private Integer evaluatorNum;//该课程评分人数
	private Integer studentNum;//该课程学生人数
	private Integer wareNum;//课件总数
	private Integer examNum;//考试总数
	private Integer isCollect;//是否收藏
	private Integer collectUserCount;//收藏学生总数
	private Integer myEvaluatorScore;//我的评分
	private Integer evaluatorCount;//课程评论总数
	private Integer learnProcess;//学习状态
	private long fromNum;//分页起始条数
	private String page;//分页当前页
	private String pageSize;//分页每页大小
	private String courseDuration;//课程时长
	private String studiedDuration;//已学时长
	private String startLearnTime;//开始学习时间
	private String lastLearnTime;//最后学习时间
	private String learnDuration;//学习时长
	private Integer learnProcessPercent;//课程学习进度
	private String courseSource;//任务类型（课程来源），1：自选课程；2：岗位课程；3：群组课程；4：部门课程，以逗号分割
	
	public String getStartLearnTime() {
		return startLearnTime;
	}
	public void setStartLearnTime(String startLearnTime) {
		this.startLearnTime = startLearnTime;
	}
	public String getLastLearnTime() {
		return lastLearnTime;
	}
	public void setLastLearnTime(String lastLearnTime) {
		this.lastLearnTime = lastLearnTime;
	}
	public String getLearnDuration() {
		return learnDuration;
	}
	public void setLearnDuration(String learnDuration) {
		this.learnDuration = learnDuration;
	}
	public Integer getLearnProcessPercent() {
		return learnProcessPercent;
	}
	public void setLearnProcessPercent(Integer learnProcessPercent) {
		this.learnProcessPercent = learnProcessPercent;
	}
	public String getCourseSource() {
		return courseSource;
	}
	public void setCourseSource(String courseSource) {
		this.courseSource = courseSource;
	}
	public String getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}
	public String getStudiedDuration() {
		return studiedDuration;
	}
	public void setStudiedDuration(String studiedDuration) {
		this.studiedDuration = studiedDuration;
	}
	public List<Integer> getChildCategoryIdList() {
		return childCategoryIdList;
	}
	public void setChildCategoryIdList(List<Integer> childCategoryIdList) {
		this.childCategoryIdList = childCategoryIdList;
	}
	public Integer getCategoryOrType() {
		return categoryOrType;
	}
	public void setCategoryOrType(Integer categoryOrType) {
		this.categoryOrType = categoryOrType;
	}
	public long getFromNum() {
		return fromNum;
	}
	public void setFromNum(long fromNum) {
		this.fromNum = fromNum;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Float getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(Float averageScore) {
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
	public Integer getWareNum() {
		return wareNum;
	}
	public void setWareNum(Integer wareNum) {
		this.wareNum = wareNum;
	}
	public Integer getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}
	public Integer getCollectUserCount() {
		return collectUserCount;
	}
	public void setCollectUserCount(Integer collectUserCount) {
		this.collectUserCount = collectUserCount;
	}
	public Integer getExamNum() {
		return examNum;
	}
	public void setExamNum(Integer examNum) {
		this.examNum = examNum;
	}
	public Integer getMyEvaluatorScore() {
		return myEvaluatorScore;
	}
	public void setMyEvaluatorScore(Integer myEvaluatorScore) {
		this.myEvaluatorScore = myEvaluatorScore;
	}
	public Integer getEvaluatorCount() {
		return evaluatorCount;
	}
	public void setEvaluatorCount(Integer evaluatorCount) {
		this.evaluatorCount = evaluatorCount;
	}
	public String getOutline() {
		return outline;
	}
	public void setOutline(String outline) {
		this.outline = outline;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Integer getLearnProcess() {
		return learnProcess;
	}
	public void setLearnProcess(Integer learnProcess) {
		this.learnProcess = learnProcess;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	
	
}
