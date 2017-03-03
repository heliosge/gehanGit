<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>人员字段配置</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css"/>


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

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css"/>

<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">

</style>

<script type="text/javascript">
//查找可选字段列表
function selectField(isEmpty){
	
	dialog({
        url:"<%=request.getContextPath()%>/manageParam/selectField.action?isEmpty="+isEmpty,
        title:"选择字段" ,
		height: 500,
		width: 750,
		/* buttons: [ { text: '确定', 
			onclick: function (item, dialog) {
				$(window.parent.document).contents().find("#"+dialog.jiframe[0].id)[0].contentWindow.slFiledInfo();
				//$("#"+dialog.jiframe[0].id).contentWindow().slUserInfo()//.contents().slUserInfo()//.contentWindow().slUserInfo(); 
			  //$(window.parent.document).contents().find("#iframename")[0].contentWindow.iframefunction();
			}},
			{ text: '取消', 
				onclick: function (item, dialog) {
					dialog.close();
				}}
			] */
	}).showModal();
}
//保存完用户的角色
function afterSaveUserRole(){
	//关闭弹出框
	$.ligerDialog.close();
}
</script>
</head>
<body style="overflow-x:hidden;">
<div class="content">
	<!-- <h3>人员字段配置</h3> -->
    <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">人员字段配置</span>
	 </div>
	<h4>固有字段</h4>
	<div class="tab_2 fl" style="width:500px;">
		<table width="100%">
    		<tbody><tr class="tr2">
            	<th>字段</th>
            	<th>必填/选填</th>
        	</tr>
        	<tr>
                <td>用户名</td>
                <td>必填</td>
        	</tr>
            <tr>
                <td>姓名</td>
                <td>必填</td>
        	</tr>
            <tr>
                <td>性别</td>
                <td>必填</td>
        	</tr>
            <tr>
                <td>出生日期</td>
                <td>必填</td>
        	</tr>
            <tr>
                <td>身份证号码</td>
                <td>必填</td>
        	</tr>
            <tr>
                <td>工号</td>
                <td>选填</td>
        	</tr>
            <tr>
                <td>部门</td>
                <td>必填</td>
        	</tr>
            <tr>
                <td>岗位</td>
                <td>必填</td>
        	</tr>
            <tr>
                <td>手机号码</td>
                <td>选填</td>
        	</tr>
            <tr>
                <td>电子邮箱</td>
                <td>选填</td>
        	</tr>
            <tr>
                <td>QQ号</td>
                <td>选填</td>
        	</tr>
            <tr>
                <td>微信号</td>
                <td>选填</td>
        	</tr>
            <tr>
                <td>通讯地址</td>
                <td>选填</td>
        	</tr>
             <tr>
                <td>最高学历</td>
                <td>选填</td>
        	</tr>
            <tr>
                <td>毕业院校</td>
                <td>选填</td>
        	</tr>
            <tr>
                <td>专业</td>
                <td>选填</td>
        	</tr>
            <tr>
                <td>进入公司时间</td>
                <td>选填</td>
        	</tr>
            <tr>
                <td>参加工作时间</td>
                <td>选填</td>
        	</tr>
        </tbody></table>
     </div>
     <div class="zd_right">
     	<h4>扩展字段选择：</h4>
        <p><span>必填项：</span><input type="button" class="btn_1" value="字段库选择" onclick="selectField(0)">
        </p>
           <p style="margin-left: 63px;">
        	<c:forEach items="${companyParamList}" var ="plist">
        		<c:if test="${plist.isEmpty==0}">
        		<label style='margin-left: 5px;background: #ccc;'>${plist.propertyName}</label>
        		</c:if>
        	</c:forEach>
       </p>
        	
        <p><span>选填项：</span><input type="button" class="btn_1" value="字段库选择" onclick="selectField(1)">
        </p>
           <p style="margin-left: 63px;">
        <c:forEach items="${companyParamList}" var ="plist">
        	<c:if test="${plist.isEmpty==1}">
        		<label style='margin-left: 5px;background: #ccc;'>${plist.propertyName}</label>
        	</c:if>
        	</c:forEach>
        	</p>
     </div>
</div>
<div id="add_iframe" style="display:none;width:100%;height:100%;">
	<iframe frameborder="0" src="" style="width:100%;height:100%;" ></iframe>
</div>
	
</body>
</html>
