/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: TrainServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月18日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.CanJoinTrainArrangeBean;
import com.jftt.wifi.bean.CourseStudyRecordBean;
import com.jftt.wifi.bean.TrainArrangeBean;
import com.jftt.wifi.bean.TrainArrangeContentBean;
import com.jftt.wifi.bean.TrainArrangeOpenCrowdBean;
import com.jftt.wifi.bean.TrainArrangeScoreBean;
import com.jftt.wifi.bean.TrainArrangeUserBean;
import com.jftt.wifi.bean.TrainCheckBean;
import com.jftt.wifi.bean.TrainConfigBean;
import com.jftt.wifi.bean.TrainPlanBean;
import com.jftt.wifi.bean.TrainQuestionnaireBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamScheduleBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.questionnaire.InvestigationAssignBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireQuestionBean;
import com.jftt.wifi.bean.vo.TrainArrangeVo;
import com.jftt.wifi.bean.vo.TrainVo;
import com.jftt.wifi.dao.CourseStudyRecordDaoMapper;
import com.jftt.wifi.dao.ExamAssignInfoDaoMapper;
import com.jftt.wifi.dao.ExamPaperDaoMapper;
import com.jftt.wifi.dao.ExamScheduleDaoMapper;
import com.jftt.wifi.dao.InvestigationAssignDaoMapper;
import com.jftt.wifi.dao.QuestionnaireAnswerDaoMapper;
import com.jftt.wifi.dao.QuestionnaireQuestionDaoMapper;
import com.jftt.wifi.dao.TrainArrangeContentDaoMapper;
import com.jftt.wifi.dao.TrainArrangeDaoMapper;
import com.jftt.wifi.dao.TrainArrangeOpenCrowdDaoMapper;
import com.jftt.wifi.dao.TrainArrangeScoreDaoMapper;
import com.jftt.wifi.dao.TrainArrangeUserDaoMapper;
import com.jftt.wifi.dao.TrainCheckDaoMapper;
import com.jftt.wifi.dao.TrainConfigDaoMapper;
import com.jftt.wifi.dao.TrainPlanDaoMapper;
import com.jftt.wifi.service.TrainService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.MyExcelHelp;
import com.jftt.wifi.util.PropertyUtil;

/**
 * class name:TrainServiceImpl <BR>
 * class description: 培训管理 <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月18日
 * @author JFTT)Lu Yunlong
 */
@Service("trainService")
public class TrainServiceImpl implements TrainService {
	
	@Resource(name="trainPlanDaoMapper")
	private TrainPlanDaoMapper trainPlanDaoMapper;
	
	@Resource(name="trainArrangeDaoMapper")
	private TrainArrangeDaoMapper trainArrangeDaoMapper;
	
	@Resource(name="trainConfigDaoMapper")
	private TrainConfigDaoMapper trainConfigDaoMapper;
	
	@Resource(name="trainCheckDaoMapper")
	private TrainCheckDaoMapper trainCheckDaoMapper;
	
	@Resource(name="trainArrangeOpenCrowdDaoMapper")
	private TrainArrangeOpenCrowdDaoMapper trainArrangeOpenCrowdDaoMapper;
	
	@Resource(name="trainArrangeContentDaoMapper")
	private TrainArrangeContentDaoMapper trainArrangeContentDaoMapper;
	
	@Resource(name="trainArrangeUserDaoMapper")
	private TrainArrangeUserDaoMapper trainArrangeUserDaoMapper;
	
	@Resource(name="investigationAssignDaoMapper")
	private InvestigationAssignDaoMapper investigationAssignDaoMapper;
	
	@Resource(name="questionnaireQuestionDaoMapper")
	private QuestionnaireQuestionDaoMapper questionnaireQuestionDaoMapper;
	
	@Resource(name="questionnaireAnswerDaoMapper")
	private QuestionnaireAnswerDaoMapper questionnaireAnswerDaoMapper;
	
	@Resource(name="trainArrangeScoreDaoMapper")
	private TrainArrangeScoreDaoMapper trainArrangeScoreDaoMapper;
	
	@Resource(name="courseStudyRecordDaoMapper")
	private CourseStudyRecordDaoMapper courseStudyRecordDaoMapper;
	
	@Resource(name="examScheduleDaoMapper")
	private ExamScheduleDaoMapper examScheduleDaoMapper;
	
	@Resource(name="examPaperDaoMapper")
	private ExamPaperDaoMapper examPaperDaoMapper;
	
