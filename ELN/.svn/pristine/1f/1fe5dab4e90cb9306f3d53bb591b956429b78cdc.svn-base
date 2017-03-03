<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的问问</title>
<!-- jQuery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>

<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/ztree_diy.css"/>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- dateUtil -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**整体框架*/
#myAskWholeFrame{width: 1066px;margin: 16px auto 0px;padding-bottom: 200px;clear: both;overflow: hidden;}

/**标题*/
#myAskWholeFrame > h3{
width: 1052px;padding-left: 20px;color: #3A3A3A;border-bottom: 1px solid #CCC;padding-bottom: 10px;
margin-bottom: 10px;font-size:14px;}

/**左边div*/
.leftMyAsk{width: 240px;float: left;padding-top: 20px;font-size: 12px;}

/**用户头像*/
#userInfoDiv{width:240px;height:50px;}

#userInfoDiv > img{width:50px;height:50px;float:left;}

#userName{font-size: 14px;float: left;padding-left: 10px;padding-top: 10px;}

/**提问和我的回答*/
#askAndAnswerCount{padding-top:18px;height:62px;}

#leftAskDiv{width: 116px;height: 60px;border: 1px solid #cccccc;
background: #EAEAEA none repeat scroll 0% 0%;float: left;cursor: pointer;}

#leftAskDiv > p{line-height: 30px;text-align: center;height: 30px;
font-size: 14px;font-weight: bold;color: #666565;margin: 0px;padding: 0px;}

#leftAnswerDiv{width: 116px;height: 60px;border: 1px solid #cccccc;
background: #EAEAEA none repeat scroll 0% 0%;float: left;margin-left:2px;cursor: pointer;}

#leftAnswerDiv > p{line-height: 30px;text-align: center;height: 30px;
font-size: 14px;font-weight: bold;color: #666565;margin: 0px;padding: 0px;}

/**所有问题分类*/
#allTypes{text-align: center;/* overflow-y: scroll;overflow-x: hidden; */
width: 240px;height: 550px;background: #EAEAEA none repeat scroll 0% 0%;
font-family: "微软雅黑";font-size: 12px;margin-top:18px;}

#allTypes > h4{width: 240px;height: 40px;background: #2C2C2C none repeat scroll 0% 0%;
text-align: center;line-height: 40px;color: #FFF;font-size: 14px;margin: 0px;
padding: 0px;font-family: "微软雅黑";}

.typeOneStyle{padding: 10px 0px 10px 10px;width: 230px;border-bottom: 1px dotted #CCC;
text-align: center;font-family: "微软雅黑";font-size: 12px;margin: 0px;padding: 0px;}

.emStyle{font-style: normal;color: #FF8800;}

.typeOneStyle > h5 > a{color: #000;font-weight: bold;font-size: 14px;text-decoration: none;cursor: pointer;}

.typeOneStyle > p{line-height: 1.5;}

.typeOneStyle > p > a{color: #545454;text-decoration: none;cursor: pointer;}

/**右边div*/
.rightMyAsk{width: 805px;margin-left: 20px;float: left;padding-top: 19px;font-size: 12px;}

/**搜索框div*/
#searchContentDiv{width: 783px;height: 80px;background: #EAEAEA none repeat scroll 0% 0%;
padding-top: 22px;padding-left: 20px;margin-bottom: 20px;border:1px solid #cccccc;}

#upSearchDiv{width:783px;height:40px;}

#wenwen{font-style: normal;font-size: 18px;font-weight: bold;color:#2C2626;margin-left: 10px;}

#searchInput{width: 300px;height: 26px;margin-left: 20px;}

#searchButton{width: 90px;height: 32px;background-color: #0091DE;
color: rgb(255, 255, 255);border: medium none;cursor: pointer;}

#answerButton{width: 90px;height: 32px;background-color: #DD0500;
color: rgb(255, 255, 255);border: medium none;cursor: pointer;}

#askButton{width: 90px;height: 32px;background-color: #0091DE;
color: rgb(255, 255, 255);border: medium none;cursor: pointer;}

#downSearchDiv{width: 713px;height: 40px;margin-left: 70px;font-size: 14px;}

#toDealCount{font-style: normal;color: #DD0500;}

#dealedCount{font-style: normal;color: #DD0500;}

/**问问展现内容div*/
#frontAskDiv{width: 805px;height: 200px;background: #EAEAEA none repeat scroll 0% 0%;}

