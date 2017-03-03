/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KnowledgeContestApplicationService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/13        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.KnowledgeContestApplicationBean;
import com.jftt.wifi.bean.knowledge_contest.ContestApplicationListItemVo;
import com.jftt.wifi.bean.knowledge_contest.ContestApplicationSearchOption;

/**
 * @author JFTT)wangyifeng
 * class name:KnowledgeContestApplicationService <BR>
 * class description: 大赛报名审批接口 <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/13
 */
public interface KnowledgeContestApplicationService {
	/**
	 * @author JFTT)wangyifeng
	 * Method name: approve <BR>
	 * Description: 审批报名 <BR>
	 * Remark: <BR>
	 * @param application  void<BR>
	 */
	void approve(KnowledgeContestApplicationBean application);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: deleteApplication <BR>
	 * Description: 删除审批信息 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void deleteApplication(Integer id);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getAppliationVoList <BR>
	 * Description: 获取审批摘要列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<ContestApplicationListItemVo><BR>
	 * @throws Exception 
	 */
	List<ContestApplicationListItemVo> getAppliationVoList(
			ContestApplicationSearchOption searchOption) throws Exception;

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getAppliationTotalCount <BR>
	 * Description: 获取申请总数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  Integer<BR>
	 * @throws Exception 
	 */
	Integer getAppliationTotalCount(ContestApplicationSearchOption searchOption) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: approveApplication <BR>
	 * Description:  审批报名-批量或单个 <BR>
	 * Remark: <BR>
	 * @param applyIds
	 * @param refuseReason
	 * @param approveStatus  void<BR>
	 */
	void approveApplication(String applyIds, String contestIds,String applyUserIds,String refuseReason,
			int approveStatus) throws Exception;
}
