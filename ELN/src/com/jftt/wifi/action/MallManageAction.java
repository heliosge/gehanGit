package com.jftt.wifi.action;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.MallCourseApproveBean;
import com.jftt.wifi.bean.MallCourseBean;
import com.jftt.wifi.bean.MallCourseCategoryBean;
import com.jftt.wifi.bean.MallSellRecordBean;
import com.jftt.wifi.bean.MallTopicBean;
import com.jftt.wifi.bean.MallTopicCourseBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ResCourseBean;

import com.jftt.wifi.bean.vo.IntegralCommodityOrderVo;
import com.jftt.wifi.bean.vo.MallCourseApproveVo;
import com.jftt.wifi.bean.vo.MallCourseCategoryVo;
import com.jftt.wifi.bean.vo.MallCourseVo;
import com.jftt.wifi.bean.vo.MallOrderVo;
import com.jftt.wifi.bean.vo.MallSellRecordVo;
import com.jftt.wifi.bean.vo.MallTopicVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.MallCourseManageService;
import com.jftt.wifi.service.MallTopicManageService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.ResService;
import com.jftt.wifi.util.ExcelExportUtils;
import com.jftt.wifi.util.JsonUtil;
import org.apache.poi.ss.usermodel.*;

@Controller
@RequestMapping(value="mall/manage")
public class MallManageAction {

    private static final Logger log = Logger.getLogger(MallManageAction.class);
	
	@Autowired
	private MallCourseManageService mallCourseManageService;
	@Autowired
	private MallTopicManageService mallTopicManageService;
	
	@Autowired
	private ManageCompanyService manageCompanyService;
	
	@Autowired
	private ResService resService;
	
	@Autowired
	private ManageUserService manageUserService;
	
/**zhangbocheng start*/
	
	
	/**
	 * Method name: toCourseListPage <BR>
	 * Description: 跳转到课程管理页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCourseListPage")
	public String toCourseListPage(HttpServletRequest request){
		HttpSession session = request.getSession();
		ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
		if(user.getCompanyId()==1){
			return "mallManage/pulianCourseList";
		}
		
		return "mallManage/courseList";
	}
	
	/**
	 * Method name: toApproveListPage <BR>
	 * Description: 跳转到课程审核列表页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toApproveListPage")
	public String toApproveListPage(HttpServletRequest request){
		return "mallManage/approveList";
	}
	
	/**
	 * Method name: toApproveDetailPage <BR>
	 * Description: 跳转到课程审核详情页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toApproveDetailPage")
	public String toApproveDetailPage(HttpServletRequest request,Integer id){
		request.setAttribute("id", id);
		return "mallManage/approveDetail";
	}
	
	/**
	 * Method name: toCompanyCourseListPage <BR>
	 * Description: 跳转到企业课程列表页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCompanyCourseListPage")
	public String toCompanyCourseListPage(HttpServletRequest request){
		return "mallManage/companyCourseList";
	}
	
	/**
	 * Method name: toTopicListPage <BR>
	 * Description: 跳转到专题管理页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toTopicListPage")
	public String toTopicListPage(HttpServletRequest request){
		return "mallManage/topicList";
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
		return "mallManage/chooseCategory";
	}
	/**
	 * Method name: toChooseCouse <BR>
	 * Description: 选择课程页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toChooseCourse")
	public String toChooseCourse(HttpServletRequest request){
		return "mallManage/chooseCourse";
	}
	
	/**
	 * Method name: toChooseMallCourse <BR>
	 * Description: 专题选择课程页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toChooseMallCourse")
	public String toChooseMallCourse(HttpServletRequest request){
		return "mallManage/chooseMallCourse";
	}
	
	/**
	 * Method name: toCourseEditPage <BR>
	 * Description: 跳转到课程编辑页面 <BR>
	 * Remark: <BR>
	 * @param id
     * @param selectCourseId
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCourseEditPage")
	public String toCourseEditPage(HttpServletRequest request,Integer id,Integer selectCourseId){
		try{
			if(id!=null){
				MallCourseVo bean =mallCourseManageService.getCourseDetailById(id);
				HttpSession session = request.getSession();
				ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
				if(user.getCompanyId()!=1&&(bean.getStatus()==1||bean.getStatus()==0)){
					
					Integer oldCourseId = bean.getId();
					Integer copyId = mallCourseManageService.getCopyId(oldCourseId);
					if(copyId==null){
						bean.setIsCopy(1);
						bean.setCopyCourseId(oldCourseId);
						bean.setSelectCourseId(oldCourseId);
						bean.setId(null);	
					}else{
						 bean =mallCourseManageService.getCourseDetailById(copyId);
					}
					
				}
				request.setAttribute("course", JsonUtil.getJson4JavaObject(bean));
			}else if(selectCourseId!=null){
				ResCourseBean bean = resService.selectCourseById(selectCourseId);
				if(bean!=null){
					MallCourseVo vo = new MallCourseVo();
					vo.resCourseToVo(bean);
					vo.setSelectCourseId(selectCourseId);
					request.setAttribute("course", JsonUtil.getJson4JavaObject(vo));
				}
			}
			else{
				request.setAttribute("course", JsonUtil.getJson4JavaObject(null));
			}
		}catch(Exception e){
			log.warn("toCourseEditPage failed", e);
		}
		
		return "mallManage/courseEdit";
	}
	
	/**
	 * Method name: toSectionEditPage <BR>
	 * Description: 课程章节编辑页面 <BR>
	 * Remark: <BR>
     * @param request
     * @param courseId
     * @param sectionCount
     * @param mallCourseId
	 * @return  String<BR>
	 */
	@RequestMapping(value="toSectionEditPage")
	public String toSectionEditPage(HttpServletRequest request,String courseId,Integer sectionCount,Integer mallCourseId){
		request.setAttribute("courseId", courseId);
		request.setAttribute("sectionCount", sectionCount+1);
		request.setAttribute("mallCourseId",mallCourseId);
		return "mallManage/sectionEdit";
	}
	
