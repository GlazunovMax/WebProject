<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />
<%-- <c:import url="WEB-INF/jsp/LocalePage.jsp" /> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="sing_in" /></title>
<!-- <link rel="stylesheet" type="text/css" href="css/style.css" /> -->
	<style type="text/css"><%@include file="/css/style.css"%></style>
</head>
<body>
	<jsp:include page="LocalePage.jsp" />
	<a href="Controller?command=logout"><fmt:message key="logout"/></a>

	<form class="box" action="Controller" method="post">
		<input type="hidden" name="command" value="sing_in" />

		<h3>
			<fmt:message key="login" />
		</h3>
		<input type="text" name="login" value="" /> <br>

		<h3>
			<fmt:message key="password" />
		</h3>
		<input type="password" name="password" value="" /> <br> 
		
		<input type="submit" value="<fmt:message key="sing_in"/>" />
	</form>

	<!--ERROR in SING IN  -->
	<h2><!-- style="text-align: center; color: red;" -->
		<c:if test="${not empty requestScope.errorSingIn}">
			<%-- <c:out value="${requestScope.errorSingIn}" /> --%>
			<fmt:message key="${requestScope.errorSingIn}" /> 
		</c:if>


		<c:if test="${not empty requestScope.errorData}">
			<%-- <c:out value="${requestScope.errorData}" /> --%>
			<fmt:message key="${requestScope.errorData}" /> 
		</c:if>
	</h2>

</body>
</html>