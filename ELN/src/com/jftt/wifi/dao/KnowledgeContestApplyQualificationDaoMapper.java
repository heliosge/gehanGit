package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.KnowledgeContestApplyQualificationBean;
import com.jftt.wifi.bean.vo.AddCompetitionVo;
/**
 * 
 * class name:KnowledgeContestApplyQualificationDaoMapper <BR>
 * class description: 知识竞赛--参赛资格信息 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)chenrui
 */
public interface KnowledgeContestApplyQualificationDaoMapper {
	public KnowledgeContestApplyQualificationBean getById(@Param("id")long id) throws Exception;

	// wangyifeng begin
	/**
	 * @author JFTT)wangyifeng
	 * Method name: addApplyQualification <BR>
	 * Description: 新增参赛资格信息 <BR>
	 * Remark: <BR>
	 * @param applyQualification  void<BR>
	 */
	void addApplyQualification(
			KnowledgeContestApplyQualificationBean applyQualification);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: realDeleteApplyQualifications <BR>
	 * Description: 物理删除参赛资格信息 <BR>
	 * Remark: <BR>
	 * @param contestId  void<BR>
	 */
	void realDeleteApplyQualifications(Integer contestId);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getApplyQualificationList <BR>
	 * Description: 获取参赛资格列表 <BR>
	 * Remark: <BR>
	 * @param contestId
	 * @return  List<KnowledgeContestApplyQualificationBean><BR>
	 */
	List<KnowledgeContestApplyQualificationBean> getApplyQualificationList(
			Integer contestId);
	// wangyifeng end
	/**chenrui start*/

	public void cleanRecord(@Param("megagameId")Integer megagameId) throws Exception;

	/**chenrui end*/
	
}