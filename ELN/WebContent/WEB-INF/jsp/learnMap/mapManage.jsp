<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>学习地图管理</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!-- validation -->
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/jquery-validation/jquery.validate.js" type="text/javascript"></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<style type="text/css">

.param_table td{
	padding: 2px;
}
.param_table .input_0{
	width: 200px;
}

.need_span{color:red;font-size:13px;}
.validation_span{color:red;font-size:13px;}
.oldPath.active{
background :#f3a842 none repeat scroll 0 0;
color: white;
}
.n-icon,.n-arrow{
	margin-left: 0px!important;
	margin-right: 0px!important;
}
.oldPath a {display:none;float:right;margin-top: 7px;}
.oldPath a img{heigth:15px;margin-right:4px}
.oldPath.active a{display:block}
</style>

<script type="text/javascript">


var pathList = ${pathList};
var tempStageList=[];
var tempStage={};

$(function(){

    renderPathList();
	
	//验证名称(中文，数字，字母)
	$.validator.addMethod(
	    "onlyName",
	    function (value, element){
	    	var withOutChinese = /^([\u4E00-\u9FA5]|\w)*[^_]$/;
	    	return this.optional(element) || withOutChinese.test(value); 
	    },
	    "请输入正确的名称"
    );
	//验证数字(0-100)
	$.validator.addMethod(
	    "number1",
	    function (value, element){
	    	var num = /^(0|[1-9]\d{0,1}|100)$/;
	    	return this.optional(element) || num.test(value); 
	    },
	    "请输入0-100的数字"
    );
	//验证数字(0-9)
	$.validator.addMethod(
	    "number2",
	    function (value, element){
	    	var num = /^[0-9]$/;
	    	return this.optional(element) || num.test(value); 
	    },
	    "请输入0-9的数字"
    );
	//验证数字(0-999)
	$.validator.addMethod(
	    "number3",
	    function (value, element){
	    	var num = /^(0|[1-9]\d{0,2})$/;
	    	return this.optional(element) || num.test(value); 
	    },
	    "请输入0-999的数字"
    );
	
	//开始验证     
	$('form').validate({
		debug:true,
	    /**//* 设置验证规则 */    
	    rules: {     
	    	description: {     
	            required:true,
	            maxlength:200
	           
	        },
	        period:{
	        	required:true,
	        	number3:true
	        },
	        obligatoryMinCredits: {     
	        	required:true,
	        	number3:true,
	        	remote:{  
	                 url:"<%=request.getContextPath()%>/map/getTotalCredits.action",  
	                 type:"post",  
	                 dataType:"text",  
	                 data:{   type:2,
		                	  postId:function(){
			                	  return $.trim($("#postId").val());}},  
	                 dataFilter: function(data, type) {  
	                 	if(data&&data!="FAIL"){
	                 		$("#obligatoryCredits").val(data);
	                 		var obligatoryMinCredits=parseInt($("#obligatoryMinCredits").val());
	                 		if(obligatoryMinCredits>data){
	                 			return false;
	                 		}
	                 		return true;
	                 	}else{
	                 		return false;
	                 	}
	                 }
	              }
	        },
	        electiveMinCredits:{
	        	required:true,
	        	number3:true,
	        	remote:{  
	                 url:"<%=request.getContextPath()%>/map/getTotalCredits.action",  
	                 type:"post",  
	                 dataType:"text",  
	                 data:{
	                	  type:1,
		                	  postId:function(){
			                	  return $.trim($("#postId").val());}},  
	                 dataFilter: function(data, type) {  
	                		if(data&&data!="FAIL"){
		                 		$("#electiveCredits").val(data);
		                 		var electiveMinCredits=parseInt($("#electiveMinCredits").val());
		                 		if(electiveMinCredits>data){
		                 			return false;
		                 		}
		                 		return true;
		                 	}else{
		                 		return false;
		                 	}
	                 }
	              }
	        },
	        examTitle:{
	        	required:true
	        },
	     
	        passScorePercent: {
	        	required:true,
	        	number1:true
	        },
	        duration:{
	        	required:true,
	        	number3:true
	        }
	    },      
	    /**//* 设置错误信息 */    
	    messages: {     
	    	description: {         
	        	required:"阶段描述不可为空",
	        	maxlength:"最多200个字符"
	        },
	        period:{
	        	required:"晋升周期不可为空",
	        	number3:"请输入0-999的整数"
	        },
	        obligatoryMinCredits:{
	        	
	        	remote:function(){
	        		return "必修分不能大于"+$("#obligatoryCredits").val();
	        	},
	        	required:"必修学分不可为空",
	        	number3:"请输入0-999的整数"
	        },
	        electiveMinCredits:{
	        	remote:function(){
	        		return "选修分不能大于"+$("#electiveCredits").val();
	        	},
	        	required:"选修学分不可为空",
	        	number3:"请输入0-999的整数"
	        	
	        },
	        examTitle: {         
	        	required:"试卷不可为空"
	        },
	        passScorePercent: {     
	        	required:"通过分数不可为空",
	            number1:"请输入0-100的整数"
	        },
	        duration:{
	        	required:"考试时长不可为空",
	            number3:"请输入0-999的整数"
	        }
	    },      
	    /**//* 设置验证触发事件 */
	    onsubmit:true,
	  
	    submitHandler: function (){
	    	//alert("sunmit");
	    	save();
	    }
	}); 
});
//限制字符显示长度
function limitLength(str, maxLength) {
	var _maxLength = maxLength;
	if (!_maxLength) {
		_maxLength = 10;
	}
	var result = str.length > _maxLength ? content = str.substring(0, _maxLength) + "…" : str;
	return result;
}
//返回
function goBack(){
	dialog.confirm('确认返回吗？', function(){ 
	    	//window.location.href = "<%=request.getContextPath()%>/learnPlan/toLearnPlanListPage.action";
	    
	});
}

