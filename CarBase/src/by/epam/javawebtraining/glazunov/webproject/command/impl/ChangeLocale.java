package by.epam.javawebtraining.glazunov.webproject.command.impl;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class ChangeLocale implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageName = request.getParameter(PAGE);
			
		String language = request.getParameter(LANGUAGE);
		
		Locale locale = new Locale(language);
		
		request.getSession().setAttribute(LOCALE, locale);
		String page;
	
		if(pageName.endsWith("index.jsp")){
			page = "index.jsp";
		}else{
			String pageJsp = pageName.substring(pageName.lastIndexOf("/"));
			page = "/WEB-INF/jsp" + pageJsp;
		}
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
		
	}

}
