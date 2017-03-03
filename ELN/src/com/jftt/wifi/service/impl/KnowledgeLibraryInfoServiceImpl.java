/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KnowledgeLibraryServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-15        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jftt.elasticsearch.bean.BackArray;
import com.jftt.elasticsearch.bean.PageSort;
import com.jftt.elasticsearch.bean.TermQuery;
import com.jftt.elasticsearch.common.ElasticConstant.QueryType;
import com.jftt.elasticsearch.util.ElastisearchUtil;
import com.jftt.wifi.bean.KLCategoryBean;
import com.jftt.wifi.bean.KlCollectDownloadBean;
import com.jftt.wifi.bean.KlEvaluateBean;
import com.jftt.wifi.bean.KnowledgeBean;
import com.jftt.wifi.bean.ManageNoticeBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.KLCategoryVo;
import com.jftt.wifi.bean.vo.KlByCatParamsVo;
import com.jftt.wifi.bean.vo.KlContributorVo;
import com.jftt.wifi.bean.vo.KnowledgeOtherVo;
import com.jftt.wifi.bean.vo.SearchKlByNameVo;
import com.jftt.wifi.bean.vo.SearchKlVo;
import com.jftt.wifi.bean.vo.judgeColDownReturnVo;
import com.jftt.wifi.bean.vo.klCountsVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.common.IntegralConstant;
import com.jftt.wifi.dao.KlBrowerInfoDaoMapper;
import com.jftt.wifi.dao.KlCollectDownloadDaoMapper;
import com.jftt.wifi.dao.KlEvaluateDaoMapper;
import com.jftt.wifi.dao.KlResourceToTagsDaoMapper;
import com.jftt.wifi.dao.KlTagsDaoMapper;
import com.jftt.wifi.dao.KnowledgeManagerDaoMapper;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.KnowledgeLibraryInfoService;
import com.jftt.wifi.service.ManageNoticeService;
import com.jftt.wifi.service.ManageUserService;

/**
 * class name:KnowledgeLibraryServiceImpl <BR>
 * class description: 知识库 <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015-7-15
 * @author JFTT)chenrui
 */
@Service("knowledgeLibraryInfoService")
public class KnowledgeLibraryInfoServiceImpl implements KnowledgeLibraryInfoService {
	@Autowired
	private KlEvaluateDaoMapper klEvaluateDaoMapper;
	@Autowired
	private KlCollectDownloadDaoMapper klCollectDownloadDaoMapper;
	@Autowired
	private KnowledgeManagerDaoMapper knowledgeManagerDaoMapper;
	@Autowired
	private KlTagsDaoMapper klTagsDaoMapper;
	@Autowired
	private KlBrowerInfoDaoMapper klBrowerInfoDaoMapper;
	@Autowired
	private KlResourceToTagsDaoMapper klResourceToTagsDaoMapper;
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private IntegralManageService integralManageService;
	@Autowired
	private ManageNoticeService manageNoticeService;

	/** chenrui start */
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#giveEvaluate(com.jftt.wifi.bean.KlEvaluateBean) <BR>
	 *      Method name: giveEvaluate <BR>
	 *      Description: 知识评价 <BR>
	 *      Remark: <BR>
	 * @param klEvaluateBean
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public void giveEvaluate(KlEvaluateBean klEvaluateBean) throws Exception {
		int fromUserId = klEvaluateBean.getFromUserId();// 当前操作用户
		int currentResourceId = klEvaluateBean.getResourceId();
		int createUserId = getUploadingKnowledge(currentResourceId + "").getCreateUserId();// 当前资源的创建者

		String evaluateContent = klEvaluateBean.getEvaluateContent();
		if(evaluateContent!=null && !evaluateContent.isEmpty()){
			integralManageService.handleIntegral(fromUserId, IntegralConstant.Num_kl_toComment);// 评论别人的知识
		}
		integralManageService.handleIntegral(fromUserId, IntegralConstant.Num_kl_toEvaluate);// 给知识评分

		/*if(evaluateContent!=null && !evaluateContent.isEmpty()){
			integralManageService.handleIntegral(createUserId, IntegralConstant.Num_kl_ByComment);// 知识被别人评论
		}*/
		integralManageService.handleIntegral(createUserId, IntegralConstant.Num_kl_ByEvaluate);// 知识被别人评分

		klEvaluateDaoMapper.giveEvaluate(klEvaluateBean);
	}