//渲染页面
function renderPathList(){
	if(pathList==null||pathList.length==0){
		return;
	}
	$newPathLi = $("#newPathLi");
	$(".oldPath").remove();
	var addTittleStr="";
	for(var i = 0;i<pathList.length;i++){
		var htmlStr='<li class="oldPath" onclick="renderStageList('+(i+1)+')">'+limitLength(pathList[i].name,11)+'<span id="is_stop_'+(i+1)+'">'+(pathList[i].isDisabled?"[已停用]":"")+'</span>'+
		'<a  href="javascript:void(0)" onclick="doDeletePath('+(i+1)+')"><img src="<%=request.getContextPath()%>/images/img/icon_remove.png" class="img_3" /></a>'+
		'<a  href="javascript:void(0)" onclick="editPath('+(i+1)+')"><img src="<%=request.getContextPath()%>/images/img/icon_pencil.png" class="img_3" /></a>'+
		'</li>';
		 addTittleStr+=htmlStr;
	}
	
	$newPathLi.before(addTittleStr);
	renderStageList(pathList.length);
}
//渲染阶段，取得阶段数据
function renderStageList(i,flag){
	if(i==null){
		return;
	}
	var pathIndex =$("#pathIndex").val();
     if(pathIndex==i&&!flag){
    	 return;
     }
	$('.oldPath').removeClass('active');
	$("#is_stop_"+i).parent().addClass('active');
	$("#pathIndex").val(i)
	$("#pathId").val(pathList[i-1].id)
	$("#pathDispalyName").text(pathList[i-1].name);
	$("#pathDispalyDesc").text(pathList[i-1].desc);
	if(pathList[i-1].isDisabled){
		$("#unStopA").show();
		$("#stopA").hide();
	}else{
		$("#unStopA").hide();
		$("#stopA").show();
	}
	if(pathList[i-1].stageList!=null&&pathList[i-1].stageList.length>0){
		tempStageList=pathList[i-1].stageList;
		 _renderStageList(tempStageList);
	}else{
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{'pathId':pathList[i-1].id},
			dataType:"json",
			url:"<%=request.getContextPath()%>/map/getLearningMapByPath.action",
			success:function(data){
			  if(data&&data.stage){
				  pathList[i-1].stageList=data.stage; 
				  tempStageList=data.stage; 
				  _renderStageList(data.stage);
			  }
			 
		    }
		});
	}

}

