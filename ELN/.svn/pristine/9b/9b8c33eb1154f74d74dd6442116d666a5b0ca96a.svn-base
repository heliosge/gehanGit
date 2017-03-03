/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseStudyServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月28日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.CourseNoteBean;
import com.jftt.wifi.bean.CourseQuestionReplyBean;
import com.jftt.wifi.bean.CourseQuestionReplyTextBean;
import com.jftt.wifi.bean.CourseQuestionTopicBean;
import com.jftt.wifi.bean.ResCoursewareBean;
import com.jftt.wifi.bean.vo.CourseNoteVoForStudy;
import com.jftt.wifi.bean.vo.CourseQuestionVoForStudy;
import com.jftt.wifi.bean.vo.CourseReplyVoForStudy;
import com.jftt.wifi.bean.vo.CourseStudyVo;
import com.jftt.wifi.dao.CourseNoteDaoMapper;
import com.jftt.wifi.dao.CourseQuestionReplyDaoMapper;
import com.jftt.wifi.dao.CourseQuestionReplyTextDaoMapper;
import com.jftt.wifi.dao.CourseQuestionTopicDaoMapper;
import com.jftt.wifi.dao.ResCourseDaoMapper;
import com.jftt.wifi.dao.ResCoursewareDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseStudyService;
import com.jftt.wifi.service.ManageUserService;

/**
 * class name:CourseStudyServiceImpl <BR>
 * class description: 课程学习service <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月28日
 * @author JFTT)ShenHaijun
 */
@Service("courseStudyService")
public class CourseStudyServiceImpl implements CourseStudyService{
	
