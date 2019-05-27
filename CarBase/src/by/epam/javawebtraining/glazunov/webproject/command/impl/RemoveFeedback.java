package by.epam.javawebtraining.glazunov.webproject.command.impl;

import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.service.FeedbackService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;
import by.epam.javawebtraining.glazunov.webproject.service.factory.ServiceFactory;

public class RemoveFeedback implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idFeedback = Long.parseLong(request.getParameter(ID));
		String page = null;

		ServiceFactory factory = ServiceFactory.getInstance();
		FeedbackService feedbackService = factory.getFeedbackService();
		
		try {
			feedbackService.removeFeedBack(idFeedback);
			
			page = REMOVE_FEEDBACK_REDIRECT_PATH_TO_DISPATCHER_JSP;
			response.sendRedirect(page);
		} catch (ServiceException e) {
			request.setAttribute(ERROR_FEEDBACK_REMOVE, MESSAGE_ERROR_FEEDBACK_REMOVE);

			page = PATH_TO_CLIENT_JSP;

			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
		
	}

}
