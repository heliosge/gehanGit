//公共方法总分
//jquery获取复选框值 
function jqchk(name){ 
	 var chk_value ='';
	$('input[name="'+name+'"]:checked').each(function(){ 
		chk_value = chk_value + $(this).val() + ","; 
	}); 
	if(chk_value != ''){
		return chk_value.substr(0,chk_value.length-1);
	}
	return chk_value;
}

//针对ie8及以下版本，自己创建indexOf函数
if (!Array.prototype.indexOf){
  Array.prototype.indexOf = function(elt /*, from*/)
  {
    var len = this.length >>> 0;
    var from = Number(arguments[1]) || 0;
    from = (from < 0)
         ? Math.ceil(from)
         : Math.floor(from);
    if (from < 0)
      from += len;
    for (; from < len; from++)
    {
      if (from in this &&
          this[from] === elt)
        return from;
    }
    return -1;
  };
}

//获得单选按钮选中的值
function GetRadioValue(RadioName){
    var obj;    
    obj=document.getElementsByName(RadioName);
    if(obj!=null){
        var i;
        for(i=0;i<obj.length;i++){
            if(obj[i].checked){
                return obj[i].value;            
            }
        }
    }
    return null;
}

//修改树的样式 
function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = path + '/images/img/ico_txt.png';
        data[idx]['iconOpen'] = path + '/images/img/ico_sq.png';
        data[idx]['iconClose'] = path + '/images/img/ico_zk.png';
    }
}

function addDiyDom(treeId, treeNode) {
	var spaceWidth = 16;
	var switchObj = $("#" + treeNode.tId + "_switch"),
	icoObj = $("#" + treeNode.tId + "_ico");
	switchObj.remove();
	icoObj.before(switchObj);

	if (treeNode.level > 0) {
		var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
		switchObj.before(spaceStr);
	}
	if(!treeNode.isParent){
		switchObj.remove();
	}
}
//初始化试卷弹出框
function initPaperTree(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			callback: {
				onClick: paperOnClick
			},
			view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				showTitle: true
				//addDiyDom: addDiyDom
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:path + "/exam/paperCategory/list.action",
		success:function(data){
			$.fn.zTree.init($("#paperTree"), setting, data);
			$.fn.zTree.getZTreeObj("paperTree").expandAll(true);
		}
	});
}

var categoryId;
function paperOnClick(event, treeId, treeNode){
	var id = treeNode.id; //分类id
	categoryId = id;
	$('#paperTable').mmGrid().load({"categoryId":id});
}
function initPaperGrid() {
	var paperMmg = $('#paperTable').mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : path + "/megagameManage/getPaperByCategory.action",
		width : 'auto',
		height : '265px',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : true,
		multiSelect : false,
		indexCol : true, // 索引列
		params : function(){
			var param = new Object();
			param.userId = userId;
			param.categoryId = categoryId;
			param.paperName = $.trim($("#paperName").val());
			return param;
		},
		cols : [ {title : 'id',name : 'id',hidden : true}, 
		         {title : '试卷名称',name : 'title',align : 'center'}, 
		         {title : '试卷库',name : 'categoryName',align : 'center'}, 
		         {title : '题型分布',name : 'count1',align : 'center',
					renderer : function(val, item, rowIndex) {
						var str = "";
						str = "主观"+item.count1+"单选"+item.count2+"多选"+item.count3+"判断"+item.count4+
						"填空"+item.count5+"组合"+item.count6+"多媒体"+item.count7;
						return str;
					}
		         }, 
		         {title : '试卷总分',name : 'totalScore',align : 'center'}
		       ],
		plugins : [ $('#paginatorPaging1').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean
			// count
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [ 10, 20, 40, 50 ]
		}) ]
	});
}

/*选择试卷  */
var paperDialog;
function selectPaper(){
	var selectPaper = $("#selectPaper");
	initPaperTree();
	//initPaperGrid();
	//$('#paperTable').mmGrid().load();
	paperDialog = dialog({
				title : "选择试卷",
				width : 980,
				height : 350,
				content :selectPaper,
				onshow: function() {
					setTimeout(initPaperGrid, 200);
				},
				fixed:true/*,
				okValue : '确定',
				ok : function() {
					var rowData = $('#paperTable').mmGrid().selectedRows();
					if(rowData.length == 0){
						dialog.alert("请选择试卷！");
						return false;
					}else{
						$("#sjId").val(rowData[0].id);
						$("#sjName").val(rowData[0].title);
					}
				},
				cancelValue : '取消',
				cancel : function() {
				}*/
			}).showModal();
}

