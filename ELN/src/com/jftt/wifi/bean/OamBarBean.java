package com.jftt.wifi.bean;

public class OamBarBean {
    private Integer id;
    private Integer style;//1:6栏；2:8栏；3:12栏
    private Integer order;//排序序号
    private Integer type;//1：专题；2：课程；3：大赛
    private Integer spreadId;//推广资源id
    private Integer companyId;//公司id
    private Integer subCompanyId;//子公司id
    private Object spreadObject;//推广资源
    
    public Object getSpreadObject() {
		return spreadObject;
	}

	public void setSpreadObject(Object spreadObject) {
		this.spreadObject = spreadObject;
	}

	public Integer getSubCompanyId() {
		return subCompanyId;
	}

	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public Integer getSpreadId() {
        return spreadId;
    }

    public void setSpreadId(Integer spreadId) {
        this.spreadId = spreadId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}