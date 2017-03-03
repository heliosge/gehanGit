package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import com.jftt.wifi.bean.OamBarBean;
import com.jftt.wifi.bean.OamTopicBean;
import com.jftt.wifi.bean.OamTopicCategoryBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.vo.OamTopicVo;

public interface OamService {
	
	/**
	 * Method name: selectTopicById <BR>
	 * Description: 根据id查询专题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  OamTopicBean<BR>
	 */
	OamTopicBean selectTopicById(String id) throws Exception;

	/**
	 * Method name: selectOamTopicList <BR>
	 * Description: 查询专题 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  List<OamTopicBean><BR>
	 */
	List<OamTopicBean> selectOamTopicList(OamTopicVo vo) throws Exception;
	
	/**
	 * Method name: selectOamTopicCount <BR>
	 * Description: 查询专题数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  int<BR>
	 */
	int selectOamTopicCount(OamTopicVo vo) throws Exception;

	/**
	 * Method name: insertTopic <BR>
	 * Description: insertTopic <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception  Integer<BR>
	 */
	Integer insertTopic(OamTopicBean bean) throws Exception;

	/**
	 * Method name: insertTopicAndCourse <BR>
	 * Description: 添加专题和课程关系 <BR>
	 * Remark: <BR>
	 * @param topicId
	 * @param courseIds
	 * @throws Exception  void<BR>
	 */
	void insertTopicAndCourse(Integer topicId, String courseId) throws Exception;

	/**
	 * Method name: insertTopicAndIndustry <BR>
	 * Description: 添加专题和行业关系 <BR>
	 * Remark: <BR>
	 * @param topicId
	 * @param industryIds
	 * @throws Exception  void<BR>
	 */
	void insertTopicAndIndustry(Integer topicId, String industryId) throws Exception;

	/**
	 * Method name: deleteTopicAndCourseAndIndustry <BR>
	 * Description: deleteTopicAndCourseAndIndustry <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	void deleteTopicAndCourseAndIndustry(OamTopicBean bean) throws Exception;

	/**
	 * Method name: updateTopic <BR>
	 * Description: 修改 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception  Integer<BR>
	 */
	void updateTopic(OamTopicBean bean) throws Exception;

	/**
	 * Method name: spreadOamTopic <BR>
	 * Description: 推广专题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	void spreadOamTopic(String id) throws Exception;

	/**
	 * Method name: cancelSpreadOamTopic <BR>
	 * Description: 取消推广专题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	void cancelSpreadOamTopic(String id) throws Exception;

	/**
	 * Method name: deleteOamTopic <BR>
	 * Description: 删除专题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	void deleteOamTopic(String id) throws Exception;

	/**
	 * Method name: selectOamBar <BR>
	 * Description: 查看运维栏信息 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<OamBarBean><BR>
	 */
	public List<OamBarBean> selectOamBar(Map<String, Object> param) throws Exception;

	/**
	 * Method name: updateOamBarSpread <BR>
	 * Description: 修改运维栏推广 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @throws Exception  void<BR>
	 */
	void updateOamBarSpread(OamBarBean vo) throws Exception;
	
	/**
	 * Method name: insertOamBarSpread <BR>
	 * Description: 新增运维栏推广 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @throws Exception  void<BR>
	 */
	void insertOamBarSpread(OamBarBean vo) throws Exception;

	/**
	 * Method name: selectOamTopicCategory <BR>
	 * Description: 查询专题体系 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  Object<BR>
	 */
	List<OamTopicCategoryBean> selectOamTopicCategory(Map<String, Object> param) throws Exception ;

	/**
	 * Method name: deleteOamBarSpreadById <BR>
	 * Description: 删除运维栏的推荐 <BR>
	 * Remark: <BR>
	 * @param barId
	 * @throws Exception  void<BR>
	 */
	void deleteOamBarSpreadById(Integer barId) throws Exception ;

	/**
	 * Method name: selectOamCourseByTopic <BR>
	 * Description: 根据专题id获取课程 <BR>
	 * Remark: <BR>
	 * @param topicId
	 * @return
	 * @throws Exception  List<ResCourseBean><BR>
	 */
	List<ResCourseBean> selectOamCourseByTopic(String topicId) throws Exception ;
	
	/**
	 * Method name: selectOamIndustryByTopic <BR>
	 * Description: 根据专题id获取课程 <BR>
	 * Remark: <BR>
	 * @param topicId
	 * @return
	 * @throws Exception  List<ResCourseBean><BR>
	 */
	List<ManageIndustryCategoryService> selectOamIndustryByTopic(String topicId) throws Exception ;

	void cancelSpreadGame(String id) throws Exception ;
	
	void spreadGame(String id) throws Exception ;

	/**
	 * Method name: chooseSpread <BR>
	 * Description: 获取推广资源 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  List<Map><BR>
	 */
	List<Map> selectChooseSpread(Map<String, Object> param) throws Exception ;
	
	/**
	 * Method name: selectChooseSpreadCount <BR>
	 * Description: 获取推广资源数量 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  List<Map><BR>
	 */
	int selectChooseSpreadCount(Map<String, Object> param) throws Exception ;

	/**
	 * Method name: updateBarSpreadOrder <BR>
	 * Description: 修改运维栏的推广 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @throws Exception  void<BR>
	 */
	void updateBarSpreadOrder(OamBarBean vo) throws Exception ;
	
	/**
	 * Method name: updateBarStyle <BR>
	 * Description: 修改运维栏风格 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @throws Exception  void<BR>
	 */
	void updateBarStyle(OamBarBean vo) throws Exception ;

	/**
	 * Method name: deleteOamBarSpread <BR>
	 * Description: 删除原专题推广栏 <BR>
	 * Remark: <BR>
	 * @param bar
	 * @throws Exception  void<BR>
	 */
	void deleteOamBarSpread(OamBarBean bar) throws Exception ;

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
	void deleteOamTopicCategoryById(int parseInt) throws Exception ;

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
	 * Description: 删除该专题的原推荐 <BR>
	 * Remark: <BR>
	 * @param parseInt  void<BR>
	 */
	void deleteOamBarSpreadByTopicId(Map<String, Object> param);
	


}
