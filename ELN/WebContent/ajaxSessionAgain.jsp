<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>多次登陆</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />

<script type="text/javascript">

$(function(){
	
	alert("您的账号已经在其它地方登陆！");
	
	//退出
	window.location.href = "<%=request.getContextPath()%>/login/loginout.action";
});

</script>
</head>


<body >

</body>

</html>
