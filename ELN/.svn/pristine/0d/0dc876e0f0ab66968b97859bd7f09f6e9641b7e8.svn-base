<%@ page import="com.jftt.wifi.bean.MobileAppVersionBean" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>移动端版本详情</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<style type="text/css">

.param_table td{
	padding: 2px;
}
.param_table .input_0{
	width: 200px;
}

.need_span{color:red;font-size:13px;}
.validation_span{color:red;font-size:13px;}
</style>

<script type="text/javascript">

$(function(){
	
	
	
	//返回按钮
	$("#backBtu").click(function(){
		    	window.location.href = "<%=request.getContextPath()%>/mobileAppVersion/toManagePage.action";
		    
		
	});
	
   
});



</script>
</head>
<body style="">
<div class="content">
	<!-- <h3>计划基本详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">移动端版本情</span>
	 </div>
     <%com.jftt.wifi.bean.MobileAppVersionBean mobileAppVersion= (com.jftt.wifi.bean.MobileAppVersionBean) request.getAttribute("mobileAppVersion");
     if(mobileAppVersion==null){
    	 out.println("<script>dialog.alert('数据加载失败！');window.location.href = "+request.getContextPath()+"/mobileAppVersion/toManagePage.action</script>");
     }
     %>
    <form >
	<div class="lesson_add" style=" border:none;">
    	<div class="add_gr">
        	<div class="add_fl">
                <em>版本名称：</em>
            </div>
            <div class="add_fr">
            <%=mobileAppVersion.getName()    %>
            </div>
        </div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em>版本号：</em>
            </div>
            <div class="add_fr">
           <%=mobileAppVersion.getCode() %>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>是否强制更新：</em>
            </div>
            <div class="add_fr">
                <%  String isForceStr ="否";
                    if(mobileAppVersion.getIsForce()==1){
                        isForceStr="是";
                } %>
            	<%=isForceStr %>

            </div>
        </div>
       <div class="add_gr" style="height: auto">
        	<div class="add_fl">
                <em>描述：</em>
            </div>
            <div class="add_fr">
 				<div	class="previewDiv" style='max-height: 350px;overflow: auto;overflow: auto;width: 80%;border:1px solid #CCCCCC;'>
					<%=mobileAppVersion.getDescr() %>
				</div>
            </div>
            <div class="add_fr">
            	
            </div>
        </div>
           <div class="add_gr" style='position: relative;height: inherit;'>
        	<div class="add_fl">
                <em>文件名称：</em>
            </div>
            
             <div class="add_fr" >
                 <%=mobileAppVersion.getFileName() %>
			
            </div>
    	</div>
        
       
        
    </div>
    </form >
      <div class="button_cz fl" style="margin-top:20px; width:1046px;">
            <input type="button" name="backBtn" id="backBtu" value="返回" class="btn_n" />
    </div>


        
</div>

	
</body>
</html>
