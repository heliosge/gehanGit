/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagDaoMapper.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2013/01/07           |  JFTT)wangjian    |  original version
 */
package com.jftt.wifi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManageLogBean;
import com.jftt.wifi.dao.ManageLogDaoMapper;
import com.jftt.wifi.service.ManageLogService;

/**
 * 操作日志
 */
@Service("manageLogService")
public class ManageLogServiceImpl implements ManageLogService{
	
	@Resource(name="manageLogDaoMapper")
	private ManageLogDaoMapper manageLogDaoMapper;
	
	/**
	 * 增加操作日志
	 */
	@Transactional
	public void addManageLog(ManageLogBean manageLogBean){
		
		manageLogDaoMapper.addManageLog(manageLogBean);
	}
	
	/**
	 * 增加操作日志
	 * 
	 * operateId: 操作人员ID
	 * nowTime：操作时间  YYYY-MM-DD HH:MM:ss
	 * dbTableName: 操作的表名称
	 * dbTableId: 操作的数据主键值
	 * descr: 操作描述
	 */
	@Transactional
	public void addManageLog(long operateId, String nowTime, String dbTableName, String dbTableId, String descr){
		
		//插入操作记录
		ManageLogBean logBean = new ManageLogBean();
		
		logBean.setOperateId(operateId);
		logBean.setOperateTime(nowTime);
		logBean.setContent(descr);
		logBean.setOperateTable(dbTableName);
		logBean.setOperateDataId(dbTableId);
		
		manageLogDaoMapper.addManageLog(logBean);
	}
	
	/**
	 * 根据条件获得操作日志的数量
	 */
	public long getManageLogCount(ManageLogBean manageLogBean){
		
		return manageLogDaoMapper.getManageLogCount(manageLogBean);
	}
	
	/**
	 * 根据条件获得操作日志
	 */
	public List<ManageLogBean> getManageLog(ManageLogBean manageLogBean){
		
		return manageLogDaoMapper.getManageLog(manageLogBean);
	}
}
