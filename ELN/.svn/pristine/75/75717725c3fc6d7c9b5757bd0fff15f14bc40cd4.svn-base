<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!--时间控件  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/themes/flick/jquery-ui.css"/>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/exam_paper/exam_shcedule.js" ></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- <style type="text/css">
.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; } 
.ui-timepicker-div dl { text-align: left; } 
.ui-timepicker-div dl dt { height: 25px; margin-bottom: -25px; } 
.ui-timepicker-div dl dd { margin: 0 10px 10px 65px; } 
.ui-timepicker-div td { font-size: 90%; } 
.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; } 
.ui_tpicker_hour_label,.ui_tpicker_minute_label,.ui_tpicker_second_label, 
.ui_tpicker_millisec_label,.ui_tpicker_time_label{padding-left:20px} 
</style> -->
<style type="text/css">
	.treeDiv1{float: left;width:210px; min-height: 305px;max-height: 305px; border: 1px solid #cdcdcd;}
	.treeDiv2{overflow:auto; min-height: 295px;max-height: 295px;}
	<%-- .ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{margin-left:10px;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{margin-left:10px;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")} --%>
</style>
<%-- <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/ztree_diy.css"/> --%>
<title>新增考试</title>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var path = '<%=request.getContextPath()%>';
var total = 0;//总条数
var pageNum = 0;//总页数
var page = 1;//当前页
var pageSize = 10;//每页数量
var userList = [];//存放原始userList
var tempUserList = [];
var tempList = [];//存放当前页面查询的userList
var nowData = [];//存放当前mmgrid显示的userList
$(function(){
	initPersonListGrid();// 初始化人员列表
	initValidate();//表单验证
});

//zhangchen start
/*保存或发布   1-保存 2-保存并发布*/
function save(saveFlag){
	$('#addForm').isValid(function(v) {
		var sjName = $("#sjName").val();
		if(!sjName || sjName == ""){
			$("#span_sjName").show();
			v=false;
			document.getElementById("sjName").focus();
		}else{
			$("#span_sjName").hide();
		}
		if (!v) {
			//alert("表单验证不通过！");
			return false;
		}else{
			var saveInfo = '';
			if(saveFlag == 1){
				saveInfo = "确认保存吗？";
			}else{
				saveInfo = "确认保存并发布吗？";
			}
			if(saveFlag == 2){
				if(tempList.length == 0){
					dialog.alert("请添加人员！");
					return false;
				}
			}
			dialog.confirm(saveInfo, function(){
				var title = $.trim($("#title").val()); //考试名称
				var paperId = $("#sjId").val();//选择试卷id
				var displayMode = GetRadioValue("displayMode");//显示模式
				var params = new Object;
				params.title = title;
				params.paperId = paperId;
				params.displayMode = displayMode;
				params.type = 3;
				params.duration = $.trim($("#duration").val());
				if(saveFlag == 2){//发布
					params.isPublished = 1;
				}else{
					params.isPublished = 0;
				}
				params.userList = tempList;
				//alert(JSON.stringify(params));
				$.ajax({
					type : "POST",
					async:false,  //默认true,异步
					contentType:"application/json; charset=utf-8",
					url : "<%=request.getContextPath()%>/exam/exam/addExam.action",
					data : JSON.stringify(params),
					success : function(data) {
						if(data == 'SUCCESS'){
							dialog.alert("操作成功！", function (){
								window.location = "<%=request.getContextPath()%>/exam/exam/gotoSimExamList.action";
							});
						}else{
							dialog.alert("操作失败！");
						}
					}
				});
			});
		}
	});
	
}	

/*表单验证  */
function initValidate() {
	$('#addForm').validator({
		theme : 'yellow_right',
		rules : {
			checkTitle:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{"title":$.trim($("#title").val())},
					url:"<%=request.getContextPath()%>/exam/exam/validateExamTitle.action",
					success:function(data){
						var flag = true;
						if(data > 0){
							flag = false;
						}
						str =  flag || "已存在相同名称";
					}
				});
				return str;
			}
		},
		fields : {
			title : {
				rule : "required;length[1~30];checkTitle",
				msg : {
					required : "考试名称不能为空",
					length:"考试名称必须小于等于30字符"
				}
			},
			/* sjName : {
				rule : "required",
				msg : {
					required : "考试试卷不能为空"
				}
			}, */
			duration : {
				rule : "required;range[1~999],integer[+]",
				msg : {
					required : "考试时长不能为空",
					range : "输入1~999的数字",
					integer : "输入1~999的数字"
				}
			}
		}
	});
}

