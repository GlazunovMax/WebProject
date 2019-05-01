package by.epam.javawebtraining.glazunov.webproject.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Glazunov
 * @version 1.0
 */
public interface Command {
	
	/**
	  * @param request - Client request
	  * @param response - Response from server to client
	  * @throws ServletException - exception if the request could not be handled 
	  * @throws IOException - exception if an input or output error is detected when the servlet handles request 
	  */
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
}
