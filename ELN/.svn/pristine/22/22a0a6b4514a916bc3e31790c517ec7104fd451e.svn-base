package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.LearnPlanStageCourseRelationBean;
import com.jftt.wifi.bean.vo.LearnPlanStageCourseRelationVo;
/**
 * class LearnPlanStageCourseRelationDaoMapper <BR>
 * class description: 学习计划阶段和课程关联dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-10
 * @author zhangbocheng
 * 
 */
public interface LearnPlanStageCourseRelationDaoMapper {

	/**
	 * Method name: getById <BR>
	 * Description: 根据id得到bean <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	LearnPlanStageCourseRelationBean getById(Integer id)throws Exception;
	
	/**zhangbocheng start*/
	/**
	 * Method name: getCountByMap <BR>
	 * Description: 根据条件查询出数目<BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int getCountByMap(Map<String,Object> map) throws Exception;
	
	/**
	 * Method name: getCountByBean <BR>
	 * Description: 查询是否重复 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int getCountByBean(LearnPlanStageCourseRelationBean bean) throws Exception;
	/**
	 * Method name: getCourseListByStageId <BR>
	 * Description: 查询出阶段下所有课程 <BR>
	 * Remark: <BR>
	 * @param stageId
	 * @return
	 * @throws Exception
	 */
	public List<LearnPlanStageCourseRelationVo> getCourseListByStageId(@Param("stageId")Integer stageId) throws Exception;

	/**
	 * Method name: insert <BR>
	 * Description: 插入一条 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int insert (LearnPlanStageCourseRelationBean bean)throws Exception;
	
	/**
	 * Method name: update <BR>
	 * Description: 修改<BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	int update(LearnPlanStageCourseRelationBean bean) throws Exception;
	
	/**
	 * Method name: update <BR>
	 * Description: 修改<BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	int updateById(LearnPlanStageCourseRelationBean bean) throws Exception;
	
	/**
	 * Method name: delete <BR>
	 * Description: 根据id删除<BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteById(@Param("id")Integer id)throws Exception;
	
	/**
	 * Method name: deleteByStageId <BR>
	 * Description: 根据计划阶段id删除计划阶段下课程 <BR>
	 * Remark: <BR>
	 * @param stageId
	 * @return
	 * @throws Exception
	 */
	int deleteByStageId(@Param("stageId")Integer stageId)throws Exception;
	
	/**zhangbocheng end*/
}
