/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManagementLearningMapService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-13        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.PostPromotionState;
import com.jftt.wifi.bean.vo.PostApplyVo;
import com.jftt.wifi.bean.vo.PostCourseVo;
import com.jftt.wifi.bean.vo.PostPath;
import com.jftt.wifi.bean.vo.PostStage;

/**
 * class name:ManagementLearningMapService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-13
 * @author JFTT)HeTianrui
 */
public interface ManagementLearningMapService {
   
	/**
	 * Method name: getPathById <BR>
	 * Description: 根据id取得路径 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	 */
	public PostPath getPathById(Integer id)throws Exception;
	
	/**
	 * Method name: getPathList <BR>
	 * Description: 取得所有路径 <BR>
	 * Remark: <BR>
     * @param  companyId
	 * @param subCompanyId
	 * @return
	 * @throws Exception  String<BR>
	 */
	List<PostPath> getPathList(Integer companyId,Integer subCompanyId)throws Exception;
	
	/**
	 * Method name: checkPathName <BR>
	 * Description: 检查路径重名 <BR>
	 * Remark: <BR>
     * @param name
     * @param companyId
     * @param subCompanyId
     * @param id
     * @return
	 * @throws Exception  int<BR>
	 */
	public int checkPathName(String name,Integer companyId,Integer subCompanyId,Integer id)throws Exception;
	/**
	 * Method name: insertPath <BR>
	 * Description: 新建路径 <BR>
	 * Remark: <BR>
	 * @param path
	 * @return
	 * @throws Exception  String<BR>
	 */
	Integer insertPath(PostPath path)throws Exception;
	
	/**
	 * Method name: updatePath <BR>
	 * Description: 修改路径 <BR>
	 * Remark: <BR>
	 * @param path
	 * @return
	 * @throws Exception  String<BR>
	 */
	Integer updatePath(PostPath path)throws Exception;
	
	/**
	 * Method name: deletePath <BR>
	 * Description: 删除路径 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  Integer<BR>
	 */
	Integer deletePath(Integer id)throws Exception;
	
	/**
	 * Method name: getPathStageById <BR>
	 * Description: 根据id查询阶段 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  LearnPlanStageBean<BR>
	 */
	public PostStage getPathStageById(Integer id)throws Exception;
	
	/**
	 * Method name: checkPathStage <BR>
	 * Description: 检查阶段重复 <BR>
	 * Remark: <BR>
	 * @param postId
     * @param pathId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int checkPathStage(Integer postId,Integer pathId
			)throws Exception;
	
	/**
	 * Method name: getTotalCredits <BR>
	 * Description: 查询课程总学分 <BR>
	 * Remark: <BR>
	 * @param type
     * @param postId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public Integer getTotalCredits(Integer type,Integer postId
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
	 * @param stage
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
	public int deletePathStage(Integer id)throws Exception;
	/**
	  * Method name: upPathStage <BR>
	  * Description: 上移阶段 <BR> 
	  * @param id
	  * @return
	  * @throws Exception
	  */

		public void upPathStage(Integer id) throws Exception ;
		/**
		  * Method name: downPathStage <BR>
		  * Description: 下移阶段 <BR> 
		  * @param id
		  * @return
		  * @throws Exception
		  */

			public void downPathStage(Integer id) throws Exception;
	
