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
<jsp:include page="LocalePage.jsp"/> 
<a href="Controller?command=logout"><fmt:message key="logout"/></a>

 <c:out value="${param.id}"/>
 <jsp:useBean id="userService" class="by.epam.javawebtraining.glazunov.webproject.service.impl.UserServiceImpl" scope="application"/>
		
<!-- CREATE ROUTE TABLE -->
 <form action="Controller" method="post">
 	<input type="hidden" name="command" value="add_route" /> 
	<input type="hidden" name="idOrder" value="${param.id}" /> 
	<%-- <input type="hidden" name="idDriver" value="${sessionScope.id}" /> 
	 --%>	
 	<table border="1">
 	<caption><fmt:message key="create_route"/></caption>	
		<tr>
			<th>ID</th> 
			<th><fmt:message key="id_order" /></th>
			<th><fmt:message key="id_user_driver" /></th>
		</tr>
		
		<tr>
			<td>1</td>
		 	<td><c:out value="${param.id}"/></td>

			<td>
				<c:choose>
					<c:when test="${empty userService.getAllDriver()}">
						<fmt:message key="no_drivers"/>
					</c:when>
					
					<c:otherwise>
						<select name="driver_id">
				 			<c:forEach var="driver" items="${userService.getAllDriver()}">
				 				<option value="<c:out value="${driver.id}"/>"> ${driver.id}</option>		 				  
				 			</c:forEach>
				  		</select>
			  		</c:otherwise>
			  	</c:choose>	
		 	</td>		
 		</tr> 
 	
 	</table>
	<input class="" type="submit" value="<fmt:message key="add_route"/>"/>
 </form>
 
 <!-- TABLE DRIVERS -->
 <table border="1" bgcolor="pink">
	<caption><fmt:message key="drivers"/></caption>
			
		<tr>
			<th>ID</th>
			<th><fmt:message key="driver_name" /></th>
			<th><fmt:message key="driver_surname" /></th>
			<th><fmt:message key="driver_phone" /></th>
			<th><fmt:message key="driver_role" /></th>
			<th><fmt:message key="car_id" /></th>
		</tr>
		
		
		<c:forEach var="driver" items="${userService.getAllDriver()}">
			 <tr>
			 	<td>${driver.id}</td>
				<td>${driver.name}</td>
			 	<td>${driver.surname}</td>
			 	<td>${driver.phoneNumber}</td>
			 	<td>${driver.role}</td>
			 	<td>
				 	<select name="car_id">
					 	<c:forEach var="car" items="${driver.cars}">
					 		<option value="<c:out value="${car.id}"/>"> ${car.id}</option>
					 	</c:forEach>
				 	</select>
			 	</td>	
			</tr>
		</c:forEach>
		<c:if test="${empty userService.getAllDriver()}">
			<tr><td><fmt:message key="no_drivers"/></td></tr>
		</c:if>
</table>


<!-- TABLE CARS --> 
<table border="1" bgcolor="pink">
	 <jsp:useBean id="carService" class="by.epam.javawebtraining.glazunov.webproject.service.impl.CarServiceImpl" scope="application"/>
	 <caption><fmt:message key="cars"/></caption>
			
		<tr>
			<th><fmt:message key="car_id" /></th>
			<th><fmt:message key="car_mark" /></th>
			<th><fmt:message key="car_number" /></th>
			<th><fmt:message key="status_car" /></th>
		</tr>
		
		<c:choose>
			<c:when test="${empty orderService.getAllOrderWithoutRoute()}">
				<tr><td><fmt:message key="no_cars"/></td></tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach var="car" items="${carService.getAllCar()}">
					<tr>
						<td>${car.id}</td>
						<td>${car.mark}</td>
						<td>${car.number}</td>
						<td>${car.statusCar}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
</table>

<!-- TABLE ORDERS -->
<table border="1" bgcolor="green">
	<jsp:useBean id="orderService" class="by.epam.javawebtraining.glazunov.webproject.service.impl.OrderServiceImpl" scope="application"/>
	<caption><fmt:message key="orders"/></caption>
			
		<tr>
			<th>ID</th>
			<th><fmt:message key="city_departure" /></th>
			<th><fmt:message key="city_destination" /></th>
			<th><fmt:message key="timeDate" /></th>
			<th><fmt:message key="count_pass" /></th>
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
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
</table>
 
 
 <%-- 
 			<td>
				 <select name="driver_id">
		 			<c:forEach var="driver" items="${userService.getAllDriver()}">
		 				<option value="<c:out value="${driver.id}"/>"> ${driver.id}</option>
		 			 <td>${driver.name}</td>
		 				<td>${driver.surname}</td>
		 				<td>${driver.phoneNumber}</td>
		 				<td>${driver.role}</td>
		 				  
		 			</c:forEach>
		  	</select>	
		 	</td>	 --%>
 
</body>
</html>