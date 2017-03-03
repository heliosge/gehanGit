/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamQuestionCategoryServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/23        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.QuestionCategoryBean;
import com.jftt.wifi.bean.exam.vo.QuestionCategorySearchOptionVo;
import com.jftt.wifi.bean.exam.vo.QuestionCategoryWithFullNameVo;
import com.jftt.wifi.bean.exam.vo.QuestionSearchOptionVo;
import com.jftt.wifi.dao.ExamQuestionCategoryDaoMapper;
import com.jftt.wifi.service.ExamQuestionCategoryService;
import com.jftt.wifi.service.ExamQuestionService;
import com.jftt.wifi.service.ManageUserService;

/**
 * class name:ExamQuestionCategoryServiceImpl <BR>
 * class description: 试题分类service <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/23
 * @author JFTT)wangyifeng
 */
@Service
public class ExamQuestionCategoryServiceImpl implements
		ExamQuestionCategoryService {
	@Autowired
	private ExamQuestionCategoryDaoMapper questionCategoryDaoMapper;
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private ExamQuestionService examQuestionService;

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.ExamQuestionCategoryService#getQuestionCategoryList(com.jftt.wifi.bean.exam.vo.QuestionCategorySearchOptionVo) <BR>
	 * Method name: getQuestionCategoryList <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/
	@Override
	@Transactional(readOnly = true)
	public List<QuestionCategoryBean> getQuestionCategoryList(
			QuestionCategorySearchOptionVo searchOption) {
		try {
			ManageUserBean user = manageUserService
					.findUserByIdCondition(searchOption.getUserId());
			
			searchOption.setSubCompanyId(user.getSubCompanyId());
			searchOption.setCompanyId(user.getCompanyId());
			
			List<QuestionCategoryBean> resultList = questionCategoryDaoMapper
					.getQuestionCategoryList(searchOption);
			QuestionCategoryBean rootNode = new QuestionCategoryBean();
			rootNode.setId(null);
			rootNode.setName(user.getSubCompanyName());
			resultList.add(rootNode);
			return resultList;
		} catch (Exception e) {
			throw new RuntimeException("Error in getQuestionCategoryList.", e);
		}
	}
	
	/**
	 * 根据公司ID获得 下面的试题库
	*/
	@Override
	@Transactional(readOnly = true)
	public List<QuestionCategoryBean> getQuestionCategoryListBySubCompany(QuestionCategorySearchOptionVo searchOption) {
		
		try {

			List<QuestionCategoryBean> resultList = questionCategoryDaoMapper.getQuestionCategoryList(searchOption);
			
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
	 * Description: 获取试题分类列表 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return  List<QuestionCategoryBean><BR>
	 */
	private List<QuestionCategoryBean> getQuestionCategoryList(Integer companyId) {
		QuestionCategorySearchOptionVo searchOption = new QuestionCategorySearchOptionVo();
		searchOption.setCompanyId(companyId);
		return questionCategoryDaoMapper.getQuestionCategoryList(searchOption);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionCategoryService#addCategory(com.jftt.wifi.bean.exam.QuestionCategoryBean) <BR>
	 *      Method name: addCategory <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param newCategory
	 * <BR>
	 */

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void addCategory(QuestionCategoryBean newCategory) {
		if (questionCategoryDaoMapper.addCategory(newCategory) != 1) {
			throw new RuntimeException("abc");
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionCategoryService#modifyCategory(com.jftt.wifi.bean.exam.QuestionCategoryBean) <BR>
	 *      Method name: modifyCategory <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param modifiedCategory
	 * <BR>
	 */

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void modifyCategory(QuestionCategoryBean modifiedCategory) {
		if (questionCategoryDaoMapper.modifyCategory(modifiedCategory) != 1) {
			throw new RuntimeException("abc");
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionCategoryService#deleteCategory(java.lang.Integer) <BR>
	 *      Method name: deleteCategory <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param categoryId
	 * <BR>
	 */

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteCategory(Integer categoryId) {
		int affectedRows = 0;
		if (!hasQuestion(categoryId) && !hasSubCategory(categoryId)) {
			affectedRows = questionCategoryDaoMapper.deleteCategory(categoryId);
		}
		if (affectedRows != 1) {
			throw new RuntimeException(
					"Can not delete category Or specified category is already deleted.");
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionCategoryService#getCategory(java.lang.Integer) <BR>
	 *      Method name: getCategory <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param categoryId
	 * @return <BR>
	 */

	@Override
	@Transactional(readOnly = true)
	public QuestionCategoryBean getCategory(Integer categoryId) {
		return questionCategoryDaoMapper.getCategory(categoryId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionCategoryService#hasSubCategory(java.lang.Integer) <BR>
	 *      Method name: hasSubCategory <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param categoryId
	 * @return <BR>
	 */

	@Override
	@Transactional(readOnly = true)
	public boolean hasSubCategory(Integer categoryId) {
		return questionCategoryDaoMapper.hasSubCategory(categoryId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionCategoryService#hasQuestion(java.lang.Integer) <BR>
	 *      Method name: hasQuestion <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param categoryId
	 * @return <BR>
	 */

	@Override
	@Transactional(readOnly = true)
	public boolean hasQuestion(Integer categoryId) {
		return questionCategoryDaoMapper.hasQuestion(categoryId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionCategoryService#getQuestionCategoryWithFullNameMap(java.lang.Integer) <BR>
	 *      Method name: getQuestionCategoryWithFullNameMap <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param companyId
	 * @return <BR>
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<Integer, QuestionCategoryWithFullNameVo> getQuestionCategoryWithFullNameMap(
			Integer companyId) {
		List<QuestionCategoryBean> categories = getQuestionCategoryList(companyId);
		Map<Integer, QuestionCategoryBean> oriMap = new HashMap<Integer, QuestionCategoryBean>();
		for (QuestionCategoryBean category : categories) {
			oriMap.put(category.getId(), category);
		}

		Map<Integer, QuestionCategoryWithFullNameVo> resultMap = new HashMap<Integer, QuestionCategoryWithFullNameVo>();
		for (QuestionCategoryBean category : categories) {
			resultMap.put(category.getId(),
					new QuestionCategoryWithFullNameVo(category.getId(),
							getFullName(category.getId(), oriMap, resultMap)));
		}

		return resultMap;
	}

	/**
	 * Method name: getFullName <BR>
	 * Description: 递归获取试题分类全名 <BR>
	 * Remark: <BR>
	 * 
	 * @param id
	 * @param oriMap
	 * @param resultMap
	 * @return String<BR>
	 */
	private String getFullName(Integer id,
			Map<Integer, QuestionCategoryBean> oriMap,
			Map<Integer, QuestionCategoryWithFullNameVo> resultMap) {
		if (resultMap.containsKey(id)) {
			return resultMap.get(id).getFullName();
		}
		StringBuilder sb = new StringBuilder();
		if (oriMap.containsKey(id)) {
			if (oriMap.get(id).getParentId() != null) {
				sb.append(getFullName(oriMap.get(id).getParentId(), oriMap,
						resultMap));
				sb.append("/");
			}
			sb.append(oriMap.get(id).getName());
		}
		return sb.toString();
	}
	
	//zhangchen start
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseQuestionCategory <BR>
	 * Description: 获取我的练习中的分类 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<QuestionCategoryBean><BR>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<QuestionCategoryBean> getExerciseQuestionCategory(QuestionCategorySearchOptionVo searchOption) {
		try {
			//ManageUserBean user = manageUserService.findUserByIdCondition(searchOption.getUserId());
			//searchOption.setSubCompanyId(user.getSubCompanyId());
			List<QuestionCategoryBean> resultList = questionCategoryDaoMapper.getParentCategory(searchOption);
			/*QuestionSearchOptionVo optionVo = new QuestionSearchOptionVo();
			for(QuestionCategoryBean category:resultList){
				optionVo.setCategoryId(category.getId());
				category.setCount(examQuestionService.getExerciseQuestionCount(optionVo));
			}*/
			List<QuestionCategoryBean> subList = new ArrayList<QuestionCategoryBean>();
			Integer id = null;
			for(int i=0;i<resultList.size();i++){
				id = resultList.get(i).getId();
				if(id != null){
					searchOption.setId(id);
					subList = questionCategoryDaoMapper.getCategoryByParentId(searchOption);
					resultList.get(i).setSubCategoryList(subList);
				}
			}
			/*QuestionCategoryBean rootNode = new QuestionCategoryBean();
			rootNode.setId(null);
			rootNode.setName(user.getSubCompanyName());
			resultList.add(rootNode);*/
			return resultList;
		} catch (Exception e) {
			throw new RuntimeException("Error in getExerciseQuestionCategory.", e);
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getQuestionCategoryById <BR>
	 * Description: 根据ID获取试题分类信息 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  QuestionCategoryBean<BR>
	 */
	@Override
	@Transactional(readOnly = true)
	public QuestionCategoryBean getQuestionCategoryById(QuestionCategorySearchOptionVo searchOption){
		QuestionCategoryBean bean = questionCategoryDaoMapper.getCategory(searchOption.getId());
		if(bean != null){
			List<QuestionCategoryBean> subList = new ArrayList<QuestionCategoryBean>();
			Integer id = bean.getId();
			searchOption.setId(id);
			subList = questionCategoryDaoMapper.getCategoryByParentId(searchOption);
			bean.setSubCategoryList(subList);
		}
		return bean;
	}
	//zhangchen end 

}
