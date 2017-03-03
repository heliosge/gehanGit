/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamRawStateInfo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/04        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam;

import java.util.Date;

/**
 * class name:ExamRawStateInfo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/04
 * @author JFTT)wangyifeng
 */
public interface ExamRawStateInfo {
	/**
	 * Method name: getIsPublished <BR>
	 * Description: 是否已发布 <BR>
	 * Remark: <BR>
	 * 
	 * @return Boolean<BR>
	 */
	Boolean getIsPublished();

	/**
	 * Method name: getBeginTime <BR>
	 * Description: 考试开始时间 <BR>
	 * Remark: <BR>
	 * 
	 * @return Date<BR>
	 */
	String getBeginTime();

	/**
	 * Method name: getEndTime <BR>
	 * Description: 考试结束时间 <BR>
	 * Remark: <BR>
	 * 
	 * @return Date<BR>
	 */
	String getEndTime();

	/**
	 * Method name: getType <BR>
	 * Description: 考试种类 <BR>
	 * Remark: <BR>
	 * 
	 * @return Integer<BR>
	 */
	Integer getType();
}
