/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: LearnPlanAssignmentDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-31        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.LearnPlanAssignmentBean;
import com.jftt.wifi.bean.vo.LearnPlanAssignmentVo;
import com.jftt.wifi.bean.vo.LearnPlanVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:LearnPlanAssignmentDaoMapper <BR>
 * class description: 用户学习计划dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-31
 * @author JFTT)ShenHaijun
 */
public interface LearnPlanAssignmentDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id获取用户学习计划 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  LearnPlanAssignmentBean<BR>
	 */
	public LearnPlanAssignmentBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getLearnPlanCount <BR>
	 * Description: 查询学习计划数目 <BR>
	 * Remark: <BR>
	 * @param learnPlanVo
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getLearnPlanCount(LearnPlanVo learnPlanVo) throws DataBaseException;
	
	/**
	 * Method name: getLearnPlansByConditions <BR>
	 * Description: 根据条件查询该学员的学习计划 <BR>
	 * Remark: <BR>
	 * @param learnPlanVo
	 * @return
	 * @throws DataBaseException  List<LearnPlanVo><BR>
	 */
	public List<LearnPlanVo> getLearnPlansByConditions(@Param("learnPlanVo")LearnPlanVo learnPlanVo,
			@Param("fromNum")Integer fromNum,@Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**shenhaijun end*/
	
	/** zhangbocheng start*/
	
	/**
	 * Method name: getLearnPlanAssignmentListByPlanId <BR>
	 * Description: 根据条件查询学习计划下所有人员 <BR>
	 * Remark: <BR>
	 * @param planId
	 * @return
	 * @throws Exception
	 */
	public List<LearnPlanAssignmentVo> getLearnPlanAssignmentListByPlanId(@Param("planId")Integer planId,@Param("userIds")String userIds,@Param("page")Integer page,@Param("pageSize")Integer pageSize)throws Exception;
	
	/**
	 * Method name: getCountByMap <BR>
	 * Description: 根据条件查询学习计划下人员数目 <BR>
	 * Remark: <BR>
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getCountByMap(Map<String,Object> map) throws Exception;
	
	/**
	 * Method name: getCountByBean <BR>
	 * Description: 根据条件查询学习计划下人员数目 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public int getCountByBean(LearnPlanAssignmentBean bean) throws Exception;
	
	/**
	 * Method name: insertByUserId <BR>
	 * Description: 添加一个人员 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public int insertByUserId(LearnPlanAssignmentBean bean) throws Exception;
	
	/**
	 * Method name: deleteById <BR>
	 * Description: 根据删除学习计划下人员 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteById(@Param("id")int id) throws Exception;
	
	/**
	 * Method name: getLearnPlanStudentProcess <BR>
	 * Description: 查询该学习计划阶段下所有学员的进度 - <BR>
	 * Remark: <BR>
	 * @param planId stageId
	 * @return
	 * @throws Exception
	 */
	public List<LearnPlanAssignmentVo> getLearnPlanStudentProcess(@Param("userIds")String userIds,@Param("planId")Integer planId,
			@Param("stageId")Integer stageId,@Param("status")Integer status,@Param("page")Integer page,@Param("pageSize")Integer pageSize)throws Exception;

	/**
	 * Method name: getLearnPlanStudentProcess <BR>
	 * Description: 查询该学习计划阶段下所有学员的进度 - <BR>
	 * Remark: <BR>
	 * @param planId stageId
	 * @return
	 * @throws Exception
	 */
	public int getLearnPlanStudentCount(@Param("userIds")String userIds,@Param("planId")Integer planId,@Param("stageId")Integer stageId,@Param("status")Integer status)throws Exception;
	
	/**
	 * Method name: updateLearnPlanStatus <BR>
	 * Description: 更新学习计划状态 <BR>
	 * Remark: <BR>
	 * @param learnPlanId
	 * @param userId
	 * @throws DataBaseException  void<BR>
	 */
	public void updateLearnPlanStatus(@Param("learnPlanId")Integer learnPlanId, @Param("userId")Integer userId) throws DataBaseException;

	/** zhangbocheng end*/
	
}
