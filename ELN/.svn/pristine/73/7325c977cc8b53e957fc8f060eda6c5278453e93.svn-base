/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireCategoryAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-19        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireCategoryBean;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.QuestionnaireCategoryService;

/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireCategoryAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-19
 */
@Controller
@RequestMapping(value = "questionnaireCategory")
public class QuestionnaireCategoryAction {
	@Autowired
	private QuestionnaireCategoryService questionnaireCategoryService;
	
	/**  
	 * 日志
	 */
	private static final Logger LOG = Logger.getLogger(QuestionnaireCategoryAction.class);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getQuestionnaireCategoryList <BR>
	 * Description: 获取问卷分类列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption
	 * @return  List<QuestionnaireCategoryBean><BR>
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public List<QuestionnaireCategoryBean> getQuestionnaireCategoryList(HttpServletRequest request,SearchOptionBean searchOption) {
		LOG.info("getQuestionnaireCategoryList");
		//session中的人员信息
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		searchOption.setUserId(userBean.getId());
		List<QuestionnaireCategoryBean> list = questionnaireCategoryService.getQuestionnaireCategoryList(searchOption);
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getQuestionCategoryListYun <BR>
	 * Description: 云问卷库，即普联的问卷库 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption
	 * @return  List<QuestionnaireCategoryBean><BR>
	 */
	@RequestMapping(value = "/listYun")
	@ResponseBody
	public List<QuestionnaireCategoryBean> getQuestionCategoryListYun(HttpServletRequest request, SearchOptionBean searchOption) {
		LOG.info("getQuestionCategoryListYun");
		//session中的人员信息
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		searchOption.setUserId(userBean.getId());
		searchOption.setSubCompanyId(1);
		List<QuestionnaireCategoryBean> list = questionnaireCategoryService.getQuestionnaireCategoryListBySubCompany(searchOption);
		QuestionnaireCategoryBean rootNode = new QuestionnaireCategoryBean();
		rootNode.setId(null);
		rootNode.setName("云问卷库");
		list.add(rootNode);
		return list;
	}

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: addCategory <BR>
	 * Description: 增加分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param newCategory
	 * @return  String<BR>
	 */
	@RequestMapping(value = "/manage/add")
	@ResponseBody
	public String addCategory(HttpServletRequest request,QuestionnaireCategoryBean newCategory) {
		LOG.info("addCategory");
		try {
			//session中的人员信息
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			newCategory.setSubCompanyId(userBean.getSubCompanyId());
			newCategory.setCompanyId(userBean.getCompanyId());
			newCategory.setCreateUserId(Integer.parseInt(userBean.getId()));
			questionnaireCategoryService.addCategory(newCategory);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modifyCategory <BR>
	 * Description: 修改分类  <BR>
	 * Remark: <BR>
	 * @param modifiedCategory
	 * @return  String<BR>
	 */
	@RequestMapping(value = "/manage/modify")
	@ResponseBody
	public String modifyCategory(QuestionnaireCategoryBean modifiedCategory) {
		LOG.info("modifyCategory");
		try {
			questionnaireCategoryService.modifyCategory(modifiedCategory);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteCategory <BR>
	 * Description: 删除分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  String<BR>
	 */
	@RequestMapping(value = "/manage/delete")
	@ResponseBody
	public String deleteCategory(Integer categoryId) {
		LOG.info("deleteCategory");
		try {
			questionnaireCategoryService.deleteCategory(categoryId);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn(String.format("Delete category(id: %d) failed.",
					categoryId), e);
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getQuestionCategory <BR>
	 * Description: 获取单个问卷分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  QuestionnaireCategoryBean<BR>
	 */
	@RequestMapping(value = "/manage/get")
	@ResponseBody
	public QuestionnaireCategoryBean getQuestionCategory(Integer categoryId) {
		LOG.info("getQuestionCategory");
		return questionnaireCategoryService.getCategory(categoryId);
	}

}
