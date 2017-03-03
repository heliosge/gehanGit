package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.LearnPlanAssignmentBean;
import com.jftt.wifi.bean.LearnPlanBean;
import com.jftt.wifi.bean.LearnPlanStageBean;
import com.jftt.wifi.bean.vo.LearnPlanAssignmentVo;
import com.jftt.wifi.bean.vo.LearnPlanBeanVo;
import com.jftt.wifi.bean.vo.LearnPlanStageCourseRelationVo;
import com.jftt.wifi.bean.vo.LearnPlanStageVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class LearnPlanService <BR>
 * class description: 学习计划service <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-6
 * @author zhangbocheng
 * 
 */
public interface LearnPlanService {

	/**
	 * Method name: selectCompanyCount <BR>
	 * Description: 根据id删除 <BR>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	 void deleteLearnPlan(Integer id) throws Exception;

	 /**
		 * Method name: selectLearnPlanById <BR>
		 * Description: 根据id查看详情 <BR>
		 * @param id
		 * @return
		 * @throws Exception
		 */
	 LearnPlanBeanVo selectLearnPlanById(Integer id) throws Exception;
	 
	 
	 /**
	  * Method name: checkLearnPlanName <BR>
	  * Description: 检查重名 <BR>
	  * @param map
	  * @return
	  * @throws Exception
	  */
	 int checkLearnPlanName(Map<String,Object> map) throws Exception;
	 
	 /**
	  * Method name: insert <BR>
	  * Description: 插入一条记录 <BR>
	  * @param record
	  * @return
	  * @throws Exception
	  */
	 Integer insert(LearnPlanBean record) throws Exception;

	 /**
	  * Method name: updateLearnPlan <BR>
	  * Description: 修改 <BR> 
	  * @param record
	  * @return
	  * @throws Exception
	  */
	 void updateLearnPlan(LearnPlanBean record) throws Exception;

	 /**
	  * Method name: selectLearnPlanBeanCount <BR>
	  * Description: 根据条件查询数量 <BR>
	  * @param vo
	  * @return
	  * @throws Exception
	  */
     int selectLearnPlanBeanCount(LearnPlanBean vo) throws Exception;
     
     /**
	  * Method name: selectLearnPlanBeanCountByMap <BR>
	  * Description: 根据条件查询数量 <BR>
	  * @param map
	  * @return
	  * @throws Exception
	  */
     int selectLearnPlanBeanCountByMap(Map<String,Object> map) throws Exception;

     /**
      * Method name: selectLearnPlanBeanList <BR>
	  * Description: 根据条件查询出学习计划 <BR> 
      * @param vo
      * @return
      * @throws Exception
      */
	 List<LearnPlanBean> selectLearnPlanBeanList(LearnPlanBean vo) throws Exception;
	 
	 /**
      * Method name: selectLearnPlansByMap <BR>
	  * Description: 根据条件查询出学习计划 <BR> 
      * @param map
      * @return
      * @throws Exception
      */
	 List<LearnPlanBeanVo> selectLearnPlansByMap(Map<String,Object> map) throws Exception;

	 /**
	  * Method name: publiceLearnPlan <BR>
	  * Description: 发布学习计划 <BR> 
	  * @param id
	  * @return
	  * @throws Exception
	  */
	 boolean publiceLearnPlan(Integer id) throws Exception;
	 
	 /**
	  * Method name: checkLearnPlanName <BR>
	  * Description: 检查计划阶段重名 <BR>
	  * @param map
	  * @return
	  * @throws Exception
	  */
	 int checkLearnPlanStageName(Map<String,Object> map) throws Exception;
	 
	 /**
	  * Method name: insertLearnPlanStage <BR>
	  * Description: 新建计划阶段 <BR> 
	  * @param stage
	  * @return
	  * @throws Exception
	  */
	 Integer insertLearnPlanStage(LearnPlanStageBean stage) throws Exception;
	 
	 /**
	  * Method name: updateLearnPlanStage <BR>
	  * Description: 修改计划阶段 <BR> 
	  * @param stage
	  * @return
	  * @throws Exception
	  */
	 void updateLearnPlanStage(LearnPlanStageBean stage) throws Exception;
	 
	 /**
	  * Method name: deleteLearnPlanStage <BR>
	  * Description: 删除学习计划阶段 <BR> 
	  * @param id
	  * @return
	  * @throws Exception
	  */
	 void deleteLearnPlanStage(Integer id) throws Exception;
	 
	 /**
	  * Method name: upLearnPlanStage <BR>
	  * Description: 上移学习计划阶段 <BR> 
	  * @param id
	  * @return
	  * @throws Exception
	  */
	 void upLearnPlanStage(Integer id) throws Exception;
	 /**
	  * Method name: downLearnPlanStage <BR>
	  * Description: 下移学习计划阶段 <BR> 
	  * @param id
	  * @return
	  * @throws Exception
	  */
	 void downLearnPlanStage(Integer id) throws Exception;
	 

