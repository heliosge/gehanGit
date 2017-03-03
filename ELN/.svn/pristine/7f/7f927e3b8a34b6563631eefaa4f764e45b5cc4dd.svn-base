<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增群组-满足条件</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>


<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<script type="text/javascript">
var group = ${group};

$(function(){
		fillGroupInfo();
		initMMGrid();
});

function fillGroupInfo(){
	if(group.type == 1){
		$("#type").html("自由加入");
	}else if(group.type == 2){
		$("#type").html("通过验证");
	}else if(group.type == 3){
		$("#type").html("指定学员");
		$("#pointButton").show();
	}else if(group.type == 4){
		$("#type").html("满足条件");
	}
}

function initMMGrid(){
	//表格
	var mmGrid = $('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        params:  function(){
        	var o = {};
        	o.id = group.id;
        	return o;
        },
        url:"<%=request.getContextPath()%>/manageGroup/selectGroupStudentByGroupId.action",
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '姓名', name: 'name', width:60, align:'center'},
 			   {title: '用户名', name: 'userName', width:60, align:'center'},
 			   {title: '电子邮箱', name: 'email', width:60, align:'center'},
			   {title: '联系电话', name: 'mobile', width:60, align:'center'},
			   {title: '岗位', name: 'postName', width:60, align:'center'},
 			   {title: '部门', name: 'deptName', width:60, align:'center'},
 			  {title: '状态', name: 'status', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "正常";
				   }else if(val == 2){
					   return "待审核";
				   }
				   else if(val == 3){
					   return "审核不通过";
				   }
				}},
			   {title: '操作', name: 'status', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1 || val == 3){
					   return  '<a href="javascript:void(0);" style="color: #999;">通过</a>  '
		   				+'<a href="javascript:void(0);" style="color: #999;">不通过</a>'
		   				;
				   }else{
					   return  '<a href="javascript:void(0);" onclick="passOneRow('+item.id+',1)" >通过</a>  '
		   				+'<a href="javascript:void(0);" onclick="passOneRow('+item.id+',3)" >不通过</a>'
		   				;
				   }
				}}
           ]
    });
	
	mmGrid.on("loadSuccess",function(e, data){
		stuGridRows = mmGrid.rows();
	});
}

function passOneRow(id, status){
	var param = [];
   	param.push(id);
   	$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		data:{
   			studentIds : param,
   			status : status,
   			id : group.id
   		},
   		url:"<%=request.getContextPath()%>/manageGroup/checkGroupOneStu.action",
   		success:function(data){
   			if(data == 'SUCCESS'){
	   			search();
	   			dialog.alert("审批成功。");
   			}else{
	   			dialog.alert("审批失败。");
   			}
   	    }
   	});
}

function passRows(status){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		var param = [];
    	$.each(selectRows, function(index, val){
    		if(val.status == 2){
	    		param.push(val.id);
    		}
    	});
    	
    	if(param.length == 0){
    		dialog.alert("请选择未审批的学员。");
    		return;
    	}
    	
    	$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
    		data:{
    			studentIds : param,
       			status : status,
       			id : group.id
    		},
    		url:"<%=request.getContextPath()%>/manageGroup/checkGroupOneStu.action",
    		success:function(data){
    			if(data == 'SUCCESS'){
	    			search();
	    			dialog.alert("审批成功。");
    			}else{
	    			dialog.alert("审批失败。");
    			}
    	    }
    	});
	}else{
		dialog.alert('请先选择数据！');
	}
}



function search(){
	$('#exampleTable').mmGrid().load();
	query();
}

