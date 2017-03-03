package com.jftt.wifi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.MallTopicBean;
import com.jftt.wifi.bean.MallTopicCourseBean;
import com.jftt.wifi.bean.vo.MallCourseVo;
import com.jftt.wifi.bean.vo.MallOrderVo;
import com.jftt.wifi.bean.vo.MallTopicVo;
import com.jftt.wifi.dao.MallOrderDaoMapper;
import com.jftt.wifi.dao.MallTopicDaoMapper;
import com.jftt.wifi.service.MallTopicManageService;

@Service
public class MallTopicManageServiceImpl implements MallTopicManageService {
	
	@Autowired
	private MallTopicDaoMapper mallTopicDaoMapper;
	@Autowired
	private MallOrderDaoMapper mallOrderDaoMapper;
	
/** zhangbocheng  start*/
	
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查专题 <BR>
	 * Remark: <BR>
	 * @param id  MallTopicBean<BR>
	 */
	public MallTopicBean getTopicById(Integer id) throws Exception{
		return mallTopicDaoMapper.getById(id);
	}

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查询专题详情 <BR>
	 * Remark: <BR>
	 * @param id  MallTopicBean<BR>
	 */
	public MallTopicVo getTopicDetailById(Integer id) throws Exception{
		return mallTopicDaoMapper.getDetailById(id);
	}

	/**
	 * Method name: checkMallTopicCode <BR>
	 * Description: 检查专题编号重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer checkTopicCode(MallTopicBean bean) throws Exception{
		return mallTopicDaoMapper.checkCode(bean);
	}
	
	/**
	 * Method name: selectCourseListByTopicId <BR>
	 * Description: 感觉专题id查询课程 <BR>
	 * Remark: <BR>
	 * @param topicId  void<BR>
	 */
	
	public List<MallCourseVo> selectCourseListByTopicId(Integer topicId){
		return mallTopicDaoMapper.selectCourseListByTopicId(topicId);
	}
	
	/**
	 * Method name: insertMallTopic <BR>
	 * Description: 新增专题 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer insertMallTopic(MallTopicBean bean) throws Exception{
		mallTopicDaoMapper.insertMallTopic(bean);
		return bean.getId();
	}

	/**
	 * Method name: updateMallTopic <BR>
	 * Description: 修改专题 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateMallTopic(MallTopicBean bean) throws Exception{
		mallTopicDaoMapper.updateMallTopic(bean);
	}

	/**
	 * Method name: deleteMallTopic <BR>
	 * Description: 删除专题<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteMallTopic(Integer id) throws Exception{
		mallTopicDaoMapper.deleteMallTopic(id);
	}

	
	/**
	 * Method name: putawayById <BR>
	 * Description: 上架专题 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void putawayById(Integer id,Integer userId) throws Exception{
		mallTopicDaoMapper.putawayById(id, userId);
	}

	
	/**
	 * Method name: putoutById <BR>
	 * Description: 下架专题 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void putoutById(Integer id) throws Exception{
		mallTopicDaoMapper.putoutById(id);
	}

	
	/**
	 * Method name: selectMallTopicList <BR>
	 * Description: 查询专题list <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public List<MallTopicVo> selectMallTopicList(MallTopicVo vo) throws Exception{
		return mallTopicDaoMapper.selectMallTopicList(vo);
	}
	
	/**
	 * Method name: selectMallTopicCount <BR>
	 * Description: 查询专题数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public Integer selectMallTopicCount(MallTopicVo vo) throws Exception{
            return mallTopicDaoMapper.selectMallTopicCount(vo); 
	}
	
	/**
	 * Method name: insertMallTopicCourse <BR>
	 * Description: 新增专题课程关联 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public void insertMallTopicCourse(MallTopicCourseBean bean) throws Exception{
		mallTopicDaoMapper.insertMallTopicCourse(bean);
	}

	
	
	/**
	 * Method name: deleteMallTopicCourse <BR>
	 * Description: 删除专题课程关联<BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public void deleteMallTopicCourse(MallTopicCourseBean bean) throws Exception{
		mallTopicDaoMapper.deleteMallTopicCourse(bean);
	}
	
	
	/**
	 * 专题销售记录
	 * Method name: selectTopicOrderSellRecord <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  List<MallOrderVo><BR>
	 */
	@Override
	public List<MallOrderVo> selectTopicOrderSellRecord(MallOrderVo vo) throws Exception{
		return mallOrderDaoMapper.selectTopicOrderSellRecord(vo);
	}
	/**
	 * 专题销售记录数目
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer selectTopicOrderSellRecordCount(MallOrderVo vo) throws Exception{
		return mallOrderDaoMapper.selectTopicOrderSellRecordCount(vo);
	}
	
	
	/**
	 * 查询订单内所有专题
	 * Method name: selectTopicListByOrderId <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  List<MallTopicVo><BR>
	 */
	public List<MallTopicVo> selectTopicListByOrderId(Integer id) throws Exception{
		return mallOrderDaoMapper.selectTopicListByOrderId(id);
	}
	
	
	/**
	 * Method name: checkTopicIsOn <BR>
	 * Description: 检查专题是否上架 <BR>
	 * Remark: <BR>
	 * @param id  Integer<BR>
	 */
	public boolean checkTopicIsOn(Integer id) throws Exception{
		return mallTopicDaoMapper.checkTopicIsOn(id)>0;
	}
	/** zhangbocheng  end*/


}