//渲染页面
function _renderStageList(stageList){
	$(".tool_2").remove();
	$("#editForm").hide();
	$("#saveBtn").hide();
	if(stageList==null||stageList.length==0){
		if(tempStageList==null||tempStageList.length==0){
			return;
		}
		stageList=tempStageList;
	}else{
		tempStageList=stageList;
	}
	$newStageH = $("#newStageH");
	
	
	var addTittleStr="";
	for(var i = 0;i<stageList.length;i++){
		var htmlStr='<div  class="tool_2" id="stage_div_'+(i+1)+'">'+
        	'<span>'+stageList[i].postName+'</span>'+
        	'<a href="javascript:void(0)"  onclick="viewStage('+(i+1)+')" class="a_ss"><img src="<%=request.getContextPath()%>/images/img/ico_down.png"/></a>';
        	
        	if(i!=0){
        		htmlStr+='<a href="javascript:void(0)"  onclick="doUp('+(i+1)+')"><img src="<%=request.getContextPath()%>/images/img/ico_tool_1.png" class="img_1" /></a>';
        	}
    		if(i!=(stageList.length-1)){
    			htmlStr+='<a href="javascript:void(0)"  onclick="doDown('+(i+1)+')"><img src="<%=request.getContextPath()%>/images/img/ico_tool_1.png" class="img_2" /></a>';
    		}
        	
    		htmlStr+='<a href="javascript:void(0)" onclick="doDelete('+(i+1)+')"><img src="<%=request.getContextPath()%>/images/img/ico_tool_1.png" class="img_3" /></a>'+
            '<a href="javascript:void(0)" onclick="viewCourses('+stageList[i].postId+')" class="a_ck" ><img src="<%=request.getContextPath()%>/images/ico_search_1.png" /></a>'+
    	    '</div>';
		 addTittleStr+=htmlStr;
	}
	
	$newStageH.after(addTittleStr);
	//viewStage(stageList.length);
}

//停用 启用
function stopPath(){
	var param={};
	var pathIndex =$("#pathIndex").val();
	param.id=$("#pathId").val();
		param.isDisabled = 1;
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:param,
		dataType:"json",
		url:"<%=request.getContextPath()%>/map/editPath.action",
		success:function(data){
			if(data == "SUCCESS"){
				pathList[pathIndex-1].isDisabled=1;
				$("#is_stop_"+pathIndex).text("[已停用]");
					$("#unStopA").show();
					$("#stopA").hide();
				
			}else{
				dialog.alert('停用失败！');
			}
	    	
	    }
	});
}

function unStopPath(){
	var param={};
	var pathIndex =$("#pathIndex").val()
	param.id=$("#pathId").val()
	param.isDisabled = 0;
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:param,
		dataType:"json",
		url:"<%=request.getContextPath()%>/map/editPath.action",
		success:function(data){
			if(data== "SUCCESS"){
				pathList[pathIndex-1].isDisabled=0;
				$("#is_stop_"+pathIndex).text("");
					$("#unStopA").hide();
					$("#stopA").show();
			}else{
				dialog.alert('启用失败！');
			}
	    	
	    }
	});
}

//查看阶段
function viewStage(i){
	var oldIndex = $("#index").val();
	if(oldIndex==i){
		$("#editForm").toggle();
		$("#saveBtn").toggle();
		return;
	}
	tempStage = fillStage();
	var oldStage={};
	if(oldIndex&&oldIndex!=""){
		oldStage = tempStageList[oldIndex-1];
	}
	if(tempStage!=null&&oldIndex===0){
		dialog.confirm('是否放弃新建的内容？', function(){ 
			
				var $StageDiv = $("#stage_div_"+i);
				var $editForm= $("#editForm");
				$StageDiv.after($editForm);
				$("#stage_div_0").remove();
				$editForm.show();
				$("#saveBtn").show();
				renderStage(i);
		    
		});
	   
	}else{
		
		cleanForm();
	var $StageDiv = $("#stage_div_"+i);
	var $editForm= $("#editForm");
	$StageDiv.after($editForm);
	$editForm.show();
	$("#saveBtn").show();
	$("#stage_div_0").remove();
	renderStage(i);}
}


