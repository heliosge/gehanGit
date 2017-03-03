<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<title>积分商品详情页面</title>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/includeMagicZoom.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script src="<%=request.getContextPath() %>/js/layer/layer.js"></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<style type="text/css">
.notes_list{float: none;padding: 0}
#tsShopContainer{margin: 0}
.d_fl{float: right;margin-top: 60px;margin-right: 300px;line-height: 5;font-size: 16px;}  
.thHifget{overflow: hidden;clear: both;}
.r_a{width: 260px;float:right;}
.r_b{background: #283577;height: 30px;line-height: 30px;text-align: center;}
.dnxh{color: #fff;font-size: 16px;font-weight: bold;}
.xh{background: #02B0EC;color: #fff;padding: 10px;font-weight: bold;}
.c_fon{color: red;font-weight: bold;}
.red_star{color: red;font-weight: bold;}
.td1{text-align: center;}
table{width: 900px;height: 400px;}
table tr .td1 {background: #F2F2F2}
table tr .td2 input{height: 70%;width: 45%;}
.btn_div{color: #fff;font-size: 16px;font-weight: bold;margin-top: 50px;text-align: center;}
.btn_div span{padding: 10px 20px 10px 20px;cursor: pointer;}
.btn_div span:nth-child(1){background: #D95627;margin-right: 20px;}
.btn_div span:nth-child(2){background: #283577;}
._byd{margin-right: 20px;}
.n-yellow .msg-wrap{margin-top:-10px;}
table tr .td2 select{width: 130px;height: 25px;}

#tsShopContainer #tsPicContainer #tsImgSCon{width: auto;}
#tsShopContainer #tsPicContainer #tsImgSCon{height: 53px;}
</style>
</head>
<body>
<div id="course_all">
	<div class="notes_list">
		<!-- <h3>商品详情</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">商品详情</span>
		</div>
	</div>
		<div style="overflow: hidden;clear: both;">
			<div id="tsShopContainer" style="float: left;">
				<div id="tsImgS">
					<a href="<%=request.getContextPath()%>/js/MagicZoom/images/Chrysanthemum.jpg" title="Images" class="MagicZoom" id="MagicZoom">
						<img width="300" height="300" src="<%=request.getContextPath()%>/js/MagicZoom/images/Chrysanthemum.jpg" /></a>
				</div>
				<div id="tsPicContainer">
						<div id="tsImgSArrL" onclick="tsScrollArrLeft()"></div>
						<div id="tsImgSCon">
							<ul id="imagesUl" style="width: auto;"></ul>
						</div>
						<div id="tsImgSArrR" onclick="tsScrollArrRight()"></div>
				</div>
				<img class="MagicZoomLoading" width="16" height="16" src="<%=request.getContextPath()%>/js/MagicZoom/images/loading.gif" alt="Loading..." />
			</div>
			<div style="float: right;" class="d_fl">
					<p >商品编号：<span id="p_code"></span></p>
					<p >兑换积分：<span id="p_jf"></span>积分</p>
					<p onclick="openDialog()"><span style="padding: 10px;background: red;color: #fff;font-weight: bold;cursor: pointer;">立即兑换</span></p>
			</div>
		</div>
		<div style="margin-top: 20px;">
			<div style="width: 750px;float: left;overflow: scroll;">
				<div id="descr"></div>
			</div>
			<div class="r_a">
				<div class="r_b">
					<span class="dnxh">兑你喜欢</span>
					<ul id="yourLike" class="thHifget" style="background: #F2F2F2;">
						<li class="thHifget" style="margin-top: 10px;">
							<p style="float: left;"><span class="xh">1</span></p>
							<div style="float: right;width: 200px;">
								<p style="float: left;"><img width="50px" height="50px" src="http://placehold.it/50x50"></p>
								<div>
									<p>这里是课程名册很难</p>
									<p><span class="c_fon" >99</span>积分</p>
								</div>
							</div>
						</li>
					</ul>	
				</div>
			</div>
		</div>
</div>

<div id="dialog" style="display: none;">
	<div>
		<div><span>请正确填写兑换数量及收件人的信息：</span></div>
		<form id="addForm">
			<div style="margin-top: 20px;">
				<table border="1px solid;">
					<tr>
						<td class="td1"><div style="width: 100px;"><span class="red_star">*</span>兑换数量：</div></td>
						<td class="td2">
							<span class="_byd"></span><input type="text" value="1" id="quantity" name="quantity"/>
							<span style="color: red;">您的积分最多可兑换<span id="maxCount"></span>个</span>
						</td>
					</tr>
					<tr>
						<td class="td1"><div style="width: 100px;"> <span class="red_star">*</span>收货人：</div></td>
						<td class="td2">
							<span class="_byd"></span><input type="text" id="people" name="people"/>
						</td>
					</tr>
					<tr>
						<td class="td1"><div style="width: 100px;"><span class="red_star">*</span>联系方式：</div></td>
						<td class="td2">
							<span class="_byd"></span><input type="text" id="phone" name="phone"/>
						</td>
					</tr>
					<tr>
						<td class="td1"><div style="width: 100px;"><span class="red_star">*</span>收货地址：</div></td>
						<td class="td2">
							<span class="_byd"></span>
							<span>
								<select id="province" id="province" name="province">
								</select>
							</span>
							
							<span>
								<select id="city" name="city">
								</select>
							</span>
							
							<span>
								<select id="area" name="area">
								</select>
							</span>
							<input type="text" id="detailAddress" name="detailAddress"/>
						</td>
					</tr>
					<tr>
						<td class="td1"><div style="width: 100px;"><span class="red_star">*</span>邮编：</div></td>
						<td class="td2">
							<span class="_byd"></span><input type="text" id="postcode" name="postcode"/>
						</td>
					</tr>
					<tr>
						<td class="td1"><div style="width: 100px;">备注：</div></td>
						<td class="td2">
							<span class="_byd"></span><textarea rows="10" cols="100" id="remark" name="remark"></textarea>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<div class="btn_div">
			<span onclick="doExchange()">确认兑换</span>
			<span onclick="dialog_one.close();">返回</span>
		</div>
	</div>
</div>

<div id="resultD" style="display: none;">
	<div>
		<p style="text-align: center;">
			恭喜您，兑换成功！
			<span style="color: red;">扣除<em id="kcjf"></em>分，剩余积分：<em id="syjf"></em>分！</span>
		</p>
		<p  class="btn_div">
			<span onclick="toSeeMyProduct()">查看我兑换的商品</span>
			<span onclick="{dialog_two.close();}">继续兑换</span>
		</p>
	</div>
</div>
</body>
<script type="text/javascript">
var thisId = '${thisId}';
var thisIntegral;//当前商品兑换需要的积分
var userTotolIntegral;//当前用户总积分
var dialog_one;
var dialog_two;

var deductIntegral;//记录兑换后扣除的积分

$(function(){
	getThisProductDetail();
	getYourLikes();
	initProvince();
	$("#province").change(function(){
		$("#city").empty();
		$("#area").empty();
		initCity($("#province").val());
		
	});
	$("#city").change(function(){
		$("#area").empty();
		initArea($("#city").val());
	});
});

/**
 * 获取当前用户的最大积分
 */
function getCurrentUserIntegral(){
	var urlStr = "<%=request.getContextPath()%>/integralStore/getCurrentUserIntegral.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		async:false,
		success : function(data) {
			userTotolIntegral = data;
			var maxCount = Math.floor(Number(userTotolIntegral)/Number(thisIntegral));
			$("#maxCount").html(maxCount);
		}
	});
}
/**
 * 获取兑你喜欢的数据
 */
function getYourLikes(){
	var urlStr = "<%=request.getContextPath()%>/integralStore/getYourLike.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		success : function(data) {
			if(data.rtnResult=="SUCCESS"){
				initYourLikes(data.rtnDataList);
			}
		}
	});
}
function initYourLikes(list){
	var obj = $("#yourLike");
	var html = '';
	if(list && list.length>0){
		$.each(list,function(index,item){
			var id = item.id;
			var name = item.name;
			var titleName = name;
			var len = name.length;
			if(len>8){
				name = name.substring(0,6) + '...';
			}
			var integral = item.integral;
			var coverImage = item.coverImage;
			
			html += '<li class="thHifget" style="margin-top: 10px;" onclick=toSee('+id+')>';
			html += '<p style="float: left;"><span class="xh">'+(Number(index)+1)+'</span></p>';
			html += '<div style="float: right;width: 200px;">';
			html += '<p style="float: left;"><img width="50px" height="50px" src="'+coverImage+'"></p>';
			html += '<div>';
			html += '<p title="'+titleName+'">'+name+'</p>';
			html += '<p><span class="c_fon">'+integral+'</span>积分</p>';
			html += '</div></div></li>';
		});
	}
	obj.html(html);
}
/**
 * 获取当前商品的详细信息
 */
function getThisProductDetail(){
	var urlStr = "<%=request.getContextPath()%>/integralStore/getById.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"id" : thisId
		},
		success : function(data) {
			if(data.rtnResult=="SUCCESS"){
				var code = data.rtnData.code;
				var integral = data.rtnData.integral;
				thisIntegral = integral;
				var descr = data.rtnData.descr;
				var coverImage = data.rtnData.coverImage;
				var showImage = data.rtnData.showImage;
				if(showImage){
					var imges = $.parseJSON(showImage);
					if(coverImage){
						imges.push(coverImage);
					}
				}
				$("#descr").html(descr);
				$("#p_code").html(code);
				$("#p_jf").html(integral);
				initImage(imges);
			}
		}
	});
}
function toSee(id){
	window.location.href = '<%=request.getContextPath()%>/integralStore/toProductDetail.action?id='+id;
}

