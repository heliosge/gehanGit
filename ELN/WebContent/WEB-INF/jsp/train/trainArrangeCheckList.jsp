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
	.button{ 
		width: 70px; height: 30px; background: #d60500; color: #fff; font-family: '微软雅黑'; text-align: center; border: none; margin-left: 10px;
	}
	.lesson_add .add_gr{width:450px;}
	.lesson_add .add_gr .add_fl {width:100px;}
	.lesson_add .add_gr .add_fr {width:300px;} 
</style>

<script>
	$(function(){
		
		initReason();
		initGrid();
		initDatepicker();
		
		$("#timeType").val() == 1 ? $("#yearTab").attr('class', 'li_a').siblings().removeClass("li_a")
				:$("#monthTab").attr('class', 'li_a').siblings().removeClass("li_a");
		
		$("input[name='status']").click(function(){
			if($(this).val()==2){
				$("#reason").attr("disabled","disabled");
				$("#refuseReason").attr("disabled","disabled");
			}else{
				$("#reason").removeAttr("disabled");
				if($("#reason").val()=='其他'){
					$("#refuseReason").removeAttr("disabled");
				}
			}
		})
		
		$("#reason").change(function(){
			if($(this).val() == '其他'){
				$("#refuseReason").removeAttr("disabled");
			}else{
				$("#refuseReason").attr("disabled","disabled");
			}
		})
	})
	
	function initReason(){
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			url:"<%=request.getContextPath()%>/train/selectTrainConfig.action",
			success:function(data){
				if(data != null){
					if(data.checkReason){
						var html = '<option value="其他">其他</option>';
						$.each(data.checkReason.split("@"),function(index,val){
							html += '<option value="'+val+'">'+val+'</option>';
						})
						$("#reason").html(html);
					}
				}
			}
		});
	}
	
	function initGrid(){
		//表格
		$('#exampleTable').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:"<%=request.getContextPath()%>/train/selectTrainArrangeCheckList.action",
	    	width: $('#exampleTable').width(),
	    	height: 'auto',
	    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
	    	showBackboard: false,
	        multiSelect: true,
	        checkCol: true,
	        indexCol: true,  //索引列
	        params: function(){
		    	return  param();
		    },
	     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
	               {title: '培训名称', name: 'arrange', width:120, align:'center', renderer:function(val, item, rowIndex){
	 				  return val.name;
				   }},
	 			   {title: '培训时间', name: 'arrange', width:120, align:'center', renderer:function(val, item, rowIndex){
					   return val.beginTime+"-"+val.endTime;
				   }},
				   {title: '培训学时', name: 'arrange', width:30, align:'center', renderer:function(val, item, rowIndex){
					   return val.allPeriod;
				   }},
				   {title: '是否允许报名', name: 'arrange', width:30, align:'center', renderer:function(val, item, rowIndex){
					   return val.isJoin == 1?"是":"否";
				   }},
	 			   {title: '提交人', name: 'arrange', width:30, align:'center', renderer:function(val, item, rowIndex){
					   return val.submitUserName;
				   }},
	 			   {title: '提交时间', name: 'arrange', width:70, align:'center', renderer:function(val, item, rowIndex){
	 				  return getSmpFormatDateByLong(val.updateTime, true);
				   }},
				   {title: '状态', name: 'status', width:30, align:'center', renderer:function(val, item, rowIndex){
					   return val==1?'待审批':(val==2?'审批通过':(val==3?'审批拒绝':'审批取消'));
				   }},
				   {title: '操作', name: 'id', width:60, align:'center', renderer:function(val, item, rowIndex){
					   
					   var buttonStr ='';
					   if(item.status == 1){
						   buttonStr += '<a href="javascript:void(0);" onclick="checkArrangeDialog('+item.id+')" >审批</a> ';
					   }else{
						   buttonStr += '<a href="javascript:void(0);" style="color:#999;">审批</a> ';
					   }
					   buttonStr += '<a href="toTrainArrangeCheckDetail.action?id='+val+'">详情</a>';
					   return buttonStr;
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
	}
	
	var ids = [];
	function checkArrangeDialog(id){
		ids = [];
		ids.push(id);
		//初始化审批窗口
		$("#reason").val('其他');
		$("#refuseReason").val('');
		$($("input[name='status']")[1]).click();
		//弹出窗口
		dialog({
			width : 450,
			height : 300,
			title : '审批',
			content : $("#checkDialog"),
			fixed:true,
			 button: [
			          {
		              value: '确定',
		              callback: function () {
		            	  checkArrange();
		            	  this.close();
		              }
			          },
			          {
			              value: '取消',
			              callback: function () {
			            	  this.close();
			              }
				          }
			      ]
		}).showModal();
	}
	
	function checkArrangesDialog(){
		ids = [];
		var selectRows = $('#exampleTable').mmGrid().selectedRows();
    	$.each(selectRows, function(index, val){
    		if(val.status == 1 ){
    			ids.push(val.id);
    		}
    	});
    	if(ids.length == 0){
    		dialog.alert("请选择要审批的数据。");
    		return;
    	}
		//初始化审批窗口
		$("#reason").val('其他');
		$("#refuseReason").val('');
		$($("input[name='status']")[1]).click();
		//弹出窗口
		dialog({
			width : 450,
			height : 300,
			title : '审批',
			content : $("#checkDialog"),
			fixed:true,
			 button: [
			          {
		              value: '确定',
		              callback: function () {
		            	  checkArrange();
		            	  this.close();
		              }
			          },
			          {
			              value: '取消',
			              callback: function () {
			            	  this.close();
			              }
				          }
			      ]
		}).showModal();
	}
	
	
	
	function checkArrange(){
		var o = {};
		o.ids = ids;
		o.status = $("input[name='status']:checked").val();
		if(o.status == 3){
			if($("#reason").val() == '其他'){
				o.refuseReason = $("#refuseReason").val()==''?'其他':$("#refuseReason").val();
			}else{
				o.refuseReason = $("#reason").val();
			}
		}
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:o,
			url:"<%=request.getContextPath()%>/train/checkTrainArrange.action",
			success:function(data){
				if(data == 'SUCCESS'){
					search();
					dialog.alert("审批成功。");
				}else{
					dialog.alert("审批失败。");
				}
			}
		});
	}
	
	function param(){
		var o = {};
		o.name = $("#name").val();
		o.timeType = $("#timeType").val();
		o.status = $("#status").val();
		o.startTime = $("#startTime").val();
		o.endTime = $("#endTime").val();
		o.submitUserName = $("#submitUserName").val();
		return o;
	}
	
	
	function tabClick(val, obj){
		$(obj).attr('class', 'li_a').siblings().removeClass("li_a");
		$("#timeType").val(val);
		$("#timeValue").html("<option value='' selected>全部</option>");
		if(val == 1){
			var date = new Date();
			var year = date.getFullYear();
			for(var i = 5; i > -5; i--){
				$("#timeValue").append("<option value='"+(year+i)+"'>"+(year+i)+"</option>");
			}
			$("#timeTypeSpan").html("年度：");
		}else{
			for(var i = 1; i < 13; i++){
				$("#timeValue").append("<option value='"+i+"'>"+i+"</option>");
			}
			$("#timeTypeSpan").html("月度：");
		}
		search();
	}
	
	function search(){
		$('#exampleTable').mmGrid().load();
	}
	
	function reset(){
		$("#name").val('');
		$("#status").val('');
		$("#startTime").val('');
		$("#endTime").val('');
		$("#submitUserName").val('');
		search();
	}
	
	function initDatepicker() {
		$("#startTime,#endTime").datepicker({
			dateFormat : 'yy-mm-dd',
	 		  changeMonth: true,
	 	      changeYear: true
		});
	}
	

