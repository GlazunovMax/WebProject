package by.epam.javawebtraining.glazunov.webproject.command.impl;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.ID;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.dao.impl.DatabaseCarDao;
import by.epam.javawebtraining.glazunov.webproject.dao.impl.DatabaseCountRows;
import by.epam.javawebtraining.glazunov.webproject.entity.Car;
import by.epam.javawebtraining.glazunov.webproject.service.CarService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

public class GetAllCarByIdDriver implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Set<Car> cars = null;
		Long id = Long.parseLong(request.getParameter(ID));

		int page = 1;
		int rowsPerPage = 2;
		
		 if(request.getParameter("page") != null){
	          page = Integer.parseInt(request.getParameter("page"));
		 }
		 
		ServiceFactory factory = ServiceFactory.getInstance();
		CarService carService = factory.getCarService();
		try {
			System.out.println("Allrows =- " + new DatabaseCountRows().getAllCarByIdDriverCount(id));
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			cars = carService.getAllCarByIdDriver(id, (page-1)*rowsPerPage, rowsPerPage);
			int countRowAllCar = new DatabaseCountRows().getAllCarByIdDriverCount(id);
			int countRows = (int) Math.ceil(countRowAllCar * 1.0 / rowsPerPage);//countRows - нужно вывести по условию limit
		       
			request.setAttribute("carsList", cars);
	        request.setAttribute("countRows", countRows);
	        request.setAttribute("currentPage", page);
		} catch (ServiceException | DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Driver.jsp");
		dispatcher.forward(request, response);
		
	}

}
