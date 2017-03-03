/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PoliciesRulesService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月13日        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.PoliciesRulesBean;
import com.jftt.wifi.bean.PoliciesRulesCategoryBean;
import com.jftt.wifi.bean.vo.PoliciesRulesSearchVo;


/**
 * class name:PoliciesRulesService <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月13日
 * @author JFTT)chenrui
 */
public interface PoliciesRulesService {
	/**
	 * 新增政策法规
	 * Method name: add <BR>
	 * Description: add <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @throws Exception  void<BR>
	 */
	public void add(PoliciesRulesBean paramVo) throws Exception;
	/**
	 * 修改
	 * Method name: updateById <BR>
	 * Description: updateById <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @throws Exception  void<BR>
	 */
	public void updateById(PoliciesRulesBean paramVo) throws Exception;
	 /**
	  * 获取政策法规列表
	  * Method name: getPoliciesRulesList <BR>
	  * Description: getPoliciesRulesList <BR>
	  * Remark: <BR>
	  * @param params
	  * @return  List<PoliciesRulesBean><BR>
	  */
	public List<PoliciesRulesBean> getPoliciesRulesList(PoliciesRulesSearchVo params) throws Exception;
	public int getPoliciesRulesListCount(PoliciesRulesSearchVo params) throws Exception;
	/**
	 * 发布
	 * Remark: <BR>
	 * @param id
	 */
	public void doPublish(String id) throws Exception;
	/**
	 * 废止
	 * Method name: doAbolish <BR>
	 * Description: doAbolish <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	public void doAbolish(String id) throws Exception;
	/**
	 * 删除
	 * Method name: deleteByIds <BR>
	 * Description: deleteByIds <BR>
	 * Remark: <BR>
	 * @param ids
	 * @throws Exception  void<BR>
	 */
	public void deleteByIds(String[] ids) throws Exception;
	public List<PoliciesRulesBean> stu_getPoliciesRulesList(PoliciesRulesSearchVo params) throws Exception;
	public int stu_getPoliciesRulesListCount(PoliciesRulesSearchVo params) throws Exception;
	public PoliciesRulesBean getById(String id) throws Exception;
	/**
	 *  获取当前政策法规的分类
	 * Method name: getCategorys <BR>
	 * Description: getCategorys <BR>
	 * Remark: <BR>
	 * @return  List<PoliciesRulesCategory><BR>
	 */
	public List<PoliciesRulesCategoryBean> getCategorys() throws Exception;
	public void addCategory(PoliciesRulesCategoryBean param) throws Exception;
	public void delCategory(String categoryId)  throws Exception;
	/**
	 * 获取分类信息根据id
	 * Method name: getCategoryById <BR>
	 * Description: getCategoryById <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  PoliciesRulesCategoryBean<BR>
	 */
	public PoliciesRulesCategoryBean getCategoryById(String categoryId) throws Exception;
	public void modifyCategory(PoliciesRulesCategoryBean param) throws Exception;

}
