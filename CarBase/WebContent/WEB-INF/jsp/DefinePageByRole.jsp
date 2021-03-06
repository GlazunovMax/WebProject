<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error/error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="role">${pageContext.request.requestURL} </c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!--Depending on the role redirect to the appropriate page  -->
	<c:if test="${requestScope.user.role eq 'CLIENT'}">
		<c:set var="role" value="CLIENT"/>
		<c:set var="id" value="${requestScope.user.id}"/>
		<c:redirect url="http://localhost:8080/CarBase/Controller?page=${role}&id=${id}&command=SELECT_PAGE"/>
	</c:if>
	
	<c:if test="${requestScope.user.role eq 'DRIVER'}">
		<c:set var="role" value="DRIVER"/>
		<c:set var="id" value="${requestScope.user.id}"/>
		<c:redirect url="http://localhost:8080/CarBase/Controller?page=${role}&id=${id}&command=SELECT_PAGE"/>
		<%-- <c:redirect url="Driver.jsp"/> --%>
	</c:if>
	
	<c:if test="${requestScope.user.role eq 'DISPATCHER'}">
		<c:set var="role" value="DISPATCHER"/>
		<c:set var="id" value="${requestScope.user.id}"/>
		<c:redirect url="http://localhost:8080/CarBase/Controller?page=${role}&id=${id}&command=SELECT_PAGE"/>
	</c:if>
</body>
</html>