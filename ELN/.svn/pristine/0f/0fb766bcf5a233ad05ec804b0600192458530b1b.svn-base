package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import com.jftt.wifi.bean.TrainArrangeScoreBean;

public interface TrainArrangeScoreDaoMapper {
    int deleteById(Integer id);

    int insert(TrainArrangeScoreBean record);

    TrainArrangeScoreBean selectById(Integer id);

    int updateById(TrainArrangeScoreBean record);

	int selectTrainArrangeUserScoreCount(int contentId);

	/**
	 * Method name: selectTrainArrangeUserScoreList <BR>
	 * Description: 查询参训人员的成绩 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<TrainArrangeScoreBean><BR>
	 */
	List<TrainArrangeScoreBean> selectTrainArrangeUserScoreList(Map<String, Object> param);

	void delete(TrainArrangeScoreBean score);
}