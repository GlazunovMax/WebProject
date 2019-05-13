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

<!-------------------------- HEADER ----------------------------------------->
<jsp:include page="/WEB-INF/jsp/fragment/LocalePage.jsp" />
<c:set var="Role" value="Client" scope="request"/>
<jsp:include page="/WEB-INF/jsp/fragment/Header.jsp" />
<!-- --------------------------------------------------------------------- -->	



<!-------------------------------MENU /ALL ORDERS/ADD ORDER/ FOR USER ------------------- -->

<c:set var="id" value="${sessionScope.id}" scope="request"/>	<!-- idClient -->

<div class="left-menu" style="border: thin;">
	<form class="" action="Controller" method="post">
		<input type="hidden" name="command" value="get_all_order_by_id" /> 
		<input type="hidden" name="id" value="${sessionScope.id}" /> 

		<h3 align="left" >
			<input class="button" type="submit" value="<fmt:message key="allOrder"/>"/>
		</h3>								
	</form>
	
	<form class="" action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_add_order" /> 
		<input class="button" type="submit" value="<fmt:message key="addOrder"/>"/>							
	</form>
</div>
<!-- -------------------------------------------------------------------------------------- -->	

	
	
<!---------------------------TABLE WITH LIST ORDERS FOR USER  ----------------------------------------------------------->

<div class="output-table-menu" >
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
			<th><fmt:message key="edit"/></th>
		</tr>
			
		<c:forEach var="order" items="${requestScope.orderList}">
			<tr>
				<td>${order.id}</td>
				<td>${order.departure.cityName}</td>
				<td>${order.destination.cityName}</td>
				<td>${order.timeDeparture}</td>
				<td>${order.countPassenger}</td>
				<td><a href="Controller?id=${order.id}&command=remove_order" onclick="if(!(confirm('Are you sure you want to delete this order?'))) return false"> 
						<fmt:message key="remove"/>
					</a>
				</td>
				<td>
					<form class="" action="Controller" method="get">
						<input type="hidden" name="command" value="show_edit_order_form" /> 
						<input type="hidden" name="id" value="${order.id}" /> 
						<input class="small-button" type="submit" value="<fmt:message key="edit"/>"/>							
					</form>
				</td>	
			</tr>
			
		</c:forEach>		
	</table>
	
	<c:set var="command" value="get_all_order_by_id" scope="request"/>
	<jsp:include page="/WEB-INF/jsp/fragment/Paginate.jsp"/>
	</c:if>
</div>
<!-- ------------------------------------------------------------------------------------------------------------------- -->	


	
<!------------------------------------------------- ADD NEW ORDER ------------------------------------------------------------------------------->
<jsp:useBean id="cityService" class="by.epam.javawebtraining.glazunov.webproject.service.impl.CityServiceImpl" scope="application"/>
		 
<div class="output-table-menu" >
	<c:if test="${not empty requestScope.addNewOrder}">	
  	
	<form class="addOrderForm" action="Controller" method="post">	
		<input type="hidden" name="command" value="add_order" /> 
		<input type="hidden" name="id" value="${sessionScope.id}" /> 
		
		<table border="1">
		<caption><fmt:message key="addOrder"/>  </caption>
		 	<%-- <jsp:useBean id="cityService" class="by.epam.javawebtraining.glazunov.webproject.service.impl.CityServiceImpl" scope="application"/>
		  --%>	<tr>
		 		<td><fmt:message key="city_departure"/>:</td>
		 		<td>
			 		<c:choose>
						<c:when test="${empty cityService.getAllCity()}">
							<tr><td><fmt:message key="no_cities"/></td></tr>
						</c:when>
				
						<c:otherwise>
				 			<select name="city_departure" style="background: #008080">	
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
				 			<select name="city_destination" style="background: #008080">
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
	
		<input class="small-button" type="submit" value="<fmt:message key="send"/>"/>				
	</form>
	</c:if>	
</div>
<!-- ------------------------------------------------------------------------------------------------------------------- -->	

<!--------------------------------------------------------------- UPDATE ORDER FORM ------------------------------------------------------------->
<div class="output-table-menu" >
	<c:if test="${not empty requestScope.singleOrder}">	
	
	<form class="addOrderForm" action="Controller" method="get">	
		<input type="hidden" name="command" value="update_order" /> 
		<input type="hidden" name="idUser" value="${sessionScope.id}" /> 
		<input type="hidden" name="id" value="${requestScope.singleOrder.id}" /> 
		
		<table border="1">
		<caption><fmt:message key="editOrder"/>  </caption>
		 	<%-- <jsp:useBean id="cityService" class="by.epam.javawebtraining.glazunov.webproject.service.impl.CityServiceImpl" scope="application"/>
		 	 --%><tr>
		 		<td><fmt:message key="city_departure"/>:</td>
		 		<td>
				 	<select name="city_departure" style="background: #008080">
				 		<option selected value="${requestScope.singleOrder.departure.id}">${requestScope.singleOrder.departure.cityName}</option>
				 		<c:forEach var="city_dep" items="${cityService.getAllCity()}">
				 			<option value="${city_dep.id}" > ${city_dep.cityName} </option>
				 		</c:forEach>
				  	</select>
		 		</td>
		 	</tr>
		 		
		 	<tr>
		 		<td><fmt:message key="city_destination"/>:</td>
		 		<td>
				 	<select name="city_destination" style="background: #008080">
				 	<option selected value="${requestScope.singleOrder.destination.id}">${requestScope.singleOrder.destination.cityName}</option>
				 		<c:forEach var="city_des" items="${cityService.getAllCity()}">
				 			<option value="${city_des.id}" > ${city_des.cityName} </option>
				 		</c:forEach>
				 	</select>
		 		</td>
		 	</tr>
		 	
		 	<tr>
		 		<td><fmt:message key="timeDate"/>:</td>	
		 		<td>
					<input type="text" name="time_departure" value="${requestScope.singleOrder.timeDeparture}">
				</td>
		 	</tr>
		 	
		 	<tr>
		 		<td><fmt:message key="count_pass"/>:</td>	
		 		<td>
					<input type="text" name="count_passenger" value="${requestScope.singleOrder.countPassenger}">
				</td>
			</tr>		 
		</table>
	
		<input class="small-button" type="submit" value="<fmt:message key="save"/>"/>				
	</form>
	</c:if>
</div>
<!----------------------------------------------------------------------------------------------------------------------------------------  -->


<jsp:include page="/WEB-INF/jsp/error/ClientError.jsp"/>

</body>
</html>