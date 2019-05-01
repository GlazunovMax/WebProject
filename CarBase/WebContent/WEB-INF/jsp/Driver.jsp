<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<jsp:include page="/WEB-INF/jsp/LocalePage.jsp" />
	<a href="Controller?command=logout"><fmt:message key="logout"/></a>


		DRIVER, <c:out value="${sessionScope.userName}"/>
				<c:out value="${sessionScope.userSurname}"/>
				 <c:out value="${sessionScope.id}"/>
		
		
<!--GET ALL ROUTE BY ID_DRIVER  -->
	<form class="" action="Controller" method="post">
		<input type="hidden" name="command" value="get_all_route_by_id" /> 
		<input type="hidden" name="id" value="${sessionScope.id}" /> 
		
		<h3 align="left" >
			<input class="button" type="submit" value="<fmt:message key="allRoute"/>"/>
		</h3>								
	</form>
	
	<!--MESSAGE IF ROUTES LIST EMPTY GET BY ID -->
	<c:if test="${not empty requestScope.messageRouteListEmpty}">
		<fmt:message key="${requestScope.messageRouteListEmpty }"/>
	</c:if>
	
	<!--MESSAGE ERROR GET_ALL_ROUTE_BY_ID  -->
	<c:if test="${not empty requestScope.errorRouteListEmpty}">
		<h4><c:out value="${requestScope.errorRouteListEmpty}" /> </h4>
		<%-- <fmt:message key="${requestScope.errorOrderListEmpty }"/> --%>
	</c:if>
	
		
<!--TABLE WITH LIST ROUTES FOR DRIVER  -->
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
				<%-- <td>${route.order.user.cars}</td>
				 --%>
				
		 		<td colspan="2">
			 		<form class="" action="Controller" method="post">
						<input type="hidden" name="command" value="edit_mark_route" /> 
						<input type="hidden" name="id" value="${route.id}" /> 
						
						<!-- <div class="mark_box"> -->
						<select name="select_mark" style="background: #008080">
							<option selected value="${route.mark}">${route.mark}</option>
		 					<c:forEach items="<%=Mark.values()%>" var="status">
		 						<option value = "${status}">${status}</option>
		  					</c:forEach>
	  					</select>
	  				<!-- 	</div> -->
						<input class="" type="submit" value="<fmt:message key="edit"/>"/>							
					</form>
  				</td>
				
			</tr>
			
		</c:forEach>
		
	</table>
	
	<!--MESSAGE SUCCESS EDIT MARK ROUTE  -->
	<c:if test="${not empty param.editMarkRouteSuccess}">
		<fmt:message key="${param.editMarkRouteSuccess }"/>
	</c:if>
	
	<!--MESSAGE ERROR EDIT MARK ROUTE  -->
	<c:if test="${not empty requestScope.errorEditMarkRoute}">
		<h4><c:out value="${requestScope.errorEditMarkRoute}" /> </h4>
	</c:if>



<!-- All Car's Driver  -->
<table border="1" bgcolor="green" >
	<jsp:useBean id="carService" class="by.epam.javawebtraining.glazunov.webproject.service.impl.CarServiceImpl" scope="application"/>
	<caption><fmt:message key="cars"/></caption>
		<tr>
			<th>ID</th>
			<th><fmt:message key="car_mark" /></th>
			<th><fmt:message key="car_number" /></th>
			<th><fmt:message key="car_condition" /></th>
			<%-- <th><fmt:message key="edit" /></th> --%>
		</tr>	

		<c:set var="idDriver" value="${sessionScope.id}" />
		
		 <%-- <c:choose>
			<c:when test="${empty carService.getAllCarByIdDriver(idDriver)}">
				<tr><td><fmt:message key="no_cars"/></td></tr>
			</c:when>
			
			<c:otherwise>  --%>
			<c:if test="${empty requestScope.carsList}">
				<c:redirect url="http://localhost:8080/CarBase/Controller?command=get_all_car_by_id_driver&id=${idDriver}"/>
			</c:if>
			<%--  <c:forEach var="car" items="${carService.getAllCarByIdDriver(idDriver, 1, 2)}">   --%>
				<c:forEach var="car" items="${requestScope.carsList}">  
				 <tr>
				 	<td>${car.id}</td>
				 	<td>${car.mark}</td>
				 	<td>${car.number}</td>
				 		
				 	<td colspan="2">
					 	<form class="" action="Controller" method="post">
							<input type="hidden" name="command" value="edit_car_condition" /> 
							<input type="hidden" name="id" value="${car.id}" /> 
								
							<select class="select_status_car" style="background: green;">
								<option selected value="${car.statusCar}">${car.statusCar}</option>
				 				<c:forEach items="<%=StatusCar.values()%>" var="status">
				 					<option value = "${status}">${status}</option>
				  				</c:forEach>
			  				</select>
			  					
							<input class="" type="submit" value="<fmt:message key="edit"/>"/>							
						</form>
		  			</td>	
				</tr>
				</c:forEach>
		 <%-- 	</c:otherwise>
		</c:choose> --%>
</table>

<%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="Controller?command=get_all_car_by_id_driver&page=${currentPage - 1}&id=${idDriver}">Previous</a></td>
    </c:if>

<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
    <table border="1" cellpadding="1" cellspacing="1">
        <tr>
            <c:forEach begin="1" end="${countRows}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    
                    <c:otherwise>
                        <td><a href="Controller?command=get_all_car_by_id_driver&page=${i}&id=${idDriver}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>

 <%--For displaying Next link --%>
    <c:if test="${currentPage lt countRows}">
        <td><a href="Controller?command=get_all_car_by_id_driver&page=${currentPage + 1}&id=${idDriver}">Next</a></td>
    </c:if>
 




<!--MESSAGE SUCCESS EDIT CAR CONDITION-->
	<c:if test="${not empty param.editCarConditionSuccess}">
		<%-- <c:out value="${param.editCarConditionSuccess}" /> --%>
		<fmt:message key="${param.editCarConditionSuccess}"/>
	</c:if>	
	
<!--MESSAGE ERROR EDIT CAR CONDITION  -->
	<c:if test="${not empty requestScope.errorEditCarCondition}">
		<h4><c:out value="${requestScope.errorEditCarCondition}" /> </h4>
	</c:if>
		
</body>
</html>