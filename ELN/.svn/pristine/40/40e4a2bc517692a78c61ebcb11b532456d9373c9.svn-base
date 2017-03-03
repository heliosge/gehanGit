<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>大赛管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/uuidUtil.js"></script>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>

<style type="text/css">
.webuploader-pick {
	line-height: 20px;
    margin-top: 10px;
}
.award_prize_pic_size_desc {
  width: 80px;
  display: inline-block;
  line-height: 15px;
  padding-top: 6px;
}
.lesson_add .add_gr .add_fr span{margin-left: 1px;}
.awardsName .n-error{position: relative;}
.awardsName .n-arrow{right: -12px;}
.awardsCounts .n-error{position: relative;}
.awardsCounts .n-arrow{right: -12px;}
.prizeName .n-error{position: relative;}
.prizeName .n-arrow{right: -12px;}
</style>
<script type="text/javascript" >
var userId = '${USER_ID_LONG}';// current user
var operateType = '${operateType}';//1新增；2修改
var megagameId = '${megagameId}';//当修改操作的时候有用
var companyId = '${USER_BEAN.companyId}';
var awardNums = 1;//奖项序号（默认1）

$(function() {
	if(operateType==1){//新增
		$("#operatorTitle").html("新增大赛");
		$("#save2Other").show();
		$("#addMatch").hide();
		upLoadImg();
		upAwardsImg("awardsPicker", $("#jx"));
	}else if(operateType==2){//修改
		$("#operatorTitle").html("修改大赛");
		//隐藏保存并添加赛程按钮
		$("#save2Other").hide();
		//显示新增赛程按钮
		$("#addMatch").show();
		//初始化头部标签信息
		initHeanTitle();
		//初始化大赛基本信息
		initMegagameBaseInfo();
		//初始化选择的参赛资格
		initQualification();
		//初始化奖项
		initAwardsInfo();
	}
	initValidate();
});

/**
 * 修改--获取大赛下的所有赛程，生成头部标签
 */
function initHeanTitle(){
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getAllMatchBymegagameId.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {"megagameId":megagameId},
		success : function(data) {
			if(data.rtnResult=="SUCCESS"){
				var list = data.rtnDataList;
				var html="";
				var opd = $("#title_ul");
				html+="<li class=\"com_1\" style=\"cursor: pointer;\" onclick=\"toModifyMegagame("+megagameId+")\">修改大赛</li>";
				if(list){
					var len = list.length;
					for(var i=0;i<len;i++){
						var id = list[i].id;
						var orderNum = list[i].orderNum;
						html+="<li class=\"com_2\" style=\"cursor: pointer;\" onclick=\"toModifyMatch("+id+")\">修改赛程"+orderNum+"</li>";
					}
				}
				opd.empty();
				opd.html(html);
			}
		}
	});
}
/**
 * 新增一个赛程
 */
function toAddNewMatch(){
	window.location.href = "<%=request.getContextPath()%>/megagameManage/toAddMatch.action?megagameId="+megagameId+"&operateType=1";
}
/**
 * 跳转修改大赛
 */
function toModifyMegagame(megagameId){
	window.location.href = "<%=request.getContextPath()%>/megagameManage/toAddMegagame.action?operateType=2&megagameId="+megagameId;
}
/**
 * 跳转 修改赛程
 */
 function toModifyMatch(id){
	 window.location.href = "<%=request.getContextPath()%>/megagameManage/toAddMatch.action?operateType=2&megagameId="+megagameId+"&matchId="+id;
}
/**
 * 修改--初始化表单大赛基础信息
 */
function initMegagameBaseInfo(){
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getMegagameInfoById.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {"megagameId":megagameId},
		success : function(data) {
			if(data.rtnResult=="SUCCESS"){
				var datas = data.rtnData;
				if(datas){
					var name = datas.name;
					var coverImageForList = datas.coverImageForList;
					$("#previewPath").attr("src",coverImageForList);
					var needApproval = datas.needApproval;
					var qualification = datas.qualification;
					var detail = datas.detail;
					var processDescription = datas.processDescription;
					$("#name").val(name);// 大赛名称
					$("#filePath").val(coverImageForList);// 宣传图片
					$("#detail").val(detail);// 比赛详情
					$("#qualification").val(qualification); // 参赛资格描述
					$("#processDescription").val(processDescription);//参赛流程
					$("input[name=needApproval]").each(function() {
						if ($(this).val()== needApproval) {
							$(this)[0].checked=true;
						}
					});
					upLoadImg();
				}
			}
		}
	});
}
/**
 * 修改--初始化选择的参赛资格信息
 */
