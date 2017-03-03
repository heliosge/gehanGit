/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: SearchOptionBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-19        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.questionnaire;

import java.util.ArrayList;
import java.util.List;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.knowledge_contest.SearchOptionBaseWithPagination;

/**
 * @author JFTT)zhangchen<BR>
 * class name:SearchOptionBean <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-19
 */
/**
 * @author JFTT)zhangchen<BR>
 * class name:SearchOptionBean <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-3
 */
public class SearchOptionBean extends SearchOptionBaseWithPagination{
	/**  
	 * 调查主键ID
	 */
	private Integer id;
	/**
	 * 分公司ID
	 */
	private Integer subCompanyId;
	/**
	 * 公司ID
	 */
	private Integer companyId;
	/**  
	 * 用户ID
	 */
	private String userId;
	/**  
	 * 问卷分类IDStr
	 */
	private String categoryIdListStr;
	/**  
	 * 问卷分类ID
	 */
	private Integer categoryId;
	/**  
	 * 创建用户ID
	 */
	private Integer createUserId;
	/**  
	 * 问卷名称
	 */
	private String name;
	/**  
	 * 是否禁用 0-否 1-是
	 */
	private Integer isEnabled;
	/**  
	 * 调查报告标题
	 */
	private String title;
	/**  
	 * 是否在调研期
	 */
	private Integer isPeriod;
	/**  
	 * 开始时间
	 */
	private String beginTime;
	/**  
	 * 结束时间
	 */
	private String endTime;
	/**  
	 * 参与状态
	 */
	private Integer status;
	/**  
	 * 结果是否公开
	 */
	private Integer isPublic;
	/**  
	 * 参与ID
	 */
	private Integer assignId;
	/**  
	 * 调查状态 1-未开始 2-进行中 3-已结束
	 */
	private Integer state;
	/**  
	 * 用户姓名
	 */
	private String username;
	/**  
	 * 部门
	 */
	private String department;
	/**  
	 * 职位
	 */
	private String post;
	/**  
	 * 用户list
	 */
	private List<ManageUserBean> userList = new ArrayList<ManageUserBean>();
	/**  
	 * 分配类型，1、调查问卷 2、培训问卷
	 */
	private Integer type;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCategoryIdListStr() {
		return categoryIdListStr;
	}
	public void setCategoryIdListStr(String categoryIdListStr) {
		this.categoryIdListStr = categoryIdListStr;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getIsPeriod() {
		return isPeriod;
	}
	public void setIsPeriod(Integer isPeriod) {
		this.isPeriod = isPeriod;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	public Integer getAssignId() {
		return assignId;
	}
	public void setAssignId(Integer assignId) {
		this.assignId = assignId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public List<ManageUserBean> getUserList() {
		return userList;
	}
	public void setUserList(List<ManageUserBean> userList) {
		this.userList = userList;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
