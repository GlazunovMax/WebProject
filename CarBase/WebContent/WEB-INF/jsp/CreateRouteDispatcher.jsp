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

<!-----------------------HEDER --------------------------------------------------->
	<c:set var="Role" value="Disparcher" scope="request"/>
	<jsp:include page="/WEB-INF/jsp/fragment/Header.jsp" />
<!-- ------------------------------------------------------------------------- -->


		
<!-------------------------------- CREATE ROUTE TABLE ------------------------------------------------------------------------------>
<jsp:useBean id="userService" class="by.epam.javawebtraining.glazunov.webproject.service.impl.UserServiceImpl" scope="application"/>

<div class="output-table-menu" align="center">
 <form action="Controller" method="post">
 	<input type="hidden" name="command" value="add_route" /> 
	<input type="hidden" name="idOrder" value="${param.id}" /> 
	<%-- <input type="hidden" name="idDriver" value="${sessionScope.id}" /> 
	 --%>	
 	<table border="1" bgcolor="#00FFFF">
 	<caption><fmt:message key="create_route"/></caption>	
		<tr>
			<th>ID</th> 
			<th><fmt:message key="id_order" /></th>
			<th><fmt:message key="id_user_driver" /></th>
		</tr>
		
		<tr>
			<td>1</td>
			
		 	<td style="text-align: center;"><abbr title="${orderHint}"><c:out value="${param.id}" /></abbr></td>
			
			<td>
				<c:choose>
					<c:when test="${empty userService.getAllDriver()}">
						<fmt:message key="no_drivers"/>
					</c:when>
					
					<c:otherwise>
						<select name="driver_id" style="background: #00FFFF">
				 			<c:forEach var="driver" items="${userService.getAllDriver()}">
				 				 <option value="<c:out value="${driver.id}"/>"> ${driver.name} ${driver.surname}</option>				  
				 			</c:forEach>
				  		</select>
			  		</c:otherwise>
			  	</c:choose>	
		 	</td>		
 		</tr> 
 	
 	</table>
	<input class="small-button" type="submit" style="background: #00FFFF" value="<fmt:message key="add_route"/>"/>
 </form>
 
 <!--------------------- error Hint -------------------------------->
	<c:if test="${not empty requestScope.errorOrderById}">
		<h4><c:out value="${requestScope.errorOrderById}" /> </h4>
	</c:if>
 <!------------------------------------------------------------------>
 
 
 
 
 <!--------------------------------------------------- TABLE DRIVERS ----------------------------------------------------->
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
		
		
		<c:forEach var="driver" items="${userService.getAllDriver()}">
			 <tr>
			 	<td>${driver.id}</td>
				<td>${driver.name}</td>
			 	<td>${driver.surname}</td>
			 	<td>${driver.phoneNumber}</td>
			 	<td>${driver.role}</td>
			 	<td>
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
			 	</td>	
			</tr>
		</c:forEach>
		<c:if test="${empty userService.getAllDriver()}">
			<tr><td><fmt:message key="no_drivers"/></td></tr>
		</c:if>
</table>
<!------------------------------------------------------------------------------------------------------------------------------  -->

<!--------------MESSAGE ERROR GET_CARS_BY_ID_DRIVER --------------- -->
	<c:if test="${not empty requestScope.errorGetAllCar}">
		<h4><c:out value="${requestScope.errorGetAllCar}" /> </h4>
	</c:if>	
<!-----------------------------------------------------------------  -->

</div>

</body>
</html>