/*选择试卷后确定操作  */
function paperSelect(){
	var rowData = $('#paperTable').mmGrid().selectedRows();
	if(rowData.length == 0){
		dialog.alert("请选择试卷！");
		return false;
	}else{
		$("#sjId").val(rowData[0].id);
		$("#sjName").val(rowData[0].title);
		paperDialog.close();
	}
}

/*搜索试卷  */
function doPaperSearch(){
	var paperName =  $.trim($("#paperName").val());
	var params = new Object;
	params.paperName = paperName;
	$('#paperTable').mmGrid().load(params);
}
//初始化批阅人弹出框
function initPeopleTree(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			callback: {
				view: { 
					showTitle: true,
					addDiyDom: addDiyDom
				},
				onClick: peopleOnClick
			},
			view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				showTitle: true,
				//addDiyDom: addDiyDom
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:path + "/exam/exam/getDepartmentBySubCompanyId.action",
		success:function(data){
			$.fn.zTree.init($("#peopleTree"), setting, data);
			$.fn.zTree.getZTreeObj("peopleTree").expandAll(true);
		}
	});
}
var departmentId;
function peopleOnClick(event, treeId, treeNode) {
	var deptId = treeNode.id;
	departmentId = deptId;
	$('#peopleTable').mmGrid().load({"deptId":deptId, "page":1});
	$("#userName").val("");
	$("#name").val("");
	
};

function initPeopleGrid() {
	$('#peopleTable').mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : path + "/megagameManage/getAllPeopleByDept.action",
		width : 'auto',
		height : '265px',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : true,
		multiSelect : true,
		indexCol : true, // 索引列
		params : function(){
			var param = new Object();
			param.userId = userId;
			param.deptId = departmentId;
			param.userName = $.trim($("#userName1").val());
			param.name = $.trim($("#name1").val());
			param.allCompany = "all";
			return param;
		},
		cols : [ {title : 'id',name : 'id',hidden : true}, 
		         {title : '用户名',name : 'userName',align : 'center'}, 
		         {title : '姓名',name : 'name',align : 'center'}, 
		         {title : '部门',name : 'deptName',align : 'center'},
		         {title : '岗位',name : 'postName',align : 'center'}
		       ],
		plugins : [ $('#paginatorPaging2').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean
			// count
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [ 10, 20, 40, 50 ]
		}) ]
	});
}

/*选择批阅人  */
var peopleDialog;
function selectPeople(){
	var selectPeople = $("#selectPeople");
	initPeopleTree();
	//initPeopleGrid();
	//$('#peopleTable').mmGrid().load();
	peopleDialog = dialog({
		title : "选择批阅人",
		width : 980,
		height : 350,
		content :selectPeople,
		onshow: function() {
			setTimeout(initPeopleGrid, 200);
		},
		fixed:true/*,
		okValue : '确定',
		ok : function() {
			var items = $('#peopleTable').mmGrid().selectedRows();
			var len = items.length;
			if(len == 0){
				dialog.alert("请至少选择一条数据!");
				return false; 
			}else{
				var uIds ="";
				var uNames ="";
				for(var i=0;i<len;i++){
					var uId = items[i].id;
					var userName = items[i].userName;
					uIds +=uId;
					uNames +=userName;
					if(i!=len-1){//非最后一条
						uIds +=",";
						uNames +=",";
					}
				}
				$("#pyrId").val(uIds);
				$("#pyrName").val(uNames);
			}
		},
		cancelValue : '取消',
		cancel : function() {
		}*/
	}).showModal();
}
//选择批阅人点击确定操作
function peopleSelect(){
	var items = $('#peopleTable').mmGrid().selectedRows();
	var len = items.length;
	if(len == 0){
		dialog.alert("请至少选择一条数据!");
		return false; 
	}else{
		var uIds ="";
		var uNames ="";
		for(var i=0;i<len;i++){
			var uId = items[i].id;
			var userName = items[i].userName;
			uIds +=uId;
			uNames +=userName;
			if(i!=len-1){//非最后一条
				uIds +=",";
				uNames +=",";
			}
		}
		$("#pyrId").val(uIds);
		$("#pyrName").val(uNames);
		peopleDialog.close();
	}
}

