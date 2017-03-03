package com.jftt.wifi.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.LearnPlanBean;
import com.jftt.wifi.bean.vo.LearnPlanBeanVo;

/**
 * class LearnPlanDaoMapper <BR>
 * class description: 学习计划dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-7
 * @author zhangbocheng
 * 
 */
public interface LearnPlanDaoMapper {	
	
	/**zhangbocheng start*/
	/**
	 * Method name: deleteLearnPlan <BR>
	 * Description: 根据id删除学习计划 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception
	 */
    int deleteLearnPlan(@Param("id")Integer id) throws Exception;

    /**
	 * Method name: getById <BR>
	 * Description: 根据id查询学习计划 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception
	 */
    LearnPlanBeanVo getById(@Param("id")Integer id) throws Exception;
    
    /**
	 * Method name: checkName <BR>
	 * Description: 检查重名<BR>
	 * Remark: <BR>
	 * @param map
	 * @return
	 * @throws Exception
	 */
    int checkName(Map<String,Object> map) throws Exception;
    
    /**
	 * Method name: insert <BR>
	 * Description: 新建学习计划 <BR>
	 * Remark: <BR>
	 * @param record
	 * @return
	 * @throws Exception
	 */
    int insert(LearnPlanBean record) throws Exception;

    /**
	 * Method name: updateLearnPlan <BR>
	 * Description: 修改学习计划 <BR>
	 * Remark: <BR>
	 * @param record
	 * @return
	 * @throws Exception
	 */
    int updateLearnPlan(LearnPlanBean record) throws Exception;

    
    /**
	  * Method name: selectLearnPlanBeanCountByMap <BR>
	  * Description: 根据条件查询数量 <BR>
	  * @param map
	  * @return
	  * @throws Exception
	  */
    int selectLearnPlanBeanCountByMap(Map<String,Object> map) throws Exception;
    
    /**
	  * Method name: selectLearnPlanBeanCount <BR>
	  * Description: 根据条件查询数量 <BR>
	  * @param vo
	  * @return
	  * @throws Exception
	  */
	int selectLearnPlanBeanCount(LearnPlanBean vo) throws Exception;

	/**
	 * Method name: selectLearnPlanBeanList <BR>
	 * Description: 根据条件查询学习计划 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	List<LearnPlanBean> selectLearnPlanBeanList(LearnPlanBean bean) throws Exception;
	
	/**
	 * Method name: selectLearnPlansByMap <BR>
	 * Description: 根据条件查询学习计划 <BR>
	 * Remark: <BR>
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<LearnPlanBeanVo> selectLearnPlansByMap(Map<String,Object> map) throws Exception;

	/**
	 * Method name: publiceLearnPlan <BR>
	 * Description: 发布计划<BR>
	 * Remark: <BR>
	 * @param planId
	 * @return
	 * @throws Exception
	 */
	int publiceLearnPlan(@Param("id")Integer id) throws Exception;
	
	/**zhangbocheng end*/
}
