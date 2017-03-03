/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:Ad.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2014/01/07           |  JFTT)wangjian     |  original version
 */
package com.jftt.wifi.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * ManageUserBean 用户表
 */
public class BaseMetaBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3439786354030521146L;
	
	private long id; 		         //主键ID
	private long parentId;           //
	private String metaKey;
	private String metaValue;
	private String descr;
	private int orderIndex;
	private int deleteFlag;
	
	private List<BaseMetaBean> metaList;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public String getMetaKey() {
		return metaKey;
	}
	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}
	public String getMetaValue() {
		return metaValue;
	}
	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public int getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public List<BaseMetaBean> getMetaList() {
		return metaList;
	}
	public void setMetaList(List<BaseMetaBean> metaList) {
		this.metaList = metaList;
	}

	@Override
	public String toString() {
	
		return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