function initImage(imgList){
	if(imgList.length>0){
		var html1 = '';
		var html2 = '';
		var imagesUl = $("#imagesUl");
		var tsImgS = $("#tsImgS");
		$.each(imgList,function(index,item){
			if(index==0){
				html1 +='<a href="'+item+'" title="Images" class="MagicZoom" id="MagicZoom">';
				html1 +='<img width="300" height="300" src="'+item+'" /></a>';
			}
			html2 += '<li onclick="showPic('+index+')"  rel="MagicZoom">';
			html2 += '<img height="42" width="42" src="'+item+'" tsImgS="'+item+'" /></li>';
		});
		
		tsImgS.html(html1);
		imagesUl.html(html2);
	}
}
/**
 * 进入我兑换的商品
 */
function toSeeMyProduct(){
	window.location.href = "<%=request.getContextPath()%>/integralStore/toMyIntegealExchange.action";
}
/**
 *兑换页面弹出
 */
function openDialog() {
	var emel = $("#dialog");
	dialog_one = dialog({
		width : 950,
		height : 600,
		title : '积分兑换',
		content : emel
	});
	dialog_one.showModal();
	getCurrentUserIntegral();
	initValidate();
}

function initProvince(){
	$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		url:"<%=request.getContextPath()%>/login/initCity.action",
   		success:function(data){
   			var re = "<option value=''>请选择</option>";
   			for(var i=0;i<data.length; i++){
   				if(data[i].parent_id == 0){
   					re += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
   				}
   			}
   			$("#province").html(re);
   	    }
   	});
}

