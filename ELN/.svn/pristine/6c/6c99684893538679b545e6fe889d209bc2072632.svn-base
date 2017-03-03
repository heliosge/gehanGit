/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseEvaluateAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-30        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.CourseEvaluationBean;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.CourseEvaluationVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseEvaluateService;
import com.jftt.wifi.service.IntegralManageService;

/**
 * class name:CourseEvaluateAction <BR>
 * class description: 课程评价action <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-30
 * @author JFTT)ShenHaijun
 */
@Controller
@RequestMapping("courseEvaluateAction")
public class CourseEvaluateAction {
	private static Logger logger = Logger.getLogger(CourseEvaluateAction.class);
	
	@Resource(name="courseEvaluateService")
	private CourseEvaluateService courseEvaluateService;
	@Resource(name="integralManageService")
	private IntegralManageService integralManageService;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getEvaluateCount <BR>
	 * Description: 获取该课程评价人数 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Integer<BR>
	 */
	@RequestMapping("getEvaluateCount")
	@ResponseBody
	public Integer getEvaluateCount(HttpServletRequest request,Integer courseId,Integer companyId,Integer subCompanyId){
		if(courseId != null && companyId != null && subCompanyId != null){
			try {
				logger.info("查询课程"+courseId+"的评价人数");
				Integer evaluateCount = courseEvaluateService.getEvaluateCount(courseId,companyId,subCompanyId);
				logger.info("课程"+courseId+"评价人数为"+evaluateCount);
				return evaluateCount;
			} catch (DataBaseException e) {
				logger.warn(CourseEvaluateAction.class,e);
			}
		}
		return null;
	}
	
	/**
	 * Method name: getCourseEvaluations <BR>
	 * Description: 获取该课程所有评价 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getCourseEvaluations")
	@ResponseBody
	public Object getCourseEvaluations(HttpServletRequest request,Integer courseId,
			Integer companyId,Integer subCompanyId,String page,String pageSize){
		if(courseId != null && companyId != null && subCompanyId != null 
				&& page != null && page != "" && pageSize != null && pageSize != ""){
			try {
				//注意page是从0开始的
				Integer fromNum = Integer.parseInt(page)*Integer.parseInt(pageSize);
				//分页查询
				logger.info("查询课程"+courseId+"的评价");
				List<CourseEvaluationVo> evaluations = courseEvaluateService.getCourseEvaluations(
						courseId,companyId,subCompanyId,fromNum,Integer.parseInt(pageSize));
				logger.info("查询课程"+courseId+"的评价结束");
				return evaluations;
			} catch (Exception e) {
				logger.warn(CourseEvaluateAction.class,e);
			}
		}
		return null;
	} 
	
	/**
	 * Method name: addCourseEvaluation <BR>
	 * Description: 添加一条课程评价 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseEvaluationBean 课程评价Bean
	 * @return  Object<BR>
	 */
	@RequestMapping("addCourseEvaluation")
	@ResponseBody
	public Object addCourseEvaluation(HttpServletRequest request,CourseEvaluationBean courseEvaluationBean){
		AjaxReturnVo<Object> arv = new AjaxReturnVo<Object>();
		try {
			logger.info("开始添加一条课程评价记录");
			courseEvaluateService.addCourseEvaluation(courseEvaluationBean);
			//设置积分（完成课程评估）
			integralManageService.handleIntegral(courseEvaluationBean.getUserId(), 7003);
			logger.info("添加完成课程评价记录");
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseEvaluateAction.class,e);
		}
		return arv;
	}
	
	/**
	 * Method name: getMyEvaluate <BR>
	 * Description: 获取我的课程评价 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @return  Object<BR>
	 */
	@RequestMapping("getMyEvaluate")
	@ResponseBody
	public Object getMyEvaluate(HttpServletRequest request,Integer userId,Integer courseId){
		try {
			logger.info("开始查询用户"+userId+"对课程"+courseId+"的评价");
			CourseEvaluationBean evaluation = courseEvaluateService.getMyEvaluate(userId,courseId);
			logger.info("结束查询用户"+userId+"对课程"+courseId+"的评价");
			return evaluation;
		} catch (Exception e) {
			logger.warn(CourseEvaluateAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: giveMyScore <BR>
	 * Description: 给出我的评分 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @param score 分数
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("giveMyScore")
	@ResponseBody
	public Object giveMyScore(HttpServletRequest request,Integer userId,Integer courseId,Integer score,
			Integer companyId, Integer subCompanyId){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("用户"+userId+"给出课程"+courseId+"评分");
			courseEvaluateService.giveMyScore(userId,courseId,score,companyId, subCompanyId);
			logger.info("用户"+userId+"给出课程"+courseId+"评分结束");
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseEvaluateAction.class,e);
		}
		return data;
	}
	
	/**
	 * Method name: giveMyEvaluation <BR>
	 * Description: 给出我的评价 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @param content 评价内容
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("giveMyEvaluation")
	@ResponseBody
	public Object giveMyEvaluation(HttpServletRequest request,Integer userId,Integer courseId,String content,
			Integer companyId, Integer subCompanyId){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("用户"+userId+"给出课程"+courseId+"评价");
			courseEvaluateService.giveMyEvaluation(userId,courseId,content,companyId,subCompanyId);
			logger.info("用户"+userId+"给出课程"+courseId+"评价结束");
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseEvaluateAction.class,e);
		}
		return data;
	}
	
	/**shenhaijun end*/
	
}
