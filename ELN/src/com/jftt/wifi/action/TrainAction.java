package com.jftt.wifi.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.CanJoinTrainArrangeBean;
import com.jftt.wifi.bean.CourseStudyRecordBean;
import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.TrainArrangeBean;
import com.jftt.wifi.bean.TrainArrangeContentBean;
import com.jftt.wifi.bean.TrainArrangeOpenCrowdBean;
import com.jftt.wifi.bean.TrainArrangeScoreBean;
import com.jftt.wifi.bean.TrainArrangeUserBean;
import com.jftt.wifi.bean.TrainCheckBean;
import com.jftt.wifi.bean.TrainConfigBean;
import com.jftt.wifi.bean.TrainPlanBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.questionnaire.InvestigationAssignBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireQuestionBean;
import com.jftt.wifi.bean.vo.CourseVo;
import com.jftt.wifi.bean.vo.TrainArrangeUserVo;
import com.jftt.wifi.bean.vo.TrainArrangeVo;
import com.jftt.wifi.bean.vo.TrainVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.InvestigationService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.MyExamManageService;
import com.jftt.wifi.service.ResService;
import com.jftt.wifi.service.TrainService;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.PropertyUtil;
import com.jftt.wifi.util.TimeUtil;
import com.jftt.wifi.util.WordUtil;
import com.jftt.wifi.util.XlsParserUtil;

/**
 * class name:TrainAction <BR>
 * class description: 培训管理 <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月18日
 * @author JFTT)Lu Yunlong
 */
@Controller
@RequestMapping(value="train")
public class TrainAction {
	
	private static Logger log = Logger.getLogger(TrainAction.class); 
	
	@Resource(name="trainService")
	private TrainService trainService;
	
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	
	@Resource(name="manageCompanyService")
	private ManageCompanyService manageCompanyService;
	
	@Autowired
	private InvestigationService investigationService;
	
	@Resource(name="manageDepartmentService")
	private ManageDepartmentService manageDepartmentService;
	
	@Resource(name="resService")
	private ResService resService;
	
	@Resource(name="myExamManageService")
	private MyExamManageService myExamManageService;
	
	/**
	 * Method name: trainPlanList <BR>
	 * Description: 培训计划页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="trainPlanList")
	public String trainPlanList(HttpServletRequest request){
		log.info("");
		return "train/trainPlanList";
	}
	
	/**
	 * Method name: selectTrainPlanList <BR>
	 * Description: 查询培训计划 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page 页码
	 * @param pageSize 每页显示数量
	 * @param createUserName 创建人
	 * @param status 状态
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectTrainPlanList")
	@ResponseBody
	public Object selectTrainPlanList(HttpServletRequest request, int page, int pageSize, String createUserName, String status, String timeType, String timeValue){
		MMGridPageVoBean<TrainPlanBean> grid = new MMGridPageVoBean<TrainPlanBean>();
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("fromNum", (page-1)*pageSize);
			param.put("pageSize", pageSize);
			param.put("createUserName", createUserName);
			param.put("timeType", timeType);
			param.put("timeValue", timeValue);
			param.put("status", status);
			ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			param.put("companyId", user.getCompanyId());
			param.put("subCompanyId", user.getSubCompanyId());
			int total = trainService.selectTrainPlanListCount(param);
			List<TrainPlanBean> rows = trainService.selectTrainPlanList(param);
			grid.setRows(rows);
			grid.setTotal(total);
			return grid;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method name: selectTrainPlanByType <BR>
	 * Description: 查询月度、年度计划 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param timeType 年度、月度
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectTrainPlanByType")
	@ResponseBody
	public Object selectTrainPlanByType(HttpServletRequest request, String timeType){
		MMGridPageVoBean<TrainPlanBean> grid = new MMGridPageVoBean<TrainPlanBean>();
		try{
			ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("status", 3);
			param.put("timeType", timeType);
			param.put("companyId", user.getCompanyId());
			param.put("subCompanyId", user.getSubCompanyId());
			return trainService.selectTrainPlanList(param);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method name: toUpdateTrainPlan <BR>
	 * Description: 培训计划修改页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 培训计划id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toUpdateTrainPlan")
	public String toUpdateTrainPlan(HttpServletRequest request, String id){
		log.info("");
		try{
			TrainPlanBean plan = trainService.selectTrainPlanById(Integer.parseInt(id));
			request.setAttribute("plan", JsonUtil.getJson4JavaObject(plan));
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("plan", "{}");
		}
		return "train/trainPlanUpdate";
	}
	
	/**
	 * Method name: toTrainPlanDetail <BR>
	 * Description: 培训计划详细页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 培训计划id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toTrainPlanDetail")
	public String toTrainPlanDetail(HttpServletRequest request, String id){
		try{
			TrainPlanBean plan = trainService.selectTrainPlanById(Integer.parseInt(id));
			request.setAttribute("plan", JsonUtil.getJson4JavaObject(plan));
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("plan", "{}");
		}
		return "train/trainPlanDetail";
	}
	
	/**
	 * Method name: toAddTrainPlan <BR>
	 * Description: 新增培训计划页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param timeType 年度、月度
	 * @return  String<BR>
	 */
	@RequestMapping(value="toAddTrainPlan")
	public String toAddTrainPlan(HttpServletRequest request, String timeType){
		log.info("");
		request.setAttribute("timeType", timeType);
		return "train/trainPlanAdd";
	}
	
