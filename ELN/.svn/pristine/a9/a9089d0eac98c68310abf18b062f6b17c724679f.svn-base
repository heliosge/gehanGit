<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程学习</title>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- scorm -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/scorm_rte.js"></script>
<!-- flexPaper -->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/flexPaper/js/flexpaper_flash.js"></script>
<!-- ckplayer -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckplayer6.7_bak/ckplayer/ckplayer.js" charset="utf-8"></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- jquery_paginator -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css">
<!-- dateUtil -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>

<style type="text/css">
img {border: medium none;vertical-align: top;}

a{text-decoration: none;}

/**整体框架*/
#courseStudyFrame{width: 1046px;margin: 0px auto;}

/**课件标题部分*/
#courseWareTitleDiv{width: 100%;height: 60px;background-color: rgb(79, 143, 225);}

#titleNameDiv{width: 854px;height: 45px;padding-left: 96px;font-size: 20px;color: rgb(255, 255, 255);
margin-top: 10px;padding-top: 15px;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;}

/**课件学习部分*/
#courseWareStudyDiv{width: 100%;height: auto;min-height: 500px;}

#studyTitleDiv{height: 22px;padding-top: 10px;width: 854px;padding-left: 96px;}

#courseWareMenu{width: 80px;float: left;cursor: pointer;}

#exitDiv{width: 80px;float: right;cursor: pointer;}

#showWareDiv{width: 854px;height: 480px;background-color: #000;margin-left: 96px;position: relative;}

/**笔记问答部分*/
#noteAndQuestionDiv{width: 854px;height: auto;min-height: 200px;margin-left: 96px;}

#noteAndQuestionDiv > ul{float: left;width: 272px;height: 45px;margin: 0px;padding: 0px;font-size: 12px;
margin-left: 1px;}

.beforeChange{list-style: outside none none;float: left;width: 134px;height: 45px;line-height: 45px;
margin-left: -1px;text-align: center;cursor: pointer;
border-left: 1px solid #CCC;
border-top: 1px solid #CCC;
border-right: 1px solid #CCC;
border-bottom: 2px solid #329CE5;}

.change{border-left: 2px solid #329CE5;
border-top : 2px solid #329CE5;
border-right: 2px solid #329CE5;
border-bottom: none;}

#noteAndQuestionDiv > h5{width: 581px;border-bottom: 2px solid #329CE5;height: 46px;float: right;
margin: 0px;padding: 0px;}

#myNoteDiv{clear:both;width:850px;height: auto;min-height: 300px;
border-left: 2px solid #329CE5;
border-bottom : 2px solid #329CE5;
border-right: 2px solid #329CE5;}

#myQuestionDiv{clear:both;width:850px;height: auto;min-height: 300px;display: none;
border-left: 2px solid #329CE5;
border-bottom : 2px solid #329CE5;
border-right: 2px solid #329CE5;}

/**我的笔记*/
#noteText{margin-top: 10px;margin-left: 10px;width: 820px;height: 60px;
border: 1px solid rgba(128, 128, 128, 0.78);}

#saveButton{margin-top: 5px;margin-left: 10px;width: 60px;height: 28px;background-color: rgb(210, 0, 1);
color: rgb(255, 255, 255);border: medium none;cursor: pointer;font-size: 12px;}

#noteHr{margin-left: 10px;width: 825px;height: 1px;height:1px;border:none;border-top:1px dashed grey;}

/**笔记列表展现*/
#noteList{width: 825px;height: auto;min-height: 40px;margin-left: 10px;font-size: 12px;}

/**我的问答*/
#myQuestionDiv{font-size: 12px;}

#askTitle{margin-left: 10px;}

#questionTitle{margin-left: 10px;width: 820px;border: 1px solid rgba(128, 128, 128, 0.78);}

#askContent{margin-left: 10px;}

#questionContent{margin-left: 10px;width: 820px;height: 60px;border: 1px solid rgba(128, 128, 128, 0.78);}

#saveReplyButton{margin-top: 5px;margin-left: 10px;width: 60px;height: 28px;background-color: #D20001;
color: #FFF;border: medium none;cursor: pointer;font-size: 12px;}

