package by.epam.javawebtraining.glazunov.webproject.command.impl;

import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.*;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.Car;
import by.epam.javawebtraining.glazunov.webproject.service.CarService;
import by.epam.javawebtraining.glazunov.webproject.service.CountRowService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

public class GetAllCarByIdDriver implements Command {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<Car> cars = null;
		Long id = Long.parseLong(request.getParameter(ID));
		String pageForward;
		int page = 1;
		int rowsPerPage = 2;
		
		 if(request.getParameter(PAGE) != null){
	          page = Integer.parseInt(request.getParameter(PAGE));
		 }
		 
		ServiceFactory factory = ServiceFactory.getInstance();
		CarService carService = factory.getCarService();
		CountRowService countRowService = factory.getCountRowService();
		
		try {
			cars = carService.getAllCarByIdDriver(id, (page-1)*rowsPerPage, rowsPerPage);
			
			if(!cars.isEmpty()){
				int countRowAllCar = countRowService.getAllCarByIdDriverCount(id);
				int countRows = (int) Math.ceil(countRowAllCar * 1.0 / rowsPerPage);
			       
				request.setAttribute(CARS_LIST, cars);
		        request.setAttribute(COUNT_ROWS, countRows);
		        request.setAttribute(CURRENT_PAGE, page);
		        pageForward = PATH_TO_DRIVER_JSP;
			}else{
				request.setAttribute(MESSAGE_CARS_LIST_EMPTY, MESSAGE_IF_CARS_LIST_EMPTY);
				pageForward = PATH_TO_DRIVER_JSP;
			}
			
		} catch (ServiceException e) {
			request.setAttribute(ERROR_GET_ALL_CAR_BY_ID_DRIVER, e.getMessage());
			pageForward = PATH_TO_DRIVER_JSP;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageForward);
		dispatcher.forward(request, response);
		
	}

}
