<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>学习计划管理</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/teacher.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>


<script type="text/javascript">


$(function(){
	
	//时间插件
	$("#beginTime, #endTime").datepicker({
		dateFormat:"yy-mm-dd"
	});	
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/learnPlan/selectLearnPlanList.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        checkCol: true,
        multiSelect: true,
        indexCol: true,  //索引列
        params:function(){
        	var param = new Object();
        	
        	param.name = $.trim($("#name").val());
        	param.createUser = $.trim($("#createUser").val());
        	param.beginTime = $("#beginTime").val();
        	param.endTime = $("#endTime").val();
        	param.isPublished = $("#isPublished").val();
        	
        	return param;
        },
        cols: [{title: '计划名称', name: 'name', width:60,
        	  renderer:function(val,item, rowIndex){
			       return "<a href='javascript:void(0);' onclick='detailLearnPlan("+item.id+")'>"+val+"</a>";
       	   }},
			   {title: '计划时间', name: 'beginTime', width:50,renderer:function(val, item, rowIndex){
				   return item.beginTime+'-'+item.endTime;
			   }},
			   {title: '计划学时', name: 'learnTime', width:50},
			   {title: '状态', name: 'isPublished', width:40, renderer:function(val, item, rowIndex){
				   if(val == 0){
					   return "编辑中";
				   }else if(val == 1){
					   return "已发布";
				   }
			   }},
			   {title: '可获学分', name: 'learnScore', width:60},
			   {title: '操作', name: 'id', width:160, renderer:function(val, item, rowIndex){
				   var buttonStr ='';
			
				   if(item.isPublished==1){
					   buttonStr+='<a href="javascript:void(0);" style="color:gray">发布</a>' +
		                '<a href="javascript:void(0);" style="color:gray">修改</a>' +
	   				    '<a href="javascript:void(0);" style="color:gray">删除</a>'+
	   				 '<a href="javascript:void(0);" class="td_a" onclick="viewPlanStudent('+val+');">查看学员进度</a>';
				   }else{
					   buttonStr+='<a href="javascript:void(0);" onclick="publicPlan('+val+')" >发布</a>' +
		                '<a href="javascript:void(0);" onclick="editRowOne('+val+')" >修改</a>' +
	   				    '<a href="javascript:void(0);" onclick="deleteRowOne('+val+')" >删除</a>';
				   }
				   
				   return  buttonStr ;
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
});

/*查看学习进度  */
function viewPlanStudent(planId){
	window.location ="<%=request.getContextPath()%>/learnPlan/toLearnPlanStudentProcessPage.action?planId="+planId;
}

//查询
function search(page){
	if(page){
		$('#exampleTable').mmGrid().load({"page":page});
	}
	else{
		$('#exampleTable').mmGrid().load();
	}
	
}
//重置
function clearAll(){
	$("#beginTime").val("");
	$("#endTime").val("");
	$("#name").val("");
	$("#createUser").val("");
    $("#isPublished").val(""); 
    search(1);
}
function detailLearnPlan(id){

	window.location.href = "<%=request.getContextPath()%>/learnPlan/toLearnPlanDetail.action?id="+id;
}

//发布计划
function publicPlan(id){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":id},
		url:"<%=request.getContextPath()%>/learnPlan/publiceLearnPlan.action",
		success:function(data){
			if(data=="SUCCESS"){
				dialog.alert('发布成功！');
				search();
			}else if(data==false){
				dialog.alert('计划内没有阶段或人员');
			}else{
				dialog.alert('发布失败！');
			}
			
	    }
	});
}

//删除一行
function deleteRow(){
	
	//被选中的一行
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	
	if(selectRows.length > 0){
		dialog.confirm('确认删除吗？', function(){ 
			
				var param = [];
		    	$.each(selectRows, function(index, val){
		    		param.push({"name":"ids", "value":val.id});
		    	});
		    	
		    	$.ajax({
		    		type:"POST",
		    		async:true,  //默认true,异步
		    		data:param,
		    		url:"<%=request.getContextPath()%>/learnPlan/deleteLearnPlan.action",
		    		success:function(data){
		    			search();
		    	    }
		    	});
		    
		});
	}else{
		dialog.alert('请先选择数据！');
	}
}
function viewStudent(id){
	if(!id){
		return;
	}
	window.location.href = "<%=request.getContextPath()%>/learnPlan/toLearnPlanStudentProcessPage.action?planId="+id;
}
//删除一行
function deleteRowOne(id){
	
	dialog.confirm('确认删除吗？', function(){ 
		
			var param = [];
			param.push({"name":"ids", "value":id});
	    	
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:param,
	    		url:"<%=request.getContextPath()%>/learnPlan/deleteLearnPlan.action",
	    		success:function(data){
	    			if(data == "SUCCESS"){
	    				dialog.alert('删除成功！');
	    				search();
	    			}else{
	    				dialog.alert('删除失败！');
	    			}
	    	    }
	    	});
	    
	});
}

