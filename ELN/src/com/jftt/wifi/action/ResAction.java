/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ResAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月30日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.ApproveRecordBean;
import com.jftt.wifi.bean.CertificateBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageNoticeBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.OamTopicBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.ResCourseCategoryBean;
import com.jftt.wifi.bean.ResCourseTypeBean;
import com.jftt.wifi.bean.ResCoursewareBean;
import com.jftt.wifi.bean.ResSectionBean;
import com.jftt.wifi.bean.ResSectionCoursewareBean;
import com.jftt.wifi.bean.ResSectionExamBean;
import com.jftt.wifi.bean.SectionExamBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.vo.CourseVo;
import com.jftt.wifi.bean.vo.OamTopicVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.common.DataBaseConstant;
import com.jftt.wifi.dao.ExamPaperDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.ApproveManageService;
import com.jftt.wifi.service.CertificateService;
import com.jftt.wifi.service.CourseDetailService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.ResService;
import com.jftt.wifi.util.JsonUtil;

/**
 * class name:ResAction <BR>
 * class description: 资源管理 <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月30日
 * @author JFTT)Lu Yunlong
 */
@Controller
@RequestMapping(value="res")
public class ResAction {
	
	@Resource(name="resService")
	private ResService resService;
	
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	
	@Resource(name="manageCompanyService")
	private ManageCompanyService manageCompanyService;
	
	@Resource(name="manageDepartmentService")
	private ManageDepartmentService manageDepartmentService;
	
	@Resource(name="certificateService")
	private CertificateService certificateService;
	
	@Resource(name="courseDetailService")
	private CourseDetailService courseDetailService;
	
	@Resource(name="approveManageService")
	private ApproveManageService approveManageService;
	
	
	/*课件 Start*/
	
	@RequestMapping(value="toResCoursewareListPage")
	public String toResCoursewareListPage(HttpServletRequest request){
		return "res/coursewareList";
	}
	
