package by.epam.javawebtraining.glazunov.webproject.service;

import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

/**
 * @author Glazunov
 * @version 1.0
 *	 Counts the number of rows returned from the database
 */
public interface CountRowService {
	int getAllCarByIdDriverCount(long id) throws ServiceException;
	
	int getAllRouteByIdDriverCount(long id) throws ServiceException;
	
	int getAllOrderByIdClientCount(long id) throws ServiceException;
	
	int getAllOrderWithoutRouteCount() throws ServiceException;

	int getAllRouteCount() throws ServiceException;
	
	int getAllCarCount() throws ServiceException; //status car = GOOD
	
	int getAllFeedbackByIdClientCount(long id) throws ServiceException;

	int getAllFeedbackCount() throws ServiceException;
}
