/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: IntegralStoreServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-12-4        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.IntegralCommodityBean;
import com.jftt.wifi.bean.IntegralCommodityCategoryBean;
import com.jftt.wifi.bean.IntegralCommodityOrderBean;
import com.jftt.wifi.bean.MallIntegralProVo;
import com.jftt.wifi.bean.vo.ExchangeRecordsSearchVo;
import com.jftt.wifi.dao.IntegralCommodityCategoryDaoMapper;
import com.jftt.wifi.dao.IntegralCommodityDaoMapper;
import com.jftt.wifi.dao.IntegralCommodityOrderDaoMapper;
import com.jftt.wifi.service.IntegralStoreService;

/**
 * class name:IntegralStoreServiceImpl <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-4
 * @author JFTT)chenrui
 */
@Service
public class IntegralStoreServiceImpl implements IntegralStoreService {

	@Autowired
	private IntegralCommodityCategoryDaoMapper integralCommodityCategoryDaoMapper; 
	@Autowired
	private IntegralCommodityDaoMapper integralCommodityDaoMapper; 
	@Autowired
	private IntegralCommodityOrderDaoMapper integralCommodityOrderDaoMapper;
	
	/**	获取积分商城分类
	 * @Override
	 * @see com.jftt.wifi.service.IntegralStoreService#getMallIntegralCategory() <BR>
	 * Method name: getMallIntegralCategory <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<IntegralCommodityCategoryBean> getMallIntegralCategory() throws Exception {
		return integralCommodityCategoryDaoMapper.getMallIntegralCategory();
	}

	/**
	 * 积分商品类目页获取商品信息
	 * @Override
	 * @see com.jftt.wifi.service.IntegralStoreService#getProductsByCategory(com.jftt.wifi.bean.MallIntegralProVo) <BR>
	 * Method name: getProductsByCategory <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<IntegralCommodityBean> getProductsByCategory(MallIntegralProVo param) throws Exception {
		String type = param.getOrderType();//排序类型  1 积分 2热门
		List<String> results = new ArrayList<String>();
		String categoryId = param.getCategoryId();//当前点击的分类id
		if(categoryId!=null && !categoryId.isEmpty()){
			returnCategoryIds(Integer.parseInt(categoryId), results);
		}
		String childIds = "";
		if(results.size()>0){
			for(String rs : results){
				childIds += rs + ",";
			}
			childIds = childIds.substring(0, childIds.length()-1);
		}
		param.setChildCategoryIds(childIds);
		if("1".equals(type)){
			return integralCommodityDaoMapper.getProductsByCategoryOrderByIntegral(param);
		}else if("2".equals(type)){
			return integralCommodityDaoMapper.getProductsByCategoryOrderByHot(param);
		}else{
			return integralCommodityDaoMapper.getProductsByCategory(param);
		}
	}
	@Override
	public int getProductsByCategoryCount(MallIntegralProVo param) throws Exception {
		List<String> results = new ArrayList<String>();
		String categoryId = param.getCategoryId();//当前点击的分类id
		if(categoryId!=null && !categoryId.isEmpty()){
			returnCategoryIds(Integer.parseInt(categoryId), results);
		}
		String childIds = "";
		if(results.size()>0){
			for(String rs : results){
				childIds += rs + ",";
			}
			childIds = childIds.substring(0, childIds.length()-1);
		}
		param.setChildCategoryIds(childIds);
		return integralCommodityDaoMapper.getProductsByCategoryCount(param);
	}

	private void returnCategoryIds(int categoryId,List<String> results) throws Exception{
		List<IntegralCommodityCategoryBean> cbean  = integralCommodityCategoryDaoMapper.getChildsByParentId(categoryId);
		if(cbean.size()>0){//说明有子分类
			results.add(categoryId+"");
			for(IntegralCommodityCategoryBean be : cbean){
				int tmpId = be.getId();
				returnCategoryIds(tmpId,results);
			}
		}else{
			results.add(categoryId+"");
		}
	}
	/**
	 * 根据积分商品id获取积分商品详细信息
	 */
	@Override
	public IntegralCommodityBean getById(String id) throws Exception {
		return integralCommodityDaoMapper.getById(Integer.parseInt(id));
	}

