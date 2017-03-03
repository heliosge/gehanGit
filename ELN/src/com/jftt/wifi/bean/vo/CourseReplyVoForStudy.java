/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseReplyVoForStudy.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月29日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.Date;

/**
 * class name:CourseReplyVoForStudy <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月29日
 * @author JFTT)ShenHaijun
 */
public class CourseReplyVoForStudy {
	private Integer id;//回答id
	private Integer userId;//回答人id
	private String userName;//回答人姓名
	private String content;//回答内容
	private Date answertime;//回答时间
	private String replyText;//回答的富文本
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getAnswertime() {
		return answertime;
	}
	public void setAnswertime(Date answertime) {
		this.answertime = answertime;
	}
	public String getReplyText() {
		return replyText;
	}
	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}
}
