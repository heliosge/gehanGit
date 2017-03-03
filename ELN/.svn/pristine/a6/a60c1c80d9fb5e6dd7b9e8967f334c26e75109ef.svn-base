/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ResServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月30日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.zeroturnaround.zip.ZipUtil;

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
import com.jftt.wifi.bean.exam.vo.ExamOrganizingRuleVo;
import com.jftt.wifi.bean.vo.CourseVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.ExamPaperDaoMapper;
import com.jftt.wifi.dao.ManageDepartmentDaoMapper;
import com.jftt.wifi.dao.ManageGroupDaoMapper;
import com.jftt.wifi.dao.ManagePostDaoMapper;
import com.jftt.wifi.dao.ResCourseDaoMapper;
import com.jftt.wifi.dao.ResCoursewareDaoMapper;
import com.jftt.wifi.service.MyExamManageService;
import com.jftt.wifi.service.ResService;
import com.jftt.wifi.util.ConvertUtil;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.PropertyUtil;
import com.jftt.wifi.util.ScormParser;

/**
 * class name:ResServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月30日
 * @author JFTT)Lu Yunlong
 */
/**
 * class name:ResServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2016年1月28日
 * @author JFTT)Lu Yunlong
 */
@Service("resService")
public class ResServiceImpl implements ResService {
	@Resource
	private ResCoursewareDaoMapper resCoursewareDao;

	@Resource
	private ResCourseDaoMapper resCourseDao;
	
	@Resource
	private ExamPaperDaoMapper examPaperDaoMapper;
	
	@Resource
	private ManageDepartmentDaoMapper manageDeptDaoMapper;
	
	@Resource
	private ManagePostDaoMapper managePostDaoMapper;
	
	@Resource
	private ManageGroupDaoMapper manageGroupDaoMapper;
	
	@Autowired
	private MyExamManageService myExamManageService;
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#selectCourseware(java.util.Map) <BR>
	 * Method name: selectCourseware <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	 */

