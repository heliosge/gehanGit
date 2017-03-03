/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: BaseException.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-20        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * class name:BaseException <BR>
 * class description: 基础异常类 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-20
 * @author JFTT)ShenHaijun
 */
public class BaseException extends Exception {

	/**  
	 * define a field serialVersionUID which type is long
	 */
	private static final long serialVersionUID = -3459455225253612032L;
	private String exceptionCode;
	private String exceptionReason;
	
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	public String getExceptionReason() {
		return exceptionReason;
	}
	public void setExceptionReason(String exceptionReason) {
		this.exceptionReason = exceptionReason;
	}
	
	@Override
	public void printStackTrace(PrintStream arg0) {
		System.out.println("[发生异常] 错误代码: " + exceptionCode + " 错误原因: " + exceptionReason);
		super.printStackTrace(arg0);
	}
	@Override
	public void printStackTrace(PrintWriter arg0) {
		System.out.println("[发送异常] 错误代码: " + exceptionCode + " 错误原因: " + exceptionReason);
		super.printStackTrace(arg0);
	}
}
