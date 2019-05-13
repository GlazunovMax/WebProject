package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class GoToRegistration implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roleRegistr = request.getParameter(ROLE_REGISTR);

		request.setAttribute(ROLE_REGISTR, roleRegistr);
		RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_TO_REGISTRATION_JSP);
		dispatcher.forward(request, response);
	}

}
