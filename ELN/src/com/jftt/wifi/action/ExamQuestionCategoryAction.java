/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamQuestionCategoryAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/22        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.QuestionCategoryBean;
import com.jftt.wifi.bean.exam.vo.QuestionCategorySearchOptionVo;
import com.jftt.wifi.bean.knowledge_contest.CommonConstants;
import com.jftt.wifi.bean.knowledge_contest.SetUserInfoUtils;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ExamQuestionCategoryService;
import com.jftt.wifi.service.ManageDepartmentService;

/**
 * class name:ExamQuestionCategoryAction <BR>
 * class description: 试题分类Action <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/22
 * @author JFTT)wangyifeng
 */
@Controller
@RequestMapping(value = "exam/questionCategory")
public class ExamQuestionCategoryAction {
	@Autowired
	private ExamQuestionCategoryService questionCategoryService;
	
	@Autowired
	private ManageDepartmentService manageDepartmentService;
	
	private static final Logger LOG = Logger
			.getLogger(ExamQuestionCategoryAction.class);

	// Begin by wangyifeng
	/**
	 * @author JFTT)wangyifeng
	 * Method name: getQuestionCategoryList <BR>
	 * Description: 获取试题分类列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption
	 * @return  List<QuestionCategoryBean><BR>
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public List<QuestionCategoryBean> getQuestionCategoryList(HttpServletRequest request, QuestionCategorySearchOptionVo searchOption) {
		LOG.info("getQuestionCategoryList");
		SetUserInfoUtils.doWork(request, searchOption, CommonConstants.FIELD_NAME_ID, CommonConstants.FIELD_NAME_USER_ID, String.class);
		
		return questionCategoryService.getQuestionCategoryList(searchOption);
	}
	
	/**
	 * 云试题库，即普联的试题库
	 */
	@RequestMapping(value = "/listYun")
	@ResponseBody
	public List<QuestionCategoryBean> getQuestionCategoryListYun(HttpServletRequest request, QuestionCategorySearchOptionVo searchOption) {
		
		LOG.info("getQuestionCategoryListYun");
		
		searchOption.setSubCompanyId(1);
		searchOption.setCompanyId(1);
		List<QuestionCategoryBean> list = questionCategoryService.getQuestionCategoryListBySubCompany(searchOption);
		
		QuestionCategoryBean rootNode = new QuestionCategoryBean();
		rootNode.setId(null);
		rootNode.setName("云试题库");
		list.add(rootNode);
		
		return list;
	}
	
	/**
	 * 集体 某个 公司的试题库
	 * @throws Exception 
	 */
	@RequestMapping(value = "/listJiTuanSubCompany")
	@ResponseBody
	public List<QuestionCategoryBean> getQuestionCategoryListJiTuanSubCompany(HttpServletRequest request, QuestionCategorySearchOptionVo searchOption, Integer subCompanyId) throws Exception {
		
		LOG.info("getQuestionCategoryListJiTuanSubCompany");
		
		ManageUserBean userInfo = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		
		searchOption.setCompanyId(userInfo.getCompanyId());
		
		String rootName = "";
		if(subCompanyId !=null){
			searchOption.setSubCompanyId(subCompanyId);
			ManageDepartmentBean dept = manageDepartmentService.getManageDepartmentById(subCompanyId);
			
			if(dept == null){
				rootName = userInfo.getCompanyName();
			}else{
				rootName = dept.getName();
			}
		}else{
			searchOption.setSubCompanyId(userInfo.getSubCompanyId());
			rootName = userInfo.getSubCompanyName();
			
			if(userInfo.getCompanyId().intValue() == userInfo.getSubCompanyId().intValue()){
				rootName = userInfo.getCompanyName();
			}
		}
		
		List<QuestionCategoryBean> list = questionCategoryService.getQuestionCategoryListBySubCompany(searchOption);
		
		QuestionCategoryBean rootNode = new QuestionCategoryBean();
		rootNode.setId(null);
		rootNode.setName(rootName);
		list.add(rootNode);
		
		return list;
	}
	
