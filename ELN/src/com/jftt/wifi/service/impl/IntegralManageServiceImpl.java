/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: IntegralManageServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月20日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;

import com.jftt.wifi.bean.IntegralBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.dao.InformationManageDaoMapper;
import com.jftt.wifi.dao.IntegralManageDaoMapper;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.ManageUserService;

/**
 * class name:IntegralManageServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月20日
 * @author JFTT)liucaibao
 */
@Service("integralManageService")
public class IntegralManageServiceImpl implements IntegralManageService {

	
	
	
	@Resource(name="integralManageDaoMapper")
	private IntegralManageDaoMapper integralManageDaoMapper;

	
	@Autowired
	private ManageUserService manageUserService;
	/**
	 * @Override
	 * @see com.jftt.wifi.service.IntegralManageService#listIntegral(java.lang.String) <BR>
	 * Method name: listIntegral <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return  <BR>
	 */

	@Override
	public List<IntegralBean> listIntegralRule(IntegralBean integralBean) {
		return  integralManageDaoMapper.listIntegralRule(integralBean);
		
	}
	public int querylistIntegralRuleCount(IntegralBean integralBean){
		return integralManageDaoMapper.querylistIntegralRuleCount(integralBean);
	}

	public List<Map>queryActionPro(String language,int companyId){
		return integralManageDaoMapper.queryActionPro(language,companyId);
	}
	
	@Override
	public void deleteOneIntRule(int ruleId,int userId) {
		// TODO Auto-generated method stub
		Map<String, Integer>  param = new HashMap<String, Integer>();
		param.put("ruleId",ruleId);
		param.put("userId",userId);
		integralManageDaoMapper.deleteOneIntRule(param);
	}
	

	@Override
	public void updateOneRuleStatus(String ruleId, String isDisabled,int userId) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("ruleId", ruleId);
		param.put("isDisabled", isDisabled);
		param.put("userId", userId);
		integralManageDaoMapper.updateOneRuleStatus(param);
	}


	@Override
	public void saveIntRule(IntegralBean integralBean) {
		// TODO Auto-generated method stub
		integralManageDaoMapper.saveIntRule(integralBean);
	}
	@Override
	public IntegralBean queryIntegralById(int ruleId){
		return integralManageDaoMapper.queryIntegralById(ruleId);
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void handleIntegral(int userId, int actionCode) throws Exception{
		
		//获取此公司的Id
		/*ManageUserBean manageUserBean = manageUserService.findUserById(String.valueOf(userId));//拿到用户对象
		int companyId =   manageUserBean.getCompanyId();*/
		
		//2、再获取此公司的此模块下此动作的积分规则
/*		Map<String,Object> pram = new HashMap<String,Object>();
		pram.put("companyId", companyId);
		pram.put("actionCode", actionCode);
*/		// 此处后续需求变动，修改为所有公司都使用普联的积分动作规则，积分动作规则只由普联来创建
		Map<String,Object> pram = new HashMap<String,Object>();
		pram.put("companyId", 1);
		pram.put("actionCode", actionCode);
		
		IntegralBean  integralBean  =integralManageDaoMapper.queryCompanyInteRule(pram);
		if(integralBean==null){//如果没有匹配的规则，则直接return
			return;
		}
		
		pram.put("userId", userId);
		pram.put("ruleId", integralBean.getRuleId());
		integralBean.setUserId(userId);
		//获取积分记录信息
		int size = integralManageDaoMapper.queryUesrInte(pram);
		//3、校验此积分是否符合积分录入
		
		if("1".equals(integralBean.getTimeRange())){//不限制
			
			insertUserIntegral(integralBean);
			
			insertTotalInteral(integralBean);
		}
		else if("2".equals(integralBean.getTimeRange())){//一次性
		
			if(0==size){//不存在记录，在进行插入操作
				
				insertUserIntegral(integralBean);
				
				insertTotalInteral(integralBean);
			}
		}
		
		else if("3".equals(integralBean.getTimeRange())){//每天
		
			//当前天内，一共有多少条记录
			int count = integralManageDaoMapper.queryCurrDayData(pram);
			
			if(integralBean.getMaxTimes()>count){
				
				insertUserIntegral(integralBean);
				
				insertTotalInteral(integralBean);
			}
		}
		else if("4".equals(integralBean.getTimeRange())){//每小时
			//当前小时内，一共有多少条记录
			int count = integralManageDaoMapper.queryCurrMinuteData(pram);
			
			if(integralBean.getMaxTimes()>count){
				
				insertUserIntegral(integralBean);
				
				insertTotalInteral(integralBean);
			}
		}
	}
	
	/**
	 * Method name: 插入积分记录表
	 * Description: insertUserIntegral <BR>
	 * Remark: <BR>
	 * @param integralBean  void<BR>
	 */
	private void insertUserIntegral(IntegralBean integralBean){
		
		integralManageDaoMapper.insertUserIntegral(integralBean);
	}
	
	
	
	/**
	 * Method name: 插入总积分表
	 * Description: insertTotalInteral <BR>
	 * Remark: <BR>
	 * @param integralBean  void<BR>
	 */
	private void insertTotalInteral(IntegralBean integralBean){
		
		integralManageDaoMapper.insertTotalInteral(integralBean);
	}
	
	/**
	 * Method name: 判断时间是否是符合的范围内
	 * Description: isTimeRange <BR>
	 * Remark: <BR>
	 * @param date
	 * @param dateType
	 * @return  boolean<BR>
	 */
	private boolean isTimeRange(Date date,String dateType) { 
	    Calendar c1 = Calendar.getInstance(); 
	    boolean result = false;
	    c1.setTime(date);                                 
	    int year1 = c1.get(Calendar.YEAR);
	        int month1 = c1.get(Calendar.MONTH)+1;
	        int day1 = c1.get(Calendar.DAY_OF_MONTH);    
	        Calendar c2 = Calendar.getInstance();    
	        c2.setTime(new Date());      
	        int year2 = c2.get(Calendar.YEAR);
	        int month2 = c2.get(Calendar.MONTH)+1;
	        int day2 = c2.get(Calendar.DAY_OF_MONTH);   
	        if(year1 == year2 && month1 == month2 && day1 == day2){
	        	if("HOUR".equals(dateType)){
	        		if(c2.get(Calendar.HOUR_OF_DAY)==c1.get(Calendar.HOUR_OF_DAY)){
	        			result =  true;
	        		}
	        	}else {
	        		
	        		result =  true;
	        	}
	        }
	    return result;                               
	}
}
