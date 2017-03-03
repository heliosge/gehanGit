/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: TrainService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月18日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.CanJoinTrainArrangeBean;
import com.jftt.wifi.bean.CourseStudyRecordBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.TrainArrangeBean;
import com.jftt.wifi.bean.TrainArrangeContentBean;
import com.jftt.wifi.bean.TrainArrangeOpenCrowdBean;
import com.jftt.wifi.bean.TrainArrangeScoreBean;
import com.jftt.wifi.bean.TrainArrangeUserBean;
import com.jftt.wifi.bean.TrainCheckBean;
import com.jftt.wifi.bean.TrainConfigBean;
import com.jftt.wifi.bean.TrainPlanBean;
import com.jftt.wifi.bean.TrainQuestionnaireBean;
import com.jftt.wifi.bean.questionnaire.InvestigationAssignBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireQuestionBean;
import com.jftt.wifi.bean.vo.TrainArrangeVo;
import com.jftt.wifi.bean.vo.TrainVo;

/**
 * class name:TrainService <BR>
 * class description: 培训管理 <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月18日
 * @author JFTT)Lu Yunlong
 */
public interface TrainService {

	/**
	 * Method name: selectTrainPlanListCount <BR>
	 * Description: 培训计划数量 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  int<BR>
	 */
	int selectTrainPlanListCount(Map<String, Object> param) throws Exception;

	/**
	 * Method name: selectTrainPlanList <BR>
	 * Description: 培训计划结果 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<TrainPlanBean><BR>
	 */
	List<TrainPlanBean> selectTrainPlanList(Map<String, Object> param) throws Exception;

	/**
	 * Method name: selectTrainPlanById <BR>
	 * Description: 根据id查询培训计划 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  TrainPlanBean<BR>
	 */
	TrainPlanBean selectTrainPlanById(int id) throws Exception;

	/**
	 * Method name: addTrainPlan <BR>
	 * Description: 新增培训计划 <BR>
	 * Remark: <BR>
	 * @param plan  void<BR>
	 */
	void addTrainPlan(TrainPlanBean plan) throws Exception;

	/**
	 * Method name: updateTrainPlan <BR>
	 * Description: 修改培训计划 <BR>
	 * Remark: <BR>
	 * @param plan  void<BR>
	 */
	void updateTrainPlan(TrainPlanBean plan) throws Exception;

	/**
	 * Method name: deleteTrainPlan <BR>
	 * Description: 删除培训计划 <BR>
	 * Remark: <BR>
	 * @param parseInt  void<BR>
	 */
	void deleteTrainPlan(int parseInt) throws Exception;

	/**
	 * Method name: selectTrainArrangeCount <BR>
	 * Description: 查询培训安排 数量<BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 */
	int selectTrainArrangeCount(TrainArrangeVo vo) throws Exception;

	/**
	 * Method name: selectTrainArrangeList <BR>
	 * Description: 查询培训安排 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<TrainArrangeBean><BR>
	 */
	List<TrainArrangeBean> selectTrainArrangeList(TrainArrangeVo vo) throws Exception;

	/**
	 * Method name: selectTrainArrangeById <BR>
	 * Description: 根据id查询培训安排 <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @return  TrainPlanBean<BR>
	 */
	TrainArrangeBean selectTrainArrangeById(int parseInt) throws Exception;

	/**
	 * Method name: selectTrainConfig <BR>
	 * Description: 获取培训配置 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  TrainConfigBean<BR>
	 */
	TrainConfigBean selectTrainConfig(Map<String, Object> param) throws Exception;

	/**
	 * Method name: saveTrainConfig <BR>
	 * Description: 保存培训配置 <BR>
	 * Remark: <BR>
	 * @param config  void<BR>
	 */
	void saveTrainConfig(TrainConfigBean config) throws Exception;

	/**
	 * Method name: submitTrainPlan <BR>
	 * Description: 提交培训计划 <BR>
	 * Remark: <BR>
	 * @param plan  void<BR>
	 */
	void submitTrainPlan(TrainPlanBean plan) throws Exception;

	/**
	 * Method name: selectTrainPlanCheckListCount <BR>
	 * Description: 获取【我】的审批的数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 */
	int selectTrainPlanCheckCount(TrainVo vo) throws Exception;

