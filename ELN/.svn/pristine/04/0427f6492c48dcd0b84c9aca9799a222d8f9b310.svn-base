/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseLearningMapService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-31        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.CoursePromotionPath;
import com.jftt.wifi.bean.vo.CoursePromotionPathStage;
import com.jftt.wifi.util.Pagination;

/**
 * class name:CourseLearningMapService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-31
 * @author JFTT)HeTianrui
 */
public interface CourseLearningMapService {
    /**
     * Method name: queryLearningMapInfo <BR>
     * Description: queryLearningMapInfo <BR>
     * Remark: <BR>
     * @param userBean
     * @return  List<?><BR>
     */
	List<CoursePromotionPath> queryLearningMapInfoByUserId(CoursePromotionPath path)throws Exception;
    /**
     * Method name: queryCurrentStageInfoByStageId <BR>
     * Description: queryCurrentStageInfoByStageId <BR>
     * Remark: <BR>
     * @param stageId
     * @return  List<?><BR>
     */
	List<CoursePromotionPathStage> queryCurrentStageInfoByStageId(CoursePromotionPathStage stage)throws Exception;
	/**
	 * Method name: queryFindMoreInfoByUserId <BR>
	 * Description: queryFindMoreInfoByUserId <BR>
	 * Remark: <BR>
	 * @param path
	 * @return  Pagination<CoursePromotionPath><BR>
	 */
	Pagination<CoursePromotionPath> queryFindMoreInfoByUserId(CoursePromotionPath path)throws Exception;

}
