/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PostManagementDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-12        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.vo.CoursePromotionPath;
import com.jftt.wifi.bean.vo.PostCourseVo;

/**
 * class name:PostManagementDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-12
 * @author JFTT)HeTianrui
 */
public interface ManagementPostDaoMapper {
	
    /**
     * Method name: getById <BR>
     * Description: getById <BR>
     * Remark: <BR>
     * @param path
     * @return  int<BR>
     */
	PostCourseVo getById(@Param("id")Integer id)throws Exception;
    /**
     * Method name: countByCondition <BR>
     * Description: countByCondition <BR>
     * Remark: <BR>
     * @param path
     * @return  int<BR>
     */
	int pathCountByCondition(CoursePromotionPath path)throws Exception;
    /**
     * Method name: queryPromotionPathByCondition <BR>
     * Description: queryPromotionPathByCondition <BR>
     * Remark: <BR>
     * @param path
     * @return  List<CoursePromotionPath><BR>
     */
	List<CoursePromotionPath> queryPromotionPathByCondition(CoursePromotionPath path)throws Exception;
	/**
	 * Method name: courseCountByCondition <BR>
	 * Description: courseCountByCondition <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  int<BR>
	 */
	int courseCountByCondition(long categoryId)throws Exception;
	/**
	 * Method name: queryCourseBycategoryId <BR>
	 * Description: queryCourseBycategoryId <BR>
	 * Remark: <BR>
	 * @param postCourseVo
	 * @return  List<PostCourseVo><BR>
	 */
	List<PostCourseVo> queryCourseBycategoryId(PostCourseVo postCourseVo)throws Exception;
	
	/**
	 * Method name: checkPostCourse <BR>
	 * Description: 检查课程是否重复 <BR>
	 * Remark: <BR>
	 * @param obj
	 * @throws Exception  void<BR>
	 */
	int checkPostCourse(PostCourseVo obj)throws Exception;
	/**
	 * Method name: addPostCourseRelation <BR>
	 * Description: addPostCourseRelation <BR>
	 * Remark: <BR>
	 * @param obj
	 * @throws Exception  void<BR>
	 */
	void addPostCourseRelation(PostCourseVo obj)throws Exception;
	/**
	 * Method name: isInPath <BR>
	 * Description: isInPath <BR>
	 * Remark: <BR>
	 * @param obj
	 * @throws Exception  Integer<BR>
	 */

	Integer isInPath(@Param("postId")Integer postId) throws Exception;
	
	/**
	 * Method name: delPostCourseRelation <BR>
	 * Description: delPostCourseRelation <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	void delPostCourseRelation(long id)throws Exception;
	/**
	 * Method name: queryCourseBycondition <BR>
	 * Description: queryCourseBycondition <BR>
	 * Remark: <BR>
	 * @param postCourseVo
	 * @return
	 * @throws Exception  List<PostCourseVo><BR>
	 */
	List<PostCourseVo> queryCourseBycondition(PostCourseVo postCourseVo)throws Exception;
	/**
	 * Method name: postCourseCountByCondition <BR>
	 * Description: postCourseCountByCondition <BR>
	 * Remark: <BR>
	 * @param postCourseVo
	 * @return
	 * @throws Exception  int<BR>
	 */
	int postCourseCountByCondition(PostCourseVo postCourseVo)throws Exception;

}
