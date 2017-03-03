/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseCollectionAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.ResCourseVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.CourseCollectionService;

/**
 * class name:CourseCollectionAction <BR>
 * class description: 我的收藏 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)HeTianrui
 */
@Controller
@RequestMapping("courseCollectionAction")
public class CourseCollectionAction {
	private static Logger logger = Logger.getLogger(CourseCollectionAction.class);
	
	@Resource(name="courseCollectionService") 
	private CourseCollectionService courseCollectionService;
	
	/**
	 * Method name: toMyCollection <BR>
	 * Description: 跳转到我的收藏页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toMyCollection")
	public String toMyCollection(HttpServletRequest request){
		return "studentCourse/myCollection";
	}
	
	/**
	 * Method name: getCollectCourses <BR>
	 * Description: 获取收藏的课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param collectTimeStart 查询开始时间
	 * @param collectTimeEnd 查询结束时间
	 * @param sortName 根据什么排序
	 * @param sortOrder 升序降序
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getCollectCourses")
	@ResponseBody
	public Object getCollectCourses(HttpServletRequest request,
			Integer userId,String courseName,String collectTimeStart,String collectTimeEnd,
			String sortName,String sortOrder,Integer page,Integer pageSize){
		Integer fromNum = page * pageSize;
		try {
			logger.info("查询学员"+userId+"收藏的课程");
			List<ResCourseVo> collectCourses = courseCollectionService.getCollectCourses(userId,courseName,collectTimeStart,collectTimeEnd,
					sortName,sortOrder,fromNum,pageSize);
			logger.info("查询学员"+userId+"收藏的课程结束");
			return collectCourses;
		} catch (Exception e) {
			logger.warn(CourseCollectionAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getCollectCoursesCount <BR>
	 * Description: 获取收藏课程的数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param collectTimeStart 查询开始时间
	 * @param collectTimeEnd 查询结束时间
	 * @return  Integer<BR>
	 */
	@RequestMapping("getCollectCoursesCount")
	@ResponseBody
	public Integer getCollectCoursesCount(HttpServletRequest request,Integer userId,
			String courseName,String collectTimeStart,String collectTimeEnd){
		Integer count = 0;
		try {
			logger.info("查询学员"+userId+"收藏课程的数量");
			count = courseCollectionService.getCollectCoursesCount(userId,courseName,collectTimeStart,collectTimeEnd);
			logger.info("学员"+userId+"收藏课程的数量为"+count);
			return count;
		} catch (Exception e) {
			logger.warn(CourseCollectionAction.class, e);
		}
		return count;
	}
	
	/**
	 * Method name: cancelCollection <BR>
	 * Description: 取消收藏该课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @return  Object<BR>
	 */
	@RequestMapping("cancelCollection")
	@ResponseBody
	public Object cancelCollection(HttpServletRequest request,Integer userId,Integer courseId){
		AjaxReturnVo<Object> returnVo = new AjaxReturnVo<Object>();
		try {
			logger.info("取消收藏课程");
			courseCollectionService.cancelCollection(userId,courseId);
			logger.info("取消收藏课程完毕");
		} catch (Exception e) {
			returnVo.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseCollectionAction.class, e);
		}
		return returnVo;
	}
}
