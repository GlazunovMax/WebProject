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
<!----------------------- ADD/UPDATE ORDER/FEEDBACK ------------------------------------------------------------>
<!--MESSAGE ABOUT SUCCESSFUL ADD ORDER  -->
	<c:if test="${not empty requestScope.AddOrderSuccess}">
		<h4 style="color: green"><fmt:message key="${requestScope.AddOrderSuccess }"/></h4>
	</c:if>
	
<!--MESSAGE ABOUT SUCCESSFUL ADD ORDER  -->
	<c:if test="${not empty param.AddFeedbackSuccess}">
		<h4 style="color: green"><fmt:message key="${param.AddFeedbackSuccess }"/></h4>
	</c:if>
	
<!--MESSAGE ERROR ADD_ORDER  -->
	<c:if test="${not empty requestScope.errorAddOrder}">
		<h4 style="color: red"><fmt:message key="${requestScope.errorAddOrder }"/></h4>
	</c:if>
	
<!--MESSAGE ERROR UPDATE_FEEDBACK  -->
	<c:if test="${not empty requestScope.errorUpdateFeedback}">
		<h4 style="color: red"><c:out value="${requestScope.errorUpdateFeedback}" /> </h4>
	</c:if>
	
<!--MESSAGE ABOUT SUCCESSFUL ADD ORDER  -->
	<c:if test="${not empty param.UpdateOrderSuccess}">
		<h4 style="color: green"><fmt:message key="${param.UpdateOrderSuccess}"/></h4>
	</c:if>
	
<!--MESSAGE ABOUT SUCCESSFUL UPDATE FEEDBACK  -->
	<c:if test="${not empty param.UpdateFeedbackSuccess}">
		<h4 style="color: green"><fmt:message key="${param.UpdateFeedbackSuccess}"/></h4>
	</c:if>
<!--------------------------------------------------------------------------------------------------->	

	
<!------------------- GET ALL ORDER BY ID CLIENT ------------------------------------------------------>
<!--MESSAGE IF ORDERS LIST EMPTY GET BY ID -->
	<c:if test="${not empty requestScope.messageOrderListEmpty}">
		<h4 style="color: red;"><fmt:message key="${requestScope.messageOrderListEmpty }"/></h4>
	</c:if>
	
<!--MESSAGE IF FEEDBACK LIST EMPTY GET BY ID -->
	<c:if test="${not empty requestScope.messageFeedbacksListEmpty}">
		<h4 style="color: red;"><fmt:message key="${requestScope.messageFeedbacksListEmpty}"/></h4>
	</c:if>
	
<!--MESSAGE ERROR GET_ALL_ORDER_BY_ID  -->
	<c:if test="${not empty requestScope.errorOrderListEmpty}">
		<h4 style="color: red"><c:out value="${requestScope.errorOrderListEmpty}" /> </h4>
	</c:if>
	
<!--MESSAGE ERROR GET_FEEDBACK_BY_ID  -->
	<c:if test="${not empty requestScope.errorGetfeedbackById}">
		<h4 style="color: red"><c:out value="${requestScope.errorGetfeedbackById}" /> </h4>
	</c:if>
	
<!--------------------------------------------------------------------------------------------------->	

	

<!-----------------------------------REMOVE ORDER/FEEDBACK----------------------------------------------------->
<!--ERROR REMOVE ORDER  -->
	<c:if test="${not empty requestScope.ErrorOrderRemove}">
		<h4 style="color: red"><fmt:message key="${requestScope.ErrorOrderRemove}"/></h4>
	</c:if>
	
<!--ERROR REMOVE FEEDBACK  -->
	<c:if test="${not empty requestScope.errorFeedbackRemove}">
		<h4 style="color: red"><fmt:message key="${requestScope.errorFeedbackRemove}"/></h4>
	</c:if>
	
<!--MESSAGE ABOUT SUCCESSFUL REMOVE ORDER -->
	<c:if test="${not empty param.removeOrderSuccess}">
		<h4 style="color: green"><fmt:message key="${param.removeOrderSuccess}"/></h4>
	</c:if>
	
<!--MESSAGE ABOUT SUCCESSFUL REMOVE  FEEDBACK -->
	<c:if test="${not empty param.removeFeedbackSuccess}">
		<h4 style="color: green"><fmt:message key="${param.removeFeedbackSuccess}"/></h4>
	</c:if>
<!--------------------------------------------------------------------------------------------------->	

</body>
</html>