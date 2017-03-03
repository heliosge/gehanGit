<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专题问答详情</title>
<!-- jquery -->
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<!-- niceValidate -->
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- jQueryUI -->
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script src="<%=request.getContextPath() %>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqmeter.min.js"></script>
<!-- 标签使用插件 -->
<script src="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tags.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/courseTags.css"/>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**框架*/
#thematicAskDetailContent{width: 1066px;margin: 16px auto 0px;padding-bottom: 200px;clear: both;overflow: hidden;}

/**框架标题*/
#thematicAskDetailContent > h3{
width: 1052px;padding-left: 20px;color: #3A3A3A;border-bottom: 1px solid #CCC;padding-bottom: 10px;
margin-bottom: 10px;font-size:14px;}

/**链接属性*/
a{text-decoration: none;cursor:pointer;}

/**返回连接*/
#thematicAskDetailContent > h3 > a{font-size:24px;color:#888888;}

/**问题*/
#askDiv{width:990px;min-height:160px;height:auto;border:1px solid #0091DE;
margin-left:20px;}

/**问题标题部分*/
#askTitle{width: 970px;height: 30px;background-color: #0091DE;color: #FFF;
padding-left: 20px;padding-top: 8px;font-size:13px;font-weight:bold;}

/**问题内容部分*/
#askContent{width:970px;min-height:140px;height:auto;background-color:#FFF;color:#000000;
padding-left:20px;padding-top:8px;padding-bottom:15px;}

#askContent > h3{margin: 3px auto;font-size:16px;}

#askAboult{color: grey;font-size: 13px;}

#askDetailContent{padding-top: 20px;font-size: 13px;width:970px;word-wrap:break-word;}

#askerAndTime{color: grey;font-size: 13px;}

/**满意答案*/
#satisfactoryAnswerDiv{width:970px;min-height:80px;height:auto;border:1px solid rgba(221, 5, 0, 0.58);
margin-bottom:5px;margin-left:20px;padding-left:20px;padding-top:8px;padding-bottom:15px;
background-color: #FFF;margin-bottom: 20px;margin-top: 20px;}

#satisfactoryAnswerDiv  h3{margin: 3px auto;font-size:16px;}

#answerContent{font-size: 13px;width:970px;word-wrap:break-word;}

#answerTime{color: grey;font-size: 13px;}

/**其他答案*/
#otherAnswerDiv{width:970px;min-height:80px;height:auto;border:1px solid #0091DE;
margin-bottom:5px;margin-left:20px;padding-left:20px;padding-top:8px;padding-bottom:15px;}

#otherAnswerDiv > h3{margin: 3px auto;font-size:16px;}

.otherAnswerContent{font-size: 13px;width:970px;word-wrap:break-word;}

.otherAnswerTime{color: grey;font-size: 13px;margin-top: 8px;}

/**em样式*/
.thisEmStyle{font-style: normal;}

/**添加图片div*/
#addPicsDiv{width:950px;}

.addPicStyle{width: 155px;height: 80px;padding-right: 10px;}

/**我来回答*/
#myReplyDiv{width: 990px;min-height: 200px;height: auto;margin-left: 20px;font-size:14px;
border-left: 1px solid #0091DE;
border-right: 1px solid #0091DE;
border-bottom: 1px solid #0091DE;}

#myReplyDiv > h3{font-size:16px;margin-top:0px;margin-left:20px;padding-top:10px;}

#replyContent{margin-left: 20px;width: 944px;height: 200px;margin-bottom: 20px;}

/**推荐一些资源*/
#suggestResourceDiv{padding-left: 20px;width: 200px;height:60px;}

#chosePlanformResourceButton{width: 120px;height: 33px;border: medium none;margin-top: 6px;
cursor: pointer;background: #D60500 none repeat scroll 0% 0%;color: #FFF;}

/**提交div*/
#submitDiv{padding-left: 20px;width: 950px;margin-bottom: 10px;height:40px;}

#submitButton{width: 60px;height: 33px;border: medium none;margin-top: 6px;
cursor: pointer;background: #D60500 none repeat scroll 0% 0%;color: #FFF;float:right;}

#hideNameDiv{width: 90px;height: 40px;float: right;margin-top: 10px;}

