/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageAskService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月17日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import com.jftt.wifi.bean.ask.AskTypeBean;
import com.jftt.wifi.bean.ask.EditAskTypeVo;
import com.jftt.wifi.bean.ask.ManageAskVo;

/**
 * class name:ManageAskService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月17日
 * @author JFTT)ShenHaijun
 */
public interface ManageAskService {
	
	/**
	 * Method name: getManageAsks <BR>
	 * Description: 获取管理端的问题列表 <BR>
	 * Remark: <BR>
	 * @param askTypeId 分类id
	 * @param searchTitle 标题
	 * @param searchAsker 提问人
	 * @param askStartTime 提问开始时间
	 * @param askEndTime 提问结束时间
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<ManageAskVo>
	 * @throws Exception  <BR>
	 */
	List<ManageAskVo> getManageAsks(String askTypeId, String searchTitle,
			String searchAsker, String askStartTime, String askEndTime, Integer fromNum, String pageSize,
			String companyId, String subCompanyId) throws Exception;
	
	/**
	 * Method name: getManageAskCount <BR>
	 * Description: 获取管理端问问数量 <BR>
	 * Remark: <BR>
	 * @param askTypeId 分类id
	 * @param searchTitle 标题
	 * @param searchAsker 提问人
	 * @param askStartTime 提问开始时间
	 * @param askEndTime 提问结束时间
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	Integer getManageAskCount(String askTypeId, String searchTitle,
			String searchAsker, String askStartTime, String askEndTime,
			String companyId, String subCompanyId) throws Exception;
	
	/**
	 * Method name: topAsk <BR>
	 * Description: 置顶问问 <BR>
	 * Remark: <BR>
	 * @param askId 问问id
	 * @throws Exception  void<BR>
	 */
	void topAsk(Integer askId) throws Exception;
	
	/**
	 * Method name: unTopAsk <BR>
	 * Description: 取消置顶问问 <BR>
	 * Remark: <BR>
	 * @param askId 问问id
	 * @throws Exception  void<BR>
	 */
	void unTopAsk(Integer askId) throws Exception;
	
	/**
	 * Method name: deleteAsk <BR>
	 * Description: 删除一条问问 <BR>
	 * Remark: <BR>
	 * @param askId 问问id
	 * @throws Exception  void<BR>
	 */
	void deleteAsk(Integer askId) throws Exception;
	
	/**
	 * Method name: batchDeleteAsks <BR>
	 * Description: 批量删除问问 <BR>
	 * Remark: <BR>
	 * @param ids 问问id集合
	 * @throws Exception  void<BR>
	 */
	void batchDeleteAsks(String[] ids) throws Exception;
	
	/**
	 * Method name: getEditAskType <BR>
	 * Description: 获取问问分类编辑框内容 <BR>
	 * Remark: <BR>
	 * @param searchTypeId 分类id
	 * @return EditAskTypeVo
	 * @throws Exception  <BR>
	 */
	EditAskTypeVo getEditAskType(Integer searchTypeId) throws Exception;
	
	/**
	 * Method name: selectAskType <BR>
	 * Description: 查询问问分类 <BR>
	 * Remark: <BR>
	 * @param param 查询参数
	 * @return List<AskTypeBean>
	 * @throws Exception  <BR>
	 */
	List<AskTypeBean> selectAskType(Map<String, Object> param) throws Exception;
	
	/**
	 * Method name: getTypeById <BR>
	 * Description: 根据id获取分类 <BR>
	 * Remark: <BR>
	 * @param searchTypeId 分类id
	 * @return AskTypeBean
	 * @throws Exception  <BR>
	 */
	AskTypeBean getTypeById(Integer searchTypeId) throws Exception;
	
	/**
	 * Method name: addAskType <BR>
	 * Description: 添加问问分类 <BR>
	 * Remark: <BR>
	 * @param parentId 父级id
	 * @param name 名称
	 * @param description 描述
	 * @throws Exception  void<BR>
	 */
	void addAskType(Integer parentId, String name, String description, String companyId, String subCompanyId) throws Exception;
	
	/**
	 * Method name: updateAskType <BR>
	 * Description: 修改问问分类 <BR>
	 * Remark: <BR>
	 * @param id 问问id
	 * @param name 名称
	 * @param description 描述 
	 * @throws Exception  void<BR>
	 */
	void updateAskType(Integer id, String name, String description) throws Exception;
	
	/**
	 * Method name: deleteAskType <BR>
	 * Description: 删除问问分类 <BR>
	 * Remark: <BR>
	 * @param id 问问id
	 * @throws Exception  void<BR>
	 */
	void deleteAskType(Integer id) throws Exception;
	
	/**
	 * Method name: changeType <BR>
	 * Description: 修改问问分类 <BR>
	 * Remark: <BR>
	 * @param askIds 问问id集合
	 * @param typeId 分类id
	 * @throws Exception  void<BR>
	 */
	void changeType(String[] askIds, String typeId) throws Exception;
	
}