/*返回  */
function goBack(){
	window.location = "<%=request.getContextPath()%>/exam/exam/gotoSimExamList.action";
}
//zhangchen end
</script>
</head>
<body>
<input type="hidden" id="deptId"/>
<input type="hidden" id="groupId"/>
<div class="content">
	<!-- <h3>新增考试</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="goBack();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增考试</span>
	</div>
	<form id="addForm">
	<div class="lesson_add_2">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>考试名称：</em>
            </div>
            <div class="add_fr">
            	<input type="text" style="width:135px;" id="title" name="title"/>
            </div>
        </div>
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>选择试卷：</em>
            </div>
            <div class="add_fr">
            	<input type="hidden" id="sjId" name="sjId">
            	<input type="text" style="width:135px;" readonly="readonly" id="sjName" name="sjName"/>
                <input type="button" value="选择试卷" class="xz" onclick="selectPaper()"/>
                <span id="span_sjName" class="msg-box n-right" for="times" style="display: none;">
					<span class="msg-wrap n-error" role="alert">
						<span class="n-arrow"><b>◆</b><i>◆</i></span>
						<span class="n-icon"></span>
						<span class="n-msg">试卷不能为空</span>
					</span>
				</span>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>显示模式：</em>
            </div>
            <div class="add_fr">
            	<input type="radio" checked="checked" name="displayMode" value="1"/>
                <span>整卷显示</span>
                <input type="radio"  name="displayMode" value="2"/>
                <span>单题可逆</span>
                <input type="radio"  name="displayMode" value="3"/>
                <span>单题不可逆</span>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>考试时长：</em>
            </div>
             <div class="add_fr">
             	<input type="text" value="0" style="width:135px;" id="duration" name="duration"/>
                <span>分钟</span>
            </div>
    	</div>
    </div>
    </form>
    <div class="button_cz" style="margin:0; padding:10px 0 0 0;">
        	<input type="button" value="授权部门" onclick="initDepartmentTree();"/>
            <input type="button" value="授权群组"  onclick="selectGroup();"/>
            <input type="button" value="授权人员"  onclick="selectPerson();"/>
            <input type="button" value="批量删除" class="back"  onclick="delMore_grid();"/>
    </div>
    <div class="search_3" style="width:1044px; margin-top:10px;">
        	<p>	
            	姓名：<input type="text" id="name" name="name"/>
                                            岗位：<input type="text" id="post" name="post"/>
            	部门：<input type="text" id="department" name="department"/>
        	</p>
            <input type="button" value="重置" onclick="clearAll();"/>
        	<input type="button" value="查询" class="btn_cx" onclick="query();"/>

    </div>
    <!-- <div class="tab_3" style="width:1066px; margin-bottom:30px;">
            <table width="100%">
                <tr class="tr1">
                    <th><input type="checkbox" /></th>
                    <th>姓名</th>
                    <th>电子邮箱</th>
                    <th>联系电话</th>
                    <th>岗位</th>
                    <th>部门</th>
                    <th>操作</th>
                </tr>
                <tr>
                   <td colspan="8">暂无数据</td> 
                </tr>
            </table>
    </div> -->
    <div class="clear_both"></div>
    <div style="width:1066px;margin-bottom:30px;" id="personListDiv">
 		<table id="personListTable"></table>
		<div id="paginator" class="paginator">
			<!-- <div id="leftDiv">
				<ul>
					<li id="size10" class="class02" onclick="setPageSize(10);">10</li>
					<li id="size20" class="class01" onclick="setPageSize(20);">20</li>
					<li id="size50" class="class01" onclick="setPageSize(50);">50</li>
					<li id="size100" class="class01" onclick="setPageSize(100);">100</li>
					<li id="size200" class="class01" onclick="setPageSize(200);">200</li>
				</ul>
			</div> -->
			<div id="rightDiv">
				<span>共 <span class="span_01" id="total"></span> 条</span>
				<span>当前第 <span class="span_01" id="nowPage"></span> 页</span>
				<span>共 <span class="span_01" id="pageNum"></span> 页</span>
				<span><span class="span_02" onclick="gotoPrePage();">上一页</span></span>
				<span><span class="span_02" onclick="gotoNextPage();">下一页</span></span>
				<span>
					<select id="pageSize" style="height:22px;" onchange="setPageSize(this);">
						<option value="10">每页10条</option>
						<option value="20">每页20条</option>
						<option value="40">每页40条</option>
						<option value="50">每页50条</option>
					</select>
				</span>
				<!-- <span>去第 <input type="text" id="toPage" style="width:20px;"/> 页</span>
				<span class="span_02" onclick="gotoPage();">跳转</span> -->
			</div>
		</div>
 	</div>
      <div class="button_cz fl" style="margin-top:20px; margin-left:0; padding:0; width:1046px;">
        	<input type="button" value="保存" onclick="save(1);"/>
            <input type="button" value="保存并发布" onclick="save(2);"/>
            <input type="button" value="返回" class="back" onclick="goBack();"/>
    </div>
