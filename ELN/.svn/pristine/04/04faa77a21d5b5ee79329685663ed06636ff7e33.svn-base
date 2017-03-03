<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增计划</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.btn_3 {width: 68px;height: 25px;background: #D20001;border: none;color: white;float: right;}
	.button{ 
		width: 70px; height: 30px; background: #d60500; color: #fff; font-family: '微软雅黑'; text-align: center; border: none; 
	}
</style>

<script type="text/javascript">

var checkList = ${checkList};

$(function(){
	$("#status").append("${check.status}"==1?'待审批；':("${check.status}"==2?'审批通过；':("${check.status}"==3?'审批拒绝；':'审批取消；')));
	if(checkList.length > 1){
		$.each(checkList,function(index,val){
			if(index == checkList.length-1){
				$("#status").append(val.checkUserName+"【"+(val.status==1?'待审批':(val.status==2?'审批通过':("${check.status}"==3?'审批拒绝；':'审批取消')))+"】");
			}else{
				$("#status").append(val.checkUserName+"【"+(val.status==1?'待审批':(val.status==2?'审批通过':("${check.status}"==3?'审批拒绝；':'审批取消')))+"】→");
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
	
	initGrid();
	
	initReason();
})

	function cancel(){
		history.back(-1);
	}
	
	function initGrid(){
		//表格
		var grid = $('#exampleTable').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:"<%=request.getContextPath()%>/train/selectTrainArrangeUserList.action",
	    	width: $('#exampleTable').parent().width(),
	    	height: '450px',
	    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
	    	showBackboard: false,
	        //multiSelect: true,
	        //checkCol: true,
	        indexCol: true,  //索引列
	        params: function(){
	        	return  toParam();
		    },
	     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
	 			   {title: '用户名', name: 'userName', width:60, align:'center'},
	               {title: '姓名', name: 'name', width:60, align:'center'},
	               {title: '部门', name: 'deptName', width:60, align:'center'},
	 			   {title: '岗位', name: 'postName', width:60, align:'center'}
	           ]
	    });
		
		grid.on("loadSuccess",function(e, data){
			stuGridRows = $('#exampleTable').mmGrid().rows();
			});
	}
	
	function toParam(){
		var o = {};
		o.arrangeId = ${check.arrange.id};
		o.name = $("#name").val();
		o.deptName = $("#deptName").val();
		o.postName = $("#postName").val();
		o.passStatus = 2;
		return o;
	}
	
	var stuGridRows = [];

	function search(){
		var rows = $('#exampleTable').mmGrid().rows();
		$('#exampleTable').mmGrid().load(stuGridRows);
		rows = $('#exampleTable').mmGrid().rows();
		var userName = $("#userName").val();
		var name = $("#name").val();
		var postName = $("#postName").val();
		var deptName = $("#deptName").val();
		for(var index = rows.length-1; index>=0; index--){
			var item = rows[index];
			if(userName){
				if(item.userName == null || item.userName.indexOf(userName) == -1){
					$('#exampleTable').mmGrid().removeRow(index);
					continue;
				}
			}
			if(name){
				if(item.name == null || item.name.indexOf(name) == -1){
					$('#exampleTable').mmGrid().removeRow(index);
					continue;
				}
			}
			if(postName){
				if(item.postName == null || item.postName.indexOf(postName) == -1){
					$('#exampleTable').mmGrid().removeRow(index);
					continue;
				}
			}
			if(deptName){
				if(item.deptName == null || item.deptName.indexOf(deptName) == -1){
					$('#exampleTable').mmGrid().removeRow(index);
					continue;
				}
			}
		}
	}
	
	function viewStage(obj,status){
		if(status == 1){
			$(obj).attr("onclick","viewStage(this,2)");
			$(obj).parent().next(".lesson_add_2").hide();
		}else{
			$(obj).attr("onclick","viewStage(this,1)");
			$(obj).parent().next(".lesson_add_2").show();
		}
		
	}
	
	function checkArrangeDialog(id){
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
		            	  checkArrange(id);
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
	
	function checkArrange(id){
		var o = {};
		o.ids = [];
		o.ids.push(id);
		o.status = $("input[name='status']:checked").val();
		if(o.status == 3){
			if($("#reason").val() == '其他'){
				o.refuseReason = $("#refuseReason").val()==''?'其他':$("#refuseReason").val();
			}else{
				o.refuseReason = $("#reason").val();
			}
		}
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:o,
			url:"<%=request.getContextPath()%>/train/checkTrainArrange.action",
			success:function(data){
				if(data == 'SUCCESS'){
					dialog.alert("审批成功。",function(){history.back(-1);});
				}else{
					dialog.alert("审批失败。");
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
	<!-- <h3>培训安排审批详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">培训安排审批详情</span>
	</div>
            <div class="add_fr" id="status">
            	状态：
            </div>
            <div style="margin-top:10px;">
            	<c:choose>
              	<c:when test="${check.status==1 }">
	               	 <input type="button" value="审批" class="button" onclick="checkArrangeDialog(${check.id})"/>
	            </c:when>
	            <c:otherwise>
	               	<input type="button" value="审批" class="button" style="background: #999;"/>
              	</c:otherwise>
       			</c:choose>
            </div>
            <div class="tool_1" id="stage_div_1" style="width: 1044px;">
	            <span id="stage_title_1" style="padding-left:5px;width:200px"> 基本信息</span>
	            <a href="javascript:void(0)" style="background: rgb(0, 133, 254) none repeat scroll 0 0;height: 46px;line-height: 46px;margin-top: 0;text-align: center;width: 42px;" onclick="viewStage(this,1)" class="a_ss">
	            	<img style="margin-top: 18px;" src="<%=request.getContextPath()%>/images/img/ico_down.png">
	            </a>
            </div>
		    <div class="lesson_add_2" style="width:1044px;">
            	<div class="add_gr" >
		        	<div class="add_fl">
		          	 	<em>计划开课：</em>
		            </div>
		            <div class="add_fr">
		            	<c:choose>
		                	<c:when test="${check.arrange.timeType==1 }">
		                		年度
		                	</c:when>
		                	<c:otherwise>
		                		月度
		                	</c:otherwise>
		                </c:choose>
		            </div>
		        </div>
				        
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训名称：</em>
		            </div>
		            <div class="add_fr">
		            	${check.arrange.name }
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训时间：</em>
		            </div>
		            <div class="add_fr">
		            	${check.arrange.beginTime } 至 ${check.arrange.endTime }
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>是否允许报名：</em>
		            </div>
		            <div class="add_fr">
		                <c:choose>
		                	<c:when test="${check.arrange.isJoin==1 }">
		                		是
		                	</c:when>
		                	<c:otherwise>
		                		否
		                	</c:otherwise>
		                </c:choose>
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		                <em>允许人数：</em>
		            </div>
		            <div class="add_fr">
		            	${check.arrange.maxJoinNum }
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		                <em>开放人群：</em>
		            </div>
		            <div class="add_fr">
		            <c:choose>
		                	<c:when test="${arrange.openCrowd==null || fn:length(arrange.openCrowd) == 0}">
		                		所有人
		                	</c:when>
		                	<c:otherwise>
		                		<c:forEach var="item" items="${arrange.openCrowd }" varStatus="status">
				            		${item.deptName };&nbsp;
				            	</c:forEach>
		                	</c:otherwise>
		                </c:choose>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>是否为推荐培训：</em>
		            </div>
		            <div class="add_fr">
		            	 <c:choose>
		                	<c:when test="${check.arrange.isRecommend==1 }">
		                		是
		                	</c:when>
		                	<c:otherwise>
		                		否
		                	</c:otherwise>
		                </c:choose>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>适合人群：</em>
		            </div>
		            <div class="add_fr">
		            	${check.arrange.fitCrowd }
		            </div>
		        </div>
		         <div class="add_gr" style="height:auto;">
		        	<div class="add_fl">
		                <em>培训简介：</em>
		            </div>
		            <div class="add_fr">
		            	${check.arrange.descr }
		            </div>
		        </div>
		         <div class="add_gr" style="height:auto;">
		        	<div class="add_fl">
		                <em>封面：</em>
		            </div>
		            <div class="add_fr">
		            	<img id="previewPath" src="${check.arrange.cover }" width="330px" height="220px"/>
		            </div>
		        </div>
		       </div>
		       
             <div class="tool_1" id="stage_div_1" style="width: 1044px;">
	            <span id="stage_title_1" style="padding-left:5px;width:200px">培训内容</span>
	            <a href="javascript:void(0)" style="background: rgb(0, 133, 254) none repeat scroll 0 0;height: 46px;line-height: 46px;margin-top: 0;text-align: center;width: 42px;" onclick="viewStage(this,1)" class="a_ss">
	            	<img style="margin-top: 18px;" src="<%=request.getContextPath()%>/images/img/ico_down.png">
	            </a>
            </div>
		    	<div class="lesson_add_2" style="width:1044px;">
	        	<c:forEach var="item" items="${contents }" varStatus="status">
		        	 <div class="add_gr">
			        	<div class="add_fl">
			                <em>培训内容：</em>
			            </div>
			             <div class="add_fr">
			            	${item.content }
			            </div>
			    	</div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>培训讲师：</em>
			            </div>
			             <div class="add_fr">
			            	${item.teacherName }
			            </div>
		            </div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>开课时间：</em>
			            </div>
			             <div class="add_fr">
			            	${item.beginTime } 至 ${item.endTime } 
			            </div>
		            </div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>签到时间：</em>
			            </div>
			             <div class="add_fr">
			            	${item.signBeginTime } 至 ${item.signEndTime } 
			            </div>
		            </div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>培训学时：</em>
			            </div>
			             <div class="add_fr">
			            	${item.period }
			            </div>
		            </div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>培训学分：</em>
			            </div>
			             <div class="add_fr">
			            	${item.score }
			            </div>
		            </div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>培训形式：</em>
			            </div>
			             <div class="add_fr">
			            	<c:choose>
			                	<c:when test="${item.trainType==1 }">
			                		在线培训
			                	</c:when>
			                	<c:otherwise>
			                		面授培训
			                	</c:otherwise>
		                	</c:choose>
			            </div>
		            </div>
		             <div class="add_gr">
			            <div class="add_fl">
			                <em>课程：</em>
			            </div>
			             <div class="add_fr">
			            	${item.courseName }
			            </div>
		            </div>
		            <c:choose>
	                	<c:when test="${item.trainType==1 }">
	                		
	                	</c:when>
	                	<c:otherwise>
	                		 <div class="add_gr">
					            <div class="add_fl">
					                <em>培训地点：</em>
					            </div>
					             <div class="add_fr">
					            	${item.place }
					            </div>
				            </div>
				            <div class="add_gr">
					            <div class="add_fl">
					                <em>培训通过成绩：</em>
					            </div>
					             <div class="add_fr">
					            	${item.passPercent }
					            </div>
				            </div>
				            <div class="add_gr">
					            <div class="add_fl">
					                <em>课后评估：</em>
					            </div>
					             <div class="add_fr">
					            	${item.afterClassTestName }
					            </div>
				            </div>
				             <div class="add_gr">
					            <div class="add_fl">
					                <em>课前预习：</em>
					            </div>
					             <div class="add_fr">
					            	文件：${item.beforeClassFileName }
					            </div>
				            </div>
				            <div class="add_gr">
					            <div class="add_fl">
					                &nbsp;
					            </div>
					             <div class="add_fr">
					            	课程：${item.beforeClassCourseName }
					            </div>
				            </div>
	                	</c:otherwise>
                	</c:choose>
	        	</c:forEach>
	        	</div>
	        	
	        	 <div class="tool_1" id="stage_div_1" style="width: 1044px;">
		            <span id="stage_title_1" style="padding-left:5px;width:200px">培训人员</span>
		            <a href="javascript:void(0)" style="background: rgb(0, 133, 254) none repeat scroll 0 0;height: 46px;line-height: 46px;margin-top: 0;text-align: center;width: 42px;" onclick="viewStage(this,1)" class="a_ss">
		            	<img style="margin-top: 18px;" src="<%=request.getContextPath()%>/images/img/ico_down.png">
		            </a>
            	</div>
            	<div class="lesson_add_2" style="width:1044px;padding-top: 0px;padding-bottom:0px;">
		        	<div class="search_3" style="width:1044px;">
				        	<p>	
				        		用户名：<input type="text" id="userName"/>
				            	姓名：<input type="text" id="name"/>
				                                                岗位：<input type="text" id="postName"/>
				            	部门：<input type="text" id="deptName"/>
				        	</p>
				        	<input type="button" value="查询" class="btn_cx" onclick="search();"/>
				
				    </div>
				   	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
			   	</div>
        		</div>
	             <div class="button_cz">
		        	 <input type="button" class="btn_2" value="返回" onclick="cancel()"/>
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
