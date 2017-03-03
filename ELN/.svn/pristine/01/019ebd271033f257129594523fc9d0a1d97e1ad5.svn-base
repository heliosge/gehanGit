/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: SafetyEducationAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月16日        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zefer.html.doc.u;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.PoliciesRulesBean;
import com.jftt.wifi.bean.SafetyEducationBean;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.KnowledgeOtherVo;
import com.jftt.wifi.bean.vo.PoliciesRulesSearchVo;
import com.jftt.wifi.bean.vo.SafetyEducationSearchVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.SafetyEducationService;
import com.jftt.wifi.util.CommonUtil;

/**
 * class name:SafetyEducationAction <BR>
 * class description: 安全宣教管理 <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月16日
 * @author JFTT)chenrui
 */
@Controller
@RequestMapping("safetyEducation")
public class SafetyEducationAction {
	@Autowired
	private SafetyEducationService safetyEducationService;
	@Autowired
	private ManageUserService manageUserService;
	private static Logger logger = Logger.getLogger(SafetyEducationAction.class);
	
	@RequestMapping("toManageSafetyList")
	public String toManageSafetyList(HttpServletRequest request){
		return "safety_education/manage_safetyList";
	}
	@RequestMapping("toSafetyList")
	public String toSafetyList(HttpServletRequest request){
		return "safety_education/safetyList";
	}
	@RequestMapping("toSafetyAdd")
	public String toSafetyAdd(HttpServletRequest request,String type,String id){
		request.setAttribute("id", id);
		request.setAttribute("type", type);
		return "safety_education/safetyAdd";
	}
	@ResponseBody
	@RequestMapping("getById")
	public Object getById(HttpServletRequest request,String id) {
		logger.debug("SafetyEducationAction执行getById方法");
		AjaxReturnVo<SafetyEducationBean> arv = new AjaxReturnVo<SafetyEducationBean>();
		try {
				SafetyEducationBean bean  = safetyEducationService.getById(id);
				arv.setRtnData(bean);
		} catch (Exception e) {
			logger.debug(SafetyEducationAction.class,e);
		}
		return arv;
	}
	/**
	 * 新增
	 * Method name: add <BR>
	 * Description: add <BR>
	 * Remark: <BR>
	 * @param request
	 * @param paramVo
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("add")
	public Object add(HttpServletRequest request,SafetyEducationBean paramVo) {
		logger.debug("SafetyEducationAction执行add方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			ManageUserBean userBean = manageUserService.findUserByIdCondition(userId);
			int companyId = userBean.getCompanyId();
			int subCompanyId = userBean.getSubCompanyId();
			paramVo.setCompanyId(companyId);
			paramVo.setSubCompanyId(subCompanyId);
			safetyEducationService.add(paramVo);
		} catch (Exception e) {
			logger.debug(SafetyEducationAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	
	/**
	 * 修改
	 * Method name: updateById <BR>
	 * Description: updateById <BR>
	 * Remark: <BR>
	 * @param request
	 * @param paramVo
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("updateById")
	public Object updateById(HttpServletRequest request,SafetyEducationBean paramVo) {
		logger.debug("SafetyEducationAction执行updateById方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			safetyEducationService.updateById(paramVo);
		} catch (Exception e) {
			logger.debug(SafetyEducationAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 删除
	 * Method name: deleteByIds <BR>
	 * Description: deleteByIds <BR>
	 * Remark: <BR>
	 * @param request  void<BR>
	 */
	@ResponseBody
	@RequestMapping("deleteByIds")
	public void deleteByIds(HttpServletRequest request) {
		logger.debug("SafetyEducationAction执行deleteByIds方法");
		try {	
			String[] ids =  request.getParameterValues("ids[]");
			safetyEducationService.deleteByIds(ids);
		} catch (Exception e) {
			logger.debug(SafetyEducationAction.class,e);
		}
	}
	
	/**
	 * 获取安全宣教列表
	 * Method name: getSafetyEducationList <BR>
	 * Description: getSafetyEducationList <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getSafetyEducationList")
	public Object getSafetyEducationList(HttpServletRequest request,SafetyEducationSearchVo params) {
		logger.debug("SafetyEducationAction执行getSafetyEducationList方法");
		MMGridPageVoBean<SafetyEducationBean> mmVo = new MMGridPageVoBean<SafetyEducationBean>();
		try {
				String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
				ManageUserBean userBean = manageUserService.findUserByIdCondition(userId);
				int companyId = userBean.getCompanyId();
				int subCompanyId = userBean.getSubCompanyId();
				params.setCompanyId(companyId);
				params.setSubCompanyId(subCompanyId);
				int count = safetyEducationService.getSafetyEducationListCount(params);
				String pageSize = params.getPageSize();
				String page = params.getPage();
				long fromNum = CommonUtil.getFromNum(pageSize, page, count);
				params.setFromNum(fromNum);
				List<SafetyEducationBean> list  = safetyEducationService.getSafetyEducationList(params);
				mmVo.setTotal(count);
				mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(SafetyEducationAction.class,e);
		}
		return mmVo;
	}
	/**
	 * 学员端--获取安全宣教列表
	 * Method name: stu_getPoliciesRulesList <BR>
	 * Description: stu_getPoliciesRulesList <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("stu_getSafetyEducationList")
	public Object stu_getPoliciesRulesList(HttpServletRequest request,SafetyEducationSearchVo params) {
		logger.debug("SafetyEducationAction执行stu_getSafetyEducationList方法");
		AjaxReturnVo<SafetyEducationBean> arv = new AjaxReturnVo<SafetyEducationBean>();
		try {
				String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
				ManageUserBean userBean = manageUserService.findUserByIdCondition(userId);
				int companyId = userBean.getCompanyId();
				int subCompanyId = userBean.getSubCompanyId();
				params.setCompanyId(companyId);
				params.setSubCompanyId(subCompanyId);
				int count = safetyEducationService.stu_getSafetyEducationListCount(params);
				String pageSize = params.getPageSize();
				String page = params.getPage();
				long fromNum = CommonUtil.getFromNum(pageSize, page, count);
				params.setFromNum(fromNum);
				List<SafetyEducationBean> list  = safetyEducationService.stu_getSafetyEducationList(params);
				arv.setRtnDataList(list);
				arv.setCount(count);
		} catch (Exception e) {
			logger.debug(SafetyEducationAction.class,e);
		}
		return arv;
	}
	@ResponseBody
	@RequestMapping("stu_getSafetyEducationListCount")
	public int stu_getPoliciesRulesListCount(HttpServletRequest request,SafetyEducationSearchVo params) {
		logger.debug("SafetyEducationAction执行stu_getPoliciesRulesListCount方法");
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			ManageUserBean userBean = manageUserService.findUserByIdCondition(userId);
			int companyId = userBean.getCompanyId();
			int subCompanyId = userBean.getSubCompanyId();
			params.setCompanyId(companyId);
			params.setSubCompanyId(subCompanyId);
			return safetyEducationService.stu_getSafetyEducationListCount(params);
		} catch (Exception e) {
			logger.debug(SafetyEducationAction.class,e);
		}
		return 0 ;
	}
	
}
