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
	.button{ 
		width: 70px; height: 30px; background: #d60500; color: #fff; font-family: '微软雅黑'; text-align: center; border: none; 
	}
</style>

<script type="text/javascript">
	var checkList = ${checkList};
	
	$(function(){
		$("#status").append("${check.status}"==1?'待审批；':("${check.status}"==2?'审批通过；':'审批拒绝；'));
		if(checkList.length > 1){
			$.each(checkList,function(index,val){
				if(index == checkList.length-1){
					$("#status").append(val.checkUserName+"【"+(val.status==1?'待审批':(val.status==2?'审批通过':'审批拒绝'))+"】");
				}else{
					$("#status").append(val.checkUserName+"【"+(val.status==1?'待审批':(val.status==2?'审批通过':'审批拒绝'))+"】→");
				}
			})
		}
		
		$("input[name='status']").click(function(){
			if($(this).val()==2){
				$("#reason").attr("disabled","disabled");
				$("#refuseReason").attr("disabled","disabled");
			}else{
				$("#reason").removeAttr("disabled");
				if($("#reason").val()=='其他'){
					$("#refuseReason").removeAttr("disabled");
				}
			}
		})
		
		$("#reason").change(function(){
			if($(this).val() == '其他'){
				$("#refuseReason").removeAttr("disabled");
			}else{
				$("#refuseReason").attr("disabled","disabled");
			}
		})
		
		initReason();
	})

	function cancel(){
		history.back(-1);
		//window.location.href="<%=request.getContextPath()%>/train/trainPlanList.action";
	}
	
	function checkPlanDialog(id){
		//初始化审批窗口
		$("#reason").val('其他');
		$("#refuseReason").val('');
		$($("input[name='status']")[1]).click();
		//弹出窗口
		dialog({
			width : 500,
			height : 300,
			title : '审批',
			content : $("#checkDialog"),
			fixed:true,
			 button: [
			          {
		              value: '确定',
		              callback: function () {
		            	  checkPlan(id);
		            	  this.close();
		              }
			          },
			          {
			              value: '取消',
			              callback: function () {
			            	  this.close();
			              }
				          }
			      ]
		}).showModal();
	}
	
	function checkPlan(id){
		var o = {};
		o.ids = [];
		o.ids.push(id);
		o.status = $("input[name='status']:checked").val();
		if($("#reason").val() == '其他'){
			o.refuseReason = $("#refuseReason").val()==''?'其他':$("#refuseReason").val();
		}else{
			o.refuseReason = $("#reason").val();
		}
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:o,
			url:"<%=request.getContextPath()%>/train/checkTrainPlan.action",
			success:function(data){
				if(data == 'SUCCESS'){
					dialog.alert("操作成功。",function(){ cancel();});
				}else{
					dialog.alert("操作失败。");
				}
			}
		});
	}
	
	function initReason(){
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			url:"<%=request.getContextPath()%>/train/selectTrainConfig.action",
			success:function(data){
				if(data != null){
					if(data.checkReason){
						var html = '<option value="其他">其他</option>';
						$.each(data.checkReason.split("@"),function(index,val){
							html += '<option value="'+val+'">'+val+'</option>';
						})
						$("#reason").html(html);
					}
				}
			}
		});
	}

</script>
</head>
<body>

