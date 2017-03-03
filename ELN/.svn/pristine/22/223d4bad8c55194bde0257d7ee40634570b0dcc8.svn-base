/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamQuestionCategoryDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/23        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.exam.PaperCategoryBean;
import com.jftt.wifi.bean.exam.QuestionCategoryBean;
import com.jftt.wifi.bean.exam.vo.QuestionCategorySearchOptionVo;

/**
 * class name:ExamQuestionCategoryDaoMapper <BR>
 * class description: 试题分类信息Mapper <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/23
 * @author JFTT)wangyifeng
 */
public interface ExamQuestionCategoryDaoMapper {
	/**
	 * Method name: getQuestionCategoryList <BR>
	 * Description: 获取试题分类列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return List<QuestionCategoryBean><BR>
	 */
	List<QuestionCategoryBean> getQuestionCategoryList(
			QuestionCategorySearchOptionVo searchOption);

	/**
	 * Method name: addCategory <BR>
	 * Description: 增加分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param newCategory
	 * @return int<BR>
	 */
	int addCategory(QuestionCategoryBean newCategory);

	/**
	 * Method name: modifyCategory <BR>
	 * Description: 修改分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param modifiedCategory
	 * @return int<BR>
	 */
	int modifyCategory(QuestionCategoryBean modifiedCategory);

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
	 * @return QuestionCategoryBean<BR>
	 */
	QuestionCategoryBean getCategory(Integer categoryId);

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
	 * Description: 该分类下是否有试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @return boolean<BR>
	 */
	boolean hasQuestion(Integer categoryId);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getCategoryFamilyTree <BR>
	 * Description: 获取指定ID的所有子孙ID（包括自己） <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  String<BR>
	 */
	String getCategoryFamilyTree(@Param("categoryId") Integer categoryId,
			@Param("subCompanyId") Integer subCompanyId);
	
	//zhangchen start
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getParentCategory <BR>
	 * Description: 获取顶层试题分类 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<PaperCategoryBean><BR>
	 */
	List<QuestionCategoryBean> getParentCategory(QuestionCategorySearchOptionVo searchOption);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCategoryByParentId <BR>
	 * Description: 通过parentId获取一级子分类 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<PaperCategoryBean><BR>
	 */
	List<QuestionCategoryBean> getCategoryByParentId(QuestionCategorySearchOptionVo searchOption);
	//zhangchen end
}
