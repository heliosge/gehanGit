/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MatchJoinUserDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-22        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.MatchJoinUserBean;
import com.jftt.wifi.bean.vo.SearchJoinListParamsVo;
import com.jftt.wifi.bean.vo.ShowWinAwardsVo;

/**
 * 
 * class name:MatchJoinUserDaoMapper <BR>
 * class description: 参赛用户表 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-22
 * @author JFTT)chenrui
 */
public interface MatchJoinUserDaoMapper {
	/**
	 * 
	 * Method name: getById <BR>
	 * Description: 根据id获取对应实体<BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  MatchJoinUserBean<BR>
	 */
	public MatchJoinUserBean getById(@Param("id")long id) throws Exception;
	/**
	 * 
	 * Method name: getGradeInfoPublic <BR>
	 * Description: 获取成绩公示数据 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @param processId  void<BR>
	 */
	public List<ShowWinAwardsVo> getGradeInfoPublic( @Param("processId")String processId,
			@Param("fromNum")long fromNum,@Param("pageSize")String pageSize,@Param("isAutoMark")int isAutoMark) throws Exception;
	public int getGradeInfoPublicCount(@Param("processId")String processId) throws Exception;
	/**
	 * 
	 * Method name: getJoinListByProcessId <BR>
	 * Description: 根据赛程id获取参赛列表信息 <BR>
	 * Remark: <BR>
	 * @param processId
	 * @param fromNum
	 * @param pageSize
	 * @return  List<ShowWinAwardsVo><BR>
	 */
	public List<ShowWinAwardsVo> getJoinListByProcessId(SearchJoinListParamsVo paramsVo) throws Exception;
	public int getJoinListByProcessIdCount(SearchJoinListParamsVo paramsVo) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getShowWinAwards <BR>
	 * Description: 获取获奖信息 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @param lastMatchId
	 * @param examId
	 * @return
	 * @throws Exception  List<ShowWinAwardsVo><BR>
	 */
	public List<ShowWinAwardsVo> getShowWinAwards(@Param("megagameId")String megagameId, @Param("processId")int lastMatchId, 
			@Param("examId")int examId) throws Exception;
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
	public List<ShowWinAwardsVo> getPromoteInfoByProcessId(@Param("processId")String processId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: addJoinNotes <BR>
	 * Description:  添加参赛人员记录信息 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @param matchId  void<BR>
	 */
	public void addJoinNotes(@Param("userId")String userId, @Param("megagameId")String megagameId, 
			@Param("matchId")String matchId) throws Exception;
	public int checkExistJoinNote(@Param("userId")String userId, @Param("matchId")String matchId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: updatePromoteStatus <BR>
	 * Description: 更新参赛人员晋升状态 <BR>
	 * Remark: <BR>
	 * @param x_userId
	 * @param x_matchId
	 * @param x_promoteStatus
	 * @throws Exception  void<BR>
	 */
	public void updatePromoteStatus(@Param("userId")int x_userId, @Param("matchId")String x_matchId,
			@Param("isPromote")int x_promoteStatus) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getLastPromoteInfo <BR>
	 * Description: 获取赛程的晋级状态 <BR>
	 * Remark: <BR>
	 * @param matchId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public Integer getLastPromoteInfo(@Param("userId")String userId,@Param("matchId")int matchId) throws Exception;
	
}