	@Override
	public List<ResCoursewareBean> selectCoursewareList(Map<String, Object> param)
			throws Exception {
		return resCoursewareDao.select(param);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#selectCoursewareCount(java.util.Map) <BR>
	 * Method name: selectCoursewareCount <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public int selectCoursewareCount(Map<String, Object> param)
			throws Exception {
		return resCoursewareDao.selectCount(param);
	}


	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#selectById(java.lang.Integer) <BR>
	 * Method name: selectById <BR>
	 * Description: 根据Id查询课件 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public ResCoursewareBean selectCoursewareById(Integer id) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		List<ResCoursewareBean> list = selectCoursewareList(param);
		if(list != null && list.size() > 0 )
			return list.get(0);
		return null;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#insert(com.jftt.wifi.bean.ResCoursewareBean) <BR>
	 * Method name: insert <BR>
	 * Description: 新增课件 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  <BR>
	*/
	@Override
	public void insertCourseware(ResCoursewareBean bean) throws Exception {
		//转义时长
		if(!"".equals(bean.getDurationString()) && bean.getDurationString() != null){
			String[] durations = bean.getDurationString().split(":");
			bean.setDuration(Integer.parseInt(("".equals(durations[0])?"0":durations[0]))*3600 +Integer.parseInt(durations[1])*60+Integer.parseInt(durations[2]));
		}
		resCoursewareDao.insert(bean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#update(com.jftt.wifi.bean.ResCoursewareBean) <BR>
	 * Method name: update <BR>
	 * Description: 修改课件 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateCourseware(ResCoursewareBean bean) throws Exception {
		//转义时长
		if(!"".equals(bean.getDurationString()) && bean.getDurationString() != null){
			String[] durations = bean.getDurationString().split(":");
			bean.setDuration(Integer.parseInt(("".equals(durations[0])?"0":durations[0]))*3600 +Integer.parseInt(durations[1])*60+Integer.parseInt(durations[2]));
		}
		resCoursewareDao.update(bean);
	}
	
	/**
	 * Method name: delete <BR>
	 * Description: 删除课件 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	@Override
	public void deleteCourseware(Integer id) throws Exception {
		resCoursewareDao.delete(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#spreadResCourse(java.lang.String) <BR>
	 * Method name: spreadResCourse <BR>
	 * Description: 推广鹅口疮 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	public void spreadResCourse(String id) throws Exception  {
		resCourseDao.spreadResCourse(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#cancelSpreadResCourse(java.lang.String) <BR>
	 * Method name: cancelSpreadResCourse <BR>
	 * Description: 取消推广课程 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	public void cancelSpreadResCourse(String id) throws Exception  {
		resCourseDao.cancelSpreadResCourse(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#insertCourse(com.jftt.wifi.bean.ResCourseBean) <BR>
	 * Method name: insertCourse <BR>
	 * Description: 新增课程 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public Integer insertCourse(ResCourseBean bean) throws Exception {
		resCourseDao.insertCourse(bean);
		return bean.getId();
		
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#updateCourse(com.jftt.wifi.bean.ResCourseBean) <BR>
	 * Method name: updateCourse <BR>
	 * Description: 修改课程 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateCourse(ResCourseBean bean) throws Exception {
		resCourseDao.updateCourse(bean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#deleteCourse(java.lang.Integer) <BR>
	 * Method name: deleteCourse <BR>
	 * Description: 删除课程 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteCourse(Integer id) throws Exception {
		//根据课程id删除章节课件
		resCourseDao.deleteSectionCoursewareByCourseId(id);
		//根据课程id删除章节试卷
		resCourseDao.deleteSectionExamByCourseId(id);
		//根据课程id删除章节
		ResSectionBean bean = new ResSectionBean();
		bean.setCourseId(id);
		resCourseDao.deleteSection(bean);
		//删除课程
		resCourseDao.deleteCourse(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#insertCourseAndOpenCrowd(java.lang.Integer, java.lang.String[], java.lang.Integer) <BR>
	 * Method name: insertCourseAndOpenCrowd <BR>
	 * Description: 新增课程开放人权 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param ids
	 * @param type
	 * @throws Exception  <BR>
	*/
	@Override
	public void insertCourseAndOpenCrowd(Integer courseId, String[] ids,
			Integer type) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("courseId", courseId);
		param.put("openType", type);
		if(ids == null){
			resCourseDao.insertCourseAndOpenCrowd(param);
		}else{
			for(String crowId : ids){
				param.put("crowId", crowId);
				resCourseDao.insertCourseAndOpenCrowd(param);
			}
		}
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#deleteCourseAndOpenCrowd(java.lang.Integer) <BR>
	 * Method name: deleteCourseAndOpenCrowd <BR>
	 * Description: 删除课程开放人员 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @throws Exception  <BR>
	*/
	@Override
	public void deleteCourseAndOpenCrowd(Integer courseId) throws Exception {
		resCourseDao.deleteCourseAndOpenCrowd(courseId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#releaseCourse(java.lang.Integer) <BR>
	 * Method name: releaseCourse <BR>
	 * Description: 发布课程 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @throws Exception  <BR>
	*/
	@Override
	public void releaseCourse(Integer courseId) throws Exception {
		resCourseDao.releaseCourse(courseId);
	}
	
	/**
	 * Method name: cancelReleaseCourse <BR>
	 * Description: 取消发布课程 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @throws Exception  void<BR>
	 */
	@Override
	public void cancelReleaseCourse(Integer courseId) throws Exception {
		resCourseDao.cancelReleaseCourse(courseId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#shareCourse(java.util.Map) <BR>
	 * Method name: shareCourse <BR>
	 * Description: 共享课程 <BR>
	 * Remark: <BR>
	 * @param param
	 * @throws Exception  <BR>
	*/
	@Override
	public void shareCourse(Map<String, Object> param) throws Exception {
		resCourseDao.shareCourse(param);
	}

	@Override
	public List<ResCourseBean> selectCourseList(CourseVo vo)
			throws Exception {
		return resCourseDao.selectCourseList(vo);
	}
	
	@Override
	public int selectCourseListCount(CourseVo vo)
			throws Exception {
		return resCourseDao.selectCourseListCount(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#insertSection(com.jftt.wifi.bean.ResSectionBean) <BR>
	 * Method name: insertSection <BR>
	 * Description: 新增章节 <BR>
	 * Remark: <BR>
	 * @param section
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public Integer insertSection(ResSectionBean section) throws Exception{
		resCourseDao.insertSection(section);
		return section.getId();
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#insertSectionCourseware(com.jftt.wifi.bean.ResSectionCoursewareBean) <BR>
	 * Method name: insertSectionCourseware <BR>
	 * Description: 新增章节、课件关系 <BR>
	 * Remark: <BR>
	 * @param voBean
	 * @throws Exception  <BR>
	*/
	@Override
	public void insertSectionCourseware(ResSectionCoursewareBean voBean)
			throws Exception {
		resCourseDao.insertSectionCourseware(voBean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#insertSectionExam(com.jftt.wifi.bean.ResSectionExamBean) <BR>
	 * Method name: insertSectionExam <BR>
	 * Description: 新增章节、试卷关系 <BR>
	 * Remark: <BR>
	 * @param voBean
	 * @throws Exception  <BR>
	*/
	@Override
	public void insertSectionExam(ResSectionExamBean voBean) throws Exception {
		resCourseDao.insertSectionExam(voBean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#deleteSection(java.lang.String) <BR>
	 * Method name: deleteSection <BR>
	 * Description: 删除章节 <BR>
	 * Remark: <BR>
	 * @param sectionId
	 * @throws Exception  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteSection(String sectionId) throws Exception {
		//删除章节
		ResSectionBean bean = new ResSectionBean();
		bean.setId(Integer.parseInt(sectionId));
		resCourseDao.deleteSection(bean);
		//删除章节、课件关系
		resCourseDao.deleteSectionCourseware(new ResSectionCoursewareBean(Integer.parseInt(sectionId), null));
		//删除章节、试卷关系
		resCourseDao.deleteSectionExam(new ResSectionExamBean(Integer.parseInt(sectionId), null, null, null, null));
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#deleteResSectionCourseware(java.lang.String) <BR>
	 * Method name: deleteResSectionCourseware <BR>
	 * Description: 删除章节、课件关系 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	public void deleteSectionCourseware(ResSectionCoursewareBean vo) throws Exception {
		resCourseDao.deleteSectionCourseware(vo);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#deleteSectionExam(java.lang.String) <BR>
	 * Method name: deleteSectionExam <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	public void deleteSectionExam(ResSectionExamBean vo) throws Exception {
		resCourseDao.deleteSectionExam(vo);
	}

	@Override
	public List<ResCourseCategoryBean> selectCourseCategory(Map<String, Object> param)  throws Exception {
		return resCourseDao.selectCourseCategory(param);
	}

	@Override
	public void deleteCourseCategory(int parseInt) throws Exception {
		resCourseDao.deleteCourseCategory(parseInt);
	}

	@Override
	public void updateCourseCategory(ResCourseCategoryBean record) throws Exception {
		resCourseDao.updateCourseCategory(record);
	}

	@Override
	public void insertCourseCategory(ResCourseCategoryBean record) throws Exception {
		resCourseDao.insertCourseCategory(record);
	}

	@Override
	public List<ResCourseTypeBean> selectCourseType(Map<String, Object> param) throws Exception {
		return resCourseDao.selectCourseType(param);
	}

	@Override
	public void deleteCourseType(int parseInt) throws Exception {
		resCourseDao.deleteCourseType(parseInt);
	}

	@Override
	public void updateCourseType(ResCourseTypeBean record) throws Exception {
		resCourseDao.updateCourseType(record);
	}

	@Override
	public void insertCourseType(ResCourseTypeBean record) throws Exception {
		resCourseDao.insertCourseType(record);
	}

	@Override
	public void changeCategory(Map<String, Object> map)
			throws Exception {
		resCourseDao.changeCategory(map);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#selectSectionAndExam(int) <BR>
	 * Method name: selectSectionAndExam <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @return  <BR>
	*/
	@Override
	public List<SectionExamBean> selectSectionAndExam(Map<String, Object> param) {
		 List<SectionExamBean> list = examPaperDaoMapper.selectSectionAndCourseware(param);
		 for (SectionExamBean exam : list) {
				int paperId = exam.getExamId();
				int organizingMode = exam.getOrganizingMode();
					if(organizingMode==2){//随机组卷
						List<ExamOrganizingRuleVo> ruleVo = myExamManageService.getRuleList(paperId);
						for (ExamOrganizingRuleVo examOrganizingRuleVo : ruleVo) {
							int type = examOrganizingRuleVo.getType();
							switch (type) {
							case 1:
								exam.setCount1(examOrganizingRuleVo.getTypeCount());
								break;
							case 2:
								exam.setCount2(examOrganizingRuleVo.getTypeCount());
								break;
							case 3:
								exam.setCount3(examOrganizingRuleVo.getTypeCount());
								break;
							case 4:
								exam.setCount4(examOrganizingRuleVo.getTypeCount());
								break;
							case 5:
								exam.setCount5(examOrganizingRuleVo.getTypeCount());
								break;
							case 6:
								exam.setCount6(examOrganizingRuleVo.getTypeCount());
								break;
							case 7:
								exam.setCount7(examOrganizingRuleVo.getTypeCount());
								break;
								
							default:
								break;
							}
							
						}
					}
			}
		 return list;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#selectSectionAndCourseware(int) <BR>
	 * Method name: selectSectionAndCourseware <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @return  <BR>
	*/
	@Override
	public List<ResCoursewareBean> selectSectionAndCourseware(Map<String, Object> param) {
		return resCoursewareDao.selectSectionAndCourseware(param);
	}
	
	@Override
	public List<SectionExamBean> selectExamList(Map<String, Object> param) {
		List<SectionExamBean> list = examPaperDaoMapper.selectExamList(param);
		 for (SectionExamBean exam : list) {
				int paperId = exam.getExamId();
				int organizingMode = exam.getOrganizingMode();
					if(organizingMode==2){//随机组卷
						List<ExamOrganizingRuleVo> ruleVo = myExamManageService.getRuleList(paperId);
						for (ExamOrganizingRuleVo examOrganizingRuleVo : ruleVo) {
							int type = examOrganizingRuleVo.getType();
							switch (type) {
							case 1:
								exam.setCount1(examOrganizingRuleVo.getTypeCount());
								break;
							case 2:
								exam.setCount2(examOrganizingRuleVo.getTypeCount());
								break;
							case 3:
								exam.setCount3(examOrganizingRuleVo.getTypeCount());
								break;
							case 4:
								exam.setCount4(examOrganizingRuleVo.getTypeCount());
								break;
							case 5:
								exam.setCount5(examOrganizingRuleVo.getTypeCount());
								break;
							case 6:
								exam.setCount6(examOrganizingRuleVo.getTypeCount());
								break;
							case 7:
								exam.setCount7(examOrganizingRuleVo.getTypeCount());
								break;
								
							default:
								break;
							}
							
						}
					}
			}
		 return list;
	}
	
	@Override
	public int selectExamListCount(Map<String, Object> param) {
		return examPaperDaoMapper.selectExamListCount(param);
	}

	@Override
	public ResCourseBean selectCourseById(int id) throws Exception {
		return resCourseDao.selectCourseById(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#selectCourseOpenGroupByCourseId(java.lang.Integer) <BR>
	 * Method name: 获取课程开放群组 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/
	@Override
	public List<ManageGroupBean> selectCourseOpenGroupByCourseId(Integer id) {
		return manageGroupDaoMapper.selectCourseOpenGroupByCourseId(id);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#selectCourseOpenDeptByCourseId(java.lang.Integer) <BR>
	 * Method name: selectCourseOpenDeptByCourseId <BR>
	 * Description: 获取课程开放部门 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/
	@Override
	public List<ManageDepartmentBean> selectCourseOpenDeptByCourseId(Integer id) {
		return manageDeptDaoMapper.selectCourseOpenDeptByCourseId(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#selectCourseOpenPostByCourseId(java.lang.Integer) <BR>
	 * Method name: 获取群组开放岗位 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/
	@Override
	public List<ManagePostBean> selectCourseOpenPostByCourseId(Integer id) {
		return managePostDaoMapper.selectCourseOpenPostByCourseId(id);
	}

	@Override
	public List<ResSectionBean> selectSectionByCourseId(String courseId) throws Exception {
		return resCourseDao.selectSectionByCourseId(courseId);
	}

	@Override
	public void updateSection(ResSectionBean section) throws Exception {
		resCourseDao.updateSection(section);
	}

	@Override
	public void changeCourseType(Map<String, Object> map) {
		resCourseDao.changeCourseType(map);
	}

	@Override
	public String uploadFile(MultipartFile file, String commonPath) {

		String extendUrl;
		String genName;
		JSONObject param = new JSONObject();
		try {
			//保存在指定文件路径里
			String path = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/
			//获取拼接地址
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			extendUrl = commonPath+"/"+df.format(new Date());
			path +=extendUrl;
			
			
			String fileName = file.getOriginalFilename();	
			String catalog = "";
			String suffixName =fileName.substring(fileName.lastIndexOf(".")+1); 
			
			genName = UUID.randomUUID()+"."+suffixName;
			
			//判断是否存在目录，如果不存在，则创建目录
			if(!new File(path).exists()){
				new File(path).mkdirs();
			}
			
			File dest = new java.io.File(path,genName);
			file.transferTo(dest);
			//判断该课件是什么类型
//			标准课件：zip
//			普通课件：doc,docx,xls,xlsx,ppt,pptx,txt,pdf
//			视频课件：mp4,flv,wmv,rmvb
			if("zip".equals(suffixName)){
				genName = genName.replace("."+suffixName, "");
				ZipUtil.unpack(dest, new File(path+"/"+genName));
				//genName += "/"+parserXml(path+"/"+genName+"/"+Constant.RES_COURSE_ZIP_XML);
				ScormParser score = new ScormParser(path+"/"+genName+"/"+Constant.RES_COURSE_ZIP_XML);
				catalog = JsonUtil.getJsonString4JavaPOJO(score.getCatalog());
				param.put("coursewareType", 1);
				param.put("path", PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH")+"/"+extendUrl+"/"+genName);
			}else if("doc".equals(suffixName) ||"docx".equals(suffixName) ||"xls".equals(suffixName) ||"xlsx".equals(suffixName) ||"ppt".equals(suffixName) ||
					"pptx".equals(suffixName) ||"txt".equals(suffixName) ||"pdf".equals(suffixName) ){
				//ConvertUtil.convertOfficeToSwf(extendUrl+"/"+genName);
				converterFile(extendUrl+"/"+genName);
				genName = genName.replace(suffixName, "swf");
				param.put("coursewareType", 2);
				param.put("path", PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH")+"/"+extendUrl+"/"+genName);
			}else if("mp4".equals(suffixName) ||"flv".equals(suffixName) ||"wmv".equals(suffixName) ||"rmvb".equals(suffixName)){
				if (ConvertUtil.needConvertToFlv("."+suffixName)) {
					//genName = ConvertUtil.convertToFlv(extendUrl+"/"+genName);
					
					genName = extendUrl+"/"+genName;
					converterVideoFile(genName);
					genName = genName.substring(0, genName.lastIndexOf("."))+".flv";
					param.put("path", PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH")+"/"+genName);
				}else{
					param.put("path", PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH")+"/"+extendUrl+"/"+genName);
				}
				param.put("coursewareType", 3);
			}
			param.put("recordCode", 1);
			param.put("fileName", fileName);
			param.put("catalog",catalog);
			param.put("fileSize", dest.length());
		//	param.put("path", PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH")+"/"+extendUrl+"/"+genName);
			
		} catch (Exception e) {
			e.printStackTrace();
			param.put("recordCode", -1);
			return param.toString();
		}
		
		return param.toString();
	}
	
	 public String parserXml(String fileName) {
			String findFileName = "";
			File inputXml = new File(fileName);
			SAXReader saxReader = new SAXReader();
			try {
				Document document = saxReader.read(inputXml);
				Element root = document.getRootElement();
				for(Iterator i = root.elementIterator();i.hasNext();){
					Element head = (Element) i.next();
					for(Iterator j = head.elementIterator();j.hasNext();){
						Element elem = (Element) j.next();
						if("resource".equals(elem.getName())){
							findFileName = elem.attributeValue("href");
							return findFileName;
//							for(Iterator k=elem.elementIterator();k.hasNext();){
//								Element last = (Element) k.next();
//								if("file".equals(last.getName())){
//									findFileName = last.attributeValue("href");
//								}
//							}
						}
					}
				}
			} catch (DocumentException e) {
			}
			return findFileName;
		}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#featurAndUnFeaturResCourse(com.jftt.wifi.bean.ResCourseBean) <BR>
	 * Method name: featurAndUnFeaturResCourse <BR>
	 * Description:  精选、取消精选课程 <BR>
	 * Remark: <BR>
	 * @param bean  <BR>
	*/
	@Override
	public void featurAndUnFeaturResCourse(ResCourseBean bean) throws Exception {
		resCourseDao.featurAndUnFeaturResCourse(bean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#selectShareResCourseList(com.jftt.wifi.bean.vo.CourseVo) <BR>
	 * Method name: selectShareResCourseList <BR>
	 * Description: 查询共享课程 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  <BR>
	*/
	@Override
	public List<ResCourseBean> selectShareResCourseList(CourseVo vo) throws Exception {
		return resCourseDao.selectShareResCourseList(vo);
	}
	
	
	//定会以一个线程池
	private  final static ExecutorService es = Executors.newFixedThreadPool(100);
	/**
	 * Method name: converterFile <BR>
	 * Description: 多线程实现文件的转换 <BR>
	 * Remark: <BR>
	 * @param filePath  void<BR>
	 */
	private void  converterFile(final String filePath){
			es.execute(new Runnable() {
			@Override
			public void run() {
				try {
					//转换->swf
					/*DocConverter d = new DocConverter(filePath);   
					d.conver();*/
					ConvertUtil.convertOfficeToSwf(filePath);
					
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
	
	private void  converterVideoFile(final String filePath){
		es.execute(new Runnable() {
		@Override
		public void run() {
			try {
				//转换->swf
				/*DocConverter d = new DocConverter(filePath);   
				d.conver();*/
				ConvertUtil.convertToFlv(filePath);
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	});
}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#selectCourseTypeByCourseId(java.util.Map) <BR>
	 * Method name: selectCourseTypeByCourseId <BR>
	 * Description: 根据课程id获取课程分类  <BR>
	 * Remark: <BR>
	 * @param map
	 * @return  <BR>
	*/
	@Override
	public List<Map<String, Object>> selectCourseTypeByCourseId(
			Map<String, Object> map) {
		return resCourseDao.selectCourseTypeByCourseId(map);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ResService#deleteCourseType(java.util.Map) <BR>
	 * Method name: deleteCourseType <BR>
	 * Description: 根据课程id删除课程分类 <BR>
	 * Remark: <BR>
	 * @param map  <BR>
	*/
	@Override
	public void deleteCourseTypeByCourseId(Map<String, Object> map) {
		resCourseDao.deleteCourseTypeByCourseId(map);
	}
	
	
	/** zhangbocheng start*/
	
	@Override
	public List<ResCourseBean> selectCourseListByPostId(CourseVo vo)
			throws Exception {
		return resCourseDao.selectCourseListByPostId(vo);
	}
	
	@Override
	public int selectCourseListCountByPostId(CourseVo vo)
			throws Exception {
		return resCourseDao.selectCourseListCountByPostId(vo);
	}
	/** zhangbocheng end*/


}
