<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>成绩批阅</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/slider.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/json2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/exam_paper/image-slider.js"></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/mediaelementInclude.jsp"></jsp:include>
<style type="text/css">
.py_xx a{width: 75px;}
.buttonClass{
	font-size:15px;
	font-family:微软雅黑;
	margin-right:5px;
	margin-top:2px;
	cursor: pointer;
	border-radius:2px;
	border:none;
	background-color:#d60500;
	padding-left: 15px;
	padding-right: 15px;
	padding-bottom:5px;
	padding-top:5px;
	color: #ffffff;
}
.py_txt2 p{  margin-bottom: 10px;}
.py_right {
  width: 790px;
  margin-left: 20px;
  height: 512px;
  float: left;
  margin-top: 20px;
}
.py_right .py_tx {
  padding: 10px;
  width: 800px;
  height: 68px;
  background: #eeeeee;
  border: 1px solid #ccc;
}
.py_txt {
  width: 820px;
  height: 424px;
  border: 1px solid #ccc;
  overflow: scroll;
  font-family: '微软雅黑';
}
.py_xx {
  width: 790px;
  height: 40px;
  background: #e1e1e1;
  padding-top: 10px;
}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var examId = '${examId}';//考试记录ID
var matchId = '${matchId}';//赛程ID
$(function(){
	query();
	getExamTitle();
});

function getExamTitle(){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":examId},
		url: "<%=request.getContextPath()%>/myExamManage/getExamTitle.action",
		success:function(data){
			if(data != null){
				$("#title").text(data);
			}
	    }
	});
}

function query(){
	$("#personDiv").html("");
	$.ajax({
		type : "POST",
		async:false,  //默认true,异步
		data : {"id" : examId,"name":$.trim($("#name").val()),"department":$.trim($("#department").val())},
		url : "<%=request.getContextPath()%>/exam/exam/getAttendUserList.action",
		success : function(data) {
			//alert(JSON.stringify(data));
			if(data != null && data != ''){
				var str = '';
				var firstPerson = 0;
				for(var i=0;i<data.length;i++){
					firstPerson = data[0].id;
					if(data[i].isMarked){
						str += '<p style="cursor:pointer;color:red;" id="p_'+data[i].id+'" onclick="clickPerson('+data[i].id+');">'+ data[i].name +'</p>';
					}else{
						str += '<p style="cursor:pointer;" id="p_'+data[i].id+'" onclick="clickPerson('+data[i].id+');">'+ data[i].name +'</p>';
					}
				}
				$("#personDiv").html(str);
				clickPerson(firstPerson);
			}else{
				$("#personDiv").html('<p>暂无数据</p>');
				$("#saveButton").remove();
			}
		}
	});
}

function clickPerson(id){
	viewPaper(id);
	$("#p_"+id).css("background-color","#dbdbdb");
	$("p[id^='p_']").each(function(index,element){
		if($(this).attr("id") != 'p_'+id){
			$(this).css("background-color","#eeeeee");
		}
	});
}

