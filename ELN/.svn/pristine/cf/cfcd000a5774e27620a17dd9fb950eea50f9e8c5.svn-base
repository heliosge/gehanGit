/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KnowledgeContestApplicationServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/13        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jftt.wifi.bean.KnowledgeContestApplicationBean;
import com.jftt.wifi.bean.KnowledgeContestMatchBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamScheduleBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.knowledge_contest.ContestApplicationListItemVo;
import com.jftt.wifi.bean.knowledge_contest.ContestApplicationSearchOption;
import com.jftt.wifi.bean.vo.ExamGradeVo;
import com.jftt.wifi.dao.ExamAssignInfoDaoMapper;
import com.jftt.wifi.dao.KnowledgeContestApplicationDaoMapper;
import com.jftt.wifi.service.ExamPaperService;
import com.jftt.wifi.service.ExamService;
import com.jftt.wifi.service.KnowledgeContestApplicationService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.MyMegagameService;

/**
 * @author JFTT)wangyifeng
 * class name:KnowledgeContestApplicationServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/13
 */
@Service
public class KnowledgeContestApplicationServiceImpl implements
		KnowledgeContestApplicationService {
	@Autowired
	KnowledgeContestApplicationDaoMapper applicationDaoMapper;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	@Autowired
	private MyMegagameService myMegagameService;
	@Autowired
	private ExamService examService;
	@Autowired
	private ExamPaperService examPaperService;
	@Autowired
	private ExamAssignInfoDaoMapper assignInfoDaoMapper;
	// wangyifeng begin
	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestApplicationService#approve(com.jftt.wifi.bean.KnowledgeContestApplicationBean) <BR>
	 * Method name: approve <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param application  <BR>
	*/

	@Override
	public void approve(KnowledgeContestApplicationBean application) {
		// TODO Auto-generated method stub
		applicationDaoMapper.approve(application);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: approveApplication <BR>
	 * Description: 审批报名-批量或单个 <BR>
	 * Remark: <BR>
	 * @param applyIds
	 * @param refuseReason
	 * @param approveStatus  void<BR>
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public void approveApplication(String applyIds,String contestIds,String applyUserIds,String refuseReason,int approveStatus) throws Exception{
		String[] ids = applyIds.split(",");
		String[] contests = contestIds.split(",");
		String[] applyUsers = applyUserIds.split(",");
		for(int i=0;i<ids.length;i++){
			KnowledgeContestApplicationBean application = new KnowledgeContestApplicationBean();
			application.setId(Integer.parseInt(ids[i]));
			if(approveStatus == 3){
				application.setRefuseReason(refuseReason);
			}
			if(approveStatus == 2){//审批通过
				//根据大赛id获取赛程信息
				 List<KnowledgeContestMatchBean> list = myMegagameService.getAllMatchBymegagameId(contests[i]);
				 for(KnowledgeContestMatchBean kb : list){
					 int examId = kb.getExamId();//获取赛程绑定的考试id
					 ExamScheduleBean examScheduleBean = examService.getExam(examId);
					 //根据考试id获取绑定的试卷id
					 int paperId = examScheduleBean.getPaperId();
					 //根据试卷id获取试卷信息
					  PaperBean paperBean = examPaperService.getPaper(paperId);
					  int passScorePercent = examScheduleBean.getPassScorePercent();
					  int passScore = (int) (paperBean.getTotalScore()*((double)passScorePercent/100));
					 //插入信息=>考试分配表
					  ExamAssignBean assignBean = new ExamAssignBean();
					  assignBean.setRelationType(1);
					  assignBean.setRelationId(examId);
					  assignBean.setPaperId(paperId);
					  assignBean.setUserId(Integer.parseInt(applyUsers[i]));
					  assignBean.setExamStatus(1);
					  assignBean.setTestTimes(0);
					  assignBean.setIsPassed(false);
					  assignBean.setIsMarked(false);
					  assignBean.setPassScore(passScore);
					  assignBean.setLeaveTimes(examScheduleBean.getAllowTimes());
					  assignInfoDaoMapper.addAssignInfo(assignBean);
				 }
			}
			application.setApprovalStatus(approveStatus);
			approve(application);
		}
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestApplicationService#deleteApplication(java.lang.Integer) <BR>
	 * Method name: deleteApplication <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id  <BR>
	*/

	@Override
	public void deleteApplication(Integer id) {
		// TODO Auto-generated method stub
		applicationDaoMapper.realDeleteApplication(id);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestApplicationService#getAppliationVoList(com.jftt.wifi.bean.knowledge_contest.ContestApplicationSearchOption) <BR>
	 * Method name: getAppliationVoList <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	 * @throws Exception 
	*/

	@Override
	public List<ContestApplicationListItemVo> getAppliationVoList(ContestApplicationSearchOption searchOption) throws Exception {
		String name = searchOption.getName();
		Map<String, Object> map =new HashMap<String, Object>();
		if(name!=null && !"".equals(name)){
			map.put("name", name);
		}
		List<ContestApplicationListItemVo> list = new ArrayList<ContestApplicationListItemVo>();
		if(map.size()>0){
			List<ManageUserBean> userList  = manageUserService.findUserByListCondition(map);
			if(userList==null || userList.size()==0){
				return list;
			}
			searchOption.setUserList(userList);
		}
		List<ContestApplicationListItemVo> resultList = applicationDaoMapper.getAppliationVoList(searchOption);
		for(ContestApplicationListItemVo tempvo : resultList){
			int userId = tempvo.getUserId();
			ManageUserBean userBean = manageUserService.findUserById(userId+"");
			String name2 = "暂无";
			if(userBean != null){
				name2 = userBean.getName();
			}
			tempvo.setUserName(name2);
			list.add(tempvo);
		}
		return list;
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestApplicationService#getAppliationTotalCount(com.jftt.wifi.bean.knowledge_contest.ContestApplicationSearchOption) <BR>
	 * Method name: getAppliationTotalCount <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	 * @throws Exception 
	*/

	@Override
	public Integer getAppliationTotalCount(
			ContestApplicationSearchOption searchOption) throws Exception {
		// TODO Auto-generated method stub
		String name = searchOption.getName();
		Map<String, Object> map =new HashMap<String, Object>();
		if(name!=null && !"".equals(name)){
			map.put("name", name);
		}
		if(map.size()>0){
			List<ManageUserBean> userList  = manageUserService.findUserByListCondition(map);
			if(userList==null || userList.size()==0){
				return 0;
			}
			searchOption.setUserList(userList);
		}
		return applicationDaoMapper.getAppliationTotalCount(searchOption);
	}
	// wangyifeng end
}
