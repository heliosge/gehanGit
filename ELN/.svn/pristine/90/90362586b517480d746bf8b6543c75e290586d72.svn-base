<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看错题</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/slider.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/exam_paper/image-slider.js"></script>
<script src="<%=request.getContextPath() %>/js/layer/layer.js"></script>
<jsp:include page="../common/mediaelementInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
var wrongId = '${wrong_id}';
var optionName = ["","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var isOrNo = ["错误","正确"];
var wrongType = ${wrongType};
var html = '';
$(function(){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":wrongId},
		url: "<%=request.getContextPath()%>/myExercise/getExerciseWrongDetail.action",
		success:function(data){
			if(data != null){
				var myAnswer = data.myAnswer;
				var questionBean = data.questionBean;
				var id = questionBean.id;
				var options = questionBean.options;
				var type = questionBean.type;
				var isMedia = questionBean.isMultimedia;
				var mediaType = questionBean.multimediaType;
				var mediaUrl = questionBean.multimediaUrl;
				var answerKeys = questionBean.answerKeys;
				html += '<div class="dx" style="padding-top:0px;padding-left:0px;">';
				html += '<h5><span style="color:black;">问题：</span>';
				html += questionBean.content + '：';
				html += '<em><a href="javascript:void(0);" onclick="deleteWrong('+wrongId +')">移出错题集</a></em></h5>';
				if(type == 6){//组合题
					var subQuestions = questionBean.subQuestions;
					//alert(JSON.stringify(subAnswers));
					if(subQuestions != null){
						for(var j=0;j<subQuestions.length;j++){
							var subAnswer = myAnswer.split("@_@");
							var subQuestion = subQuestions[j];
							type = subQuestion.type;
							id = subQuestion.id;
							options = subQuestion.options;
							isMedia = subQuestion.isMultimedia;
							mediaType = subQuestion.multimediaType;
							mediaUrl = subQuestion.multimediaUrl;
							answerKeys = subQuestion.answerKeys;
							html += '<p>问题'+(j+1)+'：'+setNull(subQuestion.content)+'</p>';
							//题目状态标识 1-已完成 2-未完成 3-已标记 
							if(type == 5){//填空题的话，将answerKeys当作options
								html += concatOptions(id,type,isMedia,mediaType,mediaUrl,answerKeys,subAnswer[j]);
							}else{
								html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options,subAnswer[j]);
							}
							//setUserAnswer(id,type,subAnswers[j].answer);
							setCorrectAnswer(type,subQuestion.correctAnswer);
						}
					}
				}else{//其它题型
					if(type == 5){//填空题的话，将answerKeys当作options
						html += concatOptions(id,type,isMedia,mediaType,mediaUrl,answerKeys,myAnswer);
					}else{
						html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options,myAnswer);
					}
					//setUserAnswer(id,type,userAnswerBean.answer);
					setCorrectAnswer(type,questionBean.correctAnswer);
				}
				html += '</div>';
				$("#wrongDiv").html(html);
				$("div[id^='focus_']").each(function(index,element){
					//alert($(this).attr("id"));
					_topImageShow($(this).attr("id"));
				});
				$('#btn_show').on('click', function(){
					showVideoPlayerDialog('#Content');
				});

				$("div .dx").children("h5").find(".question_content_img").each(function(index,element){
					$(this).on("click",function(){
						viewBigPic(this);
					});
				});
			}
	    }
	});
});

//设置正确答案
function setCorrectAnswer(type,correctAnswer){
	if(type == 2){//单选
		html += ' <p><em>正确答案：</em>'+ optionName[correctAnswer] +'</p>';
	}else if(type == 3){//多选
		html += ' <p><em>正确答案：</em>'
		cArray = correctAnswer.split(",");
		for(var i=0;i<cArray.length;i++){
			var num = cArray[i];
			html += optionName[num];
			if(i!=cArray.length - 1){
				html += ',';
			}
		}
		html += '</p>';
	}else if(type == 1 || type == 5){//主观题、填空题
		html += ' <p><em>正确答案：</em>'+ correctAnswer +'</p>';
	}else{//判断题
		html += ' <p><em>正确答案：</em>'+ isOrNo[correctAnswer] +'</p>';
	}
}

