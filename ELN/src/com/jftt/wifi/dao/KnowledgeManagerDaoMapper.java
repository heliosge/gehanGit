/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KnowledgeManagerDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月14日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.KLCategoryBean;
import com.jftt.wifi.bean.KlTagsBean;
import com.jftt.wifi.bean.KnowledgeBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.KLCategoryVo;
import com.jftt.wifi.bean.vo.KlByCatParamsVo;
import com.jftt.wifi.bean.vo.KlContributorVo;
import com.jftt.wifi.bean.vo.KnowledgeOtherVo;
import com.jftt.wifi.bean.vo.SearchKlByNameVo;
import com.jftt.wifi.bean.vo.SearchKlVo;
import com.jftt.wifi.bean.vo.klCountsVo;


/**
 * class name:KnowledgeManagerDao <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月14日
 * @author JFTT)liucaibao
 */
public interface  KnowledgeManagerDaoMapper {

	
	
	/**
	 * Method name: 查询知识列表
	 * Description: queryKnowledgeList <BR>
	 * Remark: <BR>
	 * @param knowledgeBean
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeBean> queryKnowledgeList(KnowledgeBean knowledgeBean);
	public int queryKnowledgeCount(KnowledgeBean knowledgeBean);
	
	/**
	 * Method name: 查询知识审核列表
	 * Description: queryKnowledgeList <BR>
	 * Remark: <BR>
	 * @param knowledgeBean
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeBean> queryAuditKnowledgeList(KnowledgeBean knowledgeBean);
	public int queryAuditKnowledgeCount(KnowledgeBean knowledgeBean);
	/**
	 * Method name: 查询知识列表
	 * Description: QueryKnowledgeDetail <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @return  String<BR>
	 */
	public KnowledgeBean queryKnowledgeDetail(@Param("knowledgeId")int knowledgeId);
	
	
	/**
	 * Method name: queryUserIdList <BR>
	 * Description: 查找符合要求的用户列表 <BR>
	 * Remark: <BR>
	 * @param map
	 * @return  List<Integer><BR>
	 */
	public List<Integer> queryUserIdList(Map<String,Object> map);
	
	/**
	 * Method name: 保存知识
	 * Description: saveKnowledge <BR>
	 * Remark: <BR>
	 * @param knowledgeBean  void<BR>
	 */
	public Integer saveKnowledge(KnowledgeBean knowledgeBean)throws Exception ;
	
	/**
	 * Method name: deleteKLTags <BR>
	 * Description: 删除知识标签 <BR>
	 * Remark: <BR>
	 * @param knowledgeId  void<BR>
	 */
	public void deleteKLTags(@Param("knowledgeId")int knowledgeId);
	
	
	/**
	 * Method name: queryTagId <BR>
	 * Description: 根据tag查询tagId <BR>
	 * Remark: <BR>
	 * @param tag
	 * @return  long<BR>
	 */
	public Integer queryTagId(@Param("tag")String tag);
	
	/**
	 * Method name: insertTags <BR>
	 * Description: insertTags <BR>
	 * Remark: <BR>
	 * @param klTagsBean  void<BR>
	 */
	public void  insertTags(KlTagsBean klTagsBean);
	public void insertKLTag(@Param("knowledgeId")int knowledgeId,@Param("tagId")int tagId);
	
	/**
	 * Method name: 更新知识信息
	 * Description: updateKnowledge <BR>
	 * Remark: <BR>
	 * @param knowledgeBean  void<BR>
	 */
	public void updateKnowledge(KnowledgeBean knowledgeBean);
	
	/**
	 * Method name: publishKL <BR>
	 * Description: 发布知识 <BR>
	 * Remark: <BR>
	 * @param knowledgeId  void<BR>
	 */
	public void publishKL(Map param);
	/**
	 * Method name: 更新某个知识的分类
	 * Description: updateOneKnowledgeCategroy <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @param categoryId
	 * @param userId  void<BR>
	 */
	public void updateOneKnowledgeCategroy(KnowledgeBean knowledgeBean);
	
	
	/**
	 * Method name: 删除知识
	 * Description: deleteKnowledge <BR>
	 * Remark: <BR>
	 * @param knowledgeId  void<BR>
	 */
	public void deleteKnowledge(Map map);
	
