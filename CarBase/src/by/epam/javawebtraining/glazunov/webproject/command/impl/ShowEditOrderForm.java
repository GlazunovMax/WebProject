package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;

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

public class ShowEditOrderForm implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = new Order();
		Long id = Long.parseLong(request.getParameter(ID));
		
		ServiceFactory factory = ServiceFactory.getInstance();
		OrderService orderService = factory.getOrderService();
		String page = null;
		
		try {
			order = orderService.getSingleOrderById(id);
			
			if(order != null){
				request.setAttribute(SINGLE_ORDER, order);
				page = PATH_TO_CLIENT_JSP;
			}else{
				request.setAttribute(ERROR_GET_ORDER_BY_ID, MESSAGE_ERROR_GET_ORDER_BY_ID);
				page = PATH_TO_CLIENT_JSP;
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_SHOW_EDIT_ORDER_FORM, e.getMessage());
			page = PATH_TO_CLIENT_JSP;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
