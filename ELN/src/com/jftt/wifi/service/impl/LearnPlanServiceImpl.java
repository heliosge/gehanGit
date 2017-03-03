package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.jftt.wifi.bean.LearnPlanAssignmentBean;
import com.jftt.wifi.bean.LearnPlanBean;
import com.jftt.wifi.bean.LearnPlanStageBean;
import com.jftt.wifi.bean.LearnPlanStageCourseRelationBean;
import com.jftt.wifi.bean.ManageGroupStudentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.LearnPlanAssignmentVo;
import com.jftt.wifi.bean.vo.LearnPlanBeanVo;
import com.jftt.wifi.bean.vo.LearnPlanStageCourseRelationVo;
import com.jftt.wifi.bean.vo.ManageGroupBeanVo;
import com.jftt.wifi.dao.LearnPlanAssignmentDaoMapper;
import com.jftt.wifi.dao.LearnPlanDaoMapper;
import com.jftt.wifi.dao.LearnPlanStageCourseRelationDaoMapper;
import com.jftt.wifi.dao.LearnPlanStageDaoMapper;
import com.jftt.wifi.dao.ManageGroupDaoMapper;
import com.jftt.wifi.dao.ManageUserDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.LearnPlanService;
import com.jftt.wifi.service.ManageGroupService;
import com.jftt.wifi.service.ManageUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * class LearnPlanServiceImpl <BR>
 * class description: 学习计划service实现 <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-6
 * @author zhangbocheng
 * 
 */
@Service("LearnPlanService")
public class LearnPlanServiceImpl implements LearnPlanService{
	@Autowired
	private LearnPlanDaoMapper learnPlanDaoMapper;
	
	@Autowired
	private LearnPlanStageDaoMapper learnPlanStageDaoMapper;
	
	@Autowired
	private LearnPlanStageCourseRelationDaoMapper learnPlanStageCourseRelationDaoMapper;
	
	@Autowired
	private LearnPlanAssignmentDaoMapper learnPlanAssignmentDaoMapper;
	
	@Autowired
	private ManageUserService manageUserService;
	
	@Autowired
	private ManageGroupService manageGroupService;
	
	/**
	 * Method name: selectCompanyCount <BR>
	 * Description: 根据id删除 <BR>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public void deleteLearnPlan(Integer id) throws Exception {
		learnPlanDaoMapper.deleteLearnPlan(id);

	}
	
	/**
	 * Method name: selectLearnPlanById <BR>
	 * Description: 根据id查看详情 <BR>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public LearnPlanBeanVo selectLearnPlanById(Integer id) throws Exception {
		
		return learnPlanDaoMapper.getById(id);
		
	}
	
	 /**
	  * Method name: checkLearnPlanName <BR>
	  * Description: 检查重名 <BR>
	  * @param map
	  * @return
	  * @throws Exception
	  */
	@Override
	public int checkLearnPlanName(Map<String,Object> map) throws Exception {
		return learnPlanDaoMapper.checkName(map);
	}

	 /**
	  * Method name: insert <BR>
	  * Description: 插入一条记录 <BR>
	  * @param record
	  * @return
	  * @throws Exception
	  */

	@Override
	public Integer insert(LearnPlanBean record) throws Exception {
		learnPlanDaoMapper.insert(record);
		return record.getId();
	}

	 /**
	  * Method name: updateLearnPlan <BR>
	  * Description: 修改 <BR> 
	  * @param record
	  * @return
	  * @throws Exception
	  */

	@Override
	public void updateLearnPlan(LearnPlanBean record) throws Exception {
		learnPlanDaoMapper.updateLearnPlan(record);
	}

	 /**
	  * Method name: selectLearnPlanBeanCount <BR>
	  * Description: 根据条件查询数量 <BR>
	  * @param vo
	  * @return
	  * @throws Exception
	  */
	@Override
	public int selectLearnPlanBeanCount(LearnPlanBean vo) throws Exception {
		return learnPlanDaoMapper.selectLearnPlanBeanCount(vo);
	}

