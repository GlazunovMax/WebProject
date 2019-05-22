package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class GoToAddOrder implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(MESSAGE_FOR_ADD_ROUTE, MESSAGE_FOR_ADD_ROUTE);
		RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_TO_CLIENT_JSP);
		dispatcher.forward(request, response);
	}

}
