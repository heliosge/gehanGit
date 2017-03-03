/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: LearnPlanStageCourseVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-31        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.Date;

/**
 * class name:LearnPlanStageCourseVo <BR>
 * class description: 学习计划阶段课程vo（查看计划页面展示用） <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-31
 * @author JFTT)ShenHaijun
 */
public class LearnPlanStageCourseVo {
	private Integer id;//课程id
	private String code;//课程编号
	private String name;//课程名称
	private Date beginTime;//学习开始时间
	private Date endTime;//学习结束时间
	private Integer learnProcess;//课程学习进度（1：进行中 2：已完成）
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getLearnProcess() {
		return learnProcess;
	}
	public void setLearnProcess(Integer learnProcess) {
		this.learnProcess = learnProcess;
	}
}
