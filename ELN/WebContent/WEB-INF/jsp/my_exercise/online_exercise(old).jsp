<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>在线练习</title>
<style type="text/css">
.buttonClass{
	font-size:16px;
	font-family:微软雅黑;
	margin-right:5px;
	margin-top:2px;
	cursor: pointer;
	border-radius:2px;
	border:none;
	background-color:#f5a743;
	padding-left: 15px;
	padding-right: 15px;
	padding-bottom:4px;
	padding-top:4px;
	color: #ffffff;
}
.markImg2{position: relative;top: 6px;left: -40px;}
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
var idStr = '${idStr}';
var number = ${questionCount};//题目总数
var uncompletedNum = number;//未完成
var completedNum = 0;//已完成
var correctNum = 0;//正确数
var wrongNum = 0;//错误数

/*加载试卷内容  */
var typeArray = new Array();
var optionName = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var qt = ["","主观题","单选题","多选题","判断题","填空题","组合题","多媒体题",""];

var idArray = [];
var _id;
var _index = 0;
$(function(){
	$("#totalQuestionNum").text(number);
	$("#uncompletedNum").text(number);
	if(idStr != null &&  idStr != ''){
		idArray = idStr.split(",");
		_id = idArray[_index];
		$("#questionDiv").html('<span style="padding-left:30px;font-size:24px;">正在加载中，请稍后...<span>');
		getPaperContent(_id);
	}else{
		$("#questionDiv").html('<span style="padding-left:30px;font-size:24px;">暂无试题<span>');
	}
});

function getPaperContent(_id){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":_id},
		dataType:"json",
		url: "<%=request.getContextPath()%>/myExercise/GenerateExercise.action",
		success:function(data){
			var html = '';
			$("#questionDiv").html("");
			if(data != null){
				var questionBean = data;
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
				html += '<div class="dx" flag="'+displayType+'" name="number_'+_index+'" id="div_'+id+'">';
				/*隐藏域  */
				html += '<input type="hidden" id="id_'+id+'" name="questionId" value="'+id+'"/>';
				html += '<input type="hidden" id="parentid_'+id+'" name="parentId" value="0"/>';
				html += '<input type="hidden" id="type_'+id+'" name="questionType" value="'+type+'"/>';
				html += '<input type="hidden" id="ua_'+id+'" name="userAnswer" value="" />';
				html += '<input type="hidden" id="sub_'+id+'" name="subQuestionId" value="'+questionBean.questionIdList+'" />';
				//题目分值及序号
				html += '<h5>';
				html += (_index+1);
				//题目题干
				html += '.'+questionBean.content+'</h5>';
				if(type == 6){//组合题
					var subQuestions = questionBean.subQuestions;
					//alert(JSON.stringify(subQuestions));
					if(subQuestions != null){
						for(var j=0;j<subQuestions.length;j++){
							type = subQuestions[j].type;
							id = subQuestions[j].id;
							options = subQuestions[j].options;
							isMedia = subQuestions[j].isMultimedia;
							mediaType = subQuestions[j].multimediaType;
							mediaUrl = subQuestions[j].multimediaUrl;
							answerKeys = subQuestions[j].answerKeys;
							html += '<input type="hidden" id="id_'+id+'" name="questionId" value="'+id+'"/>';
							html += '<input type="hidden" id="parentid_'+id+'" name="parentId" value="'+questionBean.id+'"/>';
							html += '<input type="hidden" id="type_'+id+'" name="questionType" value="'+type+'"/>';
							html += '<input type="hidden" id="ua_'+id+'" name="userAnswer" value=""/>';
							html += '<input type="hidden" id="sub_'+id+'" name="subQuestionId" value="0" />';
							html += '<p>问题'+(j+1)+'：'+setNull(subQuestions[j].content)+'</p>';
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
				}//else
				html += '</div>';
				html += '<div id="submitDiv" style="padding-left:20px;"><input type="button" class="buttonClass" value="提交" onclick="submitQuestion();"/><div>';
				$("#questionDiv").html(html);
				$("a[id^='btn_show_']").each(function(index,element){
					var showId = $(this).attr("name");
					$('#btn_show_'+showId).on('click', function(){
						showVideoPlayerDialog('#Content_'+showId);
					});
				});
				$("div[id^='focus_']").each(function(index,element){
					_topImageShow($(this).attr("id"));
				});
			}
	    }
	});
}


