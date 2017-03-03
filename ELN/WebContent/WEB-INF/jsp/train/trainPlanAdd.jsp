<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增计划</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.btn_3 {width: 68px;height: 25px;background: #D20001;border: none;color: white;float: right;}
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
</style>

<script type="text/javascript">

var timeType = '${timeType}';

$(function(){
	initValidate();
	initTimeValue();
});
	
	function initTimeValue(){
		var date = new Date();
		if(timeType == 1){
			$("#year").show();
			var year = date.getFullYear();
			for(var i = 5; i > -5; i--){
				if(i == 0){
					$("#timeValue1").append("<option value='"+(year+i)+"' selected>"+(year+i)+"</option>");
				}else{
					$("#timeValue1").append("<option value='"+(year+i)+"'>"+(year+i)+"</option>");
				}
			}
		}else{
			$("#month").show();
			var month = date.getMonth();
			for(var i = 1; i < 13; i++){
				if(month == i){
					$("#timeValue2").append("<option value='"+i+"' selected>"+i+"</option>");
				}else{
					$("#timeValue2").append("<option value='"+i+"'>"+i+"</option>");
				}
			}
		}
	}

	function cancel(){
		history.back(-1);
		//window.location.href="<%=request.getContextPath()%>/train/trainPlanList.action";
	}
	
	function save(type){
		$('#addForm').isValid(function(v) {
			if(v){
				var o = param();
				if(!o.trainType){
					dialog.alert("请选择培训形式");
					return;
				}
				$.ajax({
			   		type:"POST",
			   		async:true,  //默认true,异步
			   		data: o,
			   		url:"<%=request.getContextPath()%>/train/addTrainPlan.action",
			   		success:function(data){
			   			if(data == 'SUCCESS'){
				   			dialog.alert("新增成功！",function(){
				   				type == 0?cancel():window.location.reload();
			   				});
			   			}else{
			   				dialog.alert("新增失败。");
			   			}
			   	    }
			   	});
			} else {
			}
		});
	}
	
	
	function param(){
		var o = {};
		o.name=$("#name").val();
		o.period=$("#period").val();
		o.timeType = timeType;
		timeType == 1 ? (o.timeValue = $("#timeValue1").val()):(o.timeValue = $("#timeValue2").val());
		o.teacherName = $("#teacherName").val();
		o.agency = $("#agency").val();
		o.target = $("#target").val();
		o.descr = $("#descr").val();
		var trainType = [];
		$.each($("input[name='trainType']:checked"), function(index, val){
			trainType.push($(val).val());
		})
		o.trainType = trainType.join(',');
		return o;
	}
	
	
	
	/**
	 * 表单验证
	 */
	function initValidate() {
		$('#addForm').validator({
			theme : 'yellow_right',
			rules : {
				checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ]
			},
			msgStyle:"margin-top:10px;",
			fields : {
				name : {
					rule : "required;length[~30]",
					msg : {
						required : "培训名称不能为空",
						length : "长度需小于等于30个字符"
					}
				},
				period : {
					rule : "required;integer[+]",
					msg : {
						required : "学时不能为空"
					}
				},
				teacherName : {
					rule : "length[~50]",
					msg : {
						length : "长度需小于等于50个字符"
					}
				},
				agency : {
					rule : "required;length[~50]",
					msg : {
						required : "培训机构不能为空",
						length : "长度需小于等于50个字符"
					}
				},
				target : {
					rule : "required;length[~50]",
					msg : {
						required : "培训对象不能为空",
						length : "长度需小于等于50个字符"
					}
				},
				descr : {
					rule : "length[~200]",
					msg : {
						length : "长度需小于等于200个字符"
					}
				}
			}
		});
	}
</script>
</head>
<body>

<div id="content" class="content">
	<!-- <h3>新增计划</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增计划</span>
	</div>
   		<form id="addForm">
            <div class="lesson_add">
            	<div class="add_gr" id="year" style="display:none;">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>培训年度：</em>
		            </div>
		            <div class="add_fr">
		            	<select style="width:100px;" name="timeValue1" id="timeValue1">
		            	
		            	</select>
		            </div>
		        </div>
		        <div class="add_gr" id="month" style="display:none;">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>培训月度：</em>
		            </div>
		            <div class="add_fr">
		            	<select style="width:100px;" name="timeValue2" id="timeValue2">
		            	
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
		                <em>培训学时：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="period" name="period" />
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>培训形式：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="checkbox"  name="trainType" value="1"/>
		                <span>在线培训</span>
		                <input type="checkbox"  name="trainType" value="2"/>
		                <span>面授培训</span>
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训讲师：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="teacherName" name="teacherName" />
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>培训机构：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" class="i_name" id="agency" name="agency" />
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>培训对象：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" id="target" name="target"/>
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
	             <div class="button_cz">
	             	<input type="button" class="btn_2" value="保存并新增" onclick="save(1)"/>
		        	 <input type="button" class="btn_2" value="保存" onclick="save(0)"/>
		        	 <input type="button" class="btn_2" value="取消" onclick="cancel()"/>
	        	</div>
        	</div>
         </form>
    </div>
</body>
</html>
