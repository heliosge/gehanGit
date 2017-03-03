package com.jftt.wifi.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.MallCourseApproveBean;
import com.jftt.wifi.bean.MallCourseBean;
import com.jftt.wifi.bean.MallCourseCategoryBean;
import com.jftt.wifi.bean.MallOrderRelateBean;
import com.jftt.wifi.bean.MallSellRecordBean;
import com.jftt.wifi.bean.MallTopicBean;
import com.jftt.wifi.bean.MallTopicCourseBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.ResCoursewareBean;
import com.jftt.wifi.bean.ResSectionBean;
import com.jftt.wifi.bean.ResSectionCoursewareBean;
import com.jftt.wifi.bean.ResSectionExamBean;
import com.jftt.wifi.bean.SectionExamBean;
import com.jftt.wifi.bean.vo.IntegralCommodityCategoryVo;
import com.jftt.wifi.bean.vo.MallCourseApproveVo;
import com.jftt.wifi.bean.vo.MallCourseCategoryVo;
import com.jftt.wifi.bean.vo.MallCourseVo;
import com.jftt.wifi.bean.vo.MallOrderDetailsPageVo;
import com.jftt.wifi.bean.vo.MallOrderVo;
import com.jftt.wifi.bean.vo.MallSellRecordVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.MallCourseApproveDaoMapper;
import com.jftt.wifi.dao.MallCourseCategoryDaoMapper;
import com.jftt.wifi.dao.MallCourseDaoMapper;
import com.jftt.wifi.dao.MallOrderDaoMapper;
import com.jftt.wifi.dao.MallSellRecordDaoMapper;
import com.jftt.wifi.dao.MallTopicDaoMapper;
import com.jftt.wifi.dao.ResCourseDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.MallCourseManageService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.ResService;
import com.jftt.wifi.util.TimeUtil;

@Service
public class MallCourseManageServiceImpl implements MallCourseManageService {

	@Autowired
	private MallCourseDaoMapper mallCourseDaoMapper;
	
	@Autowired
	private MallCourseCategoryDaoMapper mallCourseCategoryDaoMapper;
	@Autowired
	private MallCourseApproveDaoMapper mallCourseApproveDaoMapper;
	@Autowired
	private MallOrderDaoMapper mallOrderDaoMapper;
	@Autowired
	private MallSellRecordDaoMapper mallSellRecordDaoMapper;
	@Autowired
	private ResService resService;
	
	@Autowired
	private ResCourseDaoMapper resCourseDaoMapper;
	
	@Autowired
	private MallTopicDaoMapper mallTopicDaoMapper;
	
	@Autowired
	private ManageUserService manageUserService;
	

/**zhangbocheng start*/
	
	/**
	 * Method name: getCategoryById <BR>
	 * Description: 根据id获取商城课程分类 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  MallCourseCategoryBean<BR>
	 */
	@Override
	public MallCourseCategoryBean getCategoryById(Integer id) throws Exception{
		return mallCourseCategoryDaoMapper.getById(id);
	}
	
	
	/**
	 * Method name: checkCategoryName <BR>
	 * Description: 检查分类重名 <BR>
	 * Remark: <BR>
	 * @param MallCourseCategoryBean  Integer<BR>
	 */
	@Override
	public Integer checkCategoryName(MallCourseCategoryBean bean) throws Exception{
		return mallCourseCategoryDaoMapper.checkName(bean);
	}
	
	
	
	
	
