package com.jftt.wifi.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.MallTopicBean;
import com.jftt.wifi.bean.MallTopicCourseBean;
import com.jftt.wifi.bean.vo.CommonPagingVo;
import com.jftt.wifi.bean.vo.MallCourseVo;
import com.jftt.wifi.bean.vo.MallTopicDetailVo;
import com.jftt.wifi.bean.vo.MallTopicVo;

public interface MallTopicDaoMapper {

	/**chenrui start*/
    MallTopicBean selectByPrimaryKey(Integer id);
    /**
     * 获取首页banner商城专题信息
     * Method name: getCourseTopic <BR>
     * Description: getCourseTopic <BR>
     * Remark: <BR>
     * @return  List<MallTopicBean><BR>
     */
	List<MallTopicBean> getCourseTopic() throws Exception;
	/**
	 * 获取专题列表
	 * Method name: getMallTopicList <BR>
	 * Description: getMallTopicList <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  List<MallTopicBean><BR>
	 */
	List<MallTopicBean> getMallTopicList(CommonPagingVo pageVo) throws Exception;
	int getMallTopicListCount() throws Exception;
	/**
	 * 获取专题及其下课程的详细信息
	 * Method name: getMallTopicDetailsById <BR>
	 * Description: getMallTopicDetailsById <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  MallTopicDetailVo<BR>
	 */
	MallTopicDetailVo getMallTopicDetailsById(@Param("id")String id);

	
	List<MallTopicCourseBean> getByTopicId(@Param("topicId")int relateMallTopicId);
	
	
	/**chenrui end*/
	
/** zhangbocheng  start*/
	
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查专题 <BR>
	 * Remark: <BR>
	 * @param id  MallTopicBean<BR>
	 */
	public MallTopicBean getById(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查询专题详情 <BR>
	 * Remark: <BR>
	 * @param id  MallTopicVo<BR>
	 */
	public MallTopicVo getDetailById(@Param("id")Integer id) throws Exception;

	/**
	 * Method name: checkMallTopicCode <BR>
	 * Description: 检查专题编号重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer checkCode(MallTopicBean bean) throws Exception;
	
	/**
	 * Method name: selectCourseListByTopicId <BR>
	 * Description: 根据专题id查询课程 <BR>
	 * Remark: <BR>
	 * @param topicId  void<BR>
	 */
	
	public List<MallCourseVo> selectCourseListByTopicId(@Param("topicId")Integer topicId);
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
	public void deleteMallTopic(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: putawayById <BR>
	 * Description: 上架专题 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void putawayById(@Param("id")Integer id,@Param("putawayUserId")Integer userId) throws Exception;

	
	/**
	 * Method name: putoutById <BR>
	 * Description: 下架专题 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void putoutById(@Param("id")Integer id) throws Exception;

	
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
	public Integer insertMallTopicCourse(MallTopicCourseBean bean) throws Exception;


	
	
	
	/**
	 * Method name: deleteMallTopicCourse <BR>
	 * Description: 删除专题课程关联<BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void deleteMallTopicCourse(MallTopicCourseBean bean) throws Exception;
	


	/**
	 * Method name: getPrice <BR>
	 * Description: 获取课程价格修改后，关联专题的总价格和原价格<BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public HashMap getPrice(@Param("id")Integer id) throws Exception;
	
	/**
	 * Method name: checkTopicIsOn <BR>
	 * Description: 检查专题是否上架 <BR>
	 * Remark: <BR>
	 * @param vo  Integer<BR>
	 */
	public Integer checkTopicIsOn(@Param("id")Integer id) throws Exception;
	
	
	/** zhangbocheng  end*/

}