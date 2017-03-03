/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseStoreAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月16日        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.action;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.util.AlipayNotify;
import com.jftt.wifi.bean.CourseEvaluationBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.MallCourseCategoryBean;
import com.jftt.wifi.bean.MallOrderBean;
import com.jftt.wifi.bean.MallTopicBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ShoppingCartBean;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.CommonPagingVo;
import com.jftt.wifi.bean.vo.CourseCaVo;
import com.jftt.wifi.bean.vo.CourseDetailsPageVo;
import com.jftt.wifi.bean.vo.MallCoursePageVo;
import com.jftt.wifi.bean.vo.MallOrderDetailsPageVo;
import com.jftt.wifi.bean.vo.MallTopicDetailVo;
import com.jftt.wifi.bean.vo.SearchOrderVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.MallOrderDaoMapper;
import com.jftt.wifi.service.CourseStoreService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.CommonUtil;

/**
 * 	课程商城前台
 * class name:CourseStoreAction <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月16日
 * @author JFTT)chenrui
 */
@Controller
@RequestMapping("courseStore")
public class CourseStoreAction {
	@Autowired
	private CourseStoreService courseStoreService;
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private MallOrderDaoMapper mallOrderDaoMapper;
	
	private static Logger logger = Logger.getLogger(CourseStoreAction.class);
	/**chenrui start*/
	
