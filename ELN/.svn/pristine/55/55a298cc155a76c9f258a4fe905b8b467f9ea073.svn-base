/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamPaperCategoryDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/28        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.exam.PaperCategoryBean;
import com.jftt.wifi.bean.exam.vo.PaperCategorySearchOptionVo;

/**
 * class name:ExamPaperCategoryDaoMapper <BR>
 * class description: 试卷分类信息Mapper <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/28
 * @author JFTT)wangyifeng
 */
public interface ExamPaperCategoryDaoMapper {
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
	int addCategory(PaperCategoryBean newCategory);

	/**
	 * Method name: modifyCategory <BR>
	 * Description: 修改分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param modifiedCategory
	 * @return int<BR>
	 */
	int modifyCategory(PaperCategoryBean modifiedCategory);

	/**
	 * Method name: deleteCategory <BR>
	 * Description: 删除分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @return int<BR>
	 */
	int deleteCategory(Integer categoryId);

	/**
	 * Method name: getCategory <BR>
	 * Description: 获取分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @return PaperCategoryBean<BR>
	 */
	PaperCategoryBean getCategory(Integer categoryId);

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

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getCategoryFamilyTree <BR>
	 * Description: 根据分类ID获取所有子孙ID，包括自身ID <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @param subCompanyId
	 * @return  String<BR>
	 */
	String getCategoryFamilyTree(@Param("categoryId") Integer categoryId,
			@Param("subCompanyId") Integer subCompanyId);
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getCategoryByParentId <BR>
	 * Description: 获取当前id下的子类别 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @throws Exception  void<BR>
	 */
	public List<PaperCategoryBean> getCategoryByParentId(@Param("categoryId")int categoryId) throws Exception;
}