var optionName = ["","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var isOrNo = ["错误","正确"];
var typeArray = new Array();
var qt = ["","主观题","单选题","多选题","判断题","填空题","组合题","多媒体题",""];
var html = '';
function viewPaper(exam_assign_id){
	$("#questionDiv").html('<span style="padding-left:30px;font-size:24px;">正在加载中，请稍后...<span>');
	$("#exam_assign_id").val(exam_assign_id);
	var correctNum = 0;
	var wrongNum = 0;
	var unwriteNum = 0;
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{"exam_assign_id":exam_assign_id},
		url: "<%=request.getContextPath()%>/myExamManage/getPyPaperDetail.action",
		success:function(data){
			html = '';
			if(data != null){
				//alert(JSON.stringify(data));
					for(var i=0;i<data.length;i++){
						var userAnswerBean = data[i];
						var questionBean = userAnswerBean.question;
						var id = questionBean.id;
						var options = questionBean.options;
						var type = questionBean.type;
						var displayType = questionBean.displayType;
						if(!typeArray.contains(displayType)){
							typeArray.push(displayType);
						}
						var isMedia = questionBean.isMultimedia;
						var mediaType = questionBean.multimediaType;
						var mediaUrl = questionBean.multimediaUrl;
						var answerKeys = questionBean.answerKeys;
						html += '<div class="py_txt1" name="singleQuestion" flag="'+displayType+'">';
						html += '<p>'+userAnswerBean.orderNum+'.<span>';
						html += questionBean.content + '</span></p>';
						html += '<input type="hidden" id="id_'+id+'" name="id" value="'+userAnswerBean.id+'"/>';
						if(type == 6){
							html += '<input type="hidden" id="getScore_'+id+'" name="getScore" value="0"/>';
						}
						html += '<div class="py_txt2">';
						if(type == 6){//组合题
							var subAnswers = userAnswerBean.subAnswers;
							//alert(JSON.stringify(subAnswers));
							if(subAnswers != null){
								for(var j=0;j<subAnswers.length;j++){
									var subQuestion = subAnswers[j].question;
									type = subQuestion.type;
									id = subQuestion.id;
									options = subQuestion.options;
									isMedia = subQuestion.isMultimedia;
									mediaType = subQuestion.multimediaType;
									mediaUrl = subQuestion.multimediaUrl;
									answerKeys = subQuestion.answerKeys;
									html += '<input type="hidden" id="id_'+id+'" name="id" value="'+subAnswers[j].id+'"/>';
									html += '<p>问题'+subAnswers[j].subOrderNum+'.<span>'+setNull(subQuestion.content)+'</span></p>';
									//题目状态标识 1-已完成 2-未完成 3-已标记 
									if(type == 5){//填空题的话，将answerKeys当作options
										html += concatOptions(id,type,isMedia,mediaType,mediaUrl,answerKeys,subAnswers[j].answer);
									}else{
										html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options,subAnswers[j].answer);
									}
									//setUserAnswer(id,type,subAnswers[j].answer);
									//setCorrectAnswer(type,subAnswers[j].correctAnswer);
									
									html += '<div class="py_txt3" style="margin-top:10px;">';
				                	html += '<p><span>正确答案：</span>';
				                	if(type == 1 || type == 5){
										html += '<em>'+subAnswers[j].correctAnswer+'</em>';
									}else{
										setCorrectAnswer(type,subAnswers[j].correctAnswer);
									}
				                	html += '</p><p><span>该题得分：</span>';
				                	html += '<input type="text" id="getScore_'+id+'" name="getScore" value="'+subAnswers[j].getScore+'" onblur="checkNumber(this,'+subAnswers[j].score+')"/>';
	 			                	html += '/<em>'+subAnswers[j].score+'</em>';
				                	html += '</p></div>';
								}
							}
						}else{//其它题型
							if(type == 5){//填空题的话，将answerKeys当作options
								html += concatOptions(id,type,isMedia,mediaType,mediaUrl,answerKeys,userAnswerBean.answer);
							}else{
								html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options,userAnswerBean.answer);
							}
						}
		                if(questionBean.type != 6){
		                	html += '<div class="py_txt3" style="margin-top:10px;">';
		                	html += '<p><span>正确答案：</span>';
		                	if(questionBean.type == 1 || questionBean.type == 5){
								html += '<em>'+userAnswerBean.correctAnswer+'</em>';
							}else{
								setCorrectAnswer(type,userAnswerBean.correctAnswer);
							}
		                	html += '</p><p><span>该题得分：</span>';
		                	html += '<input type="text" id="getScore_'+questionBean.id+'" name="getScore" value="'+userAnswerBean.getScore+'" onblur="checkNumber(this,'+userAnswerBean.score+')"/>';
		                	html += '/<em>'+userAnswerBean.score+'</em>';
		                	html += '</p></div>';
		                }
	                	if(userAnswerBean.score == userAnswerBean.getScore){
		                	correctNum++;
		                }else{
		                	if(userAnswerBean.answer == '-' || userAnswerBean.answer == 'unwrite'){
		                		unwriteNum++;
		                	}else{
			                	wrongNum++;
		                	}
		                }
						html += '</div></div>';
					}
					$("#questionDiv").html(html);
					initElement();
					$("#wrongNum").text("错误："+wrongNum);
					$("#correctNum").text("正确："+correctNum);
					$("#unwriteNum").text("未答："+unwriteNum);
					$("div[id^='focus_']").each(function(index,element){
						//alert($(this).attr("id"));
						_topImageShow($(this).attr("id"));
					});
					$("a[id^='btn_show_']").each(function(index,element){
						var showId = $(this).attr("name");
						$('#btn_show_'+showId).on('click', function(){
							showVideoPlayerDialog('#Content_'+showId);
						});
					});
					setTypeNum();
			}
	    }
	});
}

