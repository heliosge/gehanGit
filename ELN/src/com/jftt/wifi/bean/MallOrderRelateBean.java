package com.jftt.wifi.bean;

import java.math.BigDecimal;

public class MallOrderRelateBean {
    private Integer id;

    private Integer relateId;

    private Integer commodityType;

    private Integer orderId;

    private BigDecimal price;

    private String commodityName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRelateId() {
        return relateId;
    }

    public void setRelateId(Integer relateId) {
        this.relateId = relateId;
    }

    public Integer getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(Integer commodityType) {
        this.commodityType = commodityType;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
}