	/**
	 * Method name: getMaxOrderNum <BR>
	 * Description: 取得最大排序字段 <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public Integer getMaxOrderNum(Integer pathId)throws Exception;
	
	/**
	 * Method name: deletePathStage <BR>
	 * Description: 取得上一级阶段 <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public PostStage getUpStage(Integer pathId,Integer orderNum)throws Exception;
	
	/**
	 * Method name: getDownStage <BR>
	 * Description: 取得下一级阶段 <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public PostStage getDownStage(Integer pathId,Integer orderNum)throws Exception;
	
	
	/**
	 * Method name: insertPostPromotionStateByUser <BR>
	 * Description: 根据用户新建 晋升记录<BR>
	 * Remark: <BR>
	 * @param userId postId
	 * @return
	 * @throws Exception <BR>
	 */
	public void insertPostPromotionStateByUser(Integer userId,Integer postId)throws Exception;
	/**
	 * Method name: isCanPromotion <BR>
	 * Description: 是否满足晋升条件 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int isCanPromotion(Integer id)throws Exception;
	/**
	 * Method name: agreePromotion <BR>
	 * Description: 同意晋升<BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int agreePromotion(Integer id,Integer Status,String reason)throws Exception;
	
	/**
	 * Method name: getPostPromotionStateById <BR>
	 * Description: 根据id取得晋升记录 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public PostPromotionState getPostPromotionStateById(Integer id)throws Exception;
	
	/**
	 * Method name: checkPostPromotionState <BR>
	 * Description: 检查晋升记录是否重复 <BR>
	 * Remark: <BR>
	 * @param stageId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int checkPostPromotionState(Integer stageId,Integer userId
			)throws Exception;
	
	
	/**
	 * Method name: insertPostPromotionState <BR>
	 * Description: 新建 晋升记录<BR>
	 * Remark: <BR>
	 * @param state
	 * @return
	 * @throws Exception  int<BR>
	 */
	public Integer insertPostPromotionState(PostPromotionState state)throws Exception;
	
	/**
	 * Method name: updatePostPromotionState <BR>
	 * Description: 修改 晋升记录<BR>
	 * Remark: <BR>
	 * @param state
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int updatePostPromotionState(PostPromotionState state)throws Exception;
	
	/**
	 * Method name: deletePostPromotionState <BR>
	 * Description: 删除晋升记录 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int deletePostPromotionState(Integer id)throws Exception;
	
	/**
	 * Method name: examinePostPromotionState <BR>
	 * Description: 审批 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int examinePostPromotionState( Integer id,Integer status,String reason)throws Exception;
	
	
	/**
	 * Method name: countStudentByStageId <BR>
	 * Description: 查询阶段下学员数目 <BR>
	 * Remark: <BR>
	 * @param postApply
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int countStudentByStageId(PostApplyVo postApply)throws Exception;
	

	/**
	 * Method name: getUnfinishRecord <BR>
	 * Description:查询晋升未完成的记录<BR>
	 * Remark: <BR>
	 * @param beginId endId
	 * @return
	 * @throws Exception  List<PostApplyVo><BR>
	 */
	public List<PostApplyVo> getUnfinishRecord(Integer beginId,Integer endId)throws Exception;
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
	 * Description: 查询晋升未完成的记录数目 <BR>
	 * Remark: <BR>
	 * @param 
	 * @return
	 * @throws Exception <BR>
	 */
	public int setPromotionFail(Integer id)throws Exception;
	
	//zxhangbocheng end
	/**
	 * Method name: addPromotionPath <BR>
	 * Description: addPromotionPath <BR>
	 * Remark: <BR>
	 * @param path
	 * @return
	 * @throws Exception  String<BR>
	 */
	Integer addPromotionPath(PostPath path)throws Exception;
    /**
     * Method name: editPromotionPath <BR>
     * Description: editPromotionPath <BR>
     * Remark: <BR>
     * @param path  void<BR>
     */
	void editPromotionPath(PostPath path)throws Exception;
	/**
	 * Method name: queryLearningMapByPath <BR>
	 * Description: queryLearningMapByPath <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return
	 * @throws Exception  PostPath<BR>
	 */
	PostPath queryLearningMapByPath(long pathId)throws Exception;
	/**
	 * Method name: queryStageById <BR>
	 * Description: queryStageById <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return
	 * @throws Exception  List<PostStage><BR>
	 */
	List<PostStage> queryStageByPathId(long pathId)throws Exception;
	/**
	 * Method name: queryStudentByStageId <BR>
	 * Description: queryStudentByStageId <BR>
	 * Remark: <BR>
	 * @param postApply
	 * @return
	 * @throws Exception  List<PostApplyVo><BR>
	 */
	List<PostApplyVo> queryStudentByStageId(PostApplyVo postApply)throws Exception;
	/**
	 * Method name: queryCourseByStageId <BR>
	 * Description: queryCourseByStageId <BR>
	 * Remark: <BR>
	 * @param postId
	 * @return
	 * @throws Exception  List<PostCourseVo><BR>
	 */
	List<PostCourseVo> queryCourseByPostId(long postId)throws Exception;

}