//计算总得分
window.onload=function(){
	setInterval(setTotalScore,1);
};
function setTotalScore(){
	var totalScore = 0;
	$("input[name='getScore']").each(function(index,element){
		totalScore += parseInt($(this).val());
	});
	/* if(isNaN(totalScore)){
		alert("请输入正确的分数！");
		return false;
	} */
	$("#totalScore").text(totalScore);
}

//校验是否是数字类型
var match = /^[1-9][0-9]?|(100)|(0)$/;
function checkNumber(obj,score){
	var value = $.trim($(obj).val());
	if(!match.test(value)){
		alert("请输入非负整数！");
		obj.focus();
		return false;
	}else{
		if(value > score || value < 0){
			alert('请输0到'+score+'之间的整数！');
			return false;
		}
	}
}

//初始化部分元素
function initElement(){
	$("#inputDiv").html('<a href="javascript:void(0);" onclick="selectQuestion(0);">显示全部</a>');
	typeArray.sort();
	//初始化头部各题型按钮
	for(var n=0;n<typeArray.length;n++){
		var num = typeArray[n];
		$("#inputDiv").append('<a href="javascript:void(0);" id="input_'+num+'" onclick="selectQuestion('+num+');">'+qt[num]+'(<span displaytype="'+num+'" id="number_'+num+'">0</span>)</a>');
	}
}

function isEqual(str1,str2){
	var flag = true;
	if(str1 != null && str2 != null){
		var array1 = str1.split(",");
		var array2 = str2.split(",");
		if(array1.length == array2.length){
			for(var i=0;i<array1.length;i++){
				if(!array2.contains(array1[i])){
					flag = false;
					break;
				}
			}
		}else{
			flag = false;
		}
	}else{
		flag = false;
	}
	return flag;
}

/*统计题型的数目   */
var displayArray = [0,0,0,0,0,0,0,0];
function setTypeNum(){
	displayArray = [0,0,0,0,0,0,0,0];
	$("div[name='singleQuestion']").each(function(index,element){
		var flag = $(this).attr("flag");
		displayArray[flag] = (displayArray[flag]+1);
	});
	$("span[id^='number_']").each(function(index,element){
		var flag = $(this).attr("displaytype");
		$(this).text(displayArray[flag]);
	});
}


//判断数组中是包含某个元素 通用方法
Array.prototype.contains = function (element){
	for (var i=0; i<this.length;i++){
		if (this[i] == element){ 
			return true; 
		} 
	} 
	return false; 
}; 


