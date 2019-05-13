package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.Order;
import by.epam.javawebtraining.glazunov.webproject.service.CountRowService;
import by.epam.javawebtraining.glazunov.webproject.service.OrderService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class GetAllOrderById implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> orders = null;
		Long id = Long.parseLong(request.getParameter(ID));
		String pageForward;
		int page = 1;
		int rowsPerPage = 3;
		
		 if(request.getParameter(PAGE) != null){
	          page = Integer.parseInt(request.getParameter(PAGE));
		 }
		
		ServiceFactory factory = ServiceFactory.getInstance();
		OrderService orderService = factory.getOrderService();
		CountRowService countRowService = factory.getCountRowService();
		
		try {
			orders = orderService.getOrderById(id, (page-1)*rowsPerPage, rowsPerPage);
			
			if(!orders.isEmpty()){
				int countRowAllRoute = countRowService.getAllOrderByIdClientCount(id);
				int countRows = (int) Math.ceil(countRowAllRoute * 1.0 / rowsPerPage);
				
				request.setAttribute(ORDER_LIST, orders);
				request.setAttribute(COUNT_ROWS, countRows);
		        request.setAttribute(CURRENT_PAGE, page);
				pageForward = PATH_TO_CLIENT_JSP;
			}else{
				request.setAttribute(MESSAGE_ORDER_LIST_EMPTY, MESSAGE_IF_ORDER_LIST_EMPTY);
				pageForward = PATH_TO_CLIENT_JSP;
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_ORDER_LIST, e.getMessage());
			pageForward = PATH_TO_CLIENT_JSP;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageForward);
		dispatcher.forward(request, response);
	}

}
