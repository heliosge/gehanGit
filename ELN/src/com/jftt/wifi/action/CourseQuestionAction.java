/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseQuestionAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-22        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.vo.CourseQuestionVo;
import com.jftt.wifi.bean.vo.CourseWareIdTypeVo;
import com.jftt.wifi.bean.vo.MyQuestionVo;
import com.jftt.wifi.bean.vo.QuestionReplyVo;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseQuestionService;

/**
 * class name:CourseQuestionAction <BR>
 * class description: 学员问题action <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-22
 * @author JFTT)HeTianrui
 */
@Controller
@RequestMapping("courseQuestionAction")
public class CourseQuestionAction {
	private static Logger logger = Logger.getLogger(CourseQuestionAction.class);
	
	@Resource(name="courseQuestionService") 
	private CourseQuestionService courseQuestionService;
	
	/**
	 * Method name: toMyQuestions <BR>
	 * Description: 跳到我的问答页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toMyQuestions")
	public String toMyQuestions(HttpServletRequest request){
		return "studentCourse/myQuestion";
	}
	
	/**
	 * Method name: getAskQuestions <BR>
	 * Description: 获取学员提出的问题 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param asktimeStart 提问开始时间
	 * @param asktimeEnd 提问结束时间
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getAskQuestions")
	@ResponseBody
	public Object getAskQuestions(HttpServletRequest request,Integer userId,String courseName,String asktimeStart,String asktimeEnd,
			String sortName,String sortOrder,Integer page,Integer pageSize){
		Integer fromNum = page * pageSize;
		try {
			logger.info("查询学员"+userId+"的提出问题");
			List<MyQuestionVo> questionCourses = courseQuestionService.getAskQuestions(userId,courseName,
					asktimeStart,asktimeEnd,sortName,sortOrder,fromNum,pageSize);
			logger.info("查询学员"+userId+"的提出问题完毕");
			return questionCourses;
		} catch (Exception e) {
			logger.warn(CourseQuestionAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getAskQuestionsCount <BR>
	 * Description: 获取提出问题的课程数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param asktimeStart 提问开始时间
	 * @param asktimeEnd 提问结束时间
	 * @return  Integer<BR>
	 */
	@RequestMapping("getAskQuestionsCount")
	@ResponseBody
	public Integer getAskQuestionsCount(HttpServletRequest request,Integer userId,String courseName,String asktimeStart,String asktimeEnd){
		Integer count = 0;
		try {
			logger.info("查询"+userId+"提出的问题课程数量");
			count = courseQuestionService.getAskQuestionsCount(userId,courseName,asktimeStart,asktimeEnd);
			logger.info(""+userId+"提出的问题课程数量为"+count);
		} catch (Exception e) {
			logger.warn(CourseQuestionAction.class,e);
		}
		return count;
	}
	
