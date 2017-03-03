<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改考试</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<style type="text/css">
	.button{ 
		width: 70px; height: 30px; background: #d60500; color: #fff; font-family: '微软雅黑'; text-align: center; border: none; margin-top: 10px;
	}
</style>
<script type="text/javascript">
var arrangeId = ${arrangeId};

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
	var grid = $('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/train/selectTrainArrangeUserDetail.action",
    	width: $('#exampleTable').parent().width(),
    	height: '450px',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        //multiSelect: true,
        //checkCol: true,
        //indexCol: true,  //索引列
        params: function(){
        	return  toParam();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
 			   {title: '用户名', name: 'user', width:60, align:'center', renderer:function(val, item, rowIndex){
 				  return val.userName;
			   }},
               {title: '姓名', name: 'user', width:60, align:'center', renderer:function(val, item, rowIndex){
            	   return val.name;
			   }},
               {title: '部门', name: 'user', width:60, align:'center', renderer:function(val, item, rowIndex){
            	   return val.deptName;
			   }},
 			   {title: '岗位', name: 'user', width:60, align:'center', renderer:function(val, item, rowIndex){
            	   return val.postName;
			   }},
 			   {title: '报名时间', name: 'createTime', width:60, align:'center', renderer:function(val, item, rowIndex){
 				  return getSmpFormatDateByLong(val, true);
			   }},
			   {title: '操作', name: 'id', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(item.passStatus==1){
				 		return '<a href="javascript:void(0)" onclick="updateUserPassStatus('+item.id+',2)" >同意</a>'
				 				+' <a href="javascript:void(0)" onclick="updateUserPassStatus('+item.id+',3)" >拒绝</a>';
				   }else{
					   return '<a href="javascript:void(0)" style="color:#999;">同意</a>'
		 				+' <a href="javascript:void(0)" onclick="updateUserPassStatus('+item.id+',4)" >删除</a>';
				   }
			   }}
           ]
    });
	
	grid.on("loadSuccess",function(e, data){
		stuGridRows = $('#exampleTable').mmGrid().rows();
		search();
		});
}

function updateUserPassStatus(id,status){
	dialog.confirm("确认"+(status == 2?"同意":(status == 3?"拒绝":"删除"))+"吗?",function(obj){
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:{id:id,status:status,arrangeId:arrangeId},
			url:"<%=request.getContextPath()%>/train/updateUserPassStatus.action",
			success:function(data){
				if(data == 'SUCCESS'){
					query();
					dialog.alert((status == 2?"培训报名审批通过":(status == 3?"培训报名审批拒绝":"删除成功")));
				}else if(data == 'FAIL'){
					query();
					dialog.alert((status == 2?"培训报名审批失败":(status == 3?"培训报名审批失败":"删除失败")));
				}else{
					dialog.alert("报名人数已满。");
				}
			}
		});
	})
}

function toParam(){
	var o = {};
	o.arrangeId = arrangeId;
	o.name = $("#name").val();
	o.beginTime = $("#beginTime").val();
	o.endTime = $("#endTime").val();
	o.passStatus = '1,2';
	return o;
}


function query(){
	$('#exampleTable').mmGrid().load();
}


function deleteRow(id){
	var rows = $('#exampleTable').mmGrid().rows();
	$.each(rows, function(index, val){
		if(id == val.id){
			$('#exampleTable').mmGrid().removeRow(index);
		}
	});
}


//查询
function search(){
	var rows = $('#exampleTable').mmGrid().rows();
	var userName = $("#userName").val();
	var name = $("#name").val();
	//var postName = $("#postName").val();
	//var deptName = $("#deptName").val();
	for(var index = rows.length-1; index>=0; index--){
		if(rows[index] == undefined){
			break;
		}
		var item = rows[index].user;
		if(userName){
			if(item.userName == null || item.userName.indexOf(userName) == -1){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
		if(name){
			if(item.name == null || item.name.indexOf(name) == -1){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
		/* if(postName){
			if(item.postName == null || item.postName.indexOf(postName) == -1){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
		if(deptName){
			if(item.deptName == null || item.deptName.indexOf(deptName) == -1){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		} */
	}
}

function exportExcel(){
	$("#form").submit();
}

</script>
</head>
<body>
<div class="content">
	<!-- <h3>培训报名详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">培训报名详情</span>
	</div>
    <div class="search_3" style="width:1044px; margin-top:20px;">
    	<form id="form" action="<%=request.getContextPath()%>/train/exportTrainArrangeUserDetail.action" method="post">
    		<input type="hidden"  name="arrangeId" value="${arrangeId }"/>
    		<input type="hidden"  name="passStatus" value="1,2"/>
        	<p>	
        		用户名：<input type="text" id="userName" name="userName"/>
            	姓名：<input type="text" id="name" name="name"/>
            	报名时间:<input type="text" id="beginTime" name="beginTime"/>至<input type="text" id="endTime" name="endTime"/>
                                                <!-- 岗位：<input type="text" id="postName"/>
            	部门：<input type="text" id="deptName"/> -->
        	</p>
            <input type="button" value="导出" onclick="exportExcel();"/>
        	<input type="button" value="查询" class="btn_cx" onclick="query();"/>
		</form>
    </div>
   	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
   	<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
</div>
</body>
</html>
