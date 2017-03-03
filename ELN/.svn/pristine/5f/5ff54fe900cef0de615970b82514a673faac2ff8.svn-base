package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.IntegralCommodityCategoryBean;
import com.jftt.wifi.bean.MallCourseCategoryBean;
import com.jftt.wifi.bean.vo.MallCourseCategoryVo;

public interface MallCourseCategoryDaoMapper {

    MallCourseCategoryBean selectByPrimaryKey(Integer id);

    /**chenrui start*/
    
    /**
     * 获取商城课程分类
     * Method name: getMallCourseCategory <BR>
     * Description: getMallCourseCategory <BR>
     * Remark: <BR>
     * @return
     * @throws Exception  List<MallCourseCategoryBean><BR>
     */
	List<MallCourseCategoryBean> getMallCourseCategory() throws Exception;
	
	List<MallCourseCategoryBean> getChildsByParentId(@Param("id")int categoryId) throws Exception;
	
	/**chenrui end*/
	

	/**zhangbocheng start*/
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id获取商城课程分类 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  MallCourseCategoryBean<BR>
	 */
	public MallCourseCategoryBean getById(@Param("id")Integer id) throws Exception;
	
	
	/**
	 * Method name: checkName <BR>
	 * Description: 检查分类重名 <BR>
	 * Remark: <BR>
	 * @param MallCourseCategoryBean  Integer<BR>
	 */
	public Integer checkName(MallCourseCategoryBean bean) throws Exception;
	
	
	
	/**
	 * Method name: deleteMallCourseCategory <BR>
	 * Description: 删除分类<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteMallCourseCategory(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: hasCourse <BR>
	 * Description: 分类下是否有课程<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public Integer hasCourse(@Param("id")Integer id) throws Exception;

	/**
	 * Method name: updateMallCourseCategory <BR>
	 * Description: 修改分类 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	public void updateMallCourseCategory(MallCourseCategoryBean record) throws Exception;

	/**
	 * Method name: insertMallCourseCategory <BR>
	 * Description: 添加分类 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	public Integer insertMallCourseCategory(MallCourseCategoryBean record) throws Exception;

	
	/**
	 * Method name: getMallCourseCategorys <BR>
	 * Description: 查询分类 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return  List<MallCourseCategoryBean><BR>
	 */
	public List<MallCourseCategoryVo> getMallCourseCategorys() throws Exception;
	
	/**
	 * Method name: getChildCategory <BR>
	 * Description: 获取分类的子分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws Exception  List<MallCourseAllCategoryVo><BR>
	 */
	public List<MallCourseCategoryVo> getChildCategorys(@Param("categoryId")Integer categoryId) throws Exception;
	
	/**
	 * Method name: getUpCategorys <BR>
	 * Description: 获取上一级的所有分类<BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws Exception  List<MallCourseCategoryBean><BR>
	 */
	public List<MallCourseCategoryBean> getUpCategorys(@Param("categoryId")Integer categoryId) throws Exception;


	/**
	 * Method name: getMaxOrderNum <BR>
	 * Description: 获取最大排序字段<BR> 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public Integer getMaxOrderNum(@Param("parentId")Integer parentId) throws Exception;
	
	/**
	 * Method name: getUpCategory <BR>
	 * Description: 获取上一个分类<BR>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public MallCourseCategoryBean getUpCategory(@Param("parentId")Integer parentId,@Param("orderNum")Integer orderNum)throws Exception;
	
	
	/**
	 * Method name: getDownCategory <BR>
	 * Description: 获取下一个分类<BR>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public MallCourseCategoryBean getDownCategory(@Param("parentId")Integer parentId,@Param("orderNum")Integer orderNum)throws Exception;
	
	/**zhangbocheng end*/
}