/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseWareRecordDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-22        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.CourseWareRecordBean;
import com.jftt.wifi.bean.vo.CourseWareProgressVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseWareRecordDaoMapper <BR>
 * class description: 课件学习记录dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-22
 * @author JFTT)ShenHaijun
 */
public interface CourseWareRecordDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询课件 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException<BR>
	 */
	public CourseWareRecordBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getIdByConditions <BR>
	 * Description: 根据条件查询课件记录id <BR>
	 * Remark: <BR>
	 * @param sectionId
	 * @param wareId
	 * @param userId
	 * @param companyId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getIdByConditions(
			@Param("sectionId")Integer sectionId, @Param("wareId")Integer wareId,
			@Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: addCourseWareRecordBean <BR>
	 * Description: 添加一条学习记录 <BR>
	 * Remark: <BR>
	 * @param courseWareRecordBean
	 * @throws DataBaseException  void<BR>
	 */
	public void addCourseWareRecordBean(
			CourseWareRecordBean courseWareRecordBean) throws DataBaseException;
	
	/**
	 * Method name: updateCourseWareRecordBean <BR>
	 * Description: 更新一条学习记录 <BR>
	 * Remark: <BR>
	 * @param courseWareRecordBean
	 * @throws DataBaseException  void<BR>
	 */
	public void updateCourseWareRecordBean(
			CourseWareRecordBean courseWareRecordBean) throws DataBaseException;
	
	/**
	 * Method name: getWareRecordsBycourseRecordId <BR>
	 * Description: 根据课程学习记录id查询课件的学习记录 <BR>
	 * Remark: <BR>
	 * @param courseRecordId
	 * @return
	 * @throws DataBaseException  List<CourseWareRecordBean><BR>
	 */
	public List<CourseWareRecordBean> getWareRecordsBycourseRecordId(
			@Param("courseRecordId")Integer courseRecordId) throws DataBaseException;
	
	/**
	 * Method name: getCourseWareByConditions <BR>
	 * Description: 根据条件查询课件学习记录 <BR>
	 * Remark: <BR>
	 * @param sectionId
	 * @param courseWareId
	 * @param userId
	 * @return
	 * @throws DataBaseException  CourseWareRecordBean<BR>
	 */
	public CourseWareRecordBean getCourseWareByConditions(@Param("sectionId")Integer sectionId,
			@Param("courseWareId")Integer courseWareId, @Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getWareRecordsByCourseIdUserId <BR>
	 * Description: 根据课程id和用户id查询所有课件记录 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @return
	 * @throws DataBaseException  List<CourseWareRecordBean><BR>
	 */
	public List<CourseWareRecordBean> getWareRecordsByCourseIdUserId(
			@Param("courseId")Integer courseId, @Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: deleteRecord <BR>
	 * Description: 删除该学员的该课程的所有课件学习记录 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @throws DataBaseException  void<BR>
	 */
	public void deleteRecord(@Param("courseId")String courseId, @Param("userId")String userId) throws DataBaseException;
	
	/**
	 * Method name: getCourseStudyedDuration <BR>
	 * Description: 获取课程已学时长（时、分、秒依次存储） <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @return
	 * @throws DataBaseException  List<Long><BR>
	 */
	public List<Long> getCourseStudyedDuration(@Param("courseId")Integer courseId, @Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getStudyedPercents <BR>
	 * Description: 获取该课程所有课件学习进度 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @return
	 * @throws DataBaseException  List<Map<String,Integer>><BR>
	 */
	public List<CourseWareProgressVo> getStudyedPercents(@Param("courseId")Integer courseId,@Param("userId")Integer userId) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