function initQualification(){
	var urlStr = "<%=request.getContextPath()%>/megagameManage/getQualification.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {"megagameId":megagameId},
		success : function(data) {
			if(data){
				var len = data.length;
				var ids="";
				var names="";
				var qualificationType="";
				for(var i=0;i<len;i++){
					var qualificationId = data[i].qualificationId;
					qualificationType =  data[i].qualificationType;
					var name = data[i].name;
					ids +=qualificationId;
					names +=name;
					if(i!=len-1){
						ids+=",";
						names+=",";
					}
				}
				$("#postId").val(ids);
				$("#postName").val(names);
				$("#qualificationType").val(qualificationType);
			}
		}
	});
}
/**
 * 修改--初始化奖项信息
 */
function initAwardsInfo(){
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getAwardsSetting.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {"megagameId":megagameId},
		success : function(data) {
			if(data.rtnResult=="SUCCESS"){
				var list = data.rtnDataList;
				if(list){
					var awardsDiv = $("#awardsDiv");
					var len = list.length;
					var html="";
					var divStr1="<div class=\"jx\">";
					var divStr2="<div class=\"add_gr\" style=\"height: 40px;\">";
					var divStr3="<div class=\"add_fl\" style=\"width: 80px; height: 40px;\">";
					var divStr4="<div class=\"add_fr\">";
					var divStr5="<span>*</span> <em>共计：</em>";
					var divStr6="<span>*</span> <em>奖品名称：</em>";
					var divStrEnd="</div>";
					awardsDiv.empty();
					for(var i=0;i<len;i++){
						// 生成随机id
						var uid = Math.uuid();
						var awardName = list[i].awardName;
						var totalCount = list[i].totalCount;
						var prizeName = list[i].prizeName;
						var coverImage = list[i].coverImage;
						var orderNum = list[i].orderNum;
						html+=divStr1;
						
						html+=divStr2;
						html+=divStr3;
						html+="<span>*</span> <em>奖项名称：</em>";
						html+="<input name=\"awardsNum\" type=\"hidden\" value=\""+orderNum+"\">";
						html+=divStrEnd;
						html+=divStr4;
						html+="<input type=\"text\" name=\"awardsName\" style=\"width: 250px;\" value=\""+awardName+"\"/>";
						html+=divStrEnd;
						html+=divStrEnd;
						
						html+=divStr2;
						html+=divStr3;
						html+="<span>*</span> <em>共计：</em>";
						html+="<input name=\"awardsNum\" type=\"hidden\" value=\""+orderNum+"\">";
						html+=divStrEnd;
						html+=divStr4;
						html+="<input type=\"text\" name=\"awardsCounts\" style=\"width: 250px;\" value=\""+totalCount+"\" />";
						html+=divStrEnd;
						html+=divStrEnd;
						
						html+=divStr2;
						html+=divStr3;
						html+="<span>*</span> <em>奖品名称：</em>";
						html+="<input name=\"awardsNum\" type=\"hidden\" value=\""+orderNum+"\">";
						html+=divStrEnd;
						html+=divStr4;
						html+="<input type=\"text\" name=\"prizeName\" style=\"width: 250px;\" value=\""+prizeName+"\" />";
						html+=divStrEnd;
						html+=divStrEnd;
						
						html+=divStr2;
						html+=divStr3;
						html+="<span>*</span> <em>奖品图片：</em>";
						html+="<input name=\"awardsNum\" type=\"hidden\" value=\""+orderNum+"\">";
						html+=divStrEnd;
						html+=divStr4;
						html+="<div id=\"uploader\" class=\"wu-example\" style=\"float: left;\">";
						html+="<div id=\"thelist\" class=\"uploader-list\"></div>";
						html+="<div class=\"btns\">";
						html+="<div id=\""+uid+"\" style=\"padding: 0px;\">上传奖品图片</div>";
						html+=divStrEnd;
						html+="<input id=\"awardsPath\" name=\"awardsPath\" type=\"hidden\" value=\""+coverImage+"\">";
						html+=divStrEnd;
						html+="<img name=\"previewAward\" src=\""+coverImage+"\" style=\"margin-left: 10px;width: 100px; height: 45px; margin-top: 5px;\" />";
						html+="<em class=\"award_prize_pic_size_desc\">建议图片大小为（宽*高）：56*112</em>";
						html+=divStrEnd;
						html+=divStrEnd;
						
						html+=divStrEnd;
						awardsDiv.append(html);
						html="";
						var currentObj = $("#" + uid).parent().parent().parent();
						upAwardsImg(uid, currentObj);
						if(i==len-1){//最后一条记录 获取其奖项的序号orderNum,赋值给全局量awardNums
							awardNums = orderNum;
						}
					}
						var addhtml="<a href=\"javascript:void(0);\"><img src=\"<%=request.getContextPath()%>/images/img/add_sc.png\" style=\"margin-left: 20px; margin-top: 80px;\" onclick=\"addAwaresSetting()\" /></a>";
						awardsDiv.append(addhtml);
				}
			}
		}
	});
}
function upLoadImg() {
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
					id : '#picker',
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
		var filePath = $("#filePath").val();
		if(!filePath || filePath==""){
			$("#span_xctp").show();
		}else{
			$("#span_xctp").hide();
		}
	});

}

