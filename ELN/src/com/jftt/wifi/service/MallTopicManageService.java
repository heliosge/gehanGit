package com.jftt.wifi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.MallTopicBean;
import com.jftt.wifi.bean.MallTopicCourseBean;
import com.jftt.wifi.bean.vo.MallCourseVo;
import com.jftt.wifi.bean.vo.MallOrderVo;
import com.jftt.wifi.bean.vo.MallTopicVo;

public interface MallTopicManageService {
	
/** zhangbocheng  start*/
	
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查专题 <BR>
	 * Remark: <BR>
	 * @param id  MallTopicBean<BR>
	 */
	public MallTopicBean getTopicById(Integer id) throws Exception;

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查询专题详情 <BR>
	 * Remark: <BR>
	 * @param id  MallTopicBean<BR>
	 */
	public MallTopicVo getTopicDetailById(Integer id) throws Exception;

	/**
	 * Method name: checkMallTopicCode <BR>
	 * Description: 检查专题编号重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer checkTopicCode(MallTopicBean bean) throws Exception;
	/**
	 * Method name: selectCourseListByTopicId <BR>
	 * Description: 感觉专题id查询课程 <BR>
	 * Remark: <BR>
	 * @param topicId  void<BR>
	 */
	
	public List<MallCourseVo> selectCourseListByTopicId(Integer topicId);
	/**
	 * Method name: insertMallTopic <BR>
	 * Description: 新增专题 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer insertMallTopic(MallTopicBean bean) throws Exception;

	/**
	 * Method name: updateMallTopic <BR>
	 * Description: 修改专题 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateMallTopic(MallTopicBean bean) throws Exception;

	/**
	 * Method name: deleteMallTopic <BR>
	 * Description: 删除专题<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteMallTopic(Integer id) throws Exception;

	
	/**
	 * Method name: putawayById <BR>
	 * Description: 上架专题 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void putawayById(Integer id,Integer userId) throws Exception;

	
	/**
	 * Method name: putoutById <BR>
	 * Description: 下架专题 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void putoutById(Integer id) throws Exception;

	
	/**
	 * Method name: selectMallTopicList <BR>
	 * Description: 查询专题list <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public List<MallTopicVo> selectMallTopicList(MallTopicVo vo) throws Exception;
	
	/**
	 * Method name: selectMallTopicCount <BR>
	 * Description: 查询专题数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public Integer selectMallTopicCount(MallTopicVo vo) throws Exception;
	
	
	/**
	 * Method name: insertMallTopicCourse <BR>
	 * Description: 新增专题课程关联 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void insertMallTopicCourse(MallTopicCourseBean bean) throws Exception;

	
	
	/**
	 * Method name: deleteMallTopicCourse <BR>
	 * Description: 删除专题课程关联<BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void deleteMallTopicCourse(MallTopicCourseBean bean) throws Exception;
	
	
	
	/**
	 * 专题销售记录
	 * Method name: selectTopicOrderSellRecord <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  List<MallOrderVo><BR>
	 */
	List<MallOrderVo> selectTopicOrderSellRecord(MallOrderVo vo) throws Exception;
	/**
	 * 专题销售记录数目
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	Integer selectTopicOrderSellRecordCount(MallOrderVo vo) throws Exception;
	
	/**
	 * 查询订单内所有专题
	 * Method name: selectTopicListByOrderId <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  List<MallTopicVo><BR>
	 */
	List<MallTopicVo> selectTopicListByOrderId(Integer id) throws Exception;
	
	/**
	 * Method name: checkTopicIsOn <BR>
	 * Description: 检查专题是否上架 <BR>
	 * Remark: <BR>
	 * @param id  Integer<BR>
	 */
	public boolean checkTopicIsOn(Integer id) throws Exception;
	
	/** zhangbocheng  end*/

}
