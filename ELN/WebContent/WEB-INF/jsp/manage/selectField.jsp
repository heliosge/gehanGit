<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人员字段维护</title>
</head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
.selected td{
	background: none!important;
}

</style>
<script type="text/javascript">
var isEmpty =${isEmpty};
$(function(){
	//表格
	var mm=	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageParam/selectExpandField.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        indexCol: true,  //索引列
        cols: [{title: 'ID', name: 'id', width:60, hidden:true},
 			   {title: '字段', name: 'companyFieldName', width:60, align:'center'},
           ],
        plugins : [
        	$('#paginator11-1').mmPaginator({
        		totalCountName: 'total',    //对应MMGridPageVoBean count
        		page: 1,                    //初始页
        		pageParamName: 'page',      //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [100]
        	})
        ]
    });

	var fieldList = ${fieldList};//拿到当前公司配置的参数列表
	
	mm.on("loadSuccess",function(e, data){
		
		if(fieldList.length>0){
			for(var i=0;i<fieldList.length;i++){
				if(fieldList[i].isEmpty==isEmpty){
					$("#selectFiled").append("<label style='margin-left: 5px;background: #ccc;' propertyId='"+fieldList[i].propertyId+"'>"+fieldList[i].propertyName+"</label>")
					mm.select(function(item, index){
						if(item.id==fieldList[i].propertyId){
							return true;
						}else{
							return false;
						}
						
					})
				}
			}
		}
		$("div.mmg-body tr").click(function(e){
			e.stopPropagation();		
		})
		
		//单个checkbox的点击事件
		$("input.mmg-check").on("click",function(){
			var item = mm.row($(this).closest("tr").index());
			if($(this).is(":checked")){
				$("#selectFiled").append("<label style='margin-left: 5px;background: #ccc;' propertyId='"+item.id+"'>"+item.companyFieldName+"</label>")
			}else{
				$("#selectFiled").find("label[propertyId='"+item.id+"']").remove();
			};
			
			if($.isAllChecked($("input.mmg-check"))){
				$("input.checkAll").prop("checked",true);
			}else{
				$("input.checkAll").prop("checked",false);
			}
			
		}).css("cursor","pointer");
		
		//全选点击事件
		$("input.checkAll").on("click",function(){
			if($(this).is(":checked")){
				var items = mm.rows();
				for(var i=0;i<items.length;i++){
					var item = items[i];
					var length = $("#selectFiled").find("label[propertyId='"+item.id+"']").length;
					if(length==0){
						$("#selectFiled").append("<label style='margin-left: 5px;background: #ccc;' propertyId='"+item.id+"'>"+item.companyFieldName+"</label>")
					}
				}
			}else{
				$("#selectFiled").find("label").remove();
			}
		}).css("cursor","pointer");
	})
	
	$.isAllChecked = function(_$){
		for(var i=0;i<_$.length;i++){
			if(!_$.eq(i).is(":checked")){
				return false;
			}
		}
			return true;
	}
});


function  slFiledInfo(){
	
	/* var rows = 	$('#exampleTable').mmGrid().selectedRows();
	if(rows.length==0){
	}
	
	var ids = [];
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].id);
	} */
	var rows = $("#selectFiled").find("label");
	var ids = [];
	for(var i=0;i<rows.length;i++){
		ids.push(rows.eq(i).attr("propertyId"));
	} 
	
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{ids:ids.join("|"),isEmpty:isEmpty},
		url:"<%=request.getContextPath()%>/manageParam/saveUserParam.action",
		success:function(data){
			var dialog1 = parent.dialog.get(window);
			dialog1.close();
			parent.location="<%=request.getContextPath() %>/manageParam/queryParam.action";
			if("SUCCESS"==data){
					dialog.alert({content:"保存成功",lock:false},function(){
						//保存数据到数据库
						window.parent.$.ligerDialog.close(); //关闭弹出窗;  
						window.parent.$(".l-dialog,.l-window-mask").css("display","none"); //只隐藏遮罩层
				});
			}else{
				dialog.alert("保存失败");
			}
	    }
	});
}



</script>
<body>

<div class="content" style="width:100%;padding-bottom: 20px; margin-top: 10px;">
	<h4 style='float:left'>已选字段</h4>
	<div id="selectFiled"  style="margin-bottom: 5px;min-height: 20px"></div>
    
	<div style="margin-top:10px;height: 400px;overflow: auto;border: 1px solid #F5E1E1;">
		<div id="exampleTable"  ></div>
	</div>
	<div id="paginator11-1" align="right" style="margin-right:10px;display:none" ></div>
</div>
<div class="btns" style='display: block;position: absolute;left: 45%;bottom: -1px;'>
	<button class="btu_0" onclick="slFiledInfo()" style="float:right;margin-right:10px;">保存</button>
</div>
<script>

	
	</script>
</body>
</html>
