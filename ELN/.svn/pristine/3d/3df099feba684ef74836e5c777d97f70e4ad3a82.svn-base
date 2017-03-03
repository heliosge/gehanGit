<%@ page contentType="image/jpeg" import="com.jftt.wifi.action.*"%>  
<%
  	  response.setHeader("Pragma","No-cache");  
      response.setHeader("Cache-Control","no-cache");  
      response.setDateHeader("Expires", 0);  
      ImageAction image = new ImageAction(response);  
      String rs = image.getCertPic(60,20);  
      session.setAttribute("imageString",rs);
      out.clear();
      out = pageContext.pushBody();
  %> 