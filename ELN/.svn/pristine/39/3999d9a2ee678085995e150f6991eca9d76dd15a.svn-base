<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账户信息</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/webhr.js"></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>

<script>
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
</head>

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
            <li class="active_2"><a href="#" class="active_3">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toPersonnalInfo.action">个人信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toChangePassword.action">修改密码</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMyCertificate.action">我的证书</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMyReceiveNotice.action">站内信-收件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySendNotice.action">站内信-发件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySystemNotice.action">站内信-系统消息</a></li>
            <li><a href="<%=request.getContextPath()%>/integralStore/toMyIntegealExchange.action">我兑换的商品</a></li>
        </ul>
    	
    </div>
    <div class="right_options" id="rop">
        <div class="right_option1" style="display:block;">
         	<p>
            	<span>用户名:</span>
            	${USER_BEAN.userName }
            </p> 
            <p>
            	<span>姓名:</span>
            	${USER_BEAN.name }
            </p>
            <p>
            	<span>性别:</span>
            	${USER_BEAN.sex==1?'男':'女' }
            </p>
            <p>
            	<span>出生日期:</span>
            	${USER_BEAN.birthDay }
            </p>
            <p>
            	<span>身份证号:</span>
            	${USER_BEAN.idCard }
            </p>
            <p>
            	<span>部门:</span>
               	${USER_BEAN.deptName }
            	
            </p>
            <p>
            	<span>岗位:</span>
            	${USER_BEAN.postName }
            </p>  
        </div>
     </div>
</div>
</body>

</html>