/**弹出课程样式*/
.treeDiv1{float: left;width:210px; min-height: 305px;max-height: 305px; border: 1px solid #cdcdcd;}

.treeDiv2{overflow:auto; min-height: 295px;max-height: 295px;}

.search_3 {width: 758px;height: 40px;background: #fff;padding-left: 10px;font-family: '微软雅黑';
background: #fbfbfb;padding-right: 10px;border: 1px solid #ccc;float: left;}

.search_3 p {float: left;color: #666; margin: 5px;}

.search_3 input[type=button] {float: right;width: 60px;height: 28px;border: none;margin-top: 6px;}

.search_3 .btn_cx {background: #d20001;color: #fff;margin-right: 10px;}

.search_3 p input[type=text] {width: 135px;height: 28px;border: 1px solid #cccccc;}

.btn{font-size:14px;font-family:微软雅黑;margin-right:5px;cursor: pointer;
border-radius:2px;border:none;background-color:#d60500;padding:9px 10px;color: #ffffff;}
	
.kl-item{margin:5px 10px 5px 0px;display:inline-block;list-style-type:none;white-space:nowrap;
background-color: #e4e4e4;padding:10px 10px;cursor: pointer;}

.file-img{width:10px;padding-left:10px;}

.subClass ul{ list_style:none;}

.subClass ul li{ margin:5px 10px 5px 0px;display:inline-block;list-style-type:none;white-space:nowrap;
background-color: #e4e4e4;padding:10px 10px;cursor: pointer;}

.replyContentStyle{font-size: 15px;color: rgb(0, 145, 222);padding-bottom: 6px;}

.addCoursesOrFilesStyle{font-size: 13px;margin-bottom: 5px;}

.eachAnswerStyle{background-color: rgba(128, 128, 128, 0.2);width: 950px;height: auto;}

.answerContentStyle{word-wrap: break-word;margin-left: 15px;width: 920px;}

.resourceStyle{word-wrap: break-word;margin-left: 15px;width: 920px;}

/**上传按钮样式*/
.webuploader-pick {position: relative;display: inline-block;cursor: pointer;
background: #0085FE none repeat scroll 0% 0%;
padding: 10px 15px;color: #fff;text-align: center;border-radius: 3px;overflow: hidden;}
</style>

<script type="text/javascript">
var askId = '${askId}';
var filedata = [];
var uploader;

/**
 * 页面加载完毕
 */
$(function(){
	//获取问问基本信息
	getAskDetail();
	//获取回答列表
	getAnswerList();
});

//获取问问基本信息
function getAskDetail(){
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:{"askId":askId},
		url:'<%=request.getContextPath()%>/myAsk/getAskDetail.action',
		success:function(data){
			if(data != ''){
				$("#askContentH3").text(data.title);
				$("#answerCount").text(data.answerCount);
				if(data.typeName){
					$("#thematicAskType").text(data.typeName);
				}
				$("#askDetailContent").html(data.content);
				if(data.label){
					$("#thematicAskTab").text(data.label);
				}
				if(data.addPics){
					var pics = data.addPics.split(",");
					for(var i=0;i<pics.length;i++){
						$("#addPicsDiv").append('<img class="addPicStyle" src="'+pics[i]+'"/>');
					}
				}
				$("#askTime").text(data.createTime);
				$("#askerName").text(data.name);
			}
		}
	});
}

//获取回答列表
function getAnswerList(){
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:{"questionId":askId},
		url:'<%=request.getContextPath()%>/myAsk/getAnswerList.action',
		success:function(data){
			if(data != ''){
				var answer1 = '';
				var answer2 = '';
			for(var i=0;i<data.length;i++){
				if(data[i].isSatisfactory == 1){
					answer1 += '<div class="eachAnswerStyle">';
					if(data[i].resourceNames != null && data[i].resourceNames != '' 
							&& data[i].fileNames != null && data[i].fileNames != ''){
						var resourceNamesArr = data[i].resourceNames.split(",");
						var fileNamesArr = data[i].fileNames.split(",");
						answer1 += '<div class="addCoursesOrFilesStyle">';
						answer1 += '<div class="replyContentStyle">推荐资源：</div>';
						answer1 += '<div class="resourceStyle">';
						if(data[i].platformResources != null && data[i].platformResources != ''){
							var platformResourcesArr = data[i].platformResources.split(",");
							for(var j = 0; j < platformResourcesArr.length; j++){
								answer1 += '<span>《<a href="<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId='+platformResourcesArr[j]+'">'+resourceNamesArr[j]+'</a>》-课程</span>';
								answer1 += '<br/>';
							}
						}
						if(data[i].localFiles != null && data[i].localFiles != ''){
							var localFilesArr = data[i].localFiles.split(",");
							for(var j = 0; j < localFilesArr.length; j++){
								answer1 += '<span>《<a href="'+localFilesArr[j]+'">'+fileNamesArr[j]+'</a>》-文件</span>';
								answer1 += '<br/>';
							}
						}
						answer1 += '</div>';
						answer1 += '</div>';
					}
					answer1 += '<div id="answerContent">';
					answer1 += '<div class="replyContentStyle">回答内容：</div>';
					answer1 += '<div class="answerContentStyle">'+data[i].content+'</div>';
					answer1 += '</div>';
					answer1 += '<div class="otherAnswerTime">'+data[i].replyerName;
					answer1 += '&nbsp;'+data[i].createTime+'</div>';
					answer1 += '</div>';
					answer1 += '<br/>';
				
				<%-- <div class="addCoursesOrFilesStyle">
					<span class="replyContentStyle">回答推荐资源：</span><br/>
					<span>《<a href="<%=request.getContextPath()%>/res/toCourseDetail.action?courseId=72">测试开放人群001</a>》-课程</span>
					<br/>
					<span>《<a href="/upload/ask/20160108/7a467a8c-8e2d-4a57-b8dc-c2799a259340.doc">学籍卡.doc</a>》-文件</span>
					<br/>
				</div>
				<div id="answerContent">
					<span class="replyContentStyle">回答具体内容：</span><br/>
					太阳的"长斑周期"长期的观察累积的数据让科学家意识到，太阳黑子的出现是有一定规律的。
				</div>
				<div class="otherAnswerTime">邓伟伟&nbsp;2014-11-04 08:54:02</div>
				<br/> --%>
				}else{
					answer2 += '<div class="eachAnswerStyle">';
					if(data[i].resourceNames != null && data[i].resourceNames != '' 
						&& data[i].fileNames != null && data[i].fileNames != ''){
						var resourceNamesArr = data[i].resourceNames.split(",");
						var fileNamesArr = data[i].fileNames.split(",");
						answer2 += '<div class="addCoursesOrFilesStyle">';
						answer2 += '<div class="replyContentStyle">推荐资源：</div>';
						answer2 += '<div class="resourceStyle">';
						if(data[i].platformResources != null && data[i].platformResources != ''){
							var platformResourcesArr = data[i].platformResources.split(",");
							for(var j = 0; j < platformResourcesArr.length; j++){
								answer2 += '<span>《<a href="<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId='+platformResourcesArr[j]+'">'+resourceNamesArr[j]+'</a>》-课程</span>';
								answer2 += '<br/>';
							}
						}
						if(data[i].localFiles != null && data[i].localFiles != ''){
							var localFilesArr = data[i].localFiles.split(",");
							for(var j = 0; j < localFilesArr.length; j++){
								answer2 += '<span>《<a href="'+localFilesArr[j]+'">'+fileNamesArr[j]+'</a>》-文件</span>';
								answer2 += '<br/>';
							}
						}
						answer2 += '</div>';
						answer2 += '</div>';
					}
					answer2 += '<div id="answerContent">';
					answer2 += '<div class="replyContentStyle">回答内容：</div>';
					answer2 += '<div class="answerContentStyle">'+data[i].content+'</div>';
					answer2 += '</div>';
					answer2 += '<div class="otherAnswerTime">'+data[i].replyerName;
					answer2 += '&nbsp;'+data[i].createTime+'</div>';
					answer2 += '</div>';
					answer2 += '<br/>';
					
					<%-- <div class="addCoursesOrFilesStyle">
						<span class="replyContentStyle">回答推荐资源：</span><br/>
						<span>《<a href="<%=request.getContextPath()%>/res/toCourseDetail.action?courseId=72">测试开放人群001</a>》-课程</span>
						<br/>
						<span>《<a href="/upload/ask/20160108/7a467a8c-8e2d-4a57-b8dc-c2799a259340.doc">学籍卡.doc</a>》-文件</span>
						<br/>
					</div>
					<div class="otherAnswerContent">
						<span class="replyContentStyle">回答具体内容：</span><br/>
						火山喷发将大量的氮气排放到空气中，与此同时也喷薄出硫磺、水蒸气和二氧化碳。当地壳被挤压，大量的不稳定物质释放到位于上方的石头中。这些
						物质包括氮元素，他们或者被压制到石头中，或者变成气体在火山喷发的时候释放出来。
					</div>
					<div class="otherAnswerTime">邓伟伟&nbsp;2014-11-04 08:54:02</div>
					<br/> --%>
				}
			}
			$("#answerDiv").html(answer1);	
			$("#othersDiv").html(answer2);	
			}
		}
	});
}

/**选择平台资源  */
var idArray = [];
var nameArray = [];
var tempList = [];
function selectCourse(){
	idArray = [];
	nameArray = [];
	if(tempList != ''){
		for(var i=0;i<tempList.length;i++){
			idArray.push(tempList[i].id);
			nameArray.push(tempList[i].name);
		}
	}
	initTree();
	var selectCourse = $("#selectCourse");
	dialog({
		title : "选择平台资源",
		width : 980,
		height : 350,
		content :selectCourse,
		onshow: function() {
			setTimeout(initMMgrid, 200);
		},
		okValue : '确定',
		fixed:true,
		ok : function() {
			var items = $('#courseTable').mmGrid().selectedRows();
			if(items.length == 0){
				dialog.alert("请选择平台资源！");
				return false;
			}else{
				var results = [];
				if(tempList != ''){
					for(var i=0;i<tempList.length;i++){
						results.push(tempList[i]);
					}
				}
				for(var index = items.length-1; index>=0; index--){
					var item = items[index];
					if(item){
						if(idArray.indexOf(item.id) != -1){
							continue;
						}
						results.push(item);
					}
				}
				tempList = results;
				initList(tempList);
			}
		},
		cancelValue : '取消',
		cancel : function() {
		}
	}).showModal();	
}

/*生成人员列表  */
function initList(tempList){
	$("#courseList").empty();
	for(var i=0;i<tempList.length;i++){
		$("#courseList").append('<li id="'+tempList[i].id+'">'+'<em style="font-style: normal;">'+tempList[i].name+'</em>'
				+'<img class="file-img" src="<%=request.getContextPath()%>/images/question_delete.png" onclick="delCourse(this)"/>'
				+'</li>');
	}
}

/*点击人员删除图标  */
function delCourse(obj){
	var parentLi = $(obj).parent();
	parentLi.remove();
	delFromTempList(parentLi.attr("id"));
}

/*将删除的人员从列表中去除  */
function delFromTempList(id){
	idArray = [];
	var results = [];
	var len=tempList.length;
	for(var i=0;i<len;i++){
		var lid = tempList[i].id;
		if(id==lid){
			continue;
		}
		results.push(tempList[i]);
	}
	tempList = results;
}

/*初始化课程体系 树状结构  */
function initTree(){
	categoryId = undefined;
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
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
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/res/selectResCourseCategory.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#courseTree"), setting, data);
		}
	});
	$.fn.zTree.getZTreeObj("courseTree").expandAll(true);
}

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

var categoryIds = [];
var categoryId;
var node;
function zTreeOnClick(event, treeId, treeNode) {
	categoryId = treeNode.id;
	node = treeNode;
	categoryIds = [];
	getChildNodes(treeNode);
	search();
};

function getChildNodes(treeNode){
	categoryIds.push(treeNode.id); 
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			categoryIds.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

function getChildNodesNotAddThis(treeNode){
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			categoryIds.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

/*查询参数  */
function param(){
	var o = {};
	o.likeName = escapeForSql($("#courseName").val());
	o.categoryId = categoryIds;
	return o;
}

/*初始化课程表格-选择平台资源弹出框  */
function initMMgrid(){
	//表格
	$('#courseTable').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/res/getOnlineCourseList.action",
		width: $('#courseTable').parent().width(),
		height: '265px',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    multiSelect: true,
	    checkCol: true,
	    indexCol: true,  //索引列
	    params: function(){
	    	return  param();
	    },
	 	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
	           {title: '课程编号', name: 'code', width:60, align:'center'},
				   {title: '课程名称', name: 'name', width:160, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="<%=request.getContextPath()%>/res/toCourseDetail.action?courseId='+item.id+'" >'+val+'</a>';
			   }},
				   {title: '课程类型', name: 'type', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "线上课程 ";
				   }else if(val == 2){
					   return "直播课程";
				   }
				}},
				   {title: '课程体系', name: 'categoryName', width:60, align:'center'}
	       ],
	    plugins : [
	    	$('#paginator11-1').mmPaginator({
	    		totalCountName: 'total',    //对应MMGridPageVoBean count
	    		page: 1,                    //初始页
	    		pageParamName: 'page',      //当前页数
	    		limitParamName: 'pageSize', //每页数量
	    		limitList: [10, 20, 40, 50]
	    	})
	    ]
	});
}

