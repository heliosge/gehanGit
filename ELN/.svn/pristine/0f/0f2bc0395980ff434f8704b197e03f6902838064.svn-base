<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>登录页面</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css" />
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
	
	var Owx = document.getElementById('wx');
	var Ospan = Owx.getElementsByTagName('span')[0];
	Owx.onmouseover = function(){
		Ospan.style.display = 'block';
		
		}
	Owx.onmouseout = function(){
		Ospan.style.display = 'none';
		
		}
});

function initBackground(){
	$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			url:"<%=request.getContextPath()%>/login/initBackground.action",
			success:function(data){
				if(data.copyright != '' && data.copyright != 'null'){
					$("#copyright").html(data.copyright);
				}
				if(data.cover != '' && data.cover != 'null'){
					$(".login").append("<img width='100%' height='456px' style='z-index:-100;position:absolute;left:0px;top:70px;' src='"+data.cover+"'>");
					//$("#sign").css("background","url("+data+")");
				}else{
					$(".l_con").css("background","url(<%=request.getContextPath()%>/images/login/login_bg.png) no-repeat");
				}
				if(data.logo != ''){
					$("#logoImage").attr("src",data.logo);
				}
				if(data.isPulian == 2){
					$("#sign").remove();
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
	 						}else if(data == "OVER_MAXCONCURRENT"){
	 							alert("当前在线用户数量过多，请稍后再试！");
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

<style>
h4{
	width:50%;
	float:left;
}
h4 a{
	color:black;
}
</style>

</head>


<body onkeydown="key_down(event.keyCode);">
    <div class="head">
    	<div class="head_t">
        	<div class="logo_left" style="max-width:300px;min-width:212px;height:56px;width:auto;">
            	<img id="logoImage" src="<%=request.getContextPath()%>/images/login/logo.png" style="height:56px;width:100%;"/>	
            </div>
            <div class="logo_right">
            	<span class="fl phone">
                	<img src="<%=request.getContextPath()%>/images/login/phone.png" />
                </span>
                <span class="wx"  id="wx">
                	<a href="#">微信扫一扫</a>
                    <img src="<%=request.getContextPath()%>/images/login/wx.png" />
                    <span class="wx_f">
                    	<img src="<%=request.getContextPath()%>/images/login/apk.png" width="106px" height="106px" style="left:20px;"/>
                    	<img src="<%=request.getContextPath()%>/images/login/ewm.png" width="106px" height="106px" style="left:150px;"/>
                    	<%--<img src="<%=request.getContextPath()%>/images/login/apk_android.jpg" width="106px" height="106px" style="left:270px;"/>--%>
                    	 <p style="width:125px;float: left;text-align: center;"><span style="margin-left: -20px">下载APP</span></p>
                        <p style="width:110px;float: left;text-align: center;">关注安培在线</p>

                        <%--<p style="width:110px;float: right;text-align: center;">安卓客户端</p>--%>
                    </span>
                </span>
            </div>
        </div>
    </div>
    <div class="l_con">
    	<div class="login">
        	<div class="login_l">
            	<h4>用户登录</h4>
            	<h4><a href="<%=request.getContextPath()%>/login/toSignUpPage.action" id="sign">注册</a></h4>
                <p><input type="text" placeholder="用户名" id="user" /></p>
                <p><input type="password" placeholder="密码" id="password"/></p>
                <p>
                	<input type="text" name="code" id="code" style="width:100px;background: url();" placeholder="验证码" />
                	<img style="vertical-align:middle;float:right;height:40px;" id="image" border="0" alt="" onclick="refresh()" src="<%=request.getContextPath()%>/createimage.jsp" />
                
                </p>
                <p><input type="checkbox" name="rem" id="rem" checked /><span>记住密码</span><a href="javascript:void(0)" class="fog"  onclick="findPassword()">忘记密码？</a></p>
                <p><input type="button" value="登录" onclick="login()"/></p>
            </div>
        </div>
    </div>
	<div class="foot">
    	<p id="copyright">2015 All Rights Reserved 普联软件股份有限公司 版权所有 备案号：皖ICP备15020211号</p>
        <p>www.anpeiwang.com</p>
    </div>
</body>

</html>
