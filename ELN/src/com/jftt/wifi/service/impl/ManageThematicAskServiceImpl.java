/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageThematicAskServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月25日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ask.AskAnswerBean;
import com.jftt.wifi.bean.ask.AskDetailBean;
import com.jftt.wifi.bean.thematicAsk.ManageThematicAskVo;
import com.jftt.wifi.dao.AskAnswerDaoMapper;
import com.jftt.wifi.dao.AskDetailDaoMapper;
import com.jftt.wifi.service.ManageThematicAskService;
import com.jftt.wifi.util.CommonUtil;

/**
 * class name:ManageThematicAskServiceImpl <BR>
 * class description: 专题问答service <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月25日
 * @author JFTT)ShenHaijun
 */
@Service("manageThematicAskService")
public class ManageThematicAskServiceImpl implements ManageThematicAskService{
	@Resource(name="askDetailDaoMapper")
	private AskDetailDaoMapper askDetailDaoMapper;
	@Resource(name="askAnswerDaoMapper")
	private AskAnswerDaoMapper askAnswerDaoMapper;
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageThematicAskService#getThematicAskCount(java.lang.String, java.lang.String) <BR>
	 * Method name: getThematicAskCount <BR>
	 * Description: 根据条件查询专题问答数量 <BR>
	 * Remark: <BR>
	 * @param searchAskState 提问状态
	 * @param searchTitle 标题
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getThematicAskCount(String searchAskState, String searchTitle)
			throws Exception {
		//设置查询参数
		ManageThematicAskVo searchParams = new ManageThematicAskVo();
		if(searchAskState != null && !"".equals(searchAskState.trim())){
			searchParams.setSearchAskState(searchAskState);
		}
		if(searchTitle != null && !"".equals(searchTitle.trim())){
			searchParams.setSearchTitle(searchTitle);
		}
		//返回查询结果
		return askDetailDaoMapper.getThematicAskCount(searchParams);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageThematicAskService#getThematicAsks(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String) <BR>
	 * Method name: getThematicAsks <BR>
	 * Description: 根据条件查询专题问答列表 <BR>
	 * Remark: <BR>
	 * @param searchAskState 提问状态
	 * @param searchTitle 标题
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<ManageThematicAskVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<ManageThematicAskVo> getThematicAsks(String searchAskState,
			String searchTitle, Integer fromNum, String pageSize)
			throws Exception {
		//设置查询参数
		ManageThematicAskVo searchParams = new ManageThematicAskVo();
		if(searchAskState != null && !"".equals(searchAskState.trim())){
			searchParams.setSearchAskState(searchAskState);
		}
		if(searchTitle != null && !"".equals(searchTitle.trim())){
			searchParams.setSearchTitle(searchTitle);
		}
		if(fromNum != null){
			searchParams.setFromNum(fromNum);
		}
		if(pageSize != null && !"".equals(pageSize.trim())){
			searchParams.setPageSize(Integer.parseInt(pageSize));
		}
		//返回查询结果
		List<ManageThematicAskVo> thematicAsks = askDetailDaoMapper.getThematicAsks(searchParams);
		//设置状态和内容摘要
		ManageThematicAskVo thematicAsk = new ManageThematicAskVo();
		Date nowDate = new Date();
		for (int i = 0; i < thematicAsks.size(); i++) {
			thematicAsk = thematicAsks.get(i);
			//如果问题有效时间超过现在时间，就设置状态为已过期
			if(thematicAsk.getEffectiveTime() != null && thematicAsk.getEffectiveTime().getTime() < nowDate.getTime()){
				thematicAsk.setAskState(3);
			}else{
				List<AskAnswerBean> thematicAnswers = askAnswerDaoMapper.getByQuestionId(thematicAsk.getId());
				if(thematicAnswers != null && thematicAnswers.size() > 0){
					//遍历该问题的答案，如果答案中存在满意答案，则设置状态为已处理，否则设置为未处理
					boolean isTreated = false;
					for (int j = 0; j < thematicAnswers.size(); j++) {
						if(thematicAnswers.get(j).getIsSatisfactory() != null && thematicAnswers.get(j).getIsSatisfactory() == 1){
							isTreated = true;
						}
					}
					if(isTreated){
						thematicAsk.setAskState(2);
					}else{
						thematicAsk.setAskState(1);
					}
				}else{
					thematicAsk.setAskState(1);
				}
			}
			//设置内容摘要
			if(thematicAsk.getContentSummary() != null && thematicAsk.getContentSummary().length() > 8){
				thematicAsk.setContentSummary(thematicAsk.getContentSummary().substring(0, 8) + "...");
			}
		}
		return thematicAsks;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageThematicAskService#getThematicAsksByTitle(java.lang.String) <BR>
	 * Method name: getThematicAsksByTitle <BR>
	 * Description: 根据标题查询专题问答 <BR>
	 * Remark: <BR>
	 * @param thematicTitle 标题
	 * @return List<AskDetailBean>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<AskDetailBean> getThematicAsksByTitle(String thematicTitle)
			throws Exception {
		return askDetailDaoMapper.getThematicAsksByTitle(thematicTitle);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageThematicAskService#addThematicAsk(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: addThematicAsk <BR>
	 * Description: 添加专题问答 <BR>
	 * Remark: <BR>
	 * @param title 标题
	 * @param content 内容
	 * @param initialAnswer 初始答案
	 * @param addPics 添加图片
	 * @param coverPic 封面图片
	 * @param tags 标签
	 * @param effectiveTime 有效时间
	 * @param rewardPoints 绩点
	 * @param askerId 提问人id
	 * @param askerName 提问人姓名
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addThematicAsk(String title, String content,String initialAnswer, String addPics, 
			String coverPic, String tags,String effectiveTime, String rewardPoints,
			String askerId,String askerName,String companyId,String subCompanyId) throws Exception {
		//设置参数
		AskDetailBean thematicAsk = new AskDetailBean();
		if(title != null && !"".equals(title.trim())){
			thematicAsk.setTitle(title);
		}
		if(content != null && !"".equals(content.trim())){
			thematicAsk.setContent(content);
		}
		if(initialAnswer != null && !"".equals(initialAnswer.trim())){
			thematicAsk.setInitialAnswer(initialAnswer);
		}
		if(addPics != null && !"".equals(addPics.trim())){
			thematicAsk.setAddPics(addPics);
		}
		if(coverPic != null && !"".equals(coverPic.trim())){
			thematicAsk.setCoverPic(coverPic);
		}
		if(tags != null && !"".equals(tags.trim())){
			thematicAsk.setLabel(tags);
		}
		if(effectiveTime != null && !"".equals(effectiveTime.trim())){
			thematicAsk.setEffectiveTime(CommonUtil.getDateByString(effectiveTime));
		}
		if(rewardPoints != null && !"".equals(rewardPoints.trim())){
			thematicAsk.setRewardPoints(Integer.parseInt(rewardPoints));
		}
		if(askerId != null && !"".equals(askerId.trim())){
			thematicAsk.setAskerId(Integer.parseInt(askerId));
		}
		if(askerName != null && !"".equals(askerName.trim())){
			thematicAsk.setAskerName(askerName);
		}
		if(companyId != null && !"".equals(companyId.trim())){
			thematicAsk.setCompanyId(Integer.parseInt(companyId));
		}
		if(subCompanyId != null && !"".equals(subCompanyId.trim())){
			thematicAsk.setSubCompanyId(Integer.parseInt(subCompanyId));
		}
		thematicAsk.setIsAnonymous(2);//不匿名
		thematicAsk.setIsThematic(2);//类型为专题问答
		thematicAsk.setIsDelete(2);//未删除
		//添加记录
		askDetailDaoMapper.addThematicAsk(thematicAsk);
		
		//如果有初始答案，则在回答中添加一条满意答案
		if(initialAnswer != null && !"".equals(initialAnswer.trim())){
			AskAnswerBean thematicAnswer = new AskAnswerBean();
			thematicAnswer.setQuestionId(thematicAsk.getId());
			if("1".equals(companyId)){
				thematicAnswer.setReplyerType(1);//普连管理员
			}else{
				thematicAnswer.setReplyerType(2);//企业管理员
			}
			thematicAnswer.setContent(initialAnswer);
			thematicAnswer.setIsAnonymous(2);//不匿名（默认）
			thematicAnswer.setReplyerId(Integer.parseInt(askerId));
			thematicAnswer.setReplyerName(askerName);
			thematicAnswer.setIsThematic(2);//专题
			thematicAnswer.setIsShield(2);//未屏蔽
			thematicAnswer.setIsSatisfactory(1);//满意答案
			thematicAnswer.setSatisfactTime(CommonUtil.getDateTimeString(new Date()));//设为满意答案时间
			thematicAnswer.setIsDelete(2);//未删除
			thematicAnswer.setCompanyId(Integer.parseInt(companyId));
			thematicAnswer.setSubCompanyId(Integer.parseInt(subCompanyId));
			askAnswerDaoMapper.add(thematicAnswer);
		}
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageThematicAskService#getThematicAskById(java.lang.Integer) <BR>
	 * Method name: getThematicAskById <BR>
	 * Description: 根据id查询专题问答 <BR>
	 * Remark: <BR>
	 * @param id 专题问答id
	 * @return AskDetailBean
	 * @throws Exception  <BR>
	 */
	@Override
	public AskDetailBean getThematicAskById(Integer id) throws Exception {
		return askDetailDaoMapper.getById(id);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageThematicAskService#getAnswerCount(java.lang.Integer) <BR>
	 * Method name: getAnswerCount <BR>
	 * Description: 获取问答数量 <BR>
	 * Remark: <BR>
	 * @param thematicAskId 问题id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getAnswerCount(Integer thematicAskId) throws Exception {
		return askAnswerDaoMapper.getAnswerCount(thematicAskId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageThematicAskService#getSatisfactoryAnswer(java.lang.Integer) <BR>
	 * Method name: getSatisfactoryAnswer <BR>
	 * Description: 获取问题满意回答 <BR>
	 * Remark: <BR>
	 * @param questionId 问题id
	 * @return AskAnswerBean
	 * @throws Exception  <BR>
	 */
	@Override
	public AskAnswerBean getSatisfactoryAnswer(Integer questionId)
			throws Exception {
		return askAnswerDaoMapper.getSatisfactoryAnswer(questionId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageThematicAskService#getOtherAnswers(java.lang.Integer) <BR>
	 * Method name: getOtherAnswers <BR>
	 * Description: 获取其他问答 <BR>
	 * Remark: <BR>
	 * @param questionId 问题id
	 * @return List<AskAnswerBean>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<AskAnswerBean> getOtherAnswers(Integer questionId)
			throws Exception {
		return askAnswerDaoMapper.getOtherAnswers(questionId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageThematicAskService#getAllAnswers(java.lang.Integer) <BR>
	 * Method name: getAllAnswers <BR>
	 * Description: 获取该专题问答的所有回答 <BR>
	 * Remark: <BR>
	 * @param questionId 问题id
	 * @return List<AskAnswerBean>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<AskAnswerBean> getAllAnswers(Integer questionId)
			throws Exception {
		return askAnswerDaoMapper.getAllAnswers(questionId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageThematicAskService#shielAnswer(java.lang.Integer) <BR>
	 * Method name: shielAnswer <BR>
	 * Description: 屏蔽回答 <BR>
	 * Remark: <BR>
	 * @param id 回答id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void shielAnswer(Integer id) throws Exception {
		askAnswerDaoMapper.shielAnswer(id);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageThematicAskService#showAnswer(java.lang.Integer) <BR>
	 * Method name: showAnswer <BR>
	 * Description: 解除屏蔽回答 <BR>
	 * Remark: <BR>
	 * @param id 回答id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void showAnswer(Integer id) throws Exception {
		askAnswerDaoMapper.showAnswer(id);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageThematicAskService#setSatisfactoryAnswer(java.lang.Integer) <BR>
	 * Method name: setSatisfactoryAnswer <BR>
	 * Description: 采为满意答案 <BR>
	 * Remark: <BR>
	 * @param id 回答id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void setSatisfactoryAnswer(Integer id) throws Exception {
		askAnswerDaoMapper.setSatisfactoryAnswer(id);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageThematicAskService#deleteThematicAsk(java.lang.Integer) <BR>
	 * Method name: deleteThematicAsk <BR>
	 * Description: 删除专题问答 <BR>
	 * Remark: <BR>
	 * @param id 回答id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteThematicAsk(Integer id) throws Exception {
		askDetailDaoMapper.deleteThematicAsk(id);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageThematicAskService#batchDeleteThematicAsks(java.lang.String[]) <BR>
	 * Method name: batchDeleteThematicAsks <BR>
	 * Description: 批量删除专题问答 <BR>
	 * Remark: <BR>
	 * @param ids 回答id集合
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void batchDeleteThematicAsks(String[] ids) throws Exception {
		askDetailDaoMapper.batchDeleteThematicAsks(ids);
	}
	
}
