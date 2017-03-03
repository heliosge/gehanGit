<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增计划</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.btn_3 {width: 68px;height: 25px;background: #D20001;border: none;color: white;float: right;}
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
	.lesson_add .add_gr { height:50px;}
</style>

<script type="text/javascript">

	function cancel(){
		//window.location.href="<%=request.getContextPath()%>/train/trainPlanList.action";
		history.back(-1);
	}

</script>
</head>
<body>

<div id="content" class="content">
	<!-- <h3>培训计划详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">培训计划详情</span>
	</div>
            <div class="lesson_add">
            	<div class="add_gr" >
		        	<div class="add_fl">
		          	 <c:choose>
		              	<c:when test="${plan.timeType==1 }">
			                <em>培训年度：</em>
			            </c:when>
			            <c:otherwise>
			                <em>培训月度：</em>
		              	</c:otherwise>
	              	</c:choose>
		            </div>
		            <div class="add_fr">
		            	${plan.timeValue }
		            </div>
		        </div>
				        
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训名称：</em>
		            </div>
		            <div class="add_fr">
		            	${plan.name }
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训学时：</em>
		            </div>
		            <div class="add_fr">
		            	${plan.period }
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训形式：</em>
		            </div>
		            <div class="add_fr">
		                <c:choose>
		                	<c:when test="${plan.trainType=='1' }">
		                		在线培训
		                	</c:when>
		                	<c:when test="${plan.trainType=='2' }">
		                		面授培训
		                	</c:when>
		                	<c:when test="${plan.trainType=='1,2' }">
		                		在线培训; 面授培训
		                	</c:when>
		                	<c:otherwise>
		                	</c:otherwise>
		                </c:choose>
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训讲师：</em>
		            </div>
		            <div class="add_fr">
		            	${plan.teacherName }
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训机构：</em>
		            </div>
		            <div class="add_fr">
		            	${plan.agency }
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训对象：</em>
		            </div>
		            <div class="add_fr">
		            	${plan.target }
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训简介：</em>
		            </div>
		            <div class="add_fr">
		            	${plan.descr }
		            </div>
		        </div>
	             <div class="button_cz">
		        	 <input type="button" class="btn_2" value="返回" onclick="cancel()"/>
	        	</div>
        	</div>
    </div>
</body>
</html>
