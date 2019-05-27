<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error/error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.Date" %>

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
	
	<form class="" action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_add_feedback" /> 
		<input type="hidden" name="id" value="${sessionScope.id}" /> 
		
		<input class="button" type="submit" value="<fmt:message key="addFeedback"/>"/>							
	</form>
	
	<form class="" action="Controller" method="post">
		<input type="hidden" name="command" value="get_feedback_by_id" /> 
		<input type="hidden" name="id" value="${sessionScope.id}" /> 
		
		<input class="button" type="submit" value="<fmt:message key="showFeedbacks"/>"/>							
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



<!--------------------------------TABLE WITH LIST FEEDBACK FOR CLIENT -------------------------------------->
<div class="output-table-menu" >
	<c:if test="${not empty requestScope.feedbacks}">
		<table border="1">
		<caption><fmt:message key="Feedbacks"/></caption>
			
		<tr>
			<th>ID</th>
			<th><fmt:message key="Feedback" /></th>
			<th><fmt:message key="date_time"/></th>
			<th><fmt:message key="edit"/></th>
			<th><fmt:message key="remove"/></th>
		</tr>
		
		<c:forEach var="feedback" items="${requestScope.feedbacks}">
			<tr>
				<td>${feedback.id}</td>
				<td>${feedback.text}</td>
				<td>${feedback.dateTime}</td>
				
				<td><a href="Controller?id=${feedback.id}&command=remove_feedback" onclick="if(!(confirm('Are you sure you want to delete this order?'))) return false"> 
						<fmt:message key="remove"/>
					</a>
				</td>
				<td>
					<form class="" action="Controller" method="get">
						<input type="hidden" name="command" value="show_edit_feedback_form" /> 
						<input type="hidden" name="id" value="${feedback.id}" /> 
						<input class="small-button" type="submit" value="<fmt:message key="edit"/>"/>							
					</form>
				</td>	
			</tr>
			
		</c:forEach>
		</table>
		
		<c:set var="command" value="get_feedback_by_id" scope="request"/>
		<jsp:include page="/WEB-INF/jsp/fragment/Paginate.jsp"/>
	
	</c:if>
</div>

<!--------------------------------------------------------------------------------------------------------  -->



<!-----------------------------CURREENT DATETIME FOR FEEDBSCK ----------------- -->
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate var="currentDate" pattern="yyyy-MM-dd HH:mm:ss" value="${now}" />
<!-------------------------------------------------------------------------------->

<!------------------------------ ADD NEW FEEDBACK -------------------------------------------->
<div class="output-table-menu" >
	<c:if test="${not empty requestScope.addNewFeedback}">	
	
		<form class="addFeedbackForm" action="Controller" method="post">	
			<input type="hidden" name="command" value="add_feedback" /> 
			<input type="hidden" name="id" value="${sessionScope.id}" />
			<input type="hidden" name="date" value="${currentDate}"/>
		
			<p><b><fmt:message key="Feedback"/>:</b></p>
  			<p><textarea rows="10" cols="45" name="feedback"></textarea></p>
  			
  			
			
			<input class="small-button" type="submit" value="<fmt:message key="send"/>"/>
		</form>
 	</c:if>
 </div> 
<!------------------------------------------------------------------------------------------->
	
	
	
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



<!---------------------------------------------------------------SHOW/UPDATE ORDER FORM ------------------------------------------------------------->
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
					<%-- <input type="text" name="time_departure" value="${requestScope.singleOrder.timeDeparture}"> --%>
					<input type="text" name="time_departure" value="${requestScope.timeDeparture}">
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


<!--------------------------------- SHOW/UPDATE FEEDBACK FORM -------------------------------------------------->
<div class="output-table-menu" >
	<c:if test="${not empty requestScope.singleFeedback}">	
		<form class="addOrderForm" action="Controller" method="get">	
			<input type="hidden" name="command" value="update_feedback" /> 
			<input type="hidden" name="idClient" value="${sessionScope.id}" /> 
			<input type="hidden" name="id" value="${requestScope.singleFeedback.id}" /> 
			<input type="hidden" name="date" value="${currentDate}">
			
			<table border="1">
				<caption><fmt:message key="editFeedback"/></caption>
				
			<tr>
		 		<td><p><b><fmt:message key="Feedback"/>:</b></p></td>	
		 		<td>
  					<p><textarea rows="10" cols="45" name="feedback">${requestScope.singleFeedback.text}</textarea></p>
				</td>
		 	</tr>
			</table>
			
			
			<input class="small-button" type="submit" value="<fmt:message key="save"/>"/>
		</form>
	</c:if>
</div>

<!--------------------------------------------------------------------------------------------------------------->


<jsp:include page="/WEB-INF/jsp/error/ClientError.jsp"/>

</body>
</html>