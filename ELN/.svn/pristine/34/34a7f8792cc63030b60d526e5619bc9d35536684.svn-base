<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台数据监控</title>

<link rel="stylesheet" href="<c:url value="/css/page.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>

<script type="text/javascript">

$(function(){
	
});


</script>
<style type="text/css">
</style>
</head>

<body>

<!-- todo delete -->
<div id="showTempResult" style="width:300px;">
</div>

<div class="content" style="margin-top:20px;padding-bottom:10px;">

	<!-- <h3>后台数据监控</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">后台数据监控</span>
	</div>
    <div style="padding:30px 30px;font-size:14px;">
    	<span>同时在线人数/最大在线人数：</span> 
    	<span>${onlineCount}/${maxCount }</span>
    </div>
	
</div>
</body>
</html>
