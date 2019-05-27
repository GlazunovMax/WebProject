<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/WelcomeTagDescription.tld" prefix="wtd" %>


<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div align="center" class="header" style="background: gray;">
					<h1><fmt:message key="car_base"/></h1>
	<a href="javascript:history.back()"><fmt:message key="go_back"/></a>
	<a href="Controller?command=logout"><fmt:message key="logout"/></a> 
	
	<fmt:message key="${Role}"/> <fmt:message key="welcome"/>
	<wtd:welcome name="${sessionScope.userName}" surname="${sessionScope.userSurname}"/>		
</div>

</body>
</html>