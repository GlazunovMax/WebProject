<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error/error.jsp"%>
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

<!----------------------------------- HEADER ---------------------------------------------------------->
	<jsp:include page="/WEB-INF/jsp/fragment/LocalePage.jsp" />
	<c:set var="Role" value="Disparcher" scope="request"/>
	<jsp:include page="/WEB-INF/jsp/fragment/Header.jsp" />
<!--------------------------------------------------------------------------------------------------------->



<!------------------------MENU /REGISTER/GET ALL ROUTE/ GET ALL ORDER/ GET ALL CAR---------------------->
<div class="left-menu" style="border: thin;">

	<form method="POST" action="Controller">
		<input type="hidden" name="command" value="go_to_registration" />
		<input type="hidden" name="roleRegistr" value="driver" />
		<button class="button" type="submit"><fmt:message key="registration as a driver"/></button>
	</form>
		
	<form class="" action="Controller" method="POST">
		<input type="hidden" name="command" value="get_all_order_without_route" /> 
		<input type="hidden" name="id" value="${sessionScope.id}" /> 
		<input class="button" type="submit" value="<fmt:message key="allOrder"/>"/>								
	</form>
	
	<form class="" action="Controller" method="POST">
		<input type="hidden" name="command" value="get_all_route" />  
		<input type="hidden" name="id" value="${sessionScope.id}" />
		<input class="button" type="submit" value="<fmt:message key="allRoute"/>"/>								
	</form>
	
	<form class="" action="Controller" method="POST">
		<input type="hidden" name="command" value="get_all_car" /> 
		<input type="hidden" name="id" value="${sessionScope.id}" /> 
		<input class="button" type="submit" value="<fmt:message key="allCars"/>"/>								
	</form>
	
	<form class="" action="Controller" method="POST">
		<input type="hidden" name="command" value="get_all_driver" /> 
		<input type="hidden" name="id" value="${sessionScope.id}" /> 
		<input class="button" type="submit" value="<fmt:message key="allDrivers"/>"/>								
	</form>
	
	<form class="" action="Controller" method="POST">
		<input type="hidden" name="command" value="get_all_feedback" /> 
		<input class="button" type="submit" value="<fmt:message key="allFeedbacks"/>"/>								
	</form>
	
</div>
<!--------------------------------------------------------------------------------------------------------->



<!------------------------ TABLE ORDERS WITHOUT ROUTE---------------------------------------------->
<c:set var="id" value="${sessionScope.id}" scope="request"/><!--IDDispatcher  -->

<div class="output-table-menu" align="center">

<c:out value="${p}"/>
<c:if test="${not empty requestScope.orderList}">
	<table border="1" bgcolor="gray">
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
			<c:when test="${empty requestScope.orderList}">
				<tr><td><fmt:message key="no_orders"/></td></tr>
			</c:when>
			
			<c:otherwise>
			 	<c:forEach var="order" items="${requestScope.orderList}">
				 	<tr>
						<td>${order.id}</td>
						<td>${order.departure.cityName}</td>
						<td>${order.destination.cityName}</td>
						<td>${order.timeDeparture}</td>
						<td>${order.countPassenger}</td>
						
						<td>
							<a href="Controller?id=${order.id}&command=create_route"><fmt:message key="create_route"/></a>
						</td>
					</tr>
				</c:forEach>		
			</c:otherwise>
		</c:choose>

</table> 

	<c:set var="command" value="get_all_order_without_route" scope="request"/>
	<jsp:include page="/WEB-INF/jsp/fragment/Paginate.jsp"/>
</c:if> 
<!---------------------------------------------------------------------------------------------------------------------------------->
	
		
		
<!---------------------------------------------TABLE ALL ROUTES------------------------------- -->
<c:if test="${not empty requestScope.routeList}">
	<table border="2" bgcolor="">
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
			<c:when test="${empty requestScope.routeList}">
				<tr><td><fmt:message key="no_rotes"/></td></tr>
			</c:when>
			
			<c:otherwise>
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
							
						<td>${route.driver.name}</td>
						<td>${route.driver.surname}</td>
						<td>${route.driver.phoneNumber}</td>
							
						<td>${route.mark}</td>
						<c:if test="${route.mark eq 'DONE'}">
							<td>
								<a href="Controller?id=${route.id}&idOrder=${route.order.id}&command=remove_route" onclick="if(!(confirm('Are you sure you want to delete this route?'))) return false">
									<fmt:message key="remove"/>
								</a>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>	
	</table>
	
	<c:set var="command" value="get_all_route" scope="request"/>
	<jsp:include page="/WEB-INF/jsp/fragment/Paginate.jsp"/>
</c:if>
<!------------------------------------------------------------------------------------------------------------------------------------------------------------------->	
	


<!----------------------------------------TABLE ALL CAR -------------------------------------------------------------------->

<c:if test="${not empty requestScope.carsList}">
<table border="1" bgcolor="pink">
	<caption><fmt:message key="cars"/></caption>
			
		<tr>
			<th><fmt:message key="car_id" /></th>
			<th><fmt:message key="car_mark" /></th>
			<th><fmt:message key="car_number" /></th>
			<th><fmt:message key="status_car" /></th>
			<th><fmt:message key="remove" /></th>
			<%-- <th><fmt:message key="to_appoint" /></th> --%>
		
		</tr>
		
		<c:choose>
			<c:when test="${empty requestScope.carsList}">
				<tr><td><fmt:message key="no_cars"/></td></tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach var="car" items="${requestScope.carsList}">
					<tr>
						<td>${car.id}</td>
						<td>${car.mark}</td>
						<td>${car.number}</td>
						<td>${car.statusCar}</td>
						<td>
							<a href="Controller?id=${car.id}&command=remove_car" onclick="if(!(confirm('Are you sure you want to delete this car?'))) return false">
								<fmt:message key="remove"/>
							</a>
						</td>
						
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
</table>

	<c:set var="command" value="get_all_car" scope="request"/>
	<jsp:include page="/WEB-INF/jsp/fragment/Paginate.jsp"/>	
