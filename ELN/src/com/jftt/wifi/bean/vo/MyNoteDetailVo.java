/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyNoteDetailVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月31日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.Date;

/**
 * class name:MyNoteDetailVo <BR>
 * class description: 笔记详情vo <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月31日
 * @author JFTT)ShenHaijun
 */
public class MyNoteDetailVo {
	private Integer id;//课程笔记id
	private Integer userId;//用户id
	private String userName;//用户名
	private String userPhoto;//用户头像
	private Date createTime;//创建时间
	private String content;//笔记内容
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
