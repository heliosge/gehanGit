<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>增加用户</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!-- validation -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-validation/jquery.validate.js"></script>

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

var manageUser = ${manageUser};
var departmentList = ${departmentList}; //部门

$(function(){
	
	$(".re_password").hide();
	
	//部门
	$.each(departmentList, function(index, data){
		$("#departmentId").append("<option value='"+data.id+"'>"+data.name+"</option>");
	});
   	
   	if(manageUser){
   		//修改
   		$(".top_page_name").text("修改用户");
   		
   		//回填值
   		$("#userId").val(manageUser.userId);
   		//$("#password").val(manageUser.password);
   		//隐藏password文本框，改为重置密码
   		$("#password").hide();
   		$("#password").next().hide();
   		$("#password").parent().prev().find("span").text("重置密码：");
   		$(".re_password").show();
   		
   		$("#name").val(manageUser.name);
   		$("#departmentId").val(manageUser.departmentId);
   		$("#position").val(manageUser.position);
   		$(":radio[name='workStatus'][value='"+manageUser.workStatus+"']").attr("checked", "checked");
   		$("#phone").val(manageUser.phone);
   		$(":radio[name='gender'][value='"+manageUser.gender+"']").attr("checked", "checked");
   		$("#email").val(manageUser.email);
   		$(":radio[name='status'][value='"+manageUser.status+"']").attr("checked", "checked");
   		$("#address").val(manageUser.address);
   		$("#memo").val(manageUser.memo);
   		$("#workPhone").val(manageUser.workPhone);
   		$(":radio[name='internal'][value='"+manageUser.internal+"']").attr("checked", "checked");
   		
   	}else{
   		
   	}
	
	//返回按钮
	$("#backBtu").click(function(){
		$.ligerDialog.confirm('确认返回吗？', function(yes){ 
			if(yes){
		    	//window.location.href = "<%=request.getContextPath()%>/manageUser/userList.action";
		    	parent.$("#add_iframe iframe").attr("src", "");
		    	parent.$("div").first().show();
		    	parent.$("#add_iframe").hide();
		    	//parent.search();
		    }
		});
	});
	
	//验证数字长度
	$.validator.addMethod(
	    "mobilephone",
	    function (value, element, param){
	    	var length = value.length; 
	    	var mobile = /^[0-9]{11}$/;
	    	return this.optional(element) || (length == param && mobile.test(value)); 
	    },
	    "请输入正确的11位手机号码"
    );
	
	//验证工作号码
	$.validator.addMethod(
	    "workNumber",
	    function (value, element, param){
	    	//var length = value.length; 
	    	var mobile = /^((\d{3,5})-)(\d{6,10})(-(\d{1,5}))?$/;
	    	return this.optional(element) || mobile.test(value); 
	    },
	    "请输入正确的工作号码"
    );
	
	//验证id只包含数字字母
	$.validator.addMethod(
	    "withOutChinese",
	    function (value, element, param){
	    	var withOutChinese = /^[\w@.-]+$/;
	    	return this.optional(element) || withOutChinese.test(value); 
	    },
	    "请输入字母和数字的组合(可以有@_.-)"
    );
	
	//验证姓名(中文，数字，字母)
	$.validator.addMethod(
	    "onlyName",
	    function (value, element, param){
	    	var withOutChinese = /^([\u4E00-\u9FA5]|\w)*[^_]$/;
	    	return this.optional(element) || withOutChinese.test(value); 
	    },
	    "请输入正确的姓名"
    );
	
	//开始验证     
	$('form').validate({
		debug:true,
	    /**//* 设置验证规则 */    
	    rules: {     
	    	userId: {     
	            required:true,
	            rangelength:[2, 20],
	            //withOutChinese: true,
	            remote:{  
	                 url:"<%=request.getContextPath()%>/manageUser/checkUserId.action",  
	                 type:"post",  
	                 dataType:"text",  
	                 data:{userId:function(){
	                	 return $.trim($("#userId").val());
	                 }},  
	                 dataFilter: function(data, type) {  
	                 	if(parseInt(data) > 0){
	                 		if(manageUser){
	                 			if($.trim($("#userId").val()) == manageUser.userId){
	                 				return true;
	                 			}
	                 		}
	                 		return false;
	                 	}else{
	                 		return true;
	                 	}
	                 }  
	              }
	        },
	        name: {     
	            required:true,
	            rangelength:[2, 20],
	            onlyName:true
	        },
	        position: {     
	            required:true,
	            maxlength:10,
	            onlyName:true
	        },
	        phone: {
	        	required:true,
	        	mobilephone:11
	        },
	        email: {
	        	//required:true,
	        	email:true,
	        	withOutChinese: true,
	        	maxlength:100
	        },
	        address: {
	        	required:true,
	        	maxlength:50
	        },
	        password: {
	        	required:true,
	        	rangelength:[6, 20]
	        },
	        workPhone: {
	        	required:true,
	        	workNumber:true
	        },
	        memo: {
	        	maxlength:100
	        }
	    },      
	    /**//* 设置错误信息 */    
	    messages: {     
	        userId: {         
	        	required:"账号不可为空",
	        	rangelength:"账号必须在2-20个字符之间",
	        	remote:"账号已经存在"
	        },
	        name: {         
	        	required:"姓名不可为空",
	        	rangelength:"姓名必须在2-20个字符之间",
	        	onlyName:"请输入正确的姓名(中文,数字,字母)"
	        },
	        position: {     
	            required:"职位不可为空",
	            maxlength:"最多10个字符",
	            onlyName:"请输入正确的职位(中文,数字,字母)"
	        },
	        phone: {
	        	required:"移动电话号码不可为空",
	        	mobilephone:"请输入正确的手机号码"
	        },
	        email: {
	        	//required:"邮箱不可为空",
	        	email:"请输入正确的邮箱",
	        	maxlength:"最多100个字符"
	        },
	        address: {
	        	required:"地址不可为空",
	        	maxlength:"最多50个字符"
	        },
	        password: {
	        	required:"密码不可为空",
	        	rangelength:"密码必须在6-20个字符之间"
	        },
	        workPhone: {
	        	required:"工作电话不可为空",
	        	workNumber:"请输入正确的工作号码"
	        },
	        memo: {
	        	maxlength:"最多100个字符"
	        }
	    },      
	    /**//* 设置验证触发事件 */
	    onsubmit:true,
	    /**//* 设置错误信息提示DOM */    
	    errorPlacement: function(error, element) {   
	    	$(element[0]).parent().next().find(".validation_span").text(error.html()).show();  
	    },
	    success:function(error, element){
	    	$(element[0]).parent().next().find(".validation_span").text("");  
        },
	    submitHandler: function (){
	    	//alert("sunmit");
	    	save();
	    }
	}); 
});

