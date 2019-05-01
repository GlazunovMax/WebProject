package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.service.RouteService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class EditMarkRoute implements Command {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter(ID));
		String mark = request.getParameter(SELECT_MARK);
		String page = null;
		/*
		 * System.out.println("мid = " + id); System.out.println("mark = " +
		 * mark); request.setAttribute("editStatus", "edit");
		 */

		ServiceFactory factory = ServiceFactory.getInstance();
		RouteService routeService = factory.getRouteService();

		try {
			routeService.editMarkRoute(id, mark);
			page = PATH_REDIRECT_EDIT_MARK_ROUTE_TO_DRIVER_JSP;
			response.sendRedirect(page);
		} catch (ServiceException e) {
			request.setAttribute(ERROR_EDIT_MARK_ROUTE, e.getMessage());//MESSAGE_ERROR_EDIT_MARK_ROUTE);

			RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_FORWARD_EDIT_MARK_ROUTE_TO_DRIVER_JSP);
			dispatcher.forward(request, response);
		}

	}

}
