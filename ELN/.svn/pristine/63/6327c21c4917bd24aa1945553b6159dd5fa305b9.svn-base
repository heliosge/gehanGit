/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: SampleAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/12        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @author JFTT)wangyifeng
 * class name:SampleAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/12
 */
@Controller
@RequestMapping(value = "sample")
public class SampleAction {
	@RequestMapping(value = "i18n")
	public String toI18n(HttpServletRequest request) {
		// 获取locale信息的方法
		System.out.println(RequestContextUtils.getLocale(request));
		return "sample/i18n";
	}
}
