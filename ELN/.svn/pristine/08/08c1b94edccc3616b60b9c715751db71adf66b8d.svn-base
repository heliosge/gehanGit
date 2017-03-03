package com.jftt.wifi.util;



import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.commons.beanutils.PropertyUtils;  
public class ExcelExportUtils {
	
      
	    public static Workbook createMapExcel(List<Map<String,Object>> list,List<String> title_list,List<String> value_list) {

	        String newdate = CommonUtil.getCurrentDatetime();
	        //创建一个EXCEL
	        XSSFWorkbook wb = new XSSFWorkbook();
	        //创建一个SHEET
	        Sheet sheet1 = wb.createSheet(newdate);
	        Font font = wb.createFont();
	        font.setBoldweight((short)12);
	        font.setFontHeight((short)12);
	        font.setFontHeightInPoints((short)12);
	        CellStyle cellStyle = wb.createCellStyle();
	        cellStyle.setFont(font);
	        //创建一行
	        Row row = sheet1.createRow((short)0);

	        for(int m=0;m<title_list.size();m++){
	            //填充标题
	            Cell cell = row.createCell(m);
	            cell.setCellValue(title_list.get(m));
	            cell.setCellStyle(cellStyle);}
	        for (int n=0;n<list.size();n++){
	            Map<String,Object> map = list.get(n);
	            Row row1 = sheet1.createRow((short)n+1);
	            for (int j=0 ;j<value_list.size();j++){
	                Cell cell1 = row1.createCell(j);//   编号
	                cell1.setCellStyle(cellStyle);
	                Object value =map.get(value_list.get(j));
	                String valueStr ="";
	                if(value!=null){
	                    valueStr=value.toString();
	                }
	                cell1.setCellValue(valueStr);
	            }
	        }

	       
	        return wb;
	    }
	    
	    
	    
	    public static <T> Workbook createObjectExcel(List<T> list,List<String> title_list,List<String> value_list) {

	        String newdate = CommonUtil.getDateString(new Date());
	        //创建一个EXCEL
	        XSSFWorkbook wb = new XSSFWorkbook();
	        //创建一个SHEET
	        Sheet sheet1 = wb.createSheet(newdate);
	        Font font = wb.createFont();
	        font.setBoldweight((short)12);
	        font.setFontHeight((short)12);
	        font.setFontHeightInPoints((short)12);
	        CellStyle cellStyle = wb.createCellStyle();
	        cellStyle.setFont(font);
	        //创建一行
	        Row row = sheet1.createRow((short)0);

	        for(int m=0;m<title_list.size();m++){
	            //填充标题
	            Cell cell = row.createCell(m);
	            cell.setCellValue(title_list.get(m));
	            cell.setCellStyle(cellStyle);
	            }
	        
	        try{
	        	
	        
	        
	          int n =0;
			  for(T obj:list){ 
				  Row row1 = sheet1.createRow((short)n+1);
				  Object value = null;
				   for(int i=0;i<value_list.size();i++){
					         
			        		  Cell cell1 = row1.createCell(i);//   编号
				              cell1.setCellStyle(cellStyle);
				              value = PropertyUtils.getProperty(obj, value_list.get(i));
				              if(value==null){
				            	  value=""; 
				              }
			        		  cell1.setCellValue(value.toString());
			        		 // System.out.println(value_list.get(i)+"  :"+value.toString());
			        		 
			           }
				  
			       n++;
			   } 
	        }catch(Exception e){
	        	e.printStackTrace();
	        	return null;
	        }
	       
	        return wb;
	    }
	
	
	
	
}
