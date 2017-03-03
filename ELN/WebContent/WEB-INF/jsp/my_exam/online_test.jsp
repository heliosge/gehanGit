<%@page import="com.jftt.wifi.util.PropertyUtil"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>在线考试</title>
<style type="text/css">
	.markImg{vertical-align: middle;position: relative;top: -2px;cursor: pointer;padding-right: 5px;}
	.markImg2{position: relative;top: 6px;left: -40px;}
	
/* 	body {height:100%; overflow:auto;} */
	/* #testDiv{height:90%; overflow:auto;padding-top:169px;}
	#menu {width:100%; position:fixed;  z-index: 9999;background: #fff;} */
	/* * html #menu {position:absolute;  top:0;}  */
	/* html {overflow:auto !important; overflow:hidden;} */
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
var paramMap = ${paramMap};
var id = paramMap[0].id;//考试信息ID
var exam_type = paramMap[0].exam_type;//考试类型 1-普通考试 3-模拟考试
var duration = paramMap[0].duration;//考试时长
var exam_assign_id = paramMap[0].exam_assign_id;//考试记录ID
var is_auto_marking = paramMap[0].is_auto_marking;//是否自动阅卷
var display_mode = paramMap[0].display_mode;//试卷显示方式1整卷显示2单题可逆3单题不可逆
var allow_leave_times = paramMap[0].allow_leave_times;//允许离开考试次数
var is_anti_cheating = paramMap[0].is_anti_cheating;//是否开启防作弊功能
//var exam_status = paramMap[0].exam_status;//考试状态
var remaining_time = paramMap[0].remaining_time;//考试剩余时间
var submit_duration = paramMap[0].submit_duration;//允许最快交卷时间
//课程考试记录ID
var examRecordId = ${examRecordId};
var number = 0;//题目总数
var autoInfo = '';
//var userBean = ${userBean};
var isFree  = '${isFree}'; //商城课程用 -chenrui 
var testLimitMinute ='${TestLimitMinute}'; // -chenrui 
$(function(){
	/* if(userBean){
		$("#personName").text("测试");
		$("#headPhoto").attr("src",userBean.headPhoto);
	} */
	var clientHeight = window.screen.height;
	var clientWidth = window.screen.width;
	//alert(clientHeight);
	//clientHeight-169-120-31
	var divH = clientHeight-320-120;
	var divW = clientWidth;
	//alert(divW);
	$("#leftTopContent").css("width",divW-370);
	$("#rightDiv").css("height",divH);
	//$("#testDiv").css("height",clientHeight-290);
	$("#questionDiv").css("height",clientHeight-290);
	$("#questionDiv").css("width",divW-370);
	/* if(exam_type == 3){//模拟考试
		$("#leaveTime").css("display","none");
		$("#passScoreSpan").css("display","none");
		$("#durationSpan").css("display","none");
	}else{ */
		$(window).load(function() {
			timeCounter('timeCounter');
		});
	/* } */
	$("#userId").val(userId);
	$("#examId").val(id);
	$("#examType").val(exam_type);
	$("#examAssignId").val(exam_assign_id);
	$("#isAutoMarking").val(is_auto_marking);
	$("#examRecordId").val(examRecordId);
	if(display_mode == 1){//整卷显示
		$("#inputDiv").show();
		$("#inputPreNext").hide();
	}else if(display_mode == 2){//单题可逆
		$("#inputDiv").hide();
		$("#inputPreNext").show();
	}else{//单题不可逆
		$("#inputDiv").hide();
		$("#inputPreNext").show();
		$("#input_pre").hide();
		$("#rightBottomDiv").hide();
		$("#markedSpan").hide();
	}
	canPreview();
});
// ===== chenrui =========
/**
 * 商城课程中测试 控制预览
 */
function canPreview(){
	if(isFree != 'false'){ // 免费
		return ; 
	}
	// 在规定的设置时间后关闭当前窗口
	setTimeout(function(){
		dialog.alert('只可预览'+testLimitMinute+'分钟，请购买完整版!',function(){
			window.close();
		});
		// 防止不点击确定可以一直看，强制3秒后关闭
		setTimeout(function(){
			window.close();
		},3*1000);
	},1000*60*testLimitMinute);
}
// ===== chenrui =====
/*初始化考试头部信息  */
$(function(){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":id},
		url: "<%=request.getContextPath()%>/myExamManage/getExamScheduleInfo.action",
		success:function(data){
			if(data != null){
				$("#paperName").text(data.title);
				$("#paperTotalScore").text(data.totalScore);
				$("#totalScore").val(data.totalScore);
				$("#passScore").text(Math.round(data.totalScore*(data.passScorePercent/100)));
			}
	    }
	});
});

/*加载试卷内容  */
var typeArray = new Array();
var optionName = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var qt = ["","主观题","单选题","多选题","判断题","填空题","组合题","多媒体题",""];

