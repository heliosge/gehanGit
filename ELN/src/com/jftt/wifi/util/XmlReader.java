package com.jftt.wifi.util;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by IntelliJ IDEA.
 * User: CASH
 * Date: 10-12-17
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 */
public class XmlReader {

    Logger logger = Logger.getLogger(this.getClass());

    SAXBuilder builder;

	private Document doc;

    private Element root;

    private Format format = Format.getCompactFormat();

    private boolean isok = false;

    public XmlReader(File file) {
        builder = new SAXBuilder();
        try {
			doc = builder.build(file);
            root = doc.getRootElement();
            isok = true;
		} catch (IOException e) {
			doc = null;
			e.printStackTrace();
		} catch (JDOMException e) {
			doc = null;
			e.printStackTrace();
		}
    }

    public XmlReader(String path) {
        builder = new SAXBuilder();
        try {
			doc = builder.build(new File(path));
            root = doc.getRootElement();
            isok = true;
		} catch (IOException e) {
			doc = null;
			e.printStackTrace();
		} catch (JDOMException e) {
			doc = null;
			e.printStackTrace();
		}
    }

    public XmlReader(StringBuffer sb) {
        builder = new SAXBuilder();
        try {
			doc = builder.build(new StringReader(sb.toString()));
            root = doc.getRootElement();
            isok = true;
		} catch (IOException e) {
			doc = null;
			e.printStackTrace();
		} catch (JDOMException e) {
			doc = null;
			e.printStackTrace();
		}
    }

    public Element getRoot(){
        return this.root;
    }

    public boolean isOK(){
        return isok;
    }

}
