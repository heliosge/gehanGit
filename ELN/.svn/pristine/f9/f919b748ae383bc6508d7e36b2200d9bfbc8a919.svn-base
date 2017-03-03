<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
<title>资讯管理</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>

<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css"/>
<style type="text/css">

html, body{
	height: 100%;
}


</style>

<script type="text/javascript">

</script>
</head>

<body style="overflow-x:hidden;">

<div id='content_i' class='content' >
	<div class='knowledge'>
		<!-- <h3>资讯详情</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">资讯详情</span>
		</div>
	</div>
	<div class="lesson_add">
	<form action="" id="addForm">
    	<div class="add_gr" style="text-align:center;width: 100%">
            <div class="add_fr" style="margin: 0 auto;width: 100%;">
            	<label style="font-size: 16px;font-weight: bold;">${informationBean.infoName}</label>
            	<input type="hidden" style="width:270px;" id="infoName" name="infoName" >
            	<label id="times" style="position: relative;top: 20px;right: 60px;"><fmt:formatDate value="${informationBean.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/> </label>
            </div>
        </div>
        <div class="add_gr"  style="height: auto">
            <div class="add_fr" style="width: 100%">
 				<div class="previewDiv" style='overflow: auto;overflow: auto;width: 100%;max-height: 800px;'>
					${informationBean.infoContent}
				</div>
            </div>
             <div class="button_cz" style="margin: 0;padding: 0;float: left;margin-top: 30px;">
            <input type="button" value="返回" 
				onclick="history.go(-1)"
	            class="back">
       	</div>
        </div>
       
	</form>
    </div>
</div>
</body>
</html>
