package com.jftt.wifi.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jftt.wifi.bean.*;
import com.jftt.wifi.service.ManageNoticeService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.vo.LearnPlanAssignmentVo;
import com.jftt.wifi.bean.vo.LearnPlanBeanVo;
import com.jftt.wifi.bean.vo.LearnPlanStageCourseEditVo;
import com.jftt.wifi.bean.vo.LearnPlanStageCourseRelationVo;
import com.jftt.wifi.bean.vo.LearnPlanVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.LearnPlanService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.PropertyUtil;

import fr.opensagres.xdocreport.document.json.JSONObject;

/**
 * class LearnPlanAction <BR>
 * class description: 学习计划管理 <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-6
 * @author zhangbocheng
 * 
 */
@Controller
@RequestMapping("learnPlan")
public class LearnPlanAction {
   

	@Autowired
	private LearnPlanService learnPlanService;
	@Autowired
	private ManageUserService manageUserService;

    @Autowired
    private ManageNoticeService manageNoticeService ;

	private static final Logger LOG = Logger.getLogger(LearnPlanAction.class);
	/**
	 * Method name: toLearnPlanListPage <BR>
	 * Description: 跳转到学习计划管理页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toLearnPlanListPage")
	public String toLearnPlanListPage(HttpServletRequest request){
		return "learnPlan/learnPlanList";
	}

	
	/**
	 * Method name: selectLearnPlanList <BR>
	 * Description: 根据条件查询学习计划<BR>
	 * Remark: <BR>
     * @param request
     * @param name
     * @param createUser
     * @param beginTime
     * @param endTime
     * @param isPublished
     * @param page
     * @param pageSize
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectLearnPlanList")
	@ResponseBody
	public Object selectLearnPlanList(HttpServletRequest request, String name,String createUser,String beginTime,String endTime,Integer isPublished,Integer page,Integer pageSize){

		HttpSession s=request.getSession();
		ManageUserBean user = (ManageUserBean)s.getAttribute(Constant.SESSION_USERBEAN);
		MMGridPageVoBean<LearnPlanBeanVo> re = new MMGridPageVoBean<LearnPlanBeanVo>();
		if(user==null||user.getCompanyId()==null){
			return re;
		}
		try {
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("companyId", user.getCompanyId());
			map.put("subCompanyId", user.getSubCompanyId());
			map.put("name", name);
			map.put("createUser", createUser);
			map.put("beginTime", beginTime);
			map.put("endTime", endTime);
			map.put("isPublished", isPublished);
			
			if(page!=null&&pageSize!=null){
				map.put("page", (page-1)*pageSize);
				map.put("pageSize", pageSize);
			}
			
			int size = learnPlanService.selectLearnPlanBeanCountByMap(map);
			LOG.info("学习计划条数"+size);
			List<LearnPlanBeanVo> rows = learnPlanService.selectLearnPlansByMap(map);
			re.setTotal(size);
			re.setRows(rows);
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
		}
		return re;
	}
	
	/**
	 * Method name: checkLearnPlanName <BR>
	 * Description: 检查学习计划重名<BR>
	 * Remark: <BR>
     * @param request
     * @param name
     * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="checkLearnPlanName")
	@ResponseBody
	public Object checkLearnPlanName(HttpServletRequest request, String name,Integer id){
		HttpSession s=request.getSession();
		ManageUserBean user = (ManageUserBean)s.getAttribute(Constant.SESSION_USERBEAN);
		if(user==null||user.getCompanyId()==null){
			return Constant.AJAX_FAIL;
		}
		
		try {
			 Map<String,Object> map =new HashMap<String,Object>();
			 map.put("name", name);
			 map.put("id", id);
			 map.put("companyId", user.getCompanyId());
			 int size = learnPlanService.checkLearnPlanName(map);
			 if(size<=0){
				 return Constant.AJAX_SUCCESS;
			 }else{
				 return Constant.AJAX_FAIL;
			 }
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: addLearnPlan <BR>
	 * Description: 新建学习计划<BR>
	 * Remark: <BR>
	 * @param request
	 * @param record
	 * @return  Object<BR>
	 */
	@RequestMapping(value="addLearnPlan")
	@ResponseBody
	public Object addLearnPlan(HttpServletRequest request, @RequestBody LearnPlanBeanVo record){

		HttpSession s=request.getSession();
		ManageUserBean user = (ManageUserBean)s.getAttribute(Constant.SESSION_USERBEAN);
		if(user==null||user.getCompanyId()==null){
			return Constant.AJAX_FAIL;
		}
		try {
			record.setCompanyId(user.getCompanyId());
			record.setSubCompanyId(user.getSubCompanyId());
			record.setCreateUserId(Integer.parseInt(user.getId()));
			Integer id=learnPlanService.insert(record.vo2Bean());
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("result", Constant.AJAX_SUCCESS);
			resultMap.put("id", id);
			return resultMap;
			
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: publiceLearnPlan <BR>
	 * Description: 发布学习计划 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="publiceLearnPlan")
	@ResponseBody
	public Object publiceLearnPlan(HttpServletRequest request, Integer id){
		try {
			boolean result =learnPlanService.publiceLearnPlan(id);
			if(result){
                HttpSession s=request.getSession();
                ManageUserBean user = (ManageUserBean)s.getAttribute(Constant.SESSION_USERBEAN);
                if(user==null||user.getCompanyId()==null){
                    return Constant.AJAX_FAIL;
                }
                //发站内信
                List<LearnPlanAssignmentVo> rows = learnPlanService.getLearnPlanAssignment(id, null,null,null);
                List<String> userIds = toList(rows);
                Map<String, Object> param = new HashMap<String, Object>();
                List<ManageUserBean> userList= null;
                if(userList==null){
                    param.put("id", userIds);
                    userList = manageUserService.findUserByListCondition(param);
                }
                ManageNoticeBean noticeBean = new ManageNoticeBean();
                noticeBean.setSendUserId(Integer.parseInt(user.getId()));
                noticeBean.setSendUserName(user.getName());
                noticeBean.setIsSystem(1);//系统消息
                noticeBean.setIsRead(2);//未读
                noticeBean.setSendDeleteFlag(1);
                noticeBean.setRecDeleteFlag(1);
                LearnPlanBeanVo vo = learnPlanService.selectLearnPlanById(id);
                for(int i=0;i<userList.size();i++){
                    noticeBean.setTitle("学习计划发布通知");
                    noticeBean.setContent("尊敬的"+userList.get(i).getName()
                            +"：<em>您有新的学习计划需要参加，详情如下：</em><br/>"
                            +"<em>学习计划名称： " + vo.getName()+"</em><br/>"
                            +"<em>学习计划有效时间："+vo.getBeginTime()+"~"+vo.getEndTime()+"</em><br/>"
                            +"<em>请在有效时间内及时参加该学习计划！"+"</em><br/>");
                    noticeBean.setRecUserId(Integer.parseInt(userList.get(i).getId()));
                    manageNoticeService.insertNotice(noticeBean);

                }
                return Constant.AJAX_SUCCESS;
			}else{
				return false;
			}
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: deleteLearnPlan <BR>
	 * Description: 删除学习计划 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="deleteLearnPlan")
	@ResponseBody
	public Object deleteLearnPlan(HttpServletRequest request){
		try{
			String[] ids = request.getParameterValues("ids");
			for(String id : ids){
				
				learnPlanService.deleteLearnPlan(Integer.parseInt(id));
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: toLearnPlanDetailPage <BR>
	 * Description: 跳转学习计划详细页面 <BR>
	 * Remark: <BR>
	 * @param request
     * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toLearnPlanDetail")
	public String toLearnPlanDetailPage(HttpServletRequest request, String id){
		if(id!=null&&id.length()>0){
			try {
				LearnPlanBeanVo vo = learnPlanService.selectLearnPlanById(Integer.parseInt(id));
				request.setAttribute("learnPlan", vo);
			} catch (Exception e) {
				LOG.warn(LearnPlanAction.class, e);
			}
		}else{
			request.setAttribute("learnPlan", null);
		}
		return "learnPlan/learnPlanDetail";
	}
	
	
	/**
	 * Method name: learnPlanEdit <BR>
	 * Description: 跳转修改学习计划基本页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="learnPlanEdit")
	public String learnPlanBaseEdit(HttpServletRequest request, String id){
		LOG.info("修改，学习任务id:"+id);
		if(id!=null&&id.length()>0){
			try {
				LearnPlanBeanVo bean = learnPlanService.selectLearnPlanById(Integer.parseInt(id));
				request.setAttribute("learnPlan", JsonUtil.getJson4JavaObject(bean));
			} catch (Exception e) {
				LOG.warn(LearnPlanAction.class, e);
			}
		}else{
			request.setAttribute("learnPlan", JsonUtil.getJson4JavaObject(null));
		}
	
		return "learnPlan/learnPlanEdit";
	}
	
	
	/**
	 * Method name: updateLearnPlan <BR>
	 * Description: 修改学习计划基本<BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  String<BR>
	 */
	@RequestMapping(value="updateLearnPlan")
	@ResponseBody
	public String updateLearnPlan(HttpServletRequest request, @RequestBody LearnPlanBeanVo vo){
		try{
			learnPlanService.updateLearnPlan(vo.vo2Bean());
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
	}
	
	
	/** 学习计划基本信息 end */
	

	/**
	 * Method name: learnPlanStageEdit<BR>
	 * Description: 跳转到计划阶段页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="learnPlanStageEdit")
	public String learnPlanStageEdit(HttpServletRequest request,String planId){
		request.setAttribute("planId", planId);
		if(planId==null){
			return "FALIE";
		}
		try {
			List<LearnPlanStageBean> stageList = learnPlanService.getLearnPlanStages(Integer.parseInt(planId));
			request.setAttribute("stageList", JsonUtil.getJsonString4JavaList(stageList));
            LearnPlanBeanVo bean = learnPlanService.selectLearnPlanById(Integer.parseInt(planId));
            request.setAttribute("learnPlan", JsonUtil.getJson4JavaObject(bean));
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
		}
		return "learnPlan/learnPlanStageEdit";
	}
	
	/**
	 * Method name: selectLearnPlanStageList <BR>
	 * Description: 根据条件查询计划下阶段<BR>
	 * Remark: <BR>
	 * @param request
	 * @param learnPlanId
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectLearnPlanStageList")
	@ResponseBody
	public Object selectLearnPlanStageList(HttpServletRequest request, Integer learnPlanId){

		HttpSession s=request.getSession();
		ManageUserBean user = (ManageUserBean)s.getAttribute(Constant.SESSION_USERBEAN);
		
		MMGridPageVoBean<LearnPlanStageBean> re = new MMGridPageVoBean<LearnPlanStageBean>();
		if(user==null||user.getCompanyId()==null){
			return re;
		}
		try {
			List<LearnPlanStageBean> rows = learnPlanService.getLearnPlanStages(learnPlanId);
			re.setTotal(rows.size());
			re.setRows(rows);
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
		}
		return re;
	}
	
	
	
	/**
	 * Method name: checkLearnPlanStageName <BR>
	 * Description: 检查学习计划阶段重名<BR>
	 * Remark: <BR>
	 * @param request   * @param request
     * @param name
     * @param id
     * @param planId
	 * @return  Object<BR>
	 */
	@RequestMapping(value="checkLearnPlanStageName")
	@ResponseBody
	public Object checkLearnPlanStageName(HttpServletRequest request, String name,Integer id,Integer planId){

		
		try {
			 Map<String,Object> map =new HashMap<String,Object>();
			 map.put("name", name);
			 map.put("id", id);
			 map.put("planId", planId);
			 int size = learnPlanService.checkLearnPlanStageName(map);
			 if(size<=0){
				 return Constant.AJAX_SUCCESS;
			 }else{
				return Constant.AJAX_FAIL;
			 }
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: addLearnPlanStage <BR>
	 * Description: 添加学习计划阶段<BR>
	 * Remark: <BR>
	 * @param request
	 * @param record
	 * @return  Object<BR>
	 */
	@RequestMapping(value="addLearnPlanStage")
	@ResponseBody
	public Object addLearnPlanStage(HttpServletRequest request, @RequestBody LearnPlanStageCourseEditVo record){

		HttpSession s=request.getSession();
		ManageUserBean user = (ManageUserBean)s.getAttribute(Constant.SESSION_USERBEAN);
		if(user==null||user.getCompanyId()==null){
			return Constant.AJAX_FAIL;
		}
		try {
			Integer id=learnPlanService.insertLearnPlanStage(record.getBean());
			List<LearnPlanStageCourseRelationVo> list =record.getCourseList();
			if(list!=null&&list.size()>0){
				learnPlanService.saveLearnPlanStageCourseList(list, id);
			}
		     Map<String,Object> resultMap =new HashMap<String,Object>();
		     resultMap.put("result", Constant.AJAX_SUCCESS);
		     resultMap.put("id", id);
		     resultMap.put("courseList", list);
			return resultMap;
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
	}
	
	
	
	/**
	 * Method name: deleteLearnPlanStage <BR>
	 * Description: 删除学习计划阶段 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="deleteLearnPlanStage")
	@ResponseBody
	public Object deleteLearnPlanStage(HttpServletRequest request,String id){
		try{
			
			learnPlanService.deleteLearnPlanStage(Integer.parseInt(id));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
	}
	
	
	/**
	 * Method name: updateLearnPlanStage <BR>
	 * Description: 修改学习计划阶段<BR>
	 * Remark: <BR>
	 * @param request
	 * @param stage
	 * @return  String<BR>
	 */
	@RequestMapping(value="updateLearnPlanStage")
	@ResponseBody
	public String updateLearnPlanStage(HttpServletRequest request, @RequestBody LearnPlanStageCourseEditVo stage){
		try {
			
			learnPlanService.updateLearnPlanStage(stage.getBean());
			List<LearnPlanStageCourseRelationVo> list =stage.getCourseList();
			if(list!=null&&list.size()>0){
				learnPlanService.saveLearnPlanStageCourseList(list, stage.getId());
			}
		     
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: upLearnPlanStage <BR>
	 * Description: 上移学习计划阶段 <BR>
	 * Remark: <BR>
	 * @param request
     * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="upLearnPlanStage")
	@ResponseBody
	public Object upLearnPlanStage(HttpServletRequest request,String id){
		try{
				learnPlanService.upLearnPlanStage(Integer.parseInt(id));
			
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: downLearnPlanStage <BR>
	 * Description: 下移学习计划阶段 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="downLearnPlanStage")
	@ResponseBody
	public Object downLearnPlanStage(HttpServletRequest request,String id){
		try{
			
				learnPlanService.downLearnPlanStage(Integer.parseInt(id));
			
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: getLearnPlanStageCourseList <BR>
	 * Description: 查询计划阶段包含的课程<BR>
	 * Remark: <BR>
	 * @param request
	 * @param stageId
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getLearnPlanStageCourseList")
	@ResponseBody
	public Object getLearnPlanStageCourseList(HttpServletRequest request, Integer stageId){

		MMGridPageVoBean<LearnPlanStageCourseRelationVo> re = new MMGridPageVoBean<LearnPlanStageCourseRelationVo>();
	
		try {
			List<LearnPlanStageCourseRelationVo> rows = learnPlanService.getLearnPlanStageCourseList(stageId);
			re.setTotal(rows.size());
			re.setRows(rows);
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
		}
		return re;
	}
	
	/**
	 * Method name: deleteLearnPlanStageCourse <BR>
	 * Description: 删除学习阶段下课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="deleteLearnPlanStageCourse")
	@ResponseBody
	public Object deleteLearnPlanStageCourse(HttpServletRequest request,String id){
		try{
				learnPlanService.deleteLearnPlanStageCourse(Integer.parseInt(id));
			
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
	}
	
	
	/**
	 * Method name: disableLearnPlanStageCourse <BR>
	 * Description: 禁止或允许学习阶段下课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="disableLearnPlanStageCourse")
	@ResponseBody
	public Object disableLearnPlanStageCourse(HttpServletRequest request,String id){
		try{
				learnPlanService.disableLearnPlanStageCourse(Integer.parseInt(id));
			
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
	}
	
	
	/** 计划阶段 end */
	
	
	/**
	 * Method name: learnPlanAssignment <BR>
	 * Description: 跳转到计划页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="learnPlanAssignment")
	public String learnPlanAssignment(HttpServletRequest request,String planId){
		request.setAttribute("planId", planId);
		return "learnPlan/learnPlanAssignment";
	}
	
	/**
	 * Method name: getLearnPlanAssignmentList <BR>
	 * Description: 查询出学习计划的所有人员 <BR>
     * Remark: <BR>
     * @param request
     * @param planId
     * @param userName
     * @param page
     * @param pageSize
     * @return Object
     */
	@RequestMapping(value="getLearnPlanAssignmentList")
	@ResponseBody
	public Object getLearnPlanAssignmentList(HttpServletRequest request,Integer planId,String userName,Integer page,Integer pageSize){
			MMGridPageVoBean<LearnPlanAssignmentVo> re = new MMGridPageVoBean<LearnPlanAssignmentVo>();
	     try {
	    	 if(page!=null&&pageSize!=null){
					page=(page-1)*pageSize;
				}
	    	 ManageUserBean userBean =(ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
	    	 Map<String, Object> param = new HashMap<String, Object>();
				List<ManageUserBean> userList=null;
		        String userIdsStr=null;
				if(userName!=null&&userName.length()>0){
					 param.put("name", userName);
					 param.put("companyId", userBean.getCompanyId());
					 param.put("subCompanyId", userBean.getSubCompanyId());
					 userList = manageUserService.findUserByCondition(param);
					if(userList!=null&&userList.size()>0){
						List<String> list =new ArrayList<String>();
						for(ManageUserBean bean:userList ){
							list.add(bean.getId());
						}
						if(!list.isEmpty()){
							userIdsStr=StringUtils.join(list,",");
						}else{
							userIdsStr="-1";
						}
					}else{userIdsStr="-1";}
				}
	       Integer count=learnPlanService.getLearnPlanAssignmentCount(planId, userIdsStr);
		  List<LearnPlanAssignmentVo> rows = learnPlanService.getLearnPlanAssignment(planId, userIdsStr,page,pageSize);
		  List<String> userIds = toList(rows);
			if(userList==null){
				param.put("id", userIds);
				userList = manageUserService.findUserByListCondition(param);
			}
		
			for(ManageUserBean row : userList){
				for(LearnPlanAssignmentVo vo : rows){
					if(row.getId().equals(vo.getUserId().toString())){
						vo.setName(row.getName());
						vo.setUserName(row.getUserName());
						vo.setDepName(row.getDeptName());
						vo.setDepId(row.getDeptId());
						vo.setPostName(row.getPostName());
						vo.setPostId(row.getPostId());
					}
				}
			}
		  
		    re.setTotal(count);
			re.setRows(rows);
			} catch (Exception e) {
				LOG.warn(LearnPlanAction.class, e);
			}
			return re;
	}
	
	public List<String> toList(List<LearnPlanAssignmentVo> studentList){
		List<String> list = new ArrayList<String>();
		for(LearnPlanAssignmentVo bean : studentList){
			list.add(bean.getUserId().toString());
		}
		return list;
	}

	
   /**
     * Method name: addLearnPlanUser <BR>
	 * Description: 从人员库中添加计划人员 <BR>
	 * Remark: <BR>
     * @param planId
     * @param ids
     */
	@RequestMapping(value="addLearnPlanUser")
	@ResponseBody
	public Object addLearnPlanUser(HttpServletRequest request,Integer planId, String ids){
		try{
			String [] idArr= ids.split(",");
			learnPlanService.addLearnPlanUser(planId, idArr);
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
		
			
		
		
	}

   /**
     * Method name: addLearnPlanUserByDep <BR>
	 * Description: 按部门添加计划人员 <BR>
	 * Remark: <BR>
     * @param planId
     * @param ids
     */
	@RequestMapping(value="addLearnPlanUserByDep")
	@ResponseBody
	public Object addLearnPlanUserByDep(HttpServletRequest request,Integer planId, String ids){
		try{
			String [] idArr= ids.split(",");
			LOG.info(ids);
			learnPlanService.addLearnPlanUserByDep(planId, idArr);
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
		
	}

	
   /**
     * Method name: addLearnPlanUserByGroup <BR>
	 * Description: 从群组添加计划人员 <BR>
	 * Remark: <BR>
     * @param planId
     * @param ids
     */
	@RequestMapping(value="addLearnPlanUserByGroup")
	@ResponseBody
	public Object addLearnPlanUserByGroup(HttpServletRequest request,Integer planId, String ids){
		try{
			String [] idArr= ids.split(",");
			learnPlanService.addLearnPlanUserByGroup(planId, idArr);
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			LOG.warn(LearnPlanAction.class, e);
			return Constant.AJAX_FAIL;
		}
		
	}

    /**
     * Method name: addLearnPlanUserByPost <BR>
     * Description: 按岗位添加计划人员 <BR>
     * Remark: <BR>
     * @param planId
     * @param ids
     */
    @RequestMapping(value="addLearnPlanUserByPost")
    @ResponseBody
    public Object addLearnPlanUserByPost(HttpServletRequest request,Integer planId, String ids){
        try{
            String [] idArr= ids.split(",");
            LOG.info(ids);
            learnPlanService.addLearnPlanUserByPost(planId, idArr);
            return Constant.AJAX_SUCCESS;
        }catch(Exception e){
            LOG.warn(LearnPlanAction.class, e);
            return Constant.AJAX_FAIL;
        }

    }

   /**
     * Method name: deleteLearnPlanAssignment <BR>
	 * Description: 删除计划人员 <BR>
	 * Remark: <BR>
     * @param request
     * @param ids
     */
	@RequestMapping(value="deleteLearnPlanAssignment")
	@ResponseBody
	public Object deleteLearnPlanAssignment(HttpServletRequest request,String[] ids){
	    try{
		learnPlanService.delLearnPlanUser( ids);
		  return Constant.AJAX_SUCCESS;
	    }catch(Exception e){
	    	LOG.warn(LearnPlanAction.class, e);
		  return Constant.AJAX_FAIL;
	   }
	}

	/**
	 * Method name: toLearnPlanStudentProcessPage <BR>
	 * Description: 跳转到学习人员进度页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toLearnPlanStudentProcessPage")
	public String toLearnPlanStudentProcessPage(HttpServletRequest request,String planId){
		   LOG.info("学习任务id:"+planId);
		try {
			List<LearnPlanStageBean> stageList = learnPlanService.getLearnPlanStages(Integer.parseInt(planId));
			LOG.info("学习任务阶段数:"+stageList.size());
			request.setAttribute("stageList", JsonUtil.getJsonString4JavaList(stageList));
			
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
		}
		request.setAttribute("planId", planId);
		return "learnPlan/learnPlanProcess";
	}
	
	/**
	 * Method name: getLearnPlanStudentProcess <BR>
	 * Description: 根据条件查询学习计划人员进度<BR>
	 * Remark: <BR>
	 * @param request
	 * @param
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getLearnPlanStudentProcess")
	@ResponseBody
	public Object getLearnPlanStudentProcess(HttpServletRequest request,String name,String userName, String depName,String postName,Integer planId,Integer stageId,Integer status,Integer page,Integer pageSize){
		MMGridPageVoBean<LearnPlanAssignmentVo> re = new MMGridPageVoBean<LearnPlanAssignmentVo>();
		
		try {
			if(page!=null&&pageSize!=null){
				page=(page-1)*pageSize;
			}
			Map<String, Object> param = new HashMap<String, Object>();
			List<ManageUserBean> userList=null;
	        String userIdsStr=null;
			if((name!=null&&name.length()>0)||(userName!=null&&userName.length()>0)||(depName!=null&&depName.length()>0)||(postName!=null&&postName.length()>0)){
				 param.put("deptId", depName+"");
				 param.put("postId", postName+"");
                param.put("name", name+"");
                param.put("userName", userName+"");
				 userList = manageUserService.findUserByListCondition(param);
				if(userList!=null&&userList.size()>0){
					List<String> list =new ArrayList<String>();
					for(ManageUserBean bean:userList ){
						list.add(bean.getId());
					}
					if(!list.isEmpty()){
						userIdsStr=StringUtils.join(list,",");
					}else{
						userIdsStr="-1";
					}
				}else{userIdsStr="-1";}
			}
			List<LearnPlanAssignmentVo> rows = learnPlanService.getLearnPlanStudentProcess(userIdsStr, planId, stageId,status, page, pageSize);
			Integer total= learnPlanService.getLearnPlanStudentCount(userIdsStr, planId, stageId,status);
			  List<String> userIds = toList(rows);
				if(userList==null){
					param.clear();
					param.put("id", userIds);
					userList = manageUserService.findUserByListCondition(param);
				}
			
				for(ManageUserBean row : userList){
					for(LearnPlanAssignmentVo vo : rows){
						if(row.getId().equals(vo.getUserId().toString())){
							vo.setName(row.getName());
							vo.setUserName(row.getUserName());
							vo.setDepName(row.getDeptName());
							vo.setDepId(row.getDeptId());
							vo.setPostName(row.getPostName());
							vo.setPostId(row.getPostId());
						}
					}
				}
			re.setTotal(total);
            re.setRows(rows);
		} catch (Exception e) {
			LOG.warn(LearnPlanAction.class, e);
		}
		return re;
	}
	
	
	@RequestMapping(value="uploadImg")
	@ResponseBody
	public  String  uploadImg(HttpServletRequest request,@RequestParam(value="file", required = false)MultipartFile imgFile){

		try {
			
			//1、获取文件储存的地址。
			String pageFileName= imgFile.getOriginalFilename();//.getName() ;
			String nameSuff=".jpg";
			if(StringUtils.isNotBlank(pageFileName)){
				nameSuff=pageFileName.substring(pageFileName.lastIndexOf("."));
			}
			
			String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/

			//获取拼接地址
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String extendUrl = "learnPlan/upload/"+df.format(new Date());
			
			File file = new File(saveUrl+extendUrl);
			if(!file.exists()){
				file.mkdirs();
			}
			
			//2、获取文件的新的名称。以时间戳+四位随机数组成
			String fileName =getUUID()+nameSuff;
			String filePath = saveUrl+extendUrl+"/"+fileName;
			File sourceFile= new File(filePath);
			
			//先删除此文件
			FileUtil.deleteFile(sourceFile);
			
			//将上传的文件写到new出来的文件中
			FileUtil.SaveFileFromInputStream(imgFile.getInputStream(), filePath);

			return PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH")+"/"+extendUrl+"/"+fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	@RequestMapping(value="listUser")
	public String listUser(){
		return "learnPlan/listUser";
	}
	@RequestMapping(value="listTeacher")
	public String listTeacher(){
		return "learnPlan/listTeacher";
	}
	@RequestMapping(value="listCourse")
	public String listCourse(){
		return "learnPlan/listCourse";
	}
	@RequestMapping(value="listDep")
	public String listDep(){
		return "learnPlan/listDepartment";
	}
	@RequestMapping(value="listPost")
	public String listPost(){
		return "learnPlan/listPost";
	}
	@RequestMapping(value="listGroup")
	public String listGroup(){
		return "learnPlan/listGroup";
	}
	
	 private  String getUUID() {  
	        UUID uuid = UUID.randomUUID();  
	        String str = uuid.toString();  
	        return str;
	    }  
	
}
