/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KnowledgeContestMatchServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/13        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.KnowledgeContestMatchBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.ExamScheduleVo;
import com.jftt.wifi.bean.vo.ManagerContestMatchVo;
import com.jftt.wifi.bean.vo.SaveMatchParamVo;
import com.jftt.wifi.dao.ExamScheduleDaoMapper;
import com.jftt.wifi.dao.KnowledgeContestMatchDaoMapper;
import com.jftt.wifi.service.ExamService;
import com.jftt.wifi.service.KnowledgeContestMatchService;
import com.jftt.wifi.service.ManageUserService;

/**
 * @author JFTT)wangyifeng
 * class name:KnowledgeContestMatchServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/13
 */
@Service
public class KnowledgeContestMatchServiceImpl implements
		KnowledgeContestMatchService {
	@Autowired
	KnowledgeContestMatchDaoMapper matchDaoMapper;
	@Autowired
	private ExamScheduleDaoMapper examScheduleDaoMapper;
	@Autowired
	ExamService examService;
	@Autowired
	private ManageUserService manageUserService;

	// wangyifeng begin
	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestMatchService#addMatch(com.jftt.wifi.bean.KnowledgeContestMatchBean) <BR>
	 * Method name: addMatch <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param match  <BR>
	 * @throws ParseException 
	*/

	/*@Override
	public void addMatch(KnowledgeContestMatchBean match) throws ParseException {
		// TODO Auto-generated method stub
		examService.addExam(match.getExam());
		match.setExamId(match.getExam().getId());
		matchDaoMapper.addMatch(match);
	}*/

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestMatchService#deleteMatch(java.lang.Integer) <BR>
	 * Method name: deleteMatch <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id  <BR>
	*/

	@Override
	public void deleteMatch(Integer id) {
		// TODO Auto-generated method stub
		examService.deleteExams(id);
		matchDaoMapper.realDeleteMatch(id);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestMatchService#modifyMatch(com.jftt.wifi.bean.KnowledgeContestMatchBean) <BR>
	 * Method name: modifyMatch <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param match  <BR>
	 * @throws ParseException 
	*/

	/*@Override
	public void modifyMatch(KnowledgeContestMatchBean match) throws ParseException {
		// TODO Auto-generated method stub
		// 1.Delete old match
		deleteMatch(match.getId());
		// 2.Add modified match as new match
		addMatch(match);
	}*/

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestMatchService#getMatch(java.lang.Integer) <BR>
	 * Method name: getMatch <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/

	@Override
	public KnowledgeContestMatchBean getMatch(Integer id) {
		// TODO Auto-generated method stub
		return matchDaoMapper.getMatch(id);
	}
	// wangyifeng end

	
	
	/**chenrui start*/
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeContestMatchService#getAllMatchInfo(java.lang.String) <BR>
	 * Method name: getAllMatchInfo <BR>
	 * Description: 获取当前大赛下的所有赛程信息及赛程中考试、试卷的基本信息 <BR>
	 * Remark: <BR>
	 * @param messageId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<ManagerContestMatchVo> getAllMatchInfo(String megagameId) throws Exception {
		List<ManagerContestMatchVo> list = new ArrayList<ManagerContestMatchVo>();
		/*
		 * 当前大赛下所有赛程
		 */
		List<KnowledgeContestMatchBean> temList = matchDaoMapper.getAllMatchBymegagameId(megagameId);
		ManagerContestMatchVo matchVo = null;
		for(KnowledgeContestMatchBean bean : temList){
			matchVo = new ManagerContestMatchVo();
			int examId = bean.getExamId();
			ExamScheduleVo vo = examScheduleDaoMapper.selectExamScheduleInfo(examId);
			String scoreMarkerIds = vo.getScoreMarker();//获取批阅人id
			String ids[] = scoreMarkerIds.split(",");
			String scoreMarkerNames = "";
			for(String id : ids){
				ManageUserBean userBean = manageUserService.findUserById(id);
				if(userBean==null){
					continue;
				}
				scoreMarkerNames +=userBean.getName();
			}
			matchVo.setId(bean.getId());
			matchVo.setContestId(bean.getContestId());
			matchVo.setOrderNum(bean.getOrderNum());
			matchVo.setName(bean.getName());
			matchVo.setContent(bean.getContent());
			matchVo.setExamId(examId);
			matchVo.setRule(bean.getRule());
			matchVo.setPromotionNumber(bean.getPromotionNumber());
			matchVo.setStatus(bean.getStatus());
			matchVo.setExamVo(vo);
			matchVo.setScoreMarkerName(scoreMarkerNames);
			list.add(matchVo);
		}
		return list;
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeContestMatchService#save2Match(com.jftt.wifi.bean.vo.SaveMatchParamVo) <BR>
	 * Method name: save2Match <BR>
	 * Description: 新增赛程 <BR>
	 * Remark: <BR>
	 * @param params
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void save2Match(SaveMatchParamVo params) throws Exception {
		String userId = params.getUserId();
		ManageUserBean userBean = manageUserService.findUserById(userId);
		params.setCompanyId(userBean.getCompanyId()+"");
		params.setSubCompanyId(userBean.getSubCompanyId()+"");
		String operateType = params.getOperateType();
		if("1".equals(operateType)){//新增
			/*
			 * 插入考试记录
			 */
			examScheduleDaoMapper.save2ExamByMatch(params);
			/*
			 * 插入赛程记录
			 */
			
			//获取当前的matchOrderNum
			int count = matchDaoMapper.getCountByMegagame(params.getMegagameId());
			count++;
			params.setMatchOrderNum(count);
			matchDaoMapper.add2Match(params);
		}else if("2".equals(operateType)){//修改
			/*
			 * 更新考试记录
			 */
			examScheduleDaoMapper.update2ExamByMatch(params);
			/*
			 * 更新赛程记录
			 */
			matchDaoMapper.update2Match(params);
		}
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeContestMatchService#getExamInfoForMatch(java.lang.String) <BR>
	 * Method name: 根据考试id获取赛程关联的考试信息<BR>
	 * Description:  <BR>
	 * Remark: <BR>
	 * @param examId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public ExamScheduleVo getExamInfoForMatch(String examId) throws Exception {
		ExamScheduleVo vo = examScheduleDaoMapper.selectExamScheduleInfo(Integer.parseInt(examId));
		String scoreMarkerId = vo.getScoreMarker();
		String scoreMarkerName="";
		String[] scoreMarkerIdArr = scoreMarkerId.split(",");
		for(String userId:scoreMarkerIdArr){
			ManageUserBean uBean = manageUserService.findUserById(userId);
			if(uBean!=null){
				scoreMarkerName = uBean.getName();
				scoreMarkerName +=",";
			}
		}
		if(!"".equals(scoreMarkerName)){
			scoreMarkerName = scoreMarkerName.substring(0,scoreMarkerName.length()-1);
		}
		vo.setScoreMarkerName(scoreMarkerName);
		return vo;
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeContestMatchService#getMatchCounts(java.lang.String) <BR>
	 * Method name: 获取大赛下赛程数目 <BR>
	 * Description:  <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public int getMatchCounts(String megagameId) throws Exception {
		return matchDaoMapper.getMatchCounts(megagameId);
	}
	
	/**chenrui end*/
}
