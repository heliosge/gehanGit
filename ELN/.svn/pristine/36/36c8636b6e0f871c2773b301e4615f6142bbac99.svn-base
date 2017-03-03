
package com.jftt.wifi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.OfflineTestResultsBean;

/**
 * 线下考试  学生成绩
 */
public interface OfflineTestResultsService {
	
	/**
	 * 增加
	 */
	public void addOfflineTestResults(OfflineTestResultsBean offlineTestResults);

	/**
	 * 修改
	 */
	public void updateOfflineTestResults(OfflineTestResultsBean offlineTestResults);
	
	/**
	 * 删除
	 */
	public void deleteOfflineTestResults(long id);
	
	/**
	 * 根据条件 获得数量
	 */
	public int getOfflineTestResultsNum(OfflineTestResultsBean offlineTestResults);
	
	/**
	 * 根据条件 获得数据
	 */
	public List<OfflineTestResultsBean> getOfflineTestResults(OfflineTestResultsBean offlineTestResults);
	
	/**
	 * 根据条件 获得数据
	 */
	public List<OfflineTestResultsBean> getOfflineTestResultsByTestId(long testId);
	
	/**
	 * 根据条件 获得数据
	 */
	public OfflineTestResultsBean getOfflineTestResultsById(long id);
	
	/**
	 * 删除线下考试结果
	 */
	public void deleteOfflineTestResultsByTestId(long testId);
}
