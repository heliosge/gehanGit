/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseLearningMapAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-31        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jftt.wifi.bean.vo.CoursePromotionPath;
import com.jftt.wifi.bean.vo.CoursePromotionPathStage;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.CourseLearningMapService;
import com.jftt.wifi.util.Pagination;

/**
 * class name:CourseLearningMapAction <BR>
 * class description:学习地图 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-31
 * @author JFTT)HeTianrui
 */
@Controller
@RequestMapping("map")
public class CourseLearningMapAction {
	
	private static Logger logger = Logger.getLogger(CourseLearningMapAction.class);
	@Resource 
	private CourseLearningMapService courseLearningMapServ;
    /**
     * Method name: queryLearningMapInfo <BR>
     * Description: 学习地图 <BR>
     * Remark: <BR>
     * @param model
     * @param request
     * @return  String<BR>
     */
	@RequestMapping(value="show")
	public String queryLearningMapInfo(ModelMap model,@ModelAttribute("path")CoursePromotionPath path,HttpServletRequest request){
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			if(StringUtils.hasText(userId))
			{
				logger.info("USERID=/="+userId+"====Go queryLearningMapInfo====");
				path.setUserId(Integer.valueOf(userId));
				List<CoursePromotionPath> list =courseLearningMapServ.queryLearningMapInfoByUserId(path);
				model.addAttribute("list", list);
			}
		} catch (Exception e) {
			logger.error("学习地图首页异常!", e);
		}
		return "course/map_show";
	}
	/**
	 * Method name: findPath <BR>
	 * Description: 查看路径 <BR>
	 * Remark: <BR>
	 * @param model
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="/path/{stageId}")
	public String findPath(@PathVariable("stageId") long stageId,CoursePromotionPathStage stage,
			ModelMap model,HttpServletRequest request){
		try {
				String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
				if(StringUtils.hasText(userId))
				{
					logger.info("====Go findPath====stageId::"+stageId);
					stage.setUserId(Integer.valueOf(userId));
					stage.setStageId(Integer.valueOf(stageId+""));
				    List<CoursePromotionPathStage> list =courseLearningMapServ.queryCurrentStageInfoByStageId(stage);
				    model.addAttribute("list", list);
				}
		    } catch (Exception e) {
			logger.error("查看当前阶段异常!", e);
		 }
		return "course/map_path";
	}
	/**
	 * Method name: findMore <BR>
	 * Description: 查看更多 <BR>
	 * Remark: <BR>
	 * @param model
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="more")
	public String findMore(ModelMap model,@ModelAttribute("path")CoursePromotionPath path,HttpServletRequest request){
			try {
				String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
				if(StringUtils.hasText(userId))
				{
					logger.info("USERID=/="+userId+"====Go findMore====");
					path.setUserId(Integer.valueOf(userId));
					Pagination<CoursePromotionPath> page=courseLearningMapServ.queryFindMoreInfoByUserId(path);
					model.addAttribute("page", page);
				}
			} catch (Exception e) {
				logger.error("发现更多异常!", e);
			}
			return "course/map_more";
		}
}
