
package com.jftt.wifi.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.OfflineTestBean;
import com.jftt.wifi.bean.OfflineTestResultsBean;
import com.jftt.wifi.dao.OfflineTestDaoMapper;
import com.jftt.wifi.dao.OfflineTestResultsDaoMapper;
import com.jftt.wifi.service.OfflineTestService;

/**
 * 线下考试
 */
@Service("offlineTestService")
public class OfflineTestServiceImpl implements OfflineTestService{
	
	@Resource(name="offlineTestDaoMapper")
	private OfflineTestDaoMapper offlineTestDaoMapper;
	
	@Resource(name="offlineTestResultsDaoMapper")
	private OfflineTestResultsDaoMapper offlineTestResultsDaoMapper;
	
	/**
	 * 增加
	 */
	@Transactional
	public void addOfflineTest(OfflineTestBean offlineTest){
		
		//线下考试
		offlineTest.setIs_delete(0);
		offlineTestDaoMapper.addOfflineTest(offlineTest);
		
		if(offlineTest.getResultList() !=null && !offlineTest.getResultList().isEmpty()){
			for (OfflineTestResultsBean result : offlineTest.getResultList()) {
				
				//线下考试的学员成绩
				result.setOffline_test_id(offlineTest.getId());
				result.setIs_delete(0);
				
				offlineTestResultsDaoMapper.addOfflineTestResults(result);
			}
		}
	}

	/**
	 * 修改
	 */
	@Transactional
	public void updateOfflineTest(OfflineTestBean offlineTest){
		
		//线下考试
		offlineTestDaoMapper.updateOfflineTest(offlineTest);
		
		//删除原来线下考试的学员
		offlineTestResultsDaoMapper.deleteOfflineTestResultsByTestId(offlineTest.getId());
		
		if(offlineTest.getResultList() !=null && !offlineTest.getResultList().isEmpty()){
			for (OfflineTestResultsBean result : offlineTest.getResultList()) {
				
				//线下考试的学员成绩
				result.setOffline_test_id(offlineTest.getId());
				result.setIs_delete(0);
				
				offlineTestResultsDaoMapper.addOfflineTestResults(result);
			}
		}
	}
	
	/**
	 * 删除
	 */
	@Transactional
	public void deleteOfflineTest(long[] ids){
		
		for (long id : ids) {
			
			deleteOfflineTest(id);
		}
	}
	
	/**
	 * 删除
	 */
	@Transactional
	public void deleteOfflineTest(long id){
		
		OfflineTestBean offlineTest = new OfflineTestBean();
		offlineTest.setId(id);
		offlineTest.setIs_delete(1);
		
		offlineTestDaoMapper.updateOfflineTest(offlineTest);
	}
	
	/**
	 * 根据条件 获得数量
	 */
	@Transactional
	public int getOfflineTestNum(OfflineTestBean offlineTest){
		
		return offlineTestDaoMapper.getOfflineTestNum(offlineTest);
	}
	
	/**
	 * 根据条件 获得数据
	 */
	@Transactional
	public List<OfflineTestBean> getOfflineTest(OfflineTestBean offlineTest){
		
		return offlineTestDaoMapper.getOfflineTest(offlineTest);
	}
	
	/**
	 * 根据条件 获得数据
	 */
	@Transactional
	public OfflineTestBean getOfflineTestById(long id){
		
		OfflineTestBean offlineTest = new OfflineTestBean();
		offlineTest.setId(id);
		offlineTest.setIs_delete(0);
		
		List<OfflineTestBean> list = offlineTestDaoMapper.getOfflineTest(offlineTest);
		
		if(list !=null && !list.isEmpty()){
			return list.get(0);
		}
		
		return null;
	}
	
	/**
	 * excel 导入
	 * @throws FileNotFoundException 
	 */
	@Transactional
	public void addByExcel(String filePath, int subCompanyId) throws Exception{
		
		OfflineTestBean offlineTest = new OfflineTestBean();
		//分公司
		offlineTest.setSub_company_id(subCompanyId);
		
		InputStream inp = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(inp); 
		
		Sheet sheet = wb.getSheetAt(0);  
	    if(sheet.getRow(1) != null){  
	    	//在线考试基本信息
	    	Row testRow = sheet.getRow(1);
	    	
	    	//考试名称
	    	offlineTest.setName(getCellValue(testRow.getCell(0), 30));
	    	//考试开始时间
	    	offlineTest.setStart_time(getCellValue(testRow.getCell(2), null));
	    	//考试结束时间
	    	offlineTest.setEnd_time(getCellValue(testRow.getCell(3), null));
	    	//考试总分
	    	offlineTest.setTotal_score(Integer.parseInt(getCellValue(testRow.getCell(4), null)));
	    	//考试及格分数
	    	offlineTest.setPass_score(Integer.parseInt(getCellValue(testRow.getCell(5), null)));
	    } 
	    
	    //参加考试人员成绩
	    int maxRow = sheet.getLastRowNum();
	    if(maxRow >= 5){
	    	
	    	List<OfflineTestResultsBean> resultList = new ArrayList<OfflineTestResultsBean>();
	    	
	    	for(int i=5; i<=maxRow; i++){
	    		
	    		Row testRow = sheet.getRow(i);
	    		
	    		OfflineTestResultsBean result = new OfflineTestResultsBean();
	    		
	    		if(testRow.getCell(1) == null){
	    			break;
	    		}
		    	
		    	//姓名
	    		result.setName(getCellValue(testRow.getCell(1), 30));
		    	//邮箱
	    		result.setEmail(getCellValue(testRow.getCell(2), 30));
	    		//电话
	    		result.setPhone(getCellValue(testRow.getCell(3), 30));
	    		//岗位
	    		result.setJob(getCellValue(testRow.getCell(4), 30));
	    		//部门
	    		result.setDepartment(getCellValue(testRow.getCell(5), 30));
	    		//成绩
	    		result.setScore(getCellValue(testRow.getCell(6), 30));
	    		
	    		resultList.add(result);
	    	}
	    	
	    	offlineTest.setResultList(resultList);
	    }
	    
	    //增加到数据库
	    addOfflineTest(offlineTest); 
		
	}
	
