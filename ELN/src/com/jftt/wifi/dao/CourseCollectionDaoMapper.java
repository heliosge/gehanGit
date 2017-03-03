/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseCollectionDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-15        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.CourseCollectionBean;
import com.jftt.wifi.bean.vo.ResCourseVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseCollectionDaoMapper <BR>
 * class description: 课程收藏dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-15
 * @author JFTT)ShenHaijun
 */
public interface CourseCollectionDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询课程收藏 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  CourseCollectionBean<BR>
	 */
	public CourseCollectionBean getById(@Param("id")Integer id) throws DataBaseException;
    /**
     * Method name: queryMyCollectionByCondition <BR>
     * Description: 根据条件查询我的课程收藏 <BR>
     * Remark: <BR>
     * @param collection
     * @return  List<CourseCollectionBean><BR>
     */
	public List<CourseCollectionBean> queryMyCollectionByCondition(
			CourseCollectionBean collection)throws DataBaseException;
	/**
	 * Method name: addMyCollection <BR>
	 * Description: 添加收藏 <BR>
	 * Remark: <BR>
	 * @param collection  void<BR>
	 */
	public void addMyCollection(CourseCollectionBean collection)throws DataBaseException;
	/**
	 * Method name: delMyCollection <BR>
	 * Description: 删除收藏 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void delMyCollection(long id)throws DataBaseException;
	/**
	 * Method name: countByCondition <BR>
	 * Description: countByCondition <BR>
	 * Remark: <BR>
	 * @param collection
	 * @throws DataBaseException  void<BR>
	 */
	public int countByCondition(CourseCollectionBean collection)throws DataBaseException;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getCollectCoursesCount <BR>
	 * Description: 获取收藏课程的数量 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param courseName
	 * @param collectTimeStartDate
	 * @param collectTimeEndDate
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getCollectCoursesCount(@Param("userId")Integer userId, @Param("courseName")String courseName,
			@Param("collectTimeStartDate")Date collectTimeStartDate, @Param("collectTimeEndDate")Date collectTimeEndDate) throws DataBaseException;
	
	/**
	 * Method name: getCollectCourses <BR>
	 * Description: 获取收藏的课程 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param courseName
	 * @param collectTimeStartDate
	 * @param collectTimeEndDate
	 * @param sortName
	 * @param sortOrder
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<ResCourseVo><BR>
	 */
	public List<ResCourseVo> getCollectCourses(@Param("userId")Integer userId, @Param("courseName")String courseName, 
			@Param("collectTimeStartDate")Date collectTimeStartDate, @Param("collectTimeEndDate")Date collectTimeEndDate, 
			@Param("sortName")String sortName, @Param("sortOrder")String sortOrder,
			@Param("fromNum")Integer fromNum, @Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: deleteCollection <BR>
	 * Description: 取消收藏该课程 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param courseId
	 * @throws DataBaseException  void<BR>
	 */
	public void deleteCollection(@Param("userId")Integer userId, @Param("courseId")Integer courseId) throws DataBaseException;
	
	
	/**
	 * Method name: deleteAllCollection <BR>
	 * Description: 取消所有收藏课程 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @throws DataBaseException  void<BR>
	 */
	public void deleteAllCollection(@Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getIsCourseCollection <BR>
	 * Description: 判断该课程是否被收藏 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getIsCourseCollection(@Param("courseId")Integer courseId,@Param("userId")Integer userId) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