	/**
	 * 获取兑你喜欢的数据
	 * 业务：兑你喜欢显示兑换量最多的10个商品排行
	 * @Override
	 * @see com.jftt.wifi.service.IntegralStoreService#getYourLike() <BR>
	 * Method name: getYourLike <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<IntegralCommodityBean> getYourLike() throws Exception {
		
		return integralCommodityDaoMapper.getYourLike();
	}

	/**
	 * 兑换商品
	 * @Override
	 * @see com.jftt.wifi.service.IntegralStoreService#doExchange(com.jftt.wifi.bean.IntegralCommodityOrderBean) <BR>
	 * Method name: doExchange <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param params
	 * @throws Exception  <BR>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void doExchange(IntegralCommodityOrderBean params) throws Exception {
		//1、插入兑换记录
		integralCommodityOrderDaoMapper.doExchange(params);
		//2、更新库存和销量
		Integer coid = params.getCommodityId();
		Integer cocount = params.getCommodityCount();
		integralCommodityDaoMapper.updateStock(coid,cocount);
		//3、更新用户积分
		Integer allIntegral = params.getAllIntegral();
		if(allIntegral==null){
			allIntegral = 0;
		}
		int userId = params.getUserId();
		integralCommodityDaoMapper.updateUserIntegral(userId,allIntegral);
	}

	/**
	 * 获取区
	 * @Override
	 * @see com.jftt.wifi.service.IntegralStoreService#getArea(java.util.Map) <BR>
	 * Method name: getArea <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param map
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<Map> getArea(Map<String, Object> map) throws Exception {
		return integralCommodityDaoMapper.getArea(map);
	}

	/**
	 * 获取当前用户可用总积分
	 * @Override
	 * @see com.jftt.wifi.service.IntegralStoreService#getCurrentUserIntegral(java.lang.String) <BR>
	 * Method name: getCurrentUserIntegral <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param userId
	 * @throws Exception  <BR>
	 */
	@Override
	public int getCurrentUserIntegral(String userId) throws Exception {
		HashMap<String, Object> ss = integralCommodityDaoMapper.getCurrentUserIntegral(userId);
		if(ss==null){
			return 0;
		}else{
			String sd = ss.get("total_integral") +"";
			return Integer.parseInt(sd);
		}
	}

	/**
	 * 获取当前用户的兑换记录
	 * @Override
	 * @see com.jftt.wifi.service.IntegralStoreService#getUserExchangeRecords(com.jftt.wifi.bean.vo.ExchangeRecordsSearchVo) <BR>
	 * Method name: getUserExchangeRecords <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param params
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<IntegralCommodityOrderBean> getUserExchangeRecords(ExchangeRecordsSearchVo params) throws Exception {
		return integralCommodityOrderDaoMapper.getUserExchangeRecords(params);
	}

	@Override
	public int getUserExchangeRecordsCount(ExchangeRecordsSearchVo params) throws Exception {
		return integralCommodityOrderDaoMapper.getUserExchangeRecordsCount(params);
	}

	/**
	 * 确认收货
	 * @Override
	 * @see com.jftt.wifi.service.IntegralStoreService#doCheckProduct(java.lang.String, java.lang.String) <BR>
	 * Method name: doCheckProduct <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param orderId
	 * @param userId
	 * @throws Exception  <BR>
	 */
	@Override
	public void doCheckProduct(String orderId) throws Exception {
		integralCommodityOrderDaoMapper.doCheckProduct(orderId);
	}

	/**
	 * 获取订单详情
	 * @Override
	 * @see com.jftt.wifi.service.IntegralStoreService#getOrderDetailByOrderId(java.lang.String) <BR>
	 * Method name: getOrderDetailByOrderId <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param orderId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public IntegralCommodityOrderBean getOrderDetailByOrderId(String orderId) throws Exception {
		
		return integralCommodityOrderDaoMapper.getOrderDetailByOrderId(orderId);
	}
	
}
