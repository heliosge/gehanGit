<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单详情</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall.css" />
<style type="text/css">
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
	.close{color: black;
    font-size: 15px;
    height: 10px;
    position: absolute;
    right: -5px;
    text-decoration: none;
    top: -45px;
    width: 20px;}
    
    .close:hover{color:white}
</style>
<script>
var id;
id=${id};
var approveList=[];

$(function() {
	if(id){
   		getApproveList();
   		
   	}else{
   		cancel();
   	}
	
});


//获取课程或者专题list
function getApproveList(){
	
	if(id){
		
		$.ajax({
			type:"POST",
			async:false,
			data:{
				id:id
			},
			url:"<%=request.getContextPath()%>/mall/manage/approve/getDetailList.action",
			success:function(data){
				if(data&&data.length>0){
					approveList =data;
					renderList();
				}else{
					cancel()
				}
			}
		});
		
	}else{
		cancel();
	}
}

//渲染课程
function renderList(){
	var htmlStr='';
	if(approveList&&approveList.length>0){
		$.each(approveList,function(index,item){
			if(item.status==0){
				item.statusName = "待审批";
			}else if(item.status==1){
				item.statusName = "审批通过";
			}else if(item.status==2){
				item.statusName = "审批拒绝";
			}
			htmlStr +='<tr>'+
	                      '<td>'+(index+1)+'</td>'+
	                      '<td >'+item.createTime+'</td>'+
	                      '<td style="width:180px">'+item.statusName+'</td>'+
	                      '<td >'+(item.reason||'无')+'</td>'+
	                   '</tr>';
		});
		var companyName = approveList[0].companyName;
		var name = approveList[0].name;
		var price =approveList[0].price;
		var cheapPrice =approveList[0].cheapPrice;
		var code =approveList[0].code;
		$('#companyName').text(companyName);
		$('#name').text(name);
		$('#code').text(code);
		$('#cheapPrice').text(cheapPrice);
		$('#price').text(price);
		var $commodityDiv = $('#approveTable');
		$commodityDiv.append(htmlStr);
		$commodityDiv.show();
	}else{
		cancel();
	}

}

	
function cancel(){
	history.go(-1);
	//window.location.href="<%=request.getContextPath()%>/mall/manage/toApproveListPage.action";
	}
	




</script>


</head>
<body>
<div class="content">
	<!-- <h3 id="top_page_name">审批详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span id="top_page_name" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">审批详情</span>
	</div>
	<div class="pay">        
        <h4 id="courseInfo">公司名称：<span id="companyName"> </span>课程名称：<span id="name"> </span>
               <!--  课程编号：<span id ="code"></span>-->
                       课程价格：<span id="price"></span>优惠后价格：<span id="cheapPrice"></span></h4>
        <table width="100%" class="tabl_1" id="approveTable">
        	<tr>
            	<td>序号</td>
                <td>提交时间</td>
                <td>审批状态</td>
                <td>拒绝理由</td>
            </tr>
        </table>
        
        <div class="btn_gr">
    	<input type="button" value="返回" class="btn_1" onclick="javascript:history.go(-1);" />
    	</div>
    </div>
</div> 
<script>
</script>
</body>
</html>
