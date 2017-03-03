/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagDaoMapper.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2013/01/07           |  JFTT)wangjian    |  original version
 */
package com.jftt.wifi.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.LoginHisBean;
import com.jftt.wifi.dao.LoginHisDaoMapper;
import com.jftt.wifi.service.LoginHisService;
import com.jftt.wifi.service.MyExamManageService;


/**
 * 增加登陆记录
 */
@Service("loginHisService")
public class LoginHisServiceImpl implements LoginHisService{
	
	@Resource(name="loginHisDaoMapper")
	private LoginHisDaoMapper loginHisDaoMapper;
	
	//增加登陆记录
	public void addLoginHis(LoginHisBean loginHisBean){
		
		loginHisDaoMapper.addLoginHis(loginHisBean);
	}
	
	//删除登陆记录
	public void deleteLoginHis(String sessionId){
		
		loginHisDaoMapper.deleteLoginHis(sessionId);
	}
	
	//删除登陆记录
	public void deleteLoginHisById(long id){
		
		loginHisDaoMapper.deleteLoginHisById(id);
	}
	
	//删除登陆记录 所有
	public void deleteLoginHisAll(){
		
		loginHisDaoMapper.deleteLoginHisAll();
	}
	
	//获取最近一次用户的登陆 信息
	public LoginHisBean getLoginHisByUserLast(long userId){
		
		return loginHisDaoMapper.getLoginHisByUserLast(userId);
	}

	
	/*luyl begin*/
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.LoginHisService#selectUserCountByMap(java.util.Map) <BR>
	 * Method name: selectUserCountByMap <BR>
	 * Description: 根据条件获取当前在线用户 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  <BR>
	*/
	@Override
	public int selectUserCountByMap(Map<String, Object> param) {
		return loginHisDaoMapper.selectUserCountByMap(param);
	}
	
	/*luyl end*/
}
