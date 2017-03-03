/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageAskServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月17日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ask.AskTypeBean;
import com.jftt.wifi.bean.ask.EditAskTypeVo;
import com.jftt.wifi.bean.ask.ManageAskVo;
import com.jftt.wifi.dao.AskDetailDaoMapper;
import com.jftt.wifi.dao.AskTypeDaoMapper;
import com.jftt.wifi.service.ManageAskService;
import com.jftt.wifi.util.CommonUtil;

/**
 * class name:ManageAskServiceImpl <BR>
 * class description: 管理端问问service <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月17日
 * @author JFTT)ShenHaijun
 */
@Service("manageAskService")
public class ManageAskServiceImpl implements ManageAskService{
	@Resource(name="askDetailDaoMapper")
	private AskDetailDaoMapper askDetailDaoMapper;
	@Resource(name="askTypeDaoMapper")
	private AskTypeDaoMapper askTypeDaoMapper;
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageAskService#getManageAsks(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: getManageAsks <BR>
	 * Description: 获取管理端的问题列表 <BR>
	 * Remark: <BR>
	 * @param askTypeId 分类id
	 * @param searchTitle 标题
	 * @param searchAsker 提问人
	 * @param askStartTime 查询开始时间
	 * @param askEndTime 查询结束时间
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<ManageAskVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<ManageAskVo> getManageAsks(String askTypeId,
			String searchTitle, String searchAsker, String askStartTime,
			String askEndTime,Integer fromNum, String pageSize,
			String companyId, String subCompanyId) throws Exception {
		//设置查询参数
		ManageAskVo searchParams = new ManageAskVo();
		if(askTypeId != null && !("".equals(askTypeId.trim()))){
			searchParams.setSearchTypeId(Integer.parseInt(askTypeId));
		}
		if(searchTitle != null && (!"".equals(searchTitle.trim()))){
			searchParams.setSearchTitle(searchTitle);
		}
		if(searchAsker != null && (!"".equals(searchAsker.trim()))){
			searchParams.setSearchAsker(searchAsker);
		}
		if(askStartTime != null && (!"".equals(askStartTime.trim()))){
			searchParams.setSearchAskStartTime(CommonUtil.getDateByString(askStartTime));
		}
		if(askEndTime != null && (!"".equals(askEndTime.trim()))){
			searchParams.setSearchAskEndTime(CommonUtil.getDateByString(askEndTime));
		}
		if(fromNum != null){
			searchParams.setFromNum(fromNum);
		}
		if(pageSize != null && (!"".equals(pageSize.trim()))){
			searchParams.setPageSize(Integer.parseInt(pageSize));
		}
		if(companyId != null && (!"".equals(companyId.trim()))){
			searchParams.setCompanyId(Integer.parseInt(companyId));
		}
		if(subCompanyId != null && (!"".equals(subCompanyId.trim()))){
			searchParams.setSubCompanyId(Integer.parseInt(subCompanyId));
		}
		//返回查询结果
		return askDetailDaoMapper.getManageAsks(searchParams);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageAskService#getManageAskCount(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: getManageAskCount <BR>
	 * Description: 获取管理端问问数量 <BR>
	 * Remark: <BR>
	 * @param askTypeId 分类id
	 * @param searchTitle 标题
	 * @param searchAsker 提问人
	 * @param askStartTime 查询开始时间
	 * @param askEndTime 查询结束时间
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getManageAskCount(String askTypeId, String searchTitle,
			String searchAsker, String askStartTime, String askEndTime,
			String companyId, String subCompanyId)throws Exception {
		//设置查询参数
		ManageAskVo searchParams = new ManageAskVo();
		if(askTypeId != null && !("".equals(askTypeId.trim()))){
			searchParams.setSearchTypeId(Integer.parseInt(askTypeId));
		}
		if(searchTitle != null && (!"".equals(searchTitle.trim()))){
			searchParams.setSearchTitle(searchTitle);
		}
		if(searchAsker != null && (!"".equals(searchAsker.trim()))){
			searchParams.setSearchAsker(searchAsker);
		}
		if(askStartTime != null && (!"".equals(askStartTime.trim()))){
			searchParams.setSearchAskStartTime(CommonUtil.getDateByString(askStartTime));
		}
		if(askEndTime != null && (!"".equals(askEndTime.trim()))){
			searchParams.setSearchAskEndTime(CommonUtil.getDateByString(askEndTime));
		}
		if(companyId != null && (!"".equals(companyId.trim()))){
			searchParams.setCompanyId(Integer.parseInt(companyId));
		}
		if(subCompanyId != null && (!"".equals(subCompanyId.trim()))){
			searchParams.setSubCompanyId(Integer.parseInt(subCompanyId));
		}
		//返回查询结果
		return askDetailDaoMapper.getManageAskCount(searchParams);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageAskService#topAsk(java.lang.Integer) <BR>
	 * Method name: topAsk <BR>
	 * Description: 置顶问问 <BR>
	 * Remark: <BR>
	 * @param askId 问问id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void topAsk(Integer askId) throws Exception {
		Integer isTop = 1;
		Date topTime = new Date();
		askDetailDaoMapper.updateAskTopState(askId,isTop,topTime);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageAskService#unTopAsk(java.lang.Integer) <BR>
	 * Method name: unTopAsk <BR>
	 * Description: 取消置顶问问 <BR>
	 * Remark: <BR>
	 * @param askId 问问id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void unTopAsk(Integer askId) throws Exception {
		Integer isTop = 2;
		Date topTime = null;
		askDetailDaoMapper.updateAskTopState(askId,isTop,topTime);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageAskService#deleteAsk(java.lang.Integer) <BR>
	 * Method name: deleteAsk <BR>
	 * Description: 删除一条问问 <BR>
	 * Remark: <BR>
	 * @param askId 问问id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteAsk(Integer askId) throws Exception {
		askDetailDaoMapper.deleteAsk(askId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageAskService#batchDeleteAsks(java.lang.String[]) <BR>
	 * Method name: batchDeleteAsks <BR>
	 * Description: 批量删除问问 <BR>
	 * Remark: <BR>
	 * @param ids 问问id集合
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void batchDeleteAsks(String[] ids) throws Exception {
		askDetailDaoMapper.batchDeleteAsks(ids);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageAskService#getEditAskType(java.lang.Integer) <BR>
	 * Method name: getEditAskType <BR>
	 * Description: 获取问问分类编辑框内容 <BR>
	 * Remark: <BR>
	 * @param searchTypeId 分类id
	 * @return EditAskTypeVo 
	 * @throws Exception  <BR>
	 */
	@Override
	public EditAskTypeVo getEditAskType(Integer searchTypeId) throws Exception {
		return askTypeDaoMapper.getEditAskType(searchTypeId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageAskService#getTypeById(java.lang.Integer) <BR>
	 * Method name: getTypeById <BR>
	 * Description: 根据id获取分类 <BR>
	 * Remark: <BR>
	 * @param searchTypeId 分类id
	 * @return AskTypeBean
	 * @throws Exception  <BR>
	 */
	@Override
	public AskTypeBean getTypeById(Integer searchTypeId) throws Exception {
		return askTypeDaoMapper.getById(searchTypeId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageAskService#selectAskType(java.util.Map) <BR>
	 * Method name: selectAskType <BR>
	 * Description: 查询问问分类 <BR>
	 * Remark: <BR>
	 * @param param 查询参数
	 * @return List<AskTypeBean>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<AskTypeBean> selectAskType(Map<String, Object> param)
			throws Exception {
		return askTypeDaoMapper.selectAskType(param);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageAskService#addAskType(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: addAskType <BR>
	 * Description: 添加一个分类 <BR>
	 * Remark: <BR>
	 * @param parentId 父级id
	 * @param name 名称
	 * @param description 描述
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addAskType(Integer parentId, String name, String description, String companyId, String subCompanyId)
			throws Exception {
		//设置参数
		AskTypeBean askType = new AskTypeBean();
		if(parentId != null){
			askType.setParentId(parentId);
		}
		if(name != null && !"".equals(name.trim())){
			askType.setName(name);
		}
		if(description != null && !"".equals(description.trim())){
			askType.setDescription(description);
		}
		if(companyId != null && !"".equals(companyId.trim())){
			askType.setCompanyId(Integer.parseInt(companyId));
		}
		if(subCompanyId != null && !"".equals(subCompanyId.trim())){
			askType.setSubCompanyId(Integer.parseInt(subCompanyId));
		}
		askType.setCreateTime(new Date());
		//添加分类
		askTypeDaoMapper.addAskType(askType);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageAskService#updateAskType(java.lang.Integer, java.lang.String, java.lang.String) <BR>
	 * Method name: updateAskType <BR>
	 * Description: 修改问问分类 <BR>
	 * Remark: <BR>
	 * @param id 问问id
	 * @param name 问问名称
	 * @param description 问问描述
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateAskType(Integer id, String name, String description)
			throws Exception {
		//设置参数
		AskTypeBean askType = new AskTypeBean();
		if(id != null){
			askType.setId(id);
		}
		if(name != null && !"".equals(name.trim())){
			askType.setName(name);
		}
		askType.setDescription(description);
		//更改分类
		askTypeDaoMapper.updateAskType(askType);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageAskService#deleteAskType(java.lang.Integer) <BR>
	 * Method name: deleteAskType <BR>
	 * Description: 删除问问分类 <BR>
	 * Remark: <BR>
	 * @param id 问问id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteAskType(Integer id) throws Exception {
		askTypeDaoMapper.deleteAskType(id);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageAskService#changeType(java.lang.String[], java.lang.String) <BR>
	 * Method name: changeType <BR>
	 * Description: 修改问问分类 <BR>
	 * Remark: <BR>
	 * @param askIds 问问id集合
	 * @param typeId 分类id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void changeType(String[] askIds, String typeId) throws Exception {
		if(typeId != null && !"".equals(typeId.trim()) && askIds != null && askIds.length > 0){
			if(askIds.length == 1){
				//改变单个问问分类
				askDetailDaoMapper.updateAskType(askIds[0],typeId);
			}else{
				//改变多个问问分类
				askDetailDaoMapper.batchUpdateAskType(askIds,typeId);
			}
		}else{
			throw new Exception("修改问问分类方法参数有空值");
		}
	}
}
