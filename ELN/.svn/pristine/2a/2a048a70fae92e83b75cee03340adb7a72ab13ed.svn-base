<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大赛报名审批</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/json2.js"></script>
<!--日期控件  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/css/flick/jquery-ui.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery.ui.datepicker-zh-CN.js" ></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
.ui-dialog-body{padding:0px;}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';//登录用户ID
$(document).ready(function() {     
	$("#applyTimeBegin").datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat:'yy-mm-dd'
	});
	$("#applyTimeEnd").datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat:'yy-mm-dd'
	});
});

$(function(){
	$('#approveTable').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/megagameManage/getAppliationVoList.action',
		width: '1065px',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: true,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    params:function(){
	    	var param = new Object();
	    	param.contestName = $.trim($("#contestName").val());
	    	param.applyTimeBegin = $("#applyTimeBegin").val();
	    	param.applyTimeEnd = $("#applyTimeEnd").val();
	    	param.name = $.trim($("#name").val());
	    	param.approvalStatus = $.trim($("#approvalStatus").val());
	    	return param;
	    },
	    cols: [{title: 'ID', name: 'id',hidden:true},
	           {title:'大赛名称', name:'contestName', width:160,align:'center'},
	           {title:'创建时间', name:'contestCreateTime', width:80,align:'center'},
               {title:'报名者',name:'userName', width:80,align:'center'},
               {title:'报名时间',name:'applyTime', width:90,align:'center'},
               {title:'审批状态',name:'isPassed', width:60,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.approvalStatus == 1){
            			   return "待审批"; 
            		   }else if(item.approvalStatus == 2){
            			   return "审批通过";
            		   }else{
            			   return "审批拒绝";
            		   }
              		}
              	},
               {title:'操作',name:'operation', width:150,align:'center',
                	renderer:function(val,item, rowIndex){
                		
                		var str = '';
                		var str_approve = '<a href="javascript:void(0);" onclick="approveApply('+ item.id +","+item.contestId+","+item.userId+')" >审批</a>&nbsp;';
                		var str_view = '<a href="javascript:void(0);" onclick="viewApply('+ rowIndex +')" >查看</a>&nbsp;';
                		var str_delete = '<a href="javascript:void(0);" onclick="deleteApply('+ item.id +","+item.approvalStatus+')" >删除</a>';
                		if(item.approvalStatus == 1){
                			str += str_approve;
                		}
                		str += str_view + str_delete;
                		return str;
                	}	
	}] ,
	   plugins : [
	    	$('#paginator11-1').mmPaginator({
	    		totalCountName: 'total',    //对应MMGridPageVoBean count
	    		page: 1,                    //初始页
	    		pageParamName: 'pageNo',      //当前页数
	    		limitParamName: 'pageSize', //每页数量
	    		limitList: [10, 20, 40, 50]
	    	})
	    ] 
	});
})

/*删除报名申请  */
function deleteApply(id,approval_Status){
	if(approval_Status==1){
		dialog.alert("待审批的不可以删除噢!");
		return;
	}
	dialog.confirm("确认删除吗？", function(){
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{"id":id},
			url: "<%=request.getContextPath()%>/megagameManage/deleteApplication.action",
			success:function(data){
				if(data == "SUCCESS"){
					dialog.alert("操作成功！");
					search(true);
				}else{
					dialog.alert("操作失败！");
				}
		    }
		});
	});
}

