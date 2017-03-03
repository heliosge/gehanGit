package com.jftt.wifi.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.jftt.wifi.bean.MallCourseApproveBean;
import com.jftt.wifi.bean.MallCourseBean;
import com.jftt.wifi.bean.MallCourseCategoryBean;
import com.jftt.wifi.bean.MallSellRecordBean;
import com.jftt.wifi.bean.vo.MallCourseApproveVo;
import com.jftt.wifi.bean.vo.MallCourseCategoryVo;
import com.jftt.wifi.bean.vo.MallCourseVo;
import com.jftt.wifi.bean.vo.MallOrderVo;
import com.jftt.wifi.bean.vo.MallSellRecordVo;

public interface MallCourseManageService {
	
/**zhangbocheng start*/
	
	/**
	 * Method name: getCategoryById <BR>
	 * Description: 根据id获取商城课程分类 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  MallCourseCategoryBean<BR>
	 */
	public MallCourseCategoryBean getCategoryById(Integer id) throws Exception;
	
	
	/**
	 * Method name: checkCategoryName <BR>
	 * Description: 检查分类重名 <BR>
	 * Remark: <BR>
	 * @param MallCourseCategoryBean  Integer<BR>
	 */
	public Integer checkCategoryName(MallCourseCategoryBean bean) throws Exception;
	
	
	
	/**
	 * Method name: deleteMallCourseCategory <BR>
	 * Description: 删除分类<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public Integer deleteMallCourseCategory(Integer id) throws Exception;

	/**
	 * Method name: updateMallCourseCategory <BR>
	 * Description: 修改分类 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	public void updateMallCourseCategory(MallCourseCategoryBean record) throws Exception;

	/**
	 * Method name: insertMallCourseCategory <BR>
	 * Description: 添加分类 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	public Integer insertMallCourseCategory(MallCourseCategoryBean record) throws Exception;

	

	/**
	 * Method name: moveMallCourseCategory <BR>
	 * Description: 移动分类<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public Integer moveMallCourseCategory(Integer id,Integer flag) throws Exception;

	
	/**
	 * Method name: getMallCourseCategorys <BR>
	 * Description: 查询分类 <BR>
	 * Remark: <BR>
	 * @param
	 * @return  List<MallCourseCategoryBean><BR>
	 */
	public List<MallCourseCategoryVo> getMallCourseCategorys() throws Exception;
	
	/**
	 * Method name: getChildCategory <BR>
	 * Description: 获取分类的子分类 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return    List<MallCourseAllCategoryVo>
	 * @throws Exception  <BR>
	 */
	public List<MallCourseCategoryVo> getChildCategorys(MallCourseCategoryVo vo) throws Exception;
	
	/**
	 * Method name: getUpCategorys <BR>
	 * Description: 获取上一级的所有分类<BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws Exception  List<MallCourseCategoryBean><BR>
	 */
	public List<MallCourseCategoryBean> getUpCategorys(Integer categoryId) throws Exception;

	

	/**
	 * Method name: getCourseById <BR>
	 * Description: 根据id查课程 <BR>
	 * Remark: <BR>
	 * @param id  MallCourseBean<BR>
	 */
	public MallCourseBean getCourseById(Integer id) throws Exception;

	
	/**
	 * Method name: getCourseDetailById <BR>
	 * Description: 根据id查询课程详情 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityBean<BR>
	 */
	public MallCourseVo getCourseDetailById(Integer id) throws Exception;
	
	/**
	 * Method name: checkHaveCopy <BR>
	 * Description: 检查课程是否存在副本 <BR>
	 * Remark: <BR>
	 * @param id  <BR>
	 */
	public Integer getCopyId(Integer id) throws Exception;

	/**
	 * Method name: checkCourseCode <BR>
	 * Description: 检查课程编号重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer checkCourseCode(MallCourseVo bean) throws Exception;
	
	/**
	 * Method name: insertMallCourse <BR>
	 * Description: 新增课程 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer insertMallCourse(MallCourseBean bean) throws Exception;

	
	/**
	 * Method name: insertMallCourse <BR>
	 * Description: 新增课程 <BR>
	 * Remark: <BR>
	 * @param vo  void<BR>
	 */
	public MallCourseVo insertMallCourse(MallCourseVo vo) throws Exception;

