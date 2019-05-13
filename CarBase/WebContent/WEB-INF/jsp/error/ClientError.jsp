<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!----------------------- ADD/UPDATE ORDER ------------------------------------------------------------>
<!--MESSAGE ABOUT SUCCESSFUL ADD ORDER  -->
	<c:if test="${not empty requestScope.AddOrderSuccess}">
		<h4 style="color: green"><fmt:message key="${requestScope.AddOrderSuccess }"/></h4>
	</c:if>
<!--MESSAGE ERROR ADD_ORDER  -->
	<c:if test="${not empty requestScope.errorAddOrder}">
		<h4 style="color: red"><fmt:message key="${requestScope.errorAddOrder }"/></h4>
	</c:if>
<!--MESSAGE ABOUT SUCCESSFUL ADD ORDER  -->
	<c:if test="${not empty param.UpdateOrderSuccess}">
		<h4 style="color: green"><fmt:message key="${param.UpdateOrderSuccess}"/></h4>
	</c:if>
<!--------------------------------------------------------------------------------------------------->	
	
	
	
<!------------------- GET ALL ORDER BY ID CLIENT ------------------------------------------------------>
<!--MESSAGE IF ORDERS LIST EMPTY GET BY ID -->
	<c:if test="${not empty requestScope.messageOrderListEmpty}">
		<h4 style="color: red;"><fmt:message key="${requestScope.messageOrderListEmpty }"/></h4>
	</c:if>
	
<!--MESSAGE ERROR GET_ALL_ORDER_BY_ID  -->
	<c:if test="${not empty requestScope.errorOrderListEmpty}">
		<h4 style="color: red"><c:out value="${requestScope.errorOrderListEmpty}" /> </h4>
	</c:if>
	
<!--------------------------------------------------------------------------------------------------->	
	
	

<!-----------------------------------REMOVE ORDER----------------------------------------------------->
<!--ERROR REMOVE ORDER  -->
	<c:if test="${not empty requestScope.ErrorOrderRemove}">
		<h4 style="color: red"><fmt:message key="${requestScope.ErrorOrderRemove}"/></h4>
	</c:if>
	
<!--MESSAGE ABOUT SUCCESSFUL REMOVE  -->
	<c:if test="${not empty requestScope.removeOrderSuccess}">
		<h4 style="color: green"><fmt:message key="${requestScope.removeOrderSuccess}"/></h4>
	</c:if>
<!--------------------------------------------------------------------------------------------------->	









</body>
</html>