/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: qualificationVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-30        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * @author JFTT)chenrui
 * class name:qualificationVo <BR>
 * class description: 参赛资格vo <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-30
 * @author JFTT)chenrui
 */
public class QualificationVo {
	private String qualificationId;
	private String qualificationType;
	private String name;
	public String getQualificationId() {
		return qualificationId;
	}
	public void setQualificationId(String qualificationId) {
		this.qualificationId = qualificationId;
	}
	public String getQualificationType() {
		return qualificationType;
	}
	public void setQualificationType(String qualificationType) {
		this.qualificationType = qualificationType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
