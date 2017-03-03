<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改密码</title>
</head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<script type="text/javascript">
$(function(){
	initValidate();
})

function changePassword(){
	$('#addForm').isValid(function(v) {
		if(v){
			var newPass = $("#newPass").val();
			var newPass1 = $("#newPass1").val();
			var pass = $("#pass").val();
			if(newPass != newPass1){
				dialog.alert("两次输入密码不一致，请重新输入。");
				return;
			}
			$.ajax({
		   		type:"POST",
		   		async: false,  //默认true,异步
		   		dataType:"json",
		   		data:{
		   			"password":pass,
		   			"newPassword":newPass
		   		},
		   		url:"<%=request.getContextPath()%>/manageUser/updatePassword.action",
		   		success:function(data){
		   			//dialog.alert(JSON.stringify(data));
		   			if(data == "SUCCESS"){
		   				dialog.alert("密码修改成功。");
		   			}else if(data == "FAIL"){
		   				dialog.alert("密码修改失败。");
		   			}else{
		   				dialog.alert("原密码不正确。")
		   			}
		   	    }
		   	});
		} else {
			//dialog.alert("表单验证不通过");
		}
	})
}

function reset(){
	$("#password").val("");
	$("#newPassword").val("");
	$("#newPassword1").val("");
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

/**
 * 表单验证
 */
function initValidate() {
	$('#addForm').validator({
		theme : 'yellow_right',
		rules : {
			checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ]
		},
		//msgStyle:"height: 10px;",
		fields : {
			newPass1 : {
				rule : "required;length[6~30]",
				msg : {
					required : "新密码不能为空",
					length : "长度需在6~30个字符之间"
				}
			},
			newPass : {
				rule : "required;length[6~30]",
				msg : {
					required : "新密码不能为空",
					length : "长度需在6~30个字符之间"
				}
			},
			pass : {
				rule : "required",
				msg : {
					required : "原密码不能为空"
				}
			}
		}
	});
}

</script>

<body>

<div id="content_i">
	<div class="left_nav">
		<img src="${USER_BEAN.headPhoto }" id="headPhoto" style="height:188px;width:256px;cursor:pointer;" onclick="uploadHeadPhoto()"/>
    	<h3>
        	<span style="margin-left:-15px;">${USER_NAME_STRING }</span>
        	<span>学员</span>
        </h3>
        <h4>账户信息</h4>
        <ul  id="left_nav">
            <li><a href="<%=request.getContextPath()%>/manageUser/toPersonnalBaseInfo.action">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toPersonnalInfo.action">个人信息</a></li>
            <li class="active_2"><a href="#" class="active_3">修改密码</a></li>
           <li><a href="<%=request.getContextPath()%>/manageUser/toMyCertificate.action">我的证书</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMyReceiveNotice.action">站内信-收件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySendNotice.action">站内信-发件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySystemNotice.action">站内信-系统消息</a></li>
            <li><a href="<%=request.getContextPath()%>/integralStore/toMyIntegealExchange.action">我兑换的商品</a></li>
        </ul>
    	
    </div>
    <div class="right_options" id="rop">

        <div class="right_option3">
        	<form id="addForm">
                <table width="100%" class="tab_3">
                    <tr>
                        <th><span>*</span>原密码：</th>
                        <td><input type="password" id="pass" name="pass"/></td>
                    </tr>
                    <tr>
                        <th><span>*</span>新密码：</th>
                        <td><input type="password" id="newPass" name="newPass"/></td>
                    </tr>
                    <tr>
                        <th><span>*</span>确认密码：</th>
                        <td><input type="password" id="newPass1" name="newPass1"/></td>
                    </tr>
                </table>
                <input type="button" value="保存" class="btn_8" onclick="changePassword();"/>
                <input type="button" value="重置" class="btn_9" onclick="reset();"/>
            </form>
        </div>
    </div>
</div>
</body>

</html>
