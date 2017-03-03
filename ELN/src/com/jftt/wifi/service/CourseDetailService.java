/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseDetailService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-28        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.CourseCollectionBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.ResCourseCategoryBean;
import com.jftt.wifi.bean.ResSectionBean;
import com.jftt.wifi.bean.ResSectionExamBean;
import com.jftt.wifi.bean.vo.SectionExamVo;
import com.jftt.wifi.bean.vo.SectionWareVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseDetailService <BR>
 * class description: 课程详情service <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-28
 * @author JFTT)ShenHaijun
 */
public interface CourseDetailService {
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getCourseSections <BR>
	 * Description: 查询该课程所有章节 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return
	 * @throws DataBaseException  List<ResSectionBean><BR>
	 */
	List<ResSectionBean> getCourseSections(Integer courseId) throws DataBaseException;
	
	/**
	 * Method name: getSectionWares <BR>
	 * Description: 根据章节查询所有课件及该课件的学习记录 <BR>
	 * Remark: <BR>
	 * @param sectionId 章节id
	 * @param userId 用户id
	 * @return
	 * @throws DataBaseException  List<SectionWareVo><BR>
	 */
	List<SectionWareVo> getSectionWares(Integer sectionId, Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getCourseTest <BR>
	 * Description: 查询课程测试及测试记录 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return
	 * @throws DataBaseException  List<SectionExamVo><BR>
	 */
	List<SectionExamVo> getCourseTest(Integer courseId, Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getThisCourseCategory <BR>
	 * Description: 找出该课程的课程体系结构列表 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return
	 * @throws DataBaseException  List<ResCourseCategoryBean><BR>
	 */
	List<ResCourseCategoryBean> getThisCourseCategory(Integer courseId) throws DataBaseException;
	
	/**
	 * Method name: getCourseById <BR>
	 * Description: 根据id查询课程 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return
	 * @throws DataBaseException  ResCourseBean<BR>
	 */
	ResCourseBean getCourseById(Integer courseId) throws DataBaseException;
	
	/**
	 * Method name: getCourseCollection <BR>
	 * Description: 判断该课程是否被该学员收藏 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return
	 * @throws DataBaseException  boolean<BR>
	 */
	boolean getCourseCollection(Integer courseId,Integer userId) throws DataBaseException;
	
	/**
	 * Method name: collectThisCourse <BR>
	 * Description: 收藏该课程 <BR>
	 * Remark: <BR>
	 * @param collection 课程Bean
	 * @throws DataBaseException  void<BR>
	 */
	void collectThisCourse(CourseCollectionBean collection) throws DataBaseException;
	
	/**
	 * Method name: getSectionTests <BR>
	 * Description: 根据章节查询所有测试情况 <BR>
	 * Remark: <BR>
	 * @param sectionId 章节id
	 * @param userId 用户id
	 * @return
	 * @throws Exception  List<SectionExamVo><BR>
	 */
	List<SectionExamVo> getSectionTests(Integer sectionId, Integer userId) throws Exception;
	
	/**
	 * Method name: getNearestCategory <BR>
	 * Description: 获取最近的课程体系名称 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return
	 * @throws DataBaseException  String<BR>
	 */
	String getNearestCategory(Integer courseId) throws DataBaseException;
	
	/**
	 * Method name: getTeacherNameById <BR>
	 * Description: 根据教师id查询教师姓名 <BR>
	 * Remark: <BR>
	 * @param teacherId 教师id
	 * @return
	 * @throws Exception  String<BR>
	 */
	String getTeacherNameById(Integer teacherId) throws Exception;
	
	/**
	 * Method name: getCourseTotalDuration <BR>
	 * Description: 获取课程总时长（时、分、秒依次存储） <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return
	 * @throws Exception  String[]<BR>
	 */
	String[] getCourseTotalDuration(Integer courseId) throws Exception;
	
	/**
	 * Method name: getCourseStudyedDuration <BR>
	 * Description: 获取课程已学时长（时、分、秒依次存储） <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return  String[]<BR>
	 */
	String[] getCourseStudyedDuration(Integer courseId, Integer userId) throws Exception;
	
	/**
	 * Method name: getCourseStudyedDurationNew <BR>
	 * Description: 获取课程已学时长（时、分、秒依次存储） <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return
	 * @throws DataBaseException  String[]<BR>
	 */
	String[] getCourseStudyedDurationNew(Integer courseId, Integer userId) throws DataBaseException;
	
	/**shenhaijun end*/
	
	/**
	 * Method name: getStudentNumById <BR>
	 * Description: 根据课程id查询学习人数 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return
	 * @throws Exception  <BR>
	 */
	public Integer getStudentNumById(Integer courseId) throws Exception;
	
	/**zhangtbocheng mobile start*/
	/**
	 * 估计id查出章节测试
	 * getSectionExamById
	 * @param id 章节id
	 * @return
	 * @throws Exception
	 */
	public ResSectionExamBean getSectionExamById(Integer id)
			throws Exception ;
	/**zhangtbocheng mobile end*/
	
}
