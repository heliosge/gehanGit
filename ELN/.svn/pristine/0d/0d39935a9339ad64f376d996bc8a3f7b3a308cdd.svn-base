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
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exhide-3.5.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/checkIDCard.js"></script>

<style type="text/css">
</style>

<script type="text/javascript">
var user = ${user};

$(function(){
	getCompanyRole();
	initExpandField();
	fillUserInfo();
});

var fields ;
function initExpandField(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageParam/queryCompanyParamList.action",
		success:function(data){
			fields = data;
			var re = '';
			for(var i=0;i<data.length;i++){
				re += '<div class="add_gr"><div class="add_fl"><em>'+data[i].propertyName+
				'：</em></div><div class="add_fr"><span id="field'+data[i].originalFiled+'"/></span></div></div>';
			}
			$("#content").append(re);
		}
	})
}

function fillUserInfo(){
	$("#sex").html(user.sex==1?'男':'女');
	$("#isManager").html(user.isManager==1?'是':'否');
	$("#birthDay").html(user.birthDay);
	//填充扩展字段值
	for(var i=1;i<=100;i++){
		$("#field"+i).html(user["field"+i]);
	}
}
function getCompanyRole(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageRole/getCompanyRole.action",
		success:function(data){
			$.each(data,function(index,val){
				$.each(user.roleList,function(index1,val1){
					if(val.id == val1.id){
						$("#role").append(val.name+'；&nbsp;');
					}
				})
			})
		}
	})
}


function cancel(){
	window.location.href="<%=request.getContextPath()%>/manageUser/toStudentListPage.action";
}

</script>

</head>
<body>

<div class="content">
	<!-- <h3>学员信息</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">学员信息</span>
	</div>
	<form id="addForm">
	<div class="lesson_add" id="content">
        <div class="add_gr">
        	<div class="add_fl">
                <em>用户名：</em>
            </div>
             <div class="add_fr">
            	${user.userName }
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>姓名：</em>
            </div>
             <div class="add_fr">
            	${user.name }
            </div>
    	</div>
    	  <div class="add_gr">
        	<div class="add_fl">
                <em>性别：</em>
            </div>
             <div class="add_fr" id="sex">
            	
            </div>
    	</div>
    	  <div class="add_gr">
        	<div class="add_fl">
                <em>出生日期：</em>
            </div>
             <div class="add_fr">
            	${user.birthDay }
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
                <em>身份证号：</em>
            </div>
             <div class="add_fr">
            	${user.idCard }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em>地址：</em>
            </div>
             <div class="add_fr">
            	${user.address }
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
                <em>部门：</em>
            </div>
             <div class="add_fr">
             	${user.deptName }
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
                <em>岗位：</em>
            </div>
             <div class="add_fr">
             	${user.postName }
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
                <em>手机号码：</em>
            </div>
             <div class="add_fr">
             	${user.mobile }
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
                <em>电子邮箱：</em>
            </div>
             <div class="add_fr">
             	${user.email }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em>是否管理员：</em>
            </div>
             <div class="add_fr" id="isManager">
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
                <em>角色：</em>
            </div>
             <div class="add_fr" id="role">
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em>录入时间：</em>
            </div>
             <div class="add_fr">
             	${user.createTime }
            </div>
    	</div>
    	 <div style="position: absolute;right: 200px;">
    	 	<img src="${user.photo }" width="90px" height="90px"/>
    	</div>
        </div>
          </form>
      </div>
   
      <div id="deptDialog" style="display: none;">
       <ul id="treePage" class="ztree"></ul>
     </div>
     
    <div id="postDialog" style="display: none;">
       <ul id="postTreePage" class="ztree"></ul>
     </div>
</body>
</html>
