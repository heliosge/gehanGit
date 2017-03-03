<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增计划</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>

<style type="text/css">
	.btn_3 {width: 68px;height: 25px;background: #D20001;border: none;color: white;float: right;}
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
	.lesson_add .add_gr { height:50px;}
</style>

<script type="text/javascript">
	var time = ${time};

	function cancel(){
		history.back(-1);
	}
	
	function viewStage(obj,status){
		if(status == 1){
			$(obj).attr("onclick","viewStage(this,2)");
			$(obj).parent().next(".lesson_add_2").hide();
		}else{
			$(obj).attr("onclick","viewStage(this,1)");
			$(obj).parent().next(".lesson_add_2").show();
		}
		
	}
	
	$(function(){
		initExamGrid();
		initCourseGrid();
		initJoinedGrid();
	})
	
	function initExamGrid(){
		//表格
		$('#exampleTable').mmGrid({
			root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:'<%=request.getContextPath()%>/myExamManage/getMyExamList.action',
			width: '1046px',
			height: 'auto',
			fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
			showBackboard: false,
		    checkCol: false,
		    indexCol: true,  //索引列
		    params:function(){
		    	var param = new Object();
		    	param.user_id = $("#userId").val();
		    	param.exam_status = $("#examStatus").val();
		    	param.is_passed = $("#isPassed").val();
		    	return param;
		    },
		    cols: [{title: 'ID', name: 'id', hidden:true},
		           {title:'考试名称', name:'title', width:170,align:'center'},
		           {title:'考试状态',name:'examStatus', width:50,align:'center',
	            	   renderer:function(val,item, rowIndex){
	            		   if(item.examState == 'BEFORE_START'){
	            			   return "未开始"; 
	            		   }else if((item.examStatus == 1 || item.examStatus == 2) && item.examState == 'PROCESSING'){
	            			   return "进行中";
	            		   }else if((item.examStatus == 3 || item.examStatus == 4) && item.examState == 'PROCESSING'){
	            			   return "已提交";
	            		   }else if((item.examStatus == 3 || item.examStatus == 4)&& item.examState == 'FINISHED'){
	            			   return "已结束";
	            		   }else if((item.examStatus == 1 || item.examStatus == 2) && item.examState == 'FINISHED'){
	            			   return "已过期";
	            		   }else{
	            			   return "-";
	            		   }
	              		}
	              	},
	              {title:'考试次数',name:'testTimes', width:40,align:'center'},
		           {title:'考试时间', name:'beginTime', width:200,align:'center',
	            	   renderer:function(val,item, rowIndex){
	               		return item.beginTime + "-" + item.endTime;
	               	}
	               },
	               {title:'得分/通过分数/总分',name:'passScore', width:100,align:'center',
	            	   renderer:function(val,item, rowIndex){
	            			   if(item.isScorePublished == 1){
	                			   return item.score+"/"+item.passScore+"/"+item.totalScore;
	                		   }else{
	                			   return "-/"+item.passScore+"/"+item.totalScore;
	                		   }
	               	}},
	               	{title:'是否通过',name:'isPassed', width:45,align:'center',
	            	   renderer:function(val,item, rowIndex){
	            		   if(item.isScorePublished == 1){//如果成绩发布
	            			   if(item.isPassed == 0){
	                   			   return "未通过"; 
	                   		   }else if(item.isPassed == 1){
	                   			   return "已通过";
	                   		   }else{
	                   			   	//如果考试已过期
	                   				if((item.examStatus == 1 || item.examStatus == 2) && item.examState == 'FINISHED'){
		             				   return "未通过";
		             			   	}else{
		             			   		return "-";
		             			   	}
	                   		   }
	            		   }else{
	            			   return "-";
	            		   }
	              		}
	              	}] ,
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
	
	function initCourseGrid(){
		//表格
		$('#exampleTable2').mmGrid({
			root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:'<%=request.getContextPath()%>/train/selectCourseStudyByMap.action',
			width: '1046px',
			height: 'auto',
			fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
			showBackboard: false,
		    checkCol: false,
		    indexCol: true,  //索引列
		    params:function(){
		    	var param = new Object();
		    	param.userId = $("#userId").val();
		    	param.learnProcess = $("#courseStatus").val();
		    	return param;
		    },
		    cols: [{title: 'ID', name: 'id', hidden:true},
		           /* {title:'课程编号', name:'course', width:60,align:'center', renderer:function(val,item, rowIndex){
              			return val == null ? '' : val.code;
              		}}, */
              		{title:'课程名称', name:'course', width:120,align:'center', renderer:function(val,item, rowIndex){
                  		return val == null ? '' : val.name;
               		}},
               		{title:'课程体系', name:'course', width:60,align:'center', renderer:function(val,item, rowIndex){
                   		return val == null ? '' : val.categoryName;
                   		}},
		           {title:'学习状态',name:'learnProcess', width:50,align:'center', renderer:function(val,item, rowIndex){
	            		  return val == 1?"学习中":"已完成";
	              		}
	              	},
		           {title:'学习开始时间', name:'startTime', width:120,align:'center', renderer:function(val,item, rowIndex){
	               		return val == null?'-':getSmpFormatDateByLong(val, true);
	               	}
	               },
	               {title:'学习结束时间', name:'endTime', width:120,align:'center', renderer:function(val,item, rowIndex){
	               		return val == null?'-':getSmpFormatDateByLong(val, true);
	               	}
	               },
	               {title:'获得学时', name:'course', width:60,align:'center', renderer:function(val,item, rowIndex){
	               		return val == null ? '' : item.learnProcess == 1?'-':val.learnTime;
	               	}
	               }
	               ] ,
		   plugins : [
		    	$('#paginator11-2').mmPaginator({
		    		totalCountName: 'total',    //对应MMGridPageVoBean count
		    		page: 1,                    //初始页
		    		pageParamName: 'page',      //当前页数
		    		limitParamName: 'pageSize', //每页数量
		    		limitList: [10, 20, 40, 50]
		    	})
		    ] 
		});
	}
	
	function initJoinedGrid(){
		//表格
		$('#exampleTable3').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:"<%=request.getContextPath()%>/train/selectMyTrainArrangeList.action",
	    	width: '1046px',
	    	height: 'auto',
	    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
	    	showBackboard: false,
	        indexCol: true,  //索引列
	        params: function(){
	        	var param = {};
	        	param.startStatus = $("#startStatus").val();
		    	return  param;
		    },
	     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
	               {title: '培训名称', name: 'name', width:140, align:'center'},
	 			   {title: '培训时间', name: 'beginTimeDate', width:120, align:'center', renderer:function(val, item, rowIndex){
	 				  return getSmpFormatDateByLong(val, true)+"—"+getSmpFormatDateByLong(item.endTimeDate, true);
				   }},
				   {title: '培训状态', name: 'beginTimeDate', width:60, align:'center', renderer:function(val, item, rowIndex){
		 				  return val>time?'未开始':((val < time && item.endTimeDate >time)?"进行中":"已结束");
				   }},
				   {title: '培训内容进度', name: 'isJoin', width:30, align:'center', renderer:function(val, item, rowIndex){
	 				   var c = 0;
	 				   $.each(item.contents,function(index, val){
	 					   if(val.endTimeDate < time){
	 						   c++;
	 					   }
	 				   })
	 				  return c+'/'+item.contents.length;
				   }},
				   {title: '获得学时/培训学时', name: 'allPeriod', width:30, align:'center', renderer:function(val, item, rowIndex){
	 				  return item.getPeriod+'/'+val;
				   }}
	           ],
	        plugins : [
	        	$('#paginator11-3').mmPaginator({
	        		totalCountName: 'total',    //对应MMGridPageVoBean count
	        		page: 1,                    //初始页
	        		pageParamName: 'page',      //当前页数
	        		limitParamName: 'pageSize', //每页数量
	        		limitList: [10, 20, 40, 50]
	        	})
	        ]
	    });
	}
	
	function search(){
		$('#exampleTable').mmGrid().load({"page":1});
	}
	
	function search2(){
		$('#exampleTable2').mmGrid().load({"page":1});
	}
	
	function search3(){
		$('#exampleTable3').mmGrid().load({"page":1});
	}
	
</script>
</head>
<body>

<div id="content" class="content">
	<!-- <h3>用户学习记录详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的成绩</span>
	</div>
			<input type="hidden" value="${USER_ID_LONG }" id="userId"/>
            <div class="tool_1" id="stage_div_1" style="width: 1044px;">
	            <span id="stage_title_1" style="padding-left:5px;width:200px"> 基本信息</span>
	            <a href="javascript:void(0)" style="background: rgb(0, 133, 254) none repeat scroll 0 0;height: 46px;line-height: 46px;margin-top: 0;text-align: center;width: 42px;" onclick="viewStage(this,1)" class="a_ss">
	            	<img style="margin-top: 18px;" src="<%=request.getContextPath()%>/images/img/ico_down.png">
	            </a>
            </div>
		    <div class="lesson_add_2" style="width:1044px;">
            	<div class="add_gr" >
		        	<div class="add_fl">
		          	 	<em>用户名：</em>
		            </div>
		            <div class="add_fr">
		            	${USER_NAME_STRING}
		            </div>
		        </div>
				        
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>姓名：</em>
		            </div>
		            <div class="add_fr">
		            	${USER_NAME }
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>部门：</em>
		            </div>
		            <div class="add_fr">
		            	${USER_BEAN.deptName }
		            </div>
		        </div>
		       </div>
		       
             <div class="tool_1" id="stage_div_1" style="width: 1044px;">
	            <span id="stage_title_1" style="padding-left:5px;width:200px">考试</span>
	            <a href="javascript:void(0)" style="background: rgb(0, 133, 254) none repeat scroll 0 0;height: 46px;line-height: 46px;margin-top: 0;text-align: center;width: 42px;" onclick="viewStage(this,1)" class="a_ss">
	            	<img style="margin-top: 18px;" src="<%=request.getContextPath()%>/images/img/ico_down.png">
	            </a>
            </div>
		    	<div class="lesson_add_2" style="width:1044px;">
		    		<div class="search_3" style="width:1044px;">
				        	<p>	
				        		考试状态：
				        		<select id="examStatus">
				        			<option value="0">全部</option>
					                <option value="1">未开始</option>
					                <option value="2">进行中</option>
					                <option value="3">已提交</option>
					                <option value="4">已结束</option>
					                <option value="5">已过期</option>
				        		</select>
				            	是否通过：
				            	<select id="isPassed">
				            		<option value="">全部</option>
				            		<option value="1">是</option>
				            		<option value="0">否</option>
				            	</select>
				        	</p>
				        	<input type="button" value="查询" class="btn_cx" onclick="search();"/>
				
				    </div>
		    		<table id="exampleTable"></table>
					<div id="paginator11-1" style="text-align:right;"></div>
	        	</div>
	        	
	        	<div class="tool_1" id="stage_div_1" style="width: 1044px;">
	            <span id="stage_title_1" style="padding-left:5px;width:200px">课程</span>
	            <a href="javascript:void(0)" style="background: rgb(0, 133, 254) none repeat scroll 0 0;height: 46px;line-height: 46px;margin-top: 0;text-align: center;width: 42px;" onclick="viewStage(this,1)" class="a_ss">
	            	<img style="margin-top: 18px;" src="<%=request.getContextPath()%>/images/img/ico_down.png">
	            </a>
            </div>
		    	<div class="lesson_add_2" style="width:1044px;">
		    		<div class="search_3" style="width:1044px;">
				        	<p>	
				        		学习状态：
				        		<select id="courseStatus">
				        			<option value="">全部</option>
					                <option value="1">学习中</option>
					                <option value="2">已完成</option>
				        		</select>
				        	</p>
				        	<input type="button" value="查询" class="btn_cx" onclick="search2();"/>
				
				    </div>
		    		<table id="exampleTable2"></table>
					<div id="paginator11-2" style="text-align:right;"></div>
	        	</div>
	        	
	        	<div class="tool_1" id="stage_div_1" style="width: 1044px;">
	            <span id="stage_title_1" style="padding-left:5px;width:200px">培训</span>
	            <a href="javascript:void(0)" style="background: rgb(0, 133, 254) none repeat scroll 0 0;height: 46px;line-height: 46px;margin-top: 0;text-align: center;width: 42px;" onclick="viewStage(this,1)" class="a_ss">
	            	<img style="margin-top: 18px;" src="<%=request.getContextPath()%>/images/img/ico_down.png">
	            </a>
            </div>
		    	<div class="lesson_add_2" style="width:1044px;">
		    		<div class="search_3" style="width:1044px;">
				        	<p>	
				        		培训状态：
				        		<select id="startStatus">
				        			<option value="">全部</option>
					                <option value="1">未开始</option>
					                <option value="2">进行中</option>
					                <option value="3">已结束</option>
				        		</select>
				        	</p>
				        	<input type="button" value="查询" class="btn_cx" onclick="search3();"/>
				
				    </div>
		    		<table id="exampleTable3"></table>
					<div id="paginator11-3" style="text-align:right;"></div>
	        	</div>
	        	
            	
        		</div>
	             <!-- <div class="button_cz">
		        	 <input type="button" class="btn_2" value="取消" onclick="cancel()"/>
	        	</div> -->
</body>
</html>
