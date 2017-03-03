/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CerCertificateServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月11日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.CertificateBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.dao.CertificateDaoMapper;
import com.jftt.wifi.service.CertificateService;
import com.jftt.wifi.util.MyExcelHelp;

/**
 * class name:CerCertificateServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月11日
 * @author JFTT)Lu Yunlong
 */
@Service("certificateService")
public class CertificateServiceImpl implements CertificateService {
	
	@Resource(name="certificateDaoMapper")
	private CertificateDaoMapper certificateDaoMapper;

	/**
	 * @Override
	 * @see com.jftt.wifi.service.CertificateService#deleteCertificate(java.lang.Integer) <BR>
	 * Method name: deleteCertificate <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	 */

	@Override
	public void deleteCertificate(Integer id) throws Exception {
		certificateDaoMapper.deleteCertificate(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.CertificateService#insertCertificate(com.jftt.wifi.bean.CertificateBean) <BR>
	 * Method name: insertCertificate <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param record
	 * @throws Exception  <BR>
	 */

	@Override
	public void insertCertificate(CertificateBean record) throws Exception {
		certificateDaoMapper.insertCertificate(record);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.CertificateService#selectCertificateById(com.jftt.wifi.bean.CertificateBean) <BR>
	 * Method name: selectCertificateById <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param record
	 * @return
	 * @throws Exception  <BR>
	 */

	@Override
	public CertificateBean selectCertificateById(Integer id)
			throws Exception {
		Map<String, Object> record = new HashMap<String, Object>();
		record.put("id",id);
		return certificateDaoMapper.selectCertificate(record).get(0);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.CertificateService#selectCertificate(com.jftt.wifi.bean.CertificateBean) <BR>
	 * Method name: selectCertificate <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param record
	 * @return
	 * @throws Exception  <BR>
	 */

	@Override
	public List<CertificateBean> selectCertificate(Map<String, Object> record)
			throws Exception {
		return certificateDaoMapper.selectCertificate(record);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CertificateService#selectCertificateCount(java.util.Map) <BR>
	 * Method name: selectCertificateCount <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param record
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public int selectCertificateCount(Map<String, Object> record)
			throws Exception {
		return certificateDaoMapper.selectCertificateCount(record);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.CertificateService#updateCertificate(com.jftt.wifi.bean.CertificateBean) <BR>
	 * Method name: updateCertificate <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param record
	 * @throws Exception  <BR>
	 */

	@Override
	public void updateCertificate(CertificateBean record) throws Exception {
		certificateDaoMapper.updateCertificate(record);
	}

	@Override
	public HSSFWorkbook exportExcel(List<CertificateBean> result) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "证书列表");
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
		
		MyExcelHelp.createStringCell(row, 0, centerStyle, "证书名称");
		sheet.setColumnWidth(0, 10 * 128);
		MyExcelHelp.createStringCell(row, 1, centerStyle, "获得人员");
		sheet.setColumnWidth(1, 10 * 128);
		MyExcelHelp.createStringCell(row, 2, centerStyle, "理论成绩");
		sheet.setColumnWidth(2, 10 * 128);
		MyExcelHelp.createStringCell(row, 3, centerStyle, "实操成绩");
		sheet.setColumnWidth(3, 10 * 128);
		MyExcelHelp.createStringCell(row, 4, centerStyle, "发证日期");
		sheet.setColumnWidth(4, 10 * 128);
		MyExcelHelp.createStringCell(row, 5, centerStyle, "初次复审日期");
		sheet.setColumnWidth(5, 10 * 128);
		MyExcelHelp.createStringCell(row, 6, centerStyle, "二次复审日期");
		sheet.setColumnWidth(6, 10 * 128);
		MyExcelHelp.createStringCell(row, 7, centerStyle, "证书复审状态");
		sheet.setColumnWidth(7, 10 * 128);
		MyExcelHelp.createStringCell(row, 8, centerStyle, "培训方式");
		sheet.setColumnWidth(8, 10 * 128);
		MyExcelHelp.createStringCell(row, 9, centerStyle, "培训机构");
		sheet.setColumnWidth(9, 10 * 128);
		MyExcelHelp.createStringCell(row, 10, centerStyle, "培训机构级别");
		sheet.setColumnWidth(10, 10 * 128);
		MyExcelHelp.createStringCell(row, 11, centerStyle, "换证日期");
		sheet.setColumnWidth(11, 10 * 128);
		MyExcelHelp.createStringCell(row, 12, centerStyle, "备注");
		sheet.setColumnWidth(12, 10 * 128);
		
		for (j = 0; j < result.size(); j++) {// 控制行
			CertificateBean bean = result.get(j);
			row = sheet.createRow(j+1);
			int colIndex = 0;
			Integer level = bean.getTrainAgencyLevel();
			String levelStr = "";
			if(level == null){
				levelStr = "";
			}else if(level == 1){
				levelStr = "国家一级";
			}else if(level == 2){
				levelStr = "国家二级";
			}else if(level == 3){
				levelStr = "国家三级";
			}else if(level == 4){
				levelStr = "国际四级";
			}else if(level == 5){
				levelStr = "其他";
			}
			String status = "";
			if(bean.getCheckStatus() == null){
				
			}else if (bean.getCheckStatus() ==1){
				status = "已复审";
			}else{
				status = "未复审";
			}
			MyExcelHelp.createStringCell(row, colIndex++, leftStyle, bean.getName());
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, bean.getReceiveUserName());
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, String.valueOf(bean.getTheoryScore()));
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle,String.valueOf(bean.getOperateScore()));
			MyExcelHelp.createStringCell(row, colIndex++, moneyStyle,bean.getSendDate());
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, bean.getFirstCheckDate());
			MyExcelHelp.createStringCell(row, colIndex++, moneyStyle,bean.getSecondCheckDate());
			MyExcelHelp.createStringCell(row, colIndex++, moneyStyle,status);
			MyExcelHelp.createStringCell(row, colIndex++, moneyStyle,bean.getTrainStyle());
			MyExcelHelp.createStringCell(row, colIndex++, moneyStyle,bean.getTrainAgency());
			MyExcelHelp.createStringCell(row, colIndex++, moneyStyle,levelStr);
			MyExcelHelp.createStringCell(row, colIndex++, moneyStyle,bean.getChangeDate());
			MyExcelHelp.createStringCell(row, colIndex++, moneyStyle,bean.getDescr());
		}
		return workbook;
	}

}
