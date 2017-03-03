/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManagePostDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月11日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ManagePostBean;

/**
 * class name:ManagePostDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月11日
 * @author JFTT)Lu Yunlong
 */
public interface ManagePostDaoMapper {

	void insertManagePost(ManagePostBean post);

	void deleteManagePost(Integer id);

	void updateManagePost(ManagePostBean post);

	List<ManagePostBean> selectManagePost(Map<String, Object> param);
	
	/**
	 * Method name: selectCourseOpenPostByCourseId <BR>
	 * Description: 根据课程id获取开放岗位 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  List<ManagePostBean><BR>
	 */
	List<ManagePostBean> selectCourseOpenPostByCourseId(Integer id);

	ManagePostBean getById(@Param("id")int qualificationId);

	List<ManagePostBean> getPostByParentId(@Param("postId")int postId);
	
}
