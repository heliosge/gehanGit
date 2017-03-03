/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KlCollectDownloadDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-14        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.KlCollectDownloadBean;
import com.jftt.wifi.bean.vo.judgeColDownReturnVo;

/**
 * 
 * class name:KlCollectDownloadDaoMapper <BR>
 * class description: 知识收藏下载表 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-14
 * @author JFTT)chenrui
 */
public interface KlCollectDownloadDaoMapper {
	/**chenrui start*/
	public KlCollectDownloadBean getById(@Param("id")long id) throws Exception;
	/**
	 * 
	 * Method name: addCollectAndDownload <BR>
	 * Description: 收藏下载操作  <BR>
	 * Remark: <BR>
	 * @throws Exception  void<BR>
	 */
	public void addCollectAndDownload(KlCollectDownloadBean klCollectDownloadBean) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: judgeCollecDownload <BR>
	 * Description: 判断当前资源的是否收藏、下载 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param knowledgeId
	 * @return  Map<String,Integer><BR>
	 */
	public judgeColDownReturnVo judgeCollecDownload(@Param("userId")String userId, @Param("knowledgeId")String knowledgeId) throws Exception;
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toDelColDown <BR>
	 * Description: 批量删除-我收藏下载的知识 <BR>
	 * Remark: <BR>
	 * @param ids
	 */
	public void toDelColDown(@Param("ids")String[] ids) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: doDelColDow <BR>
	 * Description: 单个删除我收藏下载的记录 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	public void doDelColDow(@Param("id")String id) throws Exception;
	
	/**chenrui end*/
}