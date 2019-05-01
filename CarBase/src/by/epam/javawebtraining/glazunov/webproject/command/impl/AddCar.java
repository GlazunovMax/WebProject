package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.Car;
import by.epam.javawebtraining.glazunov.webproject.service.CarService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;
import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class AddCar implements Command{

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Car car = new Car();
		
		ServiceFactory factory = ServiceFactory.getInstance();
		CarService carService = factory.getCarService();
		
		String carMark = request.getParameter("car_mark");
		String carNumber = request.getParameter("car_number");
		
		String page = null;
		
		car.setMark(carMark);
		car.setNumber(carNumber);
		car.setStatusCar(Car.StatusCar.GOOD);
		
		try {
			carService.addCar(car);
			
			if(car!= null){
				page = REDIRECT_PATH_TO_DISPATCHER_JSP;
				response.sendRedirect(page);
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_ADD_CAR, e.getMessage());
			page = PATH_TO_DISPATCHER_JSP;
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}

}
