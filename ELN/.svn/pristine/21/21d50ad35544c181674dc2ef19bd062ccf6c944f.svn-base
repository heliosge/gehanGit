<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>考试安排</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/json2.js"></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!--日期控件  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/css/flick/jquery-ui.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery.ui.datepicker-zh-CN.js" ></script>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';//登录用户ID
//var examType = ${type};//考试类型
$(function(){
	//$("#type").val(examType);
	loadMMGrid();
	/* $("#type"+examType).addClass("li_this");
	$("div .lesson_tab_1").find("li[id!='type"+examType+"']").removeClass(); */
});

function setExamList(type){
	//$("#type").val(type);
	/* $("#type"+type).addClass("li_this");
	$("div .lesson_tab_1").find("li[id!='type"+type+"']").removeClass();
	$('#examTable').mmGrid().load({"type":$("#type").val()}); */
	window.location = "<%=request.getContextPath()%>/exam/exam/gotoSimExamList.action";
}

function loadMMGrid(){
	//$("#type").val(type);
	$('#examTable').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/exam/exam/getOfficialVoList.action',
		width: '1065px',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: true,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    params:function(){
	    	var param = new Object();
	    	param.type = $("#type").val();
	    	param.title = escapeForSql($.trim($("#title").val()));
	    	param.beginTimeBegin = $("#beginTimeBegin").val();
	    	param.endTimeEnd = $("#endTimeEnd").val();
	    	param.examState = $("#examState").val();
	    	return param;
	    },
	    cols: [{title: 'ID', name: 'id', hidden:true},
	           {title:'考试名称', name:'title', width:150,align:'center', 
	        	   renderer:function(val,item, rowIndex){
				       return '<a href="javascript:void(0);" onclick="viewExam('+item.id+')">'+val+'</a>';
	        	   }
               },
               {title:'考试时长',name:'duration', width:30,align:'center'},
	           {title:'考试时间', name:'beginTime', width:200,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.beginTime != null &&　item.endTime != null){
            			   return item.beginTime + "-" + item.endTime;
            		   }else{
            			   return "-";
            		   }
               		  
           		   }
               },
             {title:'答案是否公开',name:'isAnswerPublic', width:60,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.isAnswerPublic){
            			   return "公开"; 
            		   }else{
            			   return "不公开";
            		   }
              			
              		}
              	},
              	{title:'考试次数',name:'allowTimes', width:40,align:'center'},
              	{title:'通过分数/试卷总分',name:'passScore', width:40,align:'center',
             	   renderer:function(val,item, rowIndex){
             		   if(item.passScore == null){
             			  return "-/"+item.totalScore;
             		   }else{
             			  return item.passScore+"/"+item.totalScore;
             		   }
                		
                	}
                },
              {title:'考试创建时间',name:'createTime', width:100,align:'center'},
              {title:'考试状态',name:'examStatus', width:40,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.examState == 'NOT_PUBLISHED'){
            			   return "未发布"; 
            		   }else if(item.examState == 'BEFORE_START'){
            			   return "未开始";
            		   }else if(item.examState == 'PROCESSING'){
            			   return "进行中";
            		   }else if(item.examState == 'FINISHED'){
            			   return "已结束";
            		   }else{
            			   return "";
            		   }
              		}
              	},
               {title:'操作',name:'operation', width:100,align:'center',
                	renderer:function(val,item, rowIndex){
                		var str= '';
	               		var str_del = '<a href="javascript:void(0);" onclick="deleteExam('+ item.id +')" >删除</a>&nbsp;';
	               		var str_edit = '<a href="javascript:void(0);" onclick="editExam('+ rowIndex +')" >修改</a>&nbsp;';
	               		var str_publish = '<a href="javascript:void(0);" onclick="publishExam('+ rowIndex +')" >发布</a>&nbsp;';
	               		var str_cancel = '<a href="javascript:void(0);" onclick="cancelExam('+ item.id +')" >取消</a>&nbsp;';
                	   if(item.examState == 'NOT_PUBLISHED'){
                		    return str_del + str_edit + str_publish;
              		   }else if(item.examState == 'BEFORE_START'){
              			 	return str_cancel + str_edit;
              		   }else if(item.examState == 'PROCESSING'){
              			   return "";
              		   }else if(item.examState == 'FINISHED'){
              			   return "";
              		   }else{
              			   return "";
              		   }
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
}

/*查看详情  */
function viewExam(id){
	window.location = "<%=request.getContextPath()%>/exam/exam/gotoViewExam.action?examId="+id + "&type=" +$("#type").val();
}

/*删除考试安排  */
function deleteExam(id){
	dialog.confirm('确认删除吗？', function(){
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			contentType:"application/json; charset=utf-8",
			data:JSON.stringify([id]),
			url: "<%=request.getContextPath()%>/exam/exam/deleteExams.action",
			success:function(data){
				if(data == "SUCCESS"){
					dialog.alert("操作成功！", function (){
						search(true);
					});
				}else{
					dialog.alert("操作失败！");
				}
		    }
		});
	});
}
/*编辑考试安排  */
function editExam(rowIndex){
	var rowData = $('#examTable').mmGrid("row", rowIndex);
	window.location = "<%=request.getContextPath()%>/exam/exam/gotoEditExam.action?examId="+rowData.id+"&type="+$("#type").val()+"&examState="+rowData.examState;
}
/*新增考试安排  */
function addExam(){
	window.location = "<%=request.getContextPath()%>/exam/exam/gotoAddExam.action?type="+$("#type").val();
}
/*取消考试  */
function cancelExam(id){
	dialog.confirm('确认取消吗？', function(){
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			contentType:"application/json; charset=utf-8",
			data:JSON.stringify([id]),
			url: "<%=request.getContextPath()%>/exam/exam/cancelExam.action",
			success:function(data){
				if(data == "SUCCESS"){
					dialog.alert("操作成功！", function (){
						search(true);
					});
				}else{
					dialog.alert("操作失败！");
				}
		    }
		});
	});
}
/*发布考试安排  */
function publishExam(rowIndex){
	var rowData = $('#examTable').mmGrid("row", rowIndex);
	var curDate = new Date().getTime();
	var beginTime = new Date(Date.parse(rowData.beginTime.replace(/-/g,"/"))).getTime();
	if(beginTime < curDate){
		dialog.alert("考试开始时间已到达，不可以发布考试！");
		return;
	}
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":rowData.id},
		url: "<%=request.getContextPath()%>/exam/exam/isAddUser.action",
		success:function(data){
			if(data == false){
				dialog.alert("请添加考试人员！");
			}else{
				dialog.confirm('确认发布吗？', function(){
					$.ajax({
						type:"POST",
						async:true,  //默认true,异步
						contentType:"application/json; charset=utf-8",
						data:JSON.stringify([rowData.id]),
						url: "<%=request.getContextPath()%>/exam/exam/publishExam.action",
						success:function(data){
							if(data == "SUCCESS"){
								dialog.alert("操作成功！", function (){
									search(true);
								});
							}else{
								dialog.alert("操作失败！");
							}
					    }
					});
				});
			}
	    }
	});
	
}