	/**
	 * Method name: deleteMallCourseCategory <BR>
	 * Description: 删除分类<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public Integer deleteMallCourseCategory(Integer id) throws Exception{
		if(!hasCourse(id)&&!hasChild(id)){
			mallCourseCategoryDaoMapper.deleteMallCourseCategory(id);
			return 1;
		}else{
			return 0;
		}
		
	}
	
	
	private boolean hasCourse(Integer id)throws Exception{
		return mallCourseCategoryDaoMapper.hasCourse(id)>0;
	}
	private boolean hasChild(Integer id)throws Exception{
		return mallCourseCategoryDaoMapper.getChildCategorys(id).size()>0;
	}

	/**
	 * Method name: updateMallCourseCategory <BR>
	 * Description: 修改分类 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	@Override
	public void updateMallCourseCategory(MallCourseCategoryBean record) throws Exception{
		mallCourseCategoryDaoMapper.updateMallCourseCategory(record);
	}

	/**
	 * Method name: insertMallCourseCategory <BR>
	 * Description: 添加分类 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	@Override
	public Integer insertMallCourseCategory(MallCourseCategoryBean record) throws Exception{
		Integer maxOrderNum = mallCourseCategoryDaoMapper.getMaxOrderNum(record.getParentId());
		if(maxOrderNum==null){
			maxOrderNum=0;	
		}
		maxOrderNum++;
		System.err.println(maxOrderNum);
		record.setOrderNum(maxOrderNum);
		mallCourseCategoryDaoMapper.insertMallCourseCategory(record);
		return record.getId();
	}
	
	/**
	 * Method name: moveMallCourseCategory <BR>
	 * Description: 移动分类<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Integer moveMallCourseCategory(Integer id,Integer flag) throws Exception{
		if(id==null||flag==null){
			return 0;
		}
		if(flag ==0){//上移
			MallCourseCategoryBean bean = mallCourseCategoryDaoMapper.getById(id);
			if(bean==null){
				return 0;
			}
			MallCourseCategoryBean upBean=mallCourseCategoryDaoMapper.getUpCategory(bean.getParentId(),bean.getOrderNum());
			if(upBean==null){
				return 0;
			} 
	     
			Integer tempOrderNum = bean.getOrderNum();
			bean.setOrderNum(upBean.getOrderNum());
			upBean.setOrderNum(tempOrderNum);
			mallCourseCategoryDaoMapper.updateMallCourseCategory(bean);
			mallCourseCategoryDaoMapper.updateMallCourseCategory(upBean);
			return 1;
		}else if(flag==1){//下移
			MallCourseCategoryBean bean = mallCourseCategoryDaoMapper.getById(id);
			if(bean==null){
				return 0;
			}
			MallCourseCategoryBean downBean=mallCourseCategoryDaoMapper.getDownCategory(bean.getParentId(),bean.getOrderNum());
			if(downBean==null){
				return 0;
			}
			
			Integer tempOrderNum = bean.getOrderNum();
			bean.setOrderNum(downBean.getOrderNum());
			downBean.setOrderNum(tempOrderNum);
			mallCourseCategoryDaoMapper.updateMallCourseCategory(bean);
			mallCourseCategoryDaoMapper.updateMallCourseCategory(downBean);
			return 1;
		}
		return 0;
	}


	
	/**
	 * Method name: getMallCourseCategorys <BR>
	 * Description: 查询分类 <BR>
	 * Remark: <BR>
	 * @param
	 * @return  List<MallCourseCategoryBean><BR>
	 */
	@Override
	public List<MallCourseCategoryVo> getMallCourseCategorys() throws Exception{
		
		List<MallCourseCategoryVo> list = mallCourseCategoryDaoMapper.getMallCourseCategorys();
		if(list!=null&&list.size()>0){
			for(MallCourseCategoryVo vo :list){
				vo.setType(1);
				vo.setChildren(getChildCategoryByVo(vo));
			}
		}
		
		return  list;
	}
	
	/**
	 * Method name: getChildCategory <BR>
	 * Description: 获取分类的子分类 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  List<MallCourseAllCategoryVo><BR>
	 */
	@Override
	public List<MallCourseCategoryVo> getChildCategorys(MallCourseCategoryVo vo) throws Exception{

		
		return mallCourseCategoryDaoMapper.getChildCategorys(vo.getId());
	}
	
	/**
	 * Method name: getChildCategoryByVo <BR>
	 * Description: 获取分类的子分类 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  List<MallCourseAllCategoryVo><BR>
	 */
	public List<MallCourseCategoryVo> getChildCategoryByVo(MallCourseCategoryVo vo) throws Exception{
		List<MallCourseCategoryVo> list = mallCourseCategoryDaoMapper.getChildCategorys(vo.getId());
		if(list!=null&&list.size()>0){
			for(MallCourseCategoryVo childVo :list){
				childVo.setType(vo.getType()+1);
				childVo.setChildren(getChildCategoryByVo(childVo));
			}
		}
		
		return list;
	}
	
