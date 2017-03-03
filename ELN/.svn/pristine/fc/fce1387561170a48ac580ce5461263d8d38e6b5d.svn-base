/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: IntegralManageService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月20日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import com.jftt.wifi.bean.IntegralBean;

import net.sf.json.JSONArray;

/**
 * class name:IntegralManageService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月20日
 * @author JFTT)liucaibao
 */
public interface IntegralManageService {

	
	/**
	 * Method name: 查询积分列表以及总数
	 * Description: listIntegral <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return  JSONArray<BR>
	 */
	public List<IntegralBean> listIntegralRule(IntegralBean integralBean);
	public int querylistIntegralRuleCount(IntegralBean integralBean); 
	
	
	
	/**
	 * Method name:查询所有的可以获得积分的动作
	 * Description: queryActionPro <BR>
	 * Remark: <BR>
	 * @param langue
	 * @return  List<Map><BR>
	 */
	public List<Map>queryActionPro(String langue,int companyId);
	
	/**
	 * Method name: queryIntegralById <BR>
	 * Description: 查询单个积分规则 <BR>
	 * Remark: <BR>
	 * @param ruleId
	 * @return  IntegralBean<BR>
	 */
	public IntegralBean queryIntegralById(int ruleId);
	/**
	 * Method name: 删除单个的积分规则
	 * Description: deleteOneIntRule <BR>
	 * Remark: <BR>
	 * @param ruleId  void<BR>
	 */
	public void deleteOneIntRule(int ruleId,int userId)throws Exception;
	
	
	/**
	 * Method name: 禁用单个的积分规则
	 * Description: updateOneRuleStatus <BR>
	 * Remark: <BR>
	 * @param ruleId
	 * @param isDisabled  void<BR>
	 */
	public void  updateOneRuleStatus(String ruleId,String isDisabled,int userId)throws Exception;
	
	
	/**
	 * Method name:保存积分规则
	 * Description: saveIntRule <BR>
	 * Remark: <BR>
	 * @param integralBean  void<BR>
	 */
	public void saveIntRule(IntegralBean integralBean) throws Exception;
	
	
	

	/**
	 * Method name: 积分处理入口方法
	 * Description: handleIntegral <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @param moduleCode
	 * @param actionCode  void<BR>
	 */
	public void handleIntegral(int userId,int actionCode) throws Exception;
	
}
