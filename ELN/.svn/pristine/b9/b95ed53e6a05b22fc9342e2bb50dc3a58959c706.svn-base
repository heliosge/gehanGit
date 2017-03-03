<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业权限管理</title>

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
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>


<style type="text/css">
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;margin-left:13px;position: absolute;background:url("");}
	.ztree li span.button.noline_close{z-index:999;margin-left:13px;position: absolute;background:url("");}
	.page_div {width: 200px;height: auto;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.page_div h2 {font-size: 14px;width: 200px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.page_div_1 {width: 200px;height: auto;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.page_div_1 h2 {font-size: 14px;width: 200px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.page_div_1 .ztree li span.button.noline_open{z-index:999;margin-left:0px;position: absolute;background:url("");}
	.page_div_1 .ztree li span.button.noline_close{z-index:999;margin-left:0px;position: absolute;background:url("");}
	.lesson_add .add_gr .add_fr span{ } 
</style>

<script type="text/javascript">
var pageList = [];
var choosePageList = [];
var queryType = true;
var id = '${id}';
if(id == ''){
	queryType = false;
}


$(function(){
	initValidate();
	initCompanyGrid();
	initDatepicker();
	
	$("#exampleTable").click(function(){
		var selectRows = $('#exampleTable').mmGrid().selectedRows();
		if(selectRows.length > 0){
			id = selectRows[0].id;
			initCompanyInfo();
		}
	})
	
	/* if(id != ''){
		initCompanyInfo();
	}else{
		var rows = $('#exampleTable').mmGrid().rows();
		id=rows[0].id;
		initCompanyInfo();
	} */
});

function initCompanyInfo(){
	selectCompanyResInfo(id);
	initPage();
	initChoosenPage();
	initCheckedPage();
}

function initCompanyGrid(){
	var mm = $('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageCompany/selectManageCompanyList.action",
    	width: $('#exampleTable').parent().width(),
    	height: '530px',
    	params: function(){
    		var o = {};
    		o.status = 1;
    		o.id = id;
    		o.name = $("#name").val();
    		if(!queryType){
	    		o.id = null;
    		}
    		return o;
    	}, 
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: false,
        //checkCol: true,
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
 			   {title: '企业名称', name: 'name', width:120, align:'center'}
           ]/* ,
        plugins : [
               	$('#paginator11-1').mmPaginator({
               		totalCountName: 'total',    //对应MMGridPageVoBean count
               		page: 1,                    //初始页
               		pageParamName: 'page',      //当前页数
               		limitParamName: 'pageSize', //每页数量
               		limitList: [10, 20, 40, 50]
               	})
               ] */
    });
	
	mm.on("loadSuccess",function(e, data){
		if(id == ''){
			id=data.rows[0].id;
			mm.select(0);
		}else{
			for(var j=0;j<data.rows.length;j++){
				if(data.rows[j].id == id){
					mm.select(j);
				}
			}
		}
		initCompanyInfo();
	});
}

function selectCompanyResInfo(id){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{id:id},
		url:"<%=request.getContextPath()%>/manageCompany/selectCompanyResInfo.action",
		success:function(data){
			if(data != null){
				addIconInfo(data.allPageList);
				addIconInfo(data.pageList);
				pageList = data.allPageList;
				choosePageList = data.pageList;
				fillCompanyInfo(data.company);
			}
		}
	})
}

function fillCompanyInfo(company){
	if(company.initUserId != null){
		$("#initUserId").val(company.initUserId);	
		$("#initUsername").val(company.initUsername);
		$("#initPassword").val(company.initPassword);
		$("#accountCount").val(company.accountCount);
		$("#maxConcurrent").val(company.maxConcurrent);
		$("#totalCapacity").val(company.totalCapacity);
		$("#endTime").val(getSmpFormatDateByLong(company.endTime, false));
	}else{
		$("#initUserId").val("");	
		$("#initUsername").val("");
		$("#initPassword").val("");
		$("#accountCount").val("");
		$("#maxConcurrent").val("");
		$("#totalCapacity").val("");
		$("#endTime").datepicker('setDate', (new Date()) );
	}
}


