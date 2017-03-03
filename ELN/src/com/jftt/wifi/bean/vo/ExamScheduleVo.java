/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamcheduleVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-30        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.List;

import com.jftt.wifi.bean.exam.ExamOrganizingRuleBean;
import com.jftt.wifi.bean.exam.ExamUserAnswerBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.exam.QuestionBean;
import com.jftt.wifi.bean.exam.vo.ExamOrganizingRuleVo;

/**
 * @author JFTT)zhangchen
 * class name:ExamcheduleVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-30
 */
public class ExamScheduleVo {
	private int id;//考试信息ID
	private String title;//考试名称
	private int paperId;//试卷ID
	private int duration;//考试时长
	private String beginTime;//考试开始时间
	private String endTime;//考试结束时间
	private String passScore;//及格分
	private String passScorePercent;
	private int totalScore;//试卷总分
	private int totalQuestionNum;//试卷题目总数
	private int displayMode;//试卷显示方式1  整卷显示2  单题可逆3  单题不可逆
	private String notice;//考前须知
	private String paperName;//试卷名称
	private int allowTimes;//允许考试次数
	private int isScorePublic;//成绩是否公开
	private int randomOrderMode;//随机打乱模式1题目顺序2选项顺序3题目+选项顺序
	private int isAutoMarking;//是否自动批阅(包含主观题时是否自动批阅)
	private int isAntiCheating;//是否开启防作弊
	private int isAnswerPublic;//是否公开答案
	private PaperBean paperBean;//试卷
	private String scoreMarker;//批阅人ID（逗号分隔)
	private String scoreMarkerName;//批阅人姓名（逗号分隔）
	private int allowLeaveTimes;//允许离开考试次数
	private int organizingMode;//组卷方式
	private int examType;//考试类型
	private String displayType;//试题类型，以逗号拼接
	
	//private List<ExamOrganizingRuleVo> ruleList;//组卷规则
	private List<ExamOrganizingRuleBean> ruleList;//组卷规则
	
	
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	public int getExamType() {
		return examType;
	}
	public void setExamType(int examType) {
		this.examType = examType;
	}
	/*public List<ExamOrganizingRuleVo> getRuleList() {
		return ruleList;
	}
	public void setRuleList(List<ExamOrganizingRuleVo> ruleList) {
		this.ruleList = ruleList;
	}*/
	
	public int getOrganizingMode() {
		return organizingMode;
	}
	public List<ExamOrganizingRuleBean> getRuleList() {
		return ruleList;
	}
	public void setRuleList(List<ExamOrganizingRuleBean> ruleList) {
		this.ruleList = ruleList;
	}
	public void setOrganizingMode(int organizingMode) {
		this.organizingMode = organizingMode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getScoreMarkerName() {
		return scoreMarkerName;
	}
	public void setScoreMarkerName(String scoreMarkerName) {
		this.scoreMarkerName = scoreMarkerName;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
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
	public String getPassScore() {
		return passScore;
	}
	public void setPassScore(String passScore) {
		this.passScore = passScore;
	}
	public String getPassScorePercent() {
		return passScorePercent;
	}
	public void setPassScorePercent(String passScorePercent) {
		this.passScorePercent = passScorePercent;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public int getTotalQuestionNum() {
		return totalQuestionNum;
	}
	public void setTotalQuestionNum(int totalQuestionNum) {
		this.totalQuestionNum = totalQuestionNum;
	}
	public PaperBean getPaperBean() {
		return paperBean;
	}
	public void setPaperBean(PaperBean paperBean) {
		this.paperBean = paperBean;
	}
	public int getDisplayMode() {
		return displayMode;
	}
	public void setDisplayMode(int displayMode) {
		this.displayMode = displayMode;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public int getAllowTimes() {
		return allowTimes;
	}
	public void setAllowTimes(int allowTimes) {
		this.allowTimes = allowTimes;
	}
	public int getIsScorePublic() {
		return isScorePublic;
	}
	public void setIsScorePublic(int isScorePublic) {
		this.isScorePublic = isScorePublic;
	}
	public int getRandomOrderMode() {
		return randomOrderMode;
	}
	public void setRandomOrderMode(int randomOrderMode) {
		this.randomOrderMode = randomOrderMode;
	}
	public int getIsAutoMarking() {
		return isAutoMarking;
	}
	public void setIsAutoMarking(int isAutoMarking) {
		this.isAutoMarking = isAutoMarking;
	}
	public int getIsAntiCheating() {
		return isAntiCheating;
	}
	public void setIsAntiCheating(int isAntiCheating) {
		this.isAntiCheating = isAntiCheating;
	}
	public int getIsAnswerPublic() {
		return isAnswerPublic;
	}
	public void setIsAnswerPublic(int isAnswerPublic) {
		this.isAnswerPublic = isAnswerPublic;
	}
	public String getScoreMarker() {
		return scoreMarker;
	}
	public void setScoreMarker(String scoreMarker) {
		this.scoreMarker = scoreMarker;
	}
	public int getAllowLeaveTimes() {
		return allowLeaveTimes;
	}
	public void setAllowLeaveTimes(int allowLeaveTimes) {
		this.allowLeaveTimes = allowLeaveTimes;
	}
	
	
}
