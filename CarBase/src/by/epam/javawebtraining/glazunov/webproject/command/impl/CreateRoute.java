package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class CreateRoute implements Command {
	


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter(ID);
		request.setAttribute(ID_ORDER, id);
		String page = PATH_CREATE_ROUTE_TO_CREATE_ROUTE_DISPATCHER_JSP;
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
