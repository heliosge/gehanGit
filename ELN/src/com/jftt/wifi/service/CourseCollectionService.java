/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseCollectionService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.CourseCollectionBean;
import com.jftt.wifi.bean.vo.ResCourseVo;
import com.jftt.wifi.util.Pagination;

/**
 * class name:CourseCollectionService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)HeTianrui
 */
public interface CourseCollectionService {
    /**
     * Method name: addMyCollection <BR>
     * Description: 添加收藏 <BR>
     * Remark: <BR>
     * @param collection 收藏Bean
     * @throws Exception  void<BR>
     */
	void addMyCollection(CourseCollectionBean collection)throws Exception;
    /**
     * Method name: delMyCollection <BR>
     * Description: 删除收藏 <BR>
     * Remark: <BR>
     * @param id 收藏id
     * @throws Exception  void<BR>
     */
	void delMyCollection(long id)throws Exception;
	/**
	 * Method name: queryMyCollectionByCondition <BR>
	 * Description: 根据条件查询我的课程收藏 <BR>
	 * Remark: <BR>
	 * @param collection 收藏Bean
	 * @return
	 * @throws Exception  Pagination<CourseCollectionBean><BR>
	 */
	Pagination<CourseCollectionBean> queryMyCollectionByCondition(CourseCollectionBean collection)throws Exception;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getCollectCoursesCount <BR>
	 * Description: 获取收藏课程的数量 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param collectTimeStart 查询开始时间
	 * @param collectTimeEnd 查询结束时间
	 * @return
	 * @throws Exception  Integer<BR>
	 */
	Integer getCollectCoursesCount(Integer userId,String courseName,String collectTimeStart,String collectTimeEnd) throws Exception;
	
	/**
	 * Method name: getCollectCourses <BR>
	 * Description: 获取收藏的课程 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param collectTimeStart 查询开始时间
	 * @param collectTimeEnd 查询结束时间
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return
	 * @throws Exception  List<ResCourseVo><BR>
	 */
	List<ResCourseVo> getCollectCourses(Integer userId, String courseName,String collectTimeStart, String collectTimeEnd, 
			String sortName,String sortOrder, Integer fromNum, Integer pageSize) throws Exception;
	
	/**
	 * Method name: cancelCollection <BR>
	 * Description: 取消收藏该课程 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @throws Exception  void<BR>
	 */
	void cancelCollection(Integer userId, Integer courseId) throws Exception;
	
	/**
	 * Method name: cancelAllCollection <BR>
	 * Description: 取消所有收藏 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @throws Exception  void<BR>
	 */
	void cancelAllCollection(Integer userId) throws Exception;
	
	/**shenhaijun end*/
	
}
