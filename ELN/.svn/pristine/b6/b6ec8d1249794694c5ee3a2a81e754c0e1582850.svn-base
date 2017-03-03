<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>培训计划</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
</head>

<style>
	.ul_know{ width:1046px;  float:left;}
	.ul_know ul{ width:1046px; height:38px; float:left; border-bottom:1px solid #cccccc;}
	.ul_know ul li{ width:106px; height:36px; line-height:36px; text-align:center; float:left; margin-right:-1px; cursor: pointer;}
	.ul_know ul .li_a{ color:#0087d3; border-bottom:1px solid #fff;; border-top:2px solid #0087d3; border-left:1px solid #cccccc;border-right:1px solid #cccccc; font-weight:bold;}
	.content .btn_gr {padding-top: 48px;}
</style>

<script>
var arrange = ${arrange};
var contents = ${contents};
var time = ${time};

	$(function(){
		if(arrange.isRelease == 1){
			$("#saveButton").hide();
			$("#importButton").hide();
			$("#downloadButton").hide();
		}else{
			$("#exportButton").hide();
		}
		
		/* $.each(contents,function(index, val){
			if(val.endTimeDate.time < time){
				if(val.afterClassExam == 0){
					if(val.passPercent != 0){
						//有线下考试，录入成绩
						$("#content").append('<option value="2_'+val.id+'">'+val.content+'</content>');
					}else if(val.trainType == 2){
						//没有考试，通过是否签到来判断是否通过
						$("#content").append('<option value="1_'+val.id+'">'+val.content+'</content>');
					}
				}
			}
		}) */
		var c = 0;
		$.each(contents,function(index, val){
			if(val.trainType == 2 && val.endTimeDate.time < time){
				c++;
				$("#content").append('<option value="2_'+val.id+'">'+val.content+'</content>');
			}
		})
		if(arrange.afterClassExam == 0){
			if(arrange.passPercent == 0){
				if(c > 0){
					$("#content").append('<option value="1_'+arrange.id+'">签到即可通过整个考试</content>');
				}
			}else{
				$("#content").append('<option value="3_'+arrange.id+'">整个培训的通过考试</content>');
			}
		}
		
		$("#content").change(function(){
			search();
		})
		
		initGrid();
	})
	
	function initGrid(){
		//表格
		$('#exampleTable').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:"<%=request.getContextPath()%>/train/selectTrainArrangeUserScoreList.action",
	    	width: $('#exampleTable').width(),
	    	height: 'auto',
	    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
	    	showBackboard: false,
	        //multiSelect: true,
	       // checkCol: true,
	        indexCol: true,  //索引列
	        params: function(){
		    	return  param();
		    },
	     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
	               {title: '用户名', name: 'user', width:60, align:'center', renderer:function(val, item, rowIndex){
	 				  return val.userName;
				   }},
	               {title: '姓名', name: 'user', width:60, align:'center', renderer:function(val, item, rowIndex){
	 				  return val.name;
				   }},
	               {title: '岗位', name: 'user', width:60, align:'center', renderer:function(val, item, rowIndex){
	 				  return val.deptName;
				   }},
	               {title: '部门', name: 'user', width:60, align:'center', renderer:function(val, item, rowIndex){
	 				  return val.postName;
				   }},
	 			   {title: '<input name="all" type="checkbox" onclick="selectAll(this)" style="height:1em;"/> 签到', name: 'isSign', width:60, align:'center', renderer:function(val, item, rowIndex){
	 				  console.info(item);
	 				   return val ==1 ?'<input name="isSign" type="checkbox" checked/> 签到':'<input type="checkbox"  name="isSign" /> 签到';
				   }},
	 			   {title: '成绩', name: 'score', width:120, align:'center', renderer:function(val, item, rowIndex){
	 				  return '<input value="'+(val==null?'':val)+'" id="'+item.user.id+'" name="score"/>';
				   }},
	 			   {title: '是否通过', name: 'isPass', width:60, align:'center', renderer:function(val, item, rowIndex){
	 				  return val==1?"通过":"未通过";
				   }}
	           ]
	    });
	}
	
	function selectAll(obj){
		if(obj.checked == true){
			$.each($("input[name='isSign']"),function(index, val){
				val.checked = true;
			})
		}else{
			$.each($("input[name='isSign']"),function(index, val){
				val.checked = false;
			})
		}
	}
	
	function search(){
		$('#exampleTable').mmGrid().load();
	}
	
	function param(){
		var o = {};
		o.contentId = $("#content").val().split("_")[1];
		o.isSign = $("#content").val().split("_")[0];
		return o;
	}
	
	function save(){
		if(!$("#content").val()){
			dialog.alert("请选择培训内容");
			return;
		}
		$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:toParam(),
	   		url:"<%=request.getContextPath()%>/train/submitTrainArrangeScore.action",
	   		success:function(data){
	   			if(data == 'SUCCESS'){
	   				dialog.alert("保存成功。", function(index, val){
	   					search();
	   				})
	   			}else{
					dialog.alert("保存失败。");	   				
	   			}
	   	    }
	   	});
	}
	
	function toParam(){
		var scoreInput = $("input[name='score']");
		var scores = [];
		var ids = [];
		var signItems = [];
		$.each(scoreInput,function(index,val){
			scores.push($(val).val()==''?0:$(val).val());
			ids.push($(val).attr("id"));
		})
		$.each($("input[name='isSign']"),function(index,val){
			if(val.checked == true){
				signItems.push(1);
			}else{
				signItems.push(2);
			}
		})
		var o = {};
		o.scores = scores;
		o.ids = ids;
		o.signItems = signItems;
		o.contentId = $("#content").val().split("_")[1];
		o.isSign = $("#content").val().split("_")[0];
		return o;
	}
	
	function downloadTemplate(){
		$("input[name='contentId']").val($("#content").val().split("_")[1]);
		//$("#form_").submit();
		$("#form").submit();
	}
	
	function exportExcel(){
		$("input[name='contentId']").val($("#content").val().split("_")[1]);
		$("#form_").submit();
	}
	
	var artDialog;
	function importFile(){
		if(!$("#content").val()){
			dialog.alert("请选择培训内容");
			return;
		}
		artDialog = dialog({
			 url:"<%=request.getContextPath()%>/train/toImportArrangeScoreFile.action?contentId="+$("#content").val().split("_")[1],
		        title:"导入成绩" ,
				height: 250,
				width: 450
		}).showModal();
	}
	
	
</script>

<body>
	<div class="content">
		<!-- <h3>培训成绩详情</h3> -->
    <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">培训成绩详情</span>
	</div>
	<div class="search_2">
			<input type="hidden" id="timeType" value="1"/>
        	<p>培训内容：
                    <select id="content">
                    </select>
     			</p>
     				<input type="button" value="导入成绩" class="btn_cx" onclick="importFile()" id="importButton"/>
     				<input type="button" value="下载模板" class="btn_cx" onclick="downloadTemplate()" id="downloadButton"/>
     				<input type="button" value="导出成绩" class="btn_cx" onclick="exportExcel()" id="exportButton"/>
                    <input type="button" value="保存" class="btn_cx" onclick="save()" id="saveButton"/>
       </div>
       
    <form id="form" action="<%=request.getContextPath()%>/train/downloadTrainScoreTemplate.action" method="post">
       <input type="hidden" value="" name="contentId"/>
    </form>
    
     <form id="form_" action="<%=request.getContextPath()%>/train/exportTrainScore.action" method="post">
    	<input type="hidden" value="" name="contentId"/>
    </form>
	
	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
	<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
	</div>
</body>
