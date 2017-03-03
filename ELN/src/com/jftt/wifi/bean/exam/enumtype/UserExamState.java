/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: UserExamState.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/07        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam.enumtype;

/**
 * class name:UserExamStateContant <BR>
 * class description: 学员考试状态 <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/07
 * @author JFTT)wangyifeng
 */
public class UserExamState {
	/**
	 * 未开始
	 */
	public static final Integer NOT_START = 1;
	/**
	 * 未提交
	 */
	public static final Integer NOT_COMMIT = 1;
	/**
	 * 已提交
	 */
	public static final Integer COMMITTED = 1;
	/**
	 * 已批阅
	 */
	public static final Integer IS_MARKED = 1;
}