//设置正确答案
function setCorrectAnswer(type,correctAnswer){
	if(type == 2){//单选
		html += '<em>'+ optionName[correctAnswer] +'</em>';
	}else if(type == 3){//多选
		html += '<em>'
		cArray = correctAnswer.split(",");
		for(var i=0;i<cArray.length;i++){
			var num = cArray[i];
			html += optionName[num];
			if(i!=cArray.length - 1){
				html += ',';
			}
		}
		html += '</em>';
	}else if(type == 1 || type == 5){//主观题、填空题
		html += '<em>'+ correctAnswer +'</em>';
	}else{//判断题
		html += '<em>'+ isOrNo[correctAnswer] +'</em>';
	}
}
/*拼接选项 */
function concatOptions(questionId,type,isMedia,mediaType,mediaUrl,options,myanswer){
	var optionHtml = '';
	optionHtml += concatMedia(questionId,isMedia,mediaType,mediaUrl);
	if(type == 1){//主观题
		if(myanswer == '-'){
			optionHtml += '<p><textarea id="option_'+questionId+'" name="option_'+questionId+'" disabled="disabled"></textarea></p>';
		}else{
			optionHtml += '<p><textarea id="option_'+questionId+'" name="option_'+questionId+'" disabled="disabled">'+myanswer+'</textarea></p>';
		}
		
	}else if(type == 2){//单选题
		for(var i=0;i<options.length;i++){
			var otype = options[i].type;
			if(otype == 1){//文本
				if(options[i].id == myanswer){
					optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="'+options[i].id+'" checked="checked" disabled="disabled"/><em>'
					+optionName[i+1]+'.'+setNull(options[i].content)+'</em></p>';
				}else{
					optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="'+options[i].id+'" disabled="disabled"/><em>'
					+optionName[i+1]+'.'+setNull(options[i].content)+'</em></p>';
				}
			}else{
				if(options[i].id == myanswer){
					optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="'+options[i].id+'" checked="checked" disabled="disabled"/><em>'
					+optionName[i+1]+'.<img class="option_img" src="'+options[i].content+'"/></em></p>';
				}else{
					optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="'+options[i].id+'" disabled="disabled"/><em>'
					+optionName[i+1]+'.<img class="option_img" src="'+options[i].content+'"/></em></p>';
				}
			}
			
		}
	}else if(type == 3){//多选题
		var ans = myanswer.split(",");
		for(var i=0;i<options.length;i++){
			var otype = options[i].type;
			if(otype == 1){//文本
				if(ans.contains(options[i].id)){
					optionHtml += '<p><input type="checkbox" name="option_'+questionId+'" value="'+options[i].id+'" checked="checked" disabled="disabled"/><em>'
					+optionName[i+1]+'.'+setNull(options[i].content)+'</em></p>';
				}else{
					optionHtml += '<p><input type="checkbox" name="option_'+questionId+'" value="'+options[i].id+'" disabled="disabled"/><em>'
					+optionName[i+1]+'.'+setNull(options[i].content)+'</em></p>';
				}
			}else{
				if(ans.contains(options[i].id)){
					optionHtml += '<p><input type="checkbox" name="option_'+questionId+'" value="'+options[i].id+'" checked="checked" disabled="disabled"/><em>'
					+optionName[i+1]+'.<img class="option_img" src="'+options[i].content+'"/></em></p>';
				}else{
					optionHtml += '<p><input type="checkbox" name="option_'+questionId+'" value="'+options[i].id+'" disabled="disabled"/><em>'
					+optionName[i+1]+'.<img class="option_img" src="'+options[i].content+'"/></em></p>';
				}
			}
			
		}
	}else if(type == 4){//判断题
		if(myanswer == 0){
			optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="1" disabled="disabled"/><em>A.正确</em></p>';
			optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="0" checked="checked" disabled="disabled"/><em>B.错误</em></p>';
		}else if(myanswer == 1){
			optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="1" checked="checked" disabled="disabled"/><em>A.正确</em></p>';
			optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="0" disabled="disabled"/><em>B.错误</em></p>';
		}else{
			optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="1" disabled="disabled"/><em>A.正确</em></p>';
			optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="0" disabled="disabled"/><em>B.错误</em></p>';
		}
		
	}else if(type == 5){//填空题
		var answerKeys = [];
		if(options != null && options != ''){
			answerKeys = options.split(" ");
		}
		if(myanswer == '-'){
			for(var i=0;i<answerKeys.length;i++){
				optionHtml += '<p><input type="text" name="option_'+questionId+'" value="" disabled="disabled"/></p>';
			}
		}else{
			var answer = myanswer.split("##**##");
			for(var i=0;i<answerKeys.length;i++){
				optionHtml += '<p><input type="text" name="option_'+questionId+'" value="'+answer[i]+'" disabled="disabled"/></p>';
			}
		}
	}
	return optionHtml;
}