function upAwardsImg(pickId, currentObj) {
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
					id : '#' + pickId,
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

	uploader.on('fileQueued', function(file) {
	});

	// 接受文件后，进行赋值操作
	uploader.on('uploadAccept', function(file, ret) {
		currentObj.find("input[name='awardsPath']").val(ret._raw);
		// 预览图片
		// currentObj 当前操作的一个整个奖项的div对象
		currentObj.find("img[name='previewAward']").attr("src", ret._raw).css({
			"width" : "100px",
			"height" : "45px",
			"margin-top" : "5px"
		});
		var flags = true;
		currentObj.find("img[name='awardsPath']").each(function(){
			if($(this).val()=="" || !$(this).val()){
				flags = false;
			}
		});
		if(flags){
			$("#jptp").hide();
		}else{
			$("#jptp").show();
		}
	});
}
/**
 * 保存
 */
function doSave(jumpFlag) {
	var params = new Object;
	var name = $("#name").val();// 大赛名称
	var competitionImgPath = $("#filePath").val();// 宣传图片
	var detail = $("#detail").val();// 比赛详情
	var qualification = $("#qualification").val(); // 参赛资格描述
	var needApproval;
	var qualificationType = $("#qualificationType").val();//参赛资格类型
	var postId = $("#postId").val();//选择岗位id
	var processDescription = $("#processDescription").val();//参赛流程
	var awardsName = [];
	var awardsCounts =[];
	var prizeName = [];
	var awardsPath = [];
	var awardsNums = [];
	$("input[name='awardsName']").each(function(){
		awardsName.push($(this).val());
	});
	$("input[name='awardsCounts']").each(function(){
		awardsCounts.push($(this).val());
	});
	$("input[name='prizeName']").each(function(){
		prizeName.push($(this).val());
	});
	$("input[name='awardsPath']").each(function(){
		awardsPath.push($(this).val());
	});
	
	$("input[name='awardsNum']").each(function(){
		awardsNums.push($(this).val());
	});
	
	$("input[name=needApproval]").each(function() {
		if ($(this)[0].checked == true) {
			needApproval = $(this).val();
		}
	});
	params.id = megagameId;//修改时参数-大赛id
	params.megagameName = $.trim(name);
	params.qualification = $.trim(qualification);
	params.qualificationType = $.trim(qualificationType);
	params.detail = $.trim(detail);
	params.needApproval = needApproval;
	params.processDescription = processDescription;
	params.competitionImgPath = competitionImgPath;
	params.postId = postId;
	params.awardsName = $.trim(awardsName);
	params.awardsCounts = $.trim(awardsCounts);
	params.prizeName = $.trim(prizeName);
	params.awardsPath = awardsPath;
	params.awardsNums = awardsNums;
	params.operateType = operateType;
	
	$('#addForm').isValid(function(v) {
		var filePath = $("#filePath").val();
		if(!filePath || filePath==""){
			$("#span_xctp").show();
			v=false;
		}else{
			$("#span_xctp").hide();
		}
		var postId = $("#postId").val();
		if(!postId || postId==""){
			$("#span_cs").show();
			v=false;
		}else{
			$("#span_cs").hide();
		}
		
		var flag=true;
		$("input[name='awardsPath']").each(function(){
			var imgPath = $(this).val();
			if(!imgPath || imgPath==""){
				flag=false;
			}
		});
		if(!flag){
			$("#jptp").show();
			v=false;
		}else{
			$("#jptp").hide();
		}
		if (v) {
			var urlStr = "<%=request.getContextPath()%>/megagameManage/addOrUpdateCompetition.action";
			$.ajax({
				type : "POST",
				url : urlStr,
				data : params,
				success : function(data) {
					if(data){
						dialog.alert("操作成功！",function (){
							if(jumpFlag==1){//跳转到大赛列表
								window.location.href = "<%=request.getContextPath()%>/megagameManage/toMegagameList.action";
							}else if(jumpFlag==2){//保存并跳转新增赛程
								window.location.href = "<%=request.getContextPath()%>/megagameManage/toAddMatch.action?megagameId="+data+"&operateType=1";
							}
						});
					}
					
				}
			});
		}else{
			dialog.alert("填写有误，请检查");
		}
	});
	
}

