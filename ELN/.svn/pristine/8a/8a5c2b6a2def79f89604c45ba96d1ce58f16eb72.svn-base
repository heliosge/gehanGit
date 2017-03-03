/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: StudentInfoAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-22        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.vo.IndexShowVo;
import com.jftt.wifi.bean.vo.ResCourseVo;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.StudentInfoService;

/**
 * class name:StudentInfoAction <BR>
 * class description: 学员信息action <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-22
 * @author JFTT)ShenHaijun
 */
@Controller
@RequestMapping("studentInfoAction")
public class StudentInfoAction {
	private static Logger logger = Logger.getLogger(StudentInfoAction.class);
	
	@Resource(name="studentInfoService")
	private StudentInfoService studentInfoService;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: toStudentIndex <BR>
	 * Description: 跳转到学员首页 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toStudentIndex")
	public String toStudentIndex(HttpServletRequest request){
		return "studentIndex/studentIndex";
	}
	
	/**
	 * Method name: getMyPoint <BR>
	 * Description: 获取我的积分 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @return  Object<BR>
	 */
	@RequestMapping("getMyPoint")
	@ResponseBody
	public Object getMyPoint(HttpServletRequest request,String userId){
		if(userId != null && userId != ""){
			try {
				logger.info("开始查询用户"+userId+"的积分");
				Integer myPoint = studentInfoService.getMyPoint(Integer.parseInt(userId));
				logger.info("用户"+userId+"的积分为"+myPoint);
				return myPoint;
			} catch (DataBaseException e) {
				logger.warn(StudentInfoAction.class,e);
			}
		}
		return null;
	}
	
	/**
	 * Method name: getMyStudyHours <BR>
	 * Description: 获取我的学时 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @return  Object<BR>
	 */
	@RequestMapping("getMyStudyHours")
	@ResponseBody
	public Object getMyStudyHours(HttpServletRequest request,String userId){
		if(userId != null && userId != ""){
			try {
				logger.info("开始查询用户"+userId+"的学时");
				Integer myStudyHours = studentInfoService.getMyStudyHours(Integer.parseInt(userId));
				logger.info("用户"+userId+"的学时为"+myStudyHours);
				return myStudyHours;
			} catch (NumberFormatException e) {
				logger.warn(StudentInfoAction.class,e);
			} catch (DataBaseException e) {
				logger.warn(StudentInfoAction.class,e);
			}
		}
		return null;
	}
	
	/**
	 * Method name: getMyExamCount <BR>
	 * Description: 获取我的待参与考试数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @return  Object<BR>
	 */
	@RequestMapping("getMyExamCount")
	@ResponseBody
	public Object getMyExamCount(HttpServletRequest request,String userId){
		if(userId != null && userId != ""){
			try {
				logger.info("开始查询用户"+userId+"的待参与考试数量");
				Integer myExamCount = studentInfoService.getMyExamCount(Integer.parseInt(userId));
				logger.info("用户"+userId+"的待参与考试数量为"+myExamCount);
				return myExamCount;
			} catch (NumberFormatException e) {
				logger.warn(StudentInfoAction.class,e);
			} catch (DataBaseException e) {
				logger.warn(StudentInfoAction.class,e);
			}
		}
		return null;
	}
	
	/**
	 * Method name: getCompanyUserCount <BR>
	 * Description: 获取公司人数 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId
	 * @param subCompanyId
	 * @return  Object<BR>
	 */
	@RequestMapping("getCompanyUserCount")
	@ResponseBody
	public Object getCompanyUserCount(HttpServletRequest request,Integer companyId,Integer subCompanyId){
		if(companyId != null  && subCompanyId != null){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("companyId", companyId);
			paramMap.put("subCompanyId", subCompanyId);
			try {
				logger.info("开始根据公司"+companyId+"子公司"+subCompanyId+"查询用户数量");
				Integer userCount = manageUserService.findUserCountByCondition(paramMap);
				logger.info("用户数量为"+userCount);
				return userCount;
			} catch (Exception e) {
				logger.warn(StudentInfoAction.class,e);
			}
		}
		return null;
	}
	