//人员搜索
function doPeopleSearch(){
	var userName =  $.trim($("#userName1").val());
	var name =  $.trim($("#name1").val());
	var params = new Object;
	params.userName = userName;
	params.name = name;
	params.page = 1;
	
	$('#peopleTable').mmGrid().load(params);
}


var idArray = [];
var idList = "";
//初始化授权部门弹出框
function initDepartmentTree(){
	idArray = [];
	if(tempList != ''){
		for(var i=0;i<tempList.length;i++){
			var id = tempList[i].id;
			idArray.push(id);
		}
	}
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  true,
					chkboxType: { "Y": "", "N": "" }
			},
			callback: {
				//onClick: departmentOnClick
				onCheck: departmentOnClick
			},
			view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				/*showTitle: true,
				addDiyDom: addDiyDom*/
				showLine: false,
				showIcon: true
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:path + "/manageCompany/selectCompanyCategory.action",
		success:function(data){
			//addIconInfo(data);
			$.fn.zTree.init($("#departmentTree"), setting, data.data);
			$.fn.zTree.getZTreeObj("departmentTree").expandAll(true);
		}
	});
	initCheckedPage();
	dialog({
		title : "选择部门",
		width : 400,
		height : 200,
		content :$("#departmentDiv"),
		okValue : '确定',
		fixed:true,
		ok : function() {
			var deptId = $("#deptId").val();
			if(deptId == ''){
				dialog.alert("请选择部门！");
				return false;
			}
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{"deptId":deptId},
				url:path + "/exam/exam/getPersonByDeptIds.action",
				success:function(data){
					//userList = data;
					tempUserList = [];
					if(data != '' && data != null){
						//var personListGrid = $('#personListTable').mmGrid();
						var items = data;
						var results = [];
						if(tempList != ''){
							for(var i=0;i<tempList.length;i++){
								results.push(tempList[i]);
							}
							//personListGrid.load(userList);
						}
						for(var index = items.length-1; index>=0; index--){
							var item = items[index];
							if(item){
								if(idArray.indexOf(item.id) != -1){
									continue;
								}
								results.push(item);
							}
						}
						tempList = results;
						tempUserList = results;
						initList(tempList);
						//personListGrid.addRow(results);
						//personListGrid.load(results);
						//userList= personListGrid.rows();
					}
				}
			});
			//$('#personListTable').mmGrid().load(userList);
		},
		cancelValue : '取消',
		cancel : function() {
		}
	}).showModal();
}

//部门勾选
function departmentOnClick(event, treeId, treeNode) {
	/*var departmentId = treeNode.id;
	$("#deptId").val(departmentId);*/
	var ztreeObj = $.fn.zTree.getZTreeObj("departmentTree");
	var nodes = ztreeObj.getCheckedNodes(true);
	var departmentIds = "";
	for(var i=0;i<nodes.length;i++){
		departmentIds += nodes[i].id + ",";
	}
	if(departmentIds != ''){
		$("#deptId").val(departmentIds.substr(0, departmentIds.length-1));
	}
};


