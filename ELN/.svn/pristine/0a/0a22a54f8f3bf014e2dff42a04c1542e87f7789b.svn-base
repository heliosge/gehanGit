/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamPaperCategoryServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/28        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.PaperCategoryBean;
import com.jftt.wifi.bean.exam.vo.PaperCategorySearchOptionVo;
import com.jftt.wifi.bean.exam.vo.PaperCategoryWithFullNameVo;
import com.jftt.wifi.dao.ExamPaperCategoryDaoMapper;
import com.jftt.wifi.service.ExamPaperCategoryService;
import com.jftt.wifi.service.ManageUserService;

/**
 * class name:ExamPaperCategoryServiceImpl <BR>
 * class description: 试卷分类Service <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/28
 * @author JFTT)wangyifeng
 */
@Service
public class ExamPaperCategoryServiceImpl implements ExamPaperCategoryService {
	@Autowired
	private ExamPaperCategoryDaoMapper paperCategoryDaoMapper;
	
	@Autowired
	private ManageUserService manageUserService;

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamPaperCategoryService#getPaperCategoryList(java.lang.Integer) <BR>
	 *      Method name: getPaperCategoryList <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param searchOption
	 * @return <BR>
	 */

	@Override
	@Transactional
	public List<PaperCategoryBean> getPaperCategoryList(PaperCategorySearchOptionVo searchOption) {
		
		List<PaperCategoryBean> resultList = null;
		
		try {
			ManageUserBean user = manageUserService.findUserByIdCondition(searchOption.getUserId());
			
			searchOption.setSubCompanyId(user.getSubCompanyId());
			resultList = paperCategoryDaoMapper.getPaperCategoryList(searchOption);
			PaperCategoryBean rootNode = new PaperCategoryBean();
			rootNode.setId(null);
			rootNode.setName(user.getSubCompanyName());
			resultList.add(rootNode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultList;
	}

	/**
	 * Method name: getPaperCategoryList <BR>
	 * Description: 获取试卷分类列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param companyId
	 * @return List<PaperCategoryBean><BR>
	 */
	@Transactional
	private List<PaperCategoryBean> getPaperCategoryList(Integer companyId) {
		PaperCategorySearchOptionVo searchOption = new PaperCategorySearchOptionVo();
		searchOption.setCompanyId(companyId);
		return paperCategoryDaoMapper.getPaperCategoryList(searchOption);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamPaperCategoryService#addCategory(com.jftt.wifi.bean.exam.PaperCategoryBean) <BR>
	 *      Method name: addCategory <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param newCategory
	 * <BR>
	 */

	@Override
	@Transactional
	public void addCategory(PaperCategoryBean newCategory) {
		if (paperCategoryDaoMapper.addCategory(newCategory) != 1) {
			throw new RuntimeException("abc");
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamPaperCategoryService#modifyCategory(com.jftt.wifi.bean.exam.PaperCategoryBean) <BR>
	 *      Method name: modifyCategory <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param modifiedCategory
	 * <BR>
	 */

	@Override
	@Transactional
	public void modifyCategory(PaperCategoryBean modifiedCategory) {
		if (paperCategoryDaoMapper.modifyCategory(modifiedCategory) != 1) {
			throw new RuntimeException("abc");
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamPaperCategoryService#deleteCategory(java.lang.Integer) <BR>
	 *      Method name: deleteCategory <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param categoryId
	 * <BR>
	 */

	@Override
	@Transactional
	public void deleteCategory(Integer categoryId) {
		int affectedRows = 0;
		if (!hasPaper(categoryId) && !hasSubCategory(categoryId)) {
			affectedRows = paperCategoryDaoMapper.deleteCategory(categoryId);
		}
		if (affectedRows != 1) {
			throw new RuntimeException(
					"Can not delete category Or specified category is already deleted.");
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamPaperCategoryService#getCategory(java.lang.Integer) <BR>
	 *      Method name: getCategory <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param categoryId
	 * @return <BR>
	 */

	@Override
	@Transactional
	public PaperCategoryBean getCategory(Integer categoryId) {
		return paperCategoryDaoMapper.getCategory(categoryId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamPaperCategoryService#getPaperCategoryWithFullNameMap(java.lang.Integer) <BR>
	 *      Method name: getPaperCategoryWithFullNameMap <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param companyId
	 * @return <BR>
	 */

	@Override
	@Transactional
	public Map<Integer, PaperCategoryWithFullNameVo> getPaperCategoryWithFullNameMap(
			Integer companyId) {
		List<PaperCategoryBean> categories = getPaperCategoryList(companyId);
		Map<Integer, PaperCategoryBean> oriMap = new HashMap<Integer, PaperCategoryBean>();
		for (PaperCategoryBean category : categories) {
			oriMap.put(category.getId(), category);
		}

		Map<Integer, PaperCategoryWithFullNameVo> resultMap = new HashMap<Integer, PaperCategoryWithFullNameVo>();
		for (PaperCategoryBean category : categories) {
			resultMap.put(category.getId(),
					new PaperCategoryWithFullNameVo(category.getId(),
							getFullName(category.getId(), oriMap, resultMap)));
		}

		return resultMap;
	}

	/**
	 * Method name: getFullName <BR>
	 * Description: 递归获取试卷分类全名 <BR>
	 * Remark: <BR>
	 * 
	 * @param id
	 * @param oriMap
	 * @param resultMap
	 * @return String<BR>
	 */
	@Transactional
	private String getFullName(Integer id,
			Map<Integer, PaperCategoryBean> oriMap,
			Map<Integer, PaperCategoryWithFullNameVo> resultMap) {
		if (resultMap.containsKey(id)) {
			return resultMap.get(id).getFullName();
		}
		StringBuilder sb = new StringBuilder();
		if (oriMap.get(id).getParentId() != null) {
			sb.append(getFullName(oriMap.get(id).getParentId(), oriMap,
					resultMap));
			sb.append("/");
		}
		sb.append(oriMap.get(id).getName());
		return sb.toString();
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamPaperCategoryService#hasSubCategory(java.lang.Integer) <BR>
	 *      Method name: hasSubCategory <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param categoryId
	 * @return <BR>
	 */

	@Override
	@Transactional
	public boolean hasSubCategory(Integer categoryId) {
		return paperCategoryDaoMapper.hasSubCategory(categoryId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamPaperCategoryService#hasPaper(java.lang.Integer) <BR>
	 *      Method name: hasPaper <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param categoryId
	 * @return <BR>
	 */

	@Override
	@Transactional
	public boolean hasPaper(Integer categoryId) {
		return paperCategoryDaoMapper.hasPaper(categoryId);
	}
	
}
