<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="registration"/></title>
	<!-- <link href="css/style.css" rel="stylesheet" type="text/css"> -->
	<style type="text/css"><%@include file="/css/style.css"%></style>
	
</head>
<body>
	<jsp:include page="LocalePage.jsp"/> 
	<a href="Controller?command=logout"><fmt:message key="logout"/></a>

	<form class="box" action="Controller" method="post">
		<input type="hidden" name="command" value="registration"/>
		<h3><fmt:message key="name"/></h3>
		<input type="text" class="name" name="name" value="" /> 
		
		<h3><fmt:message key="surname"/></h3>
		<input type="text" class="surname" name="surname" value="" />
		
		<h3><fmt:message key="login"/></h3>
		<input type="text" class="login" name="login" value="" />
		
		<h3><fmt:message key="password"/></h3>
		<input type="password" class="password" name="password" value="" /> 
		
		<h3><fmt:message key="phone"/></h3>
		<input type="text" class="phone" name="phone" value="" />
		
		<h3><fmt:message key="role"/></h3>
		<input type="text" class="role" name="role" readonly="readonly" value="client"/>
		<br>
		<input type="submit" class="submit" value="<fmt:message key="registration"/>"/> 
	</form>

	

	<!--ERROR in REGISTRATION-->
	<h2><!-- style="text-align: center; color: red;" -->
		<c:if test="${not empty requestScope.errorRegistration}">
			<%-- <c:out value="${requestScope.errorSingIn}" /> --%>
			<fmt:message key="${requestScope.errorRegistration}" /> 
		</c:if>
	</h2>

</body>
</html>