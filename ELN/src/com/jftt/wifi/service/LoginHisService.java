/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagDaoMapper.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2013/01/07           |  JFTT)wangjian    |  original version
 */
package com.jftt.wifi.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.LoginHisBean;


/**
 * 增加登陆记录
 */
public interface LoginHisService {
	
	
	//增加登陆记录
	public void addLoginHis(LoginHisBean loginHisBean);
	
	//删除登陆记录
	public void deleteLoginHis(String sessionId);
	
	//删除登陆记录
	public void deleteLoginHisById(long id);
	
	//删除登陆记录 所有
	public void deleteLoginHisAll();
	
	//获取最近一次用户的登陆 信息
	public LoginHisBean getLoginHisByUserLast(long userId);

	/*luyl begin*/
	
	/**
	 * Method name: selectUserCountByMap <BR>
	 * Description: 根据条件获取当前在线用户 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  int<BR>
	 */
	public int selectUserCountByMap(Map<String, Object> param);
	
	/*luyl end*/
}
