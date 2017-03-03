/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: CountLineListener.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-19        | JFTT)wangjian    | original version
 */
package com.jftt.wifi.interceptor;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.jftt.wifi.bean.LoginHisBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.LoginHisService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.SpringContextUtil;

/**
 * class name:CountLineListener <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-19
 * @author JFTT)wangjian
 */
public class CountLineListener implements HttpSessionListener, HttpSessionAttributeListener{
	
	private static Logger log = Logger.getLogger(CountLineListener.class); 
	
	public static Map<String, HttpSession> map = new HashMap<String, HttpSession>();
	
	private static LoginHisService getLoginHisService(){
		
		return SpringContextUtil.getBean("loginHisService");
	}

	/**
	 * @Override
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent) <BR>
	 * Method name: sessionCreated <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param arg0  <BR>
	*/
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
		//System.out.println("新建一个session " + arg0.getSession().getId() + "   " + CommonUtil.getDateTimeString(new Date())); 
	}

	/**
	 * @Override
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent) <BR>
	 * Method name: sessionDestroyed <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param arg0  <BR>
	*/
	
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
		System.out.println("销毁一个session " + arg0.getSession().getId() + "   " + CommonUtil.getDateTimeString(new Date())); 
	}

	/**
	 * @Override
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent) <BR>
	 * Method name: attributeAdded <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param arg0  <BR>
	*/
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		
		/*System.out.println("session增加属性 " + arg0.getSession().getId() + "   " + CommonUtil.getDateTimeString(new Date())); 
		System.out.println("session增加属性 " + arg0.getName() + "   " + arg0.getValue()); 
		
		Enumeration<String> list = arg0.getSession().getAttributeNames();
		while (list.hasMoreElements()) {
			String a = list.nextElement().toString();
			System.out.println(a + "=" + arg0.getSession().getAttribute(a));
		}
		
		System.out.println("session增加属性 END"); */
		
		if(arg0.getName().equals(Constant.SESSION_USERID_LONG)){
			//新用户 登陆
			long userId = Long.parseLong(arg0.getValue().toString());
			String sessionId = arg0.getSession().getId();
			
			//是否有登陆记录
			LoginHisBean old = getLoginHisService().getLoginHisByUserLast(userId);
			if(old != null){
				//删除记录
				getLoginHisService().deleteLoginHisById(old.getId());
				//旧的那位增加标记位
				if(map.containsKey(old.getSession_id())){
					map.get(old.getSession_id()).setAttribute(Constant.SESSION_AGAIN, old.getId());
				}
			}
			
			//增加数据库记录
			LoginHisBean loginHisBean = new LoginHisBean();
			loginHisBean.setUser_id(userId);
			loginHisBean.setLogin_time(CommonUtil.getCurrentDatetime());
			loginHisBean.setSession_id(sessionId);
			loginHisBean.setCompanyId(Long.parseLong(String.valueOf(arg0.getSession().getAttribute(Constant.SESSION_USER_COMPANYID))));
			
			//记录
			getLoginHisService().addLoginHis(loginHisBean);
			
			arg0.getSession().removeAttribute(Constant.SESSION_AGAIN);
			
			map.put(sessionId, arg0.getSession());	
		}
	}

	/**
	 * @Override
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent) <BR>
	 * Method name: attributeRemoved <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param arg0  <BR>
	*/
	
	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		
		/*System.out.println("session去除属性 " + arg0.getSession().getId() + "   " + CommonUtil.getDateTimeString(new Date())); 
		System.out.println("session去除属性 " + arg0.getName() + "   " + arg0.getValue()); 
		
		Enumeration<String> list = arg0.getSession().getAttributeNames();
		while (list.hasMoreElements()) {
			String a = list.nextElement().toString();
			System.out.println(a + "=" + arg0.getSession().getAttribute(a));
		}
		
		System.out.println("session去除属性 END"); */
		
		if(arg0.getName().equals(Constant.SESSION_USERID_LONG)){
			//用户 退出 或session消亡
			map.remove(arg0.getSession().getId());
			
			//删除记录
			getLoginHisService().deleteLoginHis(arg0.getSession().getId());
			
			//arg0.getSession().invalidate();
		}
	}

	/**
	 * @Override
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent) <BR>
	 * Method name: attributeReplaced <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param arg0  <BR>
	*/
	
	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		
		/*System.out.println("session替换属性 " + arg0.getSession().getId() + "   " + CommonUtil.getDateTimeString(new Date())); 
		System.out.println("session替换属性 " + arg0.getName() + "   " + arg0.getValue()); 
		
		Enumeration<String> list = arg0.getSession().getAttributeNames();
		while (list.hasMoreElements()) {
			String a = list.nextElement().toString();
			System.out.println(a + "=" + arg0.getSession().getAttribute(a));
		}
		
		System.out.println("session替换属性 END"); */
		
	}

}
