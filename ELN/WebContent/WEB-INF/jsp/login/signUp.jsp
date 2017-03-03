<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/webhr.js"></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
	.btn_3 {width: 68px;height: 25px;background: #D20001;border: none;color: white;float: right;}
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
</style>

<script type="text/javascript">

$(function(){
	initProvince();
	initIndustry();
	$("#province").change(function(){
		initCity($("#province").val());
	});
	initValidate();
});

	function save(){
		/* var flag = false;
		//验证验证码
		$.ajax({
			type:"POST",
			async:false,
			url:"../getsession.jsp",  
			success:function(msg){  
	 			msg = jQuery.trim(msg);  
	 			if(msg == $("#validateCode").val()){
	 			}else{
	 				dialog.alert('验证码错误。');
	 				refresh();
	 				flag = true;
	 			}
			}
		 });
		if(flag){
			return;
		} */
		$('#addForm').isValid(function(v) {
			if(v){
				$.ajax({
			   		type:"POST",
			   		async:true,  //默认true,异步
			   		data:param(),
			   		url:"<%=request.getContextPath()%>/login/signUp.action",
			   		success:function(data){
			   			if(data =='WRONG_ACTIVE_CODE'){
				   			dialog.alert("激活码错误。");
			   			}else if(data == 'SUCCESS'){
			   				dialog.alert("注册成功。",function(){
			   					window.location.href="<%=request.getContextPath()%>/login/loginPage.action";
			   				});
			   			}else{
			   				dialog.alert("注册失败。");
			   			}
			   	    }
			   	});
			} else {
				//dialog.alert("表单验证不通过");
			}
		});
	}
	
	function cancel(){
		window.location.href="<%=request.getContextPath()%>/login/loginPage.action";
	}
	
	function recieveActiveCode(){
		if(!$("#email").val()){
			dialog.alert("请输入邮箱地址。");
			return;
		}
		$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:{
	   			email : $("#email").val()
	   		},
	   		url:"<%=request.getContextPath()%>/login/recieveActiveCodeOnSign.action",
	   		success:function(data){
	   			if(data == 'SUCCESS'){
		   			dialog.alert("激活码已发送。");
	   			}else{
	   				dialog.alert("激活码发送失败。");
	   			}
	   	    }
	   	});
	}
	
	function param(){
		var o = {};
		o.name=$("#name").val();
		o.domain=$("#domain").val();
		o.code=$("#code").val();
		o.province=$("#province").val();
		o.city=$("#city").val();
		o.industry=$("#industry").val();
		o.address=$("#address").val();
		o.industry=$("#industry").val();
		o.proportion=$("#proportion").val();
		o.email=$("#email").val();
		o.attention=$("#attention").val();
		o.attentionPhone=$("#attentionPhone").val();
		o.phoneNum=$("#phoneNum").val();
		o.website=$("#website").val();
		o.activeCode=$("#activeCode").val();
		o.postCode=$("#postCode").val();
		/* $.each($("input[name='logoImage']"), function(index, val){
			if(val.checked){
				o.logoImage = $(val).val();
			}
		}); */
		o.logoImage = "<%=request.getContextPath() %>/images/img/logo_2_new.png";
		return o;
	}
	
	function initIndustry(){
		$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		url:"<%=request.getContextPath()%>/login/initIndustry.action",
	   		success:function(data){
	   			var re = '';
	   			for(var i=0;i<data.length; i++){
	   				//if(data[i].parentId != null){
	   					re += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
	   				//}
	   			}
	   			$("#industry").html(re);
	   	    }
	   	});
	}
	
	function initProvince(){
		$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		url:"<%=request.getContextPath()%>/login/initCity.action",
	   		success:function(data){
	   			var re = "<option value=''>请选择</option>";
	   			for(var i=0;i<data.length; i++){
	   				if(data[i].parent_id == 0){
	   					re += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
	   				}
	   			}
	   			$("#province").html(re);
	   	    }
	   	});
	}
	
	function initCity(provinceId){
		$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:{
	   			provinceId : provinceId
	   		},
	   		url:"<%=request.getContextPath()%>/login/initCity.action",
	   		success:function(data){
	   			var re = '';
	   			for(var i=0;i<data.length; i++){
	   				re += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
	   			}
	   			$("#city").html(re);
	   	    }
	   	});
	}
	
	//更换验证码图片
	function refresh() {   
	 //IE存在缓存,需要new Date()实现更换路径的作用   
	 document.getElementById("image").src="../createimage.jsp?"+new Date();   
	} 
	
	/**
	 * 表单验证
	 */
	function initValidate() {
		$('#addForm').validator({
			theme : 'yellow_right',
			rules : {
				checkDomain:function(element,param,field){
					var str;
					$.ajax({
						type:"POST",
						async:false,  //默认true,异步
						data:{domain:element.value},
						url:"<%=request.getContextPath()%>/login/selectManageCompanyList.action",
						success:function(data){
							var flag = true;
							if(data.length > 0){
								flag = false;
							}
							str =  flag || "已存在相同域名";
						}
					});
					return str;
				},
				checkCode:function(element,param,field){
					var str;
					$.ajax({
						type:"POST",
						async:false,  //默认true,异步
						data:{code:element.value},
						url:"<%=request.getContextPath()%>/login/selectManageCompanyList.action",
						success:function(data){
							var flag = true;
							if(data.length > 0){
								flag = false;
							}
							str =  flag || "已存在相同代码";
						}
					});
					return str;
				},
				checkSamePhone:function(element,param,field){
					var str;
					$.ajax({
						type:"POST",
						async:false,  //默认true,异步
						data:{phoneNum:element.value},
						url:"<%=request.getContextPath()%>/login/selectManageCompanyList.action",
						success:function(data){
							var flag = true;
							if(data.length > 0){
								flag = false;
							}
							str =  flag || "该手机号码已注册";
						}
					});
					return str;
				},
				checkSameEmail:function(element,param,field){
					var str;
					$.ajax({
						type:"POST",
						async:false,  //默认true,异步
						data:{email:element.value},
						url:"<%=request.getContextPath()%>/login/selectManageCompanyList.action",
						success:function(data){
							var flag = true;
							if(data.length > 0){
								flag = false;
							}
							str =  flag || "该邮箱已注册";
						}
					});
					return str;
				},
				checkPostCode:[ /^[1-9][0-9]{5}$/, '邮政编码格式不正确！' ],
				checkMobile:[ /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/, '电话号码格式不正确！' ],
				checkPhone:[/^1[358]\d{9}$/,'电话号码格式不正确！'],
				checkDomainName:[/^[0-9a-zA-Z]*$/,'域名只能输入数字、字母！'],
				checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ]
			},
			msgStyle:"margin-top:10px;margin-left:10px;",
			fields : {
				/* code : {
					rule : "required;length[~30];checkCode",
					msg : {
						required : "企业大学代码不能为空",
						length : "长度需小于等于30个字符"
					}
				}, */
				name : {
					rule : "required;length[~30]",
					msg : {
						required : "企业大学名称不能为空",
						length : "长度需小于等于30个字符"
					}
				},
				domain : {
					rule : "required;length[~30];checkDomain;checkDomainName",
					msg : {
						required : "域名不能为空",
						length : "长度需小于等于30个字符"
					}
				},
				industry : {
					rule : "required",
					msg : {
						required : "行业分类不能为空"
					}
				},
				province : {
					rule : "required",
					msg : {
						required : "省不能为空"
					}
				},
				city : {
					rule : "required",
					msg : {
						required : "市不能为空"
					}
				},
				address : {
					rule : "required;length[~30]",
					msg : {
						required : "详细地址不能为空",
						length : "长度需小于等于30个字符"
					}
				},
				email : {
					rule : "required;email;checkSameEmail",
					msg : {
						required : "邮箱不能为空",
					}
				},
				postCode : {
					rule : "required;length[6];checkPostCode",
					msg : {
						required : "邮编不能为空",
						length : "长度必须等于6个字符"
					}
				},
				attention : {
					rule : "required;length[~30]",
					msg : {
						required : "联系人不能为空",
						length : "长度需小于等于30个字符"
					}
				},
				attentionPhone : {
					rule : "required;checkMobile",
					msg : {
						required : "联系电话不能为空",
						length : "长度需等于11个字符"
					}
				},
				phoneNum : {
					rule : "required;length[11];checkPhone;checkSamePhone",
					msg : {
						required : "手机号码不能为空",
						length : "长度需等于11个字符"
					}
				},
				activeCode : {
					rule : "required;length[6]",
					msg : {
						required : "激活码不能为空",
						length : "长度需等于6个字符"
						
					},
					msgClass:"n-top"
				}
			}
		});
	}