	/**
	 * Method name: getCourseCount <BR>
	 * Description: 获取课程数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId
	 * @param subCompanyId
	 * @return  Object<BR>
	 */
	@RequestMapping("getCourseCount")
	@ResponseBody
	public Object getCourseCount(HttpServletRequest request,String companyId,String subCompanyId){
		if(companyId != null && companyId != "" && subCompanyId != null && subCompanyId != ""){
			try {
				logger.info("开始根据公司"+companyId+"子公司"+subCompanyId+"查询课程数量");
				Integer courseCount = studentInfoService.getCourseCount(Integer.parseInt(companyId),
						Integer.parseInt(subCompanyId));
				logger.info("课程数量为"+courseCount);
				return courseCount;
			} catch (DataBaseException e) {
				logger.warn(StudentInfoAction.class,e);
			}
		}
		return null;
	}
	
	/**
	 * Method name: getExamCount <BR>
	 * Description: 获取考试数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId
	 * @param subCompanyId
	 * @return  Object<BR>
	 */
	@RequestMapping("getExamCount")
	@ResponseBody
	public Object getExamCount(HttpServletRequest request,Integer companyId,Integer subCompanyId,Integer userId){
		if(companyId != null  && subCompanyId != null){
			try {
				logger.info("开始根据公司"+companyId+"子公司"+subCompanyId+"查询考试数量");
				Integer examCount = studentInfoService.getExamCount(companyId,
						subCompanyId,userId);
				logger.info("考试数量为"+examCount);
				return examCount;
			} catch (DataBaseException e) {
				logger.warn(StudentInfoAction.class,e);
			}
		}
		return null;
	}
	
	/**
	 * Method name: getSpecialTopicCount <BR>
	 * Description: 获取专题数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId
	 * @param subCompanyId
	 * @return  Object<BR>
	 */
	@RequestMapping("getSpecialTopicCount")
	@ResponseBody
	public Object getSpecialTopicCount(HttpServletRequest request,String companyId,String subCompanyId){
		if(companyId != null && companyId != "" && subCompanyId != null && subCompanyId != ""){
			try {
				logger.info("开始根据公司"+companyId+"子公司"+subCompanyId+"查询专题数量");
				Integer specialTopicCount = studentInfoService.getSpecialTopicCount(Integer.parseInt(companyId),
						Integer.parseInt(subCompanyId));
				logger.info("专题数量为"+specialTopicCount);
				return specialTopicCount;
			} catch (NumberFormatException e) {
				logger.warn(StudentInfoAction.class,e);
			} catch (DataBaseException e) {
				logger.warn(StudentInfoAction.class,e);
			}
		}
		return null;
	}
	
	/**
	 * Method name: getSpreadBlocks <BR>
	 * Description: 获取学员首页推广模块 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId
	 * @param subCompanyId
	 * @return  Object<BR>
	 */
	@RequestMapping("getSpreadBlocks")
	@ResponseBody
	public Object getSpreadBlocks(HttpServletRequest request,String companyId,String subCompanyId){
		if(companyId != null && companyId != "" && subCompanyId != null && subCompanyId != ""){
			try {
				logger.info("开始根据公司"+companyId+"子公司"+subCompanyId+"查询学员首页推广模块");
				List<IndexShowVo> spreadBlocks = studentInfoService.getSpreadBlocks(Integer.parseInt(companyId),
						Integer.parseInt(subCompanyId));
				logger.info("查询学员首页推广模块完毕");
				return spreadBlocks;
			} catch (NumberFormatException e) {
				logger.warn(StudentInfoAction.class,e);
			} catch (Exception e) {
				logger.warn(StudentInfoAction.class,e);
			}
		}
		return null;
	}
	
	/**
	 * Method name: getFeatureCourses <BR>
	 * Description: 获取精选课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId
	 * @param subCompanyId
	 * @return  Object<BR>
	 */
	@RequestMapping("getFeatureCourses")
	@ResponseBody
	public Object getFeatureCourses(HttpServletRequest request,String companyId,String subCompanyId){
		if(companyId != null && companyId != "" && subCompanyId != null && subCompanyId != ""){
			try {
				logger.info("查询公司"+companyId+"子公司"+subCompanyId+"精选课程");
				List<ResCourseVo> courses = studentInfoService.getFeatureCourses(
						Integer.parseInt(companyId),Integer.parseInt(subCompanyId));
				logger.info("查询精选课程结束");
				return courses;
			} catch (NumberFormatException e) {
				logger.warn(StudentInfoAction.class,e);
			} catch (DataBaseException e) {
				logger.warn(StudentInfoAction.class,e);
			}
		}
		return null;
	}
	
	/**shenhaijun end*/
	
}