//初始化岗位弹出框
function initPostTree(){
	idArray = [];
	if(tempList != ''){
		for(var i=0;i<tempList.length;i++){
			var id = tempList[i].id;
			idArray.push(id);
		}
	}
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  true,
					chkboxType: { "Y": "", "N": "" }
			},
			callback: {
				//onClick: departmentOnClick
				onCheck: function (event, treeId, treeNode){
					//alert(JSON.stringify(treeNode));
					
					var ztreeObj = $.fn.zTree.getZTreeObj("postTreePage");
					var nodes = ztreeObj.getCheckedNodes(true);
					var postIds = "";
					for(var i=0;i<nodes.length;i++){
						postIds += nodes[i].id + ",";
					}
					if(postIds != ''){
						$("#postId").val(postIds.substr(0, postIds.length-1));
					}
					
				}
			},
			view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				/*showTitle: true,
				addDiyDom: addDiyDom*/
				showLine: false,
				showIcon: true
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:path + "/manageParam/selectManagePost.action",
		success:function(data){
			//addIconInfo(data);
			$.fn.zTree.init($("#postTreePage"), setting, data);
			$.fn.zTree.getZTreeObj("postTreePage").expandAll(true);
		}
	});
	//initCheckedPage();
	//初始化 勾选
	var treeObj = $.fn.zTree.getZTreeObj("postTreePage");
	var postIdHave = $("#postId").val();
	if(postIdHave != ''){
		var postIdArray = postIdHave.split(",");
		$.each(postIdArray, function(index, val){
			var node = treeObj.getNodeByParam("id", val, null);
			/*if(node.parentId !=null){
				treeObj.checkNode(node,true,true);
			}else if(node.children == null){
				treeObj.checkNode(node,true,true);
			}*/
			
			treeObj.checkNode(node, true, false);
		});
	}
	
	dialog({
		title : "选择岗位",
		width : 400,
		height : 200,
		content :$("#postDialog"),
		okValue : '确定',
		fixed:true,
		ok : function() {
			var postId = $("#postId").val();
			if(postId == ''){
				dialog.alert("请选择岗位！");
				return false;
			}
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{"postId":postId},
				url:path + "/exam/exam/getPersonByPost.action",
				success:function(data){
					//userList = data;
					tempUserList = [];
					if(data != '' && data != null){
						//var personListGrid = $('#personListTable').mmGrid();
						var items = data;
						var results = [];
						if(tempList != ''){
							for(var i=0;i<tempList.length;i++){
								results.push(tempList[i]);
							}
							//personListGrid.load(userList);
						}
						for(var index = items.length-1; index>=0; index--){
							var item = items[index];
							if(item){
								if(idArray.indexOf(item.id) != -1){
									continue;
								}
								results.push(item);
							}
						}
						tempList = results;
						tempUserList = results;
						initList(tempList);
						//personListGrid.addRow(results);
						//personListGrid.load(results);
						//userList= personListGrid.rows();
					}
				}
			});
			//$('#personListTable').mmGrid().load(userList);
		},
		cancelValue : '取消',
		cancel : function() {
		}
	}).showModal();
}

//部门勾选
function departmentOnClick(event, treeId, treeNode) {
	/*var departmentId = treeNode.id;
	$("#deptId").val(departmentId);*/
	var ztreeObj = $.fn.zTree.getZTreeObj("departmentTree");
	var nodes = ztreeObj.getCheckedNodes(true);
	var departmentIds = "";
	for(var i=0;i<nodes.length;i++){
		departmentIds += nodes[i].id + ",";
	}
	if(departmentIds != ''){
		$("#deptId").val(departmentIds.substr(0, departmentIds.length-1));
	}
};

//初始化树节点，勾选节点
function initCheckedPage(){
	var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
	var deptIds = $("#deptId").val();
	if(deptIds != ''){
		var deptArray = deptIds.split(",");
		$.each(deptArray, function(index, val){
			var node = treeObj.getNodeByParam("id", val, null);
			if(node.parentId !=null){
				treeObj.checkNode(node,true,true);
			}else if(node.children == null){
				treeObj.checkNode(node,true,true);
			}
		});
	}
	
}

//初始化授权群组弹出框
function initGroupGrid() {
	$('#groupTable').mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : path + "/exam/exam/getGroupBySubCompanyId.action",
		width : 'auto',
		height : '265px',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : true,
		multiSelect : true,
		indexCol : true, // 索引列
		params : function(){
			var param = new Object();
			param.name = $.trim($("#groupName").val());
			param.createUserName = $.trim($("#createUserName").val());
			return param;
		},
		cols : [ {title : 'id',name : 'id',hidden : true}, 
		         {title : '群组',name : 'name',align : 'center'}, 
		         {title : '创建人',name : 'createUserName',align : 'center'} 
		       ],
		plugins : [ $('#paginatorPaging3').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean
			// count
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [ 10, 20, 40, 50 ]
		}) ]
	});
}

