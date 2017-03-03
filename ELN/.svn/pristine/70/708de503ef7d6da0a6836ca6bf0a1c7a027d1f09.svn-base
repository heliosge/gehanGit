<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城专题管理</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>

<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />

<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!-- dialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
</style>

<script>


$(function(){
	//时间插件
	$("#beginTime, #endTime").datepicker({
		dateFormat:"yy-mm-dd"
	});	

	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/mall/manage/topic/list.action",
    	width: $('#exampleTable').parent().width(),
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
     	      {title:'专题编号',name:'code',width:30,align:'center'},
 			   {title: '专题名称', name: 'name', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="<%=request.getContextPath()%>/mall/manage/toTopicDetailPage.action?id='+item.id+'" >'+val+'</a>';
			   }},
			   {title:'专题总价',name:'price',width:30,align:'center'},
 			   {title: '优惠价', name: 'cheapPrice', width:30, align:'center'},
 			  {title: '销售数量', name: 'sellCount', width:30, align:'center'},
 			   {title: '上架时间', name: 'putawayTime', width:60, align:'center'},
 			   {title: '状态', name: 'status', width:60, align:'center',renderer:function(val,item,rowIndex){
 				 var str='';
 				 if(val==1){
 					str='上架' ;
 				 }else{
 					str='下架' ;
 				 }
 				 return str;
 			  }},
				   {title: '操作', name: 'status', width:120, align:'center', renderer:function(val, item, rowIndex){
					   
					   var buttonStr ='';
					   if(val == 0){
						   buttonStr += '<a href="javascript:void(0)" onclick="putawayOne('+item.id+')" >上架</a>  ';
						   buttonStr += '<a href="javascript:void(0)" onclick="toEditTopic('+item.id+')" >修改</a>  ';
						   buttonStr+='<a href="javascript:void(0)" onclick="deleteOne('+item.id+')">删除</a>';

					   }else if(val == 1){
						   buttonStr+='<a href="javascript:void(0)" onclick="putoutOne('+item.id+')">下架</a>';
						   buttonStr+='<a href="javascript:void(0)" style="color:gray">修改</a>';
						   buttonStr+='<a href="javascript:void(0)"  style="color:gray">删除</a>';
					   }
					  
					   
					   return "<div class='span_btus' >" + buttonStr + "</div>";
				   }}
           ],
        plugins : [
        	$('#paginator11-1').mmPaginator({
        		totalCountName: 'total',    //对应MMGridPageVoBean count
        		page: 1,                    //初始页
        		pageParamName: 'pageIndex',      //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [10, 20, 40, 50]
        	})
        ]
    });
	
})






function param(){
	var o = {};
	o.name = escapeForSql($("#name").val());
	o.code = escapeForSql($("#code").val());
	o.userRealName=$("#userRealName").val();
	o.status = $("#status").val();
	o.beginTime=$("#beginTime").val();
	o.endTime=$("#endTime").val();
	return o;
}







function deleteOne(id){
	dialog.confirm("确认删除吗?",function(obj){
		
	   	$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:{
	   			ids : id
	   		},
	   		url:"<%=request.getContextPath()%>/mall/manage/topic/delete.action",
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
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		dialog.confirm("确认删除吗?",function(obj){
			var param = [];
	    	$.each(selectRows, function(index, val){
	    		param.push(val.id);
	    	});
	    	var ids = param.join(",");
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{
	    			ids : ids
	    		},
	    		url:"<%=request.getContextPath()%>/mall/manage/topic/delete.action",
	    		success:function(data){
	    			if(data!=null&&data!='FAIL'){
						search();
						var idArr = ids.split(',');
						if(idArr.length>1&&data>0){
							dialog.alert("存在"+data+"个已上架专题，无法删除。");
						}else if(idArr.length==1&&data>0){
							dialog.alert("该专题已上架，无法删除。");
						}else{
							dialog.alert("删除成功。");
						}
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



function search(){
	$('#exampleTable').mmGrid().load({"pageIndex":1});
}

function clearOptions(){
	
	$("#name").val('');
	$("#code").val('');
	$("#status").val('');
	$("#beginTime").val('');
	$("#endTime").val('');
	search();
}

//批量上架
function putaway(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	var ids=[];
	if(selectRows.length>0){
		$.each(selectRows, function(index, val){
    		ids.push(val.id);
    	});
		doPutaway(ids.join(","));
	}else{
		dialog.alert("请至少选择一个专题");
	}
}
//上架1件
function putawayOne(id){
	if(!id){
		return;
	}

	doPutaway(id);
}
//上架
function doPutaway(ids){
	
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:{ids:ids},
			url:"<%=request.getContextPath()%>/mall/manage/topic/putaway.action",
			success:function(data){
				if(data=='SUCCESS'){
					search();
					dialog.alert("上架成功。");
				}else{
					dialog.alert("上架失败。");
				}
			}
		});
}

//批量下架
function putout(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	var ids=[];
	if(selectRows.length>0){
		$.each(selectRows, function(index, val){
    		ids.push(val.id);
    	});
		doPutout(ids.join(","));
	}else{
		dialog.alert("请至少选择一个专题");
	}
}
//下架1件
function putoutOne(id){
	if(!id){
		return;
	}
	
	doPutout(id);
}
//下架
function doPutout(ids){
	
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{ids:ids},
		url:"<%=request.getContextPath()%>/mall/manage/topic/putout.action",
		success:function(data){
			if(data=='SUCCESS'){
				search();
				dialog.alert("下架成功。");
			}else{
				dialog.alert("下架失败。");
			}
		}
	});




}


function toInsertTopic(){
	window.location.href="<%=request.getContextPath()%>/mall/manage/toTopicEditPage.action";
}

function toEditTopic(id){
	window.location.href="<%=request.getContextPath()%>/mall/manage/toTopicEditPage.action?id="+id;
}




</script>

</head>
<body>

<div class="content">
	<!-- <h3>商城专题管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">商城专题管理</span>
	</div>
       <div class="button_gr" style="height: auto;line-height: normal;padding-bottom: 5px;">
        	<input type="button" value="新增专题" class="btn_4" onClick="toInsertTopic()"/>
            <input type="button" value="批量删除" onclick="deleteRows()"/>
            <input type="button" value="上架" onclick="putaway()"/>
            <input type="button" value="下架" onclick="putout()"/>
        </div>
        <div class="search_3" style=" width: 1044px;">
        	<p>	
            	
            	 专题编号：
                <input type="text" id="code"/>
                                         专题名称：
                <input type="text" id="name"/>
                                     
         
        	  	状态：
                <select id="status">
                    <option value="">全部</option>
                    <option value="0">下架</option>
                    <option value="1">上架</option>
                </select>
                              上架时间：<input type="text" id="beginTime" name="beginTime"/> 至
                   <input type="text" id="endTime" name="endTime"/></p>
            <input type="button" onclick="clearOptions()" value="重置">
        	<input type="button" value="查询" class="btn_cx" onclick="search()"/>
        </div>
        <div id="exampleTable" style="margin-top:10px;width:100%" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
        
    
        
     
</div>

 
	

</body>
</html>