	/**
	 * excel 单元格 类型判断
	 */
	private String getCellValue(Cell cell, Integer len) {  
		
        String cellValue = "";  
        DecimalFormat df = new DecimalFormat("0");  
        
        switch (cell.getCellType()) {  
	        case Cell.CELL_TYPE_STRING:  //string
	            cellValue = cell.getRichStringCellValue().getString().trim();  
	            break;  
	        case Cell.CELL_TYPE_NUMERIC:  //数字
	            cellValue = df.format(cell.getNumericCellValue()).toString();  
	            break;  
	        case Cell.CELL_TYPE_BOOLEAN:  
	            cellValue = String.valueOf(cell.getBooleanCellValue()).trim();  
	            break;  
	        case Cell.CELL_TYPE_FORMULA:  
	            cellValue = cell.getCellFormula();  
	            break;  
	        default:  
	        	cellValue = "";  
        }  
        
        if(len != null){
        	return getStringByLen(cellValue, len);
        }
        
        return cellValue;  
    }  
	
	/**
	 * 导出
	 */
	@Transactional
	public Workbook exportOfflineTest(OfflineTestBean testBean){
		
		//创建excel
		XSSFWorkbook wb = new XSSFWorkbook();  
        XSSFSheet sheet = wb.createSheet(testBean.getName());
        
        //考试信息
        //表头
      	String[] columns = {"考试名称", "考试开始时间", "考试结束时间", "总分", "及格分数"};
      	
      	//表头
        XSSFRow row0 = sheet.createRow(0); 
        row0.setHeight((short) 500);
        
        //样式
        CellStyle cellColumnStyle = getColumnCellStyle(wb);
        
        //表头
        for (int i=0; i<columns.length; i++) {
        		
        	 if(i == 0){
        		 //考试名称  用2个格子
            	 XSSFCell cell0 = row0.createCell(i);
           	 	 cell0.setCellValue(columns[i]);
           	 	 CellRangeAddress region = new CellRangeAddress(0, 0, 0, 1);
           	 	 sheet.addMergedRegion(region);
                 cell0.setCellStyle(cellColumnStyle);
                 
                 RegionUtil.setBorderBottom(1, region, sheet, wb);  
                 RegionUtil.setBorderLeft(1, region, sheet, wb);  
                 RegionUtil.setBorderRight(1, region, sheet, wb);  
                 RegionUtil.setBorderTop(1, region, sheet, wb);  
                 
        	 }else{
        		 XSSFCell cell = row0.createCell(i+1);
            	 cell.setCellValue(columns[i]);
                 cell.setCellStyle(cellColumnStyle); 
        	 }
		}
        
        //内容
        XSSFRow row1 = sheet.createRow(1); 
        row1.setHeight((short) 500);
        
        //样式
        CellStyle cellStyle = getDataCellStyle(wb);
        
        if(testBean !=null){
        	//考试名称  用2个格子
        	XSSFCell cell0 = row1.createCell(0);
       	 	cell0.setCellValue(testBean.getName());
       	 	CellRangeAddress region = new CellRangeAddress(1, 1, 0, 1);
       	 	sheet.addMergedRegion(region);
            cell0.setCellStyle(cellStyle);
            
            RegionUtil.setBorderBottom(1, region, sheet, wb);  
            RegionUtil.setBorderLeft(1, region, sheet, wb);  
            RegionUtil.setBorderRight(1, region, sheet, wb);  
            RegionUtil.setBorderTop(1, region, sheet, wb);  
            
            //考试开始时间
            XSSFCell cell2 = row1.createCell(2);
       	 	cell2.setCellValue(testBean.getStart_time().substring(0, 19));
            cell2.setCellStyle(cellStyle);
            
            //考试结束时间
            XSSFCell cell3 = row1.createCell(3);
       	 	cell3.setCellValue(testBean.getEnd_time().substring(0, 19));
            cell3.setCellStyle(cellStyle);
            
            //总分
            XSSFCell cell4 = row1.createCell(4);
       	 	cell4.setCellValue(testBean.getTotal_score());
            cell4.setCellStyle(cellStyle);
            
            //及格分数
            XSSFCell cell5 = row1.createCell(5);
       	 	cell5.setCellValue(testBean.getPass_score());
            cell5.setCellStyle(cellStyle);
        }
        
        //参加考试人员成绩
        String[] userColumns = {"序号", "姓名", "邮箱", "联系电话", "岗位", "部门", "成绩", "是否通过"};
        
        XSSFRow row3 = sheet.createRow(3); 
        row3.setHeight((short) 500);
        
        //标题
        XSSFCell cell30 = row3.createCell(0);
        cell30.setCellValue("参加考试人员成绩");
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, userColumns.length-1));
        cell30.setCellStyle(getColumnCellStyleNoBorder(wb));
        
        //表头
        XSSFRow row4 = sheet.createRow(4); 
        row4.setHeight((short) 500);
        for (int i=0; i<userColumns.length; i++) {
			
       	 	XSSFCell cell = row4.createCell(i);
       	 	cell.setCellValue(userColumns[i]);
            cell.setCellStyle(cellColumnStyle);
		}
        
        //数据
        OfflineTestResultsBean offlineTestResults = new OfflineTestResultsBean();
		offlineTestResults.setOffline_test_id(testBean.getId());
		offlineTestResults.setIs_delete(0);
		
		List<OfflineTestResultsBean> resultList = offlineTestResultsDaoMapper.getOfflineTestResults(offlineTestResults);
        
        if(resultList !=null && !resultList.isEmpty()){
        	for(int i=0; i<resultList.size(); i++){
        		OfflineTestResultsBean user = resultList.get(i);
				
				XSSFRow row = sheet.createRow(i+5); 
                row.setHeight((short) 500);
				
                //序号
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i+1);
				cell0.setCellStyle(cellStyle);
				
				//{"序号", "姓名", "邮箱", "联系电话", "岗位", "部门", "成绩", "是否通过"};
				
				//姓名
				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(user.getName());
				cell1.setCellStyle(cellStyle);
				
				//邮箱
				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(user.getEmail());
				cell2.setCellStyle(cellStyle);
				
				//联系电话
				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(user.getPhone());
				cell3.setCellStyle(cellStyle);
				
				//岗位
				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(user.getJob());
				cell4.setCellStyle(cellStyle);
				
				//部门
				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(user.getDepartment());
				cell5.setCellStyle(cellStyle);
				
				//成绩
				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(user.getScore());
				cell6.setCellStyle(cellStyle);
				
				//是否通过
				XSSFCell cell7 = row.createCell(7);
				if(Integer.parseInt(user.getScore()) >= testBean.getPass_score()){
					cell7.setCellValue("是");
				}else{
					cell7.setCellValue("否");
				}
				cell7.setCellStyle(cellStyle);
        	}
        }
        
        //列宽调整
        for (int i=0; i<userColumns.length; i++) {
        	//列宽
         	sheet.autoSizeColumn((short)i, true); //调整宽度
		}
        return wb;
	}
	
	/**
	 * 获得表头的样式
	 */
	private CellStyle getColumnCellStyle(Workbook wb){
		
		CellStyle cellStyle = wb.createCellStyle();
		
        Font cellFont = wb.createFont();  
        cellFont.setFontName("宋体");
        cellFont.setFontHeightInPoints((short) 11);  //字号  
        cellFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        
        cellStyle.setFont(cellFont);
        //水平居中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        //边框
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);//右边框
        
        return cellStyle;
	}
	
	/**
	 * 获得表头的样式
	 */
	private CellStyle getColumnCellStyleNoBorder(Workbook wb){
		
		CellStyle cellStyle = wb.createCellStyle();
		
        Font cellFont = wb.createFont();  
        cellFont.setFontName("宋体");
        cellFont.setFontHeightInPoints((short) 11);  //字号  
        cellFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        
        cellStyle.setFont(cellFont);
        //水平居中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        
        return cellStyle;
	}
	
	/**
	 * 获得数据的样式
	 */
	private CellStyle getDataCellStyle(Workbook wb){
		
		CellStyle cellStyle = wb.createCellStyle();
		
        Font cellFont = wb.createFont();  
        cellFont.setFontName("宋体");
        cellFont.setFontHeightInPoints((short) 11);  //字号  
        
        cellStyle.setFont(cellFont);
        //水平居中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        //边框
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);//右边框
        
        return cellStyle;
	}
	
	/**
	 * 截取字符串
	 */
	private String getStringByLen(String str, int len){
		
		if(str !=null && !str.equals("")){
			if(str.length() > len){
				return str.substring(0, len);
			}
			
			return str;
		}
        
        return "";
	}
}