/*选择群组  */
var groupDialog;
function selectGroup(){
	idArray = [];
	if(tempList != ''){
		for(var i=0;i<tempList.length;i++){
			var id = tempList[i].id;
			idArray.push(id);
		}
	}
	var selectGroup = $("#selectGroup");
	groupDialog = dialog({
		title : "选择群组",
		width : 730,
		height : 350,
		content :selectGroup,
		onshow: function() {
			setTimeout(initGroupGrid, 200);
		},
		fixed:true/*,
		okValue : '确定',
		ok : function() {
			var rowData = $('#groupTable').mmGrid().selectedRows();
			if(rowData.length == 0){
				dialog.alert("请选择群组！");
				return false;
			}else{
				var groupIds ="";
				for(var i=0;i<rowData.length;i++){
					groupIds += rowData[i].id+",";
				}
				$("#groupId").val(groupIds.substr(0,groupIds.length-1));
			}
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{"groupId":$("#groupId").val()},
				url:path + "/exam/exam/getPersonByGroupId.action",
				success:function(data){
					//userList = data;
					if(data != '' && data != null){
						//var personListGrid = $('#personListTable').mmGrid();
						var items = data;
						var results = [];
						if(tempList != ''){
							for(var i=0;i<tempList.length;i++){
								results.push(tempList[i]);
							}
						}
						for(var index = items.length-1; index>=0; index--){
							var item = items[index];
							if(item){
								if(idArray.indexOf(item.id) != -1){
									continue;
								}
								results.push(item);
							}
						}
						tempList = results;
						tempUserList = results;
						initList(tempList);
					}
				}
			});
		},
		cancelValue : '取消',
		cancel : function() {
		}*/
	}).showModal();		
}

//选择群组点击确定操作
function groupSelect(){
	var rowData = $('#groupTable').mmGrid().selectedRows();
	if(rowData.length == 0){
		dialog.alert("请选择群组！");
		return false;
	}else{
		var groupIds ="";
		for(var i=0;i<rowData.length;i++){
			groupIds += rowData[i].id+",";
		}
		$("#groupId").val(groupIds.substr(0,groupIds.length-1));
		groupDialog.close();
	}
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{"groupId":$("#groupId").val()},
		url:path + "/exam/exam/getPersonByGroupId.action",
		success:function(data){
			//userList = data;
			if(data != '' && data != null){
				//var personListGrid = $('#personListTable').mmGrid();
				var items = data;
				var results = [];
				if(tempList != ''){
					for(var i=0;i<tempList.length;i++){
						results.push(tempList[i]);
					}
				}
				for(var index = items.length-1; index>=0; index--){
					var item = items[index];
					if(item){
						if(idArray.indexOf(item.id) != -1){
							continue;
						}
						results.push(item);
					}
				}
				tempList = results;
				tempUserList = results;
				initList(tempList);
			}
		}
	});
}

//群组搜索
function doGroupSearch(){
	var name = $.trim($("#groupName").val());
	var createUserName = $.trim($("#createUserName").val());
	$('#groupTable').mmGrid().load({"page":1,"name":name,"createUserName":createUserName});
}

//初始化选择人员弹出框
function initPersonTree(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			callback: {
				onClick: personOnClick
			},
			view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				showTitle: true,
				addDiyDom: addDiyDom,
				showLine: false,
				showIcon: true
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:path + "/manageCompany/selectCompanyCategory.action",
		success:function(data){
			$.fn.zTree.init($("#personTree"), setting, data.data);
			$.fn.zTree.getZTreeObj("personTree").expandAll(true);
		}
	});
}
function personOnClick(event, treeId, treeNode) {
	/*var deptId = treeNode.id;
	$('#personTable').mmGrid().load({"deptId":deptId});
	$("#userName2").val("");
	$("#name2").val("");*/
	
	var node = treeNode;
	var deptId = treeNode.id;
	var deptIds = [];
	
	function getChildren(testNode){
		
		if(testNode.children){
			for(var i=0; i<testNode.children.length; i++){
				
				if(testNode.children[i].id.indexOf("com_") == 0){
					
					deptIds.push(testNode.children[i].id.split("_")[1]);
				}else{
					deptIds.push(testNode.children[i].id);
				}					
				
				getChildren(testNode.children[i]);
			}
		}
	}

	getChildren(treeNode);
	
	//alert(deptIds);
	$('#personTable').mmGrid().load({"deptIds":deptIds});
};