.topFrontAskDiv{width: 805px;height: 165px;}

#bottomFrontAskDiv{width: 805px;height: 35px;background-color: rgba(99, 103, 105, 0.43);}

.askPicStyle{width: 268px;height: 165px;float: left; cursor: pointer;}

.picRightDiv{width: 537px;height: 165px;float: left;}

.picRightH3{margin-left: 20px;margin-right: 20px;width: 600px;
text-overflow:ellipsis;white-space:nowrap;overflow:hidden;}

.picRightP{margin-left: 20px;margin-right: 20px;width: 600px;height:50px;
overflow:hidden;word-wrap:break-word;}

#bestAnswer1{font-style: normal;color: #0091DE;}
#bestAnswer2{font-style: normal;color: #0091DE;}
#bestAnswer3{font-style: normal;color: #0091DE;}

.picRightBottom{margin-left: 20px;margin-right: 20px;width: 600px;color: #848484;}

#picRightDetail1{font-style: normal;padding-left: 0px;color: #0091DE;cursor: pointer;}
#picRightDetail2{font-style: normal;padding-left: 0px;color: #0091DE;cursor: pointer;}
#picRightDetail3{font-style: normal;padding-left: 0px;color: #0091DE;cursor: pointer;}

.beforeClick{width: 268px;height: 30px;text-align: center;color: rgb(255, 255, 255);
font-size: 14px;padding-top: 5px;float: left;cursor:pointer;
text-overflow:ellipsis;white-space:nowrap;overflow:hidden;}

.afterClick{width: 268px;height: 30px;text-align: center;color: rgb(255, 255, 255);
font-size: 14px;padding-top: 5px;float: left;cursor:pointer;background-color:rgba(13, 27, 35, 0.24);
text-overflow:ellipsis;white-space:nowrap;overflow:hidden;}

#askTops{width:805px;height:165px;}

/**列表展现*/
#listDiv{padding-top: 20px;width: 805px;}

#listTitle{width: 805px;height: 32px;padding-top: 10px;border-bottom: 1px solid;}

.listTab{width: 120px;height: 32px;margin-top: 2px;text-align: center;font-size: 14px;float: left;
cursor: pointer;}

.listTab2{width: 100px;height: 32px;margin-top: 2px;text-align: center;font-size: 14px;float: left;
cursor: pointer;}

.activeTab{width: 120px;height: 32px;margin-top: 2px;text-align: center;font-size: 14px;float: left;
cursor: pointer;color: #0091DE;}

.activeTab2{width: 100px;height: 32px;margin-top: 2px;text-align: center;font-size: 14px;float: left;
cursor: pointer;color: #0091DE;}

.askListStyle{padding-top: 20px;}

/**链接属性*/
a{text-decoration: none;cursor:pointer;}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var userName = '${USER_BEAN.name}';
var userPic = '${USER_BEAN.headPhoto}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var askShowType = 1;//mmGrid展现问题类型（1：待解决的问题，2：已解决的问题，3：我的提问，4：我的回答）
var zTree = null;

/**
 * 页面加载完成
 */
$(function(){
	//初始化用户信息
	initUserInfo();
	//初始化提问数量
	initAskCount();
	//初始化我的回答数量
	initMyReplyCount();
	//初始化所有问题分类
	reloadZTree();
	//初始化待解决的问题个数
	initToDealAskCount();
	//初始化已解决的问题个数
	initDealedAskCount();
	//初始化专题问答
	initThematicAsk();
	//初始化问答列表
	initAsksForMMGrid();
});


function reloadZTree() {
    //zTree配置
    var setting = {
        data: {
            simpleData: {
                enable: true,
                pIdKey: "parentId",
                idKey: "id",
                rootPid: null
            },
        },
        view: {
            showTitle: true,
            selectedMulti: false,
			addDiyDom: addDiyDom
        },
        callback: {
            //onRightClick: zTreeOnRightClick,
            onClick: zTreeOnClick
        }
    };

    $.ajax({
        type:"POST",
        //async:false,  //默认true,异步
        data:null,
        url:'<%=request.getContextPath()%>/myAsk/getRootTypes.action',
        success:function(categoryTree){
            zTree = $.fn.zTree.init($("#categoryTree"), setting, categoryTree);
            //全部展开
            zTree.expandAll(true);
            
            //默认选中第一个数节点
            //zTree.checkNode(zTree.getNodeIndex(0), true, true);
        }
    });
}

//树的图标
function addIconInfo(categoryTree) {
    for (var idx in categoryTree) {
        categoryTree[idx]['icon'] = '<c:url value="/images/img/ico_txt.png" />';
        categoryTree[idx]['iconOpen'] = '<c:url value="/images/img/ico_sq.png" />';
        categoryTree[idx]['iconClose'] = '<c:url value="/images/img/ico_zk.png" />';
    }
}

//获取树的被选中节点
function getSelectedZTreeNode() {
    var nodes = zTree.getSelectedNodes();
    if (nodes && nodes.length>0) {
        return nodes[0];
    }
    return null;
}

//数的左击
function zTreeOnClick(event, treeId, treeNode) {
    selectCategory(treeNode);
}

//树的左击
function selectCategory(category) {
	//表格查询重置 
    //clearSearchOptions();
	// 重新查询表格
    //reloadmmGrid({'categoryId': category['id']}, 1);
	toAskTypeDetail(category.id);
}
/**
 * 初始化用户信息
 */
function initUserInfo(){
	$("#userName").html(userName);
	$("#userPic").attr("src",userPic);
}

/**
 * 初始化提问数量
 */
function initAskCount(){
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:{"askerId":userId},
		url:'<%=request.getContextPath()%>/myAsk/getAskCountByAskerId.action',
		success:function(data){
			$("#askCount").html(data);
		}
	});
}

/**
 * 初始化我的回答数量
 */
function initMyReplyCount(){
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:{"replyerId":userId},
		url:'<%=request.getContextPath()%>/myAsk/getAnswerCountByReplyerId.action',
		success:function(data){
			$("#replyCount").html(data);
		}
	});
}

