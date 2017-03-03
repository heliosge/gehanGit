package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.TrainCheckBean;
import com.jftt.wifi.bean.vo.TrainVo;

public interface TrainCheckDaoMapper {
    int deleteById(Integer id);

    int insert(TrainCheckBean record);

    TrainCheckBean selectById(Integer id);

    int updateById(TrainCheckBean record);

	/**
	 * Method name: deleteByMap <BR>
	 * Description: 根据条件删除审批流程 <BR>
	 * Remark: <BR>
	 * @param param1  void<BR>
	 */
	void deleteByMap(Map<String, Object> param1);

	/**
	 * Method name: selectTrainPlanCheckListCount <BR>
	 * Description: 查询【我】审批的计划数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 */
	int selectTrainPlanCheckCount(TrainVo vo);

	/**
	 * Method name: selectTrainPlanCheckList <BR>
	 * Description: 查询【我】审批的计划 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<TrainCheckBean><BR>
	 */
	List<TrainCheckBean> selectTrainPlanCheckList(TrainVo vo);

	/**
	 * Method name: selectTrainPlanById <BR>
	 * Description: 根据id查询计划审批 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  TrainCheckBean<BR>
	 */
	TrainCheckBean selectTrainCheckById(@Param("id")int id);

	/**
	 * Method name: selectTrainCheckByVo <BR>
	 * Description: 根据培训id获取所有审批流程 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<TrainCheckBean><BR>
	 */
	List<TrainCheckBean> selectTrainCheckByVo(TrainVo vo);

	/**
	 * Method name: selectTrainArrangeCheckCount <BR>
	 * Description: 查询【我】审批的安排数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 */
	int selectTrainArrangeCheckCount(TrainVo vo);

	/**
	 * Method name: selectTrainArrangeCheckList <BR>
	 * Description: 查询【我】审批的安排 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<TrainCheckBean><BR>
	 */
	List<TrainCheckBean> selectTrainArrangeCheckList(TrainVo vo);

	/**
	 * Method name: updateStatusByTrainId <BR>
	 * Description: updateStatusByTrainId <BR>
	 * Remark: <BR>
	 * @param check  void<BR>
	 */
	void updateStatusByTrainId(TrainCheckBean check);
}