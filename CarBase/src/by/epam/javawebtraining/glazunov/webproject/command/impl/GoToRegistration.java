package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;

public class GoToRegistration implements Command {

	private static final String PATH_TO_REGISTRATION_JSP = "/WEB-INF/jsp/Registration.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_TO_REGISTRATION_JSP);
		dispatcher.forward(request, response);
	}

}
