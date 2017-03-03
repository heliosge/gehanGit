<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的证书</title>
</head>
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
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<style type="text/css">
 .cer_detail {
	display: block;height: 32px;width: 120px;background: #CC0001;line-height: 32px;color: white;text-align: center;float: left;border-radius: 2px;
} 
</style>

<script  type="text/javascript">
var cerList = ${cerList};
$(function(){
	var html = '';
	for(var i = 0; i < cerList.length; i++){
		var cer = cerList[i];
		html += '<div class="cert" id="'+cer.id+'_div"> <img src="'+cer.frontImg+'" width="313px" height="207px"/> '+
        '<p>证书名称：'+ cer.name +'</p>'+
        '<p>发证机构：'+ cer.trainAgency +'</p>'+
        '<p>发证日期：'+ cer.sendDate +'</p>'+
        '<a href="javascript:void(0);" class="cer_detail" id="'+cer.id+'_a" name="'+cer.id+'">查看详情</a></div>'+
     '<div id="'+cer.id+'_detail" class="cert_txt" style="display:none;"><p>理论成绩：'+ cer.theoryScore +'</p>'+
        '<p>实操成绩：'+ cer.operateScore +'</p>'+
        '<p>初次复审日期：'+ cer.firstCheckDate+'</p>'+
        '<p>二次复审日期：'+ cer.secondCheckDate+'</p>'+
        '<p>证书复审状态：'+ (cer.checkStatus==0?'':(cer.checkStatus==1?'已复审':"未复审")) +'</p>'+
        '<p>培训方式：'+ cer.trainStyle +'</p>'+
        '<p>培训机构：'+ cer.trainAgency +'</p>'+
        '<p>培训机构级别：'+ (cer.trainAgencyLevel==1?"国家一级":(cer.trainAgencyLevel==2?"国家二级":(cer.trainAgencyLevel==3?"国家三级":(cer.trainAgencyLevel==4?'国家四级':(cer.trainAgencyLevel==5?'国家五级':'其他')))))+'</p>'+
        '<p>换证日期：'+ cer.changeDate +'</p>'+
        '<p>备注：'+ cer.descr +'</p>'+
        '<input type="button" value="返回" onclick="hideDetail(this)" name="'+cer.id+'"/></div>'
	}
	$("#cerList").html(html);
	
	$(".cer_detail").click(function(){
		$(this).hide();
		$("#"+$(this).attr("name")+"_detail").show();
		$(".cert").hide();
		$("#"+$(this).attr("name")+"_div").show();
	});
})

function hideDetail(obj){
	$(obj).parent().hide();
	$(".cert").show();
	$("#"+$(obj).attr("name")+"_a").show();
}

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

<body>

<div id="content_i">
	<div class="left_nav">
		<img id="headPhoto" src="${USER_BEAN.headPhoto }" style="height:188px;width:256px;cursor:pointer;" onclick="uploadHeadPhoto()"/>
    	<h3>
        	<span style="margin-left:-15px;">${USER_NAME_STRING }</span>
        	<span>学员</span>
        </h3>
        <h4>账户信息</h4>
        <ul  id="left_nav">
            <li><a href="<%=request.getContextPath()%>/manageUser/toPersonnalBaseInfo.action">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toPersonnalInfo.action">个人信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toChangePassword.action">修改密码</a></li>
            <li  class="active_2"><a href="#"  class="active_3">我的证书</a></li>
           	<li><a href="<%=request.getContextPath()%>/manageUser/toMyReceiveNotice.action">站内信-收件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySendNotice.action">站内信-发件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySystemNotice.action">站内信-系统消息</a></li>
            <li><a href="<%=request.getContextPath()%>/integralStore/toMyIntegealExchange.action">我兑换的商品</a></li>
        </ul>
    	
    </div>
    <div class="right_options" id="rop">
        
        <div class="right_option4" id="cerList">
        </div>
     </div>
  </div>
</body>

</html>
