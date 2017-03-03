<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>登录页面</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">

if(window != top){
	//session 失效 判断有没父页面，有，改变父页面地址
	top.location.href = location.href; 
}  
	

$(function(){
	if(getCookie("eln_username") != undefined && getCookie("eln_password") != undefined){
		$("#user").val(getCookie("eln_username"));
		$("#password").val(getCookie("eln_password"));
	}
	
	initBackground();
});

function initBackground(){
	$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			dataType:'text',
			url:"<%=request.getContextPath()%>/login/initBackground.action",
			success:function(data){
				if(data != '' && data != 'null'){
					$("#sign").append("<img width='100%' height='757px' style='z-index:-100;position:absolute;left:0px;top:0px;' src='"+data+"'>");
					//$("#sign").css("background","url("+data+")");
				}else{
					$("#sign").css("background","url(<%=request.getContextPath()%>/images/img/bg.png) no-repeat center scroll");
				}
			}
	});
}

//更换验证码图片
function refresh() {   
 //IE存在缓存,需要new Date()实现更换路径的作用   
 document.getElementById("image").src="<%=request.getContextPath()%>/createimage.jsp?"+new Date();   
} 

function login(){
	
	$.ajax({  
			type:"POST",  
			url:"<%=request.getContextPath()%>/getsession.jsp",  
			success:function(msg){  
 			msg = jQuery.trim(msg);  
 			if(msg == $("#code").val()){
 				if($("#rem")[0].checked){
 					setCookie("eln_username",$.trim($("#user").val()));
 					setCookie("eln_password",$.trim($("#password").val()));
 				}else{
 					deleteCookie("eln_username");
 					deleteCookie("eln_password");
 				}
 				
 				if(getCookie("eln_username") != undefined && getCookie("eln_password") != undefined){
 					$("#user").val(getCookie("eln_username"));
 					$("#password").val(getCookie("eln_password"));
 				}
 				
 				// 准备参数
 				var param = new Object();
 				param.userName = $.trim($("#user").val());
 				param.password = $.trim($("#password").val());
 				
 				if(param.userName == ""){
 					alert('用户名不可为空');
 					return false;
 				}else if(param.password == ""){
 					alert('密码不可为空');
 					return false;
 				}

 				$.ajax({
 					type:"POST",
 					async:true,  //默认true,异步
 					data:param,
 					dataType:'text',
 					url:"<%=request.getContextPath()%>/login/tologin.action",
 					success:function(data){
 						
 						if(data == "OK"){
 							window.location.href = "<%=request.getContextPath()%>/index/toIndexPage.action";
 						}else if(data == "PASSWORD_ERROR"){
 							//dialog.alert("密码错误！");
 							alert("密码错误！");
 						}else if(data == "NO_EXIST"){
 							//dialog.alert("用户不存在！");
 							alert("用户不存在！");
 						}else if(data == "STATUS_FREEZE"){
 							//dialog.alert("该账户已被冻结，请联系管理员！");
 							alert("该账户已被冻结，请联系管理员！");
 						}else if(data == "COMPANY_STATUS_FREEZE"){
 							//dialog.alert("该企业已被冻结，请联系管理员！");
 							alert("该企业已被冻结，请联系管理员！");
 						}else if(data == "OVER_DATE"){
 							//dialog.alert("该企业所有账号已过期，请联系管理员！");
 							alert("该企业所有账号已过期，请联系管理员！");
 						}else{
 							//dialog.alert("登录失败！");
 							alert("登录失败！");
 						}
 						
 				    }
 				});
 			}else{  
 				alert('验证码错误。');
 				refresh();
 			}  
			}     
		});	
	
	
}

function key_down(num){
	if(num == 13) {
		login();
	}
}

function setCookie(key,value,options){
	  var options = options||{};
	  if(options.hour){
	    var today = new Date();
	    var expire = new Date();
	    expire.setTime(today.getTime() + options.hour * 3600000);
	  }
	  window.document.cookie = 
	    key + "=" + encodeURI(value)
	      + (options.path ? "; path=" + options.path : "")
	      + (options.hour ? "; expires=" + expire.toGMTString() : "")
	      + (options.domain ? "; domain=" + options.domain : "");
	  return this;
	}
	
