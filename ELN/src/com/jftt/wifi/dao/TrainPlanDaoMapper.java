package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.TrainCheckBean;
import com.jftt.wifi.bean.TrainPlanBean;

public interface TrainPlanDaoMapper {
    void deleteById(@Param("id")Integer id);

    void insert(TrainPlanBean record);

    TrainPlanBean selectById(@Param("id")Integer id);

    void updateById(TrainPlanBean record);
    
    int selectTrainPlanListCount(Map<String, Object> param);

	List<TrainPlanBean> selectTrainPlanList(Map<String, Object> param);

	/**
	 * Method name: submitTrainPlan <BR>
	 * Description: 提交培训计划 <BR>
	 * Remark: <BR>
	 * @param plan  void<BR>
	 */
	void submitTrainPlan(TrainPlanBean plan);

	/**
	 * Method name: rejectPlan <BR>
	 * Description: 审批拒绝，更新状态 <BR>
	 * Remark: <BR>
	 * @param trainId  void<BR>
	 */
	void rejectPlan(@Param("id")Integer trainId);

	/**
	 * Method name: passPlan <BR>
	 * Description: 审核通过该计划 <BR>
	 * Remark: <BR>
	 * @param check  void<BR>
	 */
	void passPlan(TrainCheckBean check);
}