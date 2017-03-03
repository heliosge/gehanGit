/*
* All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/06        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jftt.wifi.common.Constant;
import com.jftt.wifi.interceptor.CountLineListener;
import com.jftt.wifi.service.LoginHisService;
import com.jftt.wifi.service.ManageCompanyService;

/**
 * 在线人数
 */
@Controller
@RequestMapping(value = "onlineCount")
public class OnlineCountAction {
	private static final Logger LOG = Logger.getLogger(OnlineCountAction.class);
	
	@Resource(name="loginHisService")
	private LoginHisService loginHisService;
	
	@Resource(name="manageCompanyService")
	private ManageCompanyService manageCompanyService;

	/**
	 * 普联在线人数监控
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value="onlineCount")
	public String onlineCount(HttpServletRequest request) throws NumberFormatException, Exception {
		
		Map<String, Object> param = new HashMap<String, Object>();
		int companyId = Integer.parseInt(request.getSession().getAttribute(Constant.SESSION_USER_COMPANYID).toString());
		param.put("companyId", companyId == Constant.PULIAN_COMPANY_ID ? null : companyId);
		request.setAttribute("onlineCount", loginHisService.selectUserCountByMap(param));
		request.setAttribute("maxCount", companyId == Constant.PULIAN_COMPANY_ID ? "-" : manageCompanyService.selectCompanyById(companyId).getMaxConcurrent());
		
		return "onlineCount/online_count";
	}
}
