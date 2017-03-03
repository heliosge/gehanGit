<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--富文本  -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/kindeditor-4.1.10/themes/default/default.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/kindeditor-4.1.10/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=request.getContextPath()%>/js/kindeditor-4.1.10/kindeditor.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/js/kindeditor-4.1.10/lang/en.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/js/kindeditor-4.1.10/plugins/code/prettify.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style>
	.btn {width: 100px;height: 32px;background: #DC0500;color: white;line-height: 32px;border: none;cursor: pointer;margin-top: 9px;}
</style>
</head>

<body>

<script type="text/javascript">
 
<%-- var notice = ${notice};
$(function(){
	fillNoticeInfo();
});

function fillNoticeInfo(){
	$("#title").val(notice.title);
	$("#content").html(notice.content);
}

var editor;
KindEditor.ready(function(K){
	var langType ="zh_CN";
	editor = K.create("textarea[name=content]",{
		width: "100%",
		height: "430px",
		pasteType: 2,
		langType : langType,
		uploadJson : "<%=request.getContextPath()%>/file/uploadImg.action?path=CommonImage&DB=false",
		fileManagerJson : "<%=request.getContextPath()%>/file/filemanageJson.action",
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

function cancel(){
	window.location.href="<%=request.getContextPath()%>/manageUser/toMyReceiveNotice.action";
}

var artDialog;
function chooseReceiver(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/manageUser/chooseReceiver.action",
        title:"选择人员" ,
        height: 500,
		width: 1100
		}).showModal();
}

function chooseDept(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/manageUser/chooseDept.action",
        title:"选择部门" ,
        height: 500,
		width: 400
		}).showModal();
}

var receiverIds=[];
var deptIds=[]; --%>

var artDialog;
function uploadHeadPhoto(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/manageUser/toUploadPhoto.action",
        title:"选择头像" ,
        height: 250,
		width: 450
		}).showModal();
}

</script>

<div id="content_i">
	<div class="left_nav">
		<img src="${USER_BEAN.headPhoto }" style="height:188px;width:256px;cursor:pointer;" onclick="uploadHeadPhoto()"/>
    	<h3>
        	<span style="margin-left:-15px;">${USER_NAME_STRING }</span>
        	<span>学员</span>
        </h3>
        <h4>账户信息</h4>
        <ul  id="left_nav">
            <li><a href="<%=request.getContextPath()%>/manageUser/toPersonnalBaseInfo.action">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toPersonnalInfo.action">个人信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toChangePassword.action">修改密码</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMyCertificate.action">我的证书</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMyReceiveNotice.action">站内信-收件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySendNotice.action">站内信-发件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySystemNotice.action">站内信-系统设置</a></li>
        </ul>
    	
    </div>
    <div class="right_options" id="rop">
        <div class="right_option5" style="padding-bottom:0px;">
            <p>邮件标题：${notice.title }</p>
        </div>
        <div style="overflow:auto;height:500px;margin-top:10px;">
        	${notice.content}
        </div>
        <!--  <textarea name="content" id="content"></textarea> -->
         <input type="button" class="btn" onclick="window.history.back(-1);" value="返回"/>
    </div>
</div>
</body>

</html>
