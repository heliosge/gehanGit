/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: AjaxReturnVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-20        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.List;

import com.jftt.wifi.common.Constant;

/**
 * class name:AjaxReturnVo <BR>
 * class description: Ajax返回封装类 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-20
 * @author JFTT)ShenHaijun
 */
public class AjaxReturnVo<T> {
	private String rtnResult = Constant.AJAX_SUCCESS;
	private int count;
	private T rtnData;
	private List<T> rtnDataList;
	
	public String getRtnResult() {
		return rtnResult;
	}
	public void setRtnResult(String rtnResult) {
		this.rtnResult = rtnResult;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public T getRtnData() {
		return rtnData;
	}
	public void setRtnData(T rtnData) {
		this.rtnData = rtnData;
	}
	public List<T> getRtnDataList() {
		return rtnDataList;
	}
	public void setRtnDataList(List<T> rtnDataList) {
		this.rtnDataList = rtnDataList;
	}
}