//填充阶段的数据
function renderStage(i){
	if(!i||!tempStageList||!tempStageList[i-1]){
		return;
	}
	tempStage=tempStageList[i-1];	
	_renderStage(tempStage,i);
	
}
function _renderStage(stage,i){
	if(!stage){
		return;
	}
	cleanForm();
	fillFrom(stage,i);
}

//将数据填充到表单中
function fillFrom(stage,i){
	$("#id").val(stage.id);
	$("#postId").val(stage.postId);
	$("#index").val(i);
	$("#passScorePercent").val(stage.passScorePercent);
	$("#description").val(stage.description);
	$("#examTitle").val(stage.examTitle);
	$("#examId").val(stage.examId);
	$("#paperId").val(stage.paperId);
	$("#duration").val(stage.duration);
	$("#electiveMinCredits").val(stage.electiveMinCredits);
	$("#obligatoryMinCredits").val(stage.obligatoryMinCredits);
	$("#period").val(stage.period);
	$("#postName").val(stage.postName);
}

//清除form数据
function cleanForm(){
	$("#id").val("");
	$("#index").val("");
	$("#postId").val("");
	$("#passScorePercent").val("");
	$("#description").val("");
	$("#examTitle").val("");
	$("#examId").val("");
	$("#paperId").val("");
	$("#duration").val("");
	$("#electiveMinCredits").val("");
	$("#obligatoryMinCredits").val("");
	$("#electiveCredits").val("");
	$("#obligatoryCredits").val("");
	$("#period").val("");
	$("#postName").val("");
	tempStage={};
	$('form').validate().settings.messages["electiveMinCredits"].remote=function(){
    		return "必修分不能大于"+$("#electiveCredits").val();
    	}
	$('form').validate().settings.messages["obligatoryMinCredits"].remote=
		function(){
		return "选修分不能大于"+$("#obligatoryCredits").val();
	  }
	$('form').validate().resetForm();
}
//填充stage
function fillStage(){
	var stage={};
	stage.id=$("#id").val();
	stage.passScorePercent=$("#passScorePercent").val();
	stage.description=$("#description").val();
	stage.examTitle =$("#examTitle").val();
	stage.examId=$("#examId").val();
	stage.paperId=$("#paperId").val();
	stage.duration=$("#duration").val();
	stage.electiveMinCredits=$("#electiveMinCredits").val();
	stage.obligatoryMinCredits=$("#obligatoryMinCredits").val();
	stage.period=$("#period").val();
	stage.pathId=$("#pathId").val();
	stage.postId=$("#postId").val();
	stage.postName=$("#postName").val();
	return stage;
}
var artDialog;
//创建路径（弹出）
function addPath(){
	artDialog = dialog({
        title: "新增晋升路径" ,
        url:"<%=request.getContextPath()%>/map/pathEdit.action",
        lock:true,
        height: 500,
		width: 750
	}).showModal();

}

//修改路径（弹出）
function editPath(){
	artDialog = dialog({
        title: "修改晋升路径" ,
        url:"<%=request.getContextPath()%>/map/pathEdit.action?id="+ $("#pathId").val(),
        lock:true,
        height: 300,
		width: 600
	}).showModal();

}

//删除阶段
function doDeletePath(i){
	
	if(i==0){
	   return;	
	}
	var id = pathList[i-1].id;
	_doDeletePath(id,i);

}
function _doDeletePath(id,i){
	if(!id){
		return;
	}
	dialog.confirm('确认删除吗？', function(){ 
		
	
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{'id':id},
		dataType:"json",
		url:"<%=request.getContextPath()%>/map/deletePath.action",
		success:function(data){
			if(data == "SUCCESS"){
				pathList.splice(i-1,1);
				$("#is_stop_"+i).parent().remove();
			
				renderPathList();
				dialog.alert('删除成功！');
				
			}else{
				dialog.alert('删除失败！');
			}
	    	
	    }
	});
	})
}
//保存路径信息
function savePath(path){
	if(!path||!path.id){
		return;
	}
	
	if($("#pathId").val()==path.id){
		var i =$("#pathIndex").val();
		 pathList[i-1]=path; 
		 renderPathList();
	}else
	{
		pathList.push(path);
		renderPathList();
	}
	
}

