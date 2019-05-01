package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.service.OrderService;
import by.epam.javawebtraining.glazunov.webproject.service.RouteService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class RemoveRoute implements Command {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idRoute = Long.parseLong(request.getParameter(ID));
		Long idOrder = Long.parseLong(request.getParameter("idOrder"));
		String page = null;
		
		System.out.println("idR - " + idRoute + "  IDorder - " + idOrder);
		ServiceFactory factory = ServiceFactory.getInstance();
		RouteService routeService = factory.getRouteService();
		OrderService orderService = factory.getOrderService();
		
		try {
			routeService.removeRoute(idRoute);
			orderService.removeOrder(idOrder);
			page = REMOVE_ROUTE_REDIRECT_PATH_TO_DISPATCHER_JSP;
			response.sendRedirect(page);
		} catch (ServiceException e) {
			request.setAttribute(ERROR_ROUTE_REMOVE, MESSAGE_ERROR_ROUTE_REMOVE);

			page = PATH_TO_DISPATCHER_JSP;

			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}

}
