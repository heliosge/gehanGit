
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.OfflineTestResultsBean;

/**
 * 线下考试  学生成绩
 */
public interface OfflineTestResultsDaoMapper {
	
	/**
	 * 增加
	 */
	public void addOfflineTestResults(OfflineTestResultsBean offlineTestResults);

	/**
	 * 修改
	 */
	public void updateOfflineTestResults(OfflineTestResultsBean offlineTestResults);
	
	/**
	 * 根据条件 获得数量
	 */
	public int getOfflineTestResultsNum(OfflineTestResultsBean offlineTestResults);
	
	/**
	 * 根据条件 获得数据
	 */
	public List<OfflineTestResultsBean> getOfflineTestResults(OfflineTestResultsBean offlineTestResults);
	
	/**
	 * 删除线下考试结果
	 */
	public void deleteOfflineTestResultsByTestId(@Param("testId")long testId);
}
