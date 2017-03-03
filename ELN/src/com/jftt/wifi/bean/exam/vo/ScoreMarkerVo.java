/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ScoreMarkerVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/05        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam.vo;

/**
 * class name:ScoreMarkerVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/05
 * @author JFTT)wangyifeng
 */
public class ScoreMarkerVo {
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 用户姓名
	 */
	private String name;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
