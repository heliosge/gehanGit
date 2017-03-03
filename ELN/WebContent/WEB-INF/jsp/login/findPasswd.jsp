<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!--validate  -->
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<title>找回密码</title>
<style>
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
</style>
<script>

$(function(){
	initValidate();
})

function cancel(){
	window.location.href="<%=request.getContextPath()%>/";
}

function recieveActiveCode(){
	if(!$("#key").val() || !$("#userName").val()){
		dialog.alert("请输入邮箱/手机、用户名。");
		return;
	}
	var param= {};
	param.userName = $("#userName").val();
	if($("#keyType").val() == 1){
		param.phone = $("#key").val();
	}else{
		param.email = $("#key").val();
	}
	$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		data:param,
   		url:"<%=request.getContextPath()%>/login/recieveActiveCode.action",
   		success:function(data){
   			if(data == 'SUCCESS'){
   				dialog.alert("验证码已发送。");
   			}else if(data == 'NOT_MATCH'){
   				dialog.alert("用户名、手机号码/邮箱不匹配。");
   			}else{
   				dialog.alert("验证码发送失败，请重试。");
   			}
   	    }
   	});
}

function reSetPassWd(){
	$('#addForm').isValid(function(v) {
		if(v){
			var param = {};
			param.userName = $("#userName").val();
			param.keyType = $("#keyType").val();
			param.key = $("#key").val();
			param.activeCode = $("#activeCode").val();
			$.ajax({
		   		type:"POST",
		   		async:true,  //默认true,异步
		   		data:param,
		   		url:"<%=request.getContextPath()%>/login/reSetPassWd.action",
		   		success:function(data){
		   			if(data == 'SUCCESS'){
		   				dialog.alert("密码已发送。",function(){
		   					window.location.href="<%=request.getContextPath()%>/login/loginPage.action";
		   				});
		   			}else if(data == 'NOT_MATCH'){
		   				dialog.alert("用户名、手机号码/邮箱不匹配。");
		   			}else{
		   				dialog.alert("密码发送失败,请重试。");
		   			}
		   	    }
		   	});
		}
	})
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
		msgStyle:"margin-top:10px;margin-left:10px;",
		fields : {
			userName : {
				rule : "required;",
				msg : {
					required : "请输入用户名"
				}
			},
			key : {
				rule : "required;",
				msg : {
					required : "请输入手机号或邮箱"
				}
			},
			activeCode : {
				rule : "required;",
				msg : {
					required : "请输入验证码"
				},
				msgStyle:"left:-30px;",
				msgClass:"n-top"
			}
		}
	});
}
</script>
</head>
<body>
<div id="content">
	<div class="content_head" style="height:56px;">
    	<a href="login.html"><img width="210px" height="56px"  src="<%=request.getContextPath() %>/images/img/logo_2_new.png" title="安培在线" /></a>
        <p>找回密码</p>
    </div>
    <div class="lesson_add" style="margin-top:20px;">
    	<form id="addForm">
            <div class="add_gr">
                <div class="add_fl">
                    <span>*</span>
                    <em>用户名：</em>
                </div>
                <div class="add_fr">
                	<input type="text" id="userName" name="userName"/>
                </div>
            </div>
            <div class="add_gr">
                <div class="add_fl">
                    <span>*</span>
                    <em>找回方式：</em>
                </div>
                <div class="add_fr">
                    <select style="width:100px;" id="keyType">
                    	<option selected="selected" value="1">密保手机:</option>
                        <option value="2">邮箱:</option>
                    </select>
                    <input type="text" id="key" name="key"/>
                </div>
            </div>
            <div class="add_gr">
                <div class="add_fl">
                    <span>*</span>
                    <em>验证码：</em>
                </div>
                <div class="add_fr">
                	<input type="text" style="width:150px; color:#999;" id="activeCode" name="activeCode"/>
                    <input type="button" value="发送验证码" onclick="recieveActiveCode()"/>
                </div>
            </div>
            <div class="button_gr" style="text-align:center;">
            	<input type="button" value="确定" onclick="reSetPassWd()"/>
                <input type="button" value="取消" onclick="cancel()" />
            </div>
          </form>
     </div>
</div>
</body>
</html>