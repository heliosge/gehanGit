/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: IntegralManageAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月17日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.action;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.jftt.wifi.bean.IntegralBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.JsonUtil;

/**
 * class name: 积分管理action类
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月17日
 * @author JFTT)liucaibao
 */
@Controller
@RequestMapping(value="integral")
public class IntegralManageAction {
	
	@Resource(name="integralManageService")
	private IntegralManageService integralManageService;
	
	@Autowired
	private ManageUserService manageUserService;
	private static Logger log = Logger.getLogger(IntegralManageAction.class); 

	/**
	 * Method name: 查询积分列表
	 * Description: listIntegralRule <BR>
	 * Remark: <BR>
	 * @param request
	 * @param informationBean
	 * @return  String<BR>
	 */
	@RequestMapping(value = "list")
	public String  listIntegralRule(HttpServletRequest request,IntegralBean integralBean ){
		//入口日志
		log.debug("Enter Class:IntegralManageAction Method:listIntegralRule param:"+integralBean.toString() );
		return "integral/listInt";
	}
	
	@RequestMapping(value = "getListByMap")
	@ResponseBody
	public Object getListByMap(HttpServletRequest request, int page, int pageSize,IntegralBean integralBean){
			
			//入口日志
			log.debug("Enter Class:IntegralManageAction Method:getListByMap param:"+integralBean.toString() );
			//组织参数
			String language= String.valueOf(RequestContextUtils.getLocale(request));
			integralBean.setLanguage(language);
			int size = 0;
			try {
				integralBean.setCompanyId(getCompanyId(request));
				size = integralManageService.querylistIntegralRuleCount(integralBean);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e+"查询列表总数失败。param:"+integralBean.toString());
			}
			
			List<IntegralBean> rows = null;
			try {
				integralBean.setFromNum((page-1)*pageSize);
				
				integralBean.setPage(pageSize);
				rows = integralManageService.listIntegralRule(integralBean);//manageUserService.findUserByCondition(map,page,pageSize);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e+"查询列表失败。param:"+integralBean.toString());
			}
			
