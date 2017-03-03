/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamPaperCategoryService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/22        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.PaperCategoryBean;
import com.jftt.wifi.bean.exam.vo.PaperCategorySearchOptionVo;
import com.jftt.wifi.bean.exam.vo.PaperCategoryWithFullNameVo;

/**
 * class name:ExamPaperCategoryService <BR>
 * class description: 试卷分类Service <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/22
 * @author JFTT)wangyifeng
 */
public interface ExamPaperCategoryService {
	/**
	 * Method name: getPaperCategoryList <BR>
	 * Description: 获取试卷库分类列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return List<PaperCategoryBean><BR>
	 */
	List<PaperCategoryBean> getPaperCategoryList(
			PaperCategorySearchOptionVo searchOption);

	/**
	 * Method name: addCategory <BR>
	 * Description: 增加分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param newCategory
	 * @return int<BR>
	 */
	void addCategory(PaperCategoryBean newCategory);

	/**
	 * Method name: modifyCategory <BR>
	 * Description: 修改分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param modifiedCategory
	 * @return int<BR>
	 */
	void modifyCategory(PaperCategoryBean modifiedCategory);

	/**
	 * Method name: deleteCategory <BR>
	 * Description: 删除分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @return int<BR>
	 */
	void deleteCategory(Integer categoryId);

	/**
	 * Method name: getCategory <BR>
	 * Description: 获取分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @return PaperCategoryBean<BR>
	 */
	PaperCategoryBean getCategory(Integer categoryId);

	// Support methods
	/**
	 * Method name: getPaperCategoryWithFullNameMap <BR>
	 * Description: 获取分类Map，其中分类名称为全名 <BR>
	 * Remark: <BR>
	 * 
	 * @param companyId
	 * @return Map<Integer,PaperCategoryWithFullNameVo><BR>
	 */
	Map<Integer, PaperCategoryWithFullNameVo> getPaperCategoryWithFullNameMap(
			Integer companyId);

	// For validation
	/**
	 * Method name: hasSubCategory <BR>
	 * Description: 是否有子分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @return boolean<BR>
	 */
	boolean hasSubCategory(Integer categoryId);

	/**
	 * Method name: hasQuestion <BR>
	 * Description: 该分类下是否有试卷 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @return boolean<BR>
	 */
	boolean hasPaper(Integer categoryId);
}