	/**
	 * Method name: updateMallCourse <BR>
	 * Description: 修改课程 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateMallCourse(MallCourseBean bean) throws Exception;

	/**
	 * Method name: deleteMallCourse <BR>
	 * Description: 删除课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteMallCourse(Integer id) throws Exception;

	/**
	 * Method name: setCourseStatus <BR>
	 * Description: 设置课程提交审核状态 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void setCourseStatus(Integer id,Integer status) throws Exception;

	
	/**
	 * Method name: putawayById <BR>
	 * Description: 上架课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void putawayById(Integer id,Integer userId) throws Exception;

	
	/**
	 * Method name: putoutById <BR>
	 * Description: 下架积分商品 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public boolean putoutById(Integer id) throws Exception;

	
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
	 * Method name: checkCourseIsOn <BR>
	 * Description: 检查课程是否上架 <BR>
	 * Remark: <BR>
	 * @param id  <BR>
	 */
	public boolean checkCourseIsOn(Integer id) throws Exception;
	
	
	
	/**
	 * Method name: selectCompanyMallCourseList <BR>
	 * Description: 查询企业课程list <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public List<MallCourseVo> selectCompanyMallCourseList(MallCourseVo vo) throws Exception;
	
	/**
	 * Method name: selectCompanyMallCourseCount <BR>
	 * Description: 查询企业课程数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public Integer selectCompanyMallCourseCount(MallCourseVo vo) throws Exception;
	
	
	
	/**mallCourseApprove  课程审核 */
	
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查审核记录 <BR>
	 * Remark: <BR>
	 * @param id  MallCourseApproveBean<BR>
	 */
	public MallCourseApproveBean getApproveById(Integer id) throws Exception;

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查询记录详情 <BR>
	 * Remark: <BR>
	 * @param id  MallCourseApproveVo<BR>
	 */
	public MallCourseApproveVo getApproveDetailById(Integer id) throws Exception;

	/**
	 * Method name: getApproveDetailListById <BR>
	 * Description: 根据id查询记录详情 <BR>
	 * Remark: <BR>
	 * @param id  MallCourseApproveVo<BR>
	 */
	public List<MallCourseApproveVo> getApproveDetailListById(Integer id) throws Exception;
	/**
	 * Method name: checkMallCourseApprove <BR>
	 * Description: 检查审核记录是否重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public boolean checkMallCourseApprove(MallCourseApproveBean bean) throws Exception;
	

	/**
	 * Method name: insertMallCourseApprove <BR>
	 * Description: 新增审核记录 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer insertMallCourseApprove(MallCourseApproveBean bean) throws Exception;

	/**
	 * Method name: updateMallCourseApprove <BR>
	 * Description: 修改审核记录 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateMallCourseApprove(MallCourseApproveBean bean) throws Exception;

	/**
	 * Method name: deleteMallCourseApprove <BR>
	 * Description: 删除审核记录 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteMallCourseApprove(Integer id) throws Exception;

	

	
	/**
	 * Method name: doCheckApprove <BR>
	 * Description: 审核 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void doCheckApprove(MallCourseApproveBean bean) throws Exception;

	
	/**
	 * Method name: selectMallCourseApproveList <BR>
	 * Description: 查询审核记录list <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public List<MallCourseApproveVo> selectMallCourseApproveList(MallCourseApproveVo vo) throws Exception;
	
	/**
	 * Method name: selectMallCourseApproveCount <BR>
	 * Description: 查询记录数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public Integer selectMallCourseApproveCount(MallCourseApproveVo vo) throws Exception;
	
	
	
	/**
	 * Method name: deleteCompanyCourse <BR>
	 * Description: 删除企业课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteCompanyCourse(Integer id) throws Exception;
	
	
	
	
	/**
	 * 课程销售记录
	 * Method name: selectCourseOrderSellRecord <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  List<MallOrderVo><BR>
	 */
	List<MallOrderVo> selectCourseOrderSellRecord(MallOrderVo vo) throws Exception;
	/**
	 * 课程销售记录数目
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	Integer selectCourseOrderSellRecordCount(MallOrderVo vo) throws Exception;
	
	
	
	/**
	 * 订单详情
	 * Method name: getOrderDetailById <BR>
	 * Remark: <BR>
	 * @param id
	 * @return MallOrderVo
	 * @throws Exception  <BR>
	 */
	MallOrderVo getOrderDetailById(Integer id) throws Exception;
	