/**
 * 初始化所有问题分类
 */
function initTypes(){
	$.ajax({
		type:'POST',
		async:true,//默认异步
		url:'<%=request.getContextPath()%>/myAsk/getRootTypes.action',
		success:function(data){
			if(data != null && data.length > 0){
				var htmlStr = '';
				for(var i = 0; i < data.length; i++){
					var rootType = data[i];
					htmlStr += '<div class="typeOneStyle">';
					htmlStr += '<h5>';
					htmlStr += '<a href="javascript:void(0);" onclick="toAskTypeDetail('+rootType.id+');">';
					htmlStr += rootType.name+'&nbsp;&nbsp;';
					htmlStr += '<em class="emStyle">';
					htmlStr += rootType.askCount;
					htmlStr += '</em>';
					htmlStr += '</a>';
					htmlStr += '</h5>';
					htmlStr += '</div>';
					
					/* <div class="typeOneStyle">
						<h5><a href="javascript:void(0);">市场营销市场&nbsp;&nbsp;<em class="emStyle">5</em></a></h5>
					</div> */
				}
				$("#rootTypes").html(htmlStr);
			}
		}
	});
}

/**
 * 跳转到分类详情页面
 */
function toAskTypeDetail(typeId){
	window.location.href='<%=request.getContextPath()%>/myAsk/toAskTypeDetail.action?typeId='+typeId;
}

/**
 * 初始化待解决的问题数量
 */
function initToDealAskCount(){
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:{"companyId":companyId,"subCompanyId":subCompanyId},
		url:'<%=request.getContextPath()%>/myAsk/getToDealAskCount.action',
		success:function(data){
			$("#toDealCount").html(data);
		}
	});
}

/**
 * 初始化已解决的问题个数
 */
function initDealedAskCount(){
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:{"companyId":companyId,"subCompanyId":subCompanyId},
		url:'<%=request.getContextPath()%>/myAsk/getDealedAskCount.action',
		success:function(data){
			$("#dealedCount").html(data);
		}
	});
}

/**
 * 初始化专题问答
 */
