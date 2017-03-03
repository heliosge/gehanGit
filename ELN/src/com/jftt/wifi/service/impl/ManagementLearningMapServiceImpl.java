/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManagementLearningMapServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-13        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.PostPromotionState;
import com.jftt.wifi.bean.exam.ExamScheduleBean;
import com.jftt.wifi.bean.vo.PostApplyVo;
import com.jftt.wifi.bean.vo.PostCourseVo;
import com.jftt.wifi.bean.vo.PostPath;
import com.jftt.wifi.bean.vo.PostStage;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.ExamScheduleDaoMapper;
import com.jftt.wifi.dao.ManagementLearningMapDaoMapper;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.ManagementLearningMapService;

/**
 * class name:ManagementLearningMapServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-13
 * @author JFTT)HeTianrui
 */
@Service("managementLearningMapService")
public class ManagementLearningMapServiceImpl implements ManagementLearningMapService {

	@Autowired 
	private ManagementLearningMapDaoMapper managementLearningMapDaoMapper;
	@Autowired
	private ExamScheduleDaoMapper examScheduleDaoMapper;
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private IntegralManageService integralManageService;

	@Override
	public List<PostPath> getPathList(Integer companyId,Integer subCompanyId)
			throws Exception {
		return managementLearningMapDaoMapper.getPathList(companyId,subCompanyId);
	}
	
	@Override
	public Integer insertPath(PostPath path) throws Exception{
		return managementLearningMapDaoMapper.insertPath(path);
	}
	@Override
	public Integer updatePath(PostPath path) throws Exception{
		return managementLearningMapDaoMapper.updatePath(path);
	}
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Integer deletePath(Integer id) throws Exception{
		managementLearningMapDaoMapper.deletePath(id);
		List<Integer> ids = managementLearningMapDaoMapper.getStageByPathId(id);
		for(Integer stageId:ids){
			deletePathStage(stageId);
		}
		return 1;
	}
	
