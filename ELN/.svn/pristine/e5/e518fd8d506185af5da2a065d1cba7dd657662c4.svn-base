/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireCategoryServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-19        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.vo.QuestionCategoryWithFullNameVo;
import com.jftt.wifi.bean.questionnaire.QuestionnaireCategoryBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireCategoryWithFullNameVo;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;
import com.jftt.wifi.dao.QuestionnaireCategoryDaoMapper;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.QuestionnaireCategoryService;

/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireCategoryServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-19
 */
@Service
public class QuestionnaireCategoryServiceImpl implements QuestionnaireCategoryService{
	@Autowired
	private QuestionnaireCategoryDaoMapper questionnaireCategoryDaoMapper;
	@Autowired
	private ManageUserService manageUserService;
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireCategoryService#getQuestionnaireCategoryList(com.jftt.wifi.bean.questionnaire.SearchOptionBean) <BR>
	 * Method name: getQuestionnaireCategoryList <BR>
	 * Description: 获取问卷分类列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/
	
	@Override
	@Transactional
	public List<QuestionnaireCategoryBean> getQuestionnaireCategoryList(SearchOptionBean searchOption) {
		// TODO Auto-generated method stub
		try {
			ManageUserBean user = manageUserService.findUserByIdCondition(searchOption.getUserId());
			searchOption.setSubCompanyId(user.getSubCompanyId());
			List<QuestionnaireCategoryBean> resultList = questionnaireCategoryDaoMapper.getQuestionnaireCategoryList(searchOption);
			QuestionnaireCategoryBean rootNode = new QuestionnaireCategoryBean();
			rootNode.setId(null);
			rootNode.setName(user.getSubCompanyName());
			resultList.add(rootNode);
			return resultList;
		} catch (Exception e) {
			throw new RuntimeException("Error in getQuestionnaireCategoryList.", e);
		}
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireCategoryService#getQuestionnaireCategoryListBySubCompany(com.jftt.wifi.bean.questionnaire.SearchOptionBean) <BR>
	 * Method name: getQuestionnaireCategoryListBySubCompany <BR>
	 * Description: 根据公司ID获得 下面的问卷库 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/
	
	@Override
	@Transactional
	public List<QuestionnaireCategoryBean> getQuestionnaireCategoryListBySubCompany(
			SearchOptionBean searchOption) {
		// TODO Auto-generated method stub
		try {
			List<QuestionnaireCategoryBean> resultList = questionnaireCategoryDaoMapper.getQuestionnaireCategoryList(searchOption);
			/*QuestionCategoryBean rootNode = new QuestionCategoryBean();
			rootNode.setId(null);
			rootNode.setName(user.getSubCompanyName());
			resultList.add(rootNode);*/
			return resultList;
		} catch (Exception e) {
			throw new RuntimeException("Error in getQuestionCategoryList.", e);
		}
	}
	
	/**
	 * Method name: getQuestionCategoryList <BR>
	 * Description: 获取问卷分类列表 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return  List<QuestionnaireCategoryBean><BR>
	 */
	private List<QuestionnaireCategoryBean> getQuestionnaireCategoryList(Integer companyId) {
		SearchOptionBean searchOption = new SearchOptionBean();
		searchOption.setCompanyId(companyId);
		return questionnaireCategoryDaoMapper.getQuestionnaireCategoryList(searchOption);
	}
	
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireCategoryService#addCategory(com.jftt.wifi.bean.questionnaire.QuestionnaireCategoryBean) <BR>
	 * Method name: addCategory <BR>
	 * Description: 添加问卷分类 <BR>
	 * Remark: <BR>
	 * @param newCategory  <BR>
	*/
	
	@Override
	public void addCategory(QuestionnaireCategoryBean newCategory) {
		// TODO Auto-generated method stub
		if (questionnaireCategoryDaoMapper.addCategory(newCategory) != 1) {
			throw new RuntimeException("abc");
		}
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireCategoryService#modifyCategory(com.jftt.wifi.bean.questionnaire.QuestionnaireCategoryBean) <BR>
	 * Method name: modifyCategory <BR>
	 * Description: 更新问卷分类 <BR>
	 * Remark: <BR>
	 * @param modifiedCategory  <BR>
	*/
	
	@Override
	public void modifyCategory(QuestionnaireCategoryBean modifiedCategory) {
		// TODO Auto-generated method stub
		if (questionnaireCategoryDaoMapper.modifyCategory(modifiedCategory) != 1) {
			throw new RuntimeException("abc");
		}
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireCategoryService#deleteCategory(java.lang.Integer) <BR>
	 * Method name: deleteCategory <BR>
	 * Description: 删除问卷分类 <BR>
	 * Remark: <BR>
	 * @param categoryId  <BR>
	*/
	
	@Override
	@Transactional
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		int affectedRows = 0;
		if (!hasQuestionnaire(categoryId) && !hasSubCategory(categoryId)) {
			affectedRows = questionnaireCategoryDaoMapper.deleteCategory(categoryId);
		}
		if (affectedRows != 1) {
			throw new RuntimeException(
					"Can not delete category Or specified category is already deleted.");
		}
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireCategoryService#getCategory(java.lang.Integer) <BR>
	 * Method name: getCategory <BR>
	 * Description: 根据ID分类获取分类信息 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  <BR>
	*/
	
	@Override
	public QuestionnaireCategoryBean getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return questionnaireCategoryDaoMapper.getCategory(categoryId);
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireCategoryService#getQuestionCategoryWithFullNameMap(java.lang.Integer) <BR>
	 * Method name: getQuestionCategoryWithFullNameMap <BR>
	 * Description: 判断 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return  <BR>
	*/
	
	@Override
	public Map<Integer, QuestionnaireCategoryWithFullNameVo> getQuestionnaireCategoryWithFullNameMap(Integer companyId) {
		// TODO Auto-generated method stub
		List<QuestionnaireCategoryBean> categories = getQuestionnaireCategoryList(companyId);
		Map<Integer, QuestionnaireCategoryBean> oriMap = new HashMap<Integer, QuestionnaireCategoryBean>();
		for (QuestionnaireCategoryBean category : categories) {
			oriMap.put(category.getId(), category);
		}
		Map<Integer, QuestionnaireCategoryWithFullNameVo> resultMap = new HashMap<Integer, QuestionnaireCategoryWithFullNameVo>();
		for (QuestionnaireCategoryBean category : categories) {
			resultMap.put(category.getId(),
					new QuestionnaireCategoryWithFullNameVo(category.getId(),getFullName(category.getId(), oriMap, resultMap)));
		}
		return resultMap;
	}
	
	/**
	 * Method name: getFullName <BR>
	 * Description: 递归获取试题分类全名 <BR>
	 * Remark: <BR>
	 * @param id
	 * @param oriMap
	 * @param resultMap
	 * @return String<BR>
	 */
	private String getFullName(Integer id,
			Map<Integer, QuestionnaireCategoryBean> oriMap,
			Map<Integer, QuestionnaireCategoryWithFullNameVo> resultMap) {
		if (resultMap.containsKey(id)) {
			return resultMap.get(id).getFullName();
		}
		StringBuilder sb = new StringBuilder();
		if (oriMap.containsKey(id)) {
			if (oriMap.get(id).getParentId() != null) {
				sb.append(getFullName(oriMap.get(id).getParentId(), oriMap,resultMap));
				sb.append("/");
			}
			sb.append(oriMap.get(id).getName());
		}
		return sb.toString();
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireCategoryService#hasSubCategory(java.lang.Integer) <BR>
	 * Method name: hasSubCategory <BR>
	 * Description: 判断一个分类下是否有子分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  <BR>
	*/
	
	@Override
	public boolean hasSubCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return questionnaireCategoryDaoMapper.hasSubCategory(categoryId);
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireCategoryService#hasQuestionnaire(java.lang.Integer) <BR>
	 * Method name: hasQuestionnaire <BR>
	 * Description: 判断一个分类下是否有问卷 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  <BR>
	*/
	
	@Override
	public boolean hasQuestionnaire(Integer categoryId) {
		// TODO Auto-generated method stub
		return questionnaireCategoryDaoMapper.hasQuestionnaire(categoryId);
	}
	
	

}