	/**
	 * Method name: getDoubleQuestions <BR>
	 * Description: 获取该课程的本学员前两个问题 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return  Object<BR>
	 */
	@RequestMapping("getDoubleQuestions")
	@ResponseBody
	public Object getDoubleQuestions(HttpServletRequest request,Integer courseId,Integer userId){
		try {
			logger.info("查询课程"+courseId+"的前两个问题");
			List<String> questions = courseQuestionService.getDoubleQuestions(courseId,userId);
			logger.info("查询课程"+courseId+"的前两个问题完毕");
			return questions;
		} catch (DataBaseException e) {
			logger.warn(CourseQuestionAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getReplyQuestions <BR>
	 * Description: 获取回答问题的课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param asktimeStart 提问开始时间
	 * @param asktimeEnd 提问结束时间
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getReplyQuestions")
	@ResponseBody
	public Object getReplyQuestions(HttpServletRequest request,Integer userId,String courseName,String asktimeStart,String asktimeEnd,
			String sortName,String sortOrder,Integer page,Integer pageSize){
		Integer fromNum = page * pageSize;
		try {
			logger.info("查询学员"+userId+"回答问题的课程");
			List<MyQuestionVo> questionCourses = courseQuestionService.getReplyQuestions(userId,courseName,asktimeStart,asktimeEnd,
					sortName,sortOrder,fromNum,pageSize);
			logger.info("查询学员"+userId+"回答问题的课程完毕");
			return questionCourses;
		} catch (Exception e) {
			logger.warn(CourseQuestionAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getReplyQuestionsCount <BR>
	 * Description: 获取回答问题的课程数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param courseName 课程名称
	 * @param asktimeStart 提问开始时间
	 * @param asktimeEnd 提问结束时间
	 * @return  Integer<BR>
	 */
	@RequestMapping("getReplyQuestionsCount")
	@ResponseBody
	public Integer getReplyQuestionsCount(HttpServletRequest request,Integer userId,String courseName,String asktimeStart,String asktimeEnd){
		Integer count = 0;
		try {
			logger.info("查询学员"+userId+"回答问题的课程数量");
			count = courseQuestionService.getReplyQuestionsCount(userId,courseName,asktimeStart,asktimeEnd);
			logger.info("学员"+userId+"回答问题的课程数量为"+count);
		} catch (Exception e) {
			logger.warn(CourseQuestionAction.class,e);
		}
		return count;
	}
	
	/**
	 * Method name: getReplyDoubleQuestions <BR>
	 * Description: 获取这门课程的本学员回答所在的前两个问题 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return  Object<BR>
	 */
	@RequestMapping("getReplyDoubleQuestions")
	@ResponseBody
	public Object getReplyDoubleQuestions(HttpServletRequest request,Integer courseId,Integer userId){
		try {
			logger.info("查询"+userId+"回答的课程"+courseId+"的最近两个问题");
			List<String> questions = courseQuestionService.getReplyDoubleQuestions(courseId,userId);
			logger.info("查询"+userId+"回答的课程"+courseId+"的最近两个问题完毕");
			return questions;
		} catch (DataBaseException e) {
			logger.warn(CourseQuestionAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getWareIdType <BR>
	 * Description: 获取课件id和type <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param questionType 问题类型（提出的问题还是回答的问题）
	 * @return  Object<BR>
	 */
	@RequestMapping("getWareIdType")
	@ResponseBody
	public Object getWareIdType(HttpServletRequest request,Integer courseId,Integer userId,String questionType){
		try {
			logger.info("查询课程"+courseId+"学员"+userId+"提出或回答的最近问题的courseWareId和courseWareType");
			CourseWareIdTypeVo idType = courseQuestionService.getWareIdType(courseId,userId,questionType);
			logger.info("查询课程"+courseId+"学员"+userId+"提出或回答的最近问题的courseWareId和courseWareType结束");
			return idType;
		} catch (DataBaseException e) {
			logger.warn(CourseQuestionAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getSectionIdByCourseIdWareId <BR>
	 * Description: 根据课程id和课件id查询章节id <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param courseWareId 课件id
	 * @return  Integer<BR>
	 */
	@RequestMapping("getSectionIdByCourseIdWareId")
	@ResponseBody
	public Integer getSectionIdByCourseIdWareId(HttpServletRequest request,Integer courseId,Integer courseWareId){
		Integer sectionId = null;
		try {
			logger.info("根据课程id"+courseId+"和课件id"+courseWareId+"查询章节id");
			sectionId = courseQuestionService.getSectionIdByCourseIdWareId(courseId,courseWareId);
			logger.info("根据课程id"+courseId+"和课件id"+courseWareId+"查询章节id，查询结果为"+sectionId);
		} catch (DataBaseException e) {
			logger.warn(CourseQuestionAction.class,e);
		}
		return sectionId;
	}
	
	/**
	 * Method name: toQuestionDetail <BR>
	 * Description: 跳转到问答详情页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @return  String<BR>
	 */
	@RequestMapping("toQuestionDetail")
	public String toQuestionDetail(HttpServletRequest request,Integer courseId){
		request.setAttribute("courseId", courseId);
		String courseName = null;
		try {
			courseName = courseQuestionService.getCourseNameById(courseId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("courseName", courseName);
		return "studentCourse/questionDetail";
	}
	
	/**
	 * Method name: getCourseQuestionsCount <BR>
	 * Description: 获取列表中的问题数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Integer<BR>
	 */
	@RequestMapping("getCourseQuestionsCount")
	@ResponseBody
	public Integer getCourseQuestionsCount(HttpServletRequest request,Integer courseId,Integer companyId,Integer subCompanyId){
		try {
			logger.warn("开始查询课程"+courseId+"的问题数量");
			Integer questionsCount = courseQuestionService.getCourseQuestionsCount(courseId,companyId,subCompanyId);
			logger.warn("课程"+courseId+"的问题数量为"+questionsCount);
			return questionsCount;
		} catch (Exception e) {
			logger.warn(CourseQuestionAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getQuestionsByCourseId <BR>
	 * Description: 获取该课程所有问答 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getQuestionsByCourseId")
	@ResponseBody
	public Object getQuestionsByCourseId(HttpServletRequest request,Integer courseId,Integer companyId,Integer subCompanyId,
			Integer page,Integer pageSize){
		try {
			logger.info("开始查询课程"+courseId+"的所有问题");
			List<CourseQuestionVo> questions = courseQuestionService.getQuestionsByCourseId(courseId,companyId,subCompanyId,
					page,pageSize);
			logger.info("结束查询课程"+courseId+"的所有问题");
			return questions;
		} catch (Exception e) {
			logger.warn(CourseQuestionAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getQuestionReplys <BR>
	 * Description: 获取问题所有回复 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param topicId 问题id
	 * @return  Object<BR>
	 */
	@RequestMapping("getQuestionReplys")
	@ResponseBody
	public Object getQuestionReplys(HttpServletRequest request,Integer topicId){
		try {
			logger.info("开始查询问题"+topicId+"的所有回复");
			List<QuestionReplyVo> replys = courseQuestionService.getQuestionReplys(topicId);
			logger.info("结束查询问题"+topicId+"的所有回复");
			return replys;
		} catch (Exception e) {
			logger.warn(CourseQuestionAction.class,e);
		}
		return null;
	}
	
}
