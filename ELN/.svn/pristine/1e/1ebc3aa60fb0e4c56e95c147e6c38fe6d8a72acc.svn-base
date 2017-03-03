/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseStudyService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月28日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.CourseNoteBean;
import com.jftt.wifi.bean.CourseQuestionReplyBean;
import com.jftt.wifi.bean.CourseQuestionTopicBean;
import com.jftt.wifi.bean.ResCoursewareBean;
import com.jftt.wifi.bean.vo.CourseNoteVoForStudy;
import com.jftt.wifi.bean.vo.CourseQuestionVoForStudy;
import com.jftt.wifi.bean.vo.CourseReplyVoForStudy;
import com.jftt.wifi.bean.vo.CourseStudyVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseStudyService <BR>
 * class description: 课程学习service <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月28日
 * @author JFTT)ShenHaijun
 */
public interface CourseStudyService {
	
	/**
	 * Method name: getCourseDetail <BR>
	 * Description: 获取课程详情 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param sectionId 章节id
	 * @param courseWareId 课件id
	 * @return CourseStudyVo
	 * @throws DataBaseException <BR>
	 */
	CourseStudyVo getCourseDetail(Integer courseId,Integer sectionId,Integer courseWareId) throws DataBaseException;
	
	/**
	 * Method name: getCourseNotesCount <BR>
	 * Description: 获取课程笔记数目 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getCourseNotesCount(Integer courseId, Integer companyId,Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getCourseNotes <BR>
	 * Description: 获取课程所有笔记 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<CourseNoteVoForStudy>
	 * @throws Exception <BR>
	 */
	List<CourseNoteVoForStudy> getCourseNotes(Integer courseId,Integer companyId, Integer subCompanyId, 
			Integer fromNum,Integer pageSize) throws Exception;
	
	/**
	 * Method name: addNote <BR>
	 * Description: 添加一条笔记 <BR>
	 * Remark: <BR>
	 * @param note 笔记Bean
	 * @throws DataBaseException  void<BR>
	 */
	void addNote(CourseNoteBean note) throws DataBaseException;
	
	/**
	 * Method name: getCourseQuestionsCount <BR>
	 * Description: 查询课程问题数量 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getCourseQuestionsCount(Integer courseId, Integer companyId,
			Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getCourseQuestions <BR>
	 * Description: 获取课程的所有问题  <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<CourseQuestionVoForStudy>
	 * @throws DataBaseException <BR>
	 */
	List<CourseQuestionVoForStudy> getCourseQuestions(Integer courseId,
			Integer companyId, Integer subCompanyId, Integer fromNum,
			Integer pageSize) throws Exception;
	
	/**
	 * Method name: saveQuestion <BR>
	 * Description: 保存一个提问 <BR>
	 * Remark: <BR>
	 * @param question 问题Bean
	 * @throws DataBaseException  void<BR>
	 */
	void saveQuestion(CourseQuestionTopicBean question) throws Exception;
	
	/**
	 * Method name: getReplys <BR>
	 * Description: 获取该问题的所有回答 <BR>
	 * Remark: <BR>
	 * @param topicId 问题id
	 * @return List<CourseReplyVoForStudy>
	 * @throws Exception <BR>
	 */
	List<CourseReplyVoForStudy> getReplys(Integer topicId) throws Exception;
	
	/**
	 * Method name: saveQuestionAnswer <BR>
	 * Description: 保存问题的回复 <BR>
	 * Remark: <BR>
	 * @param questionReply 回复Bean
	 * @throws DataBaseException  void<BR>
	 */
	void saveQuestionAnswer(CourseQuestionReplyBean questionReply) throws DataBaseException;
	
	/**
	 * Method name: saveReplyAnswer <BR>
	 * Description: 保存回复的回复  <BR>
	 * Remark: <BR>
	 * @param replyId 回复id
	 * @param replyContent 回复内容
	 * @param replyText 回复富文本
	 * @param userId 用户id
	 * @throws DataBaseException  void<BR>
	 */
	void saveReplyAnswer(Integer replyId, String replyContent, String replyText, Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getCourseWare <BR>
	 * Description: 根据id获取课件bean <BR>
	 * Remark: <BR>
	 * @param courseWareId 课件id
	 * @return ResCoursewareBean
	 * @throws DataBaseException <BR>
	 */
	ResCoursewareBean getCourseWare(Integer courseWareId) throws DataBaseException;
	
	/**
	 * Method name: getCourseNoteCountByUserIdCourseId <BR>
	 * Description: 根据用户id和课程id查询笔记数量 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	Integer getCourseNoteCountByUserIdCourseId(String userId, String courseId) throws Exception;
	
	/**
	 * Method name: getCourseNotesByUserIdCourseId <BR>
	 * Description: 根据用户id和课程id查询笔记 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<CourseNoteBean>
	 * @throws Exception  <BR>
	 */
	List<CourseNoteBean> getCourseNotesByUserIdCourseId(String userId,
			String courseId, Integer fromNum, String pageSize) throws Exception;
	
}
