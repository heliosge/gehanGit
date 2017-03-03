<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>答卷详情</title>
<style type="text/css">
.correct_answer{/* height:40px; */background-color:#e9f7e0;line-height:40px;font-family: '微软雅黑';font-weight:bold;padding-left:10px; word-break: break-all;}
.wrong_answer{/* height:40px; */background-color:#f7e8eb;line-height:40px;font-family: '微软雅黑';font-weight:bold;padding-left:10px; word-break: break-all;}
.answerMargin{margin-top: 10px;}
.addWrong{float:right;padding-right:10px;}
</style>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/slider.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/json2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/exam_paper/image-slider.js"></script>
<jsp:include page="../common/mediaelementInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';//登录用户ID
var exam_assign_id = '${exam_assign_id}';//考试记录ID
var paramMap = ${paramMap};
var organizing_mode = paramMap[0].organizing_mode;//组卷方式 1标准组卷2随机组卷
var is_answer_public  = paramMap[0].is_answer_public;//答案是否公开
var is_marked = paramMap[0].is_marked;//试卷是否批阅完成
var isPy = ${isPy};
var optionName = ["","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var isOrNo = ["错误","正确"];
var typeArray = new Array();
var qt = ["","主观题","单选题","多选题","判断题","填空题","组合题","多媒体题",""];
var html = '';
$(function(){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":exam_assign_id},
		url: "<%=request.getContextPath()%>/myExamManage/getGradeOtherInfo.action",
		success:function(data){
			if(data != null){
				$("#title").text(data.title);
				$("#totalScore").text("总分："+data.totalScore);
				$("#passScore").text("及格分："+data.passScore);
				$("#duration").text("考试时长："+data.duration);
			}
	    }
	});
});

$(function(){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"exam_assign_id":exam_assign_id,"user_id":userId},
		url: "<%=request.getContextPath()%>/myExamManage/getExamAssignDetail.action",
		success:function(data){
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
					html += '<div class="dx" flag="'+displayType+'">';
					html += '<h5><span>('+userAnswerBean.score+'分)</span>'+userAnswerBean.orderNum+'.';
					html += questionBean.content + '：</h5>';
					var zuheAns = '';
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
								html += '<p><span>('+subAnswers[j].score+'分)</span>问题'+subAnswers[j].subOrderNum+'：'+setNull(subQuestion.content)+'</p>';
								//题目状态标识 1-已完成 2-未完成 3-已标记 
								if(type == 5){//填空题的话，将answerKeys当作options
									html += concatOptions(id,type,isMedia,mediaType,mediaUrl,answerKeys,subAnswers[j].answer);
								}else{
									html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options,subAnswers[j].answer);
								}
								zuheAns += setZuHeCorrectAnswer(j+1,type,subAnswers[j].correctAnswer);
								//setUserAnswer(id,type,subAnswers[j].answer);
								//setCorrectAnswer(type,subAnswers[j].correctAnswer);
							}
						}
					}else{//其它题型
						if(type == 5){//填空题的话，将answerKeys当作options
							html += concatOptions(id,type,isMedia,mediaType,mediaUrl,answerKeys,userAnswerBean.answer);
						}else{
							html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options,userAnswerBean.answer);
						}
						//setUserAnswer(id,type,userAnswerBean.answer);
						//setCorrectAnswer(type,userAnswerBean.correctAnswer);
					}
					if(userAnswerBean.score == userAnswerBean.getScore){
						html += '<div class="correct_answer answerMargin">你的答案：';
						html += '<span style="color:#6cb53c;">正确</span>';
						html += '</div>';
					}else{
						html += '<div class="wrong_answer answerMargin">你的答案：';
						html += '<span style="color:#36be82;">错误</span>';
						if(!isPy){
							if(userAnswerBean.isFavorated == null || userAnswerBean.isFavorated == 0){
								html += '<em class="addWrong" id="wrong_'+questionBean.id+'"><a href="javascript:void(0);" style="color:#36be82;" onclick=addWrong('+questionBean.id+','+userAnswerBean.id+') >加入错题集</a></em>';
							}
						}
						html += '</div>';
					}
					if(is_answer_public != null && is_answer_public != 0 && is_marked == 1){
						html += '<div class="correct_answer answerMargin">正确答案：';
						if(questionBean.type == 6){
							html += '<span>'+zuheAns+'</span>';
						}else if(questionBean.type == 1 || questionBean.type == 5){
							html += '<span>'+userAnswerBean.correctAnswer+'</span>';
						}else{
							setCorrectAnswer(type,userAnswerBean.correctAnswer);
						}
						html += '</div>';
					}
					html += '</div>';
				}//for end
				$("#questionDiv").html(html);
				initElement();
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
				$("div .dx").children("h5").find(".question_content_img").each(function(index,element){
					$(this).on("click",function(){
						viewBigPic(this);
					});
				});
			}//data end
	    }
	});
});