	/**
	 * Method name: 审核知识
	 * Description: auditKnowledge <BR>
	 * Remark: <BR>
	 * @param map  void<BR>
	 */
	public void auditKnowledge(KnowledgeBean knowledgeBean);
	
	
	/**
	 * Method name: queryKLCategoryCount <BR>
	 * Description: 重名知识分类的个数 <BR>
	 * Remark: <BR>
	 * @param klCategoryBean
	 * @return  int<BR>
	 */
	public int queryKLCategoryCount(KLCategoryBean klCategoryBean); 
	/**
	 * Method name: 删除知识分类
	 * Description: deleteKLCategory <BR>
	 * Remark: <BR>
	 * @param knowledgeId  知识分类id
	 */
	public void deleteKLCategory(@Param("categoryId")int categoryId);

	/**
	 * Method name: queryChildren <BR>
	 * Description: 查找知识分类的子节点的个数 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  int<BR>
	 */
	public int queryChildren(@Param("categoryId")int categoryId);
	
	/**
	 * Method name: queryKLCountById <BR>
	 * Description: 查找关联了知识分类的知识个数 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  int<BR>
	 */
	public int queryKLCountById(@Param("categoryId")int categoryId);
	/**
	 * Method name: 批量更新知识的分类
	 * Description: updateKnowledgeCategory <BR>
	 * Remark: <BR>
	 * @param categroyId
	 * @param changeId
	 * @param userId  void<BR>
	 */
	public void updateKnowledgeCategory(Map<String,Object> param);
	

	/**
	 * Method name: detailCategory <BR>
	 * Description: 查询知识分类的相关信息 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  KLCategoryBean<BR>
	 */
	public KLCategoryBean detailCategory(int categoryId);
	/**
	 * Method name: 保存知识的分类信息
	 * Description: saveKLCategory <BR>
	 * Remark: <BR>
	 * @param kLCategoryBean  void<BR>
	 */
	public void  saveKLCategory(KLCategoryBean kLCategoryBean);
	
	/**
	 * Method name: updateKLCategoryName <BR>
	 * Description: 更新知识分类的名称 <BR>
	 * Remark: <BR>
	 * @param kLCategoryBean
	 * @throws Exception  void<BR>
	 */
	public void updateKLCategoryName(KLCategoryBean kLCategoryBean)throws Exception;
	/**
	 * Method name: 获取知识的分类树
	 * Description: getKLCategoryTree <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return  List<KLCategoryBean><BR>
	 */
	public   List<KLCategoryBean> getKLCategoryTree(KLCategoryBean klCategoryBean);


	/**chenrui start*/
	
	/**
	 * 
	 * Method name: getKnowledgeCategory <BR>
	 * Description: 获取知识分类 <BR>
	 * Remark: <BR>
	 * @return  List<KLCategoryBean><BR>
	 */
	public List<KLCategoryVo> getKnowledgeCategory(@Param("companyId")int companyId,@Param("subCompanyId")int subCompanyId)throws Exception;
	/**
	 * 
	 * Method name: getKnowledgeCount <BR>
	 * Description: 获取当前知识资源数量统计 <BR>
	 * Remark: <BR>
	 * @return  List<klCountsVo><BR>
	 */
	public List<klCountsVo> getKnowledgeCount(@Param("companyId")int companyId,@Param("subCompanyId")int subCompanyId)throws Exception;
	/**
	 * 
	 * Method name: getMyKnowledgeCount <BR>
	 * Description: 获取我贡献的知识数目 <BR>
	 * Remark: <BR>
	 * @return  int<BR>
	 */
	public int getMyKnowledgeCount(@Param("userId")String userId,@Param("companyId")int companyId,@Param("subCompanyId")int subCompanyId) throws Exception;
	/**
	 * 
	 * Method name: getDepartmentKnowledge <BR>
	 * Description: 获取部门知识 <BR>
	 * Remark: <BR>
	 * @param ids
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeOtherVo> getDepartmentKnowledge(@Param("userList")List<ManageUserBean> userList)throws Exception;
	/**
	 * 
	 * Method name: getRecommendKnowledge <BR>
	 * Description: 获取推荐知识  <BR>
	 * Remark: <BR>
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeOtherVo> getRecommendKnowledge(@Param("companyId")int companyId,@Param("subCompanyId")int subCompanyId)throws Exception;
	/**
	 * 
	 * Method name: getKnowledgeByCategory <BR>
	 * Description: 根据类别获取知识 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeOtherVo> getKnowledgeByCategory(KlByCatParamsVo paramsVo)throws Exception;
	public int getKnowledgeByCategoryCount(KlByCatParamsVo paramsVo)throws Exception;
	/**
	 * 
	 * Method name: getKnowledgeOrderByEvaluate <BR>
	 * Description: 根据类型获取知识并按评价排序 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @param orderType
	 */
	public List<KnowledgeOtherVo> getKnowledgeOrderByEvaluate(KlByCatParamsVo paramsVo)throws Exception;
	public int getKnowledgeOrderByEvaluateCount(KlByCatParamsVo paramsVo)throws Exception;
	/**
	 * 
	 * Method name: getKnowledgeOrderByHot <BR>
	 * Description: 根据类型获取知识并按热门排序 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @param orderType
	 */
	public List<KnowledgeOtherVo> getKnowledgeOrderByHot(KlByCatParamsVo paramsVo)throws Exception;

