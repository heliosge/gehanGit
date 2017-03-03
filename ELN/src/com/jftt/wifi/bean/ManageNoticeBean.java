/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageNoticeBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月27日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.bean;

import java.util.Date;

/**
 * class name:ManageNoticeBean <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月27日
 * @author JFTT)Lu Yunlong
 */
public class ManageNoticeBean {
	private Integer id;//
	private String title;//标题
	private String content;//内容
	private Integer sendUserId;//发送人
	private String sendUserName;//发送人姓名
	private Date sendTime;//发送时间
	private Integer recUserId;//接收人
	private String recUserName;//接收人姓名
	private Integer isRead;//是否已读1：已读；2：未读；
	private Integer isSystem;//是否系统消息1：系统消息；2：站内信；
	private Integer sendDeleteFlag;//发送人员删除1：未删除；2：已删除；
	private Integer recDeleteFlag;//接收人员删除1：未删除；2：已删除；
	
	public ManageNoticeBean(){}
	
	public ManageNoticeBean(String title, String content, Integer sendUserId, Integer recUserId, Integer isSystem)
	{
		this.title = title;
		this.content = content;
		this.sendUserId = sendUserId;
		this.recUserId = recUserId;
		this.isSystem = isSystem;
	}
	
	public String getRecUserName() {
		return recUserName;
	}

	public void setRecUserName(String recUserName) {
		this.recUserName = recUserName;
	}

	public String getSendUserName() {
		return sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	public Integer getSendDeleteFlag() {
		return sendDeleteFlag;
	}
	public void setSendDeleteFlag(Integer sendDeleteFlag) {
		this.sendDeleteFlag = sendDeleteFlag;
	}
	public Integer getRecDeleteFlag() {
		return recDeleteFlag;
	}
	public void setRecDeleteFlag(Integer recDeleteFlag) {
		this.recDeleteFlag = recDeleteFlag;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(Integer sendUserId) {
		this.sendUserId = sendUserId;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getRecUserId() {
		return recUserId;
	}
	public void setRecUserId(Integer recUserId) {
		this.recUserId = recUserId;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public Integer getIsSystem() {
		return isSystem;
	}
	public void setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
	}
	

}
