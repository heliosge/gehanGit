<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程学习</title>
<!-- 引入页面style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- 引入页面js -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script> --%>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/scorm_rte.js"></script>
<!-- jquery_paginator -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css">
<!-- dateUtil -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- flexPaper -->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/flexPaper/js/flexpaper_flash_debug.js"></script>
<!-- ckplayer -->
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/ckplayer6.7/ckplayer/ckplayer.js" charset="utf-8"></script>
 <!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<!-- huadong.js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/huadong.js"></script>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**课件播放分页栏样式*/
.courseStudyPager a{display:block; width:30px; height:30px; float:left; line-height:30px; color:#676568; 
text-align:center; border:1px solid #cccccc; margin-right:-1px;}

.courseStudyPager a:hover{color:#fff; background:#0085fe;}

.courseStudyPager .r_first{color:#fff; background:#0085fe;}

.courseStudyPager span{ float:left; display:block;height:30px; line-height:30px; font-weight:bold; margin-right:5px;}

.courseStudyPager .span_1{ font-weight:normal; margin-left:10px;}

.courseStudyPager .span_1 input{ width:45px; height:30px; border:1px solid #cccccc; margin:0 4px;}

.courseStudyPager .btn_2{ background:#999999; color:#fff; text-align:center; width:45px;
 height:30px; display:block; float:right; border:none;}

/**课件播放树样式*/
.course_tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 530px;
border: 1px solid #333;float: left;font-family: '微软雅黑';}

.course_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;
height: 30px;line-height: 30px;font-weight: normal;}

/**课程名称*/
.courseNameStyle{text-overflow: ellipsis;white-space: nowrap;overflow: hidden;}

#course_play{width:100%;height:760px;}

#player{width:100%;height:760px;}

/**课件展现*/
#courseWarePlayer{width:97%;height:96%;position:relative;z-index:1;}

/**笔记问答展现*/	
#noteQuestionDiv{width:24%;height:100%;display:none;background:#FFF;z-index:2;}

/**隐藏显示右边部分按钮*/
#hideNoteQuestionDiv{width:28px;height:114px;z-index:3;}

#noteText{width:96%;height:84px;border:1px solid #cccccc;margin-left:2%;margin-right:2%;margin-top:8px;}

#saveButton{float:right;width:45px; height:24px; background:#0c9c92;color:#fff; text-align:center; 
line-height:24px; border:none; margin-right:10px;margin-top:5px;}
	
.hrStyle{margin-top:15px;height:1px;border:none;border-top:1px dashed #C0C0C0;}

#noteList{padding-left:2%;padding-right:2%;}

#noteListContents{height:350px;overflow:auto;}

#tab_b{width:93%;}

#questionDiv{display:none;}

#askTitle{font-size:13px;padding-left:2%;}

#questionTitle{width:96%;margin-left:2%;margin-right:2%;border:1px solid #cccccc;}

#askContent{font-size:13px;padding-left:2%;}

#questionContent{width:96%;height:84px;margin-left:2%;margin-right:2%;border:1px solid #cccccc;}

#saveReplyButton{float:right;width:45px; height:24px; background:#0c9c92;color:#fff; text-align:center; 
line-height:24px; border:none; margin-right:10px;margin-top:5px;}

#questionList{padding-left:2%;padding-right:2%;}

#questionListContent{height:300px;overflow:auto;}

#replyDialog{height: auto;display:none;}

#replyText{width:220px;height:180px;}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var courseWareId = '${courseWareId}';
var courseWareType = '${courseWareType}';
var courseId = '${courseId}';
var sectionId = '${sectionId}';
var initFlag = true;

/**
 * 页面加载完毕
 */
$(function() {
	//初始化课件
	initCourseWare();
	//初始化课程详情
	initCourseDetail();
	//初始化课程笔记
	initCourseNotes();
	//初始化课程问题
	initCourseQuestions();
	//判断zip课件api是否初始化
	if(initFlag){
		init_API();
		initFlag = false;
	}
	
	//滑动效果
	$("#noteQuestionDiv").huadong(noteQuestionDiv,hideNoteQuestion,courseWarePlayer,300);
});
	
/**
 * 初始化课件
 */
function initCourseWare(){
	var param = new Object();
	param.courseWareId = courseWareId;
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/courseStudyAction/getCourseWare.action',
		success:function(data){
			console.info(data);
			if(data != null && data != ''){
				//初始化课件
				var path = data.filePath;//课件路径
				//根据课件类型具体展现该课件
				if(courseWareType == 1){//标准课件，zip
					initZip(data);
				}else if(courseWareType == 2){//普通课件，swf
					initFlexpaper(path);
				}else if(courseWareType == 3){//视频课件，mp4
					initVideo(path);
				}
				
			}
		}
	});
}