		/**
		 * Method name: getById <BR>
		 * Description: 根据id查询学习计划阶段 <BR>
		 * Remark: <BR>
		 * @param id
		 * @return
		 * @throws Exception  LearnPlanStageBean<BR>
		 */
		 LearnPlanStageBean getLearnPlanStageById(Integer id) throws Exception;
	 
	 /**
		 * Method name: getLearnPlanStages <BR>
		 * Description: 查询出学习计划的所有阶段 <BR>
		 * Remark: <BR>
		 * @param learnPlanId
		 * @return
		 * @throws DataBaseException  List<LearnPlanStageVo><BR>
		 */
     List<LearnPlanStageBean> getLearnPlanStages(Integer learnPlanId) throws Exception;
     /**
		 * Method name: getLearnPlanStageCourseList <BR>
		 * Description: 查询计划阶段下所有课程 <BR>
		 * Remark: <BR>
		 * @param stageId
		 * @return
		 * @throws DataBaseException  List<LearnPlanStageVo><BR>
		 */
    List<LearnPlanStageCourseRelationVo> getLearnPlanStageCourseList(Integer stageId) throws Exception;    
     
    /**
   	 * Method name: saveLearnPlanStageCourseList <BR>
	 * Description: 保存计划阶段下所有课程 <BR>
     * @param list stageId
     * @throws Exception
     */
    void saveLearnPlanStageCourseList(List<LearnPlanStageCourseRelationVo> list,Integer stageId) throws Exception;
    /**
	  * Method name: deleteLearnPlanStageCourse <BR>
	  * Description: 删除学习计划阶段下课程 <BR> 
	  * @param id
	  * @return
	  * @throws Exception
	  */
	 void deleteLearnPlanStageCourse(Integer id) throws Exception;
	 /**
	  * Method name: disableLearnPlanStageCourse <BR>
	  * Description: 禁止或允许学习阶段下课程 <BR> 
	  * @param id
	  * @return
	  * @throws Exception
	  */
	 void disableLearnPlanStageCourse(Integer id) throws Exception;
 
	 /**
	    * Method name: getLearnPlanAssignmentCount <BR>
	    * Description: 查询出学习计划的所有人员数目 <BR>
		* Remark: <BR>
		* @param planId userName
		* @return
		* @throws Exception  <BR>
		*/
	 Integer getLearnPlanAssignmentCount(
			Integer planId,String userIds) throws Exception;
     /**
		 * Method name: getLearnPlanAssignment <BR>
		 * Description: 查询出学习计划的所有人员 <BR>
		 * Remark: <BR>
		 * @param learnPlanId
		 * @return
		 * @throws Exception  List<LearnPlanStageVo><BR>
		 */
     
  List<LearnPlanAssignmentVo> getLearnPlanAssignment(Integer learnPlanId,String userIds,Integer page,Integer PageSize) throws Exception;
     /**
      * Method name: addLearnPlanUser <BR>
	  * Description: 从人员库中添加计划人员 <BR>
	  * Remark: <BR>
      * @param learnPlanId
      * @param ids
      * @throws Exception
      */
     void addLearnPlanUser(Integer learnPlanId ,String[] ids ) throws Exception;
     /**
      * Method name: addLearnPlanUser <BR>
	  * Description: 按部门添加计划人员 <BR>
	  * Remark: <BR>
      * @param learnPlanId
      * @param ids
      * @throws Exception
      */
     void addLearnPlanUserByDep(Integer learnPlanId ,String[] ids ) throws Exception;
    /**
     * Method name: addLearnPlanUserByPost <BR>
     * Description: 从岗位添加计划人员 <BR>
     * Remark: <BR>
     * @param learnPlanId
     * @param ids
     * @throws Exception
     */
    void addLearnPlanUserByPost(Integer learnPlanId ,String[] ids ) throws Exception;
     /**
      * Method name: addLearnPlanUser <BR>
	  * Description: 从群组添加计划人员 <BR>
	  * Remark: <BR>
      * @param learnPlanId
      * @param ids
      * @throws Exception
      */
     void addLearnPlanUserByGroup(Integer learnPlanId ,String[] ids ) throws Exception;
     /**
      * Method name: delLearnPlanUser <BR>
	  * Description: 删除计划人员 <BR>
	  * Remark: <BR>
      * @param ids
      * @throws Exception
      */
     void delLearnPlanUser(String[] ids ) throws Exception;
     
     /**
      * Method name: getLearnPlanStudentCount <BR>
	  * Description: 根据条件查询出学习计划的人员数目 <BR> 
      * @param 
      * @return
      * @throws Exception
      */
	 int getLearnPlanStudentCount(String userIds,Integer planId,Integer stageId,Integer status) throws Exception;
	 /**
      * Method name: getLearnPlanStudentProcess <BR>
	  * Description: 根据条件查询出学习计划的人员学习进度 <BR> 
      * @param 
      * @return
      * @throws Exception
      */
	 List<LearnPlanAssignmentVo> getLearnPlanStudentProcess(String userIds,Integer planId,Integer stageId,Integer status,Integer page,Integer pageSize) throws Exception;
}
