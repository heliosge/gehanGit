/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamModifyScoreMemo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/29        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam;

import java.util.Date;

/**
 * class name:ExamModifyScoreMemo <BR>
 * class description: 修改考试分数Bean <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/29
 * @author JFTT)wangyifeng
 */
public class ExamModifyScoreMemo {
	/**
	 * 理由主键ID
	 */
	private Integer id;
	/**
	 * 考试分配表ID
	 */
	private Integer examAssignId;
	/**
	 * 理由类型
	 */
	private Integer type;
	/**
	 * 理由内容
	 */
	private String content;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExamAssignId() {
		return examAssignId;
	}

	public void setExamAssignId(Integer examAssignId) {
		this.examAssignId = examAssignId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
