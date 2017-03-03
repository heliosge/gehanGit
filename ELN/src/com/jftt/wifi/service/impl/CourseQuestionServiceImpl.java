/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseQuestionServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-22        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.CourseQuestionVo;
import com.jftt.wifi.bean.vo.CourseWareIdTypeVo;
import com.jftt.wifi.bean.vo.MyQuestionVo;
import com.jftt.wifi.bean.vo.QuestionReplyVo;
import com.jftt.wifi.dao.CourseQuestionReplyDaoMapper;
import com.jftt.wifi.dao.CourseQuestionTopicDaoMapper;
import com.jftt.wifi.dao.ManagePostDaoMapper;
import com.jftt.wifi.dao.ResCourseDaoMapper;
import com.jftt.wifi.dao.ResSectionDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseQuestionService;
import com.jftt.wifi.service.ManageUserService;

/**
 * class name:CourseQuestionServiceImpl <BR>
 * class description: 我的问答service <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-22
 * @author JFTT)HeTianrui
 */
@Service("courseQuestionService")
public class CourseQuestionServiceImpl implements CourseQuestionService{
	@Resource(name="courseQuestionTopicDaoMapper")
	private CourseQuestionTopicDaoMapper  courseQuestionTopicDaoMapper;
	@Resource(name="courseQuestionReplyDaoMapper")
	private CourseQuestionReplyDaoMapper  courseQuestionReplyDaoMapper;
	@Resource(name="resSectionDaoMapper")
	private ResSectionDaoMapper resSectionDaoMapper;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	@Resource(name="managePostDaoMapper")
	private ManagePostDaoMapper managePostDaoMapper;
	@Resource(name="resCourseDaoMapper")
	private ResCourseDaoMapper resCourseDaoMapper;
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseQuestionService#getAskQuestionsCount(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: getAskQuestionsCount <BR>
	 * Description: 获取学员提出问题的数目 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param asktimeStart 查询开始时间
	 * @param asktimeEnd 查询结束时间
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getAskQuestionsCount(Integer userId, String courseName,
			String asktimeStart, String asktimeEnd) throws Exception {
		//转换时间参数
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date asktimeStartDate = null;
		Date asktimeEndDate = null;
		if(asktimeStart != null && asktimeStart != ""){
			asktimeStartDate = formater.parse(asktimeStart);
		}
		if(asktimeEnd != null && asktimeEnd != ""){
			asktimeEndDate = formater.parse(asktimeEnd);
		}
		//获取查询结果
		return courseQuestionTopicDaoMapper.getAskQuestionsCount(userId,courseName,asktimeStartDate,asktimeEndDate);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseQuestionService#getAskQuestions(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getAskQuestions <BR>
	 * Description: 获取学员提出的问题 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param asktimeStart 查询开始时间
	 * @param asktimeEnd 查询结束时间
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<MyQuestionVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<MyQuestionVo> getAskQuestions(Integer userId,String courseName, String asktimeStart, String asktimeEnd,
			String sortName,String sortOrder,Integer fromNum,Integer pageSize) throws Exception {
		//转换时间参数
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date asktimeStartDate = null;
		Date asktimeEndDate = null;
		if(asktimeStart != null && asktimeStart != ""){
			asktimeStartDate = formater.parse(asktimeStart);
		}
		if(asktimeEnd != null && asktimeEnd != ""){
			asktimeEndDate = formater.parse(asktimeEnd);
		}
		//获取查询结果
		return courseQuestionTopicDaoMapper.getAskQuestions(userId,courseName,asktimeStartDate,asktimeEndDate,
				sortName,sortOrder,fromNum,pageSize);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseQuestionService#getDoubleQuestions(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getDoubleQuestions <BR>
	 * Description: 获取该课程的本学员前两个问题 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return List<String>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<String> getDoubleQuestions(Integer courseId, Integer userId) throws DataBaseException {
		return courseQuestionTopicDaoMapper.getDoubleQuestions(courseId,userId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseQuestionService#getReplyQuestionsCount(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: getReplyQuestionsCount <BR>
	 * Description: 获取回答问题的课程数量 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param asktimeStart 查询开始时间
	 * @param asktimeEnd 查询结束时间
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getReplyQuestionsCount(Integer userId, String courseName,
			String asktimeStart, String asktimeEnd) throws Exception {
		//转换时间参数
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date asktimeStartDate = null;
		Date asktimeEndDate = null;
		if(asktimeStart != null && asktimeStart != ""){
			asktimeStartDate = formater.parse(asktimeStart);
		}
		if(asktimeEnd != null && asktimeEnd != ""){
			asktimeEndDate = formater.parse(asktimeEnd);
		}
		//获取查询结果
		return courseQuestionReplyDaoMapper.getReplyQuestionsCount(userId,courseName,asktimeStartDate,asktimeEndDate);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseQuestionService#getReplyQuestions(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getReplyQuestions <BR>
	 * Description:  获取回答问题的课程 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param asktimeStart 查询开始时间
	 * @param asktimeEnd 查询结束时间
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<MyQuestionVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<MyQuestionVo> getReplyQuestions(Integer userId,String courseName, String asktimeStart, String asktimeEnd,
			String sortName, String sortOrder, Integer fromNum, Integer pageSize)throws Exception {
		//转换时间参数
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date asktimeStartDate = null;
		Date asktimeEndDate = null;
		if(asktimeStart != null && asktimeStart != ""){
			asktimeStartDate = formater.parse(asktimeStart);
		}
		if(asktimeEnd != null && asktimeEnd != ""){
			asktimeEndDate = formater.parse(asktimeEnd);
		}
		//获取查询结果
		return courseQuestionReplyDaoMapper.getReplyQuestions(userId,courseName,asktimeStartDate,asktimeEndDate,
				sortName,sortOrder,fromNum,pageSize);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseQuestionService#getReplyDoubleQuestions(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getReplyDoubleQuestions <BR>
	 * Description: 获取这门课程的本学员回答所在的前两个问题 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return List<String>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<String> getReplyDoubleQuestions(Integer courseId, Integer userId)
			throws DataBaseException {
		return courseQuestionReplyDaoMapper.getReplyDoubleQuestions(courseId,userId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseQuestionService#getWareIdType(java.lang.Integer, java.lang.Integer, java.lang.String) <BR>
	 * Method name: getWareIdType <BR>
	 * Description: 获取课件id和type <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param questionType 提问类型
	 * @return CourseWareIdTypeVo
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public CourseWareIdTypeVo getWareIdType(Integer courseId, Integer userId,
			String questionType) throws DataBaseException {
		if("ask".equals(questionType)){
			//返回该课该学员提问的最近问题的courseWareId和courseWareType
			return courseQuestionReplyDaoMapper.getAskWareIdType(courseId,userId);
		}else if("reply".equals(questionType)){
			//返回该课该学员回答的最近问题的courseWareId和courseWareType
			return courseQuestionReplyDaoMapper.getReplyWareIdType(courseId,userId);
		}else{
			return null;
		}
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseQuestionService#getSectionIdByCourseIdWareId(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getSectionIdByCourseIdWareId <BR>
	 * Description: 根据课程id和课件id查询章节id <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param courseWareId 课件id
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getSectionIdByCourseIdWareId(Integer courseId,
			Integer courseWareId) throws DataBaseException {
		return resSectionDaoMapper.getSectionIdByCourseIdWareId(courseId,courseWareId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseQuestionService#getCourseQuestionsCount(java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getCourseQuestionsCount <BR>
	 * Description: 获取课程问答列表中问答数量 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getCourseQuestionsCount(Integer courseId,Integer companyId,Integer subCompanyId) throws Exception {
		return courseQuestionTopicDaoMapper.getCourseQuestionsCount(courseId, companyId, subCompanyId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseQuestionService#getQuestionsByCourseId(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getQuestionsByCourseId <BR>
	 * Description: 获取该课程所有问答 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return List<CourseQuestionVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<CourseQuestionVo> getQuestionsByCourseId(Integer courseId,
			Integer companyId, Integer subCompanyId, Integer page,
			Integer pageSize) throws Exception {
		Integer fromNum = page * pageSize;//此处page从0开始
		List<CourseQuestionVo> questions = courseQuestionTopicDaoMapper.getQuestionsByCourseId(
				courseId,companyId,subCompanyId,fromNum,pageSize);
		//遍历questions，将用户的头像，用户名和岗位放入其中
		ManageUserBean userBean = null;
		String postName = null;
		for (int i = 0; i < questions.size(); i++) {
			userBean = manageUserService.findUserById(String.valueOf(questions.get(i).getUserId()));
			questions.get(i).setUserName(userBean.getName());
			questions.get(i).setUserPic(userBean.getHeadPhoto());
			if(userBean.getPostId() != null){
				postName = managePostDaoMapper.getById(userBean.getPostId()).getName();
			}
			questions.get(i).setUserPost(postName);
		}
		return questions;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseQuestionService#getQuestionReplys(java.lang.Integer) <BR>
	 * Method name: getQuestionReplys <BR>
	 * Description: 获取问题所有回复 <BR>
	 * Remark: <BR>
	 * @param topicId 问题id
	 * @return List<QuestionReplyVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<QuestionReplyVo> getQuestionReplys(Integer topicId)
			throws Exception {
		List<QuestionReplyVo> replys = courseQuestionReplyDaoMapper.getQuestionReplys(topicId);
		//遍历replys，将用户的姓名和岗位放入其中
		ManageUserBean userBean = null;
		String postName = null;
		for (int i = 0; i < replys.size(); i++) {
			userBean = manageUserService.findUserById(String.valueOf(replys.get(i).getUserId()));
			replys.get(i).setUserName(userBean.getName());
			if(userBean.getPostId() != null){
				postName = managePostDaoMapper.getById(userBean.getPostId()).getName();
			}
			replys.get(i).setUserPost(postName);
		}
		return replys;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseQuestionService#getCourseNameById(java.lang.Integer) <BR>
	 * Method name: getCourseNameById <BR>
	 * Description: 根据课程id查询课程名称 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return String
	 * @throws Exception  <BR>
	 */
	@Override
	public String getCourseNameById(Integer courseId) throws Exception {
		return resCourseDaoMapper.getById(courseId).getName();
	}
	
}