	@Override
	public void addBrowerInfo(String userId, String knowledgeId) throws Exception {
		int flag = klBrowerInfoDaoMapper.isExist(userId,knowledgeId);
		if(flag==0){
			klBrowerInfoDaoMapper.add(userId,knowledgeId);
		}
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#addCollectAndDownload(com.jftt.wifi.bean.KlCollectDownloadBean) <BR>
	 *      Method name: addCollectAndDownload <BR>
	 *      Description: 收藏下载操作 <BR>
	 *      Remark: <BR>
	 * @param klCollectDownloadBean
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public void addCollectAndDownload(KlCollectDownloadBean klCollectDownloadBean) throws Exception {
		int operateType = klCollectDownloadBean.getOperateType();
		if (operateType == 1) {// 收藏
			int userId = klCollectDownloadBean.getUserId();
			// 积分-收藏别人的知识
			integralManageService.handleIntegral(userId, IntegralConstant.Num_kl_toCollect);
		} else if (operateType == 2) {// 下载
			// 积分-知识被别人下载
			/*
			 * 找到当前下载的资源的上传用户
			 */
			int resourceId = klCollectDownloadBean.getResourceId();
			int userId = getUploadingKnowledge(resourceId + "").getCreateUserId();
			integralManageService.handleIntegral(userId, IntegralConstant.Num_kl_ByDownload);
		}
		klCollectDownloadDaoMapper.addCollectAndDownload(klCollectDownloadBean);
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getKnowledgeCategory() <BR>
	 *      Method name: getKnowledgeCategory <BR>
	 *      Description: 获取知识分类 <BR>
	 *      Remark: <BR>
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KLCategoryVo> getKnowledgeCategory(String userId,String isCloud) throws Exception {
		int companyId =Constant.PULIAN_COMPANY_ID;
		int subCompanyId =Constant.PULIAN_COMPANY_ID;
		if("1".equals(isCloud)){//当前知识
			ManageUserBean manageUserBean = manageUserService.findUserById(userId);
			companyId = manageUserBean.getCompanyId();
//			subCompanyId = manageUserBean.getSubCompanyId();
			subCompanyId = manageUserBean.getCompanyId();
		} // else 默认云知识 即普联知识 公司id默认普联
		return knowledgeManagerDaoMapper.getKnowledgeCategory(companyId, subCompanyId);
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getKnowledgeCount() <BR>
	 *      Method name: getKnowledgeCount <BR>
	 *      Description: 获取当前知识资源数量统计 <BR>
	 *      Remark: <BR>
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<klCountsVo> getKnowledgeCount(String userId) throws Exception {
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		return knowledgeManagerDaoMapper.getKnowledgeCount(companyId, subCompanyId);
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getMyKnowledgeCount() <BR>
	 *      Method name: getMyKnowledgeCount <BR>
	 *      Description: 获取我贡献的知识数目 <BR>
	 *      Remark: <BR>
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public int getMyKnowledgeCount(String userId) throws Exception {
		ManageUserBean userBean = manageUserService.findUserById(userId);
		int companyId = userBean.getCompanyId();
		int subCompanyId = userBean.getSubCompanyId();
		return knowledgeManagerDaoMapper.getMyKnowledgeCount(userId,companyId,subCompanyId);
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getDepartmentKnowledge(java.lang.String) <BR>
	 *      Method name: getDepartmentKnowledge <BR>
	 *      Description: 获取部门知识 <BR>
	 *      Remark: <BR>
	 * @param userId
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KnowledgeOtherVo> getDepartmentKnowledge(String userId) throws Exception {
		ManageUserBean userBean = manageUserService.findUserById(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		String depId = userBean.getDeptId() + "";
		String field = "deptId";
		map.put(field, depId);
		List<ManageUserBean> userList = manageUserService.findUserByCondition(map);
		if (userList != null && userList.size() > 0) {
			return knowledgeManagerDaoMapper.getDepartmentKnowledge(userList);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getRecommendKnowledge() <BR>
	 *      Method name: getRecommendKnowledge <BR>
	 *      Description: 获取推荐知识 <BR>
	 *      Remark: <BR>
	 * @return <BR>
	 */
	@Override
	public List<KnowledgeOtherVo> getRecommendKnowledge(String userId) throws Exception {
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		return knowledgeManagerDaoMapper.getRecommendKnowledge(companyId, subCompanyId);
	}

	
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getKnowledgeByCategory(java.lang.String) <BR>
	 *      Method name: getKnowledgeByCategory <BR>
	 *      Description: 根据类别获取知识 (默认按时间排序)<BR>
	 *      Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KnowledgeOtherVo> getKnowledgeByCategory(KlByCatParamsVo paramsVo) throws Exception {
		String userId = paramsVo.getUserId();
		int companyId =Constant.PULIAN_COMPANY_ID;
		int subCompanyId =Constant.PULIAN_COMPANY_ID;
		if("1".equals(paramsVo.getIsCloud())){//非云知识,即获取当前公司的知识
			ManageUserBean manageUserBean = manageUserService.findUserById(userId);
			companyId = manageUserBean.getCompanyId();
			subCompanyId = manageUserBean.getSubCompanyId();
		}
		paramsVo.setCompanyId(companyId);
		paramsVo.setSubCompanyId(subCompanyId);
		List<KLCategoryBean> subList = null;
		KLCategoryBean bean = new KLCategoryBean();
		String categoryId = paramsVo.getCategoryId();
		if (categoryId != null && !"".equals(categoryId)) {
			bean.setCategoryId(Integer.parseInt(categoryId));
			subList = knowledgeManagerDaoMapper.getKlCategoryByParentId(categoryId);
			subList.add(bean);
		}
		paramsVo.setSubList(subList);
		List<KnowledgeOtherVo> list = knowledgeManagerDaoMapper.getKnowledgeByCategory(paramsVo);
		return convertList(list);

	}

	@Override
	public int getKnowledgeByCategoryCount(KlByCatParamsVo paramsVo) throws Exception {
		String userId = paramsVo.getUserId();
		int companyId =Constant.PULIAN_COMPANY_ID;
		int subCompanyId =Constant.PULIAN_COMPANY_ID;
		if("1".equals(paramsVo.getIsCloud())){//非云知识,即获取当前公司的知识
			ManageUserBean manageUserBean = manageUserService.findUserById(userId);
			companyId = manageUserBean.getCompanyId();
			subCompanyId = manageUserBean.getSubCompanyId();
		}
		paramsVo.setCompanyId(companyId);
		paramsVo.setSubCompanyId(subCompanyId);
		List<KLCategoryBean> subList = null;
		KLCategoryBean bean = new KLCategoryBean();
		String categoryId = paramsVo.getCategoryId();
		if (categoryId != null && !"".equals(categoryId)) {
			bean.setCategoryId(Integer.parseInt(categoryId));
			subList = knowledgeManagerDaoMapper.getKlCategoryByParentId(categoryId);
			subList.add(bean);
		}
		paramsVo.setSubList(subList);
		return knowledgeManagerDaoMapper.getKnowledgeByCategoryCount(paramsVo);
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getKnowledgeOrderByEvaluate(java.lang.String, java.lang.String) <BR>
	 *      Method name: getKnowledgeOrderByEvaluate <BR>
	 *      Description: 根据类型获取知识并按评价排序 <BR>
	 *      Remark: <BR>
	 * @param categoryId
	 * @param orderType
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KnowledgeOtherVo> getKnowledgeOrderByEvaluate(KlByCatParamsVo paramsVo) throws Exception {
		String userId = paramsVo.getUserId();
		int companyId =Constant.PULIAN_COMPANY_ID;
		int subCompanyId =Constant.PULIAN_COMPANY_ID;
		if("1".equals(paramsVo.getIsCloud())){//非云知识,即获取当前公司的知识
			ManageUserBean manageUserBean = manageUserService.findUserById(userId);
			companyId = manageUserBean.getCompanyId();
			subCompanyId = manageUserBean.getSubCompanyId();
		}
		paramsVo.setCompanyId(companyId);
		paramsVo.setSubCompanyId(subCompanyId);
		List<KLCategoryBean> subList = null;
		KLCategoryBean bean = new KLCategoryBean();
		String categoryId = paramsVo.getCategoryId();
		if (categoryId != null && !"".equals(categoryId)) {
			bean.setCategoryId(Integer.parseInt(categoryId));
			subList = knowledgeManagerDaoMapper.getKlCategoryByParentId(categoryId);
			subList.add(bean);
		}
		paramsVo.setSubList(subList);
		List<KnowledgeOtherVo> list = knowledgeManagerDaoMapper.getKnowledgeOrderByEvaluate(paramsVo);
		return convertList(list);
	}

	@Override
	public int getKnowledgeOrderByEvaluateCount(KlByCatParamsVo paramsVo) throws Exception {
		String userId = paramsVo.getUserId();
		int companyId =Constant.PULIAN_COMPANY_ID;
		int subCompanyId =Constant.PULIAN_COMPANY_ID;
		if("1".equals(paramsVo.getIsCloud())){//非云知识,即获取当前公司的知识
			ManageUserBean manageUserBean = manageUserService.findUserById(userId);
			companyId = manageUserBean.getCompanyId();
			subCompanyId = manageUserBean.getSubCompanyId();
		}
		paramsVo.setCompanyId(companyId);
		paramsVo.setSubCompanyId(subCompanyId);
		List<KLCategoryBean> subList = null;
		KLCategoryBean bean = new KLCategoryBean();
		String categoryId = paramsVo.getCategoryId();
		if (categoryId != null && !"".equals(categoryId)) {
			bean.setCategoryId(Integer.parseInt(categoryId));
			subList = knowledgeManagerDaoMapper.getKlCategoryByParentId(categoryId);
			subList.add(bean);
		}
		paramsVo.setSubList(subList);
		return knowledgeManagerDaoMapper.getKnowledgeOrderByEvaluateCount(paramsVo);
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getKnowledgeOrderByHot(java.lang.String, java.lang.String) <BR>
	 *      Method name: getKnowledgeOrderByHot <BR>
	 *      Description: 根据类型获取知识并按热门排序 <BR>
	 *      Remark: <BR>
	 * @param categoryId
	 * @param orderType
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KnowledgeOtherVo> getKnowledgeOrderByHot(KlByCatParamsVo paramsVo) throws Exception {
		String userId = paramsVo.getUserId();
		int companyId =Constant.PULIAN_COMPANY_ID;
		int subCompanyId =Constant.PULIAN_COMPANY_ID;
		if("1".equals(paramsVo.getIsCloud())){//非云知识,即获取当前公司的知识
			ManageUserBean manageUserBean = manageUserService.findUserById(userId);
			companyId = manageUserBean.getCompanyId();
			subCompanyId = manageUserBean.getSubCompanyId();
		}
		paramsVo.setCompanyId(companyId);
		paramsVo.setSubCompanyId(subCompanyId);
		List<KLCategoryBean> subList = null;
		KLCategoryBean bean = new KLCategoryBean();
		String categoryId = paramsVo.getCategoryId();
		if (categoryId != null && !"".equals(categoryId)) {
			bean.setCategoryId(Integer.parseInt(categoryId));
			subList = knowledgeManagerDaoMapper.getKlCategoryByParentId(categoryId);
			subList.add(bean);
		}
		paramsVo.setSubList(subList);
		List<KnowledgeOtherVo> list = knowledgeManagerDaoMapper.getKnowledgeOrderByHot(paramsVo);
		return convertList(list);
	}

	@Override
	public int getKnowledgeOrderByHotCount(KlByCatParamsVo paramsVo) throws Exception {
		String userId = paramsVo.getUserId();
		int companyId =Constant.PULIAN_COMPANY_ID;
		int subCompanyId =Constant.PULIAN_COMPANY_ID;
		if("1".equals(paramsVo.getIsCloud())){//非云知识,即获取当前公司的知识
			ManageUserBean manageUserBean = manageUserService.findUserById(userId);
			companyId = manageUserBean.getCompanyId();
			subCompanyId = manageUserBean.getSubCompanyId();
		}
		paramsVo.setCompanyId(companyId);
		paramsVo.setSubCompanyId(subCompanyId);
		List<KLCategoryBean> subList = null;
		KLCategoryBean bean = new KLCategoryBean();
		String categoryId = paramsVo.getCategoryId();
		if (categoryId != null && !"".equals(categoryId)) {
			bean.setCategoryId(Integer.parseInt(categoryId));
			subList = knowledgeManagerDaoMapper.getKlCategoryByParentId(categoryId);
			subList.add(bean);
		}
		paramsVo.setSubList(subList);
		return knowledgeManagerDaoMapper.getKnowledgeOrderByHotCount(paramsVo);
	}

	/**
	 * 
	 * @author JFTT)chenrui Method name: convertList <BR>
	 *         Description: 共用方法：转换用户id=>用户信息 <BR>
	 *         Remark: <BR>
	 * @param vo
	 * @return List<KnowledgeOtherVo><BR>
	 * @throws Exception
	 */
	private List<KnowledgeOtherVo> convertList(List<KnowledgeOtherVo> vo) throws Exception {
		for (KnowledgeOtherVo temp : vo) {
			int createUserId = temp.getCreateUserId();
			ManageUserBean userBean = manageUserService.findUserById(createUserId + "");
			temp.setCreateUserName(userBean.getName());
			temp.setHeadImg(userBean.getHeadPhoto());
		}
		return vo;
	}
	

	@Override
	public List<KnowledgeOtherVo> getFullSearchKnowledge(SearchKlByNameVo paramVo) throws Exception {
		String userId = paramVo.getUserId();
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
    	String searchName = paramVo.getKnowledgeName();
    	String extendName = paramVo.getExtendName();
    	String page = paramVo.getPage();
		String pageSize = paramVo.getPageSize();
		Integer n_page = Integer.parseInt(page);
		Integer n_pageSize = Integer.parseInt(pageSize);
		Integer fromNum = n_pageSize * (n_page - 1);
		//String[] filters = {"COMMITTIME:[\"2000-01-01 00:00:00\" TO *]"};
    	List<TermQuery> termQueryList = new ArrayList<TermQuery>();
    	TermQuery query = new TermQuery(QueryType.must.getValue(), null, searchName, 1F);
    	termQueryList.add(query);
    	
    	if(extendName!=null && !extendName.isEmpty()){
    		TermQuery query2 = new TermQuery(QueryType.must.getValue(), "extendName", extendName, 1F);
    		termQueryList.add(query2);
    	}
    	
    	TermQuery query3 = new TermQuery(QueryType.must.getValue(), "companyId", companyId+"", 1F);
		termQueryList.add(query3);
		
		TermQuery query4 = new TermQuery(QueryType.must.getValue(), "subCompanyId", subCompanyId+"", 1F);
		termQueryList.add(query4);
    	
		PageSort pageSort = new PageSort(fromNum, n_pageSize, "createTime", "desc");
    	
    	BackArray backArray = ElastisearchUtil.search(Constant.ELASTICSEARCH_INDEX_knowledge,"kl_knowledge", null, termQueryList, pageSort);
    	System.out.println(backArray.getTotal());
    	System.out.println(backArray.getList());
    	
    	
    	List<KnowledgeOtherVo> list = new ArrayList<KnowledgeOtherVo>();
    	for(Object obj : backArray.getList()){
    		JSONObject jo = JSONObject.fromObject(obj);
    		String knowledgeId = jo.get("knowledgeId") +"";
    		KnowledgeOtherVo vo = getFullSearchKnowledgeById(knowledgeId);
    		list.add(vo);
    	}
    	return convertList(list);
	}

	
	@Override
	public int getFullSearchKnowledgeCount(SearchKlByNameVo paramVo) throws Exception {
		String userId = paramVo.getUserId();
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
    	String searchName = paramVo.getKnowledgeName();
    	String extendName = paramVo.getExtendName();
    	List<TermQuery> termQueryList = new ArrayList<TermQuery>();
    	TermQuery query = new TermQuery(QueryType.must.getValue(), null, searchName, 1F);
    	termQueryList.add(query);
    	if(extendName!=null && !extendName.isEmpty()){
    		TermQuery query2 = new TermQuery(QueryType.must.getValue(), "extendName", extendName, 1F);
    		termQueryList.add(query2);
    	}
    	//String[] filters = {"COMMITTIME:[\"2000-01-01 00:00:00\" TO *]"};
    	TermQuery query3 = new TermQuery(QueryType.must.getValue(), "companyId", companyId+"", 1F);
		termQueryList.add(query3);
		
		TermQuery query4 = new TermQuery(QueryType.must.getValue(), "subCompanyId", subCompanyId+"", 1F);
		termQueryList.add(query4);
    	
		PageSort pageSort = new PageSort(0, 10, "createTime", "desc");
    	
    	BackArray backArray = ElastisearchUtil.search(Constant.ELASTICSEARCH_INDEX_knowledge,"kl_knowledge", null, termQueryList, pageSort);
    	System.out.println(backArray.getTotal());
    	System.out.println(backArray.getList());
    	Map<String, Object> resultmap = new HashMap<String, Object>();
    	resultmap.put("count", backArray.getTotal());
    	resultmap.put("data", backArray.getList());
    	return backArray.getTotal();
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getUploadingKnowledge() <BR>
	 *      Method name: getUploadingKnowledge <BR>
	 *      Description: 获取查看上传知识页面数据信息 <BR>
	 *      Remark: <BR>
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public KnowledgeOtherVo getUploadingKnowledge(String knowledgeId) throws Exception {
		return convertList(knowledgeManagerDaoMapper.getUploadingKnowledge(knowledgeId)).get(0);
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getEvaluates(java.lang.String) <BR>
	 *      Method name: getEvaluates <BR>
	 *      Description: 获取知识评价信息 <BR>
	 *      Remark: <BR>
	 * @param knowledgeId
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KlEvaluateBean> getEvaluates(String knowledgeId) throws Exception {

		return klEvaluateDaoMapper.getEvaluates(knowledgeId);
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getRecommends(java.lang.String) <BR>
	 *      Method name: getRecommends <BR>
	 *      Description: 获取相关推荐知识 <BR>
	 *      Remark: <BR>
	 * @param userId
	 * @param knowledgeId
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KnowledgeBean> getRecommends(String userId, String knowledgeId) throws Exception {
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		return knowledgeManagerDaoMapper.getRecommends(userId, knowledgeId, companyId, subCompanyId);
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getKnowledgeContributor() <BR>
	 *      Method name: getKnowledgeContributor <BR>
	 *      Description: 获取突出贡献者排行信息 <BR>
	 *      Remark: <BR>
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KlContributorVo> getKnowledgeContributor(String userId) throws Exception {
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		List<KlContributorVo> list = knowledgeManagerDaoMapper.getKnowledgeContributor(companyId, subCompanyId);
		for (KlContributorVo vo : list) {
			int createUserId = vo.getCreateUserId();
			ManageUserBean userBean = manageUserService.findUserById(createUserId + "");
			if (userBean == null) {
				continue;
			}
			String userName = userBean.getName();
			String headImg = userBean.getHeadPhoto();
			vo.setHeadImg(headImg);
			vo.setUserName(userName);
		}
		return list;
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#searchKnowledge(java.lang.String) <BR>
	 *      Method name: searchKnowledge <BR>
	 *      Description: 根据条件筛选知识 <BR>
	 *      Remark: <BR>
	 * @param knowledgeId
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KnowledgeBean> searchKnowledge(String knowledgeName, String fileType,String userId) throws Exception {
		ManageUserBean userBean = manageUserService.findUserById(userId);
		int companyId = userBean.getCompanyId();
		int subCompanyId = userBean.getSubCompanyId();
		return knowledgeManagerDaoMapper.searchKnowledge(knowledgeName, fileType,companyId,subCompanyId);
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getGuessLike(java.lang.String, java.lang.String) <BR>
	 *      Method name: getGuessLike <BR>
	 *      Description: 获取查看上传知识->猜你喜欢的数据 <BR>
	 *      Remark: <BR>
	 * @param knowledgeId
	 * @param userId
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KnowledgeBean> getGuessLike(String knowledgeId, String userId) throws Exception {
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		return knowledgeManagerDaoMapper.getGuessLike(knowledgeId, userId, companyId, subCompanyId);
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getMyKnowledgeForCollectDownload(java.lang.String, java.lang.String) <BR>
	 *      Method name: getMyKnowledgeForCollectDownload <BR>
	 *      Description: 获取我收藏下载的知识 <BR>
	 *      Remark: <BR>
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KnowledgeOtherVo> getMyKnowledgeForCollectDownload(SearchKlVo searchKlVo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String fromUserName = searchKlVo.getFromUserName();// 获取搜索的上传者名称
		List<ManageUserBean> userList = null;
		if (fromUserName != null && fromUserName.trim() != "") {
			map.put("name", fromUserName);
			userList = manageUserService.findUserByListCondition(map);
			searchKlVo.setUserList(userList);
			if (userList.size() == 0) {
				return new ArrayList<KnowledgeOtherVo>();
			}
		}
		List<KnowledgeOtherVo> list = knowledgeManagerDaoMapper.getMyKnowledgeForCollectDownload(searchKlVo);

		for (KnowledgeOtherVo vo : list) {
			int createUserId = vo.getCreateUserId();
			String createName = "";
			ManageUserBean userBean = manageUserService.findUserById(createUserId + "");
			if (userBean != null) {
				createName = userBean.getName();
			}
			vo.setCreateUserName(createName);
		}
		return list;
	}
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#downCreateKl(java.lang.String) <BR>
	 * Method name: downCreateKl <BR>
	 * Description: 下载创建的知识 <BR>
	 * Remark: <BR>
	 * @param knowledgeText
	 * @throws Exception  <BR>
	 */
	@Override
	public KnowledgeOtherVo downCreateKl(String klId) throws Exception {
		return knowledgeManagerDaoMapper.getUploadingKnowledge(klId).get(0);
	}

	@Override
	public int getMyKnowledgeForCollectDownloadCount(SearchKlVo searchKlVo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String fromUserName = searchKlVo.getFromUserName();// 获取搜索的上传者名称
		List<ManageUserBean> userList = null;
		if (fromUserName != null && fromUserName.trim() != "") {
			map.put("name", fromUserName);
			userList = manageUserService.findUserByListCondition(map);
			searchKlVo.setUserList(userList);
			if (userList.size() == 0) {
				return 0;
			}
		}
		return knowledgeManagerDaoMapper.getMyKnowledgeForCollectDownloadCount(searchKlVo);
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#delKlById(java.lang.String) <BR>
	 *      Method name: delKlById <BR>
	 *      Description: 根据id删除对应知识 <BR>
	 *      Remark: <BR>
	 * @param knowledgeId
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public void delKlById(String knowledgeId) throws Exception {
		knowledgeManagerDaoMapper.delKlById(knowledgeId);
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#toPublic(java.lang.String) <BR>
	 *      Method name: toPublic <BR>
	 *      Description:公开知识操作 <BR>
	 *      Remark: <BR>
	 * @param knowledgeId
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public void toPublic(String knowledgeId, String userId) throws Exception {
		knowledgeManagerDaoMapper.toPublic(knowledgeId);
		// 知识发布完成后，需要进行发站内信
		KnowledgeBean klBean = knowledgeManagerDaoMapper.queryKnowledgeDetail(Integer.parseInt(knowledgeId));
		ManageUserBean userBean = manageUserService.findUserByIdCondition(String.valueOf(klBean.getCreateUserId()));// 拿到用户对象

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subCompanyId", klBean.getSubCompanyId());
		map.put("companyId", klBean.getCompanyId());
		map.put("url", "knowledge/auditList.action");

		List<Integer> ids = knowledgeManagerDaoMapper.queryUserIdList(map);
		SimpleDateFormat formart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (int id : ids) {

			ManageUserBean manageUserBean = manageUserService.findUserByIdCondition(String.valueOf(id));// 拿到用户对象
			if (manageUserBean != null) {
				// 组装数据
				ManageNoticeBean bean = new ManageNoticeBean();

				bean.setTitle("知识库上传审批提醒");
				// 组装消息主体
				StringBuffer strBuff = new StringBuffer();
				strBuff.append("尊敬的" + manageUserBean.getName() + "：</em><br/>");
				strBuff.append("&nbsp;&nbsp;&nbsp;&nbsp;有新上传的知识需要您审批，请尽快审批处理，详情如下：</em><br/>");
				strBuff.append("&nbsp;&nbsp;&nbsp;&nbsp;知识名称：" + klBean.getKnowledgeName() + "</em><br/>");
				strBuff.append("&nbsp;&nbsp;&nbsp;&nbsp;知识库分类：" + klBean.getCategoryName() + "</em><br/>");
				strBuff.append("&nbsp;&nbsp;&nbsp;&nbsp;上传部门：" + userBean.getSubCompanyName() + (userBean.getDeptName() == null ? "" : ("." + userBean.getDeptName())) + "</em><br/>");
				strBuff.append("&nbsp;&nbsp;&nbsp;&nbsp;上传者：" + userBean.getName() + "</em><br/>");
				strBuff.append("&nbsp;&nbsp;&nbsp;&nbsp;上传时间：" + formart.format(klBean.getCreateTime()) + "</em><br/>");
				bean.setContent(strBuff.toString());

				bean.setSendUserId(Integer.parseInt(userId));
				bean.setRecUserId(id);
				bean.setIsSystem(1);

				manageNoticeService.insertNotice(bean);
			}
		}
	}

	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#updateKl(com.jftt.wifi.bean.KnowledgeBean) <BR>
	 *      Method name: updateKl <BR>
	 *      Description: 修改知识 <BR>
	 *      Remark: <BR>
	 * @param knowledgeBean
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public void updateKl(KnowledgeBean knowledgeBean) throws Exception {
		String tags = knowledgeBean.getTags();
		if (tags.startsWith(",")) {// 判断是否以","开头
			tags = tags.substring(1, tags.length());// 去除头部","
		}
		if (tags.endsWith(",")) {// 判断是否以","结尾
			tags = tags.substring(0, tags.length() - 1);// 去除尾部","
		}
		String[] tagArr = tags.trim().split(",");
		int knowledgeId = knowledgeBean.getKnowledgeId();
		// 将此知识关联标签记录清除
		klResourceToTagsDaoMapper.removeByKlId(knowledgeId);
		// 判断当期标签是否已存在
		for (String tagName : tagArr) {
			int count = klTagsDaoMapper.isExist(tagName);
			Integer tagId = null;
			if (count == 0) {// 不存在则添加
				tagId = klTagsDaoMapper.add(tagName);
			} else {
				tagId = klTagsDaoMapper.getIdByName(tagName);
			}
			// 更新标签关联记录
			klResourceToTagsDaoMapper.add(knowledgeId, tagId);
		}
		knowledgeManagerDaoMapper.updateKl(knowledgeBean);
	}

	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getCategoryInfoById(java.lang.String) <BR>
	 *      Method name: getCategoryInfoById <BR>
	 *      Description: 根据类别id获取对应类别信息 <BR>
	 *      Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public KLCategoryVo getCategoryInfoById(String categoryId) throws Exception {
		KLCategoryVo vo = null;
		if (categoryId != null && !"".equals(categoryId)) {
			vo = knowledgeManagerDaoMapper.getByCategoryIdWith(categoryId);
			List<KLCategoryBean> parentList = new ArrayList<KLCategoryBean>();
			int parentId = vo.getParentId();
			while (parentId != 0) {
				KLCategoryBean bean = knowledgeManagerDaoMapper.getByCategoryId(parentId + "");
				parentId = bean.getParentId();
				parentList.add(bean);
			}
			vo.setSubCategory(parentList);
		}
		return vo;
	}

	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#judgeEvaluate(java.lang.String, java.lang.String) <BR>
	 *      Method name: judgeEvaluate <BR>
	 *      Description: 判断当前资源是否已评价 <BR>
	 *      Remark: <BR>
	 * @param userId
	 * @param knowledgeId
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public int judgeEvaluate(String userId, String knowledgeId) throws Exception {
		return klEvaluateDaoMapper.judgeEvaluate(userId, knowledgeId);
	}

	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#judgeCollecDownload(java.lang.String, java.lang.String) <BR>
	 *      Method name: judgeCollecDownload <BR>
	 *      Description:判断当前资源的是否收藏、下载 <BR>
	 *      Remark: <BR>
	 * @param userId
	 * @param knowledgeId
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public judgeColDownReturnVo judgeCollecDownload(String userId, String knowledgeId) throws Exception {
		return klCollectDownloadDaoMapper.judgeCollecDownload(userId, knowledgeId);
	}

	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getMyUploadKnowledge(com.jftt.wifi.bean.vo.SearchKlVo) <BR>
	 *      Method name: getMyUploadKnowledge <BR>
	 *      Description:我的知识中心-获取我上传的知识 <BR>
	 *      Remark: <BR>
	 * @param searchKlVo
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KnowledgeOtherVo> getMyUploadKnowledge(SearchKlVo searchKlVo) throws Exception {
		Integer isOpen = searchKlVo.getIsOpen();
		String status = searchKlVo.getStatus();
		if("1".equals(status) && isOpen==0){//私有 且未审核的筛选 直接返回null
			return null;
		}
		return knowledgeManagerDaoMapper.getMyUploadKnowledge(searchKlVo);
	}

	@Override
	public int getMyUploadKnowledgeCount(SearchKlVo searchKlVo) throws Exception {
		Integer isOpen = searchKlVo.getIsOpen();
		String status = searchKlVo.getStatus();
		if("1".equals(status) && isOpen==0){//私有 且未审核的筛选 直接返回0
			return 0;
		}
		return knowledgeManagerDaoMapper.getMyUploadKnowledgeCount(searchKlVo);
	}

	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getOthersKnowledge(com.jftt.wifi.bean.vo.SearchKlVo) <BR>
	 *      Method name: getOthersKnowledge <BR>
	 *      Description: 获取他人的知识<BR>
	 *      Remark: <BR>
	 * @param searchKlVo
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KnowledgeOtherVo> getOthersKnowledge(SearchKlVo searchKlVo) throws Exception {
		String userId = searchKlVo.getUserId();
		ManageUserBean userBean = manageUserService.findUserById(userId);
		searchKlVo.setCompanyId(userBean.getCompanyId());
		searchKlVo.setSubCompanyId(userBean.getSubCompanyId());
		return knowledgeManagerDaoMapper.getOthersKnowledge(searchKlVo);
	}

	@Override
	public int getOthersKnowledgeCount(SearchKlVo searchKlVo) throws Exception {
		String userId = searchKlVo.getUserId();
		ManageUserBean userBean = manageUserService.findUserById(userId);
		searchKlVo.setCompanyId(userBean.getCompanyId());
		searchKlVo.setSubCompanyId(userBean.getSubCompanyId());
		return knowledgeManagerDaoMapper.getOthersKnowledgeCount(searchKlVo);
	}
	
	
	@Override
	public KnowledgeOtherVo getFullSearchKnowledgeById(String knowledgeId) {
		return knowledgeManagerDaoMapper.getFullSearchKnowledgeById(knowledgeId);
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getSearchKnowledge(java.lang.String, java.lang.String, java.lang.String, long, java.lang.String) <BR>
	 *      Method name: getSearchKnowledge <BR>
	 *      Description: 获取搜索的知识（默认按时间排序） <BR>
	 *      Remark: <BR>
	 * @param extendName
	 * @param knowledgeName
	 * @param orderType
	 * @param fromNum
	 * @param pageSize
	 * @return <BR>
	 * @throws Exception
	 */
	@Override
	public List<KnowledgeOtherVo> getSearchKnowledge(SearchKlByNameVo paramVo) throws Exception {
		String userId = paramVo.getUserId();
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		paramVo.setCompanyId(companyId);
		paramVo.setSubCompanyId(subCompanyId);
		List<KnowledgeOtherVo> list = knowledgeManagerDaoMapper.getSearchKnowledge(paramVo);

		return convertList(list);
	}

	@Override
	public int getSearchKnowledgeCount(SearchKlByNameVo paramVo) throws Exception {
		String userId = paramVo.getUserId();
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		paramVo.setCompanyId(companyId);
		paramVo.setSubCompanyId(subCompanyId);

		return knowledgeManagerDaoMapper.getSearchKnowledgeCount(paramVo);
	}

	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getSearchKnowledgeByEvaluate(com.jftt.wifi.bean.vo.SearchKlByNameVo) <BR>
	 *      Method name: getSearchKnowledgeByEvaluate <BR>
	 *      Description: 获取搜索的知识- 按评价排序 <BR>
	 *      Remark: <BR>
	 * @param paramVo
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KnowledgeOtherVo> getSearchKnowledgeByEvaluate(SearchKlByNameVo paramVo) throws Exception {
		String userId = paramVo.getUserId();
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		paramVo.setCompanyId(companyId);
		paramVo.setSubCompanyId(subCompanyId);
		List<KnowledgeOtherVo> list = knowledgeManagerDaoMapper.getSearchKnowledgeByEvaluate(paramVo);
		return convertList(list);
	}

	@Override
	public int getSearchKnowledgeByEvaluateCount(SearchKlByNameVo paramVo) throws Exception {
		String userId = paramVo.getUserId();
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		paramVo.setCompanyId(companyId);
		paramVo.setSubCompanyId(subCompanyId);
		return knowledgeManagerDaoMapper.getSearchKnowledgeByEvaluateCount(paramVo);
	}

	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getSearchKnowledgeByHot(com.jftt.wifi.bean.vo.SearchKlByNameVo) <BR>
	 *      Method name: getSearchKnowledgeByHot <BR>
	 *      Description:获取搜索的知识- 按热门排序 <BR>
	 *      Remark: <BR>
	 * @param paramVo
	 * @return
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public List<KnowledgeOtherVo> getSearchKnowledgeByHot(SearchKlByNameVo paramVo) throws Exception {
		String userId = paramVo.getUserId();
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		paramVo.setCompanyId(companyId);
		paramVo.setSubCompanyId(subCompanyId);

		List<KnowledgeOtherVo> list = knowledgeManagerDaoMapper.getSearchKnowledgeByHot(paramVo);
		return convertList(list);
	}

	@Override
	public int getSearchKnowledgeByHotCount(SearchKlByNameVo paramVo) throws Exception {
		String userId = paramVo.getUserId();
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		paramVo.setCompanyId(companyId);
		paramVo.setSubCompanyId(subCompanyId);
		return knowledgeManagerDaoMapper.getSearchKnowledgeByHotCount(paramVo);
	}

	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#toDeleteMoreWithMy(java.lang.String[]) <BR>
	 *      Method name: 批量删除-我上传的知识 <BR>
	 *      Description: <BR>
	 *      Remark: <BR>
	 * @param ids
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public void toDeleteMoreWithMy(String[] ids) throws Exception {
		knowledgeManagerDaoMapper.toDeleteMoreWithMy(ids);
	}

	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#toDelColDown(java.lang.String[], java.lang.String) <BR>
	 *      Method name: toDelColDown <BR>
	 *      Description: 批量删除-我收藏下载的知识 <BR>
	 *      Remark: <BR>
	 * @param ids
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public void toDelColDown(String[] ids) throws Exception {
		klCollectDownloadDaoMapper.toDelColDown(ids);
	}

	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#doDelColDow(java.lang.String, java.lang.String) <BR>
	 *      Method name: doDelColDow <BR>
	 *      Description: 单个删除 知识收藏下载的记录 <BR>
	 *      Remark: <BR>
	 * @throws Exception
	 *             <BR>
	 */
	@Override
	public void doDelColDow(String id) throws Exception {

		klCollectDownloadDaoMapper.doDelColDow(id);
	}
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getPeopleInfo(java.lang.String) <BR>
	 * Method name: getPeopleInfo <BR>
	 * Description: 根据id获取人员基础信息  <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public ManageUserBean getPeopleInfo(String userId) throws Exception {
		return manageUserService.findUserById(userId);
	}


	
	/** chenrui end */
	
	
	/** luyunlong start */

	/**
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeLibraryInfoService#getAccidentCategory(java.util.Map) <BR>
	 * Method name: getAccidentCategory <BR>
	 * Description: 获取事故分类 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  <BR>
	*/
	@Override
	public List<KLCategoryBean> getAccidentCategory(Map<String, Object> param) {
		return knowledgeManagerDaoMapper.getAccidentCategory(param);
	}

	
	/** luyunlong end */
}
