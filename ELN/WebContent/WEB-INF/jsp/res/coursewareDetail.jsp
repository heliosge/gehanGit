<%@page import="com.jftt.wifi.util.PropertyUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程学习</title>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/scorm.js"></script>
<!-- flexPaper -->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/flexPaper/js/flexpaper_flash.js"></script>
<!-- jquery_paginator -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css">
<!-- artDialog -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/artDialog-master/dist/dialog-min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/artDialog-master/css/ui-dialog.css">
<!-- 引入样式表 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- 视屏播放 -->
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/ckplayer6.7_bak/ckplayer/ckplayer.js" charset="utf-8"></script> 
 <!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- 页面样式 -->
<style type="text/css">
	.course_tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.course_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
</style>

<script type="text/javascript">
var courseWareId = '${courseWareId}';
var courseWareType = '${courseWareType}';
var isFree = '${isFree}';//  -chenrui
var videoLimitMinute ='${VideoLimitMinute}';
var scormLimitMinute ='${ScormLimitMinute}';
var limitPage ='${LimitPage}';
var initFlag = true;
var API = null;

	/**
	 * 页面加载完毕
	 */
	$(function() {
		initCourseWare();
		if(initFlag){
			init_API();
			initFlag = false;
		}
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
				if(data != null && data != ''){
					var path = data.filePath;//课件路径
					//根据课件类型具体展现该课件
					if(courseWareType == 1){//标准课件，zip
						$("#catalog").show();
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
	 * 初始化video
	 */
	 function initZip(data){
		 	filePath = data.filePath;
		 	var htmlStr = '<div class="course_tree" id="tree" style="display:none;background-color:#FFF;position:absolute;top:1px;left:1px;z-index:999;">'
		 	+'<ul id="treePage" class="ztree"></ul>'
		 	+'</div>'
		 	+'<div style="position:relative;">'
		 	+'<iframe id="coursewareIFrame" style="width: 100%;height: 680px;" frameborder="0"/>'
		 	+'</div>';
		 	$("#courseWarePlayer").html(htmlStr);
		 	initTree(data);
		 }
	var filePath;
	function initTree(data){
		var setting = {
				data: {
					key: {url: "xUrl"
					},
					simpleData: {enable: true }
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
		
		var node = JSON.parse(data.fileName);
		var flag = true;
		while(flag && node.children.length > 0){
			for(var i=0;i<node.children.length;i++){
				if(node.children[i].filePath != ''){
					$("#coursewareIFrame").attr("src",filePath+"/"+node.children[i].filePath);
					flag = false;
					break;
				}
			}
			node = node.children[0];
		}
		
		// 控制预览
		if(isFree == 'false'){
			scormSetTimeOut();
		}
	}
	
	function zTreeOnClick(event, treeId, treeNode){
		if(treeNode.filePath != ''){
			$("#tree").hide();
			$("#coursewareIFrame").attr("src",filePath+"/"+treeNode.filePath);
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
				 PrintPaperAsBitmap : false,
				 
				 ViewModeToolsVisible : true,
				 ZoomToolsVisible : true,
				 NavToolsVisible : true,
				 CursorToolsVisible : true,
				 SearchToolsVisible : true,
				 localeChain: 'en_US'
				 }});
	}

	var previewAlertDialogDisplaying = false;
	var previewAlert=function(obj,callback){
		if (previewAlertDialogDisplaying) {
			return;
		}
		previewAlertDialogDisplaying = true;
		var param = {
				title:"提示",
				okValue: '确定',
				width:"200px",
				ok: callback||function(){
					window.$FlexPaper().gotoPage(1);
					previewAlertDialogDisplaying = false;
					this.close;
				}
		};
		if(typeof obj=="object"){
			$.each(obj,function(k,v){
				param[k]=v;
			});
		}else{
			param["content"]=obj;
		}
		var d = dialog(param);
		d.__backdrop = d.__mask = $('<div />')
		    .css({
		        opacity: .97,
		        background: '#000'
		    });
		d.showModal();
	};

	//控制非免费的PDF文档只能预览十页
	var initfirst = true;
	function onCurrentPageChanged(curPage){
		if(isFree != 'false'){
			return;
		}
		if(!initfirst){
			if(curPage>limitPage){
				window.$FlexPaper().gotoPage(1);
				
				// dialog.alert('只可预览前'+limitPage+'页，请购买完整版！');
				previewAlert('只可预览前'+limitPage+'页，请购买完整版！');

				// 直接使用alert可以绕过这个问题:
				// 触发alert之后，将无法继续拖动滚动条，
				// 存在小问题：点击alert对话框的确定按钮之后，依旧处于拖动滚动条的状态
				// alert('只可预览前'+limitPage+'页，请购买完整版！');

				$("#content").focus();
			}
		}
		initfirst = false;
	}
	/**
	 * 初始化video
	 */
	function initVideo(filePath){
		var flashvars={
				f:filePath,
				p:2,
				e:2,
				b:0,
				ht:'20',
				loaded:'loadedHandler' // 加载监听器 -chenrui
				};
		var params={bgcolor:'#FFF',allowFullScreen:false,allowScriptAccess:'always',wmode:'transparent'};
		CKobject.embedSWF('<%=request.getContextPath()%>/js/ckplayer6.7_bak/ckplayer/ckplayer.swf','courseWarePlayer','ckplayer_courseWarePlayer','1000','600',flashvars,params);	
	}
	
	/**
	 * 显示或者隐藏目录
	 */
	function hideShowMenu(){
		$("#tree").slideToggle();
	}
	
	//chenrui -start
	
	// Scorm预览定时控制
	function scormSetTimeOut(){
		setTimeout(function(){
			dialog.alert('只可预览'+scormLimitMinute+'分钟，请购买完整版！',function(){
				window.close();
			});
			// 防止不点击确定可以一直看，强制3秒后关闭
			setTimeout(function(){
				window.close();
			},3*1000);
		},scormLimitMinute*60*1000);
		
	}
	function loadedHandler(){
		if(isFree != 'false'){
			return;
		}
		//需分开添加监听
		var obj  =CKobject.getObjectById('ckplayer_courseWarePlayer');
		if(obj.getType()){ //HTML5播放器
			obj.addListener('time',returnTimeHandler);
		}else{//Flash播放器
			obj.addListener('time','returnTimeHandler');
		}
	}
	
	function returnTimeHandler(t){//秒
		if(t >(videoLimitMinute*60)){
			alert('只可预览'+videoLimitMinute+'分钟，请购买完整视频！');
			CKobject.getObjectById('ckplayer_courseWarePlayer').videoSeek(0);
			CKobject.getObjectById('ckplayer_courseWarePlayer').videoPause();
		}
	}
	//chenrui -end
</script>
</head>
<body>
	<div class="content" id="content">
		<!-- <h3>课件详情</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课件详情</span>
		 </div>
		 <div onclick="hideShowMenu();" style="cursor: pointer;display:none;" id="catalog">目录</div>
		 <div id="courseWarePlayer" style="width:100%;height:700px;display:block;overflow: auto;position: relative;">
		</div>
	</div>
</body>
</html>