	public int getKnowledgeOrderByHotCount(KlByCatParamsVo paramsVo)throws Exception;
	/**
	 * 
	 * Method name: getUploadingKnowledge <BR>
	 * Description: 获取查看上传知识页面数据信息 <BR>
	 * Remark: <BR>
	 * @param authorId
	 * @param userId
	 * @return  KnowledgeOtherVo<BR>
	 */
	public List<KnowledgeOtherVo> getUploadingKnowledge(@Param("knowledgeId")String knowledgeId)throws Exception;
	/**
	 * 
	 * Method name: getById <BR>
	 * Description: 根据id获取知识 <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @return
	 * @throws Exception  KnowledgeBean<BR>
	 */
	public KnowledgeBean getById(@Param("id")String knowledgeId)throws Exception;
	/**
	 * 
	 * Method name: getKnowledgeContributor <BR>
	 * Description: 获取突出贡献者排行信息 <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  List<KlContributorVo><BR>
	 */
	public List<KlContributorVo> getKnowledgeContributor(@Param("companyId")int companyId,@Param("subCompanyId")int subCompanyId)throws Exception;
	/**
	 * 
	 * Method name: searchKnowledge <BR>
	 * Description: 根据条件筛选知识 <BR>
	 * Remark: <BR>
	 * @param knowledgeName
	 * @param fileType
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeBean> searchKnowledge(@Param("knowledgeName")String knowledgeName, @Param("fileType")String fileType,
			@Param("companyId")int companyId,@Param("subCompanyId")int subCompanyId)throws Exception;
	/**
	 * 
	 * Method name: getGuessLike <BR>
	 * Description: 获取查看上传知识->猜你喜欢的数据  <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @param userId
	 * @return
	 * @throws Exception  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeBean> getGuessLike(@Param("knowledgeId")String knowledgeId, @Param("userId")String userId,
			@Param("companyId")int companyId,@Param("subCompanyId")int subCompanyId)throws Exception;
	/**
	 * 
	 * Method name: getMyKnowledgeForCollectDownload <BR>
	 * Description: 获取我收藏下载的知识 <BR>
	 * Remark: <BR>
	 * @param searchKlVo
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeOtherVo> getMyKnowledgeForCollectDownload(SearchKlVo searchKlVo)throws Exception;
	public int getMyKnowledgeForCollectDownloadCount(SearchKlVo searchKlVo)throws Exception;
	/**
	 * 
	 * Method name: getRecommends <BR>
	 * Description: 获取相关推荐知识 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param knowledgeId
	 * @return
	 * @throws Exception  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeBean> getRecommends(@Param("userId")String userId, @Param("knowledgeId")String knowledgeId,
			@Param("companyId")int companyId,@Param("subCompanyId")int subCompanyId) throws Exception;

	/**
	 * 
	 * Method name: delKlById <BR>
	 * Description:  根据id删除对应知识 <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @throws Exception  void<BR>
	 */
	public void delKlById(@Param("id")String knowledgeId)throws Exception;
	/**
	 * 
	 * Method name: toPublic <BR>
	 * Description: 公开知识操作 <BR>
	 * Remark: <BR>
	 * @param knowledgeId  void<BR>
	 */
	public void toPublic(@Param("id")String knowledgeId)throws Exception;
	/**
	 * 
	 * Method name: updateKl <BR>
	 * Description: 修改知识 <BR>
	 * Remark: <BR>
	 * @param knowledgeBean  void<BR>
	 */
	public void updateKl(KnowledgeBean knowledgeBean)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getByCategoryId <BR>
	 * Description: 根据类别id获取类别信息 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  KLCategoryBean<BR>
	 */
	public KLCategoryBean getByCategoryId(@Param("categoryId")String id)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getByCategoryIdWith <BR>
	 * Description: 根据类别id获取类别信息 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws Exception  KLCategoryVo<BR>
	 */
	public KLCategoryVo getByCategoryIdWith(@Param("categoryId")String categoryId)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getKlCategoryByParentId <BR>
	 * Description: 获取所有子分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @throws Exception  void<BR>
	 */
	public List<KLCategoryBean> getKlCategoryByParentId(@Param("categoryId")String categoryId)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMyUploadKnowledge <BR>
	 * Description: 我的知识中心-获取我上传的知识 <BR>
	 * Remark: <BR>
	 * @param searchKlVo
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeOtherVo> getMyUploadKnowledge(SearchKlVo searchKlVo)throws Exception;

