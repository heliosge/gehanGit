package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import com.jftt.wifi.bean.CanJoinTrainArrangeBean;
import com.jftt.wifi.bean.TrainArrangeBean;
import com.jftt.wifi.bean.TrainCheckBean;
import com.jftt.wifi.bean.TrainQuestionnaireBean;
import com.jftt.wifi.bean.vo.TrainArrangeVo;

public interface TrainArrangeDaoMapper {
    int deleteById(Integer id);

    int insert(TrainArrangeBean arrange);

    /**
     * Method name: selectById <BR>
     * Description: 根据id查询 <BR>
     * Remark: <BR>
     * @param id
     * @return  TrainArrangeBean<BR>
     */
    TrainArrangeBean selectById(Integer id);

    int updateById(TrainArrangeBean arrange);

	/**
	 * Method name: selectTrainArrangeListCount <BR>
	 * Description: 查询培训安排数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 */
	int selectTrainArrangeCount(TrainArrangeVo vo);

	/**
	 * Method name: selectTrainArrangeList <BR>
	 * Description: 查询培训安排 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<TrainArrangeBean><BR>
	 */
	List<TrainArrangeBean> selectTrainArrangeList(TrainArrangeVo vo);

	/**
	 * Method name: submitArrange <BR>
	 * Description: 提交培训安排 <BR>
	 * Remark: <BR>
	 * @param arrange  void<BR>
	 */
	void submitArrange(TrainArrangeBean arrange);

	/**
	 * Method name: rejectArrange <BR>
	 * Description: 审批拒绝该安排 <BR>
	 * Remark: <BR>
	 * @param trainId  void<BR>
	 */
	void rejectArrange(Integer trainId);

	/**
	 * Method name: passArrange <BR>
	 * Description: 审批通过该安排 <BR>
	 * Remark: <BR>
	 * @param checkBean  void<BR>
	 */
	void passArrange(TrainCheckBean checkBean);

	/**
	 * Method name: updateArrangeStauts <BR>
	 * Description: 修改培训安排的状态 <BR>
	 * Remark: <BR>
	 * @param arrange  void<BR>
	 */
	void updateArrangeStauts(TrainArrangeBean arrange);

	/**
	 * Method name: selectCanJoinTrainArrangeCount <BR>
	 * Description: 查询可报名培训数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 */
	int selectCanJoinTrainArrangeCount(TrainArrangeVo vo);

	/**
	 * Method name: selectCanJoinTrainArrangeList <BR>
	 * Description: 查询可报名培训列表 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<TrainArrangeBean><BR>
	 */
	List<CanJoinTrainArrangeBean> selectCanJoinTrainArrangeList(TrainArrangeVo vo);

	/**
	 * Method name: selectJoinedTrainArrangeCount <BR>
	 * Description: 已报名培训 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 */
	int selectJoinedTrainArrangeCount(TrainArrangeVo vo);

	/**
	 * Method name: selectJoinedTrainArrangeList <BR>
	 * Description: 已报名培训 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<TrainArrangeBean><BR>
	 */
	List<TrainArrangeBean> selectJoinedTrainArrangeList(TrainArrangeVo vo);
	
	/**
	 * Method name: selectAfterClassTestByArrangeId <BR>
	 * Description: 根据培训id获取所有问卷 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<TrainQuestionnaireBean><BR>
	 */
	List<TrainQuestionnaireBean> selectAfterClassTestByArrangeId(
			Map<String, Object> param);

	/**
	 * Method name: updateTrainArrangeCloseStatus <BR>
	 * Description: 开启、关闭报名状态 <BR>
	 * Remark: <BR>
	 * @param arrange  void<BR>
	 */
	void updateTrainArrangeCloseStatus(TrainArrangeBean arrange);

	/**
	 * Method name: releaseTrainArrangeScore <BR>
	 * Description: 发布成绩 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void releaseTrainArrangeScore(int id);
}