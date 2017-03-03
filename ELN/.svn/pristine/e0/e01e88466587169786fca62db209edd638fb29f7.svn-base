package com.jftt.wifi.dao;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.KlBrowerInfoBean;

/**
 * 
 * class name:KlBrowerInfoMapper <BR>
 * class description: 知识资源浏览记录 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-24
 * @author JFTT)chenrui
 */
public interface KlBrowerInfoDaoMapper {
	public KlBrowerInfoBean getById(@Param("id")long id) throws Exception;

	public int isExist(@Param("userId")String userId, @Param("knowledgeId")String knowledgeId) throws Exception;

	public void add(@Param("userId")String userId, @Param("knowledgeId")String knowledgeId) throws Exception;
}