	/**
	 * Method name: getUpCategorys <BR>
	 * Description: 获取上一级的所有分类<BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws Exception  List<MallCourseCategoryBean><BR>
	 */
	@Override
	public List<MallCourseCategoryBean> getUpCategorys(Integer categoryId) throws Exception{
		return mallCourseCategoryDaoMapper.getUpCategorys(categoryId);
	}

	

	/**
	 * Method name: getCourseById <BR>
	 * Description: 根据id查课程 <BR>
	 * Remark: <BR>
	 * @param id  MallCourseBean<BR>
	 */
	@Override
	public MallCourseBean getCourseById(Integer id) throws Exception{
		return mallCourseDaoMapper.getById(id);
	}

	
	/**
	 * Method name: getCourseDetailById <BR>
	 * Description: 根据id查询课程详情 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityBean<BR>
	 */
	@Override
	public MallCourseVo getCourseDetailById(Integer id) throws Exception{
		return mallCourseDaoMapper.getDetailById(id);
	}
	/**
	 * Method name: checkHaveCopy <BR>
	 * Description: 检查课程是否存在副本 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public Integer getCopyId(Integer id) throws Exception{
		return mallCourseDaoMapper.getCopyId(id);
	}

	/**
	 * Method name: checkCourseCode <BR>
	 * Description: 检查课程编号重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public Integer checkCourseCode(MallCourseVo bean) throws Exception{
		return mallCourseDaoMapper.checkCode(bean);
	}
	
	/**
	 * Method name: insertMallCourse <BR>
	 * Description: 新增课程 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public Integer insertMallCourse(MallCourseBean bean) throws Exception{
		mallCourseDaoMapper.insertMallCourse(bean);
		return bean.getId();
	}
	
	/**
	 * Method name: insertMallCourse <BR>
	 * Description: 新增课程 <BR>
	 * Remark: <BR>
	 * @param vo  void<BR>
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public MallCourseVo insertMallCourse(MallCourseVo vo) throws Exception{
		ResCourseBean resBean = new ResCourseBean();
		resBean= vo.getResCourseBean(resBean);
		resService.insertCourse(resBean);
		Integer courseId =resBean.getId();
		MallCourseBean bean = new MallCourseBean();
		bean=vo.getMallCourseBean(bean);
		bean.setCourseId(courseId);
		vo.setCourseId(courseId);
		mallCourseDaoMapper.insertMallCourse(bean);
		vo.setId(bean.getId());
		//选择原有课程来新建商城课程
		if(vo.getSelectCourseId()!=null&&(vo.getCopyCourseId()==null||vo.getCopyCourseId()==0)){
			List<ResSectionBean> sectionList =resService.selectSectionByCourseId(vo.getSelectCourseId().toString());
		    if(sectionList!=null&&sectionList.size()>0){
		    	for(ResSectionBean section :sectionList){
		    		Integer sectionId = section.getId();
		    		section.setCourseId(courseId);
		    		section.setId(null);
		    		//保存章节
		    		Integer newSectionId =resService.insertSection(section);
		    		Map<String, Object> param = new HashMap<String, Object>();
					param.put("sectionId", sectionId);
		    		List<ResCoursewareBean> coursewareList = resService.selectSectionAndCourseware(param);
		    		List<SectionExamBean> examList = resService.selectSectionAndExam(param);
		    		//保存课件
		    		if(coursewareList!=null&&coursewareList.size()>0){
		    			for(ResCoursewareBean coursewareBean:coursewareList){
		    				ResSectionCoursewareBean voBean = new ResSectionCoursewareBean(newSectionId, coursewareBean.getId());
							resService.insertSectionCourseware(voBean);
		    			}
		    		}
		    		//保存考试
		    		if(examList!=null&&examList.size()>0){
		    			for(SectionExamBean sectionExamBean:examList){
		    				ResSectionExamBean voBean = new ResSectionExamBean(newSectionId, sectionExamBean.getExamId(), sectionExamBean.getExamDuration() 
									,sectionExamBean.getExamTimes() ,Integer.parseInt(sectionExamBean.getPassPercent()));
							resService.insertSectionExam(voBean);
		    			}
		    		}
		    		
		    	
		    	}
		    }
		}else if(vo.getCopyCourseId()!=null&&vo.getCopyCourseId()!=0){
			MallCourseBean copyCourse = mallCourseDaoMapper.getById(vo.getCopyCourseId());
			if(copyCourse!=null&&copyCourse.getCourseId()!=null){
				List<ResSectionBean> sectionList =resService.selectSectionByCourseId(copyCourse.getCourseId().toString());
			    if(sectionList!=null&&sectionList.size()>0){
			    	for(ResSectionBean section :sectionList){
			    		Integer sectionId = section.getId();
			    		section.setCourseId(courseId);
			    		section.setId(null);
			    		//保存章节
			    		Integer newSectionId =resService.insertSection(section);
			    		Map<String, Object> param = new HashMap<String, Object>();
						param.put("sectionId", sectionId);
			    		List<ResCoursewareBean> coursewareList = resService.selectSectionAndCourseware(param);
			    		List<SectionExamBean> examList = resService.selectSectionAndExam(param);
			    		//保存课件
			    		if(coursewareList!=null&&coursewareList.size()>0){
			    			for(ResCoursewareBean coursewareBean:coursewareList){
			    				ResSectionCoursewareBean voBean = new ResSectionCoursewareBean(newSectionId, coursewareBean.getId());
								resService.insertSectionCourseware(voBean);
			    			}
			    		}
			    		//保存考试
			    		if(examList!=null&&examList.size()>0){
			    			for(SectionExamBean sectionExamBean:examList){
			    				ResSectionExamBean voBean = new ResSectionExamBean(newSectionId, sectionExamBean.getExamId(), sectionExamBean.getExamDuration() 
										,sectionExamBean.getExamTimes() ,Integer.parseInt(sectionExamBean.getPassPercent()));
								resService.insertSectionExam(voBean);
			    			}
			    		}
			    		
			    	
			    	}
			    }
			}
			
		}
		return vo;
	}

	/**
	 * Method name: updateMallCourse <BR>
	 * Description: 修改课程 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void updateMallCourse(MallCourseBean bean) throws Exception{
		mallCourseDaoMapper.updateMallCourse(bean);
		/**
		List<Integer> idList = mallCourseDaoMapper.getRelateTopicIdList(bean.getId());
		if(idList.size()>0){
			for(Integer topicId:idList){
				Map priceMap = mallTopicDaoMapper.getPrice(topicId);
				if(priceMap!=null){
					BigDecimal newPrice = (BigDecimal)priceMap.get("newPrice");
					BigDecimal newCheapPrice = (BigDecimal)priceMap.get("newCheapPrice");
					BigDecimal price = (BigDecimal)priceMap.get("price");
					BigDecimal cheapPrice = (BigDecimal)priceMap.get("cheapPrice");
					if(newPrice!=price||newCheapPrice!=cheapPrice){
						MallTopicBean topic = new MallTopicBean();
						topic.setPrice(newPrice);
						topic.setId(topicId);
						topic.setCheapPrice(newCheapPrice);
						mallTopicDaoMapper.updateMallTopic(topic);
					}
				}
			}
			
		}**/
	}


	/**
	 * Method name: checkCourseIsOn <BR>
	 * Description: 检查课程是否上架 <BR>
	 * Remark: <BR>
	 * @param id  Integer<BR>
	 */
	public boolean checkCourseIsOn(Integer id) throws Exception{
		return mallCourseDaoMapper.checkCourseIsOn(id)>0;
	}
	/**
	 * Method name: deleteMallCourse <BR>
	 * Description: 删除课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void deleteMallCourse(Integer id) throws Exception{
		mallCourseDaoMapper.deleteMallCourse(id);
	}
	
	/**
	 * Method name: setCourseStatus <BR>
	 * Description: 设置课程提交审核状态 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void setCourseStatus(Integer id,Integer status) throws Exception{
		mallCourseDaoMapper.setCourseStatus(id, status);
	}


	
	/**
	 * Method name: putawayById <BR>
	 * Description: 上架课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void putawayById(Integer id,Integer userId) throws Exception{
		mallCourseDaoMapper.putawayById(id, userId);
	}

	
	/**
	 * Method name: putoutById <BR>
	 * Description: 下架课程<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean putoutById(Integer id) throws Exception{
		Integer topicCount =mallCourseDaoMapper.isInTopic(id);
		if(topicCount!=null&&topicCount==0){
			mallCourseDaoMapper.putoutById(id);
			List<Integer> topicIdList=mallCourseDaoMapper.getRelateTopicIdList(id);
			for(Integer topicId:topicIdList){
				MallTopicCourseBean bean = new MallTopicCourseBean();
				bean.setMallCourseId(id);
				bean.setTopicId(topicId);
				mallTopicDaoMapper.deleteMallTopicCourse(bean);
				Map priceMap = mallTopicDaoMapper.getPrice(topicId);
				if(priceMap!=null){
					BigDecimal newPrice = (BigDecimal)priceMap.get("newPrice");
					BigDecimal newCheapPrice = (BigDecimal)priceMap.get("newCheapPrice");
					BigDecimal price = (BigDecimal)priceMap.get("price");
					BigDecimal cheapPrice = (BigDecimal)priceMap.get("cheapPrice");
					if(newPrice!=price||newCheapPrice!=cheapPrice){
						MallTopicBean topic = new MallTopicBean();
						topic.setPrice(newPrice);
						topic.setId(topicId);
						topic.setCheapPrice(newCheapPrice);
						mallTopicDaoMapper.updateMallTopic(topic);
					}
				}
			}
			return true;
			
		}else{
			return false;
		}
		
	}

	
	/**
	 * Method name: selectMallCourseList <BR>
	 * Description: 查询课程list <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@Override
	public List<MallCourseVo> selectMallCourseList(MallCourseVo vo) throws Exception{
		return mallCourseDaoMapper.selectMallCourseList(vo);
	}
	
	/**
	 * Method name: selectMallCourseCount <BR>
	 * Description: 查询课程数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@Override
	public Integer selectMallCourseCount(MallCourseVo vo) throws Exception{
		return mallCourseDaoMapper.selectMallCourseCount(vo);
	}
	

	/**
	 * Method name: selectCompanyMallCourseList <BR>
	 * Description: 查询课程list <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@Override
	public List<MallCourseVo> selectCompanyMallCourseList(MallCourseVo vo) throws Exception{
		return mallCourseDaoMapper.selectCompanyMallCourseList(vo);
	}
	
	/**
	 * Method name: selectMallCompanyCourseCount <BR>
	 * Description: 查询课程数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@Override
	public Integer selectCompanyMallCourseCount(MallCourseVo vo) throws Exception{
		return mallCourseDaoMapper.selectCompanyMallCourseCount(vo);
	}
	
/**mallCourseApprove  课程审核 */
	
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查审核记录 <BR>
	 * Remark: <BR>
	 * @param id  MallCourseApproveBean<BR>
	 */
	@Override
	public MallCourseApproveBean getApproveById(Integer id) throws Exception{
		return mallCourseApproveDaoMapper.getById(id);
	}

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查询记录详情 <BR>
	 * Remark: <BR>
	 * @param id  MallCourseApproveVo<BR>
	 */
	@Override
	public MallCourseApproveVo getApproveDetailById(Integer id) throws Exception{
		return mallCourseApproveDaoMapper.getDetailById(id);
	}
	
	/**
	 * Method name: getApproveDetailListById <BR>
	 * Description: 根据id查询记录详情 <BR>
	 * Remark: <BR>
	 * @param id  MallCourseApproveVo<BR>
	 */
	@Override
	public List<MallCourseApproveVo> getApproveDetailListById(Integer id) throws Exception{
		return mallCourseApproveDaoMapper.getDetailListById(id);
	}

	/**
	 * Method name: checkMallCourseApprove <BR>
	 * Description: 检查审核记录是否重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public boolean checkMallCourseApprove(MallCourseApproveBean bean) throws Exception{
		Integer count =mallCourseApproveDaoMapper.checkMallCourseApprove(bean);
		return count==0;
	}
	

	/**
	 * Method name: insertMallCourseApprove <BR>
	 * Description: 新增审核记录 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public Integer insertMallCourseApprove(MallCourseApproveBean bean) throws Exception{
		mallCourseApproveDaoMapper.insertMallCourseApprove(bean);
		return bean.getId();
	}

	/**
	 * Method name: updateMallCourseApprove <BR>
	 * Description: 修改审核记录 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public void updateMallCourseApprove(MallCourseApproveBean bean) throws Exception{
		mallCourseApproveDaoMapper.updateMallCourseApprove(bean);
	}

	/**
	 * Method name: deleteMallCourseApprove <BR>
	 * Description: 删除审核记录 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void deleteMallCourseApprove(Integer id) throws Exception{
		mallCourseApproveDaoMapper.deleteMallCourseApprove(id);
	}

	


	
	/**
	 * Method name: doCheckApprove <BR>
	 * Description: 审核<BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void doCheckApprove(MallCourseApproveBean bean) throws Exception{
		if(bean.getStatus()==1){
			mallCourseApproveDaoMapper.doCheck(bean);
			bean =mallCourseApproveDaoMapper.getById(bean.getId());
			MallCourseBean course =mallCourseDaoMapper.getById(bean.getCourseId());
			if(course!=null&&course.getIsCopy()==1&&course.getCopyCourseId()!=null){
				mallCourseDaoMapper.deleteMallCourseUnCheck(course.getCopyCourseId());
//				course.setIsCopy(0);
//				course.setCopyCourseId(null);
//				mallCourseDaoMapper.updateMallCourse(course);
			}
			mallCourseDaoMapper.putawayById(bean.getCourseId(), bean.getCheckUserId());
		}else{
			mallCourseApproveDaoMapper.doCheck(bean);
	        bean =mallCourseApproveDaoMapper.getById(bean.getId());
			mallCourseDaoMapper.setCourseStatus(bean.getCourseId(),4);
		}
		
	}

	
	/**
	 * Method name: selectMallCourseApproveList <BR>
	 * Description: 查询审核记录list <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@Override
	public List<MallCourseApproveVo> selectMallCourseApproveList(MallCourseApproveVo vo) throws Exception{
		return mallCourseApproveDaoMapper.selectMallCourseApproveList(vo);
	}
	
	/**
	 * Method name: selectMallCourseApproveCount <BR>
	 * Description: 查询记录数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@Override
	public Integer selectMallCourseApproveCount(MallCourseApproveVo vo) throws Exception{
		return mallCourseApproveDaoMapper.selectMallCourseApproveCount(vo);
	}
	

	/**
	 * Method name: deleteCompanyCourse <BR>
	 * Description: 删除企业课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void deleteCompanyCourse(Integer id) throws Exception{
		mallCourseDaoMapper.deleteCompanyCourse(id);
	}
	
	
	
	/** order 订单 */
	
	/**
	 * 课程销售记录
	 * Method name: selectCourseOrderSellRecord <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  List<MallOrderVo><BR>
	 */
	@Override
	public List<MallOrderVo> selectCourseOrderSellRecord(MallOrderVo vo) throws Exception{
		return mallOrderDaoMapper.selectCourseOrderSellRecord(vo);
	}
	/**
	 * 订单详情
	 * Method name: getOrderDetailById <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  MallOrderVo<BR>
	 */
	@Override
	public MallOrderVo getOrderDetailById(Integer id) throws Exception{
		return mallOrderDaoMapper.getOrderDetailById(id);
	}
	/**
	 * 课程销售记录数目
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer selectCourseOrderSellRecordCount(MallOrderVo vo) throws Exception{
		return mallOrderDaoMapper.selectCourseOrderSellRecordCount(vo);
	}
	
	
	
	/**
	 * 发货
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delivery(MallOrderVo vo) throws Exception {
		/**
		 * 主要目标：支付后需要将对应的课程信息同步到 课程表 以及课程销售记录表
		 */
		//取得当前支付的订单及购买的商品信息
		Integer orderId = vo.getId();
		MallOrderDetailsPageVo orderDetails = mallOrderDaoMapper.getOrderDetailsById(orderId.toString());
		int orderType = orderDetails.getCommodityType();
		int curOrderId = orderDetails.getId();
		List<MallOrderRelateBean> rbean = orderDetails.getRelateProducts();
		if(orderType == 1){//代表是课程订单
			//同步数据到课程表
			for(MallOrderRelateBean rs : rbean){
				int relateMallCourseId = rs.getRelateId();// 当前的商城课程id
				excuteElse( curOrderId, relateMallCourseId,orderDetails.getUserId());
			}
		}else if(orderType == 2){//专题订单
			for(MallOrderRelateBean rs : rbean){
				int relateMallTopicId = rs.getRelateId();// 当前的商城专题id
				//通过专题id获取 专题关联的课程
				List<MallTopicCourseBean> topicCourseList = mallTopicDaoMapper.getByTopicId(relateMallTopicId);
				for(MallTopicCourseBean topC : topicCourseList){
					int courseId = topC.getMallCourseId();// 专题关联的课程id
					excuteElse(curOrderId, courseId,orderDetails.getUserId());
				}
			}
		}
		mallOrderDaoMapper.delivery(vo);
		
	}
	private void excuteElse( int curOrderId, int mallCourseId,Integer userId) throws DataBaseException, Exception {
		MallCourseBean mallCbean = mallCourseDaoMapper.selectByPrimaryKey(mallCourseId);
		if(mallCbean != null){
			BigDecimal price =mallCbean.getCheapPrice();//课程优惠价格
			Integer courseId = mallCbean.getCourseId();// 获取到课程id
			// 先获取当前的课程信息
			ResCourseBean rcBean = resCourseDaoMapper.getById(courseId);
			String courseName = rcBean.getName();//课程名称
			ManageUserBean userBean =manageUserService.findUserById(userId.toString()) ;
			int courseCompanyId=rcBean.getCompanyId();
			int courseSubCompanyId=rcBean.getSubCompanyId();
			if(userBean != null){
				rcBean.setCode(getUUID());
			
				rcBean.setCompanyId(userBean.getCompanyId());
				rcBean.setSubCompanyId(userBean.getSubCompanyId());
			}
			rcBean.setMallStatus(3);
			// 同步当前的这条课程数据为用户已购买的课程
			resCourseDaoMapper.courseSynchronization(rcBean);
			Integer newCourseId = rcBean.getId();
			List<ResSectionBean> sectionList =resService.selectSectionByCourseId(courseId.toString());
		    if(sectionList!=null&&sectionList.size()>0){
		    	for(ResSectionBean section :sectionList){
		    		Integer sectionId = section.getId();
		    		section.setCourseId(newCourseId);
		    		section.setId(null);
		    		//保存章节
		    		Integer newSectionId =resService.insertSection(section);
		    		Map<String, Object> param = new HashMap<String, Object>();
					param.put("sectionId", sectionId);
		    		List<ResCoursewareBean> coursewareList = resService.selectSectionAndCourseware(param);
		    		List<SectionExamBean> examList = resService.selectSectionAndExam(param);
		    		//保存课件
		    		if(coursewareList!=null&&coursewareList.size()>0){
		    			for(ResCoursewareBean coursewareBean:coursewareList){
		    				ResSectionCoursewareBean voBean = new ResSectionCoursewareBean(newSectionId, coursewareBean.getId());
							resService.insertSectionCourseware(voBean);
		    			}
		    		}
		    		//保存考试
		    		if(examList!=null&&examList.size()>0){
		    			for(SectionExamBean sectionExamBean:examList){
		    				ResSectionExamBean voBean = new ResSectionExamBean(newSectionId, sectionExamBean.getExamId(), sectionExamBean.getExamDuration() 
									,sectionExamBean.getExamTimes() ,Integer.parseInt(sectionExamBean.getPassPercent()));
							resService.insertSectionExam(voBean);
		    			}
		    		}
		    		
		    	
		    	}
		    }
			
			
			
			//同时需要加入当前课程信息到对应的课程销售记录表中
			MallSellRecordBean msBean = new MallSellRecordBean();
			msBean.setCourseId(mallCourseId);
			msBean.setOrderId(curOrderId);
			msBean.setPrice(price);
			msBean.setCourseName(courseName);
			msBean.setStatus(1);
			msBean.setBuyUserId(userId);
			if(rcBean != null){
				msBean.setCompanyId(courseCompanyId);
				msBean.setSubCompanyId(courseSubCompanyId);
			}
			//新增销售记录数据
			mallSellRecordDaoMapper.insertMallSellRecord(msBean);
			
		}
	}
	 private  String getUUID() {  
	        UUID uuid = UUID.randomUUID();  
	        String str = uuid.toString();  
	        return str;
	    } 
	
	
	/**
	 * 邮寄发票
	 * @return
	 * @throws Exception
	 */
	@Override
	public void postInvoice(MallOrderVo vo)throws Exception{
		mallOrderDaoMapper.postInvoice(vo);
	}
	
	/**
	 * 订单失效
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public void doInvalid(Integer id)throws Exception{
		mallOrderDaoMapper.doInvalid(id);
	}
	
	/**
	 * 查询订单内所有课程
	 * Method name: selectCourseListByOrderId <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  List<MallCourseVo><BR>
	 */
	@Override
	public List<MallCourseVo> selectCourseListByOrderId(Integer id) throws Exception{
		return mallOrderDaoMapper.selectCourseListByOrderId(id);
	}
	
	
	
	/** 课程购买记录 */
	/**
	 * Method name: getSellRecordById <BR>
	 * Description: 根据id查记录 <BR>
	 * Remark: <BR>
	 * @param id  MallSellRecordBean<BR>
	 */
	@Override
	public MallSellRecordBean getSellRecordById(Integer id) throws Exception{
		return mallSellRecordDaoMapper.getById(id);
	}

	
	/**
	 * Method name: getSellRecordDetailById <BR>
	 * Description: 根据id查询记录详情 <BR>
	 * Remark: <BR>
	 * @param id  MallSellRecordVo<BR>
	 */
	@Override
	public MallSellRecordVo getSellRecordDetailById(Integer id) throws Exception{
		return mallSellRecordDaoMapper.getDetailById(id);
	}

	/**
	 * Method name: checkIsBuy <BR>
	 * Description: 检查是否已经购买课程<BR>
	 * Remark: <BR>
     * @param   courseId
	 * @param userId  void<BR>
	 */
	@Override
	public Integer checkIsBuy(Integer courseId,Integer userId) throws Exception{
		return mallSellRecordDaoMapper.checkIsBuy(courseId, userId);
	}
	
	/**
	 * Method name: selectMallSellRecordList <BR>
	 * Description: 查询课程购买记录 <BR>
	 * Remark: <BR>
	 * @param vo  List<MallSellRecordVo><BR>
	 */
	@Override
	public List<MallSellRecordVo> selectMallSellRecordList(MallSellRecordVo vo)throws Exception{
		return mallSellRecordDaoMapper.selectMallSellRecordList(vo);
	}
	
	/**
	 * Method name: selectMallSellRecordCount <BR>
	 * Description: 查询课程购买记录数目 <BR>
	 * Remark: <BR>
	 * @param vo  Integer<BR>
	 */
	@Override
	public Integer selectMallSellRecordCount(MallSellRecordVo vo)throws Exception{
		return mallSellRecordDaoMapper.selectMallSellRecordCount(vo);
	}
	/**
	 * Method name: insertMallSellRecord <BR>
	 * Description: 新增记录 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public Integer insertMallSellRecord(MallSellRecordBean bean) throws Exception{
		mallSellRecordDaoMapper.insertMallSellRecord(bean);
		return bean.getId();
	}

	/**
	 * Method name: updateMallSellRecord <BR>
	 * Description: 修改记录 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public void updateMallSellRecord(MallSellRecordBean bean) throws Exception{
		mallSellRecordDaoMapper.updateMallSellRecord(bean);
	}

	/**
	 * Method name: deleteMallSellRecord <BR>
	 * Description: 删除记录<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void deleteMallSellRecord(Integer id) throws Exception{
		mallSellRecordDaoMapper.deleteMallSellRecord(id);
	}

	
	/**
	 * Method name: pay <BR>
	 * Description: 付款 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void pay(Integer id,Integer userId,String payUserName) throws Exception{
		mallSellRecordDaoMapper.pay(id, userId, payUserName);
	}

	
	/**
	 * Method name: gathering <BR>
	 * Description: 收款 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void gathering(Integer id,Integer gatheringUserId,String gatheringUserName) throws Exception{
		mallSellRecordDaoMapper.gathering(id, gatheringUserId, gatheringUserName);
	}
	
	 /** Method name: getTotalMoney <BR>
	 * Description: 查询总金额 <BR>
	 * Remark: <BR>
	 * @param vo  Integer<BR>
	 */
	@Override
	public BigDecimal getTotalMoney(MallSellRecordVo vo)throws Exception{
		return mallSellRecordDaoMapper.getTotalMoney(vo);
	}
	
	/**
	 * Method name: getPayMoney <BR>
	 * Description: 查询已付款金额 <BR>
	 * Remark: <BR>
	 * @param vo  Integer<BR>
	 */
	@Override
	public BigDecimal getPayMoney(MallSellRecordVo vo)throws Exception{
		return mallSellRecordDaoMapper.getPayMoney(vo);
	}

	
	/**zhangbocheng end*/
}
