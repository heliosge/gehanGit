<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>国际化示例</title>
</head>
<body>
<spring:message code="test.test" /><br/>
<a href="<c:url value="/sample/i18n.action?locale=zh_CN" />">中文-默认</a><br/>
<a href="<c:url value="/sample/i18n.action?locale=en_US" />">English</a><br/>
<a href="<%= request.getContextPath() %>/sample/i18n.action?locale=zh_CN">中文-默认</a><br/>
</body>
</html>