/**
 * 增加奖项
 */
function addAwaresSetting() {
	awardNums = Number(awardNums)+1;
	// 生成随机id
	var uid = Math.uuid();
	var awardS = $("#awardsDiv");
	var copyD = $("#copyD").children().clone();
	
	copyD.find("div[name='btns']").children().attr("id", uid);
	copyD.find("input[name='awardsNumModel']").attr("name","awardsNum");
	copyD.find("input[name='awardsNameModel']").attr("name","awardsName");
	copyD.find("input[name='awardsCountsModel']").attr("name","awardsCounts");
	copyD.find("input[name='prizeNameModel']").attr("name","prizeName");
	copyD.find("input[name='awardsPathModel']").attr("name","awardsPath");
	copyD.find("img[name='previewAwardModel']").attr("name","previewAward");
	copyD.find("input[name='awardsNum']").attr("value",awardNums);
	
	awardS.append(copyD);
	var currentObj = $("#" + uid).parent().parent().parent();
	upAwardsImg(uid, currentObj);
	
}

function initCompanyGrid(){
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageCompany/selectManageCompanyList.action",
    	width: $('#exampleTable').width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        indexCol: true,  //索引列
        checkCol: true,
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
 			   {title: '企业代码', name: 'code', width:60, align:'center'},
 			   {title: '企业大学名称', name: 'name', width:120, align:'center'},
 			   {title: '行业分类', name: 'industryName', width:60, align:'center'},
 			   {title: '手机', name: 'phoneNum', width:90, align:'center'},
 			   {title: '邮箱', name: 'email', width:120, align:'center'},
 			   {title: '企业域名', name: 'domain', width:120, align:'center'},
			   {title: '注册时间', name: 'createTime', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return getSmpFormatDateByLong(val, false);
			   }},
			   {title: '状态', name: 'status', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "正常";
				   }else if(val == 2){
					   return "冻结";
				   }
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
function changeContent(obj){
	var v = $(obj).val();
	if(v==2){
		$("#bbsT").show();
	}else{
		$("#bbsT").hide();
	}
}
function change2Content(obj){
	var v = $(obj).val();
	if(v==1){
		$("#depT").show();
	}else{
		$("#depT").hide();
	}
}
/**
 * 参赛资格选择
 */
