<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	  <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>新增资讯</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/jquery-validation/jquery.validate.js" type="text/javascript"></script>

<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<link rel="stylesheet" href="<%=basePath%>js/kindeditor-4.1.10/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath%>js/kindeditor-4.1.10/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=basePath%>js/kindeditor-4.1.10/kindeditor.js"></script>
<script charset="utf-8" src="<%=basePath%>js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=basePath%>js/kindeditor-4.1.10/lang/en.js"></script>
<script charset="utf-8" src="<%=basePath%>js/kindeditor-4.1.10/plugins/code/prettify.js"></script>

 <script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css" type="text/css">
<!-- 弹出框 -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css"/>
<%
	//此处做一个模拟登录
	// 设置Session
	HttpSession qqq = request.getSession();
String language= String.valueOf(RequestContextUtils.getLocale(request));

%>
<style type="text/css">

html, body{
	height: 100%;
}


</style>

<script type="text/javascript">
	
	var editor;
	KindEditor.ready(function(K){
		var language= '<%=language%>';
		var langType ="zh_CN";
		if(language=="en_US"){
			langType ="en";
		} 
		 
		editor = K.create("textarea[name=content]",{
			width: "80%",
			height: "430px",
			pasteType: 2,
			langType : langType,
			uploadJson : "<%=basePath%>file/uploadImg.action?path=CommonImage&DB=false",
			fileManagerJson : "<%=basePath%>file/filemanageJson.action",
			allowFileManager : false,
			filePostName:"uploadFile",
			items: [
			        'source', '|','undo', 'redo', '|', 'preview', 'template', 'code', 'cut', 'copy', 'paste',
			        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
			        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
			        'flash', 'media','insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
			        'anchor', 'link', 'unlink', '|', 'about','anchor'
				]
			
		});
		//prettyPrint();
	});	

</script>
</head>

<body style="overflow-x:hidden;">

<div id='content_i' class='content' >
	<div class='knowledge'>
		<!-- <h3>新增资讯</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="window.location='<%=request.getContextPath()%>/information/list.action'"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增资讯</span>
		</div>
	</div>
	<div class="lesson_add">
	<form action="" id="addForm">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>标题：</em>
            </div>
            <div class="add_fr">
            	<input type="text" style="width:270px;" id="infoName" name="infoName">
            </div>
        </div>
        <div class="add_gr" style='height:430px'>
        	<div class="add_fl">
            	<span>*</span>
                <em>内容：</em>
            </div>
            <div class="add_fr">
				<textarea rows="10" cols="110" name="content"></textarea>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>是否设为banner：</em>
            </div>
             <div class="add_fr">
            	是<input type="radio" name="isBanner" checked='checked'>            	否<input type="radio" name="isBanner">
            	
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
    	         <span>*</span>
                <em>banner图片：</em>
            </div>
            <div class="add_fr" style="margin-top: 7px;">
           		<div id="uploader" class="wu-example">
			    <!--用来存放文件信息-->
				    <div id="thelist" class="uploader-list"></div>
				    <div class="btns">
				        <div id="picker">选择图片</div>
				    </div>
				</div>
				<input type="hidden" id="bannerFilePath" name="bannerFilePath"/>
				<input type="hidden" id='bannerFileName' name='bannerFileName'>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">&nbsp
            </div>
            <div class="add_fr" style="margin-top: 7px;">
           		<div class="previewPic">
           			<img alt="" src="" name="bannerPath" id="bannerPath">
           		</div>
            </div>
        </div>
        <div class="button_cz">
        	<input type="button" id='ctlBtn' value="保存" >
            <input type="button" value="返回" 
				onclick="window.location='<%=request.getContextPath()%>/information/list.action'"
	            class="back">
        </div>
	</form>
    </div>
