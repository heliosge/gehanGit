/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PostManagementService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-12        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.service;

import com.jftt.wifi.bean.vo.CoursePromotionPath;
import com.jftt.wifi.bean.vo.PostCourseVo;
import com.jftt.wifi.bean.vo.PostVo;
import com.jftt.wifi.util.Pagination;

/**
 * class name:PostManagementService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-12
 * @author JFTT)HeTianrui
 */
public interface ManagementPostService {
    /**
     * Method name: queryPromotionPathByCondition <BR>
     * Description: queryPromotionPathByCondition <BR>
     * Remark: <BR>
     * @param path
     * @return
     * @throws Exception  Pagination<CoursePromotionPath><BR>
     */
	Pagination<CoursePromotionPath> queryPromotionPathByCondition(CoursePromotionPath path)throws Exception;
    /**
     * Method name: queryCourseBycategoryId <BR>
     * Description: queryCourseBycategoryId <BR>
     * Remark: <BR>
     * @param postCourseVo
     * @return  Pagination<PostCourseVo><BR>
     */
	Pagination<PostCourseVo> queryCourseBycategoryId(PostCourseVo postCourseVo)throws Exception;
	/**
	 * Method name: addPostCourseRelation <BR>
	 * Description: addPostCourseRelation <BR>
	 * Remark: <BR>
	 * @param post  void<BR>
	 */
	void addPostCourseRelation(PostVo post)throws Exception;
	/**
	 * Method name: delPostCourseRelation <BR>
	 * Description: delPostCourseRelation <BR>
	 * Remark: <BR>
	 * @param ids
	 * @throws Exception  void<BR>
	 */
	boolean delPostCourseRelation(String ids)throws Exception;
	/**
	 * Method name: queryCourseBycondition <BR>
	 * Description: queryCourseBycondition <BR>
	 * Remark: <BR>
	 * @param postCourseVo
	 * @return
	 * @throws Exception  Pagination<PostCourseVo><BR>
	 */
	Pagination<PostCourseVo> queryCourseBycondition(PostCourseVo postCourseVo)throws Exception;

}