	 /**
     * Method name: selectLearnPlanBeanList <BR>
	  * Description: 根据条件查询出学习计划 <BR> 
     * @param vo
     * @return
     * @throws Exception
     */
	@Override
	public List<LearnPlanBean> selectLearnPlanBeanList(LearnPlanBean vo)
			throws Exception {
		return learnPlanDaoMapper.selectLearnPlanBeanList(vo);
	}
	
	 /**
     * Method name: selectLearnPlansByMap <BR>
	  * Description: 根据条件查询出学习计划 <BR> 
     * @param map
     * @return
     * @throws Exception
     */
	@Override
	public List<LearnPlanBeanVo> selectLearnPlansByMap(Map<String,Object> map)
			throws Exception {
		return learnPlanDaoMapper.selectLearnPlansByMap(map);
	}

	 /**
	  * Method name: publiceLearnPlan <BR>
	  * Description: 发布学习计划 <BR> 
	  * @param id
	  * @return
	  * @throws Exception
	  */
	@Override
	public boolean publiceLearnPlan(Integer id) throws Exception {
		int size = learnPlanStageDaoMapper.getLearnPlanStagesCount(id, 1);
		if(size>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("planId", id);
			int studentCount = learnPlanAssignmentDaoMapper.getCountByMap(map);
			if(studentCount>0){
				learnPlanDaoMapper.publiceLearnPlan(id);
				return true;
			}else
			return true;
		}else{
			return false;
		}
		
	}

	  /**
		  * Method name: selectLearnPlanBeanCountByMap <BR>
		  * Description: 根据条件查询数量 <BR>
		  * @param map
		  * @return
		  * @throws Exception
		  */	
	@Override
	public int selectLearnPlanBeanCountByMap(Map<String,Object> map) throws Exception {
		return learnPlanDaoMapper.selectLearnPlanBeanCountByMap(map);
	}

	
	
	 /**
	  * Method name: checkLearnPlanStageName <BR>
	  * Description: 检查计划阶段重名 <BR>
	  * @param map
	  * @return
	  * @throws Exception
	  */
	@Override
	 public int checkLearnPlanStageName(Map<String,Object> map) throws Exception{
		learnPlanStageDaoMapper.checkName(map);
		 return 0;
	 }	

	 /**
	  * Method name: insertLearnPlanStage <BR>
	  * Description: 新建计划阶段 <BR> 
	  * @param stage
	  * @return
	  * @throws Exception
	  */
	@Override
	public Integer insertLearnPlanStage(LearnPlanStageBean stage) throws Exception {
		Integer orderNum=learnPlanStageDaoMapper.getMaxOrderNum(stage.getPlanId());
		if(orderNum!=null){
			orderNum+=1;
		}else{
			orderNum=1;
		}
		stage.setOrderNum(orderNum);
		learnPlanStageDaoMapper.insert(stage);

		return stage.getId();
	}
	 
	 /**
	  * Method name: updateLearnPlanStage <BR>
	  * Description: 修改计划阶段 <BR> 
	  * @param stage
	  * @return
	  * @throws Exception
	  */

	@Override
	public void updateLearnPlanStage(LearnPlanStageBean stage) throws Exception {
		learnPlanStageDaoMapper.update(stage);
		
	}
	 
	 /**
	  * Method name: deleteLearnPlanStage <BR>
	  * Description: 删除学习计划阶段 <BR> 
	  * @param id
	  * @return
	  * @throws Exception
	  */
		@Override
		public void deleteLearnPlanStage(Integer id) throws Exception {
			learnPlanStageDaoMapper.deleteById(id);
			learnPlanStageCourseRelationDaoMapper.deleteByStageId(id);
			
		}
		