function save(){
	if(id == 1){
		dialog.alert("普联企业权限不可修改。");
		return;
	}
	$('#addForm').isValid(function(v) {
		if(v){
			var param = {};
			param.id=id;
			param.initUserId = $("#initUserId").val();
			param.initUsername = $("#initUsername").val();
			param.initPassword = $("#initPassword").val();
			param.accountCount = $("#accountCount").val();
			param.maxConcurrent = $("#maxConcurrent").val();
			param.totalCapacity = $("#totalCapacity").val();
			param.endTime = $("#endTime").val();
			var chooseTreeObj = $.fn.zTree.getZTreeObj("chooseTreePage");
			var arrayParam = [];
			$.each(chooseTreeObj.getNodes(), function(index, val){
				arrayParam.push(val.id);
				var childNodes = chooseTreeObj.getNodesByParam("parentId", val.id, val);
				$.each(childNodes, function(index, childVal){
					arrayParam.push(childVal.id);
				});
			});
			
			param.pageIds = arrayParam;
			var url = "";
			if(param.initUserId == ''){
				url = "<%=request.getContextPath()%>/manageCompany/insertCompanyResInfo.action";
			}else{
				url = "<%=request.getContextPath()%>/manageCompany/updateCompanyResInfo.action";
			}
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:param,
				url:url,
				success:function(data){
					if(data == 'SUCCESS'){
						if(queryType){
							dialog.alert("企业权限修改成功。",function(){
								window.history.back(-1);
							});
						}else{
							dialog.alert("企业权限修改成功。");
						}
					}else{
						dialog.alert("企业权限修改失败。");
					}
				}
			});
			initCompanyInfo();
		} else {
			//dialog.alert("表单验证不通过");
		}
	});
}

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

function search(){
	$('#exampleTable').mmGrid().load();
}


function initPage(){
	//获取所有权限页面
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable: true},
			view: {
				showLine: false,
				showIcon: true
			},
			callback: {
				beforeClick: function(treeId, treeNode, clickFlag){return false;}
			}
	};
	
	$.fn.zTree.init($("#treePage"), setting, pageList);
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}

function initCheckedPage(){
	var treeObj = $.fn.zTree.getZTreeObj("treePage");
	if(choosePageList != null){
		$.each(choosePageList, function(index, val){
			var node = treeObj.getNodeByParam("id", val.id, null);
			if(node.parentId !=null){
				treeObj.checkNode(node,true,true);
			}
		});
	}
}


function initChoosenPage(){
	//获取所有权限页面
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable: false},
			view: {
				showLine: false,
				showIcon: true
			},
			callback: {
				beforeClick: function(treeId, treeNode, clickFlag){return false;}
			}
	};
	$.fn.zTree.init($("#chooseTreePage"), setting, choosePageList);
	$.fn.zTree.getZTreeObj("chooseTreePage").expandAll(true);
}


function initDatepicker() {
	$("#endTime").datepicker({
		dateFormat : 'yy-mm-dd',
		changeMonth: true,
        changeYear: true
	});
	
	$("#endTime").datepicker('setDate', (new Date()) );
}

function choosePage(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			view: {
				showLine: false,
				showIcon: true
			},
			check: {enable: false},
			callback: {
				beforeClick: function(treeId, treeNode, clickFlag){return false;}
			}
	};
	$.fn.zTree.init($("#chooseTreePage"), setting, pageList);
	var chooseTreeObj = $.fn.zTree.getZTreeObj("chooseTreePage");
	chooseTreeObj.expandAll(true);
	//删除未选中的节点
	$.each($.fn.zTree.getZTreeObj("treePage").getCheckedNodes(false), function(index, val){
		chooseTreeObj.removeNode(chooseTreeObj.getNodeByParam("id", val.id, null), false);
	});
}

/**
 * 表单验证
 */
