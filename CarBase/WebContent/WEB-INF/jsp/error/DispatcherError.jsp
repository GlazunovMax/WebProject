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
<!--------------------------------- ADD CAR --------------------------------------------------------->
<!--MESSAGE ABOUT SUCCESSFUL ADD CAR-->
	<c:if test="${not empty param.AddCarSuccess}">
		<h4 style="color: green"><fmt:message key="${param.AddCarSuccess}"/></h4>
	</c:if>
		 	
<!--MESSAGE ERROR ADD_CAR-->
	<c:if test="${not empty requestScope.errorAddCar}">
	<%-- <fmt:message key="${requestScope.errorAddCar}"/> --%>
	<h4 style="color: red"><c:out value="${requestScope.errorAddCar}" /> </h4>
	</c:if>
<!---------------------------------------------------------------------------------------------------->



<!----------------------------------GET ALL/REMOVE CAR/FEEDBACK------------------------------------------------------->
<!-- MESSAGE IF GET_CARS_ LIST EMPTY -->
	<c:if test="${not empty requestScope.messageCarsListEmpty}">
		<h4 style="color: red"><fmt:message key="${requestScope.messageCarsListEmpty}"/> </h4>
	</c:if>	

<!-- MESSAGE IF GET_FEEDBACKS_ LIST EMPTY -->
	<c:if test="${not empty requestScope.messageFeedbacksListEmpty}">
		<h4 style="color: red"><fmt:message key="${requestScope.messageFeedbacksListEmpty}"/> </h4>
	</c:if>			
	
<!-- MESSAGE ERROR GET ALL CAR -->
	<c:if test="${not empty requestScope.errorGetAllCar}">
		<h4 style="color: red"><c:out value="${requestScope.errorGetAllCar}" /> </h4>
	</c:if>
	
<!-- MESSAGE ERROR GET ALL FEEDBACKs -->
	<c:if test="${not empty requestScope.errorGetAllFeedbacks}">
		<h4 style="color: red"><c:out value="${requestScope.errorGetAllFeedbacks}" /> </h4>
	</c:if>
	
<!--MESSAGE ABOUT SUCCESSFUL REMOVE CAR  -->
	<c:if test="${not empty param.removeCarSuccess}">
		<h4 style="color: green"><fmt:message key="${param.removeCarSuccess }"/></h4>
	</c:if>	
<!--MESSAGE ERROR REMOVE CAR  -->
	<c:if test="${not empty requestScope.errorCarRemove}">
		<fmt:message key="${requestScope.errorCarRemove}"/>
	</c:if>	
<!---------------------------------------------------------------------------------------------------->



<!--------------------------- GET ALL ORDER WITHOUT ROUTES ------------------------------------------->
<!--MESSAGE IF ORDERS WITHOUT ROUTES LIST EMPTY -->
	<c:if test="${not empty requestScope.messageOrdersListEmpty}">
		<h4 style="color: red"><fmt:message key="${requestScope.messageOrdersListEmpty }"/></h4>
	</c:if>

<!--MESSAGE ERROR GET_ALL_ORDER_BY_ID  -->
	<c:if test="${not empty requestScope.errorGetAllOrderWithoutRoute}">
		<h4 style="color: red"><c:out value="${requestScope.errorGetAllOrderWithoutRoute}" /> </h4>
	</c:if>
<!---------------------------------------------------------------------------------------------------->



<!----------------------------------- GET ALL ROUTES - REMOVE- ADD------------------------------------------------->
<!--MESSAGE IF ROUTES LIST EMPTY -->
	<c:if test="${not empty requestScope.messageRouteListEmpty}">
		<h4 style="color: red"><fmt:message key="${requestScope.messageRouteListEmpty }"/></h4>
	</c:if>
	 
<!--MESSAGE ERROR GET_ALL_ROUTE  -->
	<c:if test="${not empty requestScope.errorGetAllRoute}">
		<h4 style="color: red"><c:out value="${requestScope.errorGetAllRoute}" /> </h4>
	</c:if>	
	
	
<!--MESSAGE ABOUT SUCCESSFUL REMOVE ROUTE  -->
	<c:if test="${not empty param.removeRouteSuccess}">
		<h4 style="color: green"><fmt:message key="${param.removeRouteSuccess }"/></h4>
	</c:if>	 
	
<!--MESSAGE ERROR REMOVE ROUTE  -->
	<c:if test="${not empty requestScope.ErrorRouteRemove}">
		<fmt:message key="${requestScope.ErrorRouteRemove}"/>
	</c:if>	
		
<!--MESSAGE ABOUT SUCCESSFUL ADD ROUTE  -->
	<c:if test="${not empty param.addRouteSuccess}">
		<h4 style="color: green"><fmt:message key="${param.addRouteSuccess }"/></h4>
	</c:if>	 
<!---------------------------------------------------------------------------------------------------->



<!-----------------------------------GET ALL DRIVERS -------------------------------------------- -->
<!--MESSAGE ERROR GET_ALL_DRIVERS  -->
	<c:if test="${not empty requestScope.errorDriverList}">
		<h4 style="color: red"><c:out value="${requestScope.errorDriverList}" /> </h4>
	</c:if>	
<!---------------------------------------------------------------------------------------------------->
	
</body>
</html>