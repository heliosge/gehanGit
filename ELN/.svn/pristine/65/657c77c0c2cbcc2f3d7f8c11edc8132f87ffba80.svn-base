/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KnowledgeContestService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/12        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.knowledge_contest.ContestBean;
import com.jftt.wifi.bean.vo.AddCompetitionVo;
import com.jftt.wifi.bean.vo.GetAllPeopleByPostVo;
import com.jftt.wifi.bean.vo.KnowledgeContestContestVo;
import com.jftt.wifi.bean.vo.MegagameListParamVo;
import com.jftt.wifi.bean.vo.QualificationVo;

/**
 * @author JFTT)wangyifeng
 * class name:KnowledgeContestService <BR>
 * class description: 大赛基本信息管理接口 <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/12
 */
public interface KnowledgeContestService {
	// wangyifeng begin
	/**
	 * @author JFTT)wangyifeng
	 * Method name: addContest <BR>
	 * Description: 新增大赛 <BR>
	 * Remark: <BR>
	 * @param contest  void<BR>
	 */
	void addContest(ContestBean contest);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: deleteContest <BR>
	 * Description: 删除大赛 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void deleteContest(Integer id);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: modifyContest <BR>
	 * Description: 修改大赛 <BR>
	 * Remark: <BR>
	 * @param contest  void<BR>
	 */
	void modifyContest(ContestBean contest);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: publishContest <BR>
	 * Description: 发布大赛 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void publishContest(Integer id);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: startNewMatchOrEndContest <BR>
	 * Description: 开启新赛程 或 结束比赛 <BR>
	 * Remark: <BR>
	 * @param contestId  void<BR>
	 */
	void startNewMatchOrEndContest(Integer contestId);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getContest <BR>
	 * Description: 获取大赛详情 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ContestBean<BR>
	 */
	ContestBean getContest(Integer id);

	// wangyifeng end
	
	/**chenrui start*/
	
	/**
	 * @author JFTT)chenrui
	 * Method name: getContestVoList <BR>
	 * Description: 管理员-获取大赛列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 */
	List<KnowledgeContestContestVo> getContestVoList(MegagameListParamVo paramVo) throws Exception ;

	/**
	 * @author JFTT)chenrui
	 * Method name: getContestTotalCount <BR>
	 * Description: 管理员-获取大赛总数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  Integer<BR>
	 */
	Integer getContestTotalCount(MegagameListParamVo paramVo) throws Exception ;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: addCompetition <BR>
	 * Description: 大赛新增 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @throws Exception  void<BR>
	 */
	public Integer addCompetition(AddCompetitionVo paramVo) throws Exception ;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getAllPeopleByPost <BR>
	 * Description: 根据选择的岗位获取人员信息 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return
	 */
	public List<ManageUserBean> getAllPeopleByDept(GetAllPeopleByPostVo paramsVo) throws Exception ;
	public int getAllPeopleByDeptCount(GetAllPeopleByPostVo paramsVo) throws Exception ;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getQualification <BR>
	 * Description: 大赛参赛资格获取 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return  QualificationVo<BR>
	 */
	public List<QualificationVo> getQualification(String megagameId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: checkMegagameNameIsOnly <BR>
	 * Description: 新增大赛--验证大赛名称的唯一性 <BR>
	 * Remark: <BR>
	 * @param name
	 * @return  int<BR>
	 */
	public int checkMegagameNameIsOnly(String name,String userId,String megagameId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: checkMatchNameIsOnly <BR>
	 * Description: 新增赛程--验证赛程名称的唯一性 <BR>
	 * Remark: <BR>
	 * @param name
	 * @param userId
	 * @return  int<BR>
	 */
	public int checkMatchNameIsOnly(String name, String userId,String matchId,String megagameId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: doPublish <BR>
	 * Description: 大赛发布 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @throws Exception  void<BR>
	 */
	public void doPublish(String megagameId,String currentUserId)throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAllChildNodes <BR>
	 * Description: getAllChildNodes <BR>
	 * Remark: <BR>
	 * @param deptId
	 * @param result
	 * @return
	 * @throws Exception  List<Integer><BR>
	 */
	List<Integer> getAllChildNodes(int deptId, List<Integer> result)
			throws Exception;
	
	/**chenrui end*/

}
