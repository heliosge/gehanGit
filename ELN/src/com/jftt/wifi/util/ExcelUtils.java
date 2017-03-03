package com.jftt.wifi.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 功能说明
 *     Excel导入导出时所需相关操作方法或转换方法兼容2003、2007
 */
public class ExcelUtils {
    //日期格式化模板
    private static final DateFormat DATE_FORMAT_POINT = new SimpleDateFormat("yyyy.MM.dd");
    private static final DateFormat DATE_FORMAT_NUMERIC = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
    //十进制数格式化模板
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#");

    private static final int TEXT_CELL_FORMAT = 49;
    private static final int OPENOFFICE_TEXT_CELL_FORMAT = 165;
    private static final int OPENOFFICE_DATE_CELL_FORMAT = 167;

    /**
     * 判断单元格中数据是否为文本格式
     *
     * @param cell
     * @return
     */
    private static boolean isTextFormat(Cell cell) {
        //返回源代码中给出的底层类的简称
        String clazz = cell.getClass().getSimpleName();
        short format = cell.getCellStyle().getDataFormat();
        return ((TEXT_CELL_FORMAT == format) || (OPENOFFICE_TEXT_CELL_FORMAT == format));
    }

    /**
     * 判断单元格中数据是否为日期格式
     *
     * @param cell
     * @return
     */
    private static boolean isDateFormat(Cell cell) {
        short format = cell.getCellStyle().getDataFormat();
        return (OPENOFFICE_DATE_CELL_FORMAT == format);
    }

    /**
     * 数字转换成String
     *
     * @param cell
     * @return
     */
    private static String numericToString (Cell cell) {
        //取得单元格中数字数据
        double numericValue = cell.getNumericCellValue();

        if (Double.isNaN(numericValue)) {
            return "";
        }

        if (isDateFormat(cell)) {
            return dateFormat.format(cell.getDateCellValue());
        }

        if (isTextFormat(cell)  && (((int) numericValue) == numericValue)) {
            return Integer.toString((int) numericValue);
        } else {
            return Integer.toString((int) numericValue);
        }
    }

    /**
     * 把公式转换成String
     *
     * @param cell
     * @return
     */
    private static String formulaToString (Cell cell) {
        if (isTextFormat(cell)) {
            return cell.getRichStringCellValue().getString();
        } else {
            return numericToString(cell);
        }
    }

    /**
     *  Excel表格中单元格中数据转换成String类型
     * @param cell
     * @return
     * @throws java.io.IOException
     */
    public static String getStringValue(Cell cell) throws IOException {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BLANK :       //空白类型
                return  "";
            case Cell.CELL_TYPE_ERROR :       //出错类型
                return  "Error<" + cell.getErrorCellValue() + ">";
            case Cell.CELL_TYPE_BOOLEAN :    //布尔类型
                return  cell.getBooleanCellValue()  ? "VRAI" : "FAUX";
            case Cell.CELL_TYPE_FORMULA :    //公式类型
                return formulaToString(cell);
            case Cell.CELL_TYPE_NUMERIC :   //数字类型
                return numericToString (cell);
            case Cell.CELL_TYPE_STRING :   //字符串类型

            default:
                return cell.getRichStringCellValue().getString();
        }
    }

    /**
     * Excel表格中单元格中数据转换成日期类型
     * @param cell
     * @return
     * @throws java.io.IOException
     */
    public static Date getDateValue(Cell cell) throws IOException {
        if (cell == null) {
            return  null;
        }

        try {
            if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                if (DateUtil.isCellDateFormatted( cell) ) {   // 解析 yyyy-mm-dd格式
                    return cell.getDateCellValue();
                } else {  // 解析 yyyymmdd格式
                    double numeric = cell.getNumericCellValue();
                    String  dateStr = DECIMAL_FORMAT.format(numeric);
                    if (dateStr!=null||dateStr.length()>0) {
                        return DATE_FORMAT_NUMERIC.parse(dateStr);
                    } else {
                        return null;
                    }
                }
            } else { // 解析 yyyy.mm.dd格式
                String dateStr = getStringValue(cell);
                if (dateStr!=null||dateStr.length()>0) {
                    return DATE_FORMAT_POINT.parse(dateStr);
                } else {
                    return null;
                }
            }

        } catch (ParseException e) {
            return null;
        }
    }

    /**
     *  Excel表格中单元格中数据转换成浮点数类型
     *
     * @param cell
     * @return
     * @throws java.io.IOException
     */
    public static double getDoubleValue(Cell cell) throws IOException {
        if (cell == null) {
            return 0;
        }

        String doubleValue = getStringValue(cell);
        return Double.parseDouble(doubleValue);
    }

    /**
     * Excel表格中单元格中数据转换成整数类型
     *
     * @param cell
     * @return
     * @throws java.io.IOException
     */
    public static int getIntValue(Cell cell) throws IOException {
        if (cell == null) {
            return 0;
        }

        String intValue = getStringValue(cell);
        return Integer.parseInt(intValue);
    }

    /**
     * 取得模板
     * @param is
     * @param flag - true为2003版本，反之2007
     * @return
     * @throws Exception
     */
    public static Workbook getTemplate(InputStream is, boolean flag) throws Exception{
        POIFSFileSystem fs = null;
        Workbook wb = null;
        Sheet sheet = null;

        try {
            if (flag) {
                fs = new POIFSFileSystem(is);
                if (fs == null) {
                    throw new Exception("POIFSFileSystem is null.");
                }

                wb = new HSSFWorkbook(fs);
            } else {
                wb = new XSSFWorkbook(is);
            }

            if (wb == null) {
                throw new Exception("Workbook is null.");
            }
            sheet = wb.getSheetAt(0);
            if (sheet == null) {
                throw new Exception("Sheet is null.");
            }
        } catch (IOException e) {
            throw new Exception("Can't get the template. ", e);
        }
        return wb;

    }

    public static Workbook getTemplate(File file, boolean flag) throws Exception{
        POIFSFileSystem fs = null;
        Workbook wb = null;
        Sheet sheet = null;

        try {
            FileInputStream is = new FileInputStream(file);
            if (flag) {
                fs = new POIFSFileSystem(is);
                if (fs == null) {
                    throw new Exception("POIFSFileSystem is null.");
                }

                wb = new HSSFWorkbook(fs);
            } else {
                wb = new XSSFWorkbook(is);
            }

            if (wb == null) {
                throw new Exception("Workbook is null.");
            }
            sheet = wb.getSheetAt(0);
            if (sheet == null) {
                throw new Exception("Sheet is null.");
            }
        } catch (IOException e) {
            throw new Exception("Can't get the template. ", e);
        }
        return wb;

    }

    /**
     * 从资源文件中取得Excel模板
     *
     * @param resourceFile
     * @return
     * @throws  Exception
     */
    public static Workbook getTemplate(String resourceFile) throws Exception {
        return getTemplate(ExcelUtils.class.getResourceAsStream(resourceFile), true);
    }


    /**
     *  判断某个字符串是否存在于数组中
     *  @param stringArray 原数组
     *  @param source 查找的字符串
     *  @return 是否找到
     */
    public static boolean contains(String[] stringArray, String source) {
        // 转换为list
        List<String> tempList = Arrays.asList(stringArray);
        // 利用list的包含方法,进行判断
        if(tempList.contains(source)) {
            return true;
        } else {
            return false;
        }
    }
}
