package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.TrainArrangeUserBean;

public interface TrainArrangeUserDaoMapper {
    void deleteById(TrainArrangeUserBean bean);

    int insert(TrainArrangeUserBean record);

    TrainArrangeUserBean selectById(Integer id);

    int updateById(TrainArrangeUserBean record);
    
    /**
     * Method name: selectTrainArrangeUserIds <BR>
     * Description: 根据培训安排id查询参训人员 <BR>
     * Remark: <BR>
     * @param arrangeId
     * @return  List<String><BR>
     */
    List<String> selectTrainArrangeUserIds(Map<String, Object> param);

	/**
	 * Method name: deleteTrainArrangeUser <BR>
	 * Description: 删除原安排的参训人员 <BR>
	 * Remark: <BR>
	 * @param arrangeId  void<BR>
	 */
	void deleteTrainArrangeUser(int arrangeId);

	/**
	 * Method name: selectTrainArrangeUserDetail <BR>
	 * Description: 获取培训参训人员 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<TrainArrangeUserBean><BR>
	 */
	List<TrainArrangeUserBean> selectTrainArrangeUserDetail(
			Map<String, Object> param);

	/**
	 * Method name: updateTrainArrangeUserScore <BR>
	 * Description: 修改培训人员的成绩 <BR>
	 * Remark: <BR>
	 * @param arrangeUser  void<BR>
	 */
	void updateTrainArrangeUserScore(TrainArrangeUserBean arrangeUser);
}