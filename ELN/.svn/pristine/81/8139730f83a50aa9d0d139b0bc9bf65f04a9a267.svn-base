<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>培训计划</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
</head>

<style>
	.ul_know{ width:100%;height:38px;margin-bottom:10px;}
	.ul_know ul{ width:100%; height:38px; float:left; border-bottom:1px solid #cccccc;}
	.ul_know ul li{ width:106px; height:36px; line-height:36px; text-align:center; float:left; margin-right:-1px; cursor: pointer;}
	.ul_know ul .li_a{ color:#0087d3; border-bottom:1px solid #fff;; border-top:2px solid #0087d3; border-left:1px solid #cccccc;border-right:1px solid #cccccc; font-weight:bold;}
	.content .btn_gr {padding-top: 48px;}
	.mt{margin-top:10px;}
	.title{border-bottom: 2px solid #cccccc;height:20px;float:left;width:100%;}
	h4{border-bottom: 2px solid black;width:130px;height:20px;}
	.lesson_add .add_gr .add_fl{ text-align: left; }
	.button{ 
		width: 70px; height: 30px; background: #d60500; color: #fff; font-family: '微软雅黑'; text-align: center; border: none; margin-left: 10px;
	}
	.input{width: 315px; height: 30px; margin-top: 10px;}
	a { right: 400px;float: right;width: 25px;overflow: hidden;height: 20px;position: relative;margin-top: 12px;margin-bottom: -1px;}
	a .img_1 { position: absolute; left: -56px; top: 0;}
	a .img_2 { position: absolute; left: -28px; top: 0;}
	a .img_3 { position: absolute; left: 0x; top: 0;}
</style>

<script>
	$(function(){
		fillConfigInfo();
		
		$("textarea").change(function(){
			$("textarea").val($(this).val());
		})
	})
	
	function fillConfigInfo(){
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			url:"<%=request.getContextPath()%>/train/selectTrainConfig.action",
			success:function(data){
				if(data != null){
					$("textarea").val(data.checkAdvice);
					$("#joinEndTime").val(data.joinEndTime);
					$.each($("input[name='isQuery']"),function(index,val){
						if($(val).val() == data.isQueryJoinDetail){
							val.checked = true;
						}
					})
					$.each($("input[name='isCheck']"),function(index,val){
						if($(val).val() == data.isCheck){
							val.checked = true;
						}
					})
					if(data.checkReason){
						$.each(data.checkReason.split("@"),function(index,val){
							addReason(val);
						})
					}
					if(data.checkUserName){
						$.each(data.checkUserName.split(","),function(index,val){
							addCheckUser(val);
						})
						$.each(data.checkUserId.split(","),function(index,val){
							$("#checkUserId_"+(index+1)).val(val);
						})
					}
				}
			}
		});
	}
	
	function addReason(val){
		$("#reasonDiv").append('<div><input name="reason" class="input" value="'+(val==undefined?'':val)+'"/><input type="button" value="删除" onclick="deleThis(this)" class="button"/></div>');
	}
	
	function deleThis(obj){
		$(obj).parent().remove();
	}
	
	function deleThisUser(obj){
		$(obj).parent().parent().remove();
	}
	
	function up(obj){
		if($(obj).parent().parent().prev().attr("id") != 'checkUserDiv'){
			$(obj).parent().parent().prev().before($(obj).parent().parent());
		}
	}
	
	function down(obj){
		$(obj).parent().parent().next().after($(obj).parent().parent());
	}
	
	function tabClick(obj, val){
		$(obj).attr('class', 'li_a').siblings().removeClass("li_a");
		$(".lesson_add").hide();
		$("#tab"+val).show();
	}
	
	var i = 0;
	function addCheckUser(val){
		i++;
		var html = '<a href="javascript:void(0)" onclick="up(this)"><img src="/ELN/images/img/ico_tool_1.png" class="img_1"></a>';
		html += '<a href="javascript:void(0)" onclick="down(this)"><img src="/ELN/images/img/ico_tool_1.png" class="img_2"></a>';
		html += '<a href="javascript:void(0)" onclick="deleThisUser(this)"><img src="/ELN/images/img/ico_tool_1.png" class="img_3"></a>';
		$("#tab2 .add_gr").last().after('<div class="add_gr" style="height:50px;"><div class="add_fl">&nbsp;</div><div class="add_fr"><input type="text" name = "checkUserName" onclick="chooseCheckUser(this)" id="checkUserName_'+i+'" value="'+(val==undefined?'':val)+'"/><input type="hidden" name="checkUserId" id="checkUserId_'+i+'"/>'+html+'</div></div>');
	}
	
	var artDialog;
	var inputId = 0;
	function chooseCheckUser(obj){
		inputId = $(obj).attr("id").split("_")[1];
		artDialog = dialog({
		 	height: 500,
			width: 1100,
			title : '请选择审批人',
			url:"<%=request.getContextPath()%>/train/toChooseCheckUser.action",
			lock:true
		}).showModal();
	}
	
	function save(){
		var o = {};
		o.isCheck = $("input[name='isCheck']:checked").val();
		o.checkAdvice = $("#checkAdvice").val();
		o.joinEndTime = $("#joinEndTime").val();
		if(isNaN(o.joinEndTime)){
			dialog.alert("报名截止时间必须是数字");
			return;
		}
		o.isQueryJoinDetail = $("input[name='isQuery']:checked").val();
		var reasons = [];
		$.each($("input[name='reason']"),function(index,val){
			reasons.push($(val).val());
		})
		o.checkReason = reasons.join("@");
		var checkUserId = [];
		$.each($("input[name='checkUserId']"),function(index,val){
			checkUserId.push($(val).val());
		})
		o.checkUserId = checkUserId.join(",");
		var checkUserName = [];
		$.each($("input[name='checkUserName']"),function(index,val){
			checkUserName.push($(val).val());
		})
		o.checkUserName = checkUserName.join(",");
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:o,
			url:"<%=request.getContextPath()%>/train/saveTrainConfig.action",
			success:function(data){
				if(data == "SUCCESS"){
					dialog.alert("保存成功。");
				}else{
					dialog.alert("保存成功。");
				}
			}
		});
	}
	