	/**
	 * Method name: getPathById <BR>
	 * Description: 根据id取得路径 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	 */
	public PostPath getPathById(Integer id)throws Exception{
		return managementLearningMapDaoMapper.getPathById( id);
	}
	
	
	/**
	 * Method name: checkPathStage <BR>
	 * Description: 检查阶段重复 <BR>
	 * Remark: <BR>
	 * @param postId
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public int checkPathStage(Integer postId,Integer pathId)throws Exception{
		return managementLearningMapDaoMapper.checkPathStage( postId, pathId);
	}
	
	
	
	
	/**
	 * Method name: insertPathStage <BR>
	 * Description: 新建 阶段<BR>
	 * Remark: <BR>
	 * @param stage
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Integer insertPathStage(PostStage stage)throws Exception{
		Integer orderNum=managementLearningMapDaoMapper.getMaxOrderNum(stage.getPathId());
		if(orderNum!=null){
			orderNum+=1;
		}else{
			orderNum=1;
		}
		stage.setOrderNum(orderNum);
		ExamScheduleBean newExam = new ExamScheduleBean ();
		newExam.setPaperId(stage.getPaperId());
		newExam.setAllowTimes(3);
		newExam.setDisplayMode(1);
		newExam.setCompanyId(stage.getCompanyId());
		newExam.setSubCompanyId(stage.getSubCompanyId());
		newExam.setCreateUserId(stage.getCreateUserId());
		newExam.setTitle(stage.getExamTitle());
		newExam.setType(4);
		newExam.setDuration(stage.getDuration());
		newExam.setPassScorePercent(stage.getPassScorePercent());
        examScheduleDaoMapper.addExam(newExam);
        stage.setExamId(newExam.getId());
		managementLearningMapDaoMapper.insertPathStage(stage);
		//晋升只学习要晋升的岗位的课程
		/**
		PostStage beforeStage =managementLearningMapDaoMapper.getUpStage(stage.getPathId(), orderNum);
		if(beforeStage!=null){
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("postId", beforeStage.getPostId());
			List<ManageUserBean> userList=manageUserService.findUserByListCondition(map);
			if(userList!=null&&userList.size()>0){
				 for(ManageUserBean user:userList){
					    PostPromotionState state=new PostPromotionState();
					    state.setUserId(Integer.parseInt(user.getId()));
						state.setPromotionStageId(stage.getId());
						state.setPromotionStatus(2);
						managementLearningMapDaoMapper.insertPromotionState(state);
				    }
			}
		}
		*/
		//学习本岗位包含的课程
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("postId", stage.getPostId());
		List<ManageUserBean> userList=manageUserService.findUserByListCondition(map);
		if(userList!=null&&userList.size()>0){
			 for(ManageUserBean user:userList){
				    PostPromotionState state=new PostPromotionState();
				    state.setUserId(Integer.parseInt(user.getId()));
					state.setPromotionStageId(stage.getId());
					state.setPromotionStatus(2);
					managementLearningMapDaoMapper.insertPromotionState(state);
			    }
		}
		return stage.getId();
	}
	
	/**
	 * Method name: updateStage <BR>
	 * Description: 修改 阶段<BR>
	 * Remark: <BR>
	 * @param stage
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int updatePathStage(PostStage stage)throws Exception{
		ExamScheduleBean exam = new ExamScheduleBean ();
		exam.setId(stage.getExamId());
		exam.setTitle(stage.getExamTitle());
		exam.setDuration(stage.getDuration());
		exam.setPassScorePercent(stage.getPassScorePercent());
		exam.setPaperId(stage.getPaperId());
        examScheduleDaoMapper.modifyExam(exam);
		return managementLearningMapDaoMapper.updatePathStage(stage);
	}
	/**
	 * Method name: deletePathStage <BR>
	 * Description: 删除阶段 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int deletePathStage(Integer id)throws Exception{
		PostStage stage =managementLearningMapDaoMapper.getStageById(id);
		examScheduleDaoMapper.deleteExam(stage.getExamId());
		managementLearningMapDaoMapper.deletePromotionStateByStageId(id);
		return managementLearningMapDaoMapper.deletePathStage(id);
	}
	
	/**
	 * Method name: getMaxOrderNum <BR>
	 * Description: 取得最大排序字段 <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public Integer getMaxOrderNum(Integer pathId)throws Exception{
		return managementLearningMapDaoMapper.getMaxOrderNum(pathId);
	}
	
	 /**
	  * Method name: upPathStage <BR>
	  * Description: 上移阶段 <BR> 
	  * @param id
	  * @return
	  * @throws Exception
	  */
		@Override
		@Transactional(readOnly = false, rollbackFor = Exception.class)
		public void upPathStage(Integer id) throws Exception {
			PostStage bean = managementLearningMapDaoMapper.getStageById(id);
			Integer orderNum = bean.getOrderNum();
			PostStage upBean = managementLearningMapDaoMapper.getUpStage(bean.getPathId(), orderNum);
			Integer upOrderNum = upBean.getOrderNum();
			bean.setOrderNum(upOrderNum);
			upBean.setOrderNum(orderNum);
			managementLearningMapDaoMapper.updatePathStage(upBean);
			managementLearningMapDaoMapper.updatePathStage(bean);
			
		}
		
		/**
		  * Method name: downPathStage <BR>
		  * Description: 下移阶段 <BR> 
		  * @param id
		  * @return
		  * @throws Exception
		  */
			@Override
			@Transactional(readOnly = false, rollbackFor = Exception.class)
			public void downPathStage(Integer id) throws Exception {
				PostStage bean = managementLearningMapDaoMapper.getStageById(id);
				Integer orderNum = bean.getOrderNum();
				PostStage downBean = managementLearningMapDaoMapper.getDownStage(bean.getPathId(), orderNum);
				Integer downOrderNum = downBean.getOrderNum();
				bean.setOrderNum(downOrderNum);
				downBean.setOrderNum(orderNum);
				managementLearningMapDaoMapper.updatePathStage(downBean);
				managementLearningMapDaoMapper.updatePathStage(bean);
				
			}			 


	/**
	 * Method name: getPathStageById <BR>
	 * Description: 根据id查询阶段 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  LearnPlanStageBean<BR>
	 */
	@Override
	public PostStage getPathStageById(Integer id)throws Exception {
		return managementLearningMapDaoMapper.getStageById(id);
	}
	/**
	 * Method name: deletePathStage <BR>
	 * Description: 取得上一级阶段 <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public PostStage getUpStage(Integer pathId,Integer orderNum)throws Exception{
		return managementLearningMapDaoMapper.getUpStage(pathId,orderNum);
	}
	/**
	 * Method name: getDownStage <BR>
	 * Description: 取得下一级阶段 <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public PostStage getDownStage(Integer pathId,Integer orderNum)throws Exception{
		return managementLearningMapDaoMapper.getDownStage(pathId,orderNum);
	}
	
	
	/**
	 * Method name: getTotalCredits <BR>
	 * Description: 查询课程总学分 <BR>
	 * Remark: <BR>
	 * @param type
     * @param postId
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public Integer getTotalCredits(Integer type,Integer postId
			)throws Exception{
		return managementLearningMapDaoMapper.getTotalCredits(type,postId);
	}
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
	@Override
	public int checkPathName(String name,Integer companyId,Integer subCompanyId,Integer id)throws Exception{
		return managementLearningMapDaoMapper.queryCountByPathName(name,companyId,subCompanyId,id);
	}
	
	/**
	 * Method name: isCanPromotion <BR>
	 * Description: 是否满足晋升条件 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int isCanPromotion(Integer id)throws Exception{
		return managementLearningMapDaoMapper.isCanPromotion(id);
	}
	/**
	 * Method name: agreePromotion <BR>
	 * Description: 同意晋升<BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int agreePromotion(Integer id,Integer status,String reason)throws Exception{
		if(status==4){
			PostPromotionState oldState = managementLearningMapDaoMapper.getPromotionStateById(id);
			PostStage bean = managementLearningMapDaoMapper.getStageById(oldState.getPromotionStageId());
			Integer orderNum = bean.getOrderNum();
			PostStage downBean = managementLearningMapDaoMapper.getDownStage(bean.getPathId(), orderNum);
			if(downBean!=null){
				managementLearningMapDaoMapper.deletePromotionStateByUserId(oldState.getUserId());
				PostPromotionState state=new PostPromotionState();
				state.setUserId(oldState.getUserId());
				state.setPromotionStageId(downBean.getId());
				state.setPromotionStatus(2);
				managementLearningMapDaoMapper.insertPromotionState(state);
				//更改用户岗位
				/**ManageUserBean user = new ManageUserBean();
				user.setId(oldState.getUserId().toString());
				user.setPostId(downBean.getPostId());
				manageUserService.update(user);**/
				integralManageService.handleIntegral(oldState.getUserId(), 7005);
			}else{
				integralManageService.handleIntegral(oldState.getUserId(), 7005);
				integralManageService.handleIntegral(oldState.getUserId(), 7006);
			}
		}
		
		return managementLearningMapDaoMapper.agreePromotion(id,status,reason);
	}
	
	
	/**
	 * Method name: getPostPromotionStateById <BR>
	 * Description: 根据id取得晋升记录 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public PostPromotionState getPostPromotionStateById(Integer id)throws Exception{
		return managementLearningMapDaoMapper.getPromotionStateById(id);
	}
	
	/**
	 * Method name: checkPostPromotionState <BR>
	 * Description: 检查晋升记录是否重复 <BR>
	 * Remark: <BR>
	 * @param stageId
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public int checkPostPromotionState(Integer stageId,Integer userId
			)throws Exception{
		return managementLearningMapDaoMapper.checkPromotionState(stageId, userId);
	}
	
	

	/**
	 * Method name: insertPostPromotionStateByUser <BR>
	 * Description: 根据用户新建 晋升记录<BR>
	 * Remark: <BR>
	 * @param postId
     * @param userId
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insertPostPromotionStateByUser(Integer userId,Integer postId)throws Exception{
		List<Integer> stageList = managementLearningMapDaoMapper.getStageByPostId(postId);
		if(stageList!=null&&stageList.size()>0){
			managementLearningMapDaoMapper.deletePromotionStateByUserId(userId);
			for(Integer stageId:stageList){
				PostStage stage = managementLearningMapDaoMapper.getStageById(stageId);
				//查询出第一阶段，从路径的起点学习
				PostStage firstStage= managementLearningMapDaoMapper.getFirstStage(stage.getPathId());
				//晋升是学习要晋升的岗位包含的课程
				/**
				PostStage afterSatge = managementLearningMapDaoMapper.getDownStage(stage.getPathId(), stage.getOrderNum());
				if(afterSatge!=null){
					PostPromotionState state =new PostPromotionState();
					state.setUserId(userId);
					state.setPromotionStageId(afterSatge.getId());
					state.setPromotionStatus(2);
					managementLearningMapDaoMapper.insertPromotionState(state);
				}
				*/
				//学习本岗位包含的课程
				PostPromotionState state =new PostPromotionState();
				state.setUserId(userId);
				state.setPromotionStageId(firstStage.getId());
				state.setPromotionStatus(2);
				managementLearningMapDaoMapper.insertPromotionState(state);
			
			}
		}
	}
	
	/**
	 * Method name: insertPostPromotionState <BR>
	 * Description: 新建 晋升记录<BR>
	 * Remark: <BR>
	 * @param state
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public Integer insertPostPromotionState(PostPromotionState state)throws Exception{
		return managementLearningMapDaoMapper.insertPromotionState(state);
	}
	
	/**
	 * Method name: updatePostPromotionState <BR>
	 * Description: 修改 晋升记录<BR>
	 * Remark: <BR>
	 * @param state
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public int updatePostPromotionState(PostPromotionState state)throws Exception{
		return managementLearningMapDaoMapper.updatePromotionState(state);
	}
	
	/**
	 * Method name: deletePostPromotionState <BR>
	 * Description: 删除晋升记录 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public int deletePostPromotionState(Integer id)throws Exception{
		return managementLearningMapDaoMapper.deletePromotionState(id);
	}
	
	/**
	 * Method name: examinePostPromotionState <BR>
	 * Description: 审批 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public int examinePostPromotionState( Integer id,Integer status,String reason)throws Exception{
		return managementLearningMapDaoMapper.examinePostPromotionState(id, status, reason);
	}
	
	
	
	/**
	 * Method name: countStudentByStageId <BR>
	 * Description: 查询阶段下学员数目 <BR>
	 * Remark: <BR>
	 * @param postApply
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public int countStudentByStageId(PostApplyVo postApply)throws Exception{
		return managementLearningMapDaoMapper.countStudentByStageId(postApply);
	}
	
	/**
	 * Method name: getUnfinishRecord <BR>
	 * Description:查询晋升未完成的记录<BR>
	 * Remark: <BR>
	 * @param beginId endId
	 * @return
	 * @throws Exception  List<PostApplyVo><BR>
	 */
	@Override
	public List<PostApplyVo> getUnfinishRecord(Integer beginId,Integer endId)throws Exception{
		return managementLearningMapDaoMapper.getUnfinishRecord(beginId,endId);
	}
	/**
	 * Method name: countUnfinishRecord <BR>
	 * Description: 查询晋升未完成的记录数目 <BR>
	 * Remark: <BR>
	 * @param 
	 * @return
	 * @throws Exception  List<PostApplyVo><BR>
	 */
	@Override
	public int countUnfinishRecord()throws Exception{
		return managementLearningMapDaoMapper.countUnfinishRecord();
	}
	
	/**
	 * Method name: getMaxStateId <BR>
	 * Description: 查询晋升记录最大id <BR>
	 * Remark: <BR>
	 * @param 
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getMaxStateId()throws Exception{
		return managementLearningMapDaoMapper.getMaxStateId();
	}
	
	/**
	 * Method name: setPromotionFail <BR>
	 * Description: 设置晋升失败 <BR>
	 * Remark: <BR>
	 * @param 
	 * @return
	 * @throws Exception <BR>
	 */
	@Override
	public int setPromotionFail(Integer id)throws Exception{
		return managementLearningMapDaoMapper.setPromotionFail(id);
	}
	
	@Override
	public Integer addPromotionPath(PostPath path) throws Exception {
		int count = managementLearningMapDaoMapper.queryCountByPathName(path.getName(),path.getCompanyId(),path.getSubCompanyId(),path.getId());
		if(count<1){
			managementLearningMapDaoMapper.insertPath(path);
		}else{
			return null;
		}
		return path.getId();
	}

	@Override
	public void editPromotionPath(PostPath path) throws Exception {
		managementLearningMapDaoMapper.editPromotionPath(path);
	}

	@Override
	public PostPath queryLearningMapByPath(long pathId) throws Exception {
		return managementLearningMapDaoMapper.queryLearningMapByPath(pathId);
	}

	@Override
	public List<PostStage> queryStageByPathId(long pathId) throws Exception {
		return managementLearningMapDaoMapper.queryStageByPathId(pathId);
	}

	@Override
	public List<PostApplyVo> queryStudentByStageId(PostApplyVo postApply)
			throws Exception {
		return managementLearningMapDaoMapper.queryStudentByStageId(postApply);
	}

	@Override
	public List<PostCourseVo> queryCourseByPostId(long postid)
			throws Exception {
		return managementLearningMapDaoMapper.queryCourseByPostId(postid);
	}
}
