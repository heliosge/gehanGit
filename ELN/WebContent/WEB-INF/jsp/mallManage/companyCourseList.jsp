<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业课程商城管理</title>
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
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>

<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- dialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
	.course_tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.course_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
	.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
</style>



<script>


var companyId ='';
$(function(){
	//时间插件
	$("#beginTime, #endTime").datepicker({
		dateFormat:"yy-mm-dd"
	});	
	
	initPage();
    initCompanyOption();
	

	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/mall/manage/company/course/list.action",
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
               /**{title: '课程编号', name: 'code', width:60, align:'center'},**/
 			   {title: '课程名称', name: 'name', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="<%=request.getContextPath()%>/mall/manage/toCourseDetailPage.action?id='+item.id+'" >'+val+'</a>';
			   }},
 			   {title: '课程价格', name: 'price', width:30, align:'center'},
 			   {title: '销售数量', name: 'sellCount', width:60, align:'center'},
 			  {title: '上架时间(首次)', name: 'putawayTime', width:80, align:'center'},
 			  {title: '状态', name: 'status', width:30, align:'center',renderer:function(val,item,rowIndex){
 				 var str='';
 				 if(val==1){
 					str='上架' ;
 				 }else if(val==0){
 					str='下架' ;
 				 }
 				 return str;
 			  }},
				   {title: '操作', name: 'status', width:120, align:'center', renderer:function(val, item, rowIndex){
					   
					   var buttonStr ='';
					   if(val == 0){
						   
							   buttonStr += '<a href="javascript:void(0)" onclick="putawayOne('+item.id+')" >上架</a>  ';  
							   
							   buttonStr += '  <a href="#" onclick="deleteOne('+item.id+')" >删除</a>';
						   
						}else if(val == 1){
							
								  buttonStr += '<a href="javascript:void(0)" onclick="putoutOne('+item.id+')" >下架</a> ';
								  buttonStr += '  <a href="javascript:void(0)"  style="color:gray">删除</a>';
							   
						 
						  // buttonStr+='<a href="javascript:void(0)" style="color:gray">修改</a>';
						   //buttonStr += '  <a href="javascript:void(0)"  style="color:gray">删除</a>';
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
	o.status = $("#status").val();
	o.beginTime=$("#beginTime").val();
	o.endTime=$("#endTime").val();
	o.companyId = $("#companyId").val();
	o.categorys = categoryIds;
	return o;
}

function initPage(){
	categoryId = undefined;
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			view: {
				showLine: false,
				showIcon: true
			},
			callback: {
				//onRightClick: zTreeOnRightClick,
				onClick: zTreeOnClick
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/mall/manage/category/select.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#treePage"), setting, data);
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}
var categoryIds = [];
var categoryId;
var node;
function zTreeOnClick(event, treeId, treeNode) {
	categoryId = treeNode.id;
	node = treeNode;
	categoryIds = [];
	getChildNodes(treeNode);
	search();
};

function zTreeOnRightClick(event, treeId, treeNode) {
    if (treeNode && !treeNode.noR) {
    	categoryId = treeNode.id;
    	node = treeNode;
    	categoryIds = [];
    	getChildNodes(treeNode);
    	search();
    	$.fn.zTree.getZTreeObj("treePage").selectNode(treeNode);
    
    	
    }else{
    	$.fn.zTree.getZTreeObj("treePage").cancelSelectedNode();
    	contextMenu.hide();
    	//contextMenu.show({top: event.pageY, left: event.pageX});
    }
}


function getChildNodes(treeNode){
	categoryIds.push(treeNode.id); 
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			categoryIds.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

function getChildNodesNotAddThis(treeNode){
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			categoryIds.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}
function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

function initCompanyOption(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageCompany/selectManageCompanyList.action",
		success:function(data){
			var companyList = data.rows;
			$.each(companyList,function(index,item){
				if(item.id==1){
					companyList.splice(index,1);
					return false;
				}
			})
            if(companyList.length>0){
            	var $companyId = $('#companyId');
            	var htmlStr ='<option value="">全部</option>';
            	for(var i =0;i<companyList.length;i++){
            		htmlStr += '<option value="'+companyList[i].id+'">'+companyList[i].name+'</option>'
            		
            	}
            	$companyId.append(htmlStr)
            }
            
		
		}
	});
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
		dialog.alert("请至少选择一个课程");
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
			url:"<%=request.getContextPath()%>/mall/manage/course/putaway.action",
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
		dialog.alert("请至少选择一个课程");
	}
}
//下架1件
function putoutOne(id){
	if(!id){
		return;
	}
	
	doPutout(id+'');
}
//下架
function doPutout(ids){
	
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{ids:ids},
		url:"<%=request.getContextPath()%>/mall/manage/course/putout.action",
		success:function(data){
			if(data!=null&&data!='FAIL'){
				search();
				var idArr = ids.split(',');
				if(idArr.length>1&&data>0){
					dialog.alert("有"+data+"个课程被专题调用，无法下架。");
				}else if(idArr.length==1&&data>0){
					dialog.alert("该课程被专题调用，无法下架。");
				}else{
					dialog.alert("下架成功。");
				}
			}else{
				dialog.alert("下架失败。");
			}
		}
	});




}
function deleteOne(id){
	dialog.confirm("确认删除吗?",function(obj){
		
	   	$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:{
	   			ids : id
	   		},
	   		url:"<%=request.getContextPath()%>/mall/manage/company/course/delete.action",
	   		success:function(data){
	   			if(data!=null&&data!='FAIL'){
					search();
					if(data>0){
						dialog.alert("该课程已上架，无法删除。");
					}else{
						dialog.alert("删除成功。");
					}
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
	    		param.push(val.id+'');
	    	});
	    	var ids = param.join(",");
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{
	    			ids : ids
	    		},
	    		url:"<%=request.getContextPath()%>/mall/manage/company/course/delete.action",
	    		success:function(data){
	    			if(data!=null&&data!='FAIL'){
						search();
						var idArr = ids.split(',');
						if(idArr.length>1&&data>0){
							dialog.alert("存在"+data+"个已上架课程，无法删除。");
						}else if(idArr.length==1&&data>0){
							dialog.alert("该课程已上架，无法删除。");
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


var ids = [];


function search(){
	$('#exampleTable').mmGrid().load({"pageIndex":1});
}

function clearOptions(){
	
	$("#name").val('');
	$("#code").val('');
	$("#companyId").val('');
	$("#status").val('');
	$("#beginTime").val('');
	$("#endTime").val('');
	search();
}




var artDialog;
function chooseCategory(){
	artDialog = dialog({
        title: "选择课程分类",
        url:"<%=request.getContextPath()%>/mall/manage/toChooseCategory.action",
        lock:true,
        height: 500,
		width: 400
	}).showModal();
}

</script>

</head>
<body>

<div class="content">
	<!-- <h3>企业课程管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">企业课程管理</span>
	 </div>
   	  	<div class="course_tree" id="tree">
   		<h2>课程分类</h2>
        <ul id="treePage" class="ztree" style=""></ul>
   
   	</div>
    <div class="right_lesson"  style="">
       <div class="lesson_tab" >
        	<ul>
            	<li ><a href="<%=request.getContextPath()%>/mall/manage/toCourseListPage.action" style="color:black;">普联课程</a></li>
                <li class="li_this">企业课程</li>
               
            </ul>
        </div>
        <div class="button_gr">
            <input type="button" value="上架" onclick="putaway()"/>
            <input type="button" value="下架" onclick="putout()"/>
            <input type="button" value="批量删除" onclick="deleteRows()"/>
        </div>
        <div class="search_3">
        	<p>	
            	<!--  课程编号：
                <input type="text" id="code"/>-->
            	 课程名称：
                <input type="text" id="name"/>
                                       所属公司：
               <select id="companyId" style="width:230px">
                </select></p>
        

        </div>
        <div class="search_3">
        	<p>   	状态：
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
        

    
</div>

</body>
</html>
