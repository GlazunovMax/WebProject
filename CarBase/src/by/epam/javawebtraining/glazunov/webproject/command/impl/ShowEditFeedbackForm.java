package by.epam.javawebtraining.glazunov.webproject.command.impl;

import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.Feedback;
import by.epam.javawebtraining.glazunov.webproject.service.FeedbackService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

public class ShowEditFeedbackForm implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Feedback feedback = new Feedback();
		String page = null;
		
		Long id = Long.parseLong(request.getParameter(ID));
		
		ServiceFactory factory = ServiceFactory.getInstance();
		FeedbackService feedbackService = factory.getFeedbackService();
		
		try {
			feedback = feedbackService.getFeedbackById(id);
			
			if(feedback != null){
				request.setAttribute(SINGLE_FEEDBACK, feedback);
				page = PATH_TO_CLIENT_JSP;
			}else{
				request.setAttribute(ERROR_GET_FEEDBACK_BY_ID, MESSAGE_ERROR_GET_FEEDBACK_BY_ID);
				page = PATH_TO_CLIENT_JSP;
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_SHOW_EDIT_FEEDBACK_FORM, e.getMessage());
			page = PATH_TO_CLIENT_JSP;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
