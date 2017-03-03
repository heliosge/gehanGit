/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ApproveManageAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月29日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.ApproveBean;
import com.jftt.wifi.bean.ApproveRecordBean;
import com.jftt.wifi.bean.KnowledgeBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.ManageRoleBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.OamTopicBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.ShareRecordBean;
import com.jftt.wifi.common.ApproveStatusConstant;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ApproveManageService;
import com.jftt.wifi.service.ManageParamService;
import com.jftt.wifi.service.ManageRoleService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.JsonUtil;

/**
 * class name:审核流程管理类
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月29日
 * @author JFTT)liucaibao
 */
@Controller
@RequestMapping("approve")
public class ApproveManageAction {

	@Autowired
	private ApproveManageService approveManageService;
	
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private ManageRoleService manageRoleService;
	@Autowired
	private ManageParamService manageParamService;

	
	private static Logger log = Logger.getLogger(ApproveManageAction.class);

	/**
	 * Method name: 跳转审批设置页面
	 * Description: apprveSet <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("approveSet")
	public String approveSet(HttpServletRequest request,ModelMap model){
		
		try {
			//获取集团公司下子公司列表
			int companyId = getCompanyId(request);
			
			JSONArray jsonarr= approveManageService.querySubCompanyList(companyId);
			model.addAttribute("companyArray", jsonarr);
			model.addAttribute("reList",queryApproveSetInfo(getCompanyId(request)));
			model.addAttribute("isCompany",isCompany(request));
			model.addAttribute("isPuLian",isPuLian(request));
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
		}
		return "approve/addApprove";
	}
	
	
	@RequestMapping(value="listUser")
	public String listUser(){
		return "approve/listUser";
	}
	
	/**
	 * Method name: 获取直属领导信息
	 * Description: getUserLead <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("getUserLeader")
	@ResponseBody
	public Integer getUserLeader(HttpServletRequest request){
		Integer leaderId = null;
		try {
			int userId =getUserId(request);
			ManageUserBean userBean = manageUserService.findUserById(String.valueOf(userId));
			//获取用户的岗位id
			int postId = userBean.getPostId();
			ManagePostBean postBean =	manageParamService.selectManagePostById(postId);
			leaderId =postBean.getParentId();
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
		}
		return leaderId;
	}
	
	
	
	/**
	 * Method name: 获取角色列表
	 * Description: getUserRole <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="getUserRoleList")
	@ResponseBody
	public Object getUserRoleList(HttpServletRequest request){
		
		//所有角色
		List<ManageRoleBean> roleList = new ArrayList<ManageRoleBean>() ;
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("companyId", String.valueOf(getCompanyId(request)));
			map.put("subCompanyId", String.valueOf(getSubCompanyId(request)));
			 roleList = manageRoleService.getManageRoleByMap(map);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			return JsonUtil.getJson4JavaList(roleList);
		}
		return JsonUtil.getJson4JavaList(roleList);
	}
	
	/**
	 * Method name: getUserDept <BR>
	 * Description: getUserDept <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("getPostList")
	public Object getPostList(HttpServletRequest request){

		List<ManagePostBean> postList = new ArrayList<ManagePostBean>();
		try {
			Map<String,Object> param = new HashMap<String, Object>();
			
			param.put("companyId", getCompanyId(request));
			param.put("subCompanyId", getSubCompanyId(request));
			
			 postList = manageParamService.selectManagePost(param);
			 request.setAttribute("jsonArray", JsonUtil.getJson4JavaList(postList));
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
		} 
		 return "approve/postTree";
	}
	/**
	 * Method name:获取公司的个人列表
	 * Description: getUserList <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("getUserList")
	@ResponseBody
	public Object getUserList(HttpServletRequest request,int page, int pageSize,String userName,String name){
		
		log.debug("Enter Class:ApproveManageAction Method:getUserList ");
		 

		//组织参数
		Map<String, Object>  param = new HashMap<String, Object>();
		int size = 0;
		try {
			param.put("companyId",getCompanyId(request));
			param.put("subCompanyId",getSubCompanyId(request));
			param.put("userName", userName);
			param.put("name", name);
 			//查询总数
			size = manageUserService.findUserCountByCondition(param);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			try {
				log.error(e+"查询列表总数失败。公司id为:"+getCompanyId(request));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		List<ManageUserBean> rows = null;
		try {
			 
			rows = manageUserService.findUserByCondition(param, page, pageSize);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			log.error(e+"查询列表失败。公司Id："+param.get("companyId"));
		}
		
		MMGridPageVoBean<ManageUserBean> vo = new MMGridPageVoBean<ManageUserBean>();
		vo.setTotal(size);
		vo.setRows(rows);
		
		return vo;
	}
	
	/**
	 * Method name:查找公司的审核路线方法。此处要传入是知识、专题还是课程
	 * Description: queryApproveInfo <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	public Object queryApproveSetInfo(int companyId){
		ApproveBean approveBean = new ApproveBean();
		approveBean.setCompanyId(companyId);
		
		List<ApproveBean> reList = approveManageService.queryApproveInfo(approveBean);
		JSONObject jobj = new JSONObject();
		for(ApproveBean app:reList){
			jobj.put(app.getWayType(),JsonUtil.getJson4JavaObject(app));
		}
		
		return jobj;
	}
			
	
	
	/**
	 * Method name: 集团的审核流程
	 * Description: saveApproveInfo <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="saveApproveInfo",consumes = "application/json")
	@ResponseBody
	public String saveApproveInfo(HttpServletRequest request,@RequestBody ApproveBean approveBean){
		
		log.debug("方法入口"+approveBean.toString());
		//1、获取用户Id
		Integer userId = getUserId(request);//888;//Integer.valueOf(request.getParameter("userId"));
		
		try {
			//2、获取公司Id
			int companyId = getCompanyId(request);
			approveBean.setUserId(userId);
			approveBean.setCompanyId(companyId);
			//3、进行数据的保存
			approveManageService.saveApproveInfo(approveBean);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			log.error("保存审核信息异常"+approveBean.toString()+e);
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	@RequestMapping(value ="approveList")
	public String approveList(HttpServletRequest request,ModelMap model){
		try {
			int companyId = getCompanyId(request);
			
			JSONArray jsonarr= approveManageService.querySubCompanyList(getCompanyId(request));
			
			//加入集团公司
			Map<String,String> param = new HashMap<String, String>();
			param.put("companyId",String.valueOf(getCompanyId(request)));
			param.put("subCompanyId", String.valueOf(getCompanyId(request)));
			
			JSONObject obj = new JSONObject();
			obj.put("id", String.valueOf(getCompanyId(request)));
			obj.put("name", approveManageService.getUserCompanyName(param));
			JSONArray subCompanyList = new JSONArray();
			for(int i=0;i<jsonarr.size();i++){
				JSONObject sobj = JSONObject.fromObject(jsonarr.get(i));
				sobj.put("parentId", String.valueOf(getCompanyId(request)));
				subCompanyList.add(sobj);
			}
			subCompanyList.add(obj);
			model.addAttribute("subCompanyList", subCompanyList);
			
			if(isPuLian(request)){//公司id为1，则是普连审核页面
				JSONArray companyList= approveManageService.queryCompanyList();
				model.addAttribute("companyList", companyList);
				return "approve/approveListByPL";
			}
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
		}
		return "approve/approveList";
	}
	
	
	
	
	/**
	 * Method name: 获取可以审批的知识列表
	 * Description: getKnowlegeListByMap <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page
	 * @param pageSize
	 * @param knowledgeBean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getApproveKLListByMap")
	@ResponseBody
	public Object getKnowlegeListByMap(HttpServletRequest request, int page, int pageSize,@RequestParam(required=false)String shareStatusString,KnowledgeBean knowledgeBean){
			
		//入口日志
		log.debug("Enter Class:IntegralManageAction Method:getListByMap param:"+knowledgeBean.toString() );
		int userId = getUserId(request);//request.getParameter("userId");
		Map<String,Object> param = new HashMap<String, Object>();
		//组织参数
		int size = 0;
		try {
			ManageUserBean manageUserBean = manageUserService.findUserByIdCondition(String.valueOf(userId));//拿到用户对象
			param.put("userId", userId);
			param.put("companyId", manageUserBean.getCompanyId());
			param.put("categoryName", knowledgeBean.getCategoryName());
			param.put("knowledgeName", knowledgeBean.getKnowledgeName());
			param.put("shareStatus", shareStatusString);
			param.put("postId", manageUserBean.getPostId());
			//查询总数
			size = approveManageService.queryKnowledgeCount(param);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			log.error(e+"查询列表总数失败。param:"+knowledgeBean.toString());
		}
		
		List<KnowledgeBean> rows = null;
		try {
			param.put("fromNum", (page-1)*pageSize);
			param.put("page", pageSize);
			rows = approveManageService.queryKnowledge(param);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			log.error(e+"查询列表失败。param:"+knowledgeBean.toString());
		}
		
		MMGridPageVoBean<KnowledgeBean> vo = new MMGridPageVoBean<KnowledgeBean>();
		vo.setTotal(size);
		vo.setRows(rows);
		return vo;
		}
	/**
	 * Method name: getApproveCourseListByMap <BR>
	 * Description: 获取可以审批的课程列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page
	 * @param pageSize
	 * @param knowledgeBean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getApproveCourseListByMap")
	@ResponseBody
	public Object getApproveCourseListByMap(HttpServletRequest request, int page, int pageSize,@RequestParam(required=false)String shareStatusString,ResCourseBean resCourseBean){
			
		//入口日志
		log.debug("Enter Class:IntegralManageAction Method:getApproveCourseListByMap param:"+resCourseBean.toString() );
		int userId = getUserId(request);//request.getParameter("userId");
		Map<String,Object> param = new HashMap<String, Object>();
		//组织参数
		int size = 0;
		try {
			ManageUserBean manageUserBean = manageUserService.findUserByIdCondition(String.valueOf(userId));//拿到用户对象
			param.put("userId", userId);
			param.put("companyId", manageUserBean.getCompanyId());
			param.put("code", resCourseBean.getCode());
			param.put("name", resCourseBean.getName());
			param.put("shareStatus", shareStatusString);
			param.put("postId", manageUserBean.getPostId());
			//查询总数
			size = approveManageService.queryApproveCourseCount(param);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			log.error(e+"查询列表总数失败。param:"+resCourseBean.toString());
		}
		
		List<ResCourseBean> rows = null;
		try {
			param.put("fromNum", (page-1)*pageSize);
			param.put("page", pageSize);
			rows = approveManageService.queryApproveCourseList(param);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			log.error(e+"查询列表失败。param:"+resCourseBean.toString());
		}
		
		MMGridPageVoBean<ResCourseBean> vo = new MMGridPageVoBean<ResCourseBean>();
		vo.setTotal(size);
		vo.setRows(rows);
		return vo;
		}
	
	/**
	 * Method name: getApproveTopicListByMap <BR>
	 * Description: 获取可以审批的专题列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page
	 * @param pageSize
	 * @param knowledgeBean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getApproveTopicListByMap")
	@ResponseBody
	public Object getApproveTopicListByMap(HttpServletRequest request, int page, int pageSize,@RequestParam(required=false)String shareStatusString,OamTopicBean oamTopicBean){
			
			//入口日志
			log.debug("Enter Class:IntegralManageAction Method:getListByMap param:"+oamTopicBean.toString() );
			int userId = getUserId(request);//request.getParameter("userId");
			Map<String,Object> param = new HashMap<String, Object>();
			//组织参数
			int size = 0;
			try {
				ManageUserBean manageUserBean = manageUserService.findUserByIdCondition(String.valueOf(userId));//拿到用户对象
				param.put("userId", userId);
				param.put("companyId", manageUserBean.getCompanyId());
				param.put("name", oamTopicBean.getName());
				param.put("no", oamTopicBean.getNo());
				param.put("shareStatus", shareStatusString);
				param.put("postId", manageUserBean.getPostId());
				//查询总数
				size = approveManageService.queryApproveTopicCount(param);
			} catch (Exception e) {
				log.debug(ApproveManageAction.class,e);
				log.error(e+"查询列表总数失败。param:"+oamTopicBean.toString());
			}
			
			List<OamTopicBean> rows = null;
			try {
				param.put("fromNum", (page-1)*pageSize);
				param.put("page", pageSize);
				rows = approveManageService.queryApproveTopicList(param);
			} catch (Exception e) {
				log.debug(ApproveManageAction.class,e);
				log.error(e+"查询列表失败。param:"+oamTopicBean.toString());
			}
			
			MMGridPageVoBean<OamTopicBean> vo = new MMGridPageVoBean<OamTopicBean>();
			vo.setTotal(size);
			vo.setRows(rows);
			return vo;
		}
	
	
	
	/*--d以下是查询普连审核页面列表->*/
	/**
	 * Method name: 获取可以审批的知识列表
	 * Description: getKnowlegeListByMap <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page
	 * @param pageSize
	 * @param knowledgeBean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getKnowlegeListByPL")
	@ResponseBody
	public Object getKnowlegeListByPL(HttpServletRequest request, int page, int pageSize,@RequestParam(required=false)String shareStatusString,KnowledgeBean knowledgeBean){
			
		//入口日志
		log.debug("Enter Class:IntegralManageAction Method:getListByMap param:"+knowledgeBean.toString() );
		int userId = getUserId(request);//request.getParameter("userId");
		Map<String,Object> param = new HashMap<String, Object>();
		//组织参数
		int size = 0;
		try {
			ManageUserBean manageUserBean = manageUserService.findUserByIdCondition(String.valueOf(userId));//拿到用户对象
			param.put("userId", userId);
			param.put("categoryName", knowledgeBean.getCategoryName());
			param.put("knowledgeName", knowledgeBean.getKnowledgeName());
			param.put("shareStatus", shareStatusString);
			param.put("companyId", knowledgeBean.getCompanyId());
			param.put("postId", manageUserBean.getPostId());
			//查询总数
			size = approveManageService.queryKnowledgeCountByPL(param);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			log.error(e+"查询列表总数失败。param:"+knowledgeBean.toString());
		}
		
		List<KnowledgeBean> rows = null;
		try {
			param.put("fromNum", (page-1)*pageSize);
			param.put("page", pageSize);
			rows = approveManageService.queryKnowledgeByPL(param);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			log.error(e+"查询列表失败。param:"+knowledgeBean.toString());
		}
		MMGridPageVoBean<KnowledgeBean> vo = new MMGridPageVoBean<KnowledgeBean>();
		vo.setTotal(size);
		vo.setRows(rows);
		return vo;
		}
	/**
	 * Method name: getApproveCourseListByMap <BR>
	 * Description: 获取可以审批的课程列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page
	 * @param pageSize
	 * @param knowledgeBean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getApproveCourseListByPL")
	@ResponseBody
	public Object getApproveCourseListByPL(HttpServletRequest request, int page, int pageSize,@RequestParam(required=false)String shareStatusString,ResCourseBean resCourseBean){
			
		//入口日志
		log.debug("Enter Class:IntegralManageAction Method:getApproveCourseListByMap param:"+resCourseBean.toString() );
		int userId = getUserId(request);//request.getParameter("userId");
		Map<String,Object> param = new HashMap<String, Object>();
		//组织参数
		int size = 0;
		try {
			ManageUserBean manageUserBean = manageUserService.findUserByIdCondition(String.valueOf(userId));//拿到用户对象
			param.put("userId", userId);
			param.put("code", resCourseBean.getCode());
			param.put("name", resCourseBean.getName());
			param.put("shareStatus", shareStatusString);
			param.put("companyId", resCourseBean.getCompanyId());
			param.put("postId", manageUserBean.getPostId());
			//查询总数
			size = approveManageService.queryApproveCourseCountByPL(param);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			log.error(e+"查询列表总数失败。param:"+resCourseBean.toString());
		}
		
		List<ResCourseBean> rows = null;
		try {
			param.put("fromNum", (page-1)*pageSize);
			param.put("page", pageSize);
			rows = approveManageService.queryApproveCourseListByPL(param);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			log.error(e+"查询列表失败。param:"+resCourseBean.toString());
		}
		
		MMGridPageVoBean<ResCourseBean> vo = new MMGridPageVoBean<ResCourseBean>();
		vo.setTotal(size);
		vo.setRows(rows);
		return vo;
		}
	
	/**
	 * Method name: getApproveTopicListByMap <BR>
	 * Description: 获取可以审批的专题列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page
	 * @param pageSize
	 * @param knowledgeBean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getApproveTopicListByPL")
	@ResponseBody
	public Object getApproveTopicListByPL(HttpServletRequest request, int page, int pageSize,@RequestParam(required=false)String shareStatusString,OamTopicBean oamTopicBean){
			
			//入口日志
			log.debug("Enter Class:IntegralManageAction Method:getListByMap param:"+oamTopicBean.toString() );
			int userId = getUserId(request);//request.getParameter("userId");
			Map<String,Object> param = new HashMap<String, Object>();
			//组织参数
			int size = 0;
			try {
				ManageUserBean manageUserBean = manageUserService.findUserByIdCondition(String.valueOf(userId));//拿到用户对象
				param.put("userId", userId);
				param.put("name", oamTopicBean.getName());
				param.put("no", oamTopicBean.getNo());
				param.put("shareStatus", shareStatusString);
				param.put("companyId", oamTopicBean.getCompanyId());
				param.put("postId", manageUserBean.getPostId());
				//查询总数
				size = approveManageService.queryApproveTopicCountByPL(param);
			} catch (Exception e) {
				log.debug(ApproveManageAction.class,e);
				log.error(e+"查询列表总数失败。param:"+oamTopicBean.toString());
			}
			
			List<OamTopicBean> rows = null;
			try {
				param.put("fromNum", (page-1)*pageSize);
				param.put("page", pageSize);
				rows = approveManageService.queryApproveTopicListByPL(param);
			} catch (Exception e) {
				log.debug(ApproveManageAction.class,e);
				log.error(e+"查询列表失败。param:"+oamTopicBean.toString());
			}
			
			MMGridPageVoBean<OamTopicBean> vo = new MMGridPageVoBean<OamTopicBean>();
			vo.setTotal(size);
			vo.setRows(rows);
			return vo;
		}
	
	
	/*--d以上是查询普连审核页面列表->*/
	
