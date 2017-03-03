/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KnowledgeManagerService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月14日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.KLCategoryBean;
import com.jftt.wifi.bean.KnowledgeBean;

/**
 * class name:KnowledgeManagerService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月14日
 * @author JFTT)liucaibao
 */
public interface KnowledgeManageService {

	
	/**
	 * Method name: QueryKnowledgeList <BR>
	 * Description: 查询知识列表 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return  List<Map><BR>
	 */
	public List<KnowledgeBean> queryKnowledgeList(KnowledgeBean knowledgeBean) ;
	public int queryKnowledgeCount(KnowledgeBean knowledgeBean);
	
	/**
	 * Method name: queryAuditKnowledgeList <BR>
	 * Description: 查找审核列表 <BR>
	 * Remark: <BR>
	 * @param knowledgeBean
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeBean> queryAuditKnowledgeList(KnowledgeBean knowledgeBean) ;
	public int queryAuditKnowledgeCount(KnowledgeBean knowledgeBean);
	
	/**
	 * Method name: 查询知识详情
	 * Description: QueryKnowledgeDetail <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @return  String<BR>
	 */
	public KnowledgeBean 	queryKnowledgeDetail(int knowledgeId) ;

	
	
	/**
	 * Method name: 删除知识
	 * Description: deleteKnowledge <BR>
	 * Remark: <BR>
	 * @param knowledgeId 知识id
	 * @return  String<BR>
	 */
	public void deleteKnowledge(Map map) ;
	
	
	/**
	 * Method name: 修改知识
	 * Description: modifyKnowledge <BR>
	 * Remark: <BR>
	 * @param knowledgeId 知识id
	 * @return  String<BR>
	 */
	public String  modifyKnowledge(int knowledgeId);
	
	
	
	
	/**
	 * Method name:更新单个的知识分类节点
	 * Description: updateOneKnowledgeCategroy <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @param categoryId  void<BR>
	 */
	public void updateOneKnowledgeCategroy(KnowledgeBean knowledgeBean) throws Exception;
	
	
	
	/**
	 * Method name: updateKnowledgeCategory <BR>
	 * Description: updateKnowledgeCategory <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @param categroyId
	 * @param userId  void<BR>
	 */
	public void updateKnowledgeCategory(Map<String,Object> param)throws Exception;
	
	/**
	 * Method name: queryKLCategoryCount <BR>
	 * Description: 查找重名的分类个数 <BR>
	 * Remark: <BR>
	 * @param klCategoryBean
	 * @return  int<BR>
	 */
	public int queryKLCategoryCount(KLCategoryBean klCategoryBean) throws Exception;
	/**
	 * Method name: deleteKLCategory <BR>
	 * Description: deleteKLCategory <BR>
	 * Remark: <BR>
	 * @param knowledgeId  void<BR>
	 */
	public void deleteKLCategory(int categoryId);
	
	
	/**
	 * Method name: isAllowDeleteCategory <BR>
	 * Description: 是否允许删除分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  boolean<BR>
	 */
	public boolean isAllowDeleteCategory(int categoryId) throws Exception;
	/**
	 * Method name: 保存知识分类信息
	 * Description: saveKLCategory <BR>
	 * Remark: <BR>
	 * @param kLCategoryBean  void<BR>
	 */
	public void saveKLCategory(KLCategoryBean kLCategoryBean);
	
	
	/**
	 * Method name: updateKLCategoryName <BR>
	 * Description: 更新知识分类的名称 <BR>
	 * Remark: <BR>
	 * @param klCategoryBean  void<BR>
	 */
	public  void updateKLCategoryName(KLCategoryBean klCategoryBean)throws Exception;
	
	/**
	 * Method name: 根据公司Id获取该公司的分类树
	 * Description: getKLCategoryTree <BR>
	 * Remark: <BR>
	 * @param companyId  void<BR>
	 */
	public List<KLCategoryBean>  getKLCategoryTree(KLCategoryBean klCategoryBean);
	
	
	/**
	 * Method name: detailCategory <BR>
	 * Description: 查询知识分类的详细信息 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  KLCategoryBean<BR>
	 */
	public KLCategoryBean detailCategory(int categoryId) throws Exception;
	
	
	
	/**
	 * Method name: 保存知识信息
	 * Description: saveKnowledge <BR>
	 * Remark: <BR>
	 * @param knowledgeBean  void<BR>
	 */
	public void saveKnowledge(KnowledgeBean knowledgeBean) throws Exception;
	
	
	/**
	 * Method name: uploadFile <BR>
	 * Description: 上传文件 <BR>
	 * Remark: <BR>
	 * @param file
	 * @return  String<BR>
	 */
	public String  uploadFile(MultipartFile file,String commonPath);
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
	 * @param knowledgeId
	 * @throws Exception  void<BR>
	 */
	public void publishKL(Map param) throws Exception;
	
	/**
	 * Method name: 知识的审核
	 * Description: auditKnowledge <BR>
	 * Remark: <BR>  void<BR>
	 */
	public void auditKnowledge(KnowledgeBean knowledgeBean)  throws Exception;
	/**
	 * 推荐、取消推荐
	 * @param id
	 * @param type
	 * @throws Exception
	 */
	public void doRecommend(String id, String type,String createUserId) throws Exception;
}
