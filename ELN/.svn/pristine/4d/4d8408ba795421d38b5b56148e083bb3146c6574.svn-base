<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	  <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>审批管理</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/jquery-validation/jquery.validate.js" type="text/javascript"></script>

<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>

<!-- 弹出框 -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css"/>

<script type="text/javascript">

</script>
</head>

<body style="overflow-x:hidden;">

<div class="content" style="padding-bottom: 0px;">
	<!-- <h3>审批配置</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">审批配置</span>
	 </div>
	<c:choose>
		<c:when test="${isPuLian=='false'}">
			<c:choose>
			<c:when test="${isCompany=='true'}">
			 <div class="lesson_tab_1">
	        	<ul>
	            	<li class="li_this" wayType="1">课程</li>
	                <li class="" wayType="2">知识</li>
	                <li class="" wayType="3">专题</li>
	            </ul>
			</div>
		    <div class="sp" style="display: block;">
		    	<p>
		        	<span>是否需要审批：</span>
		            <input type="radio" checked="checked" name="isApprove"  style="cursor: pointer">
		            <em>是</em>
		            <input type="radio" name="isApprove"  style="cursor: pointer">
		            <em>否</em>
		            <input type="hidden" id="approve">
		        </p>
		        <h5>审批流程设置</h5>
		        <div class="lesson_add_2" style="border:none;">
		    	<!-- <div class="add_gr">
		        	<div class="add_fl">
		                <em>审批流程建议：</em>
		            </div>
		            <div class="add_fr">
		            </div>
		        </div> -->
		        <div class="add_gr" style="height:auto">
		        	<div class="add_fl">
		                <em>审批流程设置：</em>
		            </div>
		            <div class="add_fr" >
		                <div class="ApproveSteps">
		                	<ul class="ApproveSteps">
		                		
		                	</ul>
		                </div>
		            	<input type="button" value="新增" onclick="addApproveSetp()">
		                <select name="approverType" id="approverType">
		                	<option value="1">个人</option>
		 	                <option value="3">岗位</option>
		 	                <option value="4">角色</option>
		                	
		                </select>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>以下节点继承该流程：</em>
		            </div>
		            <div class="add_fr">
		            	<div class="node">
		                	<ul class="subcompany">
		                    	<h4><input type="checkbox" class="allCheck">全选</h4>
		                    	<script type='text/javascript'>
			                    	 var list=	${companyArray};
			                    	 for(var i=0;i<list.length;i++){
										  var obj = list[i];
										  document.write("<li><input type='checkbox' value="+obj.id+">"+obj.name+"</li>");
			                    	 }
		                    	</script>
		                        <!-- 
		                        <li><input type="checkbox">公司名称3</li>
		                        <li><input type="checkbox">公司名称4</li> -->
		                    </ul>
		                </div>
		            </div>
		        </div>
		        <div class="button_cz"  style='margin: 200px 0 0 200px;'>
		        	<input type="button" value="保存" id='ctlBtn' onclick="saveApprove()" >
				</div>
		     </div>
		    </div>
		    </c:when>
		    <c:otherwise>
		    	<h1 style='margin: 40px;'>子公司不需要设置审批流程</h1>
		    </c:otherwise>
		    </c:choose>
		</c:when>
		<c:otherwise>
			<h1 style='margin: 40px;'>普联不需要设置审批流程</h1>
		</c:otherwise>
	</c:choose>
	
   