function initThematicAsk(){
	//获取专题问答管理员用户id列表
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:{"limitsUrl":"manageThematicAsk/toManageThematicAsk.action","companyId":companyId,"subCompanyId":subCompanyId},
		url:'<%=request.getContextPath()%>/myAsk/getManageThematicAskRoles.action',
		success:function(data){
			if(data != null && data.length > 0){
				var isManager = false;//判断该用户是否管理员
				$.each(data,function(index,val){
					if(val == userId){
						isManager = true;
					}
				});
				//如果是，展现相应的专题，如果不是，将整个专题模块隐藏
				if(isManager){
					$.ajax({
						type:'POST',
						async:false,//此处为同步
						url:'<%=request.getContextPath()%>/myAsk/getThematicAskList.action',
						success:function(data){
							if(data != null && data.length > 0){
								var topStr = '';
								var bottomStr = '';
								for(var i = 0; i < data.length; i++){
									var thematicAsk = data[i];
									if(i == 0){
										topStr += '<div id="thematicAskDiv'+(i+1)+'" class="topFrontAskDiv">';
									}else{
										topStr += '<div id="thematicAskDiv'+(i+1)+'" class="topFrontAskDiv" style="display: none;">';
									}
									if(thematicAsk.coverPic){
										topStr += '<img title="'+thematicAsk.title+'" src="'+thematicAsk.coverPic+'" class="askPicStyle" onclick="toThematicAskDetail('+thematicAsk.id+');">';
									}else{
										topStr += '<img title="'+thematicAsk.title+'" src="" class="askPicStyle" onclick="toThematicAskDetail('+thematicAsk.id+');">';
									}
									topStr += '<div class="picRightDiv">';
									topStr += '<h3 title="'+thematicAsk.title+'" class="picRightH3">'+thematicAsk.title+'</h3>';
									if(thematicAsk.satisfactoryAnswer){
										topStr += '<p class="picRightP" title="'+thematicAsk.satisfactoryAnswer+'">';
										topStr += '<em id="bestAnswer'+(i+1)+'">最佳答案：</em>';
										topStr += thematicAsk.satisfactoryAnswer;
									}else{
										topStr += '<p class="picRightP" title="暂无">';
										topStr += '<em id="bestAnswer'+(i+1)+'">最佳答案：</em>';
										topStr += '暂无';
									}
									topStr += '</p>';
									topStr += '<div class="picRightBottom">';
									if(thematicAsk.replyerName){
										topStr += ''+thematicAsk.replyerName+'&nbsp;';
									}
									if(thematicAsk.replyTime){
										topStr += '回答于&nbsp;'+getSmpFormatDateByLong(thematicAsk.replyTime)+'&nbsp;';
									}
									topStr += '<em id="picRightDetail'+(i+1)+'" onclick="toThematicAskDetail('+thematicAsk.id+');">详情</em>';
									topStr += '</div>';
									topStr += '</div>';
									topStr += '</div>';
									
									/* <div id="thematicAskDiv1" class="topFrontAskDiv">
										<img title="空气是火山喷发的产物？" src="" class="askPicStyle">
										<div class="picRightDiv">
											<h3 title="空气是火山喷发的产物？" class="picRightH3">空气是火山喷发的产物？</h3>
											<p class="picRightP" title="研究结果表明，哪些我们肉眼可以看见的喷出的液滴并不是传递疾病的罪魁祸首，真正的传染源是哪些不容易被察觉的小液滴。">
												<em id="bestAnswer1">最佳答案：</em>研究结果表明，哪些我们肉眼可以看见的喷出的液滴并不是传递疾病的罪魁祸首，真正的
												传染源是哪些不容易被察觉的小液滴。
											</p>
											<div class="picRightBottom">
												艾欢&nbsp;回答于&nbsp;2014-11-03 11:10:05 
												<em id="picRightDetail1">详情</em>
											</div>
										</div>
									</div> */
									
									if(i == 0){
										bottomStr += '<div id="askBottom'+(i+1)+'" class="afterClick" title="'+thematicAsk.title+'" onclick="showThematicAsk'+(i+1)+'();">';
									}else{
										bottomStr += '<div id="askBottom'+(i+1)+'" class="beforeClick" title="'+thematicAsk.title+'" onclick="showThematicAsk'+(i+1)+'();">';
									}
									bottomStr += thematicAsk.title;
									bottomStr += '</div>';
									
									/* <div id="askBottom1" class="afterClick" title="空气是火山喷发的产物？" onclick="showThematicAsk1();">空气是火山喷发的产物？</div> */
								}
								$("#askTops").html(topStr);
								$("#bottomFrontAskDiv").html(bottomStr);
							}
						}
					});
				}else{
					//该用户不是专题问答管理员，隐藏专题问答模块
					$("#frontAskDiv").hide();
				}
			}else{
				//没有专题问答管理员，隐藏专题问答模块
				$("#frontAskDiv").hide();
			}
		}
	});
}

/**
 * 跳转到专题问答详情页面
 */
