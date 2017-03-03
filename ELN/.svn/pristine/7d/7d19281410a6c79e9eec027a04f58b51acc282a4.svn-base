/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireCategoryService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-19        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import com.jftt.wifi.bean.exam.vo.QuestionCategoryWithFullNameVo;
import com.jftt.wifi.bean.questionnaire.QuestionnaireCategoryBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireCategoryWithFullNameVo;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;

/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireCategoryService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-19
 */
public interface QuestionnaireCategoryService {
	/**
	 * Method name: getQuestionCategoryList <BR>
	 * Description: 获取问卷分类列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<QuestionCategoryBean><BR>
	 */
	List<QuestionnaireCategoryBean> getQuestionnaireCategoryList(SearchOptionBean searchOption);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getQuestionCategoryListBySubCompany <BR>
	 * Description: 根据公司ID获得 下面的试题库 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<QuestionCategoryBean><BR>
	 */
	public List<QuestionnaireCategoryBean> getQuestionnaireCategoryListBySubCompany(SearchOptionBean searchOption);

	/**
	 * Method name: addCategory <BR>
	 * Description: 增加分类 <BR>
	 * Remark: <BR>
	 * @param newCategory
	 * @void<BR>
	 */
	void addCategory(QuestionnaireCategoryBean newCategory);

	/**
	 * Method name: modifyCategory <BR>
	 * Description: 修改分类 <BR>
	 * Remark: <BR>
	 * @param modifiedCategory
	 *            void<BR>
	 */
	void modifyCategory(QuestionnaireCategoryBean modifiedCategory);

	/**
	 * Method name: deleteCategory <BR>
	 * Description: 删除分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * void<BR>
	 */
	void deleteCategory(Integer categoryId);

	/**
	 * Method name: getCategory <BR>
	 * Description: 获取分类信息 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return QuestionCategoryBean<BR>
	 */
	QuestionnaireCategoryBean getCategory(Integer categoryId);

	// Support methods
	/**
	 * Method name: getQuestionCategoryWithFullNameMap <BR>
	 * Description: 获取分类Map，其中分类名称为全名 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return Map<Integer,QuestionCategoryWithFullNameVo><BR>
	 */
	Map<Integer, QuestionnaireCategoryWithFullNameVo> getQuestionnaireCategoryWithFullNameMap(
			Integer companyId);

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

}