$(function(){
	$("#questionDiv").html('<span style="padding-left:30px;font-size:24px;">正在加载中，请稍后...<span>');
	getPaperContent();
});
var html = '';
function getPaperContent(){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":id,"exam_type":exam_type,"exam_assign_id":exam_assign_id},
		dataType:"json",
		url: "<%=request.getContextPath()%>/myExamManage/initialExamPaper.action",
		success:function(data){
			$("#questionDiv").html("");
			if(data != null){
				/* $("#paperName").text(data.title);
				$("#paperTotalScore").text(data.totalScore);
				$("#totalScore").val(data.totalScore);
				$("#passScore").text(Math.round(data.totalScore*(data.passScorePercent/100))); */
				//if(duration != 0){
					$("#duration").text(data.duration);
				/* }else{
					$("#durationSpan").css("display","none");
				} */
				//$("#organizingMode").val(data.organizingMode);
				//$("#totalQuestionNum").text(data.totalQuestionNum);
				//$("#uncompletedNum").text(data.totalQuestionNum);
				number = data.paperBean.paperQuestionList.length;
				$("#totalQuestionNum").text(number);
				$("#uncompletedNum").text(number);
				var paperBean = data.paperBean;
				if(paperBean != null){
					//$("#paperName").text(paperBean.title);
					$("#organizingMode").val(paperBean.organizingMode);
					var paperQuestionList = paperBean.paperQuestionList;
					/* var arr = new Array();
					for(var i=0;i<paperQuestionList.length;i++){
						arr.push(i);
					}
					var arri = new Array();
					if(data.randomOrderMode == 1 || data.randomOrderMode == 3){
						arri = getArrayItems(arr,8);
					}else{
						arri = arr;
					} */
					
					if(paperQuestionList != null){
						for(var i=0;i<paperQuestionList.length;i++){
							var questionBean = paperQuestionList[i].questionBean;
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
							html += '<div class="dx" flag="'+displayType+'" name="number_'+i+'" id="div_'+id+'">';
							/*隐藏域  */
							html += '<input type="hidden" id="id_'+id+'" name="questionId" value="'+id+'"/>';
							html += '<input type="hidden" id="parentid_'+id+'" name="parentId" value="0"/>';
							html += '<input type="hidden" id="type_'+id+'" name="questionType" value="'+type+'"/>';
							html += '<input type="hidden" id="orderNum_'+id+'" name="orderNum" value="'+(i+1)+'"/>';
							html += '<input type="hidden" id="subOrderNum_'+id+'" name="subOrderNum" value="0"/>';
							html += '<input type="hidden" id="score_'+id+'" name="score" value="'+paperQuestionList[i].score+'"/>';
							html += '<input type="hidden" id="sub_'+id+'" name="subQuestionId" value="'+questionBean.questionIdList+'" />';
							/* if(type == 6){
								html += '<input type="hidden" id="ua_'+id+'" name="userAnswer" value="unwrite" />';
							}else{ */
							html += '<input type="hidden" id="ua_'+id+'" name="userAnswer" value="" />';
							/* } */
							
							//题目状态标识 1-已完成 2-未完成 3-已标记 
							html += '<input type="hidden" id="state_'+id+'" value="2" ismark="0" flag="parent" qid="'+id+'" questionnum="'+(i+1)+'" displaytype="'+displayType+'"/>';
							//题目分值及序号
							html += '<h5>';
							if(exam_type != 3){//不是模拟考试
								html += '<img src="<%=request.getContextPath() %>/images/img/bg_21.png" class="markImg"  style="width:10px;height:10px;position:relative;top:-2px;" flag="0" onclick="setMark(this,'+id+');"/>';
							}
							html += '<span>('+paperQuestionList[i].score+'分)</span>'+(i+1);
							//题目题干
							html += '.'+questionBean.content+'</h5>';
							html += '<a id="mao_'+id+'" name="mao_'+id+'"></a>';
							$("#type"+displayType).append('<li id=mark_"'+questionBean.id+'">'+(i+1)+'</li>');
							var scoreDetail = [];
							if(type == 6){//组合题
								var subQuestions = questionBean.subQuestions;
								if(paperQuestionList[i].scoreDetail != null){
									scoreDetail = paperQuestionList[i].scoreDetail.split(",");
								}
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
										html += '<input type="hidden" id="orderNum_'+id+'" name="orderNum" value="'+(i+1)+'"/>';
										html += '<input type="hidden" id="subOrderNum_'+id+'" name="subOrderNum" value="'+(j+1)+'"/>';
										html += '<input type="hidden" id="score_'+id+'" name="score" value="'+scoreDetail[j]+'"/>';
										html += '<input type="hidden" id="sub_'+id+'" name="subQuestionId" value="0" />';
										html += '<input type="hidden" id="ua_'+id+'" name="userAnswer" value=""/>';
										//题目状态标识 1-已完成 2-未完成 3-已标记 
										html += '<input type="hidden" id="state_'+id+'" value="2" ismark="0" flag="child">';
										
										html += '<p><span>('+scoreDetail[j]+'分)</span>问题'+(j+1)+'：'+setNull(subQuestions[j].content)+'</p>';
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
						}//for end
						$("#questionDiv").html(html);
					}//paperQuestionList end
				}//paperBean end
				$("a[id^='btn_show_']").each(function(index,element){
					var showId = $(this).attr("name");
					$('#btn_show_'+showId).on('click', function(){
						showVideoPlayerDialog('#Content_'+showId);
					});
				});
				if(display_mode == 2 || display_mode == 3){
					setQuestionDisplay();
				}
				initElement();
				setRightData();
				$("div[id^='focus_']").each(function(index,element){
					//alert($(this).attr("id"));
					_topImageShow($(this).attr("id"));
				});
				$("div .dx").children("h5").find(".question_content_img").each(function(index,element){
					$(this).on("click",function(){
						viewBigPic(this);
					});
				});
			}
	    }
	});
}

