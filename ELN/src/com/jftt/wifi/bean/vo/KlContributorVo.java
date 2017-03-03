/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KlContributorVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-9-4        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * @author JFTT)chenrui
 * class name:KlContributorVo <BR>
 * class description: 学员知识-突出贡献者pageVo <BR>
 * Remark: <BR>
 * @version 1.00 2015-9-4
 * @author JFTT)chenrui
 */
public class KlContributorVo {
	private int createUserId;//知识创建人id
	private String userName;//创建人名称
	private String headImg;//头像
	private int orderNum;//序号
	private int counts;//贡献数量
	public int getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	
}
