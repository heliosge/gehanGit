/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: SetUserInfoUtils.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/20        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.knowledge_contest;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ReflectionUtils;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.common.Constant;

/**
 * @author JFTT)wangyifeng
 * class name:SetUserInfoUtils <BR>
 * class description: 作用：向传入Action的参数注入存储于Session中的用户关联的信息 <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/20
 */
public class SetUserInfoUtils {
	// TODO 考虑实现
	// 1.可以指定字段列表
	// 2.可以指定字段列表的映射关系（bean里取特殊名称）

	public static void doWork(HttpServletRequest request, Object bean,
			String propertyName, Class<?> propertyClass) {
		Object value = getValue(request, propertyName);

		setValue(bean, propertyName, propertyName, propertyClass, value);
	}

	public static void doWork(HttpServletRequest request, Object bean,
			String fromPropertyName, String toPropertyName, Class<?> toClass) {
		Object value = getValue(request, fromPropertyName);

		value = convertValue(value, toClass);

		setValue(bean, fromPropertyName, toPropertyName, toClass, value);
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: setValue <BR>
	 * Description: setValue <BR>
	 * Remark: <BR>
	 * @param bean
	 * @param fromPropertyName
	 * @param toPropertyName
	 * @param toClass
	 * @param value  void<BR>
	 */
	private static void setValue(Object bean, String fromPropertyName,
			String toPropertyName, Class<?> toClass, Object value) {
		// test only TODO
		// if
		// (CommonConstants.FIELD_NAME_SUB_COMPANY_ID.equals(fromPropertyName))
		// {
		// value = 888;
		// } else if (CommonConstants.FIELD_NAME_ID.equals(fromPropertyName)) {
		// value = 888;
		// }

		Method setMethod = ReflectionUtils.findMethod(bean.getClass(), "set"
				+ toPropertyName, toClass);
		ReflectionUtils.invokeMethod(setMethod, bean, value);
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getValue <BR>
	 * Description: getValue <BR>
	 * Remark: <BR>
	 * @param request
	 * @param fromPropertyName
	 * @return  Object<BR>
	 */
	private static Object getValue(HttpServletRequest request,
			String fromPropertyName) {
		ManageUserBean userInfo = (ManageUserBean) request.getSession()
				.getAttribute(Constant.SESSION_USERBEAN);

		Method getMethod = ReflectionUtils.findMethod(ManageUserBean.class,
				"get" + fromPropertyName);
		Object value = ReflectionUtils.invokeMethod(getMethod, userInfo);
		return value;
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: convertValue <BR>
	 * Description: convertValue <BR>
	 * Remark: <BR>
	 * @param fromClass
	 * @param toClass
	 * @param value
	 * @return  Object<BR>
	 */
	private static Object convertValue(Object value, Class<?> toClass) {
		if (value.getClass() != toClass) {
			if (value.getClass() == String.class && toClass == Integer.class) {
				value = Integer.parseInt((String) value);
			}
		}
		return value;
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getUserPropertyInfo <BR>
	 * Description: 提供接口，用于获取特定的用户信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param fromPropertyName
	 * @return  Object<BR>
	 */
	public static Object getUserPropertyInfo(HttpServletRequest request,
			String fromPropertyName) {
		return getValue(request, fromPropertyName);
	}
}
