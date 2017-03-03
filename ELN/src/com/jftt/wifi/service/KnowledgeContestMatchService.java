/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KnowledgeContestMatchService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/13        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service;

import java.text.ParseException;
import java.util.List;

import com.jftt.wifi.bean.KnowledgeContestMatchBean;
import com.jftt.wifi.bean.vo.ExamScheduleVo;
import com.jftt.wifi.bean.vo.ManagerContestMatchVo;
import com.jftt.wifi.bean.vo.SaveMatchParamVo;

/**
 * @author JFTT)wangyifeng
 * class name:KnowledgeContestMatchService <BR>
 * class description: 大赛赛程管理接口 <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/13
 */
public interface KnowledgeContestMatchService {
	// wangyifeng begin
	/**
	 * @author JFTT)wangyifeng
	 * Method name: addMatch <BR>
	 * Description: 新增赛程 <BR>
	 * Remark: <BR>
	 * @param match  void<BR>
	 * @throws ParseException 
	 */
	//void addMatch(KnowledgeContestMatchBean match) throws ParseException;

	/**
	 * @author JFTT)wangyifeng
	 * Method name: deleteMatch <BR>
	 * Description: 删除赛程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void deleteMatch(Integer id);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: modifyMatch <BR>
	 * Description: 修改赛程 <BR>
	 * Remark: <BR>
	 * @param match  void<BR>
	 * @throws ParseException 
	 */
	//void modifyMatch(KnowledgeContestMatchBean match) throws ParseException;

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getMatch <BR>
	 * Description: 获取赛程详细 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  KnowledgeContestMatchBean<BR>
	 */
	KnowledgeContestMatchBean getMatch(Integer id);
	// wangyifeng end
	/**chenrui start*/
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getAllMatchInfo <BR>
	 * Description: 获取当前大赛下的所有赛程信息及赛程中考试、试卷的基本信息 <BR>
	 * Remark: <BR>
	 * @param messageId
	 * @return
	 * @throws Exception  List<ManagerContestMatchVo><BR>
	 */
	public List<ManagerContestMatchVo> getAllMatchInfo(String messageId)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: save2Match <BR>
	 * Description: 新增赛程 <BR>
	 * Remark: <BR>
	 * @param params  void<BR>
	 */
	public void save2Match(SaveMatchParamVo params) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getExamInfoForMatch <BR>
	 * Description: 根据考试id获取赛程关联的考试信息 <BR>
	 * Remark: <BR>
	 * @param examId
	 * @return  ExamScheduleVo<BR>
	 */
	ExamScheduleVo getExamInfoForMatch(String examId)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMatchCounts <BR>
	 * Description: 获取大赛下赛程数目 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return  int<BR>
	 */
	public int getMatchCounts(String megagameId)throws Exception;
	/**chenrui end*/




}
