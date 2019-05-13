package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.Route;
import by.epam.javawebtraining.glazunov.webproject.service.OrderService;
import by.epam.javawebtraining.glazunov.webproject.service.RouteService;
import by.epam.javawebtraining.glazunov.webproject.service.UserService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;
import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class AddRoute implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long orderId = Long.parseLong(request.getParameter(ID_ORDER));
		Long driverId = Long.parseLong(request.getParameter(DRIVER_ID));

		Route route = new Route();
		ServiceFactory factory = ServiceFactory.getInstance();
		RouteService routeService = factory.getRouteService();
		UserService userService = factory.getUserService();
		OrderService orderService = factory.getOrderService();
		String page = null;

		route.setMark(Route.Mark.NOT_DONE);
		try {
			route.setDriver(userService.getUserById(driverId));
			route.setOrder(orderService.getSingleOrderById(orderId));

			routeService.addRoute(route);

			if (route != null) {
				page = PATH_ADD_ROUTE_TO_DISPATCHER_JSP;
				response.sendRedirect(page);
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_ADD_ROUTE, e.getMessage());
			page = PATH_ADD_ROUTE_TO_CREATE_ROUTE_DISPATCHER_JSP;
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}

	}

}
