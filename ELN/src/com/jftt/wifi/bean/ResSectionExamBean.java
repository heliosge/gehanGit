package com.jftt.wifi.bean;

public class ResSectionExamBean {
    private Integer id;

    private Integer sectionId;//章节id
    private Integer examId;//试卷id
    private Integer examDuration;//考试时长
    private Integer examTimes;//允许考试次数
    private Integer passPercent;//通过分数
    
    public ResSectionExamBean(){}
    
    public ResSectionExamBean(Integer sectionId){ this.sectionId = sectionId;}
    
    public ResSectionExamBean(Integer sectionId, Integer examId, Integer examDuration, Integer examTimes, Integer passPercent)
    {
    	 this.sectionId = sectionId;
    	 this.examId = examId;
    	 this.passPercent = passPercent;
    	 this.examDuration = examDuration;
    	 this.examTimes = examTimes;
    }

    public Integer getExamDuration() {
		return examDuration;
	}

	public void setExamDuration(Integer examDuration) {
		this.examDuration = examDuration;
	}

	public Integer getExamTimes() {
		return examTimes;
	}

	public void setExamTimes(Integer examTimes) {
		this.examTimes = examTimes;
	}

	public Integer getPassPercent() {
		return passPercent;
	}

	public void setPassPercent(Integer passPercent) {
		this.passPercent = passPercent;
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

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }
}