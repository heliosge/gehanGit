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
		
		$("#beginTime,#endTime").datepicker({
			dateFormat : 'yy-mm-dd',
	 		changeMonth: true,
	 	    changeYear: true
		});
		
	})
	
	function initGrid(){
		//表格
		$('#exampleTable').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:"<%=request.getContextPath()%>/train/selectTrainArrangeAndContents.action",
	    	width: $('#exampleTable').width(),
	    	height: 'auto',
	    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
	    	showBackboard: false,
	        //multiSelect: true,
	       // checkCol: true,
	        indexCol: true,  //索引列
	        params: function(){
		    	return  param();
		    },
	     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
	               {title: '培训名称', name: 'name', width:150, align:'center'},
	 			   {title: '培训时间', name: 'beginTimeDate', width:150, align:'center', renderer:function(val, item, rowIndex){
	 				  return getSmpFormatDateByLong(val, true)+"-"+getSmpFormatDateByLong(item.endTimeDate, true);
				   }},
	 			   {title: '培训学时', name: 'allPeriod', width:30, align:'center'},
	 			   {title: '培训内容进度', name: 'isJoin', width:30, align:'center', renderer:function(val, item, rowIndex){
	 				   var c = 0;
	 				   $.each(item.contents,function(index, val){
	 					   if(val.endTimeDate < time){
	 						   c++;
	 					   }
	 				   })
	 				  return c+'/'+item.contents.length;
				   }},
				   {title: '操作', name: 'id', width:100, align:'center', renderer:function(val, item, rowIndex){
					   var c = 0;
	 				   var cc = 0;
	 				   $.each(item.contents,function(index, val_){
	 					   if(val_.endTimeDate < time){
	 						   c++;
	 					   }
	 					   if(val_.trainType == 2){
	 						   cc++;
	 					   }
	 				   })
	 				   var buttonStr = '';
	 				   if(c > 0 ){
	 					   if((item.afterClassExam ==null && item.passPercent > 0) || cc > 0){
					   		buttonStr += '<a href="toInsertTrainArrangeScore.action?trainArrangeId='+item.id+'" >培训管理</a>  ';
	 					   }
	 				   }else{
	 					  	buttonStr += '<a href="javascript:void(0);" style="color:#999;">培训管理</a>  ';
	 				   }
					   if(item.isRelease == 2 && item.endTimeDate < time){
						   buttonStr += '<a href="javascript:void(0);" onclick="releaseScore('+item.id+')" >成绩发布</a> ';
					   }else{
						   buttonStr += '<a href="javascript:void(0);" style="color:#999;">成绩发布</a>';
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
	
	function releaseScore(id){
		dialog.confirm("确认发布成绩吗?",function(obj){
			$.ajax({
		   		type:"POST",
		   		async:true,  //默认true,异步
		   		data:{
		   			id : id
		   		},
		   		url:"<%=request.getContextPath()%>/train/releaseTrainArrangeScore.action",
		   		success:function(data){
		   			if(data == 'SUCCESS'){
		   				search();
			   			dialog.alert("发布成功。");
					}else{
						dialog.alert("发布失败。");
					}
		   	    }
		   	});
		})
	}
	
	function param(){
		var o = {};
		o.name = $("#name").val();
		o.beginTime = $("#beginTime").val();
		o.endTime = $("#endTime").val();
		o.status = 3;
		return o;
	}
	
	
	function search(){
		$('#exampleTable').mmGrid().load();
	}
	
	function reset(){
		$("#name").val('');
		$("#beginTime").val('');
		$("#endTime").val('');
		$('#exampleTable').mmGrid().load();
	}
	
	
</script>

<body>
	<div class="content">
		<!-- <h3>培训成绩管理</h3> -->
    <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">培训成绩管理</span>
	</div>
	<div class="search_2">
			<input type="hidden" id="timeType" value="1"/>
        	<p>培训名称：
                    <input type="text" id="name"/>
                  	 培训时间：
                   	<input type="text" id="beginTime"/>至<input type="text" id="endTime"/>		
     			</p>
                    <input type="button" value="重置" class="btn_3" onclick="reset()"/>
                    <input type="button" value="查询" class="btn_cx" onclick="search()"/>
       </div>
	
	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
	<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
	</div>
</body>
