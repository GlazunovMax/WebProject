<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error/error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="by.epam.javawebtraining.glazunov.webproject.entity.Route.Mark" %>
<%@ page import="by.epam.javawebtraining.glazunov.webproject.entity.Car.StatusCar" %>
 
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

<!---------------------------- HEADER ----------------------------------------------------------->
	<jsp:include page="/WEB-INF/jsp/fragment/LocalePage.jsp" />
	<c:set var="Role" value="Driver" scope="request"/>
	<jsp:include page="/WEB-INF/jsp/fragment/Header.jsp" />
<!----------------------------------------------------------------------------------------------->

		<!-- ghjпроверить 500 ошибу -->
					<%-- <jsp:useBean id="ob" class="java.lang.String"></jsp:useBean>
				${ob.toString} --%>
		<!--  -->
																													
		
<!----------------------------MENU  /GET ALL ROUTE/GET ALL CAR/ BY ID_DRIVER--------------------->
<c:set var="id" value="${sessionScope.id}" scope="request"/>  <!-- idDriver -->
<div class="left-menu" style="border: thin;">

	<form class="" action="Controller" method="get">
		<input type="hidden" name="command" value="get_all_route_by_id" /> 
		<input type="hidden" name="id" value="${sessionScope.id}" /> 
		
		<h3 align="left" >
			<input class="button" type="submit" value="<fmt:message key="allRoute"/>"/>
		</h3>								
	</form>

<!-- All Car's Driver  -->
	
	<form class="" action="Controller" method="get">
		<input type="hidden" name="command" value="get_all_car_by_id_driver" /> 
		<input type="hidden" name="id" value="${id}" /> 
		
		<h3 align="left" >
			<input class="button" type="submit" value="<fmt:message key="allCars"/>"/>
		</h3>								
	</form>	
	
</div>	
<!------------------------------------------------------------------------------------------------>

	
	
<!-----------------------------TABLE WITH LIST ROUTES FOR DRIVER ------------------------------------------------------>
<div class="output-table-menu" >

<c:if test="${not empty requestScope.routeList}">
	<table border="1">
	<caption><fmt:message key="routes"/> for ${sessionScope.userName} ${sessionScope.userSurname}</caption>
		
		<tr>
			<th>ID</th>
			<th><fmt:message key="city_departure"/></th>
			<th><fmt:message key="city_destination"/></th>
			<th><fmt:message key="timeDate"/></th>
			<th><fmt:message key="count_pass"/></th>
			<th><fmt:message key="client_name"/></th>
			<th><fmt:message key="client_surname"/></th>
			<th><fmt:message key="client_phone"/></th>
			<th><fmt:message key="done"/></th>
			<th><fmt:message key="edit"/></th>
		</tr>
		
		<c:forEach var="route" items="${requestScope.routeList}">
			<tr>
				<td>${route.id}</td>
				<td>${route.order.departure.cityName}</td>
				<td>${route.order.destination.cityName}</td>
				<td>${route.order.timeDeparture}</td>
				<td>${route.order.countPassenger}</td>
				<td>${route.order.user.name}</td>
				<td>${route.order.user.surname}</td>
				<td>${route.order.user.phoneNumber}</td>
					
		 		<td colspan="2">
			 		<form class="" action="Controller" method="post">
						<input type="hidden" name="command" value="edit_mark_route" /> 
						<input type="hidden" name="id" value="${route.id}" /> 
							
						<!-- <div class="mark_box"> -->
						<select name="select_mark" style="background: #008080">
							<option selected value="${route.mark}">${route.mark}</option>
							<%-- <c:forEach items="${Mark.values()}" var="status">  --%>
		 					<c:forEach items="<%=Mark.values()%>" var="status">
		 						<option value = "${status}">${status}</option>
		  					</c:forEach>
	  					</select>
	  				<!-- 	</div> -->
						<input class="small-button" type="submit" value="<fmt:message key="edit"/>"/>							
					</form>
  				</td>
					
			</tr>
				
		</c:forEach>	
	</table>
	
	<c:set var="command" value="get_all_route_by_id" scope="request"/>
	<jsp:include page="/WEB-INF/jsp/fragment/Paginate.jsp" />

</c:if>
<!-- -------------------------------------------------------------------------------------------------------------- -->



<!-----------------------TABLE WITH LIST CARS FOR DRIVER  ALL CARS --------------------------------------->
<c:if test="${not empty requestScope.carsList}">
	<table border="1" bgcolor="green" >
	<caption><fmt:message key="cars"/></caption>
		<tr>
			<th>ID</th>
			<th><fmt:message key="car_mark" /></th>
			<th><fmt:message key="car_number" /></th>
			<th><fmt:message key="car_condition" /></th>
		</tr>	
			
		<c:forEach var="car" items="${requestScope.carsList}">  
		<tr>
			<td>${car.id}</td>
			<td>${car.mark}</td>
			<td>${car.number}</td>
				 		
			<td colspan="2">
				<form class="" action="Controller" method="get">
					<input type="hidden" name="command" value="edit_car_condition" /> 
					<input type="hidden" name="id" value="${car.id}" /> 
								
					<select name="select_status_car" style="background: green;">
						<option selected value="${car.statusCar}">${car.statusCar}</option>
			 			<c:forEach items="<%=StatusCar.values()%>" var="status">
			 				<option value = "${status}">${status}</option>
			  			</c:forEach>
		  			</select>
			  		 	
					<input class="small-button" type="submit" value="<fmt:message key="edit"/>"/>							
				</form>
	  		</td>	
		</tr>
		</c:forEach>
	</table>
		
	<c:set var="command" value="get_all_car_by_id_driver" scope="request"/>
	<jsp:include page="/WEB-INF/jsp/fragment/Paginate.jsp"/>	
</c:if>

</div>
<!-- ------------------------------------------------------------------------------------------------------ -->

<jsp:include page="/WEB-INF/jsp/error/DriverError.jsp" />
			
</body>
</html>