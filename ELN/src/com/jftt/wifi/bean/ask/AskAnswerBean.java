/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: AskAnswerBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月17日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.ask;

import java.util.Date;

/**
 * class name:AskAnswerBean <BR>
 * class description: 问问回答bean <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月17日
 * @author JFTT)ShenHaijun
 */
public class AskAnswerBean {
	private Integer id;//回答id
	private Integer questionId;//问题id（关联问问表id）
	private Integer replyerType;//回答人类型（1:普联管理员回答 2：企业管理员回答）
	private String content;//回答内容
	private String platformResources;//平台资源（存放多个平台资源，以逗号分割）
	private String localFiles;//本地文件（存放多个本地文件，以逗号分割）
	private Integer isAnonymous;//是否匿名（1:匿名 2：不匿名（默认））
	private Integer replyerId;//回答人id
	private String replyerName;//回答人姓名
	private String createTime;//回答时间
	private Integer isThematic;//是否专题问答答案（1：普通回答 2：专题回答）
	private Integer isShield;//是否屏蔽答案（1:屏蔽 2：未屏蔽（默认））
	private Integer isSatisfactory;//是否满意答案（1：满意答案 2：不是满意答案（默认））
	private Integer isDelete;//是否删除
	private Integer companyId;//公司id
	private Integer subCompanyId;//子公司id
	private String satisfactTime;//采为满意答案时间
	private String resourceNames;//资源文件名称
	private String fileNames;//本地文件名称
	
	public String getResourceNames() {
		return resourceNames;
	}
	public void setResourceNames(String resourceNames) {
		this.resourceNames = resourceNames;
	}
	public String getFileNames() {
		return fileNames;
	}
	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Integer getReplyerType() {
		return replyerType;
	}
	public void setReplyerType(Integer replyerType) {
		this.replyerType = replyerType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPlatformResources() {
		return platformResources;
	}
	public void setPlatformResources(String platformResources) {
		this.platformResources = platformResources;
	}
	public String getLocalFiles() {
		return localFiles;
	}
	public void setLocalFiles(String localFiles) {
		this.localFiles = localFiles;
	}
	public Integer getIsAnonymous() {
		return isAnonymous;
	}
	public void setIsAnonymous(Integer isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
	public Integer getReplyerId() {
		return replyerId;
	}
	public void setReplyerId(Integer replyerId) {
		this.replyerId = replyerId;
	}
	public String getReplyerName() {
		return replyerName;
	}
	public void setReplyerName(String replyerName) {
		this.replyerName = replyerName;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getIsThematic() {
		return isThematic;
	}
	public void setIsThematic(Integer isThematic) {
		this.isThematic = isThematic;
	}
	public Integer getIsShield() {
		return isShield;
	}
	public void setIsShield(Integer isShield) {
		this.isShield = isShield;
	}
	public Integer getIsSatisfactory() {
		return isSatisfactory;
	}
	public void setIsSatisfactory(Integer isSatisfactory) {
		this.isSatisfactory = isSatisfactory;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	public String getSatisfactTime() {
		return satisfactTime;
	}
	public void setSatisfactTime(String satisfactTime) {
		this.satisfactTime = satisfactTime;
	}
	
}