		 /**
		  * Method name: upLearnPlanStage <BR>
		  * Description: 上移学习计划阶段 <BR> 
		  * @param id
		  * @return
		  * @throws Exception
		  */
			@Override
			@Transactional(readOnly = false, rollbackFor = Exception.class)
			public void upLearnPlanStage(Integer id) throws Exception {
				LearnPlanStageBean bean = learnPlanStageDaoMapper.getById(id);
				Integer orderNum = bean.getOrderNum();
				LearnPlanStageBean upBean = learnPlanStageDaoMapper.getUpStage(bean.getPlanId(), orderNum);
				Integer upOrderNum = upBean.getOrderNum();
				bean.setOrderNum(upOrderNum);
				upBean.setOrderNum(orderNum);
				learnPlanStageDaoMapper.update(upBean);
				learnPlanStageDaoMapper.update(bean);
				
			}
			
			/**
			  * Method name: downLearnPlanStage <BR>
			  * Description: 下移学习计划阶段 <BR> 
			  * @param id
			  * @return
			  * @throws Exception
			  */
				@Override
				@Transactional(readOnly = false, rollbackFor = Exception.class)
				public void downLearnPlanStage(Integer id) throws Exception {
					LearnPlanStageBean bean = learnPlanStageDaoMapper.getById(id);
					Integer orderNum = bean.getOrderNum();
					LearnPlanStageBean downBean = learnPlanStageDaoMapper.getDownStage(bean.getPlanId(), orderNum);
					Integer downOrderNum = downBean.getOrderNum();
					bean.setOrderNum(downOrderNum);
					downBean.setOrderNum(orderNum);
					learnPlanStageDaoMapper.update(downBean);
					learnPlanStageDaoMapper.update(bean);
					
				}			 
	 

		/**
		 * Method name: getById <BR>
		 * Description: 根据id查询学习计划阶段 <BR>
		 * Remark: <BR>
		 * @param id
		 * @return
		 * @throws Exception  LearnPlanStageBean<BR>
		 */
		@Override
		public LearnPlanStageBean getLearnPlanStageById(Integer id)
				throws Exception {
			return learnPlanStageDaoMapper.getById(id);
		}
	 
	 /**
		 * Method name: getLearnPlanStages <BR>
		 * Description: 查询出学习计划的所有阶段 <BR>
		 * Remark: <BR>
		 * @param learnPlanId
		 * @return
		 * @throws Exception <BR>
		 */
		@Override
		public List<LearnPlanStageBean> getLearnPlanStages(Integer learnPlanId)
				throws Exception {

			
			return learnPlanStageDaoMapper.getLearnPlanStageList(learnPlanId);
		}
		 /**
		 * Method name: getLearnPlanStageCourseList <BR>
		 * Description: 查询计划阶段下所有课程 <BR>
		 * Remark: <BR>
		 * @param stageId
		 * @return
		 * @throws DataBaseException  List<LearnPlanStageVo><BR>
		 */
		@Override
   public List<LearnPlanStageCourseRelationVo> getLearnPlanStageCourseList(Integer stageId) throws Exception{
			return learnPlanStageCourseRelationDaoMapper.getCourseListByStageId(stageId);
   } 
		
	  /**
	   	 * Method name: saveLearnPlanStageCourseList <BR>
		 * Description: 保存计划阶段下所有课程 <BR>
	     * @param list stageId
	     * @throws Exception
	     */
		@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void saveLearnPlanStageCourseList(List<LearnPlanStageCourseRelationVo> list,Integer stageId) throws Exception{
		for(LearnPlanStageCourseRelationVo vo:list){
			vo.setStageId(stageId);
			LearnPlanStageCourseRelationBean bean=vo.vo2Bean();
			if(vo.getId()!=null){
				learnPlanStageCourseRelationDaoMapper.updateById(bean);
			}else{
				Integer count=learnPlanStageCourseRelationDaoMapper.getCountByBean(bean);
				if(count>0){
					learnPlanStageCourseRelationDaoMapper.update(bean);
				}else{
					learnPlanStageCourseRelationDaoMapper.insert(bean);
				}
				
			}
			
		}
	}
	    
