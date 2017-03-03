/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyMegagameService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.KnowledgeContestApplicationBean;
import com.jftt.wifi.bean.KnowledgeContestAwardBean;
import com.jftt.wifi.bean.KnowledgeContestContestBean;
import com.jftt.wifi.bean.KnowledgeContestMatchBean;
import com.jftt.wifi.bean.KnowledgeContestNewsBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.vo.ApplicationsSerchParam;
import com.jftt.wifi.bean.vo.KnowledgeContestContestVo;
import com.jftt.wifi.bean.vo.MegagameListParamVo;
import com.jftt.wifi.bean.vo.MegagameProcessInfoVo;
import com.jftt.wifi.bean.vo.MyApplicationsReturnVo;
import com.jftt.wifi.bean.vo.SearchJoinListParamsVo;
import com.jftt.wifi.bean.vo.SearchMegagameCheckParamVo;
import com.jftt.wifi.bean.vo.ShowWinAwardsVo;

/**
 * class name:MyMegagameService <BR>
 * class description: 我的大赛 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)chenrui
 */
public interface MyMegagameService {
	/**chenrui start*/
	/**
	 * 
	 * Method name: getMegagameList <BR>
	 * Description: 获取大赛列表信息 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return  List<KnowledgeContestContestBean><BR>
	 */
	public List<KnowledgeContestContestVo> getMegagameList(MegagameListParamVo paramVo) throws Exception;
	public int getMegagameListCount(MegagameListParamVo paramVo) throws Exception;
	/**
	 * 
	 * Method name: getMegagameInfoById <BR>
	 * Description: 查看大赛详细信息 根据id来查询对应的大赛信息 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @return  List<KnowledgeContestContestBean><BR>
	 */
	public KnowledgeContestContestBean getMegagameInfoById(String userId, String megagameId) throws Exception;
	/**
	 * 
	 * Method name: getAwardsSetting <BR>
	 * Description: 获取奖项设置 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  List<KnowledgeContestAwardBean><BR>
	 */
	public List<KnowledgeContestAwardBean> getAwardsSetting(String megagameId) throws Exception;
	/**
	 * 
	 * Method name: getMegagameProcessInfo <BR>
	 * Description: 获取大赛赛程安排信息<BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  List<MegagameProcessInfoVo><BR>
	 */
	public List<MegagameProcessInfoVo> getMegagameProcessInfo(String megagameId) throws Exception;
	/**
	 * 
	 * Method name: getShowWinAwards <BR>
	 * Description: 获奖公示-最终获奖者信息  <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  List<MegagameProcessInfoVo><BR>
	 */
	public List<ShowWinAwardsVo> getShowWinAwards(String megagameId) throws Exception;
	
