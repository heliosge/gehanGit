/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: LearnPlanVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-31        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * class name:LearnPlanVo <BR>
 * class description: 学习计划页面展示用vo <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-31
 * @author JFTT)ShenHaijun
 */
public class LearnPlanVo {
	private Integer id;//学习计划id
	private String name;//学习计划名称
	private String coverImage;//计划封面
	private String type;//计划类型
	private Integer status;//计划状态（1：进行中 2：已完成）
	private String nameOrType;//计划名称或计划类别（查询条件）
	private Integer userId;//用户id
	private Integer studyPercent;//学习进度
	
	public Integer getStudyPercent() {
		return studyPercent;
	}
	public void setStudyPercent(Integer studyPercent) {
		this.studyPercent = studyPercent;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getNameOrType() {
		return nameOrType;
	}
	public void setNameOrType(String nameOrType) {
		this.nameOrType = nameOrType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