function selectQualification() {
	if(companyId==1){//普联
		initCompanyGrid();
		$('#exampleTable').mmGrid().load();
		var companyGridDialog = $("#companyGridDialog");
		$("#xzRadio").show();
		dialog(
				{
					title : "选择企业/人",
					width : 1050,
					height : 500,
					content : companyGridDialog,
					okValue : '确定',
					ok : function() {
						var qualificationType;
						$("input[name='cdd']").each(function(){
							if($(this)[0].checked){
								qualificationType = $(this).val();
							}
						});
						if(qualificationType==2){
							var dataItems = $('#exampleTable').mmGrid().selectedRows();
							var ids = "";
							var idNames = "";
							for(var i=0;i<dataItems.length;i++){
								ids += dataItems[i].id + ",";
								idNames += dataItems[i].name + ",";
							}
							$("#postId").val(ids.substring(0, ids.length - 1));
							$("#postName").val(idNames.substring(0, idNames.length - 1));
							
						}else if(qualificationType==4){
							$("#postId").val(qualificationType);
							$("#postName").val("所有人");
						}
						
						$("#qualificationType").val(qualificationType);
						$("#xzRadio").hide();
					},
					cancelValue : '取消',
					cancel : function() {
						$("#xzRadio").hide();
					}
				}).showModal();
	}else{//非普联公司
		initDepInfo();
		var depGridDialog = $("#depGridDialog");
		$("#depRadio").show();
		dialog(
				{
					title : "选择下级公司/部门/所有员工",
					width : 1050,
					height : 500,
					content : depGridDialog,
					okValue : '确定',
					ok : function() {
						var flagType;
						var qualificationType;
						$("input[name='depdd']").each(function(){
							if($(this)[0].checked){
								flagType = $(this).val();
							}
						});
						var depDataItems = $('#dep_Table').mmGrid().selectedRows();
						var ids="";
						var idNames="";
						if(flagType==1){//下级部门/公司
							depDataItems.map(function(m_value,m_index,m_item){
								var id = m_value.id;
								var name = m_value.name;
								ids+=id+",";
								idNames+=name+",";
							});
							qualificationType = 1;//权限类型为部门
							$("#postId").val(ids.substring(0, ids.length - 1));
							$("#postName").val(idNames.substring(0, idNames.length - 1));
						}else{
							qualificationType = 2;//权限类型为公司
							$("#postId").val(companyId);//当前公司所有人
							$("#postName").val("所有人");
						}
						$("#qualificationType").val(qualificationType);
					},
					cancelValue : '取消',
					cancel : function() {
					}
				}).showModal();
	}
}
/**
 * 初始化下级公司/部门信息
 */
function initDepInfo(){
	//表格
	$('#dep_Table').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/megagameManage/getDepInfo.action",
    	width: $('#exampleTable').width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        //indexCol: true,  //索引列
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '下级部门/子公司名称', name: 'name', width:60, align:'center'},
 			   {title: '描述', name: 'descr', width:120, align:'center'}
           ],
        plugins : [
        	$('#dep_Paginator11-1').mmPaginator({
        		totalCountName: 'total',    //对应MMGridPageVoBean count
        		page: 1,                    //初始页
        		pageParamName: 'page',      //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [10, 20, 40, 50]
        	})
        ]
    });
}

/**
 * 初始化选择岗位的信息
 */
<%-- function initPost() {
	var setting = {
		data : {
			key : {
				url : "xUrl"
			},
			simpleData : {
				enable : true,
				pIdKey : "parentId"
			}
		},
		check : {
			enable : true
		},
		callback : {
			beforeClick : function(treeId, treeNode, clickFlag) {
				return false;
			}
		}
	};
	$.ajax({
				type : "POST",
				async : false, // 默认true,异步
				url : "<%=request.getContextPath()%>/manageParam/selectManagePost.action",
				success : function(data) {
					$.fn.zTree.init($("#treePage"), setting, data);
				}
			});
} --%>
/**
 * 跳转-返回
 */
function goBack(){
	window.location.href = "<%=request.getContextPath()%>/megagameManage/toMegagameList.action";
}

