/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageCompanyAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月5日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageIndustryCategoryBean;
import com.jftt.wifi.bean.ManageNoticeBean;
import com.jftt.wifi.bean.ManagePageBean;
import com.jftt.wifi.bean.ManageRolePageBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.TeacherBean;
import com.jftt.wifi.bean.vo.ManageCompanyVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManageIndustryCategoryService;
import com.jftt.wifi.service.ManagePageService;
import com.jftt.wifi.service.ManageRoleService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.PropertyUtil;
import com.jftt.wifi.util.SendMessageUtil;
import com.jftt.wifi.util.XlsParserUtil;

/**
 * class name:ManageCompanyAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月5日
 * @author JFTT)Lu Yunlong
 */
@Controller
@RequestMapping(value="manageCompany")
public class ManageCompanyAction {
	
	@Resource(name="manageCompanyService")
	private ManageCompanyService manageCompanyService;
	
	@Resource(name="managePageService")
	private ManagePageService managePageService;
	
	@Resource(name="manageRoleService")
	private ManageRoleService manageRoleService;
	
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	
	@Resource(name="manageDepartmentService")
	private ManageDepartmentService manageDepartmentService;
	
	@Resource(name="manageIndustryCategoryService")
	private ManageIndustryCategoryService manageIndustryCategoryService;
	