/*平台资源查询  */
function search(){
	$('#courseTable').mmGrid().load();
}

//初始化参数
$(function(){
	//$("#klTags").tags({inputName: "tags" });
	var mimeType ="application/pdf,text/plain,application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation";
		mimeType+=",application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/msword";
		mimeType+=",application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel";
	//上传文件
	uploader = WebUploader.create({
		auto: true,
	    // swf文件路径
	    swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',
	    // 文件接收服务端。
	    server:'<%=request.getContextPath()%>/knowledge/uploadFile.action?path=ask',
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: {id:'#picker',
    		multiple:true
    	},
    	fileNumLimit:10,
    	fileSingleSizeLimit:209715200,//单个文件200M
	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: false,
	    // 只允许选择图片文件。
	    accept: {
	        title: 'Images',
	        extensions: 'xls,xlsx,doc,docx,ppt,pptx,pdf,txt',
	        mimeTypes: mimeType
	    }
	});
	
	uploader.on( 'uploadAccept', function( file ,ret ) {
		  filedata.push({
			  //knowledgeName: file.file.name.substring(0, file.file.name.lastIndexOf(".")),
              filePath: ret.path,
              extendName : file.file.ext,
              //fileSize: file.file.size,
              //fileName:file.file.name.substring(0, file.file.name.lastIndexOf("."))
          })
          $("#fileCount").val(filedata.length).next("span.msg-box").hide();
	});
	
	uploader.on( 'uploadFinished', function(file,ret){
		//上传完成，去掉蒙层
		layer.closeAll();
		$index = null;
	});
	
	var flag = false;
 	uploader.on('error', function(type) {
 		dialog.getCurrent()?dialog.getCurrent().close():null;
        if (type == 'Q_EXCEED_NUM_LIMIT') {
	 		flag =true;
            dialog.alert('上传文件个数超过限制');
            return;
        }
        if (type == 'F_EXCEED_SIZE') {
	 		flag =true;
            dialog.alert('上传文件过大');
            return;
        }
        if (type == 'Q_TYPE_DENIED') {
	 		flag =true;
            dialog.alert('上传文件类型不匹配或者文件大小为空');
            return;
        }
        if(type == 'F_DUPLICATE'){
            return;
        }
    });
 	
	uploader.on('startUpload', function(file) {
		if(flag){
			uploader.reset()
		}
		flag =false;
	});
	
	var $index;
	uploader.on('uploadStart', function(file) {
		//开始上传，进行蒙层处理
        if(!$index){
            $index = layer.open({
            	type: 1,
            	//shade: [0.3,'#000'], 
            	title: "文件上传中", //不显示标题
            	content: $('.layer_notice'), //捕获的元素
            	cancel: function(index){
            		layer.close(index);
    				$("div.kl-item").find("span.close").click();
            	}
            }); 
        }
			
		//当没有文件时，进行清空操作
		if($("div.left_up").find("div.kl-item").length==0){
			$("div.left_up").data("html",$("div.left_up").html());
			$("div.left_up").empty();
		}
	    $("div.left_up").append( '<div id="' + file.id + '" class="kl-item">' +
	        '<span class="info">' + file.name + '</span>' +
	        '<img src="<%=request.getContextPath()%>/images/question_delete.png" class="file-img"  onclick="closeFile(this)"></img>'+
	        '<div class="progress"></div>'+
	    '</div>' );
	});
	
	// 文件上传过程中创建进度条实时显示。
	uploader.on( 'uploadProgress', function( file, percentage ) {
	    var $li = $("li.progress");
	    $li.find("label.file-name").html(file.name).attr("title",file.name);
	    var processedPercentage = 100*percentage-1;
        processedPercentage = Math.round(processedPercentage < 0 ? 0 : processedPercentage);
        initJqMeter(processedPercentage);	
    });
});