	/**
	 * Method name: selectTrainPlanCheckList <BR>
	 * Description: 获取【我】的审批 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<TrainCheckBean><BR>
	 */
	List<TrainCheckBean> selectTrainPlanCheckList(TrainVo vo) throws Exception;

	/**
	 * Method name: selectTrainCheckById <BR>
	 * Description: 根据id查询【审批】 <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @return  TrainCheckBean<BR>
	 */
	TrainCheckBean selectTrainCheckById(int id) throws Exception;

	/**
	 * Method name: selectTrainCheckByMap <BR>
	 * Description: 根据计划id获取所有审批流程 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<TrainCheckBean><BR>
	 */
	List<TrainCheckBean> selectTrainCheckByVo(TrainVo vo) throws Exception;

	/**
	 * Method name: checkTrainPlan <BR>
	 * Description: 审批培训计划 <BR>
	 * Remark: <BR>
	 * @param check  void<BR>
	 */
	void checkTrainPlan(TrainCheckBean check) throws Exception;

	/**
	 * Method name: addTrainArrange <BR>
	 * Description: 新增培训安排 <BR>
	 * Remark: <BR>
	 * @param arrange  void<BR>
	 */
	void addTrainArrange(TrainArrangeBean arrange) throws Exception ;
	
	/**
	 * Method name: updateTrainArrange <BR>
	 * Description: 修改培训安排 <BR>
	 * Remark: <BR>
	 * @param arrange  void<BR>
	 */
	void updateTrainArrange(TrainArrangeBean arrange) throws Exception ;

	/**
	 * Method name: addTrainArrangeOpenCrowd <BR>
	 * Description: 新增培训安排开放人员 <BR>
	 * Remark: <BR>
	 * @param open  void<BR>
	 */
	void addTrainArrangeOpenCrowd(TrainArrangeOpenCrowdBean open) throws Exception ;

	/**
	 * Method name: selectOpenCrowdByArrangeId <BR>
	 * Description: 根据培训安排id获取开放部门 <BR>
	 * Remark: <BR>
	 * @param arrange
	 * @return  List<TrainArrangeOpenCrowdBean><BR>
	 */
	List<TrainArrangeOpenCrowdBean> selectOpenCrowdByArrangeId(
			TrainArrangeBean arrange) throws Exception ;


	/**
	 * Method name: deleteOpenCrowdByArrangeId <BR>
	 * Description: 根据安排id删除开放信息 <BR>
	 * Remark: <BR>
	 * @param arrange
	 * @throws Exception  void<BR>
	 */
	void deleteOpenCrowdByArrangeId(TrainArrangeBean arrange) throws Exception ;

	/**
	 * Method name: selectContentsByArrangeId <BR>
	 * Description: 根据安排id获取内容 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<TrainArrangeContentBean><BR>
	 */
	List<TrainArrangeContentBean> selectContentsByArrangeId(
			Map<String, Object> param) throws Exception ;

	/**
	 * Method name: updateTrainArrangeContent <BR>
	 * Description: 修改培训安排内容 <BR>
	 * Remark: <BR>
	 * @param content  void<BR>
	 */
	void updateTrainArrangeContent(TrainArrangeContentBean content) throws Exception ;

	/**
	 * Method name: addTrainArrangeContent <BR>
	 * Description: 新增培训安排内容 <BR>
	 * Remark: <BR>
	 * @param content  void<BR>
	 */
	void addTrainArrangeContent(TrainArrangeContentBean content) throws Exception ;

	/**
	 * Method name: deleteTrainArrangeContent <BR>
	 * Description: 删除培训内容 <BR>
	 * Remark: <BR>
	 * @param content  void<BR>
	 */
	void deleteTrainArrangeContent(TrainArrangeContentBean content) throws Exception ;

	/**
	 * Method name: deleteTrainArrange <BR>
	 * Description: 删除培训安排 <BR>
	 * Remark: <BR>
	 * @param parseInt  void<BR>
	 */
	void deleteTrainArrange(int id) throws Exception ;

	/**
	 * Method name: submitTrainArrange <BR>
	 * Description: 提交培训安排 <BR>
	 * Remark: <BR>
	 * @param arrange  void<BR>
	 */
	void submitTrainArrange(TrainArrangeBean arrange) throws Exception ;

	/**
	 * Method name: selectTrainPlanArrangeCount <BR>
	 * Description: 获取我审批的培训安排的数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 */
	int selectTrainArrangeCheckCount(TrainVo vo) throws Exception ;

