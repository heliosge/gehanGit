package com.jftt.wifi.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.MallCourseBean;
import com.jftt.wifi.bean.vo.CourseCaVo;
import com.jftt.wifi.bean.vo.CourseDetailsPageVo;
import com.jftt.wifi.bean.vo.MallCoursePageVo;
import com.jftt.wifi.bean.vo.MallCourseVo;

public interface MallCourseDaoMapper {

	/**chenrui start*/
   
	MallCourseBean selectByPrimaryKey(Integer id);
    /**
     * 获取精品课程
     * Method name: getBoutiqueCourse <BR>
     * Description: getBoutiqueCourse <BR>
     * Remark: <BR>
     * @return
     * @throws Exception  List<MallCoursePageVo><BR>
     */
	List<MallCoursePageVo> getBoutiqueCourse(@Param("type")String type) throws Exception;
	/**
	 *  根据商城课程id获取课程详情
	 * Method name: getMailCourseDetailById <BR>
	 * Description: getMailCourseDetailById <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  MallCoursePageVo<BR>
	 */
	MallCoursePageVo getMailCourseDetailById(@Param("id")String id) throws Exception;
	
	
	
	int getCourseByCategoryCount(CourseCaVo param) throws Exception;
	/**
	 * 按上架时间
	 * Method name: getCourseByCategoryOrderDefault <BR>
	 * Description: getCourseByCategoryOrderDefault <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  List<MallCoursePageVo><BR>
	 */
	List<MallCoursePageVo> getCourseByCategoryOrderDefault(CourseCaVo param) throws Exception;
	/**
	 * 按销量
	 * Method name: getCourseByCategoryOrderSaleNum <BR>
	 * Description: getCourseByCategoryOrderSaleNum <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  List<MallCoursePageVo><BR>
	 */
	List<MallCoursePageVo> getCourseByCategoryOrderSaleNum(CourseCaVo param) throws Exception;
	/**
	 * 按评价
	 * Method name: getCourseByCategoryOrderEvaluate <BR>
	 * Description: getCourseByCategoryOrderEvaluate <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  List<MallCoursePageVo><BR>
	 */
	List<MallCoursePageVo> getCourseByCategoryOrderEvaluate(CourseCaVo param) throws Exception;
	/**
	 * 获取当前用户购物车中信息
	 * Method name: getShoppingCartInfo <BR>
	 * Description: getShoppingCartInfo <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return  List<MallCoursePageVo><BR>
	 */
	List<MallCoursePageVo> getShoppingCartInfo(@Param("userId")String userId,@Param("searchName")String searchName,@Param("productType")String productType) throws Exception;
	
	/**
	 * 根据课程id获取课程详细信息，级联获取课程下的章节课件考试信息
	 * Method name: getDetailsByCourseId <BR>
	 * Description: getDetailsByCourseId <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return
	 * @throws Exception  CourseDetailsPageVo<BR>
	 */
	CourseDetailsPageVo getDetailsByCourseId(@Param("courseId")String courseId) throws Exception;
	
	/**
	 * 根据ids获取对应的商品信息
	 * Method name: getProductInfoByIds <BR>
	 * Description: getProductInfoByIds <BR>
	 * Remark: <BR>
	 * @param ids
	 * @return  List<MallCourseBean><BR>
	 */
	List<HashMap<String, Object>> getProductInfoByIds(@Param("ids")String ids,@Param("type")String type) throws Exception;

	
	int checkCourseIsBuyed(@Param("userId")String userId, @Param("mallCourseId")String mallCourseId);
	
	
	/**
	 * 判断集团公司是否已购买
	 * Method name: checkBuyByCompany <BR>
	 * Description: checkBuyByCompany <BR>
	 * Remark: <BR>
	 * @param mallCourseId
	 * @param comId
	 * @return  int<BR>
	 */
	Integer checkBuyByCompany(@Param("mallCourseId")String mallCourseId,@Param("comId") String comId) throws Exception;
	
	
	/**chenrui end*/
	
/** zhangbocheng  start*/
	
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查课程 <BR>
	 * Remark: <BR>
	 * @param id  MallCourseBean<BR>
	 */
	public MallCourseBean getById(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查询课程详情 <BR>
	 * Remark: <BR>
	 * @param id  MallCourseVo<BR>
	 */
	public MallCourseVo getDetailById(@Param("id")Integer id) throws Exception;

	/**
	 * Method name: getCopyId <BR>
	 * Description: 检查课程是否存在副本 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer getCopyId(@Param("id")Integer id) throws Exception;

	/**
	 * Method name: checkMallCourseCode <BR>
	 * Description: 检查课程编号重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer checkCode(MallCourseVo bean) throws Exception;
	
	/**
	 * Method name: insertMallCourse <BR>
	 * Description: 新增课程 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer insertMallCourse(MallCourseBean bean) throws Exception;

	/**
	 * Method name: updateMallCourse <BR>
	 * Description: 修改课程 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateMallCourse(MallCourseBean bean) throws Exception;


	/**
	 * Method name: checkCourseIsOn <BR>
	 * Description: 检查课程是否上架 <BR>
	 * Remark: <BR>
	 * @param vo  Integer<BR>
	 */
	public Integer checkCourseIsOn(@Param("id")Integer id) throws Exception;
	
	/**
	 * Method name: deleteMallCourse <BR>
	 * Description: 删除课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteMallCourse(@Param("id")Integer id) throws Exception;

	/**
	 * Method name: setCourseStatus <BR>
	 * Description: 设置课程提交审核状态 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void setCourseStatus(@Param("id")Integer id,@Param("status")Integer status) throws Exception;

	
	/**
	 * Method name: putawayById <BR>
	 * Description: 上架课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void putawayById(@Param("id")Integer id,@Param("putawayUserId")Integer userId) throws Exception;

	
	/**
	 * Method name: putoutById <BR>
	 * Description: 下架课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void putoutById(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: selectMallCourseList <BR>
	 * Description: 查询课程list <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public List<MallCourseVo> selectMallCourseList(MallCourseVo vo) throws Exception;
	
	/**
	 * Method name: selectMallCourseCount <BR>
	 * Description: 查询课程数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public Integer selectMallCourseCount(MallCourseVo vo) throws Exception;
	
	/**
	 * Method name: selectCompanyMallCourseList <BR>
	 * Description: 查询课程list <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public List<MallCourseVo> selectCompanyMallCourseList(MallCourseVo vo) throws Exception;
	
	/**
	 * Method name: selectCompanyMallCourseCount <BR>
	 * Description: 查询课程数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public Integer selectCompanyMallCourseCount(MallCourseVo vo) throws Exception;
	

	/**
	 * Method name: deleteCompanyCourse <BR>
	 * Description: 删除企业课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteCompanyCourse(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: isInTopic <BR>
	 * Description: 查询课程关联的专题是否都下架 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public Integer isInTopic(@Param("id")Integer id) throws Exception;
	
	
	/**
	 * Method name: getRelateTopicIdList <BR>
	 * Description: 查询课程关联的专题的id <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public List<Integer> getRelateTopicIdList(@Param("id")Integer id) throws Exception;
	
	/**
	 * Method name: deleteMallCourseUnCheck <BR>
	 * Description: 删除课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteMallCourseUnCheck(@Param("id")Integer id) throws Exception;
	
	/** zhangbocheng  end*/
}