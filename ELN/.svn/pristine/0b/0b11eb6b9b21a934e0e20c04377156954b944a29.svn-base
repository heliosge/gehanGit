<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>成绩预览</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js"></script>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jqprint.js"></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var examId = '${examId}';
var x_rank=0;
$(function(){
	initGird();
	getExamInfo();
	
});
var temp = 0;
//初始化grid数据
function initGird() {
	$('#cjylTable').mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : '<%=request.getContextPath()%>/exam/exam/getCjYlList.action',
						width : '1046px',
						height : 'auto',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : false,
						multiSelect : true,
						indexCol : true, // 索引列
						params : function(){
							var param = new Object();
							var name = $("#name").val();
							var post = $("#post").val();
							var depName = $("#depName").val();
							var isAttend = $("#isAttend").val();
							param.userName = name;
							param.postName = post;
							param.depName = depName;
							param.isAttend = isAttend;
							param.userId = userId;
							param.examId = examId;
							return param;
						},
						cols : [
								{
									title : 'id',
									name : 'examId',
									hidden : true
								},
								{
									title : '姓名',
									name : 'name',
									align : 'center'
								},
								{
									title : '岗位',
									name : 'postName',
									align : 'center'
								},
								{
									title : '部门',
									name : 'departmentName',
									align : 'center'
								},{
									title : '是否参与考试',
									name : 'isAttended',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										if(val){
											return "是";
										}else{
											return "否";
										}
									}
								},{
									title : '成绩',
									name : 'score',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										if(val){
											return val;
										}else{
											return 0;
										}
									}
								},{
									title : '是否通过',
									name : 'isPassed',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										if(val){
											return "是";
										}else{
											return "否";
										}
									}
								},{
									title : '排名',
									name : 'rank',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										if(val && val!=0){
											//x_rank =val;
											temp = val;
											return val;
										}else{
											//x_rank=x_rank+1;
											return temp+1;
										}
										//return x_rank;
									}
								},
								{
									title : '操作',
									name : 'operation',
									width : 180,
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var str = "";
										var str_edit = '<a href="javascript:void(0);" onclick="modifyGrade('+item.id+','+item.score+');">成绩修改</a>&nbsp;';
										var str_view = '<a href="javascript:void(0);" onclick="viewPaper('+item.id+');">答卷详情</a>';
										if(item.isAttended){
											str += str_edit;
										}
										str += str_view;
										return str;
									}
								}],
						plugins : [ $('#paginatorPaging').mmPaginator({
							totalCountName : 'total', // 对应MMGridPageVoBean
														// count
							page : 1, // 初始页
							pageParamName : 'page', // 当前页数
							limitParamName : 'pageSize', // 每页数量
							limitList : [ 10, 20, 40, 50 ]
						}) ]
					});
	}
/**
 * 获取考试信息
 */
function getExamInfo(){
	var urlStr = "<%=request.getContextPath()%>/exam/exam/getExamInfo.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"examId" : examId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initExamInfo(data.rtnData);
			}
		}
	});
}

/**
 * 生成考试信息
 */
function initExamInfo(data){
	var totalScore = 0;
	var passScore = 0;
	var duration = 0;
	var title = "";
	var html="";
	
	var ti = $("#ts_id");
	if(data){
		totalScore = data.totalScore;
		passScore = Math.floor((data.passScorePercent/100)*totalScore);
		duration = data.duration;
		title = data.paperName;//试卷名称
	}
	html +="<span>总分："+totalScore+"</span>";
	html +="<span>及格分："+passScore+"</span>";
	html +="<span>考试时长："+duration+"</span>";
	$("#oldScore").text(totalScore);
	$("#title_pp").html(title);
	$("#title_pp2").html(title);
	ti.empty();
	ti.html(html);
	$("#ts_id2").html(html);
}
/**
 * 搜索
 */
function doSearch(){
	$('#cjylTable').mmGrid().load({"page":1});
}