	/**
	 * Method name: selectTrainArrangeCheckList <BR>
	 * Description: 获取我审批的培训安排 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<TrainCheckBean><BR>
	 */
	List<TrainCheckBean> selectTrainArrangeCheckList(TrainVo vo) throws Exception ;

	/**
	 * Method name: checkTrainArrange <BR>
	 * Description: 审批培训安排 <BR>
	 * Remark: <BR>
	 * @param check
	 * @throws Exception  void<BR>
	 */
	void checkTrainArrange(TrainCheckBean check) throws Exception ;

	/**
	 * Method name: selectTrainArrangeUserIds <BR>
	 * Description: 查询培训安排参训人员 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<ManageUserBean><BR>
	 */
	List<String> selectTrainArrangeUserIds(Map<String, Object> param);

	/**
	 * Method name: updateTrainArrangeUser <BR>
	 * Description: 修改 培训安排的参训人员<BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	void updateTrainArrangeUser(TrainArrangeUserBean bean) throws Exception ;

	/**
	 * Method name: deleteTrainArrangeUser <BR>
	 * Description: 删除原安排的参训人员 <BR>
	 * Remark: <BR>
	 * @param arrangeId  void<BR>
	 */
	void deleteTrainArrangeUser(int arrangeId) throws Exception ;

	/**
	 * Method name: selectTrainArrangeUserDetail <BR>
	 * Description: 获取培训参训人员 <BR>
	 * Remark: <BR>
	 * @param param_
	 * @return  List<TrainArrangeUserBean><BR>
	 */
	List<TrainArrangeUserBean> selectTrainArrangeUserDetail(
			Map<String, Object> param_) throws Exception ;

	/**
	 * Method name: updateUserPassStatus <BR>
	 * Description: 审核学员状态 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	void updateUserPassStatus(TrainArrangeUserBean record) throws Exception;

	/**
	 * Method name: exportExcel <BR>
	 * Description: 导出培训参训人员 <BR>
	 * Remark: <BR>
	 * @param rows
	 * @return  HSSFWorkbook<BR>
	 */
	HSSFWorkbook exportExcel(List<TrainArrangeUserBean> rows)throws Exception;

	/**
	 * Method name: selectCanJoinTrainArrangeCount <BR>
	 * Description: 可报名培训数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  int<BR>
	 */
	int selectCanJoinTrainArrangeCount(TrainArrangeVo vo) throws Exception;

	/**
	 * Method name: selectCanJoinTrainArrangeList <BR>
	 * Description: 可报名培训 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  List<TrainArrangeBean><BR>
	 */
	List<CanJoinTrainArrangeBean> selectCanJoinTrainArrangeList(TrainArrangeVo vo) throws Exception;

	/**
	 * Method name: selectJoinedTrainArrangeCount <BR>
	 * Description: 已报名培训 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 */
	int selectJoinedTrainArrangeCount(TrainArrangeVo vo) throws Exception;

	/**
	 * Method name: selectJoinedTrainArrangeList <BR>
	 * Description: 已报名培训 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<TrainArrangeBean><BR>
	 */
	List<TrainArrangeBean> selectJoinedTrainArrangeList(TrainArrangeVo vo) throws Exception;

	/**
	 * Method name: selectAfterClassTestByArrangeId <BR>
	 * Description: 查询多有问卷 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  List<TrainQuestionnaireBean><BR>
	 */
	List<TrainQuestionnaireBean> selectAfterClassTestByArrangeId(Map<String, Object> param) throws Exception;

	/**
	 * Method name: uploadFile <BR>
	 * Description: 上传文件 <BR>
	 * Remark: <BR>
	 * @param file
	 * @param commonPath
	 * @return
	 * @throws Exception  String<BR>
	 */
	String uploadFile(MultipartFile file, String commonPath) throws Exception;

	/**
	 * Method name: addInvestigationAssign <BR>
	 * Description: 新增学生和问卷的关系 <BR>
	 * Remark: <BR>
	 * @param assign  void<BR>
	 */
	void addInvestigationAssign(InvestigationAssignBean assign) throws Exception;

	/**
	 * Method name: getAnswerDetail <BR>
	 * Description: 查询用户答卷详情 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<QuestionnaireQuestionBean><BR>
	 */
	List<QuestionnaireQuestionBean> getAnswerDetail(Map<String, Integer> param);

