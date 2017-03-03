package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.jftt.wifi.bean.ApproveBean;
import com.jftt.wifi.bean.ApproveRecordBean;
import com.jftt.wifi.bean.ApproveStepBean;
import com.jftt.wifi.bean.KnowledgeBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.OamTopicBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.ShareRecordBean;
import com.jftt.wifi.common.ApproveStatusConstant;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.ApproveManageDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.ApproveManageService;
import com.jftt.wifi.service.CourseDetailService;
import com.jftt.wifi.service.KnowledgeManageService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.OamService;
import com.jftt.wifi.util.JsonUtil;

@Service("approveManageService")
public class ApproveManageServiceImpl implements ApproveManageService {
	
	@Resource(name="approveManageDaoMapper")
	private ApproveManageDaoMapper approveManageDaoMapper;
	@Autowired
	private ManageUserService manageUserService;
	@Resource(name="oamService")
	private OamService oamService;
	@Autowired
	private KnowledgeManageService knowledgeManageService;
	@Autowired
	private CourseDetailService  courseDetailService;
	@Override
	public JSONArray	querySubCompanyList(int companyId) throws Exception{
		
		List<Map> lsmp= approveManageDaoMapper.querySubCompanyList(companyId);
		return JsonUtil.getJson4JavaList(lsmp);
	}
	@Override
	public JSONArray queryCompanyList(){
		List<Map> lsmp= approveManageDaoMapper.queryCompanyList();
		return JsonUtil.getJson4JavaList(lsmp);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveApproveInfo(ApproveBean approveBean) throws Exception {
		//此处经过讨论，得出以下结论。如果流程已经启动，则给以原流程进行
		
		/*int wayId = approveBean.getWayId();
		//此处做判断，如果wayId存在，则表明不是新加，而是更新
		if(Constant.CONSTANT_0==approveBean.getWayId()){
			
			//此处需要入三张表。ap_approveway、ap_approvestep、ap_approvedept
			//1、入表ap_approveway，并返回主键
			approveManageDaoMapper.insertApproveWay(approveBean);
			wayId = approveBean.getWayId();
			//判断，如果不需要审批，则直接return
			if(Constant.CONSTANT_1!=approveBean.getIsApprove()){
				return;
			}
		}
		else{
			
			//1、更新表ap_approveway ,将此流程设置为删除，然后添加新的流程。
			 approveManageDaoMapper.updateApproveWay(approveBean);
			 
			 //2、添加新的流程
			 approveManageDaoMapper.insertApproveWay(approveBean);
				wayId = approveBean.getWayId();
			 //删除步骤表和应用部门表
			 approveManageDaoMapper.deleteApproveStep(wayId);
			 approveManageDaoMapper.deleteApproveDept(wayId);
			 
			//判断，如果不需要审批，则直接return
			if(Constant.CONSTANT_1!=approveBean.getIsApprove()){
				return;
			}
			
		}
		*/
		//1、更新表ap_approveway ,每一次更新都是添加新的流程。
		 approveManageDaoMapper.updateApproveWay(approveBean);
		 
		 //2、添加新的流程
		 approveManageDaoMapper.insertApproveWay(approveBean);
		 int	wayId = approveBean.getWayId();
		
		//3、入表ap_approveStep
		List<ApproveStepBean> list = approveBean.getListStep();
		//此处循环插入数据。一般最多不会超过10
		for(ApproveStepBean bean:list){
			bean.setWayId(wayId);//将获取的wayId组织加入bean中
			approveManageDaoMapper.insertApproveStep(bean);
		}
		
		//4、入表ap_approvedept 
		
		List<Integer> depts = approveBean.getDeptList();
		for(Integer deptId:depts){
			//组织参数
			Map<String,Integer> param = new HashMap<String, Integer>();
			param.put("wayId", wayId);
			param.put("deptId", deptId);
			approveManageDaoMapper.insertApprovDept(param);
		}
		
	}

	@Override
	public List<ApproveBean> queryApproveInfo(ApproveBean approveBean){
		
		List<ApproveBean> relist = new ArrayList<ApproveBean>();
		//此处分三步进行查询，然后再组装
		//1、查询审核路线表信息
		List<ApproveBean> appBeanList = approveManageDaoMapper.queryApproveWayList(approveBean);
		
		for(ApproveBean appBean:appBeanList){
			
			if(null!=appBean&&appBean.getWayId()!=0){
				
				//2、查询审核步骤信息
				List<ApproveStepBean> asBean = approveManageDaoMapper.queryApproveStep(appBean.getWayId());
				
				//3、查询审核应用部门信息
				List<Integer> list = approveManageDaoMapper.queryApproveDept(appBean.getWayId());
				
				appBean.setListStep(asBean);
				appBean.setDeptList(list);
				relist.add(appBean);
			}
		}
		
		return relist;
	}

	@Override
	public List<KnowledgeBean> queryKnowledge(Map<String,Object> param) {
		return approveManageDaoMapper.queryKnowledge(param);
	}
	@Override
	public int queryKnowledgeCount(Map<String,Object> param) {
		return approveManageDaoMapper.queryKnowledgeCount(param);
	}
	@Override
	public List<ResCourseBean> queryApproveCourseList(Map<String,Object> param){
		return approveManageDaoMapper.queryApproveCourseList(param);
	}
	@Override
	public int queryApproveCourseCount(Map<String,Object> param){
		return approveManageDaoMapper.queryApproveCourseCount(param);
	}
	@Override
	public List<OamTopicBean> queryApproveTopicList(Map<String,Object> param){
		return approveManageDaoMapper.queryApproveTopicList(param);
	}
	@Override
	public int queryApproveTopicCount(Map<String,Object> param){
		return approveManageDaoMapper.queryApproveTopicCount(param);
	}
	
	@Override
	public List<KnowledgeBean> queryKnowledgeByPL(Map<String,Object> param) {
		return approveManageDaoMapper.queryKnowledgeByPL(param);
	}
	@Override
	public int queryKnowledgeCountByPL(Map<String,Object> param) {
		return approveManageDaoMapper.queryKnowledgeCountByPL(param);
	}
	@Override
	public List<ResCourseBean> queryApproveCourseListByPL(Map<String,Object> param){
		return approveManageDaoMapper.queryApproveCourseListByPL(param);
	}
	@Override
	public int queryApproveCourseCountByPL(Map<String,Object> param){
		return approveManageDaoMapper.queryApproveCourseCountByPL(param);
	}
	@Override
	public List<OamTopicBean> queryApproveTopicListByPL(Map<String,Object> param){
		return approveManageDaoMapper.queryApproveTopicListByPL(param);
	}
	@Override
	public int queryApproveTopicCountByPL(Map<String,Object> param){
		return approveManageDaoMapper.queryApproveTopicCountByPL(param);
	}
	

	
	
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void approveObj(ApproveRecordBean approveRecordBean) throws Exception{
		approveRecordBean = genRecordBean(approveRecordBean);
		approveRecordBean.setId(approveManageDaoMapper.queryApproverecordId(approveRecordBean));
		//1、首先需要做的是更新当条的记录。将上一条记录状态更新为完成，status 2
		approveManageDaoMapper.updateRecordStatus(approveRecordBean);
		
		
		//查找当前审核节点信息。此处不可能为空。否则流程没法向下走了
		ApproveStepBean currStepBean = approveManageDaoMapper.queryCurrStepInfo(approveRecordBean);
		
		//此处判断如果是通过，则需要查询下一个审核节点信息。并插入记录
		if(approveRecordBean.getIsPass()==1){
			
			//需要查找其下一个
			approveRecordBean.setCount(2);//查找下一个。eg。上一条是0，则当前一条是1,则下一个是0+2
			ApproveStepBean nextStepBean = approveManageDaoMapper.queryNextStepInfo(approveRecordBean);
			if(null==nextStepBean){//当数据位null，没有下一个节点信息时，此时代表审核已经通过
				//组装数据，入记录表 ap_approverecord
				approveRecordBean.setNextApproveType(-1);//下一个审核者不存在，这里默认给-1
				approveRecordBean.setNextApproveId(-1);
				approveRecordBean.setStatus(2);
				approveRecordBean.setWayId(currStepBean.getWayId());
				approveRecordBean.setCurrentStepId(currStepBean.getStepId());
				approveRecordBean.setCurrentOrderNum(currStepBean.getOrderNum());//审核步骤序号
				
				approveManageDaoMapper.insertApproveRecord(approveRecordBean);//插入审核记录
				
				//此处，审核通过，需要更改状态。
				//更新知识或课程或专题的表。
				approveRecordBean.setShareStatus(4);//代表审核通过
				dealTableShareStatus(approveRecordBean);

			}else{
				//此处需要判断，如果下一个节点是直属领导，此处需要查询出直接领导的岗位，并将岗位id处理
				if(nextStepBean.getApproverType()==2){
					int objUserId = approveRecordBean.getObjectUserId();
					ManageUserBean manageUserBean = manageUserService.findUserByIdCondition(String.valueOf(objUserId));//拿到用户对象
					int postId = manageUserBean.getPostId();
					String parentPostId = approveManageDaoMapper.queryParentPostId(postId);
					if(StringUtils.isNotBlank(parentPostId)){//存在上级时。则下一个节点记录
						approveRecordBean.setNextApproveType(nextStepBean.getApproverType());
						approveRecordBean.setNextApproveId(Integer.valueOf(parentPostId));
						approveRecordBean.setStatus(1);//此处状态为1，有下一步的流程
						approveRecordBean.setWayId(currStepBean.getWayId());
						approveRecordBean.setCurrentStepId(currStepBean.getStepId());
						approveRecordBean.setCurrentOrderNum(currStepBean.getOrderNum());//审核步骤序号.提交审核，默认给-1。代表的是提交审核
						//提交共享，插入审核记录表。
						approveManageDaoMapper.insertApproveRecord(approveRecordBean);//插入审核记录

					}else{//不存在上级时，跳入下一个审核环节
						//判断是否还有下一个环节，没有的话，直接通过
						approveRecordBean.setCount(3);
						ApproveStepBean threeBean = approveManageDaoMapper.queryNextStepInfo(approveRecordBean);
						if(threeBean==null){
							//1、不需要审批。	
							approveRecordBean.setShareStatus(4);
							//组装数据，入记录表 ap_approverecord 此时，记录表中很多字段用不上了。所以给默认值-1
							approveRecordBean.setNextApproveType(-1);//下一个审核者不存在，这里默认给-1
							approveRecordBean.setNextApproveId(-1);
							approveRecordBean.setStatus(2);//此处状态为1，无下一步的流程
							approveRecordBean.setWayId(currStepBean.getWayId());
							approveRecordBean.setCurrentStepId(currStepBean.getStepId());
							approveRecordBean.setCurrentOrderNum(currStepBean.getOrderNum());
							//提交共享，插入审核记录表。
							approveManageDaoMapper.insertApproveRecord(approveRecordBean);//插入审核记录
							dealTableShareStatus(approveRecordBean);
							
						}else{//有下一个环节，进入下一个环节
						
							ApproveStepBean nextBean = threeBean;
							approveRecordBean.setNextApproveType(nextBean.getApproverType());
							approveRecordBean.setNextApproveId(nextBean.getApproverId());
							approveRecordBean.setStatus(1);//此处状态为1，有下一步的流程
							approveRecordBean.setWayId(currStepBean.getWayId());
							approveRecordBean.setCurrentStepId(currStepBean.getStepId());
							approveRecordBean.setCurrentOrderNum(currStepBean.getOrderNum());
							//提交共享，插入审核记录表。
							approveManageDaoMapper.insertApproveRecord(approveRecordBean);//插入审核记
							
						}
					}
					
				}else{
				
				//组装数据，并记录下一个节点信息，入记录表 ap_approverecord
				approveRecordBean.setNextApproveType(nextStepBean.getApproverType());//
				approveRecordBean.setNextApproveId(nextStepBean.getApproverId());
				approveRecordBean.setStatus(1);//如果有下一个节点信息，此处需要给状态为1
				approveRecordBean.setWayId(currStepBean.getWayId());
				approveRecordBean.setCurrentStepId(currStepBean.getStepId());
				approveRecordBean.setCurrentOrderNum(currStepBean.getOrderNum());//当前审核的步骤序号
				
				approveManageDaoMapper.insertApproveRecord(approveRecordBean);
				}

			}
		}
		else{//驳回。流程结束。
			//1、组装数据，入记录表 ap_approverecord
			approveRecordBean.setNextApproveType(-1);//下一个审核者不存在，这里默认给-1
			approveRecordBean.setNextApproveId(-1);
			approveRecordBean.setStatus(2);
			approveRecordBean.setWayId(currStepBean.getWayId());
			approveRecordBean.setCurrentStepId(currStepBean.getStepId());
			approveRecordBean.setCurrentOrderNum(currStepBean.getOrderNum());//审核步骤序号

			approveManageDaoMapper.insertApproveRecord(approveRecordBean);
			
			//2、更新知识或课程或专题的表。
			approveRecordBean.setShareStatus(3);
			dealTableShareStatus(approveRecordBean);
		}
	}
	
	//处理更新主表中的状体
	private void dealTableShareStatus(ApproveRecordBean approveRecordBean) throws Exception{
		switch (approveRecordBean.getWayType()) {
		case 1://1为课程
			approveManageDaoMapper.updateCourseStatus(approveRecordBean);
			break;
		case 2://2为知识
			approveManageDaoMapper.updateKnowledgeStatus(approveRecordBean);
			break;
		case 3://3为专题
			approveManageDaoMapper.updateTopicStatus(approveRecordBean);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Method name: genRecordBean <BR>
	 * Description: 初始化部分参数 <BR>
	 * Remark: <BR>
	 * @param approveRecordBean
	 * @return
	 * @throws Exception  ApproveRecordBean<BR>
	 */
	private ApproveRecordBean genRecordBean(ApproveRecordBean approveRecordBean) throws Exception{
		//组装数据
		int objectCreateId = 0;
		Date createTime  = new Date();
		if(approveRecordBean.getWayType()==ApproveStatusConstant.WAY_TYPE_COURSE){//知识
			objectCreateId =courseDetailService.getCourseById(approveRecordBean.getObjectId()).getCreateUserId();
			createTime = courseDetailService.getCourseById(approveRecordBean.getObjectId()).getCreateTime();
		}else if(approveRecordBean.getWayType()==ApproveStatusConstant.WAY_TYPE_KNOWLEDGE){//课程
			objectCreateId =knowledgeManageService.queryKnowledgeDetail(approveRecordBean.getObjectId()).getCreateUserId();
			createTime = knowledgeManageService.queryKnowledgeDetail(approveRecordBean.getObjectId()).getCreateTime();
			
		}else if(ApproveStatusConstant.WAY_TYPE_TOPIC==approveRecordBean.getWayType()){//专题
			objectCreateId =oamService.selectTopicById(String.valueOf(approveRecordBean.getObjectId())).getCreateUserId();
			createTime = oamService.selectTopicById(String.valueOf(approveRecordBean.getObjectId())).getCreateTime();
		}
		approveRecordBean.setObjectUserId(objectCreateId);
		approveRecordBean.setObjectCreateTime(createTime);
		return approveRecordBean;
	}
	
	@Override
	public void approveObjByPL(ApproveRecordBean approveRecordBean) throws Exception{
		//此方法，主要针对普连用户，普连用户，只有一个状态。要么审核通过。更改对象表的share_status为7
		//要么审核驳回。更改对象表的share_status为6
		approveRecordBean = genRecordBean(approveRecordBean);
		if(approveRecordBean.getIsPass()==1){//审核通过
			
			approveRecordBean.setShareStatus(7);
		}else {//审核驳回
			approveRecordBean.setShareStatus(6);
		}
		
		//进行分类更改状态
		switch (approveRecordBean.getWayType()) {
		case 1://1为课程
			approveManageDaoMapper.updateCourseStatus(approveRecordBean);
			break;
		case 2://2为知识
			approveManageDaoMapper.updateKnowledgeStatus(approveRecordBean);
			break;
		case 3://3为专题
			approveManageDaoMapper.updateTopicStatus(approveRecordBean);
			break;
		default:
			break;
		}
		//组装数据，入记录表 ap_approverecord 此时，记录表中很多字段用不上了。所以给默认值-1
		approveRecordBean.setNextApproveType(-1);//下一个审核者不存在，这里默认给-1
		approveRecordBean.setNextApproveId(-1);
		approveRecordBean.setStatus(2);
		approveRecordBean.setWayId(-1);
		approveRecordBean.setCurrentStepId(-1);
		approveRecordBean.setCurrentOrderNum(0);//审核步骤序号
		
		approveManageDaoMapper.insertApproveRecord(approveRecordBean);//插入审核记录
	}
	
	@Override
	public void shareObj(ApproveRecordBean approveRecordBean) throws Exception{
		
		
		//首先先判断，是提交普连共享还是提交集团公司共享
		
		if(approveRecordBean.getShareStatus()==1||approveRecordBean.getShareStatus()==3){//如果为1，则是从未共享的或者共享被驳回的，需要提交集团共享
			//针对提交集团共享的，存在两种情况
			//1、当前此对象的配置了共享流程，需要走共享流程。
			ApproveBean approveBean = new 	ApproveBean();
			approveBean.setCompanyId(approveRecordBean.getCompanyId());
			approveBean.setWayType(approveRecordBean.getWayType());
			ApproveBean resultBean = approveManageDaoMapper.queryApproveWay(approveBean);
			//获取应用的部门
			List<Integer> deptList = null==resultBean?new ArrayList<Integer>():approveManageDaoMapper.queryApproveDept(resultBean.getWayId());
			if(null==resultBean||resultBean.getIsApprove()!=1||!deptList.contains(approveRecordBean.getSubCompanyId())){//三种情况，没有配置||配置了但是不需要审批||配置了审批但是没有应用到此部门
				//1、不需要审批。	
				approveRecordBean.setShareStatus(4);
				
				//组装数据，入记录表 ap_approverecord 此时，记录表中很多字段用不上了。所以给默认值-1
				approveRecordBean.setNextApproveType(-1);//下一个审核者不存在，这里默认给-1
				approveRecordBean.setNextApproveId(-1);
				approveRecordBean.setStatus(2);//此处状态为1，有下一步的流程
				approveRecordBean.setWayId(-1);
				approveRecordBean.setCurrentStepId(-1);
				approveRecordBean.setCurrentOrderNum(-1);//审核步骤序号.提交审核，默认给-1。代表的是提交审核
				//提交共享，插入审核记录表。
				approveManageDaoMapper.insertApproveRecord(approveRecordBean);//插入审核记录
			}else{
				
				//2、需要审批。
				approveRecordBean.setShareStatus(2);
				//查询出审核步骤信息。
				List<ApproveStepBean> listBean = approveManageDaoMapper.queryApproveStep(resultBean.getWayId());
				if(listBean.size()>0){
					//拿到工作流的第一个
					ApproveStepBean firstBean = listBean.get(0);
					//组装数据，入记录表 ap_approverecord 此时，记录表中很多字段用不上了。所以给默认值-1
					approveRecordBean.setNextApproveType(firstBean.getApproverType());//下一个审核者不存在，这里默认给-1
					//此处需要注意。如果下一个审核为直属领导，则需要查出直属领导的岗位id
					if(firstBean.getApproverType()==2){
						int objUserId = approveRecordBean.getObjectUserId();
						ManageUserBean manageUserBean = manageUserService.findUserByIdCondition(String.valueOf(objUserId));//拿到用户对象
						int postId = manageUserBean.getPostId();
						String parentPostId = approveManageDaoMapper.queryParentPostId(postId);
						if(StringUtils.isNotBlank(parentPostId)){//存在上级时。由上级审核
							approveRecordBean.setNextApproveId(Integer.valueOf(parentPostId));
							approveRecordBean.setStatus(1);//此处状态为1，有下一步的流程
							approveRecordBean.setWayId(firstBean.getWayId());
							approveRecordBean.setCurrentStepId(firstBean.getStepId());
							approveRecordBean.setCurrentOrderNum(-1);//审核步骤序号.提交审核，默认给-1。代表的是提交审核
							//提交共享，插入审核记录表。
							approveManageDaoMapper.insertApproveRecord(approveRecordBean);//插入审核记录

						}else{//不存在上级时，跳入下一个审核环节
							//判断是否还有下一个环节，没有的话，直接通过
							if(listBean.size()==1){
								//1、不需要审批。	
								approveRecordBean.setShareStatus(4);
								//组装数据，入记录表 ap_approverecord 此时，记录表中很多字段用不上了。所以给默认值-1
								approveRecordBean.setNextApproveType(-1);//下一个审核者不存在，这里默认给-1
								approveRecordBean.setNextApproveId(-1);
								approveRecordBean.setStatus(2);//此处状态为1，有下一步的流程
								approveRecordBean.setWayId(-1);
								approveRecordBean.setCurrentStepId(-1);
								approveRecordBean.setCurrentOrderNum(-1);//审核步骤序号.提交审核，默认给-1。代表的是提交审核
								//提交共享，插入审核记录表。
								approveManageDaoMapper.insertApproveRecord(approveRecordBean);//插入审核记录
								
							}else{//有下一个环节，进入下一个环节
							
								ApproveStepBean nextBean = listBean.get(1);
								approveRecordBean.setNextApproveType(nextBean.getApproverType());
								approveRecordBean.setNextApproveId(nextBean.getApproverId());
								approveRecordBean.setStatus(1);//此处状态为1，有下一步的流程
								approveRecordBean.setWayId(nextBean.getWayId());
								approveRecordBean.setCurrentStepId(nextBean.getStepId());
								approveRecordBean.setCurrentOrderNum(-1);//审核步骤序号.提交审核，默认给-1。代表的是提交审核
								//提交共享，插入审核记录表。
								approveManageDaoMapper.insertApproveRecord(approveRecordBean);//插入审核记
								
							}
						}
						
					}else{
						approveRecordBean.setNextApproveId(firstBean.getApproverId());
						approveRecordBean.setStatus(1);//此处状态为1，有下一步的流程
						approveRecordBean.setWayId(firstBean.getWayId());
						approveRecordBean.setCurrentStepId(firstBean.getStepId());
						approveRecordBean.setCurrentOrderNum(-1);//审核步骤序号.提交审核，默认给-1。代表的是提交审核
						//提交共享，插入审核记录表。
						approveManageDaoMapper.insertApproveRecord(approveRecordBean);//插入审核记录
					}
				}else{
					//没有审批步骤信息，则走不需要审批流程。	
					approveRecordBean.setShareStatus(4);
					
					//组装数据，入记录表 ap_approverecord 此时，记录表中很多字段用不上了。所以给默认值-1
					approveRecordBean.setNextApproveType(-1);//下一个审核者不存在，这里默认给-1
					approveRecordBean.setNextApproveId(-1);
					approveRecordBean.setStatus(2);//此处状态为1，有下一步的流程
					approveRecordBean.setWayId(-1);
					approveRecordBean.setCurrentStepId(-1);
					approveRecordBean.setCurrentOrderNum(-1);//审核步骤序号.提交审核，默认给-1。代表的是提交审核
					//提交共享，插入审核记录表。
					approveManageDaoMapper.insertApproveRecord(approveRecordBean);//插入审核记录
				}
			}
			
		}else{//需要提交普连共享。
			approveRecordBean.setShareStatus(5);
			
			//组装数据，入记录表 ap_approverecord 此时，记录表中很多字段用不上了。所以给默认值-1
			approveRecordBean.setNextApproveType(-1);//下一个审核者不存在，这里默认给-1
			approveRecordBean.setNextApproveId(-1);
			approveRecordBean.setStatus(1);//此处状态为1，有下一步的流程
			approveRecordBean.setWayId(-1);
			approveRecordBean.setCurrentStepId(-1);
			approveRecordBean.setCurrentOrderNum(-1);//审核步骤序号.提交审核，默认给-1。代表的是提交审核
			//提交共享，插入审核记录表。
			approveManageDaoMapper.insertApproveRecord(approveRecordBean);//插入审核记录
		}
		approveRecordBean.setIsUpdateShareTime(1);//需要更新共享时间为当前时间
		//进行分类更改状态
		switch (approveRecordBean.getWayType()) {
		case 1://1为课程
			approveManageDaoMapper.updateCourseStatus(approveRecordBean);
			break;
		case 2://2为知识
			approveManageDaoMapper.updateKnowledgeStatus(approveRecordBean);
			break;
		case 3://3为专题
			approveManageDaoMapper.updateTopicStatus(approveRecordBean);
			break;
		default:
			break;
		}
	}

	
	/**
	 * Method name: startWorkFlow <BR>
	 * Description: 启动工作流 <BR>
	 * Remark: <BR>
	 * @param approveRecordBean 传入参数需要赋值有： userId 当前操作者id。CompanyId：当前对象的创建公司ID
	 * ObjectId：当前对象id。 WayType：当前对象类型，1课程2知识3专题。
	 * ObjectUserId当前对象的创建者id。SubCompanyId：当前对象的分公司id
	 *   void<BR>
	 * @throws Exception 
	 */
	@Override
	public  void  startWorkFlow(ResCourseBean resCourseBean) throws Exception{
		//
		if(resCourseBean.getCompanyId()==Constant.PULIAN_COMPANY_ID){
			return;//普联用户不需要走一下流程
		}
		ApproveRecordBean approveRecordBean = new ApproveRecordBean ();
		/*if(resCourseBean.getShareStatus()==ApproveStatusConstant.APRROVE_TJ){//
			approveRecordBean.setShareStatus(ApproveStatusConstant.APRROVE_WTJ);//提交集团共享
		}else if(resCourseBean.getShareStatus()==ApproveStatusConstant.APRROVE_PLTJ){
		}*/
		approveRecordBean.setShareStatus(resCourseBean.getShareStatus());//提交集团共享
		approveRecordBean.setUserId(resCourseBean.getCreateUserId());
		approveRecordBean.setCompanyId(resCourseBean.getCompanyId());
		approveRecordBean.setObjectId(resCourseBean.getId());
		approveRecordBean.setObjectUserId(resCourseBean.getCreateUserId());
		approveRecordBean.setObjectCreateTime(resCourseBean.getCreateTime());
		approveRecordBean.setWayType(1);//知识
		approveRecordBean.setSubCompanyId(resCourseBean.getSubCompanyId());
		shareObj(approveRecordBean);
	}
	
	/**
	 * Method name: startWorkFlow <BR>
	 * Description: 启动工作流 <BR>
	 * Remark: <BR>
	 * @param approveRecordBean 传入参数需要赋值有： userId 当前操作者id。CompanyId：当前对象的创建公司ID
	 * ObjectId：当前对象id。 WayType：当前对象类型，1课程2知识3专题。
	 * ObjectUserId当前对象的创建者id。SubCompanyId：当前对象的分公司id
	 *   void<BR>
	 * @throws Exception 
	 */
	@Override
	public  void  startWorkFlow(KnowledgeBean knowledgeBean) throws Exception{
		//
		if(knowledgeBean.getCompanyId()==Constant.PULIAN_COMPANY_ID){
			return;//普联用户不需要走一下流程
		}
		ApproveRecordBean approveRecordBean = new ApproveRecordBean ();
		if(knowledgeBean.getShareStatus()==ApproveStatusConstant.APRROVE_TJ){//
			approveRecordBean.setShareStatus(ApproveStatusConstant.APRROVE_WTJ);//提交集团共享
		}else if(knowledgeBean.getShareStatus()==ApproveStatusConstant.APRROVE_PLTJ){
			approveRecordBean.setShareStatus(ApproveStatusConstant.APRROVE_TG);//提交集团共享
		}else{
			return;
		}
		approveRecordBean.setUserId(knowledgeBean.getUserId());
		approveRecordBean.setCompanyId(knowledgeBean.getCompanyId());
		approveRecordBean.setObjectId(knowledgeBean.getKnowledgeId());
		approveRecordBean.setObjectUserId(knowledgeBean.getCreateUserId());
		approveRecordBean.setObjectCreateTime(knowledgeBean.getCreateTime());
		approveRecordBean.setWayType(2);//知识
		approveRecordBean.setSubCompanyId(knowledgeBean.getSubCompanyId());
		shareObj(approveRecordBean);
	}
	
	@Override
	public int queryShareKLCount(KnowledgeBean knowledgeBean) throws Exception{
		
		return approveManageDaoMapper.queryShareKLCount(knowledgeBean);
	}
	@Override
	public List<KnowledgeBean> queryShareKL(KnowledgeBean knowledgeBean)throws Exception{
		
		return approveManageDaoMapper.queryShareKL(knowledgeBean);
	}
	@Override
	public int queryShareCourseCount(ResCourseBean resCourseBean) throws Exception{
		return approveManageDaoMapper.queryShareCourseCount(resCourseBean);
	}
	@Override
	public List<ResCourseBean> queryShareCourse(ResCourseBean resCourseBean) throws Exception{
		return approveManageDaoMapper.queryShareCourse(resCourseBean);
	}
	/**
	 * Method name: queryShareTopicCount <BR>
	 * Description: 查找共享列表中的专题 <BR>
	 * Remark: <BR>
	 * @param oamTopicBean
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public int queryShareTopicCount(OamTopicBean oamTopicBean) throws Exception{
		return approveManageDaoMapper.queryShareTopicCount(oamTopicBean);
	}
	@Override
	public List<OamTopicBean>   queryShareTopic(OamTopicBean oamTopicBean)throws Exception{
		return approveManageDaoMapper.queryShareTopic(oamTopicBean);
	}
	@Override
	public String getUserCompanyName(Map<String,String> param){
		//分两种情况，当用户是集团公司时
		if(param.get("companyId").equalsIgnoreCase(param.get("subCompanyId"))){

			return approveManageDaoMapper.queryComapnyName(param);
			
		}else{//如果是集团公司下的子公司时，走一种情况
			
			return approveManageDaoMapper.querySubComapnyName(param);
		}
	}
	
	/**
	 * Method name: queryShareRecordCount <BR>
	 * Description: 查询共享记录信息 <BR>
	 * Remark: <BR>
	 * @param approveRecordBean
	 * @return  int<BR>
	 */
	public int queryShareRecordCount(ApproveRecordBean approveRecordBean){
		int wayType = approveRecordBean.getWayType();
		if(wayType==1){
			return approveManageDaoMapper.queryShareRecordCourseCount(approveRecordBean);
			
		}else if(wayType ==2){
			return approveManageDaoMapper.queryShareRecordKLCount(approveRecordBean);
		}else{
			return approveManageDaoMapper.queryShareRecordTopicCount(approveRecordBean);
		}
	}
	public List<ShareRecordBean> queryShareRecord(ApproveRecordBean approveRecordBean){
		int wayType = approveRecordBean.getWayType();
		if(wayType==1){
			return approveManageDaoMapper.queryShareRecordCourseList(approveRecordBean);
		}else if(wayType ==2){
			return approveManageDaoMapper.queryShareRecordKLList(approveRecordBean);
		}else{
			return approveManageDaoMapper.queryShareRecordTopicList(approveRecordBean);
		}
	}
	
}