	public int getMyUploadKnowledgeCount(SearchKlVo searchKlVo)throws Exception;
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
	 * @return
	 * @throws Exception  List<KnowledgeOtherVo><BR>
	 */
	public List<KnowledgeOtherVo> getSearchKnowledge(SearchKlByNameVo paramVo)throws Exception;
	public int getSearchKnowledgeCount(SearchKlByNameVo paramVo)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getSearchKnowledgeByEvaluate <BR>
	 * Description:  获取搜索的知识- 按评价排序  <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return  List<KnowledgeOtherVo><BR>
	 */
	public List<KnowledgeOtherVo> getSearchKnowledgeByEvaluate(SearchKlByNameVo paramVo)throws Exception;
	public int getSearchKnowledgeByEvaluateCount(SearchKlByNameVo paramVo)throws Exception;
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
	public List<KnowledgeOtherVo> getSearchKnowledgeByHot(SearchKlByNameVo paramVo)throws Exception;
	public int getSearchKnowledgeByHotCount(SearchKlByNameVo paramVo)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toDeleteMoreWithMy <BR>
	 * Description: 批量删除-我上传的知识   <BR>
	 * Remark: <BR>
	 * @param ids
	 * @throws Exception  void<BR>
	 */
	public void toDeleteMoreWithMy(@Param("ids")String[] ids)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getOthersKnowledge <BR>
	 * Description: 获取他人的知识 <BR>
	 * Remark: <BR>
	 * @param searchKlVo
	 * @return  List<KnowledgeOtherVo><BR>
	 */
	public List<KnowledgeOtherVo> getOthersKnowledge(SearchKlVo searchKlVo)throws Exception;
	public int getOthersKnowledgeCount(SearchKlVo searchKlVo)throws Exception;
	/**
	 *  推荐、取消推荐
	 * @param id
	 * @param type
	 */
	public void doRecommend(@Param("id")String id,@Param("type")String type) throws Exception;

	public List<KnowledgeBean> getAllByElastisearch();
	
	public KnowledgeOtherVo getFullSearchKnowledgeById(@Param("knowledgeId")String knowledgeId);
	/**chenrui end*/
	
	/**luyunlong start*/
	/**
	 * Method name: getAccidentCategory <BR>
	 * Description: 获取事故分类 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<KLCategoryVo><BR>
	 */
	public List<KLCategoryBean> getAccidentCategory(Map<String, Object> param);
	
	/**luyunlong end*/
}