	/**
	 * Method name: 共享审批通过或则驳回。主要为针对企业管理员的
	 * Description: approveObj <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("approveObj")
	@ResponseBody
	public String approveObj(HttpServletRequest request,ApproveRecordBean approveRecordBean){
		
		log.debug("Enter Class:ApproveManageAction Method:approveObj param:"+approveRecordBean.toString() );
		 
		try {
			approveRecordBean.setUserId(getUserId(request));
			approveManageService.approveObj(approveRecordBean);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			log.error("审核失败，更新数据库失败。");
			return Constant.AJAX_FAIL;
		}
		
		return Constant.AJAX_SUCCESS;
	}
	
	
	
	/**
	 * Method name: 共享审批通过或则驳回。主要为针对普连用户的操作
	 * Description: approveObj <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("approveObjByPL")
	@ResponseBody
	public String approveObjByPL(HttpServletRequest request,ApproveRecordBean approveRecordBean){
		
		log.debug("Enter Class:ApproveManageAction Method:approveObjByPL param:"+approveRecordBean.toString() );
		 
		try {
			approveRecordBean.setUserId(getUserId(request));
			approveManageService.approveObjByPL(approveRecordBean);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			log.error("普连审核失败，更新数据库失败。");
			return Constant.AJAX_SUCCESS;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	/**
	 * Method name: shareList <BR>
	 * Description: 共享列表主页 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("shareList")
	public String shareList(HttpServletRequest request,ModelMap model ){
		
		try {
			
			JSONArray jsonarr= approveManageService.querySubCompanyList(getCompanyId(request));
			//加入集团公司
			Map<String,String> param = new HashMap<String, String>();
			param.put("companyId",String.valueOf(getCompanyId(request)));
			param.put("subCompanyId", String.valueOf(getCompanyId(request)));
			
			JSONObject obj = new JSONObject();
			obj.put("id", String.valueOf(getCompanyId(request)));
			obj.put("name", approveManageService.getUserCompanyName(param));
			jsonarr.add(obj);
			model.addAttribute("companyArray", jsonarr);
			model.addAttribute("isCompany", isCompany(request));
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
		}
		return "approve/shareList";
	}
	
	/**
	 * Method name:提交对象的共享 对象共享主入口方法
	 * Description: shareObj <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("shareObj")
	@ResponseBody
	public String shareObj(HttpServletRequest request,ApproveRecordBean approveRecordBean){
		
		log.debug("对象共享的入口方法。Enter Class:ApproveManageAction Method:shareObj");

		
		try {
			approveRecordBean.setUserId(getUserId(request));
			approveRecordBean.setCompanyId(getCompanyId(request));

			approveManageService.shareObj(approveRecordBean);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
			log.error("提交数据共享失败，更新数据库失败。");
			return Constant.AJAX_SUCCESS;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: isCompany <BR>
	 * Description: 判断是否是子公司或者集团公司 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  boolean<BR>
	 */
	private boolean  isCompany(HttpServletRequest request){
		
		try {
			int companyId = getCompanyId(request);
			int subCompanyId = getSubCompanyId(request);
			if(companyId == subCompanyId){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
		}
		return true;
	}
	
	/**
	 * Method name: isPuLian <BR>
	 * Description: 判断是否是普联公司 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  boolean<BR>
	 */
	private boolean isPuLian(HttpServletRequest request){
		try {
			int companyId = getCompanyId(request);
			if(companyId==Constant.PULIAN_COMPANY_ID) {
				return true;
			} 
			return false;	
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
		}
		return false;
	}
	/**
	 * Method name: 查询待共享的知识列表
	 * Description: getShareKLList <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page
	 * @param pageSize
	 * @param knowledgeBean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getShareKLList")
	@ResponseBody
	public Object getShareKLList(HttpServletRequest request, int page, int pageSize,KnowledgeBean knowledgeBean){
			
			//入口日志
			log.debug("Enter Class:IntegralManageAction Method:getShareKLList param:"+knowledgeBean.toString() );
			int userId = getUserId(request);//request.getParameter("userId");
			knowledgeBean.setUserId(userId);
			//组织参数
			int size = 0;
			try {
				knowledgeBean.setCompanyId(getCompanyId(request));
				if(isCompany(request)){//是集团公司
					knowledgeBean.setShareStatus(ApproveStatusConstant.APRROVE_TG);
				}else{
					knowledgeBean.setSubCompanyId(getSubCompanyId(request));
				}
				//查询总数
				size = approveManageService.queryShareKLCount(knowledgeBean);
			} catch (Exception e) {
				log.debug(ApproveManageAction.class,e);
				log.error(e+"查询知识列表总数失败。param:"+knowledgeBean.toString());
			}
			
			List<KnowledgeBean> rows = null;
			try {
				knowledgeBean.setFromNum(Integer.valueOf(CommonUtil.getFromNum(pageSize, page, size)));
				
				knowledgeBean.setPage(pageSize);
				rows = approveManageService.queryShareKL(knowledgeBean);
			} catch (Exception e) {
				log.debug(ApproveManageAction.class,e);
				log.error(e+"查询知识列表失败。param:"+knowledgeBean.toString());
			}
			
			MMGridPageVoBean<KnowledgeBean> vo = new MMGridPageVoBean<KnowledgeBean>();
			vo.setTotal(size);
			vo.setRows(rows);
			
			return vo;
		}
	
	/**
	 * Method name: 查询待共享的课程列表
	 * Description: getShareKLList <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page
	 * @param pageSize
	 * @param knowledgeBean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getShareCourseList")
	@ResponseBody
	public Object getShareCourseList(HttpServletRequest request, int page, int pageSize,ResCourseBean resCourseBean){
			
			//入口日志
			log.debug("Enter Class:IntegralManageAction Method:getShareKLList param:"+resCourseBean.toString() );
		 
			//组织参数
			int size = 0;
			try {
				resCourseBean.setCompanyId(getCompanyId(request));
				if(isCompany(request)){//是集团公司
					resCourseBean.setShareStatus(ApproveStatusConstant.APRROVE_TG);
				}else{
					resCourseBean.setSubCompanyId(getSubCompanyId(request));
				}
				//查询总数
				size = approveManageService.queryShareCourseCount(resCourseBean);
			} catch (Exception e) {
				log.debug(ApproveManageAction.class,e);
				log.error(e+"查询课程列表总数失败。param:"+resCourseBean.toString());
			}
			
			List<ResCourseBean> rows = null;
			try {
				resCourseBean.setFromNum(CommonUtil.getFromNum(pageSize, page, size));
				resCourseBean.setPage(pageSize);
				rows = approveManageService.queryShareCourse(resCourseBean);
			} catch (Exception e) {
				log.debug(ApproveManageAction.class,e);
				log.error(e+"查询课程列表失败。param:"+resCourseBean.toString());
			}
			
			MMGridPageVoBean<ResCourseBean> vo = new MMGridPageVoBean<ResCourseBean>();
			vo.setTotal(size);
			vo.setRows(rows);
			
			return vo;
		}
	
	/**
	 * Method name: 查询待共享的专题列表
	 * Description: getShareTopicList <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page
	 * @param pageSize
	 * @param knowledgeBean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getShareTopicList")
	@ResponseBody
	public Object getShareTopicList(HttpServletRequest request, int page, int pageSize,OamTopicBean oamTopicBean){
			
			//入口日志
			log.debug("Enter Class:IntegralManageAction Method:getShareTopicList param:"+oamTopicBean.toString() );
			//组织参数
			int size = 0;
			try {
				oamTopicBean.setCompanyId(getCompanyId(request));
				if(isCompany(request)){//是集团公司
					oamTopicBean.setShareStatus(ApproveStatusConstant.APRROVE_TG);
				}else{
					oamTopicBean.setSubCompanyId(getSubCompanyId(request));
				}
				//查询总数
				size = approveManageService.queryShareTopicCount(oamTopicBean);
			} catch (Exception e) {
				log.debug(ApproveManageAction.class,e);
				log.error(e+"查询列表总数失败。param:"+oamTopicBean.toString());
			}
			
			List<OamTopicBean> rows = null;
			try {
				oamTopicBean.setFromNum((page-1)*pageSize);
				oamTopicBean.setPage(pageSize);
				rows = approveManageService.queryShareTopic(oamTopicBean);
			} catch (Exception e) {
				log.debug(ApproveManageAction.class,e);
				log.error(e+"查询列表失败。param:"+oamTopicBean.toString());
			}
			
			MMGridPageVoBean<OamTopicBean> vo = new MMGridPageVoBean<OamTopicBean>();
			vo.setTotal(size);
			vo.setRows(rows);
			
			return vo;
		}
	
	
	
	/**
	 * Method name: 查找对象详情。此处可以调用其他内容的详情查看方法
	 * Description: detailObj <BR>
	 * Remark: <BR>
	 * @param request
	 * @param approveRecordBean
	 * @return  String<BR>
	 */
	@RequestMapping("detailObj")
	public String detailKLObj(HttpServletRequest request,int id,int type){
		request.setAttribute("id", id);
		request.setAttribute("type", type);
		return "approve/shareDetail";
	}
	
	/**
	 * Method name: 查询待共享的课程列表
	 * Description: getShareKLList <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page
	 * @param pageSize
	 * @param knowledgeBean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getShareRecordList")
	@ResponseBody
	public Object getShareRecordList(HttpServletRequest request, int page, int pageSize,ApproveRecordBean approveRecordBean){
			
			//入口日志
			log.debug("Enter Class:IntegralManageAction Method:getShareKLList param:"+approveRecordBean.toString() );
		 
			//组织参数
			int size = 0;
			try {
				//查询总数//传入参数，类型type ,object_id .
				size = approveManageService.queryShareRecordCount(approveRecordBean);
			} catch (Exception e) {
				log.debug(ApproveManageAction.class,e);
				log.error(e+"查询课程列表总数失败。param:"+approveRecordBean.toString());
			}
			
			List<ShareRecordBean> rows = null;
			try {
				approveRecordBean.setFromNum((page-1)*pageSize);
				approveRecordBean.setPage(pageSize);
				rows = approveManageService.queryShareRecord(approveRecordBean);
			} catch (Exception e) {
				log.debug(ApproveManageAction.class,e);
			}
			
			MMGridPageVoBean<ShareRecordBean> vo = new MMGridPageVoBean<ShareRecordBean>();
			vo.setTotal(size);
			vo.setRows(rows);
			
			return vo;
		}
	
	
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toKnowledgeLook <BR>
	 * Description: 跳转-查看上传知识页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param categoryId
	 * @return  String<BR>
	 */
	@RequestMapping("objDetail")
	public String toKnowledgeLookUp(HttpServletRequest request,int id,int type){
		request.setAttribute("id", id);
		request.setAttribute("type", type);
		return "approve/objDetail";
	}
	
	/**
	 * Method name: getUserName <BR>
	 * Description: 根据用户id查询用户的姓名 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @return  String<BR>
	 */
	@RequestMapping("getUserName")
	@ResponseBody
	public String  getUserName(HttpServletRequest request,String userId ){
		
		try {
			ManageUserBean manageUserBean = manageUserService.findUserById(userId);//拿到用户对象
			
			return manageUserBean.getName();
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getUserCompanyName <BR>
	 * Description: 查询公司名称 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId
	 * @param subCompanyId
	 * @return  String<BR>
	 */
	@ResponseBody
	@RequestMapping("getUserCompanyName")
	public String getUserCompanyName(HttpServletRequest request,String companyId,String subCompanyId){
		
		try {
			Map<String,String> param = new HashMap<String, String>();
			param.put("companyId",companyId);
			param.put("subCompanyId",subCompanyId);
			
			return approveManageService.getUserCompanyName(param);
		} catch (Exception e) {
			log.debug(ApproveManageAction.class,e);
		}
		
		return null;
	}
	
	
	
	/**
	 * Method name: 获取用户Id
	 * Description: getUserId <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  int<BR>
	 */
	private int getUserId (HttpServletRequest request){
		
		HttpSession session = request.getSession();
		Object userId = session.getAttribute(Constant.SESSION_USERID_LONG);
		if(null!=userId){
			return Integer.valueOf(String.valueOf(userId));
		}
		return 0;
	}
	
	/**
	 * Method name: 获取集团公司id
	 * Description: getCompanyId <BR>
	 * Remark: <BR>
	 * @param request
	 * @return
	 * @throws Exception  int<BR>
	 */
	private int getCompanyId (HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String userId = String.valueOf(session.getAttribute(Constant.SESSION_USERID_LONG));
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);//拿到用户对象
		return manageUserBean.getCompanyId();
	}
	
	/**
	 * Method name: 获取子公司id
	 * Description: getSubCompanyId <BR>
	 * Remark: <BR>
	 * @param request
	 * @return
	 * @throws Exception  int<BR>
	 */
	private int getSubCompanyId (HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String userId = String.valueOf(session.getAttribute(Constant.SESSION_USERID_LONG));
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);//拿到用户对象
		return manageUserBean.getSubCompanyId();
	}
}
