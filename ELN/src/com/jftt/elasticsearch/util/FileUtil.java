/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: HtmlUtil.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-8        | JFTT)zhangchen    | original version
 */
package com.jftt.elasticsearch.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.poi.POITextExtractor;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xslf.XSLFSlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * 
 */
public class FileUtil {  
	
	
	public static String readTxtFile(String filePath, String encoding){
        try {
        	
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ 		//判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);    //考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                
                String back = "";
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                	back += lineTxt + "\n";
                }
                read.close();
                
                return back;
	        }else{
	            //System.out.println("找不到指定的文件");
	        }
        } catch (Exception e) {
            //System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
     
        return "";
	}
	
	public static String readWord(String filePath){
		
		try {
			
			if(filePath.endsWith("doc") || filePath.endsWith("DOC")){
				//2003
				InputStream is = new FileInputStream(new File(filePath));
				WordExtractor ex = new WordExtractor(is);
				String text2003 = ex.getText();
				
				System.out.println(text2003);
				return text2003;
				
			}else if(filePath.endsWith("docx") || filePath.endsWith("DOCX")){
				
				OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
				POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
				String text2007 = extractor.getText();
				
				System.out.println(text2007);
				return text2007;
			}
			
		} catch (Exception e) {
            //System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
		
		return "";
	}
	
	public static String readAll(String filePath){
		
		try {
			
			/*if(filePath.endsWith("ppt") || filePath.endsWith("PPT")){
				//2003
				SlideShow ppt = new SlideShow(new HSLFSlideShow(filePath));
				
				Slide[] slides = ppt.getSlides();
				
				//提取文本信息   
				for (Slide each : slides) {
					System.out.println("each  " + each.getTitle()) ;
					TextRun[] textRuns = each.getTextRuns();
					
					for (int i=0 ;i< textRuns.length; i++ ) {
						System.out.println("textRuns[i].getText()  " + i + "    " + textRuns[i].getText());
						RichTextRun[] richTextRuns = textRuns[i].getRichTextRuns();
						
				        for (int j=0; j < richTextRuns.length; j++) {
				            System.out.println("richTextRuns[j].getText()  " + j + "  " + richTextRuns[j].getText());
				        }
					}
				}
				
				//提取所有JPEG图片
				PictureData[] picDatas = ppt.getPictureData();for (int i=0;i<picDatas.length;i++) {
			    if(picDatas[i].getType() == Picture.JPEG){
			        FileOutputStream out = new FileOutputStream("jpg_\" + i + \".jpg\");
			        ppt.write(out);
			        out.close();
			    }}
				
			}else if(filePath.endsWith("pptx") || filePath.endsWith("PPTX")){
				
				//File inputFile = new File("D:\\test.docx");  
	            //File inputFile = new File("D:\\test.pptx");   
	            //File inputFile = new File("D:\\test.xlsx");   
	            //File inputFile = new File("D:\\test.xls");   
	            //File inputFile = new File("D:\\test.doc");   
	            //File inputFile = new File("D:\\test.ppt");   
	            POITextExtractor extractor = ExtractorFactory.createExtractor(new File(filePath)); 
	            System.out.println(extractor.getText()); 
				//return text2007;
			}*/
			
			POITextExtractor extractor = ExtractorFactory.createExtractor(new File(filePath)); 
            return extractor.getText();

		} catch (Exception e) {
            //System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
		
		return "";
	}
}  