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

public class RemoveCar implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idCar = Long.parseLong(request.getParameter(ID));
		String page = null;

		ServiceFactory factory = ServiceFactory.getInstance();
		CarService carService = factory.getCarService();

		try {
			carService.removeCar(idCar);
			page = REMOVE_CAR_REDIRECT_PATH_TO_DISPATCHER_JSP;
			response.sendRedirect(page);

		} catch (ServiceException e) {
			request.setAttribute(ERROR_CAR_REMOVE, MESSAGE_ERROR_CAR_REMOVE);

			page = PATH_TO_DISPATCHER_JSP;

			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}

}
