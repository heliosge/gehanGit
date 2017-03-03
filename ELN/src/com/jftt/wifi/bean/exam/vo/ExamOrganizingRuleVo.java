/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamOrganizingRuleVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-25        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.exam.vo;

/**
 * @author JFTT)zhangchen<BR>
 * class name:ExamOrganizingRuleVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-25
 */
public class ExamOrganizingRuleVo {
	private int type;//题型：1主观题，2单选题，3多选题，4判断题，5填空题，6组合题
	private String typeName;
	private int typeCount;//题型数目 
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getTypeCount() {
		return typeCount;
	}
	public void setTypeCount(int typeCount) {
		this.typeCount = typeCount;
	}
	
}
