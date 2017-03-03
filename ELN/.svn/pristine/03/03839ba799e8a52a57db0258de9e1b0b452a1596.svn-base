/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: OamServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月4日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.OamBarBean;
import com.jftt.wifi.bean.OamTopicBean;
import com.jftt.wifi.bean.OamTopicCategoryBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.vo.OamTopicVo;
import com.jftt.wifi.common.DataBaseConstant;
import com.jftt.wifi.dao.KnowledgeContestContestDaoMapper;
import com.jftt.wifi.dao.ManageCompanyDaoMapper;
import com.jftt.wifi.dao.ManageIndustryCategoryDaoMapper;
import com.jftt.wifi.dao.OamTopicDaoMapper;
import com.jftt.wifi.dao.ResCourseDaoMapper;
import com.jftt.wifi.service.ManageIndustryCategoryService;
import com.jftt.wifi.service.OamService;

/**
 * class name:OamServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月4日
 * @author JFTT)Lu Yunlong
 */
@Service("oamService")
public class OamServiceImpl implements OamService {
	
	@Resource
	private OamTopicDaoMapper oamTopicDaoMapper;
	
	@Resource
	private ResCourseDaoMapper resCourseDaoMapper;
	
	@Resource
	private ManageIndustryCategoryDaoMapper manageIndustryCategoryDaoMapper;
	
	@Resource
	private ResCourseDaoMapper resCourseDao;
	
	@Resource(name = "knowledgeContestContestDaoMapper")
	private KnowledgeContestContestDaoMapper knowledgeContestContestDaoMapper;
	

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#selectTopicById(java.lang.String) <BR>
	 * Method name: selectTopicById <BR>
	 * Description: 根据id查询专题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	 */

