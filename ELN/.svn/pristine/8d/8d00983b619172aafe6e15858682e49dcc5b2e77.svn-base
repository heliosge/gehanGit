/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseExamRecordDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.CourseExamRecordBean;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseExamRecordDaoMapper <BR>
 * class description: 章节测试记录dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)ShenHaijun
 */
public interface CourseExamRecordDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询章节测试记录 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  CourseExamRecordBean<BR>
	 */
	public CourseExamRecordBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getIdByConditions <BR>
	 * Description: 根据条件查询测试记录id <BR>
	 * Remark: <BR>
	 * @param sectionId
	 * @param examId
	 * @param userId
	 * @param companyId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getIdByConditions(@Param("sectionId")Integer sectionId, 
			@Param("examId")Integer examId,@Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: addCourseExamRecordBean <BR>
	 * Description: 添加一条课程测试记录 <BR>
	 * Remark: <BR>
	 * @param courseExamRecordBean
	 * @throws DataBaseException  void<BR>
	 */
	public void addCourseExamRecordBean(
			CourseExamRecordBean courseExamRecordBean) throws DataBaseException;
	
	/**
	 * Method name: updateCourseExamRecordBean <BR>
	 * Description: 更新一条测试记录 <BR>
	 * Remark: <BR>
	 * @param courseExamRecordBean
	 * @throws DataBaseException  void<BR>
	 */
	public void updateCourseExamRecordBean(
			CourseExamRecordBean courseExamRecordBean) throws DataBaseException;
	
	/**
	 * Method name: getExamRecordsByCourseRecordId <BR>
	 * Description: 根据课程记录id查询相关的测试记录 <BR>
	 * Remark: <BR>
	 * @param courseRecordId
	 * @return
	 * @throws DataBaseException  List<CourseExamRecordBean><BR>
	 */
	public List<CourseExamRecordBean> getExamRecordsByCourseRecordId(
			@Param("courseRecordId")Integer courseRecordId) throws DataBaseException;
	
	/**
	 * Method name: getExamRecordsByCourseIdUserId <BR>
	 * Description: 根据课程id和用户id查询所有测试记录 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @return
	 * @throws DataBaseException  List<CourseExamRecordBean><BR>
	 */
	public List<CourseExamRecordBean> getExamRecordsByCourseIdUserId(
			@Param("courseId")Integer courseId, @Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: deleteRecord <BR>
	 * Description: 删除学员该课程的测试记录 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @throws DataBaseException  void<BR>
	 */
	public void deleteRecord(@Param("courseId")String courseId, @Param("userId")String userId) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
