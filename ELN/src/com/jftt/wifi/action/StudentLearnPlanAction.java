/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: StudentLearnPlanAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-31        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.LearnPlanStageCourseVo;
import com.jftt.wifi.bean.vo.LearnPlanStageVo;
import com.jftt.wifi.bean.vo.LearnPlanVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.StudentLearnPlanService;
import com.jftt.wifi.util.CommonUtil;

/**
 * class name:StudentLearnPlanAction <BR>
 * class description: 学员学习计划action <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-31
 * @author JFTT)ShenHaijun
 */
@Controller
@RequestMapping("studentLearnPlanAction")
public class StudentLearnPlanAction {
	private static Logger logger = Logger.getLogger(StudentLearnPlanAction.class);
	
	@Resource(name="studentLearnPlanService")
	private StudentLearnPlanService studentLearnPlanService;
	
	/**shenhaijun start*/
	
	@RequestMapping("toLearnPlan")
	public String toLearnPlan(HttpServletRequest request){
		return "studentLearnPlan/studentLearnPlan";
	}
	
	/**
	 * Method name: getLearnPlanCount <BR>
	 * Description: 获取学习计划数目 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param learnProcess
	 * @param searchText
	 * @return  Integer<BR>
	 */
	@RequestMapping("getLearnPlanCount")
	@ResponseBody
	public Integer getLearnPlanCount(HttpServletRequest request,
			Integer userId,Integer learnProcess,String searchText){
		if(userId != null && learnProcess != null){
			Integer count = 0;
			try {
				logger.info("查询学员"+userId+"学习计划数目");
				count = studentLearnPlanService.getLearnPlanCount(userId,learnProcess,searchText);
				logger.info("学员"+userId+"学习计划数目为"+count);
				return count;
			} catch (DataBaseException e) {
				logger.warn(StudentLearnPlanAction.class, e);
			}
		}
		return null;
	}
	
	/**
	 * Method name: getLearnPlansForPics <BR>
	 * Description: 获取学习计划（图片展现） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param learnProcess
	 * @param searchText
	 * @return  Object<BR>
	 */
	@RequestMapping("getLearnPlansForPics")
	@ResponseBody
	public Object getLearnPlansForPics(HttpServletRequest request,
			Integer userId,Integer learnProcess,String searchText,
			Integer page,Integer pageSize){
		if(userId != null && learnProcess != null){
			//注意page是从0开始的
			Integer fromNum = page * pageSize;
			try {
				logger.info("查询学员"+userId+"学习计划");
				List<LearnPlanVo> learnPlans = studentLearnPlanService.getLearnPlansByConditions(
						userId,learnProcess,searchText,fromNum,pageSize);
				logger.info("查询学员"+userId+"学习计划完毕");
				return learnPlans;
			} catch (DataBaseException e) {
				logger.warn(StudentLearnPlanAction.class, e);
			}
		}
		return null;
	}
	
	/**
	 * Method name: getMyLearnPlansForMMGrid <BR>
	 * Description: 获取学习计划（mmGrid展现） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param learnProcess
	 * @param searchText
	 * @param page
	 * @param pageSize
	 * @return  Object<BR>
	 */
	@RequestMapping("getMyLearnPlansForMMGrid")
	@ResponseBody
	public Object getMyLearnPlansForMMGrid(HttpServletRequest request,
			Integer userId,Integer learnProcess,String searchText,
			Integer page,Integer pageSize){
		MMGridPageVoBean<LearnPlanVo> mmGridVo = new MMGridPageVoBean<LearnPlanVo>();
		try {
			logger.info("查询学员"+userId+"学习计划数目");
			Integer count = studentLearnPlanService.getLearnPlanCount(userId,learnProcess,searchText);
			logger.info("学员"+userId+"学习计划数目为"+count);
			//获取fromNum
			Integer fromNum = (int)CommonUtil.getFromNum(String.valueOf(pageSize),String.valueOf(page),count);
			//查询学习计划
			logger.info("查询学员"+userId+"学习计划");
			List<LearnPlanVo> learnPlans = studentLearnPlanService.getLearnPlansByConditions(
					userId,learnProcess,searchText,fromNum,pageSize);
			logger.info("查询学员"+userId+"学习计划完毕");
			mmGridVo.setTotal(count);
			mmGridVo.setRows(learnPlans);
		} catch (DataBaseException e) {
			logger.warn(StudentLearnPlanAction.class, e);
		}
		return mmGridVo;
	}
	
	/**
	 * Method name: toLearnPlanDetail <BR>
	 * Description: 跳往学习计划详情页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toLearnPlanDetail")
	public String toLearnPlanDetail(HttpServletRequest request,Integer learnPlanId){
		//设置学习计划id
		request.setAttribute("learnPlanId", learnPlanId);
		return "studentLearnPlan/learnPlanDetail";
	}
	
	/**
	 * Method name: getLearnPlanStages <BR>
	 * Description: 查询出学习计划的所有阶段 <BR>
	 * Remark: <BR>
	 * @param learnPlanId
	 * @return  Object<BR>
	 */
	@RequestMapping("getLearnPlanStages")
	@ResponseBody
	public Object getLearnPlanStages(HttpServletRequest request,Integer learnPlanId,Integer userId){
		try {
			logger.info("开始查询学习计划"+learnPlanId+"的所有阶段");
			List<LearnPlanStageVo> planStages = studentLearnPlanService.getLearnPlanStages(learnPlanId,userId);
			logger.info("查询完毕学习计划"+learnPlanId+"的所有阶段");
			return planStages;
		} catch (DataBaseException e) {
			logger.warn(StudentLearnPlanAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getLearnPlanStageCourses <BR>
	 * Description: 获取该阶段的所有课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param learnPlanId
	 * @param userId
	 * @param companyId
	 * @return  Object<BR>
	 */
	@RequestMapping("getLearnPlanStageCourses")
	@ResponseBody
	public Object getLearnPlanStageCourses(HttpServletRequest request,Integer learnPlanStageId,Integer userId){
		try {
			logger.info("开始查询阶段"+learnPlanStageId+"的所有课程");
			List<LearnPlanStageCourseVo> stageCourses = studentLearnPlanService.getLearnPlanStageCourses(learnPlanStageId,userId);
			logger.info("查询完毕阶段"+learnPlanStageId+"的所有课程");
			return stageCourses;
		} catch (DataBaseException e) {
			logger.warn(StudentLearnPlanAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: updateLearnPlanStatus <BR>
	 * Description: 更新学习计划状态 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param learnPlanId
	 * @param userId
	 * @return  Object<BR>
	 */
	@RequestMapping("updateLearnPlanStatus")
	@ResponseBody
	public Object updateLearnPlanStatus(HttpServletRequest request,Integer learnPlanId,Integer userId){
		AjaxReturnVo<Object> returnVo = new AjaxReturnVo<Object>();
		try {
			studentLearnPlanService.updateLearnPlanStatus(learnPlanId,userId);
		} catch (DataBaseException e) {
			returnVo.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(StudentLearnPlanAction.class, e);
		}
		return returnVo;
	}
	
	/**shenhaijun end*/
	
}
