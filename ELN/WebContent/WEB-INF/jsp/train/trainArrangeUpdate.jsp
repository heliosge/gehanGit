<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增安排</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>

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
<!-- layer -->
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
	.btn_3 {width: 68px;height: 25px;background: #D20001;border: none;color: white;float: right;}
	.ztree {width: 250px;min-height: 300px;height: auto;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;margin-left:13px;position: absolute;background-image:url("")}
	.ztree li span.button.noline_close{z-index:999;margin-left:13px;position: absolute;background-image:url("")}
</style>

<script type="text/javascript">

var arrange = ${arrange};

$(function(){
	upLoadImg("picker");
	initDatepicker();
	initValidate();
	initDeptTree();
	
	$("input[name='timeType']").click(function(){
		initTrainPlan($(this).val());
	})
	
	$("input[name='isJoin']").click(function(){
		if($(this).val() == 1){
			$("#maxJoinNumDiv").show();
			$("#chooseDeptDiv").show();
			$("#deptNameDiv").show();
			$("#maxJoinNum").removeAttr("disabled");
		}else{
			$("#maxJoinNumDiv").hide();
			$("#chooseDeptDiv").hide();
			$("#deptNameDiv").hide();
			$("#maxJoinNum").attr("disabled","disabled");
		}
	})
	
	fillArrangeInfo();
});

	function fillArrangeInfo(){
		$("#name").val(arrange.name);
		initTrainPlan(arrange.timeType);
		$("#trainPlanId").val(arrange.trainPlanId);
		$.each($("input[name='timeType']"), function(index, val){
			if($(val).val() == arrange.timeType){
				val.checked = true;
			}
		});
		$("#beginTime").val(arrange.beginTime);
		$("#endTime").val(arrange.endTime);
		$("input[name='isJoin']:checked").val();
		$.each($("input[name='isJoin']"), function(index, val){
			if($(val).val() == arrange.isJoin){
				val.checked = true;
				$(val).click();
			}
		});
		$("#maxJoinNum").val(arrange.maxJoinNum);
		$.each($("input[name='isRecommend']"), function(index, val){
			if($(val).val() == arrange.isRecommend){
				val.checked = true;
			}
		});
		$("#descr").val(arrange.descr);
		$("#fitCrowd").val(arrange.fitCrowd);
		$("#filePath").val(arrange.cover);
		if(arrange.passPercent > 0){
			$("#passPercent").val(arrange.passPercent);
		}
		if(arrange.aceDuration > 0){
			$("#aceDuration").val(arrange.aceDuration);
		}
		if(arrange.aceAllowTimes > 0){
			$("#aceAllowTimes").val(arrange.aceAllowTimes);
		}
		if(arrange.afterClassExam > 0){
			$("#afterClassExam").val(arrange.afterClassExam);
		}
		$("#afterClassExamName").val(arrange.afterClassExamName);
		$("#previewPath").attr("src", arrange.cover);
		var treeObj = $.fn.zTree.getZTreeObj("treePage");
		//选中的节点
		$.each(arrange.openCrowd, function(index, val){
			var node = treeObj.getNodeByParam("id", val.deptId, null)
			treeObj.checkNode(node,true,true);
        	$("#deptName").append(node.name+"; ");
		});
	}
	
	function initTrainPlan(val){
		$.ajax({
	   		type:"POST",
	   		async:false,  //默认true,异步
	   		data:{timeType:val},
	   		url:"<%=request.getContextPath()%>/train/selectTrainPlanByType.action",
	   		success:function(data){
	   			if(data != null){
	   				var html = '<option value="">请选择</option>';
	   				$.each(data,function(index,val){
	   					html += '<option value="'+val.id+'">'+val.name+'</option>';
	   				})
	   				$("#trainPlanId").html(html);
	   			}
	   	    }
	   	});
	}
	
	function cancel(){
		history.back(-1);
	}

	function save(){
		var o = toParam();
		if(o.afterClassExam > 0){
			if(!o.passPercent || !o.aceDuration || !o.aceAllowTimes){
				dialog.alert("考试成绩、考试次数、考试时长不能为空。");
				return;
			}
		}
		$('#addForm').isValid(function(v) {
			if(v){
				$.ajax({
			   		type:"POST",
			   		async:true,  //默认true,异步
			   		data:o,
			   		url:"<%=request.getContextPath()%>/train/updateTrainArrange.action",
			   		success:function(data){
			   			if(data == 'FAIL'){
			   				dialog.alert("修改失败。");
			   			}else{
				   			//dialog.alert("修改成功！",function(){
				   				cancel();
				   			//});
			   			}
			   	    }
			   	});
			} else {
			}
		});
	}
	
	function next(){
		$('#addForm').isValid(function(v) {
			if(v){
				$.ajax({
			   		type:"POST",
			   		async:true,  //默认true,异步
			   		data:toParam(),
			   		url:"<%=request.getContextPath()%>/train/updateTrainArrange.action",
			   		success:function(data){
			   			if(data == 'FAIL'){
			   				dialog.alert("修改失败。");
			   			}else{
				   			//dialog.alert("修改成功！",function(){
				   				window.location.href="<%=request.getContextPath()%>/train/toTrainArrangeContentsUpdate.action?num=0&arrangeId="+arrange.id;
				   			//});
			   			}
			   	    }
			   	});
			} else {
			}
		});
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
            	  $("#deptName").html('');
            	  $.each($.fn.zTree.getZTreeObj("treePage").getCheckedNodes(true), function(index, val){
            		  if(val.id.indexOf('com') == -1){
	            		  $("#deptName").append(val.name+"; ");
            		  }
          		  });
            	  this.close();
              }
	          }
			      ]
		}).showModal();
	}
	
	
	function toParam(){
		var o = {};
		o.id = arrange.id;
		o.name = $("#name").val();
		o.trainPlanId = $("#trainPlanId").val();
		o.timeType = $("input[name='timeType']:checked").val();
		o.beginTime = $("#beginTime").val();
		o.endTime = $("#endTime").val();
		o.isJoin = $("input[name='isJoin']:checked").val();
		o.maxJoinNum = $("#maxJoinNum").val();
		o.isRecommend = $("input[name='isRecommend']:checked").val();
		o.descr = $("#descr").val();
		o.fitCrowd = $("#fitCrowd").val();
		o.cover = $("#filePath").val();
		o.deptIds = [];
		o.passPercent = $("#passPercent").val();
		o.aceDuration = $("#aceDuration").val();
		o.aceAllowTimes = $("#aceAllowTimes").val();
		o.afterClassExam = $("#afterClassExam").val();
		$.each($.fn.zTree.getZTreeObj("treePage").getCheckedNodes(true), function(index, val){
			 if(val.id.indexOf('com') == -1){
					o.deptIds.push(val.id);
				}
		});
		return o;
	}
	
	/* 初始化上传图片插件 */
	function upLoadImg(id) {
		var uploader = WebUploader
				.create({
					auto : true,
					// swf文件路径
					swf : '<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

					// 文件接收服务端。
					server : '<%=request.getContextPath()%>/megagameManage/addContest_uploadImg.action',

					// 选择文件的按钮。可选。
					// 内部根据当前运行是创建，可能是input元素，也可能是flash.
					pick : {
						id : '#'+id,
						multiple : false
					},

					// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
					resize : false,

					// 只允许选择图片文件。
					accept : {
						title : 'Images',
						extensions : 'gif,jpg,jpeg,bmp,png',
						mimeTypes : 'image/*'
					}
				});
		
		uploader.on('uploadStart', function(file) {
			   //开始上传，进行蒙层处理
		        layer.load(1, {
		            shade: [0.5,'#fff'] //0.1透明度的白色背景
		        });
		    });

		uploader.on('fileQueued', function(file) {
		});

		// 接受文件后，进行赋值操作
		uploader.on('uploadAccept', function(file, ret) {
			$("#filePath").val(ret._raw);
			// 预览图片
			$("#previewPath").attr("src", ret._raw).css({
				"width" : "278px",
				"height" : "105px"
			});
		});
		
		 uploader.on( 'uploadFinished', function( file ,ret ) {
		        //上传完成，去掉蒙层
	        	layer.closeAll('loading');
		        uploader.reset();
		    });

	}
	
	/* 初始化时间插件 */
	function initDatepicker() {
		$("#beginTime,#endTime").datetimepicker({
			dateFormat : 'yy-mm-dd',
			timeFormat : 'HH:mm',
	 		  changeMonth: true,
	 	      changeYear: true
		});
		
		$("#endTime").datetimepicker("option","minDate",arrange.beginTime);
		$("#endTime").datetimepicker("option","minDateTime",(new Date(arrange.beginTime)));
		
		$("#beginTime").datetimepicker('option','onSelect',function(dateText,inst){
			$("#endTime").datetimepicker("option","minDate",new Date($("#beginTime").val()));
			$("#endTime").datetimepicker("option","minDateTime",new Date($("#beginTime").val()));
		});
	}
	
	/* 初始化部门tree */
	function initDeptTree(){
		var setting = {
				data: {key: {url: "xUrl"},simpleData: {enable: true, pIdKey: "parentId" }},
				check: {enable:  true,chkboxType: { "Y": "s", "N": "" }},
				view: {
					showLine: false,
					showIcon: true
				}
		};
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			url:"<%=request.getContextPath()%>/manageCompany/selectCompanyCategory.action",
			success:function(data){
				addIconInfo(data.data);
				$.fn.zTree.init($("#treePage"), setting, data.data);
			}
		});
	}
	
	function addIconInfo(data) {
	    for (var idx in data) {
	        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
	        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
	        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
	    }
	}
	
	function chooseAfterClassExam(){
		artDialog = dialog({
	        url:"<%=request.getContextPath()%>/res/toChooseAfterClassExam.action",
	        title:"选择考试" ,
	        height: 500,
			width: 1100
			}).showModal();
	}
	
	
	/**
	 * 表单验证
	 */
	function initValidate() {
		$('#addForm').validator({
			theme : 'yellow_right',
			rules : {
				checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ],
				checkEndTime:function(element,param,field){
					var str;
					str = element.value > $("#beginTime").val() || "结束时间必须要大于开始时间。";
					return str;
				}
			},
			msgStyle:"margin-top:10px;margin-left:10px;",
			fields : {
				name : {
					rule : "required;length[~30]",
					msg : {
						required : "培训名称不能为空",
						length : "长度需小于等于30个字符"
					}
				},
				beginTime : {
					rule : "required;",
					msg : {
						required : "开始时间不能为空"
					},
					msgStyle:"left:-30px;",
					msgClass:"n-top"
				},
				endTime : {
					rule : "required;checkEndTime;",
					msg : {
						required : "结束时间不能为空"
					}
				},
				maxJoinNum : {
					rule : "required;integer[+];",
					msg : {
						required : "培训机构不能为空"
					}
				},
				target : {
					rule : "required;length[~50]",
					msg : {
						required : "培训对象不能为空",
						length : "长度需小于等于50个字符"
					}
				},
				fitCrowd : {
					rule : "length[~200]",
					msg : {
						length : "长度需小于等于200个字符"
					}
				}
				,
				descr : {
					rule : "length[~1000]",
					msg : {
						length : "长度需小于等于1000个字符"
					}
				},
				passPercent : {
					rule : "integer[+];length[~2]"
				},
				aceDuration : {
					rule : "integer[+]"
				},
				aceAllowTimes : {
					rule : "integer[+];"
				} 
			}
		});
	}
