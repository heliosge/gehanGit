/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ResTeacherDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年10月29日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:ResTeacherDaoMapper <BR>
 * class description: 讲师dao <BR>
 * Remark: <BR>
 * @version 1.00 2015年10月29日
 * @author JFTT)ShenHaijun
 */
public interface ResTeacherDaoMapper {
	
	/**
	 * Method name: getTeacherNameById <BR>
	 * Description: 根据讲师id查询讲师姓名 <BR>
	 * Remark: <BR>
	 * @param teacherId 讲师id
	 * @return
	 * @throws DataBaseException  String<BR>
	 */
	String getTeacherNameById(@Param("teacherId")Integer teacherId) throws DataBaseException;
}
