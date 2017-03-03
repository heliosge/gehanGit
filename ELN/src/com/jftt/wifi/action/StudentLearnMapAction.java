/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: StudentLearnMapAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年9月4日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.CourseVoForPost;
import com.jftt.wifi.bean.vo.MyPostPromotionPathVo;
import com.jftt.wifi.bean.vo.PostExamVo;
import com.jftt.wifi.bean.vo.PromotionPathDetailVo;
import com.jftt.wifi.bean.vo.PromotionPathStageVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.StudentLearnMapService;

/**
 * class name:StudentLearnMapAction <BR>
 * class description: 学员学习地图action <BR>
 * Remark: <BR>
 * @version 1.00 2015年9月4日
 * @author JFTT)ShenHaijun
 */
@Controller
@RequestMapping("studentLearnMapAction")
public class StudentLearnMapAction {
	private static Logger logger = Logger.getLogger(StudentLearnMapAction.class);
	
	@Resource(name="studentLearnMapService")
	private StudentLearnMapService studentLearnMapService;
	
	@RequestMapping("toMyMap")
	public String toMyMap(HttpServletRequest request){
		return "studentLearnMap/myMap";
	}
	
	/**
	 * Method name: getMyPromotionPathCount <BR>
	 * Description: 获取我的所有晋升路径的数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param postId
	 * @return  Integer<BR>
	 */
	@RequestMapping("getMyPromotionPathCount")
	@ResponseBody
	public Integer getMyPromotionPathCount(HttpServletRequest request,Integer userId,Integer companyId,Integer subCompanyId){
		Integer count = 0;
		try {
			logger.info("查询学员"+userId+"的所有晋升路径数量");
			count = studentLearnMapService.getMyPromotionPathCount(userId,companyId,subCompanyId);
			logger.info("学员"+userId+"的所有晋升路径数量为"+count);
		} catch (DataBaseException e) {
			logger.warn(StudentLearnMapAction.class,e);
		}
		return count;
	}
	