</script>

<body>
	<div class="content">
		<!-- <h3>培训配置</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">培训配置</span>
		</div>
		<div class="ul_know">
				<ul id="ul_exam">
					<li class="li_a" onclick="tabClick(this, 1)">培训配置</li>
					<li onclick="tabClick(this, 2)">培训审批流程</li>
				</ul>
		</div>
		<div class="lesson_add" id="tab1">
			<div class="title">
				<h4>培训审批</h4>
			</div>
           	<div class="add_gr">
	        	<div class="add_fl">
	                <em>培训是否需要审批：</em>
	            </div>
	            <div class="add_fr">
	            	<input type="radio" name="isCheck" value="1"/>是
            		<input type="radio" name="isCheck" value="2" checked/>否
	            </div>
	        </div>
	        <div class="add_gr" style="margin-top:10px;">
	        	<div class="add_fl">
	                <em>审批流程建议：</em>
	            </div>
	            <div class="add_fr">
	            	<textarea id="checkAdvice"  name="checkAdvice"></textarea>
	            </div>
        	</div>
        	<div class="title">
        		<h4>培训报名截止事件</h4>
        	</div>
        	<div class="add_gr">
	        	<div class="add_fl">
	                <em>培训开始前：</em>
	            </div>
	            <div class="add_fr">
	            	<input type="text"  id="joinEndTime" name="joinEndTime" style="width:100px;"/>小时
	            </div>
	        </div>
	        <div class="title">
	        	<h4>是否可查看报名明细</h4>
	        </div>
        	<div class="add_gr">
	        	<div class="add_fl">
	                <em>是否可查看报名明细：</em>
	            </div>
	            <div class="add_fr">
	            	<input type="radio" name="isQuery" value="1"/>是
            		<input type="radio" name="isQuery" value="2" checked/>否
	            </div>
	        </div>
	        <div id="reasonDiv">
		        <div class="title">
		        	<h4>培训模板</h4>
	        	</div>
	        	<div class="add_gr">
		        	<div class="add_fl">
		               	 审批理由模板：<input type="button" value="新增" onclick="addReason()" class="button"/>
		            </div>
		            <div class="add_fr">
		            </div>
	           </div>
	        </div>
        	<!-- <div class="add_gr" style="margin-top:10px;">
	        	<input type="button" class="button" value="提交" style="margin-left:0px;" onclick="save()"/>
        	</div> -->
	    </div>
	    
	    <div class="lesson_add" id="tab2" style="display:none;">
	    	<div class="title">
				<h4>培训审批（部门专员）</h4>
			</div>
	    	 <div class="add_gr" style="margin-top:10px;">
	        	<div class="add_fl">
	                <em>审批流程建议：</em>
	            </div>
	            <div class="add_fr">
	            	<textarea id="checkAdvice2"  name="checkAdvice"></textarea>
	            </div>
        	</div>
        	<div class="add_gr" id="checkUserDiv">
	        	<div class="add_fl">
	                <em>审批流程设置：</em>
	            </div>
	            <div class="add_fr">
	            	<input type="button" class="button" value="新增" style="margin-left:0px;" onclick="addCheckUser()"/>
	            </div>
        	</div>
	    </div>
	    <div class="add_gr" style="margin-top:10px;">
	        	<input type="button" class="button" value="提交" style="margin-left:0px;" onclick="save()"/>
        	</div>
	    
	</div>
</body>
