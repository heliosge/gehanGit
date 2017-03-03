package com.jftt.wifi.util;

import java.io.File;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;

import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * class name:OfficeToPDF <BR>
 * class description: word生成相应的pdf <BR>
 * Remark: <BR>
 * @version 1.00 2013-1-15
 * @author JFTT)
 */
public class OfficeToPDF extends Thread{
	private String sourcefile;
 	private String destfile;
 	public OfficeToPDF(String source,String dest){
 		this.sourcefile = source;
 		this.destfile = dest;
 	}
 	public void run(){			
		// 原始文件
		File inputFile = new File(sourcefile);
		// 如果目标路径不存在, 则新建该路径
		File outputFile = new File(destfile);
		if (!outputFile.getParentFile().exists()) {
			outputFile.getParentFile().mkdirs();
		}
		OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
		/*try {
			connection.connect();
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
		converter.convert(inputFile, outputFile);
		connection.disconnect();*/
		
		try  {   
            connection.connect();   
            DocumentConverter converter = new  OpenOfficeDocumentConverter(connection);   
            converter.convert(inputFile,outputFile);   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally  {   
            try {  
            	if (connection !=  null ){
            		connection.disconnect(); 
            		connection =  null ;}
            	} catch (Exception e){} 
            }
 	}
}

