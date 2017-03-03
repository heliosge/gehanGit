/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MallTopicDetailVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月18日        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.List;

import com.jftt.wifi.bean.MallTopicBean;

/**
 * class name:MallTopicDetailVo <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月18日
 * @author JFTT)chenrui
 */
public class MallTopicDetailVo extends MallTopicBean{
	List<MallCoursePageVo> courseList;//专题下的课程信息

	public List<MallCoursePageVo> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<MallCoursePageVo> courseList) {
		this.courseList = courseList;
	}
	
}
