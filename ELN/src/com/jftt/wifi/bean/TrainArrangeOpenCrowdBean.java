package com.jftt.wifi.bean;

public class TrainArrangeOpenCrowdBean {
    private Integer id;

    private Integer deptId;
    
    private String deptName;

    private Integer trainArrangeId;

    public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getTrainArrangeId() {
        return trainArrangeId;
    }

    public void setTrainArrangeId(Integer trainArrangeId) {
        this.trainArrangeId = trainArrangeId;
    }
}