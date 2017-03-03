/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: IntegralStoreService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-12-4        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import com.jftt.wifi.bean.IntegralCommodityBean;
import com.jftt.wifi.bean.IntegralCommodityCategoryBean;
import com.jftt.wifi.bean.IntegralCommodityOrderBean;
import com.jftt.wifi.bean.MallIntegralProVo;
import com.jftt.wifi.bean.vo.ExchangeRecordsSearchVo;

/**
 * class name:IntegralStoreService <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-4
 * @author JFTT)chenrui
 */
public interface IntegralStoreService {

	/**
	 * 获取积分商城分类
	 * Method name: getMallIntegralCategory <BR>
	 * Description: getMallIntegralCategory <BR>
	 * Remark: <BR>
	 * @return  List<IntegralCommodityCategoryBean><BR>
	 */
	List<IntegralCommodityCategoryBean> getMallIntegralCategory() throws Exception;

	/**
	 * 积分商品类目页获取商品信息
	 * Method name: getProductsByCategory <BR>
	 * Description: getProductsByCategory <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  List<IntegralCommodityBean><BR>
	 */
	List<IntegralCommodityBean> getProductsByCategory(MallIntegralProVo param) throws Exception;
	int getProductsByCategoryCount(MallIntegralProVo param) throws Exception;

	/**
	 *  根据积分商品id获取积分商品详细信息
	 * Method name: getById <BR>
	 * Description: getById <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  IntegralCommodityBean<BR>
	 */
	IntegralCommodityBean getById(String id) throws Exception;
	/**
	 * 获取兑你喜欢的数据
	 * 业务：兑你喜欢显示兑换量最多的10个商品排行
	 * Method name: getYourLike <BR>
	 * Description: getYourLike <BR>
	 * Remark: <BR>
	 * @return  List<IntegralCommodityBean><BR>
	 */
	List<IntegralCommodityBean> getYourLike() throws Exception;

	/**
	 * 兑换商品
	 * Method name: doExchange <BR>
	 * Description: doExchange <BR>
	 * Remark: <BR>
	 * @param params
	 * @throws Exception  void<BR>
	 */
	void doExchange(IntegralCommodityOrderBean params)throws Exception;

	List<Map> getArea(Map<String, Object> map) throws Exception;

	/**
	 * 获取当前用户可用总积分
	 * Method name: getCurrentUserIntegral <BR>
	 * Description: getCurrentUserIntegral <BR>
	 * Remark: <BR>
	 * @param userId  void<BR>
	 */
	int getCurrentUserIntegral(String userId) throws Exception;

	/**
	 * 获取当前用户的兑换记录
	 * Method name: getUserExchangeRecords <BR>
	 * Description: getUserExchangeRecords <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  List<IntegralCommodityOrderBean><BR>
	 */
	List<IntegralCommodityOrderBean> getUserExchangeRecords(ExchangeRecordsSearchVo params) throws Exception;
	int getUserExchangeRecordsCount(ExchangeRecordsSearchVo params) throws Exception;

	/**
	 * 确认收货
	 * Method name: doCheckProduct <BR>
	 * Description: doCheckProduct <BR>
	 * Remark: <BR>
	 * @param orderId
	 * @throws Exception  void<BR>
	 */
	void doCheckProduct(String orderId) throws Exception;

	/**
	 * 获取订单详情
	 * Method name: getOrderDetailByOrderId <BR>
	 * Description: getOrderDetailByOrderId <BR>
	 * Remark: <BR>
	 * @param orderId
	 * @return  IntegralCommodityOrderBean<BR>
	 */
	IntegralCommodityOrderBean getOrderDetailByOrderId(String orderId) throws Exception;

}