/**
 * 初始化zip
 */
function initZip(data){
	filePath = data.filePath;
	var htmlStr = '<div class="course_tree" id="tree" style="width:14%;height:98%;float:left;"><h2 style="width:100%;">课件目录</h2><ul id="treePage" class="ztree"></ul></div><div style="margin-left: 10px;height:100%;" class="right_lesson">'
	+'<iframe id="coursewareIFrame" frameborder="0" style="width:85%;height:98%;float:left;" /></div>'
	$("#courseWarePlayer").html(htmlStr);
	initTree(data);
}

var filePath;
function initTree(data){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true}
			},
			check: {enable:  false},
			view: {
				showLine: false,
				showIcon: true
			},
			callback: {
				onClick: zTreeOnClick
			}
	};
	$.fn.zTree.init($("#treePage"), setting, JSON.parse(data.fileName).children);
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
	initAllScos();
}

function initAllScos(){
	var treeObj = $.fn.zTree.getZTreeObj("treePage");
	var nodes = treeObj.getNodes();
	getChildNodes(nodes[0]);
}

function getChildNodes(treeNode){
	if(treeNode.filePath != undefined && treeNode.filePath != ''){
		total.push(treeNode.filePath); 
	}
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			if(treeNode.children[i].filePath != undefined && treeNode.children[i].filePath != ''){
				total.push(treeNode.children[i].filePath);
			} 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

function getChildNodesNotAddThis(treeNode){
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			if(treeNode.children[i].filePath != undefined && treeNode.children[i].filePath != ''){
				total.push(treeNode.children[i].filePath); 
			}
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

var total = [];
var sco = '';
var studyed = [];
var scormFlag = true;
function zTreeOnClick(event, treeId, treeNode){
	if(treeNode.filePath != ''){
		sco = treeNode.filePath;
		$("#coursewareIFrame").attr("src",filePath+"/"+treeNode.filePath);
	}
	if(scormFlag){
		if(sco != ''){
			for( var j=0;j<studyed.length;j++){
				if(studyed[j] == sco){
					studyed.splice(j,1);
					break;
				};
			}
			studyed.push(sco);
		}
	}
}

/**
 * 初始化FlexPaper（swf文件展示）
 */
function initFlexpaper(filePath){
	var fp = new FlexPaperViewer(	
			'<%=request.getContextPath()%>/js/flexPaper/FlexPaperViewer',
			 'courseWarePlayer', { config : {
			 SwfFile : filePath,
			 Scale : 0.6, 
			 ZoomTransition : 'easeOut',
			 ZoomTime : 0.5,
			 ZoomInterval : 0.2,
			 FitPageOnLoad : false,
			 FitWidthOnLoad : false,
			 FullScreenAsMaxWindow : false,
			 ProgressiveLoading : false,
			 MinZoomSize : 0.2,
			 MaxZoomSize : 5,
			 SearchMatchAll : false,
			 InitViewMode : 'Portrait',
			 
			 ViewModeToolsVisible : true,
			 ZoomToolsVisible : true,
			 NavToolsVisible : true,
			 CursorToolsVisible : true,
			 SearchToolsVisible : true,
			 localeChain: 'en_US'
			 }});
	$("#courseWarePlayer").children('object').append('<param value="Opaque" name="wmode"></param>');
}

/**
 * 初始化video
 */
 var currentTime = 0
function initVideo(filePath){
	var flashvars={
			f:filePath,
			p:2,
			e:2,
			b:0,
			ht:'20',
			loaded:'loadedHandler'
			};
	var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'Opaque'};
	CKobject.embedSWF('<%=request.getContextPath()%>/js/ckplayer6.7/ckplayer/ckplayer.swf','courseWarePlayer','ckplayer_courseWarePlayer','100%','100%',flashvars,params);	
}

function loadedHandler(){
    if(CKobject.getObjectById('ckplayer_courseWarePlayer').getType()){
      CKobject.getObjectById('ckplayer_courseWarePlayer').addListener('time',timeHandler);
    }
    else{
      CKobject.getObjectById('ckplayer_courseWarePlayer').addListener('time','timeHandler');
    }
  }

  function timeHandler(t){
    if(t>-1){
    	currentTime = t;
    }
  }

/**
 * 初始化课程详情
 */
function initCourseDetail(){
	var param = new Object();
	param.courseId = courseId;
	param.sectionId = sectionId;
	param.courseWareId = courseWareId;
	$.ajax({
		type:'POST',
		async:true,
		data:param,
		url:'<%=request.getContextPath()%>/courseStudyAction/getCourseDetail.action',
		success:function(data){
			if(data != null && data != ''){
				$("#courseName").html(data.name);
				$("#courseName").attr("title",""+data.name+"");
				$("#sectionName").html(data.sectionName);
				$("#sectionName").attr("title",""+data.sectionName+"");
				$("#wareName").html(data.wareName);
				$("#wareName").attr("title",""+data.wareName+"");
				$("#evaluateScore").html('课程评分：'+data.avgScore+'分');
				$("#evalutorCount").html('('+data.evaluatorCount+'人已评价,'+data.studentNum+'人已学习)');
				var createTimeStr = '课程创建时间：'+getSmpFormatDateByLong(data.createTime);
				$("#createTime").html(createTimeStr);
			}
		}
	});
}

/**
 * 初始化课程笔记
 */
function initCourseNotes(){
	var opts = {
			items_per_page:5,//每页显示条数
			prev_text:'<',
			next_text:'>',
			prev_show_always:false,//不一直显示上一页
			next_show_always:false,//不一直显示下一页
			num_edge_entries:2,//两侧显示的首尾分页的条目数
			num_display_entries:2,//连续分页主体部分显示的分页条目数
			callback:function(page,node){
				var param = new Object();
				param.courseId = courseId;
				param.companyId = companyId;
				param.subCompanyId = subCompanyId;
				param.page = page;
				param.pageSize = 5;
				$.ajax({
					type:'POST',
					async:true,
					data:param,
					url:'<%=request.getContextPath()%>/courseStudyAction/getCourseNotes.action',
					success:function(data){
						if(data != null && data != ''){
							var htmlStr = '';
							for(var i = 0; i <data.length; i++){
								var note = data[i];
								htmlStr += '<div>';
								htmlStr += '<span style="color:#2894FF;font-size:13px;">'+note.userName+'</span>';
								htmlStr += '<div style="font-size:13px;">'+note.content+'</div>';
								htmlStr += '<span style="color:grey;font-size:13px;">'+getSmpFormatDateByLong(note.createTime)+'</span>';
								htmlStr += '<hr style="height:1px;border:none;border-top:1px dashed #C0C0C0;"/>';
								htmlStr += '</div>';
							}
							$("#noteListContents").html(htmlStr);
						}
					}
				});
			}
	};
	var count = getCourseNotesCount();
	$("#noteListPager").pagination(count,opts);
}

/**
 * 获取课程笔记数量
 */
function getCourseNotesCount(){
	var param = new Object();
	param.courseId = courseId;
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	var count = 0;
	$.ajax({
		type:'POST',
		async:false,//此处需要同步
		data:param,
		url:'<%=request.getContextPath()%>/courseStudyAction/getCourseNotesCount.action',
		success:function(data){
			count = data;
		}
	});
	return count;
}

/**部分ie浏览器不支持trim的处理*/
if(typeof String.prototype.trim=='undefined'){
    String.prototype.trim = function () {
        return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
    }   
}

/**
 * 保存笔记
 */
function saveNote(){
	//验证内容
	var inputStr = $("#noteText").val();
	var saveButton = document.getElementById("saveButton");//保存按钮
	if(inputStr == null || inputStr.trim() == ''){
		dialog({
			title:'保存笔记',
			content:'保存内容不能为空！',
			align:'bottom left',
			okValue:'确认',
			ok:function(){}
		}).showModal(saveButton);
		return;
	}
	//添加笔记
	var note = new Object();
	note.courseId = courseId;
	note.userId = userId;
	note.createTime = new Date();
	note.content = inputStr;
	note.companyId = companyId;
	note.subCompanyId = subCompanyId;
	$.ajax({
		type:'POST',
		async:false,//此处需要同步
		data:note,
		url:'<%=request.getContextPath()%>/courseStudyAction/saveNote.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				dialog({
					title:'保存笔记',
					content:'保存成功！',
					align:'bottom left',
					okValue:'确认',
					ok:function(){}
				}).showModal(saveButton);
				//刷新笔记列表
				initCourseNotes();
				//清空输入框
				$("#noteText").val('');
			}else{
				dialog({
					title:'保存笔记',
					content:'保存失败...',
					align:'bottom left',
					okValue:'确认',
					ok:function(){}
				}).showModal(saveButton);
				//清空输入框
				$("#noteText").val('');
			}
		}
	});
}

