/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamMarkScoreServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/09        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.ExamUserAnswerVo;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamScheduleBean;
import com.jftt.wifi.bean.exam.ExamUserAnswerBean;
import com.jftt.wifi.bean.exam.vo.ExamSearchOptionVo;
import com.jftt.wifi.bean.exam.vo.MarkExamListItemVo;
import com.jftt.wifi.bean.exam.vo.cjylSearchVo;
import com.jftt.wifi.bean.vo.ExamScheduleVo;
import com.jftt.wifi.dao.ExamAssignInfoDaoMapper;
import com.jftt.wifi.dao.ExamScheduleDaoMapper;
import com.jftt.wifi.dao.ExamUserAnswerDaoMapper;
import com.jftt.wifi.dao.ManageDepartmentDaoMapper;
import com.jftt.wifi.dao.MatchJoinUserDaoMapper;
import com.jftt.wifi.service.ExamMarkScoreService;
import com.jftt.wifi.service.ExamPaperService;
import com.jftt.wifi.service.ExamService;
import com.jftt.wifi.service.ManageParamService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.MyExamManageService;
import com.jftt.wifi.util.MyExcelHelp;

/**
 * class name:ExamMarkScoreServiceImpl <BR>
 * class description: 考试批阅服务实现类 <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/09
 * @author JFTT)wangyifeng
 */
@Service
public class ExamMarkScoreServiceImpl implements ExamMarkScoreService {
	@Autowired
	private ExamAssignInfoDaoMapper examAssignInfoDaoMapper;
	@Autowired
	private ExamUserAnswerDaoMapper examUserAnswerDaoMapper;
	@Autowired
	private ExamScheduleDaoMapper examScheduleDaoMapper;
	@Autowired
	private ExamService examService;
	@Autowired
	private ExamPaperService paperService;
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private ManageDepartmentDaoMapper manageDepartmentDaoMapper;
	@Autowired
	private ManageParamService manageParamService;
	@Autowired
	private MatchJoinUserDaoMapper matchJoinUserDaoMapper;
	@Resource(name="myExamManageService")
	private MyExamManageService myExamManageService;

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamMarkScoreService#getMarkVoList(com.jftt.wifi.bean.exam.vo.ExamSearchOptionVo) <BR>
	 *      Method name: getMarkVoList <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param searchOption
	 * @return <BR>
	 */