	/**
	 * Method name: toCompanyListPage <BR>
	 * Description: 跳转到企业管理页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCompanyListPage")
	public String toCompanyListPage(HttpServletRequest request){
		return "manage/companyList";
	}
	
	/**
	 * Method name: selectManageCompanyList <BR>
	 * Description: 根据条件查询企业 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectManageCompanyList")
	@ResponseBody
	public Object selectManageCompanyList(HttpServletRequest request, ManageCompanyVo vo){

		MMGridPageVoBean<ManageCompanyBean> re = new MMGridPageVoBean<ManageCompanyBean>();
		try {
			vo.setFromNum((vo.getPage()-1)*vo.getPageSize());
			int size = manageCompanyService.selectCompanyCount(vo);
			List<ManageCompanyBean> rows = manageCompanyService.selectCompanyList(vo);
			re.setTotal(size);
			re.setRows(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	/**
	 * Method name: toCompanyCapacityPage <BR>
	 * Description: 企业容量管理 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCompanyCapacityPage")
	public String toCompanyCapacityPage(HttpServletRequest request){
		return "manage/companyCapacity";
	}
	
	/**
	 * Method name: selectManageCompanyCapacityList <BR>
	 * Description: 租户企业容量列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectManageCompanyCapacityList")
	@ResponseBody
	public Object selectManageCompanyCapacityList(HttpServletRequest request, ManageCompanyVo vo){

		MMGridPageVoBean<ManageCompanyBean> re = new MMGridPageVoBean<ManageCompanyBean>();
		try {
			vo.setFromNum((vo.getPage()-1)*vo.getPageSize());
			vo.setStatus(1);
			int size = manageCompanyService.selectCompanyCount(vo);
			List<ManageCompanyBean> rows = manageCompanyService.selectManageCompanyCapacityList(vo);
			re.setTotal(size);
			re.setRows(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	
	/**
	 * Method name: exportExcel <BR>
	 * Description: 导出企业容量excel <BR>
	 * Remark: <BR>
	 * @param request
	 * @param response
	 * @param vo  void<BR>
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response, ManageCompanyVo vo) {
		try {
			vo.setStatus(1);
			List<ManageCompanyBean> result = manageCompanyService.selectManageCompanyCapacityList(vo);
			HSSFWorkbook workbook = manageCompanyService.exportExcel(result);
			
			response.addHeader("Content-Type","application/x-msdownload;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename="+ new String("企业容量管理.xls".getBytes(),"iso8859-1"));
			//得到向客户端输出二进制数据的对象 
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			workbook.write(toClient);
			toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: freezeAndUnfreezeManageCompany <BR>
	 * Description: 冻结该公司 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping(value="freezeAndUnfreezeManageCompany")
	@ResponseBody
	public Object freezeAndUnfreezeManageCompany(HttpServletRequest request, String status){
		try {
			String[] ids = request.getParameterValues("ids[]");
			for(String id : ids){
				manageCompanyService.freezeAndUnfreezeManageCompany(Integer.parseInt(id), status);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: addCapacity <BR>
	 * Description: 给企业扩容 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @param addCapacity
	 * @return  Object<BR>
	 */
	@RequestMapping(value="addCapacity")
	@ResponseBody
	public Object addCapacity(HttpServletRequest request, String id, String addCapacity){
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", id);
			param.put("addCapacity", addCapacity);
			manageCompanyService.addCapacity(param);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: deleteManageCompany <BR>
	 * Description: 删除公司 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="deleteManageCompany")
	@ResponseBody
	public Object deleteManageCompany(HttpServletRequest request){
		try{
			String[] ids = request.getParameterValues("ids");
			for(String id : ids){
				manageCompanyService.deleteCompany(Integer.parseInt(id));
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: toCompanyDetailPage <BR>
	 * Description: 跳转公司详细页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCompanyDetailPage")
	public String toCompanyDetailPage(HttpServletRequest request, String id){
		try {
			ManageCompanyBean bean = manageCompanyService.selectCompanyById(Integer.parseInt(id));
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("companyId", bean.getId());
			request.setAttribute("usedAccountCount", manageUserService.findUserByCondition(param).size());
			request.setAttribute("company", JsonUtil.getJson4JavaObject(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manage/companyDetail";
	}
	
	/**
	 * Method name: toCompanyDetailPage <BR>
	 * Description: 跳转公司审批页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCheckCompanyPage")
	public String toCheckCompanyPage(HttpServletRequest request, String id){
		try {
			ManageCompanyBean bean = manageCompanyService.selectCompanyById(Integer.parseInt(id));
			request.setAttribute("company", JsonUtil.getJson4JavaObject(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manage/companyCheck";
	}
	
	
	/**
	 * Method name: toUpdateCompanyPage <BR>
	 * Description: 跳转修改企业基本信息页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toUpdateCompanyBaseInfoPage")
	public String toUpdateCompanyBaseInfoPage(HttpServletRequest request, String id){
		try {
			ManageCompanyBean bean = manageCompanyService.selectCompanyById(Integer.parseInt(id));
			request.setAttribute("company", JsonUtil.getJson4JavaObject(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manage/companyBaseInfoUpdate";
	}
	
	/**
	 * Method name: toUpdateCompanyPage <BR>
	 * Description: 修改企业信息页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toUpdateCompanyPage")
	public String toUpdateCompanyBaseInfoPage(HttpServletRequest request){
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
			ManageCompanyBean bean = manageCompanyService.selectCompanyById(user.getCompanyId());
			request.setAttribute("company", JsonUtil.getJson4JavaObject(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manage/companyUpdate";
	}
	
	
	/**
	 * Method name: updateCompany <BR>
	 * Description: 修改企业基本信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  String<BR>
	 */
	@RequestMapping(value="updateCompanyBaseInfo")
	@ResponseBody
	public String updateCompanyBaseInfo(HttpServletRequest request, ManageCompanyBean bean){
		try{
			manageCompanyService.updateCompanyBaseInfo(bean);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: toUpdateCompanyResPage <BR>
	 * Description: 跳转到企业权限管理页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toUpdateCompanyResPage")
	public String toUpdateCompanyResPage(HttpServletRequest request, String id){
		request.setAttribute("id", id);
		return "manage/companyResUpdate";
	}
	
	@RequestMapping(value="selectCompanyResInfo")
	@ResponseBody
	public Object selectCompanyResInfo(HttpServletRequest request, String id){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			ManageCompanyBean bean = manageCompanyService.selectCompanyById(Integer.parseInt(id));
			result.put("company", bean);
			if(bean.getInitUserId() == null){
				result.put("pageList", null);
			}else{
				//该企业用户的权限
				List<ManagePageBean> pageList = managePageService.getManagePageByUser(bean.getInitUserId());
				result.put("pageList", pageList);
			}
			//根据企业id获取所有有权限。租户企业权限排除掉普联的权限
			Map<String, Object> param = new HashMap<String, Object>();
			if(!id.equals(String.valueOf(Constant.PULIAN_COMPANY_ID))){
				param.put("isPulian", 2);
			}
			List<ManagePageBean> allPageList = managePageService.getAllManagePage(param);
			result.put("allPageList", allPageList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	@RequestMapping(value="insertCompanyResInfo")
	@ResponseBody
	public String insertCompanyResInfo(HttpServletRequest request, ManageCompanyBean bean){
		try{
			ManageCompanyBean company = manageCompanyService.selectCompanyById(bean.getId());
			company.setInitUsername(bean.getInitUsername());
			company.setInitPassword(bean.getInitPassword());
			company.setAccountCount(bean.getAccountCount());
			company.setTotalCapacity(bean.getTotalCapacity());
			company.setMaxConcurrent(bean.getMaxConcurrent());
			company.setEndTime(bean.getEndTime());
			String[] pageIds = request.getParameterValues("pageIds[]");
			//根据新增企业新建用户
			manageCompanyService.insertUserByCompany(company, pageIds);
			//更新企业权限信息
			//manageCompanyService.updateCompanyResInfo(company);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: updateCompanyResInfo <BR>
	 * Description: 修改企业权限信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  String<BR>
	 */
	@RequestMapping(value="updateCompanyResInfo")
	@ResponseBody
	public String updateCompanyResInfo(HttpServletRequest request, ManageCompanyBean bean){
		try{
			ManageCompanyBean company = manageCompanyService.selectCompanyById(bean.getId());
			company.setInitUsername(bean.getInitUsername());
			company.setInitPassword(bean.getInitPassword());
			company.setAccountCount(bean.getAccountCount());
			company.setTotalCapacity(bean.getTotalCapacity());
			company.setEndTime(bean.getEndTime());
			company.setMaxConcurrent(bean.getMaxConcurrent());
			String[] pageIds = request.getParameterValues("pageIds[]");
			//更新企业权限信息
			manageCompanyService.updateCompanyResInfo(company, pageIds);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: selectCompanyCategory <BR>
	 * Description: 获取公司组织结构 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectAllCompanyCategory")
	@ResponseBody
	public Object selectAllCompanyCategory(HttpServletRequest request){
		try {
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			List<ManageCompanyBean> companyList = manageCompanyService.selectCompanyList(new ManageCompanyVo());
			List<ManageDepartmentBean> deptList = manageDepartmentService.getManageDepartmentByMap(new HashMap<String, String>());
			for(ManageDepartmentBean deptBean : deptList){
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", deptBean.getId()+"");
				if(deptBean.getParentId() == null){
					map.put("parentId", "com_"+deptBean.getCompanyId());
				}else{
					map.put("parentId", ""+deptBean.getParentId());
				}
				map.put("name", deptBean.getName());
				map.put("code", deptBean.getCode());
				list.add(map);
			}
			for(ManageCompanyBean cBean : companyList){
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", "com_"+cBean.getId());
				map.put("parentId", null);
				map.put("name", cBean.getName());
				map.put("code", cBean.getCode());
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: toCompanyAddPage <BR>
	 * Description: 新增公司页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCompanyAddPage")
	public String toCompanyAddPage(HttpServletRequest request){
		return "manage/companyAdd";
	}
	
	/**
	 * Method name: signUp <BR>
	 * Description: 新增公司 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param company
	 * @return  String<BR>
	 */
	@RequestMapping(value="insertCompany")
	@ResponseBody
	public String insertCompany(HttpServletRequest request, ManageCompanyBean company){
		try {
			company.setStatus(1);
			manageCompanyService.insertCompany(company);
			return Constant.AJAX_SUCCESS;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		} 
	}
	
	/**
	 * Method name: selectCompanyCategory <BR>
	 * Description: 获取部分公司组织结构 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectCompanyCategory")
	@ResponseBody
	public Object selectCompanyCategory(HttpServletRequest request){
		Map<String, Object> re = new HashMap<String, Object>();
		try {
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			List<String> subList = new ArrayList<String>();
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
			if(user.getCompanyId().toString().equals(user.getSubCompanyId().toString())){
				ManageCompanyVo cv = new ManageCompanyVo();
				cv.setId(user.getCompanyId());
				List<ManageCompanyBean> companyList = manageCompanyService.selectCompanyList(cv);
				Map<String, String> param = new HashMap<String, String>();
				param.put("companyId", user.getCompanyId()+"");
				List<ManageDepartmentBean> deptList = manageDepartmentService.getManageDepartmentByMap(param);
				for(ManageDepartmentBean deptBean : deptList){
					/*if(deptBean.getSubCompanyId() != deptBean.getCompanyId() && deptBean.getIsSubCompany() == 2){
						continue;
					}*/
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", deptBean.getId()+"");
					if(deptBean.getParentId() == null){
						map.put("parentId", "com_"+deptBean.getCompanyId());
						if(deptBean.getIsSubCompany() == 1){
							subList.add(deptBean.getId()+"");
						}
					}else{
						map.put("parentId", ""+deptBean.getParentId());
					}
					map.put("name", deptBean.getName());
					map.put("code", deptBean.getCode());
					map.put("isSubCompany", deptBean.getIsSubCompany().toString());
					list.add(map);
				}
				for(ManageCompanyBean cBean : companyList){
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", "com_"+cBean.getId());
					map.put("parentId", null);
					map.put("name", cBean.getName());
					map.put("code", cBean.getCode());
					map.put("isSubCompany", "2");
					list.add(map);
				}
			}else{
				List<ManageDepartmentBean> deptList = getSubDept(user.getSubCompanyId());
				deptList.add(manageDepartmentService.getManageDepartmentById(user.getSubCompanyId()));
				for(ManageDepartmentBean deptBean : deptList){
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", deptBean.getId()+"");
					if(deptBean.getParentId() == null){
						map.put("parentId", "com_"+deptBean.getCompanyId());
						if(deptBean.getIsSubCompany() == 1){
							subList.add(deptBean.getId()+"");
						}
					}else{
						map.put("parentId", ""+deptBean.getParentId());
					}
					map.put("name", deptBean.getName());
					map.put("code", deptBean.getCode());
					map.put("isSubCompany", deptBean.getIsSubCompany().toString());
					list.add(map);
				}
			}
			re.put("data", list);
			if(user.getCompanyId() - user.getSubCompanyId() != 0){
				subList = new ArrayList<String>();
			}
			re.put("subData", subList);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ManageDepartmentBean> getSubDept(long id) throws Exception{
		List<ManageDepartmentBean> allSubDept = new ArrayList<ManageDepartmentBean>();
		Map<String, String> param = new HashMap<String, String>();
		param.put("parentId", String.valueOf(id));
		List<ManageDepartmentBean> deptList = manageDepartmentService.getManageDepartmentByMap(param);
		allSubDept.addAll(deptList);
		for(int i = 0;i < deptList.size(); i++){
			allSubDept.addAll(getSubDept(deptList.get(i).getId()));
		}
		return allSubDept;
	}
	
	/**
	 * Method name: downloadTemplate <BR>
	 * Description: 下载模板 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param response
	 * @param path
	 * @throws Exception  void<BR>
	 */
	@RequestMapping(value="downloadTemplate")
	public void downloadTemplate(HttpServletRequest request, HttpServletResponse response, String path) throws Exception{
		FileUtil.downloadFile(request, response, new File(path), "模版.xls");
	}
	
	@RequestMapping(value="importFile")
	public String importFile(HttpServletRequest request){
		return "manage/importFile";
	}
	
	/**
	 * Method name: createDeptCode <BR>
	 * Description: 自动生成部门编码 <BR>
	 * Remark: <BR>
	 * @param parentId
	 * @return  String<BR>
	 */
	@RequestMapping(value="createDeptCode")
	@ResponseBody
	public String createDeptCode(String parentId, HttpServletRequest request){
		String deptCode = "";
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
			Map<String, Object> param = new HashMap<String, Object>();
			if(parentId.equals("")){
				//查询当前层级的最大部门数
				param.put("companyId", user.getCompanyId());
				param.put("id",parentId);
				String maxCode = manageDepartmentService.getMaxCode(param);
				try{
					deptCode = "1" + (Integer.parseInt(maxCode) > 9 ? "" : "0"+(Integer.parseInt(maxCode)+1));
				}catch(Exception e){
					return "101";
				}
			}else{
				//查询当前层级的最大部门数
				param.put("companyId", user.getCompanyId());
				param.put("id",parentId);
				String parentCode = manageDepartmentService.getParentCode(param);
				String maxCode = manageDepartmentService.getMaxCode(param);
				try{
					deptCode = (maxCode.equals("0") ? parentCode : "") + (Integer.parseInt(maxCode) > 9 ? "" : "0")+((Integer.parseInt(maxCode)+1));
				}catch(Exception e){
					return parentCode+"01";
				}
			}
		
		return deptCode;
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
		//1、获取文件储存的地址。
		String pageFileName= imgFile.getOriginalFilename();//.getName() ;
		String filetype = pageFileName.split("\\.")[1];
		String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/

		//获取拼接地址
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String extendUrl = "company/template/"+df.format(new Date());
		
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
				reObj.put("errCode", "上传失败");
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
				 StringBuffer errCode = new StringBuffer();
				 //此处缺少校验和异常处理
				 for(int i=1;i<array.size();i++){
					 JSONArray sArr = array.getJSONArray(i);
					 if(sArr.size() == 0){
						 continue; 
					 }
					 if(sArr.size() < 15){
						 errCode.append("第"+i+"行数据不完整；");
						 continue;
					 }
					if(!checkSAARNull(sArr)){
						errCode.append("第"+i+"行数据不能为空；");
						continue;						
					}
					 //判断编码是否重复
					 ManageCompanyVo vo_ = new ManageCompanyVo();
					 vo_.setCode(sArr.getString(0));
					 List<ManageCompanyBean> list_ = manageCompanyService.selectCompanyList(vo_);
					 if(list_ != null && list_.size() > 0){
						 errCode.append("第"+i+"行数据，企业编码重复，不能导入；");
						 continue;
					 }
					 //判断域名是否重复
					 ManageCompanyVo vo = new ManageCompanyVo();
					 vo.setDomain(sArr.getString(2));
					 List<ManageCompanyBean> list = manageCompanyService.selectCompanyList(vo);
					 if(list != null && list.size() > 0){
						 errCode.append("第"+i+"行数据，企业域名重复，不能导入；");
						 continue;
					 }
					 if(!sArr.isEmpty()){
						 ManageCompanyBean company = new ManageCompanyBean();
						 company.setCode(sArr.getString(0));
						 company.setName(sArr.getString(1));
						 company.setDomain(sArr.getString(2));
						 company.setEmail(sArr.getString(3));
						 company.setAddress(sArr.getString(4));
						 company.setPostCode(sArr.getString(5));
						 ManageIndustryCategoryBean industry = manageIndustryCategoryService.selectByName(sArr.getString(6));
						 if(industry == null){
							 errCode.append("第"+i+"行数据，行业分类填写错误，不能导入；");
							 continue;
						 }
						 company.setIndustry(industry==null?0:industry.getId());
						 String proportion = sArr.getString(7);
						 if("100人以下".equals(proportion)){
							 company.setProportion(1);
						 }else if("100-999人".equals(proportion)){
							 company.setProportion(2);
						 }else if("1000-9999人".equals(proportion)){
							 company.setProportion(3);
						 }else if("10000人以上".equals(proportion)){
							 company.setProportion(4);
						 }else{
							 errCode.append("第"+i+"行数据，企业规模填写错误，不能导入；");
							 continue;
						 }
						 company.setAttention(sArr.getString(8));
						 company.setAttentionPhone(sArr.getString(9));
						 company.setStartTime(sArr.getString(10));
						 company.setEndTime(sArr.getString(11));
						 if(!"".equals(sArr.getString(12))){
							 company.setPhoneNum(sArr.getString(12));
						 }
						 company.setWebsite(sArr.getString(13));
						 company.setLogoImage("/ELN/images/img/logo_2_new.png");
						 manageCompanyService.insertCompany(company);
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
			
			System.out.println(e);
			reObj.put("errCode", "上传失败");
			return reObj;
		}
		return reObj;
	}
	
	public boolean checkSAARNull(JSONArray sArr){
		for(int i = 0; i < 14; i++){
			if(sArr.getString(i) == null || "".equals(sArr.getString(i))){
				return false;
			}
		}
		return true;
	}
	
	
}
