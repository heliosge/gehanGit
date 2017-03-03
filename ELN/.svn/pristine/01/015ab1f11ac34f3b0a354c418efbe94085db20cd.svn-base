/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: InvestigationVoBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-12-3        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.questionnaire;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.Assert;

import com.jftt.wifi.bean.exam.enumtype.ExamState;

/**
 * @author JFTT)zhangchen<BR>
 * class name:InvestigationVoBean <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-3
 */
public class InvestigationVoBean {
	/**
	 * 调查主键ID
	 */
	private Integer id;
	/**  
	 * 参与ID
	 */
	private Integer assignId;
	/**  
	 * 用户ID
	 */
	private Integer userId;
	/**  
	 * 问 卷ID
	 */
	private Integer questionnaireId;
	/**  
	 * 调查标题
	 */
	private String title;
	/**  
	 * 调查开始时间 yyyy-MM-dd
	 */
	private String beginTime;
	/**  
	 * 调查结束时间 yyyy-MM-dd
	 */
	private String endTime;
	/**  
	 * 是否公开结果，0-否 1-是
	 */
	private Integer isPublic;
	/**  
	 * 是否发布，0-否 1-是
	 */
	private Integer isPublished;
	/**  
	 *  发布时间
	 */
	private String publishTime;
	/**  
	 * 是否参与调查 
	 */
	private Integer status;
	/**  
	 * 参与总人数
	 */
	private Integer totalNum;
	/**  
	 * 已经完成调查人数
	 */
	private Integer intendNum;
	/**  
	 * 调查目的
	 */
	private String aim;
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getIsPeriod <BR>
	 * Description: 判断是否在调研期 <BR>
	 * Remark: <BR>
	 * @return
	 * @throws ParseException  Integer<BR>
	 */
	public Integer getIsPeriod() throws ParseException {
		Assert.notNull(getBeginTime());
		Integer result = null;
		Date curDate = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(getEndTime() != null && !"".equals(getEndTime())){
			if(curDate.before(format.parse(getEndTime())) && curDate.after(format.parse(getBeginTime()))) {
				result = 1;
			}else{
				result = 0;
			}
		}else{
			if(curDate.after(format.parse(getBeginTime()))){
				result = 1;
			}else{
				result = 0;
			}
			
		}
		return result;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getState <BR>
	 * Description: 调查状态 1-未开始 2-进行中 3-已结束 <BR>
	 * Remark: <BR>
	 * @return
	 * @throws ParseException  Integer<BR>
	 */
	public Integer getState() throws ParseException {
		Assert.notNull(getBeginTime());
		Integer result = null;
		Date curDate = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(getEndTime() != null && !"".equals(getEndTime())){
			if(curDate.before(format.parse(getBeginTime()))) {
				result = 1;
			}else if(curDate.after(format.parse(getEndTime()))){
				result = 3;
			}else{
				result = 2;
			}
		}else{
			if(curDate.before(format.parse(getBeginTime()))){
				result = 1;
			}else{
				result = 2;
			}
		}
		return result;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(Integer questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public Integer getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Integer isPublished) {
		this.isPublished = isPublished;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAssignId() {
		return assignId;
	}

	public void setAssignId(Integer assignId) {
		this.assignId = assignId;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getIntendNum() {
		return intendNum;
	}

	public void setIntendNum(Integer intendNum) {
		this.intendNum = intendNum;
	}

	public String getAim() {
		return aim;
	}

	public void setAim(String aim) {
		this.aim = aim;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	

}
