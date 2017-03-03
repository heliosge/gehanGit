/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseRecordService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年9月15日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service;

import java.util.Map;

import com.jftt.wifi.bean.CourseExamRecordBean;
import com.jftt.wifi.bean.CourseWareRecordBean;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseRecordService <BR>
 * class description: 课程、课件、测试记录service <BR>
 * Remark: <BR>
 * @version 1.00 2015年9月15日
 * @author JFTT)ShenHaijun
 */
public interface CourseRecordService {
	
	/**
	 * Method name: recordCourseAndWare <BR>
	 * Description: 记录课程和课件学习情况 <BR>
	 * Remark: <BR>
	 * @param courseWareId 课件id
	 * @param courseWareType 课件类型
	 * @param userId 用户id
	 * @param sectionId 章节id
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @throws Exception  void<BR>
	 */
	void recordCourseAndWare(Integer courseWareId, Integer courseWareType,
			Integer userId, Integer sectionId, Integer courseId,
			Integer companyId, Integer subCompanyId) throws Exception;
	
	/**
	 * Method name: recordExamAndCourseBeforeTest <BR>
	 * Description: 记录测试和课程学习情况（测试前 ）<BR>
	 * Remark: <BR>
	 * @param sectionId 章节id
	 * @param examId 试卷id
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param passTimes 允许次数
	 * @param passScorePercent 通过分数比例
	 * @return Integer
	 * @throws Exception <BR>
	 */
	Integer recordExamAndCourseBeforeTest(Integer sectionId, Integer examId, Integer userId,
			Integer courseId, Integer companyId, Integer subCompanyId,Integer passTimes,Integer passScorePercent) throws Exception;
	
	/**
	 * Method name: recordExamAndCourseAfterTest <BR>
	 * Description: 记录测试和课程学习情况（测试后） <BR>
	 * Remark: <BR>
	 * @param examRecordId 课程测试记录的id
	 * @param thisTestDuration 本次测试时长
	 * @param thisScore 本次得分
	 * @param totalScore 考试总分
	 * @throws Exception  void<BR>
	 */
	void recordExamAndCourseAfterTest(Integer examRecordId,Integer thisTestDuration,
			Integer thisScore,Integer totalScore) throws Exception;
	
	/**
	 * Method name: saveExamAndAssignInfo <BR>
	 * Description: 保存考试和考试分配信息 <BR>
	 * Remark: <BR>
	 * @param examId 试卷id
	 * @param duration 试卷时长
	 * @param allowTimes 允许次数
	 * @param passScorePercent 通过分数比例
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Map<String,Integer>
	 * @throws Exception <BR>
	 */
	Map<String, Integer> saveExamAndAssignInfo(Integer examId, Integer duration,Integer allowTimes, Integer passScorePercent,
			Integer userId,Integer companyId,Integer subCompanyId) throws Exception;
	
	/**
	 * Method name: getCourseWareByConditions <BR>
	 * Description: 根据条件查询课件学习记录 <BR>
	 * Remark: <BR>
	 * @param sectionId 章节id
	 * @param courseWareId 课件id
	 * @param userId 用户id
	 * @return CourseWareRecordBean 
	 * @throws DataBaseException <BR>
	 */
	CourseWareRecordBean getCourseWareByConditions(Integer sectionId,
			Integer courseWareId, Integer userId) throws DataBaseException;
	
	/**
	 * Method name: recordScromWareAfterLeavePage <BR>
	 * Description: 离开页面时记录scrom课件的学习进度 <BR>
	 * Remark: <BR>
	 * @param wareRecordId 课件记录id
	 * @param courseRecordId 课程记录id
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param total 总共
	 * @param studyed 已学
	 * @throws Exception  void<BR>
	 */
	void recordScromWareAfterLeavePage(Integer wareRecordId,Integer courseRecordId, 
			Integer courseId, Integer userId, String[] total, String[] studyed) throws Exception;
	
	/**
	 * Method name: recordSwfWareAfterLeavePage <BR>
	 * Description: 离开页面时记录swf课件的学习进度 <BR>
	 * Remark: <BR>
	 * @param wareRecordId 课件记录id
	 * @param courseRecordId 课程记录id
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param totalPages 总页数
	 * @param currPage 当前页
	 * @throws Exception  void<BR>
	 */
	void recordSwfWareAfterLeavePage(Integer wareRecordId,
			Integer courseRecordId, Integer courseId, Integer userId,
			String totalPages, String currPage) throws Exception;
	
	/**
	 * Method name: recordVedioWareAfterLeavePage <BR>
	 * Description: 离开页面时记录视频课件的学习进度 <BR>
	 * Remark: <BR>
	 * @param wareRecordId 课件记录id
	 * @param courseRecordId 课程记录id
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param totalTime 总时间
	 * @param currentTime 当前时间
	 * @throws Exception  void<BR>
	 */
	void recordVedioWareAfterLeavePage(Integer wareRecordId,
			Integer courseRecordId, Integer courseId, Integer userId,
			String totalTime, String currentTime) throws Exception;
	
	/**
	 * Method name: saveCourseRecord <BR>
	 * Description: 保存课程记录 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws Exception <BR>
	 */
	 Integer saveCourseRecord(Integer courseId,Integer userId,Integer companyId,Integer subCompanyId) throws Exception;
		
	 
	/**zhangbocheng mobile start */
 	/**
 	 * Method name: getExamRecordById <BR>
 	 * Description: 获取课程记录 <BR>
 	 * Remark: <BR>
 	 * @param examRecordId 试卷记录id
 	 * @return CourseExamRecordBean
 	 * @throws Exception <BR>
 	 */
	public CourseExamRecordBean getExamRecordById(Integer examRecordId) throws Exception;
	/**zhangbocheng mobile end */
}