//增加
function addRow(){
	//$("#add_iframe iframe").height($("body").height());
	//$("#add_iframe iframe").attr("src", "<%=request.getContextPath()%>/learnPlan/learnPlanEdit.action");
	//$("div").first().hide();
	//$("#add_iframe").show();
    window.location.href = "<%=request.getContextPath()%>/learnPlan/learnPlanEdit.action";
}

//修改标签
function editRowOne(id){
	window.location.href = "<%=request.getContextPath()%>/learnPlan/learnPlanEdit.action?id="+id;
	//$("#add_iframe iframe").height($("body").height());
	//$("#add_iframe iframe").attr("src", "<%=request.getContextPath()%>/learnPlan/learnPlanEdit.action?id=" + id);
	//$("div").first().hide();
	//$("#add_iframe").show();
}

//修改
function editRow(){
	
	if($('#exampleTable').mmGrid().selectedRows().length > 1){
		dialog.alert('只能单选操作!');
	}else{
		//被选中的一行
		var selectRowIndex = $('#exampleTable').mmGrid().selectedRowsIndex();
		
		if(selectRowIndex != ""){
			var row = $('#exampleTable').mmGrid().row(selectRowIndex);
			//$("#add_iframe iframe").attr("src", "<%=request.getContextPath()%>/learnPlan/learnPlanEdit.action?id="+row.id);
			//$("div").first().hide();
			//$("#add_iframe").show();
			window.location.href = "<%=request.getContextPath()%>/learnPlan/learnPlanEdit.action?id="+row.id;
		}else{
			dialog.alert('请先选择一行！');
		}
	}
}

</script>
</head>
<body style="overflow-x:hidden;">
	
<div class="content">
<!-- <h3>学习计划管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">学习计划管理</span>
	 </div>
    <div class="btn_gr">
    	<input type="button" class="btn_1" onclick="addRow()" value="新增"/>
        <input type="button" class="btn_1" onclick="deleteRow()" value="批量删除" />
    </div>
    
    	<div class="search_2" style="width:1042px">
    	<p>
        	计划名称：
            <input type="text" id="name" name="name" />
                               计划时间：
            <input id="beginTime" type="text" name="beginTime" />至<input id="endTime" type="text" name="endTime" />
                               状态：
            <select id="isPublished" name="isPublished" >
					<option value="">全部</option>
					<option value="0">编辑中</option>
					<option value="1">已发布</option>
				</select>

        </p>
        <input type="button" value="重置" onclick='clearAll()'>
        <input type="button" value="查询" class="btn_cx" onclick='search(1)'>

    </div>
    
	<div id="exampleTable" class="tab_1">
		
     </div>
     <div id="paginator11-1" align="right" style="margin-right:10px;" ></div>

</div>
	
<div id="add_iframe" style="display:none;width:100%;height:100%;">
	<iframe frameborder="0" src="" style="width:100%;height:100%;" ></iframe>
</div>
	
</body>
</html>
