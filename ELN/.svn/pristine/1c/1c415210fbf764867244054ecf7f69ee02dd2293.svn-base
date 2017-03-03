/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KnowledgeContestNewsService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/13        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.KnowledgeContestNewsBean;
import com.jftt.wifi.bean.knowledge_contest.ContestNewsListItemVo;
import com.jftt.wifi.bean.knowledge_contest.ContestNewsSearchOption;

/**
 * @author JFTT)wangyifeng
 * class name:KnowledgeContestNewsService <BR>
 * class description: 大赛资讯管理接口 <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/13
 */
public interface KnowledgeContestNewsService {
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
	 * Description: 获取资讯详细 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  KnowledgeContestNewsBean<BR>
	 */
	KnowledgeContestNewsBean getNews(Integer id);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getNewsVoList <BR>
	 * Description: 获取资讯列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  ContestNewsListItemVo<BR>
	 */
	List<ContestNewsListItemVo> getNewsVoList(ContestNewsSearchOption searchOption);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getNewsVoTotalCount <BR>
	 * Description: 获取资讯列表总数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  Integer<BR>
	 */
	Integer getNewsTotalCount(ContestNewsSearchOption searchOption);
	// wangyifeng end

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: addAndPublishNews <BR>
	 * Description: 保存并发布资讯 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	void addAndPublishNews(KnowledgeContestNewsBean bean);
}
