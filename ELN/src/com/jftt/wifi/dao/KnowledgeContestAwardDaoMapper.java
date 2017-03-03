package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.KnowledgeContestAwardBean;
import com.jftt.wifi.bean.vo.AddCompetitionVo;
/**
 * 
 * class name:KnowledgeContestAwardDaoMapper <BR>
 * class description: 知识竞赛--奖项 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)chenrui
 */
public interface KnowledgeContestAwardDaoMapper {
	public KnowledgeContestAwardBean getById(@Param("id")long id) throws Exception;
	/**chenrui start*/
	
	/**
	 * 
	 * Method name: getAwardsSetting <BR>
	 * Description:  获取奖项设置  <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return  List<KnowledgeContestAwardBean><BR>
	 */
	public List<KnowledgeContestAwardBean> getAwardsSetting(@Param("megagameId")String megagameId) throws Exception;
	
	/**chenrui end*/

	// wangyifeng begin
	/**
	 * @author JFTT)wangyifeng
	 * Method name: addAward <BR>
	 * Description: 新增奖项 <BR>
	 * Remark: <BR>
	 * @param award  void<BR>
	 */
	void addAward(KnowledgeContestAwardBean award);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: realDeleteAwards <BR>
	 * Description: 物理删除奖项 <BR>
	 * Remark: <BR>
	 * @param contestId  void<BR>
	 */
	void realDeleteAwards(Integer contestId);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getAwardList <BR>
	 * Description: 获取奖项列表 <BR>
	 * Remark: <BR>
	 * @param contestId
	 * @return  List<KnowledgeContestAwardBean><BR>
	 */
	List<KnowledgeContestAwardBean> getAwardList(Integer contestId);
	// wangyifeng end
	
	/**chenrui start*/
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: cleanRecord <BR>
	 * Description: 根据大赛id清空奖项 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void cleanRecord(@Param("megagameId")Integer megagameId);
	
	/**chenrui end*/

	
}