//设置多选题答案
function setUserAnswer(id,type,answer){
	if(type == 2 || type == 4){//单选题//判断题
		$("input[type=radio][name=option_"+id+"][value='"+answer+"']").attr("checked",true);
	}
	if(type == 3){//多选题
		var an = answer.split(",");
		$.each(an,function(i,item){
			$("input[name=option_"+id+"][value='"+item+"']").attr("checked","checked");
		});
	}
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
					optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="'+options[i].id+'" checked="checked" disabled="disabled"/>'
					+optionName[i+1]+'.<span>'+setNull(options[i].content)+'</span></p>';
				}else{
					optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="'+options[i].id+'" disabled="disabled"/>'
					+optionName[i+1]+'.<span>'+setNull(options[i].content)+'</span></p>';
				}
			}else{
				if(options[i].id == myanswer){
					optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="'+options[i].id+'" checked="checked" disabled="disabled"/>'
					+optionName[i+1]+'.<span><img class="option_img" src="'+options[i].content+'" onclick="viewBigPic(this);"/></span></p>';
				}else{
					optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="'+options[i].id+'" disabled="disabled"/>'
					+optionName[i+1]+'.<span><img class="option_img" src="'+options[i].content+'" onclick="viewBigPic(this);"/></span></p>';
				}
			}
			
		}
	}else if(type == 3){//多选题
		var ans = myanswer.split(",");
		for(var i=0;i<options.length;i++){
			var otype = options[i].type;
			if(otype == 1){//文本
				if(ans.contains(options[i].id)){
					optionHtml += '<p><input type="checkbox" name="option_'+questionId+'" value="'+options[i].id+'" checked="checked" disabled="disabled"/>'
					+optionName[i+1]+'.<span>'+setNull(options[i].content)+'</span></p>';
				}else{
					optionHtml += '<p><input type="checkbox" name="option_'+questionId+'" value="'+options[i].id+'" disabled="disabled"/>'
					+optionName[i+1]+'.<span>'+setNull(options[i].content)+'</span></p>';
				}
			}else{
				if(ans.contains(options[i].id)){
					optionHtml += '<p><input type="checkbox" name="option_'+questionId+'" value="'+options[i].id+'" checked="checked" disabled="disabled"/>'
					+optionName[i+1]+'.<span><img class="option_img" src="'+options[i].content+'" onclick="viewBigPic(this);"/></span></p>';
				}else{
					optionHtml += '<p><input type="checkbox" name="option_'+questionId+'" value="'+options[i].id+'" disabled="disabled"/>'
					+optionName[i+1]+'.<span><img class="option_img" src="'+options[i].content+'" onclick="viewBigPic(this);"/></span></p>';
				}
			}
			
		}
	}else if(type == 4){//判断题
		if(myanswer == 0){
			optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="1" disabled="disabled"/>A.<span>正确</span></p>';
			optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="0" checked="checked" disabled="disabled"/>B.<span>错误</span></p>';
		}else if(myanswer == 1){
			optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="1" checked="checked" disabled="disabled"/>A.<span>正确</span></p>';
			optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="0" disabled="disabled"/>B.<span>错误</span></p>';
		}else{
			optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="1" disabled="disabled"/>A.<span>正确</span></p>';
			optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="0" disabled="disabled"/>B.<span>错误</span></p>';
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
				if(answerKeys[i] != ''){
					optionHtml += '<p><input type="text" name="option_'+questionId+'" value="'+answer[i]+'" disabled="disabled"/></p>';
				}
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
		if(url != ''){
			if(mediaType == 1){//图片
				mediaHtml += '<div id="focus_'+questionId+'" class="focus">';
				mediaHtml += '<ul>';
				for(var i=0;i<url.length;i++){
					mediaHtml += '<li><img class="media_img" src="'+url[i]+'" onclick="viewBigPic(this);"/></li>';
				}
				mediaHtml += '</ul></div>';
			}else if(mediaType == 2){//音频
				for(var i=0;i<url.length;i++){
					mediaHtml += '<div style="padding: 10px 0px 10px 70px;">';
					mediaHtml += genAudioTag(url[i]);
					mediaHtml += '</div>';
				}
			}else if(mediaType == 3){//视频
				mediaHtml += '<a href="javascript:void(0);" id="btn_show" name="'+questionId+'">查看</a>';
				mediaHtml += '<span id="Content" style="display:none;">';
				mediaHtml += '<div id="al" style="padding-left: 5px;">';
				for(var i=0;i<url.length;i++){
					mediaHtml += genVideoTagWithoutInitScript(url[i]);
				}
				mediaHtml += '</div>';
				mediaHtml += '</span>';
			}
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

/*移出错题集  */
function deleteWrong(wrongId){
	dialog.confirm('确认移出吗？', function(){
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{"id":wrongId},
			url: "<%=request.getContextPath()%>/myExamManage/deleteWrongCard2.action",
			success:function(data){
				if(data == "SUCCESS"){
					dialog.alert("操作成功！", function (){
						if(wrongType == 2){
							window.location = "<%=request.getContextPath()%>/myExamManage/gotoSimulateWrongCard.action";
						}else{
							window.location = "<%=request.getContextPath()%>/myExercise/gotoExerciseWrongList.action";
						}
					});
				}else{
					dialog.alert("操作失败！");
				}
		    }
		});
	});
}


</script>
</head>

<body>
<div id="course_all">
<div class="notes_list" style="padding-bottom: 0px;width:1406px;">
    <!-- <h3>错题详情</h3> -->
    <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">错题详情</span>
	</div>
	<div class="on_test" style="margin-top:0px;">
    	<div class="left_test" id="wrongDiv" style="padding-top:12px;">
    	
        </div>
    </div>
 </div>
 </div>
</body>
</html>
