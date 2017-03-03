<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>再次练习</title>
<style type="text/css">
.buttonClass{
	font-size:14px;
	font-family:微软雅黑;
	margin-right:5px;
	margin-top:2px;
	cursor: pointer;
	border-radius:2px;
	border:none;
	background-color:#d60500;
	padding-left: 15px;
	padding-right: 15px;
	padding-bottom:4px;
	padding-top:4px;
	color: #ffffff;
}
</style>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/slider.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/exam_paper/image-slider.js"></script>
<script src="<%=request.getContextPath() %>/js/layer/layer.js"></script>
<jsp:include page="../common/mediaelementInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
var wrongId = '${wrong_id}';
var optionName = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var qt = ["","主观题","单选题","多选题","判断题","填空题","组合题","多媒体题",""];
var html = '';
var wrongId = '${wrong_id}';

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
				/*隐藏域  */
				html += '<input type="hidden" id="id_'+id+'" name="questionId" value="'+id+'"/>';
				html += '<input type="hidden" id="parentid_'+id+'" name="parentId" value="0"/>';
				html += '<input type="hidden" id="type_'+id+'" name="questionType" value="'+type+'"/>';
				html += '<input type="hidden" id="ua_'+id+'" name="userAnswer" value="" />';
				html += '<input type="hidden" id="sub_'+id+'" name="subQuestionId" value="'+questionBean.questionIdList+'" />';
				html += '<h5><span style="color:black;">问题：</span>';
				html += questionBean.content + '：';
				html += '</h5>';
				if(type == 6){//组合题
					var subQuestions = questionBean.subQuestions;
					//alert(JSON.stringify(subAnswers));
					if(subQuestions != null){
						for(var j=0;j<subQuestions.length;j++){
							var subQuestion = subQuestions[j];
							type = subQuestion.type;
							id = subQuestion.id;
							options = subQuestion.options;
							isMedia = subQuestion.isMultimedia;
							mediaType = subQuestion.multimediaType;
							mediaUrl = subQuestion.multimediaUrl;
							answerKeys = subQuestion.answerKeys;
							html += '<input type="hidden" id="id_'+id+'" name="questionId" value="'+id+'"/>';
							html += '<input type="hidden" id="parentid_'+id+'" name="parentId" value="'+questionBean.id+'"/>';
							html += '<input type="hidden" id="type_'+id+'" name="questionType" value="'+type+'"/>';
							html += '<input type="hidden" id="ua_'+id+'" name="userAnswer" value=""/>';
							html += '<input type="hidden" id="sub_'+id+'" name="subQuestionId" value="0" />';
							html += '<p>问题'+(j+1)+'：'+setNull(subQuestion.content)+'</p>';
							//题目状态标识 1-已完成 2-未完成 3-已标记 
							if(type == 5){//填空题的话，将answerKeys当作options
								html += concatOptions(id,type,isMedia,mediaType,mediaUrl,answerKeys,questionBean.questionIdList,questionBean.id);
							}else{
								html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options,questionBean.questionIdList,questionBean.id);
							}
						}
					}
				}else{//其它题型
					if(type == 5){//填空题的话，将answerKeys当作options
						html += concatOptions(id,type,isMedia,mediaType,mediaUrl,answerKeys,null,null);
					}else{
						html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options,null,null);
					}
				}
				html += '</div>';
				html += '<div id="submitDiv" style="padding-left:20px;"><input type="button" class="buttonClass" value="提交答案" onclick="submitQuestion();"/><div>';
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

