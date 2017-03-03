package com.jftt.wifi.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.IntegralCommodityBean;
import com.jftt.wifi.bean.IntegralCommodityCategoryBean;
import com.jftt.wifi.bean.IntegralCommodityOrderBean;
import com.jftt.wifi.bean.LogisticsCompanyBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.IntegralCommodityCategoryVo;
import com.jftt.wifi.bean.vo.IntegralCommodityOrderVo;
import com.jftt.wifi.bean.vo.IntegralCommodityVo;
import com.jftt.wifi.bean.vo.LearnPlanBeanVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.IntegralCommodityManageService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.JsonUtil;


@Controller
@RequestMapping(value="integral/commodity/manage")
public class IntegralCommodityManageAction {
	
	private static final Logger log = Logger.getLogger(IntegralCommodityManageAction.class);
	
	@Autowired
	private IntegralCommodityManageService integralCommodityManageService;
	
	@Autowired
	private ManageUserService manageUserService;
	
	@Autowired
	private ManageCompanyService manageCompanyService;
	
	/**zhangbocheng start*/
	
	
	/**
	 * Method name: toCommodityListPage <BR>
	 * Description: 跳转到积分商品管理页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCommodityListPage")
	public String toCommodityListPage(HttpServletRequest request){
		return "integralCommodityManage/commodityList";
	}
	
	/**
	 * Method name: toOrderListPage <BR>
	 * Description: 跳转到积分商品兑换管理页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toOrderListPage")
	public String toOrderListPage(HttpServletRequest request){
		return "integralCommodityManage/orderList";
	}
	
	/**
	 * Method name: toChooseCategory <BR>
	 * Description: 选择分类页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toChooseCategory")
	public String toChooseCategory(HttpServletRequest request){
		return "integralCommodityManage/chooseCategory";
	}
	
	/**
	 * Method name: toIntegralCommodityEditPage <BR>
	 * Description: 跳转到积分商品编辑页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCommodityEditPage")
	public String toCommodityEditPage(HttpServletRequest request,Integer id){
		try{
			if(id!=null){
				IntegralCommodityVo bean =integralCommodityManageService.getCommodityDetailById(id);
				request.setAttribute("commodity", JsonUtil.getJson4JavaObject(bean));
			}else{
				request.setAttribute("commodity", JsonUtil.getJson4JavaObject(null));
			}
		}catch(Exception e){
			log.warn("toCommodityEditPage failed", e);
		}
		
		return "integralCommodityManage/commodityEdit";
	}
	
	
	/**
	 * Method name: toCommodityDetailPage <BR>
	 * Description: 跳转商品详细页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCommodityDetailPage")
	public String toCommodityDetailPage(HttpServletRequest request, Integer id){
		if(id!=null){
			try {
				IntegralCommodityVo vo = integralCommodityManageService.getCommodityDetailById(id);
				request.setAttribute("commodity", JsonUtil.getJson4JavaObject(vo));
			} catch (Exception e) {
				log.warn("toCommodityDetailPage failed", e);
			}
		}else{
			request.setAttribute("commodity", JsonUtil.getJson4JavaObject(null));
		}
		return "integralCommodityManage/commodityDetail";
	}
	
	
	/**
	 * Method name: toOrderDetailPage <BR>
	 * Description: 跳转商品兑换详细页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toOrderDetailPage")
	public String toOrderDetailPage(HttpServletRequest request, Integer id){
		if(id!=null){
			try {
				IntegralCommodityOrderVo vo = integralCommodityManageService.getCommodityOrderDetailById(id);
				request.setAttribute("order", JsonUtil.getJson4JavaObject(vo));
			} catch (Exception e) {
				log.warn("toOrderDetailPage failed", e);
			}
		}else{
			request.setAttribute("order", JsonUtil.getJson4JavaObject(null));
		}
		return "integralCommodityManage/orderDetail";
	}
	
	
	

	/**category */
	/**
	 * Method name: getById <BR>
	 * Description: 根据id获取商品分类 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  IntegralCommodityCategoryBean<BR>
	 */
	@RequestMapping(value="category/getById")
	@ResponseBody
	public IntegralCommodityCategoryBean getCommodityCategoryById(Integer id) {
		try{
			return integralCommodityManageService.getCommodityCategoryById(id);
		}catch(Exception e){
			log.error("getCommodityCategoryById failed",e);
			return null;
		}
		
	}
	
	
	/**
	 * Method name: checkIntegralCommodityCategoryName <BR>
	 * Description: 检查分类重名 <BR>
	 * Remark: <BR>
	 * @param IntegralCommodityCategoryBean  void<BR>
	 */
	@RequestMapping(value="category/checkName")
	@ResponseBody
	public Object checkIntegralCommodityCategoryName(HttpServletRequest request,IntegralCommodityCategoryBean bean) throws Exception{
        try{
        	Integer count =integralCommodityManageService.checkIntegralCommodityCategoryName(bean);
        	if(count>0){
        		return Constant.AJAX_FAIL;
        	}
        	return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("checkIntegralCommodityCategoryName failed",e);
		}
        return Constant.AJAX_FAIL;
	}
	
	
	/**
	 * Method name: deleteIntegralCommodityCategory <BR>
	 * Description: 删除【商品分类】 <BR>
	 * Remark: <BR>
	 * @param parseInt  void<BR>
	 */
	@RequestMapping(value="category/delete")
	@ResponseBody
	public Object deleteIntegralCommodityCategory(HttpServletRequest request,Integer id) throws Exception{
        try{
        	Integer result =integralCommodityManageService.deleteIntegralCommodityCategory(id);
        	if(result!=null&&result==1){
        		return Constant.AJAX_SUCCESS;
        	}
        	
		}catch(Exception e){
			log.error("deleteIntegralCommodityCategory failed",e);
		}
        return Constant.AJAX_FAIL;
	}

