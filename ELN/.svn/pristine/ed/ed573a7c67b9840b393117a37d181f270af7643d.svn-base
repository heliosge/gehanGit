/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: SearchOptionBaseWithPagination.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/13        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.knowledge_contest;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @author JFTT)wangyifeng
 * class name:SearchOptionBaseWithPagination <BR>
 * class description: 提供分页信息的查询条件 <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/13
 */
public class SearchOptionBaseWithPagination {

	/**  
	 * 分页大小
	 */
	private Integer pageSize;
	/**  
	 * 分页编号（from 1）
	 */
	private Integer pageNo;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getOffset <BR>
	 * Description: 获取偏移量，用于MySQL分页查询 <BR>
	 * Remark: <BR>
	 * @return  Integer<BR>
	 */
	public Integer getOffset() {
		if (getPageNo() != null && getPageNo() > 0 && getPageSize() != null) {
			return (getPageNo() - 1) * getPageSize();
		}
		return null;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