function toThematicAskDetail(askId){
	window.location.href = '<%=request.getContextPath()%>/myAsk/toThematicAskDetail.action?askId='+askId;
}

/**
 * 展现第一个专题问答
 */
function showThematicAsk1(){
	$("#askBottom1").attr("class","afterClick");
	$("#askBottom2").attr("class","beforeClick");
	$("#askBottom3").attr("class","beforeClick");
	$("#thematicAskDiv1").show();
	$("#thematicAskDiv2").hide();
	$("#thematicAskDiv3").hide();
}

/**
 * 展现第二个专题问答
 */
function showThematicAsk2(){
	$("#askBottom1").attr("class","beforeClick");
	$("#askBottom2").attr("class","afterClick");
	$("#askBottom3").attr("class","beforeClick");
	$("#thematicAskDiv1").hide();
	$("#thematicAskDiv2").show();
	$("#thematicAskDiv3").hide();
}

/**
 * 展现第三个专题问答
 */
function showThematicAsk3(){
	$("#askBottom1").attr("class","beforeClick");
	$("#askBottom2").attr("class","beforeClick");
	$("#askBottom3").attr("class","afterClick");
	$("#thematicAskDiv1").hide();
	$("#thematicAskDiv2").hide();
	$("#thematicAskDiv3").show();
}

/**
 * 初始化问答列表
 */
function initAsksForMMGrid(){
	//设置参数
	var param = {};
	param.askShowType = askShowType;
	param.userId = userId;
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	var nowDate = new Date();
	//mmGrid展现
	$("#askListTable").mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : '<%=request.getContextPath()%>/myAsk/getAsksForMMGrid.action',
		width : '805px',
		height : 'auto',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : false,
		multiSelect : true,
		indexCol : false, // 索引列
		params : param,
		cols:[{
			title:'id',
			name:'id',
			width:'0px',
			hidden:true
		},{
			title:'问题标题',
			name:'title',
			align:'center',
			width:400,
			lockWidth:true,
			renderer:function(val,item,rowIndex){
				var str = '';
				if(item.effectiveTime == null || item.effectiveTime >= nowDate){
					str = '<a href="javascript:void(0)" onclick="toAskDetail('+item.id+');">'+val+'</a>';
				}else{
					str = '<a href="javascript:void(0)" style="color:gray;">'+val+'</a>';
				}
				return str;
			}
		},{
			title:'提问时间',
			name:'createTime',
			align:'center',
			renderer:function(val,item,rowIndex){
				return getSmpFormatDateByLong(val);
			}
		},{
			title:'问题截止有效期',
			name:'effectiveTime',
			align:'center',
			renderer:function(val,item,rowIndex){
				if(val){
					return getSmpFormatDateByLong(val);
				}else{
					return null;
				}
			}
		},{
			title:'回答数量',
			name:'answerCount',
			align:'center'
		}],
		plugins : [$('#askListPager').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean total
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [10,20,50,100]
		})]
	});
}

/**
 * 跳转到问题详情页面
 */
function toAskDetail(askId){
	window.location.href = '<%=request.getContextPath()%>/myAsk/toAskDetail.action?askId='+askId;
}

/**
 * 展现待处理的问题
 */
function showToDeal(){
	//tab切换样式
	$("#toDealTab").attr("class","activeTab");
	$("#dealedTab").attr("class","listTab");
	$("#myAskTab").attr("class","listTab2");
	$("#myReplyTab").attr("class","listTab2");
	//mmGrid展现
	askShowType = 1;
	var searchParam = {};
	searchParam.askShowType = askShowType;
	searchParam.userId = userId;
	$("#askListTable").mmGrid().load(searchParam);
}

/**
 * 展现已处理的问题
 */
function showDealed(){
	//tab切换样式
	$("#toDealTab").attr("class","listTab");
	$("#dealedTab").attr("class","activeTab");
	$("#myAskTab").attr("class","listTab2");
	$("#myReplyTab").attr("class","listTab2");
	//mmGrid展现
	askShowType = 2;
	var searchParam = {};
	searchParam.askShowType = askShowType;
	searchParam.userId = userId;
	$("#askListTable").mmGrid().load(searchParam);
}

/**
 * 展现我的提问
 */