//查看阶段包含课程
function viewCourses(id){
	artDialog = dialog({
        title: "查看包含课程" ,
        url:"<%=request.getContextPath()%>/map/listPostCourse.action?postId="+id ,
        lock:true,
        height: 500,
		width: 750
	}).showModal();
	
}

//选择添加岗位阶段（弹出）
function selectPost(){
 
	if(!$("#pathId").val()){
		dialog.alert("请先选择路径");
		return;
	}
	artDialog = dialog({
        title: "添加岗位阶段" ,
        url:"<%=request.getContextPath()%>/map/listPost.action" ,
        lock:true,
        height: 600,
		width: 1070
	}).showModal();

	
}
//增加阶段
function addStage(id,name){
	var oldIndex = $("#index").val();
	tempStage = fillStage();
	var oldStage ={};
	if(oldIndex){
		oldStage= tempStageList[oldIndex-1];
	}
	
	/*  判断是否更改，假如更改弹出确定
	if(oldStage!=tempStage){
		dialog.confirm('是否放弃现在编辑的内容？', function(){ 
			
				var $StageDiv = $("#stage_div_0");
				if($StageDiv.length==0){
					newStageDiv();
					$StageDiv = $("#stage_div_0");
				}else{
					$StageDiv.show();
				}
				var $editForm= $("#editForm");
				$StageDiv.after($editForm);
				courseListReload(newStage.courseList);
				cleanForm();
				$("#index").val(0);
		    
		});
	}else{ 
		var $StageDiv = $("#stage_div_0");
		if($StageDiv.length==0){
			newStageDiv();
			$StageDiv = $("#stage_div_0");
		}else{
			$StageDiv.show();
		}
		var $editForm= $("#editForm");
		$StageDiv.after($editForm);
		courseListReload(newStage.courseList);
		cleanForm();
		$("#index").val(0);
	}*/
	addStageDiv(name);
	var $StageDiv = $("#stage_div_0");
	var $editForm= $("#editForm");
	$editForm.show();
	$("#saveBtn").show();
	$StageDiv.after($editForm);
	cleanForm();
	$("#index").val(0);
	$("#postId").val(id);
	$("#postName").val(name);
}

function addStageDiv(name){
	$("#stage_div_0").remove();
	var i=tempStageList?tempStageList.length:0;
	$newStageH = $("#newStageH");
	var htmlStr='<div  class="tool_2" id="stage_div_0">'+
	'<span>'+name+'</span>'+
    '</div>';
	$newStageH.after(htmlStr);
	
}

//删除阶段
function doDelete(i){
	
	if(i==0){
	   return;	
	}
	var id = tempStageList[i-1].id;
	_doDelete(id,i);

}
function _doDelete(id,i){
	if(!id){
		return;
	}
	dialog.confirm('确认删除吗？', function(){ 
		
	
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{'id':id},
		dataType:"json",
		url:"<%=request.getContextPath()%>/map/deletePathStage.action",
		success:function(data){
			if(data == "SUCCESS"){
				$("#stage_div_"+i).remove();
				tempStageList.splice(i-1,1);
				var pathIndex= $("#pathIndex").val();
				pathList[pathIndex-1].stageList = tempStageList;
				renderStageList(pathIndex);
				dialog.alert('删除成功！');
				
			}else{
				dialog.alert('删除失败！');
			}
	    	
	    }
	});
	})
}
//上移
function doUp(i){
	if(i==0||i==1){
       return;	
		 }
		var id = tempStageList[i-1].id;
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{'id':id},
			dataType:"json",
			url:"<%=request.getContextPath()%>/map/upPathStage.action",
			success:function(data){
				if(data == "SUCCESS"){
					var stage=tempStageList[i-1];
					var upStage=tempStageList[i-2];
					tempStageList.splice(i-1,1,upStage);
					tempStageList.splice(i-2,1,stage);
					var pathIndex= $("#pathIndex").val();
					pathList[pathIndex-1].stageList = tempStageList;
					renderStageList(pathIndex);
				}else{
					dialog.alert('上移失败！');
				}
		    	
		    }
		});
}

