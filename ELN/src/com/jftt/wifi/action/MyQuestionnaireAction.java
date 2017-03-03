/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyQuestionnaireAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-12-3        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.action;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.questionnaire.InvestigationAnswerVo;
import com.jftt.wifi.bean.questionnaire.InvestigationBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireQuestionBean;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.InvestigationService;
import com.jftt.wifi.service.MyQuestionnaireService;
import com.jftt.wifi.service.QuestionnaireService;
import com.jftt.wifi.util.JsonUtil;

/**
 * @author JFTT)zhangchen<BR>
 * class name:MyQuestionnaireAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-3
 */
@Controller
@RequestMapping(value = "myQuestionnaire")
public class MyQuestionnaireAction {
	private static final Logger LOG = Logger.getLogger(ExamQuestionAction.class);
	
	@Autowired
	private QuestionnaireService questionnaireService;
	@Autowired
	private InvestigationService investigationService;
	@Autowired
	private MyQuestionnaireService myQuestionnaireService;
	
	@RequestMapping(value = "toList")
	public String toList(Model model, HttpServletRequest request) {
		LOG.info("MyQuestionnaireAction_toList");
		//公司ID 如不是1 说明是非普联  需要引入普联的试题库（云试题库）
		//ManageUserBean userInfo = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		//request.setAttribute("subCompanyId", userInfo.getSubCompanyId());
		
		return "my_questionnaire/questionnaire_list";
	}
	
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getList <BR>
	 * Description: 获取我的问卷列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getList")
	@ResponseBody
	public Object getList(HttpServletRequest request,SearchOptionBean searchOption){
		LOG.info("MyQuestionnaireAction_getList start");
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		searchOption.setCategoryIdListStr(null);
		searchOption.setUserId(userBean.getId());
		MMGridPageVoBean<QuestionnaireBean> re = new MMGridPageVoBean<QuestionnaireBean>();
		List<QuestionnaireBean> list =  myQuestionnaireService.getVoList(searchOption);
		int count = myQuestionnaireService.getTotalCount(searchOption);
		//resolveFamilyTree(searchOption);
		re.setTotal(count);
		re.setRows(list);
		LOG.info("MyQuestionnaireAction_getList end");
		return re;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toAttend <BR>
	 * Description: 跳转至在线调查页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 * @throws Exception 
	 */
	@RequestMapping(value = "toAttend")
	public String toAttend(HttpServletRequest request,int id,int assignId) throws Exception{
		LOG.info("MyQuestionnaireAction_toAttend start");
		InvestigationBean iBean = investigationService.get(id);
		//List<ManageUserBean> userList = investigationService.getUser(id);
		request.setAttribute("iBean", JsonUtil.getJson4JavaObject(iBean));
		//request.setAttribute("iBean_userList", JsonUtil.getJson4JavaList(userList));
		request.setAttribute("assignId",assignId);
		LOG.info("MyQuestionnaireAction_toAttend end");
		return "my_questionnaire/online_questionnaire";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: submitQuestionnaire <BR>
	 * Description: 提交问卷 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 * @throws Exception 
	 */
	@RequestMapping(value = "submitQuestionnaire")
	@ResponseBody
	public Object submitQuestionnaire(HttpServletRequest request,InvestigationAnswerVo vo) throws Exception{
		LOG.info("MyQuestionnaireAction_submitQuestionnaire start");
		try {
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			vo.setUserId(Integer.parseInt(userBean.getId()));
			myQuestionnaireService.saveAnswer(vo);
			LOG.info("MyQuestionnaireAction_submitQuestionnaire end");
			return Constant.AJAX_SUCCESS;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toUserAnswerDetail <BR>
	 * Description: 跳转至用户答卷详情页 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @param assignId
	 * @return
	 * @throws Exception  String<BR>
	 */
	@RequestMapping(value = "toUserAnswerDetail")
	public String toUserAnswerDetail(HttpServletRequest request,int id,int assignId,String backType) throws Exception{
		LOG.info("MyQuestionnaireAction_toUserAnswerDetail start");
		InvestigationBean iBean = investigationService.get(id);
		//List<ManageUserBean> userList = investigationService.getUser(id);
		request.setAttribute("iBean", JsonUtil.getJson4JavaObject(iBean));
		request.setAttribute("backType",backType);
		//request.setAttribute("iBean_userList", JsonUtil.getJson4JavaList(userList));
		request.setAttribute("assignId",assignId);
		LOG.info("MyQuestionnaireAction_toUserAnswerDetail end");
		return "my_questionnaire/questionnaire_view";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAnswerDetail <BR>
	 * Description: 查询用户答卷详情 <BR>
	 * Remark: <BR>
	 * @param assignId
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getAnswerDetail")
	@ResponseBody
	public Object getAnswerDetail(int assignId){
		LOG.info("MyQuestionnaireAction_getAnswerDetail start");
		List<QuestionnaireQuestionBean> list = questionnaireService.getAnswerDetail(assignId);
		LOG.info("MyQuestionnaireAction_getAnswerDetail end");
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: exportDoc <BR>
	 * Description: 员工答卷详情 导出word <BR>
	 * Remark: <BR>
	 * @param request
	 * @param response
	 * @param assignId  void<BR>
	 */
	@ResponseBody
	@RequestMapping("exportDoc")
	public void exportDoc(HttpServletRequest request,HttpServletResponse response,int assignId) {
		LOG.info("MyQuestionnaireAction_exportDoc start");
		try {
			//ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			String fileName = myQuestionnaireService.generateFileName(assignId)+".docx";
			fileName = URLEncoder.encode(fileName, "utf-8");
			response.setContentType("application/x");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename*=UTF-8''%s",fileName);
			response.setHeader(headerKey, headerValue);
			XWPFDocument doc = myQuestionnaireService.exportDoc(assignId);
			OutputStream out = response.getOutputStream();
			doc.write(out);
			out.close();
			LOG.info("MyQuestionnaireAction_exportDoc end");
		} catch (Exception e) {
			LOG.debug(ExamAction.class,e);
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoViewResult <BR>
	 * Description: 查看结果汇总 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @param assignId
	 * @return  String<BR>
	 */
	@RequestMapping(value = "gotoViewResult")
	public String gotoViewResult(HttpServletRequest request,int id){
		LOG.info("MyQuestionnaireAction_gotoViewResult start");
		InvestigationBean iBean = investigationService.get(id);
		request.setAttribute("iBean", JsonUtil.getJson4JavaObject(iBean));
		LOG.info("MyQuestionnaireAction_gotoViewResult end");
		return "my_questionnaire/result_view";
	}

}
