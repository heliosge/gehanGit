/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageCompanyServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月28日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.CertificateBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageRoleBean;
import com.jftt.wifi.bean.ManageRolePageBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ManageUserRoleBean;
import com.jftt.wifi.bean.vo.ManageCompanyVo;
import com.jftt.wifi.dao.ManageCompanyDaoMapper;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManagePageService;
import com.jftt.wifi.service.ManageRoleService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.MyExcelHelp;
import com.jftt.wifi.util.SendMessageUtil;

/**
 * class name:ManageCompanyServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月28日
 * @author JFTT)Lu Yunlong
 */
@Service("manageCompanyService")
public class ManageCompanyServiceImpl implements ManageCompanyService{
	
	@Resource(name="manageCompanyDaoMapper")
	private ManageCompanyDaoMapper manageCompanyDaoMapper;
	
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	
	@Resource(name="managePageService")
	private ManagePageService managePageService;
	
	@Resource(name="manageRoleService")
	private ManageRoleService manageRoleService;
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageCompanyService#insertCompany(com.jftt.wifi.bean.ManageCompanyBean) <BR>
	 * Method name: 新增企业 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  <BR>
	*/
	@Override
	public void insertCompany(ManageCompanyBean bean)
			throws Exception {
		manageCompanyDaoMapper.insertCompany(bean);
	}


	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageCompanyService#selectByMap(java.util.Map) <BR>
	 * Method name: selectCompanyList <BR>
	 * Description: 根据条件查询租户公司 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<ManageCompanyBean> selectCompanyList(ManageCompanyVo vo)
			throws Exception {
		return manageCompanyDaoMapper.selectCompanyList(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageCompanyService#selectById(java.lang.String) <BR>
	 * Method name: selectCompanyById <BR>
	 * Description: 根据Id查询租户公司 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public ManageCompanyBean selectCompanyById(Integer id) throws Exception {
		ManageCompanyVo vo = new ManageCompanyVo();
		vo.setId(id);
		List<ManageCompanyBean> list = selectCompanyList(vo);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int selectCompanyCount(ManageCompanyVo vo) throws Exception {
		return manageCompanyDaoMapper.selectCompanyCount(vo);
	}

	@Override
	public void deleteCompany(Integer id) throws Exception {
		manageCompanyDaoMapper.deleteCompany(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageCompanyService#freezeAndUnfreezeManageCompany(com.jftt.wifi.bean.vo.ManageCompanyVo) <BR>
	 * Method name: freezeAndUnfreezeManageCompany <BR>
	 * Description: 冻结、解冻企业 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @throws Exception  <BR>
	*/
	@Override
	public void freezeAndUnfreezeManageCompany(Integer id, String status) throws Exception {
		manageCompanyDaoMapper.freezeAndUnfreezeManageCompany(id, status);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageCompanyService#updateCompanyBaseInfo(com.jftt.wifi.bean.ManageCompanyBean) <BR>
	 * Method name: updateCompanyBaseInfo <BR>
	 * Description: 修改企业基本详细 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateCompanyBaseInfo(ManageCompanyBean bean) throws Exception {
		manageCompanyDaoMapper.updateCompanyBaseInfo(bean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageCompanyService#updateCompanyResInfo(com.jftt.wifi.bean.ManageCompanyBean) <BR>
	 * Method name: updateCompanyResInfo <BR>
	 * Description: 修改企业资源信息 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateCompanyResInfo(ManageCompanyBean company, String[] pageIds) throws Exception {
		//manageCompanyDaoMapper.updateCompanyResInfo(bean);
		//获取用户的角色Id
		long roleId = company.getInitRoleId();
		//删除原来用户的权限
		managePageService.deleteManagePageByUserId(roleId);
		if(pageIds != null && pageIds.length > 0){
			List<ManageRolePageBean> rolePageList = new ArrayList<ManageRolePageBean>();
			for (String pageId : pageIds) {
				ManageRolePageBean rolePage = new ManageRolePageBean();
				rolePage.setRoleId(roleId);
				rolePage.setPageId(Long.parseLong(pageId));
				rolePageList.add(rolePage);
			}
			managePageService.addRolesManagePage(rolePageList);
		}
		//删除该公司子用户的权限
		manageUserService.deleteSubManagePageByUserId(roleId);
		//修改该企业的资源信息
		manageCompanyDaoMapper.updateCompanyResInfo(company);
		//修改管理员登陆账号
		manageUserService.updateManageUserPassword(company.getInitUserId().toString(), company.getInitUsername(), CommonUtil.getMD5(company.getInitPassword()));
		//发送邮件通知用户
		int count = 0;
		String content = "尊敬的用户：恭喜您的账户已审核通过，用户名为"+company.getInitUsername()+
				"，密码为"+company.getInitPassword()+"，您的账号有效期为"+company.getEndTime()+
				"，账号数量为"+company.getAccountCount()+
				"，可用资源容量为"+company.getTotalCapacity()+"GB，立即访问<a href='http://"+company.getDomain()+
				".anpeiwang.com'>http://"+company.getDomain()+".anpeiwang.com</a>开始体验吧。<br/>";
		String messageContent = "尊敬的用户：恭喜您的账户已审核通过，用户名为"+company.getInitUsername()+
				"，密码为"+company.getInitPassword()+"，您的账号有效期为"+company.getEndTime()+
				"，账号数量为"+company.getAccountCount()+
				"，可用资源容量为"+company.getTotalCapacity()+"GB，立即访问 http://"+company.getDomain()+".anpeiwang.com 开始体验吧。";
		while(!SendMessageUtil.sendEmail(company.getEmail(),"安培在线会员审核",content+"安培在线，互联网+安全培训服务专家，为您提供随时随地的安全培训。<br/>Powered by 安培在线<br/>咨询热线：4008-430-400<br/>") && count++ < 3);
		while(!SendMessageUtil.sendSMS(messageContent, company.getPhoneNum().toString()) && count++ < 3);
		
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageCompanyService#insertUserByCompany(com.jftt.wifi.bean.ManageCompanyBean) <BR>
	 * Method name: 根据新增企业新建用户 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  <BR>
	*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void insertUserByCompany(ManageCompanyBean bean, String[] pageIds) throws Exception {
		//新增该企业管理员用户
		ManageUserBean user = new ManageUserBean();
		user.setUserName(bean.getInitUsername());
		user.setPassword(CommonUtil.getMD5(bean.getInitPassword()));
		user.setName("管理员");
		user.setSex(1);
		user.setAddress(bean.getAddress());
		user.setMobile((bean.getPhoneNum()==null?"":bean.getPhoneNum().toString()));
		user.setCompanyId(bean.getId());
		user.setEmail(bean.getEmail());
		user.setSubCompanyId(bean.getId());
		user.setIsManager(1);
		user.setStatus(1);
		int userId = manageUserService.insert(user);
		//新增管理员用户角色
		ManageRoleBean role = new ManageRoleBean();
		role.setName(bean.getName()+"公司管理员");
		role.setCompanyId(bean.getId());
		role.setSubCompanyId(bean.getId());
		manageRoleService.addManageRole(role);
		//更新该角色权限
		if(pageIds != null && pageIds.length > 0){
			List<ManageRolePageBean> rolePageList = new ArrayList<ManageRolePageBean>();
			for (String pageId : pageIds) {
				ManageRolePageBean rolePage = new ManageRolePageBean();
				rolePage.setRoleId(role.getId());
				rolePage.setPageId(Long.parseLong(pageId));
				rolePageList.add(rolePage);
			}
			managePageService.addRolesManagePage(rolePageList);
		}
		//为该管理员用户分配管理员角色
		ManageUserRoleBean manageUserRole = new ManageUserRoleBean();
		manageUserRole.setRoleId((int)role.getId());
		manageUserRole.setUserId(userId);
		manageUserService.addUserRole(manageUserRole);
		//返回结果
		bean.setInitUserId(userId);
		bean.setInitRoleId((int)role.getId());
		//更新企业权限信息
		manageCompanyDaoMapper.updateCompanyResInfo(bean);
		//发送邮件通知用户
		int count = 0;
		String content = "尊敬的用户：恭喜您的账号已审核通过，用户名为"+bean.getInitUsername()+
				"，密码为"+bean.getInitPassword()+"，您的账号有效期为"+bean.getEndTime()+
				"，账号数量为"+bean.getAccountCount()+
				"，可用资源容量为"+bean.getTotalCapacity()+"GB，立即访问<a href='http://"+bean.getDomain()+
				".anpeiwang.com'>http://"+bean.getDomain()+".anpeiwang.com</a>开始体验吧。<br/>";
		String messageContent = "尊敬的用户：恭喜您的账号已审核通过，用户名为"+bean.getInitUsername()+
				"，密码为"+bean.getInitPassword()+"，您的账号有效期为"+bean.getEndTime()+
				"，账号数量为"+bean.getAccountCount()+
				"，可用资源容量为"+bean.getTotalCapacity()+"GB，立即访问 http://"+bean.getDomain()+".anpeiwang.com 开始体验吧。";
		while(!SendMessageUtil.sendEmail(bean.getEmail(),"安培在线会员审核",content+"安培在线，互联网+安全培训服务专家，为您提供随时随地的安全培训。<br/>Powered by 安培在线<br/>咨询热线：4008-430-400<br/>") && count++ < 3);
		while(!SendMessageUtil.sendSMS(messageContent, bean.getPhoneNum().toString()) && count++ < 3);
	}


	@Override
	public void insertCompanyActiveCode(String email, String phone) throws Exception {
		Random ra =new Random();
    	String activeCode = ""+ra.nextInt(10)+ra.nextInt(10)+ra.nextInt(10)+ra.nextInt(10)+ra.nextInt(10)+ra.nextInt(10);
    	int count = 1;
    	if(email != null && !"".equals(email)){
    		String content = "您好，感谢您选择安培在线，验证码为"+activeCode+"，10分钟内有效，请及时完成注册。<br/>安培在线，互联网+安全培训服务专家，为您提供随时随地的安全培训。<br/>Powered by 安培在线<br/>咨询热线：4008-430-400<br/>";
    		while(!SendMessageUtil.sendEmail(email, "安培在线系统激活码", content) && count++ < 3);
    		manageCompanyDaoMapper.insertCompanyActiveCode(email, activeCode);
    	}
    	if(phone != null && !"".equals(phone)){
    		while(!SendMessageUtil.sendSMS("您好，感谢您选择安培在线，验证码为"+activeCode+"，10分钟内有效，请及时完成注册。", phone) && count++ < 3);
    		manageCompanyDaoMapper.insertCompanyActiveCode(phone, activeCode);
    	}
		
	}


	@Override
	public int checkActiveCode(String key, String activeCode) throws Exception {
		return manageCompanyDaoMapper.checkActiveCode(key, activeCode);
	}


	@Override
	public List<Map> selectCity(Map<String, Object> map) throws Exception {
		return manageCompanyDaoMapper.selectCity(map);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageCompanyService#selectManageCompanyCapacityList(com.jftt.wifi.bean.vo.ManageCompanyVo) <BR>
	 * Method name: selectManageCompanyCapacityList <BR>
	 * Description: 租户企业容量列表 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  <BR>
	*/
	@Override
	public List<ManageCompanyBean> selectManageCompanyCapacityList(
			ManageCompanyVo vo) {
		return manageCompanyDaoMapper.selectManageCompanyCapacityList(vo);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageCompanyService#addCapacity(java.util.Map) <BR>
	 * Method name: addCapacity <BR>
	 * Description: 扩容 <BR>
	 * Remark: <BR>
	 * @param param  <BR>
	*/
	@Override
	public void addCapacity(Map<String, Object> param) {
		manageCompanyDaoMapper.addCapacity(param);		
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageCompanyService#exportExcel(java.util.List) <BR>
	 * Method name: exportExcel <BR>
	 * Description: 导出企业容量excel <BR>
	 * Remark: <BR>
	 * @param result
	 * @return  <BR>
	*/
	@Override
	public HSSFWorkbook exportExcel(List<ManageCompanyBean> result) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "企业容量列表");
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
		// 标题行
		HSSFRow row = sheet.createRow(0);
		int j = 0;
		
		MyExcelHelp.createStringCell(row, 0, centerStyle, "企业代码");
		sheet.setColumnWidth(0, 40 * 128);
		MyExcelHelp.createStringCell(row, 1, centerStyle, "企业大学名称");
		sheet.setColumnWidth(1, 40 * 128);
		MyExcelHelp.createStringCell(row, 2, centerStyle, "行业分类");
		sheet.setColumnWidth(2, 40 * 128);
		MyExcelHelp.createStringCell(row, 3, centerStyle, "企业域名");
		sheet.setColumnWidth(3, 40 * 128);
		MyExcelHelp.createStringCell(row, 4, centerStyle, "注册时间");
		sheet.setColumnWidth(4, 40 * 128);
		MyExcelHelp.createStringCell(row, 5, centerStyle, "状态");
		sheet.setColumnWidth(5, 40 * 128);
		MyExcelHelp.createStringCell(row, 6, centerStyle, "可用容量（GB）/总容量（GB）");
		sheet.setColumnWidth(6, 40 * 128);
		
		for (j = 0; j < result.size(); j++) {// 控制行
			ManageCompanyBean bean = result.get(j);
			row = sheet.createRow(j+1);
			int colIndex = 0;
			MyExcelHelp.createStringCell(row, colIndex++, leftStyle, bean.getCode());
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, bean.getName());
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, bean.getIndustryName());
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, bean.getDomain());
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, bean.getCreateTime());
			if(bean.getStatus().toString().equals("1")){
				MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, "正常");
			}else if(bean.getStatus().toString().equals("2")){
				MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, "冻结");
			}else if(bean.getStatus().toString().equals("4")){
				MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, "待审批");
			}else if(bean.getStatus().toString().equals("5")){
				MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, "审批通过");
			}
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, bean.getUsedCapacity()+"/"+bean.getTotalCapacity());
		}
		return workbook;
	}
	
	//zhangbocheng mobile start
		/**
		 * Method name: getCompanyIdByName <BR>
		 * Description: 根据公司名获得id <BR>
		 * Remark: <BR>
		 * @param name
		 * @return
		 * @throws Exception  Object<BR>
		 */
	      @Override
	  public Integer getCompanyIdByName(String name) throws Exception{
	    	  return manageCompanyDaoMapper.getCompanyIdByName(name);
	      }
		//zhangbocheng mobile end


		/**
		 * @Override
		 * @see com.jftt.wifi.service.ManageCompanyService#selectTotalCapacity() <BR>
		 * Method name: selectTotalCapacity <BR>
		 * Description: 获取所有企业文件占用的空间 <BR>
		 * Remark: <BR>
		 * @return  <BR>
		*/
		@Override
		public double selectTotalCapacity() {
			return manageCompanyDaoMapper.selectTotalCapacity();
		}

		

}
