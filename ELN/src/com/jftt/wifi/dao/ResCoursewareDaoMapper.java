package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ResCoursewareBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.exception.database.DataBaseException;

public interface ResCoursewareDaoMapper {
    /**
     * Method name: delete <BR>
     * Description: 删除课件 <BR>
     * Remark: <BR>
     * @param id
     * @return  int<BR>
     */
    int delete(@Param("id")Integer id);

    /**
     * Method name: insert <BR>
     * Description: 新增课件 <BR>
     * Remark: <BR>
     * @param record
     * @return  int<BR>
     */
    int insert(ResCoursewareBean record);

    /**
     * Method name: select <BR>
     * Description: 查询课件 <BR>
     * Remark: <BR>
     * @param param
     * @return  List<ResCoursewareBean><BR>
     */
    List<ResCoursewareBean> select(Map<String, Object> param);

    /**
     * Method name: update <BR>
     * Description: 修改课件 <BR>
     * Remark: <BR>
     * @param record
     * @return  int<BR>
     */
    int update(ResCoursewareBean record);

	/**
	 * Method name: selectCount <BR>
	 * Description: 查询课件数量 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  int<BR>
	 */
	int selectCount(Map<String, Object> param);

	/**
	 * Method name: selectSectionAndCourseware <BR>
	 * Description: selectSectionAndCourseware <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<PaperBean><BR>
	 */
	List<ResCoursewareBean> selectSectionAndCourseware(Map<String, Object> param);
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询课件bean <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  ResCoursewareBean<BR>
	 */
	ResCoursewareBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**
	 * Method name: getDurationByCourseId <BR>
	 * Description: 根据课程id查询所有课件时长 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return
	 * @throws DataBaseException  List<Integer><BR>
	 */
	List<Integer> getDurationByCourseId(@Param("courseId")Integer courseId) throws DataBaseException;
	
	/**shenhaijun end*/
	
	/**chenrui start**/
	
	void inserRelate(@Param("sectionId")int newSectionId, @Param("coursewareId")int newCoursewareId);
	
	/**chenrui end**/
}