/**
 * 初始化课程问答
 */
function initCourseQuestions(){
	var opts = {
			items_per_page:5,//每页显示条数
			prev_text:'<',
			next_text:'>',
			prev_show_always:false,//不一直显示上一页
			next_show_always:false,//不一直显示下一页
			num_edge_entries:2,//两侧显示的首尾分页的条目数
			num_display_entries:2,//连续分页主体部分显示的分页条目数
			callback:function(page,node){
				var param = new Object();
				param.courseId = courseId;
				param.companyId = companyId;
				param.subCompanyId = subCompanyId;
				param.page = page;
				param.pageSize = 5;
				$.ajax({
					type:'POST',
					async:true,
					data:param,
					url:'<%=request.getContextPath()%>/courseStudyAction/getCourseQuestions.action',
					success:function(data){
						if(data != null && data != ''){
							var htmlStr = '';
							for(var i = 0; i < data.length; i++){
								var question = data[i];
								htmlStr += '<div>';
								htmlStr += '<span style="font-size:13px;font-weight:bold;">'+question.title+'--'+question.userName+'</span>';
								htmlStr += '<div style="font-size:13px;padding-left:5px;">'+question.content+'</div>';
								htmlStr += '<span id="askTime" style="color:grey;font-size:13px;">';
								htmlStr += getSmpFormatDateByLong(question.asktime);
								htmlStr += '<a href="javascript:void(0)" onclick="answerThisQuestion('+question.id+');">回复</a>';
								htmlStr += '</span>';
								//回复的内容
								htmlStr += '<div style="font-size:13px;margin-left:20px;padding-top:5px;padding-bottom:5px;">';
								var innerParam = new Object();
								innerParam.topicId = question.id;
								$.ajax({
									type:'POST',
									async:false,
									data:innerParam,
									url:'<%=request.getContextPath()%>/courseStudyAction/getReplys.action',
									success:function(data){
										var replys = data;
										if(replys != null && replys != ''){
											for(var j = 0; j < replys.length; j++){
												var reply = replys[j];
												var replyContent = '';//要添加的回复富文本内容
												//回复富文本
												if(reply.replyText != null){
													htmlStr += '<div style="background:#FFFFBB;">';
													htmlStr += reply.replyText;
													htmlStr += '</div>';
													replyContent += reply.replyText;
												}
												//回复内容
												htmlStr += '<div style="padding-top:5px;">';
												htmlStr += reply.userName + ' : ' +reply.content;
												htmlStr += '</div>';
												//要添加的回复富文本内容
												replyContent += reply.userName + ' : ' +reply.content;
												replyContent += '<br/>';
												$("body").data("replyContent"+reply.id+"",replyContent);
												//回复时间和回复按钮
												htmlStr += '<div style="padding-bottom:5px;">';
												htmlStr += '<span style="color:grey;font-size:13px;">';
												htmlStr += getSmpFormatDateByLong(reply.answertime);
												htmlStr += '<a href="javascript:void(0)" onclick="answerThisReply('+reply.id+');">回复</a>';
												htmlStr += '</span>';
												htmlStr += '</div>';
											}
										}
									}
								});
								htmlStr += '</div>';
								htmlStr += '</div>';
							}
							$("#questionListContent").html(htmlStr);
						}
					}
				});
			}
	};
	var count = getCourseQuestionsCount();
	$("#questionListPager").pagination(count,opts);
}