  /**
	* Method name: deleteLearnPlanStageCourse <BR>
	* Description: 删除学习计划阶段下课程 <BR> 
	* @param id
	* @return
	* @throws Exception
	*/
	@Override
	public void deleteLearnPlanStageCourse(Integer id) throws Exception {
		learnPlanStageCourseRelationDaoMapper.deleteById(id);
		}
	  /**
		* Method name: disableLearnPlanStageCourse <BR>
		* Description: 禁止或允许学习阶段下课程 <BR> 
	    * @param id
		* @return
		* @throws Exception
		*/
		@Override
		public void disableLearnPlanStageCourse(Integer id) throws Exception {
			LearnPlanStageCourseRelationBean bean= learnPlanStageCourseRelationDaoMapper.getById(id);
			if(bean!=null){
				if(bean.getIsDisabled()==0){
					bean.setIsDisabled(1);
				}else{
					bean.setIsDisabled(0);
				}
			}
			learnPlanStageCourseRelationDaoMapper.update(bean);
					
		}
		
		  /**
		    * Method name: getLearnPlanAssignmentCount <BR>
		    * Description: 查询出学习计划的所有人员数目 <BR>
			* Remark: <BR>
			* @param planId userName
			* @return
			* @throws Exception  <BR>
			*/
		@Override
		public Integer getLearnPlanAssignmentCount(
				Integer planId, String userIds) throws Exception {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("planId", planId);
			map.put("userIds", userIds);
			
			return learnPlanAssignmentDaoMapper.getCountByMap(map);
		}
	  /**
	    * Method name: getLearnPlanAssignment <BR>
	    * Description: 查询出学习计划的所有人员 <BR>
		* Remark: <BR>
		* @param planId
		* @return
		* @throws Exception  <BR>
		*/
	@Override
	public List<LearnPlanAssignmentVo> getLearnPlanAssignment(
			Integer planId, String userIds,Integer page,Integer PageSize) throws Exception {
		
		return learnPlanAssignmentDaoMapper.getLearnPlanAssignmentListByPlanId(planId,userIds,page,PageSize);
	}

	
   /**
     * Method name: addLearnPlanUser <BR>
	 * Description: 从人员库中添加计划人员 <BR>
	 * Remark: <BR>
     * @param planId
     * @param ids
     * @throws Exception
     */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void addLearnPlanUser(Integer planId, String[] ids)
			throws Exception {
		
		if(ids!=null){
			for(String id:ids){
				LearnPlanAssignmentBean assignment =new LearnPlanAssignmentBean();
				assignment.setPlanId(planId);
				assignment.setUserId(Integer.parseInt(id));
				Integer count=learnPlanAssignmentDaoMapper.getCountByBean(assignment);
				if(count>0){
					continue;
				}
				learnPlanAssignmentDaoMapper.insertByUserId(assignment);	
			}
		}
		
	}

