<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程管理</title>
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
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<style type="text/css">
	.course_tree {width: 98%;height: auto;border: 1px solid #333;font-family: '微软雅黑';text-align:center;}
	.course_tree h2 {font-size: 14px;width: 98%;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
</style>

<script>
$(function(){
	
	//表格
	var grid = $('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/res/selectResCoursewareList.action",
    	width: $('#exampleTable').parent().width(),
    	height: '400',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        params: function(){
        	var param = {};
        	param.type = $("#type").val();
        	param.name = $("#name").val();
	    	return param;
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '课件名称', name: 'name', width:60, align:'center'},
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
               {title: '创建时间', name: 'createTime', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return getSmpFormatDateByLong(val, true);
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
	
	grid.on("loadSuccess",function(e, data){
		var rows = grid.rows();
		for(var i=0;i<window.parent.coursewareGridRows.length;i++){
			if(window.parent.coursewareGridRows[0] != undefined){
				if(rows[0] != undefined){
					for(var j=0;j<rows.length;j++){
						if(rows[j].id==window.parent.coursewareGridRows[i].id){
							grid.select(j);
						}
					}
				}
			}
		}
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
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	var parentRows = window.parent.$('#exampleTable').mmGrid().rows();
	if(parentRows[0] != undefined){
		for(var j=0;j<parentRows.length;j++){
			for(var i=selectRows.length-1;i>=0;i--){
				if(selectRows[i].id == parentRows[j].id){
					selectRows.splice(i,1);
					break;
				}
			}
		}
	}
	window.parent.$('#exampleTable').mmGrid().addRow(selectRows);
	window.parent.coursewareGridRows = window.parent.$('#exampleTable').mmGrid().rows();
	window.parent.artDialog.close();
}

</script>

</head>
<body>
	<div class="content" style="width:100%;margin-top: 0px;padding-bottom:0px;">
    <div class="search_3" style="width:98%;">
        	<p>	
        		 课件类型：
            	 <select id="type">
                        <option value="">全部</option>
                        <option value="1">标准课件</option>
                        <option value="2">普通课件</option>
                        <option value="3">视频课件</option>
                    </select>
                   	 课件名称：
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
