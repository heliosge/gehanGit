
package com.jftt.wifi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.OfflineTestResultsBean;
import com.jftt.wifi.dao.OfflineTestResultsDaoMapper;
import com.jftt.wifi.service.OfflineTestResultsService;

/**
 * 线下考试  学生成绩
 */
@Service("offlineTestResultsService")
public class OfflineTestResultsServiceImpl implements OfflineTestResultsService{
	
	@Resource(name="offlineTestResultsDaoMapper")
	private OfflineTestResultsDaoMapper offlineTestResultsDaoMapper;
	
	/**
	 * 增加
	 */
	@Transactional
	public void addOfflineTestResults(OfflineTestResultsBean offlineTestResults){
		
		offlineTestResultsDaoMapper.addOfflineTestResults(offlineTestResults);
	}

	/**
	 * 修改
	 */
	@Transactional
	public void updateOfflineTestResults(OfflineTestResultsBean offlineTestResults){
		
		offlineTestResultsDaoMapper.updateOfflineTestResults(offlineTestResults);
	}
	
	/**
	 * 删除
	 */
	@Transactional
	public void deleteOfflineTestResults(long id){
		
		OfflineTestResultsBean offlineTestResults = new OfflineTestResultsBean();
		offlineTestResults.setId(id);
		offlineTestResults.setIs_delete(1);
		
		offlineTestResultsDaoMapper.updateOfflineTestResults(offlineTestResults);
	}
	
	/**
	 * 根据条件 获得数量
	 */
	@Transactional
	public int getOfflineTestResultsNum(OfflineTestResultsBean offlineTestResults){
		
		return offlineTestResultsDaoMapper.getOfflineTestResultsNum(offlineTestResults);
	}
	
	/**
	 * 根据条件 获得数据
	 */
	@Transactional
	public List<OfflineTestResultsBean> getOfflineTestResults(OfflineTestResultsBean offlineTestResults){
		
		return offlineTestResultsDaoMapper.getOfflineTestResults(offlineTestResults);
	}
	
	/**
	 * 根据条件 获得数据
	 */
	@Transactional
	public List<OfflineTestResultsBean> getOfflineTestResultsByTestId(long testId){
		
		OfflineTestResultsBean offlineTestResults = new OfflineTestResultsBean();
		offlineTestResults.setOffline_test_id(testId);
		offlineTestResults.setIs_delete(0);
		
		List<OfflineTestResultsBean> list = offlineTestResultsDaoMapper.getOfflineTestResults(offlineTestResults);
		
		return list;
	}
	
	/**
	 * 根据条件 获得数据
	 */
	@Transactional
	public OfflineTestResultsBean getOfflineTestResultsById(long id){
		
		OfflineTestResultsBean offlineTestResults = new OfflineTestResultsBean();
		offlineTestResults.setId(id);
		offlineTestResults.setIs_delete(0);
		
		List<OfflineTestResultsBean> list = offlineTestResultsDaoMapper.getOfflineTestResults(offlineTestResults);
		if(list !=null && !list.isEmpty()){
			return list.get(0);
		}
		
		return null;
	}
	
	/**
	 * 删除线下考试结果
	 */
	@Transactional
	public void deleteOfflineTestResultsByTestId(long testId){
		
		offlineTestResultsDaoMapper.deleteOfflineTestResultsByTestId(testId);
	}
}