//下移
function doDown(i){
	
	if(i==0||i==tempStageList.length){
       return;	
		 }
		var id = tempStageList[i-1].id;
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{'id':id},
			dataType:"json",
			url:"<%=request.getContextPath()%>/map/downPathStage.action",
			success:function(data){
				if(data == "SUCCESS"){
					var stage=tempStageList[i-1];
					var downStage=tempStageList[i];
					tempStageList.splice(i-1,1,downStage);
					tempStageList.splice(i,1,stage);
					var pathIndex= $("#pathIndex").val();
					pathList[pathIndex-1].stageList = tempStageList;
					renderStageList(pathIndex);
				}else{
					dialog.alert('下移失败！');
				}
		    	
		    }
		});
}


function beforeSave(){
	$("form").submit();
}

//保存
function save(){

	tempStage=fillStage();
	var urlStr = "<%=request.getContextPath()%>/map/addPathStage.action";
	
	if(tempStage&&tempStage.id){
		//修改
		urlStr = "<%=request.getContextPath()%>/map/updatePathStage.action";
		
	}
	dialog.confirm('确认保存吗？', function(){ 
		
			$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:JSON.stringify(tempStage),
	    		contentType:"application/json;charset=utf-8",
	    		dataType:"json",
	    		url:urlStr,
	    		success:function(data){
	    			if(data&&data.result&&data.result == "SUCCESS"){
	    				if(!tempStage.id&&!data.id){
	    					dialog.alert('保存失败！');
	    					return;
	    				}
	    				if(data.id){
	    					tempStage.id=data.id;
	    				}
	    				
	    				var stageIndex = $("#index").val();
	    				if(stageIndex==0){
	    					tempStageList.push(tempStage);
	    				}else{
	    					tempStageList[stageIndex-1]=tempStage;
	    				}
	    		
	    				var pathIndex= $("#pathIndex").val();
						pathList[pathIndex-1].stageList = tempStageList;
	    				cleanForm();
	    				console.log(pathList[pathIndex-1].stageList);
	    				renderStageList(pathIndex,true);
	    				dialog.alert('保存成功！');
	    				
	    			}else if(data&&data.result&&date.result=="FAIL"&&date.message){
	    				dialog.alert(date.message);
	    			}else if(data=="SUCCESS"){
	    				dialog.alert('保存成功！');
	    			}
	    			else{
	    				dialog.alert('保存失败！');
	    			}
			    	
	    	    }
	    	});
	    
	});
}

//查看学员
function viewStudentProcess(){
	var pathId = $("#pathId").val();
	if(!pathId){
		dialog.alert("请先选择路径")
		return;
	}
	var pathIndex =$("#pathIndex").val()
	if(!pathList[pathIndex-1]||!pathList[pathIndex-1].stageList||pathList[pathIndex-1].stageList.length==0){
		dialog.alert("路径下没有阶段")
		return;
	}
	//window.open("<%=request.getContextPath()%>/map/toPathProcess.action?pathId="+pathId);
	window.location.href = "<%=request.getContextPath()%>/map/toPathProcess.action?pathId="+pathId;
}

//查看失败学员
function viewFailStudentProcess(){
	var pathId = $("#pathId").val();
	if(!pathId){
		dialog.alert("请先选择路径")
		return;
	}
	var pathIndex =$("#pathIndex").val()
	if(!pathList[pathIndex-1]||!pathList[pathIndex-1].stageList||pathList[pathIndex-1].stageList.length==0){
		dialog.alert("路径下没有阶段")
		return;
	}
	//window.open("<%=request.getContextPath()%>/map/toPathFailProcess.action?pathId="+pathId);
	window.location.href = "<%=request.getContextPath()%>/map/toPathFailProcess.action?pathId="+pathId;
}


