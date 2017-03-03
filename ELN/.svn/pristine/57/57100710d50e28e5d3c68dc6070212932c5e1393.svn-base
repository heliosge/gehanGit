<%@page import="com.jftt.wifi.bean.vo.PostStage"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>查看学员进度</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
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
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<style type="text/css">


html, body{
	height: 100%;
}

.mmGrid,.mmPaginator{
    
}


.span_btus{
	margin:2px 2px;
}
.span_btu{
	display: inline-block;
	background-color: #4ABCF0;
	font-size: 12px;
	
	padding: 0px 5px;
	cursor: pointer;
	border-radius: 5px;
	min-width: 40px;
	text-align: center;
	color: #FFFFFF;
	margin: 0px 2px;
}
.span_btu_1{
	background-color: #34CC67;
}
.span_btu_2{
	background-color: #FF6766;
}
.span_btu_3{
	background-color: #F8CB0E;
}

</style>

<script type="text/javascript">


$(function(){
	
	
	
});

function renderTable(i){
	if(!i){
		dialog.alert("数据加载出错!");
		goBack();
		return;
	}
	//表格
	$('#exampleTable_'+i).mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/map/stage/apply.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        //checkCol: true,
        multiSelect: true,
        indexCol: true,  //索引列
        params:function(){
        	var param = new Object();
        	param.deptName = $.trim($("#depName_"+i).val());
        	param.postName = $.trim($("#postName_"+i).val());
        	param.stageId = $("#stageId_"+i).val();
        	param.postId=<%=request.getAttribute("postId")%>
        	param.type=1;
        	return param;
        },
        cols: [{title: '姓名', name: 'userName', width:30},
			   {title: '部门', name: 'deptName', width:50},
			   {title: '岗位', name: 'postName', width:50},
			   {title: '完成必须课/总课程', name: 'obligatoryProp', width:40},
			   {title: '完成选修课/总课程', name: 'electiveProp', width:60,renderer:function(val,item,rowIndex){
				   return val;
			   }},
			   {title: '考试是否通过', name: 'isPassed', width:40},
			   {title: '完成进度', name: 'progress', width:40},
			   {title: '审核状态', name: 're_start_approve_status', width:40,renderer:function(val,item,rowIndex){
				   if(val==3){
					   return '审核不通过';
				   }else if(val == 4){
					   return '审核通过';
				   }else{
					   return '未审核';
				   }
			   }},
			   {title: '操作', name: 'id', width:40,renderer:function(val,item,rowIndex){
				   var htm="<a href='javascript:void(0);' onclick='approveApply("+val+","+i+","+rowIndex+")' >审核</a>";
				   return htm;
			   }}
           ],
        plugins : [
        	$('#paginator11-1_'+i).mmPaginator({
        		totalCountName: 'total',    //对应MMGridPageVoBean count
        		page: 1,                    //初始页
        		pageParamName: 'page',      //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [10, 20, 40, 50]
        	})
        ]
    });
	
}
//查询
function search(i){
	
	$('#exampleTable_'+i).mmGrid().load({"page":1});
}
//重置
function clearAll(i){
	$("#depName_"+i).val("");
	$("#postName_"+i).val("");
}

//收起
function doUp(i){
	$(".up_"+i).hide();
	$(".down_"+i).show();
	$('#map_'+i+'>div').hide();
	$('#map_'+i+'>h5').hide();
}

//放下
function doDown(i){
	$(".down_"+i).hide();
	$(".up_"+i).show();
	renderTable(i);
	$('#map_'+i+'>div').show();
	$('#map_'+i+'>h5').show();
	
	
}


