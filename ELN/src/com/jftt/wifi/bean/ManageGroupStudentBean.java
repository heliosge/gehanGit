/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: SysGroupStudentBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-17        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean;

/**
 * class name:SysGroupStudentBean <BR>
 * class description: 学员加入群组bean <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-17
 * @author JFTT)ShenHaijun
 */
public class ManageGroupStudentBean {
	private Integer id;//学员加入群组id
	private Integer groupId;//群组id
	private Integer studentId;//学员用户id
	private Integer status;//加入状态（ 1：正常 2：等待审核 3：审核不通过 ）
	
	public ManageGroupStudentBean(){}
	
	public ManageGroupStudentBean(Integer groupId, Integer studentId, Integer status){
		this.groupId = groupId;
		this.studentId = studentId;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
