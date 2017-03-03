<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>试题导入</title>
<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>

<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>

<!-- webuploader -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<c:url value="/js/layer/layer.js"/>"></script>

<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>

<script type="text/javascript">
//试题库
//var zTree = null;


$(function(){
	var layerIndex;
    if(!WebUploader.Uploader.support()){
        alert('您的浏览器不支持上传插件！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
        throw new Error( 'WebUploader does not support the browser you are using.' );
    }
    
    var pagePicUploader = WebUploader.create({
		fileVal: "quesfile",
		//自定义参数
		formData: {},
		// 选完文件后，是否自动上传。
	    auto: false,
	    // swf文件路径
	    swf: '<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',
	    // 文件接收服务端。
	    server: "<%=request.getContextPath()%>/questionnaire/uploadFile.action",
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: {
	    	id: '#pagePicPicker',
	    	multiple: false
	    },
	    // 压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: true
	    //单个文件上传大小限制  单位B
	    //fileSingleSizeLimit:5000,
	});
	
	//上传前验证
	pagePicUploader.on('error', function(type){
		//alert(type);
		/* if(type == "Q_EXCEED_NUM_LIMIT"){
			//$.ligerDialog.warn('只允许上传一个zip包');
		}else if(type == "Q_TYPE_DENIED"){
			$.ligerDialog.warn("只允许上传<span style='color:red;' >gif,jpg,jpeg,bmp,png</span>文件");
		}else if(type == "F_EXCEED_SIZE"){
			//$.ligerDialog.warn("文件最大<span style='color:red;' >10MB</span>");
		} */
	});
	
	//当文件被加入队列之前触发，此事件的handler返回值为false，则此文件不会被添加进入队列。
	pagePicUploader.on('beforeFileQueued', function(file) {
		//重置
		pagePicUploader.reset();
	});
	
	// 当有文件被添加进队列的时候 监听
	pagePicUploader.on('fileQueued', function(file) {
		//alert(file);
		var weiZhi = file.name.lastIndexOf(".");
		if(weiZhi != -1){
			var nameStff = file.name.substring(weiZhi+1);
			
			var paperOrganizingMode = $("#questionForm input[name='organizingMode']:checked").val();
			
			if(paperOrganizingMode == 1 && (nameStff == "doc" )){
				$("#fileName").text(file.name);
			}else if(paperOrganizingMode == 2 && (nameStff == "xls" || nameStff == "xlsx")){
				$("#fileName").text(file.name);
			}else{
				//alert("上传的文件类型不对");	
				dialog.alert("上传的文件类型不对！");
				pagePicUploader.reset();
			}
		}
	});
	
	// 上传出错
	pagePicUploader.on('uploadError', function( file ) {
		//$.ligerDialog.error('上传失败');
		layer.close(layerIndex);
		dialog.alert("上传失败");
	});
	
	var waitingDialog = dialog({});
	
	// 某个文件开始上传前触发，一个文件只会触发一次
	pagePicUploader.on('uploadStart', function( file ) {
		//$.ligerDialog.waitting('正在上传,请稍候...'); 
		
		waitingDialog.showModal();
	});
	
	// 不管成功或者失败，文件上传完成时触发
	pagePicUploader.on('uploadComplete', function( file ) {
		//$.ligerDialog.closeWaitting();
		
		waitingDialog.close();
	});
	
	// 当文件上传成功时触发
	pagePicUploader.on('uploadSuccess', function(file, response) {
		
		layer.close(layerIndex);
		
		$.ligerDialog.hide();
		//alert(JSON.stringify(response));
		var backData = response;
		if(typeof(response) === "string"){
			backData = jQuery.parseJSON(response);
		};
		//alert(JSON.stringify(response));
		
		if(backData.status == 0 && !backData.errorFilePath){
			
			dialog.alert("上传成功");
			
			//导入试题
        	parent.showQuestion(backData.listData);
        	//统计 题目 信息
        	//parent.showQuestionInfo();
        	parent.layer.close(parent.loadQuestionDialog);
        	
		}else{
			if(backData.errorFilePath){
				//dialog.alert(backData.error);
				$("#path").val(backData.errorFilePath);
				dialog.alert("部分试题导入失败,<a href='javascript:;' onclick=\"downloadErrorFile()\">下载错误信息文件</a>");

				if(backData.listData&&backData.listData.length>0){
					//导入试题
		        	parent.showQuestion(backData.listData);
		        	//统计 题目 信息
		        	//parent.showQuestionInfo();

				}
				
			}else{
			    if(backData.status==4){
					dialog.alert("文件类型错误");
				}else if(backData.status==5){
					dialog.alert("模板不正确");
				}else
				dialog.alert("上传失败");
			}
			
			$("#fileName").text("");
		}
		
		//$.ligerDialog.success('上传成功！', '提示');
	});
	
	//开始上传
	$("#loadQuestion_ok").click(function(){
		//alert($("#categoryId").val());
		$('#addForm').isValid(function(v) {
			
			if($("#fileName").text() == ""){
				$("#alert_file").show();
				return false;
			}else{
				$("#alert_file").hide();
			}
			
			if(v){
				layerIndex = layer.msg('试题解析中…', {icon:16, time:0});
				var aaa = $("#questionForm input[name='organizingMode']:checked").val();
				//var bbb = $("#questionForm #categoryId").val();
				//设置参数
				pagePicUploader.option('formData', {"filetype":aaa});
				
				pagePicUploader.upload();
			}
		});
	});
	
	//关闭本弹出框
	$("#loadQuestion_close").click(function(){
		parent.layer.close(parent.loadQuestionDialog);
	});
});


//切换文件类型
/* function paperOrganizingModeChange(){
	var paperOrganizingMode = $("#questionForm input[name='organizingMode']:checked").val();
	if(paperOrganizingMode == 1){
		//word
		$("#questionForm .word").show();
		$("#questionForm .word").html($("#hide_param").html());
	}else if(paperOrganizingMode == 2){
		//excel
		$("#questionForm .word").hide();
		$("#questionForm .word").html("");
	}
	
} */

function downloadErrorFile(){
	$("#form").submit();
}

</script>
<style type="text/css">


.groupSubQuestion {
  float: left;
  border: 5px solid #ccc;
  width: 950px;
}
.groupSubQuestionControl {
    float: left;
    margin-top: 10px;
}
.need_span{
	display: none;
	color: red;
}
.button_cz{
	float: none;
	
}
.question_png{
	
}

.question_png img{
	width: 20px;
	cursor: pointer;
	margin: 0px 4px;
	vertical-align: -5px;
}
#question_info_div_2{

}
#question_info_div_2 table{
	border-left: 1px solid #CDCDCD;
	border-top: 1px solid #CDCDCD;
	margin-top: 10px;
}
#question_info_div_2 table td{
	border-right: 1px solid #CDCDCD;
	border-bottom: 1px solid #CDCDCD;
	padding: 2px 2px;
	line-height: 30px;
}
#question_info_div_2 table td input{
	height: 30px;
}