/*审批  */
function approveApply(id,i,rowIndex){
	var item=$('#exampleTable_'+i).mmGrid().row(rowIndex);
    if(item&&item.reStartApproveStatus){
    	$('input[name="status"][value='+item.reStartApproveStatus+']').attr("checked",true);
    	$("#reason").val(item.reStartRefuseReason);
    	changeStatus(item.reStartApproveStatus);
    }

	var d = dialog({
	    title: '重新申请审批',
	    content:$("#approveDiv"),
	    okValue: '确定',
	    ok: function () {
	        var status = GetRadioValue("status");
	        var reason = $.trim($("#reason").val());
	        if(status == 3){
	        	if(reason == ''){
	        		dialog.alert("请填写拒绝理由！");
	        		return false;
	        	}else{
	        		if(reason.length > 200){
	        			dialog.alert("拒绝理由不超过200字符！");
	        			return false;
	        		}
	        	}
	        }
	        doExamine(id,i,status,reason);
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
function doExamine(id,i,status,reason){
	$.ajax({
		type:"POST",
		asysc:true,
		data:{"id":id,"status":status,"reason":reason},
		dataType:"json",
		url:"<%=request.getContextPath()%>/map/examinePromotion.action",
		success:function(data){
			if(data=="SUCCESS"){
				
				dialog.alert('审核成功！')
				search(i);
			}else{
				dialog.alert('审核失败！')
			}
		}
	})
}
function changeStatus(status){
	if(!status){
		return;
	}
	if(status==3){
		$('#refuseReason').show();
	}else{
		$('#refuseReason').hide();
	}
}
//返回
function goBack(){
			window.location.href = "<%=request.getContextPath()%>/map/toMapManage.action";
}




</script>
</head>
<body style="overflow-x:hidden;">

	<div id="course_all">
            <div class="notes_list fl">
            	<!-- <h3>查看晋升失败学员</h3> -->
            	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
					<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
					<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">查看晋升失败学员</span>
				</div>
            	<% List<PostStage> stageList =(List<PostStage>)request.getAttribute("stageList");
            	if(stageList!=null&&stageList.size()>0){
            	for(int i=0;i<stageList.size();i++){ 
            		PostStage item = stageList.get(i);
            	  %>
            	<div class="map" id="map_<%=i+1 %>">
                	<h4>
                    	<span><%=i+1 %>阶段 <%=item.getPostName() %></span>
                        <a href="javascript:void(0)" onclick="doUp(<%=i+1 %>)" class="sq up_<%=i+1 %>" style="display:none">收起</a>
                        <a href="javascript:void(0)" onclick="doDown(<%=i+1 %>)" class="sq down_<%=i+1 %>">放下</a>
                    </h4>
                    <div class="xq" style="display:none">
                    	<p><%= item.getDescription() %></p>
                    </div>
                    <div style="display:none">
                     <h4>
                    	<span>当前阶段学员</span>
                     </h4>
                    </div>
                    <div class="search_3"  style="display:none">
                        <span>部门：</span><input id="depName_<%=i+1%>"  type="text" />
                        <span>岗位：</span><input id="postName_<%=i+1%>" type="text" />
                        <input id="stageId_<%=i+1%>" value="<%=item.getId() %>" type="hidden"/>
                       <input type="button" class="btn_5" onclick="search(<%=i+1 %>)" value="查询" />
                   	   <input type="button" class="btn_6" onclick="clearAll(<%=i+1 %>)" value="重置" />
                    
                    </div>
                    	<div  id="exampleTable_<%=i+1 %>" class="tab_1">
		
              </div>
              <div   id="paginator11-1_<%=i+1 %>" align="right" style="margin-right:10px;" ></div>
                 
                    </div>
                    <%}} %>
                
                
                	
        	</div>
    </div>
	
	<div class="sp_jd" style="display:none;" id="approveDiv">
	<p>
        <input type="radio"  checked="checked" name="status" onclick="changeStatus(4)" value="4"/>审批通过
        <input type="radio"  name="status" onclick="changeStatus(3)" value="3"/>审批拒绝
    </p>
    	<p id="refuseReason" style="display:none">
        	<span>拒绝理由:</span>
            <textarea rows="5" cols="20" id="reason" name="reason"></textarea>
        </p>
     </div>
</body>
</html>
