/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CertificateAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月7日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.CertificateBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageIndustryCategoryBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.vo.cjylSearchVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.CertificateService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.PropertyUtil;
import com.jftt.wifi.util.XlsParserUtil;


/**
 * class name:CertificateAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月7日
 * @author JFTT)Lu Yunlong
 */
@Controller
@RequestMapping(value="certificate")
public class CertificateAction {
	
	@Resource(name="certificateService")
	private CertificateService certificateService;
	
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	
	/**
	 * Method name: toCertificateListPage <BR>
	 * Description: 证书列表页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCertificateListPage")
	public String toCertificateListPage(HttpServletRequest request){
		return "cer/certificateList";
	}
	
	/**
	 * Method name: selectCertificateList <BR>
	 * Description: 查询证书 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param name
	 * @param page
	 * @param pageSize
	 * @return  Object<BR>
	 */
	@RequestMapping("selectCertificateList")
	@ResponseBody
	public Object selectCertificateList(HttpServletRequest request,String name, int page, int pageSize, String receiveName){
		MMGridPageVoBean<CertificateBean> re = new MMGridPageVoBean<CertificateBean>();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			HttpSession session = request.getSession();
			ManageUserBean user = manageUserService.findUserById((String)session.getAttribute(Constant.SESSION_USERID_LONG));
			param.put("companyId", user.getCompanyId());
			param.put("subCompanyId", user.getSubCompanyId());
			param.put("name", name);
			param.put("fromNum", (page - 1) * pageSize);
			param.put("pageSize", pageSize);
			List<CertificateBean> result = certificateService.selectCertificate(param);
			String[] categoryIds = request.getParameterValues("categoryIds[]");
			List<String> categoryIdList = null;
			if(categoryIds != null){
				categoryIdList = Arrays.asList(categoryIds);
			}
			for(int i = result.size() - 1;i >= 0; i--){
				CertificateBean bean = result.get(i);
				ManageUserBean user_ = manageUserService.findUserByIdCondition(bean.getReceiveUserId().toString());
				if(user_== null){
					result.remove(i);
					continue;
				}
				if(categoryIdList != null){
					if(!categoryIdList.contains(user_.getDeptId().toString())){
						result.remove(i);
						continue;
					}
				}
				if(receiveName != null && !"".equals(receiveName)){
					if(!user_.getName().contains(receiveName)){
						result.remove(i);
						continue;
					}
				}
				bean.setReceiveUserName(user_.getName());
				bean.setDeptName(user_.getDeptName());
				bean.setPostName(user_.getPostName());
			}
			//int size = certificateService.selectCertificateCount(param);
			re.setTotal(result.size());
			int fromIndex = (page-1)*pageSize;
			int toIndex = fromIndex + pageSize;
			if(fromIndex >= result.size()){
				fromIndex = result.size();
			}
			if(toIndex >= result.size()){
				toIndex = result.size();
			}
			if(result.size() > 0){
				result = result.subList(fromIndex, toIndex);
			}
			re.setRows(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	/**
	 * Method name: selectCertificateList <BR>
	 * Description: 删除证书 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("deleteCertificate")
	@ResponseBody
	public Object selectCertificateList(HttpServletRequest request){
		try {
			String[] ids = request.getParameterValues("ids[]");
			for(String id : ids){
				certificateService.deleteCertificate(Integer.parseInt(id));
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
		e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: toInsertCerPage <BR>
	 * Description: 新增证书页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toInsertCerPage")
	public String toInsertCerPage(HttpServletRequest request){
		return "cer/certificateAdd";
	}
	
	@RequestMapping(value="chooseStu")
	public String chooseStu(HttpServletRequest request){
		return "cer/chooseStu";
	}
	
	@RequestMapping(value="chooseMultiStu")
	public String chooseMultiStu(HttpServletRequest request){
		return "cer/chooseMultiStu";
	}
	
	/**
	 * Method name: insertCertificate <BR>
	 * Description: 新增证书 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("insertCertificate")
	@ResponseBody
	public Object insertCertificate(HttpServletRequest request, CertificateBean cer){
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = manageUserService.findUserById((String)session.getAttribute(Constant.SESSION_USERID_LONG));
			String[] ids = request.getParameterValues("receiveUserIds[]");
			for(String id : ids){
				cer.setReceiveUserId(Integer.parseInt(id));
				cer.setCompanyId(user.getCompanyId());
				cer.setSubCompanyId(user.getSubCompanyId());
				certificateService.insertCertificate(cer);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
		e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	@RequestMapping(value="toUpdateCerPage")
	public String toUpdateCerPage(HttpServletRequest request, String id) throws Exception{
		CertificateBean cer = certificateService.selectCertificateById(Integer.parseInt(id));
		ManageUserBean user = manageUserService.findUserById(cer.getReceiveUserId().toString());
		cer.setReceiveUserName(user.getName());
		request.setAttribute("cer", JsonUtil.getJson4JavaObject(cer));
		return "cer/certificateUpdate";
	}
	
	/**
	 * Method name: updateCertificate <BR>
	 * Description: 修改证书 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param cer
	 * @return  Object<BR>
	 */
	@RequestMapping("updateCertificate")
	@ResponseBody
	public Object updateCertificate(HttpServletRequest request, CertificateBean cer){
		try {
			certificateService.updateCertificate(cer);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
		e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	
	/**
	 * Method name: importFile <BR>
	 * Description: 批量导入页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="importFile")
	public String importFile(HttpServletRequest request){
		return "cer/importFile";
	}
	
	 private  String getUUID() {  
	        UUID uuid = UUID.randomUUID();  
	        String str = uuid.toString();  
	        return str;
	    } 
	 
	 /**
		 * Method name: 解析文件数据
		 * Description: importXlsFileData <BR>
		 * Remark: <BR>  void<BR>
		 */
		@RequestMapping(value="importXlsFileData")
		@ResponseBody
		public Object  importXlsFileData(HttpServletRequest request,@RequestParam(value="file", required = false)MultipartFile imgFile){
			
			boolean flag = true;
			
			HttpSession session = request.getSession();
			//1、获取文件储存的地址。
			String pageFileName= imgFile.getOriginalFilename();//.getName() ;
			String filetype = pageFileName.split("\\.")[1];
			String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/
			
			//获取拼接地址
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String extendUrl = "certificate/template/"+df.format(new Date());
			
			JSONObject reObj = new JSONObject();
			try {
				File file = new File(saveUrl+extendUrl);
				if(!file.exists()){
					file.mkdirs();
				}
				//将上传的文件写到new出来的文件中
				String 	filePath =saveUrl +extendUrl+"/"+getUUID()+pageFileName;
				FileUtil.SaveFileFromInputStream(imgFile.getInputStream(), filePath);
				
				int maxNum = XlsParserUtil.getXLSNum(filePath,filetype);
				if(maxNum>maxNum){
					reObj.put("errCode", 10);
				}
				else{
				
				JSONArray array = new JSONArray();
			
					//根据文件类型，去解析
					 if("xls".equals(filetype)){
						array = XlsParserUtil.importXlsParser(filePath,"utf-8",1,1,0);
					}else if("xlsx".equals(filetype)){
						array = XlsParserUtil.importXlsxParser(filePath,"utf-8",1,1,0);
					}else{
						
					}
					 ManageUserBean user = manageUserService.findUserById((String)session.getAttribute(Constant.SESSION_USERID_LONG));
					 //此处缺少校验和异常处理
					 StringBuffer errCode = new StringBuffer();
					 for(int i=1;i<array.size();i++){
						 JSONArray sArr = array.getJSONArray(i);
						 if(!sArr.isEmpty()){
							 if(sArr.size() == 0){
								 continue; 
							 }
							 CertificateBean cer = new CertificateBean();
							 if(sArr.getString(0) == null || "".equals(sArr.getString(0))){
								 errCode.append("第"+i+"行证书名称不能为空；");
								 continue;
							 }
							 cer.setName(sArr.getString(0));
							 //获得人员
							 Map<String, Object> param = new HashMap<String, Object>();
							 param.put("name", sArr.getString(1));
							 if(user != null){
								 param.put("companyId", user.getCompanyId());
							 }
							 List<ManageUserBean> userList = manageUserService.findUserByListCondition(param);
							 if(userList != null && userList.size() > 0){
								 for(ManageUserBean receiver : userList){
									 if(sArr.getString(1).equals(receiver.getName())){
										 cer.setReceiveUserId(Integer.parseInt(receiver.getId()));
									 }
								 }
							 }
							 if(cer.getReceiveUserId() == null || "".equals(cer.getReceiveUserId())){
								 errCode.append("第"+i+"行用户不存在；");
								 continue;
							 }
							 cer.setCompanyId(user.getCompanyId());
							 cer.setSubCompanyId(user.getSubCompanyId());
							 cer.setReceiveUserName(sArr.getString(1));
							 cer.setTheoryScore(("".equals(sArr.getString(2))?null:Long.parseLong(sArr.getString(2))));
							 cer.setOperateScore(("".equals(sArr.getString(3))?null:Long.parseLong(sArr.getString(3))));
							 cer.setSendDate(sArr.getString(4));
							 if(sArr.getString(4) == null || "".equals(sArr.getString(4))){
								 errCode.append("第"+i+"行发证日期不能为空；");
								 continue;
							 }
							 if(sArr.getString(5) != null && !"".equals(sArr.getString(5))){
								 cer.setFirstCheckDate(sArr.getString(5));
							 }
							 if(sArr.getString(6) != null && !"".equals(sArr.getString(6))){
								 cer.setSecondCheckDate(sArr.getString(6));
							 }
							
							 cer.setCheckStatus(sArr.getString(7).equals("已复审")?1:2);
							 cer.setTrainStyle(sArr.getString(8));
							 cer.setTrainAgency(sArr.getString(9));
							 if("国家一级".equals(sArr.getString(10))){
								 cer.setTrainAgencyLevel(1);
							 }else if("国家二级".equals(sArr.getString(10))){
								 cer.setTrainAgencyLevel(2);
							 }else if("国家三级".equals(sArr.getString(10))){
								 cer.setTrainAgencyLevel(3);
							 }else if("国家四级".equals(sArr.getString(10))){
								 cer.setTrainAgencyLevel(4);
							 }else if("其他".equals(sArr.getString(10))){
								 cer.setTrainAgencyLevel(5);
							 }
							 if(sArr.size()>11){
								 if(sArr.getString(11) != null && !"".equals(sArr.getString(11))){
									 cer.setChangeDate(sArr.getString(11));
								 }
							 }
							 if(sArr.size()>12){
								 cer.setDescr(sArr.getString(12));
							 }
							 certificateService.insertCertificate(cer);
							 flag = false;
						 }
					 }
					 if(flag && errCode.toString().isEmpty()){
						 reObj.put("errCode", "Excel中没有数据。");
						 return reObj;
					 }
					 if("".equals(errCode.toString())){
						 reObj.put("errCode", "上传成功");
					 }else{
						 reObj.put("errCode", errCode.toString());
					 }
				}
			}catch(Exception e){
				e.printStackTrace();
			//	continue;
//				System.out.println(e);
				reObj.put("errCode", "上传失败");
//				return reObj;
			}
			
			return reObj;
		}
		
		/**
		 * Method name: exportExcel <BR>
		 * Description: 导出证书Excel <BR>
		 * Remark: <BR>
		 * @param request
		 * @param response
		 * @param paramVo  void<BR>
		 */
		@RequestMapping("exportExcel")
		public void exportExcel(HttpServletRequest request,HttpServletResponse response,String name, String receiveName) {
			try {
				Map<String, Object> param = new HashMap<String, Object>();
				HttpSession session = request.getSession();
				ManageUserBean user = manageUserService.findUserById((String)session.getAttribute(Constant.SESSION_USERID_LONG));
				param.put("companyId", user.getCompanyId());
				param.put("subCompanyId", user.getSubCompanyId());
				param.put("name", name);
				List<CertificateBean> result = certificateService.selectCertificate(param);
				String[] categoryIds = request.getParameterValues("categoryIds[]");
				List<String> categoryIdList = null;
				if(categoryIds != null){
					categoryIdList = Arrays.asList(categoryIds);
				}
				for(int i = result.size() - 1;i >= 0; i--){
					CertificateBean bean = result.get(i);
					ManageUserBean user_ = manageUserService.findUserByIdCondition(bean.getReceiveUserId().toString());
					if(user_ == null){
						result.remove(i);
						continue;
					}
					if(categoryIdList != null){
						if(!categoryIdList.contains(user_.getDeptId().toString())){
							result.remove(i);
							continue;
						}
					}
					if(receiveName != null && !"".equals(receiveName)){
						if(!user_.getName().contains(receiveName)){
							result.remove(i);
							continue;
						}
					}
					bean.setReceiveUserName(user_.getName());
					bean.setDeptName(user_.getDeptName());
					bean.setPostName(user_.getPostName());
				}
				HSSFWorkbook workbook = certificateService.exportExcel(result);
				
				response.addHeader("Content-Type","application/x-msdownload;charset=utf-8");
				response.addHeader("Content-Disposition", "attachment;filename="+ new String("证书.xls".getBytes(),"iso8859-1"));
				//得到向客户端输出二进制数据的对象 
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				workbook.write(toClient);
				toClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