	/**
	 * 
	 * Method name: getMatchMessageList <BR>
	 * Description: 获取比赛资讯列表 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return  List<KnowledgeContestNewsBean><BR>
	 */
	public List<KnowledgeContestNewsBean> getMatchMessageList(String megagameId,long fromNum,String pageSize) throws Exception;
	public int getMatchMessageListCount(String megagameId) throws Exception;
	/**
	 * 
	 * Method name: getMatchMessageById <BR>
	 * Description: 根据资讯ID获取资讯信息 <BR>
	 * Remark: <BR>
	 * @param messageId
	 * @return  KnowledgeContestNewsBean<BR>
	 */
	public KnowledgeContestNewsBean getMatchMessageById(String messageId) throws Exception;
	/**
	 * 
	 * Method name: getMyApplications <BR>
	 * Description: 我的报名获取 <BR>
	 * Remark: <BR>
	 * @return  KnowledgeContestApplicationBean<BR>
	 */
	public List<MyApplicationsReturnVo> getMyApplications(ApplicationsSerchParam serchParam) throws Exception;
	/**
	 * 
	 * Method name: getGradeInfoPublic <BR>
	 * Description: 获取成绩公示数据 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @param processId  void<BR>
	 */
	public List<ShowWinAwardsVo> getGradeInfoPublic(String userId,String processId,long fromNum,String pageSize) throws Exception;
	public int getGradeInfoPublicCount(String userId,String processId) throws Exception;
	/**
	 * 
	 * Method name: getJoinListByProcessId <BR>
	 * Description: 根据赛程id获取参赛列表信息  <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param processId
	 * @param fromNum
	 * @param pageSize
	 * @return  List<ShowWinAwardsVo><BR>
	 */
	public List<ShowWinAwardsVo> getJoinListByProcessId(SearchJoinListParamsVo paramsVo)throws Exception;
	public int getJoinListByProcessIdCount(SearchJoinListParamsVo paramsVo) throws Exception;
	/**
	 * 
	 * Method name: getMegagameCheckList <BR>
	 * Description: 批阅试卷大赛信息列表 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return  List<MegagameCheckReturnVo><BR>
	 */
	public List<KnowledgeContestContestVo> getMegagameCheckList(SearchMegagameCheckParamVo paramVo) throws Exception;
	public int getMegagameCheckListCount(SearchMegagameCheckParamVo paramVo) throws Exception;
	public int getMyApplicationsCount(ApplicationsSerchParam serchParam) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getJoinListByProcessId <BR>
	 * Description: 根据大赛id获取最后一个赛程信息 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return  KnowledgeContestMatchBean<BR>
	 */
	public KnowledgeContestMatchBean getJoinListByProcessId(String megagameId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMegagameProcessInfoById <BR>
	 * Description: 根据赛程id获取大赛赛程安排信息 <BR>
	 * Remark: <BR>
	 * @param processId
	 * @return
	 * @throws Exception  MegagameProcessInfoVo<BR>
	 */
	public MegagameProcessInfoVo getMegagameProcessInfoById(String processId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getAllMatchBymegagameId <BR>
	 * Description: 获取当前大赛下的所有赛程信息 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  List<KnowledgeContestMatchBean><BR>
	 */
	public List<KnowledgeContestMatchBean> getAllMatchBymegagameId(String megagameId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getPromoteInfoByProcessId <BR>
	 * Description: 根据赛程id获取当前赛程下的晋级者信息 <BR>
	 * Remark: <BR>
	 * @param processId
	 * @return
	 * @throws Exception  List<ShowWinAwardsVo><BR>
	 */
	public List<ShowWinAwardsVo> getPromoteInfoByProcessId(String processId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: isApply <BR>
	 * Description: 判断是否已报名 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @return  int<BR>
	 */
	public KnowledgeContestApplicationBean isApply(String userId, String megagameId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: doApply <BR>
	 * Description: 报名 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public void doApply(String userId, String megagameId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMyApplicationInfoById <BR>
	 * Description: 根据大赛id获取我报名的详细信息 <BR>
	 * Remark: <BR>
	 * @return
	 */
	public KnowledgeContestApplicationBean getMyApplicationInfoById(String id) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: checkJoinQualification <BR>
	 * Description: 验证当前用户的报名审批状态是否为通过 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @return  int<BR>
	 */
	public int checkJoinQualification(String userId, String megagameId)throws Exception;
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getAssignInfoByUserExamId <BR>
	 * Description: 根据用户 和考试id获取考试信息  <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param examId
	 * @return  ExamAssignBean<BR>
	 */
	public ExamAssignBean getAssignInfoByUserExamId(String userId, String examId)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: addExamAssignInfoByMatch <BR>
	 * Description: 添加examAssigninfo记录表信息 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param examId  void<BR>
	 */
	public void addExamAssignInfoByMatch(String userId, String examId)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: addJoinNotes <BR>
	 * Description: 添加参赛人员记录信息 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @param matchId  void<BR>
	 */
	public void addJoinNotes(String userId, String megagameId, String matchId)throws Exception;
	
	public int getLastPromoteInfo(String userId,String megagameId, String orderNum)throws Exception;
	public void updateStatusByTime() throws Exception;
	
	
	/**chenrui end*/
}
