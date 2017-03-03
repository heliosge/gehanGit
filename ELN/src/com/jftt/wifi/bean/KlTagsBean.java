package com.jftt.wifi.bean;

import java.util.Date;
/**
 * 
 * class name:KlTagsBean <BR>
 * class description: 标签基础表 <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-3
 * @author JFTT)chenrui
 */
public class KlTagsBean {
    private Integer id;

    private String tagName;//标签名称

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}