/*设置用户答案  */
function setCheckAnswer(obj,questionId,type){
	var parentId = $(obj).attr("parentId");
	var idList = $(obj).attr("idList");
	if(type == 1){//主观题
		$("#ua_"+questionId).val(($.trim($(obj).val())).replace(/,/g,"@_@"));
	}else if(type == 2){//单选题
		$("#ua_"+questionId).val($(obj).val());
	}else if(type == 3){//多选题
		var an = jqchk('option_'+questionId);
		$("#ua_"+questionId).val(an);
	}else if(type == 4){//判断题
		$("#ua_"+questionId).val($(obj).val());
	}else if(type == 5){//填空题
		var str = '';
		$("input[name=option_"+questionId+"]").each(function(index,element){
			str += ($.trim($(this).val())).replace(/,/g,"@_@") + "##**##";
		})
		str = str.substr(0,str.length-6);
		$("#ua_"+questionId).val(str);
	}
	if(parentId != 0){
		var zuheAns = "";
		var idArray = idList.split(",");
		for(var i=0;i<idArray.length;i++){
			if($("#ua_"+idArray[i]).val() == ''){
				zuheAns += "-";
			}else{
				zuheAns += $("#ua_"+idArray[i]).val();
			}
			if(i != idArray.length-1){
				zuheAns += "@_@";
			}
		}
		$("#ua_"+parentId).val(zuheAns);	
	}
	//设置右侧内容
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
function concatOptions(questionId,type,isMedia,mediaType,mediaUrl,options,idList,parentId){
	var optionHtml = '';
	optionHtml += concatMedia(questionId,isMedia,mediaType,mediaUrl);
	if(type == 1){//主观题
		optionHtml += '<p><textarea id="option_'+questionId+'" name="option_'+questionId+'" onblur="setCheckAnswer(this,'+questionId+','+type+');" idList="'+
					idList+'" parentId="'+parentId+'"></textarea></p>';
	}else if(type == 2){//单选题
		for(var i=0;i<options.length;i++){
			var otype = options[i].type;
			if(otype == 1){//文本
				optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="'+options[i].id
				+'" onclick="setCheckAnswer(this,'+questionId+','+type+');" idList="'+idList+'" parentId="'+parentId+'"/>'
				+optionName[i]+'.<span>'+setNull(options[i].content)+'</span></p>';
			}else{
				optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="'+options[i].id
				+'" onclick="setCheckAnswer(this,'+questionId+','+type+');" idList="'+idList+'" parentId="'+parentId+'"/>'
				+optionName[i]+'.<span><img class="option_img" src="'+options[i].content+'" onclick="viewBigPic(this);"/></span></p>';
			}
			
		}
	}else if(type == 3){//多选题
		for(var i=0;i<options.length;i++){
			var otype = options[i].type;
			if(otype == 1){//文本
				optionHtml += '<p><input type="checkbox" name="option_'+questionId+'" value="'+options[i].id
				+'" onclick="setCheckAnswer(this,'+questionId+','+type+');" idList="'+idList+'" parentId="'+parentId+'"/>'
				+optionName[i]+'.<span>'+setNull(options[i].content)+'</span></p>';
			}else{
				optionHtml += '<p><input type="checkbox" name="option_'+questionId+'" value="'+options[i].id
				+'" onclick="setCheckAnswer(this,'+questionId+','+type+');" idList="'+idList+'" parentId="'+parentId+'"/>'
				+optionName[i]+'.<span><img class="option_img" src="'+options[i].content+'" onclick="viewBigPic(this);"/></span></p>';
			}
			
		}
		//optionHtml += '<input type="hidden" id="ca_'+questionId+'" name="correctAnswer" value="'+ans.substr(0,ans.length-6)+'"/>';
	}else if(type == 4){//判断题
		optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="1" onclick="setCheckAnswer(this,'+questionId+','+type+');" idList="'+idList+'" parentId="'+parentId+'"/>A.<span>正确</span></p>';
		optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="0" onclick="setCheckAnswer(this,'+questionId+','+type+');" idList="'+idList+'" parentId="'+parentId+'"/>B.<span>错误</span></p>';
	}else if(type == 5){//填空题
		var answerKeys = [];
		if(options != null && options != ''){
			answerKeys = options.split(" ");
		}
		for(var i=0;i<answerKeys.length;i++){
			if(answerKeys[i] != ''){
				optionHtml += '<p><input type="text" name="option_'+questionId+'" onblur="setCheckAnswer(this,'+questionId+','+type+');" idList="'+idList+'" parentId="'+parentId+'"/></p>';
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
				//var initMediaPlayer = '<script type="text/javascript">$("audio,video").mediaelementplayer();</' + 'script>';
				for(var i=0;i<url.length;i++){
					mediaHtml += '<div style="padding: 10px 0px 10px 70px;">';
		            mediaHtml += genAudioTag(url[i]);
					mediaHtml += '</div>';
				}
			}else if(mediaType == 3){//视频
				mediaHtml += '<a href="javascript:void(0);" id="btn_show_'+questionId+'" name="'+questionId+'">查看</a>';
				mediaHtml += '<span id="Content_'+questionId+'" style="display:none;">';
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

//jquery获取复选框值 
function jqchk(name){ 
	 var chk_value ='';
	$('input[name="'+name+'"]:checked').each(function(){ 
		chk_value = chk_value + $(this).val() + ","; 
	}); 
	if(chk_value != ''){
		return chk_value.substr(0,chk_value.length-1);
	}
	return chk_value;
}

//获得单选按钮选中的值
function GetRadioValue(RadioName){
    var obj;    
    obj=document.getElementsByName(RadioName);
    if(obj!=null){
        var i;
        for(i=0;i<obj.length;i++){
            if(obj[i].checked){
                return obj[i].value;            
            }
        }
    }
    return null;
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
/*提交试卷  */
function submitQuestion(){
	dialog.confirm('确认提交吗？', function(){
		$("input[id^='ua_']").each(function(index,element){
			if($.trim($(this).val()) == ''){
				$(this).val("-");
			}
		});
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data: $("#questionForm").serialize(),
			url: "<%=request.getContextPath()%>/myExercise/submitAnswer.action?isAddWrong=0",
			success:function(data){
				if(data != null && data != 'error'){
					if(data == '正确'){
						$.ajax({
							type:"POST",
							async:true,  //默认true,异步
							data:{"id":wrongId},
							url: "<%=request.getContextPath()%>/myExamManage/deleteWrongCard2.action",
							success:function(data){
								if(data == "SUCCESS"){
									dialog.alert("你的答案：正确", function (){
										window.location = "<%=request.getContextPath()%>/myExercise/gotoExerciseWrongList.action";
									});
								}else{
									dialog.alert("操作失败！");
								}
						    }
						});
					}else{
						dialog.alert("你的答案：错误");
					}
					
				}else{
					dialog.alert("提交失败！");
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
    <!-- <h3>再次练习</h3> -->
    <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">再次练习</span>
	</div>
    <form id="questionForm">
		<div class="on_test" style="margin-top:0px;">
	    	<div class="left_test" id="wrongDiv" style="padding-top:0px;">
	    	
	        </div>
	    </div>
    </form>
 </div>
 </div>
</body>
</html>