#questionHr{margin-left: 10px;width: 825px;height: 1px;height:1px;border:none;border-top:1px dashed grey;}

/**问题列表展现*/
#questionList{width: 825px;height: auto;min-height: 40px;margin-left: 10px;}

.eachQuestionContent{margin-bottom: 8px;}

.questionTitleSpan{font-weight: bold;font-size: 13px;}

.questionContent{width: 825px;word-wrap: break-word;}

.questionTime{color: rgb(101, 99, 99);font-style: normal;}

.aLinkStyle{text-decoration: none;}

.questionReplys{margin-left: 10px;margin-bottom: 5px;word-wrap: break-word;width: 815px;}

.replyTextStyle{background-color: rgb(255, 255, 187);width: 815px;word-wrap: break-word;}

.replyContentStyle{width: 815px;word-wrap: break-word;}

/**课件播放分页栏样式*/
.courseStudyPager{height:30px;}

.courseStudyPager a{display:block; width:30px; height:30px; float:left; line-height:30px; color:#676568; 
text-align:center; border:1px solid #cccccc; margin-right:-1px;}

.courseStudyPager a:hover{color:#fff; background:#0085fe;}

.courseStudyPager .r_first{color:#fff; background:#0085fe;}

.courseStudyPager span{ float:left; display:block;height:30px; line-height:30px; font-weight:bold; margin-right:5px;}

.courseStudyPager .span_1{ font-weight:normal; margin-left:10px;}

.courseStudyPager .span_1 input{ width:45px; height:30px; border:1px solid #cccccc; margin:0 4px;}

.courseStudyPager .btn_2{ background:#999999; color:#fff; text-align:center; width:45px;
 height:30px; display:block; float:right; border:none;}
 
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
var currentContent = '${currentContent}';//上次的学习进度
var backFlag = '${backFlag}';
var PdfTimeInterval = '${PdfTimeInterval}';//pdf课件翻页间隔时间

/**
 * 页面加载完成
 */
$(function(){
	//tab切换
	tabChange();
	//判断zip课件api是否初始化
	if(initFlag){
		init_API();
		initFlag = false;
	}
	//初始化课件
	initCourseWare();
	//初始化课程笔记
	initCourseNotes();
	//初始化课程问题
	initCourseQuestions();
});

/**
 * tab切换
 */
function tabChange(){
	$('#noteAndQuestionDiv').find('li').click(function(){
		$('#noteAndQuestionDiv').find('li').removeClass('change');
		$('#noteAndQuestionDiv>div').css('display','none');
		$(this).addClass('change');
		$('#noteAndQuestionDiv>div').eq($(this).index()).css('display','block');
	});
}

/**
 * 初始化课件
 */
function initCourseWare(){
	$.ajax({
		type:'POST',
		async:true,//异步
		data:{"courseWareId":courseWareId},
		url:'<%=request.getContextPath()%>/courseStudyAction/getCourseWare.action',
		success:function(data){
			if(data != null && data != ''){
				//初始化课件
				$("#titleNameDiv").html(data.name);
				$("#titleNameDiv").attr("title",data.name);
				var path = data.filePath;//课件路径
				//根据课件类型具体展现该课件
				if(courseWareType == 1){//标准课件，zip
					if(currentContent != 'null' || currentContent != null){
						var contents = currentContent.split(",");
						currentContent = contents[contents.length-1];
					}
					initZip(data);
				}else if(courseWareType == 2){//普通课件，swf
					$("#courseWareMenu").hide();
					if(currentContent == 'null' || currentContent == null){
						currentContent = 1;
					}
					initFlexpaper(path);
				}else if(courseWareType == 3){//视频课件，mp4
					$("#courseWareMenu").hide();
					if(currentContent == 'null' || currentContent == null){
						currentContent = 0;
					}
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
	var htmlStr = '<div class="course_tree" id="tree" style="display:none;background-color:#FFF;position:absolute;top:1px;left:1px;z-index:999;">'
	+'<ul id="treePage" class="ztree"></ul>'
	+'</div>'
	+'<div style="position:relative;">'
	+'<iframe id="coursewareIFrame" style="width: 854px;height: 480px;" frameborder="0"/>'
	+'</div>';
	$("#showWareDiv").html(htmlStr);
	if(currentContent != 'null'){
		$("#coursewareIFrame").attr("src",filePath+"/"+currentContent);	
	}else{
		$("#tree").show();
	}
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
	$("#tree").hide();
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
 * 显示或者隐藏目录
 */
function hideShowMenu(){
	if(courseWareType == 1){
		$("#tree").slideToggle();
	}
}

/**
 * 初始化FlexPaper（swf文件展示）
 */
function initFlexpaper(filePath){
	var fp = new FlexPaperViewer(	
			'<%=request.getContextPath()%>/js/flexPaper/FlexPaperViewer',
			 'showWareDiv', { config : {
			 SwfFile : filePath,
			 Scale : 1, 
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
	$("#showWareDiv").children('object').append('<param value="Opaque" name="wmode"></param>');
}

function onDocumentLoaded(totalPages){
	time();
	//lastPage = currentContent - 1;
	//$FlexPaper().gotoPage(currentContent);
	console.info(lastPage);
}

var returnFlag = true;
var lastPage = 1;
function onCurrentPageChanged(curPage){
	if(curPage > lastPage){
		if(curPage - lastPage == 1){
			if(timeInterval > PdfTimeInterval){
				timeInterval = 0;
				lastPage = curPage;
			}else{
				alert("请认真阅读课件");
				$FlexPaper().gotoPage(lastPage);
			}
		}else{
			alert("请认真阅读课件");
			$FlexPaper().gotoPage(lastPage);
		}
	}
}
var timeInterval = 0;
function time(){
	timeInterval++;
	setTimeout(function(){
		time();
	},1000)
}

/**
 * 初始化video
 */
var currentTime = 0
function initVideo(filePath){
	var flashvars={
			f:filePath,
			p:0,
			e:2,
			b:0,
			g:currentContent,
			ht:'20',
			loaded:'loadedHandler'
			};
	var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'Opaque'};
	CKobject.embedSWF('<%=request.getContextPath()%>/js/ckplayer6.7_bak/ckplayer/ckplayer.swf','showWareDiv','ckplayer_courseWarePlayer','100%','100%',flashvars,params);	
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
 * 初始化我的笔记
 */
function initCourseNotes(){
	$("#noteListTable").mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : '<%=request.getContextPath()%>/courseStudyAction/getCourseNotesByUserIdCourseId.action',
		width : '823px',
		height : 'auto',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : false,
		multiSelect : true,
		indexCol : true, // 索引列
		params : {"userId":userId,"courseId":courseId},
		cols : [{
			title : 'id',
			name : 'id',
			width : '0px',
			hidden : true
		},{
			title : '笔记内容',
			name : 'content',
			align : 'center'
		},{
			title : '记录时间',
			name : 'createTime',
			align : 'center',
			width : 200,
			lockWidth : true,
			renderer : function(val, item, rowIndex) {
				return getSmpFormatDateByLong(val);
			}
		}],
		plugins : [ $('#noteListPager').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean total
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [10,20,50,100]
		}) ]
	});
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
				$("#noteListTable").mmGrid().load();
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
			items_per_page:10,//每页显示条数
			prev_text:'<',
			next_text:'>',
			prev_show_always:false,//不一直显示上一页
			next_show_always:false,//不一直显示下一页
			num_edge_entries:2,//两侧显示的首尾分页的条目数
			callback:function(page,node){
				var param = {};
				param.courseId = courseId;
				param.companyId = companyId;
				param.subCompanyId = subCompanyId;
				param.page = page;
				param.pageSize = 10;
				$.ajax({
					type:'POST',
					async:true,
					data:param,
					url:'<%=request.getContextPath()%>/courseStudyAction/getCourseQuestions.action',
					success:function(data){
						if(data != null && data.length > 0){
							
							/* <div class="eachQuestionContent">
								<span class="questionTitleSpan">提个问题--admin</span>
								<div class="questionContent">这一课主要讲的什么啊？没太听懂</div>
								<span>
									<em class="questionTime">2016-01-01 12:11:52</em>&nbsp;
									<a href="javascript:void(0)" class="aLinkStyle">回复</a>
								</span>
								<div class="questionReplys">
									<div class="replyTextStyle">
										admin:不知道。<br/>
										admin:没听懂。<br/>
									</div>
									<div class="replyContentStyle">
										admin:有谁听懂了的，举个手。
									</div>
									<span>
										<em class="questionTime">2016-01-01 12:11:52</em>&nbsp;
										<a href="javascript:void(0)" class="aLinkStyle">回复</a>
									</span>
								</div>
								<div class="questionReplys">
									<div class="replyTextStyle">
										admin:不知道。<br/>
										admin:没听懂。<br/>
									</div>
									<div class="replyContentStyle">
										admin:有谁听懂了的，举个手。
									</div>
									<span>
										<em class="questionTime">2016-01-01 12:11:52</em>&nbsp;
										<a href="javascript:void(0)" class="aLinkStyle">回复</a>
									</span>
								</div>
							</div> */
							
							var htmlStr = '';
							for(var i = 0; i < data.length; i++){
								var question = data[i];
								htmlStr += '<div class="eachQuestionContent">';
								htmlStr += '<span class="questionTitleSpan">'+question.title+'--'+question.userName+'</span>';
								htmlStr += '<div class="questionContent">'+question.content+'</div>';
								htmlStr += '<span>';
								htmlStr += '<em class="questionTime">'+getSmpFormatDateByLong(question.asktime)+'</em>&nbsp;';
								htmlStr += '<a href="javascript:void(0)" class="aLinkStyle" onclick="answerThisQuestion('+question.id+');">回复</a>';
								htmlStr += '</span>';
								//回复的内容
								$.ajax({
									type:'POST',
									async:false,
									data:{"topicId":question.id},
									url:'<%=request.getContextPath()%>/courseStudyAction/getReplys.action',
									success:function(data){
										var replys = data;
										if(replys != null && replys.length > 0){
											for(var j = 0; j < replys.length; j++){
												var reply = replys[j];
												var replyContent = '';//要添加的回复富文本内容
												htmlStr += '<div class="questionReplys">';
												//回复富文本
												if(reply.replyText){
													htmlStr += '<div class="replyTextStyle">';
													htmlStr += reply.replyText;
													htmlStr += '</div>';
													replyContent += reply.replyText;
												}
												//回复内容
												htmlStr += '<div class="replyContentStyle">';
												htmlStr += reply.userName + ' : ' +reply.content;
												htmlStr += '</div>';
												//要添加的回复富文本内容
												replyContent += reply.userName + ' : ' +reply.content;
												replyContent += '<br/>';
												$("body").data("replyContent"+reply.id+"",replyContent);
												//回复时间和回复按钮
												htmlStr += '<span>';
												htmlStr += '<em class="questionTime">'+getSmpFormatDateByLong(reply.answertime)+'</em>&nbsp;';
												htmlStr += '<a href="javascript:void(0)" class="aLinkStyle" onclick="answerThisReply('+reply.id+');">回复</a>';
												htmlStr += '</span>';
												htmlStr += '</div>';
											}
										}
									}
								});
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
	var count = 0;
	$.ajax({
		type:'POST',
		async:false,//此处需要同步
		data:{"courseId":courseId,"companyId":companyId,"subCompanyId":subCompanyId},
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
	var question = {};
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
 * 回复问题
 */
function answerThisQuestion(questionId){
	var saveButton = document.getElementById("saveReplyButton");//保存按钮
	var questionDiv = document.getElementById("questionListPager");//问题列表div
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
	var questionDiv = document.getElementById("questionListPager");//问题列表div
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
 * 退出学习
 */
function exitStudy(){
	//跳转到课程详情页面
	window.location.href = "<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId="+courseId+"&backFlag="+backFlag;
}

/**
 * 页面离开事件
 */
window.onbeforeunload = function(){
	//如果是视频课件，暂停视频课件
	if(courseWareType == 3){
		pauseVideo();
	}
	//记录课件学习进度
	recordCourseWare();
};

/**
 * 暂停视频课件播放
 */
function pauseVideo(){
	CKobject.getObjectById('ckplayer_courseWarePlayer').videoPause();
}

/**
 * 记录课件学习进度
 */
function recordCourseWare(){
	var wareRecordId = null;//课件学习记录id
	var courseRecordId = null;//课程学习记录id
	//查询出该课件的学习记录
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:{"sectionId":sectionId,"courseWareId":courseWareId,"userId":userId},
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
		var param = {};
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
		var swfWareParam = {};
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
		var vedioParam = {};
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
	<!-- 整体框架 -->
	<div id="courseStudyFrame">
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="exitStudy();">
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课程学习</span>
		</div>
	
		<!-- 课件标题部分 -->
		<div id="courseWareTitleDiv">
			<div id="titleNameDiv"></div>
		</div>
		
		<!-- 课件学习部分 -->
		<div id="courseWareStudyDiv">
			<div id="studyTitleDiv">
				<div id="courseWareMenu" onclick="hideShowMenu();">目录</div>
				<div id="exitDiv" onclick="exitStudy();">退出学习</div>
			</div>
			<!-- 课件展现部分 -->
			<div id="showWareDiv"></div>
		</div>
		
		<!-- 分割线 -->
		<hr/>
		
		<!-- 笔记问答部分 -->
		<div id="noteAndQuestionDiv">
			<ul>
				<li class="beforeChange change">我的笔记</li>
				<li class="beforeChange">我的问答</li>
			</ul>
			<h5></h5>
			<!-- 我的笔记 -->
			<div id="myNoteDiv">
				<textarea id="noteText"></textarea>
				<div>
					<input id="saveButton" type="button" value="提交" onclick="saveNote();"/> 
				</div>
				<!-- 分割线 -->
				<hr id="noteHr"/>
				<!-- 笔记列表 -->
				<div id="noteList">
					<!-- 列表 -->
					<table id="noteListTable"></table>
					<!-- 分页 -->
					<div id="noteListPager" style="text-align: right;"></div>
				</div>
			</div>
			<!-- 我的问答 -->
			<div id="myQuestionDiv">
				<span id="askTitle">提问标题：</span><br/>
				<input id="questionTitle" type="text"/>
				<br/>
				<span id="askContent">提问内容：</span>
				<br/>
				<textarea id="questionContent"></textarea>
				<div>
					<input id="saveReplyButton" type="button" value="提交" onclick="saveQuestion();"/>
				</div>
				<hr id="questionHr"/>
				<!-- 问题列表 -->
				<div id="questionList">
					<div id="questionListContent">
						<!-- <div class="eachQuestionContent">
							<span class="questionTitleSpan">提个问题--admin</span>
							<div class="questionContent">这一课主要讲的什么啊？没太听懂</div>
							<span>
								<em class="questionTime">2016-01-01 12:11:52</em>&nbsp;
								<a href="javascript:void(0)" class="aLinkStyle">回复</a>
							</span>
							<div class="questionReplys">
								<div class="replyTextStyle">
									admin:不知道。<br/>
									admin:没听懂。<br/>
								</div>
								<div class="replyContentStyle">
									admin:有谁听懂了的，举个手。
								</div>
								<span>
									<em class="questionTime">2016-01-01 12:11:52</em>&nbsp;
									<a href="javascript:void(0)" class="aLinkStyle">回复</a>
								</span>
							</div>
							<div class="questionReplys">
								<div class="replyTextStyle">
									admin:不知道。<br/>
									admin:没听懂。<br/>
								</div>
								<div class="replyContentStyle">
									admin:有谁听懂了的，举个手。
								</div>
								<span>
									<em class="questionTime">2016-01-01 12:11:52</em>&nbsp;
									<a href="javascript:void(0)" class="aLinkStyle">回复</a>
								</span>
							</div>
						</div> -->
						<!-- <div class="eachQuestionContent">
							<span class="questionTitleSpan">提个问题--admin</span>
							<div class="questionContent">这一课主要讲的什么啊？没太听懂</div>
							<span>
								<em class="questionTime">2016-01-01 12:11:52</em>&nbsp;
								<a href="javascript:void(0)" class="aLinkStyle">回复</a>
							</span>
						</div> -->
					</div>
					<div id="questionListPager" class="courseStudyPager"></div>
					<br/>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 回复对话框 -->
	<div id="replyDialog">
		<textarea id="replyText"></textarea>
	</div>
</body>
</html>