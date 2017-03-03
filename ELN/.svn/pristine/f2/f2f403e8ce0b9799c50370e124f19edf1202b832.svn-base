/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireAssign.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-18        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.questionnaire;


/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireAssign <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-18
 */
public class InvestigationAssignBean extends UserBaseBean{
	/**
	 * 参与主键ID
	 */
	private Integer id;
	/**  
	 * 调查ID
	 */
	private Integer investigationId;
	/**  
	 * 参与用户ID
	 */
	private Integer userId;
	/**  
	 * 状态，1-未提交 2-已提交
	 */
	private Integer status;
	/**  
	 * 调查提交时间
	 */
	private String submitTime;
	/**  
	 * 最后更新时间
	 */
	private String updateTime;
	/**  
	 * 分配类型，1、调查问卷 2、培训问卷
	 */
	private Integer type;
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: generateAssignInfo <BR>
	 * Description: 生成参与调查问卷人员实体 <BR>
	 * Remark: <BR>
	 * @param invest
	 * @param userId
	 * @return  InvestigationAssignBean<BR>
	 */
	public static InvestigationAssignBean generateAssignInfo(InvestigationBean invest,Integer userId) {
		// 以下被注释的属性是初始化时的非必须属性
		InvestigationAssignBean result = new InvestigationAssignBean();
		result.setInvestigationId(invest.getId());
		result.setUserId(userId);
		result.setStatus(1);//未提交
		result.setType(1);//分配类型为：调查问卷
		return result;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getInvestigationId() {
		return investigationId;
	}
	public void setInvestigationId(Integer investigationId) {
		this.investigationId = investigationId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	

}
