/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamMarkScoreService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/05        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.ExamUserAnswerVo;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamUserAnswerBean;
import com.jftt.wifi.bean.exam.vo.ExamSearchOptionVo;
import com.jftt.wifi.bean.exam.vo.MarkExamListItemVo;
import com.jftt.wifi.bean.exam.vo.cjylSearchVo;
import com.jftt.wifi.bean.vo.ExamScheduleVo;

/**
 * class name:ExamMarkScoreService <BR>
 * class description: 考试批阅服务 <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/05
 * @author JFTT)wangyifeng
 */
public interface ExamMarkScoreService {
	// 列表页面
	/**
	 * Method name: getMarkVoList <BR>
	 * Description: 获取考试成绩批阅摘要列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return List<MarkExamListItemVo><BR>
	 */
	List<MarkExamListItemVo> getMarkVoList(ExamSearchOptionVo searchOption);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getMarkTotalCount <BR>
	 * Description: 获取已结束的考试总数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  Integer<BR>
	 */
	Integer getMarkTotalCount(ExamSearchOptionVo searchOption);

	/**
	 * Method name: publishScore <BR>
	 * Description: 发布成绩 <BR>
	 * Remark: <BR>
	 * 
	 * @param examId
	 *            void<BR>
	 */
	void publishScore(Integer examId);

	// 批阅页面

	/**
	 * Method name: modifyScore <BR>
	 * Description: 修改单个考生的成绩 <BR>
	 * Remark: <BR>
	 * 
	 * @param examAssign
	 *            void<BR>
	 */
	void modifyScore(ExamAssignBean examAssign);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: checkAndUpdateIsMarkedState <BR>
	 * Description: 查看指定考试是否已经都批阅，若批阅完毕，则更新考试的IsMarked状态为true <BR>
	 * Remark: <BR>
	 * @param examId  void<BR>
	 */
	void checkAndUpdateIsMarkedState(Integer examId);

	/**
	 * Method name: getAttendUserList <BR>
	 * Description: 获取参加考试的考试分配信息列表 <BR>
	 * Remark: <BR>
	 * @param ExamQueryConditionBean bean
	 * @return List<ExamAssignBean><BR>
	 * @throws Exception 
	 */
	List<ExamAssignBean> getAttendUserList(ExamQueryConditionBean bean) throws Exception;

	/**
	 * Method name: getAnswerList <BR>
	 * Description: 获取某个考生的答题详情列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param examAssignId
	 * @return List<ExamUserAnswerBean><BR>
	 */
	List<ExamUserAnswerBean> getAnswerList(Integer examAssignId);

	/**
	 * Method name: markScore <BR>
	 * Description: 记录批阅成绩 <BR>
	 * Remark: <BR>
	 * 
	 * @param examAssign
	 *            void<BR>
	 */
	void markScore(ExamUserAnswerVo vo) throws Exception;

	// 成绩预览页面

	/**
	 * Method name: getExamResultList <BR>
	 * Description: 获取成绩一览 <BR>
	 * 1.在ServiceImpl里设置排名信息（根据UserId排序）<br>
	 * 2.如果包含当前用户，当前用户放第一个<br>
	 * Remark: <BR>
	 * 
	 * @param examId
	 * @param curUserId
	 * @return List<ExamAssignBean><BR>
	 * @throws Exception 
	 */
	List<ExamAssignBean> getExamResultList(cjylSearchVo paramVo, String curUserId) throws Exception;

	/**chenrui start*/
	
	public int getExamResultListCount(cjylSearchVo paramVo) throws Exception ;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getExamInfo <BR>
	 * Description: 成绩预览-获取考试信息 <BR>
	 * Remark: <BR>
	 * @param examId
	 * @throws Exception  void<BR>
	 */
	public ExamScheduleVo getExamInfo(int examId)throws Exception ;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: exportExcel <BR>
	 * Description: 成绩预览-导出excel<BR>
	 * Remark: <BR>
	 * @throws Exception  void<BR>
	 */
	public HSSFWorkbook exportExcel(cjylSearchVo paramVo)throws Exception ;

	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: exportDoc <BR>
	 * Description: 成绩预览-导出word <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return
	 * @throws Exception  File<BR>
	 */
	public XWPFDocument exportDoc(cjylSearchVo paramVo) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modifyExamIsMarked <BR>
	 * Description:  更新一场考试的批阅字段,需要参加考试的人员全部批阅后才会更新 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void modifyExamIsMarked(int id,String modifyType);

	public List<ExamAssignBean> getCjYlListAll(cjylSearchVo paramVo, String userId)throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getUserByRelationId <BR>
	 * Description: 查询一场考试是否有添加人员 <BR>
	 * Remark: <BR>
	 * @param relation_id
	 * @return  boolean<BR>
	 */
	boolean getUserByRelationId(int relation_id);
	
	/**chenrui end*/


}
