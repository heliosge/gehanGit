<%@ page contentType="image/jpeg" import="com.jftt.wifi.action.*"%>  
<%
  	  response.setHeader("Pragma","No-cache");  
      response.setHeader("Cache-Control","no-cache");  
      response.setDateHeader("Expires", 0);  
      ImageAction image = new ImageAction(response);  
      String rs = image.getCertPic(60,20);  
      session.setAttribute("imageString",rs);
      //加上下面代码,运行时才不会出现java.lang.IllegalStateException: getOutputStream() has already been called...等异常
      out.clear();
      out = pageContext.pushBody();
  %> 