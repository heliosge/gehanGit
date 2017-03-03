package com.jftt.wifi.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jftt.wifi.bean.CourseEvaluationBean;
import com.jftt.wifi.bean.MallCourseCategoryBean;
import com.jftt.wifi.bean.MallOrderBean;
import com.jftt.wifi.bean.MallTopicBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ShoppingCartBean;
import com.jftt.wifi.bean.vo.CommonPagingVo;
import com.jftt.wifi.bean.vo.CourseCaVo;
import com.jftt.wifi.bean.vo.CourseDetailsPageVo;
import com.jftt.wifi.bean.vo.MallCoursePageVo;
import com.jftt.wifi.bean.vo.MallOrderDetailsPageVo;
import com.jftt.wifi.bean.vo.MallTopicDetailVo;
import com.jftt.wifi.bean.vo.SearchOrderVo;

public interface CourseStoreService {
	/**chenrui start*/
	
	/**
	 * 获取首页banner商城专题信息
	 * Method name: getCourseTopic <BR>
	 * Description: getCourseTopic <BR>
	 * Remark: <BR>
	 * @return  List<MallTopicBean><BR>
	 */
	List<MallTopicBean> getCourseTopic() throws Exception;
	/**
	 * 获取精品课程
	 * Method name: getBoutiqueCourse <BR>
	 * Description: getBoutiqueCourse <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  List<MallCoursePageVo><BR>
	 */
	List<MallCoursePageVo> getBoutiqueCourse(String type) throws Exception;
	/**
	 * 获取专题列表
	 * Method name: getMallTopicList <BR>
	 * Description: getMallTopicList <BR>
	 * Remark: <BR>
	 * @return  List<MallTopicBean><BR>
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
	MallTopicDetailVo getMallTopicDetailsById(String id) throws Exception;
	/**
	 * 获取商城课程分类
	 * Method name: getMallCourseCategory <BR>
	 * Description: getMallCourseCategory <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  List<MallCourseCategoryBean><BR>
	 */
	List<MallCourseCategoryBean> getMallCourseCategory() throws Exception;
	/**
	 * 根据商城课程id获取课程详情
	 * Method name: getMailCourseDetailById <BR>
	 * Description: getMailCourseDetailById <BR>
	 * Remark: <BR>
	 * @return  MallCoursePageVo<BR>
	 */
	MallCoursePageVo getMailCourseDetailById(String id) throws Exception;
	/**
	 * 根据商城课程分类 获取课程信息
	 * Method name: getCourseByCategory <BR>
	 * Description: getCourseByCategory <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<MallCoursePageVo><BR>
	 */
	List<MallCoursePageVo> getCourseByCategory(CourseCaVo param) throws Exception;
	int getCourseByCategoryCount(CourseCaVo param) throws Exception;
	/**
	 * 获取当前用户购物车中信息
	 * Method name: getShoppingCartInfo <BR>
	 * Description: getShoppingCartInfo <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return  List<MallCoursePageVo><BR>
	 */
	List<MallCoursePageVo> getShoppingCartInfo(String userId,String searchName,String productType) throws Exception;
	/**
	 * 根据课程id获取课程详细信息，级联获取课程下的章节课件考试信息
	 * Method name: getDetailsByCourseId <BR>
	 * Description: getDetailsByCourseId <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return  CourseDetailsPageVo<BR>
	 */
	CourseDetailsPageVo getDetailsByCourseId(String courseId) throws Exception;
	/**
	 * 添加购物车信息
	 * Method name: addShoppingRecord <BR>
	 * Description: addShoppingRecord <BR>
	 * Remark: <BR>
	 * @param params  void<BR>
	 */
	void addShoppingRecord(ShoppingCartBean params) throws Exception;
	/**
	 * 生成订单
	 * Method name: addMallOrder <BR>
	 * Description: addMallOrder <BR>
	 * Remark: <BR>
	 * @param orderBean
	 * @throws Exception  void<BR>
	 */
	MallOrderBean addMallOrder(MallOrderBean orderBean) throws Exception;
	/**
	 * 移除购物车记录
	 * Method name: removeShoppingRecord <BR>
	 * Description: removeShoppingRecord <BR>
	 * Remark: <BR>
	 * @param params  void<BR>
	 */
	void removeShoppingRecord(String ids) throws Exception;
	/**
	 * 获取购买记录
	 * Method name: getBuyRecords <BR>
	 * Description: getBuyRecords <BR>
	 * Remark: <BR>
	 * @param params
	 * @return
	 * @throws Exception  List<MallOrderBean><BR>
	 */
	List<MallOrderBean> getBuyRecords(SearchOrderVo params) throws Exception;
	int getBuyRecordsCount(SearchOrderVo params) throws Exception;
	/**
	 * 更改订单状态
	 * Method name: updateMallOrderStatus <BR>
	 * Description: updateMallOrderStatus <BR>
	 * Remark: <BR>
	 * @param orderId
	 * @param status
	 * @throws Exception  void<BR>
	 */
	void updateMallOrderStatus(String orderId, String status) throws Exception;
	/**
	 * 获取课程评价信息
	 * Method name: getMallEvaluationCount <BR>
	 * Description: getMallEvaluationCount <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param courseId
	 * @return  int<BR>
	 */
	List<CourseEvaluationBean> getEvaluation(String userId, String courseId, String pageSize, long fromNum) throws Exception;
	int getMallEvaluationCount(String userId, String courseId) throws Exception;
	/**
	 * 根据ids获取对应的商品信息
	 * Method name: getProductInfoByIds <BR>
	 * Description: getProductInfoByIds <BR>
	 * Remark: <BR>
	 * @param ids
	 * @return  List<MallCourseBean><BR>
	 */
	List<HashMap<String, Object>> getProductInfoByIds(String ids,String type) throws Exception;
	/**
	 * 删除购买记录
	 * Method name: deleteBuyRecordsByIds <BR>
	 * Description: deleteBuyRecordsByIds <BR>
	 * Remark: <BR>
	 * @param ids  void<BR>
	 */
	void deleteBuyRecordsByIds(String ids) throws Exception;
	/**
	 * 评价订单
	 * Method name: giveOrderEvaluate <BR>
	 * Description: giveOrderEvaluate <BR>
	 * Remark: <BR>
	 * @param contents  void<BR>
	 */
	void giveOrderEvaluate(String id,String contents) throws Exception;
	/**
	 * 根据订单id获取订单详细信息
	 * Method name: getOrderDetailsById <BR>
	 * Description: getOrderDetailsById <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  MallOrderBean<BR>
	 */
	MallOrderDetailsPageVo getOrderDetailsById(String id) throws Exception;
	
