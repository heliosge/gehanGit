/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageNoticeVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月13日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * class name:ManageNoticeVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月13日
 * @author JFTT)Lu Yunlong
 */
public class ManageNoticeVo {
	private Integer id;//
	private String title;//标题
	private Integer isRead;//是否已读1：已读；2：未读；
	private Integer isSystem;//是否系统消息1：系统消息；2：站内信；
	private String startTime;
	private String endTime;
	private Integer recDeleteFlag;//接收人员删除1：未删除；2：已删除；
	private Integer sendDeleteFlag;//发送人员删除1：未删除；2：已删除；
	private int page;
	private int pageSize;
	private int fromNum;
	private Integer sendUserId;//发送人
	private Integer recUserId;//接收人
	
	public Integer getSendDeleteFlag() {
		return sendDeleteFlag;
	}
	public void setSendDeleteFlag(Integer sendDeleteFlag) {
		this.sendDeleteFlag = sendDeleteFlag;
	}
	public Integer getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(Integer sendUserId) {
		this.sendUserId = sendUserId;
	}
	public Integer getRecUserId() {
		return recUserId;
	}
	public void setRecUserId(Integer recUserId) {
		this.recUserId = recUserId;
	}
	public Integer getRecDeleteFlag() {
		return recDeleteFlag;
	}
	public void setRecDeleteFlag(Integer recDeleteFlag) {
		this.recDeleteFlag = recDeleteFlag;
	}
	public int getFromNum() {
		return fromNum;
	}
	public void setFromNum(int fromNum) {
		this.fromNum = fromNum;
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
