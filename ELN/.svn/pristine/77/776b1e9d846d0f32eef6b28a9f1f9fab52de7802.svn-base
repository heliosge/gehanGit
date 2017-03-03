package com.jftt.wifi.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright®2013 Redot co.All Rights Reserved.
 * Author: CASH
 * Version: 1.0.0
 * Date: 13-5-28
 * Time: 下午5:36
 */
public class Catalog {

    private String name;
    private String filePath;
    private List<Catalog> children;

    public Catalog() {
        children = new ArrayList<Catalog>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<Catalog> getChildren() {
        return children;
    }

    public void setChildren(List<Catalog> children) {
        this.children = children;
    }

    public void addChild(Catalog catalog){
        getChildren().add(catalog);
    }
}
