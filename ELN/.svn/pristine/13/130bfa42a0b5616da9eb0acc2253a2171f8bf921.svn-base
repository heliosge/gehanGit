/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ResSectionDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-28        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ResSectionBean;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:ResSectionDaoMapper <BR>
 * class description: 课程章节dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-28
 * @author JFTT)ShenHaijun
 */
public interface ResSectionDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询章节 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  ResSectionBean<BR>
	 */
	public ResSectionBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**
	 * Method name: getCourseSections <BR>
	 * Description: 查询该课程所有章节 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return
	 * @throws DataBaseException  List<ResSectionBean><BR>
	 */
	public List<ResSectionBean> getCourseSections(
			@Param("courseId")Integer courseId) throws DataBaseException;
	
	/**
	 * Method name: getSectionIdByCourseIdWareId <BR>
	 * Description: 根据课程id和课件id查询章节id <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param courseWareId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getSectionIdByCourseIdWareId(@Param("courseId")Integer courseId,
			@Param("courseWareId")Integer courseWareId) throws DataBaseException;

	/**chenrui start **/
	public void addSection(ResSectionBean addSectionBean);
	
	/**chenrui end**/
}