/**
 * 初始化进度条
 */
function initJqMeter(value){
	$("#jqMeter_div").jQMeter({
		 goal:'100',
		 raised:value+'',
		 meterOrientation:'horizontal',
		 width:'300px',
		 height:'25px'
	});
}

//清除文件信息,并删除其信息
function closeFile(_this){
	filedata.splice($(_this).closest("div.kl-item").index(),1);
	$(_this).closest("div.kl-item").remove();
	if(filedata.length==0){
		$("div.left_up").html($("div.left_up").data("html"));
	}
	$("#fileCount").val(filedata.length==0?"":filedata.length);
	uploader.cancelFile( $(_this).closest("div.kl-item").attr("id"));
};

//保存
function submitAnswer(){
	//$.blockUI({message:"<font size='5'>提交中，请耐心等待...</font>"});
	if($.trim($("#replyContent").val()) == ''){
		dialog.alert("请填写回答内容！");
		return;
	}
	var layerIndex = layer.msg('保存中…', {icon:16, time:0});
	var newBean = {};
	var fileStr = '';
	for (var i = 0; i < filedata.length; i++) {
         var item = filedata[i];
         fileStr += filedata[i].filePath.substr(0,filedata[i].filePath.length-4)+"."+filedata[i].extendName;
         if(i != filedata.length - 1){
        	 fileStr += ',';
         }
     }
	 var isAnonymous = 2;
	 if($("#hideName:checked").val()){
		 isAnonymous = 1;
	 }
	 var courseIds = '';
	 var courseNames = '';
	 $("#courseList").find("li").each(function(index,element){
		 courseIds += $(this).attr("id") + ",";
		 courseNames += $(this).children("em").html() + ',';
	 });
	 if(courseIds != ''){
		 courseIds = courseIds.substr(0,courseIds.length-1);
	 }
	 if(courseNames != ''){
		 courseNames = courseNames.substr(0,courseNames.length-1);
	 }
	 var fileNames = '';
	 $("#fileDiv").find(".kl-item").each(function(index,element){
		 fileNames += $(this).children("span").html() + ',';
	 });
	 if(fileNames != ''){
		 fileNames = fileNames.substr(0,fileNames.length-1);
	 }
	 newBean.questionId = askId;
	 newBean.content = $.trim($("#replyContent").val());
	 if(courseIds != ''){
		 newBean.platformResources = courseIds;
	 }
	 if(courseNames != ''){
		 newBean.resourceNames = courseNames;
	 }
	 if(fileStr != ''){
		 newBean.localFiles = fileStr;
	 }
	 if(fileNames != ''){
		 newBean.fileNames = fileNames;
	 }
	 newBean.isAnonymous = isAnonymous;
	 newBean.isDelete = 2;//未删除
	 newBean.isThematic = 2;//专题问答
	 newBean.isShield = 2;//未屏蔽
	 newBean.isSatisfactory = 2;//不是满意答案
	 //alert(JSON.stringify(newBean));
	 
     $.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		traditional:true,
	    contentType : 'application/json;charset=utf-8',
   		data:JSON.stringify(newBean),
   		url:"<%=request.getContextPath()%>/myAsk/addAnswer.action",
   		success:function(data){
   			layer.close(layerIndex);
   			if(data == "SUCCESS"){
   				dialog.alert("保存成功",function(){
	   				//刷新回答列表
					getAnswerList();
					//清空输入框
					$("#replyContent").val('');
					//关闭上传的平台资源
					$("#courseDiv").find(".file-img").each(function(index,element){
						$(this).click();
					});
					//关闭上传的本地文件
					$("#fileDiv").find(".file-img").each(function(index,element){
						$(this).click();
					});
   				});
   			}else{
   				dialog.alert("保存失败");
   			}
   	    }
   	 });
}

