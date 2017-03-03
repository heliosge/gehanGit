/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: SafetyEducationServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月16日        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.SafetyEducationBean;
import com.jftt.wifi.bean.vo.PoliciesRulesSearchVo;
import com.jftt.wifi.bean.vo.SafetyEducationSearchVo;
import com.jftt.wifi.dao.SafetyEducationDaoMapper;
import com.jftt.wifi.service.SafetyEducationService;

/**
 * class name:SafetyEducationServiceImpl <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月16日
 * @author JFTT)chenrui
 */
@Service
public class SafetyEducationServiceImpl implements SafetyEducationService {
	@Autowired
	private SafetyEducationDaoMapper safetyEducationDaoMapper;
	
	
	@Override
	public SafetyEducationBean getById(String id) throws Exception {
		return safetyEducationDaoMapper.selectByPrimaryKey(Integer.parseInt(id));
	}
	@Override
	public void add(SafetyEducationBean paramVo) throws Exception {
		safetyEducationDaoMapper.add(paramVo);
	}
	@Override
	public void updateById(SafetyEducationBean paramVo) throws Exception {
		safetyEducationDaoMapper.updateById(paramVo);
	}
	@Override
	public List<SafetyEducationBean> getSafetyEducationList(SafetyEducationSearchVo params) throws Exception {
		return safetyEducationDaoMapper.getSafetyEducationList(params);
	}
	@Override
	public int getSafetyEducationListCount(SafetyEducationSearchVo params) throws Exception {
		return safetyEducationDaoMapper.getSafetyEducationListCount(params);
	}
	/**
	 * 删除
	 * @Override
	 * @see com.jftt.wifi.service.SafetyEducationService#deleteByIds(java.lang.String[]) <BR>
	 * Method name: deleteByIds <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param ids
	 * @throws Exception  <BR>
	 */
	@Override
	public void deleteByIds(String[] ids) throws Exception {
		safetyEducationDaoMapper.deleteByIds(ids);
	}
	
	@Override
	public List<SafetyEducationBean> stu_getSafetyEducationList(SafetyEducationSearchVo params) throws Exception {
		return safetyEducationDaoMapper.stu_getSafetyEducationList(params);
	}
	@Override
	public int stu_getSafetyEducationListCount(SafetyEducationSearchVo params) throws Exception {
		return safetyEducationDaoMapper.stu_getSafetyEducationListCount(params);
	}
	
}