	/**
	 * Method name: addTrainPlan <BR>
	 * Description: 新增培训计划 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param plan (TrainPlanBean)
	 * @return  Object<BR>
	 */
	@RequestMapping(value="addTrainPlan")
	@ResponseBody
	public Object addTrainPlan(HttpServletRequest request, TrainPlanBean plan){
		try{
			ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			plan.setCompanyId(user.getCompanyId());
			plan.setSubCompanyId(user.getSubCompanyId());
			plan.setCreateUserId(Integer.parseInt(user.getId()));
			plan.setCreateUserName(user.getName());
			trainService.addTrainPlan(plan);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: updateTrainPlan <BR>
	 * Description: 修改培训计划 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param plan (TrainPlanBean)
	 * @return  Object<BR>
	 */
	@RequestMapping(value="updateTrainPlan")
	@ResponseBody
	public Object updateTrainPlan(HttpServletRequest request, TrainPlanBean plan){
		try{
			trainService.updateTrainPlan(plan);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: submitTrainPlan <BR>
	 * Description: 提交培训计划 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param plan (TrainPlanBean)
	 * @return  Object<BR>
	 */
	@RequestMapping(value="submitTrainPlan")
	@ResponseBody
	public Object submitTrainPlan(HttpServletRequest request, TrainPlanBean plan){
		try{
			ManageUserBean user = (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			plan.setSubmitDept(user.getDeptName());
			plan.setSubmitUserName(user.getName());
			plan.setCompanyId(user.getCompanyId());
			plan.setSubCompanyId(user.getSubCompanyId());
			trainService.submitTrainPlan(plan);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: deleteTrainPlan <BR>
	 * Description: 删除培训计划 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="deleteTrainPlan")
	@ResponseBody
	public Object deleteTrainPlan(HttpServletRequest request){
		try{
			String[] ids = request.getParameterValues("ids[]");
			for(String id : ids){
				trainService.deleteTrainPlan(Integer.parseInt(id));
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: toTrainPlanCheck <BR>
	 * Description: 培训计划审批页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toTrainPlanCheck")
	public String toTrainPlanCheck(HttpServletRequest request){
		return "train/trainPlanCheckList";
	}
	
	/**
	 * Method name: selectTrainPlanCheckList <BR>
	 * Description: 获取【我】的审批计划 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectTrainPlanCheckList")
	@ResponseBody
	public Object selectTrainPlanCheckList(HttpServletRequest request, TrainVo vo){
		MMGridPageVoBean<TrainCheckBean> grid = new MMGridPageVoBean<TrainCheckBean>();
		try{
			ManageUserBean user = (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			vo.setCheckUserId(user.getId());
			vo.setCompanyId(user.getCompanyId().toString());
			vo.setSubCompanyId(user.getSubCompanyId().toString());
			int total = trainService.selectTrainPlanCheckCount(vo);
			List<TrainCheckBean> rows = trainService.selectTrainPlanCheckList(vo);
			grid.setRows(rows);
			grid.setTotal(total);
			return grid;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method name: toTrainPlanCheckDetail <BR>
	 * Description: 培训计划审批详细页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toTrainPlanCheckDetail")
	public String toTrainPlanCheckDetail(HttpServletRequest request, String id){
		try{
			TrainCheckBean check = trainService.selectTrainCheckById(Integer.parseInt(id));
			//根据trainId获取该计划的所有流程
			TrainVo vo = new TrainVo();
			vo.setType(check.getType().toString());
			vo.setTrainId(check.getTrainId().toString());
			List<TrainCheckBean> list = trainService.selectTrainCheckByVo(vo);
			request.setAttribute("check", JsonUtil.getJson4JavaObject(check));
			request.setAttribute("checkList", JsonUtil.getJsonString4JavaList(list));
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("check", "{}");
			request.setAttribute("checkList", "[]");
		}
		return "train/trainPlanCheckDetail";
	}
	
	
	/**
	 * Method name: checkTrainPlan <BR>
	 * Description: 审批培训计划 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param check (TrainCheckBean)
	 * @return  Object<BR>
	 */
	@RequestMapping(value="checkTrainPlan")
	@ResponseBody
	public Object checkTrainPlan(HttpServletRequest request, TrainCheckBean check){
		try{
			String[] ids = request.getParameterValues("ids[]");
			for(String id : ids){
				check.setId(Integer.parseInt(id));
				trainService.checkTrainPlan(check);
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	/**
	 * Method name: trainArrangeList <BR>
	 * Description: 培训安排页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="trainArrangeList")
	public String trainArrangeList(HttpServletRequest request){
		log.info("");
		request.setAttribute("time", new Date().getTime());
		return "train/trainArrangeList";
	}
	
	/**
	 * Method name: selectTrainArrange <BR>
	 * Description: 培训安排列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo (TrainArrangeVo)
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectTrainArrange")
	@ResponseBody
	public Object selectTrainArrange(HttpServletRequest request, TrainArrangeVo vo){
		MMGridPageVoBean<TrainArrangeBean> grid = new MMGridPageVoBean<TrainArrangeBean>();
		try{
			ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			vo.setCompanyId(user.getCompanyId().toString());
			vo.setSubCompanyId(user.getSubCompanyId().toString());
			int total = trainService.selectTrainArrangeCount(vo);
			List<TrainArrangeBean> rows = trainService.selectTrainArrangeList(vo);
			grid.setRows(rows);
			grid.setTotal(total);
			return grid;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method name: selectTrainArrangeAndContents <BR>
	 * Description: 查询培训安排和培训内容 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo (TrainArrangeVo)
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectTrainArrangeAndContents")
	@ResponseBody
	public Object selectTrainArrangeAndContents(HttpServletRequest request, TrainArrangeVo vo){
		MMGridPageVoBean<TrainArrangeBean> grid = new MMGridPageVoBean<TrainArrangeBean>();
		try{
			ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			vo.setCompanyId(user.getCompanyId().toString());
			vo.setSubCompanyId(user.getSubCompanyId().toString());
			int total = trainService.selectTrainArrangeCount(vo);
			List<TrainArrangeBean> rows = trainService.selectTrainArrangeList(vo);
			for(TrainArrangeBean row : rows){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("arrangeId", row.getId());
				row.setContents(trainService.selectContentsByArrangeId(param));
			}
			grid.setRows(rows);
			grid.setTotal(total);
			return grid;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method name: toAddTrainArrange <BR>
	 * Description: 新增培训安排基本信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toAddTrainArrange")
	public String toAddTrainArrange(HttpServletRequest request, String timeType){
		log.info("");
		request.setAttribute("timeType", timeType);
		return "train/trainArrangeAdd";
	}
	
	/**
	 * Method name: deleteTrainArrange <BR>
	 * Description: 删除培训安排 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="deleteTrainArrange")
	@ResponseBody
	public Object deleteTrainArrange(HttpServletRequest request){
		try{
			String[] ids = request.getParameterValues("ids[]");
			for(String id : ids){
				trainService.deleteTrainArrange(Integer.parseInt(id));
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: releaseTrainArrangeScore <BR>
	 * Description: 发布培训安排成绩 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 培训安排id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="releaseTrainArrangeScore")
	@ResponseBody
	public Object releaseTrainArrangeScore(HttpServletRequest request, int id){
		try{
			trainService.releaseTrainArrangeScore(id);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: cancelTrainArrange <BR>
	 * Description: 取消申请 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 培训安排id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="cancelTrainArrange")
	@ResponseBody
	public Object cancelTrainArrange(HttpServletRequest request, String id){
		try{
			trainService.cancelTrainArrange(Integer.parseInt(id));
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: lateTrainArrange <BR>
	 * Description: 延迟培训 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 培训安排id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="lateTrainArrange")
	@ResponseBody
	public Object lateTrainArrange(HttpServletRequest request, String id){
		try{
			trainService.lateTrainArrange(Integer.parseInt(id));
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: toTrainArrangeDetail <BR>
	 * Description: 培训安排详细信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param arrangeId 培训安排id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toTrainArrangeDetail")
	public String toTrainArrangeDetail(HttpServletRequest request, String arrangeId){
		log.info("");
		TrainArrangeBean arrange;
		try {
			arrange = trainService.selectTrainArrangeById(Integer.parseInt(arrangeId));
			List<TrainArrangeOpenCrowdBean> openCrowd = trainService.selectOpenCrowdByArrangeId(arrange);
			arrange.setOpenCrowd(openCrowd);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("arrangeId", arrangeId);
			List<TrainArrangeContentBean> contents = trainService.selectContentsByArrangeId(param);
			request.setAttribute("arrange", JsonUtil.getJson4JavaObject(arrange));
			request.setAttribute("contents", JsonUtil.getJson4JavaList(contents));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "train/trainArrangeDetail";
	}
	
	/**
	 * Method name: submitTrainArrange <BR>
	 * Description: 提交培训安排 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param arrange (TrainArrangeBean)
	 * @return  Object<BR>
	 */
	@RequestMapping(value="submitTrainArrange")
	@ResponseBody
	public Object submitTrainArrange(HttpServletRequest request, TrainArrangeBean arrange){
		try{
			ManageUserBean user = (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			arrange.setSubmitUserName(user.getName());
			arrange.setCompanyId(user.getCompanyId());
			arrange.setSubCompanyId(user.getSubCompanyId());
			trainService.submitTrainArrange(arrange);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: updateTrainArrangeCloseStatus <BR>
	 * Description: 开启、关闭培训报名状态 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param arrange (TrainArrangeBean)
	 * @return  Object<BR>
	 */
	@RequestMapping(value="updateTrainArrangeCloseStatus")
	@ResponseBody
	public Object updateTrainArrangeCloseStatus(HttpServletRequest request, TrainArrangeBean arrange){
		try{
			trainService.updateTrainArrangeCloseStatus(arrange);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: addTrainArrange <BR>
	 * Description: 新增培训安排基本信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param arrange (TrainArrangeBean)
	 * @return  Object<BR>
	 */
	@RequestMapping(value="addTrainArrange")
	@ResponseBody
	public Object addTrainArrange(HttpServletRequest request, TrainArrangeBean arrange){
		try{
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
			arrange.setCreateUserId(Integer.parseInt(user.getId()));
			arrange.setCreateUserName(user.getName());
			arrange.setCompanyId(user.getCompanyId());
			arrange.setSubCompanyId(user.getSubCompanyId());
			trainService.addTrainArrange(arrange);
			String[] deptIds = request.getParameterValues("deptIds[]");
			if(deptIds != null){
				for(String id : deptIds){
					TrainArrangeOpenCrowdBean open = new TrainArrangeOpenCrowdBean();
					open.setDeptId(Integer.parseInt(id));
					open.setTrainArrangeId(arrange.getId());
					trainService.addTrainArrangeOpenCrowd(open);
				}
			}
			return arrange.getId();
		}catch(Exception e){
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: toAddTrainArrangeCourse <BR>
	 * Description: 新增培训安排课程页面<BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 培训安排id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toAddTrainArrangeCourse")
	public String toAddTrainArrangeCourse(HttpServletRequest request, String id){
		log.info("");
		request.setAttribute("id", id);
		return "train/trainArrangeCourseUpdate";
	}
	
	/**
	 * Method name: toUpdateTrainArrange <BR>
	 * Description: 修改培训安排页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 培训安排id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toUpdateTrainArrange")
	public String toUpdateTrainArrange(HttpServletRequest request, String id){
		log.info("");
		try{
			TrainArrangeBean arrange = trainService.selectTrainArrangeById(Integer.parseInt(id));
			//获取培训安排的开放人员
			List<TrainArrangeOpenCrowdBean> openCrowd = trainService.selectOpenCrowdByArrangeId(arrange);
			arrange.setOpenCrowd(openCrowd);
			request.setAttribute("arrange", JsonUtil.getJson4JavaObject(arrange));
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("arrange", "{}");
		}
		return "train/trainArrangeUpdate";
	}
	
	/**
	 * Method name: updateTrainArrange <BR>
	 * Description: 修改培训安排 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param arrange （TrainArrangeBean）
	 * @return  Object<BR>
	 */
	@RequestMapping(value="updateTrainArrange")
	@ResponseBody
	public Object updateTrainArrange(HttpServletRequest request, TrainArrangeBean arrange){
		try{
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
			arrange.setCreateUserId(Integer.parseInt(user.getId()));
			arrange.setCompanyId(user.getCompanyId());
			arrange.setSubCompanyId(user.getSubCompanyId());
			trainService.updateTrainArrange(arrange);
			String[] deptIds = request.getParameterValues("deptIds[]");
			//删除原来开发信息
			trainService.deleteOpenCrowdByArrangeId(arrange);
			if(deptIds != null){
				for(String id : deptIds){
					TrainArrangeOpenCrowdBean open = new TrainArrangeOpenCrowdBean();
					open.setDeptId(Integer.parseInt(id));
					open.setTrainArrangeId(arrange.getId());
					trainService.addTrainArrangeOpenCrowd(open);
				}
			}
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	
	/**
	 * Method name: toTrainArrangeContentsUpdate <BR>
	 * Description: 培训安排课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 培训安排id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toTrainArrangeContentsUpdate")
	public String toTrainArrangeContentsUpdate(HttpServletRequest request, String arrangeId){
		log.info("");
		try {
			request.setAttribute("arrange",JsonUtil.getJson4JavaObject(trainService.selectTrainArrangeById(Integer.parseInt(arrangeId))));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "train/trainArrangeContentUpdate";
	}
	
	/**
	 * Method name: selectTrainArrangeContents <BR>
	 * Description: 根据培训id获取培训内容 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param arrangeId 培训安排id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectTrainArrangeContents")
	@ResponseBody
	public Object selectTrainArrangeContents(HttpServletRequest request, String arrangeId){
		log.info("");
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("arrangeId", arrangeId);
			List<TrainArrangeContentBean> contents = trainService.selectContentsByArrangeId(param);
			return contents;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: updateTrainArrangeContent <BR>
	 * Description: 新增、修改培训安排内容 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param content（TrainArrangeContentBean）
	 * @return  Object<BR>
	 */
	@RequestMapping(value="updateTrainArrangeContent")
	@ResponseBody
	public Object updateTrainArrangeContent(HttpServletRequest request,TrainArrangeContentBean content){
		log.info("");
		try{
			if(content.getId() != null){
				trainService.updateTrainArrangeContent(content);
			}else{
				trainService.addTrainArrangeContent(content);
			}
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: deleteTrainArrangeContent <BR>
	 * Description: 删除培训内容 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 培训内容id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="deleteTrainArrangeContent")
	@ResponseBody
	public Object deleteTrainArrangeContent(HttpServletRequest request,TrainArrangeContentBean content){
		log.info("");
		try{
			trainService.deleteTrainArrangeContent(content);
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: toTrainArrangeUser <BR>
	 * Description: 选择培训安排参训人员页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param arrangeId 培训安排id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toTrainArrangeUser")
	public String toTrainArrangeUser(HttpServletRequest request, String arrangeId){
		log.info("");
		try {
			request.setAttribute("arrange",JsonUtil.getJson4JavaObject(trainService.selectTrainArrangeById(Integer.parseInt(arrangeId))));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "train/trainArrangeUser";
	}
	
	/**
	 * Method name: selectTrainArrangeUserList <BR>
	 * Description: 查询培训安排参训人员 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param name 人员名称
	 * @param deptName 部门名称
	 * @param postName 岗位名称
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectTrainArrangeUserList")
	@ResponseBody
	public Object selectTrainArrangeUserList(HttpServletRequest request, String arrangeId, String name, String deptName, String postName, String passStatus){
		MMGridPageVoBean<ManageUserBean> grid = new MMGridPageVoBean<ManageUserBean>();
		try{
			Map<String, Object> param_ = new HashMap<String, Object>();
			param_.put("arrangeId", arrangeId);
			param_.put("passStatus", passStatus);
			List<String> ids = trainService.selectTrainArrangeUserIds(param_);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("name", name);
			param.put("deptName", deptName);
			param.put("postName", postName);
			param.put("id", ids);
			List<ManageUserBean> rows = manageUserService.findUserByListCondition(param);
			grid.setRows(rows);
			return grid;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

    /**
     * Method name: selectTrainArrangeUserListWithScore <BR>
     * Description: 查询培训安排参训人员带成绩 <BR>
     * Remark: <BR>
     * @param request
     * @param name 人员名称
     * @param deptName 部门名称
     * @param postName 岗位名称
     * @return  Object<BR>
     */
    @RequestMapping(value="selectTrainArrangeUserListWithScore")
    @ResponseBody
    public Object selectTrainArrangeUserListWithScore(HttpServletRequest request, String arrangeId, String name, String deptName, String postName, String passStatus){
        MMGridPageVoBean<TrainArrangeUserBean> grid = new MMGridPageVoBean<TrainArrangeUserBean>();
        try{
            Map<String, Object> param_ = new HashMap<String, Object>();
            param_.put("arrangeId", arrangeId);
            param_.put("passStatus", 2);
            List<TrainArrangeUserBean> rows = trainService.selectTrainArrangeUserDetail(param_);
            for(int i = rows.size() - 1; i >= 0;i--){
                TrainArrangeUserBean score = rows.get(i);
                ManageUserBean user = manageUserService.findUserByIdCondition(score.getUserId().toString());
                if(user == null){
                    rows.remove(i);
                }else{
                    score.setUser(user);
                }
            }
            grid.setRows(rows);
            return grid;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
	
	
	/**
	 * Method name: updateTrainArrangeUser <BR>
	 * Description: 修改培训安排参训人员 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param arrangeId 培训安排id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="updateTrainArrangeUser")
	@ResponseBody
	public Object updateTrainArrangeUser(HttpServletRequest request,String arrangeId){
		log.info("");
		try{
			//删除原参训人员
			trainService.deleteTrainArrangeUser(Integer.parseInt(arrangeId));
			String[] userIds = request.getParameterValues("userIds[]");
			if(userIds != null){
				for(String userId : userIds){
					TrainArrangeUserBean bean = new TrainArrangeUserBean();
					bean.setTrainArrangeId(Integer.parseInt(arrangeId));
					bean.setUserId(Integer.parseInt(userId));
					//默认通过
					bean.setPassStatus(2);
					trainService.updateTrainArrangeUser(bean);
				}
			}
			//判断该培训是否可以提交
			if(userIds == null){
				//更新培训安排的状态，设置为1，可提交
				TrainArrangeBean arrange = new TrainArrangeBean();
				arrange.setId(Integer.parseInt(arrangeId));
				arrange.setStatus(0);
				trainService.updateArrangeStauts(arrange);
			}else{
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("arrangeId", arrangeId);
				List<TrainArrangeContentBean> contents = trainService.selectContentsByArrangeId(param);
				if(contents.size() > 0){
					//更新培训安排的状态，设置为1，可提交
					TrainArrangeBean arrange = new TrainArrangeBean();
					arrange.setId(Integer.parseInt(arrangeId));
					arrange.setStatus(1);
					trainService.updateArrangeStauts(arrange);
				}
			}
			
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	
	/**
	 * Method name: toTrainArrangeCheck <BR>
	 * Description: 培训安排审批 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toTrainArrangeCheck")
	public String toTrainArrangeCheck(HttpServletRequest request){
		return "train/trainArrangeCheckList";
	}
	
	/**
	 * Method name: selectTrainArrangeCheckList <BR>
	 * Description: 获取【我】审批的培训安排 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo （TrainVo）
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectTrainArrangeCheckList")
	@ResponseBody
	public Object selectTrainArrangeCheckList(HttpServletRequest request, TrainVo vo){
		MMGridPageVoBean<TrainCheckBean> grid = new MMGridPageVoBean<TrainCheckBean>();
		try{
			ManageUserBean user = (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			vo.setCheckUserId(user.getId());
			vo.setCompanyId(user.getCompanyId().toString());
			vo.setSubCompanyId(user.getSubCompanyId().toString());
			int total = trainService.selectTrainArrangeCheckCount(vo);
			List<TrainCheckBean> rows = trainService.selectTrainArrangeCheckList(vo);
			grid.setRows(rows);
			grid.setTotal(total);
			return grid;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method name: toTrainArrangeCheckDetail <BR>
	 * Description: 培训安排审批详细信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 培训安排审批id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="toTrainArrangeCheckDetail")
	public Object toTrainArrangeCheckDetail(HttpServletRequest request,String id){
		try{
			TrainCheckBean check = trainService.selectTrainCheckById(Integer.parseInt(id));
			//根据trainId获取该计划的所有流程
			TrainVo vo = new TrainVo();
			vo.setType(check.getType().toString());
			vo.setTrainId(check.getTrainId().toString());
			List<TrainCheckBean> list = trainService.selectTrainCheckByVo(vo);
			request.setAttribute("check", JsonUtil.getJson4JavaObject(check));
			request.setAttribute("checkList", JsonUtil.getJsonString4JavaList(list));
			//获取培训内容
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("arrangeId", check.getTrainId());
			request.setAttribute("contents", JsonUtil.getJson4JavaList(trainService.selectContentsByArrangeId(param)));
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("check", "{}");
			request.setAttribute("checkList", "[]");
		}
		return "train/trainArrangeCheckDetail";
	}
	
	/**
	 * Method name: checkTrainArrange <BR>
	 * Description: 审批培训安排 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param check (培训安排审批)
	 * @return  Object<BR>
	 */
	@RequestMapping(value="checkTrainArrange")
	@ResponseBody
	public Object checkTrainArrange(HttpServletRequest request, TrainCheckBean check){
		try{
			String[] ids = request.getParameterValues("ids[]");
			for(String id : ids){
				check.setId(Integer.parseInt(id));
				trainService.checkTrainArrange(check);
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: toTrainArrangeUserList <BR>
	 * Description: 培训报名管理 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toTrainArrangeUserList")
	public String toTrainArrangeUserList(HttpServletRequest request){
		request.setAttribute("time", new Date().getTime());
		return "train/trainArrangeUserList";
	}
	
	/**
	 * Method name: selectTrainArrangeUserManageList <BR>
	 * Description: 培训报名管理 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo (TrainArrangeVo)
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectTrainArrangeUserManageList")
	@ResponseBody
	public Object selectTrainArrangeUserManageList(HttpServletRequest request, TrainArrangeVo vo){
		MMGridPageVoBean<TrainArrangeBean> grid = new MMGridPageVoBean<TrainArrangeBean>();
		try{
			ManageUserBean user = (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			vo.setCompanyId(user.getCompanyId().toString());
			vo.setSubCompanyId(user.getSubCompanyId().toString());
			vo.setStatus("3");
			int total = trainService.selectTrainArrangeCount(vo);
			List<TrainArrangeBean> rows = trainService.selectTrainArrangeList(vo);
			for(TrainArrangeBean arrange : rows){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("arrangeId", arrange.getId());
				param.put("passStatus", 2);
				arrange.setJoinedNum(trainService.selectTrainArrangeUserIds(param).size());
			}
			grid.setRows(rows);
			grid.setTotal(total);
			return grid;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method name: toTrainArrangeUserDetail <BR>
	 * Description: 培训报名详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toTrainArrangeUserDetail")
	public String toTrainArrangeUserDetail(HttpServletRequest request, String arrangeId){
		request.setAttribute("arrangeId", arrangeId);
		return "train/trainArrangeUserDetail";
	}
	
	/**
	 * Method name: selectTrainArrangeUserDetail <BR>
	 * Description: selectTrainArrangeUserDetail <BR>
	 * Remark: <BR>
	 * @param request
	 * @param arrangeId 培训安排id
	 * @param name 人员名称
	 * @param deptName 部门名称
	 * @param postName 岗位名称
	 * @param passStatus 通过状态
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectTrainArrangeUserDetail")
	@ResponseBody
	public Object selectTrainArrangeUserDetail(HttpServletRequest request, String arrangeId, String beginTime, String endTime, String passStatus){
		MMGridPageVoBean<TrainArrangeUserBean> grid = new MMGridPageVoBean<TrainArrangeUserBean>();
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("arrangeId", arrangeId);
			param.put("beginTime", beginTime);
			param.put("endTime", endTime);
			param.put("passStatus", passStatus);
			List<TrainArrangeUserBean> rows = trainService.selectTrainArrangeUserDetail(param);
			for(TrainArrangeUserBean row : rows){
				row.setUser(manageUserService.findUserByIdCondition(row.getUserId().toString()));
			}
			grid.setRows(rows);
			return grid;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method name: exportTrainArrangeUserDetail <BR>
	 * Description: 导出培训参训人员 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param response
	 * @param arrangeId 培训安排id
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param passStatus 通过状态
	 * @param userName 用户名
	 * @param name  void<BR>
	 */
	@RequestMapping(value="exportTrainArrangeUserDetail")
	public void exportTrainArrangeUserDetail(HttpServletRequest request, HttpServletResponse response, String arrangeId, String beginTime, String endTime, String passStatus, String userName, String name){
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("arrangeId", arrangeId);
			param.put("beginTime", beginTime);
			param.put("endTime", endTime);
			param.put("passStatus", passStatus);
			List<TrainArrangeUserBean> rows = trainService.selectTrainArrangeUserDetail(param);
			for(int i = rows.size()-1; i >= 0; i--){
				ManageUserBean user = manageUserService.findUserByIdCondition(rows.get(i).getUserId().toString());
				if(userName != null && !userName.isEmpty()){
					if(!user.getUserName().equals(userName)){
						rows.remove(i);
						continue;
					}
				}
				if(name != null && !name.isEmpty()){
					if(!user.getName().contains(name)){
						rows.remove(i);
						continue;
					}
				}
				rows.get(i).setUser(user);
			}
			HSSFWorkbook workbook = trainService.exportExcel(rows);
			
			response.addHeader("Content-Type","application/x-msdownload;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename="+ new String("参训人员.xls".getBytes(),"iso8859-1"));
			//得到向客户端输出二进制数据的对象 
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			workbook.write(toClient);
			toClient.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: exportTrainScore <BR>
	 * Description: 导出培训成绩 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param response
	 * @param content  void<BR>
	 */
	@RequestMapping(value="exportTrainScore")
	public void exportTrainScore(HttpServletRequest request, HttpServletResponse response, String contentId){
		try{
			if(contentId == null || contentId.isEmpty()){
				return ;
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("contentId", contentId);
			List<TrainArrangeScoreBean> rows = trainService.selectTrainArrangeUserScoreList(param);
			for(int i = rows.size()-1; i >= 0;i--){
				TrainArrangeScoreBean score = rows.get(i);
				ManageUserBean user = manageUserService.findUserByIdCondition(score.getUserId().toString());
				if(user == null){
					rows.remove(i);
				}else{
					score.setUser(user);
				}
			}
			HSSFWorkbook workbook = trainService.exportTrainScoreExcel(rows);
			
			response.addHeader("Content-Type","application/x-msdownload;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename="+ new String("培训成绩.xls".getBytes(),"iso8859-1"));
			//得到向客户端输出二进制数据的对象 
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			workbook.write(toClient);
			toClient.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: downloadTrainScoreTemplate <BR>
	 * Description: 下载培训成绩模板 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param response
	 * @param content  void<BR>
	 */
	@RequestMapping(value="downloadTrainScoreTemplate")
	public void downloadTrainScoreTemplate(HttpServletRequest request, HttpServletResponse response, String contentId){
		try{
			if(contentId == null || contentId.isEmpty()){
				return ;
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("contentId", contentId);
			List<TrainArrangeScoreBean> rows = trainService.selectTrainArrangeUserScoreList(param);
			for(int i = rows.size()-1; i >= 0;i--){
				TrainArrangeScoreBean score = rows.get(i);
				ManageUserBean user = manageUserService.findUserByIdCondition(score.getUserId().toString());
				if(user == null){
					rows.remove(i);
				}else{
					score.setUser(user);
				}
			}
			HSSFWorkbook workbook = trainService.downloadTrainScoreTemplate(rows);
			
			response.addHeader("Content-Type","application/x-msdownload;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename="+ new String("培训成绩.xls".getBytes(),"iso8859-1"));
			//得到向客户端输出二进制数据的对象 
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			workbook.write(toClient);
			toClient.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: updateUserPassStatus <BR>
	 * Description: 审核学员状态 <BR>
	 * Remark: <BR>
	 * @return  Object<BR>
	 */
	@RequestMapping(value="updateUserPassStatus")
	@ResponseBody
	public Object updateUserPassStatus(HttpServletRequest request, String id, String status, String arrangeId){
		try{
			TrainArrangeUserBean record = new TrainArrangeUserBean();
			record.setId(Integer.parseInt(id));
			record.setPassStatus(Integer.parseInt(status));
			if("2".equals(status)){
				//判断参训人员是否超过允许参训人员
				TrainArrangeBean arrange = trainService.selectTrainArrangeById(Integer.parseInt(arrangeId));
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("arrangeId", arrangeId);
				param.put("passStatus", 2);
				List<String> ids = trainService.selectTrainArrangeUserIds(param);
				if(ids.size() < arrange.getMaxJoinNum()){
					trainService.updateUserPassStatus(record);
				}else{
					return "OVER";
				}
			}else{
				trainService.updateUserPassStatus(record);
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	
	
	/**
	 * Method name: toChooseCourse <BR>
	 * Description: 选择课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param name 课程名称
	 * @return  String<BR>
	 */
	@RequestMapping("toChooseCourse")
	public String toChooseCourse(HttpServletRequest request, String name){
		request.setAttribute("name", name);
		return "train/chooseCourse";
	}
	
	
	
	/**
	 * Method name: trainConfig <BR>
	 * Description: 培训配置 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="trainConfig")
	public String trainConfig(HttpServletRequest request){
		log.info("");
		return "train/trainConfig";
	}
	
	/**
	 * Method name: selectTrainConfig <BR>
	 * Description: 根据登录用户的企业id查询培训配置 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectTrainConfig")
	@ResponseBody
	public Object selectTrainConfig(HttpServletRequest request){
		try{
			HttpSession session = request.getSession();
			Map<String, Object> param = new HashMap<String, Object>();
			ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
			param.put("companyId", user.getCompanyId());
			param.put("subCompanyId", user.getSubCompanyId());
			TrainConfigBean config = trainService.selectTrainConfig(param);
			return config;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: saveTrainConfig <BR>
	 * Description: 保存培训配置 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param config （TrainConfigBean）
	 * @return  Object<BR>
	 */
	@RequestMapping(value="saveTrainConfig")
	@ResponseBody
	public Object saveTrainConfig(HttpServletRequest request, TrainConfigBean config){
		try{
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
			config.setCompanyId(user.getCompanyId());
			config.setSubCompanyId(user.getSubCompanyId());
			trainService.saveTrainConfig(config);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: toChooseCheckUser <BR>
	 * Description: 查询审批人 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toChooseCheckUser")
	public String toChooseCheckUser(HttpServletRequest request){
		log.info("");
		return "train/chooseCheckUser";
	}
	
	/**
	 * Method name: toMyTrain <BR>
	 * Description: 我的培训 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toMyTrain")
	public String toMyTrain(HttpServletRequest request){
		log.info("");
		try {
			ManageUserBean user = (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("companyId", user.getCompanyId());
			param.put("subCompanyId", user.getSubCompanyId());
			TrainConfigBean config = trainService.selectTrainConfig(param);
			request.setAttribute("joinEndTime", config==null?0:config.getJoinEndTime());
		} catch (Exception e) {
			request.setAttribute("joinEndTime", 0);
		}
		request.setAttribute("time", new Date().getTime());
		return "train/myTrainList";
	}
	
	/**
	 * Method name: selectCanJoinTrainArrangeList <BR>
	 * Description: 查询可报名培训 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo （TrainArrangeVo）
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectCanJoinTrainArrangeList")
	@ResponseBody
	public Object selectCanJoinTrainArrangeList(HttpServletRequest request, TrainArrangeVo vo){
		MMGridPageVoBean<CanJoinTrainArrangeBean> grid = new MMGridPageVoBean<CanJoinTrainArrangeBean>();
		try{
			ManageUserBean user = (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			vo.setCompanyId(user.getCompanyId().toString());
			//获取当前用户父公司的subCompanyId
			StringBuffer subCompanyId = new StringBuffer();
			subCompanyId.append(user.getCompanyId());
			if(!user.getCompanyId().equals(user.getSubCompanyId())){
				List<ManageDepartmentBean> parentComs = manageDepartmentService.getParentComList(user);
				for(ManageDepartmentBean  parentCom : parentComs){
					subCompanyId.append(",").append(parentCom.getId());
				}
			}
			subCompanyId.append(",").append(user.getSubCompanyId());
			vo.setSubCompanyId(subCompanyId.toString());
			vo.setDeptId(user.getDeptId().toString());
			vo.setUserId(user.getId());
			int total = trainService.selectCanJoinTrainArrangeCount(vo);
			List<CanJoinTrainArrangeBean> rows = trainService.selectCanJoinTrainArrangeList(vo);
			for(TrainArrangeBean arrange : rows){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("arrangeId", arrange.getId());
				param.put("passStatus", 2);
				arrange.setJoinedNum(trainService.selectTrainArrangeUserIds(param).size());
			}
			grid.setRows(rows);
			grid.setTotal(total);
			return grid;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method name: selectJoinedTrainArrangeList <BR>
	 * Description: 已报名培训 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo （TrainArrangeVo）
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectJoinedTrainArrangeList")
	@ResponseBody
	public Object selectJoinedTrainArrangeList(HttpServletRequest request, TrainArrangeVo vo){
		MMGridPageVoBean<TrainArrangeBean> grid = new MMGridPageVoBean<TrainArrangeBean>();
		try{
			ManageUserBean user = (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			vo.setCompanyId(user.getCompanyId().toString());
			//vo.setSubCompanyId(user.getSubCompanyId().toString());
			vo.setDeptId(user.getDeptId().toString());
			vo.setUserId(user.getId());
			int total = trainService.selectJoinedTrainArrangeCount(vo);
			List<TrainArrangeBean> rows = trainService.selectJoinedTrainArrangeList(vo);
			for(TrainArrangeBean arrange : rows){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("arrangeId", arrange.getId());
				param.put("passStatus", 2);
				arrange.setJoinedNum(trainService.selectTrainArrangeUserIds(param).size());
			}
			grid.setRows(rows);
			grid.setTotal(total);
			return grid;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method name: selectMyTrainArrangeList <BR>
	 * Description: 查询我的培训记录 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo （TrainArrangeVo）
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectMyTrainArrangeList")
	@ResponseBody
	public Object selectMyTrainArrangeList(HttpServletRequest request, TrainArrangeVo vo){
		MMGridPageVoBean<TrainArrangeBean> grid = new MMGridPageVoBean<TrainArrangeBean>();
		try{
			ManageUserBean user = (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			vo.setCompanyId(user.getCompanyId().toString());
			vo.setSubCompanyId(user.getSubCompanyId().toString());
			vo.setDeptId(user.getDeptId().toString());
			vo.setUserId(user.getId());
			int total = trainService.selectJoinedTrainArrangeCount(vo);
			List<TrainArrangeBean> rows = trainService.selectJoinedTrainArrangeList(vo);
			for(TrainArrangeBean arrange : rows){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("arrangeId", arrange.getId());
				param.put("passStatus", 2);
				List<TrainArrangeContentBean> contents = trainService.selectContentsByArrangeId(param);
				int getPeriod = 0;
				for(TrainArrangeContentBean content : contents){
					TrainArrangeScoreBean score = new TrainArrangeScoreBean();
					Map<String, Object> param_ = new HashMap<String, Object>();
					param_.put("userId", request.getSession().getAttribute(Constant.SESSION_USERID_LONG));
					if(content.getTrainType().toString().equals("2")){
						param_.put("contentId", content.getId());
						List<TrainArrangeScoreBean> scoreList = trainService.selectTrainArrangeUserScoreList(param_);
						if(scoreList!=null && scoreList.size() > 0 ){
							if(scoreList.get(0).getIsPass() != null &&"1".equals(scoreList.get(0).getIsPass().toString())){
								getPeriod += content.getPeriod();
							}
						};
					}else{
						param_.put("courseId", content.getCourseId());
						List<CourseStudyRecordBean> records = trainService.selectCourseStudyByMap(param_);
						if(records != null && records.size() > 0){
							if(records.get(0).getLearnProcess().toString().equals("2")){
								getPeriod += content.getPeriod();
							}
						}
					}
				}
				arrange.setContents(contents);
				arrange.setGetPeriod(getPeriod);
			}
			grid.setRows(rows);
			grid.setTotal(total);
			return grid;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method name: joinTrainArrange <BR>
	 * Description: 报名参加培训安排 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id  
	 * @param arrangeId 培训安排id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="joinTrainArrange")
	@ResponseBody
	public Object joinTrainArrange(HttpServletRequest request, String id, String arrangeId){
		try{
			//判断参训人员是否超过允许参训人员
			TrainArrangeBean arrange = trainService.selectTrainArrangeById(Integer.parseInt(arrangeId));
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("arrangeId", arrangeId);
			param.put("passStatus", 2);
			List<String> ids = trainService.selectTrainArrangeUserIds(param);
			if(ids.size() < arrange.getMaxJoinNum()){
				HttpSession session = request.getSession();
				ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
				TrainArrangeUserBean record = new TrainArrangeUserBean();
				record.setPassStatus(1);
				record.setUserId(Integer.parseInt(user.getId()));
				record.setTrainArrangeId(Integer.parseInt(id));
				trainService.updateTrainArrangeUser(record);
			}else{
				return "OVER";
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	/**
	 * Method name: toMyTrainArrangeDetail <BR>
	 * Description: 我的培训的详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param arrangeId 培训安排id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toMyTrainArrangeDetail")
	public String toMyTrainArrangeDetail(HttpServletRequest request, String arrangeId){
		log.info("");
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("arrangeId", arrangeId);
			Object userId = request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			ManageUserBean user = (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			List<TrainArrangeContentBean> contents = trainService.selectContentsByArrangeId(param);
			List<TrainArrangeScoreBean> contentsScore = new ArrayList<TrainArrangeScoreBean>();
			for(TrainArrangeContentBean content : contents){
				TrainArrangeScoreBean score = new TrainArrangeScoreBean();
				//获取每个培训阶段的成绩
				/*if(content.getPassPercent() != null){
					//有通过考试，通过成绩、通过成绩的培训，获取考试成绩
					Map<String, Object> param_ = new HashMap<String, Object>();
					param_.put("contentId", content.getId());
					param_.put("userId", userId);
					List<TrainArrangeScoreBean> rows = trainService.selectTrainArrangeUserScoreList(param_);
					score = rows.get(0);
				}else if("1".equals(content.getTrainType().toString())){
					//线上培训，没有安排通过考试，根据课程id获取该学员是否通过
					Map<String, Object> param_ = new HashMap<String, Object>();
					param_.put("userId", userId);
					param_.put("courseId", content.getCourseId());
					List<CourseStudyRecordBean> records = trainService.selectCourseStudyByMap(param_);
					if(records.size() > 0){
						score.setIsPass(records.get(0).getLearnProcess().toString().equals("2")?1:2);
					}
				}else{
					//线下培训，根据是否签到判断该培训是否通过
					Map<String, Object> param_ = new HashMap<String, Object>();
					param_.put("contentId", content.getId());
					param_.put("userId", request.getSession().getAttribute(Constant.SESSION_USERID_LONG));
					List<TrainArrangeScoreBean> rows = trainService.selectTrainArrangeUserScoreList(param_);
					score = rows.get(0);
				}*/
				if(content.getTrainType().toString().equals("2")){
					Map<String, Object> param_ = new HashMap<String, Object>();
					param_.put("contentId", content.getId());
					param_.put("userId", userId);
					List<TrainArrangeScoreBean> rows = trainService.selectTrainArrangeUserScoreList(param_);
					score = rows.get(0);
				}else{
					//完成课程
					Map<String, Object> param_ = new HashMap<String, Object>();
					param_.put("courseId", content.getCourseId());
					param_.put("userId", userId);
					List<CourseStudyRecordBean> records = trainService.selectCourseStudyByMap(param_);
					if(records.size() > 0){
						score.setIsPass(records.get(0).getLearnProcess().toString().equals("2")?1:2);
					}
				}
				score.setContent(content);
				contentsScore.add(score);
			}
			request.setAttribute("contents", JsonUtil.getJson4JavaList(contentsScore));
			TrainArrangeVo vo = new TrainArrangeVo();
			vo.setUserId(userId.toString());
			vo.setCompanyId(user.getCompanyId().toString());
			vo.setId(Integer.parseInt(arrangeId));
			request.setAttribute("arrange",JsonUtil.getJson4JavaObject(trainService.selectJoinedTrainArrangeList(vo).get(0)));
			//课后评估
			request.setAttribute("afterClassTests", JsonUtil.getJson4JavaList(trainService.selectAfterClassTestByArrangeId(param)));
			request.setAttribute("time", new Date().getTime());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "train/myTrainArrangeDetail";
	}
	
	/**
	 * Method name: toMyNotStartTrainArrangeDetail <BR>
	 * Description: 我的未开始、已结束的培训的详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param arrangeId
	 * @return  String<BR>
	 */
	@RequestMapping(value="toMyNotStartTrainArrangeDetail")
	public String toMyNotStartTrainArrangeDetail(HttpServletRequest request, String arrangeId){
		log.info("");
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("arrangeId", arrangeId);
			ManageUserBean user = (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			Object userId = request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			List<TrainArrangeContentBean> contents = trainService.selectContentsByArrangeId(param);
			List<TrainArrangeScoreBean> contentsScore = new ArrayList<TrainArrangeScoreBean>();
			for(TrainArrangeContentBean content : contents){
				TrainArrangeScoreBean score = new TrainArrangeScoreBean();
				/*//获取每个培训阶段的成绩
				if(content.getPassPercent() != null){
					//有通过考试，通过成绩、通过成绩的培训，获取考试成绩
					Map<String, Object> param_ = new HashMap<String, Object>();
					param_.put("contentId", content.getId());
					param_.put("userId", userId);
					List<TrainArrangeScoreBean> rows = trainService.selectTrainArrangeUserScoreList(param_);
					score = rows.get(0);
				}else if(content.getTrainType().toString().equals("1")){
					//线上培训，没有安排通过考试，根据课程id获取该学员是否通过
					Map<String, Object> param_ = new HashMap<String, Object>();
					param_.put("courseId", content.getCourseId());
					param_.put("userId", userId);
					List<CourseStudyRecordBean> records = trainService.selectCourseStudyByMap(param_);
					if(records.size() > 0){
						score.setIsPass(records.get(0).getLearnProcess().toString().equals("2")?1:2);
					}
				}else{
					//线下培训，根据是否签到判断该培训是否通过
					Map<String, Object> param_ = new HashMap<String, Object>();
					param_.put("contentId", content.getId());
					param_.put("userId", userId);
					List<TrainArrangeScoreBean> rows = trainService.selectTrainArrangeUserScoreList(param_);
					score = rows.get(0);
				}*/
				if(content.getTrainType().toString().equals("2")){
					Map<String, Object> param_ = new HashMap<String, Object>();
					param_.put("contentId", content.getId());
					param_.put("userId", userId);
					List<TrainArrangeScoreBean> rows = trainService.selectTrainArrangeUserScoreList(param_);
					score = rows.get(0);
				}else{
					//完成课程
					Map<String, Object> param_ = new HashMap<String, Object>();
					param_.put("courseId", content.getCourseId());
					param_.put("userId", userId);
					List<CourseStudyRecordBean> records = trainService.selectCourseStudyByMap(param_);
					if(records.size() > 0){
						score.setIsPass(records.get(0).getLearnProcess().toString().equals("2")?1:2);
					}
				}
				score.setContent(content);
				contentsScore.add(score);
			}
			request.setAttribute("contents", JsonUtil.getJson4JavaList(contentsScore));
			TrainArrangeVo vo = new TrainArrangeVo();
			vo.setId(Integer.parseInt(arrangeId));
			vo.setUserId(userId.toString());
			vo.setCompanyId(user.getCompanyId().toString());
			TrainArrangeBean arrange = trainService.selectJoinedTrainArrangeList(vo).get(0);
			param.put("passStatus", 2);
			arrange.setJoinedNum(trainService.selectTrainArrangeUserIds(param).size());
			request.setAttribute("arrange",JsonUtil.getJson4JavaObject(arrange));
			//课后评估
			request.setAttribute("afterClassTests", JsonUtil.getJson4JavaList(trainService.selectAfterClassTestByArrangeId(param)));
			request.setAttribute("time", new Date().getTime());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "train/myNotStartTrainArrangeDetail";
	}
	
	/**
	 * Method name: downTrainArrangeContentBeforeClassFile <BR>
	 * Description: 下载培训文件资源 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param response
	 * @param path
	 * @param fileName
	 * @throws Exception  void<BR>
	 */
	@RequestMapping("downTrainArrangeContentBeforeClassFile")
	public void downTrainArrangeContentBeforeClassFile(HttpServletRequest request,HttpServletResponse response,String path, String fileName) throws Exception {
		log.debug("");
		try {
			// 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Type","application/x-msdownload;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" +new String(fileName.getBytes(),"iso8859-1")+"."+path.substring(path.lastIndexOf(".")+1));
            path = PropertyUtil.getConfigureProperties("UPLOAD_PATH") + path.substring(8, path.length());
            FileInputStream fis = new FileInputStream(new File(path));
            OutputStream clientOutput =  new BufferedOutputStream(response.getOutputStream());
            int b = 0;
            while((b = fis.read())!= -1){
            	clientOutput.write(b);
            }
            clientOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: toQuestionnaireDetail <BR>
	 * Description: 问卷详细页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @param backType
	 * @param assignId
	 * @param contentId
	 * @return  String<BR>
	 */
	@RequestMapping("toQuestionnaireDetail")
	public String toQuestionnaireDetail(HttpServletRequest request, String backType, int assignId, int contentId, int qId){
		log.info("");
		try {
			request.setAttribute("backType",backType);
			request.setAttribute("qId", qId);
			if(0 == assignId){
				//新增学生和问卷的关系
				ManageUserBean user = (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
				InvestigationAssignBean assign = new InvestigationAssignBean();
				assign.setInvestigationId(contentId);
				assign.setUserId(Integer.parseInt(user.getId()));
				assign.setStatus(1);//未提交
				assign.setType(2);//培训
				trainService.addInvestigationAssign(assign);
				request.setAttribute("assignId",assign.getId());
			}else{
				request.setAttribute("assignId",assignId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("");
		return "train/trainQuestionDetail";
	}
	
	/**
	 * Method name: toAnswerQuestionnaire <BR>
	 * Description: 进入问卷 <BR> 
	 * Remark: <BR>
	 * @param request
	 * @param assignId
	 * @param contentId
	 * @param qId
	 * @return  String<BR>
	 */
	@RequestMapping("toAnswerQuestionnaire")
	public String toAnswerQuestionnaire(HttpServletRequest request, int assignId, int contentId, int qId){
		log.info("");
		try {
			request.setAttribute("qId", qId);
			if(0 == assignId){
				//新增学生和问卷的关系
				ManageUserBean user = (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
				InvestigationAssignBean assign = new InvestigationAssignBean();
				assign.setInvestigationId(contentId);
				assign.setUserId(Integer.parseInt(user.getId()));
				assign.setStatus(1);//未提交
				assign.setType(2);//培训
				trainService.addInvestigationAssign(assign);
				request.setAttribute("assignId",assign.getId());
			}else{
				request.setAttribute("assignId",assignId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("");
		return "train/onlineQuestionnaire";
	}
	
	/**
	 * Method name: uploadFile <BR>
	 * Description: 上传文件 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param file
	 * @return  String<BR>
	 */
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
			uploadResult =trainService.uploadFile(file,commonPath);
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
	 * Method name: getAnswerDetail <BR>
	 * Description: 查询用户答卷详情 <BR>
	 * Remark: <BR>
	 * @param assignId
	 * @param qId
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getAnswerDetail")
	@ResponseBody
	public Object getAnswerDetail(int assignId, int qId){
		log.info("");
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("assignId", assignId);
		param.put("qId", qId);
		List<QuestionnaireQuestionBean> list = trainService.getAnswerDetail(param);
		log.info(" end");
		return list;
	}
	
	/**
	 * Method name: getQuestions <BR>
	 * Description: 查询问卷题目 <BR>
	 * Remark: <BR>
	 * @param assignId
	 * @param qId
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getQuestions")
	@ResponseBody
	public Object getQuestions(int assignId, int qId){
		log.info("");
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("assignId", assignId);
		param.put("qId", qId);
		List<QuestionnaireQuestionBean> list = trainService.getQuestions(param);
		log.info(" end");
		return list;
	}
	
	
	/**
	 * Method name: toTrainArrangeScoreManage <BR>
	 * Description: 成绩管理 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toTrainArrangeScoreManage")
	public String toTrainArrangeScoreManage(HttpServletRequest request){
		log.info("");
		request.setAttribute("time", new Date().getTime());
		return "train/trainArrangeScoreManage";
	}
	
	/**
	 * Method name: toInsertTrainArrangeScore <BR>
	 * Description: 录入成绩页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toInsertTrainArrangeScore")
	public String toInsertTrainArrangeScore(HttpServletRequest request, int trainArrangeId){
		log.info("");
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("arrangeId", trainArrangeId);
			request.setAttribute("time", new Date().getTime());
			request.setAttribute("arrange", JsonUtil.getJson4JavaObject(trainService.selectTrainArrangeById(trainArrangeId)));
			request.setAttribute("contents", JsonUtil.getJson4JavaList(trainService.selectContentsByArrangeId(param)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "train/insertTrainArrangeScore";
	}
	
	/**
	 * Method name: selectTrainArrangeUserScoreList <BR>
	 * Description: 获取参训人员的成绩列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param contentId
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectTrainArrangeUserScoreList")
	@ResponseBody
	public Object selectTrainArrangeUserScoreList(HttpServletRequest request, String contentId, int isSign){
		try{
			if(contentId == null || contentId.isEmpty()){
				return new MMGridPageVoBean<TrainArrangeScoreBean>();
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("contentId", contentId);
			if(isSign == 2){
				MMGridPageVoBean<TrainArrangeScoreBean> grid = new MMGridPageVoBean<TrainArrangeScoreBean>();
				List<TrainArrangeScoreBean> rows = trainService.selectTrainArrangeUserScoreList(param);
				for(int i = rows.size() - 1; i >= 0;i--){
					TrainArrangeScoreBean score = rows.get(i);
					ManageUserBean user = manageUserService.findUserByIdCondition(score.getUserId().toString());
					if(user == null){
						rows.remove(i);
					}else{
						score.setUser(user);
					}
				}
				grid.setRows(rows);
				return grid;
			}else{
				MMGridPageVoBean<TrainArrangeUserBean> grid = new MMGridPageVoBean<TrainArrangeUserBean>();
				Map<String, Object> param_ = new HashMap<String, Object>();
				param_.put("arrangeId", contentId);
				param_.put("passStatus", 2);
				List<TrainArrangeUserBean> rows = trainService.selectTrainArrangeUserDetail(param_);
				for(int i = rows.size() - 1; i >= 0;i--){
					TrainArrangeUserBean score = rows.get(i);
					ManageUserBean user = manageUserService.findUserByIdCondition(score.getUserId().toString());
					if(user == null){
						rows.remove(i);
					}else{
						score.setUser(user);
					}
				}
				grid.setRows(rows);
				return grid;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/**
	 * Method name: submitTrainArrangeScore <BR>
	 * Description: 保存成绩 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param contentId
	 * @return  Object<BR>
	 */
	@RequestMapping(value="submitTrainArrangeScore")
	@ResponseBody
	public Object submitTrainArrangeScore(HttpServletRequest request, int contentId, int isSign){
		try{
			String[] ids = request.getParameterValues("ids[]");
			String[] scores = request.getParameterValues("scores[]");
			String[] signItems = request.getParameterValues("signItems[]");
			if(isSign == 2){
				TrainArrangeContentBean content = trainService.selectContentsById(contentId);
				for(int i = 0; i < ids.length; i++){
					TrainArrangeScoreBean score = new TrainArrangeScoreBean();
					score.setUserId(Integer.parseInt(ids[i]));
					score.setTrainArrangeContentId(contentId);
					trainService.deleteTrainArrangeScore(score);
					score.setScore(Integer.parseInt(scores[i]));
					score.setIsSign(Integer.parseInt(signItems[i]));
					score.setIsPass(content.getPassPercent() > Integer.parseInt(scores[i])?2:1);
					trainService.insertTrainArrangeScore(score);
				}
			}else{
				TrainArrangeBean arrange = trainService.selectTrainArrangeById(contentId);
				for(int i = 0; i < ids.length; i++){
					//签到即可通过培训
					TrainArrangeUserBean aUser = new TrainArrangeUserBean();
					aUser.setIsSign(Integer.parseInt(signItems[i]));
					if(isSign == 1){
						aUser.setIsPass(Integer.parseInt(signItems[i]));
					}else{
						//成绩大于通过比例才可通过
						if(Integer.parseInt(scores[i]) > arrange.getPassPercent()){
							aUser.setIsPass(1);
						}else{
							aUser.setIsPass(2);
						}
					}
					aUser.setUserId(Integer.parseInt(ids[i]));
					aUser.setTrainArrangeId(contentId);
					aUser.setScore(Integer.parseInt(scores[i]));
					trainService.updateTrainArrangeUserScore(aUser);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: toImportArrangeScoreFile <BR>
	 * Description: 导入成绩 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toImportArrangeScoreFile")
	public String toImportArrangeScoreFile(HttpServletRequest request, int contentId){
		request.setAttribute("contentId", contentId);
		return "train/importFile";
	}
	
	/**
	 * Method name: toMyFile <BR>
	 * Description: 我的档案 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toMyFile")
	public String toMyFile(HttpServletRequest request){
		request.setAttribute("time", new Date().getTime());
		return "train/myFile";
	}
	
	/**
	 * Method name: selectCourseStudyByMap <BR>
	 * Description: 查询我的学习记录 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param contentId
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectCourseStudyByMap")
	@ResponseBody
	public Object selectCourseStudyByMap(HttpServletRequest request, String userId, int page, int pageSize, String learnProcess){
		MMGridPageVoBean<CourseStudyRecordBean> grid = new MMGridPageVoBean<CourseStudyRecordBean>();
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", userId);
			param.put("fromNum", (page-1)*pageSize);
			param.put("pageSize", pageSize);
			param.put("learnProcess", learnProcess);
			List<CourseStudyRecordBean> rows = trainService.selectCourseStudyByMap(param);
			int total = trainService.selectCourseStudyCountByMap(param);
			grid.setRows(rows);
			grid.setTotal(total);
			return grid;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	 private  String getUUID() {  
	        UUID uuid = UUID.randomUUID();  
	        String str = uuid.toString();  
	        return str;
	    } 
	
	/**
	 * Method name: 解析文件数据
	 * Description: importXlsFileData <BR>
	 * Remark: <BR>  void<BR>
	 */
	@RequestMapping(value="importXlsFileData")
	@ResponseBody
	public Object  importXlsFileData(HttpServletRequest request,@RequestParam(value="file", required = false)MultipartFile imgFile, int contentId){
		
		boolean flag = true;
		
		HttpSession session = request.getSession();
		//1、获取文件储存的地址。
		String pageFileName= imgFile.getOriginalFilename();//.getName() ;
		String filetype = pageFileName.split("\\.")[1];
		String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/
		
		//获取拼接地址
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String extendUrl = "train/template/"+df.format(new Date());
		
		JSONObject reObj = new JSONObject();
		//获取该阶段的通过分数
		TrainArrangeContentBean content = trainService.selectContentsById(contentId);
		try {
			File file = new File(saveUrl+extendUrl);
			if(!file.exists()){
				file.mkdirs();
			}
			//将上传的文件写到new出来的文件中
			String 	filePath =saveUrl +extendUrl+"/"+getUUID()+pageFileName;
			FileUtil.SaveFileFromInputStream(imgFile.getInputStream(), filePath);
			
			int maxNum = XlsParserUtil.getXLSNum(filePath,filetype);
			if(maxNum>maxNum){
				reObj.put("errCode", 10);
			} else{
			
				JSONArray array = new JSONArray();
				//根据文件类型，去解析
				 if("xls".equals(filetype)){
					array = XlsParserUtil.importXlsParser(filePath,"utf-8",1,1,0);
				}else if("xlsx".equals(filetype)){
					array = XlsParserUtil.importXlsxParser(filePath,"utf-8",1,1,0);
				}else{
					
				}
				 ManageUserBean user = manageUserService.findUserById((String)session.getAttribute(Constant.SESSION_USERID_LONG));
				 //此处缺少校验和异常处理
				 StringBuffer errCode = new StringBuffer();
				 for(int i=1;i<array.size();i++){
					 JSONArray sArr = array.getJSONArray(i);
					 if(!sArr.isEmpty()){
						if(sArr.size() == 0){
							continue; 
						}
						TrainArrangeScoreBean score = new TrainArrangeScoreBean();
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("userName", sArr.getString(0));
						if(user != null){
							param.put("companyId", user.getCompanyId());
						}
						List<ManageUserBean> userList = manageUserService.findUserByListCondition(param);
						if(userList != null && userList.size() > 0){
							for(ManageUserBean receiver : userList){
								if(sArr.getString(1).equals(receiver.getName())){
									score.setUserId(Integer.parseInt(receiver.getId()));
								}
							}
						}
						if(score.getUserId() == null || "".equals(score.getUserId())){
							errCode.append("第"+i+"行用户不存在；");
							continue;
						}
						score.setTrainArrangeContentId(contentId);
						//删除原先的成绩
						trainService.deleteTrainArrangeScore(score);
						if("是".equals(sArr.getString(2))){
							score.setIsSign(1);
						}else{
							score.setIsSign(2);
						}
						int scoreNum = 0;
						try{
							scoreNum = Integer.parseInt(sArr.getString(3));
						}catch(Exception e){
							errCode.append("第"+i+"行成绩不是数字；");
							continue;
						}
						score.setScore(scoreNum);
						score.setIsPass(content.getPassPercent() > scoreNum?2:1);
						//新增新成绩
						trainService.insertTrainArrangeScore(score);
						flag = false;
					 }
				 }
				 if(flag && errCode.toString().isEmpty()){
					 reObj.put("errCode", "Excel中没有数据。");
					 return reObj;
				 }
				 if("".equals(errCode.toString())){
					 reObj.put("errCode", "上传成功");
				 }else{
					 reObj.put("errCode", errCode.toString());
				 }
			}
		}catch(Exception e){
			e.printStackTrace();
			reObj.put("errCode", "上传失败");
		}
		
		return reObj;
	}
	
	
	/**
	 * Method name: chooseStu <BR>
	 * Description: 选择人员 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="chooseStu")
	public String chooseStu(HttpServletRequest request){
		return "train/chooseStu";
	}
	
	/**
	 * Method name: selectResCourseList <BR>
	 * Description: 查询课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
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
			List<ManageDepartmentBean> parentIds = manageDepartmentService.getParentComList(user);
			StringBuffer sb = new StringBuffer();
			//集团公司课程
			sb.append(user.getCompanyId());
			//所有父公司课程
			for(ManageDepartmentBean parentId : parentIds){
				sb.append(",").append(parentId.getId());
			}
			System.out.println("父公司id:"+sb);
			vo.setSonCompanyId(sb.toString());
			//vo.setSubCompanyId(user.getSubCompanyId());
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
	 * Method name: gotoMatchTest <BR>
	 * Description: 进入【通过考试】页面，进行考试 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param examId 考试id
	 * @param contentId 培训内容id
	 * @return  String<BR>
	 */
	@RequestMapping(value="gotoMatchTest")
	@ResponseBody
	public Object gotoMatchTest(HttpServletRequest request,int examId,int arrangeId){
		try {
			ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			Map<String, Integer> r = new HashMap<String, Integer>();
			TrainArrangeBean arrange = trainService.selectTrainArrangeById(arrangeId);
			r = trainService.saveExamAndAssignInfo(examId, arrange.getAceDuration(), arrange.getAceAllowTimes(), arrange.getPassPercent(), Integer.parseInt(user.getId()), user.getCompanyId(), user.getSubCompanyId());
			return r;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method name: gotoExamTest <BR>
	 * Description: 考试页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param exam_assign_id
	 * @return
	 * @throws ParseException  String<BR>
	 */
	@RequestMapping(value="gotoExamTest")
	public String gotoExamTest(HttpServletRequest request,int exam_assign_id, int arrangeId) throws ParseException{
		ExamQueryConditionBean bean = myExamManageService.selectInfoByAssignId(exam_assign_id);
		if(bean.getBegin_time() != null && bean.getEnd_time() != null){
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1 = sd.parse(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
			Date d2 = TimeUtil.parseString2Date(bean.getEnd_time(),"yyyy-MM-dd HH:mm");
			long sec = (d2.getTime() - d1.getTime())/1000;
			bean.setRemaining_time((int) sec);
		}
		request.setAttribute("paramMap", JSONArray.fromObject(bean));
		request.setAttribute("examRecordId", 0);//防止页面取值时报错
		request.setAttribute("exam_assign_id", exam_assign_id);
		request.setAttribute("arrangeId", arrangeId);
		//以下是用户信息，用户名、部门名、身份证号、头像
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		request.setAttribute("userName", userBean.getName());
		request.setAttribute("deptName", userBean.getDeptName());
		request.setAttribute("idCard", userBean.getIdCard());
		if(userBean.getPhoto() == null || "".equals(userBean.getPhoto())){
			request.setAttribute("headPhoto", request.getContextPath() + "/images/img/avatar_male.png");
		}else{
			request.setAttribute("headPhoto", userBean.getPhoto());
		}
		
		return "train/onlineTest";
	}
	
	
	/**
	 * Method name: insertTrainContentScore <BR>
	 * Description: insertTrainContentScore <BR>
	 * Remark: <BR>
	 * @param request
	 * @param examAssignId 考试记录id
	 * @param contentId 培训内容id
	 * @return
	 * @throws ParseException  String<BR>
	 */
	@RequestMapping(value="insertTrainContentScore")
	@ResponseBody
	public String insertTrainContentScore(HttpServletRequest request,int examAssignId, int arrangeId){
		try {
			ExamAssignBean examAssign = myExamManageService.getExamAssignById(examAssignId);
			ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			TrainArrangeUserBean arrangeUser = new TrainArrangeUserBean();
			arrangeUser.setUserId(Integer.parseInt(user.getId()));
			arrangeUser.setTrainArrangeId(arrangeId);
			arrangeUser.setScore(examAssign.getScore());
			if(examAssign.getIsPassed()){
				arrangeUser.setIsPass(1);
			}else{
				arrangeUser.setIsPass(2);
			}
			/*
			TrainArrangeScoreBean score = new TrainArrangeScoreBean();
			if(examAssign.getIsPassed()){
				score.setIsPass(1);
			}else{
				score.setIsPass(2);
			}
			score.setScore(examAssign.getScore());
			score.setUserId(Integer.parseInt(user.getId()));
			score.setTrainArrangeContentId(contentId);
			trainService.deleteTrainArrangeScore(score);
			trainService.insertTrainArrangeScore(score);*/
			trainService.updateTrainArrangeUserScore(arrangeUser);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	/**
	 * Method name: insertTrainContentScore <BR>
	 * Description: insertTrainContentScore <BR>
	 * Remark: <BR>
	 * @param request
	 * @param examAssignId 考试记录id
	 * @param contentId 培训内容id
	 * @return
	 * @throws ParseException  String<BR>
	 */
	@RequestMapping(value="exportTrain")
	@ResponseBody
	public String exportTrain(HttpServletRequest request,int arrangeId){
		try {
			TrainArrangeBean train = trainService.selectTrainArrangeById(arrangeId);
			train.setBeginTime(train.getBeginTime() == null ? "" : train.getBeginTime());
			train.setAllPeriod(train.getAllPeriod() == null ? 0 : train.getAllPeriod());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("arrangeId", arrangeId);
			List<TrainArrangeContentBean> contents = trainService.selectContentsByArrangeId(param);
			//培训地点
			String place = "";
			//培训内容
			String courseName = "";
			for (TrainArrangeContentBean bean : contents) {
				if (bean.getPlace() != null) place += bean.getPlace()+",";
				if (bean.getCourseName() != null) courseName += bean.getCourseName()+",";
			}	
			if (place.length() > 0) place = place.substring(0,place.length()-1);
			if (courseName.length() > 0) courseName = courseName.substring(0,courseName.length()-1);
			
			
			Map<String, Object> param_ = new HashMap<String, Object>();
            param_.put("arrangeId", arrangeId);
            param_.put("passStatus", 2);
            List<TrainArrangeUserBean> rows = trainService.selectTrainArrangeUserDetail(param_);
            
            List<TrainArrangeUserVo> userList = new ArrayList<TrainArrangeUserVo>();
            TrainArrangeUserVo userVo;
            for(int i = rows.size() - 1; i >= 0;i--){
                TrainArrangeUserBean score = rows.get(i);
                ManageUserBean user = manageUserService.findUserByIdCondition(score.getUserId().toString());
                if(user == null){
                    rows.remove(i);
                }else{
                    score.setUser(user);
                    userVo = new TrainArrangeUserVo();
                    userVo.setName(user.getName() == null ? "" : user.getName());
                    userVo.setDeptName(user.getDeptName() == null ? "" : user.getDeptName());
                    userVo.setPostName(user.getPostName() == null ? "" : user.getPostName());
                    userVo.setIdCard(user.getIdCard() == null ? "" : user.getIdCard());
                    userVo.setScore(score.getScore() == null ?  "" : score.getScore()+"");
                    userVo.setIsPass(score.getIsPass() != null && score.getIsPass() == 1 ? "通过" : "未通过");
                    userList.add(userVo);
                }
            }
			
			return WordUtil.createTrainWord(train, userList, place, courseName);
			
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	
	@RequestMapping(value="exportTrainRecord")
	@ResponseBody
	public String exportTrainRecord(HttpServletRequest request,int arrangeId,int userId){
		try {
			TrainArrangeBean train = trainService.selectTrainArrangeById(arrangeId);
			train.setBeginTime(train.getBeginTime() == null ? "" : train.getBeginTime());
			train.setAllPeriod(train.getAllPeriod() == null ? 0 : train.getAllPeriod());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("arrangeId", arrangeId);
			List<TrainArrangeContentBean> contents = trainService.selectContentsByArrangeId(param);
	
			
			Map<String, Object> param_ = new HashMap<String, Object>();
            param_.put("arrangeId", arrangeId);
            param_.put("user_id", userId);
            param_.put("passStatus", 2);
            List<TrainArrangeUserBean> rows = trainService.selectTrainArrangeUserDetail(param_);
            
            List<TrainArrangeUserVo> userList = new ArrayList<TrainArrangeUserVo>();
            TrainArrangeUserVo userVo;
            for(int i = rows.size() - 1; i >= 0;i--){
                TrainArrangeUserBean score = rows.get(i);
                ManageUserBean user = manageUserService.findUserByIdCondition(score.getUserId().toString());
                if(user == null){
                    rows.remove(i);
                }else{
                    score.setUser(user);
                    userVo = new TrainArrangeUserVo();
                    userVo.setName(user.getName() == null ? "" : user.getName());
                    userVo.setDeptName(user.getDeptName() == null ? "" : user.getDeptName());
                    userVo.setPostName(user.getPostName() == null ? "" : user.getPostName());
                    userVo.setIdCard(user.getIdCard() == null ? "" : user.getIdCard());
                    userVo.setScore(score.getScore() == null ?  "" : score.getScore()+"");
                    userVo.setIsPass(score.getIsPass() != null && score.getIsPass() == 1 ? "通过" : "未通过");
                    userList.add(userVo);
                }
            }
			
			return WordUtil.createTrainRecordWord(train, userList.get(0), contents);
			
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	

}
