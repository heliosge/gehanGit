package com.jftt.wifi.bean;

/**
 * Created by Administrator on 16-3-18.
 * 移动端app版本
 */
public class MobileAppVersionBean {
    private Integer id;
    private String name;       //版本名称
    private Integer code;      //版本编号，根据该属性判断是否更新
    private String descr;       //描述
    private Integer type;  //类型
    private String fileName;//更新包的名称
    private String path;       //更新包路径
    private Integer isForce;   //是否强制更新 1 是，0 否
    private Integer isPublished;//是否已发布：1已发布，0未发布
    private String createTime;//创建时间
    private Integer createUserId;//创建人id
    private String updateTime;//创建时间
    private Integer updateUserId;//创建人id
    private String  publicTime;     //发布时间
    private Integer publicUserId; //发布人
    private Integer isDelete; //是否删除（逻辑删除）：1逻辑删除，0非删除


    private Integer pageIndex;
    private Integer pageSize;
    private String beginTime;
    private String endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public Integer getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Integer isPublished) {
        this.isPublished = isPublished;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }


    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getIsForce() {
        return isForce;
    }

    public void setIsForce(Integer isForce) {
        this.isForce = isForce;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    public Integer getPublicUserId() {
        return publicUserId;
    }

    public void setPublicUserId(Integer publicUserId) {
        this.publicUserId = publicUserId;
    }
}