	/**
	 * Method name: toCourseDetailPage <BR>
	 * Description: 跳转课程详细页面 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCourseDetailPage")
	public String toCourseDetailPage(HttpServletRequest request, Integer id){
		if(id!=null){
			try {
				//MallCourseVo vo =mallCourseManageService.getCourseDetailById(id);
				//request.setAttribute("course", JsonUtil.getJson4JavaObject(vo));
				request.setAttribute("id", id);
			} catch (Exception e) {
				log.warn("toCourseDetailPage failed", e);
			}
		}else{
			request.setAttribute("course", JsonUtil.getJson4JavaObject(null));
		}
		return "mallManage/courseDetail";
	}
	
	/**
	 * Method name: toTopicEditPage <BR>
	 * Description: 跳转专题编辑页面 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toTopicEditPage")
	public String toTopicEditPage(HttpServletRequest request, Integer id){
		if(id!=null){
			try {
				MallTopicVo vo = mallTopicManageService.getTopicDetailById(id);
				request.setAttribute("topic", JsonUtil.getJson4JavaObject(vo));
			} catch (Exception e) {
				log.warn("toTopicEditPage failed", e);
			}
		}else{
			request.setAttribute("topic", JsonUtil.getJson4JavaObject(null));
		}
		return "mallManage/topicEdit";
	}
	
	
	/**
	 * Method name: toTopicDetailPage <BR>
	 * Description: 跳转专题详细页面 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toTopicDetailPage")
	public String toTopicDetailPage(HttpServletRequest request, Integer id){
		if(id!=null){
			try {
				//MallTopicVo vo = mallTopicManageService.getTopicDetailById(id);
				//request.setAttribute("topic", JsonUtil.getJson4JavaObject(vo));
				request.setAttribute("id", id);
			} catch (Exception e) {
				log.warn("toTopicDetailPage failed", e);
			}
		}else{
			request.setAttribute("topic", JsonUtil.getJson4JavaObject(null));
		}
		return "mallManage/topicDetail";
	}
	
	/**
	 * Method name: toCourseOrderListPage <BR>
	 * Description: 跳转到课程订单管理页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCourseOrderListPage")
	public String toCourseOrderListPage(HttpServletRequest request){
		return "mallManage/courseOrderList";
	}
	/**
	 * Method name: toTopicOrderListPage <BR>
	 * Description: 跳转到专题订单管理页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toTopicOrderListPage")
	public String toTopicOrderListPage(HttpServletRequest request){
		return "mallManage/topicOrderList";
	}
	
	/**
	 * Method name: toOrderDetailPage <BR>
	 * Description: 跳转到订单详情页面 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toOrderDetailPage")
	public String toOrderDetailPage(HttpServletRequest request,Integer id ){
		if(id!=null){
			try {
				MallOrderVo vo = mallCourseManageService.getOrderDetailById(id);
				request.setAttribute("order", JsonUtil.getJson4JavaObject(vo));
			} catch (Exception e) {
				log.warn("toOrderDetailPage failed", e);
			}
		}else{
			request.setAttribute("order", JsonUtil.getJson4JavaObject(null));
		}
		return "mallManage/orderDetail";
	}
	
	
	/**
	 * Method name: toSellRecordListPage <BR>
	 * Description: 跳转到课程销售列表页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toSellRecordListPage")
	public String toSellRecordListPage(HttpServletRequest request){
		HttpSession session = request.getSession();
		ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
		if(user.getCompanyId()==1){
			return "mallManage/pulianSellRecordList";
		}
		
		return "mallManage/sellRecordList";
	}
	
	/**
	 * Method name: toCompanySellRecordListPage <BR>
	 * Description: 跳转到课程销售列表页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCompanySellRecordListPage")
	public String toCompanySellRecordListPage(HttpServletRequest request){
		return "mallManage/companySellRecordList";
	}
	

	/**category */
	/**
	 * Method name: getById <BR>
	 * Description: 根据id获取分类 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  MallCourseCategoryBean<BR>
	 */
	@RequestMapping(value="category/getById")
	@ResponseBody
	public MallCourseCategoryBean getMallCourseCategoryById(Integer id) {
		try{
			return mallCourseManageService.getCategoryById(id);
		}catch(Exception e){
			log.error("getMallCourseCategoryById failed",e);
			return null;
		}
		
	}
	
	
	/**
	 * Method name: checkCategoryName <BR>
	 * Description: 检查分类重名 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@RequestMapping(value="category/checkName")
	@ResponseBody
	public Object checkCategoryName(HttpServletRequest request,MallCourseCategoryBean bean) throws Exception{
        try{
        	Integer count =mallCourseManageService.checkCategoryName(bean);
        	if(count>0){
        		return Constant.AJAX_FAIL;
        	}
        	return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("checkCategoryName failed",e);
		}
        return Constant.AJAX_FAIL;
	}
	
	
	/**
	 * Method name: deleteCategory <BR>
	 * Description: 删除分类 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@RequestMapping(value="category/delete")
	@ResponseBody
	public Object deleteCategory(HttpServletRequest request,Integer id) throws Exception{
        
		try{
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
            if(user==null||user.getCompanyId()!=1||user.getSubCompanyId()!=1){
            	log.info("deleteCategory 失败，非普联管理员");
            	return Constant.AJAX_FAIL;
            }
        	Integer result =mallCourseManageService.deleteMallCourseCategory(id);
          if(result!=null&&result==1){
        	  return Constant.AJAX_SUCCESS;
        	 }
        	return Constant.AJAX_FAIL;
		}catch(Exception e){
			log.error("deleteCategory failed",e);
		}
        return Constant.AJAX_FAIL;
	}

	/**
	 * Method name: updateCategory <BR>
	 * Description: 修改分类 <BR>
	 * Remark: <BR>
	 * @param record  <BR>
	 */
	@RequestMapping(value="category/update")
	@ResponseBody
	public Object updateCategory(HttpServletRequest request,MallCourseCategoryBean record) throws Exception{
		
	    try{
	    	HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
	        if(user==null||user.getCompanyId()!=1||user.getSubCompanyId()!=1){
	        	log.info("updateCategory 失败，非普联管理员");
	        	return Constant.AJAX_FAIL;
	        }
	    	record.setUpdateUserId(Integer.parseInt(user.getId()));
	    	mallCourseManageService.updateMallCourseCategory(record);
        	return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("updateCategory failed",e);
		}
        return Constant.AJAX_FAIL;
	}
	
	

