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
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.course_tree {width: 98%;height: auto;border: 1px solid #333;font-family: '微软雅黑';text-align:center;}
	.course_tree h2 {font-size: 14px;width: 98%;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
</style>

<script>
$(function(){
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/teacher/getTeacherByMap.action",
    	width: $('#exampleTable').width(),
    	height : '265px',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
 		checkCol: true,
        multiSelect: false,
        indexCol: false,  //索引列
        params:function(){
        	var param = new Object();
        	param.userName = $.trim($("#userName").val());
        	param.teacherName = $.trim($("#teacherName").val());
        	return param;
        },
        cols: [{title: 'ID', align:'center',name: 'teacherId', width:60, hidden:true},
			   {title: '用户名', align:'center',name: 'userName', width:50},
			   {title: '讲师姓名', align:'center',name: 'teacherName', width:50},
			   {title: '电子邮箱', align:'center',name: 'eMail', width:50, },
			   {title: '分类', align:'center',name: 'teacherCategory', width:50,renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "内部讲师";
				   }else if(val == 2){
					   return "外部讲师";
				   }
			   }},
			   {title: '擅长领域', align:'center',name: 'description', width:50, }
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


function search(){
	$('#exampleTable').mmGrid().load();
}

function save(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		if(selectRows.length > 1){
			dialog.alert("只能选择一位讲师。");
			return;
		}
    	$.each(selectRows, function(index, val){
    		window.parent.teacherId = val.teacherId;
    		window.parent.$("#teacherName").val(val.teacherName);
    	});
    }
	window.parent.artDialog.close();
}

</script>

</head>
<body>
	<div class="content" style="width:100%;margin-top: 0px;">
    <div class="search_3" style="width:99%;">
        	<p>	
            	讲师姓名：
            	<input type="text" id='teacherName'>
            	用户名：
            	<input type="text" id='userName'>
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
