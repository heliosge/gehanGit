<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改学员</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>


<script src="<%=request.getContextPath()%>/js/webhr.js"></script>

<style type="text/css">
	.td {text-align: center;padding-left: 16px;height: 36px;border: 1px solid#EAEAEA;padding-right: 16px;font-size:14px;}
</style>

<script type="text/javascript">
var user = ${user};

$(function(){
	//getAllManageRole();
	getCompanyRole();
	fillUserInfo();
});

function fillUserInfo(){
	$.each($("input[name='sex']"), function(index, val){
		if($(val).val() == user.sex){
			val.checked = true;
		}
	});
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
	window.location.href="<%=request.getContextPath()%>/manageUser/toPulianUserListPage.action";
}

</script>

</head>
<body>

<div class="content">
	<!-- <h3>学员详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">学员详情</span>
	</div>
    <div class="content_2">
    	<div class="lesson_add" >
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
		            <div class="add_fr">
		            	<c:choose>
		                	<c:when test="${user.sex==1 }">
		                		男
		                	</c:when>
		                	<c:otherwise>
		                		女
		                	</c:otherwise>
		                </c:choose>
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
		                 <em>联系电话：</em>
		            </div>
		            <div class="add_fr">
		            	${user.mobile }
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
		                 &nbsp;
		            </div>
		            <div class="add_fr">
		            	<div class="btn_gr">
					    	<input type="button" class="btn_1" value="返回" onclick="cancel()"/>
					    </div>
		            </div>
		        </div>
	       
		</div>
            
   </div>
   </div>
</body>
</html>
