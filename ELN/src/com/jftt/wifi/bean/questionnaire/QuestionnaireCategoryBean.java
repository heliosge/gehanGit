/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireCategoryBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-18        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.questionnaire;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireCategoryBean <BR>
 * class description: 问卷分类 <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-18
 */
public class QuestionnaireCategoryBean {
	/**
	 * 问卷分类主键ID
	 */
	private Integer id;
	/**
	 * 父分类ID
	 */
	private Integer parentId;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 分类描述
	 */
	private String description;
	/**
	 * 创建者ID
	 */
	private Integer createUserId;
	/**
	 * 创建时间
	 */
	private String createTime;
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
	 * 子试题分类
	 */
	private List<QuestionnaireCategoryBean> subCategoryList = new ArrayList<QuestionnaireCategoryBean>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
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
	public List<QuestionnaireCategoryBean> getSubCategoryList() {
		return subCategoryList;
	}
	public void setSubCategoryList(List<QuestionnaireCategoryBean> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}
	
	

}
