/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: AskTypeBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月17日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.ask;

import java.util.Date;

/**
 * class name:AskTypeBean <BR>
 * class description: 问问分类bean <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月17日
 * @author JFTT)ShenHaijun
 */
public class AskTypeBean {
	private Integer id;//分类id
	private String name;//分类名称
	private String description;//分类描述
	private Integer parentId;//父级id
	private Date createTime;//创建时间
	private Integer companyId;//公司id
	private Integer subCompanyId;//子公司id
	private Integer isDelete;//是否删除
	private Integer askCount;//该分类下问题数量
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getAskCount() {
		return askCount;
	}
	public void setAskCount(Integer askCount) {
		this.askCount = askCount;
	}
	
}