function beforeSave(){
	$("form").submit();
}

//保存
function save(){
	
	var param = new Object();
	
	param.userId = $.trim($("#userId").val());
	param.password = $.trim($("#password").val());
	param.name = $.trim($("#name").val());
	param.departmentId = $("#departmentId").val();
	param.position = $.trim($("#position").val());
	param.workStatus = $(":radio[name='workStatus']:checked").val();
	param.phone = $.trim($("#phone").val());
	param.gender = $(":radio[name='gender']:checked").val();
	param.email = $.trim($("#email").val());
	param.status = $(":radio[name='status']:checked").val();
	param.address = $.trim($("#address").val());
	param.memo = $.trim($("#memo").val());
	param.workPhone = $.trim($("#workPhone").val());
	param.internal = $(":radio[name='internal']:checked").val();
	
	var urlStr = "<%=request.getContextPath()%>/manageUser/addManageUser.action";
	
	if(manageUser){
		//修改
		urlStr = "<%=request.getContextPath()%>/manageUser/updateManageUser.action";
		param.id = manageUser.id
		
		if($("#rePassword").val() == "1"){
			//重置密码
			param.password = "123456";
		}
	}
	
	$.ligerDialog.confirm('确认保存吗？', function(yes){ 
		if(yes){
			$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:param,
	    		url:urlStr,
	    		success:function(data){
	    			if(data == "SUCCESS"){
	    				
	    				$.ligerDialog.success('保存成功！', '提示', function(){
	    					window.location.href = "<%=request.getContextPath()%>/manageUser/userList.action";
	    				});
	    				
	    			}else{
	    				
	    				$.ligerDialog.warn('保存失败！', '提示', function(){
	    					window.location.href = "<%=request.getContextPath()%>/manageUser/userList.action";
	    				});
	    			}
	    			
	    			parent.search();
	    			parent.$("#add_iframe iframe").attr("src", "");
			    	parent.$("div").first().show();
			    	parent.$("#add_iframe").hide();
			    	
	    	    }
	    	});
	    }
	});
}