</div>
<script  type='text/javascript'>
$.isAllChecked = function(_$){
	for(var i=0;i<_$.length;i++){
		if(!_$.eq(i).is(":checked")){
			return false;
		}
	}
		return true;
}
	var approveObj = ${reList}||{};
	initData()
	
	//添加审核步骤
	function addApproveSetp(){
		
		var approverType = $("#approverType").val();
		
		switch (approverType) {
		case "1"://用户
			getUserList();
			break;
		case "2"://直属领导
			
			if($("ul.ApproveSteps").find("li.leader").length>0){
				dialog.alert("领导已经设置过，不允许重复！");
			}else{
				$("ul.ApproveSteps").append("<li class='leader' approverType='2' showName='直属领导'>领导——<label>直属领导</label><span class='close'>X</span></li>")
			}
			break;
		case "3"://岗位
			getPostList();
			break;
		case "4"://岗位
			getRoleList();
			break;
		default:
			break;
		}
	}
	


	function getUserList(){
	
		var d=dialog({
	        url:"<%=request.getContextPath()%>/approve/listUser.action",
	        title:"人员列表" ,
			height: 530,
			width: 750,
			button: [ { value: '确定', 
				callback: function () {
					var iframe = this.iframeNode.contentWindow;
					iframe.slUserInfo();
					return false;
					//$(window.parent.document).contents().find("#"+dialog.jiframe[0].id)[0].contentWindow.slUserInfo();
					//$("#"+dialog.jiframe[0].id).contentWindow().slUserInfo()//.contents().slUserInfo()//.contentWindow().slUserInfo(); 
				  //$(window.parent.document).contents().find("#iframename")[0].contentWindow.iframefunction();
				}},
				{ value: '取消', 
					callback: function () {
					}}
				]
		});
		d.showModal();
	}
	
	//设置岗位信息
	function getPostList(){
		
		
		var d=dialog({
				title:"岗位列表",
	    		url:"<%=request.getContextPath()%>/approve/getPostList.action",
				width:"auto",
				height: 500,
				width: 350,
				button: [ { value: '确定', 
					callback: function () {
						/* var id = $("#"+dialog.jiframe[0].id).contents().find("#post").val();
						var name = $("#"+dialog.jiframe[0].id).contents().find("#post").attr("showName") */
						var iframe = this.iframeNode.contentWindow;
						var id = iframe.post.value;
						var name = $(iframe.post).attr("showName");
						if(-1==id){
							$(iframe.errTit).html("<label class='error' style='color:red'>请选择岗位！</label>");
							return false;
						}else{
							if($("ul.ApproveSteps").find("li.post[objId='"+id+"']").length>0){
							}else{
								$("ul.ApproveSteps").append("<li class='post' objId="+id+" approverType='3' showName='"+name+"'><label>岗位——"+name+"</label><span class='close'>X</span></li>");
							}
						}	
						
					}},
					{ value: '取消', 
						callback: function () {
						}}
					]
		})
		d.showModal();
	}
	
	function getRoleList() {
		var d=dialog({
			 title:"角色列表",
			 content:"<div id='roleList'><ul class='list'></ul></div>",
			width:"auto",
			height:"auto",
			okValue: '确定',
		    cancelValue: '取消',
			},function(){
				if($("div#roleList").find("input[name='roleStep']:checked").length>0){
					var liobj = $("div#roleList").find("input[name='roleStep']:checked").closest("li");
					var id=liobj.attr("objId");
					var name=liobj.attr("showName");
					if($("ul.ApproveSteps").find("li.role[objId='"+id+"']").length>0){
					}else{
						$("ul.ApproveSteps").append("<li class='role' objId="+id+" approverType='4' showName='"+name+"'><label>角色——"+name+"</label><span class='close'>X</span></li>");
					}
				}else{
					dialog.alert("请选择数据");
					return false;
				}
			})
			$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
    		url:"<%=request.getContextPath()%>/approve/getUserRoleList.action",
    		success:function(data){
    			var param = data;
    			var liHtml="";
    			for(var i=0;i<param.length;i++){
    				
    				liHtml+="<li objId='"+param[i].id+"' showName='"+param[i].name+"'><input type='radio' name='roleStep'><label>"+param[i].name+"</label></li>"
    			}		
		    			
    			$("div#roleList").find("ul").html(liHtml);
				d.showModal();
    	    }
    	});
			
			
	}
	
	//添加用户信息到审核步骤中
	function addUserInfo(id,name){
		
		if($("ul.ApproveSteps").find("li.user[objId='"+id+"']").length>0){
			return;
		}else{
			$("ul.ApproveSteps").append("<li class='user' objId="+id+" approverType='1' showName='"+name+"'><label>个人——"+name+"</label><span class='close'>X</span></li>")
		}
	}
	
	//进行数据的校验
	function validate(){
		
		var wayType = $("li.li_this").attr("wayType");
		var paramObj = approveObj[wayType]
		var isApprove =paramObj["isApprove"]
		if(isApprove == 1){
			if(paramObj.listStep.length==0){
				dialog.alert("审批流程不允许为空！")
				return false;
			}
			
			if(paramObj.deptList.length==0){
				dialog.alert("应用部门不允许为空！")
				return false;
			}
		}
		return true;
		
	}
	//保存信息
	function saveApprove(){
		
		jzData()//加载数据。
		if(validate()){
			var wayType = $("li.li_this").attr("wayType");
			var paramObj = approveObj[wayType]
			$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		contentType:"application/json; charset=utf-8",
	    		data:JSON.stringify(paramObj),
	    		url:"<%=request.getContextPath()%>/approve/saveApproveInfo.action",
	    		success:function(data){
	    			if("SUCCESS"==data){
	    				dialog.alert("保存成功");
	    			}else{
	    				dialog.alert("保存失败");
	    			}
	    	    }
	    	});
		}
	}
	
	
	//加载数据到页面json
	function jzData(){
		
		var paramObj ={};
		var wayType = $("li.li_this").attr("wayType");
		paramObj["wayType"]=wayType;
		if($("input[name='isApprove']").eq(0).is(":checked")){
			paramObj["isApprove"]=1;
		}else{
			paramObj["isApprove"]=0;
		}
		//加载审核流程信息
		var listStep = [];
		var steps = $("ul.ApproveSteps").find("li");
		for(var  i=0;i<steps.length;i++){
			var obj ={};
			obj.orderNum = i;
			obj.approverType=steps.eq(i).attr("approverType");
			obj.showName =steps.eq(i).attr("showName");
			obj.approverId=steps.eq(i).attr("objId");
			listStep.push(obj);
		}
		paramObj["listStep"]=listStep;
		
		//加载应用子公司信息
		var deptList = [];
		var nodes =$("ul.subcompany").find("li input[type='checkbox']"); 
		 for(var i=0;i<nodes.length;i++){
			if(nodes.eq(i).is(":checked")){
				 deptList.push(nodes.eq(i).val())
			}
		 }
		
		 paramObj["deptList"]=deptList;
		 
		 //加载到页面全局变量中去
		 approveObj[wayType]=paramObj;
	}
	
	//将json中的数据还原到页面表单中
	function initData(){
		
		var wayType = $("li.li_this").attr("wayType");
		var paramObj = approveObj[wayType]||{};
		
		var isApprove =paramObj["isApprove"];//设置是否需要审核
		if(isApprove== null ||isApprove==1){
			$("input[name='isApprove']").eq(0).each(function(){
			    this.checked=true;
		    });
		}else{
			$("input[name='isApprove']").eq(1).each(function(){
			    this.checked=true;
		    });
		}
		
		//加载审核流程信息
		var listStep = paramObj["listStep"]||[];
		var html="";
		var nameObj ={"1":"个人","2":"领导","3":"岗位","4":"角色"};
		var classObj ={"1":"user","2":"leader","3":"post","4":"role"};
		for(var  i=0;i<listStep.length;i++){
			var lobj = listStep[i];
			
			var approverType =lobj["approverType"];
			var approverId = lobj["approverId"];
			var showName = lobj["showName"];
			html+="<li class='"+classObj[approverType]+"' objId="+approverId+" approverType='"+approverType+"' showName='"+showName+"'><label>"+nameObj[approverType]+"——"+showName+"</label><span class='close'>X</span></li>"
			
		}
		$("ul.ApproveSteps").html(html);
		
		//加载应用子公司信息
		var deptList =  paramObj["deptList"]||[];
		 $("ul.subcompany").find("input[type='checkbox']").each(function(){
			    this.checked=false;
		    });; 
		 for(var i=0;i<deptList.length;i++){
			 var value = deptList[i]
			 $("ul.subcompany").find("li input[type='checkbox'][value='"+value+"']").each(function(){
				    this.checked=true;
			    });; 
		 }
		 //设置当所有的checkbox都勾选时。全选勾选
		 if($.isAllChecked($("ul.subcompany").find("li input[type='checkbox']"))){
				$("ul.subcompany").find("input.allCheck").each(function(){
				    this.checked=true;
			    });
			}else{
				$("ul.subcompany").find("input.allCheck").each(function(){
				    this.checked=false;
			    });
			}
	}
	
	//绑定click事件
	$("div.lesson_tab_1").find("ul li").on("click",function(){
		//点击切换时，需要进行以下事情
		//1、先将原始数据进行加载保存
		jzData()
		
		//2、进行标签的切换
		$(this).siblings().removeClass("li_this");
		$(this).addClass("li_this");
		
		//3、将数据进行还原到页面
		initData()
	})
	
	$("ul.subcompany").find("input.allCheck").on("click",function(){

		if($(this).is(":checked")){
			$("ul.subcompany").find("li input[type='checkbox']").each(function(){
			    this.checked=true;
			    });
		}else{
			$("ul.subcompany").find("li input[type='checkbox']").each(function(){
			    this.checked=false;
		    });
		}
	})
	$("ul.subcompany").find("li input[type='checkbox']").on("click",function(){
		if($.isAllChecked($(this).add($(this).siblings()))){
			$("ul.subcompany").find("input.allCheck").each(function(){
			    this.checked=true;
		    });
		}else{
			$("ul.subcompany").find("input.allCheck").each(function(){
			    this.checked=false;
		    });
		}
	})
	//绑定删除事件
	$("ul.ApproveSteps").delegate("span.close","click",function(){ 
	
		$(this).closest("li").remove();
	});
	
	</script>
</body>
</html>
