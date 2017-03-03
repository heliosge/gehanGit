package com.jftt.wifi.bean;

import java.util.Date;

public class TrainArrangeContentBean {
    private Integer id;

    private Integer trainArrangeId;

    private String content;

    private Integer teacherId;
    
    private String teacherName;

    private String beginTime;

    private String endTime;
    
    private Date beginTimeDate;
    
    private Date endTimeDate;

    private String signBeginTime;

    private String signEndTime;

    private Integer period;

    private Integer score;

    private Integer trainType;

    private Integer courseId;
    
    private String courseName;

    private String place;

    private Integer passPercent;
    
    private Integer afterClassExam;//通过考试id
    
    private String afterClassExamName;
    
    private Integer aceDuration;//通过考试时长
    
    private Integer aceAllowTimes;//允许考试次数

    private Integer afterClassTest;
    
    private String afterClassTestName;

    private String beforeClassCourseName;
    
    private Integer beforeClassCourseId;

    private String beforeClassFilePath;
    
    private String beforeClassFileName;
    
    private Long beforeClassFileSize;

	public Integer getAceDuration() {
		return aceDuration;
	}

	public void setAceDuration(Integer aceDuration) {
		this.aceDuration = aceDuration;
	}

	public Integer getAceAllowTimes() {
		return aceAllowTimes;
	}

	public void setAceAllowTimes(Integer aceAllowTimes) {
		this.aceAllowTimes = aceAllowTimes;
	}

	public String getAfterClassExamName() {
		return afterClassExamName;
	}

	public void setAfterClassExamName(String afterClassExamName) {
		this.afterClassExamName = afterClassExamName;
	}

	public Integer getAfterClassExam() {
		return afterClassExam;
	}

	public void setAfterClassExam(Integer afterClassExam) {
		this.afterClassExam = afterClassExam;
	}

	public Long getBeforeClassFileSize() {
		return beforeClassFileSize;
	}

	public void setBeforeClassFileSize(Long beforeClassFileSize) {
		this.beforeClassFileSize = beforeClassFileSize;
	}

	public Date getBeginTimeDate() {
		return beginTimeDate;
	}

	public void setBeginTimeDate(Date beginTimeDate) {
		this.beginTimeDate = beginTimeDate;
	}

	public Date getEndTimeDate() {
		return endTimeDate;
	}

	public void setEndTimeDate(Date endTimeDate) {
		this.endTimeDate = endTimeDate;
	}

	public String getAfterClassTestName() {
		return afterClassTestName;
	}

	public void setAfterClassTestName(String afterClassTestName) {
		this.afterClassTestName = afterClassTestName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getBeforeClassCourseName() {
		return beforeClassCourseName;
	}

	public void setBeforeClassCourseName(String beforeClassCourseName) {
		this.beforeClassCourseName = beforeClassCourseName;
	}

	public Integer getBeforeClassCourseId() {
		return beforeClassCourseId;
	}

	public void setBeforeClassCourseId(Integer beforeClassCourseId) {
		this.beforeClassCourseId = beforeClassCourseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainArrangeId() {
        return trainArrangeId;
    }

    public void setTrainArrangeId(Integer trainArrangeId) {
        this.trainArrangeId = trainArrangeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    public String getSignBeginTime() {
        return signBeginTime;
    }

    public void setSignBeginTime(String signBeginTime) {
        this.signBeginTime = signBeginTime;
    }

    public String getSignEndTime() {
        return signEndTime;
    }

    public void setSignEndTime(String signEndTime) {
        this.signEndTime = signEndTime;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getPassPercent() {
        return passPercent;
    }

    public void setPassPercent(Integer passPercent) {
        this.passPercent = passPercent;
    }

    public Integer getAfterClassTest() {
        return afterClassTest;
    }

    public void setAfterClassTest(Integer afterClassTest) {
        this.afterClassTest = afterClassTest;
    }

	public Integer getTrainType() {
		return trainType;
	}

	public void setTrainType(Integer trainType) {
		this.trainType = trainType;
	}

	public String getBeforeClassFilePath() {
		return beforeClassFilePath;
	}

	public void setBeforeClassFilePath(String beforeClassFilePath) {
		this.beforeClassFilePath = beforeClassFilePath;
	}

	public String getBeforeClassFileName() {
		return beforeClassFileName;
	}

	public void setBeforeClassFileName(String beforeClassFileName) {
		this.beforeClassFileName = beforeClassFileName;
	}
    
    
}