/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExceptionHandleAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/20        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.action;

import org.apache.log4j.Logger;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author JFTT)wangyifeng
 * class name:ExceptionHandleAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/20
 */
@ControllerAdvice
public class ExceptionHandleAction {
	private static final Logger LOG = Logger
			.getLogger(ExceptionHandleAction.class);
	private static final String EMPTY = new String();

	/**
	 * @author JFTT)wangyifeng
	 * Method name: handleHttpMessageNotWritableException <BR>
	 * Description: 记录异常：HttpMessageNotWritableException <BR>
	 * Remark: <BR>
	 * @param ex
	 * @return  String<BR>
	 */
	@ExceptionHandler({ HttpMessageNotWritableException.class,
			HttpMessageNotReadableException.class })
	public String handleHttpMessageNotWritableException(RuntimeException ex) {
		LOG.warn(EMPTY, ex);
		throw ex;
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: handleNotCatchedException <BR>
	 * Description: 记录未捕获异常:所有未捕获异常均以ERROR级别输出，并再次抛出（即不影响原来程序的动作） <BR>
	 * Remark: <BR>
	 * @param ex
	 * @return
	 * @throws Throwable  String<BR>
	 */
	@ExceptionHandler({ Throwable.class })
	public String handleNotCatchedException(Throwable ex) throws Throwable {
		LOG.error(EMPTY, ex);
		throw ex;
	}
}
