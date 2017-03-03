<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>讲师管理</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

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

<!-- 上传插件 -->
<script src="<%=request.getContextPath()%>/js/jqueryFileUpload/vendor/jquery.ui.widget.js"></script>
<script src="<%=request.getContextPath()%>/js/jqueryFileUpload/jquery.iframe-transport.js"></script>
<script src="<%=request.getContextPath()%>/js/jqueryFileUpload/jquery.fileupload.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/teacher.css"/>

<script type="text/javascript" src= '<%=request.getContextPath()%>/js/artdialog/artdialog.js?skin=chrome'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/js/artdialog/iframetools.js'></script>	

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style type="text/css">
.dom-hide{
	display: none
}
.input-hide-border{
	outline: none;
	border: none;
}
</style>

<script type="text/javascript">

//var listTeacherBean = ${listTeacherBean}; //部门

/*  $(function(){
	
	var param = ${teacherInfo};
	
	var description = param.description||"";
	$("#description").html(description.replace(/\n/g,"<br>"));
	
	
	$("#teacherCategory").val(function(){
		if(param.teacherCategory==1){
			return "内部讲师";
		}else{
			return "外部讲师";
		}
	});
	$("#sex").val(function(){
		if(param.sex==1){
			return "男";
		}else{
			return "女";
		}
	})
	
	  $("input[name='userName']").val(param.userName);
	  $("input[name='teacherName']").val(param.teacherName);
	  $("input[name='eMail']").val(param.eMail );
	  $("input[name='cardId']").val(param.cardId);
	  $("input[name='education']").val(param.education) 
	  $("input[name='phoneNum']").val(param.phoneNum) 
	  $("textarea[name='description']").val(param.description); 
	  $("#userIcon").attr("src",param.iconPath).show();
});*/

</script>
</head>
<body style="overflow-x:hidden;">
<div id="content_i" class='content'>	
	<div>
	
		<!-- <h2 class="ui-tit-h2 fn-clear">
		    <a href="#" class="back" title="返回">
		    	</a><span>知识管理<i></i></span>
		    <hr>
		</h2> -->
		<!-- <h3>讲师详情</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick='javascript:location = "<%=request.getContextPath()%>/teacher/list.action"'/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">讲师详情</span>
		</div>
	
	</div>

	<div class="first_div" style="border-right:none;border-bottom:none;border-left:none;margin:0px">
			
		<div style="margin:3px 10px 10px 10px;"></div>
			<div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>讲师分类：</em>
            </div>
            <div class="add_fr">
	          	<c:choose>
	          		<c:when test="${teacherInfo.teacherCategory==1}">
						<label>内部讲师</label>	
	          		</c:when>
	          		<c:otherwise>
						<label>外部讲师</label>	
	          		</c:otherwise>
	          	</c:choose>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>用户名：</em>
            </div>
            <div class="add_fr">
            	<label>${teacherInfo.userName}</label>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>姓名：</em>
            </div>
             <div class="add_fr">
     	        <label>${teacherInfo.teacherName}</label>
            </div>
    	</div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>性别：</em>
            </div>
             <div class="add_fr">
            	<c:choose>
	          		<c:when test="${teacherInfo.sex==1}">
						<label>男</label>	
	          		</c:when>
	          		<c:otherwise>
						<label>女</label>	
	          		</c:otherwise>
	          	</c:choose>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>邮箱地址：</em>
            </div>
             <div class="add_fr">
                <label>${teacherInfo.eMail}</label>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>身份证号：</em>
            </div>
             <div class="add_fr">
                 <label>${teacherInfo.cardId}</label>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>学历：</em>
            </div>
             <div class="add_fr">
                <label>${teacherInfo.education}</label>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>手机号码：</em>
            </div>
             <div class="add_fr">
                <label>${teacherInfo.phoneNum}</label>
            </div>
    	</div>
        <div class="add_gr" style='height: initial;'>
        	<div class="add_fl">
                <em>图像：</em>
            </div>
             <div class="add_fr" style='margin-top: 7px;'>
            	<!-- <input type="button" value="选择头像" id='picker' >
            	 用来存放文件信息
    			<div id="thelist" class="uploader-list"></div>
            	<input id='imgFile'name="imgFile" class='text-p' type="file" style='display:none'> -->
            	<!-- <div id="uploader" class="wu-example">
			    用来存放文件信息
				    <div id="thelist" class="uploader-list"></div>
				    <div class="btns">
				        <div id="picker">选择头像</div>
				    </div>
				</div> -->
				<c:if test="${teacherInfo.iconPath!=''}">
					<img  class='dom-hide' id='userIcon' style='width: 150px;height: 150px;display: block;' src="${teacherInfo.iconPath}">
				</c:if>
            </div>
            
            <input id='imgFile'name="imgFile" class='text-p' type="hidden" >
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>擅长领域：</em>
            </div>
            <div class="add_fr" style=''>
                <label id="description">${teacherInfo.description}</label>
            </div>
        </div>
         <div class="button_cz" style="  float: none;padding-bottom: 100px;">
        	<%-- <input type="button" id='ctlBtn' value="保存" > --%>
            <input type="button" value="返回" 
				onclick="window.location='<%=request.getContextPath()%>/teacher/list.action'"
	            class="back">
        </div>
    </div>
	</div>
</div>	
<div id="add_iframe" style="display:none;width:100%;height:100%;">
	<iframe frameborder="0" src="" style="width:100%;height:100%;" ></iframe>
</div>
	
</body>
</html>