function preQuestion(){
	if(idStr != null &&  idStr != ''){
		if(_index > 0){
			_index--;
			_id = idArray[_index];
			getPaperContent(_id);
		}else{
			dialog.alert("已经是第一题了！");
		}
	}
	
}
function nextQuestion(){
	if(idStr != null &&  idStr != ''){
		if(_index < number-1){
			_index++;
			_id = idArray[_index];
			getPaperContent(_id);
		}else{
			dialog.alert("已经是最后一题了！");
		}
	}
}

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
				+optionName[i]+'.<span><img class="option_img" src="'+options[i].content+'"/></span></p>';
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
				+optionName[i]+'.<span><img class="option_img" src="'+options[i].content+'"/></span></p>';
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
		if(mediaType == 1){//图片
			mediaHtml += '<div id="focus_'+questionId+'" class="focus">';
			mediaHtml += '<ul>';
			for(var i=0;i<url.length;i++){
				mediaHtml += '<li><img class="media_img" src="'+url[i]+'"/></li>';
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
	return mediaHtml;
}

/*视频弹出框  */
function showVideo(showId){
	showVideoPlayerDialog('#Content_'+showId);
}

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


//jquery获取复选框值 
function jqchk(name){ 
	 var chk_value ='';
	$('input[name="'+name+'"]:checked').each(function(){ 
		chk_value = chk_value + $(this).val() + "##**##"; 
	}); 
	if(chk_value != ''){
		return chk_value.substr(0,chk_value.length-6);
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

var keyWord;
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
			async:true,  //默认true,异步
			data: $("#questionForm").serialize(),
			url: "<%=request.getContextPath()%>/myExercise/submitAnswer.action",
			success:function(data){
				completedNum++;
				uncompletedNum--;
				$("#completedNum").text(completedNum);
				$("#uncompletedNum").text(uncompletedNum);
				$("#submitDiv").remove();
				if(data != null && data != 'error'){
					if(data == '正确'){
						correctNum++;
						$("#correctNum").text(correctNum);
					}else{
						wrongNum++;
						$("#wrongNum").text(wrongNum);
					}
					//dialog.alert("你的答案："+data);
					var d = dialog({
						height: 50,
						width: 250,
					    title: '提示',
					    content: '你的答案：'+data,
					    button: [{
					             	value: '进入错题集',
					                callback: function () {
					                	dialog.confirm('确认退出练习吗？', function(){
									    	window.location = '<%=request.getContextPath()%>/myExercise/gotoExerciseWrongList.action';
								    	});
					                 }
					             }
					         ],
					    okValue: '下一题',
					    ok: function () {
					    	nextQuestion();
					    }/* ,
					    cancelValue: '',
					    cancel: function () {
					    	
					    } */
					});
					d.showModal();
				}else{
					dialog.alert("提交失败！");
				}
		    }
		}); 
	});
}

//屏蔽浏览器右键菜单
function nocontextmenu(){
	if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){  
        return false;
   	}else{
   		event.cancelBubble = true;
   	 	event.returnValue = false;
   	} 
} 
function norightclick(e){
 	if (window.Event){ 
 	 	if(e.which == 2 || e.which == 3){
 	 		return false; 
 	 	}
 	}else if(event.button == 2 || event.button == 3){ 
   		event.cancelBubble = true 
   		event.returnValue = false; 
   		return false; 
  	} 
}
document.onkeydown = function (e) {
    var ev = window.event || e;
    var code = ev.keyCode || ev.which;
    if (code == 116) {
        if(ev.preventDefault) {
            ev.preventDefault();
        } else {
            ev.keyCode = 0;
            ev.returnValue = false;
        }
    }
}

if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
	//alert('你是使用IE');
	document.oncontextmenu = nocontextmenu; // for IE5+
}else{
	document.oncontextmenu = nocontextmenu; // for IE5+
	document.onmousedown = norightclick; // for all others
}