/*批量删除考试安排  */
function deleteMany(){
	mmgridchk();
	if(ExamIds == ''){
		dialog.alert("请选择数据！");
		return false;
	}
	if(examState.contains("BEFORE_START") || examState.contains("PROCESSING") || examState.contains("FINISHED") || examState.contains("PUBLISHED")){
		//alert(examState);
		dialog.alert("有未符合删除条件的数据，请重新勾选！");
		return false;
	}
	dialog.confirm('确认删除吗？', function(){
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			contentType:"application/json; charset=utf-8",
			data:JSON.stringify(ExamIds),
			url: "<%=request.getContextPath()%>/exam/exam/deleteExams.action",
			success:function(data){
				if(data == "SUCCESS"){
					dialog.alert("操作成功！", function (){
						search(true);
					});
				}else{
					dialog.alert("操作失败！");
				}
		    }
		});
	});
}

var ExamIds = [];
var examState = [];
/*获取表格中选中数据信息  */
function mmgridchk(){
	ExamIds = [];
	examState = [];
	var selectRows = $('#examTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
    	$.each(selectRows, function(index, val){
    		ExamIds.push(val.id);
    		examState.push(val.examState);
    	});
	}
	if(typeof(param) == 'undefined'){
		param = "";
	}
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

//查询
function query(){
	$('#examTable').mmGrid().load({"pageNo":1});
	/*,"title":escapeForSql($.trim($("#title").val())),"type":$("#type").val(),
									"beginTimeBegin":$("#beginTimeBegin").val(),"endTimeEnd":$("#endTimeEnd").val(),
									"examState":$("#examState").val()  */
}

//查询
function search(notOnePage){
	if(notOnePage){
		//不从第一页开始
		$('#examTable').mmGrid().load();
	}else{
		$('#examTable').mmGrid().load({"page":1});		
	}
}

$(document).ready(function() {     
	$("#beginTimeBegin").datepicker({
		dateFormat:'yy-mm-dd',
		changeMonth: true,
        changeYear: true
	});
	$("#endTimeEnd").datepicker({
		dateFormat:'yy-mm-dd',
		changeMonth: true,
        changeYear: true
	});
});

//重置
function clearAll(){
	$("#beginTimeBegin").val("");
	$("#endTimeEnd").val("");
	$("#title").val("");
	$("#examState").val("ALL_STATE");
	query();
}

</script>
<style type="text/css">
.content h3 {
  background: none;
  width: 1052px;
  padding-left: 20px;
  color: #3a3a3a;
  border-bottom: 1px solid #cccccc;
  padding-bottom: 10px;
  margin-bottom: 10px;
}
</style>
</head>
<body>
<input type="hidden" id="type" name="type" value="1"/>
<div class="content">
	<%-- <h3><img src="<%=request.getContextPath() %>/images/back.png"/> 考试安排</h3> --%>
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">考试安排</span>
	</div>
    <div class="lesson_tab_1">
        	<ul>
            	<li class="li_this" id="type1">正式考试</li>
                <li id="type3" onclick="setExamList(3);">模拟考试</li>
            </ul>
	</div>
    <div class="content_2">
        <div class="btn_gr fl">
            <input type="button" class="btn_1" value="新增考试" onclick="addExam();"/>
            <input type="button" class="btn_2" value="批量删除" onclick="deleteMany();"/>
        </div>
        <form id="form">
        <div class="search_2 fl">
            <p>考试名称：<input type="text" id="title" name="title"/>
	                考试开始时间：<input type="text" id="beginTimeBegin" name="beginTimeBegin" readonly="readonly"/>
	                <span>至</span>
	                <input type="text" id="endTimeEnd" name="endTimeEnd" readonly="readonly"/>
	                考试状态：
	                <select id="examState" name="examState">
	                    <option selected="selected" value="ALL_STATE">显示全部</option>
	                    <option value="NOT_PUBLISHED">未发布</option>
	                    <option value="BEFORE_START">未开始</option>
	                    <option value="PROCESSING">进行中</option>
	                    <option value="FINISHED">已结束</option>
	                </select>
	    
	            </p>
	            <input type="button" value="重置" onclick="clearAll();"/>
	            <input type="button" value="查询" class="btn_cx" onclick="query();"/>
	    
	        </div>
        </form>
         <div class="clear_both"></div>
	     <div id="dataDiv">
	     	<table id="examTable"></table>
		  	<div id="paginator11-1" class="mmPaginator" style="text-align:right;"></div>
	     </div>
        <!-- <div class="tab_4">
            <table width="100%">
                <tr class="tr1">
                    <th width="28px;"><input type="checkbox" /></th>
                    
                    
                    <th width="132px;">考试名称</th>
                    <th width="120px;">考试时长（分钟）</th>
                    <th width="132px;">考试时间</th>
                    <th width="90px;">答案是否公开</th>
                    <th width="66px;">考试次数</th>
                    <th width="124px;">通过分数/试卷总分</th>
                    <th width="126px;">考试创建时间</th>
                    <th width="60px;">考试状态</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <td><input type="checkbox" /></td>
                    <td><a href="#">测试发布</a></td>
                    <td>888</td>
                    <td>2015-06-10 12：00--2015-06-10 12：00</td>
                    <td>否</td>
                    <td>99</td>
                    <td>6/10</td>
                    <td>2015-06-10 12：00</td>
                    <td>未发布</td>
                    <td><a href="#">删除</a><a href="#">修改</a><a href="#">发布</a></td>
                </tr>
                <tr>
                    <td><input type="checkbox" /></td>
                    <td><a href="#">测试发布</a></td>
                    <td>888</td>
                    <td>2015-06-10 12：00--2015-06-10 12：00</td>
                    <td>是</td>
                    <td>99</td>
                    <td>6/10</td>
                    <td>2015-06-10 12：00</td>
                    <td>已结束</td>
                    <td><a href="#">取消</a><a href="#">修改</a><a href="#">发布</a></td>
                </tr>
                
               
               
               
                
                
               
            </table>
                
                
            
         </div> -->
    <!--  <div class="page clear">
     	<div class="left_page fl">
        	<a href="#" class="first">20</a>
            <a href="#">40</a>
            <a href="#">100</a>
            <a href="#">200</a>
        </div>
        <div class="right_page fr">
        	<span>共254条</span>
            <a href="#" class="r_first">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
            <a href="#">></a>
            <span class="span_1">去第<input type="text" />页</span>
            <input type="button" name="tz" value="跳转" class="btn_2"/>            
        </div>
     </div> -->
   </div> 
</div>
</body>
</html>