function showMyAsk(){
	//tab切换样式
	$("#toDealTab").attr("class","listTab");
	$("#dealedTab").attr("class","listTab");
	$("#myAskTab").attr("class","activeTab2");
	$("#myReplyTab").attr("class","listTab2");
	//mmGrid展现
	askShowType = 3;
	var searchParam = {};
	searchParam.askShowType = askShowType;
	searchParam.userId = userId;
	$("#askListTable").mmGrid().load(searchParam);	
}

/**
 * 展现我的回答
 */
function showMyReply(){
	//tab切换样式
	$("#toDealTab").attr("class","listTab");
	$("#dealedTab").attr("class","listTab");
	$("#myAskTab").attr("class","listTab2");
	$("#myReplyTab").attr("class","activeTab2");
	//mmGrid展现
	askShowType = 4;
	var searchParam = {};
	searchParam.askShowType = askShowType;
	searchParam.userId = userId;
	$("#askListTable").mmGrid().load(searchParam);		
}

/*我要提问   */
function toAsk(){
	window.location = "<%=request.getContextPath()%>/myAsk/toAsk.action";
}

/**
 * 跳转到我的提问页面
 */
function toMyAsk(){
	window.location.href = '<%=request.getContextPath()%>/myAsk/toMyQuestion.action?showType=1';
}

/**
 * 跳转到我的回答页面
 */
function toMyAnswer(){
	window.location.href = '<%=request.getContextPath()%>/myAsk/toMyQuestion.action?showType=2';
}

/**
 * 搜索问题
 */
function searchAsks(){
	var searchInput = $("#searchInput").val();
	window.location.href = '<%=request.getContextPath()%>/myAsk/toSearchAsks.action?searchInput='+encodeURI(encodeURI(searchInput));
}

</script>

