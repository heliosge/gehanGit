/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageThematicAskAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月24日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ask.AskAnswerBean;
import com.jftt.wifi.bean.ask.AskDetailBean;
import com.jftt.wifi.bean.thematicAsk.ManageThematicAskVo;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManageThematicAskService;
import com.jftt.wifi.util.CommonUtil;

/**
 * class name:ManageThematicAskAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月24日
 * @author JFTT)ShenHaijun
 */
@Controller
@RequestMapping("manageThematicAsk")
public class ManageThematicAskAction {
	private Logger logger = Logger.getLogger(ManageThematicAskAction.class);
	
	@Resource(name="manageThematicAskService")
	private ManageThematicAskService manageThematicAskService;
	
	/**
	 * Method name: toManageThematicAsk <BR>
	 * Description: 跳转到专题问答管理页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toManageThematicAsk")
	public String toManageThematicAsk(HttpServletRequest request){
		return "thematicAsk/manageThematicAsk";
	}
	
	/**
	 * Method name: getThematicAskForMMGrid <BR>
	 * Description: 获取专题问答（mmGrid展现） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchAskState 专题状态
	 * @param searchTitle 专题标题
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getThematicAskForMMGrid")
	@ResponseBody
	public Object getThematicAskForMMGrid(HttpServletRequest request, String searchAskState, String searchTitle,
			String page, String pageSize){
		MMGridPageVoBean<ManageThematicAskVo> mmGridVo = new MMGridPageVoBean<ManageThematicAskVo>();
		try {
			logger.info("开始查询管理端专题问答的数量");
			Integer count = manageThematicAskService.getThematicAskCount(searchAskState,searchTitle);
			logger.info("查询完毕管理端专题问答的数量");
			Integer fromNum = (int)CommonUtil.getFromNum(pageSize,page,count);
			logger.info("开始查询管理端专题问答的列表");
			List<ManageThematicAskVo> thematicAsks = manageThematicAskService.getThematicAsks(searchAskState,
					searchTitle,fromNum,pageSize);
			logger.info("查询完毕管理端专题问答的列表");
			mmGridVo.setTotal(count);
			mmGridVo.setRows(thematicAsks);
		} catch (Exception e) {
			logger.warn(ManageThematicAskAction.class, e);
		}
		return mmGridVo;
	}
	
	/**
	 * Method name: toNewThematicAsk <BR>
	 * Description: 跳往新增专题问答页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toNewThematicAsk")
	public String toNewThematicAsk(HttpServletRequest request){
		return "thematicAsk/newThematicAsk";
	}
	
	/**
	 * Method name: getThematicAsksByTitle <BR>
	 * Description: 根据标题查询专题问答 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param thematicTitle 专题标题
	 * @return  Object<BR>
	 */
	@RequestMapping("getAsksByTitle")
	@ResponseBody
	public Object getThematicAsksByTitle(HttpServletRequest request, String thematicTitle){
		try {
			logger.info("根据标题"+thematicTitle+"开始查询专题问答");
			List<AskDetailBean> thematicAsks = manageThematicAskService.getThematicAsksByTitle(thematicTitle);
			logger.info("根据标题"+thematicTitle+"查询完毕专题问答");
			return thematicAsks;
		} catch (Exception e) {
			logger.warn(ManageThematicAskAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: addThematicAsk <BR>
	 * Description: 添加专题问答 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param title 标题
	 * @param content 内容
	 * @param initialAnswer 初始答案
	 * @param addPics 添加图片
	 * @param coverPic 封面图片
	 * @param tags 标签
	 * @param effectiveTime 过期时间
	 * @param rewardPoints 绩点
	 * @param askerId 提问人id
	 * @param askerName 提问人姓名
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("addThematicAsk")
	@ResponseBody
	public Object addThematicAsk(HttpServletRequest request,String title,String content,String initialAnswer,
			String addPics,String coverPic,String tags,String effectiveTime,String rewardPoints,
			String askerId,String askerName,String companyId,String subCompanyId){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("开始添加一条专题问答");
			manageThematicAskService.addThematicAsk(title,content,initialAnswer,addPics,coverPic,tags,
					effectiveTime,rewardPoints,askerId,askerName,companyId,subCompanyId);
			logger.info("添加完毕一条专题问答");
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(ManageThematicAskAction.class, e);
		}
		return data;
	}
	
	/**
	 * Method name: deleteThematicAsk <BR>
	 * Description: 删除专题问答 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 问答id
	 * @return  Object<BR>
	 */
	@RequestMapping("deleteThematicAsk")
	@ResponseBody
	public Object deleteThematicAsk(HttpServletRequest request,Integer id){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("开始删除专题问答"+id);
			manageThematicAskService.deleteThematicAsk(id);
			logger.info("完成删除专题问答"+id);
		} catch (Exception e) {
			logger.warn(ManageThematicAskAction.class, e);
		}
		return data;
	}
	
	/**
	 * Method name: batchDeleteThematicAsks <BR>
	 * Description: 批量删除专题问答 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("batchDeleteThematicAsks")
	@ResponseBody
	public Object batchDeleteThematicAsks(HttpServletRequest request){
		String[] ids = request.getParameterValues("ids[]");
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("开始批量删除专题问答");
			manageThematicAskService.batchDeleteThematicAsks(ids);
			logger.info("完成批量删除专题问答");
		} catch (Exception e) {
			logger.warn(ManageThematicAskAction.class, e);
		}
		return data;
	}
	
	/**
	 * Method name: toManageThematicAskDetail <BR>
	 * Description: 跳往专题问答详情页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 专题id
	 * @return  String<BR>
	 */
	@RequestMapping("toManageThematicAskDetail")
	public String toManageThematicAskDetail(HttpServletRequest request,Integer id){
		try {
			logger.info("根据id"+id+"开始查询专题问答详情");
			AskDetailBean thematicAsk = manageThematicAskService.getThematicAskById(id);
			request.setAttribute("thematicAsk", JSONObject.fromObject(thematicAsk));
			logger.info("根据id"+id+"结束查询专题问答详情");
		} catch (Exception e) {
			logger.warn(ManageThematicAskAction.class,e);
		}
		return "thematicAsk/manageThematicAskDetail";
	}
	
	/**
	 * Method name: getAnswerCount <BR>
	 * Description: 获取该问题回答的数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param thematicAskId 专题问答id
	 * @return  Object<BR>
	 */
	@RequestMapping("getAnswerCount")
	@ResponseBody
	public Object getAnswerCount(HttpServletRequest request,Integer thematicAskId){
		try {
			logger.info("开始查询专题问答"+thematicAskId+"回答的数量");
			Integer count = manageThematicAskService.getAnswerCount(thematicAskId);
			logger.info("专题问答"+thematicAskId+"回答的数量为"+count);
			return count;
		} catch (Exception e) {
			logger.warn(ManageThematicAskAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getSatisfactoryAnswer <BR>
	 * Description: 获取专题问答满意答案 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param questionId 问题id
	 * @return  Object<BR>
	 */
	@RequestMapping("getSatisfactoryAnswer")
	@ResponseBody
	public Object getSatisfactoryAnswer(HttpServletRequest request,Integer questionId){
		try {
			logger.info("开始查询专题问答"+questionId+"的满意答案");
			AskAnswerBean satisfactoryAnswer = manageThematicAskService.getSatisfactoryAnswer(questionId);
			logger.info("结束查询专题问答"+questionId+"的满意答案");
			return satisfactoryAnswer;
		} catch (Exception e) {
			logger.warn(ManageThematicAskAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getOtherAnswers <BR>
	 * Description: 获取专题问答其他回答 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param questionId 问题id
	 * @return  Object<BR>
	 */
	@RequestMapping("getOtherAnswers")
	@ResponseBody
	public Object getOtherAnswers(HttpServletRequest request,Integer questionId){
		try {
			logger.info("开始查询专题问答"+questionId+"的其他回答");
			List<AskAnswerBean> otherAnswers = manageThematicAskService.getOtherAnswers(questionId);
			logger.info("结束查询专题问答"+questionId+"的其他回答");
			return otherAnswers;
		} catch (Exception e) {
			logger.warn(ManageThematicAskAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: toDealWithThematicAsk <BR>
	 * Description: 跳转到处理专题问答页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 专题问答id
	 * @return  String<BR>
	 */
	@RequestMapping("toDealWithThematicAsk")
	public String toDealWithThematicAsk(HttpServletRequest request,Integer id){
		try {
			logger.info("根据id"+id+"开始查询专题问答详情");
			AskDetailBean thematicAsk = manageThematicAskService.getThematicAskById(id);
			request.setAttribute("thematicAsk", JSONObject.fromObject(thematicAsk));
			logger.info("根据id"+id+"结束查询专题问答详情");
		} catch (Exception e) {
			logger.warn(ManageThematicAskAction.class,e);
		}
		return "thematicAsk/dealWithThematicAsk";
	}
	
	/**
	 * Method name: getAllAnswers <BR>
	 * Description: 获取该专题问答的所有回答 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param questionId 问题id
	 * @return  Object<BR>
	 */
	@RequestMapping("getAllAnswers")
	@ResponseBody
	public Object getAllAnswers(HttpServletRequest request,Integer questionId){
		try {
			logger.info("开始查询专题问答"+questionId+"的所有回答");
			List<AskAnswerBean> answers = manageThematicAskService.getAllAnswers(questionId);
			logger.info("结束查询专题问答"+questionId+"的所有回答");
			return answers;
		} catch (Exception e) {
			logger.warn(ManageThematicAskAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: shielAnswer <BR>
	 * Description: 屏蔽回答 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 回答id
	 * @return  Object<BR>
	 */
	@RequestMapping("shielAnswer")
	@ResponseBody
	public Object shielAnswer(HttpServletRequest request,Integer id){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("开始屏蔽回答"+id);
			manageThematicAskService.shielAnswer(id);
			logger.info("完成屏蔽回答"+id);
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(ManageThematicAskAction.class,e);
		}
		return data;
	}
	
	/**
	 * Method name: showAnswer <BR>
	 * Description: 解除屏蔽问答 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 回答id
	 * @return  Object<BR>
	 */
	@RequestMapping("showAnswer")
	@ResponseBody
	public Object showAnswer(HttpServletRequest request,Integer id){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("开始解除屏蔽回答"+id);
			manageThematicAskService.showAnswer(id);
			logger.info("完成解除屏蔽回答"+id);
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(ManageThematicAskAction.class,e);
		}
		return data;
	}
	
	/**
	 * Method name: setSatisfactoryAnswer <BR>
	 * Description: 采为满意答案 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 回答id
	 * @return  Object<BR>
	 */
	@RequestMapping("setSatisfactoryAnswer")
	@ResponseBody
	public Object setSatisfactoryAnswer(HttpServletRequest request,Integer id){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("开始采为满意答案"+id);
			manageThematicAskService.setSatisfactoryAnswer(id);
			logger.info("完成采为满意答案"+id);
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(ManageThematicAskAction.class,e);
		}
		return data;
	}
	
}