</script>
</head>
<body>
<div id="content" class="content">
	<div class="content_head" style="height:56px;">
    	<a href="login.html"><img width="210px" height="56px" src="<%=request.getContextPath() %>/images/img/logo_2_new.png" title="安培在线" /></a>
        <p>注册企业会员</p>
    </div>
    <div class="content_option">
    	<div class="">
        	<ul>
            	<li class="first_node">
                	<p>1</p>
                	<strong>注册</strong>
                </li>
                <li class="mid_node">
                	<p>2</p>
                	<strong>审批</strong>
                </li>
                <li class="last_node">
                	<p>3</p>
                	<strong>完成</strong>
                    </li>
            </ul>
           	<div class="connect_1">
            </div>
            <div class="connect_2">
            </div>
        </div>
    </div>
   	<div class="content_sign clear">
   		<form id="addForm">
            <div class="lesson_add">
            	<div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>企业大学名称：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" name="name" id="name"/>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>我的域名：</em>
		            </div>
		            <div class="add_fr">
		            	http://<input type="text" name="domain" id="domain"/>.anpeiwang.com
		            </div>
		        </div>
		        <!-- <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>企业代码：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="code" name="code" />
		            </div>
		        </div> -->
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>详细地址：</em>
		            </div>
		            <div class="add_fr">
		            	<select id="province" style="width:100px;" name="province">
	                    </select>
	                    <select id="city" style="width:100px;" name="city">
	                    </select>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	&nbsp;
		            </div>
		            <div class="add_fr">
		            	 <textarea id="address" name="address"></textarea>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>邮政编码：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" id="postCode" name="postCode"/>
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>行业分类：</em>
		            </div>
		            <div class="add_fr">
		            	<select id="industry" style="width:100px;" name="industry">
                		</select>
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>企业规模：</em>
		            </div>
		            <div class="add_fr">
		            	<select id="proportion" style="width:100px;" name="proportion">
		                	<option value="1">100人以下</option>
		                	<option value="2">100-999人</option>
		                	<option value="3">1000-9999人</option>
		                	<option value="4">10000人以上</option>
		                </select>
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>邮箱：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" id="email" name="email"/>
		            </div>
		        </div>
		         <%--  <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>验证码：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" id="validateCode"/>
		            	<img style="width: 68px;height: 34px;vertical-align:middle;cursor:pointer;" id="image" border="0" alt="" onclick="refresh()" src="../createimage.jsp" title="<s:text name='frame.changeImage'/>"/>
		            </div>
		        </div> --%>
		         <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>邮箱激活码：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" id="activeCode" name="activeCode"/>
                		<input type="button" value="获取激活码" style="width: 68px;height: 34px;background: #D20001;border: none;color: white;" onclick="recieveActiveCode()"/>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>业务联系人：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" id="attention" name="attention"/>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>联系电话：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" id="attentionPhone" name="attentionPhone"/><span style="margin-left: 20px;color:#717171;">XXXX-XXXXXXXX</span>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>手机号码：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" id="phoneNum" name="phoneNum"/>
		            </div>
		        </div>
               <div class="add_gr">
		        	<div class="add_fl">
		                <em>公司网址：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" id="website" name="website"/>
		            </div>
		        </div>
		        
		        
	             <div class="button_cz">
		        	 <input type="button" class="btn_2" value="提交" onclick="save()"/>
		        	 <input type="button" class="btn_2" value="取消" onclick="cancel()"/>
	        	</div>
        	</div>
         </form>
    </div>
</div>
</body>
</html>
