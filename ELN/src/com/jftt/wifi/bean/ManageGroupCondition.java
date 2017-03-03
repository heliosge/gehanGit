/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageGroupCondition.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年10月12日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.bean;

/**
 * class name:ManageGroupCondition <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年10月12日
 * @author JFTT)Lu Yunlong
 */
public class ManageGroupCondition {

	private long id;
	private long groupId;
	private String compareKey;
	private String compareType;
	private String compareValue;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public String getCompareKey() {
		return compareKey;
	}
	public void setCompareKey(String compareKey) {
		this.compareKey = compareKey;
	}
	public String getCompareType() {
		return compareType;
	}
	public void setCompareType(String compareType) {
		this.compareType = compareType;
	}
	public String getCompareValue() {
		return compareValue;
	}
	public void setCompareValue(String compareValue) {
		this.compareValue = compareValue;
	}
	
}
