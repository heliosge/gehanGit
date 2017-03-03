/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: StudentLearnPlanService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-31        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.vo.LearnPlanStageCourseVo;
import com.jftt.wifi.bean.vo.LearnPlanStageVo;
import com.jftt.wifi.bean.vo.LearnPlanVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:StudentLearnPlanService <BR>
 * class description: 学员学习计划service <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-31
 * @author JFTT)ShenHaijun
 */
public interface StudentLearnPlanService {
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getLearnPlanCount <BR>
	 * Description: 获取学习计划数目 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param searchText 查询内容
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getLearnPlanCount(Integer userId, Integer learnProcess,String searchText) throws DataBaseException;
	
	/**
	 * Method name: getLearnPlansByConditions <BR>
	 * Description: 根据条件查询该学员的学习计划  <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param searchText 查询内容
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<LearnPlanVo>
	 * @throws DataBaseException <BR>
	 */
	List<LearnPlanVo> getLearnPlansByConditions(Integer userId,Integer learnProcess,String searchText,
			Integer fromNum,Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getLearnPlanStages <BR>
	 * Description: 查询出学习计划的所有阶段 <BR>
	 * Remark: <BR>
	 * @param learnPlanId 学习计划id
	 * @param userId 用户id
	 * @return List<LearnPlanStageVo>
	 * @throws DataBaseException <BR>
	 */
	List<LearnPlanStageVo> getLearnPlanStages(Integer learnPlanId,Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getLearnPlanStageCourses <BR>
	 * Description: 获取该阶段的所有课程 <BR>
	 * Remark: <BR>
	 * @param learnPlanStageId 计划阶段id
	 * @param userId 用户id
	 * @return List<LearnPlanStageCourseVo>
	 * @throws DataBaseException <BR>
	 */
	List<LearnPlanStageCourseVo> getLearnPlanStageCourses(Integer learnPlanStageId,
			Integer userId) throws DataBaseException;
	
	/**
	 * Method name: updateLearnPlanStatus <BR>
	 * Description: 更新学习计划状态 <BR>
	 * Remark: <BR>
	 * @param learnPlanId 学习计划id
	 * @param userId 用户id
	 * @throws DataBaseException  void<BR>
	 */
	void updateLearnPlanStatus(Integer learnPlanId, Integer userId) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
