/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: SectionWareVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * class name:SectionWareVo <BR>
 * class description: 章节课件vo（课程详情展示用vo） <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)ShenHaijun
 */
public class SectionWareVo {
	private Integer id;//课件id
	private Integer sectionId;//章节id
	private String name;//课件名称
	private Integer type;//课件类型（1：标准课件；2：普通课件；3：视屏课件；）
	private Integer progressPercent;//学习进度百分比（0到100之内的正整数）
	private String fileName;
	private String filePath;
	/*add by luyunlong 显示视频的时长*/
	private String totalContent;
	private String currentContent;
	
	
	public String getCurrentContent() {
		return currentContent;
	}
	public void setCurrentContent(String currentContent) {
		this.currentContent = currentContent;
	}
	public String getTotalContent() {
		return totalContent;
	}
	public void setTotalContent(String totalContent) {
		this.totalContent = totalContent;
	}
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getProgressPercent() {
		return progressPercent;
	}
	public void setProgressPercent(Integer progressPercent) {
		this.progressPercent = progressPercent;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	
	
}
