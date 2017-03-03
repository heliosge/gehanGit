/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyAskServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年12月9日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ask.AskAnswerBean;
import com.jftt.wifi.bean.ask.AskDetailBean;
import com.jftt.wifi.bean.ask.AskTypeBean;
import com.jftt.wifi.bean.ask.AskVoForMMGrid;
import com.jftt.wifi.bean.ask.SearchOptionBean;
import com.jftt.wifi.bean.thematicAsk.ThematicAskVo;
import com.jftt.wifi.dao.AskAnswerDaoMapper;
import com.jftt.wifi.dao.AskDetailDaoMapper;
import com.jftt.wifi.dao.AskTypeDaoMapper;
import com.jftt.wifi.dao.ManagePageDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.MyAskService;

/**
 * class name:MyAskServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年12月9日
 * @author JFTT)ShenHaijun
 */
@Service("myAskService")
public class MyAskServiceImpl implements MyAskService{
	@Resource(name="askDetailDaoMapper")
	private AskDetailDaoMapper askDetailDaoMapper;
	@Resource(name="askAnswerDaoMapper")
	private AskAnswerDaoMapper askAnswerDaoMapper;
	@Resource(name="askTypeDaoMapper")
	private AskTypeDaoMapper askTypeDaoMapper;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	@Resource(name="managePageDaoMapper")
	private ManagePageDaoMapper managePageDaoMapper;
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getAskCount(java.lang.Integer) <BR>
	 * Method name: getAskCountByAskerId <BR>
	 * Description: 获取提问数量 <BR>
	 * Remark: <BR>
	 * @param askerId 问问id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getAskCountByAskerId(Integer askerId) throws Exception {
		return askDetailDaoMapper.getAskCountByAskerId(askerId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getAnswerCountByReplyerId(java.lang.Integer) <BR>
	 * Method name: getAnswerCountByReplyerId <BR>
	 * Description: 获取回答数量 <BR>
	 * Remark: <BR>
	 * @param replyerId 回答id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getAnswerCountByReplyerId(Integer replyerId)
			throws Exception {
		return askDetailDaoMapper.getAskCountByReplyerId(replyerId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getRootTypes(com.jftt.wifi.bean.ask.SearchOptionBean) <BR>
	 * Method name: getRootTypes <BR>
	 * Description: 查询根问题分类 <BR>
	 * Remark: <BR>
	 * @param searchOption 查询条件
	 * @return List<Map<String, String>>
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional
	public List<Map<String, String>> getRootTypes(SearchOptionBean searchOption) throws Exception {
		//List<RootAskTypeVo> rootTypes = askTypeDaoMapper.getRootTypes();
		List<AskTypeBean> rootTypes = askTypeDaoMapper.getAskTypeList(searchOption);
		//将每个分类下的所有问题数量放入列表
		if(rootTypes != null && rootTypes.size() > 0){
			for (int i = 0; i < rootTypes.size(); i++) {
				rootTypes.get(i).setAskCount(getTypeAskCount(rootTypes.get(i).getId(),""));
			}
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(AskTypeBean bean : rootTypes){
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", bean.getId()+"");
			map.put("parentId", bean.getParentId()+"");
			map.put("name",bean.getName() +"  ("+ bean.getAskCount()+")");
			list.add(map);
		}
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getRootTypesUnCount <BR>
	 * Description: 获取分类树，不带数量  <BR>
	 * Remark: <BR>
	 * @param searchOption 查询条件
	 * @return List<AskTypeBean>
	 * @throws Exception  List<AskTypeBean><BR>
	 */
	@Override
	@Transactional
	public List<AskTypeBean> getRootTypesUnCount(SearchOptionBean searchOption) throws Exception {
		//List<RootAskTypeVo> rootTypes = askTypeDaoMapper.getRootTypes();
		List<AskTypeBean> rootTypes = askTypeDaoMapper.getAskTypeList(searchOption);
		return rootTypes;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getToDealAskCount(java.lang.String, java.lang.String) <BR>
	 * Method name: getToDealAskCount <BR>
	 * Description: 获取待处理问题数量 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getToDealAskCount(String companyId,String subCompanyId) throws Exception {
		return askDetailDaoMapper.getToDealAskCount(companyId,subCompanyId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getDealedAskCount(java.lang.String, java.lang.String) <BR>
	 * Method name: getDealedAskCount <BR>
	 * Description: 获取已处理问题数量 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getDealedAskCount(String companyId,String subCompanyId) throws Exception {
		return askDetailDaoMapper.getDealedAskCount(companyId,subCompanyId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getThematicAskList() <BR>
	 * Method name: getThematicAskList <BR>
	 * Description: 获取专题问答 <BR>
	 * Remark: <BR>
	 * @return List<ThematicAskVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<ThematicAskVo> getThematicAskList() throws Exception {
		return askDetailDaoMapper.getThematicAskList();
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getAsksCount(java.lang.String, java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: getAsksCount <BR>
	 * Description: 获取问问数量 <BR>
	 * Remark: <BR>
	 * @param askShowType 显示类型
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getAsksCount(String askShowType,String userId,String companyId,String subCompanyId) throws Exception {
		if(askShowType != null && !"".equals(askShowType.trim())){
			Integer count = 0;
			if("1".equals(askShowType)){
				//待解决的问题
				count = askDetailDaoMapper.getToDealAskCount(companyId,subCompanyId);
			}else if("2".equals(askShowType)){
				//已解决的问题
				count = askDetailDaoMapper.getDealedAskCount(companyId,subCompanyId);
			}else if("3".equals(askShowType)){
				//我的提问
				count = askDetailDaoMapper.getAskCountByAskerId(Integer.parseInt(userId));
			}else if("4".equals(askShowType)){
				//我的回答
				count = askDetailDaoMapper.getAskCountByReplyerId(Integer.parseInt(userId));
			}else{
				throw new Exception("问问首页不存在的显示类型！");
			}
			return count;
		}
		return null;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getAskList(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: getAskList <BR>
	 * Description: 获取问问列表 <BR>
	 * Remark: <BR>
	 * @param askShowType 显示类型
	 * @param userId 用户id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<AskVoForMMGrid>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<AskVoForMMGrid> getAskList(String askShowType, String userId, Integer fromNum,
			String pageSize,String companyId,String subCompanyId) throws Exception {
		if(askShowType != null && !"".equals(askShowType.trim())){
			List<AskVoForMMGrid> askList = new ArrayList<AskVoForMMGrid>();
			if("1".equals(askShowType)){
				//待解决的问题
				askList = askDetailDaoMapper.getToDealAskList(fromNum,pageSize,companyId,subCompanyId);
			}else if("2".equals(askShowType)){
				//已解决的问题
				askList = askDetailDaoMapper.getDealedAskList(fromNum,pageSize,companyId,subCompanyId);
			}else if("3".equals(askShowType)){
				//我的提问
				askList = askDetailDaoMapper.getAskListByAskerId(userId,fromNum,pageSize);
			}else if("4".equals(askShowType)){
				//我的回答
				askList = askDetailDaoMapper.getAskListByReplyerId(userId,fromNum,pageSize);
			}else{
				throw new Exception("问问首页不存在的显示类型！");
			}
			return askList;
		}
		return null;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getTypeById(java.lang.Integer) <BR>
	 * Method name: getTypeById <BR>
	 * Description: 根据id获取分类 <BR>
	 * Remark: <BR>
	 * @param typeId 分类id
	 * @return AskTypeBean
	 * @throws Exception  <BR>
	 */
	@Override
	public AskTypeBean getTypeById(Integer typeId) throws Exception {
		return askTypeDaoMapper.getById(typeId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getTypeAskCount(java.lang.Integer, java.lang.String) <BR>
	 * Method name: getTypeAskCount <BR>
	 * Description: 获取该分类下的问问数量 <BR>
	 * Remark: <BR>
	 * @param typeId 分类id
	 * @param searchContent 查询条件
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getTypeAskCount(Integer typeId, String searchContent)
			throws Exception {
		//查询该分类下的所有子分类（包含本身）
		List<Integer> nodeIds = new ArrayList<Integer>();
		List<Integer> typeIdList = getChildTypeIds(typeId,nodeIds);
		//返回查询结果
		return askDetailDaoMapper.getTypeAskCount(typeIdList,searchContent);
	}
	
	/**
	 * Method name: getChildTypeIds <BR>
	 * Description: 查询该分类下的所有叶子分类 id集合<BR>
	 * Remark: <BR>
	 * @param typeId 分类id
	 * @param nodeIds 节点id集合
	 * @return List<Integer>
	 * @throws Exception  List<Integer><BR>
	 */
	private List<Integer> getChildTypeIds(Integer typeId, List<Integer> nodeIds) throws Exception {
		List<AskTypeBean> typeIds = getChildTypes(typeId);
		if(typeIds != null && typeIds.size() > 0){
			nodeIds.add(typeId);//该语句不加则表示所有叶子节点，加了表示所有子节点。
			for (int i = 0; i < typeIds.size(); i++) {
				getChildTypeIds(typeIds.get(i).getId(),nodeIds);
			}
		}else{
			nodeIds.add(typeId);
		}
		return nodeIds;
	}
	
	/**
	 * Method name: getChildTypes <BR>
	 * Description: 查询该分类下的直接子分类 <BR>
	 * Remark: <BR>
	 * @param typeId 分类id
	 * @return List<AskTypeBean>
	 * @throws Exception  List<AskTypeBean><BR>
	 */
	private List<AskTypeBean> getChildTypes(Integer typeId) throws Exception {
		return askTypeDaoMapper.getChildTypes(typeId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getTypeAskList(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String) <BR>
	 * Method name: getTypeAskList <BR>
	 * Description: 获取该分类下的问问列表 <BR>
	 * Remark: <BR>
	 * @param typeId 分类id
	 * @param searchContent 查询条件
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<AskVoForMMGrid>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<AskVoForMMGrid> getTypeAskList(Integer typeId,String searchContent,
			 String sortName, String sortOrder,
			Integer fromNum, String pageSize) throws Exception {
		//查询该分类下的所有叶子分类
		List<Integer> nodeIds = new ArrayList<Integer>();
		List<Integer> typeIdList = getChildTypeIds(typeId,nodeIds);
		//返回查询结果
		return askDetailDaoMapper.getTypeAskList(typeIdList,searchContent,sortName,sortOrder,fromNum,pageSize);
	}

	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getAskDetail(java.lang.Integer) <BR>
	 * Method name: getAskDetail <BR>
	 * Description: 获取问问详情-基本信息 <BR>
	 * Remark: <BR>
	 * @param askId 问问id
	 * @return AskDetailBean
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional
	public AskDetailBean getAskDetail(Integer askId) throws Exception {
		//问问基本信息
		AskDetailBean askBean = askDetailDaoMapper.getById(askId);
		//问问分类信息
		Integer typeId = askBean.getTypeId();
		askBean.setTypeName(askTypeDaoMapper.getTypeName(typeId));
		//问问回答数量
		SearchOptionBean searchOption =  new SearchOptionBean();
		searchOption.setQuestionId(askId);
		askBean.setAnswerCount(askAnswerDaoMapper.getAskAnswerCount(searchOption));
		//用户信息
		ManageUserBean userBean = manageUserService.findUserById(askBean.getAskerId()+"");
		askBean.setName(userBean.getName());
		return askBean;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getAnswerList(com.jftt.wifi.bean.ask.SearchOptionBean) <BR>
	 * Method name: getAnswerList <BR>
	 * Description: 获取问问回答列表 <BR>
	 * Remark: <BR>
	 * @param searchOption 查询条件
	 * @return  <BR>
	 */
	@Override
	public List<AskAnswerBean> getAnswerList(SearchOptionBean searchOption) {
		List<AskAnswerBean> list = askAnswerDaoMapper.getAskAnswerList(searchOption);
		for(AskAnswerBean ask : list){
			if(ask.getIsAnonymous() == 1){
				ask.setReplyerName("匿名");
			}
		}
		return list;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#add(com.jftt.wifi.bean.ask.AskAnswerBean) <BR>
	 * Method name: add <BR>
	 * Description: 插入回答内容 <BR>
	 * Remark: <BR>
	 * @param bean 问问Bean <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void add(AskAnswerBean bean) {
		askAnswerDaoMapper.add(bean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#addAskDetail(com.jftt.wifi.bean.ask.AskDetailBean) <BR>
	 * Method name: addAskDetail <BR>
	 * Description: 添加问题详情 <BR>
	 * Remark: <BR>
	 * @param bean 问问Bean
	 * @throws DataBaseException  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addAskDetail(AskDetailBean bean) throws DataBaseException {
		askDetailDaoMapper.addThematicAsk(bean);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getMyAskList(com.jftt.wifi.bean.ask.SearchOptionBean) <BR>
	 * Method name: getMyAskList <BR>
	 * Description: 获取我的提问列表 <BR>
	 * Remark: <BR>
	 * @param searchOption 查询条件
	 * @return List<AskDetailBean>
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional
	public List<AskDetailBean> getMyAskList(SearchOptionBean searchOption) throws Exception{
		List<AskDetailBean> list = askDetailDaoMapper.getMyAskList(searchOption);
		for(AskDetailBean bean : list){
			Integer id = bean.getId();
			//问题回答数量
			searchOption.setQuestionId(id);
			int answerCount = askAnswerDaoMapper.getAskAnswerCount(searchOption);
			//满意答案数量
			searchOption.setIsSatisfactory(1);//满意答案
			int satisfactoryAnswerCount = askAnswerDaoMapper.getAskAnswerCount(searchOption);
			bean.setAnswerCount(answerCount);
			bean.setSatisfactoryAnswerCount(satisfactoryAnswerCount);
			//我的回答数量
			//searchOption.setReplyerId(searchOption.getUserId());
			//int myAnswerCount = askAnswerDaoMapper.getAskAnswerCount(searchOption);
			//bean.setMyAnswerCount(myAnswerCount);
		}
		return list;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#deleteAsk(java.lang.Integer) <BR>
	 * Method name: deleteAsk <BR>
	 * Description: 删除提问 <BR>
	 * Remark: <BR>
	 * @param id 问题id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteAsk(Integer id) throws Exception {
		//删除提问
		askDetailDaoMapper.deleteAsk(id);
		//删除问题的回答
		askAnswerDaoMapper.deleteAnswer(id);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getAskCountForSearch(java.lang.String) <BR>
	 * Method name: getAskCountForSearch <BR>
	 * Description: 根据搜索条件查询问问数量 <BR>
	 * Remark: <BR>
	 * @param searchContent 查询条件
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getAskCountForSearch(String searchContent) throws Exception {
		return askDetailDaoMapper.getAskCountForSearch(searchContent);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getAskListForSearch(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String) <BR>
	 * Method name: getAskListForSearch <BR>
	 * Description: 根据搜索条件查询问问 <BR>
	 * Remark: <BR>
	 * @param searchContent 查询条件
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<AskVoForMMGrid>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<AskVoForMMGrid> getAskListForSearch(String searchContent,
			String sortName, String sortOrder, Integer fromNum, String pageSize)
			throws Exception {
		return askDetailDaoMapper.getAskListForSearch(searchContent,sortName,sortOrder,fromNum,pageSize);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getAsksByTitle(java.lang.String) <BR>
	 * Method name: getAsksByTitle <BR>
	 * Description: 根据标题查询问问 <BR>
	 * Remark: <BR>
	 * @param title 问问标题
	 * @return List<AskDetailBean>
	 * @throws Exception <BR>
	 */
	@Override
	public List<AskDetailBean> getAsksByTitle(String title) throws Exception {
		return askDetailDaoMapper.getAsksByTitle(title);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getManageThematicAskRoles(java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: getManageThematicAskRoles <BR>
	 * Description: 获取专题问答管理员用户id列表 <BR>
	 * Remark: <BR>
	 * @param limitsUrl 专题问答管理Url
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<Integer>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<Integer> getManageThematicAskRoles(String limitsUrl,
			String companyId, String subCompanyId) throws Exception {
		return managePageDaoMapper.getManageThematicAskRoles(limitsUrl,companyId,subCompanyId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getSatisfactAnswerForThematicAsk(java.lang.String) <BR>
	 * Method name: getSatisfactAnswerForThematicAsk <BR>
	 * Description: 获取专题问答的满意答案 <BR>
	 * Remark: <BR>
	 * @param askId 问问id
	 * @return AskAnswerBean
	 * @throws Exception  <BR>
	 */
	@Override
	public AskAnswerBean getSatisfactAnswerForThematicAsk(String askId)
			throws Exception {
		return askAnswerDaoMapper.getSatisfactoryAnswer(Integer.parseInt(askId));
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getOtherAnswers(java.lang.String) <BR>
	 * Method name: getOtherAnswers <BR>
	 * Description: 获取满意答案之外的其他回答列表 <BR>
	 * Remark: <BR>
	 * @param askId 问问id
	 * @return List<AskAnswerBean>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<AskAnswerBean> getOtherAnswers(String askId) throws Exception {
		return askAnswerDaoMapper.getOtherAnswers(Integer.parseInt(askId));
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getSearchTypes(java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: getSearchTypes <BR>
	 * Description: 获取该分类下的所有直接子分类 <BR>
	 * Remark: <BR>
	 * @param typeId 分类id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<AskTypeBean>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<AskTypeBean> getSearchTypes(String typeId,String companyId,String subCompanyId) throws Exception {
		List<AskTypeBean> types = new ArrayList<AskTypeBean>();
		if(typeId == null || typeId.trim() == ""){
			//查询所有根节点分类
			types = askTypeDaoMapper.getRootTypes(companyId, subCompanyId);
		}else{
			//查询该分类下的所有直接子分类
			types = askTypeDaoMapper.getChildTypes(Integer.parseInt(typeId));
		}
		return types;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyAskService#getAnswerListIncludeShield(com.jftt.wifi.bean.ask.SearchOptionBean) <BR>
	 * Method name: getAnswerListIncludeShield <BR>
	 * Description: 获取问问回答列表 （包含已屏蔽） <BR>
	 * Remark: <BR>
	 * @param searchOption 查询条件
	 * @return List<AskAnswerBean>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<AskAnswerBean> getAnswerListIncludeShield(SearchOptionBean searchOption) 
			throws DataBaseException {
		List<AskAnswerBean> list = askAnswerDaoMapper.getAnswerListIncludeShield(searchOption);
		for(AskAnswerBean ask : list){
			if(ask.getIsAnonymous() == 1){
				ask.setReplyerName("匿名");
			}
		}
		return list;
	}
	
}