function initPersonGrid() {
	var param = new Object();
	param.status = 1;
	
	if(!param.deptIds){
		
		var deptIds = [];
		
		function getChildren(testNode){
			
			if(testNode.children){
				for(var i=0; i<testNode.children.length; i++){
					
					if(testNode.children[i].id.indexOf("com_") == 0){
						
						deptIds.push(testNode.children[i].id.split("_")[1]);
					}else{
						deptIds.push(testNode.children[i].id);
					}					
					
					getChildren(testNode.children[i]);
				}
			}
		}
		
		var nodes = $.fn.zTree.getZTreeObj("personTree").getNodes();
		for(var i=0; i<nodes.length; i++){

			getChildren(nodes[i]);
		}
		
		param.deptIds = deptIds;
	}
	
	var personMmg = $('#personTable').mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : path + "/manageUser/selectStudentList.action",
		width : 'auto',
		height : '400px',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : true,
		multiSelect : true,
		indexCol : true, // 索引列
		params : param,
		cols : [ {title : 'id',name : 'id',hidden : true}, 
		         {title : '用户名',name : 'userName',align : 'center'}, 
		         {title : '姓名',name : 'name',align : 'center'}, 
		         {title : '部门',name : 'deptName',align : 'center'},
		         {title : '岗位',name : 'postName',align : 'center'}
		       ],
		plugins : [ $('#paginatorPaging4').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean
			// count
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [ 10, 20, 40, 50 ]
		}) ]
	});
	personMmg.on("loadSuccess",function(){
		if(personMmg){
			personMmg.select(function(item,index){
				if(item){
					if(idArray.indexOf(item.id) != -1 ){
						return true;
						//mmg.select(index);
					}
				}
				return false;
			});
		}
	});
}

/*选择人员  */

var personDialog;
function selectPerson(){
	idArray = [];
	var selectPerson = $("#selectPerson");
	// 封装参数
	if(tempList != ''){
		for(var i=0;i<tempList.length;i++){
			var id = tempList[i].id;
			idArray.push(id);
		}
	}
	
	initPersonTree();
	
	personDialog = dialog({
		title : "选择人员",
		width : 900,
		height : 450,
		content :selectPerson,
		onshow: function() {
			setTimeout(initPersonGrid, 200);
		},
		fixed:true
	}).showModal();		
}
//选择人员点击确定操作
function personSelect(){
	var personTableGrid = $('#personTable').mmGrid();
	//var personListGrid = $('#personListTable').mmGrid();
	var items = personTableGrid.selectedRows();
	var results = [];
	if(tempList != ''){
		for(var i=0;i<tempList.length;i++){
			results.push(tempList[i]);
		}
	}
	for(var index = items.length-1; index>=0; index--){
		var item = items[index];
		if(item){
			if(idArray.indexOf(item.id) != -1){
				continue;
			}
			results.push(item);
		}
	}
	tempList = results;
	tempUserList = results;
	initList(tempList);
	personDialog.close();
	//personListGrid.addRow(results);
	//userList= personListGrid.rows();
	//initList(userList);
}

//人员搜索
function doPersonSearch(){
	var userName =  $.trim($("#userName2").val());
	var name =  $.trim($("#name2").val());
	//var depName =  $.trim($("#depName2").val());
	//var post =  $.trim($("#postName2").val());
	var params = new Object;
	params.userName = userName;
	params.name = name;
	
	var deptIds = [];
	function getChildren(testNode){
		
		if(testNode.children){
			for(var i=0; i<testNode.children.length; i++){
				
				if(testNode.children[i].id.indexOf("com_") == 0){
					
					deptIds.push(testNode.children[i].id.split("_")[1]);
				}else{
					deptIds.push(testNode.children[i].id);
				}	
				
				getChildren(testNode.children[i]);
			}
		}
	}
	//获得选择的节点
	var nodes = $.fn.zTree.getZTreeObj("personTree").getSelectedNodes(true);
	if(!nodes || nodes.length == 0){
		
		nodes = $.fn.zTree.getZTreeObj("personTree").getNodes();
	}
	
	if(nodes && nodes.length > 0){
		for(var i=0; i<nodes.length; i++){

			if(nodes[i].id.indexOf("com_") == 0){
				
				deptIds.push(nodes[i].id.split("_")[1]);
			}else{
				deptIds.push(nodes[i].id);
			}
			getChildren(nodes[i]);
		}
	}
	
	params.deptIds = deptIds;
	
	//params.depName = depName;
	//params.post = post;
	$('#personTable').mmGrid().load(params);
}

