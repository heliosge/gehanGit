/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamQuestionCategoryService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/22        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.exam.QuestionCategoryBean;
import com.jftt.wifi.bean.exam.vo.QuestionCategorySearchOptionVo;
import com.jftt.wifi.bean.exam.vo.QuestionCategoryWithFullNameVo;

/**
 * class name:ExamQuestionCategoryService <BR>
 * class description: 试题分类Service <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/22
 * @author JFTT)wangyifeng
 */
public interface ExamQuestionCategoryService {
	/**
	 * Method name: getQuestionCategoryList <BR>
	 * Description: 获取试题分类列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<QuestionCategoryBean><BR>
	 */
	List<QuestionCategoryBean> getQuestionCategoryList(
			QuestionCategorySearchOptionVo searchOption);
	
	/**
	 * 根据公司ID获得 下面的试题库
	*/
	public List<QuestionCategoryBean> getQuestionCategoryListBySubCompany(QuestionCategorySearchOptionVo searchOption);

	/**
	 * Method name: addCategory <BR>
	 * Description: 增加分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param newCategory
	 *            void<BR>
	 */
	void addCategory(QuestionCategoryBean newCategory);

	/**
	 * Method name: modifyCategory <BR>
	 * Description: 修改分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param modifiedCategory
	 *            void<BR>
	 */
	void modifyCategory(QuestionCategoryBean modifiedCategory);

	/**
	 * Method name: deleteCategory <BR>
	 * Description: 删除分类 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 *            void<BR>
	 */
	void deleteCategory(Integer categoryId);

	/**
	 * Method name: getCategory <BR>
	 * Description: 获取分类信息 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @return QuestionCategoryBean<BR>
	 */
	QuestionCategoryBean getCategory(Integer categoryId);

	// Support methods
	/**
	 * Method name: getQuestionCategoryWithFullNameMap <BR>
	 * Description: 获取分类Map，其中分类名称为全名 <BR>
	 * Remark: <BR>
	 * 
	 * @param companyId
	 * @return Map<Integer,QuestionCategoryWithFullNameVo><BR>
	 */
	Map<Integer, QuestionCategoryWithFullNameVo> getQuestionCategoryWithFullNameMap(
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
	 * Description: 该分类下是否有试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @return boolean<BR>
	 */
	boolean hasQuestion(Integer categoryId);
	
	//zhangchen start
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseQuestionCategory <BR>
	 * Description: 获取我的练习中的分类 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<QuestionCategoryBean><BR>
	 */
	List<QuestionCategoryBean> getExerciseQuestionCategory(QuestionCategorySearchOptionVo searchOption);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getQuestionCategoryById <BR>
	 * Description: 根据ID获取试题分类信息 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  QuestionCategoryBean<BR>
	 */
	QuestionCategoryBean getQuestionCategoryById(QuestionCategorySearchOptionVo searchOption);

	
	//zhangchen end
}
