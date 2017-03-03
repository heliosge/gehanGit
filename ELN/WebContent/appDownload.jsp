<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!-- saved from url=(0037)http://61.177.47.138:8088/santen.html -->
<html style="">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
    <style type="text/css" media="all">
      .hide {
        display: none;
      }
      .weixin-app-download-tooltip{
        width: 100%;
        position: fixed;
        top: 0;
        z-index: 12;
      }
      .weixin-app-download-mask{
        position: absolute;
        height: 100%;
        width: 100%;
        background-color: #000;
        opacity: .6;
        z-index: 10;
      }
      #btn-ios-5-download, #btn-ios-8-download {
        color: #36c8b5;
      }
    </style>
    <title>下载应用</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">



  </head>
  <body>
  <div id="weixin-app-download-mask" class="weixin-app-download-mask hide"></div>
  <div id="weixin-app-download" class="weixin-app-download-tooltip hide">
  </div>
  <div id="main">
    <div id="container">
      <div id="header">

        <p class="smartlion-name">
          </p>
          <h2>安培在线</h2>
        <p></p>
        <div class="download-area bottom text-right">
            <div id="android" class="download-btn">
              <a href="http://fir.im/r8hg?utm_source=fir&utm_medium=qr" id="android-btn" target="_blank">
                <span></span>安卓版 下载
              </a>
            </div>
            <div id="iPhone" class="download-btn">
              <a href="https://itunes.apple.com/cn/app/an-pei-zai-xian/id1095208595?mt=8" id="iphone-btn">
                <span></span>iPhone 下载
              </a>
            </div>
            <div id="iPad" class="download-btn">
              <a href="javascript:void (0)" id="iPad-btn">
                <span></span>暂无ipad版本
              </a>
            </div>
        </div>
      </div>
      <div style="width: 80%; margin: 20px auto 0px; padding: 30px; border: 1px solid rgb(153, 153, 153); color: rgb(76, 76, 76); border-radius: 20px;">微信用户请选择右上角菜单，选择“在浏览器中打开”！
      </div>
    <!--<div id="destription">-->
        <!--<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Santen移动学习平台是网络在线学习的延伸，可以支持iPhone、iPad、Android等多种系统和移动设备。</p>-->
<!--<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;通过移动学习平台，学员可以在线观看视频、文档等课件，可以随时参加考试、查看考试详情及错题集。</p>-->
        <!--<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下载APP，开始您的移动学习平台使用之旅吧！</p>-->
      <!--</div> -->
    </div>
    

  
  </div>
  <!--<footer>-->
    <!--<p>Copyright：上海睿泰信息科技有限公司</p>-->
  <!--</footer>-->
    <script type="text/javascript">
/*
* 智能机浏览器版本信息:
*
*/
  var browser={
    versions:function(){
           var u = navigator.userAgent, app = navigator.appVersion;
           return {//移动终端浏览器版本信息
                trident: u.indexOf('Trident') > -1, //IE内核
                presto: u.indexOf('Presto') > -1, //opera内核
                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
                mobile: !!u.match(/AppleWebKit.*Mobile.*/)||!!u.match(/AppleWebKit/), //是否为移动终端
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
                iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
                iPad: u.indexOf('iPad') > -1, //是否iPad
                webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
            };
         }(),
         language:(navigator.browserLanguage || navigator.language).toLowerCase()
};
     function getUrl(){
         $.ajax({
             type:"POST",
             async:true,  //默认true,异步
             data:{
             },
             url:"<%=request.getContextPath()%>/Mobile/VersionUpdate.action",
             success:function(data){
                 if(data){
                         $("#android-btn").attr("href",data.Path);
                 }else{

                 }
             }
         });
    }

  //alert("aa");
  var iPhone=document.getElementById("iPhone");
  var iPad=document.getElementById("iPad");
  var android=document.getElementById("android");
  var icon0=document.getElementById("icon0");
  var icon1=document.getElementById("icon1");
  var iPhone_slider=document.getElementById("iPhone-slider");
  var iPad_slider=document.getElementById("iPad-slider");
  var android_infos=document.getElementById("android-infos");
  var iPhone_infos=document.getElementById("iPhone-infos");
  var iPad_infos=document.getElementById("iPad-infos");

  if(browser.versions.iPad){
    iPhone.style.display="none";
    android.style.display="none";
    icon0.style.display="none";
    icon1.style.display="block";
    android_infos.style.display="none";
    iPhone_infos.style.display="none";
    iPhone_slider.innerHTML="";
  }else if(browser.versions.iPhone){
    iPad.style.display="none";
    android.style.display="none";
    android_infos.style.display="none";
    iPad_infos.style.display="none";
    iPad_slider.innerHTML="";
  }else if(browser.versions.android){
//      alert("弹出3");
      getUrl();
    iPad.style.display="none";
    iPhone.style.display="none";
    iPhone_infos.style.display="none";
    iPad_infos.style.display="none";
    iPad_slider.innerHTML="";
      //window.location="http://192.168.20.74:8080/ELN/111.apk"
  }
</script>

</body></html>