   /**
     * Method name: addLearnPlanUser <BR>
	 * Description: 按部门添加计划人员 <BR>
	 * Remark: <BR>
     * @param planId
     * @param ids
     * @throws Exception
     */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void addLearnPlanUserByDep(Integer planId, String[] ids)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if(ids!=null&&ids.length>0){
			List<Integer> deptList = new ArrayList<Integer>();
			for(String deptId : ids){
				deptList.add(Integer.parseInt(deptId));
			}
			    map.put("deptId",deptList);

				List<ManageUserBean> userList = manageUserService.findUserByListCondition(map);
				if(userList!=null&&userList.size()>0){
					for(ManageUserBean user:userList){
						LearnPlanAssignmentBean assignment =new LearnPlanAssignmentBean();
						assignment.setPlanId(planId);
						assignment.setUserId(Integer.parseInt(user.getId()));
						Integer count=learnPlanAssignmentDaoMapper.getCountByBean(assignment);
						if(count>0){
							continue;
						}
						learnPlanAssignmentDaoMapper.insertByUserId(assignment);	
					}
				}
			
		}
		
	}

    /**
     * Method name: addLearnPlanUserByPost <BR>
     * Description: 按岗位添加计划人员 <BR>
     * Remark: <BR>
     * @param planId
     * @param ids
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void addLearnPlanUserByPost(Integer planId, String[] ids)
            throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        if(ids!=null&&ids.length>0){
            List<Integer> postList = new ArrayList<Integer>();
            for(String postId : ids){
                postList.add(Integer.parseInt(postId));
            }
            map.put("postId",postList);

            List<ManageUserBean> userList = manageUserService.findUserByListCondition(map);
            if(userList!=null&&userList.size()>0){
                for(ManageUserBean user:userList){
                    LearnPlanAssignmentBean assignment =new LearnPlanAssignmentBean();
                    assignment.setPlanId(planId);
                    assignment.setUserId(Integer.parseInt(user.getId()));
                    Integer count=learnPlanAssignmentDaoMapper.getCountByBean(assignment);
                    if(count>0){
                        continue;
                    }
                    learnPlanAssignmentDaoMapper.insertByUserId(assignment);
                }
            }

        }

    }
	
   /**
     * Method name: addLearnPlanUser <BR>
	 * Description: 从群组添加计划人员 <BR>
	 * Remark: <BR>
     * @param planId
     * @param ids
     * @throws Exception
     */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void addLearnPlanUserByGroup(Integer planId, String[] ids)
			throws Exception {
		
		if(ids!=null){
			for(String id:ids){
				ManageGroupBeanVo group = new ManageGroupBeanVo();
				group.setId(Integer.parseInt(id));
				List<ManageGroupStudentBean> studentList=	manageGroupService.selectGroupStudentListByGroupId(group);
				if(studentList!=null&&studentList.size()>0){
					for(ManageGroupStudentBean user:studentList){
						LearnPlanAssignmentBean assignment =new LearnPlanAssignmentBean();
						assignment.setPlanId(planId);
						assignment.setUserId(user.getStudentId());
						Integer count=learnPlanAssignmentDaoMapper.getCountByBean(assignment);
						if(count>0){
							continue;
						}
						learnPlanAssignmentDaoMapper.insertByUserId(assignment);	
					}
				}
			}
		}
		
	}

   /**
     * Method name: delLearnPlanUser <BR>
	 * Description: 删除计划人员 <BR>
	 * Remark: <BR>
     * @param ids
     * @throws Exception
     */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delLearnPlanUser(String[] ids)
			throws Exception {
		if(ids!=null){
			for(String id:ids){
				learnPlanAssignmentDaoMapper.deleteById(Integer.parseInt(id));
			}
		}
		
	}

		
	 /**
     * Method name: getLearnPlanStudentProcess <BR>
	  * Description: 根据条件查询出学习计划的人员学习进度 <BR> 
     * @param 
     * @return
     * @throws Exception
     */
	@Override
	 public List<LearnPlanAssignmentVo> getLearnPlanStudentProcess(String userIds,Integer planId,Integer stageId,Integer status,Integer page,Integer pageSize) throws Exception{
//		Map<String,Object> map = learnPlanStageDaoMapper.getOrderNum(stageId);
//		Integer orderNum=0;
//		Integer maxNum;
//		Integer isLast =null;
//		if(map!=null){
//			orderNum=(Integer)map.get("orderNum");
//			maxNum=(Integer)map.get("maxNum");
//			if(orderNum.equals(maxNum)){
//				isLast=1;
//			}
//		}
        return learnPlanAssignmentDaoMapper.getLearnPlanStudentProcess(userIds, planId, stageId,status,page,pageSize);
	}
	
	/**
     * Method name: getLearnPlanStudentCount <BR>
	  * Description: 根据条件查询出学习计划的人员数目 <BR> 
     * @param 
     * @return
     * @throws Exception
     */
	public int getLearnPlanStudentCount(String userIds,Integer planId,Integer stageId,Integer status) throws Exception{
//		Map<String,Object> map = learnPlanStageDaoMapper.getOrderNum(stageId);
//		Integer orderNum=0;
//		Integer maxNum;
//		if(map!=null){
//			orderNum=(Integer)map.get("orderNum");
//			maxNum=(Integer)map.get("maxNum");
//			if(orderNum==maxNum){
//				orderNum--;
//			}
//		}
        return learnPlanAssignmentDaoMapper.getLearnPlanStudentCount(userIds, planId, stageId, status);
	}

}
