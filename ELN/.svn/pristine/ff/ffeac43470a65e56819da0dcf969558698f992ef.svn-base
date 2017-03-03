/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: AskDetailDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月17日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ask.AskDetailBean;
import com.jftt.wifi.bean.ask.AskVoForMMGrid;
import com.jftt.wifi.bean.ask.ManageAskVo;
import com.jftt.wifi.bean.ask.SearchOptionBean;
import com.jftt.wifi.bean.thematicAsk.ManageThematicAskVo;
import com.jftt.wifi.bean.thematicAsk.ThematicAskVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:AskDetailDaoMapper <BR>
 * class description: 问问详情dao <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月17日
 * @author JFTT)ShenHaijun
 */
public interface AskDetailDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询问问详情 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  AskDetailBean<BR>
	 */
	public AskDetailBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**
	 * Method name: getManageAskCount <BR>
	 * Description: 获取管理端问问数量 <BR>
	 * Remark: <BR>
	 * @param searchParams
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getManageAskCount(@Param("searchParams")ManageAskVo searchParams) throws DataBaseException;
	
	/**
	 * Method name: getManageAsks <BR>
	 * Description: 获取管理端问问列表 <BR>
	 * Remark: <BR>
	 * @param searchParams
	 * @return
	 * @throws DataBaseException  List<ManageAskVo><BR>
	 */
	public List<ManageAskVo> getManageAsks(@Param("searchParams")ManageAskVo searchParams) throws DataBaseException;
	
	/**
	 * Method name: updateAskTopState <BR>
	 * Description: 改变问问置顶状态 <BR>
	 * Remark: <BR>
	 * @param askId
	 * @param isTop
	 * @param topTime
	 * @throws DataBaseException  void<BR>
	 */
	public void updateAskTopState(@Param("askId")Integer askId, @Param("isTop")Integer isTop, @Param("topTime")Date topTime) throws DataBaseException;
	
	/**
	 * Method name: deleteAsk <BR>
	 * Description: 删除一条问问 <BR>
	 * Remark: <BR>
	 * @param askId
	 * @throws DataBaseException  void<BR>
	 */
	public void deleteAsk(@Param("askId")Integer askId) throws DataBaseException;
	
	/**
	 * Method name: batchDeleteAsks <BR>
	 * Description: 批量删除问问 <BR>
	 * Remark: <BR>
	 * @param ids
	 * @throws DataBaseException  void<BR>
	 */
	public void batchDeleteAsks(@Param("ids")String[] ids) throws DataBaseException;
	
	/**
	 * Method name: updateAskType <BR>
	 * Description: 修改问问分类 <BR>
	 * Remark: <BR>
	 * @param askId
	 * @param typeId
	 * @throws DataBaseException  void<BR>
	 */
	public void updateAskType(@Param("askId")String askId, @Param("typeId")String typeId) throws DataBaseException;
	
	/**
	 * Method name: batchUpdateAskType <BR>
	 * Description: 批量修改问问分类 <BR>
	 * Remark: <BR>
	 * @param askIds
	 * @param typeId
	 * @throws DataBaseException  void<BR>
	 */
	public void batchUpdateAskType(@Param("askIds")String[] askIds, @Param("typeId")String typeId) throws DataBaseException;
	
	/**
	 * Method name: getThematicAskCount <BR>
	 * Description: 根据条件查询管理端专题问答数量 <BR>
	 * Remark: <BR>
	 * @param searchParams
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getThematicAskCount(@Param("searchParams")ManageThematicAskVo searchParams) throws DataBaseException;
	
	/**
	 * Method name: getThematicAsks <BR>
	 * Description: 根据条件查询管理端专题问答列表 <BR>
	 * Remark: <BR>
	 * @param searchParams
	 * @return
	 * @throws DataBaseException  List<ManageThematicAskVo><BR>
	 */
	public List<ManageThematicAskVo> getThematicAsks(@Param("searchParams")ManageThematicAskVo searchParams) throws DataBaseException;
	
	/**
	 * Method name: getThematicAsksByTitle <BR>
	 * Description: 根据标题查询专题问答 <BR>
	 * Remark: <BR>
	 * @param thematicTitle
	 * @return
	 * @throws DataBaseException  List<AskDetailBean><BR>
	 */
	public List<AskDetailBean> getThematicAsksByTitle(@Param("thematicTitle")String thematicTitle) throws DataBaseException;
	
	/**
	 * Method name: addThematicAsk <BR>
	 * Description: 添加专题问答 <BR>
	 * Remark: <BR>
	 * @param thematicAsk
	 * @throws DataBaseException  void<BR>
	 */
	public void addThematicAsk(AskDetailBean thematicAsk) throws DataBaseException;
	
	/**
	 * Method name: deleteThematicAsk <BR>
	 * Description: 删除专题问答 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws DataBaseException  void<BR>
	 */
	public void deleteThematicAsk(@Param("id")Integer id) throws DataBaseException;
	
	/**
	 * Method name: batchDeleteThematicAsks <BR>
	 * Description: 批量删除专题问答 <BR>
	 * Remark: <BR>
	 * @param ids
	 * @throws DataBaseException  void<BR>
	 */
	public void batchDeleteThematicAsks(@Param("ids")String[] ids) throws DataBaseException;
	
	/**
	 * Method name: getAskCountByAskerId <BR>
	 * Description: 获取提问数量 <BR>
	 * Remark: <BR>
	 * @param askerId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getAskCountByAskerId(@Param("askerId")Integer askerId) throws DataBaseException;
	
	/**
	 * Method name: getAskCountByReplyerId <BR>
	 * Description: 获取已回答的问题数量 <BR>
	 * Remark: <BR>
	 * @param replyerId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getAskCountByReplyerId(@Param("replyerId")Integer replyerId) throws DataBaseException;
	
	/**
	 * Method name: getToDealAskCount <BR>
	 * Description: 获取待处理问题数量 <BR>
	 * Remark: <BR>
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getToDealAskCount(@Param("companyId")String companyId,@Param("subCompanyId")String subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getDealedAskCount <BR>
	 * Description: 获取已处理问题数量 <BR>
	 * Remark: <BR>
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getDealedAskCount(@Param("companyId")String companyId,@Param("subCompanyId")String subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getThematicAskList <BR>
	 * Description: 获取专题问答 <BR>
	 * Remark: <BR>
	 * @return
	 * @throws DataBaseException  List<ThematicAskVo><BR>
	 */
	public List<ThematicAskVo> getThematicAskList() throws DataBaseException;
	
	/**
	 * Method name: getToDealAskList <BR>
	 * Description: 获取待解决的问题列表 <BR>
	 * Remark: <BR>
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<AskVoForMMGrid><BR>
	 */
	public List<AskVoForMMGrid> getToDealAskList(@Param("fromNum")Integer fromNum,
			@Param("pageSize")String pageSize,@Param("companyId")String companyId,
			@Param("subCompanyId") String subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getDealedAskList <BR>
	 * Description: 获取已解决的问题列表 <BR>
	 * Remark: <BR>
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<AskVoForMMGrid><BR>
	 */
	public List<AskVoForMMGrid> getDealedAskList(@Param("fromNum")Integer fromNum,
			@Param("pageSize")String pageSize,@Param("companyId")String companyId,
			@Param("subCompanyId") String subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getAskListByAskerId <BR>
	 * Description: 获取我提问的问问列表 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<AskVoForMMGrid><BR>
	 */
	public List<AskVoForMMGrid> getAskListByAskerId(@Param("askerId")String askerId,
			@Param("fromNum")Integer fromNum, @Param("pageSize")String pageSize) throws DataBaseException;
	
	/**
	 * Method name: getAskListByReplyerId <BR>
	 * Description: 获取我回答的问问列表 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<AskVoForMMGrid><BR>
	 */
	public List<AskVoForMMGrid> getAskListByReplyerId(@Param("replyerId")String replyerId,
			@Param("fromNum")Integer fromNum, @Param("pageSize")String pageSize) throws DataBaseException;
	
	/**
	 * Method name: getTypeAskCount <BR>
	 * Description: 获取该分类下的问问数量 <BR>
	 * Remark: <BR>
	 * @param typeList
	 * @param searchContent
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getTypeAskCount(@Param("typeIdList")List<Integer> typeIdList, @Param("searchContent")String searchContent) throws DataBaseException;
	
	/**
	 * Method name: getTypeAskList <BR>
	 * Description: 获取该分类下的问问列表 <BR>
	 * Remark: <BR>
	 * @param typeIdList
	 * @param searchContent
	 * @param sortName
	 * @param sortOrder
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<AskVoForMMGrid><BR>
	 */
	public List<AskVoForMMGrid> getTypeAskList(@Param("typeIdList")List<Integer> typeIdList,
			@Param("searchContent")String searchContent, @Param("sortName")String sortName, @Param("sortOrder")String sortOrder,
			@Param("fromNum")Integer fromNum, @Param("pageSize")String pageSize) throws DataBaseException;
	
	/**
	 * Method name: getAskCountForSearch <BR>
	 * Description: 根据搜索条件查询问问数量 <BR>
	 * Remark: <BR>
	 * @param searchContent
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getAskCountForSearch(@Param("searchContent")String searchContent) throws DataBaseException;
	
	/**
	 * Method name: getAskListForSearch <BR>
	 * Description: 根据搜索条件查询问问 <BR>
	 * Remark: <BR>
	 * @param searchContent
	 * @param sortName
	 * @param sortOrder
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<AskVoForMMGrid><BR>
	 */
	public List<AskVoForMMGrid> getAskListForSearch(@Param("searchContent")String searchContent,
			@Param("sortName")String sortName, @Param("sortOrder")String sortOrder, 
			@Param("fromNum")Integer fromNum, @Param("pageSize")String pageSize) throws DataBaseException;
	
	/**
	 * Method name: getAsksByTitle <BR>
	 * Description: 根据标题查询问问 <BR>
	 * Remark: <BR>
	 * @param title
	 * @return
	 * @throws DataBaseException  List<AskDetailBean><BR>
	 */
	public List<AskDetailBean> getAsksByTitle(@Param("title")String title) throws DataBaseException;
	
	//zhangchen add start
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getMyAskList <BR>
	 * Description: 获取我的提问列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<AskDetailBean><BR>
	 */
	List<AskDetailBean> getMyAskList(SearchOptionBean searchOption);
	//zhangchen add end

}
