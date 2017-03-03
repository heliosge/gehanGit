/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseNoteAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.CourseNoteBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.MyNoteDetailVo;
import com.jftt.wifi.bean.vo.MyNoteVo;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseNoteService;

/**
 * class name:courseNoteAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)HeTianrui
 */
@Controller
@RequestMapping("courseNoteAction")
public class CourseNoteAction {
	private static Logger logger = Logger.getLogger(CourseNoteAction.class);
	
	@Resource(name="courseNoteService")
	private CourseNoteService courseNoteService;
	
	/**
	 * Method name: toCourseNote <BR>
	 * Description: 跳转到课程笔记页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toCourseNote")
	public String toCourseNote(HttpServletRequest request){
		return "studentCourse/myNote";
	}
	
	/**
	 * Method name: getMyNotes <BR>
	 * Description: 获取我的笔记 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param courseName 课程名称
	 * @param createTimeStart 查询开始时间
	 * @param createTimeEnd 查询结束时间
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getMyNotes")
	@ResponseBody
	public Object getMyNotes(HttpServletRequest request,
			Integer userId,Integer companyId,Integer subCompanyId,
			String courseName,String createTimeStart,String createTimeEnd,
			String sortName,String sortOrder,Integer page,Integer pageSize){
		Integer fromNum = 0;
		if(page != null && pageSize != null){
			fromNum = page * pageSize;
		}
		try {
			logger.info("查询学员"+userId+"的笔记");
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date createTimeStartTime = null;
			Date createTimeEndTime = null;
			if(createTimeStart != null && createTimeStart.trim() != ""){
				createTimeStartTime = formater.parse(createTimeStart);
			}
			if(createTimeEnd != null && createTimeEnd.trim() != ""){
				createTimeEndTime = formater.parse(createTimeEnd);
			}
			List<MyNoteVo> myNotes = courseNoteService.getMyNotes(
					userId,companyId,subCompanyId,courseName,createTimeStartTime,createTimeEndTime,
					sortName,sortOrder,fromNum,pageSize);
			logger.info("查询学员"+userId+"的笔记结束");
			return myNotes;
		} catch (DataBaseException e) {
			logger.warn(CourseNoteAction.class,e);
		} catch (ParseException e) {
			logger.warn(CourseNoteAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getMyNotesCount <BR>
	 * Description: 获取我的笔记的数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param courseName 课程名称
	 * @param createTimeStart 查询开始时间
	 * @param createTimeEnd 查询结束时间
	 * @return  Integer<BR>
	 */
	@RequestMapping("getMyNotesCount")
	@ResponseBody
	public Integer getMyNotesCount(HttpServletRequest request,
			Integer userId,Integer companyId,Integer subCompanyId,
			String courseName,String createTimeStart,String createTimeEnd){
		Integer count = 0;
		try {
			logger.info("查询学员"+userId+"的笔记课程数量");
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date createTimeStartTime = null;
			Date createTimeEndTime = null;
			if(createTimeStart != null && createTimeStart.trim() != ""){
				createTimeStartTime = formater.parse(createTimeStart);
			}
			if(createTimeEnd != null && createTimeEnd.trim() != ""){
				createTimeEndTime = formater.parse(createTimeEnd);
			}
			count = courseNoteService.getMyNotesCount(userId,companyId,subCompanyId,courseName,createTimeStartTime,createTimeEndTime);
			logger.info("学员"+userId+"的笔记课程数量为"+count);
		} catch (DataBaseException e) {
			logger.warn(CourseNoteAction.class,e);
		} catch (ParseException e) {
			logger.warn(CourseNoteAction.class,e);
		}
		return count;
	}
	
	/**
	 * Method name: toNoteDetail <BR>
	 * Description: 跳转到笔记详情页面  <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @return  String<BR>
	 */
	@RequestMapping("toNoteDetail")
	public String toNoteDetail(HttpServletRequest request,Integer courseId){
		request.setAttribute("courseId", courseId);
		return "studentCourse/noteDetail";
	}
	
