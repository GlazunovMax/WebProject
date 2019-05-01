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
	<style type="text/css"><%@include file="/css/style.css"%></style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/LocalePage.jsp" />
	
	<a href="Controller?command=logout"><fmt:message key="logout"/></a>
	
	
CLIENR+T
	Hello, <c:out value="${sessionScope.userName}"/>
	 <c:out value="${sessionScope.id}"/>
	
	
<!--ALL ORDERS FOR USER 	 -->
	<form class="" action="Controller" method="post">
		<input type="hidden" name="command" value="get_all_order_by_id" /> 
		<input type="hidden" name="id" value="${sessionScope.id}" /> 
		<!-- <input type="hidden" name="pageNumber" value="1" />  -->

		<h3 align="left" >
			<input class="button" type="submit" value="<fmt:message key="allOrder"/>"/>
		</h3>								
	</form>
	
	<!--MESSAGE IF ORDERS LIST EMPTY GET BY ID -->
	<c:if test="${not empty requestScope.messageOrderListEmpty}">
		<fmt:message key="${requestScope.messageOrderListEmpty }"/>
	</c:if>
	
	<!--MESSAGE ERROR GET_ALL_ORDER_BY_ID  -->
	<c:if test="${not empty requestScope.errorOrderListEmpty}">
		<h4><c:out value="${requestScope.errorOrderListEmpty}" /> </h4>
	</c:if>
	
	
	
<!--TABLE WITH LIST ORDERS FOR USER  -->
	<c:if test="${not empty requestScope.orderList}">
	<table border="1">
		<caption><fmt:message key="orders"/></caption>
			
		<tr>
			<th>ID</th>
			<th><fmt:message key="city_departure" /></th>
			<th><fmt:message key="city_destination" /></th>
			<th><fmt:message key="timeDate" /></th>
			<th><fmt:message key="count_pass" /></th>
			<th><fmt:message key="cancel"/></th>
		</tr>
			
		<c:forEach var="order" items="${requestScope.orderList}">
			<tr>
				<td>${order.id}</td>
				<td>${order.departure.cityName}</td>
				<td>${order.destination.cityName}</td>
				<td>${order.timeDeparture}</td>
				<td>${order.countPassenger}</td>
				<td><a href="Controller?id=${order.id}&command=remove_order"><fmt:message key="remove"/></a></td>
			</tr>
			
		</c:forEach>		
	</table>
	</c:if>
	
<!--ERROR REMOVE ORDER  -->
	<c:if test="${not empty requestScope.ErrorOrderRemove}">
		<fmt:message key="${requestScope.ErrorOrderRemove}"/>
		
<!--MESSAGE ABOUT SUCCESSFUL REMOVE  -->
	</c:if><c:if test="${not empty requestScope.removeOrderSuccess}">
		<fmt:message key="${requestScope.removeOrderSuccess}"/>
	</c:if>
	

	
	
<!-- ADD NEW ORDER  -->
<%-- <c:set var="idRe">${sessionScope.id} </c:set>
 --%>
  <h3> <fmt:message key="addOrder"/> </h3>  
	<form class="addOrderForm" action="Controller" method="post">
		<%-- <jsp:useBean id="userService" class="by.epam.javawebtraining.glazunov.webproject.service.impl.UserServiceImpl" scope="application"/>
	 --%>	
		<input type="hidden" name="command" value="add_order" /> 
		<input type="hidden" name="id" value="${sessionScope.id}" /> 
		<%-- <input type="hidden" name="user" value="${userService.getUserById(id)}" /> --%>
		
		
	
		 <table border="1">
		 	<jsp:useBean id="cityService" class="by.epam.javawebtraining.glazunov.webproject.service.impl.CityServiceImpl" scope="application"/>
		 	<tr>
		 		<td><fmt:message key="city_departure"/>:</td>
		 		<td>
			 		<c:choose>
						<c:when test="${empty cityService.getAllCity()}">
							<tr><td><fmt:message key="no_cities"/></td></tr>
						</c:when>
				
						<c:otherwise>
				 			<select name="city_departure">	
				 				<c:forEach var="city_dep" items="${cityService.getAllCity()}">
				 					<option value="<c:out value="${city_dep.id}"/>" > ${city_dep.cityName} </option>
				 				</c:forEach>
				 			</select>
			 			</c:otherwise>
					</c:choose>
		 		</td>
		 	</tr>
		 		
		 	<tr>
		 		<td><fmt:message key="city_destination"/>:</td>
		 		<td>
		 			<c:choose>
						<c:when test="${empty cityService.getAllCity()}">
							<tr><td><fmt:message key="no_cities"/></td></tr>
						</c:when>
				
						<c:otherwise>
				 			<select name="city_destination">
				 				<c:forEach var="city_des" items="${cityService.getAllCity()}">
				 					<option value="<c:out value="${city_des.id}"/>" > ${city_des.cityName} </option>
				 				</c:forEach>
				 			</select>
		 				</c:otherwise>
					</c:choose>
		 		</td>
		 	</tr>
		 	
		 	<tr>
		 		<td><fmt:message key="timeDate"/>:</td>	
		 		<td>
					<input type="text" name="time_departure" value="">
				</td>
		 	</tr>
		 	
		 	<tr>
		 		<td><fmt:message key="count_pass"/>:</td>	
		 		<td>
					<input type="text" name="count_passenger" value="">
				</td>
		 	</tr>		 
		 </table>
		 
		 
	
		<h3 align="left" >
			<input class="" type="submit" value="<fmt:message key="send"/>"/>
		</h3>								
	</form>
<!--MESSAGE ABOUT SUCCESSFUL ADD ORDER  -->
	<c:if test="${not empty requestScope.AddOrderSuccess}">
		<fmt:message key="${requestScope.AddOrderSuccess }"/>
	</c:if>
<!--MESSAGE ERROR ADD_ORDER  -->
	<c:if test="${not empty requestScope.errorAddOrder}">
		<h4><c:out value="${requestScope.errorAddOrder}" /> </h4>
	</c:if>
	
</body>
</html>