package com.jftt.wifi.bean;

public class ResSectionCoursewareBean {
    private Integer id;

    private Integer sectionId;

    private Integer coursewareId;
    
    public ResSectionCoursewareBean(){}
    
    public ResSectionCoursewareBean(Integer sectionId, Integer coursewareId)
    {
    	this.sectionId = sectionId;
    	this.coursewareId = coursewareId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getCoursewareId() {
        return coursewareId;
    }

    public void setCoursewareId(Integer coursewareId) {
        this.coursewareId = coursewareId;
    }
}