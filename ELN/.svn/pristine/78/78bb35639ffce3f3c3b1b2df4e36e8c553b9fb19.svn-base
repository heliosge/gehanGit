/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireCategoryDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-19        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.questionnaire.QuestionnaireCategoryBean;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;

/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireCategoryDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-19
 */
public interface QuestionnaireCategoryDaoMapper {
	/**
	 * Method name: getQuestionCategoryList <BR>
	 * Description: 获取问卷分类列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return List<QuestionCategoryBean><BR>
	 */
	List<QuestionnaireCategoryBean> getQuestionnaireCategoryList(SearchOptionBean searchOption);

	/**
	 * Method name: addCategory <BR>
	 * Description: 增加分类 <BR>
	 * Remark: <BR>
	 * @param newCategory
	 * @return int<BR>
	 */
	int addCategory(QuestionnaireCategoryBean newCategory);

	/**
	 * Method name: modifyCategory <BR>
	 * Description: 修改分类 <BR>
	 * Remark: <BR>
	 * @param modifiedCategory
	 * @return int<BR>
	 */
	int modifyCategory(QuestionnaireCategoryBean modifiedCategory);

	/**
	 * Method name: deleteCategory <BR>
	 * Description: 删除分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return int<BR>
	 */
	int deleteCategory(Integer categoryId);

	/**
	 * Method name: getCategory <BR>
	 * Description: 获取分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return QuestionCategoryBean<BR>
	 */
	QuestionnaireCategoryBean getCategory(Integer categoryId);

	// For validation
	/**
	 * Method name: hasSubCategory <BR>
	 * Description: 是否有子分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return boolean<BR>
	 */
	boolean hasSubCategory(Integer categoryId);

	/**
	 * Method name: hasQuestion <BR>
	 * Description: 该分类下是否有问卷 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return boolean<BR>
	 */
	boolean hasQuestionnaire(Integer categoryId);

	/**
	 * @author JFTT)zhangchen
	 * Method name: getCategoryFamilyTree <BR>
	 * Description: 获取指定ID的所有子孙ID（包括自己） <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  String<BR>
	 */
	String getCategoryFamilyTree(@Param("categoryId") Integer categoryId,@Param("subCompanyId") Integer subCompanyId);

}
