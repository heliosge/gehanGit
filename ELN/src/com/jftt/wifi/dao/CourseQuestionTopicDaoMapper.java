/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseQuestionTopicDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-15        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.CourseQuestionTopicBean;
import com.jftt.wifi.bean.vo.CourseQuestionVo;
import com.jftt.wifi.bean.vo.CourseQuestionVoForStudy;
import com.jftt.wifi.bean.vo.MyQuestionVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseQuestionTopicDaoMapper <BR>
 * class description: 课程问答问题dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-15
 * @author JFTT)ShenHaijun
 */
public interface CourseQuestionTopicDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询课程问答问题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  CourseQuestionTopicBean<BR>
	 */
	public CourseQuestionTopicBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**
	 * Method name: queryMyQuestionByCondition <BR>
	 * Description: 新增我提出的问题 <BR>
	 * Remark: <BR>
	 * @author JFTT)HeTianrui
	 * @param CourseQuestionTopicBean question
	 * @return  List<CourseQuestionTopicBean><BR>
	 */
	public void addMyQuestion(CourseQuestionTopicBean question)throws Exception;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getCourseQuestionsCount <BR>
	 * Description: 查询课程问题数量 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getCourseQuestionsCount(@Param("courseId")Integer courseId, @Param("companyId")Integer companyId,
			@Param("subCompanyId")Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getCourseQuestions <BR>
	 * Description: 获取课程的所有问题 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param companyId
	 * @param subCompanyId
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<CourseQuestionVoForStudy><BR>
	 */
	public List<CourseQuestionVoForStudy> getCourseQuestions(@Param("courseId")Integer courseId,
			@Param("companyId")Integer companyId, @Param("subCompanyId")Integer subCompanyId, 
			@Param("fromNum")Integer fromNum,@Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getAskQuestionsCount <BR>
	 * Description: 获取学员提出问题的数目 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param courseName
	 * @param asktimeStartDate
	 * @param asktimeEndDate
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getAskQuestionsCount(@Param("userId")Integer userId, @Param("courseName")String courseName,
			@Param("asktimeStartDate")Date asktimeStartDate, @Param("asktimeEndDate")Date asktimeEndDate) throws DataBaseException;
	
	/**
	 * Method name: getAskQuestions <BR>
	 * Description: 获取学员提出的问题 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param courseName
	 * @param asktimeStartDate
	 * @param asktimeEndDate
	 * @param sortName
	 * @param sortOrder
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<MyQuestionVo><BR>
	 */
	public List<MyQuestionVo> getAskQuestions(@Param("userId")Integer userId,@Param("courseName")String courseName, 
			@Param("asktimeStartDate")Date asktimeStartDate, @Param("asktimeEndDate")Date asktimeEndDate,
			@Param("sortName")String sortName, @Param("sortOrder")String sortOrder, @Param("fromNum")Integer fromNum, @Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getDoubleQuestions <BR>
	 * Description: 获取该课程的本学员前两个问题 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @return
	 * @throws DataBaseException  List<String><BR>
	 */
	public List<String> getDoubleQuestions(@Param("courseId")Integer courseId, @Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getQuestionsByCourseId <BR>
	 * Description: 获取该课程所有问答 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param companyId
	 * @param subCompanyId
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<CourseQuestionVo><BR>
	 */
	public List<CourseQuestionVo> getQuestionsByCourseId(@Param("courseId")Integer courseId,
			@Param("companyId")Integer companyId, @Param("subCompanyId")Integer subCompanyId, @Param("fromNum")Integer fromNum,
			@Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getNotAnsweredQuestionIds <BR>
	 * Description: 获取没有回复的该学员该课程的所有问题 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @return
	 * @throws DataBaseException  List<Integer><BR>
	 */
	public List<Integer> getNotAnsweredQuestionIds(@Param("courseId")String courseId,
			@Param("userId")String userId) throws DataBaseException;
	
	/**
	 * Method name: deleteRecord <BR>
	 * Description: 删除问题记录 <BR>
	 * Remark: <BR>
	 * @param questionIds
	 * @throws DataBaseException  void<BR>
	 */
	public void deleteRecord(@Param("questionIds")List<Integer> questionIds) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