function initCity(provinceId){
	$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		data:{
   			provinceId : provinceId
   		},
   		url:"<%=request.getContextPath()%>/login/initCity.action",
   		success:function(data){
   			var re = '<option value="">请选择</option>';
   			for(var i=0;i<data.length; i++){
   				re += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
   			}
   			$("#city").html(re);
   	    }
   	});
}

function initArea(cityId){
	$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		data:{
   			"cityId" : cityId
   		},
   		url:"<%=request.getContextPath()%>/integralStore/initArea.action",
   		success:function(data){
   			var re = '<option value="">请选择</option>';
   			for(var i=0;i<data.length; i++){
   				re += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
   			}
   			$("#area").html(re);
   	    }
   	});
}
/**
 * 兑换
 */
function doExchange(){
	
	var params = new Object;
	var quantity = $.trim($("#quantity").val());//数量
	var people = $.trim($("#people").val());//收货人姓名
	var phone = $.trim($("#phone").val());
	var postcode = $.trim($("#postcode").val());
	var province = $("#province").children("option:selected").text();
	var city = $("#city").children("option:selected").text();
	var area = $("#area").children("option:selected").text();
	var detailAddress = $("#detailAddress").val();
	var remark = $("#remark").val();
	var address = province +"-"+ city +"-"+ area +" "+ detailAddress;//总地址
	var totalSum = thisIntegral*quantity;//总积分
	deductIntegral = totalSum;
	
	params.commodityId = thisId;
	params.commodityCount = quantity;
	params.allIntegral = totalSum;
	params.descr = remark;
	params.userRealName = people;
	params.phoneNum = phone;
	params.address = address;
	params.postcode = postcode;
	$('#addForm').isValid(function(v){
	    if (v) {
	    	layer.confirm('确认兑换？',function(index){
	    		$.ajax({
		       		type:"POST",
		       		async:true,  //默认true,异步
		       		data:params,
		       		url:"<%=request.getContextPath()%>/integralStore/doExchange.action",
		    	   	error:function(){
		    	   		layer.alert("兑换失败！");
		    	   	},
		       		success:function(data){
		       			var emel = $("#resultD");
		       			dialog_two = dialog({
		       				width : 450,
		       				height : 100,
		       				title : '兑换成功',
		       				content : emel
		       			});
		       			$("#kcjf").html(deductIntegral);
		       			$("#syjf").html(Number(userTotolIntegral)-Number(deductIntegral));
		       			dialog_one.close();
		       			dialog_two.showModal();
		       	    }
		       	});
	    		layer.close(index);
	    	});
	    }
	});
}
/**
 * 表单验证
 */