/*成绩修改  */
function modifyGrade(assignId,oldScore){
	$("#score").val("");
	$("#otherReason").val("");
	//$("input[type=radio][name=modifyReason][value=批阅错误]").attr("checked",true);
	initValidate();
	var d = dialog({
	    title: '成绩修改',
	    content:$("#editDiv"),
	    okValue: '确定',
	    ok: function () {
	    	var flag = false;
	    	$('#reasonForm').isValid(function(v) {
	    		if (v) {
	    			flag = true;
	    			var modifyReason = GetRadioValue("modifyReason");
	    	        var reasonInfo = '';
	    	        if(modifyReason == 0){
	    	        	reasonInfo = $.trim($("#otherReason").val());
	    	        }else{
	    	        	reasonInfo = GetRadioValue("modifyReason");
	    	        }
		    	     $.ajax({
		    			type:"POST",
		    			async:true,  //默认true,异步
		    			data:{"examAssignId":assignId,"modifyReason":reasonInfo,"totalScore":$.trim($("#score").val()),"isScoreModified":1},
		    			url: "<%=request.getContextPath()%>/exam/exam/modifyScore.action",
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
	    	        //this.remove();
	    		}
	    	});
	    	return flag;
	    },
		cancelValue : '取消',
		cancel : function() {
		}
	});
	d.width(350);
	d.height(200);
	d.show();	
}

/*查看答卷详情  */
function viewPaper(assignId){
	window.open("<%=request.getContextPath()%>/myExamManage/gotoExamDetail.action?exam_assign_id="+assignId+"&isPy=1");
}


//查询
function search(notOnePage){
	if(notOnePage){
		//不从第一页开始
		$('#cjylTable').mmGrid().load();
	}else{
		$('#cjylTable').mmGrid().load({"page":1});		
	}
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
/*表单验证  */
function initValidate() {
	$('#reasonForm').validator({
		theme : 'yellow_right_effect',
		msgClass:"n-bottom",
		rules : {
			checkModifyReason:function(element,param,field){
				var flag = false;
				if(GetRadioValue("modifyReason") == 0){
					flag = true;
				}
				str =  flag || "其它理由不能为空";
				return str;
			},
			checkScore:function(element,param,field){
				var flag = true;
				if(parseInt($("#oldScore").text()) < element.value){
					flag = false;
				}
				str =  flag || "分数应小于总分";
				return str;
			}
		},
		fields : {
			score : {
				rule : "required;integer[+];checkScore;",
				msg : {
					required : "修改分数不能为空",
					range:"分数为整数"
				},
				msgStyle:"margin-left: 10px;"
			},
			otherReason : {
				rule : "required(checkModifyReason),length[1~200]",
				msg : {
					required : "其它理由不能为空",
					length : "输入1~200的字符"
				}
			}
		}
	});
}

/*设置其它原因的样式   */
function setOtherReason(){
	$("#otherReason").removeAttr("disabled");
}

/**
 * 导出word
 */
function exportDoc(){
	var form = $("#exportDocForm");
	form.submit();
}
/**
 * 导出excel
 */
function exportExcel(){
	var form = $("#exportExcelForm");
	form.submit();
}

function doPrint(){
	var ode = $("#pppp");
	var param = new Object();
	param.userId = userId;
	param.examId = examId;
	var urlStr = "<%=request.getContextPath()%>/exam/exam/getCjYlListAll.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : param,
		success : function(data) {
			var html="";
			html +="<table border=\"1\">";
			html +="<tr>";
			html +="<th>姓名</th>";
			html +="<th>岗位</th>";
			html +="<th>部门</th>";
			html +="<th>是否参与考试</th>";
			html +="<th>成绩</th>";
			html +="<th>是否通过</th>";
			html +="<th>排名</th>";
			html +="</tr>";
			data.map(function(v,i){
				var name=v.name;
				var departmentName=v.departmentName;
				var postName=v.postName;
				if(!postName || postName=="null"){
					postName="";
				}
				if(!departmentName || departmentName=="null"){
					departmentName="";
				}
				var isAttended=v.isAttended==true?"是":"否";
				var isPassed=v.isPassed==true?"是":"否";
				var score=v.score;
				if(!score || score=="null"){
					score=0;
				}
				var rank=v.rank;
				if(!rank || rank=="null"){
					rank="";
				}
				html+="<tr>";
				html+="<td width=\"150px\" align=\"center\">"+name+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+postName+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+departmentName+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+isAttended+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+score+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+isPassed+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+rank+"</td>";
				html+="</tr>";
			});
			html +="</table>";
			$("#datas").html(html);
			ode.show();
			ode.jqprint();
			ode.hide();
		}
	});
}

/*返回  */
function goBack(){
	window.location = "<%=request.getContextPath()%>/exam/exam/toCjPyList.action";
}
</script>
</head>
<body>
<div class="content">
	<!-- <h3>成绩修改</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="goBack();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">成绩修改</span>
	</div>
    <div class="py_top" >
    	<h4 id="title_pp">2015届一年级语文期中考试</h4>
    </div>
    <div class="ts" id="ts_id">
    </div>
    <div class="button_cz fl" style="margin:0; padding-bottom:20px;">
    	<form id="exportDocForm" action="<%=request.getContextPath()%>/exam/exam/exportDoc.action" style="float: left;">
    		<input type="hidden"  name ="userId" value="${USER_ID_LONG}">
    		<input type="hidden" name="examId"  value="${examId}">
	    	<input type="button" value="导出word" onclick="exportDoc()"/>
    	</form>
    	<form id="exportExcelForm" action="<%=request.getContextPath()%>/exam/exam/exportExcel.action"style="float: left;">
    		<input type="hidden"  name ="userId" value="${USER_ID_LONG}">
    		<input type="hidden" name="examId"  value="${examId}">
	        <input type="button" value="导出excel" onclick="exportExcel()"/>
    	</form>
        <input type="button" value="打印成绩"  onclick="doPrint();"/>

    </div>
    <div class="content_2">
        
        
        <div class="search_2 fl">
            <p>
                姓名：
                <input id="name" type="text" />
                岗位：
                <input id="post" type="text" />
                部门：
                <input id="depName" type="text" />
                是否参与考试：
                <select id="isAttend">
                	<option selected="selected" value="">显示全部</option>
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
            </p>
            <input type="button" value="查询" class="btn_cx" onclick="doSearch();"/>
    
       	</div>
       	<div class="clear_both"></div>
        <div>
            <table id="cjylTable"></table>
            <div id="paginatorPaging" style="text-align: right;left: -18px;position: relative;"></div>
         </div>

    </div>
</div>

    <div class="sp_jd1" style="display:none;" id="editDiv">
    <form id="reasonForm">
        <p>
            修改成绩：<input type="text" id="score" name="score"/>/<span id="oldScore"></span>
            <!--  <span id="scoreSpan" class="msg-box n-right" for="times" style="display: none;">
					<span class="msg-wrap n-error" role="alert">
						<span class="n-arrow"><b>◆</b><i>◆</i></span>
						<span class="n-icon"></span>
						<span class="n-msg">试卷不能为空</span>
					</span>
				</span> -->
        </p>
        <p>
           <input type="radio" name="modifyReason" value="批阅错误" checked="checked"/><span>批阅错误</span>
        </p>
        <p>
           <input type="radio" name="modifyReason" value="特殊人物"/><span>特殊人物</span>
        </p>
        <p>
           <input type="radio" name="modifyReason" value="系统错误"/><span>系统错误</span>
        </p>
        <p>
           <input type="radio" name="modifyReason" value="0" /><span>其他</span>
        </p>
        <p>
           <textarea id="otherReason" name="otherReason"></textarea>
        </p>
    </form>
</div>
<div id="pppp" style="display: none;">
	<div class="py_top" >
    	<h4 id="title_pp2">2015届一年级语文期中考试</h4>
    </div>
    <div class="ts" id="ts_id2"></div>
    <div id="datas"></div>
</div>
</body>
</html>