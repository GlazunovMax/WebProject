package by.epam.javawebtraining.glazunov.webproject.command.impl;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.Car;
import by.epam.javawebtraining.glazunov.webproject.entity.User;
import by.epam.javawebtraining.glazunov.webproject.service.CarService;
import by.epam.javawebtraining.glazunov.webproject.service.CountRowService;
import by.epam.javawebtraining.glazunov.webproject.service.UserService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

public class AppointCar implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User driver = null;
		List<Car> cars = null;
		String page = null;
		Long id = Long.parseLong(request.getParameter(ID));
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		CarService carService = factory.getCarService();
		CountRowService countRowService = factory.getCountRowService();
		
		try {
			driver = userService.getUserById(id);
			cars = carService.getAllCar(0, countRowService.getAllCarCount());
			
			if(driver != null){
				request.setAttribute(DRIVER_APPOINT_CAR, driver);
				request.setAttribute(CAR_LIST, cars);
				page = PATH_TO_DISPATCHER_JSP;
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_APPOINT_CAR_LIST, e.getMessage());
			page = PATH_TO_DISPATCHER_JSP;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
		
		
	}

}
