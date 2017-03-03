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
</style>

<script>

	var time = ${time};

	$(function(){
		
		initTrainPlan();
		
		initGrid();
		
		$("#beginTime,#endTime").datepicker({
			dateFormat : 'yy-mm-dd',
	 		changeMonth: true,
	 	    changeYear: true
		});
		
		/* $("#timeType").val() == 1 ? $("#yearTab").attr('class', 'li_a').siblings().removeClass("li_a")
				:$("#monthTab").attr('class', 'li_a').siblings().removeClass("li_a"); */
		
	})
	
	function initGrid(){
		//表格
		$('#exampleTable').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:"<%=request.getContextPath()%>/train/selectTrainArrange.action",
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
	               {title: '培训名称', name: 'name', width:120, align:'center', renderer:function(val, item, rowIndex){
	 				  return '<a href="toTrainArrangeDetail.action?arrangeId='+item.id+'" > '+val+' </a>'<%-- +
	 						  (item.isRecommend==1?'<img src="<%=request.getContextPath() %>/images/recommend.png"/>':'') --%>;
				   }},
	 			   {title: '培训时间', name: 'beginTimeDate', width:80, align:'center', renderer:function(val, item, rowIndex){
	 				  return getSmpFormatDateByLong(val, true)+"-"+getSmpFormatDateByLong(item.endTimeDate, true);
				   }},
	 			   {title: '培训学时', name: 'allPeriod', width:20, align:'center'},
	 			   {title: '是否允许报名', name: 'isJoin', width:30, align:'center', renderer:function(val, item, rowIndex){
	 				  return val=='1'?"是":"否";
				   }},
                  {title: '安排人数', name: 'joinNum', width:50, align:'center'},
	 			   {title: '实际参加人数', name: 'realJoinNum', width:50, align:'center'},
	 			   {title: '创建人', name: 'createUserName', width:30, align:'center'},
				   {title: '状态', name: 'status', width:20, align:'center', renderer:function(val, item, rowIndex){
					   if(val == 3){
						   return item.beginTimeDate>time?'未开始':((item.beginTimeDate < time && item.endTimeDate >time)?"进行中":"已结束");
					   }
					   return val==1||val==0?'编辑中':(val==2?'待审批':(val==4?'审批拒绝':'审批取消'));
				   }},
				   {title: '操作', name: 'id', width:90, align:'center', renderer:function(val, item, rowIndex){
					   
					   var buttonStr ='';
					   if(item.status == 0){
						   buttonStr += '<a href="javascript:void(0);" onclick="deleteArrange('+item.id+')" >删除</a> ';
						   buttonStr += '<a href="toUpdateTrainArrange.action?id='+item.id+'" >修改</a>  ';
						   buttonStr += '<a href="javascript:void(0);" style="color:#999;">提交</a>';
					   }else if(item.status == 1 || item.status == 4 || item.status == 5){
						   buttonStr += '<a href="javascript:void(0);" onclick="deleteArrange('+item.id+')" >删除</a> ';
						   buttonStr += '<a href="toUpdateTrainArrange.action?id='+item.id+'" >修改</a>  ';
						   buttonStr += '<a href="javascript:void(0);" onclick="submitArrange('+item.id+')" >提交</a>';
					   }else if(item.status == 2){
						   buttonStr += '<a href="javascript:void(0);" onclick="cancelArrange('+item.id+')">取消</a>  ';
						   buttonStr += '<a href="javascript:void(0);" style="color:#999;" >延迟</a>  ';
						   buttonStr += '<a href="javascript:void(0);" style="color:#999;">提交</a>';
					   }else if(item.status == 3){
						   buttonStr += '<a href="javascript:void(0);" style="color:#999;">删除</a>  ';
						   if(item.beginTimeDate > time){
							   buttonStr += '<a href="javascript:void(0);" onclick="lateArrange('+item.id+')">延迟</a>  ';
						   }else{
							   buttonStr += '<a href="javascript:void(0);" style="color:#999;" >延迟</a>  ';
						   }
                           buttonStr += '<a href="toTrainArrangeDetail.action?arrangeId='+item.id+' ">详细</a>  ';
                           buttonStr += '<a href="javascript:void(0);" onclick="exportWord('+item.id+')"  >导出档案</a>  ';
						   buttonStr += '<a href="javascript:void(0);" style="color:#999;">提交</a>';
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
		
		$("#timeType").change(function(){
			initTrainPlan();
		})
	}
	
	function param(){
		var o = {};
		o.name = $("#name").val();
		if($("#status").val() > 3){
			o.startStatus=$("#status").val()-4;
		}else{
			o.status = $("#status").val();
		}
		o.timeType = $("#timeType").val();
		o.createUserName = $("#createUserName").val();
		o.isJoin = $("#isJoin").val();
		o.beginTime = $("#beginTime").val();
		o.endTime = $("#endTime").val();
		o.trainPlanId = $("#trainPlanId").val();
		return o;
	}
	
	function submitArrange(id){
		dialog.confirm("确认提交吗?",function(obj){
			$.ajax({
		   		type:"POST",
		   		async:true,  //默认true,异步
		   		data:{
		   			id : id
		   		},
		   		url:"<%=request.getContextPath()%>/train/submitTrainArrange.action",
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
		search();
	}
	
	function search(){
		$('#exampleTable').mmGrid().load();
	}
	
	function search_(){
		$('#exampleTable').mmGrid().load({"page":1});
	}
	
	function reset(){
		$("#createUserName").val('');
		$("#status").val('');
		$("#name").val('');
		$("#beginTime").val('');
		$("#endTime").val('');
		$('#exampleTable').mmGrid().load({"page":1});
	}
	
	function toAdd(){
		window.location.href="<%=request.getContextPath()%>/train/toAddTrainArrange.action?timeType="+$("#timeType").val();
	}
	
	function cancelArrange(id){
		dialog.confirm("确认取消申请吗?",function(obj){
		   	$.ajax({
		   		type:"POST",
		   		async:true,  //默认true,异步
		   		data:{
		   			id : id
		   		},
		   		url:"<%=request.getContextPath()%>/train/cancelTrainArrange.action",
		   		success:function(data){
		   			if(data == 'SUCCESS'){
			   			dialog.alert("取消成功。",function(){
			   				search();
			   			});
					}else{
						dialog.alert("取消失败。");
					}
		   	    }
		   	});
		});
	}
	
	function lateArrange(id){
		dialog.confirm("确认延迟培训吗?",function(obj){
		   	$.ajax({
		   		type:"POST",
		   		async:true,  //默认true,异步
		   		data:{
		   			id : id
		   		},
		   		url:"<%=request.getContextPath()%>/train/lateTrainArrange.action",
		   		success:function(data){
		   			if(data == 'SUCCESS'){
			   			dialog.alert("延迟成功。",function(){
			   				search();
			   			});
					}else{
						dialog.alert("延迟失败。");
					}
		   	    }
		   	});
		});
	}
	
	function deleteArrange(id){
		dialog.confirm("确认删除吗?",function(obj){
			var param = [];
		   	param.push(id);
		   	$.ajax({
		   		type:"POST",
		   		async:true,  //默认true,异步
		   		data:{
		   			ids : param
		   		},
		   		url:"<%=request.getContextPath()%>/train/deleteTrainArrange.action",
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
	
	function deleteArranges(){
		var selectRows = $('#exampleTable').mmGrid().selectedRows();
		var param = [];
    	$.each(selectRows, function(index, val){
    		if(val.status == 0 || val.status == 1 || val.status == 4 || val.status == 5){
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
		    		url:"<%=request.getContextPath()%>/train/deleteTrainArrange.action",
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
	
	function initTrainPlan(){
		$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:{timeType:$("#timeType").val()},
	   		url:"<%=request.getContextPath()%>/train/selectTrainPlanByType.action",
	   		success:function(data){
	   			if(data != null){
	   				var html = '<option value="">请选择</option>';
	   				$.each(data,function(index,val){
	   					html += '<option value="'+val.id+'">'+val.name+'</option>';
	   				})
	   				$("#trainPlanId").html(html);
	   			}
	   	    }
	   	});
	}


    function exportWord(id){
        if(!id){
            dialog.alert("导出出错");
            return false;
        }
        $.ajax({
            type:"POST",
            async:false,  //默认true,异步
            data:{},
            url:"<%=request.getContextPath()%>/train/exportTrain.action?arrangeId="+id,
            success:function(data){
                window.open(data);
            }
        });
    }
//    function exportWord(id){
//
//        if(!id){
//            dialog.alert("导出出错");
//            return false;
//        }
//        $("input[name='arrangeId']").val(id);
//        $("#form").submit();
//    }
</script>

<body>
	<div class="content">
		<!-- <h3>培训安排</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">培训安排</span>
		</div>
		<!-- <div class="ul_know" id="kn_ce">
				<ul id="ul_exam">
					<li class="li_a" onclick="tabClick(1, this)" id="yearTab">年度</li>
					<li onclick="tabClick(2, this)" id="monthTab">月度</li>
				</ul>
		</div> -->
		
		 <div class="btn_gr">
    	<input type="button" class="btn_1" value="新增安排" onclick="toAdd()"/>
        <input type="button" class="btn_1" value="批量删除" onclick="deleteArranges()"/>
    </div>
    
	<div class="search_2">
        	<p>培训名称：
                    <input type="text" id="name"/>
                  	 培训时间：
                   	<input type="text" id="beginTime"/>至<input type="text" id="endTime"/>	
                   	培训计划：
                   	<select id="timeType">
                   		<option value="">全部</option>
                   		<option value="1">年度</option>
                   		<option value="2">月度</option>
                   	</select>
                   	<select style="width:100px;" name="trainPlanId" id="trainPlanId">
		            		
	            	</select>	
     			</p>
                    <input type="button" value="重置" class="btn_3" onclick="reset()"/>
                    <input type="button" value="查询" class="btn_cx" onclick="search_()"/>
       </div>
       <div class="search_2">
       		<p>
       			创建人：
                    <input type="text" id="createUserName"/>
                   	状态：
                    <select id="status">
                    	<option value="">全部</option>
                    	<option value="0,1">编辑中</option>
                    	<option value="2">待审批</option>
                    	<option value="4">审批拒绝</option>
                    	<option value="5">未开始</option>
                    	<option value="6">进行中</option>
                    	<option value="7">已结束</option>
                    </select>
                    	是否允许报名：
                    <select id="isJoin">
                    	<option value="">全部</option>
                    	<option value="1">是</option>
                    	<option value="2">否</option>
                    </select>
       		</p>
       </div>
	
	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
	<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
	</div>
    <form id="form" action="<%=request.getContextPath()%>/train/exportTrain.action" method="post">
        <input type="hidden" value="" name="arrangeId"/>
    </form>
</body>
