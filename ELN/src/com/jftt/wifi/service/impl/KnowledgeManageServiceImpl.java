package com.jftt.wifi.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.ApproveRecordBean;
import com.jftt.wifi.bean.KLCategoryBean;
import com.jftt.wifi.bean.KlTagsBean;
import com.jftt.wifi.bean.KnowledgeBean;
import com.jftt.wifi.bean.ManageNoticeBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.common.ApproveStatusConstant;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.common.IntegralConstant;
import com.jftt.wifi.dao.ApproveManageDaoMapper;
import com.jftt.wifi.dao.KnowledgeManagerDaoMapper;
import com.jftt.wifi.dao.ManageRoleDaoMapper;
import com.jftt.wifi.service.ApproveManageService;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.KnowledgeManageService;
import com.jftt.wifi.service.ManageNoticeService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.ConvertUtil;
import com.jftt.wifi.util.DocConverter;
import com.jftt.wifi.util.PropertyUtil;
@Service("knowledgeManageService")

public class KnowledgeManageServiceImpl implements KnowledgeManageService {
	
	//定会以一个线程池
	private  final static ExecutorService es = Executors.newFixedThreadPool(100);
	
	
	@Resource(name="knowledgeManagerDaoMapper")
	private KnowledgeManagerDaoMapper knowledgeManagerDaoMapper;
	@Autowired
	private ApproveManageService approveManageService;
	@Autowired
	private ManageNoticeService manageNoticeService;
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private IntegralManageService integralManageService;
	@Autowired
	private ApproveManageDaoMapper approveManageDaoMapper;
	
	
	@Override
	public List<KnowledgeBean> queryKnowledgeList(KnowledgeBean knowledgeBean) {
		int flag = 0;
		int companyId = knowledgeBean.getCompanyId();
		String categoryIds = knowledgeBean.getCategoryIds();
		if(categoryIds!=null){
			if(companyId!=1){//当前登入的为一般企业，且当前查询的是“事故案例分析”下的知识，需要加入普联创建的事故案例分析下的资源
				flag = 1;//判断标志
			}
		}
		knowledgeBean.setFlag(flag);
		return knowledgeManagerDaoMapper.queryKnowledgeList(knowledgeBean);
	}
	/**
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeManageService#queryKnowledgeCount(com.jftt.wifi.bean.KnowledgeBean) <BR>
	 * Method name: queryKnowledgeCount <BR>
	 * Description: 查询知识的总数 <BR>
	 * Remark: <BR>
	 * @param knowledgeBean
	 * @return  <BR>
	*/
	public int queryKnowledgeCount(KnowledgeBean knowledgeBean){
		int flag = 0;
		int companyId = knowledgeBean.getCompanyId();
		String categoryIds = knowledgeBean.getCategoryIds();
		if(categoryIds!=null){
			if(companyId!=1){//当前登入的为一般企业，需要加入普联创建的事故案例分析下的资源
				flag = 1;//判断标志
			}
		}
		knowledgeBean.setFlag(flag);
		return knowledgeManagerDaoMapper.queryKnowledgeCount(knowledgeBean);
	}
	
