/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManagementLearningMapDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-13        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.PostPromotionState;
import com.jftt.wifi.bean.vo.PostApplyVo;
import com.jftt.wifi.bean.vo.PostCourseVo;
import com.jftt.wifi.bean.vo.PostPath;
import com.jftt.wifi.bean.vo.PostStage;

/**
 * class name:ManagementLearningMapDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-13
 * @author JFTT)HeTianrui
 */
public interface ManagementLearningMapDaoMapper {
    
	//zhangbocheng start
	/**
	 * Method name: getPathList <BR>
	 * Description: 查询所有路径 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public List<PostPath> getPathList(@Param("companyId") Integer companyId,@Param("subCompanyId") Integer subCompanyId)throws Exception;
	
	
	/**
	 * Method name: getPathById <BR>
	 * Description: 根据id取得路径 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	 */
	public PostPath getPathById(@Param("id") Integer id)throws Exception;
	
	/**
	 * Method name: queryCountByPathName <BR>
	 * Description: 新建 <BR>
	 * Remark: <BR>
	 * @param name
	 * @return
	 * @throws Exception  int<BR>
	 */
	public Integer insertPath(PostPath path)throws Exception;
	
	/**
	 * Method name: updatePath <BR>
	 * Description: 修改 <BR>
	 * Remark: <BR>
	 * @param name
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int updatePath(PostPath path)throws Exception;
	
	/**
	 * Method name: deletePath <BR>
	 * Description: 删除路径 <BR>
	 * Remark: <BR>
	 * @param name
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int deletePath(@Param("id") Integer id)throws Exception;
	
	
	/**
	 * Method name: getStageById <BR>
	 * Description: 根据id取得阶段 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public PostStage getStageById(@Param("id") Integer id)throws Exception;
	
	/**
	 * Method name: checkPathStage <BR>
	 * Description: 检查阶段重复 <BR>
	 * Remark: <BR>
	 * @param name
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int checkPathStage(@Param("postId")Integer postId,@Param("pathId")Integer pathId
			)throws Exception;
	
	
	/**
	 * Method name: insertPathStage <BR>
	 * Description: 新建 阶段<BR>
	 * Remark: <BR>
	 * @param stage
	 * @return
	 * @throws Exception  int<BR>
	 */
	public Integer insertPathStage(PostStage stage)throws Exception;
	
	/**
	 * Method name: updateStage <BR>
	 * Description: 修改 阶段<BR>
	 * Remark: <BR>
	 * @param Stage
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int updatePathStage(PostStage stage)throws Exception;
	
	/**
	 * Method name: deletePathStage <BR>
	 * Description: 删除阶段 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int deletePathStage(@Param("id") Integer id)throws Exception;
	
	/**
	 * Method name: getMaxOrderNum <BR>
	 * Description: 取得最大排序字段 <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public Integer getMaxOrderNum(@Param("pathId") Integer pathId)throws Exception;
	
	/**
	 * Method name: getUpStage <BR>
	 * Description: 取得上一级阶段 <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public PostStage getUpStage(@Param("pathId") Integer pathId,@Param("orderNum") Integer orderNum)throws Exception;
	
	/**
	 * Method name: getDownStage <BR>
	 * Description: 取得下一级阶段 <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public PostStage getDownStage(@Param("pathId") Integer pathId,@Param("orderNum") Integer orderNum)throws Exception;
	
	
	/**
	 * Method name: getFirstStage <BR>
	 * Description: 取得第一阶段 <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public PostStage getFirstStage(@Param("pathId") Integer pathId)throws Exception;
	
	/**
	 * Method name: getTotalCredits <BR>
	 * Description: 查询课程总学分 <BR>
	 * Remark: <BR>
	 * @param name
	 * @return
	 * @throws Exception  int<BR>
	 */
	public Integer getTotalCredits(@Param("type") Integer type,@Param("postId")Integer postId
			)throws Exception;
	
	/**
	 * Method name: getMAXCredits <BR>
	 * Description: 查询某岗位关联的阶段最大学分 <BR>
	 * Remark: <BR>
	 * @param name
	 * @return
	 * @throws Exception  int<BR>
	 */
	public Integer getMAXCredits(@Param("type") Integer type,@Param("postId")Integer postId
			)throws Exception;
	/**
	 * Method name: getStageByPostId <BR>
	 * Description: 根据岗位id取得阶段 <BR>
	 * Remark: <BR>
	 * @param postId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public List<Integer> getStageByPostId(@Param("postId") Integer postId)throws Exception;
	
	
	/**
	 * Method name: getStageByPathId <BR>
	 * Description: 根据路径id取得阶段 <BR>
	 * Remark: <BR>
	 * @param postId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public List<Integer> getStageByPathId(@Param("pathId") Integer postId)throws Exception;
	
	
	/**
	 * Method name: isCanPromotion <BR>
	 * Description: 是否满足晋升条件 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int isCanPromotion(@Param("id") Integer id)throws Exception;
	/**
	 * Method name: agreePromotion <BR>
	 * Description: 同意晋升<BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int agreePromotion(@Param("id") Integer id,@Param("status") Integer status,@Param("reason") String reason)throws Exception;
	
	
	/**
	 * Method name: getPromotionStateById <BR>
	 * Description: 根据id取得晋升记录 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public PostPromotionState getPromotionStateById(@Param("id") Integer id)throws Exception;
	
	/**
	 * Method name: checkPromotionState <BR>
	 * Description: 检查晋升记录是否重复 <BR>
	 * Remark: <BR>
	 * @param stageId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int checkPromotionState(@Param("stageId")Integer stageId,@Param("userId")Integer userId
			)throws Exception;
	
	
	/**
	 * Method name: insertPromotionState <BR>
	 * Description: 新建 晋升记录<BR>
	 * Remark: <BR>
	 * @param state
	 * @return
	 * @throws Exception  int<BR>
	 */
	public Integer insertPromotionState(PostPromotionState state)throws Exception;
	