function initValidate() {
	$('#addForm').validator({
		theme : 'yellow_right',
		rules : {
			checkUserName:function(element,param,field){
			var str;
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{userName:element.value,companyId:id},
				url:"<%=request.getContextPath()%>/manageUser/getAllManageUserByCompanyId.action",
				success:function(data){
					var flag = true;
					if(data.length > 0 && data[0].id != $("#initUserId").val()){
						flag = false;
					}
					str =  flag || "已存在相同用户名";
					}
				});
			return str;
			},
			checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ]
		},
		msgStyle:"margin-top:10px;",
		fields : {
			initUsername : {
				rule : "required;length[6~30];checkUserName",
				msg : {
					required : "初始用户名不能为空",
					length : "长度需在6-30个字符"
				}
			},
			initPassword : {
				rule : "required;length[6~30];",
				msg : {
					required : "初始密码不能为空",
					length : "长度需小于等于30个字符"
				}
			}, 
			accountCount : {
				rule : "required;integer[+]",
				msg : {
					required : "账户数量不能为空"
				}
			},
			maxConcurrent : {
				rule : "required;integer[+]",
				msg : {
					required : "最大并发账户数量不能为空"
				}
			},
			totalCapacity : {
				rule : "required;integer[+]",
				msg : {
					required : "资源容量不能为空"
				}
			},
			endTime : {
				rule : "required",
				msg : {
					required : "有效期不能为空"
				}
			}
		}
	});
}


</script>

</head>
<body>

<div class="content">
	<!-- <h3>企业权限管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">企业权限管理</span>
	</div>
    <div class="content_2">
        <div class="left_tree" style="height:530px;width:280px;">
        		<div>
	        		<input type="text" id="name" style="width:200px;height:30px;">
	        		<input style="background: #d60500;color: #fff;width:70px;height:30px;" type="button" value="查询" onclick="search()"/>
	        	</div>
       			<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
       			<!-- <div id="paginator11-1" align="right" style="margin-right:10px;" ></div> -->
        </div>	
        <form id="addForm">
        <div class="right_lesson" style="margin-left:0px;">
        	<div class="lesson_add" >
		    	<div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                 <em>初始账号:</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" id="initUserId" style="display:none;"/>
		            	 <input type="text" id="initUsername" name="initUsername"/>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                 <em>初始密码：</em>
		            </div>
		            <div class="add_fr">
		            	 <input type="password" id="initPassword" name="initPassword"/>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                 <em>账号数量：</em>
		            </div>
		            <div class="add_fr">
		            	 <input type="text" id="accountCount" name="accountCount"/>&nbsp;个
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                 <em>最大并发账号数量：</em>
		            </div>
		            <div class="add_fr">
		            	 <input type="text" id="maxConcurrent" name="maxConcurrent"/>&nbsp;个
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                 <em>资源容量：</em>
		            </div>
		            <div class="add_fr">
		            	 <input type="text" id="totalCapacity" name="totalCapacity"/>&nbsp;GB
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                 <em>有效期：</em>
		            </div>
		            <div class="add_fr">
		            	 <input type="text" id="endTime" name="endTime"/>
		            </div>
		        </div>
		        <div style="height:auto;width:1066px; line-height:80px; float:left;">
		        	<div style="width:170px; float:left; text-align:right; line-height:50px;">
		                 <em>分配权限：</em>
		            </div>
		            <div style=" margin-left:16px; float:left; width:880px; line-height:50px;">
		            	 <table>
	                	<tr>
	                		<td>
		                		<div class="page_div">
		                			<h2>全部功能模块</h2>
		                			<ul id="treePage" class="ztree"></ul>
		                		</div>
		                	</td>
	                		<td style="width:150px;text-align:center;">
		                		<div style="cursor: pointer;" onclick="choosePage()">
						    		<img class="img " src="<%=request.getContextPath() %>/images/img/u143.png">
						    	</div>
			    			</td>
	                		<td>
	                			<div class="page_div_1">
	                				<h2>已分配功能模块</h2>
	                				<ul id="chooseTreePage" class="ztree" style=""></ul>
	                			</div>
	                		</td>
	                	</tr>
	                </table>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	&nbsp;
		            </div>
		            <div class="add_fr">
		            	&nbsp;
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	&nbsp;
		            </div>
		            <div class="add_fr">
		            	 <input type="button" value="保存" class="ly_bt1" onclick="save()"/>
		            </div>
		        </div>
        	</div>
      </div>
      </form>
   </div>
   </div>
</body>
</html>
