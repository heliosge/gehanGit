/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseStudyAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月26日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.CourseNoteBean;
import com.jftt.wifi.bean.CourseQuestionReplyBean;
import com.jftt.wifi.bean.CourseQuestionTopicBean;
import com.jftt.wifi.bean.CourseWareRecordBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ResCoursewareBean;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.CourseNoteVoForStudy;
import com.jftt.wifi.bean.vo.CourseQuestionVoForStudy;
import com.jftt.wifi.bean.vo.CourseReplyVoForStudy;
import com.jftt.wifi.bean.vo.CourseStudyVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseRecordService;
import com.jftt.wifi.service.CourseStudyService;
import com.jftt.wifi.util.CommonUtil;

/**
 * class name:CourseStudyAction <BR>
 * class description: 课程学习action <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月26日
 * @author JFTT)ShenHaijun
 */
@Controller
@RequestMapping("courseStudyAction")
public class CourseStudyAction {
	private static Logger logger = Logger.getLogger(CourseStudyAction.class);
	@Resource(name="courseStudyService")
	private CourseStudyService courseStudyService;
	@Resource(name="courseRecordService")
	private CourseRecordService courseRecordService;
	
	/**
	 * Method name: toCourseStudy <BR>
	 * Description: 跳转到课程学习页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseWareId 课件id
	 * @param courseWareType 课件类型
	 * @param courseId 课程id
	 * @param sectionId 章节id
	 * @return  String<BR>
	 */
	@RequestMapping("toCourseStudy")
	public String toCourseStudy(HttpServletRequest request,Integer courseWareId,Integer courseWareType,Integer courseId,Integer sectionId){
		request.setAttribute("courseWareId", courseWareId);//课件id
		request.setAttribute("courseWareType", courseWareType);//课件类型
		request.setAttribute("courseId", courseId);//课程id
		request.setAttribute("sectionId", sectionId);//章节id
		return "studentCourse/courseStudy";
	}
	
	/**
	 * Method name: toCourseStudyNew <BR>
	 * Description: 跳转到课程学习页面（新） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseWareId 课件id
	 * @param courseWareType 课件类型
	 * @param courseId 课程id
	 * @param sectionId 章节id
	 * @param currentContent 上次学习的进度
	 * @return  String<BR>
	 */
	@RequestMapping("toCourseStudyNew")
	public String toCourseStudyNew(HttpServletRequest request,Integer courseWareId,Integer courseWareType,Integer courseId,Integer sectionId, String currentContent){
		//设置参数
		request.setAttribute("courseWareId", courseWareId);//课件id
		request.setAttribute("courseWareType", courseWareType);//课件类型
		request.setAttribute("courseId", courseId);//课程id
		request.setAttribute("sectionId", sectionId);//章节id
		request.setAttribute("currentContent", currentContent);//上次的学习进度
		//跳转页面
		return "studentCourse/courseStudyNew";
	}
	