	/**
	 * 集体公司试题库
	 */
	@RequestMapping(value = "/listJiTuan")
	@ResponseBody
	public List<QuestionCategoryBean> getQuestionCategoryListJiTuan(HttpServletRequest request, QuestionCategorySearchOptionVo searchOption) {
		
		LOG.info("getQuestionCategoryListJiTuan");
		
		ManageUserBean userInfo = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		
		searchOption.setCompanyId(userInfo.getCompanyId());
		searchOption.setSubCompanyId(userInfo.getCompanyId());
		
		List<QuestionCategoryBean> list = questionCategoryService.getQuestionCategoryListBySubCompany(searchOption);
		
		QuestionCategoryBean rootNode = new QuestionCategoryBean();
		rootNode.setId(null);
		rootNode.setName(userInfo.getCompanyName());
		list.add(rootNode);
		
		return list;
	}

	/**
	 * Method name: addCategory <BR>
	 * Description: 增加分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param newCategory
	 * @return String<BR>
	 */
	@RequestMapping(value = "/manage/add")
	@ResponseBody
	public String addCategory(HttpServletRequest request,
			QuestionCategoryBean newCategory) {
		LOG.info("addCategory");
		try {
			SetUserInfoUtils.doWork(request, newCategory,
					CommonConstants.FIELD_NAME_SUB_COMPANY_ID, Integer.class);
			SetUserInfoUtils.doWork(request, newCategory,
					CommonConstants.FIELD_NAME_COMPANY_ID, Integer.class);
			SetUserInfoUtils.doWork(request, newCategory,
					CommonConstants.FIELD_NAME_ID,
					CommonConstants.FIELD_NAME_CREATE_USER_ID, Integer.class);
			questionCategoryService.addCategory(newCategory);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * Method name: modifyCategory <BR>
	 * Description: 修改分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param modifiedCategory
	 * @return String<BR>
	 */
	@RequestMapping(value = "/manage/modify")
	@ResponseBody
	public String modifyCategory(QuestionCategoryBean modifiedCategory) {
		LOG.info("modifyCategory");
		try {
			questionCategoryService.modifyCategory(modifiedCategory);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * Method name: deleteCategory <BR>
	 * Description: 删除分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @return String<BR>
	 */
	@RequestMapping(value = "/manage/delete")
	@ResponseBody
	public String deleteCategory(Integer categoryId) {
		LOG.info("deleteCategory");
		try {
			questionCategoryService.deleteCategory(categoryId);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn(String.format("Delete category(id: %d) failed.",
					categoryId), e);
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * Method name: getQuestionCategory <BR>
	 * Description: 获取单个试题分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @return QuestionCategoryBean<BR>
	 */
	@RequestMapping(value = "/manage/get")
	@ResponseBody
	public QuestionCategoryBean getQuestionCategory(Integer categoryId) {
		LOG.info("getQuestionCategory");
		return questionCategoryService.getCategory(categoryId);
	}
	// End by wangyifeng
	
	//zhangchen start
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseQuestionCategory <BR>
	 * Description: 获取我的练习试题分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "/getExerciseQuestionCategory")
	@ResponseBody
	public Object getExerciseQuestionCategory(HttpServletRequest request){
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		QuestionCategorySearchOptionVo searchOption =  new QuestionCategorySearchOptionVo();
		searchOption.setCompanyId(userBean.getCompanyId());
		searchOption.setSubCompanyId(userBean.getSubCompanyId());
		List<QuestionCategoryBean> list = questionCategoryService.getExerciseQuestionCategory(searchOption);
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getQuestionCategoryById <BR>
	 * Description: 根据ID获取试题分类信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "/getQuestionCategoryById")
	@ResponseBody
	public Object getQuestionCategoryById(HttpServletRequest request,Integer categoryId){
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		QuestionCategorySearchOptionVo searchOption =  new QuestionCategorySearchOptionVo();
		searchOption.setCompanyId(userBean.getCompanyId());
		searchOption.setSubCompanyId(userBean.getSubCompanyId());
		searchOption.setId(categoryId);
		QuestionCategoryBean bean = questionCategoryService.getQuestionCategoryById(searchOption);
		return bean;
	}
	//zhangchen end
}
