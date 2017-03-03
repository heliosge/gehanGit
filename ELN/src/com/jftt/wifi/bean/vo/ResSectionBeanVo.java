/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ResSectionBeanVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-27        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.List;

import com.jftt.wifi.bean.ResCoursewareBean;
import com.jftt.wifi.bean.ResSectionBean;
import com.jftt.wifi.bean.exam.ExamScheduleBean;

/**
 * class name:ResSectionBeanVo <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-27
 * @author JFTT)chenrui
 */
public class ResSectionBeanVo extends ResSectionBean {
	private List<ResCoursewareBean> coursewareList;//章节下的课件list
	
	private List<MallSectionExamVo> examList;//章节下的考试list
	
	public List<ResCoursewareBean> getCoursewareList() {
		return coursewareList;
	}

	public void setCoursewareList(List<ResCoursewareBean> coursewareList) {
		this.coursewareList = coursewareList;
	}

	public List<MallSectionExamVo> getExamList() {
		return examList;
	}

	public void setExamList(List<MallSectionExamVo> examList) {
		this.examList = examList;
	}


	
}
