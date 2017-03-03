/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagService.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2014/01/07           |  JFTT)wangjian     |  original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageGroupBean;
import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.ResCourseCategoryBean;
import com.jftt.wifi.bean.ResCourseTypeBean;
import com.jftt.wifi.bean.ResCoursewareBean;
import com.jftt.wifi.bean.ResSectionBean;
import com.jftt.wifi.bean.ResSectionCoursewareBean;
import com.jftt.wifi.bean.ResSectionExamBean;
import com.jftt.wifi.bean.SectionExamBean;
import com.jftt.wifi.bean.vo.CourseVo;

/**
 * ManageService.java
 */
public interface ResService {
	/**
	 * Method name: selectCourseware <BR>
	 * Description: 根据条件查询课件 <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  List<ResCoursewareBean><BR>
	 */
	public List<ResCoursewareBean> selectCoursewareList(Map<String, Object> param) throws Exception;
	
	/**
	 * Method name: selectCoursewareCount <BR>
	 * Description: 根据条件查询课件数量 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int selectCoursewareCount(Map<String, Object> param) throws Exception;
	
	/**
	 * Method name: selectById <BR>
	 * Description: 根据Id查询课件 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  ResCoursewareBean<BR>
	 */
	public ResCoursewareBean selectCoursewareById(Integer id) throws Exception;
	
	/**
	 * Method name: insert <BR>
	 * Description: 新增课件 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	public void insertCourseware(ResCoursewareBean bean) throws Exception;

	/**
	 * Method name: update <BR>
	 * Description: 修改课件 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	void updateCourseware(ResCoursewareBean bean) throws Exception;

	/**
	 * Method name: delete <BR>
	 * Description: 删除课件 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	void deleteCourseware(Integer id) throws Exception;

	/**
	 * Method name: spreadResCourse <BR>
	 * Description: 推广课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void spreadResCourse(String id) throws Exception ;

	/**
	 * Method name: cancelSpreadResCourse <BR>
	 * Description: 取消推广课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void cancelSpreadResCourse(String id) throws Exception ;

	/**
	 * Method name: insertCourse <BR>
	 * Description: 新增课程 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return 
	 * @throws Exception  void<BR>
	 */
	public Integer insertCourse(ResCourseBean bean) throws Exception ;

	/**
	 * Method name: updateCourse <BR>
	 * Description: 修改课程 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	public void updateCourse(ResCourseBean bean) throws Exception ;

	/**
	 * Method name: deleteCourse <BR>
	 * Description: 删除课程 <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @throws Exception  void<BR>
	 */
	public void deleteCourse(Integer parseInt) throws Exception ;

	/**
	 * Method name: insertCourseAndOpenCrowd <BR>
	 * Description: 新增课程开发人群 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param ids 
	 * @param type 开放类型
	 * @throws Exception  void<BR>
	 */
	public void insertCourseAndOpenCrowd(Integer courseId, String[] ids,
			Integer type) throws Exception ;

	/**
	 * Method name: deleteCourseAndOpenCrowd <BR>
	 * Description: 删除课程开发人群 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	public void deleteCourseAndOpenCrowd(Integer id) throws Exception ;

	/**
	 * Method name: releaseResCourse <BR>
	 * Description: 发布课程 <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @throws Exception  void<BR>
	 */
	public void releaseCourse(Integer parseInt) throws Exception ;
	
	/**
	 * Method name: cancelReleaseCourse <BR>
	 * Description: 取消发布课程 <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @throws Exception  void<BR>
	 */
	public void cancelReleaseCourse(Integer id) throws Exception;
	
	/**
	 * Method name: shareCourse <BR>
	 * Description: 共享课程 <BR>
	 * Remark: <BR>
	 * @param param
	 * @throws Exception  void<BR>
	 */
	public void shareCourse(Map<String, Object> param) throws Exception ;

	public List<ResCourseBean> selectCourseList(CourseVo vo) throws Exception ;

	/**
	 * Method name: insertResSection <BR>
	 * Description: 新增章节 <BR>
	 * Remark: <BR>
	 * @param section  void<BR>
	 */
	public Integer insertSection(ResSectionBean section)throws Exception;

