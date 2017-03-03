<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>知识库管理</title>

<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css"/>


<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/jquery-validation/jquery.validate.js" type="text/javascript"></script>

<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/plugins/ligerMenu.js" ></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css" type="text/css">

<!-- 弹出框 -->
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/knowledge.css"/>

<style type="text/css">

html, body{
	height: 100%;
}
</style>

<script type="text/javascript">
var isSub = ${isSub};
var currentSubCompanyId = '${USER_BEAN.subCompanyId}';
function keydown(key){
	if(key==13){
		window.event.returnValue = false;
	}
}
$(function(){
	  //格式化日期,
    function formatDate(date,format){
      var paddNum = function(num){
        num += "";
        return num.replace(/^(\d)$/,"0$1");
      }
      //指定格式字符
      var cfg = {
         yyyy : date.getFullYear() //年 : 4位
        ,yy : date.getFullYear().toString().substring(2)//年 : 2位
        ,M  : date.getMonth() + 1  //月 : 如果1位的时候不补0
        ,MM : paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
        ,d  : date.getDate()   //日 : 如果1位的时候不补0
        ,dd : paddNum(date.getDate())//日 : 如果1位的时候补0
        ,hh : paddNum(date.getHours())  //时
        ,mm : paddNum(date.getMinutes()) //分
        ,ss : paddNum(date.getSeconds()) //秒
      }
      format || (format = "yyyy-MM-dd hh:mm:ss");
      return format.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
    } 
	 //初始化表单查询条件中的日期 
    function initDatepicker() {
    	$("#beginTime").datepicker({
    		dateFormat : 'yy-mm-dd',
    		  changeMonth: true,
       	      changeYear: true,
   			 onClose: function( selectedDate ) {
   		        $( "#endTime" ).datepicker( "option", "minDate", selectedDate );
   		      }
    	});

    	$("#endTime").datepicker({
    		dateFormat : 'yy-mm-dd',
    		  changeMonth: true,
       	      changeYear: true,
   			 onClose: function( selectedDate ) {
   		        $( "#beginTime" ).datepicker( "option", "maxDate", selectedDate );
   		      }
    	});
    }
    initDatepicker();
    
	 
	 <!-- 以下js为处理知识分类树的方法 begin sd -->
	 
	 var slCategoryId;
	    var  menu = $.ligerMenu({ top: 100, left: 100, width: 120, items:
					            [
						            { text: '新增知识分类' ,id:'addMenu', click: itemclick, icon: 'add' },
						            { text: '修改知识分类',id:'modifyMenu', click: itemclick },
						            { text: '删除知识分类',id:'deleteMenu', click: itemclick },
						            { line: true },
						            { text: '知识批量转移',id:'changeMenu', click: itemclick }
					            ]
				            });

		// 模型数据tree的set参数设置
		var treeSet = {
				edit: {
					enable: true,
					showRemoveBtn: false,
					showRenameBtn: false,
					drag: {
						isCopy: false,
						prev: false,
						next: false,
						inner: false,
						zIndex:1000
					}
				},
				data: {
					keep: {
						parent: true,
						leaf: true
					},
					simpleData: {
						enable: true,
						idKey: "categoryId",
						pIdKey: "parentId",
						rootPId: 0
					},
					key:{
						title: "categoryName",
						name:"categoryName"
					}
				},
				async: {
					enable: true,
					url:"<%=request.getContextPath()%>/knowledge/asyncCategoryTree.action",
/* 					autoParam: ["id", "name"]
 */				},
				callback: {
					 
					onAsyncSuccess: function(event, treeId, treeNode, msg){
						var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
						var o_nodes = treeObj.getNodes();//.getNodesByParam("id", 1, null);
						if (o_nodes.length>0) {
							treeObj.expandNode(o_nodes[0], true, false, true);
						}
						//treeObj.expandAll(true);
					}
					,
					onRightClick:function(e, treeId, treeNode){
						if (isSub == 1) {
							return false;
						}
						//调出右键
						slCategoryId = treeNode.categoryId;
						var subCompanyId = treeNode.subCompanyId;
						menu.show({ top: e.pageY, left: e.pageX });
							if(slCategoryId==0 || slCategoryId==-2){//根节点、事故案例节点，禁用右键所有功能菜单
								menu.setEnabled("addMenu");
								menu.setDisabled("deleteMenu");
								menu.setDisabled("modifyMenu");
								menu.setDisabled("changeMenu");
							}else{//非根节点的时候，按条件使用右键菜单
								if(currentSubCompanyId!=1){//当前为一般企业时，不可操作普联下的事故案例分类
									if(currentSubCompanyId!=subCompanyId){//非本企业的,即普联的
										menu.setDisabled("addMenu");
										menu.setDisabled("deleteMenu");
										menu.setDisabled("modifyMenu");
										menu.setDisabled("changeMenu");
									}else{
										menu.setEnabled("addMenu");
										menu.setEnabled("deleteMenu");
										menu.setEnabled("modifyMenu");
										menu.setEnabled("changeMenu");
									}
								}else{//当前为普联f
									menu.setEnabled("addMenu");
									menu.setEnabled("deleteMenu");
									menu.setEnabled("modifyMenu");
									menu.setEnabled("changeMenu");
								}
							}
						
		                return false;
					}	,
					onClick:function (e, treeId, treeNode){
						
						var ids = [];
						var fn =function(treeNode){
							ids.push(treeNode.categoryId);
							if(treeNode.isParent){
								var nodes = treeNode.children;
								for(var i=0;i<nodes.length;i++){
									if(nodes[i].isParent){
										fn(nodes[i]);
									}else{
										ids.push(nodes[i].categoryId);
									}
								}
							}
						}
						fn(treeNode);
						$("#categoryIds").val(ids.join(","))
						
						search();
					}
				},
				view: {
					fontCss : function(){
						
					},//个性化设置ztree的节点高亮效果
					showTitle: true,
					dblClickExpand: false,
					addDiyDom: addDiyDom
				}
			};
		
		$.fn.zTree.init($("#categoryTree"), treeSet,${categoryList});//初始化模型树
		
		var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
		var o_nodes = treeObj.getNodes();//.getNodesByParam("id", 1, null);
		if (o_nodes.length>0) {
			treeObj.expandNode(o_nodes[0], true, false, true);
		}
	
		//表格
		var mm = $('#exampleTable').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:"<%=request.getContextPath()%>/knowledge/getKLListByMap.action",
	    	width: 'auto',
	    	height: 'auto',
	    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
	    	showBackboard: false,
	        checkCol: true,
	        multiSelect: true,
	    /*     indexCol: true,  //索引列 */
	        params:function(){
	        	var param = new Object();
	        	
	        	param.knowledgeName = escapeForSql($.trim($("input[name='knowledgeName']").val()));
	        	param.categoryName = escapeForSql($.trim($("input[name='categoryName']").val()));
	        	if("-1"!=$("select[name='isPublish']").val()){
	        		param.isPublish=$("select[name='isPublish']").val();
	        	};
	        	param.userName=$.trim($("input[name='userName']").val());
	        	param.beginTime =$.trim($("input[name='beginTime']").val());
	        	param.endTime =$.trim($("input[name='endTime']").val());
	        	//添加分类集合查询
	        	param.categoryIds=$("input[name='categoryIds']").val();
	        	return param;
	        },
	        cols: [{title: 'knowledgeId', name: 'knowledgeId',align:'center', width:60,height:40, hidden:true},
				   {title: '知识名称', name: 'knowledgeName',align:'center', width:50,height:40,renderer:function(val, item, rowIndex){
					   
					   return "<span class='cate-style' style='color: #0C9C92;cursor:pointer'title='"+val+"' onclick='detailKL("+item.knowledgeId+")'> "+val+" </span>";
				   }},
				   {title: '知识分类',align:'center', name: 'categoryName', width:30,renderer:function(val, item, rowIndex){
					    var catePath =  getZTreePathName(treeObj.getNodesByParam("categoryId", item.categoryId, null)[0],"categoryName");
						return "<span class='cate-style' title='"+catePath+"'>"+catePath+"</span>";
				   }},
				   {title: '上传者',align:'center', name: 'userName', width:30,
					   renderer:function(val, item, rowIndex){
						   if(currentSubCompanyId==1){//普联管理的知识管理
							   return val;
						   }else{//一般企业的知识管理
							   var subCompanyId = item.subCompanyId;
							   if(subCompanyId==1){//普联上传的 展示‘普联’
								   return '普联';
							   }else{
								  return val;
							   }
						   }
					 }
						  
				   },
				   {title: '上传时间',align:'center', name: 'createTime', width:50,renderer:function(val, item, rowIndex){
					   var newDate = new Date(val);
					   return formatDate(newDate,"yyyy-MM-dd hh:mm:ss");
				   }},
				   {title: '状态',align:'center', name: 'isPublish', width:20,renderer:function(val, item, rowIndex){
					   if(val == 1){
						   return "编辑中";
					   } else if(val==2){
						   return "已发布";
					   	}
					  }},

				   {title: '操作',align:'center', name: 'knowledgeId', width:80, renderer:function(val, item, rowIndex){
					   var subCompanyId = item.subCompanyId;
					   var isAccident = item.isAccident;
					   //一般企业在查看普联的实务案例资源时，不能进行操作
					   if(subCompanyId!=currentSubCompanyId && isAccident==1){//普联的事故案例
						   return "";
					   }
					   var buttonStr = '<span class="span_btn" onclick="editKL('+val+','+item.uploadType+')" >修改</span>' +
					   				   '<span class="span_btn" onclick="deleteKL('+val+')" >删除</span>';
					   				   
					    if(item.isPublish==1&&item.isOpen==1){//已经发布过的，不允许再次发布||或者必须公开的才能发布
					    	var title = item.refuseMemo||"";
					    	buttonStr += '<span class="span_btn " onclick="publishKL('+val+')"  title='+title+'>发布</span>'; 
					    }else{
					    	if(item.isOpen!=1){
						    	buttonStr +='<span class="span_btn" onclick="openKL('+val+')" >公开</span>'; 
					    	}else{
						    	buttonStr +='<span class="span_btn gray" >发布</span>'; 
					    	}
					    }
					    buttonStr += '<span class="span_btn" onclick="switchCategory('+val+","+item.categoryId+')" >更改分类</span>'
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
	  //右键菜单
    function itemclick(item, i){
    	//右键菜单。0为新增，1为修改，2为删除。3，为批量转移
    	switch (i) {
			case 0://新增一个节点
				addCategory()				
				break;
	
			case 1://修改知识分类节点
				modifyCategory();
				break;
			case 2:
				deleteCategory();		
					break;
			case 3://批量转移数据
				
				batchChangeCate();
				break;
			 
			default:
				break;
		} 
    }
	  
	  //添加知识分类节点。
	function addCategory(){
		 
		 var param = {
					title:"新增分类",
					content:"<div id='addCategory' onkeydown=\"keydown(event.keyCode)\"></div>",
					width:400,
					okValue: '确定',
				    ok: function(){
				  	   if(validateCate("add")){
							var param = {
									parentId:slCategoryId,
									categoryName:$.trim($("input[name='addCateName']").val())
							} 
				  			$.ajax({
					    		type:"POST",
					    		async:false,  //默认true,异步
					    		data:param,
					    		url:"<%=request.getContextPath()%>/knowledge/saveKLCategory.action",
					    		success:function(data){
					    			if("SUCCESS"==data){
					    				dialog.alert("操作成功！");
					    				treeObj.reAsyncChildNodes(null, "refresh");
					    				treeObj.expandAll(true);
					    				
					    			}else{
					    				dialog.alert("操作失败！");
					    			}
					    	    }
					    	});
				  	   }else{
				  		   return false;
				  	   }
				    	
				    },
				    cancelValue: '取消',
				    cancel: function () {}
			};
			var d = dialog(param);
			d.showModal();
			
		var treeNode = treeObj.getNodesByParam("categoryId", slCategoryId, null)[0];
		
		$("#addCategory").html(function(){
			var html ="<ul class='category'><form id='categoryForm'>";
				html+="<li ><label class='label'>父节点:</label><label>"+treeNode.categoryName+"</label></li>";
				html+="<li ><label class='label'>节点名称:</label><input type='text' name='addCateName' class='text'></label></li>";
				html+="</form></ul>";
				return html;
		})
	}
	  
	  //删除节点
	 function deleteCategory(){
		 //首先判断下是否存在子孙节点
		var treeNode = treeObj.getNodesByParam("categoryId", slCategoryId, null)[0];
		if(treeNode.isParent&&treeNode.children.length>0){
			dialog.alert("存在子节点，不允许删除！")
			return ;
		}
		 //进行校验是否可以删除
		 $.ajax({
				type:"POST",
				async:true,  //默认true,异步
				data:{categoryId:slCategoryId},
				url:"<%=request.getContextPath()%>/knowledge/isAllowDelete.action",
				success:function(data){
					if("SUCCESS"==data){
						dialog.confirm("确认删除吗？",function(){
						 $.ajax({
								type:"POST",
								async:true,  //默认true,异步
								data:{categoryId:slCategoryId},
								url:"<%=request.getContextPath()%>/knowledge/deleteCategory.action",
								success:function(data){
									if("SUCCESS"==data){
										dialog.alert("操作成功！");
										treeObj.removeNode(treeNode);
									}else{
										dialog.alert("操作失败！");
										}
									}
								})
						})
					}else{
						dialog.alert("存在关联的知识，请先移除知识后再做删除！")
					}
			    }
			}); 
	 } 
	  
	  function getSLCategoryIds(){
		  
		var treeNode = treeObj.getNodesByParam("categoryId", slCategoryId, null)[0];
		  var ids = [];
			var fn =function(treeNode){
				ids.push(treeNode.categoryId);
				if(treeNode.isParent){
					var nodes = treeNode.children;
					for(var i=0;i<nodes.length;i++){
						if(nodes[i].isParent){
							fn(nodes[i]);
						}else{
							ids.push(nodes[i].categoryId);
						}
					}
				}
			}
			fn(treeNode);
			return ids.join(",");
	  }
	  
	  //批量转移知识
	 function batchChangeCate(){
		  
			//打开设置框
			 dialog({
				 	url: "<%=request.getContextPath()%>/knowledge/getKLCategoryTree.action?categoryId=0&roleType=1",
					title: '修改分类',
					height: 450,
					width: 300,
					//height: 120,
					id:"treeDialog",
					button: [ { value: '确定', 
						callback: function () { 
							var iframe = this.iframeNode.contentWindow;
							var id = iframe.categoryId.value;
							
							if(!id||id=="-1"){
								dialog.alert("请选择分类！");
								return false;
							}
							
							var param= {};
							param.categoryId=id;
							param.oldCategoryId=getSLCategoryIds();

							$.ajax({
					    		type:"POST",
					    		async:true,  //默认true,异步
					    		data:param,
					    		url:"<%=request.getContextPath()%>/knowledge/updateKLCategory.action",
					    		success:function(data){
					    			if("SUCCESS"==data){
										dialog.alert("保存成功");
					    			}else{
					    				dialog.alert("保存失败");
					    			} 
					    			//search();
					    	    }
					    	});
						}
				 }, { value: '取消', callback: function (item, dialog) {
							 } } ] 
					}).showModal();
	 
	 } 
	  
	  
	 //修改节点
	 function modifyCategory(){
		 
		 var param = {
					title:"修改分类",
					content:"<div id='modifyCategory' onkeydown=\"keydown(event.keyCode)\"></div>",
					width:400,
					okValue: '确定',
				    ok: function(){
				    	
				  	   if(validateCate()){
							var param = {
									categoryId:slCategoryId,
									categoryName:$("input[name='modifyCateName']").val()
							} 
				  			$.ajax({
					    		type:"POST",
					    		async:false,  //默认true,异步
					    		data:param,
					    		url:"<%=request.getContextPath()%>/knowledge/updateKLCategoryName.action",
					    		success:function(data){
					    			if("SUCCESS"==data){
					    				dialog.alert("操作成功！");
					    				treeObj.reAsyncChildNodes(null, "refresh");
					    			}else{
					    				dialog.alert("操作失败！");
					    			}
					    	    }
					    	});
				  	   }else{
				  		   return false;
				  	   }
				    	
				    },
				    cancelValue: '取消',
				    cancel: function () {}
			};
			var d = dialog(param);
			d.showModal();
			
		var treeNode = treeObj.getNodesByParam("categoryId", slCategoryId, null)[0];
		
		$("#modifyCategory").html(function(){
			var html ="<ul class='category'><form id='categoryForm'>";
				html+="<li ><label class='label'>父节点:</label><label>"+treeNode.getParentNode().categoryName+"</label></li>";
				html+="<li ><label class='label'>节点名称:</label><input type='text' name='modifyCateName' class='text' value='"+treeNode.categoryName+"'></label></li>";
				html+="</form></ul>";
				return html;
		})
	 } 
	  
	  //检查分类名称是否重复 true为通过校验
	 function checkCategoryName(type){
		 var param ={};
		 var treeNode = treeObj.getNodesByParam("categoryId", slCategoryId, null)[0];
		  if("add"==type){
			  param = {
						parentId:treeNode.categoryId,
						categoryName:$("input[name='addCateName']").val()
					} 
		  }else{
				if(treeNode.categoryName==$("input[name='modifyCateName']").val()){
					return true;
				}
				
				 param = {
						parentId:treeNode.parentId,
						categoryName:$("input[name='modifyCateName']").val()
				} 
		  }
			var ret = false;
			$.ajax({
	    		type:"POST",
	    		async:false,  //默认true,异步
	    		data:param,
	    		url:"<%=request.getContextPath()%>/knowledge/checkName.action",
	    		success:function(data){
	    			if("SUCCESS"!=data){
	    				/* artDialog.alert("名称重复"); */
	    				ret = true;
	    			}
	    	    }
	    	});
			return ret;
		}
		
		//检验检测
		 jQuery.validator.addMethod("checkModifyName", function(value, element) {
			    return this.optional(element) ||checkCategoryName("modify");
			}); 
		//检验检测
		 jQuery.validator.addMethod("checkAddName", function(value, element) {
			    return this.optional(element) ||checkCategoryName("add");
			}); 
		
		//进行表单校验的
		function validateCate(type){
			var  validate = null;
			  if("add"==type){

					  validate = $("#categoryForm").validate({ 
				  		   focusCleanup:true,      
				  	       rules: {  
				          		addCateName:{
				          			 required:true,  
				                 	 rangelength:[2,10],
				                 	checkAddName:true
				          		}
				  	       },  
				  	       messages:{  
				  	     		addCateName:{
				  	     		  required:"请输入名称",  
				                  rangelength:"长度必须为2-10个字符",
				                  checkAddName:"名称重复，请重新输入。"
				  	     	}
				  	       }
				  	   })
				  
				  
			  }else{

					 validate = $("#categoryForm").validate({ 
				  		   focusCleanup:true,      
				  	       rules: {  
				  	    	 modifyCateName: {  
				                  required:true,  
				                  rangelength:[2,10],
				                  checkModifyName:true
				          		}
				  	       },  
				  	       messages:{  
				  	    	 modifyCateName: {  
				                  required:"请输入名称",  
				                  rangelength:"长度必须为2-10个字符",
				                  checkModifyName:"名称重复，请重新输入。"
				       	   		}
				  	       }
				  	   })
				  
			  }
			
			return validate.form();
		}
		
	 <!--以上js为处理知识分类树的方法 end -->
});

//查询
function search(){
	$('#exampleTable').mmGrid().load({"page":1});
}

//重置查询条件
function resetSearch(){
	 //将查询条件归置为空
	 $("input[name='knowledgeName']").val("");
	 $("input[name='categoryName']").val("") ;
	 $("input[name='userName']").val("");
	 $("input[name='beginTime']").val("");
	 $("input[name='endTime']").val("") ; 
	 $("select[name='isPublish']").val(-1);
	 $("input[name='categoryIds']").val("");//主要针对右侧选择框
	 var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
	 treeObj.cancelSelectedNode();

	$('#exampleTable').mmGrid().load({"page":1});
}

//发布该知识
function publishKL(klId){
	dialog.confirm('确认发布吗？', function(){ //$.ligerD
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{knowledgeId:klId},
			url:"<%=request.getContextPath()%>/knowledge/publish.action",
			success:function(data){
				if("SUCCESS"==data){
					dialog.alert("发布成功",function(){
						search();
					});
				}else{
					dialog.alert("发布失败");
				}
				
		    }
		});
	})
}
//公开知识
function openKL(klId){
	dialog.confirm('确认公开吗？', function(){ //$.ligerD
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{knowledgeId:klId},
			url:"<%=request.getContextPath()%>/knowledgeLibraryInfo/toPublic.action",
			success:function(data){
					search();
		    }
		});
	})
}