	/**
	 * Method name: updateIntegralCommodityCategory <BR>
	 * Description: 修改【商品分类】 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	@RequestMapping(value="category/update")
	@ResponseBody
	public Object updateIntegralCommodityCategory(HttpServletRequest request,IntegralCommodityCategoryBean record) throws Exception{
		
	    try{
	    	integralCommodityManageService.updateIntegralCommodityCategory(record);
        	return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("updateIntegralCommodityCategory failed",e);
		}
        return Constant.AJAX_FAIL;
	}

	/**
	 * Method name: insertIntegralCommodityCategory <BR>
	 * Description: 添加【商品分类】 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	@RequestMapping(value="category/insert")
	@ResponseBody
	public Object insertIntegralCommodityCategory(HttpServletRequest request, IntegralCommodityCategoryBean record) throws Exception{
		try{
			Integer id =integralCommodityManageService.insertIntegralCommodityCategory(record);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", Constant.AJAX_SUCCESS);
			resultMap.put("id", id);
			return resultMap;
		}catch(Exception e){
			log.error("insertIntegralCommodityCategory failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	
	/**
	 * Method name: getIntegralCommodityCategorys <BR>
	 * Description: 查询商品分类 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return  List<IntegralCommodityCategoryBean><BR>
	 */
	@RequestMapping(value="category/select")
	@ResponseBody
	public Object getIntegralCommodityCategorys(HttpServletRequest request) throws Exception{
		
		try{
//			HttpSession session = request.getSession();
//			String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
//			ManageUserBean user = manageUserService.findUserById(id);
			List<IntegralCommodityCategoryVo> categoryLsit= integralCommodityManageService.getIntegralCommodityCategorys();
			IntegralCommodityCategoryVo bean = new IntegralCommodityCategoryVo();
			
				bean.setName(manageCompanyService.selectCompanyById(1).getName());
			    bean.setType(0);
			categoryLsit.add(bean);
			
			return categoryLsit;
		}catch(Exception e){
			log.error("getIntegralCommodityCategorys failed",e);
		}
        return Constant.AJAX_FAIL;
	}

	
	/**
	 * Method name: getUpCategorys <BR>
	 * Description: 获取上一级的所有商品分类<BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  List<ResCourseCategoryBean><BR>
	 */
	@RequestMapping(value="category/selectUp")
	@ResponseBody
	public List<IntegralCommodityCategoryBean> getUpCategorys(HttpServletRequest request,Integer categoryId) {

		try{
			
			return integralCommodityManageService.getUpCategorys(categoryId);
		}catch(Exception e){
			log.error("getUpCategorys failed",e);
		}
        return null;
	}

	
	
	
	/**IntegralCommodity */
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查积分商品 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityBean<BR>
	 */
	@RequestMapping(value="getById")
	@ResponseBody
	public IntegralCommodityBean getCommodityById(HttpServletRequest request,Integer id) {

		try{
			
			return integralCommodityManageService.getCommodityById(id);
		}catch(Exception e){
			log.error("getCommodityById failed",e);
		}
        return null;
		
	}

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查积分商品 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityBean<BR>
	 */
	@RequestMapping(value="getDetailById")
	@ResponseBody
	public IntegralCommodityVo getCommodityDetailById(HttpServletRequest request,Integer id) {
	try{
			
			return integralCommodityManageService.getCommodityDetailById(id);
		}catch(Exception e){
			log.error("getCommodityById failed",e);
		}
        return null;
	}
	
	
	/**
	 * Method name: checkCommodityCode <BR>
	 * Description: 检查商品编号重复 <BR>
	 * Remark: <BR>
	 * @param IntegralCommodityBean  void<BR>
	 */
	@RequestMapping(value="checkCommodityCode")
	@ResponseBody
	public Object checkCommodityCode(HttpServletRequest request,IntegralCommodityBean bean) throws Exception{
        try{
        	Integer count =integralCommodityManageService.checkIntegralCommodityCode(bean);
        	if(count>0){
        		return Constant.AJAX_FAIL;
        	}
        	return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("checkCommodityCode failed",e);
		}
        return Constant.AJAX_FAIL;
	}
	

	
	/**
	 * Method name: insertIntegralCommodity <BR>
	 * Description: 新增积分商品 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@RequestMapping(value="insert")
	@ResponseBody
	public Object insertIntegralCommodity(HttpServletRequest request,IntegralCommodityBean bean){
		try{
			Integer id =integralCommodityManageService.insertIntegralCommodity(bean);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", Constant.AJAX_SUCCESS);
			resultMap.put("id", id);
			return resultMap;
		}catch(Exception e){
			log.error("insertIntegralCommodity failed",e);
		}
        return Constant.AJAX_FAIL;
	}

	/**
	 * Method name: updateIntegralCommodity <BR>
	 * Description: 修改积分商品 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Object updateIntegralCommodity(HttpServletRequest request, IntegralCommodityBean bean) {
		try{
			integralCommodityManageService.updateIntegralCommodity(bean);
			
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("insertIntegralCommodity failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	/**
	 * Method name: deleteIntegralCommodity <BR>
	 * Description: 删除积分商品 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public Object deleteIntegralCommodity(HttpServletRequest request,String ids) {
		try{
			if(ids!=null&&ids.length()>0){
				String [] idArr = ids.split(",");
				for(String id : idArr){
					integralCommodityManageService.deleteIntegralCommodity(Integer.parseInt(id));
				}
				return Constant.AJAX_SUCCESS;
			}else{
				return Constant.AJAX_FAIL;
			}
			
			
		}catch(Exception e){
			log.error("insertIntegralCommodity failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	
	/**
	 * Method name: putawayById <BR>
	 * Description: 上架积分商品 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@RequestMapping(value="putaway")
	@ResponseBody
	public Object putawayById(HttpServletRequest request,String ids) {
		try{
			if(ids!=null&&ids.length()>0){
				HttpSession session = request.getSession();
				Integer userId = Integer.parseInt((String) session.getAttribute(Constant.SESSION_USERID_LONG));
				String [] idArr = ids.split(",");
				for(String id : idArr){
					integralCommodityManageService.putawayById(Integer.parseInt(id),userId);
				}
				return Constant.AJAX_SUCCESS;
			}
			
		}catch(Exception e){
			log.error("putawayById failed",e);
		}
        return Constant.AJAX_FAIL;
	
	}

	
	/**
	 * Method name: putoutById <BR>
	 * Description: 下架积分商品 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@RequestMapping(value="putout")
	@ResponseBody
	public Object putoutById(HttpServletRequest request,String ids) {
		try{
			if(ids!=null&&ids.length()>0){
				String [] idArr = ids.split(",");
				for(String id : idArr){
					integralCommodityManageService.putoutById(Integer.parseInt(id));
				}
				return Constant.AJAX_SUCCESS;
			}
			
			
		}catch(Exception e){
			log.error("putawayById failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	
	/**
	 * Method name: selectIntegralCommodityList <BR>
	 * Description: 查询积分商品 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Object selectIntegralCommodityList(HttpServletRequest request, IntegralCommodityVo vo){
		MMGridPageVoBean<IntegralCommodityVo> re = new MMGridPageVoBean<IntegralCommodityVo>();
		try{
			vo.setCategoryIds(request.getParameterValues("categorys[]"));
			if(vo.getPageIndex()!=null&&vo.getPageSize()!=null){
				vo.setPageIndex((vo.getPageIndex()-1)*vo.getPageSize());
			}
			List<IntegralCommodityVo>  list= integralCommodityManageService.selectIntegralCommodityList(vo);
			Integer count = integralCommodityManageService.selectIntegralCommodityCount(vo);
			re.setRows(list);
			re.setTotal(count);
			return re;
		}catch(Exception e){
			log.error("selectIntegralCommodityList failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	
	/**
	 * Method name: selectIntegralCommodityCount <BR>
	 * Description: 查询积分商品数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@RequestMapping(value="count")
	@ResponseBody
	public Integer selectIntegralCommodityCount(HttpServletRequest request, IntegralCommodityVo vo) {
         try{
			integralCommodityManageService.selectIntegralCommodityCount(vo);
		}catch(Exception e){
			log.error("selectIntegralCommodityCount failed",e);
		}
		return null;
	}
	
	
	
	
	/** IntegralCommodityOrder */
	