	/**
	 * Method name: insertSectionCourseware <BR>
	 * Description: 新增章节、课件关系 <BR>
	 * Remark: <BR>
	 * @param voBean
	 * @throws Exception  void<BR>
	 */
	public void insertSectionCourseware(ResSectionCoursewareBean voBean) throws Exception;

	/**
	 * Method name: insertSectionExam <BR>
	 * Description: 新增课件、试卷关系 <BR>
	 * Remark: <BR>
	 * @param voBean
	 * @throws Exception  void<BR>
	 */
	public void insertSectionExam(ResSectionExamBean voBean) throws Exception;

	/**
	 * Method name: deleteSection <BR>
	 * Description: 删除章节 <BR>
	 * Remark: <BR>
	 * @param sectionId
	 * @throws Exception  void<BR>
	 */
	public void deleteSection(String sectionId) throws Exception;

	/**
	 * Method name: deleteResSectionCourseware <BR>
	 * Description: 删除章节、课件关系 <BR>
	 * Remark: <BR>
	 * @throws Exception  void<BR>
	 */
	public void deleteSectionCourseware(ResSectionCoursewareBean vo) throws Exception;

	/**
	 * Method name: deleteSectionExam <BR>
	 * Description: 删除章节、试卷关系 <BR>
	 * Remark: <BR>
	 * @throws Exception  void<BR>
	 */
	void deleteSectionExam(ResSectionExamBean vo) throws Exception;

	/**
	 * Method name: selectCourseCategory <BR>
	 * Description: 查询【课程体系】 <BR>
	 * Remark: <BR>
	 * @param param 
	 * @return  List<ResCourseCategoryBean><BR>
	 */
	public List<ResCourseCategoryBean> selectCourseCategory(Map<String, Object> param) throws Exception;

	/**
	 * Method name: deleteCourseCategory <BR>
	 * Description: 删除【课程体系】 <BR>
	 * Remark: <BR>
	 * @param parseInt  void<BR>
	 */
	public void deleteCourseCategory(int parseInt) throws Exception;

	/**
	 * Method name: updateCourseCategory <BR>
	 * Description: 修改【课程体系】 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	public void updateCourseCategory(ResCourseCategoryBean record) throws Exception;

	/**
	 * Method name: insertCourseCategory <BR>
	 * Description: 添加【课程体系】 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	public void insertCourseCategory(ResCourseCategoryBean record) throws Exception;

	/**
	 * Method name: selectCourseType <BR>
	 * Description: 查询【课程分类】 <BR>
	 * Remark: <BR>
	 * @param param 
	 * @return  List<ResCourseTypeBean><BR>
	 */
	public List<ResCourseTypeBean> selectCourseType(Map<String, Object> param) throws Exception;

	/**
	 * Method name: deleteCourseType <BR>
	 * Description: 删除【课程分类】 <BR>
	 * Remark: <BR>
	 * @param parseInt  void<BR>
	 */
	public void deleteCourseType(int parseInt) throws Exception;

	/**
	 * Method name: updateCourseType <BR>
	 * Description: 修改【课程分类】 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	public void updateCourseType(ResCourseTypeBean record) throws Exception;

	/**
	 * Method name: insertCourseType <BR>
	 * Description: 添加【课程分类】 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	public void insertCourseType(ResCourseTypeBean record) throws Exception;

	/**
	 * Method name: selectCourseListCount <BR>
	 * Description: 查询课程数量 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int selectCourseListCount(CourseVo vo) throws Exception;

	/**
	 * Method name: changeCategory <BR>
	 * Description: 更改课程体系 <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @param categoryId
	 * @throws Exception  void<BR>
	 */
	public void changeCategory(Map<String, Object> map)throws Exception;

	/**
	 * Method name: selectSectionAndExam <BR>
	 * Description: selectSectionAndExam <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @return  List<PaperBean><BR>
	 */
	public List<SectionExamBean> selectSectionAndExam(Map<String, Object> param);