//初始化考试人员列表mmgrid
function initPersonListGrid(){
	var personListMmg = $('#personListTable').mmGrid({
		cols : [ {title : 'id',name : 'id',hidden : true}, 
		         {title : '用户名',name : 'userName', width:100,align : 'center'}, 
		         {title : '姓名',name : 'name', width:120,align : 'center'}, 
		         {title : '性别',name : 'sex', width:50,align : 'center',
		        	 renderer:function(val,item, rowIndex){
	                		if(item.sex == 1){
	                			return '男';
	                		}else{
	                			return '女';
	                		}
	                	}
		         }, 
		         {title : '部门',name : 'deptName', width:120,align : 'center'},
		         {title : '岗位',name : 'postName', width:120,align : 'center'}, 
		         {title : '电子邮箱',name : 'email', width:200,align : 'center'}, 
		         {title : '联系电话',name : 'mobile', width:100,align : 'center'}, 
		         {title:'操作',name:'operation', width:80,align:'center',
	                	renderer:function(val,item, rowIndex){
	                		if(!item.isEdit && item.isEdit != 1){
	                			return '<a href="javascript:void(0);" onclick="del_grid('+item.id+')">删除</a>';
	                		}
	                	}	
		         }],
		url:"",
		width : 'auto',
		height : 'auto',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : true,
		multiSelect : true,
		indexCol : true, // 索引列
		params : function(){
	    	var param = new Object();
	    	return param;
	    }/*,
		plugins : [ $('#paginatorPaging5').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean
			// count
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [ 10, 20, 40, 50 ]
		}) ]*/
	});
}

/*删除列  */
function removeRow(index){
	$('#personListTable').mmGrid().removeRow(index);
}

/*查询  */
function query(){
	page = 1;
	search_grid();
	initList(tempList);
}

//考试人员列表批量删除
function delMore_grid(){
	idArray = [];
	var items = $('#personListTable').mmGrid().selectedRows();
	for(var j=0;j<items.length;j++){
		var isEdit = items[j].isEdit;
		if(isEdit && isEdit == 1){
			dialog.alert("有不可以删除的数据，请重新勾选！");
			return;
		}
	}
	if(items.length == 0){
		dialog.alert("请选择数据！");
		return;
	}
	dialog.confirm("确认删除吗？", function(){
		var results = [];
		var len=tempList.length;
		var itemLen = items.length;
		for(var i=0;i<len;i++){
			var flag=0;
			var id = tempList[i].id;
			for(var j=0;j<itemLen;j++){
				var id2 = items[j].id;
				if(id==id2){//当在删除集合中被找到
					flag = flag+1;
				}
			}
			if(flag==0){
				results.push(tempList[i]);
			}
		}
		tempList = results;
		if(tempList != ''){
			for(var i=0;i<tempList.length;i++){
				var id = tempList[i].id;
				idArray.push(id);
			}
		}
		initList();
		//$('#personListTable').mmGrid().load(userList);
	});
}
//考试人员列表单条删除
function del_grid(id){
	idArray = [];
	//alert(tempList.length);
	var results = [];
	var len=tempList.length;
	for(var i=0;i<len;i++){
		var lid = tempList[i].id;
		if(id==lid){
			continue;
		}
		results.push(tempList[i]);
	}
	tempList = results;
	if(tempList != ''){
		for(var i=0;i<tempList.length;i++){
			var id = tempList[i].id;
			idArray.push(id);
		}
	}
	initList();
	//userList = results;
	//$('#personListTable').mmGrid().load(userList);
}

