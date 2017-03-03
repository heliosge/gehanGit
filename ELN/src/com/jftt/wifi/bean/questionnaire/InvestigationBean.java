/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: Investigation.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-18        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.questionnaire;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.Assert;

import com.jftt.wifi.bean.ManageUserBean;

/**
 * @author JFTT)zhangchen<BR>
 * class name:Investigation <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-18
 */
/**
 * @author JFTT)zhangchen<BR>
 * class name:InvestigationBean <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-2
 */
public class InvestigationBean {
	/**
	 * 调查主键ID
	 */
	private Integer id;
	/**  
	 * 问 卷ID
	 */
	private Integer questionnaireId;
	/**  
	 * 调查标题
	 */
	private String title;
	/**  
	 * 调查目的
	 */
	private String aim;
	/**  
	 * 调查开始时间 yyyy-MM-dd
	 */
	private String beginTime;
	/**  
	 * 调查结束时间 yyyy-MM-dd
	 */
	private String endTime;
	/**  
	 * 参与者类型
		1-全员,不需要存用户ID
		2-组织架构，用户ID存入questionnaire_assign表中
	 */
	private Integer intendType;
	/**  
	 * 是否公开结果，0-否 1-是
	 */
	private Integer isPublic;
	/**  
	 * 是否发布，0-否 1-是
	 */
	private Integer isPublished;
	/**
	 * 创建者ID
	 */
	private Integer createUserId;
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**  
	 *  发布时间
	 */
	private String publishTime;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 是否删除
	 */
	private Integer isDeleted;
	/**
	 * 分公司ID
	 */
	private Integer subCompanyId;
	/**
	 * 公司ID
	 */
	private Integer companyId;
	/**  
	 * 参与人员
	 */
	private List<ManageUserBean> userList = new ArrayList<ManageUserBean>();
	/**  
	 * 问卷名称
	 */
	private String questionnaireName;
	/**  
	 * 保存类型，1-保存并发布 2、保存
	 */
	private Integer modifyType;
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
	public String getAim() {
		return aim;
	}
	public void setAim(String aim) {
		this.aim = aim;
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
	public Integer getIntendType() {
		return intendType;
	}
	public void setIntendType(Integer intendType) {
		this.intendType = intendType;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Integer getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public List<ManageUserBean> getUserList() {
		return userList;
	}
	public void setUserList(List<ManageUserBean> userList) {
		this.userList = userList;
	}
	public String getQuestionnaireName() {
		return questionnaireName;
	}
	public void setQuestionnaireName(String questionnaireName) {
		this.questionnaireName = questionnaireName;
	}
	public Integer getModifyType() {
		return modifyType;
	}
	public void setModifyType(Integer modifyType) {
		this.modifyType = modifyType;
	}
	

}
