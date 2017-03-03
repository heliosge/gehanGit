/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ResSectionCoursewareDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ResSectionCoursewareBean;
import com.jftt.wifi.bean.vo.SectionWareVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:ResSectionCoursewareDaoMapper <BR>
 * class description: 章节课件关联dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)ShenHaijun
 */
public interface ResSectionCoursewareDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id获取章节课程关系 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  ResSectionCoursewareBean<BR>
	 */
	public ResSectionCoursewareBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getSectionWares <BR>
	 * Description: 根据章节查询所有课件及该课件的学习记录 <BR>
	 * Remark: <BR>
	 * @param sectionId
	 * @param userId
	 * @param companyId
	 * @return
	 * @throws DataBaseException  List<SectionWareVo><BR>
	 */
	public List<SectionWareVo> getSectionWares(@Param("sectionId")Integer sectionId,
			@Param("userId")Integer userId) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
