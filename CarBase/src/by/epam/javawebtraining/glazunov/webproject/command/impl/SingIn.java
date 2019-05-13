package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.User;
import by.epam.javawebtraining.glazunov.webproject.service.UserService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class SingIn implements Command {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		String page = null;
		User user = new User();

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		HttpSession session = request.getSession(true);

		try {
			user = userService.singIn(login, password);

			if (user != null) {
				request.setAttribute(USER, user);
				session.setAttribute(ID, user.getId());
				session.setAttribute(USER_NAME, user.getName());
				session.setAttribute(USER_SURNAME, user.getSurname());
				page = PATH_TO_DEFINE_PAGE_BY_ROLE_JSP;

			} else {
				request.setAttribute(ERROR_SING_IN, MESSAGE_IF_ERROR_SING_IN);
				page = PATH_TO_SING_IN_JSP;
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_DATA, MESSAGE_IF_ERROR_DATA);
			page = PATH_TO_SING_IN_JSP;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}

}