/*拼接多媒体题  */
function concatMedia(questionId,isMedia,mediaType,mediaUrl){
	var mediaHtml = '';
	if(isMedia){
		var url = [];
		if(mediaUrl != null && mediaUrl != ''){
			url = JSON.parse(mediaUrl);
		}
		if(mediaType == 1){//图片
			mediaHtml += '<div id="focus_'+questionId+'" class="focus">';
			mediaHtml += '<ul>';
			for(var i=0;i<url.length;i++){
				mediaHtml += '<li><img class="media_img" src="'+url[i]+'"/></li>';
			}
			mediaHtml += '</ul></div>';
		}else if(mediaType == 2){//音频
			for(var i=0;i<url.length;i++){
				mediaHtml += '<div style="padding: 10px 0px 10px 70px;">';
				mediaHtml += genAudioTag(url[i]);
				mediaHtml += '</div>';
			}
		}else if(mediaType == 3){//视频
			mediaHtml += '<a href="javascript:void(0);" id="btn_show_'+questionId+'" name="'+questionId+'" onclick="showVideo('+questionId+')">查看</a>';
			mediaHtml += '<span id="Content_'+questionId+'" style="display:none;">';
			mediaHtml += '<div id="al" style="padding-left: 5px;">';
			for(var i=0;i<url.length;i++){
				mediaHtml += genVideoTagWithoutInitScript(url[i]);
			}
			mediaHtml += '</div>';
			mediaHtml += '</span>';
		}
	}
	return mediaHtml;
}

/*字符截取  */
function subText(title,len){
	if(title != null){
		if(title.length>len){
			title = title.substr(0,len)+"...";
		} 
	}
	return title;
}

/*判空  */
function setNull(title){
	if(title == null){
		return "暂无";
	}
	return title;
}

/*控制按钮样式与切换试题  */
function selectQuestion(type){
	$("#input_"+type).addClass("input_a");
	$("#inputDiv").find("input[id!='input_"+type+"']").removeClass();
	if(type != 0){
		$("#questionDiv >div").each(function(){
	          //alert($(this).attr("id"));  //打印子div的ID
	          if($(this).attr("flag") != type){
	        	  $(this).css("display","none");
	          }else{
	        	  $(this).css("display","block");
	          }
		});
	}else{
		$("#questionDiv").find("div").css("display","block");
	}
}

/*保存  */
var scoreStr = '';
var idStr = '';
function saveData(){
	dialog.confirm('确认保存吗？', function(){
		var d2 = dialog({
		    content: '正在提交，请等待...'
		});
		d2.showModal();
		var param = new Object();
		$("input[name=getScore]").each(function(index,element){
			//scoreArray.push($.trim($(this).val()));
			scoreStr += $.trim($(this).val()) + ',';
		});
		$("input[name=id]").each(function(index,element){
			//questionIdArray.push($.trim($(this).val()));
			idStr += $.trim($(this).val()) + ',';
		});
		//alert(scoreArray);
		param.examAssignId = $("#exam_assign_id").val();
		param.getScore = scoreStr;
		param.id = idStr;
		param.totalScore = $("#totalScore").text();
		param.examId = examId;
		param.matchId = matchId;
		//alert(param);
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:param,
			url: "<%=request.getContextPath()%>/exam/exam/modifyScore.action",
			success:function(data){
				if(data == 'SUCCESS'){
					d2.close();
					dialog.alert("操作成功！", function (){
						query();
					});
				}else{
					d2.close();
					dialog.alert("操作失败！");
				}
		    }
		});
	});
}