//初始化部分元素
function initElement(){
	typeArray.sort();
	//初始化头部各题型按钮
	for(var n=0;n<typeArray.length;n++){
		var num = typeArray[n];
		$("#inputDiv").append('<input type="button" style="margin-right: 5px;" value="'+qt[num]+'" id="input_'+num+'" onclick="selectQuestion('+num+');"/>');
	}
	//初始化右侧模块
	for(var m=0;m<4;m++){
		for(var n=0;n<typeArray.length;n++){
			var num = typeArray[n];
			$("#stateDiv"+m).append('<p>'+qt[num]+'</p>');
			$("#stateDiv"+m).append('<ul class="th" id="type'+num+'"></ul>');
		}
	}
}

var numIndex = 0;
function preQuestion(){
	if(numIndex > 0){
		numIndex--;
		setQuestionDisplay();
	}else{
		dialog.alert("已经是第一题了！");
	}
}
function nextQuestion(){
	//alert(numIndex + "   " + number);
	if(numIndex < number-1){
		numIndex++;
		setQuestionDisplay();
	}else{
		dialog.alert("已经是最后一题了！");
	}
}
function setQuestionDisplay(){
	$("div[name^='number_']").each(function(index,element){
		if($(this).attr("name") == 'number_'+numIndex){
			$(this).css("display","block");
		}else{
			$(this).css("display","none");
		}
	});
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

/*视频弹出框  */
function showVideo(showId){
	showVideoPlayerDialog('#Content_'+showId);
	/* dialog({
		title : "视窗",
		width : 800,
		height : 450,
		content :$('#Content_'+showId),
		fixed:true
	}).showModal(); */	
}

/*设置用户答案  */
function setCheckAnswer(obj,questionId,type){
	var parentId = $(obj).attr("parentId");
	var idList = $(obj).attr("idList");
	$("#state_"+questionId).val(1);
	if(type == 1){//主观题
		$("#ua_"+questionId).val(($.trim($(obj).val())).replace(/,/g,"@_@"));
		if($.trim($("#ua_"+questionId).val()) == ''){
			$("#state_"+questionId).val(2);
		}
	}else if(type == 2){//单选题
		$("#ua_"+questionId).val($(obj).val());
	}else if(type == 3){//多选题
		var an = jqchk('option_'+questionId);
		if(an == ''){
			$("#state_"+questionId).val(2);
		}
		$("#ua_"+questionId).val(an);
		
	}else if(type == 4){//判断题
		$("#ua_"+questionId).val($(obj).val());
	}else if(type == 5){//填空题
		var str = '';
		$("input[name=option_"+questionId+"]").each(function(index,element){
			str += ($.trim($(this).val())).replace(/,/g,"@_@") + "##**##";
			if($.trim($(this).val()) == ''){
				$("#state_"+questionId).val(2);
			}
		})
		str = str.substr(0,str.length-6);
		$("#ua_"+questionId).val(str);
	}
	
	/*设置组合题的答案 题干的总答案  */
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
	
	//判断组合题的完成状态
	/* var idList = $(obj).attr("idList");
	var parentId = $(obj).attr("parentId"); */
	if(idList != null){
		var idArray = idList.split(",");
		var flag = true;
		for(var i=0;i<idArray.length;i++){
			//alert(idArray[i]+"++++"+$("#state_"+idArray[i]).val());
			if($("#state_"+idArray[i]).val() == 2){
				flag = false;
				break;
			}
		}
		if(flag == true){
			$("#state_"+parentId).val(1);
			//$("#ua_"+parentId).val("write");
		}else{
			$("#state_"+parentId).val(2);
			//$("#ua_"+parentId).val("unwrite");
		}
	}
	//设置右侧内容
	setRightData();
}

/*标记题目  */
function setMark(obj,questionId){
	if($(obj).attr("flag") == 0){//打上标记
		$(obj).attr("src","<%=request.getContextPath() %>/images/img/red.png");
		$(obj).attr("flag",1);
		$("#state_"+questionId).attr("ismark",3);
	}else{//取消标记
		$(obj).attr("src","<%=request.getContextPath() %>/images/img/bg_21.png");
		$(obj).attr("flag",0);
		$("#state_"+questionId).attr("ismark",0);
	}
	//设置右侧内容
	setRightData();
} 

/* window.onload=function(){
	 //setZhuheState(calculate,1);
	 //setInterval(setPJStage);
}; */


//设置页面右侧内容
function setRightData(){
	$("#stateDiv0").find("ul").html("");
	$("#stateDiv1").find("ul").html("");
	$("#stateDiv2").find("ul").html("");
	$("#stateDiv3").find("ul").html("");
	var completedNum = 0;
	var uncompletedNum = 0;
	var markedNum = 0;
	$("input[id^='state_']").each(function(){
		if($(this).attr("flag") != 'child'){
			if($(this).val() == 2){
				$("#stateDiv0").find("ul[id=type"+$(this).attr("displaytype")+"]").append("<li style='background-color:#8a8f9a;' num='"+($(this).attr("questionnum")-1)+"' onclick='findQuestion(this,"+$(this).attr("qid")+");'>"+$(this).attr("questionnum")+"</li>");
				$("#stateDiv2").find("ul[id=type"+$(this).attr("displaytype")+"]").append("<li style='background-color:#8a8f9a;' num='"+($(this).attr("questionnum")-1)+"' onclick='findQuestion(this,"+$(this).attr("qid")+");'>"+$(this).attr("questionnum")+"</li>");
				uncompletedNum++;
			}else{
				$("#stateDiv0").find("ul[id=type"+$(this).attr("displaytype")+"]").append("<li style='background-color:#e19999;' num='"+($(this).attr("questionnum")-1)+"' onclick='findQuestion(this,"+$(this).attr("qid")+");'>"+$(this).attr("questionnum")+"</li>");
				$("#stateDiv1").find("ul[id=type"+$(this).attr("displaytype")+"]").append("<li style='background-color:#e19999;' num='"+($(this).attr("questionnum")-1)+"' onclick='findQuestion(this,"+$(this).attr("qid")+");'>"+$(this).attr("questionnum")+"</li>");
				completedNum++;
			}
			if($(this).attr("isMark") == 3){
				$("#stateDiv3").find("ul[id=type"+$(this).attr("displaytype")+"]").append("<li style='background-color:#b50d1d;' num='"+($(this).attr("questionnum")-1)+"' onclick='findQuestion(this,"+$(this).attr("qid")+");'>"+$(this).attr("questionnum")+"</li>");
				markedNum++;
			}
		}
	});
	$("#uncompletedNum").text(uncompletedNum);
	$("#completedNum").text(completedNum);
	$("#markedNum").text(markedNum);
}

function findQuestion(obj,qid){
	if(display_mode == 1){
		document.getElementById('div_'+qid).scrollIntoView(true);
		/* var oDiv=document.getElementById("mao_"+qid);
		var t=document.createElement("input");
		t.type="text";
		oDiv.insertBefore(t,oDiv.firstChild);
		//t.focus();
		setTimeout(function () { t.focus(); }, 0); */
		//oDiv.removeChild(t);
	}else if(display_mode == 2){
		numIndex = $(obj).attr("num");
		$("div[id^='div_']").each(function(index,element){
			if($(this).attr("id") == 'div_'+qid){
				$(this).css("display","block");
			}else{
				$(this).css("display","none");
			}
		});
	}
}

function showSearch(){
	var oDiv=document.getElementById("search");
	var t=document.createElement("input");
	t.type="text";
	oDiv.insertBefore(t,null);
	t.focus();
	oDiv.removeChild(t);
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

/*处理题目的各种状态  */
function findQuestionNum(state){
	$("#botDiv").find("li[id!=bot_"+state+"]").removeClass();
	$("#bot_"+state).addClass("li_b");
	$("#stateDiv"+state).css("display","block");
	$("#rightDiv").find("div[id!=stateDiv"+state+"]").css("display","none");
}

var keyWord;
var subFlag = false;
/*提交试卷  */
function submitPaper(){
	//首先要判断允许最快交卷时间
	if(exam_type == 1){
		//考试剩余时间
		var remainingTime = $("#timeCounter").text().split(":");
		var time = parseInt(remainingTime[0])*60*60 + parseInt(remainingTime[1])*60 + parseInt(remainingTime[2]);
		var hasTime = duration*60 - time;
		//alert(time +"---" + hasTime +"---"+submit_duration);
		if(hasTime < submit_duration*60){
			dialog.alert("答卷时间不得少于"+submit_duration+"分钟！");
			return;
		}
	}
	dialog.confirm('确认提交吗？', function(){
		subFlag = true;
		/*提交时，要将监听的事件去掉，不然，退出后也会调用onblur方法  */
		if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
			//$(window).off("load");
			//alert(123);
		}else{
			window.onblur = function () {}
		}
		var d2 = dialog({
		    content: '正在提交，请等待...'
		});
		d2.showModal();
		$("input[id^='ua_']").each(function(index,element){
			if($.trim($(this).val()) == ''){
				$(this).val("-");
			}
		});
		if(exam_type == 1){
			//考试剩余时间
			var remainingTime = $("#timeCounter").text().split(":");
			var time = parseInt(remainingTime[0])*60*60 + parseInt(remainingTime[1])*60 + parseInt(remainingTime[2]);
			$("#remainingTime").val(time);
		}
		if(exam_type == 5){//课程考试
			//考试剩余时间
			var remainingTime = $("#timeCounter").text().split(":");
			var time = parseInt(remainingTime[0])*60*60 + parseInt(remainingTime[1])*60 + parseInt(remainingTime[2]);
			var thisTestDuration = duration*60 - time;
			$("#remainingTime").val(thisTestDuration);
		}
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data: $("#questionForm").serialize(),
			url: "<%=request.getContextPath()%>/myExamManage/submitPaper.action",
			success:function(data){
				if(data != null && data != 'error'){
					if(exam_type == 3){//模拟考试
						d2.close();
						dialog.alert("本次考试得分为："+data, function (){
							window.opener.location.href=window.opener.location.href;
							window.close();
						});
					}else{
						d2.close();
						dialog.alert("提交成功！", function (){
							window.close();
						});
					}
				}else{
					d2.close();
					dialog.alert("提交失败！");
				}
		    }
		});
	});
}

