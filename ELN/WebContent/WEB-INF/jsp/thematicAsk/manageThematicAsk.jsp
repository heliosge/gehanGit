<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专题问答管理</title>
<!-- jQuery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- dateUtil -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**专题问答整体*/
#thematicContent{width: 1066px;margin: 16px auto 0px;padding-bottom: 200px;clear: both;overflow: hidden;}

/**专题问答面包屑*/
#thematicContent > h3{background: transparent url("../images/img/ico_4.png") no-repeat scroll left 2px;
width: 1052px;padding-left: 20px;color: #3A3A3A;border-bottom: 1px solid #CCC;padding-bottom: 10px;
margin-bottom: 10px;font-size:14px;}

/**按钮功能区域*/
#functionButtons {width: 1066px;height: 50px;}

/**新增专题问答按钮*/
#newThematicAsk{background: #D60500 none repeat scroll 0% center;color: #FFF;text-align: center;
width: 130px;height: 35px;border: medium none;border-radius: 2px;
font-weight: bold;margin-right: 10px;cursor: pointer;font-size:12px;}

/**批量删除按钮*/
#batchDelete{background: #0085FE none repeat scroll 0% center;color: #FFF;text-align: center;
width: 130px;height: 35px;border: medium none;border-radius: 2px;
font-weight: bold;margin-right: 10px;cursor: pointer;font-size:12px;}

/**查询条件*/
#searchConditions{width: 1043px;height: 40px;padding-left: 10px;font-family: "微软雅黑";
background: #FBFBFB none repeat scroll 0% 0%;padding-right: 10px;border: 1px solid #CCC;}

#searchConditions > p{line-height: 40px;float: left;color: #666;font-size:12px;margin: 0px;padding: 0px;}

/**重置按钮*/
#reset{float: right;width: 60px;height: 28px;border: medium none;margin-top: 6px;cursor: pointer;
background: #0085FE none repeat scroll 0% 0%;color: #FFF;margin-right: 10px;}

/**查询按钮*/
#search{float: right;width: 60px;height: 28px;border: medium none;margin-top: 6px;cursor: pointer;
background: #D60500 none repeat scroll 0% 0%;color: #FFF;margin-right: 10px;}

/**select选择框*/
#askType{width: 100px;height: 28px;border: 1px solid #CCC;}

/**问题标题输入框*/
#askTitle{width: 135px;height: 28px;border: 1px solid #CCC;}

/**专题问答列表*/
#thematicForMMGridDiv{width: 1066px;height: auto;font-size:12px;}

/**链接属性*/
a{text-decoration: none;cursor:pointer;}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';

/**
 * 页面加载完成
 */
$(function(){
	//初始化专题问答列表
	initThematicAskForMMGrid();
});

/**
 * 初始化专题问答列表
 */
function initThematicAskForMMGrid(){
	$("#thematicForMMGridTable").mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : '<%=request.getContextPath()%>/manageThematicAsk/getThematicAskForMMGrid.action',
		width : $("#thematicForMMGridDiv").width(),
		height : 'auto',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : true,
		multiSelect : true,
		indexCol : false, // 索引列
		params : {"searchAskState":$("#askType").val(),"searchTitle":$("#askTitle").val()},
		cols : [{
			title:'id',
			name:'id',
			width:'0px',
			hidden:true
		},{
			title:'askerId',
			name:'askerId',
			width:'0px',
			hidden:true
		},{
			title:'标题',
			name:'title',
			align:'center',
			renderer:function(val,item,rowIndex){
				var str = '<a href="javascript:void(0)" onclick="toManageThematicAskDetail('+item.id+');">'+val+'</a>';
				return str;
			}
		},{
			title:'内容摘要',
			name:'contentSummary',
			align:'center'
		},{
			title:'提问时间',
			name:'askTime',
			align:'center',
			renderer:function(val,item,rowIndex){
				return getSmpFormatDateByLong(val);
			}
		},{
			title:'最新回复',
			name:'replyCount',
			align:'center'
		},{
			title:'状态',
			name:'askState',
			align:'center',
			renderer:function(val,item,rowIndex){
				var str = '';
				if(val == 1){
					str = '未处理';
				}else if(val == 2){
					str = '已处理';
				}else if(val == 3){
					str = '已过期';
				}
				return str;
			}
		},{
			title:'操作',
			name:'operation',
			align:'center',
			renderer:function(val,item,rowIndex){
				var str = '';
				//只有自己的专题才有权限删除或者处理
				if(item.askerId == userId){
					//已过期的专题无法处理
					if(item.askState == 3){
						str += '<a href="javascript:void(0)" style="color:grey;">处理</a>';
					}else{
						str += '<a href="javascript:void(0)" onclick="dealWith('+item.id+');">处理</a>';
					}
					str += '&nbsp;&nbsp;<a href="javascript:void(0)" onclick="deleteThis('+item.id+');">删除</a>';
				}else{
					str += '<a href="javascript:void(0)" style="color:grey;">处理</a>';
					str += '&nbsp;&nbsp;<a href="javascript:void(0)" style="color:grey;">删除</a>';
				}
				return str;
			}
		}],
		plugins : [$('#thematicForMMGridPager').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean total
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [20,50,100,200]
		})]
	});
}