</div>

<!-- dialog1 选择试卷 start -->
	<div id="selectPaper" style="display: none;overflow: auto;">
		<div class="course_tree treeDiv1">
        	<div id="paperTree" class="ztree treeDiv2"></div>
   		</div>
   		<div class="search_3 fl" style="float: right;width: 700px;border: none">
	        	<p>	
	               	试卷名称： <input id="paperName" type="text">
	        	</p>
	        	<input type="button" value="确定" class="btn_cx"  style="background-color: #0085fe;" onclick="paperSelect();">
	            <input type="button" value="查询" class="btn_cx" onclick="doPaperSearch()">
        </div>
   		<div style="width: 720px;float: right;">
   			<table id="paperTable"></table>
			<div id="paginatorPaging1" style="text-align: right;margin-right: 10px;"></div>
   		</div>
	</div>
	<!-- dialog1 end -->
	
	<div class="course_tree" style="display:none;" id="departmentDiv">
       	<ul id="departmentTree" class="ztree" style=""></ul>
  	</div>
	<!-- <div class="course_tree" style="display:none;" id="groupDiv">
       	<ul id="groupTree" class="ztree" style=""></ul>
  	</div> -->
  	<div id="selectGroup" style="display: none;">
   		<div class="search_3 fl" style="width: 700px;border: none">
	        	<p>	
	            	群组名： <input id="groupName" type="text">
	               	创建人： <input id="createUserName" type="text">
	        	</p>
	        	<input type="button" value="确定" class="btn_cx"  style="background-color: #0085fe;" onclick="groupSelect();">
	            <input type="button" value="查询" class="btn_cx" onclick="doGroupSearch()">
        </div>
        <div class="clear_both"></div>
   		<div style="width: 720px;">
   			<table id="groupTable"></table>
			<div id="paginatorPaging3" style="text-align: right;margin-right: 10px;"></div>
   		</div>
	</div>
  	<div id="selectPerson" style="display: none;">
		<div class="course_tree treeDiv1" style="max-height:440px;height:440px;min-height:none;min-width:none;border:none;">
        	<div id="personTree" class="ztree treeDiv2" style="max-height:440px;height:440px;width:200px;overflow:auto;border:1px solid #DDD;" ></div>
   		</div>
   		<div class="search_3 fl" style="width: 640px;border: none;float:right;">
	        	<p>	
	            	用户名： <input id="userName2" type="text">
	               	姓名： <input id="name2" type="text">
	        	</p>
	        	<input type="button" value="确定" class="btn_cx"  style="background-color: #0085fe;margin-right: 0px;" onclick="personSelect();">
	            <input type="button" value="查询" class="btn_cx" style="margin-right: 2px;" onclick="doPersonSearch()">
        </div>
   		<div style="width: 640px;float:right;">
   			<table id="personTable"></table>
			<div id="paginatorPaging4" style="text-align: right;margin-right: 10px;"></div>
   		</div>
	</div>
</body>
</html>
