/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: searchKlVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-28        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.Date;
import java.util.List;

import com.jftt.wifi.bean.ManageUserBean;

/**
 * class name:searchKlVo <BR>
 * class description: 我收藏、下载的知识列表搜索条件 <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015-7-28
 * @author JFTT)chenrui
 */
public class SearchKlVo {
	private String userId;//当前用户
	private String othersId;//他人的id
	private String status;//审批状态
	private int companyId;
	private int subCompanyId;
	private Integer isOpen;//公开程度 1公开,0不公开
	private String operateType;//操作类型
	private String searchName;// 知识名称
	private String categoryName;// 知识库分类
	private String fromUserName;// 上传者
	private String  startTime;// 开始时间
	private String endTime;// 结束时间
	private String page;
	private String pageSize;
	private long fromNum;
	
	private List<ManageUserBean> userList;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOthersId() {
		return othersId;
	}

	public void setOthersId(String othersId) {
		this.othersId = othersId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getSubCompanyId() {
		return subCompanyId;
	}

	public void setSubCompanyId(int subCompanyId) {
		this.subCompanyId = subCompanyId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public long getFromNum() {
		return fromNum;
	}

	public void setFromNum(long fromNum) {
		this.fromNum = fromNum;
	}

	public List<ManageUserBean> getUserList() {
		return userList;
	}

	public void setUserList(List<ManageUserBean> userList) {
		this.userList = userList;
	}

}
