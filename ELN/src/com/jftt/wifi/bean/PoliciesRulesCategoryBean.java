/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PoliciesRulesCategory.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-2-17        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean;

import java.util.Date;

/**
 * class name:PoliciesRulesCategory <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2016-2-17
 * @author JFTT)chenrui
 */
public class PoliciesRulesCategoryBean {

	private Integer id;
	private Integer parentId;
	private String name;
	private Date createTime;
	private Date updateTime;
	private Integer isDelete;
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
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
}
