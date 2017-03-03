<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>角色管理</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- blackBox -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/blackBox/blackbox.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/blackBox/jquery.blackbox.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>

<style type="text/css">
.mmGrid,.mmPaginator{
    
}

.png_table{margin-bottom:8px;}

.button_a{cursor:pointer;border:1px solid #AAAAAA;padding:3px 12px 3px 12px;text-decoration:none;
	-moz-border-radius: 10px;      /* Gecko browsers */
    -webkit-border-radius: 10px;   /* Webkit browsers */
    border-radius:10px;            /* W3C syntax */
    margin-right:10px;
    text-decoration:none;
}
.button_a:HOVER{background-color:#EEEEEE;}
.button_a img{height:16px;vertical-align:-2px;border:0px;}
.button_a span{line-height:30px;font-size:14px;color:#111111;font-family::Microsoft YaHei;}

.tree_table{border:1px solid #666666;width:100%;}
.tree_table .left_tree{border-right:1px solid #666666;width:180px;}

.tab_span{font-size:13px;color:#111111;font-family::Microsoft YaHei;padding:7px 15px;border:1px solid #666666;margin-left:50px;cursor:pointer;border-bottom:none;}
.tab_span_checked{background-color:#D7D7D7;}

.right_div{margin:7px 20px 10px 20px;border:1px solid #666666;z-index:100;}

#user_div{display:none;}

#have_role_div_span{font-size:14px;font-weight:bold;padding:5px;margin-top:10px;}
.have_role_table{background-color:#666666;margin-top:5px;font-size:13px;}
.have_role_table td{background-color:#FFFFFF;height:20px;}

button.btu{padding:0px 12px;line-height:25px;margin-right:10px;cursor:pointer;}

.dialog.alertBox_text span{font-weight:normal;letter-spacing:0;}
.text_red{color:red;}

</style>

<script type="text/javascript">
var dialog.alertBox = new BlackBox(); //弹出框对象
var roleNodeChecked = null; //角色数选择的节点
//var departmentList = ${departmentList}; //部门
var roleList = null; //角色
var roleObj = {}; //角色
var dialog.alertWin = null; //弹出框

$(function(){
	
	//获得所有角色
	getAllRole();
	
	//获取所有权限页面
	getAllPage();
	
	//tab的动作
	$(".tab_span").click(function(){
		if($(this).hasClass("tab_span_checked")){
			//dialog.alert("self checked");
		}else{
			//去除颜色
			$(".tab_span").removeClass("tab_span_checked");
			//加上颜色
			$(this).addClass("tab_span_checked");
			
			if($(this).attr("role") == "role"){
				$("#user_div").hide();
				$("#role_div").show();
			}else{
				$("#role_div").hide();
				$("#user_div").show();
				roleUserTable(roleNodeChecked.id);
			}
		}
	});
	
	//
	$("#table_div").width($("#role_div").width());
});

function getAllRole(){
	//获得所有角色
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageRole/getAllManageRole.action",
		success:function(data){
			//所有角色
			roleList = data;
			
			$.each(roleList, function(index, val){
				roleObj[val.id+''] = val.name;
			});
			
			var setting = {
					view: {
						selectedMulti: false
					},
					callback: {
						beforeClick: function(treeId, treeNode, clickFlag){
							if(!treeNode.id){
								return false;
							}
						},
						onClick: function(e, treeId, treeNode, clickFlag){
							//dialog.alert(1);
							roleNodeChecked = treeNode;
							//把右边的选中
							$.ajax({
								type:"POST",
								async:true,  //默认true,异步
								data:"roleId="+roleNodeChecked.id,
								url:"<%=request.getContextPath()%>/manageRole/getManagePageByRole.action",
								success:function(data){
									
									var treeObj = $.fn.zTree.getZTreeObj("treePage");
									
									//取消勾选
									$.each(treeObj.getCheckedNodes(true), function(index, val){
										treeObj.checkNode(val, false, true);
									});
									
									var haveData = [];
									$.each(data, function(index, val){
										//dialog.alert(JSON.stringify(val));
										if(val.parentId){
											//dialog.alert(JSON.stringify(treeObj.getNodeByTId(val.id+"")));
											haveData.push(treeObj.getNodeByParam("id", val.id, null));	
										}
									});
									//dialog.alert(JSON.stringify(haveData.length));
									
									//给权限表格加上数据
									//$("#have_role_div_span").text(roleNodeChecked.name+"拥有的权限：");
									
									for (var i=0; i < haveData.length; i++) {
										if(haveData[i].levelType == 1){
											treeObj.checkNode(haveData[i], true, true);
										}
									}
								}
							});
							//角色成员
							roleUserTable(roleNodeChecked.id);
						}
					}
			};
			var zNodes =[{name:"角色列表", open:true, children:null}];
			zNodes[0].children = data;
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	    }
	});
}

function getAllPage(){
	//获取所有权限页面
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageRole/getAllManagePage.action",
		success:function(data){
			
			//打开
			$.each(data, function(index, val){
				val.open = true;
				
				//权限表格
				var trHtml = "<tr index='"+val.id+"' align='center' valign='middle' style='display:none;'><td class='index'>"+(index+1)+"</td><td class='roleName'>"+val.name+"</td></tr>";
				$(".have_role_table").append(trHtml);
			});
			
			var setting = {
					data: {
						key: {
							url: "xUrl"
						},
						simpleData: {
							enable: true,
							pIdKey: "parentId"
						}
					},
					check: {
						enable: true
					},
					callback: {
						beforeClick: function(treeId, treeNode, clickFlag){
							return false;
						}
					}
			};
			$.fn.zTree.init($("#treePage"), setting, data);
	    }
	});
}

//增加权限
function addRow(){
	$("#BlackBox_backVal").val("");
	dialog.alertBox.prompt("<div class='dialog.alertBox_text'>请输入新角色的名称(<span>名称最多10个字符</span>)</div>", function (data) {
        if (data) {
            
        	$.ajax({
        		type:"POST",
        		async:true,  //默认true,异步
        		url:"<%=request.getContextPath()%>/manageRole/addManageRole.action",
        		data:"name="+data,
        		success:function(){
        			//获得所有角色
        			getAllRole();
        	    }
        	});
        }
    }, {
    	title:"提示信息",
    	verify: function (data) {
    		
    		//dialog.alert(JSON.stringify(roleList));
    		for(var i=0; i<roleList.length; i++){
    			if(data == roleList[i].name){
    				dialog.alert("此角色名称已经存在！");
        			return false;
    			}
    		}
    			
    		if(data.length > 10){
    			$(".dialog.alertBox_text").find("span").addClass("text_red");
    			return false;
    		}else{
    			$(".dialog.alertBox_text").find("span").removeClass("text_red");
    			return true;
    		}
        }
    });
}

//删除权限
function deleteRow(){
	
	if(roleNodeChecked){
		dialog.alertBox.confirm("<div class='dialog.alertBox_text'>确认删除吗？</div>", function(data){
		    if(data){
		    	$.ajax({
		    		type:"POST",
		    		async:true,  //默认true,异步
		    		url:"<%=request.getContextPath()%>/manageRole/delManageRole.action",
		    		data:"id="+roleNodeChecked.id,
		    		success:function(){
		    			//获得所有角色
		    			getAllRole();
		    	    }
		    	});
		    }
		}, {title:"提示信息"});
	}else{
		dialog.alertBox.dialog.alert("<div class='dialog.alertBox_text'>请先选择一个角色！</div>", {title:"提示信息"});
	}
	
}

//修改权限
function editRow(){
	if(roleNodeChecked){
		$("#BlackBox_backVal").val(roleNodeChecked.name);
		dialog.alertBox.prompt("<div class='dialog.alertBox_text'>请输入角色的新名称(<span>名称最多10个字符</span>)</div>", function (data) {
	        if (data) {
	            
	        	$.ajax({
	        		type:"POST",
	        		async:true,  //默认true,异步
	        		url:"<%=request.getContextPath()%>/manageRole/updateManageRole.action",
	        		data:"name="+data+"&id="+roleNodeChecked.id,
	        		success:function(){
	        			//获得所有角色
	        			getAllRole();
	        	    }
	        	});
	        }
	    }, {
	    	title:"提示信息",
	    	verify: function (data) {
	    		
	    		//只可以是数字，中文，字母
	    		var withOutChinese = /^([\u4E00-\u9FA5]|\w)*[^_@]$/;
	    		if(!withOutChinese.test(data)){
	    			dialog.alert("名称只能包含中文，数字，字母！");
        			return false;
	    		}
	    		
	    		for(var i=0; i<roleList.length; i++){
	    			if(data == roleList[i].name && data != roleNodeChecked.name){
	    				dialog.alert("此角色名称已经存在！");
	        			return false;
	    			}
	    		}
	    		
	    		if(data.length > 10){
	    			$(".dialog.alertBox_text").find("span").addClass("text_red");
	    			return false;
	    		}else{
	    			$(".dialog.alertBox_text").find("span").removeClass("text_red");
	    			return true;
	    		}
	        }
	    });
	}else{
		dialog.alertBox.dialog.alert("<div class='dialog.alertBox_text'>请先选择一个角色！</div>", {title:"提示信息"});
	}
}

//保存角色的权限页面
function saveRolePage(){
	
	if(roleNodeChecked){
		
		dialog.alertBox.confirm("<div class='dialog.alertBox_text'>确认保存吗？</div>", function(agree){
		    if(agree){
		    	dialog.alertBox.load("index", function () {
			        //console.log("第一个index载入内容载入完毕");
			    });
				
				var treeObj = $.fn.zTree.getZTreeObj("treePage");
				//被勾选的对象
				//dialog.alert(JSON.stringify(treeObj.getCheckedNodes(true)));
				/* $.each(treeObj.getCheckedNodes(true), function(index, val){
					dialog.alert(val.name);
				}); */
				
				//删除以前的勾选
				$.ajax({
					type:"POST",
					async:true,  //默认true,异步
					url:"<%=request.getContextPath()%>/manageRole/delManagePageByRole.action",
					data:"roleId="+roleNodeChecked.id,
					success:function(){
						//增加现在选择的节点
						var param = [];
						
						param.push({"name":"roleId", "value":roleNodeChecked.id});
						
						$.each(treeObj.getCheckedNodes(true), function(index, val){
							param.push({"name":"pageIds", "value":val.id});
						});
						//dialog.alert(JSON.stringify(param));
						$.ajax({
							type:"POST",
							async:true,  //默认true,异步
							url:"<%=request.getContextPath()%>/manageRole/addManagePageByRole.action",
							data:param,
							success:function(data){
								//dialog.alert(data);
								dialog.alertBox.ready("index");
								if(data == "SUCCESS"){
				    				dialog.alertBox.dialog.alert("<div class='dialog.alertBox_text'>保存成功！</div>", {title:"提示信息"});
				    			}else{
				    				dialog.alertBox.dialog.alert("<div class='dialog.alertBox_text'>保存失败！</div>", {title:"提示信息"});
				    			}
								
						    }
						});
				    }
				});
		    }
		}, {title:"提示信息"});
	}else{
		dialog.alertBox.dialog.alert("<div class='dialog.alertBox_text'>请先选择一个角色，并勾选这个角色可以拥有的权限！</div>", {title:"提示信息"});
	}
}

//角色成员
function roleUserTable(roleId){
	
	//dialog.alert($('#exampleTable').html());
	
	if($('#exampleTable').html() != ""){
		//表格已经存在
		var param = new Object();
		param.roleId = roleId;
		param.page = 1;
		//dialog.alert(JSON.stringify(param));
		$('#exampleTable').mmGrid().load(param);
	}else{
		//表格
		$('#exampleTable').mmGrid({
	    	root:'rows',
			url:"<%=request.getContextPath()%>/manageUser/getManageUserByMap.action",
			params: function(){
				var param = new Object();
				param.roleId = roleNodeChecked.id;

				return param;
			},
	    	width: 'auto',
	    	height: 'auto',
	        //checkCol: true,
	        fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    		showBackboard: false,
	        //nowrap: true,
	        indexCol: true,  //索引列
	        cols: [{title: 'ID', name: 'id', width:60, hidden:true},
				   {title: '姓名', name: 'name', width:60},
				   {title: '账号', name: 'userId', width:80},
				   /* {title: '部门', name: 'departmentId', width:80, renderer:function(val, item, rowIndex){
				       if(departmentList && departmentList.length > 0){
				    	   for(var i=0; i<departmentList.length; i++){
				    		   if(departmentList[i].id == val){
				    			   return departmentList[i].name;
				    		   }
				    	   }
				       }
				       
				       return "";
				   }},
				  {title: '职位', name: 'position', width:40},
				   {title: '工作状态', name: 'workStatus', width:60, renderer:function(val, item, rowIndex){
					   if(val == 0){
						   return "在职";
					   }else if(val == 1){
						   return "离职";
					   }
				   }}, */
				   {title: '性别', name: 'gender', width:50, renderer:function(val, item, rowIndex){
					   if(val == 0){
						   return "男";
					   }else if(val == 1){
						   return "女";
					   }
				   }},
				   /* {title: '移动电话', name: 'phone', width:100},
				   {title: '邮箱', name: 'email', width:150}, */
				   /* {title: '是否锁定', name: 'status', width:60, renderer:function(val, item, rowIndex){
					   if(val == 0){
						   return "未锁定";
					   }else if(val == 1){
						   return "锁定";
					   }
				   }}, */
				   {title: '角色', name: 'roleIdList', width:300, renderer:function(val, item, rowIndex){
					   // dialog.alert(JSON.stringify(roleList));
					   var role = "";
					   for(var i=0; i<val.length; i++){
						   role = role + roleObj[val[i]+""] + ",";
					   }
					   role = role.substring(0, role.length-1);
					   return "<label title='"+role+"'>"+role+"</label>";
				   }},
				   {title: '操作', name: 'id', width:60, renderer:function(val, item, rowIndex){
					   // dialog.alert(JSON.stringify(roleList));
					   return "<a href='javascript:;' onclick='removeRole("+val+", "+rowIndex+")'>删除</a>";
				   }}
	           ],
	        plugins : [
	        	$('#paginator11-1').mmPaginator({
	        		totalCountName: 'total',   //对应MMGridPageVoBean count
	        		page: 1,                   //初始页
	        		pageParamName: 'page',     //当前页数
	        		limitParamName: 'pageSize', //每页数量
	        		limitList: [20, 40, 50]
	        	})
	        ]
	    });	
	}
}

//选择用户
function dialog.alertWinUser(){
	
	if(roleNodeChecked == null){
		//未选择角色
		dialog.alertBox.dialog.alert("<div class='dialog.alertBox_text'>请先选择一个角色！</div>", {title:"提示信息"});
	}else{
		dialog.alertWin = $.ligerDialog.open({ 
			height:500, 
			url: '<%=request.getContextPath()%>/manageRole/selectUser.action', 
			width: 800
		});	
	}
}

//保存完用户的角色
function afterSaveUserRole(){
	//关闭弹出框
	dialog.alertWin.close();
	//刷新表格
	roleUserTable(roleNodeChecked.id);
}

//删除用户的角色
function removeRole(id, rowIndex){
	//dialog.alert(id);
	if (confirm("确认删除吗？")) {
		
		$('#exampleTable').mmGrid().removeRow(rowIndex);
		
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			url:"<%=request.getContextPath()%>/manageRole/deleteUserRole.action",
			data:"userId="+id+"&roleId="+roleNodeChecked.id,
			success:function(data){
				//刷新表格
				var param = new Object();
				param.roleId = roleNodeChecked.id;
				//dialog.alert(JSON.stringify(param));
				$('#exampleTable').mmGrid().load(param);
		    }
		});	
	}
}
</script>
</head>
<body style="">
	
	<div style="padding:10px 20px 20px 20px;">
		
		<table class="tree_table" cellpadding="0" cellspacing="0" >
			<tr>
				<td class="left_tree" valign="top">
					
					<!-- 按钮 -->
					<table class="png_table" cellpadding="0" cellspacing="0" style="margin-top: 5px;">
						<tr>
							<td>
								<button class="btu_0" onclick="addRow()" style="margin:0px 10px 0px 10px;">增加</button>
							</td>
							<td>
								<button class="btu_0" onclick="deleteRow()" style="margin:0px 10px 0px 0px;">删除</button>
							</td>
							<td>
								<button class="btu_0" onclick="editRow()" style="margin:0px 10px 0px 0px;">修改</button>
							</td>
						</tr>
					</table>
				
					<ul id="treeDemo" class="ztree"></ul>
				</td>
				<td>
					<div style="margin-top:20px;">
						<span class="tab_span tab_span_checked" role="role">角色权限</span>
						<span class="tab_span" style="margin-left:10px;" role="user">角色成员</span>
						<div class="right_div" id="role_div" align="right" >
							<table style="width:100%;height:100%;" cellpadding="0" cellspacing="0" >
								<tr align="left" valign="top">
									<td style="width:300px;">
										<ul id="treePage" class="ztree" style=""></ul>
									</td>
									<td>
										<div class="have_role_div">
											<div id="have_role_div_span"></div>
											<table class="have_role_table" cellpadding="1" cellspacing="1" style="display:none;">
												<tr align="center" valign="middle">
													<td style="width:60px;font-weight:bold;background-color:#DDDDDD;">序号</td>
													<td style="width:180px;font-weight:bold;background-color:#DDDDDD;">权限名称</td>
												</tr>
											</table>
										</div>	
									</td>
								</tr>
							</table>
							<button class="btu_0" style="margin-bottom:10px;margin-right:10px;" onclick="saveRolePage()">保存</button>
						</div>
						<div class="right_div" id="user_div">
							<!-- 角色成员 -->
							<div style="margin:10px;" id="table_div">
								<button class="btu_0" onclick="dialog.alertWinUser()" style="margin-bottom:5px;">增加用户</button>
								<table id="exampleTable"></table>
								<div style="text-align:right;">
							    	<div id="paginator11-1"></div>
							    </div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	
	<input type="hidden" id="BlackBox_backVal" />
	
</body>
</html>
