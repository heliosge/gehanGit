/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/06        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageGroupBean;
import com.jftt.wifi.bean.ManageNoticeBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamScheduleBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.exam.vo.ExamSearchOptionVo;
import com.jftt.wifi.bean.exam.vo.OfficialExamListItemVo;
import com.jftt.wifi.bean.exam.vo.SimExamListItemVo;
import com.jftt.wifi.bean.vo.ManageGroupBeanVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.ExamAssignInfoDaoMapper;
import com.jftt.wifi.dao.ExamPaperDaoMapper;
import com.jftt.wifi.dao.ExamScheduleDaoMapper;
import com.jftt.wifi.dao.ManageGroupStudentDaoMapper;
import com.jftt.wifi.service.ExamService;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManageGroupService;
import com.jftt.wifi.service.ManageNoticeService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.ExamUtils;

/**
 * class name:ExamServiceImpl <BR>
 * class description: 考试服务实现类 <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/06
 * @author JFTT)wangyifeng
 */
@Service
public class ExamServiceImpl implements ExamService {
	@Autowired
	private ExamScheduleDaoMapper examScheduleDaoMapper;
	@Autowired
	private ExamAssignInfoDaoMapper examAssignInfoDaoMapper;
	@Autowired
	private ExamPaperDaoMapper paperDaoMapper;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	@Resource(name="manageGroupStudentDaoMapper")
	private ManageGroupStudentDaoMapper manageGroupStudentDaoMapper;
	@Resource(name="manageNoticeService")
	private ManageNoticeService manageNoticeService;

	// wangyifeng begin

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamService#addExam(com.jftt.wifi.bean.exam.ExamScheduleBean) <BR>
	 *      Method name: addExam <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param newExam
	 * @return <BR>
	 * @throws Exception 
	 */

	@Override
	@Transactional
	public Integer addExam(ExamScheduleBean newExam,ManageUserBean userBean) throws Exception {
		newExam.setSubCompanyId(userBean.getSubCompanyId());
		newExam.setCompanyId(userBean.getCompanyId());
		newExam.setIsDeleted(false);
		newExam.setIsScorePublished(false);
		newExam.setIsMarked(0);
		validateExam(newExam);
		examScheduleDaoMapper.addExam(newExam);
		Integer resultId = newExam.getId();
		addAssignInfo(newExam);
		if(newExam.getIsPublished() == 1){
			List<ManageUserBean> list = newExam.getUserList();
			if(!list.isEmpty() && list.get(0) != null){
				List<String> idList = new ArrayList<String>();
				for(int i=0;i<list.size();i++){
					idList.add(list.get(i).getId());
				}
				list = manageUserService.findUserByIds(idList);
			}
			ExamQueryConditionBean examBean = examScheduleDaoMapper.selectExamInfoById(newExam.getId());
			ManageNoticeBean noticeBean = new ManageNoticeBean();
			noticeBean.setSendUserId(Integer.parseInt(userBean.getId()));
			noticeBean.setSendUserName(userBean.getName());
			noticeBean.setIsSystem(1);//系统消息
			noticeBean.setIsRead(2);//未读
			noticeBean.setSendDeleteFlag(1);
			noticeBean.setRecDeleteFlag(1);
			for(int i=0;i<list.size();i++){
				noticeBean.setTitle("参加考试通知");
				noticeBean.setContent("尊敬的"+list.get(i).getName()
										+"：<em>您有新的考试需要参加，详情如下：</em><br/>"
										+"<em>考试名称： " + examBean.getTitle()+"</em><br/>"
										+"<em>考试有效时间："+examBean.getBegin_time()+"~"+examBean.getEnd_time()+"</em><br/>"
										+"<em>考试时长：" + examBean.getDuration()+"</em><br/>"
										+"<em>请在有效时间内及时参加考试！"+"</em><br/>");
				noticeBean.setRecUserId(Integer.parseInt(list.get(i).getId()));
				manageNoticeService.insertNotice(noticeBean);
			}
		}
		return resultId;
	}

	/**
	 * Method name: addAssignInfo <BR>
	 * Description: 新增考试分配信息 <BR>
	 * Remark: <BR>
	 * 
	 * @param newExam
	 *            void<BR>
	 */
	@Transactional
	private void addAssignInfo(ExamScheduleBean newExam) {
		Assert.notNull(newExam.getPaperId());
		PaperBean paper = paperDaoMapper.getPaper(newExam.getPaperId());
		Assert.notNull(paper.getTotalScore());
		Integer passScore = null;
		if (newExam.getPassScorePercent() != null) {
			passScore = ExamUtils.calculatePassScore(paper.getTotalScore(),
					newExam.getPassScorePercent());
		}
		if(!newExam.getUserList().isEmpty() && newExam.getUserList().get(0) != null){
			for (ManageUserBean userBean : newExam.getUserList()) {
				// 使用assignedUserIdList及考试的相关属性来初始化ExamAssign对象<br>
				// 用于保存考试分配信息
				ExamAssignBean newAssignInfo = ExamAssignBean.generateAssignInfo(newExam, Integer.parseInt(userBean.getId()), passScore);
				examAssignInfoDaoMapper.addAssignInfo(newAssignInfo);
			}
		}
	}

