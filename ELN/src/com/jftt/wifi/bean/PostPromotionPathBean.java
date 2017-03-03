/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PostPromotionPathBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年9月10日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean;

import java.util.Date;

/**
 * class name:PostPromotionPathBean <BR>
 * class description: 晋升路径bean <BR>
 * Remark: <BR>
 * @version 1.00 2015年9月10日
 * @author JFTT)ShenHaijun
 */
public class PostPromotionPathBean {
	private Integer id;//晋升路径id
	private String name;//晋升路径名称
	private String description;//晋升路径描述
	private Integer isDisabled;//是否停用：1停用，0启用
	private Integer isDeleted;//是否删除（逻辑删除）：1逻辑删除，0非删除
	private Date updateTime;//最后更新时间
	private Integer companyId;//公司id
	private Integer subCompanyId;//子公司id
	
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
	public Integer getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
}