	/**
	 * Method name: getCourseWare <BR>
	 * Description: 获取课件bean <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseWareId 课件id
	 * @return  Object<BR>
	 */
	@RequestMapping("getCourseWare")
	@ResponseBody
	public Object getCourseWare(HttpServletRequest request,Integer courseWareId){
		try {
			logger.info("查询课件"+courseWareId+"");
			ResCoursewareBean courseWareBean = courseStudyService.getCourseWare(courseWareId);
			logger.info("查询课件"+courseWareId+"完毕");
			return courseWareBean;
		} catch (DataBaseException e) {
			logger.warn(CourseStudyAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getCourseDetail <BR>
	 * Description: 获取该门课程详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param sectionId 章节id
	 * @param courseWareId 课件id
	 * @return  CourseStudyVo<BR>
	 */
	@RequestMapping("getCourseDetail")
	@ResponseBody
	public CourseStudyVo getCourseDetail(HttpServletRequest request,Integer courseId,Integer sectionId,Integer courseWareId){
		try {
			logger.info("查询课程的详细信息");
			CourseStudyVo courseStudyVo = courseStudyService.getCourseDetail(courseId,sectionId,courseWareId);
			logger.info("查询课程的详细信息完毕");
			return courseStudyVo;
		} catch (DataBaseException e) {
			logger.warn(CourseStudyAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getCourseNotes <BR>
	 * Description: 获取课程所有笔记 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getCourseNotes")
	@ResponseBody
	public Object getCourseNotes(HttpServletRequest request,
			Integer courseId,Integer companyId,Integer subCompanyId,Integer page,Integer pageSize){
		Integer fromNum = page * pageSize;
		try {
			logger.info("查询课程所有笔记");
			List<CourseNoteVoForStudy> notes = courseStudyService.getCourseNotes(
					courseId,companyId,subCompanyId,fromNum,pageSize);
			logger.info("查询课程所有笔记结束");
			return notes;
		} catch (Exception e) {
			logger.warn(CourseStudyAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getCourseNotesCount <BR>
	 * Description: 查询课程笔记数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Integer<BR>
	 */
	@RequestMapping("getCourseNotesCount")
	@ResponseBody
	public Integer getCourseNotesCount(HttpServletRequest request,Integer courseId,Integer companyId,Integer subCompanyId){
		Integer count = 0;
		try {
			logger.info("查询课程"+courseId+"笔记数量");
			count = courseStudyService.getCourseNotesCount(courseId,companyId,subCompanyId);
			logger.info("课程"+courseId+"笔记数量为"+count);
		} catch (DataBaseException e) {
			logger.warn(CourseStudyAction.class, e);
		}
		return count;
	}
	
	/**
	 * Method name: saveNote <BR>
	 * Description: 添加一条笔记 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param note 笔记Bean
	 * @return  Object<BR>
	 */
	@RequestMapping("saveNote")
	@ResponseBody
	public Object saveNote(HttpServletRequest request,CourseNoteBean note){
		AjaxReturnVo<Object> arv = new AjaxReturnVo<Object>();
		try {
			logger.info("添加一条笔记");
			courseStudyService.addNote(note);
			logger.info("笔记添加完毕");
		} catch (DataBaseException e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseStudyAction.class, e);
		}
		return arv;
	}
	
	/**
	 * Method name: getCourseQuestions <BR>
	 * Description: 获取课程的所有问题 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getCourseQuestions")
	@ResponseBody
	public Object getCourseQuestions(HttpServletRequest request,
			Integer courseId,Integer companyId,Integer subCompanyId,Integer page,Integer pageSize){
		Integer fromNum = page * pageSize;
		try {
			logger.info("查询课程"+courseId+"的所有问题");
			List<CourseQuestionVoForStudy> questions = courseStudyService.getCourseQuestions(
					courseId,companyId,subCompanyId,fromNum,pageSize);
			logger.info("查询课程"+courseId+"的所有问题完毕");
			return questions;
		} catch (Exception e) {
			logger.warn(CourseStudyAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getCourseQuestionsCount <BR>
	 * Description: 查询课程问题数量 <BR>
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
		Integer count = 0;
		try {
			logger.info("查询课程"+courseId+"的问题数量");
			count = courseStudyService.getCourseQuestionsCount(courseId,companyId,subCompanyId);
			logger.info("课程"+courseId+"的问题数量为"+count);
		} catch (DataBaseException e) {
			logger.warn(CourseStudyAction.class, e);
		}
		return count;
	}
	
	/**
	 * Method name: saveQuestion <BR>
	 * Description: 保存一个问题 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param question 问题Bean
	 * @return  Object<BR>
	 */
	@RequestMapping("saveQuestion")
	@ResponseBody
	public Object saveQuestion(HttpServletRequest request,CourseQuestionTopicBean question){
		AjaxReturnVo<Object> arv = new AjaxReturnVo<Object>();
		try {
			logger.info("新增一个提问");
			courseStudyService.saveQuestion(question);
			logger.info("新增一个提问完毕");
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseStudyAction.class, e);
		}
		return arv;
	}
	
	/**
	 * Method name: getReplys <BR>
	 * Description: 获取问题的所有回答 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param topicId 问题id
	 * @return  Object<BR>
	 */
	@RequestMapping("getReplys")
	@ResponseBody
	public Object getReplys(HttpServletRequest request,Integer topicId){
		try {
			logger.info("查询问题"+topicId+"的所有回答");
			List<CourseReplyVoForStudy> replys = courseStudyService.getReplys(topicId);
			logger.info("查询问题"+topicId+"的所有回答完毕");
			return replys;
		} catch (Exception e) {
			logger.warn(CourseStudyAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: saveQuestionAnswer <BR>
	 * Description: 保存问题回复  <BR>
	 * Remark: <BR>
	 * @param request
	 * @param questionReply 回复Bean
	 * @return  Object<BR>
	 */
	@RequestMapping("saveQuestionAnswer")
	@ResponseBody
	public Object saveQuestionAnswer(HttpServletRequest request,CourseQuestionReplyBean questionReply){
		AjaxReturnVo<Object> returnVo = new AjaxReturnVo<Object>();
		try {
			logger.info("保存一个问题"+questionReply.getTopicId()+"回复");
			courseStudyService.saveQuestionAnswer(questionReply);
			logger.info("保存一个问题"+questionReply.getTopicId()+"回复完毕");
		} catch (DataBaseException e) {
			logger.warn(CourseStudyAction.class, e);
		}
		return returnVo;
	}
	
	/**
	 * Method name: saveReplyAnswer <BR>
	 * Description: 保存回复的回复 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param replyId 回复id
	 * @param replyContent 回复内容
	 * @param replyText 所有回复内容（以富文本存储）
	 * @param userId 用户id
	 * @return  Object<BR>
	 */
	@RequestMapping("saveReplyAnswer")
	@ResponseBody
	public Object saveReplyAnswer(HttpServletRequest request,
			Integer replyId,String replyContent,String replyText,Integer userId){
		AjaxReturnVo<Object> returnVo = new AjaxReturnVo<Object>();
		try {
			logger.info("保存一个问题"+replyId+"回复");
			courseStudyService.saveReplyAnswer(replyId,replyContent,replyText,userId);
			logger.info("保存一个问题"+replyId+"回复完毕");
		} catch (DataBaseException e) {
			returnVo.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseStudyAction.class, e);
		}
		return returnVo;
	}
	
	/**
	 * Method name: getCourseWareByConditions <BR>
	 * Description: 根据条件查询课件学习记录 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param sectionId 章节id
	 * @param courseWareId 课件id
	 * @param userId 用户id
	 * @return  Object<BR>
	 */
	@RequestMapping("getCourseWareByConditions")
	@ResponseBody
	public Object getCourseWareByConditions(HttpServletRequest request,Integer sectionId,Integer courseWareId,Integer userId){
		try {
			logger.info("根据章节"+sectionId+"课件"+courseWareId+"用户"+userId+"查询课件记录");
			CourseWareRecordBean wareRecord = courseRecordService.getCourseWareByConditions(sectionId,courseWareId,userId);
			logger.info("根据章节"+sectionId+"课件"+courseWareId+"用户"+userId+"查询课件记录，查询完毕");
			return wareRecord;
		} catch (DataBaseException e) {
			logger.warn(CourseStudyAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: recordScromWareAfterLeavePage <BR>
	 * Description: 离开页面时记录scrom课件的学习进度 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param wareRecordId 课件记录id
	 * @param courseRecordId 课程记录id
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return  Object<BR>
	 */
	@RequestMapping("recordScromWareAfterLeavePage")
	@ResponseBody
	public Object recordScromWareAfterLeavePage(HttpServletRequest request,Integer wareRecordId,Integer courseRecordId,Integer courseId,Integer userId){
		AjaxReturnVo<Object> returnVo = new AjaxReturnVo<Object>();
		String[] total = request.getParameterValues("total[]");
		String[] studyed = request.getParameterValues("studyed[]");
		try {
			logger.info("开始记录课件"+wareRecordId+"的学习进度");
			courseRecordService.recordScromWareAfterLeavePage(wareRecordId,courseRecordId,courseId,userId,total,studyed);
			logger.info("开始记录课件"+wareRecordId+"的学习进度，记录完毕");
		} catch (Exception e) {
			returnVo.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseStudyAction.class,e);
		}
		return returnVo;
	}
	
	/**
	 * Method name: recordSwfWareAfterLeavePage <BR>
	 * Description: 离开页面时记录swf课件的学习进度 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param wareRecordId 课件记录id
	 * @param courseRecordId 课程记录id
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param totalPages 总共页数
	 * @param currPage 当前页
	 * @return  Object<BR>
	 */
	@RequestMapping("recordSwfWareAfterLeavePage")
	@ResponseBody
	public Object recordSwfWareAfterLeavePage(HttpServletRequest request,Integer wareRecordId,Integer courseRecordId,Integer courseId,Integer userId,String totalPages,String currPage){
		AjaxReturnVo<Object> returnVo = new AjaxReturnVo<Object>();
		try {
			logger.info("开始记录课件"+wareRecordId+"的学习进度");
			courseRecordService.recordSwfWareAfterLeavePage(wareRecordId,courseRecordId,courseId,userId,totalPages,currPage);
			logger.info("开始记录课件"+wareRecordId+"的学习进度，记录完毕");
		} catch (Exception e) {
			returnVo.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseStudyAction.class,e);
		}
		return returnVo;
	}
	
	/**
	 * Method name: recordVedioWareAfterLeavePage <BR>
	 * Description: 离开页面时记录视频课件的学习进度  <BR>
	 * Remark: <BR>
	 * @param request
	 * @param wareRecordId 课件记录id
	 * @param courseRecordId 课程记录id
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param totalTime 总共时长
	 * @param currentTime 当前时长
	 * @return  Object<BR>
	 */
	@RequestMapping("recordVedioWareAfterLeavePage")
	@ResponseBody
	public Object recordVedioWareAfterLeavePage(HttpServletRequest request,Integer wareRecordId,Integer courseRecordId,Integer courseId,Integer userId,String totalTime,String currentTime){
		AjaxReturnVo<Object> returnVo = new AjaxReturnVo<Object>();
		try {
			logger.info("开始记录课件"+wareRecordId+"的学习进度");
			courseRecordService.recordVedioWareAfterLeavePage(wareRecordId,courseRecordId,courseId,userId,totalTime,currentTime);
			logger.info("开始记录课件"+wareRecordId+"的学习进度，记录完毕");
		} catch (Exception e) {
			returnVo.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseStudyAction.class,e);
		}
		return returnVo;
	}
	
	/**
	 * Method name: getCourseNotesByUserIdCourseId <BR>
	 * Description: 根据用户id和课程id查询笔记 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getCourseNotesByUserIdCourseId")
	@ResponseBody
	public Object getCourseNotesByUserIdCourseId(HttpServletRequest request, String userId, String courseId,
			String page, String pageSize){
		logger.info("----getCourseNotesByUserIdCourseId----");
		MMGridPageVoBean<CourseNoteBean> mmGridVo = new MMGridPageVoBean<CourseNoteBean>();
		try {
			Integer count = courseStudyService.getCourseNoteCountByUserIdCourseId(userId,courseId);
			Integer fromNum = (int)CommonUtil.getFromNum(String.valueOf(pageSize), String.valueOf(page), count);
			List<CourseNoteBean> notes = courseStudyService.getCourseNotesByUserIdCourseId(userId,courseId,
					fromNum,pageSize);
			mmGridVo.setTotal(count);
			mmGridVo.setRows(notes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mmGridVo;
	}
	
}