</c:if>

<!---------------------------------------------------------------------------------------------------------------------------->



<!--------------------------------------- GET ALL FEEDBACKS --------------------------------->
<c:if test="${not empty requestScope.feedbacks}">
	<table border="1" bgcolor="gray">
		<caption><fmt:message key="Feedbacks"/></caption>
		
		<tr>
			<th>ID</th>
			<th><fmt:message key="Feedback" /></th>
			<th><fmt:message key="user" /></th>
		</tr>
		
		<c:choose>
			<c:when test="${empty requestScope.feedbacks}">
				<tr><td><fmt:message key="no_feedbacks"/></td></tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach var="feedback" items="${requestScope.feedbacks}">
					<tr>
						<td>${feedback.id}</td>
						<td>${feedback.text}</td>
						<td>${feedback.user.name} ${feedback.user.surname}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>	
	</table>
	
	<c:set var="command" value="get_all_feedback" scope="request"/>
	<jsp:include page="/WEB-INF/jsp/fragment/Paginate.jsp"/>	
</c:if>
<!--------------------------------------------------------------------------------------------->



<!---------------------------------------ADD CAR---------------------------------------------------->
<jsp:useBean id="userService" class="by.epam.javawebtraining.glazunov.webproject.service.impl.UserServiceImpl" scope="application"/>

	<form class="addForm" action="Controller" method="post">
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
		 	
		 	<tr>
		 		<td><fmt:message key="drivers"/>:</td>	
		 		<td>
			 		<c:forEach var="driver" items="${userService.getAllDriver()}">
			 			<input type="checkbox" name="id" value="${driver.id}"> ${driver.name} ${driver.surname}<Br>						  
					</c:forEach>
   				</td>
		 	</tr>
		 			
		</table>
		
		<input class="small-button" type="submit" value="<fmt:message key="send"/>"/>
	</form> 
	
<jsp:include page="/WEB-INF/jsp/error/DispatcherError.jsp"/>	


<!----------------------------------------------------------------------------------------------------->


<!-- --------------------------------GET ALL DRIVERS ---------------------------------------------->
<c:if test="${not empty requestScope.listDriver}">
<table border="1" bgcolor="#CD853F">
	<caption><fmt:message key="drivers"/></caption>
			
		<tr>
			<th>ID</th>
			<th><fmt:message key="driver_name" /></th>
			<th><fmt:message key="driver_surname" /></th>
			<th><fmt:message key="driver_phone" /></th>
			<th><fmt:message key="driver_role" /></th>
			<th><fmt:message key="car_status" /></th>
		</tr>
		
		
		<c:forEach var="driver" items="${requestScope.listDriver}">
			 <tr>
			 	<td>${driver.id}</td>
				<td>${driver.name}</td>
			 	<td>${driver.surname}</td>
			 	<td>${driver.phoneNumber}</td>
			 	<td>${driver.role}</td>
			 	
			 	<td>
				 	<c:if test="${empty driver.cars}">
				 		<a href="Controller?id=${driver.id}&command=appoint_car"><fmt:message key="appoint"/></a><!-- fgfhf -->
				 	</c:if>
				 	
				 	<c:if test="${not empty driver.cars}">
				 		<select name="car_id" style="background: #CD853F">
						 	
						 	<c:forEach var="car" items="${driver.cars}"> 
						 		<c:choose>
						 			<c:when test="${car.statusCar eq 'GOOD'}">
						 				<option value="<c:out value="${car.id}"/>"> ${car.mark} ${car.number}</option>
						 			</c:when>
						 			
						 			<c:otherwise>
						 				<option value="<c:out value="${car.id}"/>"> ${car.mark} ${car.number} is broken</option>
						 			</c:otherwise>
						 			
						 		</c:choose>
						 	</c:forEach>
						 	
					 	</select>
				 	</c:if>
			 	</td>	
			  	
			</tr>
		</c:forEach>
		
</table>
</c:if>

	
<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->




<!-- -------------------APPOINT CAR FOR DRIVER ------------------------------------------------------------------>
<c:if test="${not empty requestScope.driver}">

<form class="addForm" action="Controller" method="post">
<input type="hidden" name="command" value="add_cars_for_driver" />
<input type="hidden" name="idDriver" value="${requestScope.driver.id}" />

	<table border="1" bgcolor="#CD853F">
		<caption><fmt:message key="drivers"/></caption>
			
			
					
			<tr>
				<th>ID</th>
				<th><fmt:message key="driver" /></th>
				<th><fmt:message key="cars" /></th>
			</tr>
			
			
			<%-- <c:forEach var="newDriver" items="${requestScope.driver}"> --%>
			 <tr>
				 <td>${requestScope.driver.id}</td>
				 <td>${requestScope.driver.name} ${requestScope.driver.surname}</td>
				 	
				 <td>
			 		<c:forEach var="carAp" items="${requestScope.carList}">
			 			<input type="checkbox" name="id" value="${carAp.id}"> ${carAp.mark} ${carAp.number}<Br>						  
					</c:forEach>
				 </td>
			</tr>
			<%-- </c:forEach> --%>
	</table>
	<input class="small-button" type="submit" value="<fmt:message key="send"/>"/>
</form>

</c:if> 

</div>
<!--------------------------------------------------------------------------------------------------------------------------->
</body>
</html>