	/**
	 * 课程商城首页
	 * Method name: toCoursetIndex <BR>
	 * Description: toCoursetIndex <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toCoursetIndex")
	public String toCoursetIndex(HttpServletRequest request) {
		return "course_store/course_index";
	}
	
	/**
	 * 跳转购物车页面
	 * Method name: toBuyCar <BR>
	 * Description: toBuyCar <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toBuyCar")
	public String toBuyCar(HttpServletRequest request) {
		return "course_store/buy_car";
	}
	/**
	 * 跳转课程商城列表页
	 * Method name: toProductList <BR>
	 * Description: toProductList <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toCoursetList")
	public String toProductList(HttpServletRequest request,String categoryId) {
		request.setAttribute("categoryId", categoryId);
		return "course_store/mallCourseList";
	}
	
	/**
	 * 跳转商城课程详情页
	 * Method name: toMallCoursetDetail <BR>
	 * Description: toMallCoursetDetail <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toMallCoursetDetail")
	public String toMallCoursetDetail(HttpServletRequest request,String id) {
		request.setAttribute("id", id);
		// 判断当前用户所在的是公司还是子公司（考虑到集团公司购买后子公司就不需要再继续购买的需求）
		ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		Integer comId = user.getCompanyId();
		Integer subComId = user.getSubCompanyId();
		if(comId == subComId){ // 集团公司
			
		}else{// 子公司
			request.setAttribute("comId", comId);
			request.setAttribute("subId", subComId);
		}
		return "course_store/mallCourseDetail";
	}
	/**
	 * 跳转专题列表
	 * Method name: toTopicList <BR>
	 * Description: toTopicList <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toTopicList")
	public String toTopicList(HttpServletRequest request) {
		return "course_store/topic_list";
	}
	
	/**
	 * 跳转专题详情页面
	 * Method name: toTopicDetail <BR>
	 * Description: toTopicDetail <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping("toTopicDetail")
	public String toTopicDetail(HttpServletRequest request,String id) {
		request.setAttribute("id", id);
		return "course_store/topic_detail";
	}
	/**
	 * 跳转下单页面
	 * Method name: toGenerateOrder <BR>
	 * Description: toGenerateOrder <BR>
	 * Remark: <BR>
	 * @param request
	 * @param ids
	 * @param productType
	 * @return  String<BR>
	 */
	@RequestMapping("toGenerateOrder")
	public String toGenerateOrder(HttpServletRequest request,String ids,String productType) {
		request.setAttribute("ids", ids);
		request.setAttribute("productType", productType);
		return "course_store/generate_order";
	}
	/**
	 * 跳转课程购买记录页面
	 * Method name: toBuyRecords <BR>
	 * Description: toBuyRecords <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toBuyRecords")
	public String toBuyRecords(HttpServletRequest request) {
		return "course_store/buy_records";
	}
	/**
	 * 跳转订单详情
	 * Method name: toOrderDetails <BR>
	 * Description: toOrderDetails <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toOrderDetails")
	public String toOrderDetails(HttpServletRequest request,String orderId) {
		request.setAttribute("orderId", orderId);
		return "course_store/orderDetails";
	}
	/**
	 * 获取首页banner商城专题信息
	 * Method name: getCourseTopic <BR>
	 * Description: getCourseTopic <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getCourseTopic")
	public Object getCourseTopic(HttpServletRequest request) {
		logger.debug("CourseStoreAction执行getCourseTopic方法");
		AjaxReturnVo<MallTopicBean> arv = new AjaxReturnVo<MallTopicBean>();
		try {
			List<MallTopicBean> list = courseStoreService.getCourseTopic();
			arv.setRtnDataList(list);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	
	/**
	 * 验证当前课程是否已购买
	 * Method name: checkCourseIsBuyed <BR>
	 * Description: checkCourseIsBuyed <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("checkCourseIsBuyed")
	public int checkCourseIsBuyed(HttpServletRequest request,String mallCourseId,String comId,String subId) {
		logger.debug("CourseStoreAction执行checkCourseIsBuyed方法");
		String userId =  (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
		int result = 0;
		try {
			result = courseStoreService.checkCourseIsBuyed(userId,mallCourseId,comId,subId);
			
		} catch (Exception e) {
			logger.debug(CourseStoreAction.class,e);
		}
		return result;
	}
	/**
	 * 验证当前专题是否已购买过
	 * Method name: checkTopicIsBuyed <BR>
	 * Description: checkTopicIsBuyed <BR>
	 * Remark: <BR>
	 * @param request
	 * @param mallCourseId
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("checkTopicIsBuyed")
	public int checkTopicIsBuyed(HttpServletRequest request,String mallTopicId) {
		logger.debug("CourseStoreAction执行checkTopicIsBuyed方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		String userId =  (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
		try {
			return courseStoreService.checkTopicIsBuyed(userId,mallTopicId);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return 0;
	}
	
	/**
	 * 获取精品、免费、最新课程
	 * Method name: getBoutiqueCourse <BR>
	 * Description: getBoutiqueCourse <BR>
	 * Remark: <BR> type[1:精品2：免费]
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getBoutiqueCourse")
	public Object getBoutiqueCourse(HttpServletRequest request,String type) {
		logger.debug("CourseStoreAction执行getBoutiqueCourse方法");
		AjaxReturnVo<MallCoursePageVo> arv = new AjaxReturnVo<MallCoursePageVo>();
		try {
			List<MallCoursePageVo> list = courseStoreService.getBoutiqueCourse(type);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	/**
	 * 获取专题列表
	 * Method name: getMallTopicList <BR>
	 * Description: getMallTopicList <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMallTopicList")
	public Object getMallTopicList(HttpServletRequest request,CommonPagingVo pageVo) {
		logger.debug("CourseStoreAction执行getMallTopicList方法");
		AjaxReturnVo<MallTopicBean> arv = new AjaxReturnVo<MallTopicBean>();
		try {
			int count = courseStoreService.getMallTopicListCount();
			String pageSize = pageVo.getPageSize();
			String page = pageVo.getPage();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			pageVo.setFromNum(fromNum);
			List<MallTopicBean> list = courseStoreService.getMallTopicList(pageVo);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	@ResponseBody
	@RequestMapping("getMallTopicListCount")
	public int getMallTopicListCount(HttpServletRequest request) {
		logger.debug("CourseStoreAction执行getMallTopicListCount方法");
		int count = 0;
		try {
			count = courseStoreService.getMallTopicListCount();
		} catch (Exception e) {
			logger.debug(CourseStoreAction.class,e);
		}
		return count;
	}
	/**
	 * 获取专题及其下课程的详细信息
	 * Method name: getMallTopicDetailsById <BR>
	 * Description: getMallTopicDetailsById <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMallTopicDetailsById")
	public Object getMallTopicDetailsById(HttpServletRequest request,String id) {
		logger.debug("CourseStoreAction执行getMallTopicDetailsById方法");
		AjaxReturnVo<MallTopicDetailVo> arv = new AjaxReturnVo<MallTopicDetailVo>();
		try {
			MallTopicDetailVo vo = courseStoreService.getMallTopicDetailsById(id);
			arv.setRtnData(vo);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	/**
	 * 获取商品课程分类
	 * Method name: getMallCourseCategory <BR>
	 * Description: getMallCourseCategory <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMallCourseCategory")
	public Object getMallCourseCategory(HttpServletRequest request) {
		logger.debug("CourseStoreAction执行getMallCourseCategory方法");
		AjaxReturnVo<MallCourseCategoryBean> arv = new AjaxReturnVo<MallCourseCategoryBean>();
		try {
			List<MallCourseCategoryBean> list = courseStoreService.getMallCourseCategory();
			arv.setRtnDataList(list);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	
	/**
	 * 根据商城课程id获取课程详情
	 * Method name: getMailCourseDetailById <BR>
	 * Description: getMailCourseDetailById <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMailCourseDetailById")
	public Object getMailCourseDetailById(HttpServletRequest request,String id) {
		logger.debug("CourseStoreAction执行getMailCourseDetailById方法");
		AjaxReturnVo<MallCoursePageVo> arv = new AjaxReturnVo<MallCoursePageVo>();
		try {
			MallCoursePageVo bean = courseStoreService.getMailCourseDetailById(id);
			arv.setRtnData(bean);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	/**
	 * 根据商城课程分类 获取课程信息
	 * Method name: getCourseByCategory <BR>
	 * Description: getCourseByCategory <BR>
	 * Remark: <BR>
	 * @param request
	 * @param type
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getCourseByCategory")
	public Object getCourseByCategory(HttpServletRequest request,CourseCaVo param) {
		logger.debug("CourseStoreAction执行getCourseByCategory方法");
		AjaxReturnVo<MallCoursePageVo> arv = new AjaxReturnVo<MallCoursePageVo>();
		try {
			String userId =  (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			ManageUserBean uBean = manageUserService.findUserById(userId);
			param.setUserId(userId);
			param.setCompanyId(uBean.getCompanyId()+"");
			param.setSubCompanyId(uBean.getSubCompanyId()+"");
			
			int count  = courseStoreService.getCourseByCategoryCount(param);
			String pageSize = param.getPageSize();
			String page = param.getPage();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			param.setFromNum(fromNum);
			
			List<MallCoursePageVo> list = courseStoreService.getCourseByCategory(param);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	
	@ResponseBody
	@RequestMapping("getCourseByCategoryCount")
	public int getCourseByCategoryCount(HttpServletRequest request,CourseCaVo param) {
		logger.debug("CourseStoreAction执行getCourseByCategoryCount方法");
		int count=0;
		try {
			String userId =  (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			ManageUserBean uBean = manageUserService.findUserById(userId);
			param.setUserId(userId);
			param.setCompanyId(uBean.getCompanyId()+"");
			param.setSubCompanyId(uBean.getSubCompanyId()+"");
			count = courseStoreService.getCourseByCategoryCount(param);
		} catch (Exception e) {
			logger.debug(CourseStoreAction.class,e);
		}
		return count;
	}
	/**
	 * 获取当前用户购物车中信息
	 * Method name: getShoppingCartInfo <BR>
	 * Description: getShoppingCartInfo <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getShoppingCartInfo")
	public Object getShoppingCartInfo(HttpServletRequest request,String searchName,String productType) {
		logger.debug("CourseStoreAction执行getShoppingCartInfo方法");
		AjaxReturnVo<MallCoursePageVo> arv = new AjaxReturnVo<MallCoursePageVo>();
		try {
			String userId =  (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			List<MallCoursePageVo> list = courseStoreService.getShoppingCartInfo(userId,searchName,productType);
			arv.setCount(list.size());
			arv.setRtnDataList(list);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	
	/**
	 * 根据课程id获取课程详细信息，级联获取课程下的章节课件考试信息
	 * Method name: getDetailsByCourseId <BR>
	 * Description: getDetailsByCourseId <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getDetailsByCourseId")
	public Object getDetailsByCourseId(HttpServletRequest request,String courseId) {
		logger.debug("CourseStoreAction执行getDetailsByCourseId方法");
		AjaxReturnVo<CourseDetailsPageVo> arv = new AjaxReturnVo<CourseDetailsPageVo>();
		try {
			CourseDetailsPageVo bean = courseStoreService.getDetailsByCourseId(courseId);
			arv.setRtnData(bean);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	/**
	 * 添加购物车信息
	 * Method name: addShoppingRecord <BR>
	 * Description: addShoppingRecord <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("addShoppingRecord")
	public Object addShoppingRecord(HttpServletRequest request,ShoppingCartBean params) {
		logger.debug("CourseStoreAction执行addShoppingRecord方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			params.setUserId(Integer.parseInt(userId));
			courseStoreService.addShoppingRecord(params);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	/**
	 * 移除购物车记录
	 * Method name: removeShoppingRecord <BR>
	 * Description: removeShoppingRecord <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("removeShoppingRecord")
	public Object removeShoppingRecord(HttpServletRequest request,String ids) {
		logger.debug("CourseStoreAction执行removeShoppingRecord方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			courseStoreService.removeShoppingRecord(ids);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	/**
	 * 生成订单
	 * Method name: addMallOrder <BR>
	 * Description: addMallOrder <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("addMallOrder")
	public Object addMallOrder(HttpServletRequest request,MallOrderBean orderBean) {
		logger.debug("CourseStoreAction执行addMallOrder方法");
		AjaxReturnVo<MallOrderBean> arv = new AjaxReturnVo<MallOrderBean>();
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			orderBean.setUserId(Integer.parseInt(userId));
			MallOrderBean result = courseStoreService.addMallOrder(orderBean);
			arv.setRtnData(result);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	/**
	 * 获取购买记录
	 * Method name: getBuyRecords <BR>
	 * Description: getBuyRecords <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getBuyRecords")
	public Object getBuyRecords(HttpServletRequest request,SearchOrderVo params) {
		logger.debug("CourseStoreAction执行getBuyRecords方法");
		MMGridPageVoBean<MallOrderBean> mmVo = new MMGridPageVoBean<MallOrderBean>();
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			params.setUserId(userId);
			int count = courseStoreService.getBuyRecordsCount(params);
			String pageSize = params.getPageSize();
			String page = params.getPage();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			params.setFromNum(fromNum);
			List<MallOrderBean> list = courseStoreService.getBuyRecords(params);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(CourseStoreAction.class,e);
		}
		return mmVo;
	}
	
	/**
	 * 导出购买记录
	 * Method name: exportBuyRecords <BR>
	 * Description: exportBuyRecords <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("exportBuyRecords")
	public void exportBuyRecords(HttpServletRequest request,HttpServletResponse response) {
		logger.debug("CourseStoreAction执行exportBuyRecords方法");
		MMGridPageVoBean<MallOrderBean> mmVo = new MMGridPageVoBean<MallOrderBean>();
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			
			HSSFWorkbook workbook = courseStoreService.exportBuyRecords(userId);
			response.addHeader("Content-Type","application/x-msdownload;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename="+new String("购买记录.xls".getBytes(),"iso8859-1"));
			//得到向客户端输出二进制数据的对象 
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			workbook.write(toClient);
			toClient.close();
			
			
			
		} catch (Exception e) {
			logger.debug(CourseStoreAction.class,e);
		}
	}
	/**
	 * 更改订单状态
	 * Method name: cancelMallOrder <BR>
	 * Description: cancelMallOrder <BR>
	 * Remark: <BR>
	 * @param request
	 * @param orderBean
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("updateMallOrderStatus")
	public Object cancelMallOrder(HttpServletRequest request,String orderId,String status) {
		logger.debug("CourseStoreAction执行updateMallOrderStatus方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			courseStoreService.updateMallOrderStatus(orderId,status);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	/**
	 * 获取购物车中商品数量
	 * Method name: getShoppingCarCount <BR>
	 * Description: getShoppingCarCount <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("getShoppingCarCount")
	public int getShoppingCarCount(HttpServletRequest request) {
		
		logger.debug("CourseStoreAction执行getShoppingCarCount方法");
		String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
		int count = 0;
		try {
			Integer res = courseStoreService.getShoppingCarCount(userId);
			if(res != null){
				logger.debug("if购物车中数量不为NULL");
				count = res;
			}
		} catch (Exception e) {
			logger.debug(CourseStoreAction.class,e);
		}
		return count;
	}
	/**
	 * 获取课程评价信息
	 * Method name: getEvaluationCount <BR>
	 * Description: getEvaluationCount <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("getEvaluation")
	public Object getEvaluation(HttpServletRequest request,String courseId,String page,String pageSize) {
		logger.debug("CourseStoreAction执行getEvaluation方法");
		AjaxReturnVo<CourseEvaluationBean> arv = new AjaxReturnVo<CourseEvaluationBean>();
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			int count = courseStoreService.getMallEvaluationCount(userId,courseId);
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			List<CourseEvaluationBean> list = courseStoreService.getEvaluation(userId,courseId,pageSize,fromNum);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	
	@ResponseBody
	@RequestMapping("getEvaluationCount")
	public int getEvaluationCount(HttpServletRequest request,String courseId) {
		logger.debug("CourseStoreAction执行getEvaluationCount方法");
		int count = 0;
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			count = courseStoreService.getMallEvaluationCount(userId,courseId);
		} catch (Exception e) {
			logger.debug(CourseStoreAction.class,e);
		}
		return count;
	}
	
	/**
	 * 根据ids获取对应的商品信息
	 * Method name: getProductInfoByIds <BR>
	 * Description: getProductInfoByIds <BR>
	 * Remark: <BR>
	 * @param request
	 * @param ids
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getProductInfoByIds")
	public Object getProductInfoByIds(HttpServletRequest request,String ids,String type) {
		logger.debug("CourseStoreAction执行getProductInfoByIds方法");
		AjaxReturnVo<HashMap<String, Object>> arv = new AjaxReturnVo<HashMap<String, Object>>();
		try {
			List<HashMap<String, Object>> list = courseStoreService.getProductInfoByIds(ids,type);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	/**
	 * 删除购买记录
	 * Method name: deleteBuyRecordsByIds <BR>
	 * Description: deleteBuyRecordsByIds <BR>
	 * Remark: <BR>
	 * @param request
	 * @param ids  void<BR>
	 */
	@ResponseBody
	@RequestMapping("deleteBuyRecordsByIds")
	public Object deleteBuyRecordsByIds(HttpServletRequest request,String ids) {
		logger.debug("CourseStoreAction执行deleteBuyRecordsByIds方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			courseStoreService.deleteBuyRecordsByIds(ids);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	/**
	 * 评价订单
	 * Method name: giveOrderEvaluate <BR>
	 * Description: giveOrderEvaluate <BR>
	 * Remark: <BR>
	 * @param request
	 * @param contents
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("giveOrderEvaluate")
	public Object giveOrderEvaluate(HttpServletRequest request,String id,String contents) {
		logger.debug("CourseStoreAction执行giveOrderEvaluate方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			courseStoreService.giveOrderEvaluate(id,contents);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	
	/**
	 * 根据订单id获取订单详细信息
	 * Method name: getOrderDetailsById <BR>
	 * Description: getOrderDetailsById <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getOrderDetailsById")
	public Object getOrderDetailsById(HttpServletRequest request,String id) {
		logger.debug("CourseStoreAction执行getOrderDetailsById方法");
		AjaxReturnVo<MallOrderDetailsPageVo> arv = new AjaxReturnVo<MallOrderDetailsPageVo>();
		try {
			MallOrderDetailsPageVo vo = courseStoreService.getOrderDetailsById(id);
			arv.setRtnData(vo);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(CourseStoreAction.class,e);
		}
		return arv;
	}
	
	/**
	 * 判断当前课程该用户所在公司是否已购买
	 * Method name: judgeIsBuy <BR>
	 * Description: judgeIsBuy <BR>
	 * Remark: <BR>
	 * @param request
	 * @param mallCourseId
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("judgeIsBuy")
	public int judgeIsBuy(HttpServletRequest request,String courseId) {
		logger.debug("CourseStoreAction执行judgeIsBuy方法");
		int count = 0;
		try {
			ManageUserBean userbean  = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			int companyId = userbean.getCompanyId();
			int subCompanyId = userbean.getSubCompanyId();
			count = courseStoreService.judgeIsBuy(courseId,companyId,subCompanyId);
		} catch (Exception e) {
			logger.debug(CourseStoreAction.class,e);
		}
		return count;
	}
	
	/**
	 * 检测当前用户针对该课程的评价权限  return: Object.type(0:不可评价 1：可评价 2：已评价过)
	 * Method name: checkUserCanEvaluate <BR>
	 * Description: checkUserCanEvaluate <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("checkUserCanEvaluate")
	public Object checkUserCanEvaluate(HttpServletRequest request,String courseId) {
		logger.debug("CourseStoreAction执行checkUserCanEvaluate方法");
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			return courseStoreService.checkUserCanEvaluate(courseId,userId);
		} catch (Exception e) {
			logger.debug(CourseStoreAction.class,e);
		}
		return null;
	}
	/**
	 * 课程评价
	 * Method name: giveEvaluate <BR>
	 * Description: giveEvaluate <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("giveEvaluate")
	public void giveEvaluate(HttpServletRequest request,CourseEvaluationBean courseEvaluationBean) {
		logger.debug("CourseStoreAction执行giveEvaluate方法");
		try {
			ManageUserBean userBean =  (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			if(userBean != null){
				courseEvaluationBean.setUserId(Integer.parseInt(userBean.getId()));
				courseEvaluationBean.setCompanyId(userBean.getCompanyId());
				courseEvaluationBean.setSubCompanyId(userBean.getSubCompanyId());
			}
			courseStoreService.giveEvaluate(courseEvaluationBean);
		} catch (Exception e) {
			logger.debug(CourseStoreAction.class,e);
		}
	}
	/**
	 * 免费课程直接添加到 已购买课程
	 * Method name: addAlreadyBuy <BR>
	 * Description: addAlreadyBuy <BR>
	 * Remark: <BR>
	 * @param request
	 * @param mallCourseId  void<BR>
	 */
	@ResponseBody
	@RequestMapping("addAlreadyBuy")
	public void addAlreadyBuy(HttpServletRequest request,String mallCourseId) {
		logger.debug("CourseStoreAction执行addAlreadyBuy方法");
		try {
			ManageUserBean userBean =  (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			courseStoreService.addAlreadyBuy(mallCourseId,userBean);
		} catch (Exception e) {
			logger.debug(CourseStoreAction.class,e);
		}
	}
	/**
	 * 支付宝支付跳转
	 * Method name: toAlipay <BR>
	 * Description: toAlipay <BR>
	 * Remark: <BR>
	 * @param request
	 * isAgian : 是否是从订单购买记录处重新付款的
	 * @param param  void<BR>
	 */
	@RequestMapping("toAlipay")
	public String toAlipay(HttpServletRequest request,String orderId,String isAgian){
		
		MallOrderBean orderBean = mallOrderDaoMapper.selectByPrimaryKey(Integer.parseInt(orderId));
		request.setAttribute("payOrderBean", orderBean);
		request.setAttribute("isAgian", isAgian);
		// 开启线程控制当前订单失效操作
		/*TimerSelf timerSelf = new TimerSelf(orderId);
		Thread thread = new Thread(timerSelf);
		thread.start();*/
		return "alipay/alipayapi";
	}
	
	// 支付定时失效类
	class TimerSelf implements Runnable{
		private String orderId;
		public TimerSelf(String id) {
			this.orderId = id;
		}
		
		@Override
		public void run() {
			try {
				logger.debug("===========进入线程体==================");
				// -- 测试用
				//Thread.sleep(1000*10);
				// 定时31分钟
				Thread.sleep(1000*60*31);
				// 查询当前订单状态
				logger.debug("===========定时时间到，开始执行代码==================");
				MallOrderBean orderBean = mallOrderDaoMapper.selectByPrimaryKey(Integer.parseInt(orderId));
				int status = orderBean.getStatus();
				if(status == 0){// 如果还是待付款状态，则订单失效
					courseStoreService.updateMallOrderStatus(orderId,"4");
				}
			} catch (Exception e) {
				logger.debug(CourseStoreAction.class,e);
			}
		}
	}
	/**
	 * 支付宝返回处理
	 * Method name: toAlipayReturn <BR>
	 * Description: toAlipayReturn <BR>
	 * Remark: <BR>
	 * @param request  void<BR>
	 */
	@RequestMapping("toAlipayReturn")
	public String toAlipayReturn(HttpServletRequest request) {
		try {
			logger.debug("===========进入支付宝返回请求==================");
			//获取支付宝GET过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			
			// 业务回传参数  (回传订单id)
			String orderId = new String(request.getParameter("extra_common_param").getBytes("ISO-8859-1"),"UTF-8");

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			
			boolean verify_result = AlipayNotify.verify(params);
			//boolean verify_result = true;
			if(verify_result){//验证成功
				logger.debug("-----------return_url-------再次验证订单状态------------------");
				MallOrderBean mallorderBean = mallOrderDaoMapper.selectByPrimaryKey(Integer.parseInt(orderId));
				int nowStatus = mallorderBean.getStatus();
				logger.debug("-----------return_url-------当前订单状态=>"+ nowStatus +"------------------");
				if(nowStatus == 0){ // 当订单状态 未未付款的时候 才执行业务操作
					logger.debug("---------return_url---------验证回传参数=>SUCCESS------------------");
					logger.debug("-----------return_url-------trade_status=>"+ trade_status +"------------------");
					courseStoreService.dealwithAfterPay(request,orderId);
				}
			}else{
			}
		} catch (Exception e) {
			logger.debug(CourseStoreAction.class,e);
		}
		return "course_store/buy_records"; // 跳转课程购买记录页面
	}
	@ResponseBody
	@RequestMapping("toAlipayNotify")
	public String toAlipayNotify(HttpServletRequest request) throws Exception{
		logger.debug("------------------进入notify请求------------------");
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		
		// 业务回传参数  (回传订单id)
		String orderId = new String(request.getParameter("extra_common_param").getBytes("ISO-8859-1"),"UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				//如果有做过处理，不执行商户的业务程序
				logger.debug("-------------notify-----再次验证订单状态------------------");
				MallOrderBean mallorderBean = mallOrderDaoMapper.selectByPrimaryKey(Integer.parseInt(orderId));
				int nowStatus = mallorderBean.getStatus();
				logger.debug("------------notify------当前订单状态=>"+ nowStatus +"------------------");
				if(nowStatus == 0){ // 当订单状态 未未付款的时候 才执行业务操作
					logger.debug("----------notify--------验证回传参数=>SUCCESS------------------");
					logger.debug("----------notify--------trade_status=>"+ trade_status +"------------------");
					courseStoreService.dealwithAfterPay(request,orderId);
				}
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			return "success";	//请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			return "fail";
		}
	}
	
	
	/**chenrui end*/
}
