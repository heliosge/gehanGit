/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PostManagementServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-12        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.vo.CoursePromotionPath;
import com.jftt.wifi.bean.vo.PostCourseVo;
import com.jftt.wifi.bean.vo.PostVo;
import com.jftt.wifi.dao.ManagementLearningMapDaoMapper;
import com.jftt.wifi.dao.ManagementPostDaoMapper;
import com.jftt.wifi.service.ManagementPostService;
import com.jftt.wifi.util.Pagination;

/**
 * class name:PostManagementServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-12
 * @author JFTT)HeTianrui
 */
@Service
public class ManagementPostServiceImpl  implements ManagementPostService{

	@Autowired
	private ManagementPostDaoMapper postManagementDaoMapper;
	@Autowired
	private ManagementLearningMapDaoMapper managementLearningMapDaoMapper;

	@Override
	public Pagination<CoursePromotionPath> queryPromotionPathByCondition(
			CoursePromotionPath path) throws Exception {
		int total =postManagementDaoMapper.pathCountByCondition(path);
		int pageStartSize = (path.getPageIndex()-1) * path.getPageSize();
		path.setPageStartSize(pageStartSize);
		List<CoursePromotionPath> list =postManagementDaoMapper.queryPromotionPathByCondition(path);
		Pagination<CoursePromotionPath> page =new Pagination<CoursePromotionPath>(list, total, path.getPageSize(),path.getPageIndex());
		return page;
	}

	@Override
	public Pagination<PostCourseVo> queryCourseBycategoryId(PostCourseVo postCourseVo) throws Exception{
		long categoryId=postCourseVo.getCategoryId();
		int total =postManagementDaoMapper.courseCountByCondition(categoryId);
		int pageStartSize = (postCourseVo.getPageIndex()-1) * postCourseVo.getPageSize();
		postCourseVo.setPageStartSize(pageStartSize);
		List<PostCourseVo> list =postManagementDaoMapper.queryCourseBycategoryId(postCourseVo);
		Pagination<PostCourseVo> page =new Pagination<PostCourseVo>(list, total, postCourseVo.getPageSize(),postCourseVo.getPageIndex());
		return page;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void addPostCourseRelation(PostVo post) throws Exception {
		for(PostCourseVo obj:post.getList())
		{
			obj.setPostId(post.getPostId());
			if(postManagementDaoMapper.checkPostCourse(obj)>0){
				continue;
			}
			
			postManagementDaoMapper.addPostCourseRelation(obj);
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean delPostCourseRelation(String ids) throws Exception {
		String[] dbids=ids.split(",");
		for(String dbid:dbids)
		{
			long id=Long.valueOf(dbid);
			//判断岗位是否关联了路径
			if(postManagementDaoMapper.isInPath(Integer.parseInt(dbid))>0){
				PostCourseVo vo =postManagementDaoMapper.getById(Integer.parseInt(dbid));
				Integer credits = managementLearningMapDaoMapper.getTotalCredits(vo.getCourseType(), vo.getPostId());
				Integer maxCredits =managementLearningMapDaoMapper.getMAXCredits(vo.getCourseType(), vo.getPostId());
				int score = vo.getLearnScore()==null?0:vo.getLearnScore();
				if((credits-score)<maxCredits){
					if(dbids.length==1){
						return false;
					}
					continue;
				}
				
			}
			postManagementDaoMapper.delPostCourseRelation(id);
		}
		return true;
	}

	@Override
	public Pagination<PostCourseVo> queryCourseBycondition(
			PostCourseVo postCourseVo) throws Exception {
		int total =postManagementDaoMapper.postCourseCountByCondition(postCourseVo);
		int pageStartSize = (postCourseVo.getPageIndex()-1) * postCourseVo.getPageSize();
		postCourseVo.setPageStartSize(pageStartSize);
		List<PostCourseVo> list =postManagementDaoMapper.queryCourseBycondition(postCourseVo);
		Pagination<PostCourseVo> page =new Pagination<PostCourseVo>(list, total, postCourseVo.getPageSize(),postCourseVo.getPageIndex());
		return page;
	}
	
}