	/**
	 * 导出购买记录
	 * Method name: exportBuyRecords <BR>
	 * Description: exportBuyRecords <BR>
	 * Remark: <BR>
	 * @param userId  void<BR>
	 */
	HSSFWorkbook exportBuyRecords(String userId) throws Exception;
	
	/**
	 * when order is payed, need do something
	 * Method name: behindApplyDoSomething <BR>
	 * Description: behindApplyDoSomething <BR>
	 * Remark: <BR>
	 * @param orderId
	 * @param code  void<BR>
	 */
	void behindApplyDoSomething(HttpServletRequest request,String orderId)  throws Exception ;
	/**
	 * 判断当前课程该用户所在公司是否已购买
	 * Method name: judgeIsBuy <BR>
	 * Description: judgeIsBuy <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param companyId
	 * @param subCompanyId
	 * @return  int<BR>
	 */
	int judgeIsBuy(String courseId, int companyId, int subCompanyId) throws Exception ;
	
	/**
	 * 检测当前用户针对该课程的评价权限  return:Object.type(0:不可评价 1：可评价 2：已评价过)
	 * Method name: checkUserCanEvaluate <BR>
	 * Description: checkUserCanEvaluate <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @return  int<BR>
	 */
	Object checkUserCanEvaluate(String courseId, String userId) throws Exception ;
	/**
	 * 课程评价
	 * Method name: giveEvaluate <BR>
	 * Description: giveEvaluate <BR>
	 * Remark: <BR>
	 * @param courseEvaluationBean  void<BR>
	 */
	void giveEvaluate(CourseEvaluationBean courseEvaluationBean) throws Exception;
	/**
	 * 支付宝结算完成后业务处理
	 * Method name: dealwithAfterPay <BR>
	 * Description: dealwithAfterPay <BR>
	 * Remark: <BR>
	 * @param request
	 * @param orderId  void<BR>
	 */
	void dealwithAfterPay(HttpServletRequest request, String orderId) throws Exception;
	/**
	 * 免费课程直接添加到 已购买课程
	 * Method name: addAlreadyBuy <BR>
	 * Description: addAlreadyBuy <BR>
	 * Remark: <BR>
	 * @param mallCourseId
	 * @param userBean  void<BR>
	 */
	void addAlreadyBuy(String mallCourseId, ManageUserBean userBean) throws Exception;
	/**
	 * 获取购物车中商品数量
	 * Method name: getShoppingCarCount <BR>
	 * Description: getShoppingCarCount <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  int<BR>
	 */
	Integer getShoppingCarCount(String userId) throws Exception;
	/**
	 * 验证当前课程是否已购买
	 * Method name: checkIsBuyed <BR>
	 * Description: checkIsBuyed <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param mallCourseId
	 * @return  int<BR>
	 */
	int checkCourseIsBuyed(String userId, String mallCourseId,String comId,String subId) throws Exception;
	/**
	 * 验证当前专题是否已购买过
	 * Method name: checkTopicIsBuyed <BR>
	 * Description: checkTopicIsBuyed <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param mallTopicId
	 * @return  int<BR>
	 */
	int checkTopicIsBuyed(String userId, String mallTopicId) throws Exception;
	
	
	/**chenrui end*/
}
