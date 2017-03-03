/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: CategorySearchOptionBaseVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/30        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam.vo;

/**
 * class name:CategorySearchOptionBaseVo <BR>
 * class description: 分类信息查询条件基类Vo <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/30
 * @author JFTT)wangyifeng
 */
public class CategorySearchOptionBaseVo {
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
}
