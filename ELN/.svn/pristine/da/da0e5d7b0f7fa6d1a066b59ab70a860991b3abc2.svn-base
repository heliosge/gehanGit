package com.jftt.wifi.util;

import org.jdom.Element;

import com.jftt.wifi.bean.Catalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright®2013 Redot co.All Rights Reserved.
 * Author: CASH
 * Version: 1.0.0
 * Date: 13-5-28
 * Time: 下午6:06
 */
@SuppressWarnings("unchecked")
public class ScormParser {
    private Catalog catalog;
    private XmlReader xr;
    private Map<String, String> res;


    public Catalog getCatalog() {
        return catalog;
    }

    public ScormParser(String xml) {
        xr = new XmlReader(xml);
        if (!xr.isOK()) {
            return;
        }
        parse();
    }

    public void parse() {
        Element organizations = xr.getRoot().getChild("organizations", xr.getRoot().getNamespace());
        if (organizations == null) {
            return;
        }
        res = new HashMap<String, String>();
        parseResource();
        catalog = new Catalog();
        catalog.setName("ROOT");
        List<Element> organizationList = organizations.getChildren("organization", xr.getRoot().getNamespace());
        for (Element el : organizationList) {
            parseOrganization(el, catalog);
        }
    }


    public void parseOrganization(Element el, Catalog parent) {
        Catalog _parent;
        Element title = el.getChild("title", xr.getRoot().getNamespace());
        if (title != null) {
            Catalog catalog = new Catalog();
            catalog.setName(title.getText());
            parent.addChild(catalog);
            _parent = catalog;
        } else {
            _parent = parent;
        }
        List<Element> itemList = el.getChildren("item", xr.getRoot().getNamespace());
        for (Element item : itemList) {
            parseItem(item, _parent);
        }
    }

    public void parseItem(Element el, Catalog parent) {
        Element title = el.getChild("title", xr.getRoot().getNamespace());
        if (title == null) {
            return;
        }
        Catalog catalog = new Catalog();
        catalog.setName(title.getText());
        String file_id = el.getAttributeValue("identifierref");
        catalog.setFilePath(res.get(file_id));
        parent.addChild(catalog);
        List<Element> itemList = el.getChildren("item", xr.getRoot().getNamespace());
        for (Element item : itemList) {
            parseItem(item, catalog);
        }
    }

    public void parseResource() {
        Element resources = xr.getRoot().getChild("resources", xr.getRoot().getNamespace());
        if (resources == null) {
            return;
        }
        List<Element> resList = resources.getChildren();
        for (Element el : resList) {
            res.put(el.getAttributeValue("identifier"), el.getAttributeValue("href"));
        }
    }
}
