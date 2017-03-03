/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManagementLearningMapAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-13        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.LearnPlanStageBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.LearnPlanAssignmentVo;
import com.jftt.wifi.bean.vo.LearnPlanStageCourseEditVo;
import com.jftt.wifi.bean.vo.LearnPlanStageCourseRelationVo;
import com.jftt.wifi.bean.vo.PostApplyVo;
import com.jftt.wifi.bean.vo.PostCourseVo;
import com.jftt.wifi.bean.vo.PostPath;
import com.jftt.wifi.bean.vo.PostStage;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.ManagementLearningMapService;
import com.jftt.wifi.util.JsonUtil;

/**
 * class name:ManagementLearningMapAction <BR>
 * class description: 学习地图管理 <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-13
 * @author JFTT)HeTianrui
 */
@Controller
@RequestMapping("map")
public class ManagementLearningMapAction {
    
	private static Logger logger = Logger.getLogger(ManagementLearningMapAction.class);
	
	@Resource 
	private ManagementLearningMapService managementLearningMapServ;
	@Resource
	private ManageUserService manageUserServ;
	@Resource
	private ManageDepartmentService manageDepartmentServ;
	
	/**
	 * Method name: gotoPromotionPathAdd <BR>
	 * Description: 晋升路径-跳转 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping(value="/path/add")
	public String gotoPromotionPathAdd(){
		return "post/path_add";
	}
	
	/**
	 * Method name: toManage <BR>
	 * Description: 岗位体系管理页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value = "toManage")
	public String toManageList(HttpServletRequest request) {
		ManageUserBean userBean =(ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		if(userBean==null){
			return null;
		}
		try {
			List<PostPath> pathList = managementLearningMapServ.getPathList(userBean.getCompanyId(),userBean.getSubCompanyId());
			request.setAttribute("pathList", JsonUtil.getJsonString4JavaList(pathList));
		} catch (Exception e) {
			logger.warn(ManagementLearningMapAction.class, e);
		}
		return "learnMap/mapManage";
	}
	
	
	/**
	 * Method name: checkPathName <BR>
	 * Description: 检查路径重复<BR>
	 * Remark: <BR>
	 * @param request
     * @param name
     * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="checkPathName")
	@ResponseBody
	public Object checkPathName(HttpServletRequest request,String name,Integer id){
		try {
			ManageUserBean userBean =(ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			 int size = managementLearningMapServ.checkPathName(name,userBean.getCompanyId(),userBean.getSubCompanyId(),id);
			 if(size<=0){
				 return Constant.AJAX_SUCCESS;
			 }else{
				return Constant.AJAX_FAIL;
			 }
		} catch (Exception e) {
			logger.error("检查阶段重复!", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: addPromotionPath <BR>
	 * Description: 晋升路径-新增 <BR>
	 * Remark: <BR>
	 * @param path
	 * @param request  void<BR>
	 */
	@RequestMapping(value="savePath")
	@ResponseBody
	public Object addPromotionPath(PostPath path,HttpServletRequest request){

		try {
			ManageUserBean userBean =(ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			logger.info("USERID=/="+userBean.getId()+"====Go addMyNote====");
			path.setCompanyId(userBean.getCompanyId());
			path.setSubCompanyId(userBean.getSubCompanyId());
			Integer id =managementLearningMapServ.addPromotionPath(path);
			if(id==null){
				return Constant.AJAX_FAIL;
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", id);
			map.put("result", Constant.AJAX_SUCCESS);
			return  map;
	
		} catch (Exception e) {
			logger.error("新增晋升路径异常!", e);
			return Constant.AJAX_FAIL;
		}
	}
	/**
	 * Method name: updatePromotionPath <BR>
	 * Description: 晋升路径-修改 <BR>
	 * Remark: <BR>
	 * @param path
	 * @param request  void<BR>
	 */
	@RequestMapping(value="updatePath")
	@ResponseBody
	public Object updatePromotionPath(PostPath path,HttpServletRequest request){

		try {
			ManageUserBean userBean =(ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			logger.info("USERID=/="+userBean.getId()+"====Go addMyNote====");
			path.setCompanyId(userBean.getCompanyId());
			path.setSubCompanyId(userBean.getSubCompanyId());
			managementLearningMapServ.updatePath(path);
			return  Constant.AJAX_SUCCESS;
	
		} catch (Exception e) {
			logger.error("修改晋升路径异常!", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: deletePath <BR>
	 * Description: 晋升路径-删除 <BR>
	 * Remark: <BR>
	 * @param id
	 * @param request  void<BR>
	 */
	@RequestMapping(value="deletePath")
	@ResponseBody
	public Object deletePromotionPath(HttpServletRequest request,Integer id){

		try {
		
			managementLearningMapServ.deletePath(id);
			return  Constant.AJAX_SUCCESS;
	
		} catch (Exception e) {
			logger.error("删除晋升路径异常!", e);
			return Constant.AJAX_FAIL;
		}
	}
	/**
	 * Method name: editPromotionPath <BR>
	 * Description: 晋升路径-启用/停用编辑 <BR>
	 * Remark: <BR>
	 * @param path
	 * @param request  void<BR>
	 */
	@RequestMapping(value="editPath")
	@ResponseBody
	public Object editPromotionPath(PostPath path,HttpServletRequest request){
		String result = Constant.AJAX_SUCCESS;
		logger.info("====Go editPromotionPath====/id:"+path.getId());
		try {
			managementLearningMapServ.editPromotionPath(path);
		} catch (Exception e) {
			result = Constant.AJAX_FAIL;
			logger.error("编辑晋升路径(启用/禁用)异常!", e);
		}
		return result;
	}
	/**
	 * Method name: getLearningMapByPath <BR>
	 * Description: 点击晋升路径右侧学习地图 信息<BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping(value="getLearningMapByPath")
	@ResponseBody
	public Object getLearningMapByPath(Integer pathId,HttpServletRequest request)
	{
		try {
			ManageUserBean userBean =(ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			logger.info("USERID=/="+userBean.getId()+"====Go queryLearningMapByPath====/pathId:"+pathId);
			PostPath path = managementLearningMapServ.queryLearningMapByPath(pathId);
			return path;
		} catch (Exception e) {
			logger.error("学习地图管理异常!", e);
		}
		return Constant.AJAX_FAIL;
	}
	
	
	
	
	
	/**
	 * Method name: checkPathStage <BR>
	 * Description: 检查阶段重复<BR>
	 * Remark: <BR>
     * @param request
     * @param postId
     * @param pathId
	 * @return  Object<BR>
	 */
	@RequestMapping(value="checkPathStage")
	@ResponseBody
	public Object checkPathStage(HttpServletRequest request,Integer postId,Integer pathId){

		
		try {
			 int size = managementLearningMapServ.checkPathStage(postId,pathId);
			 if(size<=0){
				 return Constant.AJAX_SUCCESS;
			 }else{
				return Constant.AJAX_FAIL;
			 }
		} catch (Exception e) {
			logger.error("检查阶段重复!", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: addPathStage <BR>
	 * Description: 添加阶段<BR>
	 * Remark: <BR>
	 * @param request
	 * @param record
	 * @return  Object<BR>
	 */
	@RequestMapping(value="addPathStage")
	@ResponseBody
	public Object addPathStage(HttpServletRequest request, @RequestBody PostStage record){

		HttpSession s=request.getSession();
		ManageUserBean user = (ManageUserBean)s.getAttribute(Constant.SESSION_USERBEAN);
		if(user==null||user.getCompanyId()==null){
			return Constant.AJAX_FAIL;
		}
		record.setCompanyId(user.getCompanyId());
        record.setSubCompanyId(user.getSubCompanyId());
        record.setCreateUserId(Integer.parseInt(user.getId()));
		try {
			 Map<String,Object> resultMap =new HashMap<String,Object>();
		    
			int count=managementLearningMapServ.checkPathStage(record.getPostId(), record.getPathId());
			if(count==0){
				Integer id=managementLearningMapServ.insertPathStage(record);
				 resultMap.put("result", Constant.AJAX_SUCCESS);
				 resultMap.put("id", id);
			}else{
				 resultMap.put("result", Constant.AJAX_FAIL);
				 resultMap.put("message", "岗位重复");
			}
			 return resultMap;
		} catch (Exception e) {
			logger.error("学习地图管理异常!", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	
	
	/**
	 * Method name: deletePathStage <BR>
	 * Description: 删除阶段 <BR>
	 * Remark: <BR>
	 * @param request
     * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="deletePathStage")
	@ResponseBody
	public Object deletePathStage(HttpServletRequest request,String id){
		try{
			
			managementLearningMapServ.deletePathStage(Integer.parseInt(id));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			logger.error("学习地图管理异常!", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	
	/**
	 * Method name: updatePathStage <BR>
	 * Description: 修改阶段<BR>
	 * Remark: <BR>
	 * @param request
	 * @param stage
	 * @return  String<BR>
	 */
	@RequestMapping(value="updatePathStage")
	@ResponseBody
	public String updatePathStage(HttpServletRequest request, @RequestBody PostStage stage){
		try {
			
			managementLearningMapServ.updatePathStage(stage);
		     
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			logger.error("学习地图管理异常!", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: upPathStage <BR>
	 * Description: 上移阶段 <BR>
	 * Remark: <BR>
	 * @param request
     * @param id
     * @return  Object<BR>
	 */
	@RequestMapping(value="upPathStage")
	@ResponseBody
	public Object upPathStage(HttpServletRequest request,String id){
		try{
			managementLearningMapServ.upPathStage(Integer.parseInt(id));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			logger.error("学习地图管理异常!", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: downPathStage <BR>
	 * Description: 下移阶段 <BR>
	 * Remark: <BR>
	 * @param request
     * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="downPathStage")
	@ResponseBody
	public Object downPathStage(HttpServletRequest request,String id){
		try{	
			managementLearningMapServ.downPathStage(Integer.parseInt(id));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			logger.error("学习地图管理异常!", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: getTotalCredits <BR>
	 * Description: 查询课程总学分 <BR>
	 * Remark: <BR>
	 * @param request
     * @param type
     * @param postId
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getTotalCredits")
	@ResponseBody
	public Object getTotalCredits(HttpServletRequest request,Integer type,Integer postId){
		try{	
			Integer totalCredits=managementLearningMapServ.getTotalCredits(type,postId);
			return totalCredits;
		} catch (Exception e) {
			logger.error("查询课程总学分异常!", e);
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * Method name: queryStageByPathId <BR>
	 * Description: 查看阶段信息<BR>
	 * Remark: <BR>
     * @param pathId
     * @param type
     * @param model
     * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="/stage/{pathId}/{type}")
	public Object queryStageByPathId(@PathVariable("pathId") long pathId,@PathVariable("type") int type,ModelMap model,HttpServletRequest request)
	{
		try {
			ManageUserBean userBean =(ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			logger.info("USERID=/="+userBean.getId()+"====Go queryStageById====/pathId:"+pathId);
			List<PostStage> stage = managementLearningMapServ.queryStageByPathId(pathId);
			model.addAttribute("type", type);
			return stage;
			
		} catch (Exception e) {
			logger.error("学习地图管理异常!", e);
		}
		return Constant.AJAX_FAIL;
	}
	/**
	 * Method name: queryStageByPathId <BR>
	 * Description: 查看阶段对应学员信息<BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping(value="/stage/apply")
	@ResponseBody
	public Object queryStudentByStageId(PostApplyVo postApply,HttpServletRequest request)
	{
		try {
			ManageUserBean userBean =(ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			logger.info("USERID=/="+userBean.getId()+"====Go queryStudentByStageId====");
			Map<String, Object> param = new HashMap<String, Object>();
			List<ManageUserBean> userList=null;
			if(postApply.getPage()!=null&&postApply.getPageSize()!=null){
				postApply.setPage((postApply.getPage()-1)*postApply.getPageSize());
			}
			if(postApply.getDeptName()!=null&&postApply.getDeptName().length()>0){
				param.put("deptId", postApply.getDeptName());
				 userList = manageUserServ.findUserByListCondition(param);
				if(userList!=null&&userList.size()>0){
					List<String> list =new ArrayList<String>();
					for(ManageUserBean bean:userList ){
						list.add(bean.getId());
					}
					if(!list.isEmpty()){
						postApply.setUserIds(StringUtils.join(list,","));
					}else{
						postApply.setUserIds("-1");
					}
				}else{postApply.setUserIds("-1");}
			}
		
			int totalRows= managementLearningMapServ.countStudentByStageId(postApply);
			List<PostApplyVo> student = managementLearningMapServ.queryStudentByStageId(postApply);
			//条件->部门 姓名
			List<String> userIds = toList(student);
			if(userList==null){
				param.clear();
				param.put("id", userIds);
				userList = manageUserServ.findUserByListCondition(param);
			}
		
			for(ManageUserBean row : userList){
				for(PostApplyVo vo : student){
					if(row.getId().equals(vo.getUserId().toString())){
						vo.setUserName(row.getName());
						vo.setUserName(row.getUserName());
						vo.setDeptName(row.getDeptName());
						vo.setPostName(row.getPostName());
						vo.setPostId(row.getPostId());
					}
				}
			}
		
			MMGridPageVoBean<PostApplyVo> re = new MMGridPageVoBean<PostApplyVo>();
			re.setRows(student);
			re.setTotal(totalRows);
			return re;
		} catch (Exception e) {
			logger.error("学习地图管理异常!", e);
		}
		return Constant.AJAX_FAIL;
	}
    private ManageUserBean getUserBean(int userId) throws Exception{
		
		return 	 manageUserServ.findUserByIdCondition(String.valueOf(userId));
	}
	public List<String> toList(List<PostApplyVo> studentList){
		List<String> list = new ArrayList<String>();
		for(PostApplyVo bean : studentList){
			list.add(bean.getUserId().toString());
		}
		return list;
	}
	/**
	 * Method name: queryCourseByPostId <BR>
	 * Description: 查看阶段对应课程<BR>
	 * Remark: <BR>
     * @param postId
     * @param model
     * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="/stage/course/{postId}")
	public Object queryCourseByPostId(@PathVariable("postId") long postId,ModelMap model,HttpServletRequest request)
	{
		try {
			ManageUserBean userBean =(ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			logger.info("USERID=/="+userBean.getId()+"====Go queryCourseByPostId====/postId:"+postId);
			List<PostCourseVo> list = managementLearningMapServ.queryCourseByPostId(postId);
			return list;
		} catch (Exception e) {
			logger.error("查看阶段对应课程异常!", e);
		}
		return Constant.AJAX_FAIL;
	}


    /**
     *
     * @return
     */
	@RequestMapping(value="listPost")
	public String listPost(){
		return "learnMap/listPost";
	}

    /**
     *
     * @param request
     * @param postId
     * @return
     */
	@RequestMapping(value="listPostCourse")
	public String listPostCourse(HttpServletRequest request,Integer postId){
		request.setAttribute("postId", postId);
		return "learnMap/listPostCourse";
	}
	/**
	 * 跳转到晋升路径编辑
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="pathEdit")
	public String pathEdit(HttpServletRequest request,Integer id){
		try{
			if(id!=null){
				PostPath path=managementLearningMapServ.getPathById(id);
				request.setAttribute("path", JsonUtil.getJson4JavaObject(path));
			}else{
				request.setAttribute("path", JsonUtil.getJson4JavaObject(null));
			}
			
		}catch(Exception e){
			logger.error("查询路径异常!", e);
		}
		
		return "learnMap/pathEdit";
	}
	
	/**
	 * Method name: toPathProcess <BR>
	 * Description: 跳转到进度页面 <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return  String<BR>
	 */
	@RequestMapping(value="toPathProcess")
	public String toPathProcess(HttpServletRequest request,Integer pathId){
		try {
			List<PostStage> stageList = managementLearningMapServ.queryStageByPathId(pathId);
			request.setAttribute("stageList", stageList);
			
		} catch (Exception e) {
			logger.warn("查看学习进度异常", e);
		}
		request.setAttribute("pathId", pathId);
		return "learnMap/pathProcess";
	}
	
	/**
	 * Method name: agreePromotion <BR>
	 * Description: 晋升审批 <BR>
	 * Remark: <BR>
     * @param request
     * @param id
     * @param status
     * @param reason
	 * @return  String<BR>
	 */
	@RequestMapping(value="agreePromotion")
	@ResponseBody
	public Object agreePromotion(HttpServletRequest request,Integer id,Integer status,String reason){
		try {
			if(status==4){
				if(managementLearningMapServ.isCanPromotion(id)>0){
					managementLearningMapServ.agreePromotion(id,status,reason);
					return Constant.AJAX_SUCCESS;
				}else{
					return false;
				}
			}else{
				managementLearningMapServ.agreePromotion(id,status,reason);
				return Constant.AJAX_SUCCESS;
			}
			

		} catch (Exception e) {
			logger.warn("查看学习进度异常", e);
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: toPathFailProcess <BR>
	 * Description: 跳转到晋升失败学员进度页面 <BR>
	 * Remark: <BR>
	 * @param pathId
	 * @return  String<BR>
	 */
	@RequestMapping(value="toPathFailProcess")
	public String toPathFailProcess(HttpServletRequest request,Integer pathId){
		try {
			List<PostStage> stageList = managementLearningMapServ.queryStageByPathId(pathId);
			request.setAttribute("stageList", stageList);
			
		} catch (Exception e) {
			logger.warn("查看学习进度异常", e);
		}
		request.setAttribute("pathId", pathId);
		return "learnMap/pathFailProcess";
	}
	
	/**
	 * Method name: examinePromotion <BR>
	 * Description: 审核 <BR>
	 * Remark: <BR>
     * @param request
     * @param id
     * @param status
     * @param reason
	 * @return  String<BR>
	 */
	@RequestMapping(value="examinePromotion")
	@ResponseBody
	public String examinePromotion(HttpServletRequest request,Integer id,Integer status,String reason){
		try {
			managementLearningMapServ.examinePostPromotionState(id, status, reason);
				return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			logger.warn("审核晋升异常", e);
		}
		return Constant.AJAX_FAIL;
	}
	
	

}
