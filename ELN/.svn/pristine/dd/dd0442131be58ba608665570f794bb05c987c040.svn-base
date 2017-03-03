/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageAskAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月16日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ask.AskTypeBean;
import com.jftt.wifi.bean.ask.EditAskTypeVo;
import com.jftt.wifi.bean.ask.ManageAskVo;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManageAskService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.CommonUtil;

/**
 * class name:ManageAskAction <BR>
 * class description: 问问管理action <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月16日
 * @author JFTT)ShenHaijun
 */
@Controller
@RequestMapping("manageAsk")
public class ManageAskAction {
	private static Logger logger = Logger.getLogger(ManageAskAction.class);
	
	@Resource(name="manageAskService")
	private ManageAskService manageAskService;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	@Resource(name="manageCompanyService")
	private ManageCompanyService manageCompanyService;
	
	/**
	 * Method name: toManageAsk <BR>
	 * Description: 跳往问问管理页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toManageAsk")
	public String toManageAsk(HttpServletRequest request){
		return "ask/manageAsk";
	}
	
	/**
	 * Method name: getManageAsks <BR>
	 * Description: 获取管理端的问题列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchTypeId 分类id
	 * @param searchTitle 标题
	 * @param searchAsker 提问人
	 * @param askStartTime 提问开始时间
	 * @param askEndTime 提问结束时间
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("getManageAsks")
	@ResponseBody
	public Object getManageAsks(HttpServletRequest request,String searchTypeId,String searchTitle,
			String searchAsker,String askStartTime,String askEndTime,String page,String pageSize,
			String companyId, String subCompanyId){
		MMGridPageVoBean<ManageAskVo> mmGridVo = new MMGridPageVoBean<ManageAskVo>();
		try {
			logger.info("根据条件开始查询管理端问问数量");
			Integer count = manageAskService.getManageAskCount(searchTypeId,searchTitle,
					searchAsker,askStartTime,askEndTime,companyId,subCompanyId);
			logger.info("根据条件查询完毕管理端问问数量，数量为"+count);
			Integer fromNum = (int)CommonUtil.getFromNum(pageSize,page,count);
			logger.info("根据条件开始查询管理端问问列表");
			List<ManageAskVo> manageAsks = manageAskService.getManageAsks(searchTypeId,searchTitle,
					searchAsker,askStartTime,askEndTime,fromNum,pageSize,companyId,subCompanyId);
			logger.info("根据条件查询完毕管理端问问列表");
			mmGridVo.setTotal(count);
			mmGridVo.setRows(manageAsks);
		} catch (Exception e) {
			logger.warn(ManageAskAction.class,e);
		}
		return mmGridVo;
	}
	
	/**
	 * Method name: topAsk <BR>
	 * Description: 置顶问问 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param askId 问问id
	 * @return  Object<BR>
	 */
	@RequestMapping("topAsk")
	@ResponseBody
	public Object topAsk(HttpServletRequest request,Integer askId){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("开始置顶问问"+askId);
			manageAskService.topAsk(askId);
			logger.info("置顶完毕"+askId);
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(ManageAskAction.class, e);
		}
		return data;
	}
	
	/**
	 * Method name: unTopAsk <BR>
	 * Description: 取消置顶问问 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param askId 问问id
	 * @return  Object<BR>
	 */
	@RequestMapping("unTopAsk")
	@ResponseBody
	public Object unTopAsk(HttpServletRequest request,Integer askId){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("开始取消置顶问问"+askId);
			manageAskService.unTopAsk(askId);
			logger.info("取消置顶完毕"+askId);
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(ManageAskAction.class, e);
		}
		return data;
	}
	
	/**
	 * Method name: deleteAsk <BR>
	 * Description: 删除一条问问 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param askId 问问id
	 * @return  Object<BR>
	 */
	@RequestMapping("deleteAsk")
	@ResponseBody
	public Object deleteAsk(HttpServletRequest request,Integer askId){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("开始删除问问"+askId);
			manageAskService.deleteAsk(askId);
			logger.info("删除完毕问问"+askId);
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(ManageAskAction.class, e);
		}
		return data;
	}
	
	/**
	 * Method name: batchDeleteAsks <BR>
	 * Description: 批量删除问问 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("batchDeleteAsks")
	@ResponseBody
	public Object batchDeleteAsks(HttpServletRequest request){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		String[] ids = request.getParameterValues("ids[]");
		try {
			logger.info("开始批量删除问问");
			manageAskService.batchDeleteAsks(ids);
			logger.info("批量删除问问完毕");
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(ManageAskAction.class, e);
		}
		return data;
	}
	
	/**
	 * Method name: selectAskType <BR>
	 * Description: 查询问问分类  <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchTypeId 分类id
	 * @param name 名称
	 * @param parentId 父级id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("selectAskType")
	@ResponseBody
	public Object selectAskType(HttpServletRequest request, String searchTypeId, String name, String parentId,
			String companyId, String subCompanyId){
		String userId = (String)request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
		try {
			ManageUserBean user = manageUserService.findUserById(userId);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", searchTypeId);
			param.put("name", name);
			param.put("parentId", parentId);
			param.put("companyId", companyId);
			param.put("subCompanyId", subCompanyId);
			List<AskTypeBean> askTypes = manageAskService.selectAskType(param);
			if(name == null){
				AskTypeBean type = new AskTypeBean();
				type.setName(manageCompanyService.selectCompanyById(user.getCompanyId()).getName());
				askTypes.add(type);
			}
			return askTypes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: getTypeById <BR>
	 * Description: 根据id获取分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchTypeId 分类id
	 * @return  Object<BR>
	 */
	@RequestMapping("getTypeById")
	@ResponseBody
	public Object getTypeById(HttpServletRequest request,Integer searchTypeId){
		try {
			AskTypeBean typeBean = null;
			if(searchTypeId != null){
				typeBean = manageAskService.getTypeById(searchTypeId);
			}
			if(typeBean == null){
				//新建typeBean，并将公司名称放入typeBean的nam属性中
				typeBean = new AskTypeBean();
				String userId = (String)request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
				ManageUserBean user = manageUserService.findUserById(userId);
				typeBean.setName(manageCompanyService.selectCompanyById(user.getCompanyId()).getName());
			}
			return typeBean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: getEditAskType <BR>
	 * Description: 获取问问分类编辑框内容 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchTypeId 分类id
	 * @return  Object<BR>
	 */
	@RequestMapping("getEditAskType")
	@ResponseBody
	public Object getEditAskType(HttpServletRequest request, Integer searchTypeId){
		try {
			logger.info("开始查询分类"+searchTypeId+"的编辑框内容");
			EditAskTypeVo editType = null;
			if(searchTypeId != null){
				editType = manageAskService.getEditAskType(searchTypeId);
			}else{
				logger.info("查询完毕分类"+searchTypeId+"的编辑框内容");
				return null;
			}
			if(editType.getParentTypeName() == null){
				//将公司名称放入上级名称中
				String userId = (String)request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
				ManageUserBean user = manageUserService.findUserById(userId);
				editType.setParentTypeName(manageCompanyService.selectCompanyById(user.getCompanyId()).getName());
			}
			logger.info("查询完毕分类"+searchTypeId+"的编辑框内容");
			return editType;
		} catch (Exception e) {
			logger.warn(ManageAskAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: addAskType <BR>
	 * Description: 添加一个分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param parentId 父级id
	 * @param name 名称
	 * @param description 描述
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("addAskType")
	@ResponseBody
	public Object addAskType(HttpServletRequest request, Integer parentId, String name, String description,
			String companyId, String subCompanyId){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("开始添加一个问问分类");
			manageAskService.addAskType(parentId,name,description,companyId,subCompanyId);
			logger.info("添加完毕一个问问分类");
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(ManageAskAction.class, e);
		}
		return data;
	}
	
	/**
	 * Method name: updateAskType <BR>
	 * Description: 更新一个分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 分类id
	 * @param name 名称
	 * @param description 描述
	 * @return  Object<BR>
	 */
	@RequestMapping("updateAskType")
	@ResponseBody
	public Object updateAskType(HttpServletRequest request, Integer id, String name, String description){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("开始修改一个问问分类");
			manageAskService.updateAskType(id,name,description);
			logger.info("修改完毕一个问问分类");
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(ManageAskAction.class, e);
		}
		return data;
	}
	
	/**
	 * Method name: deleteAskType <BR>
	 * Description: 删除一个分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 分类id
	 * @return  Object<BR>
	 */
	@RequestMapping("deleteAskType")
	@ResponseBody
	public Object deleteAskType(HttpServletRequest request, Integer id){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("开始删除一个问问分类");
			manageAskService.deleteAskType(id);
			logger.info("删除成功一个问问分类");
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(ManageAskAction.class, e);
		}
		return data;
	}
	
	/**
	 * Method name: toChangeAskType <BR>
	 * Description: 跳往修改问问分类页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toChangeAskType")
	public String toChangeAskType(HttpServletRequest request){
		return "ask/changeAskType";
	}
	
	/**
	 * Method name: changeType <BR>
	 * Description: 修改问问分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param typeId 分类id
	 * @return  Object<BR>
	 */
	@RequestMapping("changeType")
	@ResponseBody
	public Object changeType(HttpServletRequest request, String typeId){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		String[] askIds = request.getParameterValues("askIds[]");
		try {
			logger.info("开始修改问问分类");
			manageAskService.changeType(askIds,typeId);
			logger.info("修改成功问问分类");
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(ManageAskAction.class, e);
		}
		return data;
	}
}
