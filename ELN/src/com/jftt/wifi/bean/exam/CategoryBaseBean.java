/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: CategoryBaseBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/28        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam;

import java.util.Date;

/**
 * class name:CategoryBaseBean <BR>
 * class description: 分类基类Bean <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/28
 * @author JFTT)wangyifeng
 */
public class CategoryBaseBean {
	/**
	 * 试题分类主键ID
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
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否删除
	 */
	private Boolean isDeleted;
	/**
	 * 分公司ID
	 */
	private Integer subCompanyId;
	/**
	 * 公司ID
	 */
	private Integer companyId;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
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
}