	/**
	 * Method name: selectSectionAndCourseware <BR>
	 * Description: selectSectionAndCourseware <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @return  List<ResCoursewareBean><BR>
	 */
	public List<ResCoursewareBean> selectSectionAndCourseware(Map<String, Object> param);

	/**
	 * Method name: selectExamList <BR>
	 * Description: selectExamList <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<SectionExamBean><BR>
	 */
	public List<SectionExamBean> selectExamList(Map<String, Object> param);
	
	/**
	 * Method name: selectExamListCount <BR>
	 * Description: selectExamListCount <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  int<BR>
	 */
	public int selectExamListCount(Map<String, Object> param);

	/**
	 * Method name: selectCourseById <BR>
	 * Description: 根据课程id获取课程 <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @return  ResCourseBean<BR>
	 */
	public ResCourseBean selectCourseById(int parseInt) throws Exception;

	/**
	 * Method name: selectCourseOpenGroupByCourseId <BR>
	 * Description: 获取课程开放群组 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  List<ManageGroupBean><BR>
	 */
	public List<ManageGroupBean> selectCourseOpenGroupByCourseId(Integer id) throws Exception;
	
	/**
	 * Method name: selectCourseOpenDeptByCourseId <BR>
	 * Description: 获取课程开放部门 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  List<ManageDepartmentBean><BR>
	 */
	public List<ManageDepartmentBean> selectCourseOpenDeptByCourseId(Integer id) throws Exception;

	/**
	 * Method name: selectCourseOpenPostByCourseId <BR>
	 * Description: 获取课程开放岗位 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  List<ManagePostBean><BR>
	 */
	public List<ManagePostBean> selectCourseOpenPostByCourseId(Integer id) throws Exception;

	/**
	 * Method name: selectSectionByCourseId <BR>
	 * Description: 根据课程获取章节id列表 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return  List<Integer><BR>
	 */
	public List<ResSectionBean> selectSectionByCourseId(String courseId) throws Exception;

	/**
	 * Method name: updateSection <BR>
	 * Description: 修改章节 <BR>
	 * Remark: <BR>
	 * @param section
	 * @throws Exception  void<BR>
	 */
	public void updateSection(ResSectionBean section) throws Exception;

	/**
	 * Method name: insertCourseType <BR>
	 * Description: 修改课程分类 <BR>
	 * Remark: <BR>
	 * @param map  void<BR>
	 */
	public void changeCourseType(Map<String, Object> map);

	/**
	 * Method name: uploadFile <BR>
	 * Description: 上传课件文件 <BR>
	 * Remark: <BR>
	 * @param file
	 * @param commonPath
	 * @return  String<BR>
	 */
	public String uploadFile(MultipartFile file, String commonPath);

	/**
	 * Method name: featurAndUnFeaturResCourse <BR>
	 * Description: 精选、取消精选课程 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void featurAndUnFeaturResCourse(ResCourseBean bean) throws Exception ;

	/**
	 * Method name: selectShareResCourseList <BR>
	 * Description: 查询共享课程 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<ResCourseBean><BR>
	 */
	public List<ResCourseBean> selectShareResCourseList(CourseVo vo) throws Exception;
	
	/**
	 * Method name: selectCourseTypeByCourseId <BR>
	 * Description: 根据课程id获取课程分类 <BR>
	 * Remark: <BR>
	 * @param map
	 * @return  List<Map<String,Object>><BR>
	 */
	public List<Map<String,Object>> selectCourseTypeByCourseId(Map<String, Object> map);

	/**
	 * Method name: deleteCourseType <BR>
	 * Description: 根据课程id删除课程分类 <BR>
	 * Remark: <BR>
	 * @param map  void<BR>
	 */
	public void deleteCourseTypeByCourseId(Map<String, Object> map);
	
/** zhangbocheng start*/
	
	public List<ResCourseBean> selectCourseListByPostId(CourseVo vo)
			throws Exception ;
	
	public int selectCourseListCountByPostId(CourseVo vo)
			throws Exception ;
	/** zhangbocheng end*/

	



}
