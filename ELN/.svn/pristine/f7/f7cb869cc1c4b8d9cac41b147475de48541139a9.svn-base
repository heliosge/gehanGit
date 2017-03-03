/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamPaperDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-30        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.SectionExamBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.exam.vo.PaperListItemVo;
import com.jftt.wifi.bean.exam.vo.PaperSearchOptionVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * @author JFTT)zhangchen class name:ExamPaperDaoMapper <BR>
 *         class description: 试卷DaoMapper <BR>
 *         Remark: <BR>
 * @version 1.00 2015-7-30
 */
public interface ExamPaperDaoMapper {
	// wangyifeng begin

	/**
	 * Method name: addPaper <BR>
	 * Description: 增加试卷 <BR>
	 * Remark: <BR>
	 * 
	 * @param newPaper
	 *            void<BR>
	 */
	Integer addPaper(PaperBean newPaper);

	/**
	 * Method name: deletePapers <BR>
	 * Description: 逻辑删除指定ID列表对应的试卷 <BR>
	 * Remark: <BR>
	 * 
	 * @param paperIdList
	 *            void<BR>
	 */
	void deletePaper(Integer paperId);

	/**
	 * Method name: modifyPaper <BR>
	 * Description: 修改试卷 <BR>
	 * Remark: <BR>
	 * 
	 * @param paper
	 *            void<BR>
	 */
	void modifyPaper(PaperBean paper);

	/**
	 * Method name: getPaper <BR>
	 * Description: 获取试卷 <BR>
	 * 关于打印： <BR>
	 * 打印功能是在UI端对试题显示样式的调整，<br/>
	 * 使用浏览器自带的打印功能，不使用特殊控件实现<br/>
	 * Remark: <BR>
	 * 
	 * @param paperId
	 * @return PaperBean<BR>
	 */
	PaperBean getPaper(Integer paperId);

	/**
	 * Method name: getVoList <BR>
	 * Description: 获取试卷基本信息列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return List<PaperListItemVo><BR>
	 */
	List<PaperListItemVo> getVoList(PaperSearchOptionVo searchOption);

	/**
	 * Method name: getTotalCount <BR>
	 * Description: 获取试卷总数 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return Integer<BR>
	 */
	Integer getTotalCount(PaperSearchOptionVo searchOption);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: checkPaperReference <BR>
	 * Description: check试卷是否被引用 <BR>
	 * Remark: <BR>
	 * @param paperId
	 * @return  Boolean<BR>
	 */
	Boolean checkPaperReference(Integer paperId);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: disablePaper <BR>
	 * Description: 将传入试卷ID对应的试卷设为禁用 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void disablePaper(Integer id);

	// wangyifeng end

	// zhangchen start

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectById <BR>
	 * Description: 通过ID查询试卷信息 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  PaperBean<BR>
	 */
	public PaperBean selectById(@Param("id") int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectTotalScoreById <BR>
	 * Description: 查询试卷部分 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  int<BR>
	 */
	public int selectTotalScoreById(@Param("id") int id);

	

	// zhangchen end
	
	// luyunlong start
	
	/**
	 * Method name: selectSectionAndCourseware <BR>
	 * Description: 根据章节获取试卷 <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @return  List<PaperBean><BR>
	 */
	public List<SectionExamBean> selectSectionAndCourseware(Map<String, Object> param);
	
	/**
	 * Method name: selectExamList <BR>
	 * Description: 获取试卷列表 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<PaperBean><BR>
	 */
	public List<SectionExamBean> selectExamList(Map<String, Object> param);
	
	/**
	 * Method name: selectExamListCount <BR>
	 * Description: 获取试卷列表 数量 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  int<BR>
	 */
	public int selectExamListCount(Map<String, Object> param);
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getPaperByCategory <BR>
	 * Description: 根据试卷分类获取试卷 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @param name
	 * @param comId
	 * @return  List<PaperBean><BR>
	 */
	List<PaperBean> getPaperByCategory(@Param("categoryIdList")List<Integer> categoryIdList, @Param("name")String name,
			@Param("companyId")Integer companyId,@Param("subCompanyId")int subCompanyId,@Param("fromNum")long fromNum,@Param("pageSize")String pageSize) throws Exception;

	int getPaperByCategoryCount(@Param("categoryIdList")List<Integer> categoryIdList, @Param("name")String name,
			@Param("companyId")Integer companyId,@Param("subCompanyId")int subCompanyId) throws Exception;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getTotalScoreById <BR>
	 * Description: 根据id查询试卷总分 <BR>
	 * Remark: <BR>
	 * @param paperId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	Integer getTotalScoreById(@Param("paperId")Integer paperId) throws DataBaseException;
	
	/**
	 * Method name: getCoursePapers <BR>
	 * Description: 查询课程试卷 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return
	 * @throws DataBaseException  List<PaperBean><BR>
	 */
	List<PaperBean> getCoursePapers(@Param("courseId")Integer courseId) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
