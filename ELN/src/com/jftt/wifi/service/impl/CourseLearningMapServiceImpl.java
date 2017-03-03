/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseLearningMapServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-31        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.CoursePromotionPath;
import com.jftt.wifi.bean.vo.CoursePromotionPathStage;
import com.jftt.wifi.dao.CourseLearningMapDaoMapper;
import com.jftt.wifi.service.CourseLearningMapService;
import com.jftt.wifi.util.Pagination;

/**
 * class name:CourseLearningMapServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-31
 * @author JFTT)HeTianrui
 */
@Service
public class CourseLearningMapServiceImpl implements CourseLearningMapService{

	@Autowired
	private CourseLearningMapDaoMapper courseLearningMapDaoMapper;
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseLearningMapService#queryLearningMapInfoByUserId(com.jftt.wifi.bean.vo.CoursePromotionPath) <BR>
	 * Method name: queryLearningMapInfoByUserId <BR>
	 * Description: 根据用户id查询学习地图 <BR>
	 * Remark: <BR>
	 * @param path 路径
	 * @return List<CoursePromotionPath>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<CoursePromotionPath> queryLearningMapInfoByUserId(CoursePromotionPath path) throws Exception{
		return courseLearningMapDaoMapper.queryLearningMapInfoByUserId(path);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseLearningMapService#queryCurrentStageInfoByStageId(com.jftt.wifi.bean.vo.CoursePromotionPathStage) <BR>
	 * Method name: queryCurrentStageInfoByStageId <BR>
	 * Description: 查询当前学习路径 <BR>
	 * Remark: <BR>
	 * @param stage 路径
	 * @return List<CoursePromotionPathStage>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<CoursePromotionPathStage> queryCurrentStageInfoByStageId(CoursePromotionPathStage stage)throws Exception {
		return courseLearningMapDaoMapper.queryCurrentStageInfoByStageId(stage);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseLearningMapService#queryFindMoreInfoByUserId(com.jftt.wifi.bean.vo.CoursePromotionPath) <BR>
	 * Method name: queryFindMoreInfoByUserId <BR>
	 * Description: 发现更多 <BR>
	 * Remark: <BR>
	 * @param path 路径
	 * @return Pagination<CoursePromotionPath>
	 * @throws Exception  <BR>
	 */
	@Override
	public Pagination<CoursePromotionPath> queryFindMoreInfoByUserId(
			CoursePromotionPath path)throws Exception {
		int total = courseLearningMapDaoMapper.countByCondition(path);
		int pageStartSize = (path.getPageIndex()-1) * path.getPageSize();
		path.setPageStartSize(pageStartSize);
		List<CoursePromotionPath> list=courseLearningMapDaoMapper.queryFindMoreInfoByUserId(path);
		Pagination<CoursePromotionPath> page = new Pagination<CoursePromotionPath>(list, total, path.getPageSize(),path.getPageIndex());
		return page;
	}

}
