/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseStudyRecordDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-22        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.CourseStudyRecordBean;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseStudyRecordDaoMapper <BR>
 * class description: 学习记录dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-22
 * @author JFTT)ShenHaijun
 */
public interface CourseStudyRecordDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询学习记录 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  CourseStudyRecordBean<BR>
	 */
	public CourseStudyRecordBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getIdByConditions <BR>
	 * Description: 根据条件查询课程学习记录id <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @param companyId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getIdByConditions(@Param("courseId")Integer courseId, @Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: addCourseStudyRecordBean <BR>
	 * Description: 添加一条课程学习记录 <BR>
	 * Remark: <BR>
	 * @param courseStudyRecordBean
	 * @throws DataBaseException  void<BR>
	 */
	public void addCourseStudyRecordBean(
			CourseStudyRecordBean courseStudyRecordBean) throws DataBaseException;
	
	/**
	 * Method name: updateCourseStudyRecordBean <BR>
	 * Description: 更新一条课程学习记录 <BR>
	 * Remark: <BR>
	 * @param courseStudyRecordBean
	 * @throws DataBaseException  void<BR>
	 */
	public void updateCourseStudyRecordBean(
			CourseStudyRecordBean courseStudyRecordBean) throws DataBaseException;
	
	/**
	 * Method name: deleteRecord <BR>
	 * Description: 删除一条课程学习记录 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @throws DataBaseException  void<BR>
	 */
	public void deleteRecord(@Param("courseId")String courseId, @Param("userId")String userId) throws DataBaseException;
	
	/**shenhaijun end*/
	
	/**luyunlong start*/
	/**
	 * Method name: selectCourseStudyByMap <BR>
	 * Description: 查询我的学习记录 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<CourseStudyRecordBean><BR>
	 */
	public List<CourseStudyRecordBean> selectCourseStudyByMap(Map<String, Object> param);
	
	/**
	 * Method name: selectCourseStudyCountByMap <BR>
	 * Description: 查询我的学习记录的数量 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  int<BR>
	 */
	public int selectCourseStudyCountByMap(Map<String, Object> param);
	
	/**luyunlong end*/
	
}