/**
 * 获取课程问题数量
 */
function getCourseQuestionsCount(){
	var param = new Object();
	param.courseId = courseId;
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	var count = 0;
	$.ajax({
		type:'POST',
		async:false,//此处需要同步
		data:param,
		url:'<%=request.getContextPath()%>/courseStudyAction/getCourseQuestionsCount.action',
		success:function(data){
			count = data;
		}
	});
	return count;
}

/**
 * 保存一个提问
 */
function saveQuestion(){
	var saveButton = document.getElementById("saveReplyButton");//保存按钮
	//验证问题标题和问题内容不能为空
	var title = $("#questionTitle").val();
	var content = $("#questionContent").val();
	if(title == null || title.trim() == ''){
		dialog({
			title:'保存问题',
			content:'提问标题不能为空...',
			align:'bottom left',
			okValue:'确认',
			ok:function(){}
		}).showModal(saveButton);
		return;
	}
	if(title.length > 200){
		dialog({
			title:'保存问题',
			content:'提问标题过长，不能超过200个字符。',
			align:'bottom left',
			okValue:'确认',
			ok:function(){}
		}).showModal(saveButton);
		return;
	}
	if(content == null || content.trim() == ''){
		dialog({
			title:'保存问题',
			content:'提问内容不能为空...',
			align:'bottom left',
			okValue:'确认',
			ok:function(){}
		}).showModal(saveButton);
		return;
	}
	//添加提问
	var question = new Object();
	question.userId = userId;
	question.title = title;
	question.content = content;
	question.asktime = new Date();
	question.courseId = courseId;
	question.companyId = companyId;
	question.subCompanyId = subCompanyId;
	question.courseWareId = courseWareId;
	question.courseWareType = courseWareType;
	$.ajax({
		type:'POST',
		async:false,//此处需要同步
		data:question,
		url:'<%=request.getContextPath()%>/courseStudyAction/saveQuestion.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				dialog({
					title:'保存问题',
					content:'保存成功！',
					align:'bottom left',
					okValue:'确认',
					ok:function(){}
				}).showModal(saveButton);
				//刷新问题列表
				initCourseQuestions();
				//清空输入框
				$("#questionTitle").val('');
				$("#questionContent").val('');
			}else{
				dialog({
					title:'保存问题',
					content:'保存失败...',
					align:'bottom left',
					okValue:'确认',
					ok:function(){}
				}).showModal(saveButton);
				//清空输入框
				$("#questionTitle").val('');
				$("#questionContent").val('');
			}
		}
	});
}