//删除知识
function deleteKL(id){
	
	dialog.confirm('确认删除吗？', function(yes){ 
    	$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
    		data:{knowledgeId:id},
    		url:"<%=request.getContextPath()%>/knowledge/deleteKL.action",
    		success:function(data){
    			if(data == "SUCCESS"){
    				dialog.alert('删除成功！');
    				search();
    			}else{
    				dialog.alert('删除失败！');
    			}
    	    }
    	});
	});
}

//批量删除知识
function deleteBatchKL(){
	
	var rows = $('#exampleTable').mmGrid().selectedRows();
	
	if(rows.length==0){
		dialog.alert('请选择需要删除的知识！');
		return ;
	}
	
	dialog.confirm('确认批量删除吗？', function(yes){ 
			
			var	ids = [];
			for(var i=0;i<rows.length;i++){
				if(rows[i].isAccident==1 && rows[i].subCompanyId!=currentSubCompanyId){//非当前企业的事故案例分析不能操作
					dialog.alert('普联资源不可操作');
					return ;
				}
				ids.push(rows[i].knowledgeId)
			}
	/* 		
			contentType:"application/json; charset=utf-8",
			data:JSON.stringify(param), */
    		
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{"ids":ids.join("|")},
	    		url:"<%=request.getContextPath()%>/knowledge/deleteBatch.action",
	    		success:function(data){
	    			if(data == "SUCCESS"){
	    				dialog.alert('删除成功！', function(){
		    				search();
	    				});
	    			}else{
	    				dialog.alert('删除失败！');
	    			}
	    	    }
	    	});
	});
}
//批量更新分类
function  batchUpdate(){
	
	var rows = $('#exampleTable').mmGrid().selectedRows();
	
	if(rows.length==0){
		dialog.alert('请选择需要修改的知识！');
		return ;
	}
	
	dialog({
		url: "<%=request.getContextPath()%>/knowledge/getKLCategoryTree.action?categoryId=0&roleType=1",
		height: 450,
		title:"分类列表",
		width: 300,
		button: [ { value: '确定', 
						callback: function () { 
							
							var iframe = this.iframeNode.contentWindow;
							var categoryId = iframe.categoryId.value;
							
							if(!categoryId||categoryId=="-1"){
								dialog.alert("请选择分类！");
								return false;
							}
							
							var	ids = [];
							for(var i=0;i<rows.length;i++){
								if(rows[i].isAccident==1 && rows[i].subCompanyId!=currentSubCompanyId){//非当前企业的事故案例分析不能操作
									dialog.alert('普联资源不可操作');
									return ;
								}
								ids.push(rows[i].knowledgeId)
							}
							
							var param= {};
							param.categoryId=categoryId;
							param.ids=ids.join("|");

							$.ajax({
					    		type:"POST",
					    		async:true,  //默认true,异步
					    		data:param,
					    		url:"<%=request.getContextPath()%>/knowledge/updateBatch.action",
					    		success:function(data){
					    			if("SUCCESS"==data){
										search();
					    			}else{
					    				dialog.alert("保存失败");
					    			}
					    			//search();
					    	    }
					    	});
						}
				 }, { value: '取消', callback: function () {} } ] }).showModal();
}
//修改分类
function switchCategory(klId,categoryId){
	
	dialog({
		url: "<%=request.getContextPath()%>/knowledge/getKLCategoryTree.action?categoryId="+categoryId+"&roleType=1",
		height: 450,
		title:"分类列表",
		width: 300,
		button: [ { value: '确定', 
						callback: function () { 
							//获取到选择的值 
							var iframe = this.iframeNode.contentWindow;
							var categoryId = iframe.categoryId.value;
							
							if(!categoryId||categoryId=="-1"){
								dialog.alert("请选择分类！");
								return false;
							}
							
							var param= {};
							param.categoryId=categoryId;
							param.knowledgeId=klId;

							$.ajax({
					    		type:"POST",
					    		async:true,  //默认true,异步
					    		data:param,
					    		url:"<%=request.getContextPath()%>/knowledge/updateOneKLCategory.action",
					    		success:function(data){
					    			if("SUCCESS"==data){
										search();
					    			}else{
					    				dialog.alert("保存失败");
					    			}
					    			//search();
					    	    }
					    	});
						}
				 }, { value: '取消', callback: function () {  } } ] }).showModal();
}


