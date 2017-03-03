package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.MallOrderBean;
import com.jftt.wifi.bean.vo.MallCourseVo;
import com.jftt.wifi.bean.vo.MallOrderDetailsPageVo;
import com.jftt.wifi.bean.vo.MallOrderVo;
import com.jftt.wifi.bean.vo.MallTopicVo;
import com.jftt.wifi.bean.vo.SearchOrderVo;

public interface MallOrderDaoMapper {

    /**chenrui start*/
	
	MallOrderBean selectByPrimaryKey(@Param("id")Integer id);
	/**
	 * 根据订单id获取订单详细信息
	 * Method name: getOrderDetailsById <BR>
	 * Description: getOrderDetailsById <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  MallOrderBean<BR>
	 */
	MallOrderDetailsPageVo getOrderDetailsById(@Param("id")String id) throws Exception;
    /**
     * 生成订单
     * Method name: addMallOrder <BR>
     * Description: addMallOrder <BR>
     * Remark: <BR>
     * @param orderBean
     * @throws Exception  void<BR>
     */
	int addMallOrder(MallOrderBean orderBean) throws Exception;
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
	 * @param status  void<BR>
	 */
	void updateMallOrderStatus(@Param("orderId")String orderId, @Param("status")String status)throws Exception;

	/**
	 * 删除购买记录
	 * Method name: deleteBuyRecordsByIds <BR>
	 * Description: deleteBuyRecordsByIds <BR>
	 * Remark: <BR>
	 * @param ids  void<BR>
	 */
	void deleteBuyRecordsByIds(@Param("ids")String ids) throws Exception;
	/**
	 * 评价订单
	 * Method name: giveOrderEvaluate <BR>
	 * Description: giveOrderEvaluate <BR>
	 * Remark: <BR>
	 * @param contents  void<BR>
	 */
	void giveOrderEvaluate(@Param("id")String id,@Param("contents")String contents);
	
	/**
	 * 获取购买记录所有数据 ，用于导出
	 * Method name: exportBuyRecords <BR>
	 * Description: exportBuyRecords <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return  List<MallOrderBean><BR>
	 */
	List<MallOrderBean> exportBuyRecords(@Param("userId")String userId) throws Exception;
	
	/**
	 * 检测当前用户对商城课程的评价权限（只有购买人能评价）
	 * Method name: checkUserCanEvaluate <BR>
	 * Description: checkUserCanEvaluate <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @return
	 * @throws Exception  int<BR>
	 */
	int checkUserCanEvaluate(@Param("courseId")String courseId, @Param("userId")String userId) throws Exception;
	
	
	void updateStatusAndPayTime(@Param("orderId")String orderId) throws Exception;
	
	/**
	 * 定时检测订单并更新失效状态
	 * Method name: checkeOrdelInvalidTime <BR>
	 * Description: checkeOrdelInvalidTime <BR>
	 * Remark: <BR>  void<BR>
	 */
	void checkeOrdelInvalidTime()  throws Exception;
	
	/**
	 * 验证当前专题是否已购买过
	 * Method name: checkTopicIsBuyed <BR>
	 * Description: checkTopicIsBuyed <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param mallTopicId
	 * @return  int<BR>
	 */
	int checkTopicIsBuyed( @Param("userId")String userId,  @Param("mallTopicId")String mallTopicId) throws Exception;
	
	/**chenrui end*/
	
/**zhangbocheng end*/
	/**
	 * 订单详情
	 * Method name: getOrderDetailById <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  MallOrderVo<BR>
	 */
	MallOrderVo getOrderDetailById(@Param("id")Integer id) throws Exception;
	
	
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
	 * 查询订单内所有专题
	 * Method name: selectCourseListByOrderId <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  List<MallCourseVo><BR>
	 */
	List<MallCourseVo> selectCourseListByOrderId(@Param("id")Integer id) throws Exception;
	
	
	
	/**
	 * 发货
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	Integer delivery(MallOrderVo vo)throws Exception;
	
	/**
	 * 邮寄发票
	 * @return
	 * @throws Exception
	 */
	Integer postInvoice(MallOrderVo vo)throws Exception;
	
	/**
	 * 订单失效
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer doInvalid(@Param("id")Integer id)throws Exception;
	
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
	 * @param vo
	 * @return
	 * @throws Exception  List<MallTopicVo><BR>
	 */
	List<MallTopicVo> selectTopicListByOrderId(@Param("id")Integer id) throws Exception;
	
	
	
	
	/**zhangbocheng end*/
}