	@Resource(name="resCourseDaoMapper")
	private ResCourseDaoMapper resCourseDaoMapper;
	@Resource(name="courseNoteDaoMapper")
	private CourseNoteDaoMapper courseNoteDaoMapper;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	@Resource(name="courseQuestionTopicDaoMapper")
	private CourseQuestionTopicDaoMapper courseQuestionTopicDaoMapper;
	@Resource(name="courseQuestionReplyDaoMapper")
	private CourseQuestionReplyDaoMapper courseQuestionReplyDaoMapper;
	@Resource(name="courseQuestionReplyTextDaoMapper")
	private CourseQuestionReplyTextDaoMapper courseQuestionReplyTextDaoMapper;
	@Resource(name="resCoursewareDaoMapper")
	private ResCoursewareDaoMapper resCoursewareDaoMapper;
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseStudyService#getCourseDetail(java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getCourseDetail <BR>
	 * Description: 获取课程详情 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param sectionId 章节id
	 * @param courseWareId 课件id
	 * @return CourseStudyVo
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public CourseStudyVo getCourseDetail(Integer courseId,Integer sectionId,Integer courseWareId) throws DataBaseException {
		return resCourseDaoMapper.getCourseDetailForStudy(courseId,sectionId,courseWareId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseStudyService#getCourseNotesCount(java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getCourseNotesCount <BR>
	 * Description: 获取课程笔记数目 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer 
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getCourseNotesCount(Integer courseId, Integer companyId,
			Integer subCompanyId) throws DataBaseException {
		return courseNoteDaoMapper.getCourseNotesCount(courseId,companyId,subCompanyId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseStudyService#getCourseNotes(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getCourseNotes <BR>
	 * Description: 获取课程所有笔记 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<CourseNoteVoForStudy>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<CourseNoteVoForStudy> getCourseNotes(Integer courseId,
			Integer companyId, Integer subCompanyId, Integer fromNum,
			Integer pageSize) throws Exception {
		List<CourseNoteVoForStudy> notes = courseNoteDaoMapper.getCourseNotes(courseId,companyId,subCompanyId,fromNum,pageSize);
		//将笔记记录人姓名加入到集合中
		if(notes != null && notes.size() > 0){
			String userName = "";
			for (int i = 0; i < notes.size(); i++) {
				userName = manageUserService.findUserById(String.valueOf(notes.get(i).getUserId())).getName();
				notes.get(i).setUserName(userName);
			}
		}
		return notes;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseStudyService#addNote(com.jftt.wifi.bean.CourseNoteBean) <BR>
	 * Method name: addNote <BR>
	 * Description: 添加一条笔记 <BR>
	 * Remark: <BR>
	 * @param note 笔记Bean
	 * @throws DataBaseException  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addNote(CourseNoteBean note) throws DataBaseException {
		courseNoteDaoMapper.addMyNote(note);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseStudyService#getCourseQuestionsCount(java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getCourseQuestionsCount <BR>
	 * Description: 查询课程问题数量 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getCourseQuestionsCount(Integer courseId, Integer companyId,
			Integer subCompanyId) throws DataBaseException {
		return courseQuestionTopicDaoMapper.getCourseQuestionsCount(courseId,companyId,subCompanyId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseStudyService#getCourseQuestions(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getCourseQuestions <BR>
	 * Description: 获取课程的所有问题  <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<CourseQuestionVoForStudy>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<CourseQuestionVoForStudy> getCourseQuestions(Integer courseId,
			Integer companyId, Integer subCompanyId, Integer fromNum,
			Integer pageSize) throws Exception {
		List<CourseQuestionVoForStudy> questions = courseQuestionTopicDaoMapper.getCourseQuestions(courseId,companyId,subCompanyId,fromNum,pageSize);
		if(questions != null && questions.size() > 0){
			String userName = "";
			for (int i = 0; i < questions.size(); i++) {
				userName = manageUserService.findUserById(String.valueOf(questions.get(i).getUserId())).getName();
				questions.get(i).setUserName(userName);
			}
		}
		return questions;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseStudyService#saveQuestion(com.jftt.wifi.bean.CourseQuestionTopicBean) <BR>
	 * Method name: saveQuestion <BR>
	 * Description: 保存一个提问 <BR>
	 * Remark: <BR>
	 * @param question 问题Bean
	 * @throws DataBaseException  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveQuestion(CourseQuestionTopicBean question)
			throws Exception {
		courseQuestionTopicDaoMapper.addMyQuestion(question);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseStudyService#getReplys(java.lang.Integer) <BR>
	 * Method name: getReplys <BR>
	 * Description: 获取该问题的所有回答 <BR>
	 * Remark: <BR>
	 * @param topicId 问题id
	 * @return List<CourseReplyVoForStudy>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<CourseReplyVoForStudy> getReplys(Integer topicId)
			throws Exception {
		List<CourseReplyVoForStudy> replys = courseQuestionReplyDaoMapper.getReplys(topicId);
		if(replys != null && replys.size() > 0){
			String userName = "";
			for (int i = 0; i < replys.size(); i++) {
				userName = manageUserService.findUserById(String.valueOf(replys.get(i).getUserId())).getName();
				replys.get(i).setUserName(userName);
			}
		}
		return replys;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseStudyService#saveQuestionAnswer(com.jftt.wifi.bean.CourseQuestionReplyBean) <BR>
	 * Method name: saveQuestionAnswer <BR>
	 * Description: 保存问题的回复 <BR>
	 * Remark: <BR>
	 * @param questionReply 回复Bean
	 * @throws DataBaseException  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveQuestionAnswer(CourseQuestionReplyBean questionReply)
			throws DataBaseException {
		courseQuestionReplyDaoMapper.addMyReply(questionReply);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseStudyService#saveReplyAnswer(java.lang.Integer, java.lang.String) <BR>
	 * Method name: saveReplyAnswer <BR>
	 * Description: 保存回复的回复 <BR>
	 * Remark: <BR>
	 * @param replyId 回复id
	 * @param replyContent 回复内容
	 * @throws DataBaseException  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveReplyAnswer(Integer replyId, String replyContent, String replyText, Integer userId)
			throws DataBaseException {
		//添加该回复的富文本内容
		Integer id = courseQuestionReplyTextDaoMapper.getIdByReplyId(replyId);
		CourseQuestionReplyTextBean replyTextBean = new CourseQuestionReplyTextBean();
		if(id == null){
			replyTextBean.setReplyId(replyId);
			replyTextBean.setReplyContent(replyContent);
			courseQuestionReplyDaoMapper.addReplyContent(replyTextBean);
		}else{
			replyTextBean.setId(id);
			replyTextBean.setReplyContent(replyContent);
			courseQuestionReplyTextDaoMapper.updateReplyContent(replyTextBean);
		}
		//修改该回复的内容
		CourseQuestionReplyBean replyBean = new CourseQuestionReplyBean();
		replyBean.setId(replyId);//回复id
		replyBean.setUserId(userId);//回复人id
		replyBean.setContent(replyText);//回复内容
		replyBean.setAnswertime(new Date());//回复时间
		courseQuestionReplyDaoMapper.updateReply(replyBean);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseStudyService#getCourseWare(java.lang.Integer) <BR>
	 * Method name: getCourseWare <BR>
	 * Description: 根据id获取课件bean <BR>
	 * Remark: <BR>
	 * @param courseWareId 课件id
	 * @return ResCoursewareBean
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public ResCoursewareBean getCourseWare(Integer courseWareId) throws DataBaseException {
		return resCoursewareDaoMapper.getById(courseWareId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseStudyService#getCourseNoteCountByUserIdCourseId(java.lang.String, java.lang.String) <BR>
	 * Method name: getCourseNoteCountByUserIdCourseId <BR>
	 * Description: 根据用户id和课程id查询笔记数量 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getCourseNoteCountByUserIdCourseId(String userId,
			String courseId) throws Exception {
		return courseNoteDaoMapper.getCourseNoteCountByUserIdCourseId(userId,courseId);
	}
	
	/**
	 * Method name: getCourseNotesByUserIdCourseId <BR>
	 * Description: 根据用户id和课程id查询笔记 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<CourseNoteBean>
	 * @throws Exception  List<CourseNoteBean><BR>
	 */
	@Override
	public List<CourseNoteBean> getCourseNotesByUserIdCourseId(String userId,
			String courseId, Integer fromNum, String pageSize) throws Exception {
		return courseNoteDaoMapper.getCourseNotesByUserIdCourseId(userId,courseId,fromNum,pageSize);
	}
}
