/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: StudentLearnPlanServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-31        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.vo.LearnPlanStageCourseVo;
import com.jftt.wifi.bean.vo.LearnPlanStageVo;
import com.jftt.wifi.bean.vo.LearnPlanVo;
import com.jftt.wifi.dao.LearnPlanAssignmentDaoMapper;
import com.jftt.wifi.dao.LearnPlanStageDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.StudentLearnPlanService;

/**
 * class name:StudentLearnPlanServiceImpl <BR>
 * class description: 学员学习计划service <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-31
 * @author JFTT)ShenHaijun
 */
@Service("studentLearnPlanService")
public class StudentLearnPlanServiceImpl implements StudentLearnPlanService {
	
	@Resource(name="learnPlanAssignmentDaoMapper")
	private LearnPlanAssignmentDaoMapper learnPlanAssignmentDaoMapper;
	@Resource(name="learnPlanStageDaoMapper")
	private LearnPlanStageDaoMapper learnPlanStageDaoMapper;
	
	/**shenhaijun start*/
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnPlanService#getLearnPlanCount(java.lang.Integer, java.lang.Integer, java.lang.String) <BR>
	 * Method name: getLearnPlanCount <BR>
	 * Description: 获取学习计划数目 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param searchText 查询文本
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getLearnPlanCount(Integer userId, Integer learnProcess,
			String searchText) throws DataBaseException {
		//设置查询条件
		LearnPlanVo learnPlanVo = new LearnPlanVo();
		learnPlanVo.setUserId(userId);
		learnPlanVo.setStatus(learnProcess);
		learnPlanVo.setNameOrType(searchText);
		//查询学习计划数目
		return learnPlanAssignmentDaoMapper.getLearnPlanCount(learnPlanVo);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnPlanService#getLearnPlansByConditions(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getLearnPlansByConditions <BR>
	 * Description: 根据条件查询该学员的学习计划 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param searchText 查询文本
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<LearnPlanVo>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<LearnPlanVo> getLearnPlansByConditions(Integer userId,Integer learnProcess,String searchText,
			Integer fromNum,Integer pageSize)throws DataBaseException {
		//设置查询条件
		LearnPlanVo learnPlanVo = new LearnPlanVo();
		learnPlanVo.setUserId(userId);
		learnPlanVo.setStatus(learnProcess);
		learnPlanVo.setNameOrType(searchText);
		//查询学习计划
		List<LearnPlanVo> plans = learnPlanAssignmentDaoMapper.getLearnPlansByConditions(learnPlanVo,fromNum,pageSize);
		//遍历学习计划，将具体的学习进度添加进来
		if(plans != null && plans.size() > 0){
			for (int i = 0; i < plans.size(); i++) {
				//获取该计划该学员的学习进度
				Integer percent = getPlanStudyPercent(plans.get(i).getId(),userId);
				//将学习进度放入添加到列表中
				plans.get(i).setStudyPercent(percent);
			}
			return plans;
		}else{
			return plans;
		}
	}
	
	/**
	 * Method name: getPlanStudyPercent <BR>
	 * Description: 获取该计划该学员的学习进度 <BR>
	 * Remark: <BR>
	 * @param learnPlanId 学习计划id
	 * @param userId 用户id
	 * @return Integer
	 * @throws DataBaseException  Integer<BR>
	 */
	private Integer getPlanStudyPercent(Integer learnPlanId, Integer userId) throws DataBaseException {
		//默认学习进度未0
		Integer percent = 0;
		//获取计划的所有阶段
		List<LearnPlanStageVo> stages = learnPlanStageDaoMapper.getLearnPlanStages(learnPlanId,userId);
		//遍历各阶段，计算该计划学习进度
		if(stages != null && stages.size() > 0){
			for (int i = 0; i < stages.size(); i++) {
				//获取每一阶段的学习进度
				Integer stagePercent = getStagePercent(stages.get(i).getId(),userId);
				//获取进度和
				percent += stagePercent;
			}
			//获取该计划学习进度
			percent = percent / stages.size();
		}else{
			percent = 0;
		}
		return percent;
	}
	
	/**
	 * Method name: getStagePercent <BR>
	 * Description: 获取每一阶段的学习进度 <BR>
	 * Remark: <BR>
	 * @param stageId 阶段id
	 * @param userId 用户id
	 * @return Integer
	 * @throws DataBaseException  Integer<BR>
	 */
	private Integer getStagePercent(Integer stageId, Integer userId) throws DataBaseException {
		Integer stagePercent = 0;
		//查询每一阶段学完的课程数目
		Integer completeCourseCount = learnPlanStageDaoMapper.getCompleteCourseCount(stageId,userId);
		//查询每一阶段总共课程数目
		Integer toatlCourseCount = learnPlanStageDaoMapper.getToatlCourseCount(stageId);
		//计算每一阶段百分比
		if(toatlCourseCount > 0){
			stagePercent = completeCourseCount * 100 / toatlCourseCount;
		}else{
			stagePercent = 0;
		}
		return stagePercent;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnPlanService#getLearnPlanStages(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getLearnPlanStages <BR>
	 * Description: 查询出学习计划的所有阶段 <BR>
	 * Remark: <BR>
	 * @param learnPlanId 学习计划id
	 * @param userId 用户id
	 * @return List<LearnPlanStageVo>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<LearnPlanStageVo> getLearnPlanStages(Integer learnPlanId,Integer userId) throws DataBaseException {
		List<LearnPlanStageVo> stages = learnPlanStageDaoMapper.getLearnPlanStages(learnPlanId,userId);
		//遍历各阶段，将阶段进度百分比放入
		for (int i = 0; i < stages.size(); i++) {
			//计算百分比并放入
			Integer process = getStagePercent(stages.get(i).getId(),userId);
			stages.get(i).setProcess(process);
		}
		return stages;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnPlanService#getLearnPlanStageCourses(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getLearnPlanStageCourses <BR>
	 * Description: 获取该阶段的所有课程 <BR>
	 * Remark: <BR>
	 * @param learnPlanStageId 学习计划阶段id
	 * @param userId 用户id
	 * @return List<LearnPlanStageCourseVo>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<LearnPlanStageCourseVo> getLearnPlanStageCourses(
			Integer learnPlanStageId, Integer userId)
			throws DataBaseException {
		return learnPlanStageDaoMapper.getLearnPlanStageCourses(learnPlanStageId,userId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnPlanService#updateLearnPlanStatus(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: updateLearnPlanStatus <BR>
	 * Description: 更新学习计划状态 <BR>
	 * Remark: <BR>
	 * @param learnPlanId 学习计划id
	 * @param userId 用户id
	 * @throws DataBaseException  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateLearnPlanStatus(Integer learnPlanId, Integer userId)
			throws DataBaseException {
		learnPlanAssignmentDaoMapper.updateLearnPlanStatus(learnPlanId,userId);
	}
	
	/**shenhaijun end*/
	
}
