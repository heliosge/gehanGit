/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseLearningMapDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-4        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.CoursePromotionPath;
import com.jftt.wifi.bean.vo.CoursePromotionPathStage;


/**
 * class name:CourseLearningMapDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-4
 * @author JFTT)HeTianrui
 */
public interface CourseLearningMapDaoMapper {
    /**
     * Method name: queryLearningMapInfoByUserId <BR>
     * Description: queryLearningMapInfoByUserId <BR>
     * Remark: <BR>
     * @param userId
     * @return  List<CoursePromMapInfo><BR>
     */
	List<CoursePromotionPath> queryLearningMapInfoByUserId(CoursePromotionPath path)throws Exception;
    /**
     * Method name: queryCurrentStageInfoByStageId <BR>
     * Description: queryCurrentStageInfoByStageId <BR>
     * Remark: <BR>
     * @param stage
     * @return
     * @throws Exception  List<CoursePromotionPathStage><BR>
     */
	List<CoursePromotionPathStage> queryCurrentStageInfoByStageId(CoursePromotionPathStage stage)throws Exception;
	/**
	 * Method name: countByCondition <BR>
	 * Description: countByCondition <BR>
	 * Remark: <BR>
	 * @param path
	 * @return  int<BR>
	 */
	int countByCondition(CoursePromotionPath path)throws Exception;
	/**
	 * Method name: queryFindMoreInfoByUserId <BR>
	 * Description: queryFindMoreInfoByUserId <BR>
	 * Remark: <BR>
	 * @param path
	 * @return  List<CoursePromotionPath><BR>
	 */
	List<CoursePromotionPath> queryFindMoreInfoByUserId(CoursePromotionPath path)throws Exception;

}
