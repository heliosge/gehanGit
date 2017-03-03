package com.jftt.wifi.bean.exam.vo;

public class QuestionExportVo {

	private String content;
	private String typeName;
	private Integer type;
	private String difficulty;
	private Integer difficultyLevel;
	private String analysis;
	private String options;
	private String answer;
	private String key;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
		if(type!=null){
			switch(type){
			case 1:
				this.typeName="主观题";
				break;
			case 2:
				this.typeName="单选题";
				break;
			case 3:
				this.typeName="多选题";
				break;
			case 4:
				this.typeName="判断题";
				break;
			case 5:
				this.typeName="填空题";
				break;
			
			}
		}
	}
	public Integer getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
		if(difficultyLevel!=null){
			switch(difficultyLevel){
			case 1:
				this.difficulty="易";
				break;
			case 2:
				this.difficulty="中";
				break;
			case 3:
				this.difficulty="难";
				break;
			
			}
		}
	}
	
	

}