/*自动提交试卷  */
function autoSubmitPaper(){
	subFlag = true;
	/*提交时，要将监听的事件去掉，不然，退出后也会调用onblur方法  */
	if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
		//$(window).unbind("load");
	}else{
		window.onblur = function () {}
	}
	var d2 = dialog({
	    content: '正在提交，请等待...'
	});
	d2.showModal();
	$("input[id^='ua_']").each(function(index,element){
		if($.trim($(this).val()) == ''){
			$(this).val("-");
		}
	});
	if(exam_type == 1){//普通考试
		//考试剩余时间
		var remainingTime = $("#timeCounter").text().split(":");
		var time = parseInt(remainingTime[0])*60*60 + parseInt(remainingTime[1])*60 + parseInt(remainingTime[2]);
		$("#remainingTime").val(time);
	}
	if(exam_type == 5){//课程考试
		//考试剩余时间
		var remainingTime = $("#timeCounter").text().split(":");
		var time = parseInt(remainingTime[0])*60*60 + parseInt(remainingTime[1])*60 + parseInt(remainingTime[2]);
		var thisTestDuration = duration*60 - time;
		$("#remainingTime").val(thisTestDuration);
	}
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data: $("#questionForm").serialize(),
		url: "<%=request.getContextPath()%>/myExamManage/submitPaper.action",
		success:function(data){
			if(data != null && data != 'error'){
				d2.close();
				if(exam_type == 3){//模拟考试
					dialog.alert(autoInfo + "本次考试得分为："+data, function (){
						window.opener.location.href=window.opener.location.href;
						window.close();
					});
				}else{
					dialog.alert(autoInfo, function (){
						window.close();
					});
					/* var d = dialog({
					    title: '提示',
					    content: autoInfo,
					    okValue: '确定',
					    width:300,
					    cancel: false,
					    ok: function () {
					    	window.close();
					    }
					});
					d.showModal(); */
				}
			}else{
				d2.close();
				dialog.alert("提交失败！");
			}
	    }
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
/* function nof5(){
	if(event.keyCode==116){ 
		event.keyCode = 0; 
		event.cancelBubble = true; 
		return false; 
	} 
} */
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


