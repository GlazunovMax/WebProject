package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.Order;
import by.epam.javawebtraining.glazunov.webproject.service.OrderService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class GetAllOrderById implements Command{
 
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> orders = null;
		Long id = Long.parseLong(request.getParameter(ID));
		String page;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		OrderService orderService = factory.getOrderService();
		
		try {
			orders = orderService.getOrderById(id);
			
			if(!orders.isEmpty()){
				
				request.setAttribute(ORDER_LIST, orders);
				request.setAttribute(USER_ID_PAR, id);
				page = PATH_TO_CLIENT_JSP;
			}else{
				request.setAttribute(MESSAGE_ORDER_LIST_EMPTY, MESSAGE_IF_ORDER_LIST_EMPTY);
				page = PATH_TO_CLIENT_JSP;
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_ORDER_LIST, e.getMessage());
			page = PATH_TO_CLIENT_JSP;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
