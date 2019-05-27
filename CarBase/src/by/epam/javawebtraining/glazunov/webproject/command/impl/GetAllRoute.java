package by.epam.javawebtraining.glazunov.webproject.command.impl;

import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.Route;
import by.epam.javawebtraining.glazunov.webproject.service.CountRowService;
import by.epam.javawebtraining.glazunov.webproject.service.RouteService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

public class GetAllRoute implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Route> routes = null;
		int page = 1;
		int rowsPerPage = 2;
		String pageForward = null;
		
		if(request.getParameter(PAGE) != null){
			page = Integer.parseInt(request.getParameter(PAGE));
		}
		
		ServiceFactory factory = ServiceFactory.getInstance();
		RouteService routeService = factory.getRouteService();
		CountRowService countRowService = factory.getCountRowService();
		
		try {
			routes = routeService.getAllRoute((page-1)*rowsPerPage, rowsPerPage);
			int countRowAllRoute = countRowService.getAllRouteCount();
			int countRows = (int) Math.ceil(countRowAllRoute * 1.0 / rowsPerPage);
			
			if(!routes.isEmpty()){
				request.setAttribute(ROUTE_LIST, routes);
				request.setAttribute(COUNT_ROWS, countRows);
				request.setAttribute(CURRENT_PAGE, page);
				pageForward = PATH_TO_DISPATCHER_JSP;
			}else{
				request.setAttribute(MESSAGE_ROUTE_LIST_EMPTY, MESSAGE_IF_ROUTE_LIST_EMPTY);
				pageForward = PATH_TO_DISPATCHER_JSP;
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_GET_ALL_ROUTE, e.getMessage());
			pageForward = PATH_TO_DISPATCHER_JSP;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageForward);
		dispatcher.forward(request, response);
		
	}

}
