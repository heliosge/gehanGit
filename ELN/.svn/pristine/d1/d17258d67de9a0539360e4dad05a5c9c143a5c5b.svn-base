<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课件管理</title>
</head>

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

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>


<body>

<script type="text/javascript">

$(function(){
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/res/selectResCoursewareList.action",
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        params: function(){
        	var param = {};
        	param.type = $("#type").val();
        	param.name = escapeForSql($("#name").val());
	    	return param;
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '课件名称', name: 'name', width:120, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="#" onclick="toCourseStudy('+item.id+','+item.type+')">'+val+'</a>';
			   }},
			   {title: '课件类型', name: 'type', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "标准课件";
				   } else if(val == 2){
					   return "普通课件";
				   } else if(val == 3){
					   return "视频课件";
				   }
				   return "";
			   }},
			   {title: '创建人', name: 'createUserName', width:60, align:'center'},
			   {title: '创建时间', name: 'createTime', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return getSmpFormatDateByLong(val, true);
			   }},
			   {title: '操作', name: 'id', width:80, align:'center', renderer:function(val, item, rowIndex){
				   
				   var buttonStr = '<a href="javascript:void(0);" onclick="deleteRowOne('+val+','+item.isInCourse+')" >删除</a>   '+
				   	'<a href="<%=request.getContextPath()%>/res/toUpdateResCoursewarePage.action?id='+val+'" >修改</a>  ';
				   return "<div class='span_btus' >" + buttonStr + "</div>";
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

/**
 * 跳转到课程学习页面
 */
function toCourseStudy(courseWareId,courseWareType){
	window.location.href = '<%=request.getContextPath()%>/res/toCoursewareDetail.action?courseWareId='+courseWareId+'&courseWareType='+courseWareType;
}

function search(){
	$('#exampleTable').mmGrid().load();
}

function search_(){
	$('#exampleTable').mmGrid().load({"page":1});
}

function reset(){
	$("#name").val("");
	$("#type").val("");
	search_();
}

function deleteRowOne(id, isInCourse){
	dialog.confirm(isInCourse > 0 ? "该课件已关联在课程里，确认删除吗？":"确认删除吗？",function(obj){
		var param = [];
	   	param.push(id);
	   	
	   	$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:{
	   			ids : param
	   		},
	   		url:"<%=request.getContextPath()%>/res/deleteResCourseware.action",
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

function deleteRows(){
	//被选中的一行
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		var param = [];
		var isInCourse = [];
    	$.each(selectRows, function(index, val){
    		param.push(val.id);
    		val.isInCourse > 0 && isInCourse.push(index+1)
    	});
    	
		dialog.confirm(isInCourse.length > 0 ?"第"+isInCourse.join(",")+"个课件已关联在课程中，确认删除吗？":"确认删除吗？",function(obj){
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{
	    			ids : param
	    		},
	    		url:"<%=request.getContextPath()%>/res/deleteResCourseware.action",
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

<div class="content">
	<!-- <h3>课件管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课件管理</span>
	 </div>
    <div class="btn_gr">
    	<a href="toInsertResCoursewarePage.action"><input type="button" class="btn_1" value="新增课件" /></a>
        <input type="button" class="btn_2" value="批量删除" onclick="deleteRows()"/>
        
    </div>
    
	<div class="search_2">
    	<p>课件类型：
            <select id="type">
            	<option value="">全部</option>
                <option value="1">标准课件</option>
                <option value="2">普通课件</option>
                <option value="3">视频课件</option>
            </select>
            课件名称：
            <input type="text" id="name"/>
        </p>
        <input type="button" value="重置" onclick="reset();"/>
        <input type="button" value="查询" class="btn_cx" onclick="search_();"/>
        
		
        
	</div>
	
    <div id="exampleTable" style="margin-top:10px;width:100%" ></div>
	<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
     
     
</div>

</body>
</html>
