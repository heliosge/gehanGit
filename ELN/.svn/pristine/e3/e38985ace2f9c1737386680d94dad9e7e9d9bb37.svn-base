<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程管理</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
</style>

<script>
$(function(){
	
	//表格
	$('#exampleTable').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/oam/selectChooseSpread.action",
    	width: $('#exampleTable').parent().width(),
    	height : '400px',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: false,
        checkCol: true,
        params: function(){
        	var param = {};
        	param.name = $("#name").val();
	    	return param;
	    },
     	cols: [{title: 'ID', name: 'ID', width:30, hidden:true},
               {title: '资源名称', name: 'NAME', width:60, align:'center'},
               {title: '资源类别', name: 'TYPE', width:180, align:'center', renderer:function(val, item, rowIndex){
            	  if(val == 1){
            		  return "专题";
            	  }else if(val == 2){
            		  return "课程";
            	  }else{
            		  return "大赛";
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
});

function param(){
	var o = {};
	o.name = $("#name").val();
	o.createUserName = $("#createUserName").val();
	return o;
}

function search(){
	$('#exampleTable').mmGrid().load();
}

function save(){
	var style;
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	$.each($("input[name='style']"), function(index, val){
		if(val.checked){
			style = $(val).val();
		}
	});
	for(var i = 0; i < window.parent.spreadList.length; i++){
		var spread = window.parent.spreadList[i];
		if(spread.type == selectRows[0].TYPE && spread.spreadId == selectRows[0].ID){
			flag = false;
			dialog.alert("该资源已经推广，不可重复推广。");
			return;
		}
	}
	if(selectRows.length > 0){
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:{
				order:window.parent.order,
				spreadId:selectRows[0].ID,
				type:selectRows[0].TYPE,
				style:style
			},
			url:"<%=request.getContextPath()%>/oam/insertOamBarSpread.action",
			success:function(data){
				if(data == 'SUCCESS'){
					window.parent.artDialog.close();
				}
			}
		});
	}
	window.parent.fillInfo();
}

</script>

</head>
<body>
	<div class="content" style="width:100%;margin-top: 0px;padding-bottom:0px;">
    <div class="search_3" style="width:100%;">
        	<p>	
                   	 资源名称：
                    <input type="text" id="name"/>
        	</p>
        	<input type="button" value="查询" class="btn_3" onclick="search()"/>
        	<input type="button" value="确定" class="btn_cx" onclick="save()"/>

        </div>
	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
	<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
     
</div>
<script>
</script>

</body>
</html>
