/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: CategoryWithFullNameBaseVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/28        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam.vo;

/**
 * class name:CategoryWithFullNameBaseVo <BR>
 * class description: 带全名的分类信息Vo <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/28
 * @author JFTT)wangyifeng
 */
public class CategoryWithFullNameBaseVo {
	/**
	 * 试题分类主键ID
	 */
	private Integer id;
	/**
	 * 试题分类全名<br />
	 * 例：分类1/子分类2
	 */
	private String fullName;

	public CategoryWithFullNameBaseVo(Integer id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