<div id="content" class="content">
	<!-- <h3>培训计划审批详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">培训计划审批详情</span>
	</div>
		<div class="lesson_add">
       		<div class="add_gr" >
	        	<div class="add_fl">
	          	 <c:choose>
	              	<c:when test="${check.status==1 }">
		               	 待审批：
		            </c:when>
		            <c:when test="${check.status==2 }">
		               	 审批通过：
		            </c:when>
		            <c:otherwise>
		               	审批拒绝：
	              	</c:otherwise>
	       		</c:choose>
	            </div>
	            <div class="add_fr">
	            	${USER_BEAN.name }
	            </div>
	        </div>
	        <div class="add_gr" >
	        	<div class="add_fl">
	          	 	&nbsp;
	            </div>
	            <div class="add_fr">
	            	<c:choose>
	              	<c:when test="${check.status==1 }">
		               	 <input type="button" value="审批" class="button" onclick="checkPlanDialog(${check.id})"/>
		            </c:when>
		            <c:otherwise>
		               	<input type="button" value="审批" class="button" style="background: #999;"/>
	              	</c:otherwise>
	       		</c:choose>
	            </div>
	        </div>
       		<div class="add_gr" >
	        	<div class="add_fl">
	          	 <c:choose>
	              	<c:when test="${check.plan.timeType==1 }">
		                <em>培训年度：</em>
		            </c:when>
		            <c:otherwise>
		                <em>培训月度：</em>
	              	</c:otherwise>
	           		</c:choose>
	            </div>
	            <div class="add_fr">
	            	${check.plan.timeValue }
	            </div>
	        </div>
	        <div class="add_gr" >
	        	<div class="add_fl">
	          		 创建人：
	            </div>
	            <div class="add_fr">
	            	${check.plan.createUserName }
	            </div>
	        </div>
	        <div class="add_gr"  style="border-bottom: 2px solid #cccccc;">
	        	<div class="add_fl">
	          		 状态：
	            </div>
	            <div class="add_fr" id="status">
	            </div>
	        </div>
		</div>
          <div class="lesson_add">
	          	<div class="add_gr" >
	        	<div class="add_fl">
	          	 <c:choose>
	              	<c:when test="${check.plan.timeType==1 }">
		                <em>培训年度：</em>
		            </c:when>
		            <c:otherwise>
		                <em>培训月度：</em>
	              	</c:otherwise>
	           		</c:choose>
	            </div>
	            <div class="add_fr">
	            	${check.plan.timeValue }
	            </div>
	        </div>
			        
	        <div class="add_gr">
	        	<div class="add_fl">
	                <em>培训名称：</em>
	            </div>
	            <div class="add_fr">
	            	${check.plan.name }
	            </div>
	        </div>
	        <div class="add_gr">
	        	<div class="add_fl">
	                <em>培训学时：</em>
	            </div>
	            <div class="add_fr">
	            	${check.plan.period }
	            </div>
	        </div>
	        <div class="add_gr">
	        	<div class="add_fl">
	                <em>培训形式：</em>
	            </div>
	            <div class="add_fr">
	                <c:choose>
	                	<c:when test="${check.plan.trainType==1 }">
	                		在线培训
	                	</c:when>
	                	<c:otherwise>
	                		线下培训
	                	</c:otherwise>
	                </c:choose>
	            </div>
	        </div>
	         <div class="add_gr">
	        	<div class="add_fl">
	                <em>培训讲师：</em>
	            </div>
	            <div class="add_fr">
	            	${check.plan.teacherName }
	            </div>
	        </div>
	         <div class="add_gr">
	        	<div class="add_fl">
	                <em>培训机构：</em>
	            </div>
	            <div class="add_fr">
	            	${check.plan.agency }
	            </div>
	        </div>
	         <div class="add_gr">
	        	<div class="add_fl">
	                <em>培训对象：</em>
	            </div>
	            <div class="add_fr">
	            	${check.plan.target }
	            </div>
	        </div>
	        <div class="add_gr">
	        	<div class="add_fl">
	                <em>培训简介：</em>
	            </div>
	            <div class="add_fr">
	            	${check.plan.descr }
	            </div>
	        </div>
	            <div class="button_cz">
	        	 <input type="button" class="btn_2" value="返回" onclick="cancel()"/>
	       	</div>
      	</div>
    </div>
    
    <div id="checkDialog" style="display:none;">
		 <div class="lesson_add" style="width:450px;">
		 	<div class="add_gr">
	        	<div class="add_fl" style="width:100px;">
	                <em>是否通过：</em>
	            </div>
	            <div class="add_fr" style="width:350px;">
	            	<input type="radio" name="status" value="2"/>是
            		<input type="radio" name="status" value="3" checked/>否
	            </div>
	        </div>
	        <div class="add_gr">
	        	<div class="add_fl" style="width:100px;">
	                <em>拒绝原因：</em>
	            </div>
	            <div class="add_fr" style="width:350px;">
	            	<select id="reason"></select>
	            </div>
          	</div>
          	<div class="add_gr">
	        	<div class="add_fl" style="width:100px;">
	                &nbsp;
	            </div>
	            <div class="add_fr" style="width:350px;">
	            	<textarea id="refuseReason" ></textarea>
	            </div>
          	</div>
		 </div>
	</div>
</body>
</html>