			MMGridPageVoBean<IntegralBean> vo = new MMGridPageVoBean<IntegralBean>();
			vo.setTotal(size);
			vo.setRows(rows);
			return vo;
		}
	
	
	
	/**
	 * Method name: 初始化积分规则
	 * Description: addRule <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value = "initRule")
	@ResponseBody
	public Object  initRule(HttpServletRequest request){
		
		//入口日志
		log.debug("Enter Class:IntegralManageAction Method:addRule");
		//针对查询出来的数据，在此处做处理
		JSONObject actionProObj = new JSONObject();
		try {
			String language= String.valueOf(RequestContextUtils.getLocale(request));
			int companyId = getCompanyId(request);
			List<Map> languageList= integralManageService.queryActionPro(language,companyId);
			
			for(Map map:languageList){
				//首先获取此模块分类
				String moule =String.valueOf(map.get("property_category"));
				
				JSONObject sjo =  new JSONObject();//小分类
				if(actionProObj.containsKey(moule)){
					sjo = actionProObj.getJSONObject(moule);
				}
				sjo.put(map.get("property_code"), map.get("property_desc"));
				actionProObj.put(moule, sjo);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return actionProObj;
		}
	}
	
	
	/**
	 * Method name: queryIntegralById <BR>
	 * Description: 查询积分规则 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param ruleId
	 * @return  JSONObject<BR>
	 */
	@RequestMapping(value="queryInt")
	@ResponseBody
	public Object queryIntegralById(HttpServletRequest request,int ruleId){
		try {
			return JsonUtil.getJson4JavaObject(integralManageService.queryIntegralById(ruleId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JSONObject();
		}
 	}
	
	/**
	 * Method name: 删除单个的积分规则
	 * Description: deleteOneIntRule <BR>
	 * Remark: <BR>
	 * @param request
	 * @param ruleId  void<BR>
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String deleteOneIntRule(HttpServletRequest request,int ruleId){
		
		//入口日志
		log.debug("Enter Class:IntegralManageAction Method:deleteOneIntRule param:"+ruleId);
		int userId = getUserId(request);
		if(userId==0){
			return Constant.AJAX_FAIL;
		}
		try {
			integralManageService.deleteOneIntRule(ruleId,userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e+"ruleId:"+ruleId);
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: 批量删除积分规则
	 * Description: deleteOneIntRule <BR>
	 * Remark: <BR>
	 * @param request
	 * @param ruleId  void<BR>
	 */
	@RequestMapping(value = "deleteBatch")
	@ResponseBody
	public String  deleteBatchIntRule(HttpServletRequest request,String ids){
		
		//入口日志
		log.debug("Enter Class:IntegralManageAction Method:deleteBatchIntRule param:"+ids.toString());
		int userId = getUserId(request);
		try {
			if(!ids.isEmpty()){
				List<String> idArr =  Arrays.asList(ids.split("\\|"));
				for(int i=0;i<idArr.size();i++){
					if(StringUtils.isBlank(idArr.get(i)))
						continue;
					int ruleId = Integer.valueOf(idArr.get(i));
					integralManageService.deleteOneIntRule(ruleId,userId);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e+"param:"+ids.toString());
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: 禁用单个的规则
	 * Description: updateOneRuleStatus <BR>
	 * Remark: <BR>
	 * @param request
	 * @param ruleId  void<BR>
	 */
	@RequestMapping(value = "disabled")
	@ResponseBody
	public String updateOneRuleStatus(HttpServletRequest request,String ruleId,String isDisabled){
		
		//入口日志
		log.debug("Enter Class:IntegralManageAction Method:updateOneRuleStatus param:"+ruleId.toString());
		try {
			int userId = getUserId(request);
			if(StringUtils.isNotBlank(ruleId)&&StringUtils.isNotBlank(isDisabled)){
				integralManageService.updateOneRuleStatus(ruleId,isDisabled,userId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e+"param:"+ruleId.toString());
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	/**
	 * Method name: 批量禁用规则
	 * Description: updateOneRuleStatus <BR>
	 * Remark: <BR>
	 * @param request
	 * @param ruleId  void<BR>
	 */
	@RequestMapping(value = "disableBatch")
	@ResponseBody
	public String updateBatchRuleStatus(HttpServletRequest request,String ids){
		//入口日志
		log.debug("Enter Class:IntegralManageAction Method:updateBatchRuleStatus param:"+ids.toString());
		int userId = getUserId(request);
		try{
			if(!ids.isEmpty()){
				List<String> idArr =  Arrays.asList(ids.split("\\|"));
				for(int i=0;i<idArr.size();i++){
					if(StringUtils.isBlank(idArr.get(i)))
						continue;
					String isDisabled = "0";//批量时，默认给0, 0:禁用 1:启用
					String ruleId = idArr.get(i);
					integralManageService.updateOneRuleStatus(ruleId,isDisabled,userId);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e+"param:"+ids.toString());
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: 保存积分规则
	 * Description: saveIntRule <BR>
	 * Remark: <BR>
	 * @param request
	 * @param informationBean  void<BR>
	 */
	@RequestMapping(value = "save",consumes = "application/json")
	@ResponseBody
	public String  saveIntRule(HttpServletRequest request,@RequestBody IntegralBean integralBean){
		//入口日志
		log.debug("Enter Class:IntegralManageAction Method:saveIntRule param:"+integralBean.toString());
		try {
			//组装数据，并保存
			integralBean.setUserId(getUserId(request));
			integralBean.setCompanyId(getCompanyId(request));
			integralManageService.saveIntRule(integralBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e+"param:"+integralBean.toString());
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	
	@RequestMapping(value = "handleInt")
	@ResponseBody
	public String handleInt(HttpServletRequest request,int userId,int code){
		try {
			integralManageService.handleIntegral(userId,code);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;

		}
		return Constant.AJAX_SUCCESS;
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
	
}