	@Override
	public List<KnowledgeBean> queryAuditKnowledgeList(KnowledgeBean knowledgeBean) {
		return knowledgeManagerDaoMapper.queryAuditKnowledgeList(knowledgeBean);
	}
	/**
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeManageService#queryKnowledgeCount(com.jftt.wifi.bean.KnowledgeBean) <BR>
	 * Method name: queryKnowledgeCount <BR>
	 * Description: 查询知识的总数 <BR>
	 * Remark: <BR>
	 * @param knowledgeBean
	 * @return  <BR>
	*/
	public int queryAuditKnowledgeCount(KnowledgeBean knowledgeBean){
		return knowledgeManagerDaoMapper.queryAuditKnowledgeCount(knowledgeBean);
	}
	@Override
	public KnowledgeBean queryKnowledgeDetail(int knowledgeId) {
		
		return knowledgeManagerDaoMapper.queryKnowledgeDetail(knowledgeId);
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteKnowledge(Map map) {
		// TODO Auto-generated method stub
		knowledgeManagerDaoMapper.deleteKnowledge(map);
	}

	@Override
	public String modifyKnowledge(int knowledgeId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void updateOneKnowledgeCategroy(KnowledgeBean knowledgeBean) throws Exception {
		int categoryId = knowledgeBean.getCategoryId();
		//先获取到当前需要更新的类别是属于事故还是知识
		int isAccident = knowledgeManagerDaoMapper.getByCategoryId(categoryId+"").getIsAccident();
		knowledgeBean.setIsAccident(isAccident);
		knowledgeManagerDaoMapper.updateOneKnowledgeCategroy(knowledgeBean);
	}
	
	@Override
	public int queryKLCategoryCount(KLCategoryBean klCategoryBean)throws Exception{
		
		return knowledgeManagerDaoMapper.queryKLCategoryCount(klCategoryBean);
	}
	@Override
	public void deleteKLCategory(int categoryId) {
		
		knowledgeManagerDaoMapper.deleteKLCategory(categoryId);
	}
	/**
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeManageService#isAllowDeleteCategory(int) <BR>
	 * Method name: isAllowDeleteCategory <BR>
	 * Description: 是否允许删除分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  <BR>
	*/
	@Override
	public boolean isAllowDeleteCategory(int categoryId)throws Exception{
		
		//此处需要考虑两点
		//1、是否存在分类子集，存在就不允许删除
		int size =0;
		size = knowledgeManagerDaoMapper.queryChildren(categoryId);
		if(size>0){
			//有子集，不允许删除
			return false;
		}
		//2、判断该知识分类是否有知识进行关联
		size = knowledgeManagerDaoMapper.queryKLCountById(categoryId);
		if(size>0){
			
			return false;
		}
		return true;
	}

	@Override
	public void updateKnowledgeCategory(Map<String,Object> param) throws Exception {
		knowledgeManagerDaoMapper.updateKnowledgeCategory(param);
	}

	

	@Override
	public void saveKLCategory(KLCategoryBean kLCategoryBean) {
		int parendId = kLCategoryBean.getParentId();
		if(parendId==-2){
			kLCategoryBean.setIsAccident(1);
		}else{
			KLCategoryBean kbe = knowledgeManagerDaoMapper.detailCategory(parendId);
			if(kbe==null){
				kLCategoryBean.setIsAccident(0);
			}else{
				Integer isAccident = kbe.getIsAccident();
				kLCategoryBean.setIsAccident(isAccident);
			}
			
		}
		knowledgeManagerDaoMapper.saveKLCategory(kLCategoryBean);
		
	}
	
	@Override
	public  void updateKLCategoryName(KLCategoryBean klCategoryBean) throws Exception{
		
		knowledgeManagerDaoMapper.updateKLCategoryName(klCategoryBean);
	}


	@Override
	public List<KLCategoryBean> getKLCategoryTree(KLCategoryBean klCategoryBean) {
		int flag = 0;//用于sql中并入普联数据
		int companyId = klCategoryBean.getCompanyId();
			if(companyId!=1){//当前登入的为一般企业,需要加入普联创建的事故案例分析分类
				flag = 1;//判断标志
			}
		klCategoryBean.setFlag(flag);
		List<KLCategoryBean> kbl = knowledgeManagerDaoMapper.getKLCategoryTree(klCategoryBean);
		Integer roleType = klCategoryBean.getRoleType();
		if(roleType==null || roleType==1){
			//加入 "事故案例分析"固定节点
			KLCategoryBean kb = new KLCategoryBean();
			kb.setCategoryId(-2);
			kb.setParentId(0);
			kb.setCategoryName("事故案例分析");
			kb.setIsAccident(1);
			kbl.add(kb);
		}
		return kbl;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeManageService#detailCategory(int) <BR>
	 * Method name: detailCategory <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return  <BR>
	*/
	@Override
	public KLCategoryBean detailCategory(int categoryId) throws Exception{
		
		return knowledgeManagerDaoMapper.detailCategory(categoryId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeManageService#saveKnowledge(com.jftt.wifi.bean.KnowledgeBean) <BR>
	 * Method name: saveKnowledge <BR>
	 * Description: 保存知识 <BR>
	 * Remark: <BR>
	 * @param knowledgeBean
	 * @throws Exception  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveKnowledge(KnowledgeBean knowledgeBean) throws Exception {
		
		int cateId = knowledgeBean.getCategoryId();
		KLCategoryBean kbe = null;
		if(cateId==-2){
			kbe = new KLCategoryBean();
			kbe.setCategoryId(-2);
			kbe.setIsAccident(1);
			kbe.setParentId(0);
			kbe.setCategoryName("事故案例分析");
		}else{
			kbe = knowledgeManagerDaoMapper.detailCategory(cateId);
		}
		Integer isAccident = kbe.getIsAccident();
		knowledgeBean.setIsAccident(isAccident);
		knowledgeBean.setShareStatus(getShareStatus(knowledgeBean.getCompanyId(), knowledgeBean.getSubCompanyId(), knowledgeBean.getShareStatus()));
		
		 knowledgeManagerDaoMapper.saveKnowledge(knowledgeBean);
		
		//由于此处标签需要单独维护，所有要单独拿出出来处理。
		int knowledgeId =knowledgeBean.getKnowledgeId();
		String tags = knowledgeBean.getTags();
		dealKnowledgeTags(knowledgeId,tags);//处理知识分类信息
		
		/*//判断并启动工作流
		if(knowledgeBean.getShareStatus()==2){
			ApproveRecordBean approveRecordBean = new ApproveRecordBean ();
			approveRecordBean.setUserId(knowledgeBean.getUserId());
			approveRecordBean.setCompanyId(knowledgeBean.getCompanyId());
			approveRecordBean.setShareStatus(1);//提交集团共享
			approveRecordBean.setObjectId(knowledgeId);
			approveRecordBean.setObjectUserId(knowledgeBean.getUserId());
			approveRecordBean.setWayType(2);//知识
			approveRecordBean.setSubCompanyId(knowledgeBean.getSubCompanyId());
			approveManageService.shareObj(approveRecordBean);
		}*/
	}
	
	private int getShareStatus(int companyId,int subCompanyId,int shareStatus){
		int status =1;
		try {
			if(companyId==Constant.PULIAN_COMPANY_ID){
				status=ApproveStatusConstant.APRROVE_PLTG;//普连管理员.直接给7.
				/*if(companyId==subCompanyId ){//普连管理员
				}else if(companyId!=subCompanyId ){
					status=shareStatus;
				}*/
			}else{
				if(companyId==subCompanyId ){//集团管理员
					status=shareStatus;
				}else if(companyId!=subCompanyId ){
					if(shareStatus==4){
						status=1;
					}else if(shareStatus==5){
						status=2;//调用提交审核的方法。进入工作流
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	/**
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeManageService#uploadFile(org.springframework.web.multipart.MultipartFile) <BR>
	 * Method name: uploadFile <BR>
	 * Description: 实现上传文件的方法<BR>
	 * Remark: <BR>
	 * @param file
	 * @return  <BR>
	*/
	public String uploadFile(MultipartFile file,String commonPath){
		
		String extendUrl;
		String genName;
		JSONObject param = new JSONObject();
		try {
			//保存在指定文件路径里
			String path = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/
			//获取拼接地址
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			extendUrl = commonPath+"/"+df.format(new Date());
			path +=extendUrl;
			
			
			String fileName = file.getOriginalFilename();	
			String suffixName =fileName.substring(fileName.lastIndexOf(".")+1); 
			
			genName = UUID.randomUUID()+"."+suffixName;
			
			//判断是否存在目录，如果不存在，则创建目录
			if(!new File(path).exists()){
				new File(path).mkdirs();
			}
			
			File dest = new java.io.File(path,genName);
			file.transferTo(dest);
			
			converterFile(extendUrl+"/"+genName);
			
			param.put("recordCode", 1);
			param.put("path", PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH")+"/"+extendUrl+"/"+genName.replace(suffixName, "swf"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			param.put("recordCode", -1);
			return param.toString();
		}
		
		return param.toString();
	}
	
	
	/**
	 * Method name: dealKnowledgeTags <BR>
	 * Description: 由于知识的标签需要用作其他，此处单独处理进行保存 <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @param tags  void<BR>
	 */
	private void dealKnowledgeTags(int knowledgeId,String tags){
		
		//1、首先将此标签和知识的关联关系清除。
		knowledgeManagerDaoMapper.deleteKLTags(knowledgeId);
		
		//循环标签
		String[] tagArr = tags.split(",");
		for(String tag:tagArr){
			
			if(tag.trim().equals("")){
				continue;
			}else{
				//判断是否存在此标签。
				Integer tagId= knowledgeManagerDaoMapper.queryTagId(tag.trim());  
				if(null == tagId){//没有保存，则重新保存一回
					KlTagsBean tagBean = new KlTagsBean();
					tagBean.setTagName(tag.trim());
					knowledgeManagerDaoMapper.insertTags(tagBean); 
					tagId = tagBean.getId();
				}
				//将关联关系入表
				knowledgeManagerDaoMapper.insertKLTag(knowledgeId,tagId); 
			}
		}
	}
	
	
	@Override
	public void updateKnowledge(KnowledgeBean knowledgeBean) {
		// TODO Auto-generated method stub
		knowledgeManagerDaoMapper.updateKnowledge(knowledgeBean);
	}
	/**
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeManageService#publishKL(int) <BR>
	 * Method name: publishKL <BR>
	 * Description: 发布知识 <BR>
	 * Remark: <BR>
	 * @param knowledgeId
	 * @throws Exception  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void publishKL(Map param) throws Exception{
		//1、知识进行发布
		knowledgeManagerDaoMapper.publishKL(param);
		
		
		//2 知识发布后，此时是直接展示在学员我的知识中。对于已经发布的知识，如果提交共享
		//此处启动工作流
		KnowledgeBean  klBean = knowledgeManagerDaoMapper.queryKnowledgeDetail(Integer.valueOf(String.valueOf(param.get("knowledgeId"))));
		klBean.setUserId(Integer.valueOf(String.valueOf(param.get("userId"))));
		approveManageService.startWorkFlow(klBean);
		
		//add 2015.9.17  确认后，发布直接应用，不需要审核，是学员公开才需要审核
		//2、知识发布完成后，需要进行发站内信
		/*KnowledgeBean  klBean = knowledgeManagerDaoMapper.queryKnowledgeDetail(Integer.valueOf(String.valueOf(param.get("knowledgeId"))));
		ManageUserBean userBean = manageUserService.findUserByIdCondition(String.valueOf(klBean.getCreateUserId()));//拿到用户对象
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("subCompanyId", klBean.getSubCompanyId());
		map.put("companyId", klBean.getCompanyId());
		map.put("url", "knowledge/auditList.action");
		
		List<Integer> ids = knowledgeManagerDaoMapper.queryUserIdList(map);
		SimpleDateFormat formart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for (int id:ids){
			
			ManageUserBean manageUserBean = manageUserService.findUserByIdCondition(String.valueOf(id));//拿到用户对象
			if(manageUserBean!=null){
				//组装数据
				ManageNoticeBean bean = new ManageNoticeBean();
				
				bean.setTitle("知识库上传审批提醒");
				//组装消息主体
				StringBuffer strBuff = new StringBuffer();
				strBuff.append("尊敬的"+manageUserBean.getName()+"：</em><br/>");
				strBuff.append("&nbsp;&nbsp;&nbsp;&nbsp;有新上传的知识需要您审批，请尽快审批处理，详情如下：</em><br/>");
				strBuff.append("&nbsp;&nbsp;&nbsp;&nbsp;知识名称："+klBean.getKnowledgeName()+"</em><br/>");
				strBuff.append("&nbsp;&nbsp;&nbsp;&nbsp;知识库分类："+klBean.getCategoryName()+"</em><br/>");
				strBuff.append("&nbsp;&nbsp;&nbsp;&nbsp;上传部门："+userBean.getSubCompanyName()+(userBean.getDeptName()==null?"":("."+userBean.getDeptName()))+"</em><br/>");
				strBuff.append("&nbsp;&nbsp;&nbsp;&nbsp;上传者："+userBean.getName()+"</em><br/>");
				strBuff.append("&nbsp;&nbsp;&nbsp;&nbsp;上传时间："+formart.format(klBean.getCreateTime())+"</em><br/>");
				bean.setContent(strBuff.toString());
				
				bean.setSendUserId(Integer.valueOf(String.valueOf(param.get("userId"))));//param.put("userId",getUserId(request));
				bean.setRecUserId(id);
				bean.setIsSystem(2);
				
				manageNoticeService.insertNotice(bean);
			}
		}*/
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeManageService#auditKnowledge(com.jftt.wifi.bean.KnowledgeBean) <BR>
	 * Method name: auditKnowledge <BR>
	 * Description: 审核知识<BR>
	 * Remark: <BR>
	 * @param knowledgeBean  <BR>
	 * @throws Exception 
	*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void auditKnowledge(KnowledgeBean knowledgeBean) throws Exception {

		if(knowledgeBean.getStatus()==3){//1：未审批；2：通过；3：拒绝
			//拒绝不需要做任何操作。
		}else if(knowledgeBean.getStatus()==2){//审核通过，获得积分     // 测试提出审核为管理端操作，不应该作为学员积分获取的途径，故去除 .【2016/1/25】
			/*KnowledgeBean  klBean = knowledgeManagerDaoMapper.queryKnowledgeDetail(knowledgeBean.getKnowledgeId());
			integralManageService.handleIntegral(klBean.getCreateUserId(),7007);//完成审核	审核管理	7007	*/
			//审核通过，设置为发布状态
			knowledgeBean.setIsPublish(2);
		}
		knowledgeManagerDaoMapper.auditKnowledge(knowledgeBean);
	}
	
	/**
	 * Method name: converterFile <BR>
	 * Description: 多线程实现文件的转换 <BR>
	 * Remark: <BR>
	 * @param filePath  void<BR>
	 */
	private void  converterFile(final String filePath){
			es.execute(new Runnable() {
			@Override
			public void run() {
				try {
					//转换->swf
					/*DocConverter d = new DocConverter(filePath);   
					d.conver();*/
					ConvertUtil.convertOfficeToSwf(filePath);
					
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
	/**
	 *  推荐、取消推荐
	 * @Override
	 * @see com.jftt.wifi.service.KnowledgeManageService#doRecommend(java.lang.String, java.lang.String) <BR>
	 * Method name: doRecommend <BR>
	 * Description:  <BR>
	 * Remark: <BR>
	 * @param id
	 * @param type
	 * @throws Exception  <BR>
	 */
	@Override
	public void doRecommend(String id, String type,String createUserId) throws Exception {
		if("1".equals(type)){
			if(createUserId !=null && !createUserId.isEmpty()){
				integralManageService.handleIntegral(Integer.parseInt(createUserId),IntegralConstant.Num_kl_ByRecommend);// 知识被管理员推荐
			}
		}
		knowledgeManagerDaoMapper.doRecommend(id,type);
	}
	
}
