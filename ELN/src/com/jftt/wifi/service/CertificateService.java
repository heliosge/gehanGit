/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CerCertificateService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月11日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jftt.wifi.bean.CertificateBean;

/**
 * class name:CerCertificateService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月11日
 * @author JFTT)Lu Yunlong
 */
public interface CertificateService {
	
	/**
	 * Method name: deleteCertificate <BR>
	 * Description: 删除证书 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	void deleteCertificate(Integer id) throws Exception;

    /**
     * Method name: insertCertificate <BR>
     * Description: 新增证书 <BR>
     * Remark: <BR>
     * @param record
     * @throws Exception  void<BR>
     */
    void insertCertificate(CertificateBean record) throws Exception;

    /**
     * Method name: selectCertificateById <BR>
     * Description: 根据id查询证书 <BR>
     * Remark: <BR>
     * @param record
     * @return
     * @throws Exception  CerCertificateBean<BR>
     */
    CertificateBean selectCertificateById(Integer id) throws Exception;
    
    /**
     * Method name: selectCertificate <BR>
     * Description: 查询证书 <BR>
     * Remark: <BR>
     * @param record
     * @return
     * @throws Exception  List<CerCertificateBean><BR>
     */
    List<CertificateBean> selectCertificate(Map<String, Object> record) throws Exception;
    
    /**
     * Method name: selectCertificateCount <BR>
     * Description: 查询证书数量 <BR>
     * Remark: <BR>
     * @param record
     * @return
     * @throws Exception  int<BR>
     */
    int selectCertificateCount(Map<String, Object> record) throws Exception;
    
    /**
     * Method name: updateCertificate <BR>
     * Description: 修改证书 <BR>
     * Remark: <BR>
     * @param record
     * @throws Exception  void<BR>
     */
    void updateCertificate(CertificateBean record) throws Exception;

	/**
	 * Method name: exportExcel <BR>
	 * Description: 导出证书excel <BR>
	 * Remark: <BR>
	 * @param result
	 * @return  HSSFWorkbook<BR>
	 */
	HSSFWorkbook exportExcel(List<CertificateBean> result);
}
