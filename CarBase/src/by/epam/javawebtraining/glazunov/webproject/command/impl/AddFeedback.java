package by.epam.javawebtraining.glazunov.webproject.command.impl;

import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.entity.Feedback;
import by.epam.javawebtraining.glazunov.webproject.service.FeedbackService;
import by.epam.javawebtraining.glazunov.webproject.service.UserService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

public class AddFeedback implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Feedback feedback = new Feedback();
		String page = null;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		FeedbackService feedbackService = factory.getFeedbackService();
		UserService userService = factory.getUserService();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FEEDBACK_DATETIME);
		
		
		try {
			feedback.setText(request.getParameter(FEEDBACK));
			feedback.setDateTime(LocalDateTime.parse(request.getParameter(DATE_FEEDBACK),formatter).plusHours(3));
			feedback.setUser(userService.getUserById(Long.parseLong(request.getParameter(ID))));
			
			feedbackService.addFeedBack(feedback);
			
			if(feedback!= null){
				page = ADD_FEEDBACK_REDIRECT_PATH_TO_CLIENT_JSP;
				response.sendRedirect(page);
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_ADD_FEEDBACK, e.getMessage());
			page = PATH_TO_CLIENT_JSP;
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}	
	}
}