//弹出选择试卷
function initTree1(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			callback: {
				onClick: zTreeOnClick1
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/exam/paperCategory/list.action",
		success:function(data){
			$.fn.zTree.init($("#treePage1"), setting, data);
		}
	});
}
function zTreeOnClick1(event, treeId, treeNode){
	var id = treeNode.id; //分类id
	$('#paperTable').mmGrid().load({"categoryId":id});
}
function initGrid1() {
	// 封装参数
	var param = new Object();
	$('#paperTable').mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : "<%=request.getContextPath()%>/megagameManage/getPaperByCategory.action",
						width : 'auto',
						height : 'auto',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : true,
						multiSelect : false,
						indexCol : true, // 索引列
						params : param,
						cols : [ {
							title : 'id',
							name : 'id',
							hidden : true
						}, {
							title : '试卷名称',
							name : 'title',
							align : 'center'
						}, {
							title : '试卷库',
							name : 'categoryName',
							align : 'center'
						}, {
							title : '题型分布',
							name : 'count1',
							align : 'center',
							renderer : function(val, item, rowIndex) {
								var str = "";
								str = "主观"+item.count1+"单选"+item.count2+"多选"+item.count3+"判断"+item.count4+
								"填空"+item.count5+"组合"+item.count6+"多媒体"+item.count7;
								return str;
							}
						}, {
							title : '试卷总分',
							name : 'totalScore',
							align : 'center'
						}],
						plugins : [ $('#paginatorPaging1').mmPaginator({
							totalCountName : 'total', // 对应MMGridPageVoBean
							// count
							page : 1, // 初始页
							pageParamName : 'page', // 当前页数
							limitParamName : 'pageSize', // 每页数量
							limitList : [ 10, 20, 40, 50 ]
						}) ]
					});
}

/**
 * 选择试卷
 */
function selectPaper(){
	var selectPaper = $("#selectPaper");
	initTree1();
	initGrid1();
	$('#paperTable').mmGrid().load();
	dialog(
			{
				title : "选择试卷",
				width : 980,
				height : 300,
				content :selectPaper,
				okValue : '确定',
				fixed:true,
				ok : function() {
					var items = $('#paperTable').mmGrid().selectedRows();
					var len = items.length;
					if(len==1){
							var pIds ="";
							var pNames ="";
							var pId = items[0].id;
							var paperName = items[0].title;
							pIds +=pId;
							pNames +=paperName;
							$("#examTitle").val(pNames);
							$("#paperId").val(pId);
					}else{
						dialog.alert("请选择一条数据");
						return false;
					}
				},
				cancelValue : '取消',
				cancel : function() {
				}
			}).showModal();
}
/*搜索试卷  */
function doPaperSearch(){
	var paperName = $("#paperName").val();
	var params = new Object;
	params.paperName = paperName;
	$('#paperTable').mmGrid().load(params);
}