/**
 * 返回上一步
 */
function backLastPage(){
	window.history.go(-1);
}

</script>
</head>
<body>
	<div id="thematicAskDetailContent">
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">问题详情</span>
		</div>
		<!-- <h3>
			<a href="javascript:void(0)" onclick="backLastPage();">&lt;</a>&nbsp;&nbsp;&nbsp;问题详情
		</h3> -->
		<!-- 提问 -->
		<div id="askDiv">
			<div id="askTitle">提问</div>
			<div id="askContent">
				<h3 id="askContentH3"></h3>
				<br/> 
				<span id="askAboult"> 
					<em id="answerCount" class="thisEmStyle">4</em>个回答&nbsp;|&nbsp; 
					<!-- 分类：<em id="thematicAskType" class="thisEmStyle"></em>&nbsp;|&nbsp; --> 
					标签：<em id="thematicAskTab" class="thisEmStyle"></em>
				</span> 
				<br/>
				<div id="askDetailContent"></div>
				<br/>
				<div id="addPicsDiv"></div>
				<br/> 
				<span id="askerAndTime"> 
					<em id="askerName" class="thisEmStyle"></em>&nbsp; 
					<em id="askTime" class="thisEmStyle"></em>
				</span>
			</div>
		</div>
		<!-- 我来回答 -->
		<div id="myReplyDiv">
			<h3>我来回答</h3>
			<textarea id="replyContent"></textarea>
			<br/>
			<div id="suggestResourceDiv">
				推荐一些资源：<br/>
				<div style="width: 300px; height: 42px;">
					<div style="width: 140px; float: left;">
						<input type="button" class="btn" value="选择平台资源" onclick="selectCourse();"/>
					</div>
					<div style="width: 140px; float: left;">
						<div class="btns">
							<div id="picker">上传本地文件</div>
						</div>
					</div>
				</div>
				<br/>
				<div id="thelist" class="uploader-list"></div>
			</div>
			<div id="courseDiv" class="subClass" style="line-height: 10px;">
				<ul id="courseList">
				</ul>
			</div>
			<ul class="layer_notice" style="display: none; height: 150px; width: 350px">
				<li class="progress">
					<label>文件名称:</label>
					<label class="file-name"></label>
				</li>
				<li class="progress">
					<div id="jqMeter_div"></div>
				</li>
			</ul>
			<div id="fileDiv" class="left_up" style='padding-top: 10px; padding-left: 40px;'></div>
			<br/>
			<div id="submitDiv">
				<input type="button" id="submitButton" onclick="submitAnswer();" value="提交"/>
				<div id="hideNameDiv">
					<input type="checkbox" id="hideName" name="hideName"/>匿名回答
				</div>
			</div>
		</div>
		<!-- 满意答案 -->
		<div id="satisfactoryAnswerDiv">
			<h3><img src="<%=request.getContextPath() %>/images/cup.png"/> 满意答案</h3>
			<br/>
			<div id="answerDiv">
				<!-- <div id="answerContent">
					太阳的"长斑周期"长期的观察累积的数据让科学家意识到，太阳黑子的出现是有一定规律的。
				</div>
				<br/>
				<span id="answerTime">
					<em id="replyerName" class="thisEmStyle">黄蓓</em>&nbsp;
					<em id="replyTime" class="thisEmStyle">2014-11-04 09:05:33</em>
				</span> -->
			</div>
		</div>
		<!-- 其他回答 -->
		<div id="otherAnswerDiv">
			<h3>其他回答</h3>
			<br />
			<!-- 其他回答具体内容 -->
			<div id="othersDiv">
				<!-- <div class="otherAnswerContent">
					火山喷发将大量的氮气排放到空气中，与此同时也喷薄出硫磺、水蒸气和二氧化碳。当地壳被挤压，大量的不稳定物质释放到位于上方的石头中。这些
					物质包括氮元素，他们或者被压制到石头中，或者变成气体在火山喷发的时候释放出来。
				</div>
				<br/>
				<div class="otherAnswerTime">邓伟伟&nbsp;2014-11-04 08:54:02</div>
				<br/> -->
			</div>
		</div>
	</div>

	<div id="selectCourse" style="display: none;">
		<div class="course_tree treeDiv1">
			<div id="courseTree" class="ztree treeDiv2"></div>
		</div>
		<div class="search_3 fl" style="float: right; width: 700px; border: none">
			<p>
				课程名称： <input id="courseName" type="text">
			</p>
			<input type="button" value="查询" class="btn_cx" onclick="search()">
		</div>
		<div style="width: 720px; float: right;">
			<table id="courseTable"></table>
			<div id="paginator11-1" style="text-align: right; margin-right: 10px;"></div>
		</div>
	</div>
</body>
</html>