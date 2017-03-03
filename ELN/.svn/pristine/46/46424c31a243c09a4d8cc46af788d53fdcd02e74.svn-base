package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.KnowledgeContestNewsBean;
import com.jftt.wifi.bean.knowledge_contest.ContestNewsListItemVo;
import com.jftt.wifi.bean.knowledge_contest.ContestNewsSearchOption;
/**
 * 
 * class name:KnowledgeContestNewsDaoMapper <BR>
 * class description: 知识竞赛--资讯 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)chenrui
 */
public interface KnowledgeContestNewsDaoMapper {
	public KnowledgeContestNewsBean getById(@Param("id")long id) throws Exception;
	/**chenrui start*/
	/**
	 * 
	 * Method name: getMatchMessageList <BR>
	 * Description: 获取比赛资讯列表 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return  List<KnowledgeContestNewsBean><BR>
	 */
	public List<KnowledgeContestNewsBean> getMatchMessageList(@Param("megagameId")String megagameId,
			@Param("fromNum")long fromNum,@Param("pageSize")String pageSize)throws Exception;
	public int getMatchMessageListCount(@Param("megagameId")String megagameId)throws Exception;

	/**chenrui end*/

	// wangyifeng begin
	/**
	 * @author JFTT)wangyifeng
	 * Method name: addNews <BR>
	 * Description: 新增资讯 <BR>
	 * Remark: <BR>
	 * @param news  void<BR>
	 */
	void addNews(KnowledgeContestNewsBean news);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: deleteNews <BR>
	 * Description: 删除资讯 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void deleteNews(Integer id);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: modifyNews <BR>
	 * Description: 修改资讯 <BR>
	 * Remark: <BR>
	 * @param news  void<BR>
	 */
	void modifyNews(KnowledgeContestNewsBean news);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: publishNews <BR>
	 * Description: 发布资讯 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void publishNews(Integer id);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getNews <BR>
	 * Description: 获取资讯详情 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  KnowledgeContestNewsBean<BR>
	 */
	KnowledgeContestNewsBean getNews(Integer id);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getNewsVoList <BR>
	 * Description: 获取资讯摘要列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  ContestNewsListItemVo<BR>
	 */
	List<ContestNewsListItemVo> getNewsVoList(ContestNewsSearchOption searchOption);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getNewsTotalCount <BR>
	 * Description: 获取资讯总数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  Integer<BR>
	 */
	Integer getNewsTotalCount(ContestNewsSearchOption searchOption);
	// wangyifeng end
	
	//zhangchen start
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: addAndPublishNews <BR>
	 * Description: 保存并发布资讯 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void addAndPublishNews(KnowledgeContestNewsBean bean);
	//zhangchen end 
}