function initValidate() {
	$('#addForm').validator({
		theme : 'yellow_right_effect',
		msgStyle:"margin-top: 10px;left: 10px;",
		rules : {
			checkPhone:[/^1[358]\d{9}$/,'手机号码格式不正确！'],
			checkPostCode:[ /^[1-9][0-9]{5}$/, '邮政编码格式不正确！' ],
			checkStock:function(element,param,field){
				var str;
				var urlStr = "<%=request.getContextPath()%>/integralStore/getById.action";
				$.ajax({
					type : "POST",
					url : urlStr,
					data : {
						"id" : thisId
					},
					success : function(data) {
						if(data.rtnResult=="SUCCESS"){
							var stock = data.rtnData.stock;
							var count = element.value;
							if(count>stock){
								str = "库存不足，最多可兑换"+stock+"个";
							}
						}
					}
				});
				return str;
			},
			checkIntegral:function(element,param,field){//检测当前用户是否积分不足
				var str;
				var count = element.value; 
				var totalValue = count*thisIntegral;//兑换当前数目所需总积分
				if(userTotolIntegral<totalValue){
					str = "当前积分不足";
				}
				return str;
			}
		},
		fields : {
			quantity : {
				rule : "required;range[1~];checkStock;checkIntegral",
				msg : {
					required : "不能为空",
					range : "数量必须为大于0的有效整数"
				}
			},
			people : {
				rule : "required;length[~30]",
				msg : {
					required : "不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			phone : {
				rule : "required;length[~11];checkPhone",
				msg : {
					required : "不能为空",
					length : "长度需小于等于11个字符"
				}
			},
			province : {
				rule : "required;",
				msgClass: "n-top",
				msg : {
					required : "不能为空"
				}
			},
			city : {
				rule : "required;",
				msgClass: "n-top",
				msg : {
					required : "不能为空"
				}
			},
			area : {
				rule : "required;",
				msgClass: "n-top",
				msg : {
					required : "不能为空"
				}
			},
			detailAddress : {
				rule : "required;length[~200]",
				msg : {
					required : "不能为空",
					length : "长度需小于等于200个字符"
				}
			},
			postcode : {
				rule : "required;checkPostCode",
				msg : {
					required : "不能为空"
				}
			}
		}
	});
}
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/MagicZoom/js/ShopShow.js"></script>
</html>