/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KlCollectDownloadBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-14        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean;

import java.util.Date;
/**
 * 
 * class name:KlCollectDownloadBean <BR>
 * class description: 知识收藏下载 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-14
 * @author JFTT)chenrui
 */
public class KlCollectDownloadBean {
    private Integer id;//主键id

    private Integer resourceId;//资源id

    private Integer userId;//用户id

    private Date operateTime;//操作时间

    private Integer operateType;//操作类型1:收藏2:下载

    private Date updateTime;//更新时间
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
}