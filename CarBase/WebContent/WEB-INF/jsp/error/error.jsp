<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css"><%@include file="/css/style.css"%></style>
</head>
<body>
<div class="output-table-menu" style="color: red;">
	Request from ${pageContext.errorData.requestURI} is failed <br/>
	Servlet name: ${pageContext.errorData.servletName} <br/>
	Status code: ${pageContext.errorData.statusCode} <br/>
	Exception: ${pageContext.exception} <br/>
	Message from exception: ${pageContext.exception.message}
</div>

</body>
</html>