/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PoliciesRulesServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月13日        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.PoliciesRulesBean;
import com.jftt.wifi.bean.PoliciesRulesCategoryBean;
import com.jftt.wifi.bean.vo.PoliciesRulesSearchVo;
import com.jftt.wifi.dao.PoliciesRulesDaoCategoryMapper;
import com.jftt.wifi.dao.PoliciesRulesDaoMapper;
import com.jftt.wifi.service.PoliciesRulesService;

/**
 * class name:PoliciesRulesServiceImpl <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月13日
 * @author JFTT)chenrui
 */
@Service
public class PoliciesRulesServiceImpl implements PoliciesRulesService{
	@Autowired
	private PoliciesRulesDaoMapper policiesRulesDaoMapper;
	@Autowired
	private PoliciesRulesDaoCategoryMapper policiesRulesDaoCategoryMapper;
	
	@Override
	public PoliciesRulesBean getById(String id) throws Exception {
		return policiesRulesDaoMapper.selectByPrimaryKey(Integer.parseInt(id));
	}
	/**
	 * 新增政策法规
	 * @Override
	 * @see com.jftt.wifi.service.PoliciesRulesService#add(com.jftt.wifi.bean.PoliciesRulesBean) <BR>
	 * Method name: add <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @throws Exception  <BR>
	 */
	@Override
	public void add(PoliciesRulesBean paramVo) throws Exception {
		policiesRulesDaoMapper.add(paramVo);
	}
	
	@Override
	public void updateById(PoliciesRulesBean paramVo) throws Exception {
		policiesRulesDaoMapper.updateById(paramVo);
	}
	/**
	 * 获取政策法规列表
	 * @Override
	 * @see com.jftt.wifi.service.PoliciesRulesService#getPoliciesRulesList(com.jftt.wifi.bean.vo.PoliciesRulesSearchVo) <BR>
	 * Method name: getPoliciesRulesList <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param params
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<PoliciesRulesBean> getPoliciesRulesList(PoliciesRulesSearchVo params) throws Exception {
		return policiesRulesDaoMapper.getPoliciesRulesList(params);
	}
	@Override
	public int getPoliciesRulesListCount(PoliciesRulesSearchVo params) throws Exception {
		return policiesRulesDaoMapper.getPoliciesRulesListCount(params);
	}
	/**
	 * 发布
	 * @Override
	 * @see com.jftt.wifi.service.PoliciesRulesService#doPublishOrAbolish(java.lang.String, java.lang.String) <BR>
	 * Method name: doPublish <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	 */
	@Override
	public void doPublish(String id) throws Exception {
		policiesRulesDaoMapper.doPublish(id);
	}
	/**
	 * 废止
	 * @Override
	 * @see com.jftt.wifi.service.PoliciesRulesService#doAbolish(java.lang.String) <BR>
	 * Method name: doAbolish <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	 */
	@Override
	public void doAbolish(String id) throws Exception {
		policiesRulesDaoMapper.doAbolish(id);
	}
	@Override
	public void deleteByIds(String[] ids) throws Exception {
			policiesRulesDaoMapper.deleteByIds(ids);
	}
	/**
	 *  获取当前政策法规的分类
	 * @Override
	 * @see com.jftt.wifi.service.PoliciesRulesService#getCategorys() <BR>
	 * Method name: getCategorys <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @return  <BR>
	 */
	@Override
	public List<PoliciesRulesCategoryBean> getCategorys() throws Exception{
		List<PoliciesRulesCategoryBean> list = policiesRulesDaoCategoryMapper.getCategorys();
		PoliciesRulesCategoryBean root = new PoliciesRulesCategoryBean();
		root.setId(1);
		root.setParentId(-2);
		root.setName("政策法规分类");
		root.setIsDelete(0);
		list.add(root);
		return list;
	}
	@Override
	public List<PoliciesRulesBean> stu_getPoliciesRulesList(PoliciesRulesSearchVo params) throws Exception {
		return policiesRulesDaoMapper.stu_getPoliciesRulesList(params);
	}
	@Override
	public void addCategory(PoliciesRulesCategoryBean param) throws Exception {
		policiesRulesDaoCategoryMapper.addCategory(param);
	}
	@Override
	public int stu_getPoliciesRulesListCount(PoliciesRulesSearchVo params) throws Exception {
		return policiesRulesDaoMapper.stu_getPoliciesRulesListCount(params);
	}
	@Override
	public void delCategory(String categoryId) throws Exception {
		policiesRulesDaoCategoryMapper.delCategory(categoryId);
	}
	@Override
	public PoliciesRulesCategoryBean getCategoryById(String categoryId) throws Exception {
		return policiesRulesDaoCategoryMapper.getCategoryById(categoryId);
	}
	@Override
	public void modifyCategory(PoliciesRulesCategoryBean param) throws Exception {
		policiesRulesDaoCategoryMapper.modifyCategory(param);
	}
}

