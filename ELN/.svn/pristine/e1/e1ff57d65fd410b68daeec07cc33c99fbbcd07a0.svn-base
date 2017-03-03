package com.jftt.wifi.dao;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.KlResourceToTagsBean;

/**
 * 
 * class name:KlResourceToTagsDaoMapper <BR>
 * class description: 标签知识关联表 <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-3
 * @author JFTT)chenrui
 */
public interface KlResourceToTagsDaoMapper {
	/**chenrui start*/
	public KlResourceToTagsBean getById(@Param("id")long id) throws Exception;
	/**
	 * 
	 * Method name: removeByKlId <BR>
	 * Description: 根据知识id清空知识标签的关联记录 <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @throws Exception  void<BR>
	 */
	public void removeByKlId(@Param("knowledgeId")int knowledgeId) throws Exception;
	/**
	 * 
	 * Method name: add <BR>
	 * Description: 新增关联记录 <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @param tagId
	 * @throws Exception  void<BR>
	 */
	public void add(@Param("knowledgeId")int knowledgeId, @Param("tagId")Integer tagId) throws Exception;
	
	/**chenrui end*/
}