var stuGridRows;
function query(){
	var rows = $('#exampleTable').mmGrid().rows();
	for(var i = rows.length-1; i >= 0; i--){
		$('#exampleTable').mmGrid().removeRow(i);
	}
	$('#exampleTable').mmGrid().addRow(stuGridRows);
	rows = $('#exampleTable').mmGrid().rows();
	var userName = $("#userName").val();
	var name_ = $("#name_").val();
	var postId = $("#postId").val();
	var deptId = $("#deptId").val();
	var sex = $("#sex").val();
	var status = $("#status").val();
	for(var index = rows.length-1; index>=0; index--){
		var item = rows[index];
		if(userName){
			if(item.name.indexOf(userName) == -1){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
		if(name_){
			if(item.name.indexOf(name_) == -1){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
		if(sex){
			if(item.sex != sex){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
		if(status){
			if(item.status != status){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
		if(postId){
			if( item.postName==null || item.postName.indexOf(postId) == -1){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
		if(deptId){
			if( item.deptName==null || item.deptName.indexOf(deptId) == -1){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
	}
	
	hideErrorInfo();
}


function cancel(){
	window.location.href="<%=request.getContextPath()%>/manageGroup/toGroupListPage.action";
	}

function hideErrorInfo(){
	if($('#exampleTable').mmGrid().rows()[0] == undefined){
		$(".mmg-message").css("top","50px");
	}
}
	

</script>

</head>
<body>

<div class="content">
	<!-- <h3>审批群组</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">审批群组</span>
	</div>
	<div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl">
                <em>群组名称：</em>
            </div>
            <div class="add_fr">
            	${group.name }
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>群组容量：</em>
            </div>
            <div class="add_fr">
            	${group.capacity }
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>群组类型：</em>
            </div>
            <div class="add_fr" id="type">
            </div>
        </div>
        <div class="add_gr" style="height:auto;">
        	<div class="add_fl">
                <em>描述：</em>
            </div>
             <div class="add_fr">
            	${group.descr }
            </div>
    	</div>
        
         <div class="add_gr" id="condition1" style="display:none">
        	<div class="add_fl">
            	<span>*</span>
                <em>满足条件：</em>
            </div>
             <div class="add_fr">
            	<select>
                	<option>请选择</option>
                </select>
                <select>
                	<option>小于</option>
                </select>
                <input type="text" style="width:230px;" />
            </div>
	   	</div>
        <div class="add_gr" id="condition2" style="display:none;">
        	<div class="add_fl">
            	<span>&nbsp;</span>
                <em>&nbsp;</em>
            </div>
             <div class="add_fr">
            	<select>
                	<option>请选择</option>
                </select>
                <select>
                	<option>小于</option>
                </select>
                <input type="text" style="width:230px;" />
            </div>
        </div>
        <div class="zd" id="conditionButton" style="display:none;">
        	<input type="button" value="自动匹配学员" />
        </div>
           
    
    <div>
     <div class="btn_gr fl">
        <input type="button" class="btn_1" value="批量通过" onclick="passRows(1)" id="pointButton" />
    	<input type="button" class="btn_1" value="批量不通过" onclick="passRows(3)"/>
    </div>
	<div class="search_2 fl" style="margin-bottom:-1px;width:98%;">
    	<p>用户名：
            <input type="text" id="userName"/>
           	 姓名：
            <input type="text" id="name_"/>
			岗位：
            <input type="text" id="postId"/>
          	 部门：
            <input type="text" id="deptId"/>
        </p>
        <input type="button" value="查询" class="btn_cx" onclick="query()"/>

    </div>
    <div class="search_2 fl" style="width:98%;">
    	<p>	性别：
            <select id="sex">
            	<option value="">显示全部</option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
           	 状态：
            <select id="status">
            	<option value="">显示全部</option>
                <option value="1">正常</option>
                <option value="2">待审核</option>
                <option value="3">审核不通过</option>
            </select>

        </p>

    </div>
	</div>
    	<div style="float: left;width:1066px;">
    		<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
    	</div>
    </div>
        <div class="button_cz fl" style="padding-left:150px;">
            <input type="button" value="返回" class="back" onclick="cancel()"/>
        </div>
    
         
</div>
</body>
</html>
