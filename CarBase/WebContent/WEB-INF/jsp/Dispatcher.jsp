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
		
		Disparcher, <c:out value="${sessionScope.userName}"/>
				<c:out value="${sessionScope.userSurname}"/>
				 <c:out value="${sessionScope.id}"/>

<!-- TABLE ORDERS -->				 
<table border="1" bgcolor="gray">
	<jsp:useBean id="orderService" class="by.epam.javawebtraining.glazunov.webproject.service.impl.OrderServiceImpl" scope="application"/>
	<caption class="caption_table"><fmt:message key="orders"/></caption>
			
		<tr>
			<th>ID</th>
			<th><fmt:message key="city_departure" /></th>
			<th><fmt:message key="city_destination" /></th>
			<th><fmt:message key="timeDate" /></th>
			<th><fmt:message key="count_pass" /></th>
			<th><fmt:message key="create_route"/></th>
		</tr>
  	
		
		<c:choose>
			<c:when test="${empty orderService.getAllOrderWithoutRoute()}">
				<tr><td><fmt:message key="no_orders"/></td></tr>
			</c:when>
			
			<c:otherwise>
			 	<c:forEach var="order" items="${orderService.getAllOrderWithoutRoute()}">
				 	<tr>
						<td>${order.id}</td>
						<td>${order.departure.cityName}</td>
						<td>${order.destination.cityName}</td>
						<td>${order.timeDeparture}</td>
						<td>${order.countPassenger}</td>
						<td><a href="Controller?id=${order.id}&command=create_route"><fmt:message key="create_route"/></a></td>
					</tr>
				</c:forEach>		
			</c:otherwise>
		</c:choose>
			 
		
</table> 
		
<!-- TABLE ALL ROUTES -->
	<table border="2" bgcolor="">
		<jsp:useBean id="routeService" class="by.epam.javawebtraining.glazunov.webproject.service.impl.RouteServiceImpl" scope="application"/>
		<caption class="caption_table"><fmt:message key="routes"/></caption>
		
		<tr>
			<th>ID</th>
			<th><fmt:message key="city_departure" /></th>
			<th><fmt:message key="city_destination" /></th>
			<th><fmt:message key="timeDate" /></th>
			<th><fmt:message key="count_pass" /></th>
			
			<th><fmt:message key="client_name"/></th>
			<th><fmt:message key="client_surname"/></th>
			<th><fmt:message key="client_phone"/></th>
			
			<th><fmt:message key="driver_name"/></th>
			<th><fmt:message key="driver_surname"/></th>
			<th><fmt:message key="driver_phone"/></th>
			
			<th><fmt:message key="route_mark"/></th>
			
			<th><fmt:message key="remove"/></th>
		</tr>
		
		<c:choose>
			<c:when test="${empty routeService.getAllRoute()}">
				<tr><td><fmt:message key="no_rotes"/></td></tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach var="route" items="${routeService.getAllRoute()}">
					<tr>
						<td>${route.id}</td>
						<td>${route.order.departure.cityName}</td>
						<td>${route.order.destination.cityName}</td>
						<td>${route.order.timeDeparture}</td>
						<td>${route.order.countPassenger}</td>
							
						<td>${route.order.user.name}</td>
						<td>${route.order.user.surname}</td>
						<td>${route.order.user.phoneNumber}</td>
							
						<td>${route.driver.name}</td>
						<td>${route.driver.surname}</td>
						<td>${route.driver.phoneNumber}</td>
							
						<td>${route.mark}</td>
						<c:if test="${route.mark eq 'DONE'}">
							<td><a href="Controller?id=${route.id}&idOrder=${route.order.id}&command=remove_route"><fmt:message key="remove"/></a></td>
						</c:if>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>	
	</table>
<!--MESSAGE ABOUT SUCCESSFUL REMOVE ROUTE  -->
	<c:if test="${not empty param.removeRouteSuccess}">
		<fmt:message key="${param.removeRouteSuccess }"/>
	</c:if>	 	
		
		ADDCAR	 remove нужно вместе с заявкой Y/N
<!--MESSAGE ABOUT SUCCESSFUL ADD ROUTE  -->
	<c:if test="${not empty param.addRouteSuccess}">
		<fmt:message key="${param.addRouteSuccess }"/>
	</c:if>	 
	




<!--ADD CAR  -->

	<form class="addForm" action="Controller">
		<input type="hidden" name="command" value="add_car" />
	
		<table border="1">
		<caption class="caption_table"><fmt:message key="add_car"/></caption>
			<tr>
		 		<td><fmt:message key="car_mark"/>:</td>	
		 		<td>
					<input type="text" name="car_mark" value="">
				</td>
		 	</tr>
		 	
		 	<tr>
		 		<td><fmt:message key="car_number"/>:</td>	
		 		<td>
					<input type="text" name="car_number" value="">
				</td>
		 	</tr> 
		 			
		</table>
		
		<input class="" type="submit" value="<fmt:message key="send"/>"/>
	</form> 
<!--MESSAGE ABOUT SUCCESSFUL ADD CAR-->
	<c:if test="${not empty param.AddCarSuccess}">
		<fmt:message key="${param.AddCarSuccess}"/>
	</c:if>	 	
<!--MESSAGE ERROR ADD_CAR-->
	<c:if test="${not empty requestScope.errorAddCar}">
		<h4><c:out value="${requestScope.errorAddCar}" /> </h4>
	</c:if>
		
	
	
	<!-- ADD CITY -->
</body>
</html>