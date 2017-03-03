package com.jftt.wifi.dao;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.KlTagsBean;

/**
 * 
 * class name:KlTagsDaoMapper <BR>
 * class description: 知识标签基础表 <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-3
 * @author JFTT)chenrui
 */
public interface KlTagsDaoMapper {
	public KlTagsBean getById(@Param("id")long id) throws Exception;
	/**chenrui start*/
	/**
	 * 
	 * Method name: isExist <BR>
	 * Description: 判断是否存在同名标签 <BR>
	 * Remark: <BR>
	 * @param tagName
	 * @throws Exception  void<BR>
	 */
	public int isExist(@Param("tagName")String tagName) throws Exception;
	/**
	 * 
	 * Method name: add <BR>
	 * Description: 添加标签 <BR>
	 * Remark: <BR>
	 * @param tagName
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int add(@Param("tagName")String tagName) throws Exception;
	/**
	 * 
	 * Method name: getIdByName <BR>
	 * Description: 根据名称获取id <BR>
	 * Remark: <BR>
	 * @param tagName
	 * @return
	 * @throws Exception  Integer<BR>
	 */
	public Integer getIdByName(@Param("tagName")String tagName) throws Exception;

	/**chenrui end*/
}