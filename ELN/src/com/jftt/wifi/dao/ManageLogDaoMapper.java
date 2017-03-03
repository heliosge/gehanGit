/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagDaoMapper.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2013/01/07           |  JFTT)wangjian    |  original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import com.jftt.wifi.bean.ManageLogBean;

/**
 * 操作日志
 */
public interface ManageLogDaoMapper {
	
	/**
	 * 增加操作日志
	 */
	public void addManageLog(ManageLogBean manageLogBean);
	
	/**
	 * 根据条件获得操作日志的数量
	 */
	public long getManageLogCount(ManageLogBean manageLogBean);
	
	/**
	 * 根据条件获得操作日志
	 */
	public List<ManageLogBean> getManageLog(ManageLogBean manageLogBean);
}
