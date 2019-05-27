package by.epam.javawebtraining.glazunov.webproject.command.impl;

import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.Order;
import by.epam.javawebtraining.glazunov.webproject.service.OrderService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

public class CreateRoute implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = null;
		Long id = Long.parseLong(request.getParameter(ID));

		String page;

		ServiceFactory factory = ServiceFactory.getInstance();
		OrderService orderService = factory.getOrderService();

		try {
			order = orderService.getSingleOrderById(id);
		} catch (ServiceException e) {
			request.setAttribute(ERROR_ORDER_BY_ID, e.getMessage());
			page = PATH_CREATE_ROUTE_TO_CREATE_ROUTE_DISPATCHER_JSP;
		}

		request.setAttribute(ID_ORDER, id);
		request.setAttribute(ORDER_HINT, order.getDeparture().getCityName() + " - "
				+ order.getDestination().getCityName() + ". " + order.getTimeDeparture());

		page = PATH_CREATE_ROUTE_TO_CREATE_ROUTE_DISPATCHER_JSP;

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