	/**
	 * Method name: updatePromotionState <BR>
	 * Description: 修改 晋升记录<BR>
	 * Remark: <BR>
	 * @param state
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int updatePromotionState(PostPromotionState state)throws Exception;
	
	/**
	 * Method name: deletePromotionState <BR>
	 * Description: 删除晋升记录 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int deletePromotionState(@Param("id") Integer id)throws Exception;
	
	/**
	 * Method name: deletePromotionStateByUserId <BR>
	 * Description: 根据用户删除晋升记录 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int deletePromotionStateByUserId(@Param("userId") Integer userId)throws Exception;
	
	
	/**
	 * Method name: deletePromotionStateByStageId <BR>
	 * Description: 根据晋升阶段删除晋升记录 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int deletePromotionStateByStageId(@Param("stageId") Integer stageId)throws Exception;
	
	
	/**
	 * Method name: examinePostPromotionState <BR>
	 * Description: 审批 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int examinePostPromotionState(@Param("id") Integer id,@Param("status")Integer status,@Param("reason")String reason)throws Exception;
	
	
	/**
	 * Method name: getUnfinishRecord <BR>
	 * Description:查询晋升未完成的记录<BR>
	 * Remark: <BR>
	 * @param page pageSize
	 * @return
	 * @throws Exception  List<PostApplyVo><BR>
	 */
	public List<PostApplyVo> getUnfinishRecord(@Param("beginId")Integer beginId,@Param("endId")Integer endId)throws Exception;
	/**
	 * Method name: countUnfinishRecord <BR>
	 * Description: 查询晋升未完成的记录数目 <BR>
	 * Remark: <BR>
	 * @param 
	 * @return
	 * @throws Exception  <BR>
	 */
	public int countUnfinishRecord()throws Exception;
	
	/**
	 * Method name: getMaxStateId <BR>
	 * Description: 查询晋升记录最大id <BR>
	 * Remark: <BR>
	 * @param 
	 * @return
	 * @throws Exception  <BR>
	 */
	public Integer getMaxStateId()throws Exception;
	
	/**
	 * Method name: setPromotionFail <BR>
	 * Description: 设置晋升失败 <BR>
	 * Remark: <BR>
	 * @param 
	 * @return
	 * @throws Exception  <BR>
	 */
	public int setPromotionFail(@Param("id")Integer id)throws Exception;
	
	
	//zhangbocheng end
	/**
	 * Method name: queryCountByPathName <BR>
	 * Description: queryCountByPathName <BR>
	 * Remark: <BR>
	 * @param name
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int queryCountByPathName(@Param("name") String name,@Param("companyId") Integer companyId,
			@Param("subCompanyId") Integer subCompanyId,@Param("id") Integer id)throws Exception;
    /**
     * Method name: addPromotionPath <BR>
     * Description: addPromotionPath <BR>
     * Remark: <BR>
     * @param path
     * @throws Exception  void<BR>
     */
	public void addPromotionPath(PostPath path)throws Exception;
	/**
	 * Method name: editPromotionPath <BR>
	 * Description: editPromotionPath <BR>
	 * Remark: <BR>
	 * @param path
	 * @throws Exception  void<BR>
	 */
	public void editPromotionPath(PostPath path)throws Exception;
	/**
	 * Method name: queryLearningMapByPath <BR>
	 * Description: queryLearningMapByPath <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return
	 * @throws Exception  PostPath<BR>
	 */
	public PostPath queryLearningMapByPath(@Param("pathId") long pathId)throws Exception;
	/**
	 * Method name: queryStageById <BR>
	 * Description: queryStageById <BR>
	 * Remark: <BR>
	 * @param stageId
	 * @return
	 * @throws Exception  List<PostStage><BR>
	 */
	public List<PostStage> queryStageByPathId(@Param("pathId") long  pathId)throws Exception;
	/**
	 * Method name: queryStudentByStageId <BR>
	 * Description: queryStudentByStageId <BR>
	 * Remark: <BR>
	 * @param postApply
	 * @return
	 * @throws Exception  List<PostApplyVo><BR>
	 */
	public List<PostApplyVo> queryStudentByStageId(PostApplyVo postApply)throws Exception;
	/**
	 * Method name: countStudentByStageId <BR>
	 * Description: 查询阶段下学员数目 <BR>
	 * Remark: <BR>
	 * @param postApply
	 * @return
	 * @throws Exception  List<PostApplyVo><BR>
	 */
	public int countStudentByStageId(PostApplyVo postApply)throws Exception;
	
	/**
	 * Method name: queryCourseByStageId <BR>
	 * Description: queryCourseByStageId <BR>
	 * Remark: <BR>
	 * @param stageId
	 * @return
	 * @throws Exception  List<PostCourseVo><BR>
	 */
	public List<PostCourseVo> queryCourseByPostId(@Param("postId") long postId)throws Exception;

}