</script>

<body>

	<div id="checkDialog" style="display:none;">
		 <div class="lesson_add" style="width:450px;">
		 	<div class="add_gr">
	        	<div class="add_fl">
	                <em>是否通过：</em>
	            </div>
	            <div class="add_fr">
	            	<input type="radio" name="status" value="2"/>是
            		<input type="radio" name="status" value="3" checked/>否
	            </div>
	        </div>
	        <div class="add_gr">
	        	<div class="add_fl">
	                <em>拒绝原因：</em>
	            </div>
	            <div class="add_fr">
	            	<select id="reason"></select>
	            </div>
          	</div>
          	<div class="add_gr">
	        	<div class="add_fl">
	                &nbsp;
	            </div>
	            <div class="add_fr">
	            	<textarea id="refuseReason" ></textarea>
	            </div>
          	</div>
		 </div>
	</div>
	
	<div class="content">
		<!-- <h3>培训安排审批</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">培训安排审批</span>
		</div>
		<div class="ul_know" id="kn_ce">
				<ul id="ul_exam">
					<li class="li_a" onclick="tabClick(1, this)" id="yearTab">年度</li>
					<li onclick="tabClick(2, this)" id="monthTab">月度</li>
				</ul>
		</div>
		
		 <div class="btn_gr">
        <input type="button" class="btn_1" value="批量审批" onclick="checkArrangesDialog()"/>
    </div>
    
	<div class="search_2">
			<input type="hidden" id="timeType" value="1"/>
			<p>培训时间：<input type="text" id="arrangeStartTime"/>至<input type="text" id="arrangeEndTime"/>
        			培训名称：
                    <input type="text" id="name"/>		
        			提交人：
                    <input type="text" id="submitUserName"/>
            </p>
       </div>
	<div class="search_2">
		<p>
            	提交时间：<input type="text" id="startTime"/>至<input type="text" id="endTime"/>
                   	状态：
                    <select id="status">
                    	<option value="">全部</option>
                    	<option value="1">待审批</option>
                    	<option value="2">审批通过</option>
                    	<option value="3">审批拒绝</option>
                    	<option value="4">审批取消</option>
                    </select></p>
                    <input type="button" value="重置" class="btn_3" onclick="reset()"/>
                    <input type="button" value="查询" class="btn_cx" onclick="search()"/>
	</div>	
	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
	<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
	</div>
</body>
