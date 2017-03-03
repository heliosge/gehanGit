/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: IndexShowVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-10        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * class name:IndexShowVo <BR>
 * class description: 学员首页运维栏展现vo <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-10
 * @author JFTT)ShenHaijun
 */
public class IndexShowVo {
	private Integer spreadId;//推广id（可以是专题、课程、大赛）
	private String coverImg;//封面
	private String name;//名称
	private Integer order;//序号（在运维栏中的位置）
	private Integer type;//推广中的类型
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSpreadId() {
		return spreadId;
	}
	public void setSpreadId(Integer spreadId) {
		this.spreadId = spreadId;
	}
	public String getCoverImg() {
		return coverImg;
	}
	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
}