</script>
</head>
<body style="">

	<div class="top_page_name">增加用户</div>

	<div class="first_div" style="border-right:none;border-bottom:none;border-left:none;">
		
		<div class="search_div">
			
			<form>
			<table class="param_table" cellpadding="0" cellspacing="0">
				<tr>
					<td style="width:100px;" align="right">
						<span>账号：</span>
					</td>
					<td style="width:185px;">
						<input id="userId" type="text" name="userId" class="input_0"/>
						<span class="need_span">*</span>
					</td>
					<td style="width:180px;" align="left">
						<span class="validation_span"></span>
					</td>
					<td style="width:100px;" align="right">
						<span>密码：</span>
					</td>
					<td style="width:250px;">
						<input id="password" type="password" name="password" class="input_0" />
						<span class="need_span">*</span>
						
						<input class="re_password" type="radio" id="rePassword" name="rePassword" value="1" class="input_0"  ><span class="re_password">是</span>
						<span class="re_password" style="margin-left:10px;" ></span>
						<input class="re_password" type="radio" id="rePassword" name="rePassword" value="0" checked="checked" class="input_0" /><span class="re_password">否</span>
						<span class="re_password" style="margin-left:10px;" >(默认密码：123456)</span>
					</td>
					<td style="width:180px;" align="left">
						<span class="validation_span"></span>
					</td>
				</tr>
				<tr>
					<td style="width:100px;" align="right">
						<span>姓名：</span>
					</td>
					<td style="">
						<input id="name" type="text" name="name" class="input_0" />
						<span class="need_span">*</span>
					</td>
					<td>
						<span class="validation_span"></span>
					</td>
					<td style="width:100px;" align="right">
						 <span>所属部门：</span>
					</td>
					<td style="">
						<select id="departmentId" name="departmentId" style="width:200px;" class="input_0">
							
						</select>
					</td>
					<td>
						
					</td>
				</tr>
				<tr style="display:none;">
					<td style="width:100px;" align="right">
						<span>职位：</span>
					</td>
					<td style="">
						<input id="position" type="text" name="position" class="input_0" value="无" />
						<span class="need_span">*</span>
					</td>
					<td>
						<span class="validation_span"></span>
					</td>
					<td style="width:100px;" align="right">
						<span>工作状态：</span>
					</td>
					<td style="">
						<input type="radio" id="workStatus" name="workStatus" value="0" checked="checked"/> 在职
						<span style="margin-left:30px;"></span>
						<input type="radio" id="workStatus" name="workStatus" value="1" /> 离职
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td style="width:100px;" align="right">
						<span>移动电话：</span>
					</td>
					<td style="">
						<input id="phone" type="text" name="phone" class="input_0" />
						<span class="need_span">*</span>
					</td>
					<td>
						<span class="validation_span"></span>
					</td>
					<td style="width:100px;" align="right">
						<span>性别：</span>
					</td>
					<td style="">
						<input type="radio" id="gender" name="gender" value="0" checked="checked" /> 男
						<span style="margin-left:44px;"></span>
						<input type="radio" id="gender" name="gender" value="1" /> 女
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td style="width:100px;" align="right">
						<span>邮箱：</span>
					</td>
					<td style="">
						<input id="email" type="text" name="email" class="input_0" />
						<span class="need_span">*</span>
					</td>
					<td>
						<span class="validation_span"></span>
					</td>
					<td style="width:100px;display:none;" align="right">
						<span>是否锁定：</span>
					</td>
					<td style="display:none;">
						<input type="radio" id="status" name="status" value="0" checked="checked"/> 未锁定
						<span style="margin-left:18px;"></span>
						<input type="radio" id="status" name="status" value="1" /> 锁定
					</td>
					<td style="display:none;">
						
					</td>
				</tr>
				<tr style="display: none;">
					<td style="width:100px;" align="right">
						<span>地址：</span>
					</td>
					<td style="">
						<input id="address" type="text" name="address" class="input_0" value="无" />
						<span class="need_span">*</span>
					</td>
					<td>
						<span class="validation_span"></span>
					</td>
					<td style="width:100px;" align="right">
						<span>是否内部人员：</span>
					</td>
					<td style="">
						<input type="radio" id="internal" name="internal" value="0" checked="checked"/> 内部人员
						<span style="margin-left:6px;"></span>
						<input type="radio" id="internal" name="internal" value="1" /> 非内部人员
					</td>
					<td>
						
					</td>
				</tr>
				<tr style="display:none;">
					<td style="width:100px;" align="right">
						<span>工作电话：</span>
					</td>
					<td style="">
						<input id="workPhone" type="text" name="workPhone" class="input_0" value="123456"/>
						<span class="need_span">*</span>
					</td>
					<td>
						<span class="validation_span"></span>
					</td>
				</tr>
				<tr>
					<td style="width:100px;" align="right">
						<span>备注：</span>
					</td>
					<td colspan="4">
						<textarea id="memo" name="memo" rows="5" cols="87" style="border:1px solid #DDDDDD;"></textarea>
					</td>
					<td>
						<span class="validation_span"></span>
					</td>
				</tr>
			</table>
			</form>
		</div>
					
	</div>
	
	<div align="right" style="margin-right:100px;">
		<button class="btu_0" onclick="beforeSave()">保存</button>
		<button class="btu_0" id="backBtu">返回</button>
	</div>

</body>
</html>
