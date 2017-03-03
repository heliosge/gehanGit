package com.jftt.wifi.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.KnowledgeContestContestBean;
import com.jftt.wifi.bean.knowledge_contest.ContestBean;
import com.jftt.wifi.bean.knowledge_contest.ContestListItemVo;
import com.jftt.wifi.bean.knowledge_contest.KnowledgeContestSearchOptionVo;
import com.jftt.wifi.bean.vo.AddCompetitionVo;
import com.jftt.wifi.bean.vo.KnowledgeContestContestVo;
import com.jftt.wifi.bean.vo.MegagameListParamVo;
import com.jftt.wifi.bean.vo.SearchMegagameCheckParamVo;

/**
 * 
 * class name:KnowledgeContestContestDaoMapper <BR>
 * class description: 知识竞赛--大赛<BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)chenrui
 */
public interface KnowledgeContestContestDaoMapper {
	/**chenrui start*/
	
	public void updateStatusByTime() throws Exception;

	public KnowledgeContestContestBean getById(@Param("id") long id) throws Exception;
			
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getDoPublish1 <BR>
	 * Description: 发布大赛时-更新当前大赛当前所在赛程字段 <BR>
	 * Remark: <BR>
	 * @param matchId
	 * @throws Exception  void<BR>
	 */
	public void getDoPublish1(@Param("megagameId")String megagameId,@Param("matchId")int matchId)throws Exception;
	/**
	 * 
	 * Method name: getMegagameList <BR>
	 * Description: 获取大赛列表信息 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return  List<KnowledgeContestContestBean><BR>
	 */
	public List<KnowledgeContestContestVo> getMegagameList(MegagameListParamVo paramVo) throws Exception;

	public int getMegagameListCount(MegagameListParamVo paramVo) throws Exception;

	/**
	 * 
	 * Method name: getMegagameCheckList <BR>
	 * Description: 批阅试卷赛信息列表 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return
	 * @throws Exception  List<MegagameCheckReturnVo><BR>
	 */
	public List<KnowledgeContestContestVo> getMegagameCheckList(
			SearchMegagameCheckParamVo paramVo) throws Exception;

	public int getMegagameCheckListCount(SearchMegagameCheckParamVo paramVo) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: addMegagame <BR>
	 * Description: 新增大赛 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @throws Exception  void<BR>
	 */
	public void addMegagame(AddCompetitionVo paramVo)throws Exception;

	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: updateMegagame <BR>
	 * Description: 根据id修改大赛 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @throws Exception  void<BR>
	 */
	public void updateMegagame(AddCompetitionVo paramVo) throws Exception;
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: checkMegagameNameIsOnly <BR>
	 * Description:  新增大赛--验证大赛名称的唯一性 <BR>
	 * Remark: <BR>
	 * @param name
	 * @return  int<BR>
	 */
	public int checkMegagameNameIsOnly(@Param("name") String name,@Param("companyId")int companyId,
			@Param("subCompanyId")int subCompanyId,@Param("megagameId")String megagameId) throws Exception;
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getContestStartTime <BR>
	 * Description: 获取大赛开始时间 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  String<BR>
	 */
	public Date getContestStartTime(@Param("megagameId")String megagameId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getContestEndTime <BR>
	 * Description: 获取大赛结束时间 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  Date<BR>
	 */
	public Date getContestEndTime(@Param("megagameId")String megagameId) throws Exception;
	
	/**chenrui end*/

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
	 * Method name: startFirstMatch <BR>
	 * Description: 开启第一赛程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void startFirstMatch(Integer id);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: startNextMatch <BR>
	 * Description: 开启下一个赛程 <BR>
	 * Remark: <BR>
	 * @param contestId
	 * @param curMatchOrderNum  void<BR>
	 */
	void startNextMatch(@Param("contestId") Integer contestId,
			@Param("curMatchOrderNum") Integer curMatchOrderNum);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: finishLastMatch <BR>
	 * Description: 结束最后一个赛程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void finishLastMatch(Integer id);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getContest <BR>
	 * Description: 获取大赛详情 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ContestBean<BR>
	 */
	ContestBean getContest(Integer id);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getManageContestList <BR>
	 * Description: 获取大赛列表（管理员视角） <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<KnowledgeContestContestVo><BR>
	 */
	List<KnowledgeContestContestVo> getManageContestVoList(MegagameListParamVo paramVo);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getManageContestListCount <BR>
	 * Description: 获取大赛总数（管理员视角） <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  Integer<BR>
	 */
	Integer getManageContestTotalCount(MegagameListParamVo paramVo);
	// wangyifeng end

	
}