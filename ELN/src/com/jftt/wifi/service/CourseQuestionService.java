/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseQuestionService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-22        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.vo.CourseQuestionVo;
import com.jftt.wifi.bean.vo.CourseWareIdTypeVo;
import com.jftt.wifi.bean.vo.MyQuestionVo;
import com.jftt.wifi.bean.vo.QuestionReplyVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseQuestionService <BR>
 * class description: 我的问答service <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-22
 * @author JFTT)HeTianrui
 */
public interface CourseQuestionService {
	
	/**
	 * Method name: getAskQuestionsCount <BR>
	 * Description: 获取学员提出问题的数目 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param asktimeStart 提问开始时间
	 * @param asktimeEnd 提问结束时间
	 * @return Integer
	 * @throws Exception <BR>
	 */
	Integer getAskQuestionsCount(Integer userId, String courseName,
			String asktimeStart, String asktimeEnd) throws Exception;
	
	/**
	 * Method name: getAskQuestions <BR>
	 * Description: 获取学员提出问题 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param asktimeStart 提问开始时间
	 * @param asktimeEnd 提问结束时间
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<MyQuestionVo>
	 * @throws Exception  <BR>
	 */
	List<MyQuestionVo> getAskQuestions(Integer userId, String courseName,String asktimeStart, String asktimeEnd, 
			String sortName,String sortOrder,Integer fromNum,Integer pageSize) throws Exception;
	
	/**
	 * Method name: getDoubleQuestions <BR>
	 * Description: 获取该课程的本学员前两个问题 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return List<String>
	 * @throws DataBaseException <BR>
	 */
	List<String> getDoubleQuestions(Integer courseId, Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getReplyQuestionsCount <BR>
	 * Description: 获取回答问题的课程数量 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param asktimeStart 提问开始时间
	 * @param asktimeEnd 提问结束时间
	 * @return Integer
	 * @throws Exception <BR>
	 */
	Integer getReplyQuestionsCount(Integer userId, String courseName,
			String asktimeStart, String asktimeEnd) throws Exception;
	
	/**
	 * Method name: getReplyQuestions <BR>
	 * Description: 获取回答问题的课程 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param asktimeStart 提问开始时间
	 * @param asktimeEnd 提问结束时间
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<MyQuestionVo>
	 * @throws Exception <BR>
	 */
	List<MyQuestionVo> getReplyQuestions(Integer userId, String courseName,String asktimeStart, String asktimeEnd, 
			String sortName,String sortOrder, Integer fromNum, Integer pageSize) throws Exception;
	
	/**
	 * Method name: getReplyDoubleQuestions <BR>
	 * Description: 获取这门课程的本学员回答所在的前两个问题 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return List<String>
	 * @throws DataBaseException <BR>
	 */
	List<String> getReplyDoubleQuestions(Integer courseId, Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getWareIdType <BR>
	 * Description: 获取课件id和type <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param questionType 问题类型
	 * @return CourseWareIdTypeVo
	 * @throws DataBaseException <BR>
	 */
	CourseWareIdTypeVo getWareIdType(Integer courseId, Integer userId,String questionType) throws DataBaseException;
	
	/**
	 * Method name: getSectionIdByCourseIdWareId <BR>
	 * Description: 根据课程id和课件id查询章节id <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param courseWareId 课件id
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getSectionIdByCourseIdWareId(Integer courseId, Integer courseWareId) throws DataBaseException;
	
	/**
	 * Method name: getCourseQuestionsCount <BR>
	 * Description: 获取课程问答列表中问答数量 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompany 子公司id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	Integer getCourseQuestionsCount(Integer courseId,Integer companyId,Integer subCompany) throws Exception;
	
	/**
	 * Method name: getQuestionsByCourseId <BR>
	 * Description: 获取该课程所有问答 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return List<CourseQuestionVo>
	 * @throws Exception <BR>
	 */
	List<CourseQuestionVo> getQuestionsByCourseId(Integer courseId,
			Integer companyId, Integer subCompanyId, Integer page,
			Integer pageSize) throws Exception;
	
	/**
	 * Method name: getQuestionReplys <BR>
	 * Description: 获取问题所有回复 <BR>
	 * Remark: <BR>
	 * @param topicId 问题id
	 * @return List<QuestionReplyVo>
	 * @throws Exception <BR>
	 */
	List<QuestionReplyVo> getQuestionReplys(Integer topicId) throws Exception;
	
	/**
	 * Method name: getCourseNameById <BR>
	 * Description: 根据课程id查询课程名称 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return String
	 * @throws Exception <BR>
	 */
	String getCourseNameById(Integer courseId) throws Exception;
	
}
