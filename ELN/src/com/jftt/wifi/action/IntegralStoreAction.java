/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: IntegralStoreAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-12-4        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.IntegralCommodityBean;
import com.jftt.wifi.bean.IntegralCommodityCategoryBean;
import com.jftt.wifi.bean.IntegralCommodityOrderBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.MallIntegralProVo;
import com.jftt.wifi.bean.MallOrderBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ShoppingCartBean;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.ExchangeRecordsSearchVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.IntegralStoreService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.CommonUtil;

/**	积分商城A
 * class name:IntegralStoreAction <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-4
 * @author JFTT)chenrui
 */
@Controller
@RequestMapping("integralStore")
public class IntegralStoreAction {
	@Autowired
	private IntegralStoreService integralStoreService;
	@Autowired
	private ManageUserService manageUserService;
	private static Logger logger = Logger.getLogger(IntegralStoreAction.class);
	/**
	 * 跳转积分商品类目页
	 * Method name: toProductList <BR>
	 * Description: toProductList <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("toProductList")
	public String toProductList(HttpServletRequest request) {
		return "integral_store/productList";
	}
	/**
	 * 跳转我兑换的商品
	 * Method name: toMyIntegealExchange <BR>
	 * Description: toMyIntegealExchange <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toMyIntegealExchange")
	public String toMyIntegealExchange(HttpServletRequest request) {
		return "integral_store/myIntegralExchange";
	}
	
	@RequestMapping("toProductDetail")
	public String toProductDetail(HttpServletRequest request,String id) {
		request.setAttribute("thisId", id);
		return "integral_store/productDetail";
	}
	/**
	 * 跳转订单详情
	 * Method name: toOrderDetail <BR>
	 * Description: toOrderDetail <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toOrderDetail")
	public String toOrderDetail(HttpServletRequest request,String orderId) {
		request.setAttribute("orderId", orderId);
		return "integral_store/orderDetails";
	}
	/**
	 *  获取积分商城分类
	 * Method name: getMallIntegralCategory <BR>
	 * Description: getMallIntegralCategory <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMallIntegralCategory")
	public Object getMallIntegralCategory(HttpServletRequest request) {
		logger.debug("IntegralStoreAction执行getMallIntegralCategory方法");
		AjaxReturnVo<IntegralCommodityCategoryBean> arv = new AjaxReturnVo<IntegralCommodityCategoryBean>();
		try {
			List<IntegralCommodityCategoryBean> list = integralStoreService.getMallIntegralCategory();
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(IntegralStoreAction.class,e);
		}
		return arv;
	}
	
	/**
	 * 积分商品类目页获取商品信息
	 * Method name: getProductsByCategory <BR>
	 * Description: getProductsByCategory <BR>
	 * Remark: <BR>
	 * @param request
	 * @param param
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getProductsByCategory")
	public Object getProductsByCategory(HttpServletRequest request,MallIntegralProVo param) {
		logger.debug("IntegralStoreAction执行getProductsByCategory方法");
		AjaxReturnVo<IntegralCommodityBean> arv = new AjaxReturnVo<IntegralCommodityBean>();
		try {
			String userId =  (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			ManageUserBean uBean = manageUserService.findUserById(userId);
			param.setUserId(userId);
			param.setCompanyId(uBean.getCompanyId()+"");
			param.setSubCompanyId(uBean.getSubCompanyId()+"");
			
			int count  = integralStoreService.getProductsByCategoryCount(param);
			String pageSize = param.getPageSize();
			String page = param.getPage();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			param.setFromNum(fromNum);
			
			List<IntegralCommodityBean> list = integralStoreService.getProductsByCategory(param);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(IntegralStoreAction.class,e);
		}
		return arv;
	}
	
	@ResponseBody
	@RequestMapping("getProductsByCategoryCount")
	public int getProductsByCategoryCount(HttpServletRequest request,MallIntegralProVo param) {
		logger.debug("IntegralStoreAction执行getProductsByCategoryCount方法");
		AjaxReturnVo<IntegralCommodityBean> arv = new AjaxReturnVo<IntegralCommodityBean>();
		int count = 0;
		try {
			count  = integralStoreService.getProductsByCategoryCount(param);
		} catch (Exception e) {
			logger.debug(IntegralStoreAction.class,e);
		}
		return count;
	}
	/**
	 * 根据积分商品id获取积分商品详细信息
	 * Method name: getById <BR>
	 * Description: getById <BR>
	 * Remark: <BR>
	 * @param request
	 * @param param
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getById")
	public Object getById(HttpServletRequest request,String id) {
		logger.debug("IntegralStoreAction执行getById方法");
		AjaxReturnVo<IntegralCommodityBean> arv = new AjaxReturnVo<IntegralCommodityBean>();
		try {
			IntegralCommodityBean bean = integralStoreService.getById(id);
			arv.setRtnData(bean);
		} catch (Exception e) {
			logger.debug(IntegralStoreAction.class,e);
		}
		return arv;
	}
	
	/**
	 * 获取兑你喜欢的数据
	 * 业务：兑你喜欢显示兑换量最多的10个商品排行
	 * Method name: getYourLike <BR>
	 * Description: getYourLike <BR>
	 * Remark: <BR>
	 * @param request
	 * @param param
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getYourLike")
	public Object getYourLike(HttpServletRequest request) {
		logger.debug("IntegralStoreAction执行getYourLike方法");
		AjaxReturnVo<IntegralCommodityBean> arv = new AjaxReturnVo<IntegralCommodityBean>();
		try {
			
			List<IntegralCommodityBean> list = integralStoreService.getYourLike();
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(IntegralStoreAction.class,e);
		}
		return arv;
	}
	
	/**
	 * 兑换商品
	 * Method name: addShoppingRecord <BR>
	 * Description: addShoppingRecord <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("doExchange")
	public Object addShoppingRecord(HttpServletRequest request,IntegralCommodityOrderBean params) {
		logger.debug("IntegralStoreAction执行doExchange方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			params.setUserId(Integer.parseInt(userId));
			integralStoreService.doExchange(params);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(IntegralStoreAction.class,e);
		}
		return arv;
	}
	/**
	 * 获取区
	 * Method name: initArea <BR>
	 * Description: initArea <BR>
	 * Remark: <BR>
	 * @param request
	 * @param cityId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping(value="initArea")
	public Object initArea(HttpServletRequest request, String cityId){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentId", cityId);
			return integralStoreService.getArea(map);
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	/**
	 * 获取当前用户可用总积分
	 * Method name: getCurrentUserIntegral <BR>
	 * Description: getCurrentUserIntegral <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("getCurrentUserIntegral")
	public int getCurrentUserIntegral(HttpServletRequest request){
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			return integralStoreService.getCurrentUserIntegral(userId);
		}catch (Exception e) {
			logger.debug(IntegralStoreAction.class,e);
		} 
		return 0;
	}
	
	/**
	 * 获取当前用户的兑换记录
	 * Method name: getUserExchangeRecords <BR>
	 * Description: getUserExchangeRecords <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getUserExchangeRecords")
	public Object getUserExchangeRecords(HttpServletRequest request,ExchangeRecordsSearchVo params) {
		logger.debug("IntegralStoreAction执行getUserExchangeRecords方法");
		MMGridPageVoBean<IntegralCommodityOrderBean> mmVo = new MMGridPageVoBean<IntegralCommodityOrderBean>();
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			params.setUserId(userId);
			
			int count  = integralStoreService.getUserExchangeRecordsCount(params);
			String pageSize = params.getPageSize();
			String page = params.getPage();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			params.setFromNum(fromNum);
			List<IntegralCommodityOrderBean> list = integralStoreService.getUserExchangeRecords(params);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(IntegralStoreAction.class,e);
		}
		return mmVo;
	}
	/**
	 * 确认收货
	 * Method name: doCheckProduct <BR>
	 * Description: doCheckProduct <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id  void<BR>
	 */
	@ResponseBody
	@RequestMapping("doCheckProduct")
	public void doCheckProduct(HttpServletRequest request,String orderId){
		try {
			integralStoreService.doCheckProduct(orderId);
		}catch (Exception e) {
			logger.debug(IntegralStoreAction.class,e);
		} 
	}
	
	/**
	 * 获取订单详情
	 * Method name: getOrderDetailByOrderId <BR>
	 * Description: getOrderDetailByOrderId <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getOrderDetailByOrderId")
	public Object getOrderDetailByOrderId(HttpServletRequest request,String orderId) {
		logger.debug("IntegralStoreAction执行getOrderDetailByOrderId方法");
		AjaxReturnVo<IntegralCommodityOrderBean> arv = new AjaxReturnVo<IntegralCommodityOrderBean>();
		try {
			IntegralCommodityOrderBean bean = integralStoreService.getOrderDetailByOrderId(orderId);
			arv.setRtnData(bean);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(IntegralStoreAction.class,e);
		}
		return arv;
	}
	
}