/* if(isFirefox = navigator.userAgent.indexOf("Firefox")>0){
	if(e.explicitOriginalTarget){
		target = e.explicitOriginalTarget;
		while(target.nodeType != 1){
			target = target.parentElement;
		}
	}
}else if((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
	target = document.activeElement;
} */
/* if("dropdown" == target.getAttr("flag")){
	return false;
} */

 
var activeElement;
function logWindow() {
    if (activeElement != document.activeElement) {
        activeElement = document.activeElement;
    }else {
    	//dialog.alert("123");
    	if(!subFlag){
    		leavePage();
    	}
    }
}
function pageLoad() {
    activeElement = document.activeElement;
    document.onfocusout = logWindow;
}
var leaveTimes = 0;
//防作弊功能 ，要区分IE与其它浏览器
//IE8的onblur事件会与onclick事件混淆，需要重新定义事件
if(is_anti_cheating == 1){
	//IE8浏览器
	if((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
		$(window).on("load", function() {
		   	pageLoad();
		});
	}else{//其它浏览器
		 window.onblur = function () {
			 leavePage();
		} 
	}
}

//失去焦点时的动作
function leavePage(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{"assign_id":exam_assign_id},
		url: "<%=request.getContextPath()%>/myExamManage/getLeaveTimes.action",
		success:function(data){
			leaveTimes = data;
			if(allow_leave_times >= (leaveTimes+1)){//如果离开次数未达到限制
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{"assign_id":exam_assign_id},
					url: "<%=request.getContextPath()%>/myExamManage/updateLeaveTimes.action",
					success:function(data){
						if(data == 'SUCCESS'){
							if(allow_leave_times == (leaveTimes+1)){
								autoInfo = '离开考试次数已达'+allow_leave_times+'次,系统已自动提交试卷！';
								var d = dialog({
								    content: '离开考试次数已达'+allow_leave_times+'次，系统将在5秒后自动提交试卷！'
								});
								d.showModal();
								setTimeout(function () {
									autoSubmitPaper();
								    d.close().remove();
								}, 5000);
							}else{
								dialog.alert("离开考试页面"+(leaveTimes+1)+"次！");
							}
						}else{
							dialog.alert("系统错误!");
						}
					}
				});
			}
		}
	});
}
/* window.onblur = function (e) {
    e = e || window.event;
    if (window.ActiveXObject && /MSIE/.test(navigator.userAgent)) {  //IE
        //如果 blur 事件是窗口内部的点击所产生，返回 false, 也就是说这是一个假的 blur
        var x = e.clientX;
        var y = e.clientY;
        var w = document.body.clientWidth;
        var h = document.body.clientHeight;

        if (x >= 0 && x <= w && y >= 0 && y <= h) {
            window.focus();
            return false;
        }else{
        	alert("leave");
        }
    }
} */