/**
 * 展现笔记
 */
function showNote(){
	$("#noteButton").addClass("a_active");
	$("#questionButton").removeClass("a_active");
	$("#noteDiv").show();
	$("#questionDiv").hide();
}

/**
 * 展现问题
 */
function showQuestion(){
	$("#noteButton").removeClass("a_active");
	$("#questionButton").addClass("a_active");
	$("#noteDiv").hide();
	$("#questionDiv").show();
}

/**
 * 回复问题
 */
function answerThisQuestion(questionId){
	var saveButton = document.getElementById("saveReplyButton");//保存按钮
	var questionDiv = document.getElementById("questionList");//问题列表div
	var d = dialog({
		title:'回复问题',
		content:'回复内容不能为空...',
		align:'bottom left',
		okValue:'确认',
		ok:function(){}
	});
	dialog({
		title:'回复问题',
		width:220,
		height:180,
		align:'top',
		content:$("#replyDialog"),
		okValue:'确定',
		ok:function(){
			var replyText = $("#replyText").val();
			if(replyText != null && replyText.trim() != ''){
				saveQuestionAnswer(questionId);
			}else{
				d.showModal(saveButton);
				return false;
			}
		},
		cancelValue:'取消',
		cancel:function(){}
	}).showModal(questionDiv);
}