.lesson_add .add_gr .add_fl{
	width: 110px;
	line-height: 50px;
}
.lesson_add .add_gr{
	height: auto;
	line-height: 50px;
}
.n-arrow{display: none;}
</style>
</head>
<body>
<div class="content" style="margin-top:20px;padding-bottom:10px;width:100%;">
    <form id="form" action="<%=request.getContextPath()%>/exam/question/downloadErrorFile.action" method="post">
    	<input type="hidden" value="" id ="path" name="path"/>
    </form>
    <div class="lesson_add">
    	<form id="addForm" >
	    <div id="questionForm" >
	        <div id="basicInfoZone">
	        	<div class="add_gr ">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>文件类型：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="radio" id="organizingMode1" name="organizingMode" value="1" onclick="" checked="checked" />
		                <span>Word</span>
		                <input type="radio" id="organizingMode2" name="organizingMode" value="2" onclick="" />
		                <span>Excel</span>
		            </div>
		        </div>
				
				<div class="add_gr">
	                <div class="add_fl">
	                    <span>*</span>
	                    <em>上传文件：</em>
	                </div>
	                <div class="add_fr">
	                	<table>
	                		<tr valign="middle">
	                			<td>
	                				<span id="fileName" style="line-height:50px;" ></span>
	                			</td>
	                			<td>
	                				<span id="pagePicPicker" style="line-height:20px;">选择文件</span>
	                			</td>
	                			<td>
	                				<span id="alert_file" class="msg-box n-right" style="margin-left:0px;margin-top:-10px;display:none;">
										<span class="msg-wrap n-error" role="alert">
											<span class="n-arrow"><b>◆</b><i>◆</i></span>
											<span class="n-icon"></span>
											<span class="n-msg">上传文件不能为空</span>
										</span>
									</span>
	                			</td>
	                		</tr>
	                	</table>
	                </div>
	            </div>
	            
	            <div class="add_gr">
	                <div class="add_fl">

	                </div>
	               	 <div align="" style="margin:20px 0px 0px 140px;">
			        	<input id="loadQuestion_ok" type="button" value="确认" class="buttonClass" style="background-color: #d60500" />
			        	<input id="loadQuestion_close" type="button" value="取消" class="buttonClass" style="background-color: #0085fe" />
			        </div>
	            </div>
				
	        </div>
        
	    </div>
	    </form>
    </div>

</div>
</body>
</html>
