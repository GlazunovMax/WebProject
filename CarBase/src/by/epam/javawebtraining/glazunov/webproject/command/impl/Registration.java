package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.User;
import by.epam.javawebtraining.glazunov.webproject.entity.User.Role;
import by.epam.javawebtraining.glazunov.webproject.service.UserService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class Registration implements Command{

		@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		
		user.setName(request.getParameter(NAME));
		user.setSurname(request.getParameter(SURNAME));
		user.setPassword(request.getParameter(PASSWORD));
		user.setLogin(request.getParameter(LOGIN));
		user.setPhoneNumber(request.getParameter(PHONE));
		user.setRole(Role.valueOf(request.getParameter(ROLE).toUpperCase()));
		
		/*user.setOrders(new ArrayList<>());*/
		String page = null;
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		HttpSession session = request.getSession(true);
		
		try {
			userService.registration(user);
			if(user != null){
				request.setAttribute(USER, user);
				session.setAttribute(USER, user.getName());
				page = PATH_TO_CLIENT_JSP;
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_REGISTRATION, e.getMessage());//MESSAGE_ERROR_REGISTRATION= "Incorrect data";);
			page = PATH_TO_REGISTRATION_JSP;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