	@Resource(name="examAssignInfoDaoMapper")
	private ExamAssignInfoDaoMapper examAssignInfoDaoMapper;
	

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainPlanListCount(java.util.Map) <BR>
	 * Method name: selectTrainPlanListCount <BR>
	 * Description: 查询培训计划数量 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public int selectTrainPlanListCount(Map<String, Object> param) throws Exception {
		return trainPlanDaoMapper.selectTrainPlanListCount(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainPlanList(java.util.Map) <BR>
	 * Method name: selectTrainPlanList <BR>
	 * Description: 查询培训计划 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<TrainPlanBean> selectTrainPlanList(Map<String, Object> param) throws Exception {
		return trainPlanDaoMapper.selectTrainPlanList(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainPlanById(int) <BR>
	 * Method name: selectTrainPlanById <BR>
	 * Description: 根据id查询培训计划 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public TrainPlanBean selectTrainPlanById(int id) throws Exception {
		return trainPlanDaoMapper.selectById(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#addTrainPlan(com.jftt.wifi.bean.TrainPlanBean) <BR>
	 * Method name: addTrainPlan <BR>
	 * Description: 新增培训计划 <BR>
	 * Remark: <BR>
	 * @param plan
	 * @throws Exception  <BR>
	*/
	@Override
	public void addTrainPlan(TrainPlanBean plan) throws Exception {
		trainPlanDaoMapper.insert(plan);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#updateTrainPlan(com.jftt.wifi.bean.TrainPlanBean) <BR>
	 * Method name: updateTrainPlan <BR>
	 * Description: 修改培训计划 <BR>
	 * Remark: <BR>
	 * @param plan
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateTrainPlan(TrainPlanBean plan) throws Exception {
		trainPlanDaoMapper.updateById(plan);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#submitTrainPlan(com.jftt.wifi.bean.TrainPlanBean) <BR>
	 * Method name: submitTrainPlan <BR>
	 * Description: 提交培训计划 <BR>
	 * Remark: <BR>
	 * @param plan
	 * @throws Exception  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void submitTrainPlan(TrainPlanBean plan) throws Exception {
		//判断不是第一次提交，那么删除原先的审批流程
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("trainId", plan.getId());
		param1.put("type", 1);
		trainCheckDaoMapper.deleteByMap(param1);
		//培训计划的状态默认【待审批】
		plan.setStatus(2);
		//判断目前提交的培训是否需要审批
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", plan.getCompanyId());
		param.put("subCompanyId", plan.getSubCompanyId());
		TrainConfigBean config = trainConfigDaoMapper.selectById(param);
		if(config != null && "1".equals(config.getIsCheck().toString())){
			if(config.getCheckUserId() != null || !"".equals(config.getCheckUserId())){
				//审批人按照顺序，新增数据
				String[] checkUserIds = config.getCheckUserId().split(",");
				String[] checkUserNames = config.getCheckUserName().split(",");
				for(int i = 0; i < checkUserIds.length; i++){
					TrainCheckBean check = new TrainCheckBean();
					check.setCheckUserId(Integer.parseInt(checkUserIds[i]));
					check.setCheckUserName(checkUserNames[i]);
					check.setTrainId(plan.getId());
					check.setType(1);
					trainCheckDaoMapper.insert(check);
				}
			}else{
				//不需要审批,那么提交后培训计划状态就是【审批通过】
				plan.setStatus(3);
			}
		}else{
			//不需要审批,那么提交后培训计划状态就是【审批通过】
			plan.setStatus(3);
		}
		//提交该培训计划审批
		trainPlanDaoMapper.submitTrainPlan(plan);
		
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainPlanCheckCount(com.jftt.wifi.bean.vo.TrainVo) <BR>
	 * Method name: selectTrainPlanCheckCount <BR>
	 * Description: 查询【我】审批的培训计划数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public int selectTrainPlanCheckCount(TrainVo vo) throws Exception {
		return trainCheckDaoMapper.selectTrainPlanCheckCount(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainPlanCheckList(com.jftt.wifi.bean.vo.TrainVo) <BR>
	 * Method name: selectTrainPlanCheckList <BR>
	 * Description: 查询【我】审批的培训计划 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<TrainCheckBean> selectTrainPlanCheckList(TrainVo vo) throws Exception {
		return trainCheckDaoMapper.selectTrainPlanCheckList(vo);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainCheckById(int) <BR>
	 * Method name: selectTrainCheckById <BR>
	 * Description: 根据id查询培训审批流程 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public TrainCheckBean selectTrainCheckById(int id) throws Exception {
		TrainCheckBean check = trainCheckDaoMapper.selectTrainCheckById(id);
		if(check.getType().toString().equals("1")){
			check.setPlan(trainPlanDaoMapper.selectById(check.getTrainId()));
		}else{
			check.setArrange(trainArrangeDaoMapper.selectById(check.getTrainId()));
		}
		return check;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainCheckByVo(com.jftt.wifi.bean.vo.TrainVo) <BR>
	 * Method name: selectTrainCheckByVo <BR>
	 * Description: 根据条件查询培训审批流程 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<TrainCheckBean> selectTrainCheckByVo(TrainVo vo) throws Exception {
		return trainCheckDaoMapper.selectTrainCheckByVo(vo);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#checkTrainPlan(com.jftt.wifi.bean.TrainCheckBean) <BR>
	 * Method name: checkTrainPlan <BR>
	 * Description: 审批培训计划 <BR>
	 * Remark: <BR>
	 * @param check
	 * @throws Exception  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void checkTrainPlan(TrainCheckBean check) throws Exception {
		//更新当前流程状态
		trainCheckDaoMapper.updateById(check);
		//如果审批拒绝，那么更新培训计划
		TrainCheckBean checkBean = trainCheckDaoMapper.selectById(check.getId());
		if("3".equals(check.getStatus().toString())){
			trainPlanDaoMapper.rejectPlan(checkBean.getTrainId());
		}else{
			//判断【我】如果是最后一个审核人，那么将该培训状态改为审核通过
			trainPlanDaoMapper.passPlan(checkBean);
		}
	}


	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#deleteTrainPlan(int) <BR>
	 * Method name: deleteTrainPlan <BR>
	 * Description: 删除培训计划 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	public void deleteTrainPlan(int id) throws Exception {
		trainPlanDaoMapper.deleteById(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainArrangeCount(com.jftt.wifi.bean.vo.TrainArrangeVo) <BR>
	 * Method name: selectTrainArrangeCount <BR>
	 * Description: 查询培训安排数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public int selectTrainArrangeCount(TrainArrangeVo vo) throws Exception {
		return trainArrangeDaoMapper.selectTrainArrangeCount(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainArrangeList(com.jftt.wifi.bean.vo.TrainArrangeVo) <BR>
	 * Method name: selectTrainArrangeList <BR>
	 * Description: 查询培训安排 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<TrainArrangeBean> selectTrainArrangeList(TrainArrangeVo vo) throws Exception {
		return trainArrangeDaoMapper.selectTrainArrangeList(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainArrangeById(int) <BR>
	 * Method name: selectTrainArrangeById <BR>
	 * Description: 根据培训安排id查询培训安排 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public TrainArrangeBean selectTrainArrangeById(int id) throws Exception {
		return trainArrangeDaoMapper.selectById(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainConfig(java.util.Map) <BR>
	 * Method name: selectTrainConfig <BR>
	 * Description: 根据条件查询培训配置 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public TrainConfigBean selectTrainConfig(Map<String, Object> param) throws Exception {
		return trainConfigDaoMapper.selectById(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#saveTrainConfig(com.jftt.wifi.bean.TrainConfigBean) <BR>
	 * Method name: saveTrainConfig <BR>
	 * Description: 保存培训配置 <BR>
	 * Remark: <BR>
	 * @param config
	 * @throws Exception  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveTrainConfig(TrainConfigBean config) throws Exception {
		//删除原配置
		trainConfigDaoMapper.deleteById(config);
		//新增新配置
		trainConfigDaoMapper.insert(config);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#addTrainArrange(com.jftt.wifi.bean.TrainArrangeBean) <BR>
	 * Method name: addTrainArrange <BR>
	 * Description: 新增培训安排 <BR>
	 * Remark: <BR>
	 * @param arrange  <BR>
	*/
	@Override
	public void addTrainArrange(TrainArrangeBean arrange) throws Exception  {
		trainArrangeDaoMapper.insert(arrange);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#updateTrainArrange(com.jftt.wifi.bean.TrainArrangeBean) <BR>
	 * Method name: updateTrainArrange <BR>
	 * Description: 修改培训安排 <BR>
	 * Remark: <BR>
	 * @param arrange
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateTrainArrange(TrainArrangeBean arrange) throws Exception {
		trainArrangeDaoMapper.updateById(arrange);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#addTrainArrangeOpenCrowd(com.jftt.wifi.bean.TrainArrangeOpenCrowdBean) <BR>
	 * Method name: addTrainArrangeOpenCrowd <BR>
	 * Description: 新增培训安排开放人员 <BR>
	 * Remark: <BR>
	 * @param open  <BR>
	*/
	@Override
	public void addTrainArrangeOpenCrowd(TrainArrangeOpenCrowdBean open)  throws Exception {
		trainArrangeOpenCrowdDaoMapper.insert(open);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectOpenCrowdByArrangeId(com.jftt.wifi.bean.TrainArrangeBean) <BR>
	 * Method name: selectOpenCrowdByArrangeId <BR>
	 * Description: 根据培训安排id获取开放部门 <BR>
	 * Remark: <BR>
	 * @param arrange
	 * @return  <BR>
	*/
	@Override
	public List<TrainArrangeOpenCrowdBean> selectOpenCrowdByArrangeId(
			TrainArrangeBean arrange)  throws Exception {
		return trainArrangeOpenCrowdDaoMapper.selectOpenCrowdByArrangeId(arrange);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#deleteOpenCrowdByArrangeId(com.jftt.wifi.bean.TrainArrangeBean) <BR>
	 * Method name: deleteOpenCrowdByArrangeId <BR>
	 * Description: 根据id删除开放信息 <BR>
	 * Remark: <BR>
	 * @param arrange
	 * @throws Exception  <BR>
	*/
	@Override
	public void deleteOpenCrowdByArrangeId(TrainArrangeBean arrange)
			throws Exception {
		trainArrangeOpenCrowdDaoMapper.deleteByArrangeId(arrange);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectContentsByArrangeId(java.util.Map) <BR>
	 * Method name: selectContentsByArrangeId <BR>
	 * Description: 根据安排id获取内容 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  <BR>
	*/
	@Override
	public List<TrainArrangeContentBean> selectContentsByArrangeId(
			Map<String, Object> param) throws Exception  {
		return trainArrangeContentDaoMapper.selectContentsByArrangeId(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#updateTrainArrangeContent(com.jftt.wifi.bean.TrainArrangeContentBean) <BR>
	 * Method name: updateTrainArrangeContent <BR>
	 * Description: 修改安排内容 <BR>
	 * Remark: <BR>
	 * @param content  <BR>
	*/
	@Override
	public void updateTrainArrangeContent(TrainArrangeContentBean content) throws Exception  {
		trainArrangeContentDaoMapper.updateById(content);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#addTrainArrangeContent(com.jftt.wifi.bean.TrainArrangeContentBean) <BR>
	 * Method name: addTrainArrangeContent <BR>
	 * Description: 新增安排内容 <BR>
	 * Remark: <BR>
	 * @param content  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addTrainArrangeContent(TrainArrangeContentBean content) throws Exception  {
		//新增培训安排内容
		trainArrangeContentDaoMapper.insert(content);
		//判断该阶段是不是第一阶段
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("arrangeId", content.getTrainArrangeId());
		List<TrainArrangeContentBean> contents = trainArrangeContentDaoMapper.selectContentsByArrangeId(param);
		param.put("passStatus", 2);
		List<String> ids = trainArrangeUserDaoMapper.selectTrainArrangeUserIds(param);
		if(contents.size() == 1 && ids.size() > 0){
			//更新培训安排的状态，设置为1，可提交
			TrainArrangeBean arrange = new TrainArrangeBean();
			arrange.setId(content.getTrainArrangeId());
			arrange.setStatus(1);
			trainArrangeDaoMapper.updateArrangeStauts(arrange);
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#deleteTrainArrangeContent(int) <BR>
	 * Method name: deleteTrainArrangeContent <BR>
	 * Description: 删除培训内容 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteTrainArrangeContent(TrainArrangeContentBean content) throws Exception {
		trainArrangeContentDaoMapper.deleteById(content.getId());
		//判断该阶段是不是仅有的内容
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("arrangeId", content.getTrainArrangeId());
		List<TrainArrangeContentBean> contents = trainArrangeContentDaoMapper.selectContentsByArrangeId(param);
		if(contents.size() == 0){
			//更新培训安排的状态，设置为0，不可提交
			TrainArrangeBean arrange = new TrainArrangeBean();
			arrange.setId(content.getTrainArrangeId());
			arrange.setStatus(0);
			trainArrangeDaoMapper.updateArrangeStauts(arrange);
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#deleteTrainArrange(int) <BR>
	 * Method name: deleteTrainArrange <BR>
	 * Description: 删除培训安排 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	public void deleteTrainArrange(int id) throws Exception {
		trainArrangeDaoMapper.deleteById(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#submitTrainArrange(com.jftt.wifi.bean.TrainArrangeBean) <BR>
	 * Method name: submitTrainArrange <BR>
	 * Description: 提交培训安排 <BR>
	 * Remark: <BR>
	 * @param arrange
	 * @throws Exception  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void submitTrainArrange(TrainArrangeBean arrange) throws Exception {
		//判断不是第一次提交，那么删除原先的审批流程
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("trainId", arrange.getId());
		param1.put("type", 2);
		trainCheckDaoMapper.deleteByMap(param1);
		//培训安排的状态默认【待审批】
		arrange.setStatus(2);
		//判断目前提交的培训是否需要审批
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", arrange.getCompanyId());
		param.put("subCompanyId", arrange.getSubCompanyId());
		TrainConfigBean config = trainConfigDaoMapper.selectById(param);
		if(config != null && "1".equals(config.getIsCheck().toString())){
			if(config.getCheckUserId() != null || !"".equals(config.getCheckUserId())){
				//审批人按照顺序，新增数据
				String[] checkUserIds = config.getCheckUserId().split(",");
				String[] checkUserNames = config.getCheckUserName().split(",");
				for(int i = 0; i < checkUserIds.length; i++){
					TrainCheckBean check = new TrainCheckBean();
					check.setCheckUserId(Integer.parseInt(checkUserIds[i]));
					check.setCheckUserName(checkUserNames[i]);
					check.setTrainId(arrange.getId());
					check.setType(2);
					trainCheckDaoMapper.insert(check);
				}
			}else{
				//不需要审批,那么提交后培训计划状态就是【审批通过】
				arrange.setStatus(3);
			}
		}else{
			//不需要审批,那么提交后培训计划状态就是【审批通过】
			arrange.setStatus(3);
		}
		//提交该培训计划审批
		trainArrangeDaoMapper.submitArrange(arrange);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainArrangeCheckCount(com.jftt.wifi.bean.vo.TrainVo) <BR>
	 * Method name: selectTrainArrangeCheckCount <BR>
	 * Description: 我审批的培训安排数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public int selectTrainArrangeCheckCount(TrainVo vo) throws Exception {
		return trainCheckDaoMapper.selectTrainArrangeCheckCount(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainArrangeCheckList(com.jftt.wifi.bean.vo.TrainVo) <BR>
	 * Method name: selectTrainArrangeCheckList <BR>
	 * Description: 我审批的培训安排 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<TrainCheckBean> selectTrainArrangeCheckList(TrainVo vo)
			throws Exception {
		return trainCheckDaoMapper.selectTrainArrangeCheckList(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#checkTrainArrange(com.jftt.wifi.bean.TrainCheckBean) <BR>
	 * Method name: checkTrainArrange <BR>
	 * Description: 审批培训安排 <BR>
	 * Remark: <BR>
	 * @param check
	 * @throws Exception  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void checkTrainArrange(TrainCheckBean check) throws Exception {
		//更新当前流程状态
		trainCheckDaoMapper.updateById(check);
		//如果审批拒绝，那么更新培训安排
		TrainCheckBean checkBean = trainCheckDaoMapper.selectById(check.getId());
		if("3".equals(check.getStatus().toString())){
			trainArrangeDaoMapper.rejectArrange(checkBean.getTrainId());
		}else{
			//判断【我】如果是最后一个审核人，那么将该培训状态改为审核通过
			trainArrangeDaoMapper.passArrange(checkBean);
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainArrangeUserIds(java.util.Map) <BR>
	 * Method name: selectTrainArrangeUserIds <BR>
	 * Description: 查询培训安排的参训人员 <BR>
	 * Remark: <BR>
	 * @param arrangeId
	 * @return  <BR>
	*/
	@Override
	public List<String> selectTrainArrangeUserIds(Map<String, Object> param) {
		return trainArrangeUserDaoMapper.selectTrainArrangeUserIds(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#updateTrainArrangeUser(com.jftt.wifi.bean.TrainArrangeUserBean) <BR>
	 * Method name: updateTrainArrangeUser <BR>
	 * Description: 修改 培训安排的参训人员 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateTrainArrangeUser(TrainArrangeUserBean bean)
			throws Exception {
		trainArrangeUserDaoMapper.deleteById(bean);
		trainArrangeUserDaoMapper.insert(bean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#deleteTrainArrangeUser(int) <BR>
	 * Method name: deleteTrainArrangeUser <BR>
	 * Description: 删除原安排的参训人员 <BR>
	 * Remark: <BR>
	 * @param arrangeId
	 * @throws Exception  <BR>
	*/
	@Override
	public void deleteTrainArrangeUser(int arrangeId) throws Exception {
		trainArrangeUserDaoMapper.deleteTrainArrangeUser(arrangeId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainArrangeUserDetail(java.util.Map) <BR>
	 * Method name: selectTrainArrangeUserDetail <BR>
	 * Description: 获取培训参训人员 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<TrainArrangeUserBean> selectTrainArrangeUserDetail(
			Map<String, Object> param) throws Exception {
		return trainArrangeUserDaoMapper.selectTrainArrangeUserDetail(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#updateUserPassStatus(com.jftt.wifi.bean.TrainArrangeUserBean) <BR>
	 * Method name: updateUserPassStatus <BR>
	 * Description: 审核学员状态 <BR>
	 * Remark: <BR>
	 * @param record
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateUserPassStatus(TrainArrangeUserBean record)
			throws Exception {
		trainArrangeUserDaoMapper.updateById(record);	
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#exportExcel(java.util.List) <BR>
	 * Method name: exportExcel <BR>
	 * Description: 导出培训参训人员 <BR>
	 * Remark: <BR>
	 * @param rows
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public HSSFWorkbook exportExcel(List<TrainArrangeUserBean> rows)
			throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "参训人员列表");
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("宋体");

		HSSFDataFormat dataFormat = workbook.createDataFormat();
		// 单元格数据样式准备设置
		HSSFCellStyle centerStyle = MyExcelHelp.createCellStyle(workbook, font, CellStyle.ALIGN_CENTER);// 左
		// 标题行
		HSSFRow row = sheet.createRow(0);
		int j = 0;
		
		MyExcelHelp.createStringCell(row, 0, centerStyle, "用户名");
		sheet.setColumnWidth(0, 10 * 128);
		MyExcelHelp.createStringCell(row, 1, centerStyle, "姓名");
		sheet.setColumnWidth(1, 10 * 128);
		MyExcelHelp.createStringCell(row, 2, centerStyle, "岗位");
		sheet.setColumnWidth(2, 10 * 128);
		MyExcelHelp.createStringCell(row, 3, centerStyle, "部门");
		sheet.setColumnWidth(3, 10 * 128);
		MyExcelHelp.createStringCell(row, 4, centerStyle, "报名时间");
		sheet.setColumnWidth(4, 10 * 128);
		MyExcelHelp.createStringCell(row, 5, centerStyle, "报名状态");
		sheet.setColumnWidth(5, 10 * 128);
		
		for (j = 0; j < rows.size(); j++) {// 控制行
			TrainArrangeUserBean bean = rows.get(j);
			row = sheet.createRow(j+1);
			int colIndex = 0;
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getUser().getUserName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getUser().getName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getUser().getDeptName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getUser().getPostName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, CommonUtil.getDateTimeString(rows.get(j).getCreateTime()));
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, "已报名");
		}
		return workbook;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectCanJoinTrainArrangeCount(com.jftt.wifi.bean.vo.TrainArrangeVo) <BR>
	 * Method name: selectCanJoinTrainArrangeCount <BR>
	 * Description: 可报名培训数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public int selectCanJoinTrainArrangeCount(TrainArrangeVo vo)
			throws Exception {
		return trainArrangeDaoMapper.selectCanJoinTrainArrangeCount(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectCanJoinTrainArrangeList(com.jftt.wifi.bean.vo.TrainArrangeVo) <BR>
	 * Method name: selectCanJoinTrainArrangeList <BR>
	 * Description: 可报名培训列表 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<CanJoinTrainArrangeBean> selectCanJoinTrainArrangeList(
			TrainArrangeVo vo) throws Exception {
		return trainArrangeDaoMapper.selectCanJoinTrainArrangeList(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectJoinedTrainArrangeCount(com.jftt.wifi.bean.vo.TrainArrangeVo) <BR>
	 * Method name: selectJoinedTrainArrangeCount <BR>
	 * Description: 已报名培训 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public int selectJoinedTrainArrangeCount(TrainArrangeVo vo)
			throws Exception {
		return trainArrangeDaoMapper.selectJoinedTrainArrangeCount(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectJoinedTrainArrangeList(com.jftt.wifi.bean.vo.TrainArrangeVo) <BR>
	 * Method name: selectJoinedTrainArrangeList <BR>
	 * Description: 已报名培训 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<TrainArrangeBean> selectJoinedTrainArrangeList(
			TrainArrangeVo vo) throws Exception {
		return trainArrangeDaoMapper.selectJoinedTrainArrangeList(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectAfterClassTestByArrangeId(java.util.Map) <BR>
	 * Method name: selectAfterClassTestByArrangeId <BR>
	 * Description: 根据培训id获取所有问卷 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<TrainQuestionnaireBean> selectAfterClassTestByArrangeId(Map<String, Object> param)
			throws Exception {
		return trainArrangeDaoMapper.selectAfterClassTestByArrangeId(param);
	}

	@Override
	public String uploadFile(MultipartFile file, String commonPath)
			throws Exception {

		String extendUrl;
		String genName;
		JSONObject param = new JSONObject();
		try {
			//保存在指定文件路径里
			String path = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/
			//获取拼接地址
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			extendUrl = commonPath+"/"+df.format(new Date());
			path +=extendUrl;
			
			
			String fileName = file.getOriginalFilename();	
			String suffixName =fileName.substring(fileName.lastIndexOf(".")+1); 
			
			genName = UUID.randomUUID()+"."+suffixName;
			
			//判断是否存在目录，如果不存在，则创建目录
			if(!new File(path).exists()){
				new File(path).mkdirs();
			}
			
			File dest = new java.io.File(path,genName);
			file.transferTo(dest);
			param.put("recordCode", 1);
			param.put("fileSize", dest.length());
			param.put("fileName", fileName.replace("."+suffixName, ""));
			param.put("path", PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH")+"/"+extendUrl+"/"+genName);
			
		} catch (Exception e) {
			e.printStackTrace();
			param.put("recordCode", -1);
			return param.toString();
		}
		
		return param.toString();
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#addInvestigationAssign(com.jftt.wifi.bean.questionnaire.InvestigationAssignBean) <BR>
	 * Method name: addInvestigationAssign <BR>
	 * Description: 新增学生和问卷的关系 <BR>
	 * Remark: <BR>
	 * @param assign  <BR>
	*/
	@Override
	public void addInvestigationAssign(InvestigationAssignBean assign) throws Exception {
		investigationAssignDaoMapper.add(assign);		
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#getAnswerDetail(java.util.Map) <BR>
	 * Method name: getAnswerDetail <BR>
	 * Description: 查询用户答卷详情<BR>
	 * Remark: <BR>
	 * @param param
	 * @return  <BR>
	*/
	@Override
	public List<QuestionnaireQuestionBean> getAnswerDetail(
			Map<String, Integer> param) {
		//通过调查查询详情答案信息
		List<QuestionnaireQuestionBean> qList = questionnaireQuestionDaoMapper.getQuestionnaireQuestionList(param.get("qId"));
		for(QuestionnaireQuestionBean qBean:qList){
			String answer = questionnaireAnswerDaoMapper.getAnswer(qBean.getId(), param.get("assignId"));
			qBean.setAnswer(answer);
		}
		return qList;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#getQuestions(java.util.Map) <BR>
	 * Method name: getQuestions <BR>
	 * Description: 查询问卷题目 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  <BR>
	*/
	@Override
	public List<QuestionnaireQuestionBean> getQuestions(
			Map<String, Integer> param) {
		return questionnaireQuestionDaoMapper.getQuestionnaireQuestionList(param.get("qId"));
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#cancelTrainArrange(int) <BR>
	 * Method name: cancelTrainArrange <BR>
	 * Description: 取消申请 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void cancelTrainArrange(int id) throws Exception {
		TrainArrangeBean arrange = new TrainArrangeBean();
		arrange.setStatus(1);
		arrange.setId(id);
		trainArrangeDaoMapper.updateArrangeStauts(arrange);
		//更新审批流程中的状态
		TrainCheckBean check = new TrainCheckBean();
		check.setTrainId(id);
		check.setType(2);
		check.setStatus(4);
		trainCheckDaoMapper.updateStatusByTrainId(check);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#lateTrainArrange(int) <BR>
	 * Method name: lateTrainArrange <BR>
	 * Description: 延迟培训 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	public void lateTrainArrange(int id) throws Exception {
		TrainArrangeBean arrange = new TrainArrangeBean();
		arrange.setStatus(1);
		arrange.setId(id);
		trainArrangeDaoMapper.updateArrangeStauts(arrange);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#updateTrainArrangeCloseStatus(com.jftt.wifi.bean.TrainArrangeBean) <BR>
	 * Method name: updateTrainArrangeCloseStatus <BR>
	 * Description:  开启、关闭报名状态 <BR>
	 * Remark: <BR>
	 * @param arrange
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateTrainArrangeCloseStatus(TrainArrangeBean arrange)
			throws Exception {
		trainArrangeDaoMapper.updateTrainArrangeCloseStatus(arrange);
	}

	@Override
	public int selectTrainArrangeUserScoreCount(int contentId) {
		return trainArrangeScoreDaoMapper.selectTrainArrangeUserScoreCount(contentId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectTrainArrangeUserScoreList(java.util.Map) <BR>
	 * Method name: selectTrainArrangeUserScoreList <BR>
	 * Description: 查询培训人员的成绩 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  <BR>
	*/
	@Override
	public List<TrainArrangeScoreBean> selectTrainArrangeUserScoreList(Map<String, Object> param) {
		return trainArrangeScoreDaoMapper.selectTrainArrangeUserScoreList(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectContentsById(int) <BR>
	 * Method name: selectContentsById <BR>
	 * Description: 根据id查询培训内容 <BR>
	 * Remark: <BR>
	 * @param contentId
	 * @return  <BR>
	*/
	@Override
	public TrainArrangeContentBean selectContentsById(int contentId) {
		return trainArrangeContentDaoMapper.selectById(contentId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#deleteTrainArrangeScore(com.jftt.wifi.bean.TrainArrangeScoreBean) <BR>
	 * Method name: deleteTrainArrangeScore <BR>
	 * Description: 删除学员培训成绩 <BR>
	 * Remark: <BR>
	 * @param score  <BR>
	*/
	@Override
	public void deleteTrainArrangeScore(TrainArrangeScoreBean score) {
		trainArrangeScoreDaoMapper.delete(score);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#insertTrainArrangeScore(com.jftt.wifi.bean.TrainArrangeScoreBean) <BR>
	 * Method name: insertTrainArrangeScore <BR>
	 * Description: 新增学员培训成绩 <BR>
	 * Remark: <BR>
	 * @param score  <BR>
	*/
	@Override
	public void insertTrainArrangeScore(TrainArrangeScoreBean score) {
		trainArrangeScoreDaoMapper.insert(score);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#releaseTrainArrangeScore(int) <BR>
	 * Method name: releaseTrainArrangeScore <BR>
	 * Description: 发布培训成绩 <BR>
	 * Remark: <BR>
	 * @param id  <BR>
	*/
	@Override
	public void releaseTrainArrangeScore(int id) {
		trainArrangeDaoMapper.releaseTrainArrangeScore(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectCourseStudyByMap(java.util.Map) <BR>
	 * Method name: selectCourseStudyByMap <BR>
	 * Description: 我的学习记录 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  <BR>
	*/
	@Override
	public List<CourseStudyRecordBean> selectCourseStudyByMap(
			Map<String, Object> param) {
		return courseStudyRecordDaoMapper.selectCourseStudyByMap(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#selectCourseStudyCountByMap(java.util.Map) <BR>
	 * Method name: selectCourseStudyCountByMap <BR>
	 * Description: 我的学习记录数量 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  <BR>
	*/
	@Override
	public int selectCourseStudyCountByMap(Map<String, Object> param) {
		return courseStudyRecordDaoMapper.selectCourseStudyCountByMap(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#updateArrangeStauts(com.jftt.wifi.bean.TrainArrangeBean) <BR>
	 * Method name: updateArrangeStauts <BR>
	 * Description: 修改培训状态 <BR>
	 * Remark: <BR>
	 * @param arrange  <BR>
	*/
	@Override
	public void updateArrangeStauts(TrainArrangeBean arrange) {
		trainArrangeDaoMapper.updateArrangeStauts(arrange);		
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#exportTrainScoreExcel(java.util.List) <BR>
	 * Method name: exportTrainScoreExcel <BR>
	 * Description: 导出培训成绩 <BR>
	 * Remark: <BR>
	 * @param rows
	 * @return  <BR>
	*/
	@Override
	public HSSFWorkbook exportTrainScoreExcel(List<TrainArrangeScoreBean> rows) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "培训成绩列表");
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("宋体");

		HSSFDataFormat dataFormat = workbook.createDataFormat();
		// 单元格数据样式准备设置
		HSSFCellStyle centerStyle = MyExcelHelp.createCellStyle(workbook, font, CellStyle.ALIGN_CENTER);// 左
		// 标题行
		HSSFRow row = sheet.createRow(0);
		int j = 0;
		
		MyExcelHelp.createStringCell(row, 0, centerStyle, "用户名");
		sheet.setColumnWidth(0, 40 * 128);
		MyExcelHelp.createStringCell(row, 1, centerStyle, "姓名");
		sheet.setColumnWidth(1, 40 * 128);
		MyExcelHelp.createStringCell(row, 2, centerStyle, "岗位");
		sheet.setColumnWidth(2, 40 * 128);
		MyExcelHelp.createStringCell(row, 3, centerStyle, "部门");
		sheet.setColumnWidth(3, 40 * 128);
		MyExcelHelp.createStringCell(row, 4, centerStyle, "签到");
		sheet.setColumnWidth(4, 40 * 128);
		MyExcelHelp.createStringCell(row, 5, centerStyle, "成绩");
		sheet.setColumnWidth(5, 40 * 128);
		MyExcelHelp.createStringCell(row, 6, centerStyle, "是否通过");
		sheet.setColumnWidth(6, 40 * 128);
		
		for (j = 0; j < rows.size(); j++) {// 控制行
			row = sheet.createRow(j+1);
			int colIndex = 0;
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getUser().getUserName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getUser().getName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getUser().getDeptName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getUser().getPostName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getIsSign().toString().endsWith("1")?"是":"否");
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getScore().toString());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getIsPass().toString().endsWith("1")?"通过":"未通过");
		}
		return workbook;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#downloadTrainScoreTemplate(java.util.List) <BR>
	 * Method name: downloadTrainScoreTemplate <BR>
	 * Description: 下载培训成绩模板 <BR>
	 * Remark: <BR>
	 * @param rows
	 * @return  <BR>
	*/
	@Override
	public HSSFWorkbook downloadTrainScoreTemplate(
			List<TrainArrangeScoreBean> rows) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "培训成绩列表");
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("宋体");

		HSSFDataFormat dataFormat = workbook.createDataFormat();
		// 单元格数据样式准备设置
		HSSFCellStyle centerStyle = MyExcelHelp.createCellStyle(workbook, font, CellStyle.ALIGN_CENTER);// 左
		// 标题行
		HSSFRow row = sheet.createRow(0);
		int j = 0;
		
		MyExcelHelp.createStringCell(row, 0, centerStyle, "用户名");
		sheet.setColumnWidth(0, 40 * 128);
		MyExcelHelp.createStringCell(row, 1, centerStyle, "姓名");
		sheet.setColumnWidth(1, 40 * 128);
		MyExcelHelp.createStringCell(row, 2, centerStyle, "是否签到");
		sheet.setColumnWidth(2, 40 * 128);
		MyExcelHelp.createStringCell(row, 3, centerStyle, "成绩");
		sheet.setColumnWidth(3, 40 * 128);
		
		//生成【签到】的下拉内容
		CellRangeAddressList regions = new CellRangeAddressList(1,rows.size(),2,2);
		DVConstraint constraint = DVConstraint.createExplicitListConstraint(new String[]{"是","否"});
		HSSFDataValidation validation = new HSSFDataValidation(regions, constraint);
		sheet.addValidationData(validation);
		
		for (j = 0; j < rows.size(); j++) {// 控制行
			row = sheet.createRow(j+1);
			int colIndex = 0;
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getUser().getUserName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getUser().getName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getIsSign()==null?"否":(rows.get(j).getIsSign().toString().equals("1")?"是":"否"));
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, rows.get(j).getScore()==null?"":rows.get(j).getScore().toString());
		}
		return workbook;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#saveExamAndAssignInfo(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: saveExamAndAssignInfo <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param examId
	 * @param duration
	 * @param allowTimes
	 * @param passScorePercent
	 * @param userId
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Integer> saveExamAndAssignInfo(Integer examId, Integer duration,Integer allowTimes, Integer passScorePercent, 
			Integer userId,Integer companyId,Integer subCompanyId) throws Exception {
		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		//保存考试信息
		PaperBean examPaper = examPaperDaoMapper.getPaper(examId);
		Integer examScheduleId = examScheduleDaoMapper.getIdByConditions(examPaper.getTitle(),examId,duration,allowTimes,passScorePercent,6,companyId,subCompanyId);
		if(examScheduleId == null){
			ExamScheduleBean examSchedule = new ExamScheduleBean();
			examSchedule.setTitle(examPaper.getTitle());
			examSchedule.setPaperId(examId);
			examSchedule.setDuration(duration);
			examSchedule.setDisplayMode(1);//整卷显示
			examSchedule.setRandomOrderMode(0);
			examSchedule.setAllowTimes(allowTimes);
			examSchedule.setPassScorePercent(passScorePercent);
			examSchedule.setType(6);//培训通过考试
			examSchedule.setIsAutoMarking(0);
			examSchedule.setIsScorePublic(1);
			examSchedule.setIsAntiCheating(0);
			examSchedule.setCompanyId(companyId);
			examSchedule.setSubCompanyId(subCompanyId);
			examScheduleDaoMapper.addExam(examSchedule);
			examScheduleId = examSchedule.getId();
		}
		//保存考试记录信息
		Integer assignId = examAssignInfoDaoMapper.getIdByConditions(1,examScheduleId,examId,userId);
		if(assignId == null){
			ExamAssignBean assignInfo = new ExamAssignBean();
			assignInfo.setRelationType(1);
			assignInfo.setRelationId(examScheduleId);
			assignInfo.setPaperId(examId);
			assignInfo.setUserId(userId);
			assignInfo.setPassScore((int) examPaper.getTotalScore()*passScorePercent/100);
			examAssignInfoDaoMapper.addAssignInfo(assignInfo);
			assignId = assignInfo.getId();
			returnMap.put("testTimes", 0);
		}else{
			ExamAssignBean assignInfo = examAssignInfoDaoMapper.getById(assignId);
			returnMap.put("testTimes", assignInfo.getTestTimes());
		}
		returnMap.put("examScheduleId", examScheduleId);
		returnMap.put("assignId", assignId);
		return returnMap;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.TrainService#updateTrainArrangeUserScore(com.jftt.wifi.bean.TrainArrangeUserBean) <BR>
	 * Method name: updateTrainArrangeUserScore <BR>
	 * Description: 修改培训人员的成绩 <BR>
	 * Remark: <BR>
	 * @param arrangeUser  <BR>
	*/
	@Override
	public void updateTrainArrangeUserScore(TrainArrangeUserBean arrangeUser) {
		trainArrangeUserDaoMapper.updateTrainArrangeUserScore(arrangeUser);
	}

	
}
