/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: DeptExamVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-2-19        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.exam.vo;

/**
 * @author JFTT)zhangchen<BR>
 * class name:DeptExamVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2016-2-19
 */
public class DeptExamVo {
	/**  
	 * 部门ID
	 */
	private Integer id;
	/**  
	 * 部门名称
	 */
	private String deptName;
	/**  
	 * 部门人员总数
	 */
	private Integer userNum;
	/**  
	 * 参加考试人数
	 */
	private Integer attendNum;
	/**  
	 * 考试人数覆盖率
	 */
	private Integer attendPercent;
	/**  
	 * 参加考试数
	 */
	private Integer attendExamNum;
	/**  
	 * 排名
	 */
	private Integer rank;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserNum() {
		return userNum;
	}
	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}
	public Integer getAttendNum() {
		return attendNum;
	}
	public void setAttendNum(Integer attendNum) {
		this.attendNum = attendNum;
	}
	public Integer getAttendPercent() {
		return attendPercent;
	}
	public void setAttendPercent(Integer attendPercent) {
		this.attendPercent = attendPercent;
	}
	public Integer getAttendExamNum() {
		return attendExamNum;
	}
	public void setAttendExamNum(Integer attendExamNum) {
		this.attendExamNum = attendExamNum;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	

}
