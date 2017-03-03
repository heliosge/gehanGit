package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.KnowledgeContestMatchBean;
import com.jftt.wifi.bean.vo.MegagameProcessInfoVo;
import com.jftt.wifi.bean.vo.SaveMatchParamVo;
/**
 * 
 * class name:KnowledgeContestMatchDaoMapper <BR>
 * class description: 知识竞赛--赛程 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)chenrui
 */
public interface KnowledgeContestMatchDaoMapper {
	public KnowledgeContestMatchBean getById(@Param("id")long id) throws Exception;
	
	/**chenrui start*/
	
	public void getDoPublish1(@Param("id")int matchId)throws Exception;
	
	public int getDoPublish(@Param("megagameId")String megagameId)throws Exception;
	/**
	 * 
	 * Method name: getMegagameProcessInfo <BR>
	 * Description: 获取大赛赛程安排信息 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return  List<MegagameProcessInfoVo><BR>
	 */
	public List<MegagameProcessInfoVo> getMegagameProcessInfo(@Param("megagameId")String megagameId)  throws Exception;
	/**
	 * 
	 * Method name: getlastMatchInfo <BR>
	 * Description: 根据大赛id获取本次大赛最后一个赛程的详细信息 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return  KnowledgeContestMatchBean<BR>
	 */
	public KnowledgeContestMatchBean getLastMatchInfo(@Param("megagameId")String megagameId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMegagameProcessInfoById <BR>
	 * Description: 根据赛程id获取大赛赛程安排信息  <BR>
	 * Remark: <BR>
	 * @param processId
	 * @return  MegagameProcessInfoVo<BR>
	 */
	public MegagameProcessInfoVo getMegagameProcessInfoById(@Param("processId")String processId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getAllMatchBymegagameId <BR>
	 * Description: 获取当前大赛下的所有赛程信息 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return  List<KnowledgeContestMatchBean><BR>
	 */
	public List<KnowledgeContestMatchBean> getAllMatchBymegagameId(@Param("megagameId")String megagameId) throws Exception;

	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: add2Match <BR>
	 * Description: 新增赛程记录 <BR>
	 * Remark: <BR>
	 * @param params
	 * @throws Exception  void<BR>
	 */
	public void add2Match(SaveMatchParamVo params) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getCountByMegagame <BR>
	 * Description: 根据大赛id获取当前大赛下的赛程数 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int getCountByMegagame(@Param("megagameId")String megagameId)throws Exception;
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: update2Match <BR>
	 * Description: 更新赛程记录  <BR>
	 * Remark: <BR>
	 * @param params
	 * @throws Exception  void<BR>
	 */
	public void update2Match(SaveMatchParamVo params)throws Exception;
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: checkMatchNameIsOnly <BR>
	 * Description: 新增赛程--验证赛程名称的唯一性 <BR>
	 * Remark: <BR>
	 * @param name
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int checkMatchNameIsOnly(@Param("name") String name,@Param("companyId")int companyId,
			@Param("subCompanyId")int subCompanyId,@Param("matchId") String matchId,@Param("megagameId") String megagameId)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMatchCounts <BR>
	 * Description:  获取大赛下赛程数目 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return  int<BR>
	 */
	public int getMatchCounts(@Param("megagameId")String megagameId)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMatchInfoByOrderNum <BR>
	 * Description: 根据大赛id及orderNum获取赛程id <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @param orderNum
	 * @return
	 */
	public KnowledgeContestMatchBean getMatchInfoByOrderNum(@Param("megagameId")String megagameId,@Param("orderNum") String orderNum)throws Exception;

	/**chenrui end*/

	// wangyifeng begin
	/**
	 * @author JFTT)wangyifeng
	 * Method name: addMatch <BR>
	 * Description: 新增赛程 <BR>
	 * Remark: <BR>
	 * @param match  void<BR>
	 */
	void addMatch(KnowledgeContestMatchBean match);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: realDeleteMatch <BR>
	 * Description: 物理删除赛程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void realDeleteMatch(Integer id);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getMatch <BR>
	 * Description: 获取赛程详情 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  KnowledgeContestMatchBean<BR>
	 */
	KnowledgeContestMatchBean getMatch(Integer id);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getMatchIdListOrderByOrderNum <BR>
	 * Description: 获取大赛包含的赛程ID列表，结果按赛程序号排序 <BR>
	 * Remark: <BR>
	 * @param contestId
	 * @return  List<Integer><BR>
	 */
	List<Integer> getMatchIdListOrderByOrderNum(Integer contestId);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: updateMatchStatus <BR>
	 * Description: 更新赛程状态 <BR>
	 * Remark: <BR>
	 * @param id
	 * @param status  void<BR>
	 */
	void updateMatchStatus(@Param("id") Integer id,
			@Param("status") Integer status);
	// wangyifeng end


}