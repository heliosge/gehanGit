/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: SafetyEducationService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月16日        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.PoliciesRulesBean;
import com.jftt.wifi.bean.SafetyEducationBean;
import com.jftt.wifi.bean.vo.PoliciesRulesSearchVo;
import com.jftt.wifi.bean.vo.SafetyEducationSearchVo;

/**
 * class name:SafetyEducationService <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月16日
 * @author JFTT)chenrui
 */
public interface SafetyEducationService {
	SafetyEducationBean getById(String id) throws Exception;
	/**
	 * 新增安全宣教
	 * Method name: add <BR>
	 * Description: add <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @throws Exception  void<BR>
	 */
	void add(SafetyEducationBean paramVo) throws Exception;
	/**
	 * 修改
	 * Method name: uploadById <BR>
	 * Description: uploadById <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @throws Exception  void<BR>
	 */
	void updateById(SafetyEducationBean paramVo) throws Exception;
	/**
	 * 获取安全宣教列表
	 * Method name: getSafetyEducationList <BR>
	 * Description: getSafetyEducationList <BR>
	 * Remark: <BR>
	 * @param params
	 * @return
	 * @throws Exception  List<SafetyEducationBean><BR>
	 */
	List<SafetyEducationBean> getSafetyEducationList(SafetyEducationSearchVo params) throws Exception;
	int getSafetyEducationListCount(SafetyEducationSearchVo params) throws Exception;
	/**
	 * 删除
	 * Method name: deleteByIds <BR>
	 * Description: deleteByIds <BR>
	 * Remark: <BR>
	 * @param ids
	 * @throws Exception  void<BR>
	 */
	void deleteByIds(String[] ids) throws Exception;
	/**
	 * 学员端--获取安全宣教列表
	 * Method name: stu_getPoliciesRulesList <BR>
	 * Description: stu_getPoliciesRulesList <BR>
	 * Remark: <BR>
	 * @param params
	 * @return
	 * @throws Exception  List<PoliciesRulesBean><BR>
	 */
	List<SafetyEducationBean> stu_getSafetyEducationList(SafetyEducationSearchVo params) throws Exception;
	int stu_getSafetyEducationListCount(SafetyEducationSearchVo params) throws Exception;

}