var applyIds = '';
var contestIds = '';
var applyUserIds = '';
var approvalStatus = [];
function mmgridchk(){
	applyIds = '';
	contestIds = '';
	applyUserIds = '';
	approvalStatus = [];
	var selectRows = $('#approveTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
    	$.each(selectRows, function(index, val){
    		applyIds  += val.id + ",";
    		contestIds += val.contestId + ",";
    		applyUserIds += val.userId + ",";
    		approvalStatus.push(val.approvalStatus);
    	});
	}
	if(typeof(param) == 'undefined'){
		param = "";
	}
}
/*报名审批  */
function approveApply(id,contestId,applyUserId){
	$("#refuseReason").val("");
	$("input[type=radio][name=approveStatus][value=2]").attr("checked",true);
	var d = dialog({
	    title: '报名审批',
	    /* content: '<input type="radio" value="2" name="approveStatus" checked="checked"/>审批通过'
	    		+'<input type="radio" value="3" name="approveStatus"/>审批拒绝'
	    		+'<br/><br/>驳回理由:<textarea rows="5" cols="10" id="refuseReason"></textarea>', */
	    content:$("#approveDiv"),
	    okValue: '确定',
	    ok: function () {
	        var approveStatus = GetRadioValue("approveStatus");
	        if(approveStatus == 3){
	        	if($.trim($("#refuseReason").val()) == ''){
	        		dialog.alert("请填写拒绝理由！");
	        		return false;
	        	}else{
	        		if($.trim($("#refuseReason").val()).length > 200){
	        			dialog.alert("拒绝理由不超过200字符！");
	        			return false;
	        		}
	        	}
	        }
	      //this.close(approveStatus);
	       //alert(GetRadioValue("approveStatus") + "-----" + $("#refuseReason").val());
	       $.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{"applyIds":id,"applyUserIds":applyUserId,"contestIds":contestId,"refuseReason":$.trim($("#refuseReason").val()),"approveStatus":approveStatus},
			url: "<%=request.getContextPath()%>/megagameManage/approveApplication.action",
			success:function(data){
				if(data == "SUCCESS"){
					dialog.alert("审批成功！", function (){
						search(true);
					});
				}else{
					dialog.alert("审批失败！");
				}
		    }
		});
	        this.remove();
	    }
	});
	d.width(350);
	d.height(150);
	/* d.addEventListener('close', function () {
	    alert(this.returnValue);
	}); */
	d.show();	
}
/*批量审批  */
function approveMany(){
	$("#refuseReason").val("");
	$("input[type=radio][name=approveStatus][value=2]").attr("checked",true);
	mmgridchk();
	if(applyIds == ''){
		dialog.alert("请选择数据！");
		return;
	}
	if(approvalStatus.contains("2") || approvalStatus.contains("3")){
		dialog.alert("部分数据已经审批，请重新勾选！");
		return;
	}
	approveApply(applyIds,contestIds,applyUserIds);
}

//判断数组中是包含某个元素 通用方法
Array.prototype.contains = function (element){
	for (var i=0; i<this.length;i++){
		if (this[i] == element){ 
			return true; 
		} 
	} 
	return false; 
}; 

function viewApply(rowIndex){//
	var rowData = $('#approveTable').mmGrid("row", rowIndex);
	window.location = "<%=request.getContextPath()%>/MyMegagame/toSeeMoreApplication.action?id="+rowData.id+"&megagameId="+rowData.contestId;
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

//查询
function query(){
	$('#approveTable').mmGrid().load({"page":1});
}

//查询
function search(notOnePage){
	if(notOnePage){
		//不从第一页开始
		$('#approveTable').mmGrid().load();
	}else{
		$('#approveTable').mmGrid().load({"page":1});		
	}
}

//重置
function clearAll(){
	$("#applyTimeBegin").val("");
	$("#applyTimeEnd").val("");
	$("#contestName").val("");
	$("#name").val("");
	$("#approvalStatus").val("0");
	query();
}

function setApprovalStatus(type){
	if(type == 3){
		$("#refuseReasonSpan").css("display","block");
		$("#refuseReason").val("");
	}else{
		$("#refuseReasonSpan").css("display","none");
		$("#refuseReason").val("拒绝理由");
	}
}

</script>
</head>
<body>
<div class="content">
	<!-- <h3>大赛报名审批</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
		<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">大赛报名审批</span>
	</div>
    <div class="btn_gr">
        <input type="button" class="btn_2" value="批量审批" onclick="approveMany();"/>
    </div>
    <form id="form">
	<div class="search_2">
    	<p>大赛名称：
            <input type="text" id="contestName" name="contestName"/>
            报名者：
            <input type="text" id="name" name="name"/>
            审批状态：
            <select id="approvalStatus" name="approvalStatus">
            	<option selected="selected" value="0">显示全部</option>
                <option value="1">待审批</option>
                <option value="2">审批通过</option>
                <option value="3">审批拒绝</option>
            </select>
        </p>
	</div>
    <div class="search_2" style="margin-top:-1px;">
    	<p>报名时间：
            <input type="text" id="applyTimeBegin" name="applyTimeBegin"/>
            <span>至</span>
            <input type="text" id="applyTimeEnd" name="applyTimeEnd"/>
        </p>
     	<input type="button" value="重置" onclick="clearAll();"/>
        <input type="button" value="查询" class="btn_cx" onclick="query();" />
	</div>
	</form>
	<div class="clear_both"></div>
     <div id="dataDiv">
     	<table id="approveTable"></table>
	  	<div id="paginator11-1" style="text-align:right;"></div>
     </div>
     
</div>
<div class="sp_jd" style="display:none;" id="approveDiv">
	<p>
        <input type="radio"  checked="checked" name="approveStatus" value="2" onclick="setApprovalStatus(2);"/>审批通过
        <input type="radio"  name="approveStatus" value="3" onclick="setApprovalStatus(3);"/>审批拒绝
    </p>
    	<p id="refuseReasonSpan" style="display:none;">
        	<span>拒绝理由:</span>
            <textarea rows="5" cols="20" id="refuseReason" name="refuseReason"></textarea>
        </p>
</div>

</body>
</html>
