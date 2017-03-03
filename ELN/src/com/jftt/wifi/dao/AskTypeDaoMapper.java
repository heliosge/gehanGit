/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: AskTypeDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月17日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ask.AskTypeBean;
import com.jftt.wifi.bean.ask.EditAskTypeVo;
import com.jftt.wifi.bean.ask.SearchOptionBean;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:AskTypeDaoMapper <BR>
 * class description: 问问分类dao <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月17日
 * @author JFTT)ShenHaijun
 */
public interface AskTypeDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询问问分类 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  AskTypeBean<BR>
	 */
	public AskTypeBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**
	 * Method name: getEditAskType <BR>
	 * Description: 获取问问分类编辑框内容 <BR>
	 * Remark: <BR>
	 * @param searchTypeId
	 * @return
	 * @throws DataBaseException  EditAskTypeVo<BR>
	 */
	public EditAskTypeVo getEditAskType(@Param("searchTypeId")Integer searchTypeId) throws DataBaseException;
	
	/**
	 * Method name: selectAskType <BR>
	 * Description: 查询问问分类 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws DataBaseException  List<AskTypeBean><BR>
	 */
	public List<AskTypeBean> selectAskType(Map<String, Object> param) throws DataBaseException;
	
	/**
	 * Method name: addAskType <BR>
	 * Description: 添加一个问问分类 <BR>
	 * Remark: <BR>
	 * @param askType
	 * @throws DataBaseException  void<BR>
	 */
	public void addAskType(AskTypeBean askType) throws DataBaseException;
	
	/**
	 * Method name: updateAskType <BR>
	 * Description: 修改一个问问分类 <BR>
	 * Remark: <BR>
	 * @param askType
	 * @throws DataBaseException  void<BR>
	 */
	public void updateAskType(AskTypeBean askType) throws DataBaseException;
	
	/**
	 * Method name: deleteAskType <BR>
	 * Description: 删除一个分类 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws DataBaseException  void<BR>
	 */
	public void deleteAskType(@Param("id")Integer id) throws DataBaseException;
	
	/**
	 * Method name: getRootTypes <BR>
	 * Description: 查询根问题分类 <BR>
	 * Remark: <BR>
	 * @return
	 * @throws DataBaseException  List<AskTypeBean><BR>
	 */
	public List<AskTypeBean> getRootTypes(@Param("companyId")String companyId,@Param("subCompanyId")String subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getChildTypes <BR>
	 * Description: 获取该分类下的直接子分类 <BR>
	 * Remark: <BR>
	 * @param typeId
	 * @return
	 * @throws DataBaseException  List<AskTypeBean><BR>
	 */
	public List<AskTypeBean> getChildTypes(@Param("typeId")Integer typeId) throws DataBaseException;
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAskTypeList <BR>
	 * Description: 查询问问分类列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<AskTypeBean><BR>
	 */
	List<AskTypeBean> getAskTypeList(SearchOptionBean searchOption);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getTypeName <BR>
	 * Description: 根据分类ID获取分类名称 <BR>
	 * Remark: <BR>
	 * @param typeId
	 * @return  String<BR>
	 */
	public String getTypeName(@Param("id")Integer id);
	
}
