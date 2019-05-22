package by.epam.javawebtraining.glazunov.webproject.command.impl;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.Car;
import by.epam.javawebtraining.glazunov.webproject.entity.User;
import by.epam.javawebtraining.glazunov.webproject.service.CarService;
import by.epam.javawebtraining.glazunov.webproject.service.UserService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

public class AddCarsForDriver implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		CarService carService = factory.getCarService();
		UserService userService = factory.getUserService();
		User driver;
		String page;
		
		Set<Car> cars = new HashSet<>();
		String carId[] = request.getParameterValues(ID);
		Long idDriver = Long.parseLong(request.getParameter(ID_DRIVER));
		
		
		
		try {
			driver = userService.getUserById(idDriver);
			
			if (carId != null && carId.length != 0) {
				for (int i = 0; i < carId.length; i++) {
					cars.add(carService.getCarById(Long.parseLong(carId[i])));
				}
			}
			
			driver.setCars(cars);
			
			carService.addCarsDriver(driver);
			
			if(driver != null){
				page = REDIRECT_PATH_TO_DISPATCHER_JSP;
				response.sendRedirect(page);
			}			
		}catch (ServiceException e) {
			request.setAttribute(ERROR_ADD_CAR, e.getMessage());
			page = PATH_TO_DISPATCHER_JSP;
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}

}