</head>
<body>
	<div id="myAskWholeFrame">
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的问问</span>
		</div>
		<!-- <h3 >我的问问</h3> -->
		<!-- 左边div -->
		<div class="leftMyAsk">
			<!-- 用户头像 -->
			<div id="userInfoDiv">
				<img id="userPic" src=""/>
				<div id="userName"></div>
			</div>
			<!-- 提问和我的回答 -->
			<div id="askAndAnswerCount">
				<div id="leftAskDiv" onclick="toMyAsk();">
					<p id="askCount"></p>
					<p>提问</p>
				</div>
				<div id="leftAnswerDiv" onclick="toMyAnswer();">
					<p id="replyCount"></p>
					<p>我的回答</p>
				</div>
			</div>
			<!-- 所有问题分类 -->
			<div id="allTypes">
				<h4>所有问题分类</h4>
				<!-- <div id="rootTypes"> -->
					<!-- <div class="typeOneStyle">
						<h5><a href="javascript:void(0);">市场营销市场&nbsp;&nbsp;<em class="emStyle">5</em></a></h5>
						<p>
							<a>营销策略36计&nbsp;&nbsp;<em class="emStyle">1</em></a>
							<br/>
							<a>营销艺术&nbsp;&nbsp;<em class="emStyle">2</em></a>
							<br/>
							<a>把握客户心理&nbsp;&nbsp;<em class="emStyle">2</em></a>
							<br/>
						</p>
					</div> -->
					<!-- <div class="typeOneStyle">
						<h5><a href="javascript:void(0);">儿童动漫&nbsp;&nbsp;<em class="emStyle">3</em></a></h5>
						<p>
							<a>海贼王&nbsp;&nbsp;<em class="emStyle">0</em></a>
							<br/>
							<a>七龙珠&nbsp;&nbsp;<em class="emStyle">3</em></a>
							<br/>
						</p>
					</div> -->
					 <ul id="categoryTree" class="ztree" >
        			</ul>
				<!-- </div> -->
			</div>
		</div>
		<!-- 右边div -->
		<div class="rightMyAsk">
			<!-- 搜索框 -->
			<div id="searchContentDiv">
				<div id="upSearchDiv">
					<em id="wenwen">问问</em>
					<input type="text" id="searchInput"/>
					<input type="button" value="搜索问题" id="searchButton" onclick="searchAsks();"/>
					<input type="button" value="我要回答" id="answerButton" onclick="toMyAnswer();"/>
					<input type="button" value="我要提问" id="askButton" onclick="toAsk();"/>
				</div>
				<div id="downSearchDiv">
					待解决的问题：<em id="toDealCount"></em>个&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					已解决的问题：<em id="dealedCount"></em>个
				</div>
			</div>
			<!-- 问问展现内容 -->
			<div id="frontAskDiv">
				<div id="askTops">
					<!-- <div id="thematicAskDiv1" class="topFrontAskDiv">
						<img title="空气是火山喷发的产物？" src="" class="askPicStyle">
						<div class="picRightDiv">
							<h3 title="空气是火山喷发的产物？" class="picRightH3">空气是火山喷发的产物？</h3>
							<p class="picRightP" title="研究结果表明，哪些我们肉眼可以看见的喷出的液滴并不是传递疾病的罪魁祸首，真正的传染源是哪些不容易被察觉的小液滴。">
								<em id="bestAnswer1">最佳答案：</em>研究结果表明，哪些我们肉眼可以看见的喷出的液滴并不是传递疾病的罪魁祸首，真正的
								传染源是哪些不容易被察觉的小液滴。
							</p>
							<div class="picRightBottom">
								艾欢&nbsp;回答于&nbsp;2014-11-03 11:10:05 
								<em id="picRightDetail1">详情</em>
							</div>
						</div>
					</div> -->
					<!-- <div id="thematicAskDiv2" class="topFrontAskDiv" style="display: none;">
						<img title="扔纸飞机前为什么先哈口气？" src="" class="askPicStyle">
						<div class="picRightDiv">
							<h3 title="扔纸飞机前为什么先哈口气？" class="picRightH3">扔纸飞机前为什么先哈口气？</h3>
							<p class="picRightP" title="研究结果表明，哪些我们肉眼可以看见的喷出的液滴并不是传递疾病的罪魁祸首，真正的传染源是哪些不容易被察觉的小液滴。">
								<em id="bestAnswer2">最佳答案：</em>研究结果表明，哪些我们肉眼可以看见的喷出的液滴并不是传递疾病的罪魁祸首，真正的
								传染源是哪些不容易被察觉的小液滴。
							</p>
							<div class="picRightBottom">
								艾欢&nbsp;回答于&nbsp;2014-11-03 11:10:05 
								<em id="picRightDetail2">详情</em>
							</div>
						</div>
					</div> -->
					<!-- <div id="thematicAskDiv3" class="topFrontAskDiv" style="display: none;">
						<img title="咳嗽和喷嚏 飞沫到底能飞多远？" src="" class="askPicStyle">
						<div class="picRightDiv">
							<h3 title="咳嗽和飞沫到底能飞多远？" class="picRightH3">咳嗽和飞沫到底能飞多远？</h3>
							<p class="picRightP" title="研究结果表明，哪些我们肉眼可以看见的喷出的液滴并不是传递疾病的罪魁祸首，真正的传染源是哪些不容易被察觉的小液滴。">
								<em id="bestAnswer3">最佳答案：</em>研究结果表明，哪些我们肉眼可以看见的喷出的液滴并不是传递疾病的罪魁祸首，真正的
								传染源是哪些不容易被察觉的小液滴。
							</p>
							<div class="picRightBottom">
								艾欢&nbsp;回答于&nbsp;2014-11-03 11:10:05 
								<em id="picRightDetail3">详情</em>
							</div>
						</div>
					</div> -->
				</div>
				<div id="bottomFrontAskDiv">
					<!-- <div id="askBottom1" class="afterClick" title="空气是火山喷发的产物？" onclick="showThematicAsk1();">空气是火山喷发的产物？</div>
					<div id="askBottom2" class="beforeClick" title="扔纸飞机前为什么先哈口气？" onclick="showThematicAsk2();">扔纸飞机前为什么先哈口气？</div>
					<div id="askBottom3" class="beforeClick" title="咳嗽和飞沫到底能飞多远？" onclick="showThematicAsk3();">咳嗽和飞沫到底能飞多远？</div> -->
				</div>
			</div>
			<!-- 问题列表div -->
			<div id="listDiv">
				<!-- 标题部分 -->
				<div id="listTitle">
					<div id="toDealTab" class="activeTab" onclick="showToDeal();">O 待解决的问题</div>
					<div id="dealedTab" class="listTab" onclick="showDealed();">O 已解决的问题</div>
					<div id="myAskTab" class="listTab2" onclick="showMyAsk();">O 我的提问</div>
					<div id="myReplyTab" class="listTab2" onclick="showMyReply();">O 我的回答</div>
				</div>
				<!-- 待解决的问题列表 -->
				<div id="askList" class="askListStyle">
					<table id="askListTable"></table>
					<div id="askListPager" style="float:right;"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>