//考试人员列表搜索
function search_grid(){
	//tempList = [];
	//alert(tempList.length);
	var searchName = $.trim($("#name").val());
	var searchPostName = $.trim($("#post").val());
	var searchDeptName = $.trim($("#department").val());
	//var len = userList.length;
	var len = tempList.length;
	var results = [];
	var flagIndex;
	for(var i=0;i<len;i++){
		var flag1=true;
		var flag2=true;
		var flag3=true;
		flagIndex =0;
		var name = tempList[i].name;
		var postName = tempList[i].postName;
		var deptName = tempList[i].deptName;
		if(searchName!='' && searchName){
			if(!name){
				$('#personListTable').mmGrid().load(results);
				continue;
			}
			if(name.indexOf(searchName)!=-1){
				flag1=true;
			}else{
				flag1=false;
			}
		}else{
			flagIndex= flagIndex+1;
		}
		if(searchPostName!='' && searchPostName){
			if(!postName){
				$('#personListTable').mmGrid().load(results);
				continue;
			}
			if(postName.indexOf(searchPostName)!=-1){
				flag2=true;
			}else{
				flag2=false;
			}
		}else{
			flagIndex= flagIndex+1;
		}
		if(searchDeptName!='' && searchDeptName){
			if(!deptName){
				$('#personListTable').mmGrid().load(results);
				continue;
			}
			if(deptName.indexOf(searchDeptName)!=-1){
				flag3=true;
			}else{
				flag3=false;
			}
		}else{
			flagIndex= flagIndex+1;
		}
		if(flag1 && flag2 && flag3){
			results.push(tempList[i]);
		}
	}
	if(flagIndex==3){
		//initList(tempList);
		//$('#personListTable').mmGrid().load(userList);
		//tempList = results;
		tempList = tempUserList;
	}else{
		tempList = results;
		//initList(tempList);
		//$('#personListTable').mmGrid().load(results);	
	}
}
/*初始化人员列表  */
function initList(){
	nowData = [];
	total = tempList.length;
	pageNum = Math.ceil(total/pageSize);
	var count = pageSize;
	if(tempList.length <= pageSize){
		count = tempList.length;
	}
	$("#total").text(total);
	$("#pageNum").text(pageNum);
	$("#nowPage").text(page);
	for(var i=0;i<count;i++){
		nowData.push(tempList[i]);
	}
	$('#personListTable').mmGrid().load(nowData);
	//userList = $('#personListTable').mmGrid().rows();
}

/*设置每页显示条数  */
function setPageSize(obj){
	nowData = [];
	page = 1;
	//pageSize = size;
	pageSize = $(obj).val();
	$('div[id=leftDiv]').find('li[id=size'+pageSize+']').removeClass().addClass("class02");
	$('div[id=leftDiv]').find('li[id!=size'+pageSize+']').removeClass().addClass("class01");
	$('#personListTable').mmGrid().removeRow();
	search_grid();
	initList(tempList);
}

/*跳转至第几页  */
function gotoPage(type){
	var toPage;
	if(type){
		toPage = parseInt(page)+ parseInt(type);
	}else{
		toPage = $.trim($("#toPage").val());
	}
	total = tempList.length;
	nowData = [];
	search_grid();
	if(toPage != page && toPage > 0){
		//alert(tempList.length);
		pageNum = Math.ceil(tempList.length/pageSize);//总页数
		//alert(toPage + "---" + pageNum);
		if(toPage <= pageNum){//只有跳转页小于等于总页数时，才跳转，否则无反应
			page = toPage;
			var from = (toPage-1)*pageSize;
			var end = parseInt(from) + parseInt(pageSize-1);
			var lastPageCount = total%pageSize;
			if(toPage == pageNum){
				if(lastPageCount != 0){
					end = parseInt(from) + parseInt(lastPageCount-1);
				}
			}
			//alert(pageNum+"-"+from+"-"+end);
			for(var i=from;i<end+1;i++){
				nowData.push(tempList[i]);
			}
			$('#personListTable').mmGrid().load(nowData);
			$("#nowPage").text(page);
		}
	}
}
/*上一页  */
function gotoPrePage(){
	gotoPage(-1);
}
/*下一页  */
function gotoNextPage(){
	gotoPage(1);
	
}

//重置
function clearAll(){
	page = 1;
	$("#name").val("");
	$("#post").val("");
	$("#department").val("");
	tempList = userList;
	initList();
	//$('#personListTable').mmGrid().load(userList);
	//当修改时，需要重新加载数据
}