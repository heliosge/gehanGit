/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: OamTopicDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月4日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.OamBarBean;
import com.jftt.wifi.bean.OamTopicBean;
import com.jftt.wifi.bean.OamTopicCategoryBean;
import com.jftt.wifi.bean.vo.OamTopicVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:OamTopicDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月4日
 * @author JFTT)Lu Yunlong
 */
public interface OamTopicDaoMapper {
	
	/**
	 * Method name: selectOamTopicById <BR>
	 * Description: 根据id获取专题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  OamTopicBean<BR>
	 */
	public OamTopicBean selectOamTopicById(@Param("id")Integer id) throws DataBaseException;
	
	/**luyunlong start*/
	
	/**
	 * Method name: selectOamTopicList <BR>
	 * Description: 查询专题 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  List<OamTopicBean><BR>
	 */
	public List<OamTopicBean> selectOamTopicList(OamTopicVo vo) throws Exception;
	
	/**
	 * Method name: selectOamTopicCount <BR>
	 * Description: 查询专题数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int selectOamTopicCount(OamTopicVo vo) throws Exception;

	/**
	 * Method name: insertTopic <BR>
	 * Description: 新增专题 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	public void insertTopic(OamTopicBean bean) throws Exception;

	/**
	 * Method name: insertTopicAndCourse <BR>
	 * Description: 新增专题、课程关系 <BR>
	 * Remark: <BR>
	 * @param param
	 * @throws Exception  void<BR>
	 */
	public void insertTopicAndCourse(Map<String, Object> param) throws Exception;

	/**
	 * Method name: insertTopicAndIndustry <BR>
	 * Description: 新增专题推广企业 <BR>
	 * Remark: <BR>
	 * @param param
	 * @throws Exception  void<BR>
	 */
	public void insertTopicAndIndustry(Map<String, Object> param) throws Exception;

	/**
	 * Method name: deleteTopicAndCourse <BR>
	 * Description: 删除专题、课程关系 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	public void deleteTopicAndCourse(@Param("topicId")Integer id) throws Exception;

	/**
	 * Method name: deleteTopicAndIndustry <BR>
	 * Description: 删除专题推广企业 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	public void deleteTopicAndIndustry(@Param("topicId")Integer id) throws Exception;

	/**
	 * Method name: updateTopic <BR>
	 * Description: 修改【专题】 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	public void updateTopic(OamTopicBean bean) throws Exception;

	/**
	 * Method name: spreadOamTopic <BR>
	 * Description: 推广专题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	public void spreadOamTopic(String id) throws Exception;

	/**
	 * Method name: cancelSpreadOamTopic <BR>
	 * Description: 取消推广【专题】 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	public void cancelSpreadOamTopic(@Param("id")String id) throws Exception;

	/**
	 * Method name: deleteOamTopic <BR>
	 * Description: 删除【专题】 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	public void deleteOamTopic(@Param("id")String id) throws Exception;
	
	
	/**
	 * Method name: selectOamBar <BR>
	 * Description: 查询运维栏信息 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws DataBaseException  List<OamBarBean><BR>
	 */
	public List<OamBarBean> selectOamBar(Map<String, Object> param) throws DataBaseException;
	
	/**
	 * Method name: updateOamBarSpread <BR>
	 * Description: 修改运维栏推广 <BR>
	 * Remark: <BR>
	 * @param vo  void<BR>
	 */
	public void updateOamBarSpread(OamBarBean vo);
	
	/**
	 * Method name: insertOamBarSpread <BR>
	 * Description: 新增运维栏推广 <BR>
	 * Remark: <BR>
	 * @param vo  void<BR>
	 */
	public void insertOamBarSpread(OamBarBean vo);
	
	/**
	 * Method name: selectOamTopicCategory <BR>
	 * Description: 查询专题体系 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws DataBaseException  Object<BR>
	 */
	public List<OamTopicCategoryBean> selectOamTopicCategory(Map<String, Object> param) throws DataBaseException;
	
	/**
	 * Method name: deleteOamBarSpreadById <BR>
	 * Description: 删除运维栏的推荐 <BR>
	 * Remark: <BR>
	 * @param barId
	 * @throws DataBaseException  void<BR>
	 */
	public void deleteOamBarSpreadById(Integer barId)throws DataBaseException;
	
	/**
	 * Method name: cancelSpreadGame <BR>
	 * Description: 取消推广大赛 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void cancelSpreadGame(@Param("id")String id);

	/**
	 * Method name: spreadGame <BR>
	 * Description: 推广大赛 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void spreadGame(@Param("id")String id);
	
	/**
	 * Method name: selectChooseSpread <BR>
	 * Description: 获取推广资源 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<Map><BR>
	 */
	public List<Map> selectChooseSpread(Map<String, Object> param);
	
	/**
	 * Method name: selectChooseSpreadCount <BR>
	 * Description: 获取推广资源 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  int<BR>
	 */
	public int selectChooseSpreadCount(Map<String, Object> param);
	
	/**
	 * Method name: updateBarSpreadOrder <BR>
	 * Description: 修改运维栏的推广资源 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateBarSpreadOrder(OamBarBean bean);
	
	/**
	 * Method name: updateBarStyle <BR>
	 * Description: 修改专题的栏目 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateBarStyle(OamBarBean bean);
	
	/**
	 * Method name: deleteOamBarSpread <BR>
	 * Description: 删除专题原推广位置 <BR>
	 * Remark: <BR>
	 * @param bar  void<BR>
	 */
	public void deleteOamBarSpread(OamBarBean bar);
	
	/**
	 * Method name: insertOamTopicCategory <BR>
	 * Description: 新增专题体系 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	void insertOamTopicCategory(OamTopicCategoryBean bean) throws Exception ;

	/**
	 * Method name: deleteOamTopicCategoryById <BR>
	 * Description: 删除专题体系 <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @throws Exception  void<BR>
	 */
	void deleteOamTopicCategoryById(@Param("id")int id) throws Exception ;

	/**
	 * Method name: updateOamTopicCategory <BR>
	 * Description: 修改专题体系 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	void updateOamTopicCategory(OamTopicCategoryBean bean) throws Exception ;

	/**
	 * Method name: deleteOamBarSpreadByTopicId <BR>
	 * Description:  删除该专题的原推荐 <BR>
	 * Remark: <BR>
	 * @param param  void<BR>
	 */
	public void deleteOamBarSpreadByTopicId(Map<String, Object> param);
	
	/**luyunlong end*/
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getSpecialTopicCount <BR>
	 * Description: 获取专题数量 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @param subCompanyId
	 * @param deleteStatus
	 * @return  Integer<BR>
	 */
	public Integer getSpecialTopicCount(@Param("companyId")Integer companyId,
			@Param("subCompanyId")Integer subCompanyId, 
			@Param("deleteStatus")Integer deleteStatus) throws DataBaseException;


	/**shenhaijun end*/
	
}
