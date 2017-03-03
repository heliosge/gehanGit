if(typeof dialog != "undefined"){
	
	dialog.confirm= function(obj,callback){
		
		var param = {
				title:"警告",
				okValue: '确定',
				ok: callback,
				width:"250px",
				cancelValue: '取消',
				cancel: function () {}
		};
		if(typeof obj=="object"){
			$.each(obj,function(k,v){
				param[k]=v;
			});
		}else{
			param["content"]=obj;
		}
		var d = dialog(param);
		d.showModal();
	};
	
	dialog.alert=function(obj,callback){
		var param = {
				title:"提示",
				okValue: '确定',
				width:"200px",
				ok: callback||function(){
					this.close;
				}
		};
		if(typeof obj=="object"){
			$.each(obj,function(k,v){
				param[k]=v;
			});
		}else{
			param["content"]=obj;
		}
		var d = dialog(param);
		d.showModal();
	};
}


//公共方法，给优化ztree树的样式用的
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

// 让zTree展开指定层数
// 参数说明：
//   zTree: 操作对象,zTree的实例对象
//   expandLevel: 范围正整数，希望展开的层数
// 附加说明：当希望展开所有节点时，请调用zTree对象自带的方法：expandAll(expandFlag)
function expandZTree(zTree, expandLevel) {
	return expandZTreeInner(zTree, zTree.getNodes(), expandLevel);
}
function expandZTreeInner(zTree, nodeList, expandLevel) {
	if (expandLevel <= 0 || !nodeList || nodeList.length == 0) {
		return false;
	}
    for (var i = 0; i < nodeList.length; i++) {
        zTree.expandNode(nodeList[i], true, false, false);
        expandZTreeInner(nodeList[i].children, expandLevel-1);
	}
}

// 获取zTree里指定节点的全路径名称（忽略根节点名称）
// 参数说明：
//   node: zTree的节点对象
//   attrName: 存储节点名称的属性名，不指定或传入null时，默认使用'name'属性
function getZTreePathName(node, attrName) {
	attrName = attrName ? attrName : 'name';
	if (node && node.getParentNode()) { // 如果不是根节点
		if (node.getParentNode().getParentNode()) { // 如果该节点的父节点不是根节点
			return getZTreePathName(node.getParentNode(), attrName) + '/' + node[attrName];
		} else {
			return node[attrName];
		}
	} else {
		return '';
	}
}

// 获取zTree里指定节点的全路径名称（包含根节点名称）
// 参数说明：
//   node: zTree的节点对象
//   attrName: 存储节点名称的属性名，不指定或传入null时，默认使用'name'属性
function getZTreePathNameWithRootNode(node, attrName) {
	attrName = attrName ? attrName : 'name';
	if (node) { // 如果传入节点是有效节点
		if (node.getParentNode()) { // 如果传入节点的父节点是有效节点
			return getZTreePathNameWithRootNode(node.getParentNode(), attrName) + '/' + node[attrName];
		} else {
			return node[attrName];
		}
	} else {
		return '';
	}
}

// 转义特殊字符以供sql查询的like语句使用
function escapeForSql(str) {
	var s = str ? str.trim() : str;
	return s ? s.replace(/\\/g, '\\\\').replace(/([%_])/g, '\\$1') : s;
}


//f5刷新处理
/*document.onkeydown = function(e)
{	event = window.event || event;
	if ( event.keyCode==116)
	{
		if(event.preventDetault){
			event.preventDetault();
		}else{
			window.location.reload(true);
			event.keyCode = 0;
			event.cancelBubble = true;
		}
		return false;
	}
}*/

document.onkeydown = function(e)
{	var ev = window.event || e;
	var code = ev.keyCode || ev.which;
	if ( code == 116)
	{
		window.location.reload(true);
		ev.keyCode ? ev.keyCode = 0 : ev.which = 0;
		cancelBubble = true;
		return false;
	}
}

