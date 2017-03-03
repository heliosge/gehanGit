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
	// 获取语言类型，主要为插件做国际化
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
var roleType = '${roleType}';//角色类型(用于学员页面保存后跳转控制)
//初始化参数
$(function(){
	
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
			
			if('${klJSON.isPublish}'!='2'){
				editor.readonly(false);
			}else{
				editor.readonly();
			}
		});	
		
		//初始化数据
		function initData(){
			
			$("#klTags").tags({
				valueString: "${klJSON.tags}",
	            inputCls: "noborder",
	            inputName: "tags" });
	
			if('${klJSON.shareStatus}'=='4'){
				$("[name='klShare']").eq(1).each(function(){
				    this.checked=true;
			    });
			}else{
				$("[name='klShare']").eq(0).each(function(){
				    this.checked=true;
			    });
			}
			
			$("select[name='isOpen']").val(${klJSON.isOpen});
			$("select[name='isDownload']").val(${klJSON.isDownload});
			$("select[name='isRecommend']").val(${klJSON.isRecommend});
			//chenrui s-
			if(${klJSON.status}==2 && roleType==2){//学员登入已审核通过的知识 不给修改是否开放
				$("#_select_open").attr("disabled","disabled");
				$("#_select_open").css("background","#D6D3D3");
			}
			//chenrui e-
		}
		initData();
		
		
		// 模型数据tree的set参数设置
		var treeSet = {
				data: {
					keep: {
						parent: true,
						leaf: true
					},
					simpleData: {
						enable: true,
						idKey: "categoryId",
						pIdKey: "parentId",
						rootPId: 0
					},
					key:{
						title: "categoryName",
						name:"categoryName"
					}
				}
			};
		$("body").append("<div id='tree' style='display:none'></div>");
		$.fn.zTree.init($("#tree"), treeSet,${categoryList});//初始化模型树
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var catePath =  getZTreePathName(treeObj.getNodesByParam("categoryId",$("input[name='categoryId']").val(), null)[0],"categoryName");
		$("input[name='categoryName']").val(catePath).attr("title",catePath);
})
	
//选择知识分类	
function selectCategory(){
	var categoryId = 0;
	//打开设置框
	 dialog({
		url: "<%=request.getContextPath()%>/knowledge/getKLCategoryTree.action?categoryId="+categoryId,
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
</script>
</head>

<body style="overflow-x:hidden;">

<div id='content_i' class="content" >
	<div class='knowledge'>
		<!-- <h3>修改知识</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">修改知识</span>
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
					<input type='text' class='text-p' name='knowledgeName' value="${klJSON.knowledgeName}"></input>
				</div></li>
			<li class='margin-left: -22px;'><div class='kl-label'>
					<span
						style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>知识内容:
				</div>
				<div class='kl-content'>
					<textarea rows="10" cols="110" name="content" >${klJSON.knowledgeText}</textarea>
				</div></li>
     	  			<h5>基本信息</h5>		
			<li><div class='kl-label'>
					<span
						style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>知识分类:
				</div>
				<div class='kl-content'> 
					<input type='text' class='text-p' name='categoryName' value="${klJSON.categoryName}" placeholder='选择分类' style='cursor:pointer'
						readonly="readonly" ></input>
						<input type="hidden" name='categoryId' value="${klJSON.categoryId}"/>
					<input type="button" class='category-search' value="选择" onclick='selectCategory()'>
					<div class="tit-error"></div>	
				</div></li>
		<c:if test="${klJSON.isPublish==1}">
			<li><div class='kl-label'>
					<span
						style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>是否开放:
				</div>
				<div class='kl-content'>
					<select id="_select_open" class='text-p' name='isOpen' value='${klJSON.isOpen}'>
						<option value='0'>否</option>	
						<option value='1'>是</option>	
					</select>
				</div></li>
			</c:if>
			<li><div class='kl-label'>
					<span
						style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>允许下载:
				</div>
				<div class='kl-content'>
					<select  class='text-p' name='isDownload' value='${klJSON.isDownload}'>
						<option value='0'>否</option>	
						<option value='1' selected>是</option>	
					</select>
				</div></li>
			
		 <c:if test="${roleType!=2}">
			<li><div class='kl-label'>
					<span
						style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>精彩推荐:
				</div>
				<div class='kl-content'>
					<select class='text-p' name='isRecommend' value='${klJSON.isRecommend}'>
						<option value='0'>否</option>	
						<option value='1' selected>是</option>	
					</select>
				</div></li>
				</c:if>
			<li><div class='kl-label'>
					<span
						style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>标签:
				</div>
				<div class='kl-content' style="height: 80px;">
				<div id="klTags" class="tags-box fn-clear" style="width: 70%;">
       			 </div>
					<!-- <input type='text' class='text-p' name='"klTags"' id="klTags"></input>
					<input type="hidden" class='text-p' name='tags' id="tags"></input> -->
					<h6 style='margin-left: 0px;padding-left: 0px;margin-top: 0px;margin-bottom: 31px;width: 100%;font-size: 12px;'>最多输入5个标签，多个标签之间请用空格或","隔开</h6>
				</div></li>
			<li><div class='kl-label'>知识描述:</div>
				<div class='kl-content'>
					<textarea  placeholder='2000个字符以内，文本输入' name='knowledgeDesc'  rows="5" cols="120" >${klJSON.knowledgeDesc}</textarea>
				</div></li>
			<!-- <li><div class='kl-label'>
					<span
						style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>是否共享:
				</div>
				<div class='kl-content'>
					<input type='hidden' name='isShare'></input><input type='radio'
						name='klShare' checked="checked"></input>共享 <input type='radio' style='margin-left: 60px;'
						name='klShare'></input>不共享
				</div></li> -->
			<li><div class=""
					style='margin-top: 20px; margin-left: 100px;'>
					<div class="controls">
						<input type="button" id="btnSubmit" class="btn" value="保存"> 
						<c:choose>
							<c:when test="${roleType==2}">
								<input type="button" onclick="window.location='<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeCenter.action'"
								class="btn btn-cancel" value="取消">
							</c:when>
							<c:otherwise>
							<input type="button" onclick="window.location='<%=request.getContextPath()%>/knowledge/list.action'"
								class="btn btn-cancel" value="取消">
							
							</c:otherwise>
						</c:choose>
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
	            maxlength:"输入长度必须小于等于 80 个字符",
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
		param.knowledgeId=${klJSON.knowledgeId}
		param.knowledgeName=$("input[name='knowledgeName']").val();//knowledge_name
		param.uploadType=$("input[name='uploadType']").val();
		param.categoryId=$("input[name='categoryId']").val();
		param.isOpen=$("select[name='isOpen']").val();
		param.isDownload=$("select[name='isDownload']").val();
		param.isRecommend=$("select[name='isRecommend']").val()||${klJSON.isRecommend};
		param.knowledgeDesc=$("textarea[name='knowledgeDesc']").val();
		param.tags=$("input[name='tags']").val();
		param.knowledgeText=$("textarea[name='content']").val();
		
		$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
/*    		  	contentType:"application/json; charset=utf-8",
 */    		data:param,
    		url:"<%=request.getContextPath()%>/knowledge/update.action",
    		success:function(data){
    			if("SUCCESS"==data){
    				dialog.alert("保存成功",function(){
    					if(roleType==2){
    						location = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeCenter.action";
    					}else{
		    				location = "<%=request.getContextPath()%>/knowledge/list.action";
    					}
    				});
    			}else{
    				dialog.alert("保存失败");
    			}
    			//search();
    	    }
    	});
	}
	</script>
</body>
</html>