	/**
	 * 发货
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	void delivery(MallOrderVo vo)throws Exception;
	
	/**
	 * 邮寄发票
	 * @return
	 * @throws Exception
	 */
	void postInvoice(MallOrderVo vo)throws Exception;
	
	/**
	 * 订单失效
	 * @param id
	 * @return
	 * @throws Exception
	 */
	void doInvalid(Integer id)throws Exception;

	/**
	 * 查询订单内所有课程
	 * Method name: selectCourseListByOrderId <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  List<MallCourseVo><BR>
	 */
	List<MallCourseVo> selectCourseListByOrderId(Integer id) throws Exception;
	

	/** 课程购买记录 */
	/**
	 * Method name: getSellRecordById <BR>
	 * Description: 根据id查记录 <BR>
	 * Remark: <BR>
	 * @param id  MallSellRecordBean<BR>
	 */
	public MallSellRecordBean getSellRecordById(Integer id) throws Exception;

	
	/**
	 * Method name: getSellRecordDetailById <BR>
	 * Description: 根据id查询记录详情 <BR>
	 * Remark: <BR>
	 * @param id  MallSellRecordVo<BR>
	 */
	public MallSellRecordVo getSellRecordDetailById(Integer id) throws Exception;

	/**
	 * Method name: checkIsBuy <BR>
	 * Description: 检查是否已经购买课程<BR>
	 * Remark: <BR>
     * @param courseId
	 * @param userId  <BR>
	 */
	public Integer checkIsBuy(Integer courseId,Integer userId) throws Exception;
	
	/**
	 * Method name: selectMallSellRecordList <BR>
	 * Description: 查询课程购买记录 <BR>
	 * Remark: <BR>
	 * @param vo  List<MallSellRecordVo><BR>
	 */
	
	public List<MallSellRecordVo> selectMallSellRecordList(MallSellRecordVo vo)throws Exception;
	
	/**
	 * Method name: selectMallSellRecordCount <BR>
	 * Description: 查询课程购买记录数目 <BR>
	 * Remark: <BR>
	 * @param vo  Integer<BR>
	 */
	
	public Integer selectMallSellRecordCount(MallSellRecordVo vo)throws Exception;
	/**
	 * Method name: insertMallSellRecord <BR>
	 * Description: 新增记录 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer insertMallSellRecord(MallSellRecordBean bean) throws Exception;

	/**
	 * Method name: updateMallSellRecord <BR>
	 * Description: 修改记录 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateMallSellRecord(MallSellRecordBean bean) throws Exception;

	/**
	 * Method name: deleteMallSellRecord <BR>
	 * Description: 删除记录<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteMallSellRecord(Integer id) throws Exception;

	
	/**
	 * Method name: pay <BR>
	 * Description: 付款 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void pay(Integer id,Integer userId,String payUserName) throws Exception;

	
	/**
	 * Method name: gathering <BR>
	 * Description: 收款 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void gathering(Integer id,Integer gatheringUserId,String gatheringUserName) throws Exception;

	
	/**
	 * Method name: getTotalMoney <BR>
	 * Description: 查询总金额 <BR>
	 * Remark: <BR>
	 * @param vo  Integer<BR>
	 */
	
	public BigDecimal getTotalMoney(MallSellRecordVo vo)throws Exception;
	
	/**
	 * Method name: getPayMoney <BR>
	 * Description: 查询已付款金额 <BR>
	 * Remark: <BR>
	 * @param vo  Integer<BR>
	 */
	
	public BigDecimal getPayMoney(MallSellRecordVo vo)throws Exception;
	
	/**zhangbocheng end*/

}
