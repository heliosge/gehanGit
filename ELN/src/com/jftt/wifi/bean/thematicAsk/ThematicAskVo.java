/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ThematicAskVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年12月9日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.thematicAsk;

import java.util.Date;

/**
 * class name:ThematicAskVo <BR>
 * class description: 专题问答vo <BR>
 * Remark: <BR>
 * @version 1.00 2015年12月9日
 * @author JFTT)ShenHaijun
 */
public class ThematicAskVo {
	private Integer id;//专题问答id
	private String coverPic;//封面图片
	private String title;//标题
	private String satisfactoryAnswer;//满意答案
	private String replyerName;//回答人姓名
	private Date replyTime;//回答时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCoverPic() {
		return coverPic;
	}
	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSatisfactoryAnswer() {
		return satisfactoryAnswer;
	}
	public void setSatisfactoryAnswer(String satisfactoryAnswer) {
		this.satisfactoryAnswer = satisfactoryAnswer;
	}
	public String getReplyerName() {
		return replyerName;
	}
	public void setReplyerName(String replyerName) {
		this.replyerName = replyerName;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
}