	/**
	 * Method name: getQuestions <BR>
	 * Description: 查询问卷题目 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<QuestionnaireQuestionBean><BR>
	 */
	List<QuestionnaireQuestionBean> getQuestions(Map<String, Integer> param);

	/**
	 * Method name: cancelTrainArrange <BR>
	 * Description: 取消申请 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void cancelTrainArrange(int id) throws Exception;

	/**
	 * Method name: lateTrainArrange <BR>
	 * Description: 延迟培训 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void lateTrainArrange(int id) throws Exception;

	/**
	 * Method name: updateTrainArrangeCloseStatus <BR>
	 * Description: 开启、关闭报名状态 <BR>
	 * Remark: <BR>
	 * @param arrange
	 * @throws Exception  void<BR>
	 */
	void updateTrainArrangeCloseStatus(TrainArrangeBean arrange) throws Exception;

	/**
	 * Method name: selectTrainArrangeUserScoreCount <BR>
	 * Description: selectTrainArrangeUserScoreCount <BR>
	 * Remark: <BR>
	 * @param contentId
	 * @return  int<BR>
	 */
	int selectTrainArrangeUserScoreCount(int contentId);

	/**
	 * Method name: selectTrainArrangeUserScoreList <BR>
	 * Description: 查询参训学生成绩列表 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<TrainArrangeScoreBean><BR>
	 */
	List<TrainArrangeScoreBean> selectTrainArrangeUserScoreList(Map<String, Object> param);

	/**
	 * Method name: selectContentsById <BR>
	 * Description: 根据id查询培训内容 <BR>
	 * Remark: <BR>
	 * @param contentId
	 * @return  TrainArrangeContentBean<BR>
	 */
	TrainArrangeContentBean selectContentsById(int contentId);

	/**
	 * Method name: deleteTrainArrangeScore <BR>
	 * Description: 删除学生培训成绩 <BR>
	 * Remark: <BR>
	 * @param score  void<BR>
	 */
	void deleteTrainArrangeScore(TrainArrangeScoreBean score);

	/**
	 * Method name: insertTrainArrangeScore <BR>
	 * Description: 新增培训成绩 <BR>
	 * Remark: <BR>
	 * @param score  void<BR>
	 */
	void insertTrainArrangeScore(TrainArrangeScoreBean score);

	/**
	 * Method name: releaseTrainArrangeScore <BR>
	 * Description: 发布培训成绩 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void releaseTrainArrangeScore(int id);

	/**
	 * Method name: selectCourseStudyByMap <BR>
	 * Description: 查询我的学习记录 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<CourseStudyRecordBean><BR>
	 */
	List<CourseStudyRecordBean> selectCourseStudyByMap(Map<String, Object> param);

	/**
	 * Method name: selectCourseStudyCountByMap <BR>
	 * Description: 查询我的学习记录的数量 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  int<BR>
	 */
	int selectCourseStudyCountByMap(Map<String, Object> param);

	/**
	 * Method name: updateArrangeStauts <BR>
	 * Description: 修改培训状态 <BR>
	 * Remark: <BR>
	 * @param arrange  void<BR>
	 */
	void updateArrangeStauts(TrainArrangeBean arrange);

	/**
	 * Method name: exportTrainScoreExcel <BR>
	 * Description: 导出培训成绩excel <BR>
	 * Remark: <BR>
	 * @param rows
	 * @return  HSSFWorkbook<BR>
	 */
	HSSFWorkbook exportTrainScoreExcel(List<TrainArrangeScoreBean> rows);

	/**
	 * Method name: downloadTrainScoreTemplate <BR>
	 * Description: 下载培训成绩模板 <BR>
	 * Remark: <BR>
	 * @param rows
	 * @return  HSSFWorkbook<BR>
	 */
	HSSFWorkbook downloadTrainScoreTemplate(List<TrainArrangeScoreBean> rows);

	Map<String, Integer> saveExamAndAssignInfo(Integer examId,
			Integer duration, Integer allowTimes, Integer passScorePercent,
			Integer userId, Integer companyId, Integer subCompanyId)
			throws Exception;

	/**
	 * Method name: updateTrainArrangeUserScore <BR>
	 * Description: 修改培训人员的成绩 <BR>
	 * Remark: <BR>
	 * @param arrangeUser  void<BR>
	 */
	void updateTrainArrangeUserScore(TrainArrangeUserBean arrangeUser);

}