</script>

</head>
<body style="overflow-x:hidden;">
<input id='categoryIds' type="hidden" name='categoryIds'> 
<div id="" class='content'>	
	<!-- <h3>知识管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">知识管理</span>
	</div>
	<div  class='content_2 table fn-clear' >
		<div class='tree ztree'>
			<h2 class="tree-tit">
                知识库分类</h2>
			<div  id='categoryTree' style='margin: 0px 0 0 0;height: 550px;overflow: auto;'>
			
			</div>
		</div>	
		<div class='right_lesson '>
			<div class="op-area fn-clear">
	            <input type="button" class="red-btn btn" value="上传知识" onclick="uploadKnowledge();">
	            <input type="button" class="red-btn btn" value="创建知识" onclick="createKnowledge();">
	            <input type="button" id="btnDelete" class="red-btn btn"  value="批量删除" onclick="deleteBatchKL();" >
	            <input type="button" id="modifySystem" class="red-btn btn" value="更改分类" onclick="batchUpdate();"  >
	        </div>
			<div class="search_3" style='margin-bottom: -1px;'>
	                    	<span>知识名称：</span><input name='knowledgeName' type="text" class="text">
	                        <span>知识库分类：</span><input  name='categoryName' type="text" class="text">
	                        <span>状态：</span><select  name='isPublish' class="text">
	                        						<option value='-1'>全部显示</option>
	                        						<option value='1'>编辑中</option>
	                        						<option value='2'>已发布</option>
	                        					</select>
	                   	   <input type="button" class="gray-btn btn" onclick='resetSearch()' value="重置">
	                       <input type="button" class="red-btn btn" onclick='search()' value="查询">
	           </div>
	          <div class="search_3" style='margin-bottom: -1px;'>
	           <span>上传者：</span><input name='userName' type="text"  class="text">
	           <span>上传时间：</span><input id='beginTime' name='beginTime' type="text" class="text"/>&nbsp至&nbsp<input id='endTime' name='endTime'  type="text" class="text"/>
	           </div>
			<div style="width:100%;float: left">
				<div id="exampleTable" style="margin-top:10px;" ></div>
				<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
			</div>
		</div>
 	</div>
</div>
<script  type='text/javascript'>

	//上传知识
	function uploadKnowledge(){
		
		location = "<%=request.getContextPath()%>/knowledge/upload.action";
	}
	
	//创建知识
	function createKnowledge(){
		
		location = "<%=request.getContextPath()%>/knowledge/create.action";
	}
	
	//知识详情
	function detailKL(id){
		
		//跳转知识详情
		//location = "<%=request.getContextPath()%>/knowledge/toKnowledgeLookUp.action?knowledgeId="+id;
		location = "<%=request.getContextPath()%>/approve/objDetail.action?type=2&id="+id;
	}
	
	function editKL(id,type){
		if(type==2){
			location = "<%=request.getContextPath()%>/knowledge/edit.action?knowledgeId="+id+"&type=editCreate";
		}else{
			location = "<%=request.getContextPath()%>/knowledge/edit.action?knowledgeId="+id+"&type=editUpload";

		}
	}
	</script>
</body>
</html>
