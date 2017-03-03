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
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>

<style type="text/css">
	.course_tree {width: 98%;height: auto;border: 1px solid #333;font-family: '微软雅黑';text-align:center;}
	.course_tree h2 {font-size: 14px;width: 98%;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
</style>

<script>
$(function(){
	
	//表格
	var grid = $('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageGroup/selectManageGroupList.action",
    	width: $('#exampleTable').width(),
    	height: '400',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        //indexCol: true,  //索引列
        params: function(){
	    	return  param();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '群组名称', name: 'name', width:60, align:'center'},
			   {title: '类型', name: 'type', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "自由加入";
				   }else if(val == 2){
					   return "通过验证";
				   }else if(val == 3){
					   return "指定学员";
				   }else if(val == 4){
					   return "满足条件";
				   }
			   }},
			   {title: '成员数', name: 'capacity', width:60, align:'center'},
			   {title: '成立时间', name: 'createTime', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return getSmpFormatDateByLong(val, false);;
			   }},
			   {title: '创建人', name: 'createUserName', width:60, align:'center'}
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
		for(var i=0;i<window.parent.groupIds.length;i++){
			if(rows[0] != undefined){
				for(var j=0;j<rows.length;j++){
					if(rows[j].id==window.parent.groupIds[i]){
						grid.select(j);
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

/* function save(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
    	$.each(selectRows, function(index, val){
    		window.parent.groupIds.push(val.id);
    		window.parent.$("#group").append("群组："+val.name+"; ");
    	});
    }
	window.parent.artDialog.close();
} */

function save(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	var name ='';
	if(selectRows.length > 0){
		for(var i=selectRows.length-1;i>=0;i--){
			for( var j=0;j<window.parent.groupIds.length;j++){
				if(window.parent.groupIds[j] == selectRows[i].id){
					selectRows.splice(i,1);
					break;
				};
			}
		}
	}
	if(selectRows.length > 0){
		for(var i=selectRows.length-1;i>=0;i--){
			window.parent.groupIds.push(selectRows[i].id);
			name += "群组："+selectRows[i].name+";";
		}
		window.parent.$("#group").append(window.parent.$('#group').val()+name);
	}
	window.parent.artDialog.close();
}

</script>

</head>
<body>
	<div class="content" style="width:100%;margin-top: 0px;">
    <div class="search_3">
        	<p>	
            	群组名称：
                <input type="text" id="name"/>
            	 创建人：
                <input type="text" id="createUserName"/>
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
