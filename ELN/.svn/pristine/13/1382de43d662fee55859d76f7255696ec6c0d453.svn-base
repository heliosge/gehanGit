<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增用户</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<script src="<%=request.getContextPath()%>/js/webhr.js"></script>

<style type="text/css">
	.td {text-align: center;padding-left: 16px;height: 36px;border: 1px solid#EAEAEA;padding-right: 16px;font-size:14px;}
	.content_sign span { margin-left: 0px;}
</style>

<script type="text/javascript">

$(function(){
	getAllManageRole();
	initValidate();
});

function getAllManageRole(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageRole/getCompanyRole.action",
		success:function(data){
			var re = '';
			for(var i=0;i<data.length;i++){
				re += '<input type="checkbox" name="role" style="width:14px;height:13px;" value="'+data[i].id+'"/>'+data[i].name;
			}
			$("#role").append(re);
		}
	})
}


function save(){
	$('#addForm').isValid(function(v) {
		if(v){
			var param = {};
			param.userName = $("#userName").val();
			param.name = $("#name").val();
			$.each($("input[name='sex']"), function(index, val){
				if(val.checked){
					param.sex = $(val).val();
				}
			});
			param.email = $("#email").val();
			param.mobile = $("#mobile").val();
			var roleIds = [];
			$.each($("input[name='role']"), function(index, val){
				if(val.checked){
					roleIds.push($(val).val());
				}
			});
			if(roleIds.length == 0){
				dialog.alert("请至少选中一个角色。");
				return;
			}
			param.roleIds = roleIds;
			
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:param,
				url:"<%=request.getContextPath()%>/manageUser/insertPulianUser.action",
				success:function(data){
					if(data == 'SUCCESS'){
						dialog.alert("新增成功！",function(){cancel();});
					}else{
						dialog.alert("新增失败。");
					}
				}
			});
		}else{
			//dialog.alert("表单验证不通过");
		}
	});
}

function cancel(){
	window.location.href="<%=request.getContextPath()%>/manageUser/toPulianUserListPage.action";
}

/**
 * 表单验证
 */
function initValidate() {
	$('#addForm').validator({
		theme : 'yellow_right',
		rules : {
			checkUserName:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{userName:element.value},
					url:"<%=request.getContextPath()%>/manageUser/getAllManageUser.action",
					success:function(data){
						var flag = true;
						if(data.length > 0){
							flag = false;
						}
						str =  flag || "已存在相同用户名";
					}
				});
				return str;
			},
			checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ],
			checkPhone:[/^1[358]\d{9}$/,'电话号码格式不正确！']
		},
		msgStyle:"margin-top:10px;",
		fields : {
			name : {
				rule : "required;length[~30]",
				msg : {
					required : "姓名不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			userName : {
				rule : "required;length[6~30];checkUserName",
				msg : {
					required : "用户名不能为空",
					length : "长度需在6~30个字符"
				}
			},
			mobile : {
				rule : "checkPhone"
			},
			email : {
				rule : "email"
			}
		}
	});
}


</script>

</head>
<body>

<div class="content">
	<!-- <h3>新增用户</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增用户</span>
	</div>
    <div class="content_2">
        <div class="content_option" >
        	<form id="addForm">
			<div class="content_sign clear">
	            <p class="a_name"><span>*</span>用户名：&nbsp;&nbsp;<input type="text" id="userName" name="userName"/></p>
	            <p class="a_name"><span>*</span>姓名：&nbsp;&nbsp;&nbsp;<input type="text" id="name"  name="name"/></p>
	            <p class="a_name"><span>*</span>性别：&nbsp;&nbsp;&nbsp;&nbsp;
	            <input type="radio" name="sex" style="width:14px;height:13px;" value="1" checked/>男&nbsp;&nbsp;&nbsp;&nbsp;
	            <input type="radio" name="sex" style="width:14px;height:13px;" value="2"/>女</p>
	            <p class="a_name"><span>&nbsp;</span>电子邮箱：<input type="text" id="email"  name="email"/></p>
	            <p class="a_name"><span>&nbsp;</span>联系电话：<input type="text" id="mobile" name="mobile" /></p>
	            <p class="a_name" id="role"><span>*</span>角色：&nbsp;&nbsp;&nbsp;&nbsp;</p>
            </div>
		</form>          
      </div>
       <div class="btn_gr">
    	<input type="button" class="btn_2" value="保存" onclick="save()"/>
    	<input type="button" class="btn_1" value="返回" onclick="cancel()"/>
    </div>
            
   </div>
   </div>
</body>
</html>