/**
 * 跳转到专题问答详情页面
 */
function toManageThematicAskDetail(id){
	window.location.href = '<%=request.getContextPath()%>/manageThematicAsk/toManageThematicAskDetail.action?id='+id;
}

/**
 * 处理
 */
function dealWith(id){
	window.location.href = '<%=request.getContextPath()%>/manageThematicAsk/toDealWithThematicAsk.action?id='+id;
}

/**
 * 删除
 */
function deleteThis(id){
	dialog.confirm("确认要删除吗？",function(obj){
		$.ajax({
			type:'POST',
			async:false,//此处为同步
			data:{"id":id},
			url:'<%=request.getContextPath()%>/manageThematicAsk/deleteThematicAsk.action',
			success:function(data){
				if(data.rtnResult == 'SUCCESS'){
					dialog.alert("删除成功！");
					//刷新列表
					$("#thematicForMMGridTable").mmGrid().load();
				}else{
					dialog.alert("删除失败！");
				}
			}
		});
	});
}

/**
 * 批量删除
 */
function batchDelete(){
	//验证
	var selectRows = $("#thematicForMMGridTable").mmGrid().selectedRows();
	if(selectRows.length <= 0){
		dialog.alert("请先选中需要删除的行！");
		return;
	}
	//判断选中行中是否有不是自己创建的专题，如果有就不允许删除
	var canDelete = true;
	$.each(selectRows,function(index,val){
		if(val.askerId != userId){
			canDelete = false;
		}
	});
	if(canDelete == false){
		dialog.alert("选中专题问答中有别人创建的专题问答，只能删除自己创建的专题问答！");
		return;
	}else{
		//批量删除
		dialog.confirm("确认要删除吗？",function(obj){
			var ids = [];
			$.each(selectRows,function(index,val){
				ids.push(val.id);
			});
			//删除
			$.ajax({
				type:'POST',
				async:false,//此处为同步
				data:{"ids":ids},
				url:'<%=request.getContextPath()%>/manageThematicAsk/batchDeleteThematicAsks.action',
				success:function(data){
					if(data.rtnResult == 'SUCCESS'){
						dialog.alert("删除成功！");
						//刷新列表
						$("#thematicForMMGridTable").mmGrid().load();
					}else{
						dialog.alert("删除失败！");
					}
				}
			});
		});
	}
}

/**
 * 查询
 */
function search(){
	var searchParam = {"searchAskState":$("#askType").val(),"searchTitle":$("#askTitle").val()};
	$("#thematicForMMGridTable").mmGrid().load(searchParam);
}

/**
 * 重置
 */
function reset(){
	$("#askType").val('');
	$("#askTitle").val('');
	$("#thematicForMMGridTable").mmGrid().load();
}

/**
 * 新增专题问答
 */
function newThematicAsk(){
	window.location.href='<%=request.getContextPath()%>/manageThematicAsk/toNewThematicAsk.action';
}
</script>
</head>
<body>
	<div id="thematicContent">
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">专题问答</span>
		</div>
		<!-- <h3>专题问答</h3> -->
		<!-- 功能按钮 -->
		<div id="functionButtons">
			<input type="button" id="newThematicAsk" value="新增专题问答" onclick="newThematicAsk();"/>
			<input type="button" id="batchDelete" value="批量删除" onclick="batchDelete();"/>
		</div>
		
		<!-- 查询条件 -->
		<div id="searchConditions">
			<p>
				问题状态：
				<select id="askType">
					<option value="">全部提问</option>
					<option value="1">未处理</option>
					<option value="2">已处理</option>
					<option value="3">已过期</option>
				</select>
				&nbsp;问题标题：
				<input type="text" id="askTitle"/>
			</p>
			<input type="button" id="reset" value="重置" onclick="reset();"/>
			<input type="button" id="search" value="查询" onclick="search();"/>
		</div>
		
		<!-- 列表展现 -->
		<div class="clear_both"></div>
		<div id="thematicForMMGridDiv">
			<table id="thematicForMMGridTable"></table>
			<!-- 分页栏 -->
			<div id="thematicForMMGridPager" style="float:right;"></div>
		</div>
	</div>
</body>
</html>