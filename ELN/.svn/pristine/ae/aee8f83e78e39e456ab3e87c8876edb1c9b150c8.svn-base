/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: LearnPlanStageDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-31        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.LearnPlanStageBean;
import com.jftt.wifi.bean.vo.LearnPlanStageCourseVo;
import com.jftt.wifi.bean.vo.LearnPlanStageVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:LearnPlanStageDaoMapper <BR>
 * class description: 学习计划阶段dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-31
 * @author JFTT)ShenHaijun
 */
public interface LearnPlanStageDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询学习计划阶段 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  LearnPlanStageBean<BR>
	 */
	public LearnPlanStageBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getLearnPlanStages <BR>
	 * Description: 查询出学习计划的所有阶段 <BR>
	 * Remark: <BR>
	 * @param learnPlanId
	 * @param userId
	 * @return
	 * @throws DataBaseException  List<LearnPlanStageVo><BR>
	 */
	public List<LearnPlanStageVo> getLearnPlanStages(@Param("learnPlanId")Integer learnPlanId,
			@Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getLearnPlanStageCourses <BR>
	 * Description: 获取该阶段的所有课程 <BR>
	 * Remark: <BR>
	 * @param learnPlanId
	 * @param userId
	 * @param companyId
	 * @return
	 * @throws DataBaseException  List<LearnPlanStageCourseVo><BR>
	 */
	public List<LearnPlanStageCourseVo> getLearnPlanStageCourses(@Param("learnPlanStageId")Integer learnPlanStageId,
			@Param("userId")Integer userId) throws DataBaseException;
	
	/**shenhaijun end*/
	
	
	/**zhangbocheng start*/
	/**
	 * Method name: getLearnPlanStageList <BR>
	 * Description: 查询出学习计划下所有阶段<BR> 
	 * @param planId
	 * @return
	 * @throws Exception
	 */
	public List<LearnPlanStageBean> getLearnPlanStageList(@Param("planId")Integer planId) throws Exception;
	
	
	/**
	 * Method name: getLearnPlanStagesCount <BR>
	 * Description: 查询出学习计划下所有阶段数目<BR> 
	 * @param planId
	 * @return
	 * @throws Exception
	 */
	public int getLearnPlanStagesCount(@Param("planId")Integer planId,@Param("isHave")Integer isHave) throws Exception;
	/**
	 * Method name: checkName <BR>
	 * Description: 检查阶段重名<BR> 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int checkName (Map<String,Object> map) throws Exception;
	
	/**
	 * Method name: insert <BR>
	 * Description: 添加阶段<BR> 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public int insert (LearnPlanStageBean bean) throws Exception;
	
	/**
	 * Method name: update <BR>
	 * Description: 修改阶段<BR> 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public int update (LearnPlanStageBean bean) throws Exception;
	
	/**
	 * Method name: deleteById <BR>
	 * Description: 删除阶段<BR> 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteById (Integer id) throws Exception;
	
	/**
	 * Method name: getMaxOrderNum <BR>
	 * Description: 取得学习计划最大的排序字段order_num<BR> 
	 * @param planId
	 * @return
	 * @throws Exception
	 */
	public Integer getMaxOrderNum (@Param("planId")Integer planId) throws Exception;
	
	/**
	 * Method name: getOrderNum <BR>
	 * Description: 获得阶段的orderNum和最大，最小orderNum<BR> 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getOrderNum (@Param("id")Integer id) throws Exception;
	
	/**
	 * Method name: getUpStage <BR>
	 * Description: 取得该阶段前面的阶段<BR> 
	 * @param planId orderNum
	 * @return
	 * @throws Exception
	 */
	public LearnPlanStageBean getUpStage (@Param("planId")Integer planId,@Param("orderNum")Integer orderNum) throws Exception;
	
	/**
	 * Method name: getDownStage <BR>
	 * Description: 取得该阶段后一个阶段<BR> 
	 * @param planId orderNum
	 * @return
	 * @throws Exception
	 */
	public LearnPlanStageBean getDownStage (@Param("planId")Integer planId,@Param("orderNum")Integer orderNum) throws Exception;
	
	/**
	 * Method name: getCompleteCourseCount <BR>
	 * Description: 查询每一阶段学完的课程数目 <BR>
	 * Remark: <BR>
	 * @param stageId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getCompleteCourseCount(@Param("stageId")Integer stageId,@Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getToatlCourseCount <BR>
	 * Description: 查询每一阶段总共课程数目 <BR>
	 * Remark: <BR>
	 * @param stageId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getToatlCourseCount(@Param("stageId")Integer stageId) throws DataBaseException;
	
	/**zhangbocheng end*/
}