<%-- var leaveTimes = 0;
if(is_anti_cheating == 1){
	window.onblur = function () {
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:{"assign_id":exam_assign_id},
			url: "<%=request.getContextPath()%>/myExamManage/getLeaveTimes.action",
			success:function(data){
				leaveTimes = data;
				if(allow_leave_times >= (leaveTimes+1)){//如果离开次数未达到限制
					$.ajax({
						type:"POST",
						async:false,  //默认true,异步
						data:{"assign_id":exam_assign_id},
						url: "<%=request.getContextPath()%>/myExamManage/updateLeaveTimes.action",
						success:function(data){
							if(data == 'SUCCESS'){
								if(allow_leave_times == (leaveTimes+1)){
									autoInfo = '离开考试次数已达'+allow_leave_times+'次,系统已自动提交试卷！';
									var d = dialog({
									    content: '离开考试次数已达'+allow_leave_times+'次，系统将在5秒后自动提交试卷！'
									});
									d.showModal();
									setTimeout(function () {
										autoSubmitPaper();
									    d.close().remove();
									}, 5000);
								}else{
									dialog.alert("离开考试页面"+(leaveTimes+1)+"次！");
								}
							}else{
								dialog.alert("系统错误!");
							}
						}
					});
				}
			}
		});
		
	}
} --%>

var timeCounter = (function() {
	 var int;
	 var total = 0;
	 if(exam_type == 1){
		 if(remaining_time <= duration *60){
			 total = remaining_time;
		 }else{
			total = duration * 60; 
		 } 
	 }else{
		 total = duration * 60;
	 }
	 return function(elemID) {
	  obj = document.getElementById(elemID);
	  var s = (total%60) < 10 ? ('0' + total%60) : total%60;
	  var h = total/3600 < 10 ? ('0' + parseInt(total/3600)) : parseInt(total/3600);
	  var m = (total-h*3600)/60 < 10 ? ('0' + parseInt((total-h*3600)/60)) : parseInt((total-h*3600)/60);
	  obj.innerHTML = h + ':' + m + ':' + s;
	  total--;
	  int = setTimeout("timeCounter('" + elemID + "')", 1000);
	  if(total < 0) {
		  clearTimeout(int);
		  autoInfo = '考试时间已到，系统已自动提交试卷！';
		  autoSubmitPaper();
		}
	 };
})();

//判断数组中是包含某个元素 通用方法
Array.prototype.contains = function (element){
	for (var i=0; i<this.length;i++){
		if (this[i] == element){ 
			return true; 
		} 
	} 
	return false; 
};

//从一个给定的数组arr中,随机返回num个不重复项
function getArrayItems(arr, num) {
    //新建一个数组,将传入的数组复制过来,用于运算,而不要直接操作传入的数组;
    var temp_array = new Array();
    for (var index in arr) {
        temp_array.push(arr[index]);
    }
    //取出的数值项,保存在此数组
    var return_array = new Array();
    for (var i = 0; i<num; i++) {
        //判断如果数组还有可以取出的元素,以防下标越界
        if (temp_array.length>0) {
            //在数组中产生一个随机索引
            var arrIndex = Math.floor(Math.random()*(temp_array.length-1));
            //alert(temp_array[arrIndex]);
            //将此随机索引的对应的数组元素值复制出来
            return_array[i] = temp_array[arrIndex];
            //然后删掉此索引的数组元素,这时候temp_array变为新的数组
            temp_array.splice(arrIndex, 1);
        } else {
            //数组中数据项取完后,退出循环,比如数组本来只有10项,但要求取出20项.
            break;
        }
    }
    return return_array;
}
 

