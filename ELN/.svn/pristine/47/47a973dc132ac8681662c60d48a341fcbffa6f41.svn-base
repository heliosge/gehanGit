/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamRecorderVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.vo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.Assert;

import com.jftt.wifi.bean.exam.enumtype.ExamState;

/**
 * @author JFTT)zhangchen
 * class name:ExamRecorderVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 */
public class ExamRecorderVo {
	private int id;//考试主键ID
	private int paperId;//试卷ID
	private String paperName;//试卷名称
	private int userId;//用户ID
	private int examAssignId;//考试记录ID
	private String title;//考试名称
	private String beginTime;//考试开始时间
	private String endTime;//考试结束时间
	private int isScorePublic;//是否公开成绩
	private int isAnswerPublic;//是否公开答案
	private int examStatus;//个人考试状态1：未开始2：未提交3：已提交4：已批阅
	private int isPassed;//是否通过 0-未通过 1-已通过
	private int allowTimes;//允许考试次数
	private int testTimes;//个人考试次数
	private int score;//个人考试得分
	private int passScore;//及格得分
	private int passScorePercent;//考试通过百分比
	private int totalScore;//试卷总分
	private int totalnum;//考试总人数
	private String rownum;//个人考试排名
	private int isAutoMarking;//是否自动批阅(包含主观题时是否自动批阅)0-非自动批阅 1-自动批阅
	private int type;//考试种类：1普通考试，2赛程考试，3模拟考试，4学习地图测试，5课程章节测试（实际由课程部分管理）
	private int displayMode;//试卷显示方式1  整卷显示2  单题可逆3  单题不可逆
	private int duration;//考试时长
	private int organizingMode;//组卷方式 1标准组卷2随机组卷
	private int isPublished;//是否发布
	private int isScorePublished;//成绩是否发布
	private int isMarked;//是否已批阅
	private int isAntiCheating;//
	
	
	public int getIsAntiCheating() {
		return isAntiCheating;
	}
	public void setIsAntiCheating(int isAntiCheating) {
		this.isAntiCheating = isAntiCheating;
	}
	public int getIsMarked() {
		return isMarked;
	}
	public void setIsMarked(int isMarked) {
		this.isMarked = isMarked;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getIsScorePublic() {
		return isScorePublic;
	}
	public void setIsScorePublic(int isScorePublic) {
		this.isScorePublic = isScorePublic;
	}
	public int getExamStatus() {
		return examStatus;
	}
	public void setExamStatus(int examStatus) {
		this.examStatus = examStatus;
	}
	public int getIsPassed() {
		return isPassed;
	}
	public void setIsPassed(int isPassed) {
		this.isPassed = isPassed;
	}
	public int getAllowTimes() {
		return allowTimes;
	}
	public void setAllowTimes(int allowTimes) {
		this.allowTimes = allowTimes;
	}
	public int getTestTimes() {
		return testTimes;
	}
	public void setTestTimes(int testTimes) {
		this.testTimes = testTimes;
	}
	public int getPassScore() {
		return passScore;
	}
	public void setPassScore(int passScore) {
		this.passScore = passScore;
	}
	
	public int getPassScorePercent() {
		return passScorePercent;
	}
	public void setPassScorePercent(int passScorePercent) {
		this.passScorePercent = passScorePercent;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public int getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public int getIsAutoMarking() {
		return isAutoMarking;
	}
	public void setIsAutoMarking(int isAutoMarking) {
		this.isAutoMarking = isAutoMarking;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIsAnswerPublic() {
		return isAnswerPublic;
	}
	public void setIsAnswerPublic(int isAnswerPublic) {
		this.isAnswerPublic = isAnswerPublic;
	}
	public int getExamAssignId() {
		return examAssignId;
	}
	public void setExamAssignId(int examAssignId) {
		this.examAssignId = examAssignId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getDisplayMode() {
		return displayMode;
	}
	public void setDisplayMode(int displayMode) {
		this.displayMode = displayMode;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public int getOrganizingMode() {
		return organizingMode;
	}
	public void setOrganizingMode(int organizingMode) {
		this.organizingMode = organizingMode;
	}
	public int getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(int isPublished) {
		this.isPublished = isPublished;
	}
	
	public int getIsScorePublished() {
		return isScorePublished;
	}
	public void setIsScorePublished(int isScorePublished) {
		this.isScorePublished = isScorePublished;
	}
	
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamState <BR>
	 * Description: 获取考试状态 <BR>
	 * Remark: <BR>
	 * @return
	 * @throws ParseException  ExamState<BR>
	 */
	public ExamState getExamState() throws ParseException {
		Assert.notNull(getIsPublished());
		ExamState result = ExamState.ALL_STATE;
		if(getIsPublished() == 0){
			result = ExamState.NOT_PUBLISHED;
		}
		if (getType() == 1 && getIsPublished() == 1) {
			Assert.notNull(getBeginTime());
			Assert.notNull(getEndTime());
			Date curDate = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if (curDate.before(format.parse(getBeginTime()))) {
				result = ExamState.BEFORE_START;
			} else if (curDate.after(format.parse(getEndTime()))) {
				result = ExamState.FINISHED;
			} else {
				result = ExamState.PROCESSING;
			}
		}
		if (getType() == 3 && getIsPublished() == 1) {
			result = ExamState.PUBLISHED;
		}
		return result;
	}
	
}