</script>
</head>
<body style="">
<div class="content">
	<!-- <h3>学习地图</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">学习地图</span>
	</div>
    <div class="left_map" style="height: auto;">
    	<ul style="height: auto;">
        	<li class="li_active1">地图路径</li>
            <li id="newPathLi" class="li_active2" onclick="addPath()">新增地图路径</li>
        </ul>
    </div>
    <div class="right_map">
    	<div class="map_top" style="height: auto;">
        	<h4 id="pathDetial">
            <span id="pathDispalyName" onclick="editPath()"></span>
            <a href="javascript:void(0)" onclick="viewFailStudentProcess()" style="margin-left: 5px;"  class="top_2">晋升失败学员</a>
            <a href="javascript:void(0)" onclick="viewStudentProcess()" style="margin-left: 5px;" class="top_2">查看学员</a>
            <a id="stopA" href="javascript:void(0)" onclick="stopPath()" class="top_1" style="display:none" >停用</a>
            <a id="unStopA" href="javascript:void(0)" onclick="unStopPath()" class="top_2" style="display:none">启用</a>
            
             <input type="hidden" id="pathId" />
             <input type="hidden" id="pathIndex"/>
        
            </h4>
            <div style="width:786px"><h5 id="pathDispalyDesc" style="padding-left: 7px;word-break: break-all;"></h5></div>
        </div>
        <h5 id="newStageH" onclick="selectPost()" class="cs_gw">选择岗位</h5>
         
        <div id="editForm" class="lesson_add_2" style="width:796px; margin-top:-1px; margin-bottom:20px;display:none;">
         <form >
            <div class="add_gr" style="height:100px;">
            <input type="hidden" id="id" />
             <input type="hidden" id="index"/>
             <input type="hidden" id="postId"/>
             <input type="hidden" id="postName" />
                <div class="add_fl">
                    <span>*</span>
                    <em>阶段描述：</em>
                </div>
                <div class="add_fr">
                    <textarea id="description" name="description" style="height:88px; width:394px;"></textarea>
                <span class="validation_span"></span>
                </div>
            </div>
            <div class="add_gr">
                <div class="add_fl">
                    <span>*</span>
                    <em>晋升周期：</em>
                </div>
                <div class="add_fr">
                    <input type="text" id="period" name="period" style="width:135px;"/>
                    <span class="validation_span"></span>
                </div>
            </div>
            <div class="add_gr">
                <div class="add_fl">
                    <span>*</span>
                    <em>学分（必修）≥：</em>
                </div>
                <div class="add_fr">
                    <input type="hidden" id ="obligatoryCredits" value=""/>
                    <input type="text" id="obligatoryMinCredits" name="obligatoryMinCredits" style="width:135px;"/>
               <span class="validation_span"></span>
                </div>
            </div>
            <div class="add_gr">
                <div class="add_fl">
                    <span>*</span>
                    <em>学分（选修）≥：</em>
                </div>
                <div class="add_fr">
                       <input type="hidden" id ="electiveCredits" value=""/>
                    <input type="text" id="electiveMinCredits" name="electiveMinCredits" style="width:135px;"/>
                <span class="validation_span"></span>
                </div>
            </div>
            <div class="add_gr">
                <div class="add_fl">
                    <span>*</span>
                    <em>试卷：</em>
                </div>
                <div class="add_fr">
                    <input type="text" id="examTitle" name="examTitle"style="width:135px;" disabled="disabled"/>
                 
                    <input type="button" name="popPaper"  onclick="selectPaper()"  value="选择试卷" class="xz"/>
                     <input type="hidden" id="examId" name="examId"  />
                    <input type="hidden" id="paperId" name="paperId"  />
                    <span class="validation_span"></span>
                </div>
            </div>
            
            <div class="add_gr">
                <div class="add_fl">
                    <span>*</span>
                    <em>通过分数：</em>
                </div>
                <div class="add_fr">
                	<span>试卷总分的
                    <input type="text" id="passScorePercent" name="passScorePercent" style="width:30px;"/>%
                    <span class="validation_span"></span>
                    </span>
                </div>
            </div>
             <div class="add_gr">
                <div class="add_fl">
                    <span>*</span>
                    <em>考试时长：</em>
                </div>
                <div class="add_fr">
                    <input type="text" id="duration" name="duration" style="width:135px;" />
                    <span class="validation_span"></span>
                   
                </div>
            </div>
              </form>
              <div id="saveBtn" class="button_gr" style="display:none;padding-top: 10px;">
         	<input type="button" onclick="beforeSave()" style="float:right;margin-right: 100px;" value="保存" />
              </div>
         </div>
       
         
    </div>
   	<!-- dialog1 选择试卷 start -->
	<div id="selectPaper" style="display: none;">
		<div class="course_tree" style="float: left;overflow:auto;">
        	<ul id="treePage1" class="ztree" style=""></ul>
   		</div>
   		<div class="search_3 fl" style="float: right;width: 700px;border: none">
	        	<p>	
	               	试卷名称： <input id="paperName" type="text">
	        	</p>
	            <input type="button" value="查询" class="btn_cx" onclick="doPaperSearch()">
        </div>
   		<div style="width: 720px;float: right;">
   			
   			<table id="paperTable"></table>
			<div id="paginatorPaging1" style="text-align: right;margin-right: 10px;"></div>
   		</div>
	</div>
    <!--选择试卷  end -->    
</div>

</body>
</html>