</script>
</head>
<body>

<div id="content" class="content">
	
	 <div id="deptDialog" style="display: none;">
	     <ul id="treePage" class="ztree" style=""></ul>
     </div>

	<!-- <h3>基本信息</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">基本信息</span>
	</div>
		<div class="make_sc">
	    	<div class="sc_tap1">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_ty.png">
	            <span>1</span>
	        </div>
	        <div class="connect_3">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_connect1.png">
	            <img src="<%=request.getContextPath()%>/images/img/ico_connect.png">
	        </div>
	        <div class="sc_tap1">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_ty1.png">
	            <span class="span_2">2</span>
	        </div>
	        <div class="connect_3">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_connect.png">
	            <img src="<%=request.getContextPath()%>/images/img/ico_connect.png">
	        </div>
	        <div class="sc_tap1">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_ty1.png">
	            <span class="span_2">3</span>
	        </div>
	        <div class="bt" style="margin-left:-5px;">
	        	<span class="bt_1" style="margin-right:308px;">基本信息</span>
	            <span>培训内容</span>
	            <span class="last_bt" style="margin-left: 308px;">参与人员</span>
	        </div>
    	</div>
   		<form id="addForm">
            <div class="lesson_add">
            	<div class="add_gr" id="year" >
		        	<div class="add_fl">
		                <em>计划开课：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="radio" checked name="timeType" value="1"/>
		                <span>年度计划</span>
		                <input type="radio"  name="timeType" value="2"/>
		                <span>月度计划</span>
		                <select style="width:100px;" name="trainPlanId" id="trainPlanId">
		            		
		            	</select>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>培训名称：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" name="name" id="name"/>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>培训时间：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="beginTime" name="beginTime" style="width:150px;"/>
		            	至<input type="text" class="i_name" id="endTime" name="endTime" style="width:150px;" />
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>是否允许报名：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="radio" checked name="isJoin" value="1"/>
		                <span>是</span>
		                <input type="radio"  name="isJoin" value="2"/>
		                <span>否</span>
		            </div>
		        </div>
		         <div class="add_gr" id="maxJoinNumDiv">
		        	<div class="add_fl">
		        		<span>*</span>
		                <em>允许人数：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="maxJoinNum" name="maxJoinNum" />
		            </div>
		        </div>
		         <div class="add_gr" id="chooseDeptDiv">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>开放人群：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="button" class="i_name" value="选择" onclick="chooseDept()"/>
		            </div>
		        </div>
		         <div class="add_gr" id="deptNameDiv" style="height:auto;">
		        	<div class="add_fl">
		            	&nbsp;
		            </div>
		            <div class="add_fr" id="deptName">
		            </div>
		        </div>
		        <div class="add_gr" style="display:none;">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>是否为推荐培训：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="radio" name="isRecommend" value="1"/>
		                <span>是</span>
		                <input type="radio" checked name="isRecommend" value="2"/>
		                <span>否</span>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>适合人群：</em>
		            </div>
		            <div class="add_fr">
		            	<textarea id="fitCrowd" name="fitCrowd"></textarea>
		            </div>
		        </div>
		        <div class="add_gr">
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
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训简介：</em>
		            </div>
		            <div class="add_fr">
		            	<textarea id="descr" name="descr"></textarea>
		            </div>
		        </div>
		        <div class="add_gr" style="line-height:40px;">
		        	<div class="add_fl" style="line-height:40px;">
		                <em>封面图片：</em>
		            </div>
		            <div class="add_fr" style="line-height:20px;">
							<div id="picker">选择图片</div>
							<input id="filePath" type="hidden">
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                &nbsp;
		            </div>
		            <div class="add_fr">
							<em>建议图片大小为（宽*高）：250*170</em>
		            </div>
		        </div>
	        <div class="add_gr"  style="height:auto;">
	        	<div class="add_fl">
	                &nbsp;
	            </div>
	            <div class="add_fr">
						<p class="tp">
							<img id="previewPath" src="" width="278px" height="105px"/>
						</p>
	            </div>
	        </div>
             <div class="button_cz">
	        	 <input type="button" class="btn_2" value="保存" onclick="save()"/>
	        	 <input type="button" class="btn_2" value="下一步" onclick="next()"/>
        	</div>
       	</div>
         </form>
    </div>
</body>
</html>
