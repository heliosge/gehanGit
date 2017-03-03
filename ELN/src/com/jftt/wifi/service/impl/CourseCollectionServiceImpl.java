/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseCollectionServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.CourseCollectionBean;
import com.jftt.wifi.bean.vo.ResCourseVo;
import com.jftt.wifi.dao.CourseCollectionDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseCollectionService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.Pagination;

/**
 * class name:CourseCollectionServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)HeTianrui
 */
@Service("courseCollectionService")
public class CourseCollectionServiceImpl implements CourseCollectionService{

	@Resource(name="courseCollectionDaoMapper")
	private CourseCollectionDaoMapper courseCollectionDaoMapper;
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseCollectionService#addMyCollection(com.jftt.wifi.bean.CourseCollectionBean) <BR>
	 * Method name: addMyCollection <BR>
	 * Description: 添加收藏 <BR>
	 * Remark: <BR>
	 * @param collection 收藏Bean
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addMyCollection(CourseCollectionBean collection)throws Exception {
		courseCollectionDaoMapper.addMyCollection(collection);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseCollectionService#delMyCollection(long) <BR>
	 * Method name: delMyCollection <BR>
	 * Description: 删除收藏 <BR>
	 * Remark: <BR>
	 * @param id 收藏id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delMyCollection(long id) throws Exception{
		courseCollectionDaoMapper.delMyCollection(id);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseCollectionService#queryMyCollectionByCondition(com.jftt.wifi.bean.CourseCollectionBean) <BR>
	 * Method name: queryMyCollectionByCondition <BR>
	 * Description: 查询收藏 <BR>
	 * Remark: <BR>
	 * @param collection 收藏Bean
	 * @return Pagination<CourseCollectionBean>
	 * @throws Exception  <BR>
	 */
	@Override
	public Pagination<CourseCollectionBean> queryMyCollectionByCondition(CourseCollectionBean collection)
			throws Exception {
		int total =courseCollectionDaoMapper.countByCondition(collection);
		int pageStartSize = (collection.getPageIndex()-1) * collection.getPageSize();
		collection.setPageStartSize(pageStartSize);
		List<CourseCollectionBean> list = courseCollectionDaoMapper.queryMyCollectionByCondition(collection);
		Pagination<CourseCollectionBean> page = new Pagination<CourseCollectionBean>(list, total, collection.getPageSize(),collection.getPageIndex());
		return page;
	}
	
	/**shenhaijun start*/
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseCollectionService#getCollectCoursesCount(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: getCollectCoursesCount <BR>
	 * Description: 获取收藏课程的数量 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param collectTimeStart 查询开始时间
	 * @param collectTimeEnd 查询结束时间
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getCollectCoursesCount(Integer userId,String courseName,String collectTimeStart,String collectTimeEnd) throws Exception{
		//转换日期
		Date collectTimeStartDate = null;
		Date collectTimeEndDate = null;
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		if(collectTimeStart != null && collectTimeStart != ""){
			collectTimeStartDate = formater.parse(collectTimeStart);
		}
		if(collectTimeEnd != null && collectTimeEnd != ""){
			collectTimeEndDate = formater.parse(collectTimeEnd);
		}
		//返回查询结果
		return courseCollectionDaoMapper.getCollectCoursesCount(userId,courseName,collectTimeStartDate,collectTimeEndDate);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseCollectionService#getCollectCourses(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer) <BR>
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
	 * @return List<ResCourseVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<ResCourseVo> getCollectCourses(Integer userId,String courseName, String collectTimeStart, String collectTimeEnd,
			String sortName, String sortOrder, Integer fromNum, Integer pageSize) throws Exception {
		//转换日期
		Date collectTimeStartDate = null;
		Date collectTimeEndDate = null;
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		if(collectTimeStart != null && collectTimeStart != ""){
			collectTimeStartDate = formater.parse(collectTimeStart);
		}
		if(collectTimeEnd != null && collectTimeEnd != ""){
			collectTimeEndDate = formater.parse(collectTimeEnd);
		}
		//返回查询结果
		List<ResCourseVo> courses = courseCollectionDaoMapper.getCollectCourses(userId,courseName,collectTimeStartDate,collectTimeEndDate,sortName,sortOrder,fromNum,pageSize);
		//遍历结果，将评分改为保留一位小数
		for (int i = 0; i < courses.size(); i++) {
			courses.get(i).setAverageScore(CommonUtil.getOneDecimalNumber(courses.get(i).getAverageScore()));
		}
		return courses;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseCollectionService#cancelCollection(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: cancelCollection <BR>
	 * Description: 取消收藏该课程 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @throws Exception <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void cancelCollection(Integer userId, Integer courseId)
			throws Exception {
		courseCollectionDaoMapper.deleteCollection(userId,courseId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseCollectionService#cancelAllCollection(java.lang.Integer) <BR>
	 * Method name: cancelCollection <BR>
	 * Description: 取消所有收藏课程 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void cancelAllCollection(Integer userId)
			throws Exception {
		courseCollectionDaoMapper.deleteAllCollection(userId);
	}
	
	/**shenhaijun end*/
	
}
