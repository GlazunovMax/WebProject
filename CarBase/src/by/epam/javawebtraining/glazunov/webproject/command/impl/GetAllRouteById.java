package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.Route;
import by.epam.javawebtraining.glazunov.webproject.service.RouteService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class GetAllRouteById implements Command  {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Route> routes = null;
		
		Long id = Long.parseLong(request.getParameter(ID));
		String page = null;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		RouteService routeService = factory.getRouteService();
		
		try {
			routes = routeService.getRouteById(id);
			
			if(!routes.isEmpty()){
				request.setAttribute(ROUTE_LIST, routes);
				//request.setAttribute("driverId", id);
				page = PATH_TO_DRIVER_JSP;
			}else{
				request.setAttribute(MESSAGE_ROUTE_LIST_EMPTY, MESSAGE_IF_ROUTE_LIST_EMPTY);
				page = PATH_TO_DRIVER_JSP;
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_ROUTE_LIST, e.getMessage());
			page = PATH_TO_DRIVER_JSP;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	
	}

}
