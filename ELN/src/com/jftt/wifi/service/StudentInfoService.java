/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: StudentInfoService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-5        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.vo.IndexShowVo;
import com.jftt.wifi.bean.vo.ResCourseVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:StudentInfoService <BR>
 * class description: 学员信息service <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-5
 * @author JFTT)ShenHaijun
 */
public interface StudentInfoService {
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getMyPoint <BR>
	 * Description: 获取我的积分 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getMyPoint(Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getMyStudyHours <BR>
	 * Description: 获取我的学时 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getMyStudyHours(Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getMyExamCount <BR>
	 * Description: 获取我的待参与考试数量 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getMyExamCount(Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getCourseCount <BR>
	 * Description: 获取公司的课程数量 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getCourseCount(Integer companyId, Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getExamCount <BR>
	 * Description: 获取公司的考试数量 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param userId 用户id
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getExamCount(Integer companyId, Integer subCompanyId,Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getSpecialTopicCount <BR>
	 * Description: 获取专题数量 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getSpecialTopicCount(Integer companyId, Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getSpreadBlocks <BR>
	 * Description: 获取学员首页推广模块 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<IndexShowVo>
	 * @throws DataBaseException <BR>
	 */
	List<IndexShowVo> getSpreadBlocks(Integer companyId, Integer subCompanyId) throws Exception;
	
	/**
	 * Method name: getFeatureCourses <BR>
	 * Description: 获取精选课程 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<ResCourseVo>
	 * @throws DataBaseException <BR>
	 */
	List<ResCourseVo> getFeatureCourses(Integer companyId, Integer subCompanyId) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
