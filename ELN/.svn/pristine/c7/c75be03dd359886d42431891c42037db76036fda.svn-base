/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyExcelHelp.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-9-2        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.util;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @author JFTT)chenrui
 * class name:MyExcelHelp <BR>
 * class description:  <BR>
 * Remark: <BR>
 * @version 1.00 2015-9-2
 * @author JFTT)chenrui
 */
public class MyExcelHelp {
	
	public final static String XLS = "xls";

	public final static String XLSX = "xlsx";
	// 分隔符
	private final static String SEPARATOR = "|";

	public final static String DATE_FORMAT = "yyyy-mm-dd hh:mm:ss";

	public final static String MONEY_FORMAT = "###,##0,00";
	/**
	 * 设置单元格样式
	 */
	public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, HSSFFont font, short align) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(align);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		return style;
	}

	public static HSSFCellStyle createWrapCellStyle(HSSFWorkbook workbook, HSSFFont font, short align, boolean wrapped) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(align);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setWrapText(wrapped);// 设置单元格自动换行
		return style;
	}

	public static HSSFCellStyle createFormatCellStyle(HSSFWorkbook workbook, HSSFFont font, short align, HSSFDataFormat dataFormat, String format) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(align);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setDataFormat(dataFormat.getFormat(format));
		return style;
	}

	// 创建普通字符串单元格
	public static void createStringCell(HSSFRow row, int column, HSSFCellStyle cellStyle, String value) {
		HSSFCell cell = row.createCell(column);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(new HSSFRichTextString(value));
	}

	// 创建数字类型单元格
	public static void createNumberCell(HSSFRow row, int column, HSSFCellStyle cellStyle, double value) {
		HSSFCell cell = row.createCell(column);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(value);
	}

	// 创建日期类型单元格
	public static void createDateCell(HSSFRow row, int column, HSSFCellStyle cellStyle, Date value) {
		HSSFCell cell = row.createCell(column);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(value);
	}
}
