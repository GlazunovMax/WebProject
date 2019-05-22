package by.epam.javawebtraining.glazunov.webproject.command.impl;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.Order;
import by.epam.javawebtraining.glazunov.webproject.service.CityService;
import by.epam.javawebtraining.glazunov.webproject.service.OrderService;
import by.epam.javawebtraining.glazunov.webproject.service.UserService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

public class UpdateOrder implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = new Order();
		
		ServiceFactory factory = ServiceFactory.getInstance();
		OrderService orderService = factory.getOrderService();
		CityService cityService = factory.getCityService();
		UserService userService = factory.getUserService();
		
		long cityDep = Long.parseLong(request.getParameter(CITY_DEPARTURE));
		long cityDes = Long.parseLong(request.getParameter(CITY_DESTINATION));	
		long idUser = Long.parseLong(request.getParameter(ID_USER));
		
		String page = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN_ADD_ORDER);
		
		try {
			order.setCountPassenger(Integer.parseInt(request.getParameter(COUNT_PASSENGER)));
			order.setTimeDeparture(LocalDateTime.parse(request.getParameter(TIME_DEPARTURE),formatter).plusHours(3));//new java.sql.Date(order.getTimeDeparture().getTime()));//request.getParameter("time_departure"));// java.sql.Date.valueOf("2019-12-30 07:55:00")); //'2099-12-30 07:55:00'"));
			order.setDeparture(cityService.getCityById(cityDep));
			order.setDestination(cityService.getCityById(cityDes));
			order.setId(Long.parseLong(request.getParameter(ID)));
			order.setUser(userService.getUserById(idUser));
		
			orderService.updateOrder(order);
			if(order!= null){
				page = REDIRECT_UPDATE_PATH_TO_CLIENT_JSP;
				response.sendRedirect(page);
			}
		} catch (ServiceException e){
			request.setAttribute(ERROR_UPDATE_ORDER, e.getMessage());
			forwardToClient(request, response, page);	
		} catch (DateTimeParseException e) {
			request.setAttribute(ERROR_UPDATE_ORDER, MEESAGE_ERROR_DATE_FORMAT);
			forwardToClient(request, response, page);
		} catch (NumberFormatException e) {
			request.setAttribute(ERROR_UPDATE_ORDER, MEESAGE_ERROR_TYPE_COUNT_FORMAT);
			forwardToClient(request, response, page);
		}	
	}

	private void forwardToClient(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
		page = PATH_TO_CLIENT_JSP;
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