	@Override
	public List<MarkExamListItemVo> getMarkVoList(
			ExamSearchOptionVo searchOption) {
		
		return examScheduleDaoMapper.getMarkVoList(searchOption);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.ExamMarkScoreService#getMarkTotalCount(com.jftt.wifi.bean.exam.vo.ExamSearchOptionVo) <BR>
	 * Method name: getMarkTotalCount <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/

	@Override
	public Integer getMarkTotalCount(ExamSearchOptionVo searchOption) {
		return examScheduleDaoMapper.getMarkTotalCount(searchOption);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamMarkScoreService#publishScore(java.lang.Integer) <BR>
	 *      Method name: publishScore <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param examId
	 * <BR>
	 */

	@Override
	public void publishScore(Integer examId) {
		// TODO wangyifeng
		examScheduleDaoMapper.publishScore(examId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamMarkScoreService#modifyScore(com.jftt.wifi.bean.exam.ExamAssignBean) <BR>
	 *      Method name: modifyScore <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param examAssign
	 * <BR>
	 */

	@Override
	public void modifyScore(ExamAssignBean examAssign) {
		// TODO wangyifeng test
		// need: examAssignId, newScore, reason
		// 仅仅修改score字段可能还不够，还需要修改其他字段？（is_passed,is_score_modified等

		// 强制设置成绩被修改属性，简化sql处理
		examAssign.setIsScoreModified(true);
		examAssignInfoDaoMapper.modifyScore(examAssign);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.ExamMarkScoreService#checkAndUpdateIsMarkedState(java.lang.Integer) <BR>
	 * Method name: checkAndUpdateIsMarkedState <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param examId  <BR>
	*/

	@Override
	public void checkAndUpdateIsMarkedState(Integer examId) {
		// TODO Auto-generated method stub
		ExamSearchOptionVo searchOption = new ExamSearchOptionVo();
		searchOption.setExamId(examId);
		List<MarkExamListItemVo> markResultList = getMarkVoList(searchOption);
		if (markResultList.size() == 1) {
			MarkExamListItemVo markResult = markResultList.get(0);
			// 可能存在一些进入考试但没有提交试卷的记录，如何处理？
			// 现在的考虑是：
			// 对于这部分人，依旧可以手工批阅，但答题记录都留空
			// 批阅完毕后，更新assign_info的is_marked字段
			// 确保在下面条件成立时，更新考试的is_marked为1
			// 条件：参与考试的人数==已经批阅的人数
			if (markResult.getTotalCountToBeMarked() == markResult
					.getMarkCount()) {
				examScheduleDaoMapper.updateIsMarkedState(examId);
			}
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamMarkScoreService#getAttendUserList(java.lang.Integer) <BR>
	 * Method name: getAttendUserList <BR>
	 * Description: 获取批阅页面参加考试的人员 <BR>
	 * Remark: <BR>
	 * @param examId
	 * @return <BR>
	 * @throws Exception 
	 */

	@Override
	public List<ExamAssignBean> getAttendUserList(ExamQueryConditionBean bean) throws Exception {
		// TODO wangyifeng test
		// 根据用户ID列表，获取用户摘要信息
		String name = bean.getName();
		String depName = bean.getDepartment();
		String post = bean.getPost();
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(name)) {
			map.put("name", name);
		}
		if (StringUtils.isNotEmpty(depName)) {
			map.put("deptId", depName);
		}
		if (StringUtils.isNotEmpty(post)) {
			map.put("postId", post);
		}
		List<ManageUserBean> tempUserList = null;
		if(map.size()>0){
			tempUserList = manageUserService.findUserByListCondition(map);
			if (tempUserList == null || tempUserList.size() == 0) {
				return null;
			}
		}
		bean.setUserList(tempUserList);
		//通过匹配到user去查询考试记录
		List<ExamAssignBean> attendUserInfoList = examAssignInfoDaoMapper.getAttendUserInfoList(bean);
		List<String> userIds = new ArrayList<String>();
		for(ExamAssignBean assignBean:attendUserInfoList){
			/*if(assignBean.getUserId() != null){
				userIds.add(assignBean.getUserId().toString());
			}*/
			ManageUserBean userBean = manageUserService.findUserById(assignBean.getUserId()+"");
			assignBean.setName(userBean.getName());
		}
		//查询用户接口
		//List<ManageUserBean> userList = manageUserService.findUserByIds(userIds);
		return attendUserInfoList;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamMarkScoreService#getAnswerList(java.lang.Integer) <BR>
	 *      Method name: getAnswerList <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param examAssignId
	 * @return <BR>
	 */

	@Override
	public List<ExamUserAnswerBean> getAnswerList(Integer examAssignId) {
		// TODO wangyifeng test
		return examUserAnswerDaoMapper.getAnswerList(examAssignId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamMarkScoreService#markScore(java.util.List) <BR>
	 *      Method name: markScore <BR>
	 *      Description: 批阅时更新成绩 <BR>
	 *      Remark: <BR>
	 * @param scoreList
	 * <BR>
	 * @throws Exception 
	 */

	@Override
	@Transactional
	public void markScore(ExamUserAnswerVo vo) throws Exception {
		// TODO wangyifeng test
		// input need:
		// (examUserAnswer.)id, getScore, evaluation(非必须)
		//Assert.notNull(examAssign.getUserAnswerList());
		int assign_id = vo.getExamAssignId();
		if(vo.getId() != null){
			String[] id = vo.getId().split(",");
			String[] getScore =vo.getGetScore().split(",");
			for (int i=0;i<id.length;i++) {
				ExamUserAnswerBean bean = new ExamUserAnswerBean();
				//bean.setExamAssignId(assign_id);
				bean.setId(Integer.parseInt(id[i]));
				bean.setGetScore(Integer.parseInt(getScore[i]));
				examUserAnswerDaoMapper.updateScore(bean);
			}
			
			//计算组合题的总得分
			List<ExamUserAnswerBean> list = myExamManageService.getQuestionGetScore(vo.getExamAssignId());
			for(int i=0;i<list.size();i++){
				ExamUserAnswerBean bean = list.get(i);
				List<ExamUserAnswerBean> subAnswers = bean.getSubAnswers();
				if(subAnswers != null && !subAnswers.isEmpty()){
					int getScore2 = 0;
					for(int j=0;j<subAnswers.size();j++){
						getScore2 += subAnswers.get(j).getGetScore();
					}
					bean.setGetScore(getScore2);
					myExamManageService.modifyGetScore(bean);
				}
			}
		}
		ExamAssignBean examAssign = new ExamAssignBean();
		examAssign.setId(assign_id);
		examAssign.setScore(vo.getTotalScore());
		examAssign.setExamStatus(4);//已批阅
		int passScore = examAssignInfoDaoMapper.selectPassScore(assign_id);
		/*int x_userId = vo.getUserId();
		String x_matchId = vo.getMatchId();*/
		if(vo.getTotalScore() >= passScore){
			examAssign.setIsPassed(true);
			/*int x_promoteStatus = 1;//已晋升
			if(x_matchId!=null && !"".equals(x_matchId)){
				matchJoinUserDaoMapper.updatePromoteStatus(x_userId,x_matchId,x_promoteStatus);
			}*/
		}else{
			examAssign.setIsPassed(false);
			/*int x_promoteStatus = 0;//未晋升
			if(x_matchId!=null && !"".equals(x_matchId)){
				matchJoinUserDaoMapper.updatePromoteStatus(x_userId,x_matchId,x_promoteStatus);
			}*/
		}
		examAssign.setIsMarked(true);
		examAssignInfoDaoMapper.modifyScore(examAssign);
		//更新考试表中的批阅字段
		modifyExamIsMarked(vo.getExamId(),"isMarked");
	}
	
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modifyExamIsMarked <BR>
	 * Description: 更新一场考试的批阅字段,需要参加考试的人员全部批阅后才会更新 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id  void<BR>
	 */
	@Override
	@Transactional
	public void modifyExamIsMarked(int id,String modifyType){
		//先通过exam_assign_id查询出所有参加考试人员是否已经批阅
		ExamQueryConditionBean bean = new ExamQueryConditionBean();
		bean.setId(id);
		List<ExamAssignBean> list = examAssignInfoDaoMapper.getAttendUserInfoList(bean);
		List<Boolean> markedList = new ArrayList<Boolean>();
		for(int i=0;i<list.size();i++){
			markedList.add(list.get(i).getIsMarked());
		}
		ExamScheduleBean exam = new ExamScheduleBean();
		exam.setId(id);
		if("isMarked".equals(modifyType)){
			if(!markedList.contains(false)){
				exam.setIsMarked(1);
				examScheduleDaoMapper.modifyExam(exam);
			}
		}else{
			exam.setIsMarked(1);
			exam.setIsScorePublished(true);
			examScheduleDaoMapper.modifyExam(exam);
		}
		
		/*for(int i=0;i<markedList.size();i++){
			if(!markedList.contains(false)){
				ExamScheduleBean exam = new ExamScheduleBean();
				exam.setId(id);
				if("isMarked".equals(modifyType)){
					exam.setIsMarked(1);
				}else{
					exam.setIsMarked(1);
					exam.setIsScorePublished(true);
				}
				
			}
		}*/
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamMarkScoreService#getExamResultList(java.lang.Integer) <BR>
	 *      Method name: getExamResultList <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param examId
	 * @param curUserId
	 * @return <BR>
	 * @throws Exception 
	 */

	@Override
	public List<ExamAssignBean> getExamResultList(cjylSearchVo paramVo,
			String curUserIdStr) throws Exception {
		String name = paramVo.getUserName();
		String depName = paramVo.getDepName();
		String post = paramVo.getPostName();
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(name)) {
			map.put("name", name);
		}
		if (StringUtils.isNotEmpty(depName)) {
			map.put("deptId", depName);
		}
		if (StringUtils.isNotEmpty(post)) {
			map.put("postId", post);
		}
		List<ManageUserBean> userList = null;
		if(map.size()>0){
			userList = manageUserService.findUserByListCondition(map);
			if (userList == null || userList.size() == 0) {
				return new ArrayList<ExamAssignBean>();
			}
		}
		paramVo.setUserList(userList);
		List<ExamAssignBean> resultList = examAssignInfoDaoMapper.getExamResultList(paramVo);
		for (ExamAssignBean assignBean : resultList) {
			int userId  = assignBean.getUserId();
			ManageUserBean userBean = manageUserService.findUserById(userId+"");
			Integer depId = 0;
			String userName = "暂无";
			String postName = "暂无";
			String departmentName = "暂无";
			Integer postId = 0;
			if(userBean != null){
				depId = userBean.getDeptId();
				userName = userBean.getName();
				postId = userBean.getPostId();
				//获取部门名称
				if(depId != null){
					ManageDepartmentBean dept= manageDepartmentDaoMapper.getManageDepartmentById(depId);
					if(dept != null){
						departmentName = dept.getName();
					}
				}
				if(postId != null){
					ManagePostBean postBean = manageParamService.selectManagePostById(postId);
					if(postBean!=null){
						postName = postBean.getName();
					}
				}
				
				//身份证
				assignBean.setId_card(userBean.getIdCard());
			}
			assignBean.setDepartmentName(departmentName);
			assignBean.setPostName(postName);
			assignBean.setName(userName);
		}
		return resultList;
	}



	/**chenrui start
	 * 
	 * 
	 * @throws Exception */
	@Override
	public List<ExamAssignBean> getCjYlListAll(cjylSearchVo paramVo, String curUserIdStr) throws Exception {
		int tmpRank = 0;
		List<ExamAssignBean> resultList = examAssignInfoDaoMapper.getCjYlListAll(paramVo);
		for (ExamAssignBean assignBean : resultList) {
			int userId  = assignBean.getUserId();
			ManageUserBean userBean = manageUserService.findUserByIdCondition(userId+"");
			String depName = "";
			String poName = "";
			String name = "";
			if(userBean!=null){
				if(userBean.getDeptName()!=null){
					depName = userBean.getDeptName();
				}
				if(userBean.getPostName()!=null){
					poName = userBean.getPostName();
				}
				if(userBean.getName()!=null){
					name = userBean.getName();
				}
				assignBean.setDepartmentName(depName);
				assignBean.setPostName(poName);
				assignBean.setName(name);
				Integer rank = assignBean.getRank();
				if(rank!=null && rank!=0){
					tmpRank = rank;
				}else{
					assignBean.setRank(tmpRank+1);
				}
			}
			
		}
		return resultList;
	}
	@Override
	public int getExamResultListCount(cjylSearchVo paramVo) throws Exception {
		String name = paramVo.getUserName();
		String depName = paramVo.getDepName();
		String post = paramVo.getPostName();
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(name)) {
			map.put("name", name);
		}
		if (StringUtils.isNotEmpty(depName)) {
			map.put("deptId", depName);
		}
		if (StringUtils.isNotEmpty(post)) {
			map.put("postId", post);
		}
		List<ManageUserBean> userList = null;
		if(map.size()>0){
			userList = manageUserService.findUserByListCondition(map);
			if (userList == null || userList.size() == 0) {
				return 0;
			}
		}
		paramVo.setUserList(userList);
		return examAssignInfoDaoMapper.getExamResultListCount(paramVo);
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.ExamMarkScoreService#getExamInfo(int) <BR>
	 * Method name: getExamInfo <BR>
	 * Description:成绩预览-获取考试信息  <BR>
	 * Remark: <BR>
	 * @param examId
	 * @param bean 
	 * @throws Exception  <BR>
	 */
	@Override
	public ExamScheduleVo getExamInfo(int examId) throws Exception {
		return examScheduleDaoMapper.selectExamInfo(examId);
	}
	
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.ExamMarkScoreService#exportExcel(java.lang.String, java.lang.String) <BR>
	 * Method name: exportExcel <BR>
	 * Description: 成绩预览-导出Excel <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param examId
	 * @throws Exception  <BR>
	 */
	@Override
	public HSSFWorkbook exportExcel(cjylSearchVo paramVo) throws Exception {
		List<ExamAssignBean> list = getExamResultList(paramVo, paramVo.getUserId());
		int maxRow = list.size();
		return writeExcel("成绩预览",maxRow, 7, list);
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: writeExcel <BR>
	 * Description: 写入Excel文件 <BR>
	 * Remark: <BR>  void<BR>
	 */
		public  HSSFWorkbook writeExcel(String sheetName,int maxRow, int maxCol, List<ExamAssignBean> dataList) throws Exception {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();
			workbook.setSheetName(0, sheetName);
			// 设置字体
			HSSFFont font = workbook.createFont();
			font.setFontHeightInPoints((short) 12);
			font.setFontName("宋体");

			HSSFDataFormat dataFormat = workbook.createDataFormat();
			// 单元格数据样式准备设置
			HSSFCellStyle leftStyle = MyExcelHelp.createCellStyle(workbook, font, CellStyle.ALIGN_LEFT);// 左
			HSSFCellStyle centerStyle = MyExcelHelp.createCellStyle(workbook, font, CellStyle.ALIGN_CENTER);// 左
			HSSFCellStyle rightStyle = MyExcelHelp.createCellStyle(workbook, font, CellStyle.ALIGN_RIGHT);// 右
			HSSFCellStyle wrapStyle = MyExcelHelp.createWrapCellStyle(workbook, font, CellStyle.ALIGN_LEFT, true);// 单元格内容自动换行
			HSSFCellStyle dateStyle = MyExcelHelp.createFormatCellStyle(workbook, font, CellStyle.ALIGN_RIGHT, dataFormat, MyExcelHelp.DATE_FORMAT);
			HSSFCellStyle moneyStyle = MyExcelHelp.createFormatCellStyle(workbook, font, CellStyle.ALIGN_RIGHT, dataFormat,MyExcelHelp. MONEY_FORMAT);
			int rowIndex = 0;
			// 标题行
			HSSFRow row = sheet.createRow(rowIndex);
			int j = 0;
			
			MyExcelHelp.createStringCell(row, 0, centerStyle, "姓名");
			sheet.setColumnWidth(0, 10 * 128);
			MyExcelHelp.createStringCell(row, 1, centerStyle, "岗位");
			sheet.setColumnWidth(0, 10 * 128);
			MyExcelHelp.createStringCell(row, 2, centerStyle, "部门");
			sheet.setColumnWidth(0, 10 * 128);
			MyExcelHelp.createStringCell(row, 3, centerStyle, "是否参与考试");
			sheet.setColumnWidth(0, 10 * 128);
			MyExcelHelp.createStringCell(row, 4, centerStyle, "成绩");
			sheet.setColumnWidth(0, 10 * 128);
			MyExcelHelp.createStringCell(row, 5, centerStyle, "是否通过");
			sheet.setColumnWidth(0, 10 * 128);
			MyExcelHelp.createStringCell(row, 6, centerStyle, "排名");
			sheet.setColumnWidth(0, 10 * 128);
			int rank = 0;
			int tmpRank = 0;
			for (j = 0; j < maxRow; j++) {// 控制行
				ExamAssignBean bean = dataList.get(j);
				row = sheet.createRow(++rowIndex);
				String isAttend = bean.getIsAttended()==true?"是":"否";
				String isPass = "";
				if(bean.getIsPassed() != null){
					isPass = bean.getIsPassed()==true?"是":"否";
				}else{
					isPass ="否";
				}
				if(bean.getRank()!=null && bean.getRank()!=0){
					rank = bean.getRank();
					tmpRank = rank;
				}else{
					rank = tmpRank +1;
				}
				int colIndex = 0;
				MyExcelHelp.createStringCell(row, colIndex++, leftStyle, bean.getName()==null?"":bean.getName());
				MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, bean.getPostName()==null?"":bean.getPostName());
				MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, bean.getDepartmentName()==null?"":bean.getDepartmentName());
				MyExcelHelp.createStringCell(row, colIndex++, wrapStyle,isAttend);
				MyExcelHelp.createStringCell(row, colIndex++, moneyStyle,bean.getScore()==null?"0":(bean.getScore()+""));
				MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, isPass);
				MyExcelHelp.createStringCell(row, colIndex++, moneyStyle,rank+"");
			}
			return workbook;
		}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.ExamMarkScoreService#exportDoc(com.jftt.wifi.bean.exam.vo.cjylSearchVo) <BR>
	 * Method name: exportDoc <BR>
	 * Description: 成绩预览-导出word <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public XWPFDocument exportDoc(cjylSearchVo paramVo) throws Exception {
		List<ExamAssignBean> list = getExamResultList(paramVo, paramVo.getUserId());
		int maxRow = list.size();
		return writeWord(maxRow,7,list);
	}
	
	private XWPFDocument writeWord(int maxRow, int maxCol, List<ExamAssignBean> dataList) throws Exception{
		XWPFDocument doc = new XWPFDocument();
		//多加一行表格头
		maxRow = maxRow+1;
		XWPFTable table = doc.createTable(maxRow, maxCol);
		table.setCellMargins(50, 50, 50, 50);
		List<XWPFTableCell> tableCellsHead = table.getRow(0).getTableCells();
		tableCellsHead.get(0).setText("姓名");
		tableCellsHead.get(1).setText("岗位");
		tableCellsHead.get(2).setText("部门");
		tableCellsHead.get(3).setText("是否参与考试");
		tableCellsHead.get(4).setText("成绩");
		tableCellsHead.get(5).setText("是否通过");
		tableCellsHead.get(6).setText("排名");
		int rank =0;
		int tmpRank = 0;
		for(int i=1;i<maxRow;i++){
			List<XWPFTableCell> tableCells = table.getRow(i).getTableCells();
			ExamAssignBean bean = dataList.get(i-1);
			String isAttend = bean.getIsAttended()==true?"是":"否";
			String isPass = "";
			if(bean.getIsPassed() != null){
				isPass = bean.getIsPassed()==true?"是":"否";
			}else{
				isPass = "否";
			}
			if(bean.getRank()!=null && bean.getRank()!=0){
				rank = bean.getRank();
				tmpRank = rank;
			}else{
				rank = tmpRank +1;
			}
			tableCells.get(0).setText(bean.getName()==null?"":bean.getName());
			tableCells.get(1).setText(bean.getPostName()==null?"":bean.getPostName());
			tableCells.get(2).setText(bean.getDepartmentName()==null?"":bean.getDepartmentName());
			tableCells.get(3).setText(isAttend);
			tableCells.get(4).setText(bean.getScore()==null?"0":(bean.getScore()+""));
			tableCells.get(5).setText(isPass);
			tableCells.get(6).setText(rank+"");
		}
		return doc;
	}
	
	
	/**chenrui end*/
	
	/**zhangchen start */
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getUserByRelationId <BR>
	 * Description: 查询一场考试是否有添加人员 <BR>
	 * Remark: <BR>
	 * @param relation_id
	 * @return  boolean<BR>
	 */
	@Override
	public boolean getUserByRelationId(int relation_id){
		List<ExamAssignBean> list = examAssignInfoDaoMapper.selectUserByRelationId(relation_id);
		if(list != null && !list.isEmpty()){
			return true;
		}else{
			return false;
		}
	}

	/**zhangchen end */

}
