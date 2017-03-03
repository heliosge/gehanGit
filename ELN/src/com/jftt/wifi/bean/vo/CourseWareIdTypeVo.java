/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseWareIdTypeVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年9月2日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * class name:CourseWareIdTypeVo <BR>
 * class description: 课件id和typ使用vo <BR>
 * Remark: <BR>
 * @version 1.00 2015年9月2日
 * @author JFTT)ShenHaijun
 */
public class CourseWareIdTypeVo {
	private Integer courseWareId;//课件id
	private Integer courseWareType;//课件类型
	
	public Integer getCourseWareId() {
		return courseWareId;
	}
	public void setCourseWareId(Integer courseWareId) {
		this.courseWareId = courseWareId;
	}
	public Integer getCourseWareType() {
		return courseWareType;
	}
	public void setCourseWareType(Integer courseWareType) {
		this.courseWareType = courseWareType;
	}
}