/**
 * 表单验证
 */
function initValidate() {
	$('#addForm').validator({
		theme : 'yellow_right_effect',
		rules : {
			checkTitle:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{
						"userId":userId,
						"megagameId" : megagameId,
						"name":$.trim($("#name").val())
						},
					url:"<%=request.getContextPath()%>/megagameManage/checkMegagameNameIsOnly.action",
					success:function(data){
						var flag = true;
						if(data > 0){
							flag = false;
						}
						str =  flag || "已存在相同名称";
					}
				});
				return str;
			},
			checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ]
		},
		msgStyle:"margin-top: 10px;margin-left:10px",
		fields : {
			name : {
				rule : "required;length[~30]",
				msg : {
					required : "大赛不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			qualification : {
				rule : "required;length[~200]",
				msg : {
					required : "参赛资格描述不能为空",
					length : "长度需小于等于200个字符"
				}
			},
			postId : {
				rule : "required;length[~200]",
				msg : {
					required : "参赛资格描述不能为空",
					length : "长度需小于等于200个字符"
				}
			},
			detail : {
				rule : "required;length[~200]",
				msg : {
					required : "比赛详情不能为空",
					length : "长度需小于等于200个字符"
				}
			},
			processDescription : {
				rule : "required;length[~200]",
				msg : {
					required : "参赛流程不能为空",
					length : "长度需小于等于200个字符"
				}
			},
			awardsName : {
				rule : "required;length[~30]",
				msg : {
					required : "奖项名称不能为空",
					length : "长度需小于等于30个字符"
				},
				msgClass:"n-left awardsName",
				msgStyle :"margin-left:-520px;margin-top:20px;"
			},
			awardsCounts : {
				rule : "required;integer",
				msg : {
					required : "奖品数量不能为空",
					integer : "请输入整数"
				},
				msgClass:"n-left awardsCounts",
				msgStyle :"margin-left:-520px;margin-top:20px;"
			},
			prizeName : {
				rule : "required;length[~30]",
				msg : {
					required : "奖品名称不能为空",
					length : "长度需小于等于30个字符"
				},
				msgClass:"n-left prizeName",
				msgStyle :"margin-left:-520px;margin-top:20px;"
			}
		}
	});
}

