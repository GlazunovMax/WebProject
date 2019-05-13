<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />
<c:set var="url">${pageContext.request.requestURI}index.jsp </c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<!-- <link href="css/style.css" rel="stylesheet" type="text/css"/> -->
	<style type="text/css"><%@include file="css/style.css"%></style>

</head>
<body>
 <jsp:include page="WEB-INF/jsp/fragment/LocalePage.jsp"/>  
	
	<h1 style="color: red"><fmt:message key="car_base"/></h1>
	<div class="buttons">
		
		<form method="POST" action="Controller">
			<input type="hidden" name="command" value="go_to_sing_in" />
		    <button class="but" type="submit"><fmt:message key="sing_in"/></button>
		</form>
		
		<form method="POST" action="Controller">
			<input type="hidden" name="command" value="go_to_registration" />
			<input type="hidden" name="roleRegistr" value="client" />
		    <button class="but" type="submit"><fmt:message key="registration"/></button>
		</form>
	
	</div>
</body>
</html>