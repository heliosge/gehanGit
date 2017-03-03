/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CertificateAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月7日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.ManageIndustryCategoryBean;
import com.jftt.wifi.bean.ResCoursewareBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManageIndustryCategoryService;

/**
 * class name:CertificateAction <BR>
 * class description: 行业体系 <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月7日
 * @author JFTT)Lu Yunlong
 */
public class ManageIndustryCategoryAction {

	@Resource(name="manageIndustryCategoryService")
	ManageIndustryCategoryService manageIndustryCategoryService;
	
	
	/**
	 * Method name: toIndustryCategoryPage <BR>
	 * Description: 【企业分类】页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toIndustryCategoryPage")
	public String toIndustryCategoryPage(HttpServletRequest request){
		return "manage/industryCategory";
	}
	
	/**
	 * Method name: selectIndustryCategoryList <BR>
	 * Description: 查询 【企业分类】<BR>
	 * Remark: <BR>
	 * @param request
	 * @param name
	 * @return  Object<BR>
	 */
	@RequestMapping("selectIndustryCategoryList")
	@ResponseBody
	public Object selectIndustryCategoryList(HttpServletRequest request, String name){
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("name", name);
			List<ManageIndustryCategoryBean> result = manageIndustryCategoryService.select(param);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: insertIndustryCategory <BR>
	 * Description: 新增 【企业分类】<BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  String<BR>
	 */
	@RequestMapping("insertIndustryCategory")
	@ResponseBody
	public String insertIndustryCategory(HttpServletRequest request, ManageIndustryCategoryBean bean){
		try {
			manageIndustryCategoryService.insert(bean);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: updateIndustryCategory <BR>
	 * Description: 修改 【企业分类】<BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  String<BR>
	 */
	@RequestMapping("updateIndustryCategory")
	@ResponseBody
	public String updateIndustryCategory(HttpServletRequest request, ManageIndustryCategoryBean bean){
		try {
			manageIndustryCategoryService.update(bean);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: deleteIndustryCategory <BR>
	 * Description: 删除【企业分类】 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping("deleteIndustryCategory")
	@ResponseBody
	public String deleteIndustryCategory(HttpServletRequest request, String id){
		try {
			manageIndustryCategoryService.delete(Integer.parseInt(id));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
}
