<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>知识库管理</title>
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
<!-- 上传插件 -->
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>

<!-- 标签使用插件 -->
<script src="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tags.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tagsinput.css" />


<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/knowledge.css"/>
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
			uploadJson : "<%=basePath%>file/uploadImg.action?path=knowledge%2Fcreate",
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
				],
			afterChange:function(){
				$("textarea[name=content]").next().hide();
			},
			afterBlur: function(){this.sync();}
			
		});
		//prettyPrint();
	});	


	//选择知识分类	
	function selectCategory(){
		var categoryId = 0;
		//打开设置框
		 dialog({
			url: "<%=request.getContextPath()%>/knowledge/getKLCategoryTree.action?categoryId="+categoryId+"&roleType=1",
			height: 450,
			width: 300,
			title:"分类列表",
			button: [ { value: '确定', 
						callback: function () { 
							
							var iframe = this.iframeNode.contentWindow;
							var id = iframe.categoryId.value;
							var name = $(iframe.categoryId).attr("showName");
							if(!id||id=="-1"){
								dialog.alert("请选择分类！");
								return false;
							}
							$("input[name='categoryId']").val(id);
							$("input[name='categoryName']").val(name).attr("title",name);
							
							//清除后面的手动错误
							$("input.category-search").next("div.tit-error").find("span.msg-box").remove();
						}
				 }, { value: '取消', callback: function ( ) {} } ] }).showModal();
	}
	//初始化参数
	$(function(){
		$("#klTags").tags({inputName: "tags" });
	})
	
</script>
</head>

<body style="overflow-x:hidden;">

<div id='content_i' class="content" >
	<div class='knowledge'>
		<!-- <h3>创建知识</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="window.location='<%=request.getContextPath()%>/knowledge/list.action'"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">创建知识</span>
		</div>
		<input type="hidden" value='2' name='uploadType'></input>
	</div>
	<div style='margin: 20px' class='basic'>
	<form action="" id="knowledgeForm">
		<ul class='kl-list'>
			<li class='margin-left: -22px;'><div class='kl-label'>
					<span
						style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>知识名称:
				</div>
				<div class='kl-content'>
					<input type='text' class='text-p' name='knowledgeName'></input>
				</div></li>
			<li class='margin-left: -22px;'><div class='kl-label'>
					<span
						style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>知识内容:
				</div>
				<div class='kl-content'>
					<textarea rows="10" cols="110" name="content"></textarea>
				</div></li>
     	  			<h5>基本信息</h5>		
			<li><div class='kl-label'>
					<span
						style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>知识分类:
				</div>
				<div class='kl-content'> 
					<input type='text' class='text-p' name='categoryName' placeholder='选择分类' style='cursor:pointer'
						readonly="readonly" ></input>
						<input type="hidden" name='categoryId' />
					<input type="button" class='category-search' value="选择" onclick='selectCategory()'>
					<div class="tit-error"></div>	
				</div></li>
			<li><div class='kl-label'>
					<span
						style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>是否开放:
				</div>
				<div class='kl-content'>
					<select class='text-p' name='isOpen'>
						<option value='0'>否</option>	
						<option value='1'>是</option>	
					</select>
				</div></li>
			<li><div class='kl-label'>
					<span
						style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>允许下载:
				</div>
				<div class='kl-content'>
					<select  class='text-p' name='isDownload' value='1'>
						<option value='0'>否</option>	
						<option value='1' selected>是</option>	
					</select>
				</div></li>
			<li><div class='kl-label'>
					<span
						style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>精彩推荐:
				</div>
				<div class='kl-content'>
					<select class='text-p' name='isRecommend' value='1'>
					<option value='0'>否</option>	
						<option value='1' selected>是</option>	
					</select>
				</div></li>
			<li><div class='kl-label'>
					<span
						style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>标签:
				</div>
				<div class='kl-content' style="height: 80px;">
					<div id="klTags" class="tags-box fn-clear" style="width: 70%;">
	       			 </div>
						<!-- <input type='text' class='text-p' name='"klTags"' id="klTags"></input>
						<input type="hidden" class='text-p' name='tags' id="tags"></input> -->
						<h6 style='margin-left: 0px;padding-left: 0px;margin-top: 0px;margin-bottom: 31px;width: 100%;font-size: 12px'>最多输入5个标签，多个标签之间请用空格或","隔开</h6>
				</div></li>
			<li><div class='kl-label'>知识描述:</div>
				<div class='kl-content'>
					<textarea  placeholder='2000个字符以内，文本输入' name='knowledgeDesc'  rows="5" cols="120" ></textarea>
				</div></li>
			<%-- <c:if test="${isPuLian=='false'}" >
				<li><div class='kl-label'>
						<span
							style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>是否共享:
					</div>
					<div class='kl-content'>
						<input type='hidden' name='isShare'></input><input type='radio'
							name='klShare' checked="checked"></input>共享 <input type='radio' style='margin-left: 60px;'
							name='klShare' ></input>不共享
					</div></li>
			</c:if> --%>
			<li><div class=""
					style='margin-top: 20px; margin-left: 100px;'>
					<div class="controls">
						<input type="button" id="btnSubmit" class="btn" value="保存"> 
						<input type="button" onclick="window.location='<%=request.getContextPath()%>/knowledge/list.action'"
							class="btn btn-cancel" value="取消">
					</div>
				</div></li>
			</ul>
		</form>
	</div>
