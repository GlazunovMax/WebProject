package by.epam.javawebtraining.glazunov.webproject.command.impl;

import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.Feedback;
import by.epam.javawebtraining.glazunov.webproject.service.CarService;
import by.epam.javawebtraining.glazunov.webproject.service.CountRowService;
import by.epam.javawebtraining.glazunov.webproject.service.FeedbackService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

public class GetFeedbackById implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Feedback> feedbacks = new ArrayList<>();
		
		Long id = Long.parseLong(request.getParameter(ID));
		String pageForward;
		int page = 1;
		int rowsPerPage = 2;
		
		 if(request.getParameter(PAGE) != null){
	          page = Integer.parseInt(request.getParameter(PAGE));
		 }
		 
		ServiceFactory factory = ServiceFactory.getInstance();
		FeedbackService feedbackService = factory.getFeedbackService();
		CountRowService countRowService = factory.getCountRowService();
		

		try {
			feedbacks = feedbackService.getAllFeedbackByClientId(id,(page-1)*rowsPerPage, rowsPerPage);
			
			if(!feedbacks.isEmpty()){
				int countRowAllFeedback = countRowService.getAllFeedbackByIdClientCount(id);
				int countRows = (int) Math.ceil(countRowAllFeedback * 1.0 / rowsPerPage);
				  
				request.setAttribute(FEEDBACKS_LIST, feedbacks);
		        request.setAttribute(COUNT_ROWS, countRows);
		        request.setAttribute(CURRENT_PAGE, page);
		        pageForward = PATH_TO_CLIENT_JSP;
			}else{
				request.setAttribute(MESSAGE_FEEDBACKS_LIST_EMPTY, MESSAGE_IF_FEEDBACK_LIST_EMPTY);
				pageForward = PATH_TO_CLIENT_JSP;
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_GET_ALL_FEEDBACK_BY_ID_DRIVER, e.getMessage());
			pageForward = PATH_TO_CLIENT_JSP;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageForward);
		dispatcher.forward(request, response);
	}

}