/**
 * 保存问题的回复
 */
function saveQuestionAnswer(questionId){
	var saveButton = document.getElementById("saveReplyButton");//保存按钮
	var param = new Object();
	param.topicId = questionId;
	param.userId = userId;
	param.content = $("#replyText").val();
	param.answertime = new Date();
	param.type = 1;
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	//保存
	$.ajax({
		type:'POST',
		async:false,//同步
		data:param,
		url:'<%=request.getContextPath()%>/courseStudyAction/saveQuestionAnswer.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				dialog({
					title:'回复问题',
					content:'回复成功！',
					align:'bottom left',
					okValue:'确认',
					ok:function(){}
				}).showModal(saveButton);
				initCourseQuestions();
			}else{
				dialog({
					title:'回复问题',
					content:'回复失败...',
					align:'bottom left',
					okValue:'确认',
					ok:function(){}
				}).showModal(saveButton);
			}
		}
	});
}

/**
 * 针对回复的回复
 */
function answerThisReply(replyId){
	var saveButton = document.getElementById("saveReplyButton");//保存按钮
	var questionDiv = document.getElementById("questionList");//问题列表div
	var replyContent = $("body").data("replyContent"+replyId+"");
	var d = dialog({
		title:'回复该回复',
		content:'回复内容不能为空...',
		align:'bottom left',
		okValue:'确认',
		ok:function(){}
	});
	dialog({
		title:'针对回复的回复',
		width:220,
		height:180,
		align:'top',
		content:$("#replyDialog"),
		okValue:'确定',
		ok:function(){
			var replyText = $("#replyText").val();
			if(replyText != null && replyText.trim() != ''){
				saveReplyAnswer(replyId,replyContent,replyText);
			}else{
				d.showModal(saveButton);
				return false;
			}
		},
		cancelValue:'取消',
		cancel:function(){}
	}).showModal(questionDiv);
}

/**
 * 保存回复的回复
 */
function saveReplyAnswer(replyId,replyContent,replyText){
	var saveButton = document.getElementById("saveReplyButton");//保存按钮
	var param = new Object();
	param.replyId = replyId;//回复id
	param.replyContent = replyContent;//富文本
	param.replyText = replyText;//回复内容
	param.userId = userId;
	
	$.ajax({
		type:'POST',
		async:false,//同步
		data:param,
		url:'<%=request.getContextPath()%>/courseStudyAction/saveReplyAnswer.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				dialog({
					title:'回复该回复',
					content:'回复成功！',
					align:'bottom left',
					okValue:'确认',
					ok:function(){}
				}).showModal(saveButton);
				initCourseQuestions();
			}else{
				dialog({
					title:'回复该回复',
					content:'回复失败...',
					align:'bottom left',
					okValue:'确认',
					ok:function(){}
				}).showModal(saveButton);
			}
		}
	});
}

/**
 * 页面离开事件
 */
window.onbeforeunload = function(){
	recordCourseWare();
};

/**
 * 记录课件学习进度
 */
