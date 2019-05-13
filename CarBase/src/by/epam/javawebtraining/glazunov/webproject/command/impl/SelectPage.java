package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class SelectPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = request.getParameter(PAGE).toUpperCase();
		String id = request.getParameter(ID);
		String page = null;

		// Client
		String addSuccess = request.getParameter(ADD_ORDER_SUCCESS);
		String removeSuccess = request.getParameter(REMOVE_ORDER_SUCCESS);

		switch (role) {
		case CLIENT:
			request.setAttribute(ID, id);
			request.setAttribute(ADD_ORDER_SUCCESS, addSuccess);
			request.setAttribute(REMOVE_ORDER_SUCCESS, removeSuccess);
			page = PATH_TO_CLIENT_JSP;
			break;
		case DRIVER:
			page = PATH_TO_DRIVER_JSP;
			request.setAttribute(ID, id);
			break;
		case DISPATCHER:
			page = PATH_TO_DISPATCHER_JSP;
			request.setAttribute(ID, id);
			break;
		default:
			break;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