</div>	
<script  type='text/javascript'>
	$("#btnSubmit").click(function(){
		
		if(validateForm()){
			saveKnowledge()
		}
	})
	

//检验检测
 jQuery.validator.addMethod("repeatName", function(value, element) {
	    return this.optional(element) ||checkUerName();
	});  
	//校验表单
	function validateForm() {  
		
	 var  validate = $("#knowledgeForm").validate({ 
		   focusCleanup:true,  
		   ignore: "", 
	       rules: {  
	    	   knowledgeName: {  
	            required:true,  
	            maxlength:80,  
	    		},
	    		categoryName:{  
	                required:true  
	        	},
	        	tags:{
	        		required:true  
	        	},
	        	knowledgeDesc: {
	        		maxlength: 2000
	        		},
	        	content:{
	        		required:true  
	        	}
	       },  
	       messages:{  
	    	   knowledgeName: {  
	            required:"知识名称必填",  
	            maxlength:"输入长度必须小于等于80个字符",
	 	  	 },
	 	  	categoryName:{  
	            	required:"请选择知识分类"  
	    		},
	        	tags:{
	        		required:"知识标签必填"  
	        	},
	        	knowledgeDesc:{
	        		maxlength: "知识描述最多2000个字符！"
        		},
	        	content:{
	        		required:"内容必须输入."  
	        	}
	       },  
	       errorPlacement: function(error, element) {  
				if($(element).closest("div.kl-content").find("div.tit-error").length>0){
					$(element).closest("div.kl-content").find("div.tit-error").html(error);
				}else if(element.attr("id")=="tags"){
					$("#klTags").after(error);
				}else{
					
					$(element).after(error);
				}
	    	 }
	   })
	  return validate.form();  
	} 

	//保存创建的知识
	function saveKnowledge(){
		
		var param = {};
		
		param.shareStatus=4;
		if($("input[name='klShare']").eq(0).is(":checked")){
			param.shareStatus=5;
		}
		param.knowledgeName=$("input[name='knowledgeName']").val();//knowledge_name
		param.uploadType=$("input[name='uploadType']").val();
		param.categoryId=$("input[name='categoryId']").val();
		param.isOpen=$("select[name='isOpen']").val();
		param.isDownload=$("select[name='isDownload']").val();
		param.isRecommend=$("select[name='isRecommend']").val();
		param.knowledgeDesc=$("textarea[name='knowledgeDesc']").val();
		param.tags=$("input[name='tags']").val();
		param.isPublish=1;
		param.knowledgeText=editor.html();
		param.roleType = 1;
		$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
    		data:param,
    		url:"<%=request.getContextPath()%>/knowledge/saveCreate.action",
    		success:function(data){
    			if("SUCCESS"==data){
    				dialog.alert("保存成功",function(){
	    				location = "<%=request.getContextPath()%>/knowledge/list.action";
    				});
    			}else{
    				dialog.alert("保存失败");
    			}
    	    }
    	});
	}
	</script>
</body>
</html>