function removeAward(obj){
	$(obj).parent().parent().remove();
}
</script>
</head>
<body>
	<div class="content">
		<!-- <h3 id="operatorTitle">新增大赛</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="goBack();"/> 
			<span id="operatorTitle" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增大赛</span>
		</div>
		<div class="tab_competition">
			<ul id="title_ul">
				<li class="com_1">1新增大赛</li>
				<li class="com_2">2新增赛程</li>
			</ul>
		</div>
		<form id="addForm">
		<div class="lesson_add">
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>大赛名称：</em>
				</div>
				<div class="add_fr">
					<input type="text" id="name" name="name" />
				</div>
			</div>
			<div class="add_gr_1">
				<div class="add_fl">
					<span>*</span> <em>宣传图片：</em>

				</div>
				<div class="add_fr">
					<div id="uploader" class="wu-example">
						<!--用来存放文件信息-->
						<div id="thelist" class="uploader-list"></div>
						<div class="btns">
							<div id="picker">选择图片</div>
						</div>
						<input id="filePath" type="hidden" name="filePath">
					</div>
					
					<em>建议图片大小为（宽*高）：340*270</em>
					<span id="span_xctp" class="msg-box n-right" for="filePath" style="display: none;">
						<span class="msg-wrap n-error" role="alert">
							<span class="n-arrow"><b>◆</b><i>◆</i></span>
							<span class="n-icon"></span>
							<span class="n-msg">宣传图片不能为空</span>
						</span>
					</span>
					<p class="tp">
						<img id="previewPath" src="<%=request.getContextPath()%>/images/img/left_2.png" style="width: 278px; height: 105px;"/>
					</p>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>参赛是否需要报名：</em>
				</div>
				<div class="add_fr">
					<input type="radio" name="needApproval" value="1" checked="checked"/> <span>需要报名</span> 
					<input type="radio" name="needApproval" value="0" /> <span>不需要报名</span>
				</div>
			</div>

			<div class="add_gr" style="margin-bottom: 40px;">
				<div class="add_fl">
					<span>*</span> <em>参赛资格描述：</em>
				</div>
				<div class="add_fr">
					<textarea id="qualification" name="qualification" style="overflow: scroll; height: 70px;"></textarea>
				</div>
			</div>
			
			<div class="add_gr" style="margin-bottom: 20px;">
				<div class="add_fl">
					<span>*</span> <em>选择参赛资格：</em>
				</div>
				<div class="add_fr">
						<input type="text" disabled="disabled" id="postName" name="postName">
						<input type="hidden" id="qualificationType">
						<input type="hidden" id="postId">
						<input type="button" value="选择" class="gw" onclick="selectQualification()"/>
						<span id="span_cs" class="msg-box n-right" for="postName" style="display: none;">
							<span class="msg-wrap n-error" role="alert">
								<span class="n-arrow"><b>◆</b><i>◆</i></span>
								<span class="n-icon"></span>
								<span class="n-msg">参赛资格不能为空</span>
							</span>
						</span>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>比赛详情：</em>
				</div>
				<div class="add_fr">
					<textarea id="detail" style="overflow: scroll; height: 70px;" name="detail"></textarea>
				</div>
			</div>

			<div class="add_gr" style="margin-top: 30px;">
				<div class="add_fl">
					<span>*</span> <em>参赛流程：</em>
				</div>
				<div class="add_fr">
					<textarea id="processDescription" name="processDescription" style="overflow: scroll; height: 70px;"></textarea>
				</div>
			</div>

			<div class="add_gr_2" style="margin-top: 30px;">
				<div class="add_fl">
					<span>*</span> <em>奖项设置：</em>
				</div>
				<div class="add_fr" id="awardsDiv">
					<div class="jx" id="jx">
						<div>
							<img alt="删除" src="<%= request.getContextPath()%>/images/question_delete.png" 
							style="width: 10px;margin-top: 5px;float: right;margin-right: 10px;cursor: pointer;"onclick="removeAward(this)">
						</div>
						<div class="add_gr" style="height: 40px;">
							<div class="add_fl" style="width: 80px; height: 40px;">
								<span>*</span> <em>奖项名称：</em>
								<input name="awardsNum" type="hidden" value="1">
							</div>
							<div class="add_fr">
								<input type="text" name="awardsName" style="width: 250px;" />
							</div>
						</div>
						<div class="add_gr" style="height: 40px;">
							<div class="add_fl" style="width: 80px; height: 40px;">
								<span>*</span> <em>共计：</em>
							</div>
							<div class="add_fr">
								<input type="text" name="awardsCounts" style="width: 250px;" />
							</div>
						</div>
						<div class="add_gr" style="height: 40px;">
							<div class="add_fl" style="width: 80px; height: 40px;">
								<span>*</span> <em>奖品名称：</em>
							</div>
							<div class="add_fr">
								<input type="text" name="prizeName" style="width: 250px;" />
							</div>
						</div>
						<div class="add_gr" style="height: 40px;">
							<div class="add_fl" style="width: 80px; height: 40px;">
								<span>*</span> <em>奖品图片：</em>
							</div>
							<div class="add_fr">
								<div id="uploader" class="wu-example" style="float: left;">
									<!--用来存放文件信息-->
									<div id="thelist" class="uploader-list"></div>
									<div class="btns">
										<div id="awardsPicker" style="padding: 0px;">上传奖品图片</div>
									</div>
									<input id="awardsPath" name="awardsPath" type="hidden">
								</div>
								<img name="previewAward" src="" style="margin-left: 10px;width: 100px; height: 45px; margin-top: 5px;" />
								<em class="award_prize_pic_size_desc">建议图片大小为（宽*高）：56*112</em>
							</div>
						</div>
					</div>
					<span id="jptp" class="msg-box n-left" for="awardsPath" style="display: none;right: 425px;">
								<span class="msg-wrap n-error" role="alert"
								 style="width: 125px;margin-top: 155px;">
									<span class="n-arrow"><b>◆</b><i>◆</i></span>
									<span class="n-icon"></span>
									<span class="n-msg">奖品图片不能为空</span>
								</span>
					</span>
					<a href="javascript:void(0);"><img src="<%=request.getContextPath()%>/images/img/add_sc.png" style="margin-left: 20px; margin-top: 80px;" onclick="addAwaresSetting()" /></a>
				</div>
			</div>
			<div class="button_cz">
				<input type="button" value="保存" onclick="doSave(1)" /> 
				<input id="save2Other" type="button" value="保存并添加赛程"  onclick="doSave(2)"/>
				<input id="addMatch" type="button" value="添加赛程" style="display: none;" onclick="toAddNewMatch()"/>
				 <input type="button" value="返回" class="back" onclick="goBack()"/>
			</div>
			<!-- 弹出-一般企业-参赛资格选择 s -->
			<div id="depGridDialog">
				<div id="depRadio" style="display: none;">
				<input type="radio" name="depdd" value="1" checked="checked" onchange="change2Content(this)">选择下级公司/部门
				<input type="radio" name="depdd" value="2" onchange="change2Content(this)">所有员工</div>
				<div id="depT">
					<div id="dep_Table" style="margin-top:10px;width:100%;" ></div>
					<div id="dep_Paginator11-1" align="right" style="margin-right:10px;" ></div>
				</div>
			</div>
			<!-- 弹出-一般企业-参赛资格选择  e-->
			
			<!-- 弹出-普联参赛资格选择 s -->
			<div id="companyGridDialog">
				<div id="xzRadio" style="display: none;">
				<input type="radio" name="cdd" value="2" checked="checked" onchange="changeContent(this)">选择企业
				<input type="radio" name="cdd" value="4" onchange="changeContent(this)">所有人</div>
				<div id="bbsT">
					<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
					<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
				</div>
			</div>
			<!-- 弹出-普联参赛资格选择  e-->
		</div>
		</form>
	</div>
	<div style="display: none"id="copyD" >
		<div class="jx" >
			<div>
				<img alt="删除" src="<%= request.getContextPath()%>/images/question_delete.png" 
				style="width: 10px;margin-top: 5px;float: right;margin-right: 10px;cursor: pointer;"onclick="removeAward(this)">
			</div>
			<div class="add_gr" style="height: 40px;">
				<div class="add_fl" style="width: 80px; height: 40px;">
					<span>*</span> <em>奖项名称：</em>
					<input name="awardsNumModel" type="hidden">
				</div>
				<div class="add_fr">
					<input type="text" name="awardsNameModel" style="width: 250px;" />
				</div>
			</div>
			<div class="add_gr" style="height: 40px;">
				<div class="add_fl" style="width: 80px; height: 40px;">
					<span>*</span> <em>共计：</em>
				</div>
				<div class="add_fr">
					<input type="text" name="awardsCountsModel" style="width: 250px;" />
				</div>
			</div>
			<div class="add_gr" style="height: 40px;">
				<div class="add_fl" style="width: 80px; height: 40px;">
					<span>*</span> <em>奖品名称：</em>
				</div>
				<div class="add_fr">
					<input type="text" name="prizeNameModel" style="width: 250px;" />
				</div>
			</div>
			<div class="add_gr" style="height: 40px;">
				<div class="add_fl" style="width: 80px; height: 40px;">
					<span>*</span> 
					<em>奖品图片：</em>
				</div>
				
				<div class="add_fr">
					<div id="uploader" class="wu-example" style="float: left;">
						<!--用来存放文件信息-->
						<div id="thelist" class="uploader-list"></div>
						<div class="btns" name="btns">
							<div id="awardsPicker" style="padding: 0;">上传奖品图片</div>
						</div>
						<input id="awardsPath" name="awardsPathModel" type="hidden">
					</div>
					<img name="previewAwardModel" src="" style="margin-left: 10px;width: 100px; height: 45px; margin-top: 5px;" />
					<em class="award_prize_pic_size_desc">建议图片大小为（宽*高）：56*112</em>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
