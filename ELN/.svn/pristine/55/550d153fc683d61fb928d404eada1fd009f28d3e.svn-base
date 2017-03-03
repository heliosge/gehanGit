/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: StartupListener.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-3-9        | JFTT)wangjian    | original version
 */
package com.jftt.wifi.interceptor;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.jftt.wifi.action.ManagePageAction;
import com.jftt.wifi.service.LoginHisService;
import com.jftt.wifi.util.SpringContextUtil;

/**
 * class name:StartupListener <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2016-3-9
 * @author JFTT)wangjian
 */

/**
 * 启动监听器
 */
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
	
	private static Logger logger = Logger.getLogger(StartupListener.class);  
	
	private LoginHisService loginHisService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
    	
    	logger.debug("项目启动时运行");
    	
    	if(evt.getApplicationContext().getParent() == null){
    		//root application context 没有parent，他就是老大.
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
    		logger.debug("项目启动时运行 删除所有登陆数据 START");
    		
        	loginHisService.deleteLoginHisAll();
        	
        	logger.debug("项目启动时运行 删除所有登陆数据 END");
        }
    }

	public LoginHisService getLoginHisService() {
		return loginHisService;
	}

	public void setLoginHisService(LoginHisService loginHisService) {
		this.loginHisService = loginHisService;
	}
 
}
