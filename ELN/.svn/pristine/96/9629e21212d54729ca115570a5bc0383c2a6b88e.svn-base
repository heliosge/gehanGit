/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KnowledgeLibraryService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-15        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.jftt.wifi.bean.KLCategoryBean;
import com.jftt.wifi.bean.KlCollectDownloadBean;
import com.jftt.wifi.bean.KlEvaluateBean;
import com.jftt.wifi.bean.KnowledgeBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.KLCategoryVo;
import com.jftt.wifi.bean.vo.KlByCatParamsVo;
import com.jftt.wifi.bean.vo.KlContributorVo;
import com.jftt.wifi.bean.vo.KnowledgeOtherVo;
import com.jftt.wifi.bean.vo.SearchKlByNameVo;
import com.jftt.wifi.bean.vo.SearchKlVo;
import com.jftt.wifi.bean.vo.judgeColDownReturnVo;
import com.jftt.wifi.bean.vo.klCountsVo;

/**
 * class name:KnowledgeLibraryService <BR>
 * class description: 知识库 <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015-7-15
 * @author JFTT)chenrui
 */
public interface KnowledgeLibraryInfoService {
	/** chenrui start */
	/**
	 * 
	 * Method name: giveEvaluate <BR>
	 * Description: 知识评价 <BR>
	 * Remark: <BR>
	 * 
	 * @param klEvaluateBean
	 * @throws Exception
	 *             void<BR>
	 */
	public void giveEvaluate(KlEvaluateBean klEvaluateBean) throws Exception;

	/**
	 * 
	 * Method name: addCollectAndDownload <BR>
	 * Description: 收藏下载操作 <BR>
	 * Remark: <BR>
	 * 
	 * @param klCollectDownloadBean
	 * @throws Exception
	 *             void<BR>
	 */
	public void addCollectAndDownload(KlCollectDownloadBean klCollectDownloadBean) throws Exception;

	/**
	 * 
	 * Method name: getKnowledgeCategory <BR>
	 * Description: 获取知识分类 <BR>
	 * Remark: <BR>
	 * 
	 * @return
	 * @throws Exception
	 *             List<KLCategoryBean><BR>
	 */
	public List<KLCategoryVo> getKnowledgeCategory(String userId,String isCloud) throws Exception;

	/**
	 * 
	 * Method name: getKnowledgeCount <BR>
	 * Description: 获取当前知识资源数量统计 <BR>
	 * Remark: <BR>
	 * 
	 * @return List<klCountsVo><BR>
	 */
	public List<klCountsVo> getKnowledgeCount(String userId) throws Exception;

	/**
	 * 
	 * Method name: getMyKnowledgeCount <BR>
	 * Description: 获取我贡献的知识数目 <BR>
	 * Remark: <BR>
	 * 
	 * @return int<BR>
	 */
	public int getMyKnowledgeCount(String userId) throws Exception;

	/**
	 * 
	 * Method name: getDepartmentKnowledge <BR>
	 * Description: 获取部门知识 <BR>
	 * Remark: <BR>
	 * 
	 * @param userId
	 * @return List<KnowledgeBean><BR>
	 */
	public List<KnowledgeOtherVo> getDepartmentKnowledge(String userId) throws Exception;

	/**
	 * 
	 * Method name: getRecommendKnowledge <BR>
	 * Description: 获取推荐知识 <BR>
	 * Remark: <BR>
	 * 
	 * @return List<KnowledgeBean><BR>
	 */
	public List<KnowledgeOtherVo> getRecommendKnowledge(String userId) throws Exception;

	/**
	 * 
	 * Method name: getKnowledgeByCategory <BR>
	 * Description: 根据类别获取知识 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @return
	 * @throws Exception
	 *             List<KnowledgeBean><BR>
	 */
	public int getKnowledgeByCategoryCount(KlByCatParamsVo paramsVo) throws Exception;

	public List<KnowledgeOtherVo> getKnowledgeByCategory(KlByCatParamsVo paramsVo) throws Exception;

