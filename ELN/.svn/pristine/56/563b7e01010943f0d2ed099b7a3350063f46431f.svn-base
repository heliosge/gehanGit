/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: Test.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-20        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.util;

import java.io.File;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public   class  JodDemo {  
	public   static   void  main(String[] args)  throws  Exception{   
		File inputFile = new  File( "d:/44.pptx" );   
		File outputFile = new  File( "d:/44.pdf" );   
		// connect to an OpenOffice.org instance running on port 8100    
		OpenOfficeConnection connection = new  SocketOpenOfficeConnection( 8100 );   
		connection.connect();   
		// convert    
		DocumentConverter converter = new  OpenOfficeDocumentConverter(connection);   
		converter.convert(inputFile, outputFile);   
		// close the connection    
		connection.disconnect();
	}   
}