package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.KnowledgeContestApplicationBean;
import com.jftt.wifi.bean.KnowledgeContestNewsBean;
import com.jftt.wifi.bean.knowledge_contest.ContestApplicationListItemVo;
import com.jftt.wifi.bean.knowledge_contest.ContestApplicationSearchOption;
import com.jftt.wifi.bean.vo.ApplicationsSerchParam;
import com.jftt.wifi.bean.vo.MyApplicationsReturnVo;

/**
 * 
 * class name:KnowledgeContestApplicationMapper <BR>
 * class description: 知识竞赛--报名 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)chenrui
 */
public interface KnowledgeContestApplicationDaoMapper {
	/**chenrui start*/
	public KnowledgeContestApplicationBean getById(@Param("id")long id) throws Exception;
	/**
	 * 
	 * Method name: getMyApplications <BR>
	 * Description: 我的报名获取 <BR>
	 * Remark: <BR>
	 * @return  List<KnowledgeContestApplicationBean><BR>
	 */
	public List<MyApplicationsReturnVo> getMyApplications(ApplicationsSerchParam serchParam) throws Exception;
	public int getMyApplicationsCount(ApplicationsSerchParam serchParam) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: isApply <BR>
	 * Description: isApply <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @return  int<BR>
	 */
	public KnowledgeContestApplicationBean isApply(@Param("userId")String userId, @Param("megagameId")String megagameId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: doApply <BR>
	 * Description: 报名 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @throws Exception  void<BR>
	 */
	public void doApply(@Param("userId")String userId, @Param("megagameId")String megagameId) throws Exception;
	public void updateApply(@Param("userId")String userId, @Param("megagameId")String megagameId) throws Exception;
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
	public int checkJoinQualification(@Param("userId")String userId, @Param("megagameId")String megagameId) throws Exception;
	/**chenrui end*/

	// wangyifeng begin
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
	 * Method name: realDeleteApplication <BR>
	 * Description: 物理删除审批信息 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void realDeleteApplication(Integer id);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getAppliationVoList <BR>
	 * Description: 获取审批摘要列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<ContestApplicationListItemVo><BR>
	 */
	List<ContestApplicationListItemVo> getAppliationVoList(
			ContestApplicationSearchOption searchOption);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getAppliationTotalCount <BR>
	 * Description: 获取申请总数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  Integer<BR>
	 */
	Integer getAppliationTotalCount(ContestApplicationSearchOption searchOption);
	// wangyifeng end
	

	
}