	/**
	 * Method name: getMyPromotionPath <BR>
	 * Description: 获取我的所有晋升路径 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param postId
	 * @param companyId
	 * @param subCompanyId
	 * @param page
	 * @param pageSize
	 * @return  Object<BR>
	 */
	@RequestMapping("getMyPromotionPath")
	@ResponseBody
	public Object getMyPromotionPath(HttpServletRequest request,Integer userId,Integer companyId,Integer subCompanyId,
			Integer page,Integer pageSize){
		Integer fromNum = page * pageSize;//此处page从0开始
		try {
			logger.info("查询用户"+userId+"的所有晋升路径");
			List<MyPostPromotionPathVo> promotionPaths = studentLearnMapService.getMyPromotionPath(userId,companyId,subCompanyId,fromNum,pageSize);
			logger.info("查询用户"+userId+"的所有晋升路径完毕");
			return promotionPaths;
		} catch (DataBaseException e) {
			logger.warn(StudentLearnMapAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getPathStages <BR>
	 * Description: 获取该路径所有阶段和当前所处阶段 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param promotionPathId
	 * @param userId
	 * @return  Object<BR>
	 */
	@RequestMapping("getPathStages")
	@ResponseBody
	public Object getPathStages(HttpServletRequest request,Integer promotionPathId){
		try {
			logger.info("查询路径"+promotionPathId+"的所有阶段");
			List<PromotionPathStageVo> pathStages = studentLearnMapService.getPathStages(promotionPathId);
			logger.info("查询路径"+promotionPathId+"的所有阶段完毕");
			return pathStages;
		} catch (DataBaseException e) {
			logger.warn(StudentLearnMapAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: toPostDetail <BR>
	 * Description: 跳转到晋升路径阶段 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toPostDetail")
	public String toPostDetail(HttpServletRequest request,Integer orderNum,Integer stageId){
		request.setAttribute("orderNum", orderNum);
		request.setAttribute("stageId",stageId);
		//查出当前阶段对应的岗位id
		Integer postId = null;
		try {
			postId = studentLearnMapService.getPostIdByStageId(stageId);
		} catch (DataBaseException e) {
			logger.warn(StudentLearnMapAction.class,e);
		}
		request.setAttribute("thisPostId", postId);
		return "studentLearnMap/postDetail";
	}
	
	/**
	 * Method name: getStageTest <BR>
	 * Description: 获取阶段测试 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param stageId
	 * @return  Object<BR>
	 */
	@RequestMapping("getStageTest")
	@ResponseBody
	public Object getStageTest(HttpServletRequest request,Integer stageId,Integer userId){
		try {
			logger.info("查询阶段"+stageId+"测试");
			PostExamVo stageTest = studentLearnMapService.getStageTest(stageId,userId);
			logger.info("查询阶段"+stageId+"测试完毕");
			return stageTest;
		} catch (DataBaseException e) {
			logger.warn(StudentLearnMapAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getPostCoursesCount <BR>
	 * Description: 获取岗位课程数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param postId
	 * @return  Integer<BR>
	 */
	@RequestMapping("getPostCoursesCount")
	@ResponseBody
	public Integer getPostCoursesCount(HttpServletRequest request,Integer postId){
		Integer count = 0;
		try {
			logger.info("查询岗位"+postId+"课程数量");
			count = studentLearnMapService.getPostCoursesCount(postId);
			logger.info("岗位"+postId+"课程数量为"+count);
		} catch (DataBaseException e) {
			logger.warn(StudentLearnMapAction.class,e);
		}
		return count;
	}
	
	/**
	 * Method name: getPostCourses <BR>
	 * Description: 获取岗位所有课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param postId
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return  Object<BR>
	 */
	@RequestMapping("getPostCourses")
	@ResponseBody
	public Object getPostCourses(HttpServletRequest request,Integer postId,Integer userId,Integer page,Integer pageSize){
		try {
			logger.info("查询岗位"+postId+"所有课程");
			List<CourseVoForPost> postCourses = studentLearnMapService.getPostCourses(postId,userId,page,pageSize);
			logger.info("查询岗位"+postId+"所有课程完毕");
			return postCourses;
		} catch (DataBaseException e) {
			logger.warn(StudentLearnMapAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: toRoadDetail <BR>
	 * Description: 跳往路径详情页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toRoadDetail")
	public String toRoadDetail(HttpServletRequest request){
		return "studentLearnMap/roadDetail";
	}
	
	/**
	 * Method name: getRoadDetailsCount <BR>
	 * Description: 获取晋升路径详细数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @return  Integer<BR>
	 */
	@RequestMapping("getRoadDetailsCount")
	@ResponseBody
	public Integer getRoadDetailsCount(HttpServletRequest request,Integer userId,String searchContent){
		Integer count = 0;
		try {
			logger.info("查询晋升路径"+userId+"详细数量");
			count = studentLearnMapService.getRoadDetailsCount(userId,searchContent);
			logger.info("晋升路径"+userId+"详细数量为"+count);
		} catch (DataBaseException e) {
			logger.warn(StudentLearnMapAction.class,e);
		}
		return count;
	}
	
	/**
	 * Method name: getRoadDetails <BR>
	 * Description: 获取路径详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param searchContent
	 * @param page
	 * @param pageSize
	 * @return  Object<BR>
	 */
	@RequestMapping("getRoadDetails")
	@ResponseBody
	public Object getRoadDetails(HttpServletRequest request,Integer userId,String searchContent,
			Integer page,Integer pageSize){
		try {
			logger.info("查询晋升路径"+userId+"详细");
			List<PromotionPathDetailVo> pathDetails = studentLearnMapService.getRoadDetails(userId,searchContent,page,pageSize);
			logger.info("查询晋升路径"+userId+"详细完毕");
			return pathDetails;
		} catch (DataBaseException e) {
			logger.warn(StudentLearnMapAction.class,e);
		}
		return null;
	}
	
	
	/**
	 * Method name: saveAssignInfo <BR>
	 * Description: 保存学习地图考试的考试分配表信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param examId
	 * @param userId
	 * @return  Object<BR>
	 */
	@RequestMapping("saveAssignInfo")
	@ResponseBody
	public Object saveAssignInfo(HttpServletRequest request,Integer examId,Integer userId){
		AjaxReturnVo<Object> returnVo = new AjaxReturnVo<Object>();
		try {
			logger.info("开始保存学习地图中"+userId+"的考试分配信息");
			studentLearnMapService.saveAssignInfo(examId,userId);
			logger.info("保存学习地图中"+userId+"的考试分配信息完毕");
		} catch (DataBaseException e) {
			returnVo.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(StudentLearnMapAction.class, e);
		}
		return returnVo;
	}
	
	/**
	 * Method name: getPostById <BR>
	 * Description: 根据id获取岗位 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param postId
	 * @return  Object<BR>
	 */
	@RequestMapping("getPostById")
	@ResponseBody
	public Object getPostById(HttpServletRequest request,Integer postId){
		try {
			logger.info("根据id"+postId+"开始查询岗位");
			ManagePostBean post = studentLearnMapService.getPostById(postId);
			logger.info("根据id"+postId+"结束查询岗位");
			return post;
		} catch (Exception e) {
			logger.warn(StudentLearnMapAction.class,e);
		}
		return null;
	}
}
