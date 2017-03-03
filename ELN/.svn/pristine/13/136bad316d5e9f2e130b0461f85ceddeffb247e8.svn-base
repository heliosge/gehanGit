<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建知识</title>
<style type="text/css">
.buttonClass{
	font-size:16px;
	font-family:微软雅黑;
	margin-right:5px;
	margin-top:2px;
	cursor: pointer;
	border-radius:2px;
	border:none;
	background-color:#d54b1f;
	padding-left: 10px;
	padding-right: 10px;
	padding-bottom:5px;
	padding-top:5px;
	color: #ffffff;
}
</style>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/includeKindeditor.jsp"></jsp:include>
<script type="text/javascript">
var contestId = ${contestId};
$(function(){
	initValidate();
});

var editor;
KindEditor.ready(function(K){
	<%-- var language= '<%=language%>';
	if(language=="en_US"){
		langType ="en";
	}  --%>
	var langType ="zh_CN";
	editor = K.create("textarea[name=content]",{
		width: "80%",
		height: "430px",
		pasteType: 2,
		langType : langType,
		uploadJson : "<%=request.getContextPath()%>/file/uploadImg.action?path=CommonImage&DB=false",
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
});
function selectPicBack(path){
	editor.insertHtml("<div align='center' style='margin:5px 0px 5px 0px;'><img src='"+path+"' data-ke-src='"+path+"' align='middle'/></div>");
}

//保存
function save(){
	$('#addForm').isValid(function(v) {
		var content = editor.html();
		if(!content || content == ""){
			$("#span_content").show();
			v=false;
		}else{
			$("#span_content").hide();
		}
		if(v){
			dialog.confirm("确认保存吗？", function(){
				$.ajax({
					type:"POST",
					async:true,  //默认true,异步
					data:{"contestId":contestId,"title":$.trim($("#title").val()),"content":editor.html()},
					url: "<%=request.getContextPath()%>/megagameManage/saveNews.action",
					success:function(data){
						if(data == "SUCCESS"){
							dialog.alert("操作成功！", function (){
								window.location = "<%=request.getContextPath()%>/megagameManage/toMegagameList.action";
							});
						}else{
							dialog.alert("操作失败");
						}
				    }
				});
			});
		}
	});
}
//发布
function publish(){
	var content = editor.html();
	if(!content || content == ""){
		$("#span_content").show();
		v=false;
	}else{
		$("#span_content").hide();
	}
	$('#addForm').isValid(function(v) {
		if(v){
			dialog.confirm("确认发布吗？", function(){
				$.ajax({
					type:"POST",
					async:true,  //默认true,异步
					data:{"contestId":contestId,"title":$.trim($("#title").val()),"content":editor.html()},
					url: "<%=request.getContextPath()%>/megagameManage/addAndPublishNews.action",
					success:function(data){
						if(data == "SUCCESS"){
							dialog.alert("操作成功！", function (){
								window.location = "<%=request.getContextPath()%>/megagameManage/toMegagameList.action";
							});
						}else{
							dialog.alert("操作失败");
						}
				    }
				});
			});
		}
	});
}

function checkData(){
	if($.trim($("#title").val()) == ''){
		dialog.alert("请输入标题");
		return false;
	}
	if(editor.html() == ''){
		dialog.alert("请输入内容");
		return false;
	}
	return true;
}

/*表单验证  */
function initValidate() {
	$('#addForm').validator({
		theme : 'yellow_right',
		rules : {
		},
		fields : {
			title : {
				rule : "required;length[1~30];",
				msg : {
					required : "标题不能为空",
					length:"标题不超过30字符"
				}
			}
		}
	});
}

/*返回  */
function goBack(){
	window.location = "<%=request.getContextPath()%>/megagameManage/toMegagameList.action";
}
</script>

</head>

<body>
<div id="content_i">
	<div class="know">
    	<!-- <h4> 资讯发布</h4> -->
    	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="goBack();"/> 
			<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">资讯发布</span>
		</div>
        <div class="cr_kn">
        <form id="addForm">
         	<div style="margin-top: 20px;">
         		<span style="color:#283577;text-decoration: underline;font-size: 20px;">[${contestName}]</span>
         		<span style="font-weight: bold;font-size: 14px">资讯发布</span>
         	</div>
        	<p><span>*</span>标题：<input type="text" id="title" name="title"/></p>
            <p><span>*</span>内容：
            	<span id="span_content" class="msg-box n-right" for="times" style="display: none;">
					<span class="msg-wrap n-error" role="alert">
						<span class="n-arrow"><b>◆</b><i>◆</i></span>
						<span class="n-icon"></span>
						<span class="n-msg">内容不能为空</span>
					</span>
				</span>
			</p>
            <div>
				<textarea name="content" id="content"></textarea>
			</div>
           <%--  <div class="cr_txt">
            	<img src="<%=request.getContextPath() %>/images/img/cr_txt.png" />
            </div> --%>
         </form>
        </div>
        <div>
        	<input type="button" class="buttonClass" value="保存" onclick="save();"/>
        	<input type="button" class="buttonClass" value="发布" onclick="publish();"/>
        	<input type="button" class="buttonClass" value="返回" onclick="goBack();"/>
        </div>
    </div>
</div>
</body>

</html>