</script>
</head>
<body>
	<div class="on_test">
	 <!--上侧模块 start  -->
	 <div>
	<div id="menuTop">
    	<%-- <div class="test_title">
        	<h3>
        		<span style="float:left;">
        			<span id="personName" style="">${userName }</span>
        			<span ><img id="headPhoto" src="${headPhoto }"/></span>
        		</span>
        		<span id="paperName" style="float: none;font-size: 30px;"></span>
        		<span style="display:block;" id="leaveTime">剩余时间<em id="timeCounter"></em></span>
        	</h3>
        </div> --%>
        <!-- <div style="clear:both;"></div> -->
	      <div id="topContent">	
	        <!-- <div class="test_tool" style="margin-right: 364px;float:left;">
	        	<div class="tool_top">
	            	<p>考试详情：
	                	<span>总分：<span id="paperTotalScore"></span></span>
	                    <span id="passScoreSpan">及格分数：<span id="passScore"></span></span>
	                    <span id="durationSpan">考试时长：<span id="duration"></span></span>
	                </p>
	            </div>
	          	<div class="tool_bottom" id="inputDiv">
	            	<input type="button" value="显示全部" class="input_a" id="input_0" onclick="selectQuestion(0);"/>
	                <input type="button" value="填空题" id="input_5" onclick="selectQuestion(5);"/>
	                <input type="button" value="判断题" id="input_4" onclick="selectQuestion(4);"/>
	                <input type="button" value="多选题" id="input_3" onclick="selectQuestion(3);"/>
	                <input type="button" value="单选题" id="input_2" onclick="selectQuestion(2);"/>
	                <input type="button" value="主观题" id="input_1" onclick="selectQuestion(1);"/>
	                <input type="button" value="组合题" id="input_6" onclick="selectQuestion(6);"/>
	                <input type="button" value="多媒体题" id="input_7" onclick="selectQuestion(7);"/>
	            </div>
	            <div class="tool_bottom" id="inputPreNext">
	            	<input type="button" style="margin: 10px;" value="上一题" id="input_pre" onclick="preQuestion();"/>
	            	<input type="button" style="margin: 10px;" value="下一题" id="input_next" onclick="nextQuestion();"/>
	            </div>
	        </div> -->
        	<%-- <div id="personDiv" style="float: right;position: fixed;right: 0px;  width: 364px;height: 85px; background: #f2f2f2;">
	        	<div style="float:left;padding:10px 5px;">
	        		<p style="font-family: 微软雅黑;font-size: 15px;  padding-bottom: 5px;">姓名：<span>张三</span></p>
	        		<p style="font-family: 微软雅黑;font-size: 15px;  padding-bottom: 5px;">部门：<span>部门一</span></p>
	        		<p style="font-family: 微软雅黑;font-size: 15px;">身份证号：<span>342522198702330962</span></p>
	        	</div>
	        	<div style="float:right;">
	        		<img src="<%=request.getContextPath() %>/images/img/avatar_male.png" style="width:80px;height:80px;"/>
	        	</div>
	        </div> --%>
        </div>	
       </div>
	      <!--   -->
       </div>
        <!--上侧模块 end  -->
        <div style="clear:both;"></div>
         <!--下侧模块 start  -->
        <div id="testDiv">
	        <form id="questionForm" method="post" action="" onsubmit="">
		        <input type="hidden" id="userId" name="userId" />
				<input type="hidden" id="examId" name="examId"/>
				<input type="hidden" id="examType" name="examType"/>
				<input type="hidden" id="examAssignId" name="examAssignId"/>
				<input type="hidden" id="isAutoMarking" name="isAutoMarking"/>
				<input type="hidden" id="remainingTime" name="remainingTime" value="0"/>
				<input type="hidden" id="organizingMode" name="organizingMode"/>
				<input type="hidden" id="totalScore" name="totalScore"/>
				<input type="hidden" id="examRecordId" name="examRecordId"/>
				<!--左侧模块 开始  -->
				<div id="leftContent" style="float: left;margin-right: 364px;width:auto;">
					<div id="leftTopContent" style="margin-right: 364px;width:auto;">
						<div class="test_title">
				        	<h3>
				        		<%-- <span style="float:left;">
				        			<span id="personName" style="">${userName }</span>
				        			<span ><img id="headPhoto" src="${headPhoto }"/></span>
				        		</span> --%>
				        		<span id="paperName" style="float: none;font-size: 30px;"></span>
				        		<!-- <span style="display:block;" id="leaveTime">剩余时间<em id="timeCounter"></em></span> -->
				        	</h3>
				        </div>
						<div class="test_tool">
				        	<div class="tool_top">
				            	<p>考试详情：
				                	<span>总分：<span id="paperTotalScore"></span></span>
				                    <span id="passScoreSpan">及格分数：<span id="passScore"></span></span>
				                    <span id="durationSpan">考试时长：<span id="duration"></span></span>
				                </p>
				            </div>
				          	<div class="tool_bottom" id="inputDiv">
				            	<input type="button" value="显示全部" class="input_a" id="input_0" onclick="selectQuestion(0);"/>
				                <!-- <input type="button" value="填空题" id="input_5" onclick="selectQuestion(5);"/>
				                <input type="button" value="判断题" id="input_4" onclick="selectQuestion(4);"/>
				                <input type="button" value="多选题" id="input_3" onclick="selectQuestion(3);"/>
				                <input type="button" value="单选题" id="input_2" onclick="selectQuestion(2);"/>
				                <input type="button" value="主观题" id="input_1" onclick="selectQuestion(1);"/>
				                <input type="button" value="组合题" id="input_6" onclick="selectQuestion(6);"/>
				                <input type="button" value="多媒体题" id="input_7" onclick="selectQuestion(7);"/> -->
				            </div>
				            <div class="tool_bottom" id="inputPreNext">
				            	<input type="button" style="margin: 10px;" value="上一题" id="input_pre" onclick="preQuestion();"/>
				            	<input type="button" style="margin: 10px;" value="下一题" id="input_next" onclick="nextQuestion();"/>
				            </div>
				        </div>
			        </div>
			        <!--试题内容 开始   -->
					<div class="left_test" id="questionDiv" style="overflow: auto;"></div>
					<!--试题内容 结束   -->
				</div>
		    	<!--左侧模块 结束  -->
	        </form>
        <!--右侧模块 开始   -->
        <div id="rightContent" class="right_test" style="float: right;  right: 0px;  position: fixed;">
        	<div style="border-bottom: 1px dotted #cccccc;  height: 79px;">
        		<div style="  padding-left: 180px;padding-top: 40px;">
        			<span style="display:block;" id="leaveTime">剩余时间<em id="timeCounter" style="font-size: 30px;font-weight: bold;"></em></span>
        		</div>
        	</div>
        	<div id="personDiv">
	        	<div style="float:left;padding:10px 5px;">
	        		<p style="font-family: 微软雅黑;font-size: 15px;  padding-bottom: 5px;">姓名：<span>${userName }</span></p>
	        		<p style="font-family: 微软雅黑;font-size: 15px;  padding-bottom: 5px;">部门：<span>${deptName }</span></p>
	        		<p style="font-family: 微软雅黑;font-size: 15px;">身份证号：<span>${idCard }</span></p>
	        	</div>
	        	<div style="float:right;">
	        		<img src="${headPhoto }" style="width:80px;height:80px;"/>
	        	</div>
	        </div>
        	<div class="right_top" style="height:auto;">
            	<input type="button" value="提交试卷" onclick="submitPaper();"/>
                <p>
                	<span style="margin-right:0px;color: #333333;font-weight: normal;">总题目：<span id="totalQuestionNum" style="padding-right: 10px;margin-right: 50px;"></span></span>
                    <span style="margin-right:0px;color: #333333;font-weight: normal;">已完成：<span id="completedNum" style="margin-right: 50px;">0</span><img src="<%=request.getContextPath() %>/images/img/yellow.png" class="markImg2"/></span>
                </p>
                <p>
                	<span style="margin-right:0px;color: #333333;font-weight: normal;">未完成：<span id="uncompletedNum" style="margin-right: 50px;"></span><img src="<%=request.getContextPath() %>/images/img/bg_21.png" class="markImg2"/></span>
                	<span id="markedSpan" style="margin-right:0px;color: #333333;font-weight: normal;">已标记：<span id="markedNum" style="margin-right: 50px;">0</span><img src="<%=request.getContextPath() %>/images/img/red.png" class="markImg2"/></span>
                </p>
            </div>
            <div class="right_bottom" id="rightBottomDiv">
            	<div class="bot_ul" id="botDiv">
                    <ul>
                        <li id="bot_0" class="li_b" onclick="findQuestionNum(0);">全部</li>
                        <li id="bot_1" onclick="findQuestionNum(1);">已完成</li>
                        <li id="bot_2" onclick="findQuestionNum(2);">未完成</li>
                        <li id="bot_3" onclick="findQuestionNum(3);">已标记</li>
                    </ul>
                </div>
                <div id="rightDiv" style="overflow-y: auto;overflow-x: hidden; height: 450px;width: 364px;">
                <div id="stateDiv0" style="display:block">
	            </div>
	            <div id="stateDiv1" style="display:none">
	            </div>
	            <div id="stateDiv2" style="display:none">
	            </div>
	            <div id="stateDiv3" style="display:none;">
	            </div>
	            </div>
            </div>
        </div>
        <!--右侧模块 结束   -->
        </div>
    </div>
</body>
<script src="<%=request.getContextPath() %>/js/jquery.artwl.thickbox.js"></script>
</html>
