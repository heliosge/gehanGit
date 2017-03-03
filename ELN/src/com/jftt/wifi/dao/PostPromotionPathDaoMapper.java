/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PostPromotionPathDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年9月10日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.PostPromotionPathBean;
import com.jftt.wifi.bean.vo.MyPostPromotionPathVo;
import com.jftt.wifi.bean.vo.PromotionPathDetailVo;
import com.jftt.wifi.bean.vo.PromotionPathStageVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:PostPromotionPathDaoMapper <BR>
 * class description: 晋升路径dao <BR>
 * Remark: <BR>
 * @version 1.00 2015年9月10日
 * @author JFTT)ShenHaijun
 */
public interface PostPromotionPathDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询晋升路径 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  PostPromotionPathBean<BR>
	 */
	public PostPromotionPathBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**
	 * Method name: getMyPromotionPathCount <BR>
	 * Description: 获取我的所有晋升路径的数量 <BR>
	 * Remark: <BR>
	 * @param postId
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getMyPromotionPathCount(@Param("userId")Integer userId, @Param("companyId")Integer companyId,
			@Param("subCompanyId")Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getMyPromotionPath <BR>
	 * Description: 获取我的所有晋升路径 <BR>
	 * Remark: <BR>
	 * @param postId
	 * @param companyId
	 * @param subCompanyId
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<MyPostPromotionPathVo><BR>
	 */
	public List<MyPostPromotionPathVo> getMyPromotionPath(@Param("userId")Integer userId,
			@Param("companyId")Integer companyId, @Param("subCompanyId")Integer subCompanyId,
			@Param("fromNum")Integer fromNum, @Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getPathStages <BR>
	 * Description: 获取该路径所有阶段 <BR>
	 * Remark: <BR>
	 * @param promotionPathId
	 * @return
	 * @throws DataBaseException  List<PromotionPathStageVo><BR>
	 */
	public List<PromotionPathStageVo> getPathStages(@Param("promotionPathId")Integer promotionPathId) throws DataBaseException;
	
	/**
	 * Method name: getRoadDetailsCount <BR>
	 * Description: 获取晋升路径详细数量 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param searchContent
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getRoadDetailsCount(@Param("userId")Integer userId, @Param("searchContent")String searchContent,
			@Param("promotionStatusContinue")Integer promotionStatusContinue) throws DataBaseException;
	
	/**
	 * Method name: getRoadDetails <BR>
	 * Description: 获取晋升路径详细 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param searchContent
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<PromotionPathDetailVo><BR>
	 */
	public List<PromotionPathDetailVo> getRoadDetails(@Param("userId")Integer userId,@Param("searchContent")String searchContent,
			@Param("fromNum")Integer fromNum, @Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getPostIdByStageId <BR>
	 * Description: 查出当前阶段对应的岗位id <BR>
	 * Remark: <BR>
	 * @param stageId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getPostIdByStageId(@Param("stageId")Integer stageId) throws DataBaseException;
	
}