	/**
	 * Method name: getById <BR>
	 * Description: 根据id查积分商品订单 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityOrderBean<BR>
	 */
	@RequestMapping(value="order/getById")
	@ResponseBody
	public IntegralCommodityOrderBean getCommodityOrderById(HttpServletRequest request ,Integer id) {
		 try{
			 return integralCommodityManageService.getCommodityOrderById(id);
			}catch(Exception e){
				log.error("getCommodityOrderById failed",e);
			}
		return null;
	}

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查积分商品 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityOrderVo<BR>
	 */
	@RequestMapping(value="order/getDetail")
	@ResponseBody
	public IntegralCommodityOrderVo getCommodityOrderDetailById(HttpServletRequest request,Integer id) {
		try{
			return integralCommodityManageService.getCommodityOrderDetailById(id);
			}catch(Exception e){
				log.error("getCommodityOrderDetailById failed",e);
			}
		return null;
		
	}

	
	/**
	 * Method name: insertIntegralCommodityOrder <BR>
	 * Description: 新增积分商品订单 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@RequestMapping(value="order/insert")
	@ResponseBody
	public Object insertIntegralCommodityOrder(HttpServletRequest request , IntegralCommodityOrderBean bean) {
		try{
			Integer id =integralCommodityManageService.insertIntegralCommodityOrder(bean);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", Constant.AJAX_SUCCESS);
			resultMap.put("id", id);
			return resultMap;
		}catch(Exception e){
			log.error("insertIntegralCommodityOrder failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	/**
	 * Method name: updateIntegralCommodityOrder <BR>
	 * Description: 修改积分商品订单 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@RequestMapping(value="order/update")
	@ResponseBody
	public Object updateIntegralCommodityOrder(HttpServletRequest request , IntegralCommodityOrderBean bean) {
		try{
			integralCommodityManageService.updateIntegralCommodityOrder(bean);
			
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("updateIntegralCommodityOrder failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	/**
	 * Method name: deleteIntegralCommodityOrder <BR>
	 * Description: 删除积分商品订单 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@RequestMapping(value="order/delete")
	@ResponseBody
	public Object deleteIntegralCommodityOrder(HttpServletRequest request ,Integer id) {
		try{
			integralCommodityManageService.deleteIntegralCommodityOrder(id);
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("deleteIntegralCommodityOrder failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	
	/**
	 * Method name: postById <BR>
	 * Description:  发货<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@RequestMapping(value="order/post")
	@ResponseBody
	public Object postById(HttpServletRequest request ,IntegralCommodityOrderBean bean){
		try{
			HttpSession session = request.getSession();
			Integer userId = Integer.parseInt((String) session.getAttribute(Constant.SESSION_USERID_LONG));
			bean.setLogisticsUserId(userId);
			integralCommodityManageService.post(bean);
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("postById failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	
	
	
	/**
	 * Method name: selectIntegralCommodityOrderList <BR>
	 * Description: 查询积分商品订单 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@RequestMapping(value="order/list")
	@ResponseBody
	public Object selectIntegralCommodityOrderList(HttpServletRequest request , IntegralCommodityOrderVo vo) {
		
		MMGridPageVoBean<IntegralCommodityOrderVo> re = new MMGridPageVoBean<IntegralCommodityOrderVo>();
		try{
			
			if(vo.getPageIndex()!=null&&vo.getPageSize()!=null){
				vo.setPageIndex((vo.getPageIndex()-1)*vo.getPageSize());
			}
			List<IntegralCommodityOrderVo>  list= integralCommodityManageService.selectIntegralCommodityOrderList(vo);
			Integer count = integralCommodityManageService.selectIntegralCommodityOrderCount(vo);
			re.setRows(list);
			re.setTotal(count);
			return re;
		}catch(Exception e){
			log.error("selectIntegralCommodityOrderList failed",e);
		}
        return Constant.AJAX_FAIL;
	}
	
	
	/**
	 * Method name: selectIntegralCommodityOrderCount <BR>
	 * Description: 查询积分商品订单数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@RequestMapping(value="order/count")
	@ResponseBody
	public Integer selectIntegralCommodityOrderCount(HttpServletRequest request , IntegralCommodityOrderVo vo) {
		
           try{
			
			return integralCommodityManageService.selectIntegralCommodityOrderCount(vo);
		}catch(Exception e){
			log.error("selectIntegralCommodityOrderList failed",e);
		}
        return null;
		
	}
	
	
	/** LogisticsCompany 物流公司*/
	
	
	/**
	 * Method name: selectLogisticsCompanyList <BR>
	 * Description: 查询物流公司list <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@RequestMapping(value="company/list")
	@ResponseBody
	public Object selectLogisticsCompanyList(HttpServletRequest request) {
		
	         try{
			
			List<LogisticsCompanyBean>  list= integralCommodityManageService.selectLogisticsCompany();
			
			return list;
		}catch(Exception e){
			log.error("selectLogisticsCompanyList failed",e);
		}
        return null;
	}
	
	
	/**
	 * Method name: getLogisticsCompanyById <BR>
	 * Description: 根据id查物流公司 <BR>
	 * Remark: <BR>
	 * @param id  LogisticsCompanyBean<BR>
	 */
	@RequestMapping(value="company/getById")
	@ResponseBody
	public LogisticsCompanyBean getLogisticsCompanyById(HttpServletRequest request,Integer id) {
		try{
			return integralCommodityManageService.getLogisticsCompanyById(id);
			}catch(Exception e){
				log.error("getLogisticsCompanyById failed",e);
			}
		return null;
		
	}

	
	/**
	 * Method name: insertLogisticsCompany <BR>
	 * Description: 新增物流公司 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@RequestMapping(value="company/insert")
	@ResponseBody
	public Object insertLogisticsCompany(HttpServletRequest request , LogisticsCompanyBean bean) {
		try{
			Integer id =integralCommodityManageService.insertLogisticsCompany(bean);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", Constant.AJAX_SUCCESS);
			resultMap.put("id", id);
			return resultMap;
		}catch(Exception e){
			log.error("insertLogisticsCompany failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	/**
	 * Method name: updateLogisticsCompany <BR>
	 * Description: 修改物流公司 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@RequestMapping(value="company/update")
	@ResponseBody
	public Object updateLogisticsCompany(HttpServletRequest request , LogisticsCompanyBean bean) {
		try{
			integralCommodityManageService.updateLogisticsCompany(bean);
			
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("updateLogisticsCompany failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	/**
	 * Method name: deleteLogisticsCompany <BR>
	 * Description: 删除物流公司 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@RequestMapping(value="company/delete")
	@ResponseBody
	public Object deleteLogisticsCompany(HttpServletRequest request ,Integer id) {
		try{
			integralCommodityManageService.deleteLogisticsCompany(id);
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("deleteLogisticsCompany failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	
	/**zhangbocheng end*/

}
