/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagDaoMapper.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2013/01/07           |  JFTT)wangjian    |  original version
 */
package com.jftt.wifi.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManageLogBean;

/**
 * 操作日志
 */
public interface ManageLogService {
	
	/**
	 * 增加操作日志
	 */
	public void addManageLog(ManageLogBean manageLogBean);
	
	/**
	 * 增加操作日志
	 * 
	 * operateId: 操作人员ID
	 * nowTime：操作时间  YYYY-MM-DD HH:MM:ss
	 * dbTableName: 操作的表名称
	 * dbTableId: 操作的数据主键值
	 * descr: 操作描述
	 */
	public void addManageLog(long operateId, String nowTime, String dbTableName, String dbTableId, String descr);
	
	/**
	 * 根据条件获得操作日志的数量
	 */
	public long getManageLogCount(ManageLogBean manageLogBean);
	
	/**
	 * 根据条件获得操作日志
	 */
	public List<ManageLogBean> getManageLog(ManageLogBean manageLogBean);
}
