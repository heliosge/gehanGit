/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PoliciesRulesDaoCategoryMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-2-17        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.PoliciesRulesCategoryBean;

/**
 * class name:PoliciesRulesDaoCategoryMapper <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2016-2-17
 * @author JFTT)chenrui
 */
public interface PoliciesRulesDaoCategoryMapper {

	List<PoliciesRulesCategoryBean> getCategorys() throws Exception;

	void addCategory(PoliciesRulesCategoryBean param) throws Exception;

	void delCategory(@Param("categoryId")String categoryId) throws Exception;

	PoliciesRulesCategoryBean getCategoryById(@Param("categoryId")String categoryId) throws Exception;

	void modifyCategory(PoliciesRulesCategoryBean param)throws Exception;
	
}
