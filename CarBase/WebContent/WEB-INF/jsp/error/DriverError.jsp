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
<!----------------------------EDIT CAR CONDITION----------------------------------------------------->
<!--MESSAGE SUCCESS EDIT CAR CONDITION-->
	<c:if test="${not empty param.editCarConditionSuccess}">
		<h4 style="color: green"><fmt:message key="${param.editCarConditionSuccess}"/></h4>
	</c:if>	
	
<!--MESSAGE ERROR EDIT CAR CONDITION  -->
	<c:if test="${not empty requestScope.errorEditCarCondition}">
		<h4 style="color: red"><c:out value="${requestScope.errorEditCarCondition}" /> </h4>
	</c:if>
<!--------------------------------------------------------------------------------------------------->


<!--------------------------GET ALL CAR BY DRIVER ID------------------------------------------------------->
<!-- MESSAGE IF GET_CARS_BY_ID_DRIVER LIST EMPTY -->
	<c:if test="${not empty requestScope.messageCarsListEmpty}">
		<h4 style="color: red"><fmt:message key="${requestScope.messageCarsListEmpty}"/></h4>
	</c:if>	
	
<!-- MESSAGE ERROR GET ALL CAR BY_ID_DRIVER -->
	<c:if test="${not empty requestScope.errorGetAllCarByIdDriver}">
		<h4 style="color: red"><c:out value="${requestScope.errorGetAllCarByIdDriver}" /> </h4>
	</c:if>
<!--------------------------------------------------------------------------------------------------->
	
	
	
<!--------------------- GET ALL ROUYE BY ID DRIVER AND CHANGE MARK ROUTE-------------------------- -->
<!--MESSAGE IF ROUTES LIST EMPTY -->
	<c:if test="${not empty requestScope.messageRouteListEmpty}">
		<h4 style="color: red"><fmt:message key="${requestScope.messageRouteListEmpty }"/></h4>
	</c:if>
	 
<!--MESSAGE ERROR GET_ALL_ROUTE_BY_ID  -->
	<c:if test="${not empty requestScope.errorGetAllRoute}">
		<h4 style="color: red"><c:out value="${requestScope.errorGetAllRoute}" /> </h4>
	</c:if>	


<!--MESSAGE SUCCESS EDIT MARK ROUTE  -->
	<c:if test="${not empty param.editMarkRouteSuccess}">
		<h4 style="color: green"><fmt:message key="${param.editMarkRouteSuccess }"/></h4>
	</c:if>
	
<!--MESSAGE ERROR EDIT MARK ROUTE  -->
	<c:if test="${not empty requestScope.errorEditMarkRoute}">
		<h4 style="color: red"><c:out value="${requestScope.errorEditMarkRoute}" /> </h4>
	</c:if>
<!--------------------------------------------------------------------------------------------------->


















	

</body>
</html>