	/**
	 * Method name: validateExam <BR>
	 * Description: 验证Exam实例 <BR>
	 * Remark: <BR>
	 * 
	 * @param newExam
	 *            void<BR>
	 * @throws ParseException 
	 */
	private void validateExam(ExamScheduleBean newExam) throws ParseException {
		Assert.notNull(newExam.getType());
		if (newExam.getExamType().getNeedBeginEndTime()) {
			Assert.notNull(newExam.getBeginTime());
			Assert.notNull(newExam.getEndTime());
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			/*Assert.isTrue(format.parse(newExam.getBeginTime()).after(new Date()),
					"Begin time should after current time in server.");
			Assert.isTrue(format.parse(newExam.getBeginTime()).before(format.parse(newExam.getEndTime())),
					"Begin time should before end time.");*/
		}
		//Assert.notNull(newExam.getAssignedUserIdList());
		//Assert.notEmpty(newExam.getAssignedUserIdList());
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamService#deleteExams(java.util.List) <BR>
	 *      Method name: deleteExams <BR>
	 *      Description: 删除考试，单个或多个 <BR>
	 *      Remark: <BR>
	 * @param idList
	 * <BR>
	 */

	@Override
	@Transactional
	public void deleteExams(Integer... idList) {
		Assert.notNull(idList);
		for (Integer id : idList) {
			examScheduleDaoMapper.deleteExam(id);
			// TODO wangyifeng
			// Ignore assign info for now.

			// Delete assgin info
			// examAssignInfoDaoMapper.realDeleteAssignInfo(
			// ExamRelationType.COMMON, id);
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamService#modifyExam(com.jftt.wifi.bean.exam.ExamScheduleBean) <BR>
	 *      Method name: modifyExam <BR>
	 *      Description: 修改考试 <BR>
	 *      Remark: <BR>
	 * @param modifiedExam
	 * <BR>
	 * @throws ParseException 
	 */

	@Override
	@Transactional
	public void modifyExam(ExamScheduleBean modifiedExam) throws ParseException {
		Assert.notNull(modifiedExam.getId());
		//validateExam(modifiedExam);
		examScheduleDaoMapper.modifyExam(modifiedExam);

		// Remove old relation of user and exam
		examAssignInfoDaoMapper.realDeleteAssignInfo(
				modifiedExam.getExamRelationType(), modifiedExam.getId());
		// Add new relation of user and exam
		addAssignInfo(modifiedExam);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamService#publishExam(java.lang.Integer) <BR>
	 *      Method name: publishExam <BR>
	 *      Description: 发布考试 <BR>
	 *      Remark: <BR>
	 * @param examId
	 * <BR>
	 * @throws Exception 
	 */

	@Override
	@Transactional
	public void publishExam(Integer examId,ManageUserBean bean) throws Exception {
		examScheduleDaoMapper.publishExam(examId);
		//发站内信
		List<String> idList = examAssignInfoDaoMapper.selectUserIdsByRelationId(examId);
		List<ManageUserBean> list = new ArrayList<ManageUserBean>();
		if(idList != null){
//			List<String> idList = new ArrayList<String>();
//			String[] idArray = StringUtils.split(userIds,",");
//			for(int i=0;i<idArray.length;i++){
//				idList.add(idArray[i]);
//			}
			list = manageUserService.findUserByIds(idList);
		}
		ExamQueryConditionBean examBean = examScheduleDaoMapper.selectExamInfoById(examId);
		ManageNoticeBean noticeBean = new ManageNoticeBean();
		noticeBean.setSendUserId(Integer.parseInt(bean.getId()));
		noticeBean.setSendUserName(bean.getName());
		noticeBean.setIsSystem(1);//系统消息
		noticeBean.setIsRead(2);//未读
		noticeBean.setSendDeleteFlag(1);
		noticeBean.setRecDeleteFlag(1);
		for(int i=0;i<list.size();i++){
			noticeBean.setTitle("参加考试通知");
			noticeBean.setContent("尊敬的"+list.get(i).getName()
									+"：<em>您有新的考试需要参加，详情如下：</em><br/>"
									+"<em>考试名称： " + examBean.getTitle()+"</em><br/>"
									+"<em>考试有效时间："+examBean.getBegin_time()+"~"+examBean.getEnd_time()+"</em><br/>"
									+"<em>考试时长：" + examBean.getDuration()+"</em><br/>"
									+"<em>请在有效时间内及时参加考试！"+"</em><br/>");
			noticeBean.setRecUserId(Integer.parseInt(list.get(i).getId()));
			manageNoticeService.insertNotice(noticeBean);
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamService#cancelExam(java.lang.Integer) <BR>
	 *      Method name: cancelExam <BR>
	 *      Description: 取消考试 <BR>
	 *      Remark: <BR>
	 * @param examId
	 * <BR>
	 */

	@Override
	@Transactional
	public void cancelExam(Integer examId) {
		examScheduleDaoMapper.cancelExam(examId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamService#getExam(java.lang.Integer) <BR>
	 *      Method name: getExam <BR>
	 *      Description: 获取考试信息 <BR>
	 *      Remark: <BR>
	 * @param examId
	 * @return <BR>
	 * @throws Exception 
	 */

	@Override
	@Transactional
	public ExamScheduleBean getExam(Integer examId) throws Exception {
		ExamScheduleBean bean = examScheduleDaoMapper.getExam(examId);
		List<String> idList = new ArrayList<String>();
		if(bean.getScoreMarker() != null){
			String[] ids = bean.getScoreMarker().split(",");
			for(int i=0;i<ids.length;i++){
				idList.add(ids[i]);
			}
		}
		List<ManageUserBean> userList = manageUserService.findUserByIds(idList);
		String scoreMarkerName = "";
		for(int i=0;i<userList.size();i++){
			if(userList.get(i) != null){
				scoreMarkerName += userList.get(i).getUserName();
				if(i != userList.size() - 1){
					scoreMarkerName += ",";
				}
			}
		}
		bean.setScoreMarkerName(scoreMarkerName);
		return bean;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamService#getOfficialVoList(com.jftt.wifi.bean.exam.vo.ExamSearchOptionVo) <BR>
	 *      Method name: getOfficialVoList <BR>
	 *      Description: 获取考试安排列表 <BR>
	 *      Remark: <BR>
	 * @param searchOption
	 * @return <BR>
	 */

	@Override
	public List<OfficialExamListItemVo> getOfficialVoList(
			ExamSearchOptionVo searchOption) {
		return examScheduleDaoMapper.getOfficialVoList(searchOption);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamService#getSimVoList(com.jftt.wifi.bean.exam.vo.ExamSearchOptionVo) <BR>
	 *      Method name: getSimVoList <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param searchOption
	 * @return <BR>
	 */

	@Override
	public List<SimExamListItemVo> getSimVoList(ExamSearchOptionVo searchOption) {
		return examScheduleDaoMapper.getSimVoList(searchOption);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamService#getTotalCount(com.jftt.wifi.bean.exam.vo.ExamSearchOptionVo) <BR>
	 *      Method name: getTotalCount <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param searchOption
	 * @return <BR>
	 */

	@Override
	public Integer getTotalCount(ExamSearchOptionVo searchOption) {
		return examScheduleDaoMapper.getTotalCount(searchOption);
	}
	// wangyifeng end

	
	//zhangchen start
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ExamService#getUserIdByGroupId(int) <BR>
	 * Method name: getUserIdByGroupId <BR>
	 * Description: 根据群组ID查询用户ID <BR>
	 * Remark: <BR>
	 * @param groupId
	 * @return  <BR>
	 */
	
	@Override
	public String getUserIdByGroupId(int groupId) {
		// TODO Auto-generated method stub
		return manageGroupStudentDaoMapper.selectUserIdByGroupId(groupId);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ExamService#getAssignInfoByRelationId(int) <BR>
	 * Method name: getAssignInfoByRelationId <BR>
	 * Description: 根据考试ID查询用户ID <BR>
	 * Remark: <BR>
	 * @param relation_id
	 * @return  <BR>
	 * @throws Exception 
	*/
	
	@Override
	@Transactional
	public List<ManageUserBean> getAssignInfoByRelationId(int relation_id,String examState) throws Exception {
		// TODO Auto-generated method stub
		//List<ExamAssignBean> list = examAssignInfoDaoMapper.selectUserByRelationId(relation_id);
		List<String> idList = examAssignInfoDaoMapper.selectUserIdsByRelationId(relation_id);
		if(idList != null){
//			String[] idArray = StringUtils.split(userIds, ",");
//			List<String> idList = new ArrayList<String>();
//			for(int i=0;i<idArray.length;i++){
//				idList.add(idArray[i]);
//			}
			Map map = new HashMap();
			map.put("id", idList);
			List<ManageUserBean> userList = manageUserService.findUserByListCondition(map);
			System.out.println("length:"+userList.size());
			if("BEFORE_START".equals(examState) || "PUBLISHED".equals(examState)){
				for(int i=0;i<userList.size();i++){
					userList.get(i).setIsEdit("1");
				}
			}
			
			if(userList !=null && !userList.isEmpty()){
				//考试次数
				for(int i=0;i<userList.size();i++){
					
					Integer num = examAssignInfoDaoMapper.getUserTestNum(relation_id, Long.parseLong(userList.get(i).getId()));
					
					if(num !=null){
						userList.get(i).setField100(num+"");
					}
				}
			}
			
			return userList;
		}else{
			return null;
		}
		
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ExamService#getTitleCount(java.lang.String, java.lang.String) <BR>
	 * Method name: getTitleCount <BR>
	 * Description: 验证考试名称唯一性 <BR>
	 * Remark: <BR>
	 * @param examId
	 * @param title
	 * @return  <BR>
	*/
	
	@Override
	public int getTitleCount(ExamScheduleBean bean) {
		// TODO Auto-generated method stub
		return examScheduleDaoMapper.selectTitleCount(bean);
	}
	
	//zhangchen end
}