function recordCourseWare(){
	var wareRecordId = null;//课件学习记录id
	var courseRecordId = null;//课程学习记录id
	//查询出该课件的学习记录
	var recordParam = new Object();
	recordParam.sectionId = sectionId;
	recordParam.courseWareId = courseWareId;
	recordParam.userId = userId;
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:recordParam,
		url:'<%=request.getContextPath()%>/courseStudyAction/getCourseWareByConditions.action',
		success:function(data){
			if(data != null && data != ''){
				wareRecordId = data.id;
				courseRecordId = data.recordId;
			}
		}
	});
	
	//根据课件类型记录课件的学习进度
	if(courseWareType == 1){
		//scrom标准课件
		var param = new Object();
		param.wareRecordId = wareRecordId;
		param.courseRecordId = courseRecordId;
		param.courseId = courseId;
		param.userId = userId;
		param.total = total;
		param.studyed = studyed;
		$.ajax({
			type:'POST',
			async:false,//此处为同步
			data:param,
			url:'<%=request.getContextPath()%>/courseStudyAction/recordScromWareAfterLeavePage.action',
			success:function(data){
				if(data.rtnResult == 'FAIL'){
					alert("保存scrom课件进度错误");
				}
			}
		});
	}else if(courseWareType == 2){
		//swf普通课件
		var swfWareParam = new Object();
		swfWareParam.wareRecordId = wareRecordId;
		swfWareParam.courseRecordId = courseRecordId;
		swfWareParam.courseId = courseId;
		swfWareParam.userId = userId;
		swfWareParam.totalPages = $FlexPaper().getTotalPages();
		swfWareParam.currPage = $FlexPaper().getCurrPage();
		$.ajax({
			type:'POST',
			async:false,//此处为同步
			data:swfWareParam,
			url:'<%=request.getContextPath()%>/courseStudyAction/recordSwfWareAfterLeavePage.action',
			success:function(data){
				if(data.rtnResult == 'FAIL'){
					alert("保存swf课件进度错误");
				}
			}
		});
	}else if(courseWareType == 3){
		//视频课件
		var vedioParam = new Object();
		vedioParam.wareRecordId = wareRecordId;
		vedioParam.courseRecordId = courseRecordId;
		vedioParam.courseId = courseId;
		vedioParam.userId = userId;
		vedioParam.totalTime = CKobject.getObjectById('ckplayer_courseWarePlayer').getStatus().totalTime;
		vedioParam.currentTime = currentTime;
		$.ajax({
			type:'POST',
			async:false,//此处为同步
			data:vedioParam,
			url:'<%=request.getContextPath()%>/courseStudyAction/recordVedioWareAfterLeavePage.action',
			success:function(data){
				if(data.rtnResult == 'FAIL'){
					alert("保存swf课件进度错误");
				}
			}
		});
	}
}
	
</script>
</head>
<body>
	<div id="course_play" class="course_play">
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课程学习</span>
		</div>
		<div id="player" class="player">
		
			<!-- 课件展现 -->
			<div id="courseWarePlayer" class="player_1"></div>
			
			<!-- 笔记问答展现 -->
			<div id="noteQuestionDiv" class="play_right">
				<h4 id="courseName" class="courseNameStyle"></h4>
				<p>
				    <span id="sectionName" class="courseNameStyle"></span>
				    <em id="wareName" class="courseNameStyle"></em>
				    <br/>
				    <span id="evaluateScore"></span> 
				    <em id="evalutorCount"></em>
				</p>
				<p id="createTime"></p>
				<div id="tab_b" class="tab_b">
					<a id="noteButton" href="javascript:void(0)" class="a_active" onclick="showNote();">笔记</a> 
					<a id="questionButton" href="javascript:void(0)" onclick="showQuestion();">问答</a>
				</div>
				
				<!-- 笔记div -->
				<div id="noteDiv">
					<textarea id="noteText"></textarea>
					<div>
						<input id="saveButton" type="button" value="保存" onclick="saveNote();"/> 
					</div>
					<br/>
					<hr class="hrStyle"/>
					<!-- 笔记列表 -->
					<div id="noteList">
						<div id="noteListContents">
						</div>
						<div id="noteListPager" class="courseStudyPager">
						</div>
					</div>
				</div>
				
				<!-- 问答div -->
				<div id="questionDiv">
					<span id="askTitle">提问标题：</span><br/>
					<input id="questionTitle" type="text"/>
					<br/>
					<span id="askContent">提问内容：</span>
					<br/>
					<textarea id="questionContent"></textarea>
					<div>
						<input id="saveReplyButton" type="button" value="保存" onclick="saveQuestion();"/>
					</div>
					<br/>
					<hr class="hrStyle"/>
					<!-- 问题列表 -->
					<div id="questionList">
						<div id="questionListContent">
						</div>
						<div id="questionListPager" class="courseStudyPager">
						</div>
					</div>
				</div>
			</div>
				
			<!-- 隐藏显示按钮 -->
			<div id="hideNoteQuestionDiv">
				<img id="hideNoteQuestion" title="更多操作" src="<%=request.getContextPath()%>/images/img/btn_more.png">
			</div>
			
		</div>
	</div>
	
	<!-- 回复对话框 -->
	<div id="replyDialog">
		<textarea id="replyText"></textarea>
	</div>
</body>
</html>