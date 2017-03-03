<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>积分商品详情</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
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
var commodity={};
var id;
commodity= ${commodity};

var showImageCount=0;

$(function() {
	if(commodity&&commodity.id){
   		
   		id=commodity.id;
   		//回填值
   		$("#categoryName").text(commodity.categoryName);
   		$("#name").text(commodity.name);
   		$("#code").text(commodity.code);
   		$("#integral").text(commodity.integral);
   		$("#stock").text(commodity.stock);
   		$("#descr").html(commodity.descr);
   		$("#previewPath").attr("src",commodity.coverImage);
   		if(commodity.status==1){
   			$("#status").text("上架");
   			$("#putawayTime").text(commodity.putawayTime);
   			$("#putawayTimeDiv").show();
   		}else{
   			$("#status").text("下架");
   		}
   	    var showImageList = JSON.parse(commodity.showImage);
   	    if(showImageList&&showImageList.length>0){
   	    	for(var i = 0;i<showImageList.length;i++){
   	    		addShowImageSpan(showImageList[i]);
   	    	}
   	    }
   	}else{
   		history.go(-1);
   		//window.location.href="<%=request.getContextPath()%>/integral/commodity/manage/toCommodityListPage.action";
   	}
	
});

function addShowImageSpan(url){
	var htmlStr='<span class="uploadFileInfo" style="position:relative">'+
    '<img style="vertical-align: middle; max-width:80px; height: 80px;" src="'+url+'">'+

    '</span> ';
    
   $("#show_image_div").append(htmlStr);
   showImageCount++;
   return true;
}








	
function cancel(){
	history.go(-1);
	//window.location.href="<%=request.getContextPath()%>/integral/commodity/manage/toCommodityListPage.action";
	}
	




</script>


</head>
<body>
<div class="content">
	<!-- <h3 id="top_page_name">积分商品详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">积分商品详情</span>
	</div>
	<form id='addForm'>
	<div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl">
            	
                <em>商品分类：</em>
            </div>
            <div class="add_fr" id="categoryName">
            	
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	
                <em>商品编号：</em>
            </div>
            <div class="add_fr" id="code" >
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>商品名称：</em>
            </div>
             <div class="add_fr" id="name">
            </div>
    	</div>

      <div class="add_gr">
        	<div class="add_fl">
                <em>兑换积分：</em>
            </div>
             <div class="add_fr" id="integral">
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>库存：</em>
            </div>
             <div class="add_fr" id="stock" >
            	
            </div>
    	</div>
    
    	<div style="height:40px;width: 1066px;float: left;">
        	<div style="width: 170px;float: left;text-align: right;height:40px;">
                <em>封面图片：</em>
            </div>
         
        </div>
        <div class="add_gr"  style="height:auto;">
        	<div class="add_fl">
                &nbsp;
            </div>
            <div class="add_fr">
					<p class="tp">
						<img style="width:200px;height:200px" id="previewPath" src="<%=request.getContextPath()%>/images/img/left_2.png" />
					</p>
            </div>
        </div>
        
        
        	<div style="height:40px;width: 1066px;float: left;padding-top:5px">
        	<div style="width: 170px;float: left;text-align: right;height:40px;">
                <em>展示图片：</em>
            </div>
          
        </div>
      
       <div class="add_gr"  style="height:auto;padding-bottom:5px">
        	<div class="add_fl">
                &nbsp;
            </div>
            <div id="show_image_div" class="add_fr">
				
            </div>
        </div>  
 
    
  
        <div class="add_gr_1">
        	<div class="add_fl">
                <em>商品详情：</em>
            </div>
            <div class="add_fr" id="descr">
            </div>
        </div>
          <div class="add_gr">
        	<div class="add_fl">
                <em>状态：</em>
            </div>
             <div class="add_fr" id="status" >
            	
            </div>
    	</div>
    	  <div id="putawayTimeDiv" class="add_gr" style="display:none">
        	<div class="add_fl">
                <em>上架时间：</em>
            </div>
             <div class="add_fr" id="putawayTime" >
            	
            </div>
    	</div>
        <div class="button_cz">
        	
            <input type="button" value="返回" class="back" onclick="cancel()"/>
        </div>
    </div>
    </form>
</div> 
<script>
</script>
</body>
</html>
