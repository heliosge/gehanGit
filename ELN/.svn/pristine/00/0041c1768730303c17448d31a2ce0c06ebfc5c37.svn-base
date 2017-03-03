/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamPaperCategoryAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/28        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.PaperCategoryBean;
import com.jftt.wifi.bean.exam.vo.PaperCategorySearchOptionVo;
import com.jftt.wifi.bean.knowledge_contest.CommonConstants;
import com.jftt.wifi.bean.knowledge_contest.SetUserInfoUtils;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ExamPaperCategoryService;
import com.jftt.wifi.service.ManageUserService;

/**
 * class name:ExamPaperCategoryAction <BR>
 * class description: 试卷分类Action <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/28
 * @author JFTT)wangyifeng
 */
@Controller
@RequestMapping(value = "exam/paperCategory")
public class ExamPaperCategoryAction {
	@Autowired
	private ExamPaperCategoryService paperCategoryService;

	private static final Logger LOG = Logger
			.getLogger(ExamPaperCategoryAction.class);
	@Autowired
	private ManageUserService manageUserService;
	// Begin by wangyifeng
	/**
	 * Method name: getQuestionCategoryList <BR>
	 * Description: 获取试卷库分类列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return List<QuestionCategoryBean><BR>
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public List<PaperCategoryBean> getPaperCategoryList(HttpServletRequest request,
			PaperCategorySearchOptionVo searchOption) {
		LOG.info("getPaperCategoryList");

		try {
			
			SetUserInfoUtils.doWork(request, searchOption,
					CommonConstants.FIELD_NAME_ID,
					CommonConstants.FIELD_NAME_USER_ID, String.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paperCategoryService.getPaperCategoryList(searchOption);
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
	public String addCategory(HttpServletRequest request, PaperCategoryBean newCategory) {
		LOG.info("addCategory");
		try {
			
			SetUserInfoUtils.doWork(request, newCategory, CommonConstants.FIELD_NAME_SUB_COMPANY_ID, Integer.class);
			SetUserInfoUtils.doWork(request, newCategory, CommonConstants.FIELD_NAME_COMPANY_ID, Integer.class);
			SetUserInfoUtils.doWork(request, newCategory, CommonConstants.FIELD_NAME_ID, CommonConstants.FIELD_NAME_CREATE_USER_ID, Integer.class);
			
			paperCategoryService.addCategory(newCategory);
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
	public String modifyCategory(PaperCategoryBean modifiedCategory) {
		LOG.info("modifyCategory");
		paperCategoryService.modifyCategory(modifiedCategory);
		return Constant.AJAX_SUCCESS;
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
			paperCategoryService.deleteCategory(categoryId);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn(String.format("Delete category(id: %d) failed.",
					categoryId), e);
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * Method name: getQuestionCategory <BR>
	 * Description: 获取单个试卷库分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @return QuestionCategoryBean<BR>
	 */
	@RequestMapping(value = "/manage/get")
	@ResponseBody
	public PaperCategoryBean getPaperCategory(Integer categoryId) {
		LOG.info("getPaperCategory");
		return paperCategoryService.getCategory(categoryId);
	}
	// End by wangyifeng

}
