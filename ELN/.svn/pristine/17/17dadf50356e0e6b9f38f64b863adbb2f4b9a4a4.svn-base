
package com.jftt.wifi.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.OfflineTestBean;
import com.jftt.wifi.bean.OfflineTestResultsBean;

/**
 * 线下考试
 */
public interface OfflineTestService {
	
	/**
	 * 增加
	 */
	public void addOfflineTest(OfflineTestBean offlineTest);

	/**
	 * 修改
	 */
	public void updateOfflineTest(OfflineTestBean offlineTest);
	
	/**
	 * 删除
	 */
	public void deleteOfflineTest(long id);
	
	/**
	 * 删除
	 */
	public void deleteOfflineTest(long[] ids);
	
	/**
	 * 根据条件 获得数量
	 */
	public int getOfflineTestNum(OfflineTestBean offlineTest);
	
	/**
	 * 根据条件 获得数据
	 */
	public List<OfflineTestBean> getOfflineTest(OfflineTestBean offlineTest);
	
	/**
	 * 根据条件 获得数据
	 */
	public OfflineTestBean getOfflineTestById(long id);
	
	/**
	 * 导出
	 */
	public Workbook exportOfflineTest(OfflineTestBean testBean);
	
	/**
	 * excel 导入
	 */
	public void addByExcel(String filePath, int subCompanyId) throws Exception;
}
