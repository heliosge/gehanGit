/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KnowledgeContestNewsServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/13        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.KnowledgeContestNewsBean;
import com.jftt.wifi.bean.knowledge_contest.ContestNewsListItemVo;
import com.jftt.wifi.bean.knowledge_contest.ContestNewsSearchOption;
import com.jftt.wifi.dao.KnowledgeContestNewsDaoMapper;
import com.jftt.wifi.service.KnowledgeContestNewsService;

/**
 * @author JFTT)wangyifeng
 * class name:KnowledgeContestNewsServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/13
 */
@Service
public class KnowledgeContestNewsServiceImpl implements
		KnowledgeContestNewsService {
	@Autowired
	KnowledgeContestNewsDaoMapper newsDaoMapper;

	// wangyifeng begin
	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestNewsService#addNews(com.jftt.wifi.bean.KnowledgeContestNewsBean) <BR>
	 * Method name: addNews <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param news  <BR>
	*/

	@Override
	public void addNews(KnowledgeContestNewsBean news) {
		// TODO Auto-generated method stub
		newsDaoMapper.addNews(news);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestNewsService#deleteNews(java.lang.Integer) <BR>
	 * Method name: deleteNews <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id  <BR>
	*/

	@Override
	public void deleteNews(Integer id) {
		newsDaoMapper.deleteNews(id);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestNewsService#modifyNews(com.jftt.wifi.bean.KnowledgeContestNewsBean) <BR>
	 * Method name: modifyNews <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param news  <BR>
	*/

	@Override
	public void modifyNews(KnowledgeContestNewsBean news) {
		// TODO Auto-generated method stub
		newsDaoMapper.modifyNews(news);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestNewsService#publishNews(java.lang.Integer) <BR>
	 * Method name: publishNews <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id  <BR>
	*/

	@Override
	public void publishNews(Integer id) {
		newsDaoMapper.publishNews(id);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestNewsService#getNews(java.lang.Integer) <BR>
	 * Method name: getNews <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/

	@Override
	public KnowledgeContestNewsBean getNews(Integer id) {
		// TODO Auto-generated method stub
		return newsDaoMapper.getNews(id);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestNewsService#getNewsVoList(com.jftt.wifi.bean.knowledge_contest.ContestNewsSearchOption) <BR>
	 * Method name: getNewsVoList <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/

	@Override
	public List<ContestNewsListItemVo> getNewsVoList(
			ContestNewsSearchOption searchOption) {
		
		return newsDaoMapper.getNewsVoList(searchOption);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestNewsService#getNewsTotalCount(com.jftt.wifi.bean.knowledge_contest.ContestNewsSearchOption) <BR>
	 * Method name: getNewsTotalCount <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/

	@Override
	public Integer getNewsTotalCount(ContestNewsSearchOption searchOption) {
		// TODO Auto-generated method stub
		return newsDaoMapper.getNewsTotalCount(searchOption);
	}
	// wangyifeng end

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.KnowledgeContestNewsService#addAndPublishNews(com.jftt.wifi.bean.KnowledgeContestNewsBean) <BR>
	 * Method name: addAndPublishNews <BR>
	 * Description: 保存并发布资讯 <BR>
	 * Remark: <BR>
	 * @param bean  <BR>
	*/
	
	@Override
	public void addAndPublishNews(KnowledgeContestNewsBean bean) {
		newsDaoMapper.addAndPublishNews(bean);
	}
}
