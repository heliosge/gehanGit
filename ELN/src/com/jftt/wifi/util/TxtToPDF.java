package com.jftt.wifi.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class TxtToPDF {
	
	public static void main(String[] args) throws DocumentException, IOException {
		if (args.length != 3 ) {
			System.exit(1);
		}
		String fontPath = args[0];
		String srcFile = args[1];
		String destFile = args[2];
		txtToPdf(fontPath,srcFile, destFile);
	}
	
	// modified by xujingyu 2014.03.25
	private static Log log = LogFactory.getLog(TxtToPDF.class);
	
	
	
	public static void txtToPdf(String fontPath, String filePath, String pdfPath) throws DocumentException, IOException {
		log.debug("TxtToPDF.txtToPdf begin");
		
		
		File inputFile = new File(filePath);
		// 如果目标路径不存在, 则新建该路径
		File outputFile = new File(pdfPath);
		if (!outputFile.getParentFile().exists()) {
			outputFile.getParentFile().mkdirs();
		}
		// 判断pdf存放路径是否存在,不存在就新建
		/*File file = new File(pdfDir);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}*/
		// 创建一个Document对象
	    Document document = new Document();
	    // 生成pdf 的文档
	    PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
	    /**  新建一个字体,iText的方法
	     *  STSongStd-Light 是字体，在iTextAsian.jar 中以property为后缀
	     *  UniGB-UCS2-H   是编码，在iTextAsian.jar 中以cmap为后缀
	     *  H 代表文字版式是 横版， 相应的 V 代表 竖版
	     */
	    //BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
	    //BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	    //BaseFont bfChinese = BaseFont.createFont("/usr/local/xpdf-chinese-simplified/CMap/gkai00mp.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
	    BaseFont bfChinese = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
	    
	    // 设置字体
	    Font fontChinese = new Font(bfChinese, 10, Font.NORMAL); 
	    // 打开文档，将要写入内容
	    document.open();
	    // 读取txt内容
	    String content = readTxt(filePath);

	    // 插入一个段落
	    Paragraph par = new Paragraph(content,fontChinese);
	    document.add(par);
	   
	    // 关闭打开的文档
	    document.close();
	    log.debug("TxtToPDF.txtToPdf end");
	 }
	
	 /**
	  * method name: readTxt 
	  * @param path 上传文件后文件存放在服务器上完整路径,包含文件名称
	  * @return String 返回该文件的文本内容
	 * @throws IOException 
	  * @throws Exception 
	  */
	 public static String readTxt(String path) throws IOException { 
		 File file = new File(path);
		 //判断文件是否存在或者是否是文件
		 if(!file.exists() || !file.isFile()){
			 throw new IOException("文件不存在或者不是文件");		 
		 }	
		 String code = codeString(file);
		 //String code2 = CharacterEnding.getFileCharacterEnding(file);
	     StringBuffer content = new StringBuffer("");// 记录读取的文档内容 
	     FileInputStream inputFile = new FileInputStream(file);
	     byte a[] = new byte[inputFile.available()];
	     if(inputFile.read(a) != -1){       
	    	 inputFile.read(a);
	    	 content.append(new String(a, code) + "\n");  
	     }
	     inputFile.close();
	     return content.toString().trim(); 
	 }
	 
	 /**
	 * Method name: codeString <BR>
	 * Description: 获取文档内容编码 <BR>
	 * Remark: <BR>
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws Exception  String<BR>
	 */
	public static String codeString(File file) throws IOException {
		 BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));  
		 int p = (bin.read() << 8) + bin.read();  
		 String code = null;  
		 switch (p) {
		   case 0xefbb:  
		   code = "UTF-8";  
		   break;  
		   case 0xfffe:  
			   code = "Unicode";  
			   break;  
		   case 0xfeff:  
			   code = "UTF-16BE";  
			   break;  
		   default:  
			   code = "GBK";  
		 }  
		 	return code;  
		 }  
}
