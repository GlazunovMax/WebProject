package by.epam.javawebtraining.glazunov.webproject.command.impl;

import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.service.CarService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

public class EditCarCondition implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter(ID));
		String carCondition = request.getParameter(SELECT_STATUS_CAR);
		String page = null;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		CarService carService = factory.getCarService();
		
		
		try {
			carService.editCarCondition(id, carCondition);
			page = PATH_REDIRECT_EDIT_CAR_CONDITION_TO_DRIVER_JSP; 
			response.sendRedirect(page);
		} catch (ServiceException e) {
			request.setAttribute(ERROR_EDIT_CAR_CONDITION, e.getMessage());

			RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_FORWARD_EDIT_CAR_CONDITION_TO_DRIVER_JSP);
			dispatcher.forward(request, response);
		}
	}

}
