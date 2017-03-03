/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: IntegralManageDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月20日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;

import com.jftt.wifi.bean.IntegralBean;


/**
 * class name:IntegralManageDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月20日
 * @author JFTT)liucaibao
 */
public interface IntegralManageDaoMapper {

	
	/**
	 * Method name: 查询积分规则列表
	 * Description: listIntegral <BR>
	 * Remark: <BR>
	 * @param company
	 * @return  JSONArray<BR>
	 */
	public  List<IntegralBean> listIntegralRule(IntegralBean integralBean);
	public  int  querylistIntegralRuleCount(IntegralBean integralBean);
	
	
	
	/**
	 * Method name: 查询可以获取积分的动作
	 * Description: queryActionPro <BR>
	 * Remark: <BR>
	 * @param language
	 * @return  List<Map><BR>
	 */
	public  List<Map> queryActionPro(@Param("language")String language,@Param("companyId")int companyId);
	
	/**
	 * Method name:删除单个的积分规则
	 * Description: deleteOneIntRule <BR>
	 * Remark: <BR>
	 * @param ruleId  void<BR>
	 */
	public void deleteOneIntRule(Map map);
	
	
	/**
	 * Method name: 禁用积分规则
	 * Description: updateOneRuleStatus <BR>
	 * Remark: <BR>
	 * @param ruleId
	 * @param isDisabled  void<BR>
	 */
	public void updateOneRuleStatus(Map map);
	
	
	
	
	/**
	 * Method name: 保存积分规则
	 * Description: saveIntRule <BR>
	 * Remark: <BR>
	 * @param integralBean  void<BR>
	 */
	public void saveIntRule(IntegralBean integralBean);
	
	
	
	/**
	 * Method name: 查询此公司的积分规则
	 * Description: queryCompanyRule <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @param moduleCode
	 * @param actionCode
	 * @return  IntegralBean<BR>
	 */
	public IntegralBean queryCompanyInteRule(Map map);
	
	
	/**
	 * Method name: queryIntegralById <BR>
	 * Description: 查询积分规则详情 <BR>
	 * Remark: <BR>
	 * @param ruleId
	 * @return  IntegralBean<BR>
	 */
	public IntegralBean queryIntegralById(@Param("ruleId") int ruleId);
	/**
	 * Method name: 查询用户的积分详细信息
	 * Description: queryUesrInte <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @param moduleCode
	 * @param actionCode
	 * @return  int<BR>
	 */
	public int  queryUesrInte(Map map);
	
	
	/**
	 * Method name: insertTotalInteral <BR>
	 * Description: insertTotalInteral <BR>
	 * Remark: <BR>
	 * @param integralBean  void<BR>
	 */
	public void insertTotalInteral(IntegralBean integralBean);
	
	
	/**
	 * Method name: insertUserIntegral <BR>
	 * Description: insertUserIntegral <BR>
	 * Remark: <BR>
	 * @param integralBean  void<BR>
	 */
	public void  insertUserIntegral (IntegralBean integralBean);
	
	
	
	/**
	 * Method name:查询某个用户当天的
	 * Description: queryCurrDayData <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return  int<BR>
	 */
	public int queryCurrDayData(Map<String,Object> map);
	
	
	/**
	 * Method name: queryCurrMinuData <BR>
	 * Description: queryCurrMinuData <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return  int<BR>
	 */
	public int queryCurrMinuteData(Map<String,Object> map);
}
