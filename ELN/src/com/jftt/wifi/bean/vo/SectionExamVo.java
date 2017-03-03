/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: SectionExamVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * class name:SectionExamVo <BR>
 * class description: 章节测试vo <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)ShenHaijun
 */
public class SectionExamVo {
	private Integer id;//试卷id
	private Integer rsExamId;//章节考试id
	private String title;//试卷标题
	private Integer totalTestDuration;//测试总时长
	private Integer testTimes;//测试次数
	private Integer permitTestTimes;//允许次数
	private Integer sectionId;//课程章节id
	private Integer examDuration;//分配的考试时长
	private Integer passPercent;//通过分数比例
	private Integer passScore;//及格分
	private Integer maxScore;//最高分
	private Integer totalScore;//总分
	
	public Integer getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}
	public Integer getPassScore() {
		return passScore;
	}
	public void setPassScore(Integer passScore) {
		this.passScore = passScore;
	}
	public Integer getPermitTestTimes() {
		return permitTestTimes;
	}
	public void setPermitTestTimes(Integer permitTestTimes) {
		this.permitTestTimes = permitTestTimes;
	}
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
	public Integer getTotalTestDuration() {
		return totalTestDuration;
	}
	public void setTotalTestDuration(Integer totalTestDuration) {
		this.totalTestDuration = totalTestDuration;
	}
	public Integer getTestTimes() {
		return testTimes;
	}
	public void setTestTimes(Integer testTimes) {
		this.testTimes = testTimes;
	}
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	public Integer getExamDuration() {
		return examDuration;
	}
	public void setExamDuration(Integer examDuration) {
		this.examDuration = examDuration;
	}
	public Integer getPassPercent() {
		return passPercent;
	}
	public void setPassPercent(Integer passPercent) {
		this.passPercent = passPercent;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public Integer getRsExamId() {
		return rsExamId;
	}
	public void setRsExamId(Integer rsExamId) {
		this.rsExamId = rsExamId;
	}
	
}