	/**
	 * 
	 * Method name: getKnowledgeOrderByEvaluate <BR>
	 * Description: 根据类型获取知识并按评价排序 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @param orderType
	 * @return List<HashMap<String,Object>><BR>
	 */
	public List<KnowledgeOtherVo> getKnowledgeOrderByEvaluate(KlByCatParamsVo paramsVo) throws Exception;

	public int getKnowledgeOrderByEvaluateCount(KlByCatParamsVo paramsVo) throws Exception;

	/**
	 * 
	 * Method name: getKnowledgeOrderByHot <BR>
	 * Description: 根据类型获取知识并按热门排序 <BR>
	 * Remark: <BR>
	 * 
	 * @param categoryId
	 * @param orderType
	 */
	public List<KnowledgeOtherVo> getKnowledgeOrderByHot(KlByCatParamsVo paramsVo) throws Exception;

	public int getKnowledgeOrderByHotCount(KlByCatParamsVo paramsVo) throws Exception;

	/**
	 * 
	 * Method name: getUploadingKnowledge <BR>
	 * Description: 获取查看上传知识页面数据信息 <BR>
	 * Remark: <BR>
	 * 
	 */
	public KnowledgeOtherVo getUploadingKnowledge(String knowledgeId) throws Exception;

	/**
	 * 
	 * Method name: getEvaluates <BR>
	 * Description: 获取知识评价信息 <BR>
	 * Remark: <BR>
	 * 
	 * @param knowledgeId
	 * @return List<KlEvaluateBean><BR>
	 */
	public List<KlEvaluateBean> getEvaluates(String knowledgeId) throws Exception;
	/**
	 * 
	 * Method name: getRecommends <BR>
	 * Description: 获取相关推荐知识 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param knowledgeId
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeBean> getRecommends(String userId,String knowledgeId) throws Exception;
	/**
	 * 
	 * Method name: getKnowledgeContributor <BR>
	 * Description: 获取突出贡献者排行信息 <BR>
	 * Remark: <BR>
	 * @return  List<KlContributorVo><BR>
	 */
	public List<KlContributorVo> getKnowledgeContributor(String userId) throws Exception;
	/**
	 * 
	 * Method name: searchKnowledge <BR>
	 * Description: 根据条件筛选知识  <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeBean> searchKnowledge(String knowledgeName,String fileType,String userId)throws Exception;
	/**
	 * 
	 * Method name: getGuessLike <BR>
	 * Description: 获取查看上传知识->猜你喜欢的数据 <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeBean> getGuessLike(String knowledgeId,String userId)throws Exception;
	/**
	 * 
	 * Method name: getMyKnowledgeForCollectDownload <BR>
	 * Description: 获取我收藏下载的知识 <BR>
	 * Remark: <BR>
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeOtherVo> getMyKnowledgeForCollectDownload(SearchKlVo searchKlVo)throws Exception;
	public int getMyKnowledgeForCollectDownloadCount(SearchKlVo searchKlVo)throws Exception;
	/**
	 * 
	 * Method name: delKlById <BR>
	 * Description: 根据id删除对应知识 <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @throws Exception  void<BR>
	 */
	public void delKlById(String knowledgeId) throws Exception;
	/**
	 * 
	 * Method name: toPublic <BR>
	 * Description: 公开知识操作 <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @throws Exception  void<BR>
	 */
	public void toPublic(String knowledgeId,String userId ) throws Exception;
	/**
	 * 
	 * Method name: updateKl <BR>
	 * Description: 修改知识 <BR>
	 * Remark: <BR>
	 * @param knowledgeBean  void<BR>
	 */
	public void updateKl(KnowledgeBean knowledgeBean) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getCategoryInfoById <BR>
	 * Description: 根据类别id获取对应类别信息 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws Exception  KLCategoryVo<BR>
	 */
	public KLCategoryVo getCategoryInfoById(String categoryId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: judgeEvaluate <BR>
	 * Description: 判断当前资源是否已评价 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param knowledgeId  void<BR>
	 */
	public int judgeEvaluate(String userId, String knowledgeId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: judgeCollecDownload <BR>
	 * Description: 判断当前资源的是否收藏、下载 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param knowledgeId
	 */
	public judgeColDownReturnVo judgeCollecDownload(String userId, String knowledgeId) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMyUploadKnowledge <BR>
	 * Description: 我的知识中心-获取我上传的知识 <BR>
	 * Remark: <BR>
	 * @param searchKlVo
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeOtherVo> getMyUploadKnowledge(SearchKlVo searchKlVo) throws Exception;
	public int getMyUploadKnowledgeCount(SearchKlVo searchKlVo) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getSearchKnowledge <BR>
	 * Description: 获取搜索的知识（默认按时间排序） <BR>
	 * Remark: <BR>
	 * @param extendName
	 * @param knowledgeName
	 * @param orderType
	 * @param fromNum
	 * @param pageSize
	 * @return  List<KnowledgeOtherVo><BR>
	 */
	public List<KnowledgeOtherVo> getSearchKnowledge(SearchKlByNameVo paramVo) throws Exception;
	public int getSearchKnowledgeCount(SearchKlByNameVo paramVo) throws Exception;
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getSearchKnowledgeByEvaluate <BR>
	 * Description: 获取搜索的知识- 按评价排序  <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return
	 * @throws Exception  List<KnowledgeOtherVo><BR>
	 */
	public List<KnowledgeOtherVo> getSearchKnowledgeByEvaluate(SearchKlByNameVo paramVo) throws Exception;

	public int getSearchKnowledgeByEvaluateCount(SearchKlByNameVo paramVo) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getSearchKnowledgeByHot <BR>
	 * Description: 获取搜索的知识- 按热门排序 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return
	 * @throws Exception  List<KnowledgeOtherVo><BR>
	 */
	public List<KnowledgeOtherVo> getSearchKnowledgeByHot(SearchKlByNameVo paramVo) throws Exception;

	public int getSearchKnowledgeByHotCount(SearchKlByNameVo paramVo) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toDeleteMoreWithMy <BR>
	 * Description: 批量删除-我上传的知识 <BR>
	 * Remark: <BR>
	 * @param ids  void<BR>
	 */
	public void toDeleteMoreWithMy(String[] ids) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toDelColDown <BR>
	 * Description:  批量删除-我收藏下载的知识 <BR>
	 * Remark: <BR>
	 * @param ids
	 */
	public void toDelColDown(String[] ids) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: doDelColDow <BR>
	 * Description: 单个删除 知识收藏下载的记录 <BR>
	 * Remark: <BR>
	 */
	public void doDelColDow(String id) throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getOthersKnowledge <BR>
	 * Description: 他人的知识 <BR>
	 * Remark: <BR>
	 * @param searchKlVo
	 * @return  List<KnowledgeOtherVo><BR>
	 */
	public List<KnowledgeOtherVo> getOthersKnowledge(SearchKlVo searchKlVo)throws Exception;
	public int getOthersKnowledgeCount(SearchKlVo searchKlVo)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getPeopleInfo <BR>
	 * Description: 根据id获取人员基础信息 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return
	 * @throws Exception  ManageUserBean<BR>
	 */
	public ManageUserBean getPeopleInfo(String userId)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: downCreateKl <BR>
	 * Description: 下载创建的知识 <BR>
	 * Remark: <BR>
	 * @param knowledgeText  void<BR>
	 */
	public KnowledgeOtherVo downCreateKl(String klId)throws Exception;

	public void addBrowerInfo(String userId, String knowledgeId)throws Exception;

	public List<KnowledgeOtherVo> getFullSearchKnowledge(SearchKlByNameVo paramVo)throws Exception;
	public int getFullSearchKnowledgeCount(SearchKlByNameVo paramVo) throws Exception;
	/** chenrui end */
	
	/** luyunlong start */
	/**
	 * Method name: getAccidentCategory <BR>
	 * Description: 获取事故分类 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<KLCategoryVo><BR>
	 */
	public List<KLCategoryBean> getAccidentCategory(Map<String, Object> param);

	public KnowledgeOtherVo getFullSearchKnowledgeById(String knowledgeId);


	
	/** luyunlong end */
}