//初始化部分元素
function initElement(){
	typeArray.sort();
	//初始化头部各题型按钮
	for(var n=0;n<typeArray.length;n++){
		var num = typeArray[n];
		$("#inputDiv").append('<input type="button" style="margin-right: 5px;" value="'+qt[num]+'" id="input_'+num+'" onclick="selectQuestion('+num+');"/>');
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

//设置正确答案
function setCorrectAnswer(type,correctAnswer){
	if(type == 2){//单选
		html += '<span>'+ optionName[correctAnswer] +'</span>';
	}else if(type == 3){//多选
		html += '<span>';
		cArray = correctAnswer.split(",");
		for(var i=0;i<cArray.length;i++){
			var num = cArray[i];
			html += optionName[num];
			if(i!=cArray.length - 1){
				html += ',';
			}
		}
		html += '</span>';
	}else if(type == 1 || type == 5){//主观题、填空题
		html += '<span>'+ correctAnswer +'</span>';
	}else{//判断题
		html += '<span>'+ isOrNo[correctAnswer] +'</span>';
	}
}

//设置组合题正确答案
function setZuHeCorrectAnswer(num,type,correctAnswer){
	var zuhe = '问题'+num+'：';
	if(type == 2){//单选
		zuhe += '<span>'+ optionName[correctAnswer] +'</span>';
	}else if(type == 3){//多选
		zuhe += '<span>'
		cArray = correctAnswer.split(",");
		for(var i=0;i<cArray.length;i++){
			var num = cArray[i];
			zuhe += optionName[num];
			if(i!=cArray.length - 1){
				zuhe += ',';
			}
		}
		zuhe += '</span>';
	}else if(type == 1 || type == 5){//主观题、填空题
		zuhe += '<span>'+ correctAnswer +'</span>';
	}else{//判断题
		zuhe += '<span>'+ isOrNo[correctAnswer] +'</span>';
	}
	return zuhe+"；";
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
		//$("#div_"+type).css("display","block");
		//$("#questionDiv").find("div[flag ='flag_"+type+"']").css("display","block");
		//$("#questionDiv").find("div[flag!='flag_"+type+"']").css("display","none");
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
/*加入错题集  */
function addWrong(questionId,answer_id){
	dialog.confirm('确认加入吗？', function(){
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:{"fromUserId":userId,"examResultId":exam_assign_id,"questionId":questionId,"answer_id":answer_id,"type":1},
			url: "<%=request.getContextPath()%>/myExamManage/addWrongCard.action",
			success:function(data){
				if(data == "SUCCESS"){
					dialog.alert("操作成功！", function (){
						$("#wrong_"+questionId).remove();
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
	<div class="on_test" style="margin-top:30px;width: 1406px;">
    	<div class="test_title" style="width: 1406px;">
        	<h3 id="title"></h3>
        </div>
        <div class="test_tool" style="width: 1406px;">
        	<div class="tool_top">
            	<p>考试详情：
                	<span id="totalScore"></span>
                    <span id="passScore"></span>
                    <span id="duration"></span>
                </p>
            </div>
            <div class="tool_bottom" id="inputDiv">
            	<input type="button" value="显示全部" class="input_a" id="input_0" onclick="selectQuestion(0);"/>
               <!--  <input type="button" value="填空题" />
                <input type="button" value="判断题" />
                <input type="button" value="多选题" />
                <input type="button" value="单选题" />
                <input type="button" value="主观题" />
                <input type="button" value="组合题" />
                <input type="button" value="多媒体题" /> -->
            
            </div>
        </div>
    	<div class="left_test" id="questionDiv">
        	<!-- <div class="dx">
            	<h5><span>(1分)</span>1.全新QX250具备灵活的车内空间组合，其后排座椅放倒比例为：</h5>
                <p><input type="radio" name="dx" checked="checked" />A.<span>60:40</span></p>
                <p><input type="radio" name="dx" />B.<span>70:30</span></p>
                <p><input type="radio" name="dx" />C.<span>50:50</span></p>
                <p><input type="radio" name="dx" />D.<span>40:20:20</span></p>
            </div>
            <div class="dx">
            	<h5><span>(3分)</span>2.全新QX250具备灵活的车内空间组合，其后排座椅放倒比例为：</h5>
                <p><input type="checkbox" name="da" checked="checked"/>A.<span>60:40</span></p>
                <p><input type="checkbox" name="da" checked="checked" />B.<span>70:30</span></p>
                <p><input type="checkbox" name="da" />C.<span>50:50</span></p>
                <p><input type="checkbox" name="da" />D.<span>40:20:20</span></p>
            </div>
            <div class="dx">
            	<h5><span>(3分)</span>3.全新QX250具备灵活的车内空间组合，其后排座椅放倒比例为50:50，此说法：</h5>
                <p><input type="radio" name="pd" checked="checked" />A.<span>正确</span></p>
                <p><input type="radio" name="pd" />B.<span>错误</span></p>
                
            </div>
            <div class="dx">
            	<h5><span>(10分)</span>4.全新QX250具备灵活的车内空间组合，其后排座椅放倒比例为:</h5>
                <p><input type="text" name="tk" /></p>
                <p><input type="text" name="tk" /></p>
                
            </div>
            <div class="dx">
            	<h5><span>(12分)</span>5.全新QX250具备灵活的车内空间组合，其后排座椅放倒比例为:</h5>
                <p><textarea></textarea></p>
            </div>
            <div class="dx">
            	<h5><span>(12分)</span>6.这是一段描述题干，这是一段描述题干，这是一段描述题干</h5>
                <p>问题1：是8级么</p>
                <p><input type="text" name="tk" /></p>
                <p>问题2：是9级么</p>
                <p><input type="text" name="tk" /></p>
                
            </div>
             <div class="dx">
            	<h5><span>(12分)</span>7.视频说的是什么:</h5>
                <a href="#" id="btn_show">查看</a>
                <span id="Content" style="display:none;">
                	<img src="img/player.png">
                </span>
                <p><textarea></textarea></p>
            </div> -->
        </div>
    </div>
</body>
</html>
