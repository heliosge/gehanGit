<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账户信息</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<style type="text/css">
.search_5{width: auto;border: 1px solid #aaa;}
.search_5 p input[type=button]{float: none;margin-left: 20px;}
</style>
<script>
$(function(){
	getCurrentUserIntegral();
	initDatepicker();
	initGird();
});

function initDatepicker() {
	$("#startTime").datepicker({
		dateFormat : 'yy-mm-dd',
		changeMonth: true,
        changeYear: true
	});

	$("#endTime").datepicker({
		dateFormat : 'yy-mm-dd',
		changeMonth: true,
        changeYear: true
	});
}
var artDialog;
function uploadHeadPhoto(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/manageUser/toUploadPhoto.action",
        title:"选择头像" ,
        height: 250,
		width: 450
		}).showModal();
}

/**
 * 获取当前用户的最大积分
 */
function getCurrentUserIntegral(){
	var urlStr = "<%=request.getContextPath()%>/integralStore/getCurrentUserIntegral.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		success : function(data) {
			var maxCount = data;
			$("#maxCount").html(maxCount);
		}
	});
}
//初始化grid数据
function initGird() {
	$('#mmTable')
			.mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : '<%=request.getContextPath()%>/integralStore/getUserExchangeRecords.action',
						height : 'auto',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : true,
						multiSelect : true,
						indexCol : true, // 索引列
						params : function(){
							// 封装参数
							var params = new Object;
							var name = $("#name").val();
							var status = $("#status").val();
							var startTime = $("#startTime").val();
							var endTime = $("#endTime").val();
							params.name = name;
							params.status = status;
							params.startTime = startTime;
							params.endTime = endTime;
							return params;
						},
						cols : [
								{
									title : 'id',
									name : 'id',
									hidden : true
								},
								{
									title : '商品名称',
									name : 'commodityName',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var id = item.commodityId;
										var ahtml="<%=request.getContextPath()%>/integralStore/toProductDetail.action?id="+id;
										return "<a href=\""+ahtml+"\">"+val+"</a>";
									}
								},
								{
									title : '兑换积分',
									name : 'allIntegral',
									align : 'center'
								},
								{
									title : '兑换时间',
									name : 'createTime',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										return getFormatDateByLong(val, "yyyy-MM-dd hh:mm:ss");
									}
								},{
									title : '状态',
									name : 'status',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var str="";
										if(val==0){
											str = "未发货";
										}else if(val==1){
											str = "已发货";
										}else if(val==2){
											str = "已完成";
										}
										return str;
									}
								},
								{
									title : '操作',
									name : 'operation',
									width : 150,
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var str = "";
										var status = item.status;
										var mod = "<a href=\"javascript:void(0);\" onclick=\"doCheckProduct("+item.id+")\">确认收货</a>&nbsp;";
										if(status!=1){
											mod = "<a href=\"javascript:void(0);\" style=\"color:#ddd;\">确认收货</a>&nbsp;";
										}
										var pubs = "<a href=\"javascript:void(0);\" onclick=\"toDetail("+item.id+")\">详情</a>&nbsp;";
										str = mod+pubs;
										return str;
									}
								} ],
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
 * 查看订单详情
 */
function toDetail(orderId){
	window.location.href = "<%=request.getContextPath()%>/integralStore/toOrderDetail.action?orderId="+orderId;
}
/**
 * 查看物流
 */
function seeLogistics(){
	
}
/**
 * 确认收货
 */
function doCheckProduct(orderId){
	var urlStr = "<%=request.getContextPath()%>/integralStore/doCheckProduct.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data:{
			"orderId" : orderId
			},
		success : function(data) {
			$('#mmTable').mmGrid().load();
		}
	});
}

function doSearch(){
	$('#mmTable').mmGrid().load({"page":1});
}
function doReset(){
	$("#searchForm")[0].reset();
	$('#mmTable').mmGrid().load({"page":1});
}
</script>

</head>

<body>
<div id="content_i">
	<div class="left_nav">
		<img id="headPhoto" src="${USER_BEAN.headPhoto }" style="height:188px;width:256px;cursor:pointer;" onclick="uploadHeadPhoto()"/>
    	<h3>
        	<span style="margin-left:-15px;">${USER_NAME_STRING }</span>
        </h3>
        <h4>账户信息</h4>
        <ul  id="left_nav">
            <li><a href="#" class="active_3">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toPersonnalInfo.action">个人信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toChangePassword.action">修改密码</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMyCertificate.action">我的证书</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMyReceiveNotice.action">站内信-收件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySendNotice.action">站内信-发件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySystemNotice.action">站内信-系统消息</a></li>
            <li class="active_2"><a href="<%=request.getContextPath()%>/integralStore/toMyIntegealExchange.action">我兑换的商品</a></li>
           
        </ul>
    	
    </div>
    <div class="right_options" id="rop">
        <div class="right_option1" style="display:block;">
        	<div><span style="font-size: 18px;">我的积分：</span><span id="maxCount" style="color: red;font-size: 18px;"></span></div>
        	<form id="searchForm">
	        		<div class="search_5" style="margin-top: 20px;">
						<p style="border: none;">
							<span>商品名称：</span> <input type="text" id="name" name="name"/> 
								<span><span>状态：</span> 
									<select id="status" id="status" name="status">
										<option value="" selected="selected">显示全部</option>
										<option value="0">未发货</option>
										<option value="1">已发货</option>
										<option value="2">已完成</option>
									</select>
								</span>
						</p>
						<p style="border: none;">
							<span>时间：</span>
							<input type="text" id="startTime" name="startTime"/> <em>至</em> <input id="endTime" type="text" name="endTime"/>
							<input type="button" class="btn_5" value="查询" onclick="doSearch()"/>
							<input type="button" class="btn_5" value="重置" onclick="doReset()" style="background: #283577"/>
						</p>
					</div>
				</form>
				<div style="clear: both;overflow: hidden;width: 755px">
					<table id="mmTable"></table>
					<div id="paginatorPaging" style="text-align: right;"></div>
				</div>
        </div>
     </div>
</div>
</body>

</html>