/*返回  */
function goBack(){
	if(matchId){
		window.location = "<%=request.getContextPath()%>/MyMegagame/toMegagameCheck.action";
	}else{
		window.location = "<%=request.getContextPath()%>/exam/exam/toCjPyList.action";
	}
}
</script>
</head>
<body>
<input type="hidden" id="exam_assign_id" name="exam_assign_id"/>
<div class="content">
	<!-- <h3>成绩批阅</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="goBack();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">成绩批阅</span>
	</div>
    <div class="py_top">
    	<h4 id="title"></h4>
        <span>总得分：<span id="totalScore">22</span></span>
    </div>
    <div class="content_2">
        <div class="search_2 fl">
            <p>
		                部门：<input type="text" id="department" name="department"/>
		                姓名：<input type="text" id="name" name="name"/>
            </p>
            <input type="button" value="查询" class="btn_cx" onclick="query();"/>
        </div>
        <div class="person_lis">
        	<h2>人员列表</h2>
        	<div id="personDiv">
        	</div>
        </div>
        <div class="py_right">
        	<div class="py_tx">
            	<p>
                	<span id="wrongNum"></span>
                    <span id="unwriteNum"></span>
                    <span id="correctNum"></span>
                </p>
                <div class="py_xx" id="inputDiv">
<!--                 	<a href="#" onclick="selectQuestion(0);">显示全部</a> -->
                    <!-- <a href="#">主观题（1）</a>
                    <a href="#">单选题（1）</a>
                    <a href="#">多选题（1）</a>
                    <a href="#">判断题（1）</a>
                    <a href="#">填空题（1）</a> -->
                </div>
            </div>
        	<div class="py_txt" id="questionDiv">
            	<!-- <div class="py_txt1">
                	<p>1.<span>1+1=?</span></p>
                    <div class="py_txt2">
                    	<p><input type="radio" /><em>A.1</em></p>
                        <p><input type="radio" checked="checked" /><em>B.2</em></p>
                        <p><input type="radio" /><em>C.3</em></p>
                        <div class="py_txt3">
                        	<p>
                            	<span>正确答案：</span>
                                <em>B</em>
                            </p>
                            <p>
                            	<span>该题得分：</span>
                                <input type="text" />
                                /
                                <em>B</em>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="py_txt1">
                	<p>2.<span>1+1=?</span></p>
                    <div class="py_txt2">
                    	<p><input type="radio" /><em>A.1</em></p>
                        <p><input type="radio" checked="checked" /><em>B.2</em></p>
                        <p><input type="radio" /><em>C.3</em></p>
                        <div class="py_txt3">
                        	<p>
                            	<span>正确答案：</span>
                                <em>B</em>
                            </p>
                            <p>
                            	<span>该题得分：</span>
                                <input type="text" />
                                /
                                <em>B</em>
                            </p>
                        </div>
                    </div>
                </div> 
                <div class="py_txt1">
                	<p>3.<span>1+1=?</span></p>
                    <div class="py_txt2">
                    	<p><input type="radio" /><em>A.1</em></p>
                        <p><input type="radio" checked="checked" /><em>B.2</em></p>
                        <p><input type="radio" /><em>C.3</em></p>
                        <div class="py_txt3">
                        	<p>
                            	<span>正确答案：</span>
                                <em>B</em>
                            </p>
                            <p>
                            	<span>该题得分：</span>
                                <input type="text" />
                                /
                                <em>B</em>
                            </p>
                        </div>
                    </div>
                </div> -->    	   	    	
            </div>
            <div style="padding-top:10px;">
            	<input id="saveButton" type="button" class="buttonClass" value="保存" onclick="saveData();"/>
        		<input type="button" class="buttonClass" style="background:#0085fe" value="返回" onclick="goBack();"/>
            </div>
        </div>
   </div> 
</div>
</body>
</html>