	@Override
	public OamTopicBean selectTopicById(String id) throws Exception {
		return oamTopicDaoMapper.selectOamTopicById(Integer.parseInt(id));
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#selectOamTopicList(com.jftt.wifi.bean.vo.OamTopicVo) <BR>
	 * Method name: selectOamTopicList <BR>
	 * Description: 查询专题列表 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  <BR>
	 */

	@Override
	public List<OamTopicBean> selectOamTopicList(OamTopicVo vo)
			throws Exception {
		return oamTopicDaoMapper.selectOamTopicList(vo);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#selectOamTopicCount(com.jftt.wifi.bean.vo.OamTopicVo) <BR>
	 * Method name: selectOamTopicCount <BR>
	 * Description: 查询专题列表数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public int selectOamTopicCount(OamTopicVo vo)
			throws Exception {
		return oamTopicDaoMapper.selectOamTopicCount(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#insertTopic(com.jftt.wifi.bean.OamTopicBean) <BR>
	 * Method name: insertTopic <BR>
	 * Description: 添加专题 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception  <BR>
	 */

	@Override
	public Integer insertTopic(OamTopicBean bean) throws Exception {
		// TODO Auto-generated method stub
		oamTopicDaoMapper.insertTopic(bean);
		return bean.getId();
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#insertTopicAndCourse(java.lang.Integer, java.lang.String[]) <BR>
	 * Method name: insertTopicAndCourse <BR>
	 * Description: 添加专题、课程关系 <BR>
	 * Remark: <BR>
	 * @param topicId
	 * @param courseIds
	 * @throws Exception  <BR>
	 */

	@Override
	public void insertTopicAndCourse(Integer topicId, String courseId)
			throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("topicId",topicId);
		param.put("courseId",courseId);
		oamTopicDaoMapper.insertTopicAndCourse(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#insertTopicAndIndustry(java.lang.Integer, java.lang.String[]) <BR>
	 * Method name: insertTopicAndIndustry <BR>
	 * Description: 添加专题、推广企业关系 <BR>
	 * Remark: <BR>
	 * @param topicId
	 * @param industryIds
	 * @throws Exception  <BR>
	 */

	@Override
	public void insertTopicAndIndustry(Integer topicId, String industryId)
			throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("topicId",topicId);
		param.put("industryId",industryId);
		oamTopicDaoMapper.insertTopicAndIndustry(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#deleteTopicAndCourseAndIndustry(com.jftt.wifi.bean.OamTopicBean) <BR>
	 * Method name: deleteTopicAndCourseAndIndustry <BR>
	 * Description: 删除专题、课程、推广企业关系 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  <BR>
	 */

	@Override
	public void deleteTopicAndCourseAndIndustry(OamTopicBean bean)
			throws Exception {
		oamTopicDaoMapper.deleteTopicAndCourse(bean.getId());
		oamTopicDaoMapper.deleteTopicAndIndustry(bean.getId());
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#updateTopic(com.jftt.wifi.bean.OamTopicBean) <BR>
	 * Method name: updateTopic <BR>
	 * Description: 修改专题 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception  <BR>
	 */

	@Override
	public void updateTopic(OamTopicBean bean) throws Exception {
		oamTopicDaoMapper.updateTopic(bean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#spreadOamTopic(java.lang.String) <BR>
	 * Method name: spreadOamTopic <BR>
	 * Description: 推广专题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	 */

	@Override
	public void spreadOamTopic(String id) throws Exception {
		oamTopicDaoMapper.spreadOamTopic(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#cancelSpreadOamTopic(java.lang.String) <BR>
	 * Method name: cancelSpreadOamTopic <BR>
	 * Description: 取消推广专题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	 */

	@Override
	public void cancelSpreadOamTopic(String id) throws Exception {
		oamTopicDaoMapper.cancelSpreadOamTopic(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#deleteOamTopic(java.lang.String) <BR>
	 * Method name: deleteOamTopic <BR>
	 * Description: 删除专题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	 */

	@Override
	public void deleteOamTopic(String id) throws Exception {
		oamTopicDaoMapper.deleteOamTopic(id);
		oamTopicDaoMapper.deleteTopicAndCourse(Integer.parseInt(id));
		oamTopicDaoMapper.deleteTopicAndIndustry(Integer.parseInt(id));
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#selectOamBar(java.util.Map) <BR>
	 * Method name: selectOamBar <BR>
	 * Description: 查询运维栏信息 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<OamBarBean> selectOamBar(Map<String, Object> param)
			throws Exception {
		List<OamBarBean> list = oamTopicDaoMapper.selectOamBar(param);
		for(OamBarBean bean : list){
			if(bean.getType() == DataBaseConstant.OAM_BAR_SPREAD_TOPIC){
				bean.setSpreadObject(oamTopicDaoMapper.selectOamTopicById(bean.getSpreadId()));
			} else if (bean.getType() == DataBaseConstant.OAM_BAR_SPREAD_COURSE){
				bean.setSpreadObject(resCourseDaoMapper.selectCourseById(bean.getSpreadId()));
			} else if (bean.getType() == DataBaseConstant.OAM_BAR_SPREAD_MEGAGAME){
				bean.setSpreadObject(knowledgeContestContestDaoMapper.getById(bean.getSpreadId()));
			}
		}
		return list;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#updateOamBarSpread(com.jftt.wifi.bean.OamBarBean) <BR>
	 * Method name: 修改运维栏推广 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param vo
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateOamBarSpread(OamBarBean vo) throws Exception {
		oamTopicDaoMapper.updateOamBarSpread(vo);
	}
	
	@Override
	public void insertOamBarSpread(OamBarBean vo) throws Exception {
		oamTopicDaoMapper.insertOamBarSpread(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#selectOamTopicCategory(java.util.Map) <BR>
	 * Method name: selectOamTopicCategory <BR>
	 * Description: 查询专题数量 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<OamTopicCategoryBean> selectOamTopicCategory(Map<String, Object> param) throws Exception  {
		return oamTopicDaoMapper.selectOamTopicCategory(param);
	}

	/**
	 * Method name: deleteOamBarSpreadById <BR>
	 * Description: 删除运维栏的推荐 <BR>
	 * Remark: <BR>
	 * @param barId
	 * @throws Exception  void<BR>
	 */
	@Override
	public void deleteOamBarSpreadById(Integer barId) throws Exception {
		oamTopicDaoMapper.deleteOamBarSpreadById(barId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#selectOamCourseByTopic(java.lang.String) <BR>
	 * Method name: 查询专题推广课程 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param topicId
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<ResCourseBean> selectOamCourseByTopic(String topicId)
			throws Exception {
		return resCourseDao.selectOamCourseByTopic(topicId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#selectOamIndustryByTopic(java.lang.String) <BR>
	 * Method name: 查询专题推广企业分类 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param topicId
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<ManageIndustryCategoryService> selectOamIndustryByTopic(
			String topicId) throws Exception {
		return manageIndustryCategoryDaoMapper.selectOamIndustryByTopic(topicId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#cancelSpreadGame(java.lang.String) <BR>
	 * Method name: 取消推广大赛 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	public void cancelSpreadGame(String id) throws Exception {
		oamTopicDaoMapper.cancelSpreadGame(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#spreadGame(java.lang.String) <BR>
	 * Method name: 推广大赛 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	public void spreadGame(String id) throws Exception {
		oamTopicDaoMapper.spreadGame(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#selectChooseSpread(java.util.Map) <BR>
	 * Method name: 查询可推广的资源 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<Map> selectChooseSpread(Map<String, Object> param) throws Exception {
		return oamTopicDaoMapper.selectChooseSpread(param);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#selectChooseSpreadCount(java.util.Map) <BR>
	 * Method name: selectChooseSpreadCount <BR>
	 * Description:  查询可推广的资源的数量 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public int selectChooseSpreadCount(Map<String, Object> param) throws Exception {
		return oamTopicDaoMapper.selectChooseSpreadCount(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#updateBarSpreadOrder(com.jftt.wifi.bean.OamBarBean) <BR>
	 * Method name: updateBarSpreadOrder <BR>
	 * Description: 修改运维栏的推广内容 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateBarSpreadOrder(OamBarBean vo) throws Exception {
		oamTopicDaoMapper.updateBarSpreadOrder(vo);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#updateBarStyle(com.jftt.wifi.bean.OamBarBean) <BR>
	 * Method name: 修改推广类型 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param vo
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateBarStyle(OamBarBean vo) throws Exception{
		oamTopicDaoMapper.updateBarStyle(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#deleteOamBarSpread(com.jftt.wifi.bean.OamBarBean) <BR>
	 * Method name: 删除中运维栏的推广 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param bar
	 * @throws Exception  <BR>
	*/
	@Override
	public void deleteOamBarSpread(OamBarBean bar) throws Exception {
		oamTopicDaoMapper.deleteOamBarSpread(bar);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#insertOamTopicCategory(com.jftt.wifi.bean.OamTopicCategoryBean) <BR>
	 * Method name: insertOamTopicCategory <BR>
	 * Description: 新增专题体系 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  <BR>
	*/
	@Override
	public void insertOamTopicCategory(OamTopicCategoryBean bean)
			throws Exception {
		oamTopicDaoMapper.insertOamTopicCategory(bean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#deleteOamTopicCategoryById(int) <BR>
	 * Method name: deleteOamTopicCategoryById <BR>
	 * Description: 删除专题体系 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	public void deleteOamTopicCategoryById(int id) throws Exception {
		oamTopicDaoMapper.deleteOamTopicCategoryById(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.OamService#updateOamTopicCategory(com.jftt.wifi.bean.OamTopicCategoryBean) <BR>
	 * Method name: updateOamTopicCategory <BR>
	 * Description: 修改专题体系 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateOamTopicCategory(OamTopicCategoryBean bean)
			throws Exception {
		oamTopicDaoMapper.updateOamTopicCategory(bean);
	}

	/**
	 * Method name: deleteOamBarSpreadByTopicId <BR>
	 * Description: 删除该专题的原推荐 <BR>
	 * Remark: <BR>
	 * @param parseInt  void<BR>
	 */
	@Override
	public void deleteOamBarSpreadByTopicId(Map<String, Object> param) {
		oamTopicDaoMapper.deleteOamBarSpreadByTopicId(param);		
	}
	
	

}
