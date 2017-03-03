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
	.ul_know{ width:1046px;  float:left;}
	.ul_know ul{ width:1046px; height:38px; float:left; border-bottom:1px solid #cccccc;}
	.ul_know ul li{ width:106px; height:36px; line-height:36px; text-align:center; float:left; margin-right:-1px; cursor: pointer;}
	.ul_know ul .li_a{ color:#0087d3; border-bottom:1px solid #fff;; border-top:2px solid #0087d3; border-left:1px solid #cccccc;border-right:1px solid #cccccc; font-weight:bold;}
	.content .btn_gr {padding-top: 48px;}
</style>

<script>

var time = ${time};
	$(function(){
		
		initGrid();
		
	})
	
	function initGrid(){
		//表格
		$('#exampleTable').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:"<%=request.getContextPath()%>/train/selectTrainArrangeUserManageList.action",
	    	width: $('#exampleTable').width(),
	    	height: 'auto',
	    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
	    	showBackboard: false,
	        //multiSelect: true,
	        //checkCol: true,
	        indexCol: true,  //索引列
	        params: function(){
		    	return  param();
		    },
	     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
	               {title: '培训名称', name: 'name', width:80, align:'center', renderer:function(val, item, rowIndex){
	 				  return '<a href="toTrainArrangeUserDetail.action?arrangeId='+item.id+'" > '+val+' </a>';
				   }},
	 			   {title: '培训状态', name: 'beginTimeDate', width:120, align:'center', renderer:function(val, item, rowIndex){
	 				  return val>time?'未开始':((val < time && item.endTimeDate >time)?"进行中":"已结束");
				   }},
	 			   {title: '允许名额/已报名', name: 'maxJoinNum', width:30, align:'center', renderer:function(val, item, rowIndex){
	 				  return val+"/"+item.joinedNum;
				   }},
	 			   {title: '报名率', name: 'maxJoinNum', width:30, align:'center', renderer:function(val, item, rowIndex){
	 				  return (item.joinedNum/val*100).toFixed(2);
				   }},
				   {title: '报名状态', name: 'isClose', width:60, align:'center', renderer:function(val, item, rowIndex){
					   return val==1?'已关闭':'已开启';
				   }},
				   {title: '操作', name: 'id', width:120, align:'center', renderer:function(val, item, rowIndex){
					   if(item.isClose == 1 &&  item.beginTimeDate > time){
						   return '<a href="javascript:void(0);" onclick="openArrange('+item.id+')">报名开启</a>';
					   }else if(item.isClose == 2){
						   return '<a href="javascript:void(0);" onclick="closeArrange('+item.id+')">报名关闭</a>';
					   }else{
						   return '<a href="javascript:void(0);"  style="color:#999;">报名关闭</a>';
					   }
				   }}
	           ],
	        plugins : [
	        	$('#paginator11-1').mmPaginator({
	        		totalCountName: 'total',    //对应MMGridPageVoBean count
	        		page: 1,                    //初始页
	        		pageParamName: 'page',      //当前页数
	        		limitParamName: 'pageSize', //每页数量
	        		limitList: [10, 20, 40, 50]
	        	})
	        ]
	    });
	}
	
	function param(){
		var o = {};
		o.name = $("#name").val();
		o.startStatus = $("#startStatus").val();
		o.isClose = $("#isClose").val();
		o.isJoin = 1;
		return o;
	}
	
	function openArrange(id){
		dialog.confirm("确认开启报名吗?",function(obj){
			$.ajax({
		   		type:"POST",
		   		async:true,  //默认true,异步
		   		data:{
		   			id : id,
		   			isClose : 2
		   		},
		   		url:"<%=request.getContextPath()%>/train/updateTrainArrangeCloseStatus.action",
		   		success:function(data){
		   			if(data == 'SUCCESS'){
		   				search();
			   			dialog.alert("开启成功。");
					}else{
						dialog.alert("开启失败。");
					}
		   	    }
		   	});
		})
	}
	
	function closeArrange(id){
		dialog.confirm("确认关闭报名吗?",function(obj){
			$.ajax({
		   		type:"POST",
		   		async:true,  //默认true,异步
		   		data:{
		   			id : id,
		   			isClose : 1
		   		},
		   		url:"<%=request.getContextPath()%>/train/updateTrainArrangeCloseStatus.action",
		   		success:function(data){
		   			if(data == 'SUCCESS'){
		   				search();
			   			dialog.alert("关闭成功。");
					}else{
						dialog.alert("关闭失败。");
					}
		   	    }
		   	});
		})
	}
	
	function search(){
		$('#exampleTable').mmGrid().load();
	}
	
	function reset(){
		$("#createUserName").val('');
		$("#status").val('');
		$('#exampleTable').mmGrid().load();
	}
	
</script>

<body>
	<div class="content">
		<!-- <h3>培训报名管理</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">培训报名管理</span>
		</div>
	<div class="search_2">
			<input type="hidden" id="timeType" value="1"/>
        	<p>培训名称：
                    <input type="text" id="name"/>
                  		报名状态：
                    <select id="isClose">
                    	<option value="">全部</option>
                    	<option value="1">已关闭</option>
                    	<option value="2">已开启</option>
                    </select>
                    	培训状态：
                    <select id="startStatus">
                    	<option value="">全部</option>
                    	<option value="1">未开始</option>
                    	<option value="2">进行中</option>
                    	<option value="3">已结束</option>
                    </select>		
     			</p>
                    <input type="button" value="重置" class="btn_3" onclick="reset()"/>
                    <input type="button" value="查询" class="btn_cx" onclick="search()"/>
       </div>
	
	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
	<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
	</div>
</body>
