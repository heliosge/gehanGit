/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: InformationBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月17日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * class name:资讯信息bean
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月17日
 * @author JFTT)liucaibao
 */
public class InformationBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7124312780468392305L;
	
	/**  
	 * 资讯id
	 */
	private int infoId;
	
	/**  
	 * 当前上传者的公司id
	 */
	private int companyId;
	
	/**  
	 * 当前资讯的名称
	 */
	private String infoName;
	
	/**  
	 * 当前资讯的内容
	 */
	private String infoContent;
	
	/**  
	 * 是否为banner，设置横幅
	 */
	private int isBanner;
	
	/**  
	 * banner文件的名称
	 */
	private String bannerFileName;
	
	/**  
	 * banner文件的路径
	 */
	private String bannerFilePath;
	
	private String isPublish;
	/**  
	 * 用户id
	 */
	private int userId;
	
	private String userIds;

	/**  
	 * 用户名称
	 */
	private String userName;
	
	/**  
	 * 开始时间
	 */
	private String beginTime;
	
	/**  
	 * 结束时间
	 */
	private String endTime;
	
	private String roleType;//1代表管理端的列表   2 代表学员端的列表 
	
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}

	private Date createTime;
	private String createUserId;
	
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public int getFromNum() {
		return fromNum;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public void setFromNum(int fromNum) {
		this.fromNum = fromNum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	private int fromNum;
	private int page;
	
	/**  
	 * 是否删除，默认为不删除
	 */
	private int isDelete = 0 ;
	
	

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}


	public String getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}

	public int getIsBanner() {
		return isBanner;
	}

	public void setIsBanner(int isBanner) {
		this.isBanner = isBanner;
	}

	public String getBannerFileName() {
		return bannerFileName;
	}

	public void setBannerFileName(String bannerFileName) {
		this.bannerFileName = bannerFileName;
	}

	public String getBannerFilePath() {
		return bannerFilePath;
	}

	public void setBannerFilePath(String bannerFilePath) {
		this.bannerFilePath = bannerFilePath;
	}


	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public int getInfoId() {
		return infoId;
	}

	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new ReflectionToStringBuilder(this).toString();
	}
}