</div>	
<script  type='text/javascript'>
	function saveInformation(){
		
		var param = {};
		
		if($("input[name='isBanner']").eq(0).is(":checked")){
			param.isBanner=1;
		}else{
			param.isBanner=0;
		}
		param.infoName=$("input[name='infoName']").val();//knowledge_name
		param.infoContent=editor.html();
		param.bannerFilePath=$("#bannerFilePath").val();
		param.bannerFileName=$("#bannerFileName").val();
		$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
    		data:param,
    		url:"<%=request.getContextPath()%>/information/save.action",
    		success:function(data){
    			if("SUCCESS"==data){
    				dialog.alert({content:"保存成功",lock:true},function(){
	    				location = "<%=request.getContextPath()%>/information/list.action";
    				});
    			}else{
    				dialog.alert("保存失败");
    			}
    	    }
    	});
		
	}
	
	//上传图片
	$(function(){
	//	var iconfile;
		var uploader = WebUploader.create({
			auto: true,
		    // swf文件路径
		    swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

		    // 文件接收服务端。
		    server:'<%=request.getContextPath()%>/teacher/uploadImg.action',

		    // 选择文件的按钮。可选。
		    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
		    pick: {id:'#picker',
	    			multiple:false
	    		},

		    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		    resize: false,

		    // 只允许选择图片文件。
		    accept: {
		        title: 'Images',
		        extensions: 'gif,jpg,jpeg,bmp,png',
		        mimeTypes: 'image/*'
		    }
		});
		
		uploader.on( 'fileQueued', function( file ) {
			/* iconfile = file;
		    $("#picker").append( '<div id="' + file.id + '" class="teacher-item">' +
		        '<h4 class="info">' + file.name + '</h4>' +
		        '<a class="close" href="javascript:void" onclick="cancelFile(this,'+file.id+')">X</a>'+
		    '</div>' ); */
		 
		});
		
		//接受文件后，进行赋值操作
		uploader.on( 'uploadAccept', function( file ,ret ) {
			 $("#picker").find("span.error").remove();
			$("#bannerFilePath").val(ret._raw);
			$("#bannerFileName").val(file.file.name);
			
			//预览图片
			$("#bannerPath").attr("src",ret._raw).css({"width":"700px","height":"300px"});;
			$("#bannerPath").closest("div.add_gr").css("height","300px");
		});

		  uploader.on('error', function(type) {
		        if (type == 'Q_EXCEED_NUM_LIMIT') {
		            dialog.alert('上传文件个数超过限制');
		            return;
		        }
		        if (type == 'F_EXCEED_SIZE') {
		            dialog.alert('上传文件过大');
		            return;
		        }
		        if (type == 'Q_TYPE_DENIED') {
		            dialog.alert('上传文件类型不匹配');
		            return;
		        }
		    });
	 var  validate = $("#addForm").validate({ 
		   focusCleanup:true,      
	       rules: {  
	    	   infoName: {  
                required:true,  
                rangelength:[1,30],  
        		},
        	 content:{
        		 required:true,
   		   },
   			bannerFilePath:{
				   required:true,  
				   email:true 
			   }
	       },  
	       messages:{  
	    	   infoName: {  
                required:"请输入标题",  
                rangelength:"输入长度必须小于30个字符",
     	   },
     	  content:{
     		   required:"请输入内容",
     	   },
     	  bannerFilePath:{
                required:"请选择图片",  
			   },
			   
	       },  
	   })
	function validateForm() {  
	   //validate方法参数可选  
	   var checkForm =function(){
		   
		  if($.trim(editor.html())==""){
			  dialog.alert("请输入内容");
			  return false;
		  } 
		   
		   if($.trim($("#bannerFilePath").val())==""){
			   $("#picker").append('<label for="bannerFilePath" class="error" style="isplay: inline;position: absolute; width: 200px; top: 0px;left: 120px;height: 30px; line-height: 30px;">请选择图片</label>')  
			   return false; 
		   }
			 return  true;
	   }
	   
	  return validate.form()&&checkForm();  
	}  
	
   $("#ctlBtn").on( 'click', function() {
	   
	   if(validateForm()){
		   saveInformation();
	   }
          
   	 });
})
	
	</script>
</body>
</html>
