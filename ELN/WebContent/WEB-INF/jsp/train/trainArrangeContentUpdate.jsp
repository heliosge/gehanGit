<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增安排</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- 进度条 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqmeter.min.js"></script>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<style type="text/css">
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
	.btn_3 {width: 68px;height: 25px;background: #D20001;border: none;color: white;float: right;}
	.ztree {width: 250px;min-height: 300px;height: auto;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.webuploader-container{
		margin-top: 11px;
    	float: left;
    	margin-left:5px;
	}
	/* .webuploader-pick{background: #d60500; } */
	.webuploader-element-invisible{height:31px;}
	.add_sc{margin-top:5px;}
</style>

<script type="text/javascript">
var arrangeId = ${arrange.id};
var arrange = ${arrange};
var num = 0;
var contents = [];
var addContent = {};
var updateFlag = true;

$(function(){
	upLoadImg("picker");
	initDatepicker();
	initValidate();
	$("input[name='trainType']").click(function(){
		if($(this).val() == 1){
			$("#content2").hide();
			$("#place").attr("disabled","disabled");
			$("#passPercent").attr("disabled","disabled");
			$("#courseName").val('');
			$("#courseId").val('');
			$("#beforeClassCourseName").val('');
			$("#beforeClassCourseSize").val('');
			$("#beforeClassCourseId").val('');
		}else{
			$("#content2").show();
			$("#place").removeAttr("disabled");
			$("#passPercent").removeAttr("disabled");
			$("#courseName").val('');
			$("#courseId").val('');
			$("#beforeClassCourseName").val('');
			$("#beforeClassCourseSize").val('');
			$("#beforeClassCourseId").val('');
		}
	});
	
	//初始化问卷
	//initPaperGrid();
	//初始化问卷体系
	initPaperTree();
	
	//填充每一阶段
	initContent();
});
	
	function selectContents(){
		$.ajax({
	   		type:"POST",
	   		async:false,  //默认true,异步
	   		data:{arrangeId:arrangeId},
	   		url:"<%=request.getContextPath()%>/train/selectTrainArrangeContents.action",
	   		success:function(data){
	   			contents = data;
	   	    }
	   	});
	}
	
	function initContent(){
		selectContents();
		$("#total .add_sc").remove();
		var afterHtml = '';
		var beforeHtml = '';
		var addButton= '<div class="add_sc"><div onclick="" class="add_sc1"><img src="<%=request.getContextPath()%>/images/img/add_sc.png"><span style="cursor: pointer;" onclick="addNewContent()">继续新增</span></div></div>';
		$.each(contents,function(index,val){
			if(index <= num){
				beforeHtml += '<div class="add_sc"><div class="add_sc1" style="float:left;"><span style="cursor: pointer;" onclick="showContent('+index+');">阶段'+(index+1)+'</span></div>';
				beforeHtml += '<div class="add_sc1" style="float:left;padding-left:300px;"><span style="cursor: pointer;" onclick="deleteContent('+val.id+',this,'+index+');">删除</span></div></div>';
			}else{
				afterHtml += '<div class="add_sc"><div class="add_sc1" style="float:left;"><span style="cursor: pointer;" onclick="showContent('+index+');">阶段'+(index+1)+'</span></div>';
				afterHtml += '<div class="add_sc1" style="float:left;padding-left:300px;"><span style="cursor: pointer;" onclick="deleteContent('+val.id+',this,'+index+');">删除</span></div></div>';
			}
		})
		if(contents.length > num){
			$(".msg-box").hide();
			updateFlag = true;
	    	fillContent(contents[num]);
		 	$("#addForm").before(beforeHtml);
			$("#addForm").after(afterHtml+addButton);
		}else{
			updateFlag = false;
			$("#addForm").before(beforeHtml);
			$("#addForm").before(afterHtml);
			$("#addForm").before(addButton);
			fillContent(addContent);
		}
	}
	
	function showContent(index){
		num = index;
		if(!updateFlag){
			initAddContent();
		}
		initContent();
	}
	
	function initAddContent(){
		addContent.content = $("#name").val();
		addContent.teacherId = teacherId;
		addContent.teacherName = $("#teacherName").val();
		addContent.trainType = $("input[name='trainType']:checked").val();
		addContent.beginTime = $("#beginTime").val();
		addContent.endTime = $("#endTime").val();
		addContent.signBeginTime = $("#signBeginTime").val();
		addContent.signEndTime = $("#signEndTime").val();
		addContent.score = $("#score").val();
		addContent.period = $("#period").val();
		addContent.courseId = $("#courseId").val();
		addContent.courseName = $("#courseName").val();
		addContent.place = $("#place").val();
		addContent.passPercent = $("#passPercent").val();
		/* addContent.aceDuration = $("#aceDuration").val();
		addContent.aceAllowTimes = $("#aceAllowTimes").val();
		addContent.afterClassExamName = $("#afterClassExamName").val();
		addContent.afterClassExam = $("#afterClassExam").val(); */
		addContent.afterClassTest = $("#afterClassTestId").val();
		addContent.afterClassTestName = $("#afterClassTestName").val();
		addContent.beforeClassCourseId = $("#beforeClassCourseId").val();
		addContent.beforeClassCourseName = $("#beforeClassCourseName").val();
		addContent.beforeClassFilePath = $("#beforeClassFilePath").val();
		addContent.beforeClassFileName = $("#beforeClassFileName").val();
		addContent.beforeClassFileSize = $("#beforeClassFileSize").val();
	}
	
	function fillContent(content){
		$("#name").val(content.content);
		teacherId = content.teacherId;
		$("#teacherName").val(content.teacherName);
		$.each($("input[name='trainType']"), function(index, val){
			if($(val).val() == content.trainType){
				val.checked = true;
				$(val).click();
			}
		});
		content.beginTime == null ? $("#beginTime").val(arrange.beginTime) : $("#beginTime").val(content.beginTime);
		content.endTime == null ? $("#endTime").val(arrange.endTime) : $("#endTime").val(content.endTime);
		content.signBeginTime == null ? $("#signBeginTime").val(arrange.beginTime) : $("#signBeginTime").val(content.signBeginTime);
		content.signEndTime == null ? $("#signEndTime").val(arrange.endTime) : $("#signEndTime").val(content.signEndTime);
		$("#score").val(content.score);
		$("#period").val(content.period);
		$("#courseId").val(content.courseId);
		$("#courseName").val(content.courseName);
		$("#place").val(content.place);
		$("#passPercent").val(content.passPercent);
		/* $("#aceDuration").val(content.aceDuration);
		$("#aceAllowTimes").val(content.aceAllowTimes);
		$("#afterClassExam").val(content.afterClassExam);
		$("#afterClassExamName").val(content.afterClassExamName); */
		$("#afterClassTestId").val(content.afterClassTest);
		$("#afterClassTestName").val(content.afterClassTestName);
		$("#beforeClassCourseId").val(content.beforeClassCourseId);
		$("#beforeClassCourseName").val(content.beforeClassCourseName);
		$("#beforeClassFilePath").val(content.beforeClassFilePath);
		$("#beforeClassFileName").val(content.beforeClassFileName);
		$("#beforeClassFileSize").val(content.beforeClassFileSize);
	}
	
	function deleteContent(id, thisObj, index){
		dialog.confirm("确认删除吗?",function(obj){
			$.ajax({
		   		type:"POST",
		   		async:false,  //默认true,异步
		   		data:{id:id,trainArrangeId:arrangeId},
		   		url:"<%=request.getContextPath()%>/train/deleteTrainArrangeContent.action",
		   		success:function(data){
		   			if(data == 'FAIL'){
		   				dialog.alert("删除失败。");
		   			}else{
			   			dialog.alert("删除成功！",function(){
			   				if(num == index){
			   					if(num > 0){
				   					num--;
			   					}
			   					initContent();
			   				}else{
			   					$(thisObj).parent().parent().remove();
			   				}
			   			});
		   			}
		   	    }
		   	});
		})
	}
	
	function save(){
		if(saveContent()){
			//dialog.alert("保存成功。",function(){
				window.location.href="<%=request.getContextPath()%>/train/trainArrangeList.action";
			//})
		}else{
			//dialog.alert("保存成功。");
		}
	}
	
	function next(){
		if(saveContent()){
			//dialog.alert("保存成功。",function(){
				window.location.href="<%=request.getContextPath()%>/train/toTrainArrangeUser.action?arrangeId="+arrangeId;
			//})
		}else{
			//dialog.alert("保存成功。");
		}
	}
	
	function saveContent(){
		var flag = true;
		var o = toParam();
		if(!o.beginTime || !o.endTime){
			dialog.alert("开课时间不能为空。");
			flag = false;
			return;
		}
		if(o.beginTime < arrange.beginTime || o.endTime > arrange.endTime){
			dialog.alert("开课时间必须在培训时间之间。");
			flag = false;
			return;
		}
		if(!o.signBeginTime || !o.signEndTime){
			dialog.alert("签到时间不能为空。");
			flag = false;
			return;
		}
		/* if(o.afterClassExam > 0){
			if(!o.passPercent || !o.aceDuration || !o.aceAllowTimes){
				dialog.alert("考试成绩、考试次数、考试时长不能为空。");
				flag = false;
				return;
			}
		} */
		$('#addForm').isValid(function(v) {
			if(v){
				$.ajax({
			   		type:"POST",
			   		async:false,  //默认true,异步
			   		data:toParam(),
			   		url:"<%=request.getContextPath()%>/train/updateTrainArrangeContent.action",
			   		success:function(data){
			   			if(data == 'FAIL'){
			   				dialog.alert("保存失败。");
			   				flag =  false;
			   			}
			   	    }
			   	});
			} else {
				flag =  false;
			}
		});
		return flag;
	}
	
	function addNewContent(){
		saveContent();
		num = contents.length;
		initContent();
	}
	
	function chooseDept(){
		dialog({
			width : 300,
			height : 350,
			title : '请选择部门',
			content : $("#deptDialog"),
		 	button: [
	          {
              value: '确定',
              callback: function () {
            	  this.close();
              }
	          }
			      ]
		}).showModal();
	}
	
	var teacherId;
	function toParam(){
		var o = {};
		if(updateFlag){
			o.id = contents[num].id;
		}
		o.trainArrangeId = arrangeId;
		o.content = $("#name").val();
		o.teacherId = teacherId;
		o.trainType = $("input[name='trainType']:checked").val();
		o.beginTime = $("#beginTime").val();
		o.endTime = $("#endTime").val();
		o.signBeginTime = $("#signBeginTime").val();
		o.signEndTime = $("#signEndTime").val();
		o.score = $("#score").val();
		o.period = $("#period").val();
		o.courseId = $("#courseId").val();
		/* o.aceDuration = $("#aceDuration").val();
		o.aceAllowTimes = $("#aceAllowTimes").val();
		o.afterClassExam = $("#afterClassExam").val(); */
		if(o.trainType == 2){
			o.passPercent = $("#passPercent").val();
			o.place = $("#place").val();
			o.afterClassTest = $("#afterClassTestId").val();
			o.beforeClassCourseId = $("#beforeClassCourseId").val();
			o.beforeClassFilePath = $("#beforeClassFilePath").val();
			o.beforeClassFileName = $("#beforeClassFileName").val();
			o.beforeClassFileSize = $("#beforeClassFileSize").val();
		}
		
		return o;
	}
	
	/* 初始化时间插件 */
	function initDatepicker() {
		$("#beginTime,#endTime").datetimepicker({
			dateFormat : 'yy-mm-dd',
	 		changeMonth: true,
	 	    changeYear: true
		});
		
		$("#beginTime,#endTime").datetimepicker("option","minDate",arrange.beginTime);
    	$("#beginTime,#endTime").datetimepicker("option","maxDate",arrange.endTime);
		
		$("#signBeginTime,#signEndTime").datetimepicker({
			dateFormat : 'yy-mm-dd',
	 		changeMonth: true,
	 	    changeYear: true
		});
		
		$("#beginTime,#signBeginTime").datetimepicker('setDate', arrange.beginTime );
		$("#endTime,#signEndTime").datetimepicker('setDate', arrange.endTime );
	}
	
	var teacherId;
	function chooseTeacher(){
		artDialog = dialog({
			 url:"<%=request.getContextPath()%>/res/toChooseTeacher.action",
		        title:"选择讲师" ,
	        lock:true,
	        height: 400,
			width: 800,
			focus: true
		}).showModal();
	}
	
	function chooseCourse(name){
		artDialog = dialog({
		 	url:"<%=request.getContextPath()%>/train/toChooseCourse.action?name="+name,
	        title:"选择课程" ,
	        lock:true,
	        height: 500,
			width: 1100,
			focus: true
		}).showModal();
	}
	
	function chooseAfterClassExam(){
		artDialog = dialog({
	        url:"<%=request.getContextPath()%>/res/toChooseAfterClassExam.action",
	        title:"选择考试" ,
	        height: 500,
			width: 1100
			}).showModal();
	}
	
	/*搜索问卷  */
	function doPaperSearch(){
		var paperName =  $.trim($("#questionnaireName").val());
		var params = new Object;
		params.name = paperName;
		$('#questionnaireTable').mmGrid().load(params);
	}
	
	//初始化问卷弹出框
	function initPaperTree(){
		var setting = {
				data: {
					key: {url: "xUrl"},
					simpleData: {enable: true, pIdKey: "parentId" }
				},
				check: {enable:  false},
				callback: {
		            onClick: zTreeOnClick
		        },
				view: {
					fontCss : function(){
						
					},//个性化设置ztree的节点高亮效果
					showLine: false,
					showIcon: true
				}
		};
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			url:"<%=request.getContextPath()%>/questionnaireCategory/list.action",
			success:function(data){
				$.fn.zTree.init($("#questionnaireTree"), setting, data);
				$.fn.zTree.getZTreeObj("questionnaireTree").expandAll(true);
			}
		});
	}
	
	var categoryId;
	function zTreeOnClick(event, treeId, treeNode){
		var id = treeNode.id; //分类id
		categoryId = id;
		$('#questionnaireTable').mmGrid().load({"categoryId":id});
	}
	
	function initPaperGrid() {
		var paperMmg = $('#questionnaireTable').mmGrid({
			root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url : "<%=request.getContextPath()%>/questionnaire/voList.action",
			width : 'auto',
			height : '265px',
			fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
			showBackboard : false,
			checkCol : true,
			multiSelect : false,
			indexCol : true, // 索引列
			params : function(){
				var param = new Object();
				param.userId = ${USER_ID_LONG};
				param.categoryId = categoryId;
				param.isEnabled = 1;
				param.name = $.trim($("#questionnaireName").val());
				return param;
			},
			cols : [ {title : 'id',name : 'id',hidden : true}, 
			         {title : '问卷名称',name : 'name',align : 'center'}, 
			         {title : '问卷库',name : 'categoryFullName',align : 'center'}, 
			         {title : '最后更新时间',name : 'updateTime',align : 'center'}
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

	/*选择问卷  */
	function selectTest(){
		var selectQuestionnaire = $("#selectQuestionnaire");
		$('#paperTable').mmGrid().load();
		//initPaperTree();
		//initPaperGrid();
		//$('#paperTable').mmGrid().load();
			dialog({
					title : "选择问卷",
					width : 1000,
					height : 350,
					content :selectQuestionnaire,
					onshow: function() {
						setTimeout(initPaperGrid, 200);
					},
					okValue : '确定',
					fixed:true,
					ok : function() {
						var rowData = $('#questionnaireTable').mmGrid().selectedRows();
						if(rowData.length == 0){
							dialog.alert("请选择问卷！");
							return false;
						}else{
							$("#afterClassTestId").val(rowData[0].id);
							$("#afterClassTestName").val(rowData[0].name);
						}
					},
					cancelValue : '取消',
					cancel : function() {
					}
				}).showModal();
	}
	
	/* 初始化上传图片插件 */
	function upLoadImg(id) {
		var uploader = WebUploader
				.create({
					auto : true,
					// swf文件路径
					swf : '<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

					// 文件接收服务端。
					server:'<%=request.getContextPath()%>/train/uploadFile.action?path=train%2FuploadKL',

					// 选择文件的按钮。可选。
					// 内部根据当前运行是创建，可能是input元素，也可能是flash.
					pick : {
						id : '#'+id,
						multiple : false
					},

					// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
					resize : false

					// 只允许选择图片文件。
					
				});

		uploader.on('fileQueued', function(file) {
		});
		
	   uploader.on('uploadStart', function(file) {
		   //开始上传，进行蒙层处理
	        layer.load(1, {
	            shade: [0.5,'#fff'] //0.1透明度的白色背景
	        });
	    });
	   
		// 文件上传过程中创建进度条实时显示。
	    uploader.on( 'uploadProgress', function( file, percentage ) {
	    });
	   
		// 接受文件后，进行赋值操作
		uploader.on('uploadAccept', function(file, ret) {
			if(ret.result == 'OVER_CAPACITY'){
				dialog.alert("资源容量已用完，请联系管理员。");
			}else{
				$("#beforeClassFilePath").val(ret.path);
				$("#beforeClassFileSize").val(ret.fileSize);
		        $("#beforeClassFileName").val(ret.fileName);
	      	}
		});
		
		 uploader.on( 'uploadFinished', function( file ,ret ) {
	        //上传完成，去掉蒙层
        	layer.closeAll('loading');
	        uploader.reset();
	    });
		 

	}
	
	/**
	 * 初始化进度条
	 */
	function initJqMeter(value){
		$("#jqMeter_div").jQMeter({
			 	goal:'100',
			    raised:value+'',
			    meterOrientation:'horizontal',
			    width:'300px',
			    height:'25px'
		});
	}
	
	
	/**
	 * 表单验证
	 */
	function initValidate() {
		$('#addForm').validator({
			theme : 'yellow_right',
			msgStyle:"margin-top:10px;margin-left:10px;",
			rules : {
				checkEndTime:function(element,param,field){
					var str;
					str = element.value < arrange.endTime || "开课结束时间必须要小于培训结束时间。";
					return str;
				},
				checkBeginTime:function(element,param,field){
					var str;
					str = element.value > arrange.beginTime || "开课时间必须要大于培训开始时间。";
					return str;
				}
			},
			fields : {
				name : {
					rule : "required;length[~30];",
					msg : {
						required : "培训内容不能为空",
						length : "长度需小于等于30个字符"
					}
				},
				period : {
					rule : "required;Integer[+]",
					msg : {
						required : "培训学时不能为空"
					}
				},
				score : {
					rule : "required;Integer[+]",
					msg : {
						required : "培训学分不能为空"
					}
				},
				courseId : {
					rule : "required;",
					msg : {
						required : "课程不能为空"
					},
					msgStyle:"left:30px;",
					msgClass:"n-top"
				},
				place : {
					rule : "required;length[~30];",
					msg : {
						required : "培训地点不能为空",
						length : "长度需小于等于30个字符"
					}
				},
				passPercent : {
					rule : "required;integer[+]",
					msg : {
						required : "培训通过成绩不能为空"
					}
				}/* ,
				aceDuration : {
					rule : "integer[+];"
				},
				aceAllowTimes : {
					rule : "integer[+];"
				} */
			}
		});
	}
</script>
</head>
<body>

<div id="content" class="content">
	<!-- <h3>培训内容</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">培训内容</span>
	</div>
		<div class="make_sc">
	    	<div class="sc_tap1">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_ty1.png">
	            <span class="span_2">1</span>
	        </div>
	        <div class="connect_3">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_connect.png">
	            <img src="<%=request.getContextPath()%>/images/img/ico_connect1.png">
	        </div>
	        <div class="sc_tap1">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_ty.png">
	            <span>2</span>
	        </div>
	        <div class="connect_3">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_connect1.png">
	            <img src="<%=request.getContextPath()%>/images/img/ico_connect.png">
	        </div>
	        <div class="sc_tap1">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_ty1.png">
	            <span class="span_2">3</span>
	        </div>
	        <div class="bt" style="margin-left:-5px;">
	        	<span >基本信息</span>
	            <span class="bt_1" style="margin-left: 308px;margin-right: 308px;">培训内容</span>
	            <span >参与人员</span>
	        </div>
    	</div>
   		<div style="border: 1px solid #ccc;float: left;width:1044px;margin-top:30px;" id="total">
	   		<form id="addForm">
            <div class="lesson_add">
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>培训内容：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" name="name" id="name"/>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训讲师：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" id="teacherName" disabled name="teacherName"/>
		                <input type="button" value="选择讲师" class="te" onclick="chooseTeacher()"/>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>开课时间：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="beginTime" name="beginTime" style="width:150px;"/>
		            	至<input type="text" class="i_name" id="endTime" name="endTime" style="width:150px;" />
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>签到时间：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="signBeginTime" name="signBeginTime" style="width:150px;"/>
		            	至<input type="text" class="i_name" id="signEndTime" name="signEndTime" style="width:150px;" />
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		        		<span>*</span>
		                <em>培训学时：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="period" name="period" />
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		        		<span>*</span>
		                <em>培训学分：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="score" name="score" />
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>培训形式：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="radio" checked name="trainType" value="1"/>
		                <span>在线培训</span>
		                <input type="radio"  name="trainType" value="2"/>
		                <span>面授培训</span>
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		        		<span>*</span>
		                <em>课程：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="courseName" name="courseName" disabled/>
		            	<input type="hidden" id="courseId" name="courseId"/>
		            	<input type="button" value="选择课程" class="te" onclick="chooseCourse('course')"/>
		            </div>
		        </div>
		        <!-- <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训通过考试：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="afterClassExamName" name="afterClassExamName"/>
		            	<input type="hidden" id="afterClassExam" name="afterClassExam"/>
		            	<input type="button" value="选择考试" class="te" onclick="chooseAfterClassExam()"/>
		            </div>
		        </div> 
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训通过成绩：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="passPercent" name="passPercent"/>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训通过考试次数：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="aceAllowTimes" name="aceAllowTimes"/>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训通过考试时长：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="aceDuration" name="aceDuration"/>
		            </div>
		        </div> -->
        	</div>
        	<div  class="lesson_add" id="content2" style="display:none;">
        		  <div class="add_gr">
		        	<div class="add_fl">
		        		<span>*</span>
		                <em>培训地点：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="place" name="place" disabled/>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		        		<span>*</span>
		                <em>培训通过成绩：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="passPercent" name="passPercent" disabled/>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>课后评估：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="afterClassTestName" name="afterClassTestName" disabled/>
		            	<input type="hidden" class="i_name" id="afterClassTestId"/>
		            	<input type="button" value="选择问卷" class="te" onclick="selectTest()"/>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>课前预习：</em>
		            </div>
		            <div class="add_fr" style="line-height:11px;">
		            	<input type="text" class="i_name" id="beforeClassFileName" name="beforeClassFileName" style="float:left;"/>
		            	<input type="hidden" class="i_name" id="beforeClassFilePath"/>
		            	<input type="hidden" class="i_name" id="beforeClassFileSize"/>
		            	<div id="picker">选择文件</div>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>&nbsp;</em>
		            </div>
		            <div class="add_fr">
		            	<input type="hidden" id="beforeClassCourseId" />
		            	<input type="text" class="i_name" id="beforeClassCourseName" name="beforeClassCourseName" disabled/>
		            	<input type="button" value="选择课程" class="te" onclick="chooseCourse('beforeClassCourse')"/>
		            </div>
		        </div>
        	</div>
         </form>
       	</div>
         
	    <div class="button_cz">
		        	 <input type="button" class="btn_2" value="保存" onclick="save()"/>
		        	 <input type="button" class="btn_2" value="下一步" onclick="next()"/>
	        	</div>
    </div>
    
    <!-- dialog1 选择问卷 start -->
	<div id="selectQuestionnaire" style="display: none;">
		<div class="course_tree treeDiv1" >
        	<div id="questionnaireTree" class="ztree treeDiv2" ></div>
   		</div>
   		<div class="search_3 fl" style="float: right;width: 700px;border: none;">
	        	<p>	
	               	问卷名称： <input id="questionnaireName" type="text">
	        	</p>
	            <input type="button" value="查询" class="btn_cx" onclick="doPaperSearch()">
        </div>
   		<div style="width: 720px;float: right;">
   			
   			<table id="questionnaireTable"></table>
			<div id="paginatorPaging1" style="text-align: right;margin-right: 10px;"></div>
   		</div>
	</div>
	<!-- dialog1 end -->
</body>
</html>
