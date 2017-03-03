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
	var joinEndTime = ${joinEndTime};
	
	$(function(){
		
		initCanJoinGrid();
		//initJoinedGrid();
		
		$("#beginTime,#endTime").datepicker({
			dateFormat : 'yy-mm-dd',
	 		changeMonth: true,
	 	    changeYear: true
		});
		
	})
	
	function initCanJoinGrid(){
		//表格
		$('#exampleTable').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:"<%=request.getContextPath()%>/train/selectCanJoinTrainArrangeList.action",
	    	width: $('#exampleTable').width(),
	    	height: 'auto',
	    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
	    	showBackboard: false,
	        indexCol: true,  //索引列
	        params: function(){
		    	return  param();
		    },
	     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
	               {title: '培训名称', name: 'name', width:140, align:'center', renderer:function(val, item, rowIndex){
	 				  return '<a href="toMyNotStartTrainArrangeDetail.action?arrangeId='+item.id+'" > '+val+' </a>'<%-- +
	 						  (item.isRecommend==1?'<img src="<%=request.getContextPath() %>/images/recommend.png"/>':'') --%>;
				   }},
	 			   {title: '培训时间', name: 'beginTimeDate', width:120, align:'center', renderer:function(val, item, rowIndex){
	 				  return getSmpFormatDateByLong(val, true)+"—"+getSmpFormatDateByLong(item.endTimeDate, true);
				   }},
	 			   {title: '允许名额/已报名', name: 'maxJoinNum', width:120, align:'center', renderer:function(val, item, rowIndex){
	 				  return val+"/"+item.joinedNum;
				   }},
				   {title: '培训状态', name: 'beginTimeDate', width:60, align:'center', renderer:function(val, item, rowIndex){
		 				  return val>time?'未开始':((val < time && item.endTimeDate >time)?"进行中":"已结束");
				   }},
				   {title: '报名状态', name: 'passStatus', width:60, align:'center', renderer:function(val, item, rowIndex){
		 				  return val == null?'未报名':(val == 1?"已报名":"报名失败");
				   }},
				   {title: '操作', name: 'id', width:60, align:'center', renderer:function(val, item, rowIndex){
					   if(item.passStatus != 1 && item.maxJoinNum > item.joinedNum){
						   if(item.beginTimeDate-time > joinEndTime*1000*60*60){
							   return '<a href="javascript:void(0);" onclick="joinArrange('+item.id+','+val+')" >报名</a>';
						   }
					   }
					   return '<a href="javascript:void(0);" style="color:#999;">报名</a>';
					   
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
	
	function initJoinedGrid(){
		//表格
		$('#exampleTable2').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:"<%=request.getContextPath()%>/train/selectJoinedTrainArrangeList.action",
	    	width: $('#exampleTable').width(),
	    	height: 'auto',
	    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
	    	showBackboard: false,
	        indexCol: true,  //索引列
	        params: function(){
		    	return  param();
		    },
	     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
	               {title: '培训名称', name: 'name', width:140, align:'center', renderer:function(val, item, rowIndex){
	            	   if(item.beginTimeDate< time && item.endTimeDate >time && item.isRelease == 2){
		 				  return '<a href="toMyTrainArrangeDetail.action?arrangeId='+item.id+'" > '+val+' </a>'<%-- +
		 						  (item.isRecommend==1?'<img src="<%=request.getContextPath() %>/images/recommend.png"/>':'') --%>;
	            	   }else{
	            		   return '<a href="toMyNotStartTrainArrangeDetail.action?arrangeId='+item.id+'" > '+val+' </a>'<%-- +
	 						  (item.isRecommend==1?'<img src="<%=request.getContextPath() %>/images/recommend.png"/>':'') --%>;
	            	   }
				   }},
	 			   {title: '培训时间', name: 'beginTimeDate', width:120, align:'center', renderer:function(val, item, rowIndex){
	 				  return getSmpFormatDateByLong(val, true)+"—"+getSmpFormatDateByLong(item.endTimeDate, true);
				   }},
	 			   {title: '允许名额/已报名', name: 'maxJoinNum', width:120, align:'center', renderer:function(val, item, rowIndex){
	 				  return val+"/"+item.joinedNum;
				   }},
				   {title: '培训状态', name: 'beginTimeDate', width:60, align:'center', renderer:function(val, item, rowIndex){
		 				  return val>time?'未开始':((val < time && item.endTimeDate >time)?"进行中":"已结束");
				   }},
				   {title: '是否通过', name: 'isPass', width:60, align:'center', renderer:function(val, item, rowIndex){
		 				  return val==1?'已通过':'未通过';
				   }},
				   {title: '操作', name: 'id', width:60, align:'center', renderer:function(val, item, rowIndex){
					   if(item.beginTimeDate< time && item.endTimeDate >time && item.isRelease == 2){
			 				  return '<a href="toMyTrainArrangeDetail.action?arrangeId='+item.id+'" >进入</a>';
		            	   }else{
		            		   return '<a href="toMyNotStartTrainArrangeDetail.action?arrangeId='+item.id+'" >进入</a>';
		            	   }
				   }}
	           ],
	        plugins : [
	        	$('#paginator11-2').mmPaginator({
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
		o.teacherName = $("#teacherName").val();
		var timeType = [];
		$.each($("input[name='timeType']"), function(index, val){
			if(val.checked){
				timeType.push($(val).val());
			}
		})
		o.timeType = timeType.join(",");
		o.beginTime = $("#beginTime").val();
		o.endTime = $("#endTime").val();
		return o;
	}
	
	function tabClick(val, obj){
		$(obj).attr('class', 'li_a').siblings().removeClass("li_a");
		if(val == 1){
			$("#joined").hide();
			$("#canJoin").show();
			initCanJoinGrid();
			$('#exampleTable').mmGrid().load();
		}else{
			$("#joined").show();
			$("#canJoin").hide();
			initJoinedGrid();
			$('#exampleTable2').mmGrid().load();
		}
	}
	
	function search(){
		$('#exampleTable').mmGrid().load();
		$('#exampleTable2').mmGrid().load();
	}
	
	function joinArrange(id, arrangeId){
		dialog.confirm("确认报名吗?",function(obj){
			$.ajax({
		   		type:"POST",
		   		async:true,  //默认true,异步
		   		data:{
		   			id : id,
		   			arrangeId : arrangeId
		   		},
		   		url:"<%=request.getContextPath()%>/train/joinTrainArrange.action",
		   		success:function(data){
		   			if(data == 'SUCCESS'){
		   				search();
			   			dialog.alert("报名成功。");
					}else if(data == 'FAIL'){
						dialog.alert("报名失败。");
					}else{
						search();
			   			dialog.alert("报名人数已满。");
					}
		   	    }
		   	});
		})
	}
	
</script>

<body>
	<div class="content">
		<!-- <h3>我的培训</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的培训</span>
		</div>
		<div class="ul_know" id="kn_ce">
				<ul id="ul_exam">
					<li class="li_a" onclick="tabClick(1, this)">可报名培训</li>
					<li onclick="tabClick(2, this)">已报名培训</li>
				</ul>
		</div>
	<div class="search_2" style="margin-top: 60px;">
        	<p>
                  	 培训时间：
                   	<input type="text" id="beginTime"/>至<input type="text" id="endTime"/>		
     			</p>
                    <input type="button" value="查询" class="btn_cx" onclick="search()"/>
       </div>
       <div class="search_2">
       		 <p>
            		 培训名称：
                    <input type="text" id="name"/>
                   	讲师名称：
                    <input type="text" id="teacherName"/>
                   	 培训类别：
                    <input type="checkbox" name="timeType" value="1"/>年度
                    <input type="checkbox" name="timeType" value="2"/>月度
                    </p>
       </div>
	<div id="canJoin">
		<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
	</div>
	<div id="joined" style="display:none;">
		<div id="exampleTable2" style="margin-top:10px;width:100%;" ></div>
		<div id="paginator11-2" align="right" style="margin-right:10px;" ></div>
	</div>
	</div>
</body>