	/**
	 * Method name: insertCategory <BR>
	 * Description: 添加分类 <BR>
	 * Remark: <BR>
	 * @param record  <BR>
	 */
	@RequestMapping(value="category/insert")
	@ResponseBody
	public Object insertCategory(HttpServletRequest request, MallCourseCategoryBean record) throws Exception{
		try{
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
            if(user==null||user.getCompanyId()!=1||user.getSubCompanyId()!=1){
            	log.info("insertCategory 失败，非普联管理员");
            	return Constant.AJAX_FAIL;
            }
			record.setCreateUserId(Integer.parseInt(user.getId()));
			Integer id =mallCourseManageService.insertMallCourseCategory(record);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", Constant.AJAX_SUCCESS);
			resultMap.put("id", id);
			return resultMap;
		}catch(Exception e){
			log.error("insertCategory failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	
	/**
	 * Method name: moveCategory <BR>
	 * Description: 移动分类 <BR>
	 * Remark: <BR>
     * @param request
     * @param id
     * @param flag
     * @return
     * @throws Exception
	 */
	@RequestMapping(value="category/move")
	@ResponseBody
	public Object moveCategory(HttpServletRequest request,Integer id,Integer flag) throws Exception{
        
		try{
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
            if(user==null||user.getCompanyId()!=1||user.getSubCompanyId()!=1){
            	log.info("moveCategory 失败，非普联管理员");
            	return Constant.AJAX_FAIL;
            }
        	Integer result =mallCourseManageService.moveMallCourseCategory(id,flag);
          if(result!=null&&result==1){
        	  return Constant.AJAX_SUCCESS;
        	 }
        	return Constant.AJAX_FAIL;
		}catch(Exception e){
			log.error("deleteCategory failed",e);
		}
        return Constant.AJAX_FAIL;
	}

	
	/**
	 * Method name: getCategorys <BR>
	 * Description: 查询分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="category/select")
	@ResponseBody
	public Object getCategorys(HttpServletRequest request) throws Exception{
		
		try{
//			HttpSession session = request.getSession();
//			String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
//			ManageUserBean user = manageUserService.findUserById(id);
			List<MallCourseCategoryVo> categoryList = mallCourseManageService.getMallCourseCategorys();
			MallCourseCategoryVo bean = new MallCourseCategoryVo();
			
				bean.setName(manageCompanyService.selectCompanyById(1).getName());
			    bean.setType(0);
			    categoryList.add(bean);
			
			return categoryList;
		}catch(Exception e){
			log.error("getCategorys failed",e);
		}
        return Constant.AJAX_FAIL;
	}

	
	/**
	 * Method name: getUpCategorys <BR>
	 * Description: 获取上一级的所有分类<BR>
	 * Remark: <BR>
     * @param request
     * @param categoryId
	 * @return    List<ResCourseCategoryBean>
	 */
	@RequestMapping(value="category/selectUp")
	@ResponseBody
	public List<MallCourseCategoryBean> getUpCategorys(HttpServletRequest request,Integer categoryId) {

		try{
			
			return mallCourseManageService.getUpCategorys(categoryId);
		}catch(Exception e){
			log.error("getUpCategorys failed",e);
		}
        return null;
	}

	
	
	
	/**course 课程 */
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查课程 <BR>
	 * Remark: <BR>
	 * @param id  <BR>
     * @return      MallCourseBean
	 */
	@RequestMapping(value="course/getById")
	@ResponseBody
	public MallCourseBean getCourseById(HttpServletRequest request,Integer id) {

		try{
			
			return mallCourseManageService.getCourseById(id);
		}catch(Exception e){
			log.error("getCourseById failed",e);
		}
        return null;
		
	}

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查课程 <BR>
	 * Remark: <BR>
	 * @param id  <BR>
     *  @return     MallCourseVo
	 */
	@RequestMapping(value="course/getDetailById")
	@ResponseBody
	public MallCourseVo getCourseDetailById(HttpServletRequest request,Integer id) {
	try{
			
			return mallCourseManageService.getCourseDetailById(id);
		}catch(Exception e){
			log.error("getCourseDetailById failed",e);
		}
        return null;
	}
	
	
	/**
	 * Method name: checkCourseCode <BR>
	 * Description: 检查课程编号重复 <BR>
	 * Remark: <BR>
	 * @param bean  <BR>
	 */
	@RequestMapping(value="course/checkCode")
	@ResponseBody
	public Object checkCourseCode(HttpServletRequest request,MallCourseVo bean) throws Exception{
        try{
        	Integer count =mallCourseManageService.checkCourseCode(bean);
        	if(count>0){
        		return Constant.AJAX_FAIL;
        	}
        	return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("checkCourseCode failed",e);
		}
        return Constant.AJAX_FAIL;
	}
	

	
	/**
	 * Method name: insertCourse <BR>
	 * Description: 新增课程 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="course/insert")
	@ResponseBody
	public Object insertCourse(HttpServletRequest request,MallCourseVo vo){
		try{
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			vo.setCompanyId(user.getCompanyId());
			vo.setSubCompanyId(user.getSubCompanyId());
			vo.setCreateUserId(Integer.parseInt(user.getId()));
			vo.setStatus(0);
			if(user.getCompanyId()!=1){
			 vo.setStatus(2);
			}
			if(vo.getIsCopy()==null){
				vo.setIsCopy(0);
			}
			vo =mallCourseManageService.insertMallCourse(vo);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", Constant.AJAX_SUCCESS);
			resultMap.put("id", vo.getId());
			resultMap.put("courseId", vo.getCourseId());
			return resultMap;
		}catch(Exception e){
			log.error("insertCourse failed",e);
		}
        return Constant.AJAX_FAIL;
	}

	/**
	 * Method name: updateCourse <BR>
	 * Description: 修改课程 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="course/update")
	@ResponseBody
	public Object updateCourse(HttpServletRequest request, MallCourseVo vo) {
		try{
			ResCourseBean resBean =new ResCourseBean();
			vo.getResCourseBean(resBean);
			resService.updateCourse(resBean);
			MallCourseBean bean =new MallCourseBean ();
			vo.getMallCourseBean(bean);
			mallCourseManageService.updateMallCourse(bean);
			
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("updateCourse failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	/**
	 * Method name: deleteCourse <BR>
	 * Description: 删除课程 <BR>
	 * Remark: <BR>
	 * @param ids  <BR>
	 */
	@RequestMapping(value="course/delete")
	@ResponseBody
	public Object deleteCourse(HttpServletRequest request,String ids) {
		try{
			if(ids!=null&&ids.length()>0){
				HttpSession session = request.getSession();
				ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
				
				String [] idArr = ids.split(",");
				int errorCount=0;
				for(String id : idArr){
					boolean result =mallCourseManageService.checkCourseIsOn(Integer.parseInt(id));
					if(!result){
						mallCourseManageService.deleteMallCourse(Integer.parseInt(id));
					}else{
						errorCount++;
					}
				}
				return errorCount;
			}else{
				return Constant.AJAX_FAIL;
			}
			
			
		}catch(Exception e){
			log.error("deleteCourse failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	
	/**
	 * Method name: putawayCourse <BR>
	 * Description: 上架课程 <BR>
	 * Remark: <BR>
	 * @param ids  <BR>
	 */
	@RequestMapping(value="course/putaway")
	@ResponseBody
	public Object putawayCourse(HttpServletRequest request,String ids) {
		try{
			if(ids!=null&&ids.length()>0){
				HttpSession session = request.getSession();
				ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
				if(user.getCompanyId()!=1){
					//在课程审核表中插入一条记录
					String [] idArr = ids.split(",");
					for(String id : idArr){
						MallCourseBean mallCourse = mallCourseManageService.getCourseById(Integer.parseInt(id));
						if(mallCourse!=null){
							List sectionList =resService.selectSectionByCourseId(mallCourse.getCourseId().toString());
							if(sectionList==null||sectionList.size()<=0){
								continue;
							}
							MallCourseApproveBean approveBean = new MallCourseApproveBean();
							approveBean.setCourseId(Integer.parseInt(id));
							approveBean.setCreateUserId(Integer.parseInt(user.getId()));
							if(mallCourseManageService.checkMallCourseApprove(approveBean)){
								mallCourseManageService.insertMallCourseApprove(approveBean);
								mallCourseManageService.setCourseStatus(Integer.parseInt(id),3);
							}
							
						}
						continue;
					}
					
				}else{
					Integer userId = Integer.parseInt(user.getId());
					String [] idArr = ids.split(",");
					for(String id : idArr){
						MallCourseBean mallCourse = mallCourseManageService.getCourseById(Integer.parseInt(id));
						if(mallCourse!=null){
							List sectionList =resService.selectSectionByCourseId(mallCourse.getCourseId().toString());
							if(sectionList==null||sectionList.size()<=0){
								continue;
							}
						mallCourseManageService.putawayById(Integer.parseInt(id), userId);
						}
						continue;
					}
				}
				
				return Constant.AJAX_SUCCESS;
			}
			
		}catch(Exception e){
			log.error("putawayCourse failed",e);
		}
        return Constant.AJAX_FAIL;
	
	}

	
	/**
	 * Method name: putoutCourse <BR>
	 * Description: 下架课程 <BR>
	 * Remark: <BR>
	 * @param ids  <BR>
	 */
	@RequestMapping(value="course/putout")
	@ResponseBody
	public Object putoutCourse(HttpServletRequest request,String ids) {
		try{
			
			if(ids!=null&&ids.length()>0){
				HttpSession session = request.getSession();
				ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
				if(user.getCompanyId()!=1){
					return Constant.AJAX_FAIL;
				}
				String [] idArr = ids.split(",");
				int errorCount =0;
				for(String id : idArr){
					boolean subResult=mallCourseManageService.putoutById(Integer.parseInt(id));
				    if(!subResult){
				    	errorCount++;
				    }
				}
				return errorCount;
			}
			
			
		}catch(Exception e){
			log.error("putoutCourse failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	
	/**
	 * Method name: selectCourseList <BR>
	 * Description: 查询课程 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="course/list")
	@ResponseBody
	public Object selectCourseList(HttpServletRequest request, MallCourseVo vo){
		MMGridPageVoBean<MallCourseVo> re = new MMGridPageVoBean<MallCourseVo>();
		try{
			if(vo.getIsAll()==null||vo.getIsAll()==0){
				HttpSession session = request.getSession();
				ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
				vo.setCompanyId(user.getCompanyId());
				vo.setSubCompanyId(user.getSubCompanyId());
			}
			
			vo.setCategoryIds(request.getParameterValues("categorys[]"));
			if(vo.getPageIndex()!=null&&vo.getPageSize()!=null){
				vo.setPageIndex((vo.getPageIndex()-1)*vo.getPageSize());
			}
			List<MallCourseVo>  list= mallCourseManageService.selectMallCourseList(vo);
			Integer count = mallCourseManageService.selectMallCourseCount(vo);
			re.setRows(list);
			re.setTotal(count);
			return re;
		}catch(Exception e){
			log.error("selectCourseList failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	
	/**
	 * Method name: selectCourseCount <BR>
	 * Description: 查询课程数目 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="course/count")
	@ResponseBody
	public Integer selectCourseCount(HttpServletRequest request, MallCourseVo vo) {
         try{
        	 HttpSession session = request.getSession();
 			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
 			vo.setCompanyId(user.getCompanyId());
 			vo.setSubCompanyId(user.getSubCompanyId());
        	 vo.setCategoryIds(request.getParameterValues("categorys[]"));
			return mallCourseManageService.selectMallCourseCount(vo);
		}catch(Exception e){
			log.error("selectCourseCount failed",e);
		}
		return null;
	}
	

	/**
	 * Method name: selectCompanyCourseList <BR>
	 * Description: 查询企业课程 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="company/course/list")
	@ResponseBody
	public Object selectCompanyCourseList(HttpServletRequest request, MallCourseVo vo){
		MMGridPageVoBean<MallCourseVo> re = new MMGridPageVoBean<MallCourseVo>();
		try{
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
		
			vo.setCategoryIds(request.getParameterValues("categorys[]"));
			if(vo.getPageIndex()!=null&&vo.getPageSize()!=null){
				vo.setPageIndex((vo.getPageIndex()-1)*vo.getPageSize());
			}
			List<MallCourseVo>  list= mallCourseManageService.selectCompanyMallCourseList(vo);
			Integer count = mallCourseManageService.selectCompanyMallCourseCount(vo);
			re.setRows(list);
			re.setTotal(count);
			return re;
		}catch(Exception e){
			log.error("selectCompanyCourseList failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	
	/**
	 * Method name: deleteCompanyCourse <BR>
	 * Description: 删除课程 <BR>
	 * Remark: <BR>
	 * @param ids  <BR>
	 */
	@RequestMapping(value="company/course/delete")
	@ResponseBody
	public Object deleteCompanyCourse(HttpServletRequest request,String ids) {
		try{
			if(ids!=null&&ids.length()>0){
				HttpSession session = request.getSession();
				ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
				if(user==null||user.getCompanyId()!=1){
					 return Constant.AJAX_FAIL;
				}
				String [] idArr = ids.split(",");
				int errorCount=0;
				for(String id : idArr){
					boolean result =mallCourseManageService.checkCourseIsOn(Integer.parseInt(id));
					if(!result){
						mallCourseManageService.deleteCompanyCourse(Integer.parseInt(id));
					}else{
						errorCount++;
					}
				}
				return errorCount;
			}else{
				return Constant.AJAX_FAIL;
			}
			
			
		}catch(Exception e){
			log.error("deleteCourse failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	
	/** MallCourseApprove 课程审核 */
	
	
	/**
	 * Method name: deleteMallCourseApprove <BR>
	 * Description: 删除审核 <BR>
	 * Remark: <BR>
	 * @param ids  <BR>
	 */
	@RequestMapping(value="approve/delete")
	@ResponseBody
	public Object deleteMallCourseApprove(HttpServletRequest request,String ids) {
		try{
			if(ids!=null&&ids.length()>0){
				String [] idArr = ids.split(",");
				for(String id : idArr){
					mallCourseManageService.deleteMallCourseApprove(Integer.parseInt(id));
				}
				return Constant.AJAX_SUCCESS;
			}
			
			
		}catch(Exception e){
			log.error("deleteMallCourseApprove failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	

	
	/**
	 * Method name: doCheckApprove <BR>
	 * Description: 审核 <BR>
	 * Remark: <BR>
     * @param request
     * @param ids
     * @param status
     * @param reason
     * @return Object
	 */
	@RequestMapping(value="approve/doCheck")
	@ResponseBody
	public Object doCheckApprove(HttpServletRequest request,String ids,Integer status,String reason) {
		try{
			HttpSession session =  request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
	         if(ids!=null&&ids.length()>0){
				String [] idArr = ids.split(",");
				for(String id : idArr){
					MallCourseApproveBean  bean = new MallCourseApproveBean();
					bean.setId(Integer.parseInt(id));
					bean.setCheckUserId(Integer.parseInt(user.getId()));
					bean.setStatus(status);
					bean.setReason(reason);
					mallCourseManageService.doCheckApprove(bean);
				}
				return Constant.AJAX_SUCCESS;
			}		
			
			
		}catch(Exception e){
			log.error("doCheckApprove failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	/**
	 * Method name: selectApproveDetailList <BR>
	 * Description: 查询课程审核详情list <BR>
	 * Remark: <BR>
	 * @param id  <BR>
	 */
	@RequestMapping(value="approve/getDetailList")
	@ResponseBody
	public Object selectApproveDetailList(HttpServletRequest request, Integer id){
		try{
			List<MallCourseApproveVo>  list= mallCourseManageService.getApproveDetailListById(id);
			
			return list;
		}catch(Exception e){
			log.error("selectApproveDetailList failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	
	/**
	 * Method name: selectCourseApproveList <BR>
	 * Description: 查询课程审核列表 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="approve/list")
	@ResponseBody
	public Object selectCourseApproveList(HttpServletRequest request, MallCourseApproveVo vo){
		MMGridPageVoBean<MallCourseApproveVo> re = new MMGridPageVoBean<MallCourseApproveVo>();
		try{
			vo.setCategoryIds(request.getParameterValues("categorys[]"));
			if(vo.getPageIndex()!=null&&vo.getPageSize()!=null){
				vo.setPageIndex((vo.getPageIndex()-1)*vo.getPageSize());
			}
			List<MallCourseApproveVo>  list= mallCourseManageService.selectMallCourseApproveList(vo);
			Integer count = mallCourseManageService.selectMallCourseApproveCount(vo);
			re.setRows(list);
			re.setTotal(count);
			return re;
		}catch(Exception e){
			log.error("selectCourseApproveList failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	
	/**
	 * Method name: selectCourseApproveCount <BR>
	 * Description: 查询课程审核数目 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="approve/count")
	@ResponseBody
	public Integer selectCourseApproveCount(HttpServletRequest request, MallCourseApproveVo vo) {
         try{
        	 
        	 vo.setCategoryIds(request.getParameterValues("categorys[]"));
			return mallCourseManageService.selectMallCourseApproveCount(vo);
		}catch(Exception e){
			log.error("selectCourseApproveCount failed",e);
		}
		return null;
	}
	
	/** courseOrder 课程订单 */
	
	/**
	 * Method name: selectCourseOrderList <BR>
	 * Description: 查询课程订单 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="course/order/list")
	@ResponseBody
	public Object selectCourseOrderList(HttpServletRequest request, MallOrderVo vo){
		MMGridPageVoBean<MallOrderVo> re = new MMGridPageVoBean<MallOrderVo>();
		try{
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
		
			if(vo.getPageIndex()!=null&&vo.getPageSize()!=null){
				vo.setPageIndex((vo.getPageIndex()-1)*vo.getPageSize());
			}
			List<MallOrderVo>  list= mallCourseManageService.selectCourseOrderSellRecord(vo);
			Integer count = mallCourseManageService.selectCourseOrderSellRecordCount(vo);
			re.setRows(list);
			re.setTotal(count);
			return re;
		}catch(Exception e){
			log.error("selectCourseOrderList failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	
	/**
	 * Method name: selectCourseOrderCount <BR>
	 * Description: 查询课程订单数目 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="course/order/count")
	@ResponseBody
	public Integer selectCourseOrderCount(HttpServletRequest request, MallOrderVo vo) {
         try{
        	 HttpSession session = request.getSession();
 			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
 		
			return mallCourseManageService.selectCourseOrderSellRecordCount(vo);
		}catch(Exception e){
			log.error("selectCourseOrderCount failed",e);
		}
		return null;
	}
	
	/**
	 * Method name: delivery <BR>
	 * Description: 发货 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="order/delivery")
	@ResponseBody
	public Object delivery(HttpServletRequest request,MallOrderVo vo) {
		try{
			HttpSession session =  request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			if(user==null||user.getCompanyId()!=1){
				return Constant.AJAX_FAIL;
			}
		
			vo.setPostUserId(Integer.parseInt(user.getId()));
			vo.setPostUserName(user.getName());
			mallCourseManageService.delivery(vo);
			return Constant.AJAX_SUCCESS;
			
		}catch(Exception e){
			log.error("delivery failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	
	/**
	 * Method name: postInvoice <BR>
	 * Description: 邮寄发票 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="order/postInvoice")
	@ResponseBody
	public Object postInvoice(HttpServletRequest request,MallOrderVo vo) {
		try{
			HttpSession session =  request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			if(user==null||user.getCompanyId()!=1){
				return Constant.AJAX_FAIL;
			}
			
			vo.setLogisticsUserId(Integer.parseInt(user.getId()));
			log.debug(vo);
			mallCourseManageService.postInvoice(vo);
			return Constant.AJAX_SUCCESS;
					
			
		}catch(Exception e){
			log.error("delivery failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	
	/**
	 * Method name: doInvalid <BR>
	 * Description: 失效 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@RequestMapping(value="order/doInvalid")
	@ResponseBody
	public Object doInvalid(HttpServletRequest request,Integer id) {
		try{
			HttpSession session =  request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			if(user==null||user.getCompanyId()!=1){
				return Constant.AJAX_FAIL;
			}
			mallCourseManageService.doInvalid(id);
			return Constant.AJAX_SUCCESS;
					
			
		}catch(Exception e){
			log.error("delivery failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	/**
	 * Method name: deleteMallOrder <BR>
	 * Description: 删除订单 <BR>
	 * Remark: <BR>
	 * @param ids  <BR>
	 */
	@RequestMapping(value="order/delete")
	@ResponseBody
	public Object deleteMallOrder(HttpServletRequest request,String ids) {
		try{
			if(ids!=null&&ids.length()>0){
				String [] idArr = ids.split(",");
				for(String id : idArr){
					
					//TODO
					//mallCourseManageService.deleteMallOrder(Integer.parseInt(id));
				}
				return Constant.AJAX_SUCCESS;
			}
			
			
		}catch(Exception e){
			log.error("deleteMallOrder failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	
	/**
	 * Method name: getRelateList <BR>
	 * Description: 查询订单内课程或专题 <BR>
	 * Remark: <BR>
     * @param request
     * @param id
     * @param commodityType
     * @return    Object
	 */
	@RequestMapping(value="order/getRelateList")
	@ResponseBody
	public Object getRelateList(HttpServletRequest request, Integer id,Integer commodityType){
		try{
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
		    if(commodityType==1){
		    	List<MallCourseVo>  list= mallCourseManageService.selectCourseListByOrderId(id);
		    	return list;
		    }else if(commodityType==2){
		    	List<MallTopicVo>  list= mallTopicManageService.selectTopicListByOrderId(id);
		    	if(list!=null&&list.size()>0){
		    		for(MallTopicVo topic:list){
		    			List<MallCourseVo>  courseList = mallTopicManageService.selectCourseListByTopicId(topic.getId());
		    			topic.setCourseList(courseList);
		    		}
		    	}
		    	return list;
		    }
		    return null;
			
		}catch(Exception e){
			log.error("getRelateList failed",e);
		}
        return null;
		
	}
	
	
	
	
   /**topic 专题 */
	
	/**
	 * Method name: getTopicById <BR>
	 * Description: 根据id查专题 <BR>
	 * Remark: <BR>
	 * @param id  <BR>
     * @return MallTopicBean
	 */
	@RequestMapping(value="topic/getById")
	@ResponseBody
	public MallTopicBean getTopicById(HttpServletRequest request,Integer id) {

		try{
			
			return mallTopicManageService.getTopicById(id);
		}catch(Exception e){
			log.error("getTopicById failed",e);
		}
        return null;
		
	}

	
	/**
	 * Method name: getTopicDetailById <BR>
	 * Description: 根据id查专题 <BR>
	 * Remark: <BR>
	 * @param id  <BR>
     * @return MallTopicVo
	 */
	@RequestMapping(value="topic/getDetailById")
	@ResponseBody
	public MallTopicVo getTopicDetailById(HttpServletRequest request,Integer id) {
	try{
			
			return mallTopicManageService.getTopicDetailById(id);
		}catch(Exception e){
			log.error("getTopicDetailById failed",e);
		}
        return null;
	}
	
	
	/**
	 * Method name: checkTopicCode <BR>
	 * Description: 检查专题编号重复 <BR>
	 * Remark: <BR>
	 * @param bean  <BR>
	 */
	@RequestMapping(value="topic/checkCode")
	@ResponseBody
	public Object checkTopicCode(HttpServletRequest request,MallTopicBean bean) throws Exception{
        try{
        	Integer count =mallTopicManageService.checkTopicCode(bean);
        	if(count>0){
        		return Constant.AJAX_FAIL;
        	}
        	return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("checkTopicCode failed",e);
		}
        return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: selectCourseList <BR>
	 * Description: 查询课程 <BR>
	 * Remark: <BR>
	 * @param topicId  <BR>
	 */
	@RequestMapping(value="/topic/courseList")
	@ResponseBody
	public Object selectCourseListByTopicId(HttpServletRequest request, Integer topicId){
		
		try{
		
			List<MallCourseVo>  list= mallTopicManageService.selectCourseListByTopicId(topicId);
		
			
			return list;
		}catch(Exception e){
			log.error("selectCourseListByTopicId failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	
	/**
	 * Method name: insertTopic <BR>
	 * Description: 新增专题 <BR>
	 * Remark: <BR>
	 * @param bean  <BR>
	 */
	@RequestMapping(value="topic/insert")
	@ResponseBody
	public Object insertTopic(HttpServletRequest request,MallTopicBean bean){
		try{
			 HttpSession session = request.getSession();
	 		ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
	 		bean.setCreateUserId(Integer.parseInt(user.getId()));
	 		Integer count =mallTopicManageService.checkTopicCode(bean);
	 		if(count>0){
	 			 return Constant.AJAX_FAIL;
	 		}
			mallTopicManageService.insertMallTopic(bean);
			String[] courseIds = request.getParameterValues("courseIds[]");
			//新增专题和课程关系
			if(courseIds != null){
				int i = 0;
				for(String courseId : courseIds){
					MallTopicCourseBean courseBean = new MallTopicCourseBean();
					courseBean.setMallCourseId(Integer.parseInt(courseId));
					courseBean.setTopicId(bean.getId());
					mallTopicManageService.insertMallTopicCourse(courseBean);
					i++;
				}
			}
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", Constant.AJAX_SUCCESS);
			resultMap.put("id", bean.getId());
			return resultMap;
		}catch(Exception e){
			log.error("insertTopic failed",e);
		}
        return Constant.AJAX_FAIL;
	}

	/**
	 * Method name: updateTopic <BR>
	 * Description: 修改专题 <BR>
	 * Remark: <BR>
	 * @param bean  <BR>
	 */
	@RequestMapping(value="topic/update")
	@ResponseBody
	public Object updateTopic(HttpServletRequest request, MallTopicBean bean) {
		try{
			 HttpSession session = request.getSession();
		 	ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
		 	bean.setUpdateUserId(Integer.parseInt(user.getId()));
			mallTopicManageService.updateMallTopic(bean);
			String[] courseIds = request.getParameterValues("courseIds[]");
			MallTopicCourseBean paramBean = new MallTopicCourseBean();
			paramBean.setTopicId(bean.getId());
			mallTopicManageService.deleteMallTopicCourse(paramBean);
			//新增专题和课程关系
			if(courseIds != null){
				for(String courseId : courseIds){
					MallTopicCourseBean courseBean = new MallTopicCourseBean();
					courseBean.setMallCourseId(Integer.parseInt(courseId));
					courseBean.setTopicId(bean.getId());
					mallTopicManageService.insertMallTopicCourse(courseBean);
				}
			}
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			log.error("updateTopic failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	/**
	 * Method name: deleteTopic <BR>
	 * Description: 删除专题 <BR>
	 * Remark: <BR>
	 * @param ids  <BR>
	 */
	@RequestMapping(value="topic/delete")
	@ResponseBody
	public Object deleteTopic(HttpServletRequest request,String ids) {
		try{
			if(ids!=null&&ids.length()>0){
				String [] idArr = ids.split(",");
				int errorCount =0;
				for(String id : idArr){
					boolean result =mallTopicManageService.checkTopicIsOn(Integer.parseInt(id));
					if(!result){

						mallTopicManageService.deleteMallTopic(Integer.parseInt(id));
					}else{
						errorCount++;
					}
				}
				return errorCount;
			}else{
				return Constant.AJAX_FAIL;
			}
			
			
		}catch(Exception e){
			log.error("deleteTopic failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	
	/**
	 * Method name: putawayTopic <BR>
	 * Description: 上架专题 <BR>
	 * Remark: <BR>
	 * @param ids  <BR>
	 */
	@RequestMapping(value="topic/putaway")
	@ResponseBody
	public Object putawayTopic(HttpServletRequest request,String ids) {
		try{
			if(ids!=null&&ids.length()>0){
				HttpSession session = request.getSession();
				Integer userId = Integer.parseInt((String) session.getAttribute(Constant.SESSION_USERID_LONG));
				String [] idArr = ids.split(",");
				for(String id : idArr){
					mallTopicManageService.putawayById(Integer.parseInt(id), userId);
				}
				return Constant.AJAX_SUCCESS;
			}
			
		}catch(Exception e){
			log.error("putawayTopic failed",e);
		}
        return Constant.AJAX_FAIL;
	
	}

	
	/**
	 * Method name: putoutTopic<BR>
	 * Description: 下架专题 <BR>
	 * Remark: <BR>
	 * @param ids  <BR>
	 */
	@RequestMapping(value="topic/putout")
	@ResponseBody
	public Object putoutTopic(HttpServletRequest request,String ids) {
		try{
			if(ids!=null&&ids.length()>0){
				String [] idArr = ids.split(",");
				for(String id : idArr){
					mallTopicManageService.putoutById(Integer.parseInt(id));
				}
				return Constant.AJAX_SUCCESS;
			}
			
			
		}catch(Exception e){
			log.error("putoutTopic failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}

	
	/**
	 * Method name: selectTopicList <BR>
	 * Description: 查询专题 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="topic/list")
	@ResponseBody
	public Object selectTopicList(HttpServletRequest request, MallTopicVo vo){
		MMGridPageVoBean<MallTopicVo> re = new MMGridPageVoBean<MallTopicVo>();
		try{
		
			if(vo.getPageIndex()!=null&&vo.getPageSize()!=null){
				vo.setPageIndex((vo.getPageIndex()-1)*vo.getPageSize());
			}
			List<MallTopicVo>  list= mallTopicManageService.selectMallTopicList(vo);
			Integer count = mallTopicManageService.selectMallTopicCount(vo);
			re.setRows(list);
			re.setTotal(count);
			return re;
		}catch(Exception e){
			log.error("selectTopicList failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	
	/**
	 * Method name: selectTopicCount <BR>
	 * Description: 查询专题数目 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="topic/count")
	@ResponseBody
	public Integer selectTopicCount(HttpServletRequest request, MallTopicVo vo) {
         try{
			return mallTopicManageService.selectMallTopicCount(vo);
		}catch(Exception e){
			log.error("selectTopicCount failed",e);
		}
		return null;
	}
	
	/** 专题订单 */
	/**
	 * Method name: selectTopicOrderList <BR>
	 * Description: 查询专题订单 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="topic/order/list")
	@ResponseBody
	public Object selectTopicOrderList(HttpServletRequest request, MallOrderVo vo){
		MMGridPageVoBean<MallOrderVo> re = new MMGridPageVoBean<MallOrderVo>();
		try{
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
		
			if(vo.getPageIndex()!=null&&vo.getPageSize()!=null){
				vo.setPageIndex((vo.getPageIndex()-1)*vo.getPageSize());
			}
			List<MallOrderVo>  list= mallTopicManageService.selectTopicOrderSellRecord(vo);
			Integer count = mallTopicManageService.selectTopicOrderSellRecordCount(vo);
			re.setRows(list);
			re.setTotal(count);
			return re;
		}catch(Exception e){
			log.error("selectTopicOrderList failed",e);
		}
        return Constant.AJAX_FAIL;
		
	}
	
	/**
	 * Method name: selectTopicOrderCount <BR>
	 * Description: 查询专题订单数目 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 */
	@RequestMapping(value="topic/order/count")
	@ResponseBody
	public Integer selectTopicOrderCount(HttpServletRequest request, MallOrderVo vo) {
         try{
        	 HttpSession session = request.getSession();
 			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
 		
			return mallTopicManageService.selectTopicOrderSellRecordCount(vo);
		}catch(Exception e){
			log.error("selectTopicOrderCount failed",e);
		}
		return null;
	}
	
	
	  /**mallSellRecord 课程出售记录 */
	
		/**
		 * Method name: getSellRecordById <BR>
		 * Description: 根据id查记录 <BR>
		 * Remark: <BR>
		 * @param id  <BR>
         * @return MallSellRecordBean
		 */
		@RequestMapping(value="sellRecord/getById")
		@ResponseBody
		public MallSellRecordBean getSellRecordById(HttpServletRequest request,Integer id) {

			try{
				return mallCourseManageService.getSellRecordById(id);
			}catch(Exception e){
				log.error("getSellRecordById failed",e);
			}
	        return null;
			
		}

		
		/**
		 * Method name: getSellRecordDetailById <BR>
		 * Description: 根据id查出售记录详情 <BR>
		 * Remark: <BR>
		 * @param id  <BR>
         *@return MallSellRecordVo
		 */
		@RequestMapping(value="sellRecord/getDetailById")
		@ResponseBody
		public MallSellRecordVo getSellRecordDetailById(HttpServletRequest request,Integer id) {
		try{
				
				return mallCourseManageService.getSellRecordDetailById(id);
			}catch(Exception e){
				log.error("getSellRecordDetailById failed",e);
			}
	        return null;
		}
		
		
		/**
		 * Method name: checkIsBuy <BR>
		 * Description: 检查是否购买 <BR>
		 * Remark: <BR>
		 * @param courseId   <BR>
		 */
		@RequestMapping(value="sellRecord/checkIsBuy")
		@ResponseBody
		public Object checkIsBuy(HttpServletRequest request,Integer courseId) throws Exception{
	        try{
	        	HttpSession session = request.getSession();
				ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
	        	Integer count =mallCourseManageService.checkIsBuy(courseId,Integer.parseInt(user.getId()));
	        	if(count>0){
	        		return Constant.AJAX_FAIL;
	        	}
	        	return Constant.AJAX_SUCCESS;
			}catch(Exception e){
				log.error("checkIsBuy failed",e);
			}
	        return Constant.AJAX_FAIL;
		}
		
	
		
		/**
		 * Method name: insertSellRecord <BR>
		 * Description: 新增记录 <BR>
		 * Remark: <BR>
		 * @param bean  <BR>
		 */
		@RequestMapping(value="sellRecord/insert")
		@ResponseBody
		public Object insertMallSellRecord(HttpServletRequest request,MallSellRecordBean bean){
			try{
				 HttpSession session = request.getSession();
		 		ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
		        
				Integer id =mallCourseManageService.insertMallSellRecord(bean);
			
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("result", Constant.AJAX_SUCCESS);
				resultMap.put("id", id);
				return resultMap;
			}catch(Exception e){
				log.error("insertMallSellRecord failed",e);
			}
	        return Constant.AJAX_FAIL;
		}

		
		/**
		 * Method name: deleteSellRecord <BR>
		 * Description: 删除记录 <BR>
		 * Remark: <BR>
		 * @param ids  <BR>
		 */
		@RequestMapping(value="sellRecord/delete")
		@ResponseBody
		public Object deleteSellRecord(HttpServletRequest request,String ids) {
			try{
				if(ids!=null&&ids.length()>0){
					String [] idArr = ids.split(",");
					for(String id : idArr){
						mallCourseManageService.deleteMallSellRecord(Integer.parseInt(id));
					}
					return Constant.AJAX_SUCCESS;
				}else{
					return Constant.AJAX_FAIL;
				}
				
				
			}catch(Exception e){
				log.error("deleteSellRecord failed",e);
			}
	        return Constant.AJAX_FAIL;
			
		}

		
		/**
		 * Method name: pay <BR>
		 * Description: 付款 <BR>
		 * Remark: <BR>
		 * @param ids  <BR>
		 */
		@RequestMapping(value="sellRecord/pay")
		@ResponseBody
		public Object pay(HttpServletRequest request,String ids) {
			try{
				if(ids!=null&&ids.length()>0){
					HttpSession session = request.getSession();
					ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
					String [] idArr = ids.split(",");
					for(String id : idArr){
						mallCourseManageService.pay(Integer.parseInt(id), Integer.parseInt(user.getId()),user.getName());
					}
					return Constant.AJAX_SUCCESS;
				}
				
			}catch(Exception e){
				log.error("pay failed",e);
			}
	        return Constant.AJAX_FAIL;
		
		}

		
		/**
		 * Method name: gathering<BR>
		 * Description: 收款 <BR>
		 * Remark: <BR>
		 * @param ids  <BR>
		 */
		@RequestMapping(value="sellRecord/gathering")
		@ResponseBody
		public Object gathering(HttpServletRequest request,String ids) {
			try{
				HttpSession session = request.getSession();
				ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
				if(ids!=null&&ids.length()>0){
					String [] idArr = ids.split(",");
					for(String id : idArr){
						mallCourseManageService.gathering(Integer.parseInt(id), Integer.parseInt(user.getId()),user.getName());
					}
					return Constant.AJAX_SUCCESS;
				}
				
				
			}catch(Exception e){
				log.error("gathering failed",e);
			}
	        return Constant.AJAX_FAIL;
			
		}

		
		/**
		 * Method name: selectSellRecordList <BR>
		 * Description: 普联查询第三方公司出售记录 <BR>
		 * Remark: <BR>
		 * @param vo  <BR>
		 */
		@RequestMapping(value="sellRecord/list")
		@ResponseBody
		public Object selectSellRecordList(HttpServletRequest request, MallSellRecordVo vo){
			MMGridPageVoBean<MallSellRecordVo> re = new MMGridPageVoBean<MallSellRecordVo>();
			try{
			
				if(vo.getPageIndex()!=null&&vo.getPageSize()!=null){
					vo.setPageIndex((vo.getPageIndex()-1)*vo.getPageSize());
				}
				vo.setType(1);
				vo.setCategoryIds(request.getParameterValues("categorys[]"));
				List<MallSellRecordVo>  list= mallCourseManageService.selectMallSellRecordList(vo);
				Integer count = mallCourseManageService.selectMallSellRecordCount(vo);
				re.setRows(list);
				re.setTotal(count);
				Map<String,Object> oterMap = new HashMap<String,Object>();
				BigDecimal totalMoney = mallCourseManageService.getTotalMoney(vo);
				BigDecimal payMoney = mallCourseManageService.getPayMoney(vo);
				oterMap.put("totalMoney", totalMoney);
				oterMap.put("payMoney", payMoney);
				re.setOther(oterMap);
				return re;
			}catch(Exception e){
				log.error("selectSellRecordList failed",e);
			}
	        return Constant.AJAX_FAIL;
			
		}
		
		/**
		 * Method name: selectSellRecordCount <BR>
		 * Description: 查询出售记录数目 <BR>
		 * Remark: <BR>
		 * @param vo  <BR>
		 */
		@RequestMapping(value="sellRecord/count")
		@ResponseBody
		public Integer selectSellRecordCount(HttpServletRequest request, MallSellRecordVo vo) {
	         try{
	        	 vo.setCategoryIds(request.getParameterValues("categorys[]"));
				return mallCourseManageService.selectMallSellRecordCount(vo);
			}catch(Exception e){
				log.error("selectTopicCount failed",e);
			}
			return null;
		}	
		
		
		/**
		 * Method name: selectCompanySellRecordList <BR>
		 * Description: 各公司的出售记录 <BR>
		 * Remark: <BR>
		 * @param vo  <BR>
		 */
		@RequestMapping(value="company/sellRecord/list")
		@ResponseBody
		public Object selectCompanySellRecordList(HttpServletRequest request, MallSellRecordVo vo){
			MMGridPageVoBean<MallSellRecordVo> re = new MMGridPageVoBean<MallSellRecordVo>();
			try{
			
				if(vo.getPageIndex()!=null&&vo.getPageSize()!=null){
					vo.setPageIndex((vo.getPageIndex()-1)*vo.getPageSize());
				}
				vo.setCategoryIds(request.getParameterValues("categorys[]"));
				HttpSession session = request.getSession();
				ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
				vo.setCompanyId(user.getCompanyId());
				vo.setSubCompanyId(user.getSubCompanyId());
				List<MallSellRecordVo>  list= mallCourseManageService.selectMallSellRecordList(vo);
				Integer count = mallCourseManageService.selectMallSellRecordCount(vo);
				re.setRows(list);
				re.setTotal(count);
				Map<String,Object> oterMap = new HashMap<String,Object>();
				BigDecimal totalMoney = mallCourseManageService.getTotalMoney(vo);
				BigDecimal payMoney = mallCourseManageService.getPayMoney(vo);
				oterMap.put("totalMoney", totalMoney);
				oterMap.put("payMoney", payMoney);
				re.setOther(oterMap);
				return re;
			}catch(Exception e){
				log.error("selectCompanySellRecordList failed",e);
			}
	        return Constant.AJAX_FAIL;
			
		}
		
		/**
		 * Method name: getTotalMoney <BR>
		 * Description: 查询总金额 <BR>
		 * Remark: <BR>
		 * @param vo  <BR>
		 */
		@RequestMapping(value="sellRecord/getTotalMoney")
		@ResponseBody
		public Object getTotalMoney(HttpServletRequest request, MallSellRecordVo vo) {
	         try{
				return mallCourseManageService.getTotalMoney(vo);
			}catch(Exception e){
				log.error("getTotalMoney failed",e);
			}
			return null;
		}	
		
		/**
		 * Method name: getPayMoney <BR>
		 * Description: 查询已付款的金额 <BR>
		 * Remark: <BR>
		 * @param vo  <BR>
		 */
		@RequestMapping(value="sellRecord/getPayMoney")
		@ResponseBody
		public Object getPayMoney(HttpServletRequest request, MallSellRecordVo vo) {
	         try{
				return mallCourseManageService.getPayMoney(vo);
			}catch(Exception e){
				log.error("getPayMoney failed",e);
			}
			return null;
		}	
		
		/**
		 * Method name: exportRecord <BR>
		 * Description: 导出销售记录到excel <BR>
		 * Remark: <BR>
         * @param request
         * @param response
         * @param vo
		 */
		@RequestMapping(value = "exportRecord")
		public void exportRecord(HttpServletRequest request,HttpServletResponse response, MallSellRecordVo vo) {
			log.info("导出excel");
			try {

				if(vo.getPageIndex()!=null&&vo.getPageSize()!=null){
					vo.setPageIndex((vo.getPageIndex()-1)*vo.getPageSize());
				}
				vo.setType(1);
				vo.setCategoryIds(request.getParameterValues("categorys[]"));
				List<MallSellRecordVo>  list= mallCourseManageService.selectMallSellRecordList(vo);
				List<String> titleList = new ArrayList<String>();
				List<String> valueList = new ArrayList<String>();
				titleList.add("课程名称");
				titleList.add("订单编号");
				titleList.add("课程价格");
				titleList.add("所属企业");
				titleList.add("购买企业");
				titleList.add("下单时间");
				titleList.add("付款状态");
				
				valueList.add("courseName");
				valueList.add("orderCode");
				valueList.add("price");
				valueList.add("companyName");
				valueList.add("buyCompanyName");
				valueList.add("createTime");
				valueList.add("statusStr");
				String fileName = "销售记录.xlsx";
				fileName = URLEncoder.encode(fileName, "utf-8");
				response.setContentType("application/x");
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename*=UTF-8''%s",
						fileName);
				response.setHeader(headerKey, headerValue);
				Workbook doc = ExcelExportUtils.createObjectExcel(list, titleList, valueList);
				OutputStream out = response.getOutputStream();
				doc.write(out);
				out.close();
			} catch (Exception e) {
				log.warn("Failed to export to excel.", e);
			}
		}
		
		
		/**
		 * Method name: exportCompanyRecord <BR>
		 * Description: 导出销售记录到excel <BR>
		 * Remark: <BR>
         * @param request
         * @param response
         * @param vo
		 */
		@RequestMapping(value = "exportCompanyRecord")
		public void exportCompanyRecord(HttpServletRequest request,HttpServletResponse response, MallSellRecordVo vo) {
			log.info("导出excel");
			try {
				if(vo.getPageIndex()!=null&&vo.getPageSize()!=null){
					vo.setPageIndex((vo.getPageIndex()-1)*vo.getPageSize());
				}
				vo.setCategoryIds(request.getParameterValues("categorys[]"));
				HttpSession session = request.getSession();
				ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
				vo.setCompanyId(user.getCompanyId());
				vo.setSubCompanyId(user.getSubCompanyId());
				List<MallSellRecordVo>  list = new ArrayList<MallSellRecordVo>();
				 list= mallCourseManageService.selectMallSellRecordList(vo);
				List<String> titleList = new ArrayList<String>();
				List<String> valueList = new ArrayList<String>();
				titleList.add("课程名称");
				titleList.add("订单编号");
				titleList.add("课程价格");
				titleList.add("所属企业");
				titleList.add("购买企业");
				titleList.add("下单时间");
				titleList.add("付款状态");
				
				valueList.add("courseName");
				valueList.add("orderCode");
				valueList.add("price");
				valueList.add("companyName");
				valueList.add("buyCompanyName");
				valueList.add("createTime");
				valueList.add("statusStr");
				String fileName = "销售记录.xlsx";
				fileName = URLEncoder.encode(fileName, "utf-8");
				response.setContentType("application/x");
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename*=UTF-8''%s",
						fileName);
				response.setHeader(headerKey, headerValue);
				Workbook doc = ExcelExportUtils.createObjectExcel(list, titleList, valueList);
				OutputStream out = response.getOutputStream();
				doc.write(out);
				out.close();
			} catch (Exception e) {
				log.warn("Failed to export to excel.", e);
			}
		}
		
		
		
		
		/**
		 * Method name: exportCourseOrder <BR>
		 * Description: 导出课程订单到excel <BR>
		 * Remark: <BR>
         * @param request
         * @param response
         * @param vo
		 */
		@RequestMapping(value = "exportCourseOrder")
		public void exportCourseOrder(HttpServletRequest request,HttpServletResponse response, MallOrderVo vo) {
			log.info("导出excel");
			try {
				HttpSession session = request.getSession();
				ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			
				if(vo.getPageIndex()!=null&&vo.getPageSize()!=null){
					vo.setPageIndex((vo.getPageIndex()-1)*vo.getPageSize());
				}
				List<MallOrderVo>  list= mallCourseManageService.selectCourseOrderSellRecord(vo);
			
				List<String> titleList = new ArrayList<String>();
				List<String> valueList = new ArrayList<String>();
				titleList.add("订单编号");
				titleList.add("课程名称");
				titleList.add("订单价格");
				titleList.add("买家");
				titleList.add("下单时间");
				titleList.add("状态");
				titleList.add("发票状态");
				
				valueList.add("code");
				valueList.add("names");
				valueList.add("price");
				valueList.add("userRealName");
				valueList.add("createTime");
				valueList.add("statusStr");
				valueList.add("invoiceStatusStr");
				String fileName = "课程订单记录.xlsx";
				fileName = URLEncoder.encode(fileName, "utf-8");
				response.setContentType("application/x");
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename*=UTF-8''%s",
						fileName);
				response.setHeader(headerKey, headerValue);
				Workbook doc = ExcelExportUtils.createObjectExcel(list, titleList, valueList);
				OutputStream out = response.getOutputStream();
				doc.write(out);
				out.close();
			} catch (Exception e) {
				log.warn("Failed to export to excel.", e);
			}
		}
		
		
		/**
		 * Method name: exportTopicOrder <BR>
		 * Description: 导出专题订单到excel <BR>
		 * Remark: <BR>
         * @param request
         * @param response
         * @param vo
		 */
		@RequestMapping(value = "exportTopicOrder")
		public void exportTopicOrder(HttpServletRequest request,HttpServletResponse response, MallOrderVo vo) {
			log.info("导出excel");
			try {
				HttpSession session = request.getSession();
				ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			
				if(vo.getPageIndex()!=null&&vo.getPageSize()!=null){
					vo.setPageIndex((vo.getPageIndex()-1)*vo.getPageSize());
				}
				List<MallOrderVo>  list= mallTopicManageService.selectTopicOrderSellRecord(vo);
				List<String> titleList = new ArrayList<String>();
				List<String> valueList = new ArrayList<String>();
				titleList.add("订单编号");
				titleList.add("专题名称");
				titleList.add("订单价格");
				titleList.add("买家");
				titleList.add("下单时间");
				titleList.add("状态");
				titleList.add("发票状态");
				valueList.add("code");
				valueList.add("names");
				valueList.add("price");
				valueList.add("userRealName");
				valueList.add("createTime");
				valueList.add("statusStr");
				valueList.add("invoiceStatusStr");
				String fileName = "专题订单记录.xlsx";
				fileName = URLEncoder.encode(fileName, "utf-8");
				response.setContentType("application/x");
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename*=UTF-8''%s",
						fileName);
				response.setHeader(headerKey, headerValue);
				Workbook doc = ExcelExportUtils.createObjectExcel(list, titleList, valueList);
				OutputStream out = response.getOutputStream();
				doc.write(out);
				out.close();
			} catch (Exception e) {
				log.warn("Failed to export to excel.", e);
			}
		}
		
		
		
}
