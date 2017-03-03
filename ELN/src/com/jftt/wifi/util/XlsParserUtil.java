/**
 * All rights Reserved, Copyright (C) 2014<BR>
 * 
 * FileName: CsvParserUtil.java <BR>
 * Version: $Id: CsvParserUtil.java, v 1.00 2014-6-23  <BR>
 * Modify record: <BR>
 * NO. |     Date         |    Name                 |      Content <BR>
 * 1   |     2014-6-23      | JFTT)Cheng yingqi       | original version <BR>
 */
package com.jftt.wifi.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 * class name:解析xml
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月29日
 * @author JFTT)liucaibao
 */
public class XlsParserUtil {
/*	public static String csvToTxt(String path,String unicode,int startRowNum,int endRowNum,Map<Integer,Integer> useColumn ) throws IOException{
		FileInputStream fr = new FileInputStream(path);
		CSVReader csvReader = new CSVReader(new InputStreamReader(fr, unicode));
		File outPutFile = new File(path+".txt");
		if(outPutFile.exists()){
			FileUtil.deleteFile(outPutFile);
		}
		FileOutputStream fos = new FileOutputStream(outPutFile, true);
		BufferedOutputStream Buff=new BufferedOutputStream(fos);   
		// first row is title, so past
		String[] csvRow = null;// row
		int count = 0;
		if(endRowNum == 0){
			endRowNum = Integer.MAX_VALUE;
		}
		while ((csvRow = csvReader.readNext()) != null) {
			if(count>=startRowNum-1 && count <= endRowNum-1){
				for (int i = 0; i < csvRow.length; i++) {
					if(useColumn.get(i)==null){
						continue;
					}
					Buff.write(csvRow[i].getBytes(unicode));
					Buff.write("\t".getBytes());
				}
				Buff.write("\n".getBytes());
			}
			count++;
			if (count >= endRowNum) {
				break;
			}
		}
		Buff.flush();
		Buff.close();
		FileUtil.deleteFile(path);
		return path+".txt";
	}*/
	public static String xlsToTxt(String path,String unicode,int startRowNum,int endRowNum,Map<Integer,Integer> useColumn) throws IOException{
		FileInputStream fr = new FileInputStream(path);
		File outPutFile = new File(path+".txt");
		if(outPutFile.exists()){
			FileUtil.deleteFile(outPutFile);
		}
		FileOutputStream fos = new FileOutputStream(outPutFile, true);
		BufferedOutputStream Buff=new BufferedOutputStream(fos);   
		int count = 0;
		if(endRowNum == 0){
			endRowNum = Integer.MAX_VALUE;
		}
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fr);
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets() && numSheet==0; numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}

			// 循环行Row
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				if(count>=startRowNum-1 && count <= endRowNum-1){
					// 循环列Cell
					for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
						if(useColumn.get(cellNum)==null){
							continue;
						}
						HSSFCell hssfCell = hssfRow.getCell(cellNum);
						if (hssfCell == null) {
							Buff.write("".getBytes(unicode));
						}else{
							Buff.write(getValue(hssfCell).getBytes(unicode));
						}
						Buff.write("\t".getBytes());
					}
					Buff.write("\n".getBytes());
				}
				count++;
				if (count >= endRowNum) {
					break;
				}
			}
		}
		Buff.flush();
		Buff.close();
		FileUtil.deleteFile(path);
		return path+".txt";
	}
	public static String xlsxToTxt(String path,String unicode,int startRowNum,int endRowNum,Map<Integer,Integer> useColumn) throws IOException{
		FileInputStream fr = new FileInputStream(path);
		File outPutFile = new File(path+".txt");
		FileUtil.deleteFile(outPutFile);
		FileOutputStream fos = new FileOutputStream(outPutFile, true);
		BufferedOutputStream Buff=new BufferedOutputStream(fos);   
		if(endRowNum == 0){
			endRowNum = Integer.MAX_VALUE;
		}
		// first row is title, so past
		XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(fr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (wb != null && wb.getNumberOfSheets() > 0) {
            try {
                // get first sheet object
                XSSFSheet sheet = wb.getSheetAt(0);
                // get current Workbook rows
                int rowNum = sheet.getLastRowNum();
                for (int i = 0;i < rowNum;i++) {
                    // get current row cells
                	if(i>=startRowNum-1 && i <= endRowNum-1){
	                    XSSFRow row = sheet.getRow(i);
	                    int cellNum = row.getLastCellNum();
	                    if (cellNum > 0) {
	                        for (int j = 0;j < cellNum;j++) {
	                        	if(useColumn.get(j)==null){
	    							continue;
	    						}
	                            XSSFCell cell = row.getCell(j);
	                            Buff.write(getValue(cell).getBytes(unicode));
	        					Buff.write("\t".getBytes());
	                        }
	                    } else {
	                        throw new RuntimeException("No cells found in excel .");
	                    }
	                    Buff.write("\n".getBytes());
                	}
                    if (i >= endRowNum) {
    					break;
    				}
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Please select a valided excel file !");
        }
		Buff.flush();
		Buff.close();
		FileUtil.deleteFile(path);
		return path+".txt";
	}
	// csv数据解析方法
	/*public static JSONArray importCsvParser(String path, String unicode, int csvHeadRowNum,int startNum,int endNum) throws IOException {
		FileInputStream fr = new FileInputStream(path);
		CSVReader csvReader = new CSVReader(new InputStreamReader(fr, unicode));
		// 看有没有标题列
		String[] csvRow = null;// row
		JSONArray result = new JSONArray();
		//先把标题行留着
		result.add(new JSONArray());
		//这个是计数，统计数据行数
		int count = 0;
		if(endNum == 0){
			endNum = Integer.MAX_VALUE;
		}
		while ((csvRow = csvReader.readNext()) != null) {
			JSONArray rowJsonArray = new JSONArray();
			for (int i = 0; i < csvRow.length; i++) {
				if(csvHeadRowNum == count+1 && csvRow[i].equals("")){
					rowJsonArray.add("COL_"+i);
				}else{
					rowJsonArray.add(csvRow[i]);
				}
			}
			//循环第一行时，拼接一个临时标题
			if(csvHeadRowNum == 0 && count == 0){
				JSONArray headRowJsonArray = new JSONArray();
				for(int i = 0; i < csvRow.length; i++){
					headRowJsonArray.add("COL_"+i);
				}
				result.add(0, headRowJsonArray);
				if(count>=startNum-1 && count <= endNum-1){
					result.add(rowJsonArray);
				}
			}else if(csvHeadRowNum == count+1){
				result.add(0, rowJsonArray);
			}else{
				if(count>=startNum-1 && count <= endNum-1){
					result.add(rowJsonArray);
				}
			}
			count++;
			if (count >= endNum) {
				break;
			}
		}
		return result;
	}*/

	/**
	 * @throws IOException
	 * 
	 */
	public static JSONArray importXlsParser(String path, String unicode, int csvHeadRowNum,int startNum,int endNum) throws IOException {
		JSONArray result = new JSONArray();
		//先把标题行留着
		result.add(new JSONArray());
		int count = 0;
		if(endNum == 0){
			endNum = Integer.MAX_VALUE;
		}
		File file = new File(path);
		InputStream is = new FileInputStream(file);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets() && numSheet==0; numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}

			// 循环行Row
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				JSONArray rowJsonArray = new JSONArray();
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}

				// 循环列Cell
				for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
					HSSFCell hssfCell = hssfRow.getCell(cellNum);
					if(csvHeadRowNum == count+1 && getValue(hssfCell).equals("")){
						rowJsonArray.add("COL_"+cellNum);
					}else{
						rowJsonArray.add(getValue(hssfCell));
					}
				}
				//循环第一行时，拼接一个临时标题
				if(csvHeadRowNum == 0 && count == 0){
					JSONArray headRowJsonArray = new JSONArray();
					for(int i = 0; i < hssfRow.getLastCellNum(); i++){
						headRowJsonArray.add("COL_"+i);
					}
					result.add(0, headRowJsonArray);
					if(count>=startNum-1 && count <= endNum-1){
						result.add(rowJsonArray);
					}
				}else if(csvHeadRowNum == count+1){
					result.add(0, rowJsonArray);
				}else{
					if(count>=startNum-1 && count <= endNum-1){
						result.add(rowJsonArray);
					}
				}
				count++;
				if (count >= endNum) {
					break;
				}
			}
		}
		return result;
	}

	
	public static int  getXLSNum(String path,String fileType){
		File file = new File(path);
		
		if("xlsx".equals(fileType)){
			XSSFWorkbook wb = null;
			try {
				wb = new XSSFWorkbook(new FileInputStream(file));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (wb != null && wb.getNumberOfSheets() > 0) {
				try {
					// get first sheet object
					XSSFSheet sheet = wb.getSheetAt(0);
					// get current Workbook rows
					int rowNum = sheet.getLastRowNum();
					return rowNum;
				}
				catch(Exception e){
					System.out.println(e);
				}
				
			}
		}else {
			
			try {
				HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(file));
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
				
				int rowNum = hssfSheet.getLastRowNum();
				return rowNum;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return 0;
	}
	
	public static JSONArray importXlsxParser(String path, String unicode, int csvHeadRowNum,int startNum,int endNum) {
		JSONArray result = new JSONArray();
		//先把标题行留着
		result.add(new JSONArray());
		if(endNum == 0){
			endNum = Integer.MAX_VALUE;
		}
		File file = new File(path);
		XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (wb != null && wb.getNumberOfSheets() > 0) {
            try {
                // get first sheet object
                XSSFSheet sheet = wb.getSheetAt(0);
                // get current Workbook rows
                int rowNum = sheet.getLastRowNum();
                for (int i = 0;i < rowNum;i++) {
                	JSONArray rowJsonArray = new JSONArray();
                    // get current row cells
                    XSSFRow row = sheet.getRow(i);
                    int cellNum = row.getLastCellNum();
                    if (cellNum > 0) {
                        for (int j = 0;j < cellNum;j++) {
                            XSSFCell cell = row.getCell(j);
                            if(csvHeadRowNum == i+1 && getValue(cell).equals("")){
        						//rowJsonArray.add("COL_"+j);
        					}else{
        						rowJsonArray.add(getValue(cell));
        					}
                           
                        }
                      //循环第一行时，拼接一个临时标题
        				if(csvHeadRowNum == 0 && i == 0){
        					JSONArray headRowJsonArray = new JSONArray();
        					for(int j = 0; j < cellNum; j++){
        						//headRowJsonArray.add("COL_"+j);
        					}
        					result.add(0, headRowJsonArray);
        					if(i>=startNum-1 && i <= endNum-1){
        						result.add(rowJsonArray);
        					}
        				}else if(csvHeadRowNum == i+1){
        					result.add(0, rowJsonArray);
        				}else{
        					if(i>=startNum-1 && i <= endNum-1){
        						result.add(rowJsonArray);
        					}
        				}
                    } else {
                        throw new RuntimeException("No cells found in excel .");
                    }
                    
                    if (i+1 >= endNum) {
    					break;
    				}
                }
            } catch (Exception e) {
            	return result;
            }
        } else {
            return result;
        }
        return result;

	}
	
	private static String getValue(Cell cell) {
		if (null != cell) {
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
            	if(HSSFDateUtil.isCellDateFormatted(cell)){
    				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    				return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
    			}else{
    				// 是否为数值型
					double d = cell.getNumericCellValue();
					if (d - (int) d < Double.MIN_VALUE) {
						// 是否为int型
						return Integer.toString((int) d);
					} else {
						// 是否为double型
						DecimalFormat df = new DecimalFormat("########.#######");
						return df.format(cell.getNumericCellValue());
					}
				}
            case Cell.CELL_TYPE_STRING: // 字符串
            	return cell.getStringCellValue();
            case Cell.CELL_TYPE_BOOLEAN: // Boolean
            	return Boolean.toString(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_FORMULA: // 公式
            	return cell.getCellFormula();
            case Cell.CELL_TYPE_BLANK: // 空值
            	return "";
            case Cell.CELL_TYPE_ERROR: // 故障
            	return "";
            default:
            	return "";
            }
        } else {
        	return "";
        }
	}
	}
