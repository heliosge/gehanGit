
$(document).ajaxComplete(function(event, xhr, settings){
	if(xhr.responseText && xhr.responseText == "SESSION_AGAIN"){
		//alert("SESSION_OUT");
		//ajax 访问 session 失效
		//alert("SESSION_AGAIN");
		window.location.href = "/ELN/ajaxSessionAgain.jsp";
	}else if(xhr.responseText && xhr.responseText == "SESSION_OUT"){
		//alert("SESSION_OUT");
		//ajax 访问 session 失效
		window.location.href = "/ELN";
	}
});