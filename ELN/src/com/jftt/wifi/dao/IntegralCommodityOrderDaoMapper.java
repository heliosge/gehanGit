package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.IntegralCommodityOrderBean;
import com.jftt.wifi.bean.vo.ExchangeRecordsSearchVo;
import com.jftt.wifi.bean.vo.IntegralCommodityOrderVo;

/**
 * class IntegralCommodityOrderDaoMapper <BR>
 * class description: 积分商品订单dao <BR>
 * Remark: <BR>
 * @author zhangbocheng
 */
public interface IntegralCommodityOrderDaoMapper {


	/** zhangbocheng  start*/
	
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查积分商品订单 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityOrderBean<BR>
	 */
	public IntegralCommodityOrderBean getById(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查积分商品 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityOrderVo<BR>
	 */
	public IntegralCommodityOrderVo getDetailById(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: insertIntegralCommodityOrder <BR>
	 * Description: 新增积分商品订单 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer insertIntegralCommodityOrder(IntegralCommodityOrderBean bean) throws Exception;

	/**
	 * Method name: updateIntegralCommodityOrder <BR>
	 * Description: 修改积分商品订单 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateIntegralCommodityOrder(IntegralCommodityOrderBean bean) throws Exception;

	/**
	 * Method name: deleteIntegralCommodityOrder <BR>
	 * Description: 删除积分商品订单 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteIntegralCommodityOrder(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: post<BR>
	 * Description:  发货<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void post(IntegralCommodityOrderBean bean) throws Exception;

	
	
	
	/**
	 * Method name: selectIntegralCommodityOrderList <BR>
	 * Description: 查询积分商品订单 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public List<IntegralCommodityOrderVo> selectIntegralCommodityOrderList(IntegralCommodityOrderVo vo) throws Exception;
	
	
	/**
	 * Method name: selectIntegralCommodityOrderCount <BR>
	 * Description: 查询积分商品订单数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public Integer selectIntegralCommodityOrderCount(IntegralCommodityOrderVo vo) throws Exception;


	
	
	
	/** zhangbocheng  end*/
	
	/** chenrui start*/
	
	/**
	 * 兑换商品
	 * Method name: doExchange <BR>
	 * Description: doExchange <BR>
	 * Remark: <BR>
	 * @param params  void<BR>
	 */
	public void doExchange(IntegralCommodityOrderBean params) throws Exception;


	/**
	 * 获取当前用户的兑换记录
	 * Method name: getUserExchangeRecords <BR>
	 * Description: getUserExchangeRecords <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  List<IntegralCommodityOrderBean><BR>
	 */
	public List<IntegralCommodityOrderBean> getUserExchangeRecords(ExchangeRecordsSearchVo params) throws Exception;

	public int getUserExchangeRecordsCount(ExchangeRecordsSearchVo params) throws Exception;


	/**
	 * 确认收货
	 * Method name: doCheckProduct <BR>
	 * Description: doCheckProduct <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param orderId
	 * @throws Exception  void<BR>
	 */
	public void doCheckProduct(@Param("orderId")String orderId) throws Exception;


	/**
	 * 获取订单详情
	 * Method name: getOrderDetailByOrderId <BR>
	 * Description: getOrderDetailByOrderId <BR>
	 * Remark: <BR>
	 * @param orderId
	 * @return  IntegralCommodityOrderBean<BR>
	 */
	public IntegralCommodityOrderBean getOrderDetailByOrderId(@Param("orderId")String orderId) throws Exception;
	
	/** chenrui end*/
}