function getCookie(key){
	  var reg = new RegExp("(^| )" + key + "=([^;]*)(;|\x24)"),
	  result = reg.exec(document.cookie);
	  if(result){
	    return decodeURI(result[2])||null;
	  }
	}
	
	
function deleteCookie(key) { 
	document.cookie=key + "=; expire="+(new Date()).toGMTString(); 
	}
function findPassword(){
	window.location.href="<%=request.getContextPath()%>/login/findPassword.action";
}
	
</script>
</head>


<body onkeydown="key_down(event.keyCode);">
<div id="sign">
	<div class="sign_up clear">
        <div class="login clear">
       	<div class="sign_txt">
	        <a href="<%=request.getContextPath()%>/login/toSignUpPage.action" style="border-radius:8px;text-align:center;background-color:#4C97CC;">
	       		<span style="font-size:15px;font-family:'微软雅黑';">注册</span>
	        	<%-- <img src="<%=request.getContextPath()%>/images/img/btn_reg.png" ></img> --%>
	        </a>
	        <a href="" style="border-radius:8px;text-align:center;background-color:#4C97CC;">
	        	<span style="font-size:15px;font-family:'微软雅黑';">下载APP</span>
	        	<%-- <img src="<%=request.getContextPath()%>/images/img/btn_download.png" ></img> --%>
	        </a>
	        <a href="" style="border-radius:8px;text-align:center;background-color:#4C97CC;margin-right:0px;">
	        	<span style="font-size:15px;font-family:'微软雅黑';">官方微信</span>
	        	<%-- <img src="<%=request.getContextPath()%>/images/img/btn_wechat_p.png" ></img> --%>
	        </a>
        </div>
            <div class="login_txt">
            	<table>
            		<tr><td width="70px"><img src="<%=request.getContextPath()%>/images/img/username.png" width="20px" height="20px"/> 用户名:</td><td><input type="text" name="name" id="user" /></td></tr>
            		<tr><td><img src="<%=request.getContextPath()%>/images/img/password.png"  width="20px" height="20px"/> 密码:</td><td><input type="password" name="password" id="password" /></td></tr>
            		<tr>
            			<td>验证码:</td>
	            		<td>
	            			<input type="text" name="code" id="code" style="width:120px;float:left;"/>
							<img style="vertical-align:middle;float:right;height:32px;margin-top: 8px;" id="image" border="0" alt="" onclick="refresh()" src="<%=request.getContextPath()%>/createimage.jsp" title="<s:text name='frame.changeImage'/>"/>
                
	            		</td>
	            	</tr>
	            	<tr >
	            		<td colspan="2">
		        			<img src="<%=request.getContextPath()%>/images/img/btn_login.png" onclick="login()" style="cursor: pointer;margin-top:15px;width:300px;height:30px;"/>
	            		</td>
	            	</tr>
	            	<tr >
	            		<td colspan="2">
		            		<input type="checkbox" name="rem" id="rem" checked style="margin-top:15px;width:12px;height:12px;"/>
		                	<span>记住密码</span>
		                	<span class="fog fr" style="cursor: pointer;margin-top:15px;" onclick="findPassword()">忘记密码?</span>
	            		</td>
	            	</tr>
            	</table>
               	  	
            </div>
        </div>
        <div style="margin: 0 auto;width:375px;">
        	<p style="font-size:25px;font-family:'方正姚体';color:#4678CB;">服务热线：4&nbsp;0&nbsp;0&nbsp;&nbsp;-&nbsp;&nbsp;8&nbsp;4&nbsp;3&nbsp;0&nbsp;&nbsp;-&nbsp;&nbsp;4&nbsp;0&nbsp;0</p>
        	<p style="font-size:25px;font-family:'方正姚体';color:#4678CB;">服务邮箱：service@anpeiwang.com</p>
        </div>
    </div>
</div>
</body>

</html>
