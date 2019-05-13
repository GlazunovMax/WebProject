package by.epam.javawebtraining.glazunov.webproject.command.impl;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.ERROR_ORDER_LIST;
import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.PATH_TO_CLIENT_JSP;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.User;
import by.epam.javawebtraining.glazunov.webproject.service.UserService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

public class GetAllDriver implements Command {

	private static final String ERROR_DRIVER_LIST = "errorDriverList";
	private static final String PATH_TO_DISPATCHER_JSP = "/WEB-INF/jsp/Dispatcher.jsp";
	private static final String LIST_DRIVER = "listDriver";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("dhgdjhd");
		List<User> users = null;
		String page = null;
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		try {
			users = userService.getAllDriver();
			
			if(!users.isEmpty()){
				request.setAttribute(LIST_DRIVER, users);
				page = PATH_TO_DISPATCHER_JSP;
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_DRIVER_LIST, e.getMessage());
			page = PATH_TO_DISPATCHER_JSP;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
