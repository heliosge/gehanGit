/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KlEvaluateDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-14        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.KlEvaluateBean;

/**
 * 
 * class name:KlEvaluateDaoMapper <BR>
 * class description: 知识评价表 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-14
 * @author JFTT)chenrui
 */
public interface KlEvaluateDaoMapper {
	/**chenrui start*/
	public KlEvaluateBean getById(@Param("id")long id) throws Exception;
	/**
	 * 
	 * Method name: giveEvaluate <BR>
	 * Description: 评价知识 <BR>
	 * Remark: <BR>
	 * @param klEvaluateBean
	 * @throws Exception  void<BR>
	 */
	public void giveEvaluate(KlEvaluateBean klEvaluateBean) throws Exception;
	/**
	 * 
	 * Method name: getEvaluates <BR>
	 * Description: 获取知识评价信息 <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @return  List<KlEvaluateBean><BR>
	 */
	public List<KlEvaluateBean> getEvaluates(@Param("knowledgeId")String knowledgeId)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: judgeEvaluate <BR>
	 * Description: 判断当前资源是否已评价 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param knowledgeId
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int judgeEvaluate(@Param("userId")String userId, @Param("knowledgeId")String knowledgeId)throws Exception;
	
	/**chenrui end*/
}