/*退出练习  */
function outExercise(){
	dialog.confirm('是否确定退出练习，一旦退出，本次练习结束？', function(){
		window.onbeforeunload = function(b) {
	       
	    }
		window.opener=null;
		window.open('','_self');
		window.close();
	});
	/* var userAgent = navigator.userAgent;
	if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Chrome") !=-1) {
	   window.location.href="about:blank";
	} else {
	   window.opener = null;
	   window.open("", "_self");
	   window.close();
	}  */
}

/*关闭浏览器  */
window.onbeforeunload = onbeforeunload_handler;  
function onbeforeunload_handler(){  
    var warning="是否确定退出练习，一旦退出，本次练习结束？";          
    return warning;  
}

/* var UnloadConfirm = {};
UnloadConfirm.MSG_UNLOAD = "是否确定退出练习，一旦退出，本次练习结束？";
UnloadConfirm.set = function(a) {
    window.onbeforeunload = function(b) {
        b = b || window.event;
        b.returnValue = a;
        return a;
    }
};
UnloadConfirm.clear = function() {
    fckDraft.delDraftById();
    window.onbeforeunload = function() {}
};
UnloadConfirm.set(UnloadConfirm.MSG_UNLOAD); */

//判断数组中是包含某个元素 通用方法
Array.prototype.contains = function (element){
	for (var i=0; i<this.length;i++){
		if (this[i] == element){ 
			return true; 
		} 
	} 
	return false; 
}; 


function CloseWebPage(){
	if (navigator.userAgent.indexOf("MSIE") > 0) {
		if(navigator.userAgent.indexOf("MSIE 6.0") > 0){
			 window.opener = null;
			 window.close();
		}else{
			 window.open('', '_top');
			 window.top.close();
		}
	 }else if(navigator.userAgent.indexOf("Firefox") > 0){
	  	window.location.href = 'about:blank ';
	 }else{
		window.opener = null;
		window.open('', '_self', '');
		window.close();
	 }
}

function closeme() {
    var browserName = navigator.appName;
    if (browserName == "Netscape") {
        window.open('', '_parent', '');
        window.close();
    } else if (browserName == "Microsoft Internet Explorer") {
        window.opener = "whocares";
        window.close();
    }
}

</script>
</head> 
<body>
	<div class="on_test">
    	<div class="test_title">
        	<h3><span id="categoryName" style="float: none;font-size: 30px;">${categoryName }</span></h3>
        </div>
        <div class="test_tool" style="height: 60px;">
            <div class="tool_bottom" id="inputPreNext">
            	<!-- <input type="button" style="margin: 10px;" value="上一题" id="input_pre" onclick="preQuestion();"/> -->
            	<input type="button" style="margin: 10px;" value="下一题" id="input_next" onclick="nextQuestion();"/>
            </div>
        </div>
        <!-- window.opener=null;window.close(); -->
        <form id="questionForm" method="post" action="" onsubmit="">
	    	<div class="left_test" id="questionDiv">
	    	
	        </div>
        </form>
        <div class="right_test" style="position: fixed;right:0px;">
        	<div class="right_top">
            	<input type="button" value="退出练习" onclick="outExercise();"/>
                <p>
                	<span style="margin-right:0px;color: #333333;font-weight: normal;">总题目：<span id="totalQuestionNum" style="padding-right: 10px;margin-right: 50px;"></span></span>
                    <span style="margin-right:0px;color: #333333;font-weight: normal;">已完成：<span id="completedNum" style="margin-right: 50px;">0</span><img src="<%=request.getContextPath() %>/images/img/yellow.png" class="markImg2"/></span>
                </p>
                <p>
                	<span style="margin-right:0px;color: #333333;font-weight: normal;">未完成：<span id="uncompletedNum" style="margin-right: 50px;"></span><img src="<%=request.getContextPath() %>/images/img/bg_21.png" class="markImg2"/></span>
                </p>
            </div>
            <div class="right_bottom">
                <div id="rightDiv" style="padding-left: 10px;">
                	<span>正确：</span><span id="correctNum">0</span>
                	<span>错误：</span><span id="wrongNum">0</span>
	            </div>
            </div>
        </div>
    </div>
</body>
<script src="<%=request.getContextPath() %>/js/jquery.artwl.thickbox.js"></script>
</html>
