/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MallSectionExamVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-27        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * class name:MallSectionExamVo <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-27
 * @author JFTT)chenrui
 */
public class MallSectionExamVo {
	private Integer id;
	private Integer examId;
	private String title;
	private Integer duration;//时长
	private Integer allowTimes;//允许考试
	private Integer passScorePercent;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getAllowTimes() {
		return allowTimes;
	}
	public void setAllowTimes(Integer allowTimes) {
		this.allowTimes = allowTimes;
	}
	public Integer getPassScorePercent() {
		return passScorePercent;
	}
	public void setPassScorePercent(Integer passScorePercent) {
		this.passScorePercent = passScorePercent;
	}
	
	
}
