<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />
<c:set var="url">${pageContext.request.requestURI} </c:set> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Start page </title>

</head>
<body>
	<div class="localeLink">
		<a href="Controller?language=en&page=${url}&command=change_Locale ">
			<fmt:message key="English"/>
		</a>
	
	    <a href="Controller?language=ru&page=${url}&command=change_Locale ">
			<fmt:message key="Russian"/>
		</a>
	</div>
</body>
</html>