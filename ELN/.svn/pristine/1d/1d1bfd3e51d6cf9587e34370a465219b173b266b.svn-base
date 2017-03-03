/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ResSectionExamDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ResSectionExamBean;
import com.jftt.wifi.bean.vo.SectionExamVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:ResSectionExamDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)ShenHaijun
 */
public interface ResSectionExamDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询章节测试关系 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  ResSectionExamBean<BR>
	 */
	public ResSectionExamBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getSectionExams <BR>
	 * Description: 查询课程测试及测试记录 <BR>
	 * Remark: <BR>
	 * @param sectionId
	 * @param userId
	 * @param companyId
	 * @return
	 * @throws DataBaseException  List<SectionExamVo><BR>
	 */
	public List<SectionExamVo> getCourseTest(
			@Param("courseId")Integer courseId,
			@Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getSectionTests <BR>
	 * Description: 根据章节查询所有测试情况 <BR>
	 * Remark: <BR>
	 * @param sectionId
	 * @param userId
	 * @return
	 * @throws DataBaseException  List<SectionExamVo><BR>
	 */
	public List<SectionExamVo> getSectionTests(@Param("sectionId")Integer sectionId, @Param("userId")Integer userId) throws DataBaseException;


	
	/**shenhaijun end*/

	/**chenrui start**/
	/**
	 * 插入章节考试关联信息
	 * Method name: inserRelate <BR>
	 * Description: inserRelate <BR>
	 * Remark: <BR>
	 * @param newSectionId
	 * @param newExamId  void<BR>
	 */
	public void inserRelate(ResSectionExamBean addSebean);
	/**chenrui end**/
}
