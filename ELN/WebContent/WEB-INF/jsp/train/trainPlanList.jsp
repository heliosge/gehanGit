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
	$(function(){
		
		var date = new Date();
		var year = date.getFullYear();
		for(var i = 5; i > -5; i--){
			$("#timeValue").append("<option value='"+(year+i)+"'>"+(year+i)+"</option>");
		}
		
		initGrid();
		
		$("#timeType").val() == 1 ? $("#yearTab").click():$("#monthTab").click();
		
	})
	
	function initGrid(){
		//表格
		$('#exampleTable').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:"<%=request.getContextPath()%>/train/selectTrainPlanList.action",
	    	width: $('#exampleTable').width(),
	    	height: 'auto',
	    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
	    	showBackboard: false,
	        multiSelect: true,
	        checkCol: true,
	        indexCol: true,  //索引列
	        params: function(){
		    	return  param();
		    },
	     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
	               {title: '培训计划', name: 'name', width:120, align:'center', renderer:function(val, item, rowIndex){
	 				  return '<a href="toTrainPlanDetail.action?id='+item.id+'" > '+item.timeValue+'--'+val+' </a>';
				   }},
	 			   {title: '培训次数', name: 'arrangeCount', width:30, align:'center'},
	 			   {title: '创建人', name: 'createUserName', width:60, align:'center'},
	 			   {title: '创建时间', name: 'createTime', width:60, align:'center', renderer:function(val, item, rowIndex){
	 				  return getSmpFormatDateByLong(val, true);
				   }},
				   {title: '状态', name: 'status', width:60, align:'center', renderer:function(val, item, rowIndex){
					   return val==1?'编辑中':(val==2?'待审批':(val==3?'审批通过':'审批拒绝'));
				   }},
				   {title: '操作', name: 'id', width:120, align:'center', renderer:function(val, item, rowIndex){
					   var buttonStr ='';
					   if(item.status == 1 || item.status == 4){
						   buttonStr += '<a href="javascript:void(0);" onclick="deletePlan('+item.id+')" >删除</a> ';
						   buttonStr += '<a href="toUpdateTrainPlan.action?id='+item.id+'" >修改</a>  ';
						   buttonStr += '<a href="javascript:void(0);" onclick="submitPlan('+item.id+')" >提交</a>';
					   }else{
						   buttonStr += '<a href="#" style="color:#999;">删除</a>  ';
						   buttonStr += '<a href="#" style="color:#999;">修改</a>  ';
						   buttonStr += '<a href="#" style="color:#999;">提交</a>';
					   }
					   return buttonStr;
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
		o.status = $("#status").val();
		o.timeType = $("#timeType").val();
		o.timeValue = $("#timeValue").val();
		o.createUserName = $("#createUserName").val();
		return o;
	}
	
	function submitPlan(id){
		dialog.confirm("确认提交吗?",function(obj){
			$.ajax({
		   		type:"POST",
		   		async:true,  //默认true,异步
		   		data:{
		   			id : id
		   		},
		   		url:"<%=request.getContextPath()%>/train/submitTrainPlan.action",
		   		success:function(data){
		   			if(data == 'SUCCESS'){
		   				search();
			   			dialog.alert("提交成功。");
					}else{
						dialog.alert("提交失败。");
					}
		   	    }
		   	});
		})
	}
	
	function tabClick(val, obj){
		$(obj).attr('class', 'li_a').siblings().removeClass("li_a");
		$("#timeType").val(val);
		$("#timeValue").html("<option value='' selected>全部</option>");
		if(val == 1){
			var date = new Date();
			var year = date.getFullYear();
			for(var i = 5; i > -5; i--){
				$("#timeValue").append("<option value='"+(year+i)+"'>"+(year+i)+"</option>");
			}
			$("#timeTypeSpan").html("培训年度：");
		}else{
			for(var i = 1; i < 13; i++){
				$("#timeValue").append("<option value='"+i+"'>"+i+"</option>");
			}
			$("#timeTypeSpan").html("培训月度：");
		}
		search();
	}
	
	function search(){
		$('#exampleTable').mmGrid().load();
	}
	
	function search_(){
		$('#exampleTable').mmGrid().load({"page":1});
	}
	
	function reset(){
		$("#timeValue").val('');
		$("#createUserName").val('');
		$("#status").val('');
		$('#exampleTable').mmGrid().load({"page":1});
	}
	
	function toAdd(){
		window.location.href="<%=request.getContextPath()%>/train/toAddTrainPlan.action?timeType="+$("#timeType").val();
	}
	
	function deletePlan(id){
		dialog.confirm("确认删除吗?",function(obj){
			var param = [];
		   	param.push(id);
		   	$.ajax({
		   		type:"POST",
		   		async:true,  //默认true,异步
		   		data:{
		   			ids : param
		   		},
		   		url:"<%=request.getContextPath()%>/train/deleteTrainPlan.action",
		   		success:function(data){
		   			if(data == 'SUCCESS'){
		   				search();
			   			dialog.alert("删除成功。");
					}else{
						dialog.alert("删除失败。");
					}
		   	    }
		   	});
		});
	}
	
	function deletePlans(){
		var selectRows = $('#exampleTable').mmGrid().selectedRows();
		var param = [];
    	$.each(selectRows, function(index, val){
    		if(val.status == 1 || val.status == 4){
	    		param.push(val.id);
    		}
    	});
		if(param.length > 0){
			dialog.confirm("确认删除吗?",function(obj){
		    	$.ajax({
		    		type:"POST",
		    		async:true,  //默认true,异步
		    		data:{
		    			ids : param
		    		},
		    		url:"<%=request.getContextPath()%>/train/deleteTrainPlan.action",
		    		success:function(data){
		    			if(data == 'SUCCESS'){
		    				search();
			    			dialog.alert("删除成功。");
						}else{
							dialog.alert("删除失败。");
						}
		    	    }
		    	});
			});
		}else{
			dialog.alert('请先选择数据！');
		}
	}
	
</script>

<body>
	<div class="content">
		<!-- <h3>培训计划</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">培训计划</span>
		</div>
		<div class="ul_know" id="kn_ce">
				<ul id="ul_exam">
					<li class="li_a" onclick="tabClick(1, this)" id="yearTab">年度</li>
					<li onclick="tabClick(2, this)" id="monthTab">月度</li>
				</ul>
		</div>
		
		 <div class="btn_gr">
    	<input type="button" class="btn_1" value="新增计划" onclick="toAdd()"/>
        <input type="button" class="btn_1" value="批量删除" onclick="deletePlans()"/>
    </div>
    
	<div class="search_2">
			<input type="hidden" id="timeType" value="1"/>
        	<p><span id="timeTypeSpan">培训年度：</span>
                    <select id="timeValue">
                    	<option value="" selected>全部</option>
                    </select>		
        			创建人：
                    <input type="text" id="createUserName"/>
                   	状态：
                    <select id="status">
                    	<option value="">全部</option>
                    	<option value="1">编辑中</option>
                    	<option value="2">待审批</option>
                    	<option value="3">审批通过</option>
                    	<option value="4">审批拒绝</option>
                    </select></p>
                    <input type="button" value="重置" class="btn_3" onclick="reset()"/>
                    <input type="button" value="查询" class="btn_cx" onclick="search_()"/>
       </div>
	
	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
	<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
	</div>
</body>