	/**
	 * Method name: selectResCoursewareList <BR>
	 * Description: 查询课件列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param type 课件类型
	 * @param name 课件名称
	 * @return  Object<BR>
	 */
	@RequestMapping("selectResCoursewareList")
	@ResponseBody
	public Object selectResCoursewareList(HttpServletRequest request, String type, String name, int page, int pageSize){
		MMGridPageVoBean<ResCoursewareBean> re = new MMGridPageVoBean<ResCoursewareBean>();
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			Map<String, Object> param = new HashMap<String, Object>();
			String[] ids = request.getParameterValues("ids[]");
			param.put("companyId", user.getCompanyId());
			param.put("subCompanyId", user.getSubCompanyId());
			param.put("ids", ids);
			param.put("type", type);
			param.put("name", name);
			param.put("fromNum", (page - 1) * pageSize);
			param.put("pageSize", pageSize);
			List<ResCoursewareBean> result = resService.selectCoursewareList(param);
			int size = resService.selectCoursewareCount(param);
			re.setTotal(size);
			re.setRows(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	/**
	 * Method name: toInsertResCoursewarePage <BR>
	 * Description: 新增课件页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toInsertResCoursewarePage")
	public String toInsertResCoursewarePage(HttpServletRequest request){
		return "res/coursewareAdd";
	}
	
	/**
	 * Method name: insertResCourseware <BR>
	 * Description: 新增课件 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean (ResCoursewareBean)
	 * @return  String<BR>
	 */
	@RequestMapping("insertResCourseware")
	@ResponseBody
	public String insertResCourseware(HttpServletRequest request, ResCoursewareBean bean){
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			bean.setCompanyId(user.getCompanyId());
			bean.setSubCompanyId(user.getSubCompanyId());
			bean.setCreateUserId(Integer.parseInt(user.getId()));
			bean.setCreateUserName(user.getName());
			resService.insertCourseware(bean);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: toUpdateResCoursewarePage <BR>
	 * Description: 跳转到修改课件页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 课件id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toUpdateResCoursewarePage")
	public String toUpdateOamTopicPage(HttpServletRequest request, String id){
		try {
			ResCoursewareBean bean = resService.selectCoursewareById(Integer.parseInt(id));
			if(bean == null){
				request.setAttribute("courseware", "{}");
			}else{
				request.setAttribute("courseware", JsonUtil.getJson4JavaObject(bean));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "res/coursewareUpdate";
	}
	
	/**
	 * Method name: updateResCourseware <BR>
	 * Description: 修改课件 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean (ResCoursewareBean)
	 * @return  String<BR>
	 */
	@RequestMapping("updateResCourseware")
	@ResponseBody
	public String updateResCourseware(HttpServletRequest request, ResCoursewareBean bean){
		try {
			resService.updateCourseware(bean);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: deleteResCourseware <BR>
	 * Description: 删除课件 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping("deleteResCourseware")
	@ResponseBody
	public String deleteResCourseware(HttpServletRequest request){
		try {
			String[] ids = request.getParameterValues("ids[]");
			for(String id : ids){
				resService.deleteCourseware(Integer.parseInt(id));
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: toCoursewareDetail <BR>
	 * Description: 课件详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseWareId
	 * @param courseWareType
	 * @return  String<BR>
	 */
	@RequestMapping("toCoursewareDetail")
	public String toCoursewareDetail(HttpServletRequest request,Integer courseWareId,Integer courseWareType,String isFree){
		request.setAttribute("courseWareId", courseWareId);//课件id
		request.setAttribute("courseWareType", courseWareType);//课件类型
		//课程商城跳转 判断是否免费课程
		request.setAttribute("isFree", isFree);
		
		return "res/coursewareDetail";
	}
	
	/*课件 End*/
	
	/*课程 Start*/
	
	/**
	 * Method name: toResCourseListPage <BR>
	 * Description: 课程页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toResCourseListPage")
	public String toResCourseListPage(HttpServletRequest request){
		ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		if(user.getCompanyId() - user.getSubCompanyId() == 0){
			request.setAttribute("isSub", "2");
		}else{
			request.setAttribute("isSub", "1");
		}
		return "res/courseList";
	}
	
	/**
	 * Method name: toResCloundCourseListPage <BR>
	 * Description: 云平台课程页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toResCloundCourseListPage")
	public String toResCloundCourseListPage(HttpServletRequest request){
		return "res/cloundCourseList";
	}
	
	/**
	 * Method name: toResShareCourseListPage <BR>
	 * Description: 共享 课程页面<BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toResShareCourseListPage")
	public String toResShareCourseListPage(HttpServletRequest request){
		return "res/shareCourseList";
	}
	
	/**
	 * Method name: selectResCourseList <BR>
	 * Description: 查询课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo (CourseVo)
	 * @return  Object<BR>
	 */
	@RequestMapping("selectResCourseList")
	@ResponseBody
	public Object selectResCourseList(HttpServletRequest request, CourseVo vo){
		MMGridPageVoBean<ResCourseBean> re = new MMGridPageVoBean<ResCourseBean>();
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			vo.setCompanyId(user.getCompanyId());
			List<Long> subIds = manageDepartmentService.getChildren(user.getCompanyId(), user.getSubCompanyId());
			StringBuffer sb = new StringBuffer();
			if(user.getSubCompanyId().equals(user.getCompanyId())){
				sb.append(user.getCompanyId()).append(",");
			}
			for(Long subId : subIds){
				sb.append(subId).append(",");
			}
//			System.out.println("子公司id:"+sb);
			vo.setSonCompanyId(sb.substring(0, sb.length()-1));
			vo.setCategoryIds(request.getParameterValues("categoryId[]"));
			vo.setTypeIds(request.getParameterValues("typeId[]"));
			vo.setFromNum((vo.getPage() - 1) * vo.getPageSize());
			List<ResCourseBean> result = resService.selectCourseList(vo);
			//获取公司名称
			for(ResCourseBean course : result){
				if(course.getCompanyId() - course.getSubCompanyId() == 0){
					course.setCompanyName(user.getCompanyName());
				}
			}
			int size = resService.selectCourseListCount(vo);
			re.setRows(result);
			re.setTotal(size);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Method name: selectResCourseList <BR>
	 * Description: 查询【云平台】课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping("selectResCloundCourseList")
	@ResponseBody
	public Object selectResCloundCourseList(HttpServletRequest request, CourseVo vo){
		MMGridPageVoBean<ResCourseBean> re = new MMGridPageVoBean<ResCourseBean>();
		try {
			vo.setFromNum((vo.getPage() - 1) * vo.getPageSize());
			List<ResCourseBean> result = resService.selectCourseList(vo);
			int size = resService.selectCourseListCount(vo);
			re.setRows(result);
			re.setTotal(size);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	/**
	 * Method name: selectShareResCourseList <BR>
	 * Description: 查询共享课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping("selectShareResCourseList")
	@ResponseBody
	public Object selectShareResCourseList(HttpServletRequest request, CourseVo vo){
		MMGridPageVoBean<ResCourseBean> re = new MMGridPageVoBean<ResCourseBean>();
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			vo.setCompanyId(user.getCompanyId());
			if("1".equals(vo.getQueryType())){
				vo.setSubCompanyId(user.getSubCompanyId());
				vo.setShareStatus("2,3,4,5,6,7");
			}else{
				if(Constant.PULIAN_COMPANY_ID-user.getCompanyId()==0){
					vo.setShareStatus("7");
					vo.setCompanyId(null);
				}else{
					vo.setShareStatus("4");
				}
			}
			vo.setCategoryIds(request.getParameterValues("categoryIds[]"));
			vo.setFromNum((vo.getPage() - 1) * vo.getPageSize());
			List<ResCourseBean> result = resService.selectShareResCourseList(vo);
			int size = resService.selectCourseListCount(vo);
			re.setRows(result);
			re.setTotal(size);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Method name: toInsertResCoursePage <BR>
	 * Description: 新增课程页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toInsertResCoursePage")
	public String toInsertResCoursePage(HttpServletRequest request){
		return "res/courseAdd";
	}
	
	/**
	 * Method name: insertResCourse <BR>
	 * Description: 新增课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  String<BR>
	 */
	@RequestMapping("insertResCourse")
	@ResponseBody
	public String insertResCourse(HttpServletRequest request, ResCourseBean bean){
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			bean.setCompanyId(user.getCompanyId());
			bean.setSubCompanyId(user.getSubCompanyId());
			bean.setCreateUserId(Integer.parseInt(user.getId()));
			bean.setMallStatus(1);
			//判断共享状态
			if(Constant.PULIAN_COMPANY_ID - user.getCompanyId() == 0){
				bean.setShareStatus(7);
			}else{
				if(user.getCompanyId().equals(user.getSubCompanyId())){
					bean.setShareStatus(4);
				}else{
					bean.setShareStatus(1);
				}
			}
			Integer id = resService.insertCourse(bean);
			//开放人群
			String[] postIds = request.getParameterValues("postIds[]");
			String[] groupIds = request.getParameterValues("groupIds[]");
			String[] deptIds = request.getParameterValues("deptIds[]");
			if(postIds == null && groupIds == null && deptIds == null){
				//开放全部学员
				resService.insertCourseAndOpenCrowd(id, null, DataBaseConstant.COURSE_OPEN_CROWD_ALL);
			}else{
				//开放岗位
				if(postIds != null){
					resService.insertCourseAndOpenCrowd(id, postIds, DataBaseConstant.COURSE_OPEN_CROWD_POST);
				}
				//开放群组
				if(groupIds != null){
					resService.insertCourseAndOpenCrowd(id, groupIds, DataBaseConstant.COURSE_OPEN_CROWD_GROUP);
				}
				//开放部门
				if(deptIds != null){
					resService.insertCourseAndOpenCrowd(id, deptIds, DataBaseConstant.COURSE_OPEN_CROWD_DEPT);
				}
			}
			return id.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: toUpdateResCoursePage <BR>
	 * Description: 修改课程页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping("toUpdateResCoursePage")
	public String toUpdateResCoursePage(HttpServletRequest request, String id) throws Exception{
		ResCourseBean course = resService.selectCourseById(Integer.parseInt(id));
		course.setPostList(resService.selectCourseOpenPostByCourseId(course.getId()));
		course.setGroupList(resService.selectCourseOpenGroupByCourseId(course.getId()));
		course.setDeptList(resService.selectCourseOpenDeptByCourseId(course.getId()));
		request.setAttribute("course", JsonUtil.getJson4JavaObject(course));
		return "res/courseUpdate";
	}
	
	/**
	 * Method name: updateResCourse <BR>
	 * Description: 修改课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  String<BR>
	 */
	@RequestMapping("updateResCourse")
	@ResponseBody
	public String updateResCourse(HttpServletRequest request, ResCourseBean bean){
		try {
			resService.updateCourse(bean);
			//删除开放人员
			resService.deleteCourseAndOpenCrowd(bean.getId());
			//开放人群
			String[] postIds = request.getParameterValues("postIds[]");
			String[] groupIds = request.getParameterValues("groupIds[]");
			String[] deptIds = request.getParameterValues("deptIds[]");
			if(postIds == null && groupIds == null && deptIds== null){
				//开放全部学员
				resService.insertCourseAndOpenCrowd(bean.getId(), null, DataBaseConstant.COURSE_OPEN_CROWD_ALL);
			}else{
				//开放部门
				if(postIds != null){
					resService.insertCourseAndOpenCrowd(bean.getId(), postIds, DataBaseConstant.COURSE_OPEN_CROWD_POST);
				}
				//开放群组
				if(groupIds != null){
					resService.insertCourseAndOpenCrowd(bean.getId(), groupIds, DataBaseConstant.COURSE_OPEN_CROWD_GROUP);
				}
				//开放部门
				if(deptIds != null){
					resService.insertCourseAndOpenCrowd(bean.getId(), deptIds, DataBaseConstant.COURSE_OPEN_CROWD_DEPT);
				}
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: deleteResCourse <BR>
	 * Description: 删除课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("deleteResCourse")
	@ResponseBody
	public String deleteResCourse(HttpServletRequest request){
		try {
			String[] ids = request.getParameterValues("ids[]");
			for(String id : ids){
				resService.deleteCourse(Integer.parseInt(id));
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: toCourseDetail <BR>
	 * Description: 课程详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId
	 * @return  String<BR>
	 */
	@RequestMapping("toCourseDetail")
	public String toCourseDetail(HttpServletRequest request,Integer courseId){
		try {
			ResCourseBean courseBean = courseDetailService.getCourseById(courseId);
			request.setAttribute("courseBean", JSONObject.fromObject(courseBean));
		} catch (DataBaseException e) {
		}
		return "res/courseDetail";
	}
	
	@RequestMapping("toChangeCategory")
	public String toChangeCategory(){
		return "res/changeCategory";
	}
	
	@RequestMapping("toChangeType")
	public String toChangeType(){
		return "res/changeType";
	}
	
	/**
	 * Method name: toChooseCategory <BR>
	 * Description: 选择课程体系 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("toChooseCategory")
	public String toChooseCategory(){
		return "res/chooseCategory";
	}
	
	/**
	 * Method name: toChoosePost <BR>
	 * Description: 选择岗位 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("toChoosePost")
	public String toChoosePost(){
		return "res/choosePost";
	}
	
	/**
	 * Method name: toChooseGroup <BR>
	 * Description: 选择群组 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("toChooseGroup")
	public String toChooseGroup(){
		return "res/chooseGroup";
	}
	
	/**
	 * Method name: toChooseCourseware <BR>
	 * Description: 选择课件 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("toChooseCourseware")
	public String toChooseCourseware(){
		return "res/chooseCourseware";
	}
	
	/**
	 * Method name: toChooseExam <BR>
	 * Description: 选择考试 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("toChooseExam")
	public String toChooseExam(){
		return "res/chooseExam";
	}
	
	
	/**
	 * Method name: toChooseAfterClassExam <BR>
	 * Description: 选择考试 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("toChooseAfterClassExam")
	public String toChooseAfterClassExam(){
		return "res/chooseAfterClassExam";
	}
	
	/**
	 * Method name: toChooseTeacher <BR>
	 * Description: 选择讲师 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("toChooseTeacher")
	public String toChooseTeacher(){
		return "res/chooseTeacher";
	}
	
	/**
	 * Method name: changeCategory <BR>
	 * Description: 修改课程体系 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param categoryId
	 * @return  String<BR>
	 */
	@RequestMapping("changeCategory")
	@ResponseBody
	public String changeCategory(HttpServletRequest request, String categoryId){
		try {
			String[] ids = request.getParameterValues("ids[]");
			for(String id : ids){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", id);
				map.put("categoryId", categoryId);
				resService.changeCategory(map);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: selectCourseTypeByCourseId <BR>
	 * Description: 根据课程id获取分类id <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId
	 * @return  String<BR>
	 */
	@RequestMapping("selectCourseTypeByCourseId")
	@ResponseBody
	public List<Map<String,Object>> selectCourseTypeByCourseId(HttpServletRequest request, String courseId){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("courseId", courseId);
			return resService.selectCourseTypeByCourseId(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: changeType <BR>
	 * Description: 修改课程分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param categoryId
	 * @return  String<BR>
	 */
	@RequestMapping("changeType")
	@ResponseBody
	public String changeType(HttpServletRequest request){
		try {
			String[] ids = request.getParameterValues("ids[]");
			String[] typeIds = request.getParameterValues("typeIds[]");
 			for(String id : ids){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("courseId", id);
				//删除课程原来分类
				resService.deleteCourseTypeByCourseId(map);
				if(typeIds != null){
					for(String typeId : typeIds){
						map.put("typeId", typeId);
						resService.changeCourseType(map);
					}
				}
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: releaseResCourse <BR>
	 * Description: 发布 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping("releaseResCourse")
	@ResponseBody
	public String releaseResCourse(HttpServletRequest request, String id){
		try {
			resService.releaseCourse(Integer.parseInt(id));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: cancelReleaseResCourse <BR>
	 * Description: 取消发布课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping("cancelReleaseResCourse")
	@ResponseBody
	public String cancelReleaseResCourse(HttpServletRequest request, String id){
		try {
			resService.cancelReleaseCourse(Integer.parseInt(id));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: shareResCourse <BR>
	 * Description: 共享课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping("shareResCourse")
	@ResponseBody
	public String shareResCourse(HttpServletRequest request, String id){
		try {
//			HttpSession session = request.getSession();
//			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
//			//判断该用户是不是集团用户
//			Integer shareStatus = 0;
//			
//			if(user.getCompanyId() == user.getSubCompanyId()){
//				shareStatus = 5;
//			}else{
//				shareStatus = 2;
//				//添加共享流程
//				ResCourseBean course = resService.selectCourseById(Integer.parseInt(id));
//				ApproveRecordBean approveRecordBean = new ApproveRecordBean ();
//				approveRecordBean.setUserId(Integer.parseInt(user.getId()));
//				approveRecordBean.setCompanyId(user.getCompanyId());
//				approveRecordBean.setShareStatus(1);//提交集团共享
//				approveRecordBean.setObjectId(Integer.parseInt(id));
//				approveRecordBean.setObjectUserId(course.getCreateUserId());
//				approveRecordBean.setWayType(1);//课程
//				approveRecordBean.setSubCompanyId(user.getSubCompanyId());
//				approveManageService.shareObj(approveRecordBean);
//			}
//			Map<String, Object> param = new HashMap<String, Object>();
//			param.put("id", id);
//			param.put("shareStatus", shareStatus);
//			resService.shareCourse(param);
			ResCourseBean course = resService.selectCourseById(Integer.parseInt(id));
			approveManageService.startWorkFlow(course);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	
	/*课程 End*/
	
	/*章节 Start*/
	@RequestMapping("toInsertResSectionPage")
	public String toInsertResSectionPage(HttpServletRequest request, String courseId, String sectionCount){
		request.setAttribute("courseId", courseId);
		request.setAttribute("sectionCount", Integer.parseInt(sectionCount)+1);
		return "res/sectionAdd";
	}
	
	@RequestMapping("toUpdateResSectionPage")
	public String toUpdateResSectionPage(HttpServletRequest request, String courseId, String sectionCount){
		request.setAttribute("courseId", courseId);
		request.setAttribute("sectionCount", Integer.parseInt(sectionCount)+1);
		return "res/sectionUpdate";
	}
	
	/**
	 * Method name: selectExamList <BR>
	 * Description: 获取试卷列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param name
	 * @param page
	 * @param pageSize
	 * @return  Object<BR>
	 */
	@RequestMapping("selectExamList")
	@ResponseBody
	public Object selectExamList(HttpServletRequest request, String name, int page, int pageSize){
		MMGridPageVoBean<SectionExamBean> mm = new MMGridPageVoBean<SectionExamBean>();
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			String[] categoryIds = request.getParameterValues("categoryIds[]");
			param.put("categoryIds", categoryIds);
			param.put("name", name);
			param.put("fromNum", (page-1)*pageSize);
			param.put("pageSize", pageSize);
			ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			param.put("subCompanyId", user.getSubCompanyId());
			List<SectionExamBean> rows = resService.selectExamList(param);
			int size = resService.selectExamListCount(param);
			mm.setRows(rows);
			mm.setTotal(size);
			return mm;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("selectSectionByCourseId")
	@ResponseBody
	public Object selectSectionByCourseId(String courseId) throws Exception{
		return resService.selectSectionByCourseId(courseId);
	}
	
	/**
	 * Method name: selectSectionAndCourseware <BR>
	 * Description: 获取章节、课件关系 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param sectionId
	 * @return  Object<BR>
	 */
	@RequestMapping("selectSectionAndCourseware")
	@ResponseBody
	public Object selectSectionAndCourseware(HttpServletRequest request, String sectionId){
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("sectionId", sectionId);
			if(sectionId == null || sectionId.isEmpty()){
				return new ArrayList<ResCoursewareBean>();
			}
			List<ResCoursewareBean> list = resService.selectSectionAndCourseware(param);
			return list;
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Method name: selectSectionAndExam <BR>
	 * Description: 获取章节、试卷关系 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param sectionId
	 * @return  Object<BR>
	 */
	@RequestMapping("selectSectionAndExam")
	@ResponseBody
	public Object selectSectionAndExam(HttpServletRequest request, String sectionId, String examName){
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("sectionId", sectionId);
			if(sectionId  == null || sectionId.isEmpty()){
				return new ArrayList<SectionExamBean>();
			}
			List<SectionExamBean> list = resService.selectSectionAndExam(param);
			return list;
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Method name: insertResSection <BR>
	 * Description: 新增章节 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param section
	 * @return  String<BR>
	 */
	@RequestMapping("insertResSection")
	@ResponseBody
	public String insertResSection(HttpServletRequest request, ResSectionBean section){
		try {
			//新增课件
			Integer sectionId = resService.insertSection(section);
			//新增章节、课件关系
			String[] coursewareIds = request.getParameterValues("coursewareIds[]");
			if(coursewareIds != null){
				for(String coursewareId : coursewareIds){
					ResSectionCoursewareBean voBean = new ResSectionCoursewareBean(sectionId, Integer.parseInt(coursewareId));
					resService.insertSectionCourseware(voBean);
				}
			}
			//新增章节、试卷的关系
			String[] examIds = request.getParameterValues("examIds[]");
			String[] examDurations = request.getParameterValues("examDurations[]");
			String[] examTimesS = request.getParameterValues("examTimesS[]");
			String[] passPercents = request.getParameterValues("passPercents[]");
			if(examIds != null){
				for(int i = 0; i <= examIds.length -1; i++){
					Integer examId = Integer.parseInt(examIds[i]);
					ResSectionExamBean voBean = new ResSectionExamBean(sectionId, examId, Integer.parseInt(examDurations[i]) 
							,Integer.parseInt(examTimesS[i]) , Integer.parseInt(passPercents[i]));
					resService.insertSectionExam(voBean);
				}
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: updateResSection <BR>
	 * Description: 修改章节 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param section
	 * @return  String<BR>
	 */
	@RequestMapping("updateResSection")
	@ResponseBody
	public String updateResSection(HttpServletRequest request, ResSectionBean section){
		try {
			//修改课件
			resService.updateSection(section);
			//删除章节、课件、试卷的关系
			resService.deleteSectionCourseware(new ResSectionCoursewareBean(section.getId(), null));
			resService.deleteSectionExam(new ResSectionExamBean(section.getId()));
			//新增章节、课件关系
			String[] coursewareIds = request.getParameterValues("coursewareIds[]");
			if(coursewareIds != null){
				for(String coursewareId : coursewareIds){
					ResSectionCoursewareBean voBean = new ResSectionCoursewareBean(section.getId(), Integer.parseInt(coursewareId));
					resService.insertSectionCourseware(voBean);
				}
			}
			//新增章节、试卷的关系
			String[] examIds = request.getParameterValues("examIds[]");
			String[] examDurations = request.getParameterValues("examDurations[]");
			String[] examTimesS = request.getParameterValues("examTimesS[]");
			String[] passPercents = request.getParameterValues("passPercents[]");
			if(examIds != null){
				for(int i = 0; i <= examIds.length -1; i++){
					Integer examId = Integer.parseInt(examIds[i]);
					ResSectionExamBean voBean = new ResSectionExamBean(section.getId(), examId, Integer.parseInt(examDurations[i]) 
							,Integer.parseInt(examTimesS[i]) , Integer.parseInt(passPercents[i]));
					resService.insertSectionExam(voBean);
				}
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	
	/**
	 * Method name: deleteResSection <BR>
	 * Description: 删除章节 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param sectionId
	 * @return  String<BR>
	 */
	@RequestMapping("deleteResSection")
	@ResponseBody
	public String deleteResSection(HttpServletRequest request, String sectionId){
		try{
			resService.deleteSection(sectionId);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: deleteResSectionCourseware <BR>
	 * Description: 删除章节试卷 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	/*@RequestMapping("deleteResSectionCourseware")
	@ResponseBody
	public String deleteResSectionCourseware(HttpServletRequest request){
		try{
			String[] ids = request.getParameterValues("ids");
			for(String id : ids){
				resService.deleteSectionCourseware(id);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}*/
	
	/**
	 * Method name: deleteResSectionExam <BR>
	 * Description: 删除课件试卷 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	/*@RequestMapping("deleteResSectionExam")
	@ResponseBody
	public String deleteResSectionExam(HttpServletRequest request){
		try{
			String[] ids = request.getParameterValues("ids");
			for(String id : ids){
				resService.deleteSectionExam(id);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}*/
	
	
	/*章节 End*/
	
	/*课程体系 End*/
	 /**
	 * Method name: selectResCourseCategory <BR>
	 * Description: 查询【课程体系】 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("selectResCourseCategory")
	@ResponseBody
	public Object selectResCourseCategory(HttpServletRequest request, String categoryId, String name, String parentId){
		try{
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
			ManageUserBean user = manageUserService.findUserById(id);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("companyId", user.getCompanyId());
//			param.put("subCompanyId", user.getSubCompanyId());
			param.put("id", categoryId);
			param.put("name", name);
			param.put("parentId", parentId);
			List<ResCourseCategoryBean> categoryList = resService.selectCourseCategory(param);
			if(name == null){
				ResCourseCategoryBean bean = new ResCourseCategoryBean();
				bean.setName(manageCompanyService.selectCompanyById(user.getCompanyId()).getName());
//				if(user.getCompanyId()-user.getSubCompanyId()==0){
//				}else{
//					ManageDepartmentBean dept = manageDepartmentService.getManageDepartmentById(user.getSubCompanyId());
//					bean.setName(dept==null?"":dept.getName());
//				}
				categoryList.add(bean);
			}
			return categoryList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	 }
	 
	/**
	 * Method name: deleteResCourseCategory <BR>
	 * Description: 删除【课程体系】 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping("deleteResCourseCategory")
	@ResponseBody
	public String deleteResCourseCategory(HttpServletRequest request, String id){
		try{
			resService.deleteCourseCategory(Integer.parseInt(id));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: updateResCourseCategory <BR>
	 * Description: 修改【课程体系】 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param record
	 * @return  String<BR>
	 */
	@RequestMapping("updateResCourseCategory")
	@ResponseBody
	public String updateResCourseCategory(HttpServletRequest request, ResCourseCategoryBean record){
		try{
			resService.updateCourseCategory(record);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: insertResCourseCategory <BR>
	 * Description: 添加课程体系 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param record
	 * @return  String<BR>
	 */
	@RequestMapping("insertResCourseCategory")
	@ResponseBody
	public String insertResCourseCategory(HttpServletRequest request, ResCourseCategoryBean record){
		try{
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
			ManageUserBean user = manageUserService.findUserById(id);
			record.setCompanyId(user.getCompanyId());
			record.setSubCompanyId(user.getSubCompanyId());
			record.setCreateUserId(Integer.parseInt(user.getId()));
			resService.insertCourseCategory(record);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	@RequestMapping("toChooseCourse")
	public String toChooseCourse(HttpServletRequest request){
		return "res/chooseCourse";
	}
	
	/*课程体系 End*/
	
	
	/*课程分类 start*/
	
	@RequestMapping("toResCourseTypeListPage")
	public String toResCourseTypeListPage(HttpServletRequest request){
		return "res/courseTypeList";
	}
	
	/**
	 * Method name: selectResCourseType <BR>
	 * Description: 查询【课程分类】 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("selectResCourseType")
	@ResponseBody
	public Object selectResCourseType(HttpServletRequest request, String typeId, String name, String parentId){
		try{
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
			ManageUserBean user = manageUserService.findUserById(id);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("companyId", user.getCompanyId());
			param.put("subCompanyId", user.getSubCompanyId());
			param.put("id", typeId);
			param.put("name", name);
			param.put("parentId", parentId);
			List<ResCourseTypeBean> typeList = resService.selectCourseType(param);
			if(name == null){
				ResCourseTypeBean bean = new ResCourseTypeBean();
				if(user.getCompanyId()-user.getSubCompanyId()==0){
					bean.setName(manageCompanyService.selectCompanyById(user.getCompanyId()).getName());
				}else{
					ManageDepartmentBean dept = manageDepartmentService.getManageDepartmentById(user.getSubCompanyId());
					bean.setName(dept==null?"":dept.getName());
				}
				typeList.add(bean);
			}
			return typeList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 
	/**
	 * Method name: deleteResCourseType <BR>
	 * Description: 删除【课程分类】 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping("deleteResCourseType")
	@ResponseBody
	public String deleteResCourseType(HttpServletRequest request, String id){
		try{
			resService.deleteCourseType(Integer.parseInt(id));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: updateResCourseType <BR>
	 * Description: 修改【课程分类】 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param record
	 * @return  String<BR>
	 */
	@RequestMapping("updateResCourseType")
	@ResponseBody
	public String updateResCourseType(HttpServletRequest request, ResCourseTypeBean record){
		try{
			resService.updateCourseType(record);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: insertResCourseType <BR>
	 * Description: 新增【课程分类】 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param record
	 * @return  String<BR>
	 */
	@RequestMapping("insertResCourseType")
	@ResponseBody
	public String insertResCourseType(HttpServletRequest request, ResCourseTypeBean record){
		try{
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
			ManageUserBean user = manageUserService.findUserById(id);
			record.setCompanyId(user.getCompanyId());
			record.setSubCompanyId(user.getSubCompanyId());
			record.setCreateUserId(Integer.parseInt(user.getId()));
			resService.insertCourseType(record);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: selectResCourseListByCode <BR>
	 * Description: 根据课程编码获取课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping("selectResCourseListByCode")
	@ResponseBody
	public Object selectResCourseListByCode(HttpServletRequest request, CourseVo vo){
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			vo.setCompanyId(user.getCompanyId());
			vo.setSubCompanyId(user.getSubCompanyId());
			return resService.selectCourseList(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*课程分类 End*/
	@RequestMapping("uploadFile")
	@ResponseBody
	public String uploadFile(HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file){
		String uploadResult = null;
		try {
			ManageCompanyBean company = manageCompanyService.selectCompanyById(((ManageUserBean)(request.getSession().getAttribute(Constant.SESSION_USERBEAN))).getCompanyId());
			if(company.getTotalCapacity() <= company.getUsedCapacity()){
				return "{\"result\":\"OVER_CAPACITY\"}";
			}
			String commonPath = request.getParameter("path").toString();
			uploadResult =resService.uploadFile(file,commonPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("recordCode", -1);
			return param.toString();
		}
		
		return uploadResult;//d.getswfPath();
	}
	
	/**
	 * Method name: featurAndUnFeaturResCourse <BR>
	 * Description: 精选、取消精选课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  String<BR>
	 */
	@RequestMapping("featurAndUnFeaturResCourse")
	@ResponseBody
	public String featurAndUnFeaturResCourse(HttpServletRequest request, ResCourseBean bean){
		try{
			resService.featurAndUnFeaturResCourse(bean);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: selectSectionAndExam <BR>
	 * Description: 获取章节、试卷关系 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param sectionId
	 * @return  Object<BR>
	 */
	@RequestMapping("selectCourseExam")
	@ResponseBody
	public Object selectCourseExam(HttpServletRequest request, String sectionId){
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("sectionId", sectionId);
			List<SectionExamBean> list = resService.selectSectionAndExam(param);
			return list;
		}catch(Exception e){
			return null;
		}
	}
	
	//zhangbocheng start 
	/**
	 * Method name: toBuyResCourseListPage <BR>
	 * Description: 购买课程页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toBuyResCourseListPage")
	public String toBuyResCourseListPage(HttpServletRequest request){
		return "res/buyCourseList";
	}
	

	/**
	 * Method name: selectBuyResCourseList <BR>
	 * Description: 查询购买的课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping("selectBuyResCourseList")
	@ResponseBody
	public Object selectBuyResCourseList(HttpServletRequest request, CourseVo vo){
		MMGridPageVoBean<ResCourseBean> re = new MMGridPageVoBean<ResCourseBean>();
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			vo.setCompanyId(user.getCompanyId());
			vo.setSubCompanyId(user.getSubCompanyId());
			if(vo.getMallStatus()==null){
               vo.setMallStatus(0);
			}
			vo.setCategoryIds(request.getParameterValues("categoryId[]"));
			vo.setTypeIds(request.getParameterValues("typeId[]"));
			vo.setFromNum((vo.getPage() - 1) * vo.getPageSize());
			List<ResCourseBean> result = resService.selectCourseList(vo);
			int size = resService.selectCourseListCount(vo);
			re.setRows(result);
			re.setTotal(size);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: toEditBuyResCoursePage <BR>
	 * Description: 修改购买的课程页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 * @throws Exception 
	 */
	@RequestMapping("toEditBuyResCoursePage")
	public String toEditBuyResCoursePage(HttpServletRequest request, String id) throws Exception{
		ResCourseBean course = resService.selectCourseById(Integer.parseInt(id));
		course.setPostList(resService.selectCourseOpenPostByCourseId(course.getId()));
		course.setGroupList(resService.selectCourseOpenGroupByCourseId(course.getId()));
		request.setAttribute("course", JsonUtil.getJson4JavaObject(course));
		return "res/buyCourseEdit";
	}
	//zhangbocheng end
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getOnlineCourseList <BR>
	 * Description: 查询线上课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping("getOnlineCourseList")
	@ResponseBody
	public Object getOnlineCourseList(HttpServletRequest request, CourseVo vo){
		MMGridPageVoBean<ResCourseBean> re = new MMGridPageVoBean<ResCourseBean>();
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			vo.setCompanyId(user.getCompanyId());
			vo.setSubCompanyId(user.getSubCompanyId());
			vo.setCategoryIds(request.getParameterValues("categoryId[]"));
			vo.setTypeIds(request.getParameterValues("typeId[]"));
			vo.setFromNum((vo.getPage() - 1) * vo.getPageSize());
			vo.setType("1");//线上课程
			vo.setMallStatus(1);
			List<ResCourseBean> result = resService.selectCourseList(vo);
			int size = resService.selectCourseListCount(vo);
			re.setRows(result);
			re.setTotal(size);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**shenhaijun end*/
	
}