	/**
	 * Method name: getCourseDetail <BR>
	 * Description: 获取笔记的课程详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @return  ResCourseBean<BR>
	 */
	@RequestMapping("getCourseDetail")
	@ResponseBody
	public ResCourseBean getCourseDetail(HttpServletRequest request,Integer courseId){
		try {
			logger.info("查询课程"+courseId+"详情");
			ResCourseBean course = courseNoteService.getCourseDetail(courseId);
			logger.info("查询课程"+courseId+"详情结束");
			return course;
		} catch (DataBaseException e) {
			logger.warn(CourseNoteAction.class,e);
		}
		return null;
	}
	
	
	/**
	 * Method name: getNotesDetail <BR>
	 * Description: 获取笔记详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getNotesDetail")
	@ResponseBody
	public Object getNotesDetail(HttpServletRequest request,
			Integer courseId,Integer userId,Integer companyId,Integer subCompanyId,
			Integer page, Integer pageSize){
		Integer fromNum = page * pageSize;
		try {
			logger.info("查询学员"+userId+"课程"+courseId+"的笔记详情");
			List<MyNoteDetailVo> noteDetails = courseNoteService.getNotesDetail(courseId,userId,companyId,subCompanyId,fromNum,pageSize);
			logger.info("查询学员"+userId+"课程"+courseId+"的笔记详情结束");
			return noteDetails;
		} catch (Exception e) {
			logger.warn(CourseNoteAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getNotesDetailCount <BR>
	 * Description: 获取笔记详情数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Integer<BR>
	 */
	@RequestMapping("getNotesDetailCount")
	@ResponseBody
	public Integer getNotesDetailCount(HttpServletRequest request,
			Integer courseId,Integer userId,Integer companyId,Integer subCompanyId){
		Integer count = 0;
		try {
			logger.info("查询学员"+userId+"课程"+courseId+"的笔记详情数量");
			count = courseNoteService.getNotesDetailCount(courseId,userId,companyId,subCompanyId);
			logger.info("学员"+userId+"课程"+courseId+"的笔记详情数量为"+count);
		} catch (DataBaseException e) {
			logger.warn(CourseNoteAction.class,e);
		}
		return count;
	}
	
	/**
	 * Method name: deleteNote <BR>
	 * Description: 删除一条笔记 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param noteId 笔记id
	 * @return  Object<BR>
	 */
	@RequestMapping("deleteNote")
	@ResponseBody
	public Object deleteNote(HttpServletRequest request,Integer noteId){
		AjaxReturnVo<Object> arv = new AjaxReturnVo<Object>();
		try {
			logger.info("删除"+noteId+"笔记");
			courseNoteService.deleteNote(noteId);
			logger.info(noteId+"笔记删除成功");
		} catch (DataBaseException e) {
			logger.warn(CourseNoteAction.class,e);
		}
		return arv;
	}
	
	/**
	 * Method name: getEditDialog <BR>
	 * Description: 获取一条笔记详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param noteId 笔记id
	 * @return  Object<BR>
	 */
	@RequestMapping("getEditDialog")
	@ResponseBody
	public Object getEditDialog(HttpServletRequest request,Integer noteId){
		try {
			logger.info("根据笔记id查询笔记");
			CourseNoteBean noteBean = courseNoteService.getEditDialog(noteId);
			logger.info("根据笔记id查询笔记结束");
			return noteBean;
		} catch (DataBaseException e) {
			logger.warn(CourseNoteAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: saveNote <BR>
	 * Description: 更新一条笔记 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param noteId 笔记id
	 * @param content 笔记内容
	 * @return  Object<BR>
	 */
	@RequestMapping("saveNote")
	@ResponseBody
	public Object saveNote(HttpServletRequest request,Integer noteId,String content){
		AjaxReturnVo<Object> returnVo  = new AjaxReturnVo<Object>();
		try {
			logger.info("修改笔记"+noteId+"内容");
			courseNoteService.updateNote(noteId,content);
			logger.info("修改笔记"+noteId+"内容结束");
		} catch (DataBaseException e) {
			logger.warn(CourseNoteAction.class,e);
		}
		return returnVo;
	}
}
